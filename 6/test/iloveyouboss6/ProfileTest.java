package iloveyouboss6;

import iloveyouboss7.Answer;
import iloveyouboss7.Bool;
import iloveyouboss7.BooleanQuestion;
import iloveyouboss7.Criteria;
import iloveyouboss7.Criterion;
import iloveyouboss7.Profile;
import iloveyouboss7.Question;
import iloveyouboss7.Weight;
import org.junit.Test;

public class ProfileTest {

    // 必須の条件にマッチしない場合、matchesはfalseを返す
    @Test
    public void test() {

        // arrange
        iloveyouboss7.Profile profile = new Profile("Bull Hockey, Inc.");
        Question question = new BooleanQuestion(1, "ボーナスは支給されますか?");
        iloveyouboss7.Criteria criteria = new Criteria();
        iloveyouboss7.Answer criteriaAnswer = new Answer(question, Bool.TRUE);
        iloveyouboss7.Criterion criterion = new Criterion(criteriaAnswer, Weight.MustMatch);
        criteria.add(criterion);

        // act

        // assert
    }
}