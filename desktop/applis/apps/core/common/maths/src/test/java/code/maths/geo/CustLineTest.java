package code.maths.geo;
import static code.maths.EquallableMathUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.maths.Rate;


public class CustLineTest {

    @Test
    public void new_CustLine_1Test() {
        CustPoint one_ = new CustPoint(1,2);
        CustPoint two_ = new CustPoint(5,8);
        CustLine l_ = new CustLine(one_, two_);
        assertEq(new Rate("-3"), l_.getxRate());
        assertEq(new Rate("2"), l_.getyRate());
        assertEq(new Rate("1"), l_.getCst());
        assertTrue(l_.isDefined());
    }

    @Test
    public void new_CustLine_2Test() {
        CustPoint one_ = new CustPoint(2,1);
        CustPoint two_ = new CustPoint(8,5);
        CustLine l_ = new CustLine(one_, two_);
        assertEq(new Rate("2"), l_.getxRate());
        assertEq(new Rate("-3"), l_.getyRate());
        assertEq(new Rate("1"), l_.getCst());
        assertTrue(l_.isDefined());
    }

    @Test
    public void new_CustLine_3Test() {
        CustPoint one_ = new CustPoint(2,1);
        CustPoint two_ = new CustPoint(2,1);
        CustLine l_ = new CustLine(one_, two_);
        assertTrue(!l_.isDefined());
    }

    @Test
    public void new_CustLine_4Test() {
        CustPoint one_ = new CustPoint(1,2);
        CustPoint two_ = new CustPoint(1,2);
        CustLine l_ = new CustLine(one_, two_);
        assertTrue(!l_.isDefined());
    }

    @Test
    public void new_CustLine_5Test() {
        RatePoint one_ = new RatePoint(new CustPoint(1,2));
        RatePoint two_ = new RatePoint(new CustPoint(5,8));
        CustLine l_ = new CustLine(one_, two_);
        assertEq(new Rate("-3"), l_.getxRate());
        assertEq(new Rate("2"), l_.getyRate());
        assertEq(new Rate("1"), l_.getCst());
        assertTrue(l_.isDefined());
    }

    @Test
    public void new_CustLine_6Test() {
        RatePoint one_ = new RatePoint(new CustPoint(2,1));
        RatePoint two_ = new RatePoint(new CustPoint(8,5));
        CustLine l_ = new CustLine(one_, two_);
        assertEq(new Rate("2"), l_.getxRate());
        assertEq(new Rate("-3"), l_.getyRate());
        assertEq(new Rate("1"), l_.getCst());
        assertTrue(l_.isDefined());
    }

    @Test
    public void new_CustLine_7Test() {
        RatePoint one_ = new RatePoint(new CustPoint(2,1));
        RatePoint two_ = new RatePoint(new CustPoint(2,1));
        CustLine l_ = new CustLine(one_, two_);
        assertTrue(!l_.isDefined());
    }

    @Test
    public void new_CustLine_8Test() {
        RatePoint one_ = new RatePoint(new CustPoint(1,2));
        RatePoint two_ = new RatePoint(new CustPoint(1,2));
        CustLine l_ = new CustLine(one_, two_);
        assertTrue(!l_.isDefined());
    }

    @Test
    public void new_CustLine_9Test() {
        CustPoint one_ = new CustPoint(1,2);
        CustPoint two_ = new CustPoint(0,0);
        CustLine l_ = new CustLine(one_, two_);
        assertEq(new Rate("1"), l_.getxRate());
        assertEq(new Rate("-1/2"), l_.getyRate());
        assertEq(new Rate("0"), l_.getCst());
        assertTrue(l_.isDefined());
    }

    @Test
    public void new_CustLine_10Test() {
        CustPoint one_ = new CustPoint(2,1);
        CustPoint two_ = new CustPoint(0,0);
        CustLine l_ = new CustLine(one_, two_);
        assertEq(new Rate("1"), l_.getxRate());
        assertEq(new Rate("-2"), l_.getyRate());
        assertEq(new Rate("0"), l_.getCst());
        assertTrue(l_.isDefined());
    }

    @Test
    public void new_CustLine_11Test() {
        CustPoint one_ = new CustPoint(0,0);
        CustPoint two_ = new CustPoint(1,2);
        CustLine l_ = new CustLine(one_, two_);
        assertEq(new Rate("1"), l_.getxRate());
        assertEq(new Rate("-1/2"), l_.getyRate());
        assertEq(new Rate("0"), l_.getCst());
        assertTrue(l_.isDefined());
    }

