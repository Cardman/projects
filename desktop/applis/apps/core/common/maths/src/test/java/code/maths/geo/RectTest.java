package code.maths.geo;

import code.maths.EquallableMathUtil;
import code.maths.Rate;
import org.junit.Test;


public class RectTest extends EquallableMathUtil {

    @Test
    public void intersect1Test() {
        Rect rOne_ = rect(1, 1, 2, 2);
        Rect rTwo_ = rect(5, 5, 2, 2);
        Rect int_ = rOne_.intersect(rTwo_);
        assertTrue(int_.isEmpty());
    }

    @Test
    public void intersect2Test() {
        Rect rOne_ = rect(1, 1, 2, 2);
        Rect rTwo_ = rect(2, 2, 2, 2);
        Rect int_ = rOne_.intersect(rTwo_);
        assertEq(2, int_.getLeft());
        assertEq(2, int_.getTop());
        assertEq(1, int_.getWidth());
        assertEq(1, int_.getHeight());
    }

    @Test
    public void intersect3Test() {
        Rect rOne_ = rect(1, 1, 4, 4);
        Rect rTwo_ = rect(2, 2, 2, 2);
        Rect int_ = rOne_.intersect(rTwo_);
        assertEq(2, int_.getLeft());
        assertEq(2, int_.getTop());
        assertEq(2, int_.getWidth());
        assertEq(2, int_.getHeight());
    }

    @Test
    public void intersect4Test() {
        Rect rOne_ = rect(1, 1, 2, 2);
        Rect rTwo_ = rect(2, 3, 2, 2);
        Rect int_ = rOne_.intersect(rTwo_);
        assertTrue(int_.isEmpty());
    }

    @Test
    public void intersect5Test() {
        Rect rOne_ = rect(1, 1, 2, 3);
        Rect rTwo_ = rect(2, 3, 2, 3);
        Rect int_ = rOne_.intersect(rTwo_);
        assertEq(2, int_.getLeft());
        assertEq(3, int_.getTop());
        assertEq(1, int_.getWidth());
        assertEq(1, int_.getHeight());
    }

    @Test
    public void intersect6Test() {
        Rect rOne_ = rect(1, 1, 2, 3);
        Rect rTwo_ = rect(2, 0, 2, 4);
        Rect int_ = rOne_.intersect(rTwo_);
        assertEq(2, int_.getLeft());
        assertEq(1, int_.getTop());
        assertEq(1, int_.getWidth());
        assertEq(3, int_.getHeight());
    }

    @Test
    public void intersect7Test() {
        Rect rOne_ = rect(1, 1, 2, 2);
        Rect rTwo_ = rect(3, 2, 2, 2);
        Rect int_ = rOne_.intersect(rTwo_);
        assertTrue(int_.isEmpty());
    }

    @Test
    public void intersect8Test() {
        Rect rOne_ = rect(2, 3, 2, 2);
        Rect rTwo_ = rect(1, 1, 2, 2);
        Rect int_ = rOne_.intersect(rTwo_);
        assertTrue(int_.isEmpty());
    }

    @Test
    public void intersect9Test() {
        Rect rOne_ = rect(3, 2, 2, 2);
        Rect rTwo_ = rect(1, 1, 2, 2);
        Rect int_ = rOne_.intersect(rTwo_);
        assertTrue(int_.isEmpty());
    }

    @Test
    public void intersect10Test() {
        Rect rOne_ = rect(3, -2, 2, 2);
        Rect rTwo_ = rect(1, 1, 2, 2);
        Rect int_ = rOne_.intersect(rTwo_);
        assertTrue(int_.isEmpty());
    }

    @Test
    public void intersect11Test() {
        Rect rOne_ = rect(5, 5, 2, 2);
        Rect rTwo_ = rect(1, 1, 2, 2);
        Rect int_ = rOne_.intersect(rTwo_);
        assertTrue(int_.isEmpty());
    }

