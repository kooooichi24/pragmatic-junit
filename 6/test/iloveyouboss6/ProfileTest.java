package iloveyouboss6;

import org.junit.Test;

public class ProfileTest {

    // 必須の条件にマッチしない場合、matchesはfalseを返す
    @Test
    public void test() {

        // arrange
        Profile profile = new Profile("Bull Hockey, Inc.");
        Question question = new BooleanQuestion(1, "ボーナスは支給されますか?");
        Criteria criteria = new Criteria();
        Answer criteriaAnswer = new Answer(question, Bool.TRUE);
        Criterion criterion = new Criterion(criteriaAnswer, Weight.MustMatch);
        criteria.add(criterion);

        // act

        // assert
    }
}