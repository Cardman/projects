package code.maths.geo;
import static code.maths.EquallableMathUtil.assertEq;

import org.junit.Test;


public class SitePointThreeDimsTest {

    @Test
    public void new_SitePointThreeDims_1Test() {
        CustPointThreeDims one_ = new CustPointThreeDims(5, 3, 0);
        CustPointThreeDims two_ = new CustPointThreeDims(3, 5, 0);
        CustPointThreeDims three_ = new CustPointThreeDims(2, 3, 0);
//        CustPointThreeDims four_ = new CustPointThreeDims(4, 2, 0);
        VectThreeDims eOne_ = new VectThreeDims(one_, two_);
        SitePointThreeDims s_ = new SitePointThreeDims(three_, one_, eOne_);
        assertEq(SitePointThreeDims.QUAD_FOUR, s_.getNumber());
    }

    @Test
    public void new_SitePointThreeDims_2Test() {
        CustPointThreeDims one_ = new CustPointThreeDims(5, 3, -1);
        CustPointThreeDims two_ = new CustPointThreeDims(3, 5, 1);
        CustPointThreeDims three_ = new CustPointThreeDims(2, 3, 0);
//        CustPointThreeDims four_ = new CustPointThreeDims(4, 2, 0);
        VectThreeDims eOne_ = new VectThreeDims(one_, two_);
        SitePointThreeDims s_ = new SitePointThreeDims(three_, one_, eOne_);
        assertEq(SitePointThreeDims.QUAD_FOUR, s_.getNumber());
    }

    @Test
    public void new_SitePointThreeDims_3Test() {
        CustPointThreeDims one_ = new CustPointThreeDims(5, 3, 0);
        CustPointThreeDims two_ = new CustPointThreeDims(3, 5, 0);
        CustPointThreeDims three_ = new CustPointThreeDims(4, 5, 0);
//        CustPointThreeDims four_ = new CustPointThreeDims(4, 2, 0);
        VectThreeDims eOne_ = new VectThreeDims(one_, two_);
        SitePointThreeDims s_ = new SitePointThreeDims(three_, one_, eOne_);
        assertEq(SitePointThreeDims.QUAD_ONE, s_.getNumber());
    }

    @Test
    public void new_SitePointThreeDims_4Test() {
        CustPointThreeDims one_ = new CustPointThreeDims(5, 3, -1);
        CustPointThreeDims two_ = new CustPointThreeDims(3, 5, 1);
        CustPointThreeDims three_ = new CustPointThreeDims(4, 5, 0);
//        CustPointThreeDims four_ = new CustPointThreeDims(4, 2, 0);
        VectThreeDims eOne_ = new VectThreeDims(one_, two_);
        SitePointThreeDims s_ = new SitePointThreeDims(three_, one_, eOne_);
        assertEq(SitePointThreeDims.QUAD_ONE, s_.getNumber());
    }

    @Test
    public void new_SitePointThreeDims_5Test() {
        CustPointThreeDims one_ = new CustPointThreeDims(5, 3, 0);
        CustPointThreeDims two_ = new CustPointThreeDims(3, 5, 0);
        CustPointThreeDims three_ = new CustPointThreeDims(8, 3, 0);
//        CustPointThreeDims four_ = new CustPointThreeDims(4, 2, 0);
        VectThreeDims eOne_ = new VectThreeDims(one_, two_);
        SitePointThreeDims s_ = new SitePointThreeDims(three_, one_, eOne_);
        assertEq(SitePointThreeDims.QUAD_TWO, s_.getNumber());
    }

    @Test
    public void new_SitePointThreeDims_6Test() {
        CustPointThreeDims one_ = new CustPointThreeDims(5, 3, -1);
        CustPointThreeDims two_ = new CustPointThreeDims(3, 5, 1);
        CustPointThreeDims three_ = new CustPointThreeDims(8, 3, 0);
//        CustPointThreeDims four_ = new CustPointThreeDims(4, 2, 0);
        VectThreeDims eOne_ = new VectThreeDims(one_, two_);
        SitePointThreeDims s_ = new SitePointThreeDims(three_, one_, eOne_);
        assertEq(SitePointThreeDims.QUAD_TWO, s_.getNumber());
    }

    @Test
    public void new_SitePointThreeDims_7Test() {
        CustPointThreeDims one_ = new CustPointThreeDims(5, 3, 0);
        CustPointThreeDims two_ = new CustPointThreeDims(3, 5, 0);
        CustPointThreeDims three_ = new CustPointThreeDims(3, -3, 0);
//        CustPointThreeDims four_ = new CustPointThreeDims(4, 2, 0);
        VectThreeDims eOne_ = new VectThreeDims(one_, two_);
        SitePointThreeDims s_ = new SitePointThreeDims(three_, one_, eOne_);
        assertEq(SitePointThreeDims.QUAD_THREE, s_.getNumber());
    }

    @Test
    public void new_SitePointThreeDims_8Test() {
        CustPointThreeDims one_ = new CustPointThreeDims(5, 3, -1);
        CustPointThreeDims two_ = new CustPointThreeDims(3, 5, 1);
        CustPointThreeDims three_ = new CustPointThreeDims(3, -3, 0);
//        CustPointThreeDims four_ = new CustPointThreeDims(4, 2, 0);
        VectThreeDims eOne_ = new VectThreeDims(one_, two_);
        SitePointThreeDims s_ = new SitePointThreeDims(three_, one_, eOne_);
        assertEq(SitePointThreeDims.QUAD_THREE, s_.getNumber());
    }

