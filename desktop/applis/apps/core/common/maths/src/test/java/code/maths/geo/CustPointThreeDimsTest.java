package code.maths.geo;

import code.maths.EquallableMathUtil;
import code.maths.Rate;
import org.junit.Test;

public class CustPointThreeDimsTest extends EquallableMathUtil {
    @Test
    public void new_CustPointThreeDims_test() {
        CustPointThreeDims c_ = CustPointThreeDims.newCustPointThreeDims("1,2,3");
        assertEq(1,c_.getXcoords());
        assertEq(2,c_.getYcoords());
        assertEq(3,c_.getZcoords());
    }
    @Test
    public void eq1Test() {
        CustPointThreeDims c_ = CustPointThreeDims.newCustPointThreeDims("1,2,3");
        CustPointThreeDims d_ = CustPointThreeDims.newCustPointThreeDims("1,2,3");
        assertTrue(c_.eq(d_));
    }
    @Test
    public void eq2Test() {
        CustPointThreeDims c_ = CustPointThreeDims.newCustPointThreeDims("1,2,3");
        CustPointThreeDims d_ = CustPointThreeDims.newCustPointThreeDims("1,2,4");
        assertTrue(!c_.eq(d_));
    }
    @Test
    public void eq3Test() {
        CustPointThreeDims c_ = CustPointThreeDims.newCustPointThreeDims("1,2,3");
        CustPointThreeDims d_ = CustPointThreeDims.newCustPointThreeDims("1,4,3");
        assertTrue(!c_.eq(d_));
    }
    @Test
    public void eq4Test() {
        CustPointThreeDims c_ = CustPointThreeDims.newCustPointThreeDims("1,2,3");
        CustPointThreeDims d_ = CustPointThreeDims.newCustPointThreeDims("4,2,3");
        assertTrue(!c_.eq(d_));
    }
    @Test
    public void new_RatePointThreeDims_test() {
        RatePointThreeDims c_ = RatePointThreeDims.newCustRatePoint("1,2,3");
        assertEq(new Rate(1),c_.getXcoords());
        assertEq(new Rate(2),c_.getYcoords());
        assertEq(new Rate(3),c_.getZcoords());
        RatePointThreeDims r_ =new RatePointThreeDims(c_);
        r_.setXcoords(new Rate(4));
        assertTrue(!c_.eq(r_));
        r_ =new RatePointThreeDims(c_);
        r_.setYcoords(new Rate(4));
        assertTrue(!c_.eq(r_));
        r_ =new RatePointThreeDims(c_);
        r_.setZcoords(new Rate(4));
        assertTrue(!c_.eq(r_));
        r_ =new RatePointThreeDims(c_);
        assertTrue(c_.eq(r_));
        assertTrue(new CustPointThreeDims(1,2,3).eq(c_.toCustPoint()));
        assertTrue(new RatePointThreeDims(new CustPointThreeDims(1,2,3)).eq(c_));
        assertEq("1,2,3",c_.display());
    }
}