    @Test
    public void intersect12Test() {
        Rect rOne_ = rect(5, 5, 2, 2);
        Rect rTwo_ = rect(4, 1, 2, 2);
        Rect int_ = rOne_.intersect(rTwo_);
        assertTrue(int_.isEmpty());
    }

    @Test
    public void intersects1Test() {
        Rect rOne_ = rect(1, 1, 2, 2);
        Rect rTwo_ = rect(5, 5, 2, 2);
        boolean int_ = rOne_.intersects(rTwo_);
        assertTrue(!int_);
    }

    @Test
    public void intersects2Test() {
        Rect rOne_ = rect(1, 1, 2, 2);
        Rect rTwo_ = rect(2, 2, 2, 2);
        boolean int_ = rOne_.intersects(rTwo_);
        assertTrue(int_);
    }

    @Test
    public void intersects3Test() {
        Rect rOne_ = rect(1, 1, 4, 4);
        Rect rTwo_ = rect(2, 2, 2, 2);
        boolean int_ = rOne_.intersects(rTwo_);
        assertTrue(int_);
    }

    @Test
    public void intersects4Test() {
        Rect rOne_ = rect(1, 1, 2, 2);
        Rect rTwo_ = rect(2, 3, 2, 2);
        boolean int_ = rOne_.intersects(rTwo_);
        assertTrue(!int_);
    }

    @Test
    public void intersects5Test() {
        Rect rOne_ = rect(1, 1, 2, 3);
        Rect rTwo_ = rect(2, 3, 2, 3);
        boolean int_ = rOne_.intersects(rTwo_);
        assertTrue(int_);
    }

    @Test
    public void intersects6Test() {
        Rect rOne_ = rect(1, 1, 2, 3);
        Rect rTwo_ = rect(2, 0, 2, 4);
        boolean int_ = rOne_.intersects(rTwo_);
        assertTrue(int_);
    }

    @Test
    public void intersects7Test() {
        Rect rOne_ = rect(1, 1, 2, 2);
        Rect rTwo_ = rect(3, 2, 2, 2);
        boolean int_ = rOne_.intersects(rTwo_);
        assertTrue(!int_);
    }

    @Test
    public void intersects8Test() {
        Rect rOne_ = rect(2, 3, 2, 2);
        Rect rTwo_ = rect(1, 1, 2, 2);
        boolean int_ = rOne_.intersects(rTwo_);
        assertTrue(!int_);
    }

    @Test
    public void intersects9Test() {
        Rect rOne_ = rect(3, 2, 2, 2);
        Rect rTwo_ = rect(1, 1, 2, 2);
        boolean int_ = rOne_.intersects(rTwo_);
        assertTrue(!int_);
    }

    @Test
    public void intersects10Test() {
        Rect rOne_ = rect(3, -2, 2, 2);
        Rect rTwo_ = rect(1, 1, 2, 2);
        boolean int_ = rOne_.intersects(rTwo_);
        assertTrue(!int_);
    }

    @Test
    public void intersects11Test() {
        Rect rOne_ = rect(5, 5, 2, 2);
        Rect rTwo_ = rect(1, 1, 2, 2);
        boolean int_ = rOne_.intersects(rTwo_);
        assertTrue(!int_);
    }

    @Test
    public void intersects12Test() {
        Rect rOne_ = rect(5, 5, 2, 2);
        Rect rTwo_ = rect(4, 1, 2, 2);
        boolean int_ = rOne_.intersects(rTwo_);
        assertTrue(!int_);
    }

    @Test
    public void containsInside1Test() {
        Rect rOne_ = rect(5, 5, 2, 2);
        RatePoint pt_ = new RatePoint();
        setx(pt_, 6);
        sety(pt_, 6);
        boolean int_ = rOne_.containsInside(pt_);
        assertTrue(int_);
    }

    @Test
    public void containsInside2Test() {
        Rect rOne_ = rect(5, 5, 2, 2);
        RatePoint pt_ = new RatePoint();
        setx(pt_, 5);
        sety(pt_, 5);
        boolean int_ = rOne_.containsInside(pt_);
        assertTrue(int_);
    }

