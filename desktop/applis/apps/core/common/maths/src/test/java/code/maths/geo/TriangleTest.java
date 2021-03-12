package code.maths.geo;

import code.maths.EquallableMathUtil;
import org.junit.Test;

import code.maths.Rate;


public class TriangleTest extends EquallableMathUtil {

    @Test
    public void getCircumCenter1Test() {
        RatePoint one_ = pt(1, 1);
        RatePoint two_ = pt(3, 2);
        RatePoint three_ = pt(2, 4);
        Triangle t_ = new Triangle(one_, two_, three_);
        RatePoint p_ = t_.getCircumCenter();
        assertEq(new Rate(15,10), p_.getXcoords());
        assertEq(new Rate(25,10), p_.getYcoords());
    }

    @Test
    public void isInCircum1Test() {
        RatePoint one_ = pt(1, 1);
        RatePoint two_ = pt(3, 2);
        RatePoint three_ = pt(2, 4);
        Triangle t_ = new Triangle(one_, two_, three_);
        assertTrue(!t_.isInCircum(pt(0, 3)));
    }

    @Test
    public void isInCircum2Test() {
        RatePoint one_ = pt(1, 1);
        RatePoint two_ = pt(3, 2);
        RatePoint three_ = pt(2, 4);
        Triangle t_ = new Triangle(one_, two_, three_);
        assertTrue(!t_.isInCircum(pt(0, 1)));
    }

    @Test
    public void isInCircum3Test() {
        RatePoint one_ = pt(1, 1);
        RatePoint two_ = pt(3, 2);
        RatePoint three_ = pt(2, 4);
        Triangle t_ = new Triangle(one_, two_, three_);
        assertTrue(!t_.isInCircum(pt(0, 2)));
    }

    @Test
    public void isInCircum4Test() {
        RatePoint one_ = pt(0, 0);
        RatePoint two_ = pt(3, 2);
        RatePoint three_ = pt(2, 4);
        Triangle t_ = new Triangle(one_, two_, three_);
        assertTrue(t_.isInCircum(pt(0, 3)));
    }

    @Test
    public void isInCircum5Test() {
        RatePoint one_ = pt(0, 0);
        RatePoint two_ = pt(3, 2);
        RatePoint three_ = pt(2, 4);
        Triangle t_ = new Triangle(one_, two_, three_);
        assertTrue(t_.isInCircum(pt(0, 1)));
    }

    @Test
    public void isInCircum6Test() {
        RatePoint one_ = pt(0, 0);
        RatePoint two_ = pt(3, 2);
        RatePoint three_ = pt(2, 4);
        Triangle t_ = new Triangle(one_, two_, three_);
        assertTrue(t_.isInCircum(pt(0, 2)));
    }

    @Test
    public void isInCircum7Test() {
        RatePoint one_ = pt(0, 0);
        RatePoint two_ = pt(2, 4);
        RatePoint three_ = pt(3, 2);
        Triangle t_ = new Triangle(one_, two_, three_);
        assertTrue(t_.isInCircum(pt(0, 3)));
    }


    @Test
    public void isInCircum8Test() {
        RatePoint one_ = pt(1, 1);
        RatePoint two_ = pt(2, 4);
        RatePoint three_ = pt(3, 2);
        Triangle t_ = new Triangle(one_, two_, three_);
        assertTrue(!t_.isInCircum(pt(0, 1)));
    }

    @Test
    public void isInCircum9Test() {
        RatePoint one_ = pt(1, 1);
        RatePoint two_ = pt(2, 4);
        RatePoint three_ = pt(3, 2);
        Triangle t_ = new Triangle(one_, two_, three_);
        assertTrue(!t_.isInCircum(pt(0, 2)));
    }

    @Test
    public void euler1Test() {
        RatePoint one_ = pt(1, 1);
        RatePoint two_ = pt(3, 2);
        RatePoint three_ = pt(2, 4);
        Triangle t_ = new Triangle(one_, two_, three_);
        CustLine c_ = t_.euler();
        assertEq(new Rate("1"), c_.getCst());
        assertEq(new Rate("1/9"), c_.getxRate());
        assertEq(new Rate("1/3"), c_.getyRate());
    }

    @Test
    public void euler2Test() {
        RatePoint one_ = pt(0, 0);
        RatePoint two_ = pt(3, 2);
        RatePoint three_ = pt(2, 4);
        Triangle t_ = new Triangle(one_, two_, three_);
        CustLine c_ = t_.euler();
        assertEq(new Rate("1"), c_.getCst());
        assertEq(new Rate("3/49"), c_.getxRate());
        assertEq(new Rate("22/49"), c_.getyRate());
    }

    @Test
    public void euler3Test() {
        RatePoint one_ = pt(0, 0);
        RatePoint two_ = pt(0, 2);
        RatePoint three_ = pt(0, 4);
        Triangle t_ = new Triangle(one_, two_, three_);
        CustLine c_ = t_.euler();
        assertTrue(!c_.isDefined());
    }

    @Test
    public void displayTest() {
        RatePoint one_ = pt(1, 2);
        RatePoint two_ = pt(3, 4);
        RatePoint three_ = pt(5, 6);
        Triangle t_ = new Triangle(one_, two_, three_);
        assertEq("1,2;3,4;5,6",t_.display());
    }

    private static RatePoint pt(int _x, int _y) {
        return new RatePoint(new Rate(_x), new Rate(_y));
    }

}