    @Test
    public void new_SitePointThreeDims_9Test() {
        CustPointThreeDims one_ = new CustPointThreeDims(5, 0, 3);
        CustPointThreeDims two_ = new CustPointThreeDims(3, 0, 5);
        CustPointThreeDims three_ = new CustPointThreeDims(2, 0, 3);
//        CustPointThreeDims four_ = new CustPointThreeDims(4, 2, 0);
        VectThreeDims eOne_ = new VectThreeDims(one_, two_);
        SitePointThreeDims s_ = new SitePointThreeDims(three_, one_, eOne_);
        assertEq(SitePointThreeDims.QUAD_FOUR, s_.getNumber());
    }

    @Test
    public void new_SitePointThreeDims_10Test() {
        CustPointThreeDims one_ = new CustPointThreeDims(5, 0, 3);
        CustPointThreeDims two_ = new CustPointThreeDims(3, 0, 5);
        CustPointThreeDims three_ = new CustPointThreeDims(4, 0, 5);
//        CustPointThreeDims four_ = new CustPointThreeDims(4, 2, 0);
        VectThreeDims eOne_ = new VectThreeDims(one_, two_);
        SitePointThreeDims s_ = new SitePointThreeDims(three_, one_, eOne_);
        assertEq(SitePointThreeDims.QUAD_ONE, s_.getNumber());
    }

    @Test
    public void new_SitePointThreeDims_11Test() {
        CustPointThreeDims one_ = new CustPointThreeDims(5, 0, 3);
        CustPointThreeDims two_ = new CustPointThreeDims(3, 0, 5);
        CustPointThreeDims three_ = new CustPointThreeDims(8, 0, 3);
//        CustPointThreeDims four_ = new CustPointThreeDims(4, 2, 0);
        VectThreeDims eOne_ = new VectThreeDims(one_, two_);
        SitePointThreeDims s_ = new SitePointThreeDims(three_, one_, eOne_);
        assertEq(SitePointThreeDims.QUAD_TWO, s_.getNumber());
    }

    @Test
    public void new_SitePointThreeDims_12Test() {
        CustPointThreeDims one_ = new CustPointThreeDims(5, 0, 3);
        CustPointThreeDims two_ = new CustPointThreeDims(3, 0, 5);
        CustPointThreeDims three_ = new CustPointThreeDims(3, 0, -3);
//        CustPointThreeDims four_ = new CustPointThreeDims(4, 2, 0);
        VectThreeDims eOne_ = new VectThreeDims(one_, two_);
        SitePointThreeDims s_ = new SitePointThreeDims(three_, one_, eOne_);
        assertEq(SitePointThreeDims.QUAD_THREE, s_.getNumber());
    }
    @Test
    public void new_SitePointThreeDims_13Test() {
        CustPointThreeDims one_ = new CustPointThreeDims(0, 5, 3);
        CustPointThreeDims two_ = new CustPointThreeDims(0, 3, 5);
        CustPointThreeDims three_ = new CustPointThreeDims(0, 2, 3);
//        CustPointThreeDims four_ = new CustPointThreeDims(4, 2, 0);
        VectThreeDims eOne_ = new VectThreeDims(one_, two_);
        SitePointThreeDims s_ = new SitePointThreeDims(three_, one_, eOne_);
        assertEq(SitePointThreeDims.QUAD_FOUR, s_.getNumber());
    }

    @Test
    public void new_SitePointThreeDims_14Test() {
        CustPointThreeDims one_ = new CustPointThreeDims(0, 5, 3);
        CustPointThreeDims two_ = new CustPointThreeDims(0, 3, 5);
        CustPointThreeDims three_ = new CustPointThreeDims(0, 4, 5);
//        CustPointThreeDims four_ = new CustPointThreeDims(4, 2, 0);
        VectThreeDims eOne_ = new VectThreeDims(one_, two_);
        SitePointThreeDims s_ = new SitePointThreeDims(three_, one_, eOne_);
        assertEq(SitePointThreeDims.QUAD_ONE, s_.getNumber());
    }

    @Test
    public void new_SitePointThreeDims_15Test() {
        CustPointThreeDims one_ = new CustPointThreeDims(0, 5, 3);
        CustPointThreeDims two_ = new CustPointThreeDims(0, 3, 5);
        CustPointThreeDims three_ = new CustPointThreeDims(0, 8, 3);
//        CustPointThreeDims four_ = new CustPointThreeDims(4, 2, 0);
        VectThreeDims eOne_ = new VectThreeDims(one_, two_);
        SitePointThreeDims s_ = new SitePointThreeDims(three_, one_, eOne_);
        assertEq(SitePointThreeDims.QUAD_TWO, s_.getNumber());
    }

    @Test
    public void new_SitePointThreeDims_16Test() {
        CustPointThreeDims one_ = new CustPointThreeDims(0, 5, 3);
        CustPointThreeDims two_ = new CustPointThreeDims(0, 3, 5);
        CustPointThreeDims three_ = new CustPointThreeDims(0, 3, -3);
//        CustPointThreeDims four_ = new CustPointThreeDims(4, 2, 0);
        VectThreeDims eOne_ = new VectThreeDims(one_, two_);
        SitePointThreeDims s_ = new SitePointThreeDims(three_, one_, eOne_);
        assertEq(SitePointThreeDims.QUAD_THREE, s_.getNumber());
    }
}