    @Test
    public void new_CustLine_12Test() {
        CustPoint one_ = new CustPoint(0,0);
        CustPoint two_ = new CustPoint(2,1);
        CustLine l_ = new CustLine(one_, two_);
        assertEq(new Rate("1"), l_.getxRate());
        assertEq(new Rate("-2"), l_.getyRate());
        assertEq(new Rate("0"), l_.getCst());
        assertTrue(l_.isDefined());
    }

    @Test
    public void new_CustLine_13Test() {
        RatePoint one_ = new RatePoint(new CustPoint(1,2));
        RatePoint two_ = new RatePoint(new CustPoint(0,0));
        CustLine l_ = new CustLine(one_, two_);
        assertEq(new Rate("1"), l_.getxRate());
        assertEq(new Rate("-1/2"), l_.getyRate());
        assertEq(new Rate("0"), l_.getCst());
        assertTrue(l_.isDefined());
    }

    @Test
    public void new_CustLine_14Test() {
        RatePoint one_ = new RatePoint(new CustPoint(2,1));
        RatePoint two_ = new RatePoint(new CustPoint(0,0));
        CustLine l_ = new CustLine(one_, two_);
        assertEq(new Rate("1"), l_.getxRate());
        assertEq(new Rate("-2"), l_.getyRate());
        assertEq(new Rate("0"), l_.getCst());
        assertTrue(l_.isDefined());
    }

    @Test
    public void new_CustLine_15Test() {
        RatePoint one_ = new RatePoint(new CustPoint(0,0));
        RatePoint two_ = new RatePoint(new CustPoint(1,2));
        CustLine l_ = new CustLine(one_, two_);
        assertEq(new Rate("1"), l_.getxRate());
        assertEq(new Rate("-1/2"), l_.getyRate());
        assertEq(new Rate("0"), l_.getCst());
        assertTrue(l_.isDefined());
    }

    @Test
    public void new_CustLine_16Test() {
        RatePoint one_ = new RatePoint(new CustPoint(0,0));
        RatePoint two_ = new RatePoint(new CustPoint(2,1));
        CustLine l_ = new CustLine(one_, two_);
        assertEq(new Rate("1"), l_.getxRate());
        assertEq(new Rate("-2"), l_.getyRate());
        assertEq(new Rate("0"), l_.getCst());
        assertTrue(l_.isDefined());
    }

    @Test
    public void new_CustLine_17Test() {
        CustPoint one_ = new CustPoint(1,0);
        CustPoint two_ = new CustPoint(0,0);
        CustLine l_ = new CustLine(one_, two_);
        assertEq(new Rate("0"), l_.getxRate());
        assertEq(new Rate("1"), l_.getyRate());
        assertEq(new Rate("0"), l_.getCst());
        assertTrue(l_.isDefined());
    }

    @Test
    public void new_CustLine_18Test() {
        CustPoint one_ = new CustPoint(0,1);
        CustPoint two_ = new CustPoint(0,0);
        CustLine l_ = new CustLine(one_, two_);
        assertEq(new Rate("1"), l_.getxRate());
        assertEq(new Rate("0"), l_.getyRate());
        assertEq(new Rate("0"), l_.getCst());
        assertTrue(l_.isDefined());
    }

    @Test
    public void new_CustLine_19Test() {
        RatePoint one_ = new RatePoint(new CustPoint(1,0));
        RatePoint two_ = new RatePoint(new CustPoint(0,0));
        CustLine l_ = new CustLine(one_, two_);
        assertEq(new Rate("0"), l_.getxRate());
        assertEq(new Rate("1"), l_.getyRate());
        assertEq(new Rate("0"), l_.getCst());
        assertTrue(l_.isDefined());
    }

    @Test
    public void new_CustLine_20Test() {
        RatePoint one_ = new RatePoint(new CustPoint(0,1));
        RatePoint two_ = new RatePoint(new CustPoint(0,0));
        CustLine l_ = new CustLine(one_, two_);
        assertEq(new Rate("1"), l_.getxRate());
        assertEq(new Rate("0"), l_.getyRate());
        assertEq(new Rate("0"), l_.getCst());
        assertTrue(l_.isDefined());
    }

    @Test
    public void new_CustLine_21Test() {
        CustPoint one_ = new CustPoint(0,0);
        CustPoint two_ = new CustPoint(1,0);
        CustLine l_ = new CustLine(one_, two_);
        assertEq(new Rate("0"), l_.getxRate());
        assertEq(new Rate("1"), l_.getyRate());
        assertEq(new Rate("0"), l_.getCst());
        assertTrue(l_.isDefined());
    }

