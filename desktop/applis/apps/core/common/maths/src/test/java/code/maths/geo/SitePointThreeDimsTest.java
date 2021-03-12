package code.maths.geo;

import code.maths.EquallableMathUtil;
import code.maths.Rate;
import org.junit.Test;


public class SitePointThreeDimsTest extends EquallableMathUtil {

    @Test
    public void new_SitePointThreeDims_1Test() {
        RatePointThreeDims one_ = pt(5, 3, 0);
        RatePointThreeDims two_ = pt(3, 5, 0);
        RatePointThreeDims three_ = pt(2, 3, 0);
//        RatePointThreeDims four_ = new RatePointThreeDims(4, 2, 0);
        VectThreeDims eOne_ = new VectThreeDims(one_, two_);
        SitePointThreeDims s_ = new SitePointThreeDims(three_, one_, eOne_);
        assertEq(SiteInfo.QUAD_FOUR, s_.getNumber());
        assertSame(three_, s_.getPoint());
    }

    @Test
    public void new_SitePointThreeDims_2Test() {
        RatePointThreeDims one_ = pt(5, 3, -1);
        RatePointThreeDims two_ = pt(3, 5, 1);
        RatePointThreeDims three_ = pt(2, 3, 0);
//        RatePointThreeDims four_ = new RatePointThreeDims(4, 2, 0);
        VectThreeDims eOne_ = new VectThreeDims(one_, two_);
        SitePointThreeDims s_ = new SitePointThreeDims(three_, one_, eOne_);
        assertEq(SiteInfo.QUAD_FOUR, s_.getNumber());
    }

    @Test
    public void new_SitePointThreeDims_3Test() {
        RatePointThreeDims one_ = pt(5, 3, 0);
        RatePointThreeDims two_ = pt(3, 5, 0);
        RatePointThreeDims three_ = pt(4, 5, 0);
//        RatePointThreeDims four_ = new RatePointThreeDims(4, 2, 0);
        VectThreeDims eOne_ = new VectThreeDims(one_, two_);
        SitePointThreeDims s_ = new SitePointThreeDims(three_, one_, eOne_);
        assertEq(SiteInfo.QUAD_ONE, s_.getNumber());
    }

    @Test
    public void new_SitePointThreeDims_4Test() {
        RatePointThreeDims one_ = pt(5, 3, -1);
        RatePointThreeDims two_ = pt(3, 5, 1);
        RatePointThreeDims three_ = pt(4, 5, 0);
//        RatePointThreeDims four_ = new RatePointThreeDims(4, 2, 0);
        VectThreeDims eOne_ = new VectThreeDims(one_, two_);
        SitePointThreeDims s_ = new SitePointThreeDims(three_, one_, eOne_);
        assertEq(SiteInfo.QUAD_ONE, s_.getNumber());
    }

    @Test
    public void new_SitePointThreeDims_5Test() {
        RatePointThreeDims one_ = pt(5, 3, 0);
        RatePointThreeDims two_ = pt(3, 5, 0);
        RatePointThreeDims three_ = pt(8, 3, 0);
//        RatePointThreeDims four_ = new RatePointThreeDims(4, 2, 0);
        VectThreeDims eOne_ = new VectThreeDims(one_, two_);
        SitePointThreeDims s_ = new SitePointThreeDims(three_, one_, eOne_);
        assertEq(SiteInfo.QUAD_TWO, s_.getNumber());
    }

    @Test
    public void new_SitePointThreeDims_6Test() {
        RatePointThreeDims one_ = pt(5, 3, -1);
        RatePointThreeDims two_ = pt(3, 5, 1);
        RatePointThreeDims three_ = pt(8, 3, 0);
//        RatePointThreeDims four_ = new RatePointThreeDims(4, 2, 0);
        VectThreeDims eOne_ = new VectThreeDims(one_, two_);
        SitePointThreeDims s_ = new SitePointThreeDims(three_, one_, eOne_);
        assertEq(SiteInfo.QUAD_TWO, s_.getNumber());
    }

    @Test
    public void new_SitePointThreeDims_7Test() {
        RatePointThreeDims one_ = pt(5, 3, 0);
        RatePointThreeDims two_ = pt(3, 5, 0);
        RatePointThreeDims three_ = pt(3, -3, 0);
//        RatePointThreeDims four_ = new RatePointThreeDims(4, 2, 0);
        VectThreeDims eOne_ = new VectThreeDims(one_, two_);
        SitePointThreeDims s_ = new SitePointThreeDims(three_, one_, eOne_);
        assertEq(SiteInfo.QUAD_THREE, s_.getNumber());
    }

    @Test
    public void new_SitePointThreeDims_8Test() {
        RatePointThreeDims one_ = pt(5, 3, -1);
        RatePointThreeDims two_ = pt(3, 5, 1);
        RatePointThreeDims three_ = pt(3, -3, 0);
//        RatePointThreeDims four_ = new RatePointThreeDims(4, 2, 0);
        VectThreeDims eOne_ = new VectThreeDims(one_, two_);
        SitePointThreeDims s_ = new SitePointThreeDims(three_, one_, eOne_);
        assertEq(SiteInfo.QUAD_THREE, s_.getNumber());
    }

