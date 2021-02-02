package code.maths.geo;

import code.maths.EquallableMathUtil;
import org.junit.Test;


public class RectTest extends EquallableMathUtil {

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

    @Test
    public void intersects1Test() {
        Rect rOne_ = new Rect(1, 1, 2, 2);
        Rect rTwo_ = new Rect(5, 5, 2, 2);
        boolean int_ = rOne_.intersects(rTwo_);
        assertTrue(!int_);
    }

    @Test
    public void intersects2Test() {
        Rect rOne_ = new Rect(1, 1, 2, 2);
        Rect rTwo_ = new Rect(2, 2, 2, 2);
        boolean int_ = rOne_.intersects(rTwo_);
        assertTrue(int_);
    }

    @Test
    public void intersects3Test() {
        Rect rOne_ = new Rect(1, 1, 4, 4);
        Rect rTwo_ = new Rect(2, 2, 2, 2);
        boolean int_ = rOne_.intersects(rTwo_);
        assertTrue(int_);
    }

    @Test
    public void intersects4Test() {
        Rect rOne_ = new Rect(1, 1, 2, 2);
        Rect rTwo_ = new Rect(2, 3, 2, 2);
        boolean int_ = rOne_.intersects(rTwo_);
        assertTrue(!int_);
    }

    @Test
    public void intersects5Test() {
        Rect rOne_ = new Rect(1, 1, 2, 3);
        Rect rTwo_ = new Rect(2, 3, 2, 3);
        boolean int_ = rOne_.intersects(rTwo_);
        assertTrue(int_);
    }

    @Test
    public void intersects6Test() {
        Rect rOne_ = new Rect(1, 1, 2, 3);
        Rect rTwo_ = new Rect(2, 0, 2, 4);
        boolean int_ = rOne_.intersects(rTwo_);
        assertTrue(int_);
    }

    @Test
    public void intersects7Test() {
        Rect rOne_ = new Rect(1, 1, 2, 2);
        Rect rTwo_ = new Rect(3, 2, 2, 2);
        boolean int_ = rOne_.intersects(rTwo_);
        assertTrue(!int_);
    }

    @Test
    public void intersects8Test() {
        Rect rOne_ = new Rect(2, 3, 2, 2);
        Rect rTwo_ = new Rect(1, 1, 2, 2);
        boolean int_ = rOne_.intersects(rTwo_);
        assertTrue(!int_);
    }

    @Test
    public void intersects9Test() {
        Rect rOne_ = new Rect(3, 2, 2, 2);
        Rect rTwo_ = new Rect(1, 1, 2, 2);
        boolean int_ = rOne_.intersects(rTwo_);
        assertTrue(!int_);
    }

    @Test
    public void intersects10Test() {
        Rect rOne_ = new Rect(3, -2, 2, 2);
        Rect rTwo_ = new Rect(1, 1, 2, 2);
        boolean int_ = rOne_.intersects(rTwo_);
        assertTrue(!int_);
    }

    @Test
    public void intersects11Test() {
        Rect rOne_ = new Rect(5, 5, 2, 2);
        Rect rTwo_ = new Rect(1, 1, 2, 2);
        boolean int_ = rOne_.intersects(rTwo_);
        assertTrue(!int_);
    }

    @Test
    public void intersects12Test() {
        Rect rOne_ = new Rect(5, 5, 2, 2);
        Rect rTwo_ = new Rect(4, 1, 2, 2);
        boolean int_ = rOne_.intersects(rTwo_);
        assertTrue(!int_);
    }

    @Test
    public void containsInside1Test() {
        Rect rOne_ = new Rect(5, 5, 2, 2);
        CustPoint pt_ = new CustPoint();
        pt_.setXcoords(6);
        pt_.setYcoords(6);
        boolean int_ = rOne_.containsInside(pt_);
        assertTrue(int_);
    }

    @Test
    public void containsInside2Test() {
        Rect rOne_ = new Rect(5, 5, 2, 2);
        CustPoint pt_ = new CustPoint();
        pt_.setXcoords(5);
        pt_.setYcoords(5);
        boolean int_ = rOne_.containsInside(pt_);
        assertTrue(int_);
    }

    @Test
    public void containsInside3Test() {
        Rect rOne_ = new Rect(5, 5, 2, 2);
        CustPoint pt_ = new CustPoint();
        pt_.setXcoords(6);
        pt_.setYcoords(5);
        boolean int_ = rOne_.containsInside(pt_);
        assertTrue(int_);
    }

    @Test
    public void containsInside4Test() {
        Rect rOne_ = new Rect(5, 5, 2, 2);
        CustPoint pt_ = new CustPoint();
        pt_.setXcoords(5);
        pt_.setYcoords(6);
        boolean int_ = rOne_.containsInside(pt_);
        assertTrue(int_);
    }

    @Test
    public void containsInside5Test() {
        Rect rOne_ = new Rect(5, 5, 2, 2);
        CustPoint pt_ = new CustPoint();
        pt_.setXcoords(6);
        pt_.setYcoords(6);
        boolean int_ = rOne_.containsInside(pt_);
        assertTrue(int_);
    }

    @Test
    public void containsInside6Test() {
        Rect rOne_ = new Rect(5, 5, 2, 2);
        CustPoint pt_ = new CustPoint();
        pt_.setXcoords(8);
        pt_.setYcoords(6);
        boolean int_ = rOne_.containsInside(pt_);
        assertTrue(!int_);
    }

    @Test
    public void containsInside7Test() {
        Rect rOne_ = new Rect(5, 5, 2, 2);
        CustPoint pt_ = new CustPoint();
        pt_.setXcoords(4);
        pt_.setYcoords(6);
        boolean int_ = rOne_.containsInside(pt_);
        assertTrue(!int_);
    }

