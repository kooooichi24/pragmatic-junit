package iloveyouboss4;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.*;

public class ScoreCollectionTest {

    // 2つの数値の算術平均を返す
    @Test
    public void answerArithmeticMeanOfTwoNumbers() {
        // arrange
        ScoreCollection collection = new ScoreCollection();
        collection.add(() -> 5);
        collection.add(() -> 7);

        // act
        int actual = collection.arithmeticMean();

        // assert
        assertThat(actual, equalTo(6));

    }
}