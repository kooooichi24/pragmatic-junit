package iloveyouboss7;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProfileTest {

    private Profile profile;
    private Question question;
    private Criteria criteria;

    @Before
    public void create() {
        profile = new Profile("Bull Hockey, Inc.");
        question = new BooleanQuestion(1, "ボーナスは支給されますか?");
        criteria = new Criteria();
    }

    // 必須の条件にマッチしない場合、matchesはfalseを返す
    @Test
    public void matchAnswersFalseWhenMustMatchCriteriaNotMet() {

        // arrange
        profile.add(new Answer(question, Bool.FALSE));
        criteria.add(
                new Criterion(new Answer(question, Bool.TRUE), Weight.MustMatch));

        // act
        boolean matches = profile.matches(criteria);

        // assert
        assertFalse(matches);
    }

    // 不問の条件があればmatchesはtrueを返す
    @Test
    public void matchAnswersTrueForAnyDontCareCriteria() {

        // arrange
        profile.add(new Answer(question, Bool.FALSE));
        criteria.add(
                new Criterion(new Answer(question, Bool.TRUE), Weight.DontCare));

        // act
        boolean matches = profile.matches(criteria);

        // assert
        assertTrue(matches);
    }
}