    @Test
    public void containsInside8Test() {
        Rect rOne_ = new Rect(5, 5, 2, 2);
        CustPoint pt_ = new CustPoint();
        pt_.setXcoords(6);
        pt_.setYcoords(8);
        boolean int_ = rOne_.containsInside(pt_);
        assertTrue(!int_);
    }

    @Test
    public void containsInside9Test() {
        Rect rOne_ = new Rect(5, 5, 2, 2);
        CustPoint pt_ = new CustPoint();
        pt_.setXcoords(6);
        pt_.setYcoords(4);
        boolean int_ = rOne_.containsInside(pt_);
        assertTrue(!int_);
    }

    @Test
    public void eq1Test() {
        Rect rOne_ = new Rect();
        rOne_.setLeft(1);
        rOne_.setTop(1);
        rOne_.setWidth(1);
        rOne_.setHeight(1);
        Rect rTwo_ = new Rect();
        rTwo_.setLeft(1);
        rTwo_.setTop(1);
        rTwo_.setWidth(1);
        rTwo_.setHeight(1);
        assertTrue(rOne_.eq(rTwo_));
    }

    @Test
    public void eq2Test() {
        Rect rOne_ = new Rect();
        rOne_.setLeft(1);
        rOne_.setTop(1);
        rOne_.setWidth(1);
        rOne_.setHeight(1);
        Rect rTwo_ = new Rect();
        rTwo_.setLeft(1);
        rTwo_.setTop(1);
        rTwo_.setWidth(1);
        rTwo_.setHeight(2);
        assertTrue(!rOne_.eq(rTwo_));
    }

    @Test
    public void eq3Test() {
        Rect rOne_ = new Rect();
        rOne_.setLeft(1);
        rOne_.setTop(1);
        rOne_.setWidth(1);
        rOne_.setHeight(1);
        Rect rTwo_ = new Rect();
        rTwo_.setLeft(1);
        rTwo_.setTop(1);
        rTwo_.setWidth(2);
        rTwo_.setHeight(1);
        assertTrue(!rOne_.eq(rTwo_));
    }

    @Test
    public void eq4Test() {
        Rect rOne_ = new Rect();
        rOne_.setLeft(1);
        rOne_.setTop(1);
        rOne_.setWidth(1);
        rOne_.setHeight(1);
        Rect rTwo_ = new Rect();
        rTwo_.setLeft(1);
        rTwo_.setTop(2);
        rTwo_.setWidth(1);
        rTwo_.setHeight(1);
        assertTrue(!rOne_.eq(rTwo_));
    }

    @Test
    public void eq5Test() {
        Rect rOne_ = new Rect();
        rOne_.setLeft(1);
        rOne_.setTop(1);
        rOne_.setWidth(1);
        rOne_.setHeight(1);
        Rect rTwo_ = new Rect();
        rTwo_.setLeft(2);
        rTwo_.setTop(1);
        rTwo_.setWidth(1);
        rTwo_.setHeight(1);
        assertTrue(!rOne_.eq(rTwo_));
    }

    @Test
    public void isEmptyTest() {
        Rect rOne_ = new Rect();
        rOne_.setLeft(1);
        rOne_.setTop(1);
        rOne_.setWidth(0);
        rOne_.setHeight(1);
        assertTrue(rOne_.isEmpty());
        Rect rTwo_ = new Rect();
        rTwo_.setLeft(2);
        rTwo_.setTop(1);
        rTwo_.setWidth(1);
        rTwo_.setHeight(0);
        assertTrue(rTwo_.isEmpty());
        Rect rThree_ = new Rect();
        rThree_.setLeft(2);
        rThree_.setTop(1);
        rThree_.setWidth(1);
        rThree_.setHeight(1);
        assertTrue(!rThree_.isEmpty());
    }

    @Test
    public void isValidTest() {
        Rect rOne_ = new Rect();
        rOne_.setLeft(1);
        rOne_.setTop(1);
        rOne_.setWidth(0);
        rOne_.setHeight(1);
        assertTrue(rOne_.isValid());
        Rect rTwo_ = new Rect();
        rTwo_.setLeft(2);
        rTwo_.setTop(1);
        rTwo_.setWidth(1);
        rTwo_.setHeight(0);
        assertTrue(rTwo_.isValid());
        Rect rThree_ = new Rect();
        rThree_.setLeft(2);
        rThree_.setTop(1);
        rThree_.setWidth(1);
        rThree_.setHeight(1);
        assertTrue(rThree_.isValid());
        Rect rFour_ = new Rect();
        rFour_.setLeft(1);
        rFour_.setTop(1);
        rFour_.setWidth(0);
        rFour_.setHeight(-1);
        assertTrue(!rFour_.isValid());
        Rect rFive_ = new Rect();
        rFive_.setLeft(2);
        rFive_.setTop(1);
        rFive_.setWidth(-1);
        rFive_.setHeight(0);
        assertTrue(!rFive_.isValid());
        Rect rSix_ = new Rect();
        rSix_.setLeft(2);
        rSix_.setTop(1);
        rSix_.setWidth(-1);
        rSix_.setHeight(-1);
        assertTrue(!rSix_.isValid());
    }

    @Test
    public void new_Rect_Test() {
        Rect rOne_ = Rect.newRect("1,2,3,4");
        assertEq(1,rOne_.getLeft());
        assertEq(2,rOne_.getTop());
        assertEq(3,rOne_.getWidth());
        assertEq(4,rOne_.getHeight());
    }

    @Test
    public void displayTest() {
        Rect rOne_ = new Rect(5, 6, 2, 3);
        assertEq("5,6,2,3",rOne_.display());
        assertEq(4,rOne_.getEdges().size());
        assertEq(4,rOne_.getPoints().size());
    }
}
