package code.maths.geo;

import code.maths.EquallableMathUtil;
import code.maths.Rate;
import org.junit.Test;


public class EdgeThreeDimensionsTest extends EquallableMathUtil {

    @Test
    public void intersection1Test() {
        RatePointThreeDims one_ = pt(5, 3, 0);
        RatePointThreeDims two_ = pt(3, 5, 0);
        RatePointThreeDims three_ = pt(2, 3, 0);
        RatePointThreeDims four_ = pt(4, 2, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(one_, two_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(!eOne_.intersection(eTwo_));
    }

    @Test
    public void intersection2Test() {
        RatePointThreeDims one_ = pt(5, 3, 0);
        RatePointThreeDims two_ = pt(3, 5, 0);
        RatePointThreeDims three_ = pt(2, 3, 1);
        RatePointThreeDims four_ = pt(4, 2, 1);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(one_, two_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(!eOne_.intersection(eTwo_));
    }

    @Test
    public void intersection3Test() {
        RatePointThreeDims one_ = pt(5, 3, 0);
        RatePointThreeDims two_ = pt(3, 5, 0);
        RatePointThreeDims three_ = pt(2, 3, 0);
        RatePointThreeDims four_ = pt(8, 0, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(one_, two_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(!eOne_.intersection(eTwo_));
    }

    @Test
    public void intersection4Test() {
        RatePointThreeDims one_ = pt(8, 0, 0);
        RatePointThreeDims two_ = pt(3, 5, 0);
        RatePointThreeDims three_ = pt(2, 3, 0);
        RatePointThreeDims four_ = pt(4, 2, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(one_, two_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(!eOne_.intersection(eTwo_));
    }

    @Test
    public void intersection5Test() {
        RatePointThreeDims one_ = pt(5, 3, 0);
        RatePointThreeDims two_ = pt(3, 5, 0);
        RatePointThreeDims three_ = pt(2, 3, 0);
        RatePointThreeDims four_ = pt(4, 1, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(one_, two_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(!eOne_.intersection(eTwo_));
    }

    @Test
    public void intersection6Test() {
        RatePointThreeDims one_ = pt(5, 3, 1);
        RatePointThreeDims two_ = pt(3, 5, 1);
        RatePointThreeDims three_ = pt(2, 3, 1);
        RatePointThreeDims four_ = pt(8, 0, 1);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(one_, two_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(!eOne_.intersection(eTwo_));
    }

    @Test
    public void intersection7Test() {
        RatePointThreeDims one_ = pt(8, 0, 1);
        RatePointThreeDims two_ = pt(3, 5, 1);
        RatePointThreeDims three_ = pt(2, 3, 1);
        RatePointThreeDims four_ = pt(4, 2, 1);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(one_, two_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(!eOne_.intersection(eTwo_));
    }

    @Test
    public void intersection8Test() {
        RatePointThreeDims one_ = pt(5, 3, 0);
        RatePointThreeDims two_ = pt(3, 5, 0);
        RatePointThreeDims three_ = pt(6, 2, 0);
        RatePointThreeDims four_ = pt(8, 0, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(one_, two_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(!eOne_.intersection(eTwo_));
    }
    @Test
    public void intersection9Test() {
        RatePointThreeDims one_ = pt(10, -2, 0);
        RatePointThreeDims two_ = pt(3, 5, 0);
        RatePointThreeDims three_ = pt(2, 3, 0);
        RatePointThreeDims four_ = pt(10, -1, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(one_, two_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(eOne_.intersection(eTwo_));
    }

    @Test
    public void intersection10Test() {
        RatePointThreeDims one_ = pt(8, 0, 0);
        RatePointThreeDims two_ = pt(3, 5, 0);
        RatePointThreeDims three_ = pt(2, 3, 0);
        RatePointThreeDims four_ = pt(8, 0, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(one_, two_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(eOne_.intersection(eTwo_));
    }

    @Test
    public void intersection11Test() {
        RatePointThreeDims one_ = pt(6, 2, 0);
        RatePointThreeDims two_ = pt(3, 5, 0);
        RatePointThreeDims three_ = pt(5, 3, 0);
        RatePointThreeDims four_ = pt(8, 0, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(one_, two_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(eOne_.intersection(eTwo_));
    }

    @Test
    public void intersection12Test() {
        RatePointThreeDims one_ = pt(6, 2, 0);
        RatePointThreeDims two_ = pt(3, 5, 0);
        RatePointThreeDims three_ = pt(5, 3, 0);
        RatePointThreeDims four_ = pt(8, 0, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(one_, two_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(eTwo_.intersection(eOne_));
    }

    @Test
    public void intersection13Test() {
        RatePointThreeDims one_ = pt(6, 2, 0);
        RatePointThreeDims two_ = pt(3, 5, 0);
        RatePointThreeDims three_ = pt(5, 3, 0);
        RatePointThreeDims four_ = pt(8, 0, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(two_, one_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(four_, three_);
        assertTrue(eOne_.intersection(eTwo_));
    }

    @Test
    public void intersection14Test() {
        RatePointThreeDims one_ = pt(8, 0, 0);
        RatePointThreeDims two_ = pt(3, 5, 0);
        RatePointThreeDims three_ = pt(5, 3, 0);
        RatePointThreeDims four_ = pt(6, 2, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(two_, one_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(four_, three_);
        assertTrue(eOne_.intersection(eTwo_));
    }

    @Test
    public void intersection15Test() {
        RatePointThreeDims one_ = pt(8, 0, 0);
        RatePointThreeDims two_ = pt(3, 5, 0);
        RatePointThreeDims three_ = pt(5, 3, 0);
        RatePointThreeDims four_ = pt(6, 2, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(two_, one_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(eOne_.intersection(eTwo_));
    }

    @Test
    public void intersection16Test() {
        RatePointThreeDims one_ = pt(5, 3, 3);
        RatePointThreeDims two_ = pt(3, 5, 5);
        RatePointThreeDims three_ = pt(2, 3, 6);
        RatePointThreeDims four_ = pt(4, 2, 4);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(one_, two_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(!eOne_.intersection(eTwo_));
    }

    @Test
    public void intersection17Test() {
        RatePointThreeDims one_ = pt(8, 0, 0);
        RatePointThreeDims two_ = pt(3, 5, 0);
        RatePointThreeDims three_ = pt(2, 3, 0);
        RatePointThreeDims four_ = pt(8, 0, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(one_, two_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(eTwo_.intersection(eOne_));
    }

    @Test
    public void intersection18Test() {
        RatePointThreeDims one_ = pt(6, 2, 0);
        RatePointThreeDims two_ = pt(3, 5, 0);
        RatePointThreeDims three_ = pt(5, 3, 0);
        RatePointThreeDims four_ = pt(8, 0, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(one_, two_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(eTwo_.intersection(eOne_));
    }

    @Test
    public void intersection19Test() {
        RatePointThreeDims one_ = pt(6, 2, 0);
        RatePointThreeDims two_ = pt(3, 5, 0);
        RatePointThreeDims three_ = pt(5, 3, 0);
        RatePointThreeDims four_ = pt(8, 0, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(one_, two_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(eOne_.intersection(eTwo_));
    }

    @Test
    public void intersection20Test() {
        RatePointThreeDims one_ = pt(6, 2, 0);
        RatePointThreeDims two_ = pt(3, 5, 0);
        RatePointThreeDims three_ = pt(5, 3, 0);
        RatePointThreeDims four_ = pt(8, 0, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(two_, one_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(four_, three_);
        assertTrue(eTwo_.intersection(eOne_));
    }

    @Test
    public void intersection21Test() {
        RatePointThreeDims one_ = pt(8, 0, 0);
        RatePointThreeDims two_ = pt(3, 5, 0);
        RatePointThreeDims three_ = pt(5, 3, 0);
        RatePointThreeDims four_ = pt(6, 2, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(two_, one_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(four_, three_);
        assertTrue(eTwo_.intersection(eOne_));
    }

    @Test
    public void intersection22Test() {
        RatePointThreeDims one_ = pt(8, 0, 0);
        RatePointThreeDims two_ = pt(3, 5, 0);
        RatePointThreeDims three_ = pt(5, 3, 0);
        RatePointThreeDims four_ = pt(6, 2, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(two_, one_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(eTwo_.intersection(eOne_));
    }

    @Test
    public void intersection23Test() {
        RatePointThreeDims one_ = pt(8, 0, 0);
        RatePointThreeDims two_ = pt(3, 5, 0);
        RatePointThreeDims three_ = pt(5, 3, 0);
        RatePointThreeDims four_ = pt(6, 2, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(two_, one_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(eTwo_.intersection(eOne_));
    }

    @Test
    public void intersection24Test() {
        RatePointThreeDims one_ = pt(8, 0, 0);
        RatePointThreeDims two_ = pt(3, 5, 0);
        RatePointThreeDims three_ = pt(5, 3, 0);
        RatePointThreeDims four_ = pt(6, 2, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(two_, one_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(four_, three_);
        assertTrue(eTwo_.intersection(eOne_));
    }

    @Test
    public void intersection25Test() {
        RatePointThreeDims one_ = pt(8, 0, 0);
        RatePointThreeDims two_ = pt(3, 5, 0);
        RatePointThreeDims three_ = pt(5, 3, 0);
        RatePointThreeDims four_ = pt(6, 2, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(one_, two_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(four_, three_);
        assertTrue(eTwo_.intersection(eOne_));
    }

    @Test
    public void intersection26Test() {
        RatePointThreeDims one_ = pt(8, 0, 0);
        RatePointThreeDims two_ = pt(3, 5, 0);
        RatePointThreeDims three_ = pt(5, 3, 0);
        RatePointThreeDims four_ = pt(6, 2, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(one_, two_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(eTwo_.intersection(eOne_));
    }

    @Test
    public void intersection27Test() {
        RatePointThreeDims one_ = pt(8, 0, 0);
        RatePointThreeDims two_ = pt(0, 0, 0);
        RatePointThreeDims three_ = pt(4, 0, 0);
        RatePointThreeDims four_ = pt(6, 2, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(two_, one_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(four_, three_);
        assertTrue(eTwo_.intersection(eOne_));
    }

    @Test
    public void intersection28Test() {
        RatePointThreeDims one_ = pt(8, 0, 0);
        RatePointThreeDims two_ = pt(0, 0, 0);
        RatePointThreeDims three_ = pt(4, 0, 0);
        RatePointThreeDims four_ = pt(6, 2, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(two_, one_);
        eOne_.setFirst(two_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(four_, three_);
        eTwo_.setSecond(three_);
        assertTrue(eTwo_.intersection(eOne_));
    }

    private static RatePointThreeDims pt(int _x, int _y, int _z) {
        return new RatePointThreeDims(new Rate(_x), new Rate(_y), new Rate(_z));
    }

}