    @Test
    public void new_SitePointThreeDims_9Test() {
        RatePointThreeDims one_ = pt(5, 0, 3);
        RatePointThreeDims two_ = pt(3, 0, 5);
        RatePointThreeDims three_ = pt(2, 0, 3);
//        RatePointThreeDims four_ = new RatePointThreeDims(4, 2, 0);
        VectThreeDims eOne_ = new VectThreeDims(one_, two_);
        SitePointThreeDims s_ = new SitePointThreeDims(three_, one_, eOne_);
        assertEq(SiteInfo.QUAD_FOUR, s_.getNumber());
    }

    @Test
    public void new_SitePointThreeDims_10Test() {
        RatePointThreeDims one_ = pt(5, 0, 3);
        RatePointThreeDims two_ = pt(3, 0, 5);
        RatePointThreeDims three_ = pt(4, 0, 5);
//        RatePointThreeDims four_ = new RatePointThreeDims(4, 2, 0);
        VectThreeDims eOne_ = new VectThreeDims(one_, two_);
        SitePointThreeDims s_ = new SitePointThreeDims(three_, one_, eOne_);
        assertEq(SiteInfo.QUAD_ONE, s_.getNumber());
    }

    @Test
    public void new_SitePointThreeDims_11Test() {
        RatePointThreeDims one_ = pt(5, 0, 3);
        RatePointThreeDims two_ = pt(3, 0, 5);
        RatePointThreeDims three_ = pt(8, 0, 3);
//        RatePointThreeDims four_ = new RatePointThreeDims(4, 2, 0);
        VectThreeDims eOne_ = new VectThreeDims(one_, two_);
        SitePointThreeDims s_ = new SitePointThreeDims(three_, one_, eOne_);
        assertEq(SiteInfo.QUAD_TWO, s_.getNumber());
    }

    @Test
    public void new_SitePointThreeDims_12Test() {
        RatePointThreeDims one_ = pt(5, 0, 3);
        RatePointThreeDims two_ = pt(3, 0, 5);
        RatePointThreeDims three_ = pt(3, 0, -3);
//        RatePointThreeDims four_ = new RatePointThreeDims(4, 2, 0);
        VectThreeDims eOne_ = new VectThreeDims(one_, two_);
        SitePointThreeDims s_ = new SitePointThreeDims(three_, one_, eOne_);
        assertEq(SiteInfo.QUAD_THREE, s_.getNumber());
    }
    @Test
    public void new_SitePointThreeDims_13Test() {
        RatePointThreeDims one_ = pt(0, 5, 3);
        RatePointThreeDims two_ = pt(0, 3, 5);
        RatePointThreeDims three_ = pt(0, 2, 3);
//        RatePointThreeDims four_ = new RatePointThreeDims(4, 2, 0);
        VectThreeDims eOne_ = new VectThreeDims(one_, two_);
        SitePointThreeDims s_ = new SitePointThreeDims(three_, one_, eOne_);
        assertEq(SiteInfo.QUAD_FOUR, s_.getNumber());
    }

    @Test
    public void new_SitePointThreeDims_14Test() {
        RatePointThreeDims one_ = pt(0, 5, 3);
        RatePointThreeDims two_ = pt(0, 3, 5);
        RatePointThreeDims three_ = pt(0, 4, 5);
//        RatePointThreeDims four_ = new RatePointThreeDims(4, 2, 0);
        VectThreeDims eOne_ = new VectThreeDims(one_, two_);
        SitePointThreeDims s_ = new SitePointThreeDims(three_, one_, eOne_);
        assertEq(SiteInfo.QUAD_ONE, s_.getNumber());
    }

    @Test
    public void new_SitePointThreeDims_15Test() {
        RatePointThreeDims one_ = pt(0, 5, 3);
        RatePointThreeDims two_ = pt(0, 3, 5);
        RatePointThreeDims three_ = pt(0, 8, 3);
//        RatePointThreeDims four_ = new RatePointThreeDims(4, 2, 0);
        VectThreeDims eOne_ = new VectThreeDims(one_, two_);
        SitePointThreeDims s_ = new SitePointThreeDims(three_, one_, eOne_);
        assertEq(SiteInfo.QUAD_TWO, s_.getNumber());
    }

    @Test
    public void new_SitePointThreeDims_16Test() {
        RatePointThreeDims one_ = pt(0, 5, 3);
        RatePointThreeDims two_ = pt(0, 3, 5);
        RatePointThreeDims three_ = pt(0, 3, -3);
//        RatePointThreeDims four_ = new RatePointThreeDims(4, 2, 0);
        VectThreeDims eOne_ = new VectThreeDims(one_, two_);
        SitePointThreeDims s_ = new SitePointThreeDims(three_, one_, eOne_);
        assertEq(SiteInfo.QUAD_THREE, s_.getNumber());
    }

    private static RatePointThreeDims pt(int _x, int _y, int _z) {
        return new RatePointThreeDims(new Rate(_x), new Rate(_y), new Rate(_z));
    }
}
