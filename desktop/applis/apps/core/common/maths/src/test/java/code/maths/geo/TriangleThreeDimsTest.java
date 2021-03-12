package code.maths.geo;

import code.maths.EquallableMathUtil;
import code.maths.Rate;
import org.junit.Test;

public class TriangleThreeDimsTest extends EquallableMathUtil {
    @Test
    public void getCircumCenter1Test() {
        RatePointThreeDims one_ = new RatePointThreeDims();
        px(one_);
        py(one_);
        pz(one_);
        RatePointThreeDims two_ = new RatePointThreeDims();
        px(two_);
        py(two_);
        pz(two_);
        RatePointThreeDims three_ = new RatePointThreeDims();
        px(three_);
        py(three_);
        pz(three_);
        RatePointThreeDims four_ = new RatePointThreeDims();
        px(four_);
        py(four_);
        pz(four_);
        TriangleThreeDims t_ = new TriangleThreeDims(one_,two_,three_);
        assertNotNull(t_.getCircumCenter());
        assertNotNull(t_.getGravityCenter());
    }
    @Test
    public void getCircumCenter2Test() {
        RatePointThreeDims one_ = new RatePointThreeDims();
        px(one_);
        py(one_);
        pz(one_);
        RatePointThreeDims two_ = new RatePointThreeDims();
        px(two_);
        py(two_);
        pz(two_);
        RatePointThreeDims three_ = new RatePointThreeDims();
        px(three_);
        py(three_);
        pz(three_);
        RatePointThreeDims four_ = new RatePointThreeDims();
        px(four_);
        py(four_);
        pz(four_);
        TriangleThreeDims t_ = new TriangleThreeDims(one_,two_,three_);
        assertNotNull(t_.getCircumCenter());
        assertNotNull(t_.display());
    }

    private static void pz(RatePointThreeDims _pt) {
        _pt.setZcoords(new Rate(0));
    }

    private static void py(RatePointThreeDims _pt) {
        _pt.setYcoords(new Rate(0));
    }

    private static void px(RatePointThreeDims _pt) {
        _pt.setXcoords(new Rate(0));
    }
}
