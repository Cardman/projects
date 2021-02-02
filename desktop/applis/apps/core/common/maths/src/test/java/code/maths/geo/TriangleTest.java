package code.maths.geo;

import code.maths.EquallableMathUtil;
import org.junit.Test;

import code.maths.Rate;


public class TriangleTest extends EquallableMathUtil {

    @Test
    public void getCircumCenter1Test() {
        CustPoint one_ = new CustPoint(1, 1);
        CustPoint two_ = new CustPoint(3, 2);
        CustPoint three_ = new CustPoint(2, 4);
        Triangle t_ = new Triangle(one_, two_, three_);
        CompactPlanePoint p_ = t_.getCircumCenter();
        assertEq(15, p_.getXcoords());
        assertEq(25, p_.getYcoords());
        assertEq(10, p_.getCommon());
    }

    @Test
    public void isInCircum1Test() {
        CustPoint one_ = new CustPoint(1, 1);
        CustPoint two_ = new CustPoint(3, 2);
        CustPoint three_ = new CustPoint(2, 4);
        Triangle t_ = new Triangle(one_, two_, three_);
        assertTrue(!t_.isInCircum(new CustPoint(0, 3)));
    }

    @Test
    public void isInCircum2Test() {
        CustPoint one_ = new CustPoint(1, 1);
        CustPoint two_ = new CustPoint(3, 2);
        CustPoint three_ = new CustPoint(2, 4);
        Triangle t_ = new Triangle(one_, two_, three_);
        assertTrue(!t_.isInCircum(new CustPoint(0, 1)));
    }

    @Test
    public void isInCircum3Test() {
        CustPoint one_ = new CustPoint(1, 1);
        CustPoint two_ = new CustPoint(3, 2);
        CustPoint three_ = new CustPoint(2, 4);
        Triangle t_ = new Triangle(one_, two_, three_);
        assertTrue(!t_.isInCircum(new CustPoint(0, 2)));
    }

    @Test
    public void isInCircum4Test() {
        CustPoint one_ = new CustPoint(0, 0);
        CustPoint two_ = new CustPoint(3, 2);
        CustPoint three_ = new CustPoint(2, 4);
        Triangle t_ = new Triangle(one_, two_, three_);
        assertTrue(t_.isInCircum(new CustPoint(0, 3)));
    }

    @Test
    public void isInCircum5Test() {
        CustPoint one_ = new CustPoint(0, 0);
        CustPoint two_ = new CustPoint(3, 2);
        CustPoint three_ = new CustPoint(2, 4);
        Triangle t_ = new Triangle(one_, two_, three_);
        assertTrue(t_.isInCircum(new CustPoint(0, 1)));
    }

    @Test
    public void isInCircum6Test() {
        CustPoint one_ = new CustPoint(0, 0);
        CustPoint two_ = new CustPoint(3, 2);
        CustPoint three_ = new CustPoint(2, 4);
        Triangle t_ = new Triangle(one_, two_, three_);
        assertTrue(t_.isInCircum(new CustPoint(0, 2)));
    }

    @Test
    public void isInCircum7Test() {
        CustPoint one_ = new CustPoint(0, 0);
        CustPoint two_ = new CustPoint(2, 4);
        CustPoint three_ = new CustPoint(3, 2);
        Triangle t_ = new Triangle(one_, two_, three_);
        assertTrue(t_.isInCircum(new CustPoint(0, 3)));
    }


    @Test
    public void isInCircum8Test() {
        CustPoint one_ = new CustPoint(1, 1);
        CustPoint two_ = new CustPoint(2, 4);
        CustPoint three_ = new CustPoint(3, 2);
        Triangle t_ = new Triangle(one_, two_, three_);
        assertTrue(!t_.isInCircum(new CustPoint(0, 1)));
    }

    @Test
    public void isInCircum9Test() {
        CustPoint one_ = new CustPoint(1, 1);
        CustPoint two_ = new CustPoint(2, 4);
        CustPoint three_ = new CustPoint(3, 2);
        Triangle t_ = new Triangle(one_, two_, three_);
        assertTrue(!t_.isInCircum(new CustPoint(0, 2)));
    }

    @Test
    public void euler1Test() {
        CustPoint one_ = new CustPoint(1, 1);
        CustPoint two_ = new CustPoint(3, 2);
        CustPoint three_ = new CustPoint(2, 4);
        Triangle t_ = new Triangle(one_, two_, three_);
        CustLine c_ = t_.euler();
        assertEq(new Rate("1"), c_.getCst());
        assertEq(new Rate("1/9"), c_.getxRate());
        assertEq(new Rate("1/3"), c_.getyRate());
    }

    @Test
    public void euler2Test() {
        CustPoint one_ = new CustPoint(0, 0);
        CustPoint two_ = new CustPoint(3, 2);
        CustPoint three_ = new CustPoint(2, 4);
        Triangle t_ = new Triangle(one_, two_, three_);
        CustLine c_ = t_.euler();
        assertEq(new Rate("1"), c_.getCst());
        assertEq(new Rate("3/49"), c_.getxRate());
        assertEq(new Rate("22/49"), c_.getyRate());
    }

    @Test
    public void euler3Test() {
        CustPoint one_ = new CustPoint(0, 0);
        CustPoint two_ = new CustPoint(0, 2);
        CustPoint three_ = new CustPoint(0, 4);
        Triangle t_ = new Triangle(one_, two_, three_);
        CustLine c_ = t_.euler();
        assertTrue(!c_.isDefined());
    }

    @Test
    public void displayTest() {
        CustPoint one_ = new CustPoint(1,2);
        CustPoint two_ = new CustPoint(3,4);
        CustPoint three_ = new CustPoint(5,6);
        Triangle t_ = new Triangle(one_, two_, three_);
        assertEq("1,2;3,4;5,6",t_.display());
    }
}