    @Test
    public void containsInside3Test() {
        Rect rOne_ = rect(5, 5, 2, 2);
        RatePoint pt_ = new RatePoint();
        setx(pt_, 6);
        sety(pt_, 5);
        boolean int_ = rOne_.containsInside(pt_);
        assertTrue(int_);
    }

    @Test
    public void containsInside4Test() {
        Rect rOne_ = rect(5, 5, 2, 2);
        RatePoint pt_ = new RatePoint();
        setx(pt_, 5);
        sety(pt_, 6);
        boolean int_ = rOne_.containsInside(pt_);
        assertTrue(int_);
    }

    @Test
    public void containsInside5Test() {
        Rect rOne_ = rect(5, 5, 2, 2);
        RatePoint pt_ = new RatePoint();
        setx(pt_, 6);
        sety(pt_, 6);
        boolean int_ = rOne_.containsInside(pt_);
        assertTrue(int_);
    }

    @Test
    public void containsInside6Test() {
        Rect rOne_ = rect(5, 5, 2, 2);
        RatePoint pt_ = new RatePoint();
        setx(pt_, 8);
        sety(pt_, 6);
        boolean int_ = rOne_.containsInside(pt_);
        assertTrue(!int_);
    }

    @Test
    public void containsInside7Test() {
        Rect rOne_ = rect(5, 5, 2, 2);
        RatePoint pt_ = new RatePoint();
        setx(pt_, 4);
        sety(pt_, 6);
        boolean int_ = rOne_.containsInside(pt_);
        assertTrue(!int_);
    }

    @Test
    public void containsInside8Test() {
        Rect rOne_ = rect(5, 5, 2, 2);
        RatePoint pt_ = new RatePoint();
        setx(pt_, 6);
        sety(pt_, 8);
        boolean int_ = rOne_.containsInside(pt_);
        assertTrue(!int_);
    }

    @Test
    public void containsInside9Test() {
        Rect rOne_ = rect(5, 5, 2, 2);
        RatePoint pt_ = new RatePoint();
        setx(pt_, 6);
        sety(pt_, 4);
        boolean int_ = rOne_.containsInside(pt_);
        assertTrue(!int_);
    }

    @Test
    public void eq1Test() {
        Rect rOne_ = new Rect();
        setleft(rOne_, 1);
        settop(rOne_, 1);
        setwidth(rOne_, 1);
        setheight(rOne_, 1);
        Rect rTwo_ = new Rect();
        setleft(rTwo_, 1);
        settop(rTwo_, 1);
        setwidth(rTwo_, 1);
        setheight(rTwo_, 1);
        assertTrue(rOne_.eq(rTwo_));
    }

    @Test
    public void eq2Test() {
        Rect rOne_ = new Rect();
        setleft(rOne_, 1);
        settop(rOne_, 1);
        setwidth(rOne_, 1);
        setheight(rOne_, 1);
        Rect rTwo_ = new Rect();
        setleft(rTwo_, 1);
        settop(rTwo_, 1);
        setwidth(rTwo_, 1);
        setheight(rTwo_, 2);
        assertTrue(!rOne_.eq(rTwo_));
    }

    @Test
    public void eq3Test() {
        Rect rOne_ = new Rect();
        setleft(rOne_, 1);
        settop(rOne_, 1);
        setwidth(rOne_, 1);
        setheight(rOne_, 1);
        Rect rTwo_ = new Rect();
        setleft(rTwo_, 1);
        settop(rTwo_, 1);
        setwidth(rTwo_, 2);
        setheight(rTwo_, 1);
        assertTrue(!rOne_.eq(rTwo_));
    }

    @Test
    public void eq4Test() {
        Rect rOne_ = new Rect();
        setleft(rOne_, 1);
        settop(rOne_, 1);
        setwidth(rOne_, 1);
        setheight(rOne_, 1);
        Rect rTwo_ = new Rect();
        setleft(rTwo_, 1);
        settop(rTwo_, 2);
        setwidth(rTwo_, 1);
        setheight(rTwo_, 1);
        assertTrue(!rOne_.eq(rTwo_));
    }

