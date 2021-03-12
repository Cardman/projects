package code.maths.geo;

import code.maths.EquallableMathUtil;
import code.maths.Rate;
import org.junit.Test;

public class TetrahedronTest extends EquallableMathUtil {
    @Test
    public void getCircumCenter1Test() {
        RatePointThreeDims one_ = new RatePointThreeDims();
        px(one_, 0);
        py(one_);
        pz(one_);
        RatePointThreeDims two_ = new RatePointThreeDims();
        px(two_, 0);
        py(two_);
        pz(two_);
        RatePointThreeDims three_ = new RatePointThreeDims();
        px(three_, 0);
        py(three_);
        pz(three_);
        RatePointThreeDims four_ = new RatePointThreeDims();
        px(four_, 0);
        py(four_);
        pz(four_);
        Tetrahedron t_ = new Tetrahedron(one_,two_,three_,four_);
        assertNull(t_.getCircumCenter());
    }

    @Test
    public void getCircumCenter2Test() {
        RatePointThreeDims one_ = new RatePointThreeDims();
        px(one_, 0);
        py(one_);
        pz(one_);
        RatePointThreeDims two_ = new RatePointThreeDims();
        px(two_, 0);
        py(two_);
        pz(two_);
        RatePointThreeDims three_ = new RatePointThreeDims();
        px(three_, 0);
        py(three_);
        pz(three_);
        RatePointThreeDims four_ = new RatePointThreeDims();
        px(four_, 0);
        py(four_);
        pz(four_);
        Tetrahedron t_ = new Tetrahedron(one_,two_,three_,four_);
        RatePointThreeDims pt_ = new RatePointThreeDims();
        px(pt_, 1);
        py(pt_);
        pz(pt_);
        assertTrue(!t_.isInCircum(pt_));
        assertNotNull(t_.getGravityCenter());
        assertNotNull(t_.display());
        assertNotNull(t_.getEdges());
    }

    private static void pz(RatePointThreeDims _pt) {
        _pt.setZcoords(new Rate(0));
    }

    private static void py(RatePointThreeDims _pt) {
        _pt.setYcoords(new Rate(0));
    }

    private static void px(RatePointThreeDims _pt, int _i) {
        _pt.setXcoords(new Rate(_i));
    }

//    @Test
//    public void getCircumCenter3Test() {
//        RatePointThreeDims one_ = new RatePointThreeDims();
//        one_.setXcoords(0);
//        one_.setYcoords(0);
//        one_.setZcoords(0);
//        RatePointThreeDims two_ = new RatePointThreeDims();
//        two_.setXcoords(1);
//        two_.setYcoords(0);
//        two_.setZcoords(0);
//        RatePointThreeDims three_ = new RatePointThreeDims();
//        three_.setXcoords(0);
//        three_.setYcoords(1);
//        three_.setZcoords(0);
//        RatePointThreeDims four_ = new RatePointThreeDims();
//        four_.setXcoords(0);
//        four_.setYcoords(0);
//        four_.setZcoords(0);
//        assertNotNull(Tetrahedron.getCircumCenter(one_,two_,three_));
//        assertNotNull(Tetrahedron.getEq(one_,two_,three_));
//    }
}
