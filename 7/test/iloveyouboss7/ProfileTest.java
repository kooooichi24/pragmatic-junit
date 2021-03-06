package iloveyouboss7;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class ProfileTest {

    private Profile profile;
    private Criteria criteria;

    private Question questionReimbursesTuition;
    private Answer answerReimbursesTuition;
    private Answer answerDoesNotReimburseTuition;

    private Question questionIsThereRelocation;
    private Answer answerThereIsRelocation;
    private Answer answerThereIsNoRelocation;

    private Question questionOnsiteDaycare;
    private Answer answerNoOnsiteDaycare;
    private Answer answerHasOnsiteDaycare;

    @Before
    public void createProfile() {
        profile = new Profile("Bull Hockey, Inc.");
    }

    @Before
    public void createCriteria() {
        criteria = new Criteria();
    }

    @Before
    public void createQuestionsAndAnswers() {
        questionIsThereRelocation = new BooleanQuestion(1, "Relocation package?");
        answerThereIsRelocation = new Answer(questionIsThereRelocation, Bool.TRUE);
        answerThereIsNoRelocation = new Answer(questionIsThereRelocation, Bool.FALSE);

        questionReimbursesTuition = new BooleanQuestion(1, "Reimburses tuition?");
        answerReimbursesTuition = new Answer(questionReimbursesTuition, Bool.TRUE);
        answerDoesNotReimburseTuition = new Answer(questionReimbursesTuition, Bool.FALSE);

        questionOnsiteDaycare = new BooleanQuestion(1, "Onsite daycare?");
        answerHasOnsiteDaycare = new Answer(questionOnsiteDaycare, Bool.TRUE);
        answerNoOnsiteDaycare = new Answer(questionOnsiteDaycare, Bool.FALSE);
    }

    // 必須の条件にマッチしない場合、matchesはfalseを返す
    @Test
    public void matchAnswersFalseWhenMustMatchCriteriaNotMet() {

        // arrange
        profile.add(answerDoesNotReimburseTuition);
        criteria.add(
                new Criterion(answerReimbursesTuition, Weight.MustMatch));

        // act
        boolean matches = profile.matches(criteria);

        // assert
        assertFalse(matches);
    }

    // 不問の条件があればmatchesはtrueを返す
    @Test
    public void matchAnswersTrueForAnyDontCareCriteria() {

        // arrange
        profile.add(answerDoesNotReimburseTuition);
        criteria.add(
                new Criterion(answerReimbursesTuition, Weight.DontCare));

        // act
        boolean matches = profile.matches(criteria);

        // assert
        assertTrue(matches);
    }

    @Test
    public void matchAnswersFalseWhenNoneOfMultipleCriteriaMatch() {

        // arrange
        profile.add(answerThereIsNoRelocation);
        profile.add(answerDoesNotReimburseTuition);
        criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
        criteria.add(new Criterion(answerReimbursesTuition, Weight.Important));

        // act
        boolean matches = profile.matches(criteria);

        // assert
        assertFalse(matches);
    }

    @Test
    public void scoreIsZeroWhenThereAreNoMatches() {

        // arrange
        profile.add(answerThereIsNoRelocation);
        criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));

        // act
        profile.matches(criteria);

        // assert
        assertThat(profile.score(), equalTo(0));
    }

    @Test
    public void scoreIsCriterionValueForSingleMatch() {

        // arrange
        profile.add(answerThereIsRelocation);
        criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));

        // act
        profile.matches(criteria);

        // assert
        assertThat(profile.score(), equalTo(Weight.Important.getValue()));
    }

    @Test
    public void scoreAccumulatesCriterionValuesForMatches() {

        // arrange
        profile.add(answerThereIsRelocation);
        profile.add(answerReimbursesTuition);
        profile.add(answerNoOnsiteDaycare);
        criteria.add(new Criterion(answerThereIsRelocation, Weight.Important));
        criteria.add(new Criterion(answerReimbursesTuition, Weight.WouldPrefer));
        criteria.add(new Criterion(answerHasOnsiteDaycare, Weight.VeryImportant));

        // act
        profile.matches(criteria);

        // assert
        int expectedScore = Weight.Important.getValue() + Weight.WouldPrefer.getValue();
        assertThat(profile.score(), equalTo(expectedScore));
    }

    int[] ids(Collection<Answer> answers) {
        return answers.stream()
                .mapToInt(a -> a.getQuestion().getId()).toArray();
    }

    // 条件に基づいて回答を検索する
    @Test
    public void findsAnswersBasedOnPredicate() {
        profile.add(new Answer(new BooleanQuestion(1, "1"), Bool.FALSE));
        profile.add(new Answer(new PercentileQuestion(2, "2", new String[]{}), 0));
        profile.add(new Answer(new PercentileQuestion(3, "3", new String[]{}), 0));

        List<Answer> answers = profile.find(a->a.getQuestion().getClass() == PercentileQuestion.class);

        assertThat(ids(answers), equalTo(new int[] {2, 3}));

        List<Answer> answersComplement = profile.find(a->a.getQuestion().getClass() != PercentileQuestion.class);

        List<Answer> allAnswers = new ArrayList<Answer>();
        allAnswers.addAll(answersComplement);
        allAnswers.addAll(answers);

        assertThat(ids(allAnswers), equalTo(new int[] {1, 2, 3}));
    }

    @Test
    public void findAnswers() {
        int dataSize = 5000;
        for (int i = 0; i < dataSize; i++) {
            profile.add(new Answer(new BooleanQuestion(i, String.valueOf(i)), Bool.FALSE));
        }
        profile.add(new Answer(new PercentileQuestion(dataSize, String.valueOf(dataSize), new String[] {}), 0));

        int numberOfTimes = 1000;
        long elapsedMs = run(numberOfTimes, () -> profile.find(a -> a.getQuestion().getClass() == PercentileQuestion.class));

        assertTrue(elapsedMs < 1000);
    }

    private long run(int times, Runnable func) {
        long start = System.nanoTime();
        for (int i = 0; i < times; i++) {
            func.run();
        }
        long stop = System.nanoTime();
        return (stop - start) / 1000000;
    }
}