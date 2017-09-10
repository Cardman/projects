package code.maths.geo;
import static code.util.opers.EquallableUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

@SuppressWarnings("static-method")
public class RectTest {

    @Test
    public void intersect1Test() {
        Rect rOne_ = new Rect(1, 1, 2, 2);
        Rect rTwo_ = new Rect(5, 5, 2, 2);
        Rect int_ = rOne_.intersect(rTwo_);
        assertTrue(int_.isEmpty());
    }

    @Test
    public void intersect2Test() {
        Rect rOne_ = new Rect(1, 1, 2, 2);
        Rect rTwo_ = new Rect(2, 2, 2, 2);
        Rect int_ = rOne_.intersect(rTwo_);
        assertEq(2, int_.getLeft());
        assertEq(2, int_.getTop());
        assertEq(1, int_.getWidth());
        assertEq(1, int_.getHeight());
    }

    @Test
    public void intersect3Test() {
        Rect rOne_ = new Rect(1, 1, 4, 4);
        Rect rTwo_ = new Rect(2, 2, 2, 2);
        Rect int_ = rOne_.intersect(rTwo_);
        assertEq(2, int_.getLeft());
        assertEq(2, int_.getTop());
        assertEq(2, int_.getWidth());
        assertEq(2, int_.getHeight());
    }

    @Test
    public void intersect4Test() {
        Rect rOne_ = new Rect(1, 1, 2, 2);
        Rect rTwo_ = new Rect(2, 3, 2, 2);
        Rect int_ = rOne_.intersect(rTwo_);
        assertTrue(int_.isEmpty());
    }

    @Test
    public void intersect5Test() {
        Rect rOne_ = new Rect(1, 1, 2, 3);
        Rect rTwo_ = new Rect(2, 3, 2, 3);
        Rect int_ = rOne_.intersect(rTwo_);
        assertEq(2, int_.getLeft());
        assertEq(3, int_.getTop());
        assertEq(1, int_.getWidth());
        assertEq(1, int_.getHeight());
    }

    @Test
    public void intersect6Test() {
        Rect rOne_ = new Rect(1, 1, 2, 3);
        Rect rTwo_ = new Rect(2, 0, 2, 4);
        Rect int_ = rOne_.intersect(rTwo_);
        assertEq(2, int_.getLeft());
        assertEq(1, int_.getTop());
        assertEq(1, int_.getWidth());
        assertEq(3, int_.getHeight());
    }

    @Test
    public void intersect7Test() {
        Rect rOne_ = new Rect(1, 1, 2, 2);
        Rect rTwo_ = new Rect(3, 2, 2, 2);
        Rect int_ = rOne_.intersect(rTwo_);
        assertTrue(int_.isEmpty());
    }

    @Test
    public void intersect8Test() {
        Rect rOne_ = new Rect(2, 3, 2, 2);
        Rect rTwo_ = new Rect(1, 1, 2, 2);
        Rect int_ = rOne_.intersect(rTwo_);
        assertTrue(int_.isEmpty());
    }

    @Test
    public void intersect9Test() {
        Rect rOne_ = new Rect(3, 2, 2, 2);
        Rect rTwo_ = new Rect(1, 1, 2, 2);
        Rect int_ = rOne_.intersect(rTwo_);
        assertTrue(int_.isEmpty());
    }

    @Test
    public void intersect10Test() {
        Rect rOne_ = new Rect(3, -2, 2, 2);
        Rect rTwo_ = new Rect(1, 1, 2, 2);
        Rect int_ = rOne_.intersect(rTwo_);
        assertTrue(int_.isEmpty());
    }

    @Test
    public void intersect11Test() {
        Rect rOne_ = new Rect(5, 5, 2, 2);
        Rect rTwo_ = new Rect(1, 1, 2, 2);
        Rect int_ = rOne_.intersect(rTwo_);
        assertTrue(int_.isEmpty());
    }

    @Test
    public void intersect12Test() {
        Rect rOne_ = new Rect(5, 5, 2, 2);
        Rect rTwo_ = new Rect(4, 1, 2, 2);
        Rect int_ = rOne_.intersect(rTwo_);
        assertTrue(int_.isEmpty());
    }

}
