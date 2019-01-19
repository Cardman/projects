package code.maths.geo;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class EdgeThreeDimensionsTest {

    @Test
    public void intersection1Test() {
        CustPointThreeDims one_ = new CustPointThreeDims(5, 3, 0);
        CustPointThreeDims two_ = new CustPointThreeDims(3, 5, 0);
        CustPointThreeDims three_ = new CustPointThreeDims(2, 3, 0);
        CustPointThreeDims four_ = new CustPointThreeDims(4, 2, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(one_, two_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(!eOne_.intersection(eTwo_));
    }

    @Test
    public void intersection2Test() {
        CustPointThreeDims one_ = new CustPointThreeDims(5, 3, 0);
        CustPointThreeDims two_ = new CustPointThreeDims(3, 5, 0);
        CustPointThreeDims three_ = new CustPointThreeDims(2, 3, 1);
        CustPointThreeDims four_ = new CustPointThreeDims(4, 2, 1);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(one_, two_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(!eOne_.intersection(eTwo_));
    }

    @Test
    public void intersection3Test() {
        CustPointThreeDims one_ = new CustPointThreeDims(5, 3, 0);
        CustPointThreeDims two_ = new CustPointThreeDims(3, 5, 0);
        CustPointThreeDims three_ = new CustPointThreeDims(2, 3, 0);
        CustPointThreeDims four_ = new CustPointThreeDims(8, 0, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(one_, two_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(!eOne_.intersection(eTwo_));
    }

    @Test
    public void intersection4Test() {
        CustPointThreeDims one_ = new CustPointThreeDims(8, 0, 0);
        CustPointThreeDims two_ = new CustPointThreeDims(3, 5, 0);
        CustPointThreeDims three_ = new CustPointThreeDims(2, 3, 0);
        CustPointThreeDims four_ = new CustPointThreeDims(4, 2, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(one_, two_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(!eOne_.intersection(eTwo_));
    }

    @Test
    public void intersection5Test() {
        CustPointThreeDims one_ = new CustPointThreeDims(5, 3, 0);
        CustPointThreeDims two_ = new CustPointThreeDims(3, 5, 0);
        CustPointThreeDims three_ = new CustPointThreeDims(2, 3, 0);
        CustPointThreeDims four_ = new CustPointThreeDims(4, 1, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(one_, two_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(!eOne_.intersection(eTwo_));
    }

    @Test
    public void intersection6Test() {
        CustPointThreeDims one_ = new CustPointThreeDims(5, 3, 1);
        CustPointThreeDims two_ = new CustPointThreeDims(3, 5, 1);
        CustPointThreeDims three_ = new CustPointThreeDims(2, 3, 1);
        CustPointThreeDims four_ = new CustPointThreeDims(8, 0, 1);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(one_, two_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(!eOne_.intersection(eTwo_));
    }

    @Test
    public void intersection7Test() {
        CustPointThreeDims one_ = new CustPointThreeDims(8, 0, 1);
        CustPointThreeDims two_ = new CustPointThreeDims(3, 5, 1);
        CustPointThreeDims three_ = new CustPointThreeDims(2, 3, 1);
        CustPointThreeDims four_ = new CustPointThreeDims(4, 2, 1);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(one_, two_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(!eOne_.intersection(eTwo_));
    }

    @Test
    public void intersection8Test() {
        CustPointThreeDims one_ = new CustPointThreeDims(5, 3, 0);
        CustPointThreeDims two_ = new CustPointThreeDims(3, 5, 0);
        CustPointThreeDims three_ = new CustPointThreeDims(6, 2, 0);
        CustPointThreeDims four_ = new CustPointThreeDims(8, 0, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(one_, two_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(!eOne_.intersection(eTwo_));
    }
    @Test
    public void intersection9Test() {
        CustPointThreeDims one_ = new CustPointThreeDims(10, -2, 0);
        CustPointThreeDims two_ = new CustPointThreeDims(3, 5, 0);
        CustPointThreeDims three_ = new CustPointThreeDims(2, 3, 0);
        CustPointThreeDims four_ = new CustPointThreeDims(10, -1, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(one_, two_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(eOne_.intersection(eTwo_));
    }

    @Test
    public void intersection10Test() {
        CustPointThreeDims one_ = new CustPointThreeDims(8, 0, 0);
        CustPointThreeDims two_ = new CustPointThreeDims(3, 5, 0);
        CustPointThreeDims three_ = new CustPointThreeDims(2, 3, 0);
        CustPointThreeDims four_ = new CustPointThreeDims(8, 0, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(one_, two_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(eOne_.intersection(eTwo_));
    }

    @Test
    public void intersection11Test() {
        CustPointThreeDims one_ = new CustPointThreeDims(6, 2, 0);
        CustPointThreeDims two_ = new CustPointThreeDims(3, 5, 0);
        CustPointThreeDims three_ = new CustPointThreeDims(5, 3, 0);
        CustPointThreeDims four_ = new CustPointThreeDims(8, 0, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(one_, two_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(eOne_.intersection(eTwo_));
    }

    @Test
    public void intersection12Test() {
        CustPointThreeDims one_ = new CustPointThreeDims(6, 2, 0);
        CustPointThreeDims two_ = new CustPointThreeDims(3, 5, 0);
        CustPointThreeDims three_ = new CustPointThreeDims(5, 3, 0);
        CustPointThreeDims four_ = new CustPointThreeDims(8, 0, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(one_, two_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(eTwo_.intersection(eOne_));
    }

    @Test
    public void intersection13Test() {
        CustPointThreeDims one_ = new CustPointThreeDims(6, 2, 0);
        CustPointThreeDims two_ = new CustPointThreeDims(3, 5, 0);
        CustPointThreeDims three_ = new CustPointThreeDims(5, 3, 0);
        CustPointThreeDims four_ = new CustPointThreeDims(8, 0, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(two_, one_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(four_, three_);
        assertTrue(eOne_.intersection(eTwo_));
    }

    @Test
    public void intersection14Test() {
        CustPointThreeDims one_ = new CustPointThreeDims(8, 0, 0);
        CustPointThreeDims two_ = new CustPointThreeDims(3, 5, 0);
        CustPointThreeDims three_ = new CustPointThreeDims(5, 3, 0);
        CustPointThreeDims four_ = new CustPointThreeDims(6, 2, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(two_, one_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(four_, three_);
        assertTrue(eOne_.intersection(eTwo_));
    }

    @Test
    public void intersection15Test() {
        CustPointThreeDims one_ = new CustPointThreeDims(8, 0, 0);
        CustPointThreeDims two_ = new CustPointThreeDims(3, 5, 0);
        CustPointThreeDims three_ = new CustPointThreeDims(5, 3, 0);
        CustPointThreeDims four_ = new CustPointThreeDims(6, 2, 0);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(two_, one_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(eOne_.intersection(eTwo_));
    }

    @Test
    public void intersection16Test() {
        CustPointThreeDims one_ = new CustPointThreeDims(5, 3, 3);
        CustPointThreeDims two_ = new CustPointThreeDims(3, 5, 5);
        CustPointThreeDims three_ = new CustPointThreeDims(2, 3, 6);
        CustPointThreeDims four_ = new CustPointThreeDims(4, 2, 4);
        EdgeThreeDimensions eOne_ = new EdgeThreeDimensions(one_, two_);
        EdgeThreeDimensions eTwo_ = new EdgeThreeDimensions(three_, four_);
        assertTrue(!eOne_.intersection(eTwo_));
    }
}
