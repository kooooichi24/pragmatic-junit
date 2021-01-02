package scratch;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static scratch.ConstrainsSidesTo.constrainsSidesTo;
import org.junit.*;

public class RectangleTest {

    private Rectangle rectangle;

    @After
    public void ensureInvariant() {
        assertThat(rectangle, constrainsSidesTo(100));
    }

    // 面積を返す
    @Test
    public void answersArea() {
        rectangle = new Rectangle(new Point(5, 5), new Point(15, 10));
        assertThat(rectangle.area(), equalTo(50));
    }

    // 動的に座標を変更できる
    @Test
    @ExpectToFail
    @Ignore
    public void allowsDynamicallyChangingSize() {
        rectangle = new Rectangle(new Point(5, 5));
        rectangle.setOppositeCorner(new Point(130, 130));
        assertThat(rectangle.area(), equalTo(15625));
    }
}