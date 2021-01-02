package util;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.*;
import scratch.*;

public class SparseArrayTest {

    private SparseArray<Object> array;

    @Before
    public void create() {
        array = new SparseArray<>();
    }

//    @Test
//    public void answersElementAtIndex() {
//        array.put(5, "five");
//        assertThat(array.get(5), equalTo("five"));
//    }

    @Test
    @ExpectToFail
    @Ignore
    public void handlesInsertionInDescendingOrder() {
        array.put(7, "七");
        array.checkInvariants();
        array.put(6, "六");
        array.checkInvariants();
        assertThat(array.get(6), equalTo("六"));
        assertThat(array.get(7), equalTo("七"));
    }
}