    @Test
    public void new_CustLine_22Test() {
        CustPoint one_ = new CustPoint(0,0);
        CustPoint two_ = new CustPoint(0,1);
        CustLine l_ = new CustLine(one_, two_);
        assertEq(new Rate("1"), l_.getxRate());
        assertEq(new Rate("0"), l_.getyRate());
        assertEq(new Rate("0"), l_.getCst());
        assertTrue(l_.isDefined());
    }

    @Test
    public void new_CustLine_23Test() {
        RatePoint one_ = new RatePoint(new CustPoint(0,0));
        RatePoint two_ = new RatePoint(new CustPoint(1,0));
        CustLine l_ = new CustLine(one_, two_);
        assertEq(new Rate("0"), l_.getxRate());
        assertEq(new Rate("1"), l_.getyRate());
        assertEq(new Rate("0"), l_.getCst());
        assertTrue(l_.isDefined());
    }

    @Test
    public void new_CustLine_24Test() {
        RatePoint one_ = new RatePoint(new CustPoint(0,0));
        RatePoint two_ = new RatePoint(new CustPoint(0,1));
        CustLine l_ = new CustLine(one_, two_);
        assertEq(new Rate("1"), l_.getxRate());
        assertEq(new Rate("0"), l_.getyRate());
        assertEq(new Rate("0"), l_.getCst());
        assertTrue(l_.isDefined());
    }

    @Test
    public void new_CustLine_25Test() {
        CustPoint one_ = new CustPoint(0,0);
        CustPoint two_ = new CustPoint(0,0);
        CustLine l_ = new CustLine(one_, two_);
        assertTrue(!l_.isDefined());
    }

    @Test
    public void new_CustLine_26Test() {
        RatePoint one_ = new RatePoint(new CustPoint(0,0));
        RatePoint two_ = new RatePoint(new CustPoint(0,0));
        CustLine l_ = new CustLine(one_, two_);
        assertTrue(!l_.isDefined());
    }

    @Test
    public void new_CustLine_27Test() {
        Rate one_ = new Rate(0);
        Rate two_ = new Rate(0);
        Rate cst_ = new Rate(0);
        CustLine l_ = new CustLine(one_, two_,cst_);
        assertTrue(!l_.isDefined());
    }

    @Test
    public void new_CustLine_28Test() {
        Rate one_ = new Rate(0);
        Rate two_ = new Rate(1);
        Rate cst_ = new Rate(0);
        CustLine l_ = new CustLine(one_, two_,cst_);
        assertTrue(l_.isDefined());
    }

    @Test
    public void new_CustLine_29Test() {
        Rate one_ = new Rate(1);
        Rate two_ = new Rate(0);
        Rate cst_ = new Rate(0);
        CustLine l_ = new CustLine(one_, two_,cst_);
        assertTrue(l_.isDefined());
    }

    @Test
    public void new_CustLine_30Test() {
        Rate one_ = new Rate(1);
        Rate two_ = new Rate(1);
        Rate cst_ = new Rate(0);
        CustLine l_ = new CustLine(one_, two_,cst_);
        assertTrue(l_.isDefined());
    }

    @Test
    public void new_CustLine_31Test() {
        CustLine l_ = new CustLine();
        assertTrue(!l_.isDefined());
    }
    @Test
    public void incPoint1Test() {
        CustPoint one_ = new CustPoint(1,2);
        CustPoint two_ = new CustPoint(5,8);
        CustLine l_ = new CustLine(one_, two_);
        RatePoint r_ = l_.incPoint(new RatePoint(one_));
        assertEq(new Rate("7/3"), r_.getXcoords());
        assertEq(new Rate("7/2"), r_.getYcoords());
    }

    @Test
    public void incPoint2Test() {
        CustPoint one_ = new CustPoint(1,2);
        CustPoint two_ = new CustPoint(5,8);
        CustLine l_ = new CustLine(one_, two_);
        RatePoint r_ = l_.incPoint(new RatePoint(two_));
        assertEq(new Rate("19/3"), r_.getXcoords());
        assertEq(new Rate("19/2"), r_.getYcoords());
    }

    @Test
    public void incPoint3Test() {
        CustPoint one_ = new CustPoint(1,2);
        CustPoint two_ = new CustPoint(5,2);
        CustLine l_ = new CustLine(one_, two_);
        RatePoint r_ = l_.incPoint(new RatePoint(one_));
        assertEq(new Rate("1"), r_.getXcoords());
        assertEq(new Rate("2"), r_.getYcoords());
    }

