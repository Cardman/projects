package code.maths.geo;

import code.maths.Rate;
import org.junit.Test;

import static code.maths.EquallableMathUtil.assertEq;
import static org.junit.Assert.assertTrue;

public class CustPointTest {
    @Test
    public void new_CustPointThreeDims_test() {
        CustPoint c_ = CustPoint.newCustPoint("1,2");
        assertEq(1,c_.getXcoords());
        assertEq(2,c_.getYcoords());
    }
    @Test
    public void eq1Test() {
        CustPoint c_ = CustPoint.newCustPoint("1,2");
        CustPoint d_ = CustPoint.newCustPoint("1,2");
        assertTrue(c_.eq(d_));
    }
    @Test
    public void eq2Test() {
        CustPoint c_ = CustPoint.newCustPoint("1,2");
        CustPoint d_ = CustPoint.newCustPoint("1,3");
        assertTrue(!c_.eq(d_));
    }
    @Test
    public void eq3Test() {
        CustPoint c_ = CustPoint.newCustPoint("1,2");
        CustPoint d_ = CustPoint.newCustPoint("3,2");
        assertTrue(!c_.eq(d_));
    }
    @Test
    public void new_RatePointThreeDims_test() {
        RatePoint c_ = RatePoint.newCustRatePoint("1,2");
        assertEq(new Rate(1),c_.getXcoords());
        assertEq(new Rate(2),c_.getYcoords());
        RatePoint r_ =new RatePoint(c_);
        r_.setXcoords(new Rate(3));
        assertTrue(!c_.eq(r_));
        r_ =new RatePoint(c_);
        r_.setYcoords(new Rate(3));
        assertTrue(!c_.eq(r_));
        r_ =new RatePoint(c_);
        assertTrue(c_.eq(r_));
        assertTrue(new CustPoint(1,2).eq(c_.toCustPoint()));
        assertTrue(new RatePoint(new CustPoint(1,2)).eq(c_));
        assertEq("1,2",c_.display());
    }
}
