package iloveyouboss7;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ScoreCollectionTest {

    ScoreCollection collection;

    @Before
    public void create() {
        collection = new ScoreCollection();
    }

    // 2つの数値の算術平均を返す
    @Test
    public void answersArithmeticMeanOfTwoNumbers() {

        collection.add(() -> 5);
        collection.add(() -> 7);

        int actualResult = collection.arithmeticMean();

        assertThat(actualResult, equalTo(6));
    }

    // nullを追加すると例外が発生する
    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionWhenAddingNull() {

        collection.add(null);
    }

    // 何も追加されていない場合はゼロを返す
    @Test
    public void answersZeroWhenNoElementsAdded() {

        assertThat(collection.arithmeticMean(), equalTo(0));
    }

    // Integerとしてのオーバーフローに対処する
    @Test
    public void dealsWithIntegerOverflow() {

        collection.add(() -> Integer.MAX_VALUE);
        collection.add(() -> 1);

        assertThat(collection.arithmeticMean(), equalTo(1073741824));
    }

    // Integerとしてのオーバーフローには対処しない
    @Test
    @Ignore
    public void doesNotProperlyHandleIntegerOverflow() {

        collection.add(() -> Integer.MAX_VALUE);
        collection.add(() -> 1);

        assertTrue(collection.arithmeticMean() < 0);
    }
}