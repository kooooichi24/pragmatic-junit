package scratch;

import org.junit.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.hamcrest.number.IsCloseTo.*;

public class AssertHamcrestTest {

    @Test
    @ExpectToFail
    @Ignore
    public void assertDoubleEqual() {
        assertThat(2.32 * 3, equalTo(6.96));
    }

    @Test
    public void assertWithTolerance() {
        assertTrue(Math.abs((2.32 * 3) - 6.96) < 0.0005);
    }

    @Test
    public void assertDoubleCloseTo() {
        assertThat(2.32 * 3, closeTo(6.96, 0.0005));
    }
}