    @Test
    public void incPoint4Test() {
        CustPoint one_ = new CustPoint(1,2);
        CustPoint two_ = new CustPoint(5,2);
        CustLine l_ = new CustLine(one_, two_);
        RatePoint r_ = l_.incPoint(new RatePoint(two_));
        assertEq(new Rate("5"), r_.getXcoords());
        assertEq(new Rate("2"), r_.getYcoords());
    }

    @Test
    public void incPoint5Test() {
        CustPoint one_ = new CustPoint(1,2);
        CustPoint two_ = new CustPoint(1,8);
        CustLine l_ = new CustLine(one_, two_);
        RatePoint r_ = l_.incPoint(new RatePoint(one_));
        assertEq(new Rate("1"), r_.getXcoords());
        assertEq(new Rate("2"), r_.getYcoords());
    }

    @Test
    public void incPoint6Test() {
        CustPoint one_ = new CustPoint(1,2);
        CustPoint two_ = new CustPoint(1,8);
        CustLine l_ = new CustLine(one_, two_);
        RatePoint r_ = l_.incPoint(new RatePoint(two_));
        assertEq(new Rate("1"), r_.getXcoords());
        assertEq(new Rate("8"), r_.getYcoords());
    }

    @Test
    public void incPoint7Test() {
        CustPoint one_ = new CustPoint(2,1);
        CustPoint two_ = new CustPoint(8,5);
        CustLine l_ = new CustLine(one_, two_);
        RatePoint r_ = l_.incPoint(new RatePoint(one_));
        assertEq(new Rate("7/2"), r_.getXcoords());
        assertEq(new Rate("7/3"), r_.getYcoords());
    }

    @Test
    public void incPoint8Test() {
        CustPoint one_ = new CustPoint(2,1);
        CustPoint two_ = new CustPoint(8,5);
        CustLine l_ = new CustLine(one_, two_);
        RatePoint r_ = l_.incPoint(new RatePoint(two_));
        assertEq(new Rate("19/2"), r_.getXcoords());
        assertEq(new Rate("19/3"), r_.getYcoords());
    }

    @Test
    public void incPoint9Test() {
        CustPoint one_ = new CustPoint(1,2);
        CustPoint two_ = new CustPoint(5,8);
        CustLine l_ = new CustLine(one_, two_);
        RatePoint r_ = l_.incPoint(new RatePoint(one_),1,2);
        assertEq(new Rate("7/3"), r_.getXcoords());
        assertEq(new Rate("7/3"), r_.getYcoords());
    }

    @Test
    public void incPoint10Test() {
        CustPoint one_ = new CustPoint(1,2);
        CustPoint two_ = new CustPoint(5,8);
        CustLine l_ = new CustLine(one_, two_);
        RatePoint r_ = l_.incPoint(new RatePoint(two_),1,2);
        assertEq(new Rate("19/3"), r_.getXcoords());
        assertEq(new Rate("19/3"), r_.getYcoords());
    }

    @Test
    public void incPoint11Test() {
        CustPoint one_ = new CustPoint(2,1);
        CustPoint two_ = new CustPoint(8,5);
        CustLine l_ = new CustLine(one_, two_);
        RatePoint r_ = l_.incPoint(new RatePoint(one_),2,1);
        assertEq(new Rate("7/2"), r_.getXcoords());
        assertEq(new Rate("7/2"), r_.getYcoords());
    }

    @Test
    public void incPoint12Test() {
        CustPoint one_ = new CustPoint(2,1);
        CustPoint two_ = new CustPoint(8,5);
        CustLine l_ = new CustLine(one_, two_);
        RatePoint r_ = l_.incPoint(new RatePoint(two_),2,1);
        assertEq(new Rate("19/2"), r_.getXcoords());
        assertEq(new Rate("19/2"), r_.getYcoords());
    }

    @Test
    public void intersectTest() {
        CustPoint one_ = new CustPoint(1,1);
        CustPoint two_ = new CustPoint(-1,-1);
        CustLine l_ = new CustLine(one_, two_);
        CustPoint three_ = new CustPoint(1,-1);
        CustPoint four_ = new CustPoint(-1,1);
        CustLine l2_ = new CustLine(three_, four_);
        RatePoint r_ = l_.intersect(l2_);
        assertEq(new Rate("0"), r_.getXcoords());
        assertEq(new Rate("0"), r_.getYcoords());
    }
}
