package code.maths.geo;
import static code.maths.EquallableMathUtil.assertEq;

import org.junit.Test;

import code.maths.Rate;
import code.maths.geo.CustLine;
import code.maths.geo.CustPoint;
import code.maths.geo.RatePoint;

@SuppressWarnings("static-method")
public class CustLineTest {

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
}