    @Test
    public void eq5Test() {
        Rect rOne_ = new Rect();
        setleft(rOne_, 1);
        settop(rOne_, 1);
        setwidth(rOne_, 1);
        setheight(rOne_, 1);
        Rect rTwo_ = new Rect();
        setleft(rTwo_, 2);
        settop(rTwo_, 1);
        setwidth(rTwo_, 1);
        setheight(rTwo_, 1);
        assertTrue(!rOne_.eq(rTwo_));
    }

    @Test
    public void isEmptyTest() {
        Rect rOne_ = new Rect();
        setleft(rOne_, 1);
        settop(rOne_, 1);
        setwidth(rOne_, 0);
        setheight(rOne_, 1);
        assertTrue(rOne_.isEmpty());
        Rect rTwo_ = new Rect();
        setleft(rTwo_, 2);
        settop(rTwo_, 1);
        setwidth(rTwo_, 1);
        setheight(rTwo_, 0);
        assertTrue(rTwo_.isEmpty());
        Rect rThree_ = new Rect();
        setleft(rThree_, 2);
        settop(rThree_, 1);
        setwidth(rThree_, 1);
        setheight(rThree_, 1);
        assertTrue(!rThree_.isEmpty());
    }

    @Test
    public void isValidTest() {
        Rect rOne_ = new Rect();
        setleft(rOne_, 1);
        settop(rOne_, 1);
        setwidth(rOne_, 0);
        setheight(rOne_, 1);
        assertTrue(rOne_.isValid());
        Rect rTwo_ = new Rect();
        setleft(rTwo_, 2);
        settop(rTwo_, 1);
        setwidth(rTwo_, 1);
        setheight(rTwo_, 0);
        assertTrue(rTwo_.isValid());
        Rect rThree_ = new Rect();
        setleft(rThree_, 2);
        settop(rThree_, 1);
        setwidth(rThree_, 1);
        setheight(rThree_, 1);
        assertTrue(rThree_.isValid());
        Rect rFour_ = new Rect();
        setleft(rFour_, 1);
        settop(rFour_, 1);
        setwidth(rFour_, 0);
        setheight(rFour_, -1);
        assertTrue(!rFour_.isValid());
        Rect rFive_ = new Rect();
        setleft(rFive_, 2);
        settop(rFive_, 1);
        setwidth(rFive_, -1);
        setheight(rFive_, 0);
        assertTrue(!rFive_.isValid());
        Rect rSix_ = new Rect();
        setleft(rSix_, 2);
        settop(rSix_, 1);
        setwidth(rSix_, -1);
        setheight(rSix_, -1);
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
        Rect rOne_ = rect(5, 6, 2, 3);
        assertEq("5,6,2,3",rOne_.display());
        assertEq(4,rOne_.getEdges().size());
        assertEq(4,rOne_.getPoints().size());
    }

    private static void assertEq(int _value,Rate _v) {
        assertEq(new Rate(_value),_v);
    }

    private static void setx(RatePoint _pt, int _x) {
        _pt.setXcoords(new Rate(_x));
    }

    private static void sety(RatePoint _pt, int _y) {
        _pt.setYcoords(new Rate(_y));
    }

    private static void setleft(Rect _rect, int _v) {
        _rect.setLeft(new Rate(_v));
    }

    private static void settop(Rect _rect, int _v) {
        _rect.setTop(new Rate(_v));
    }

    private static void setwidth(Rect _rect, int _v) {
        _rect.setWidth(new Rate(_v));
    }

    private static void setheight(Rect _rect, int _v) {
        _rect.setHeight(new Rate(_v));
    }

    private static Rect rect(int _one, int _two, int _three, int _four) {
        return new Rect(new Rate(_one), new Rate(_two), new Rate(_three), new Rate(_four));
    }
}
