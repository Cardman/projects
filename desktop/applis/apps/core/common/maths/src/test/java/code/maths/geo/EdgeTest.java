package code.maths.geo;

import code.maths.EquallableMathUtil;
import code.maths.Rate;
import org.junit.Test;


public class EdgeTest extends EquallableMathUtil {

    @Test
    public void intersect1Test() {
        RatePoint one_ = newPt(1, 0);
        RatePoint two_ = newPt(3, 2);
        RatePoint three_ = newPt(0, 0);
        RatePoint four_ = newPt(4, 2);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(twoEd_.intersect(oneEd_));
    }

    @Test
    public void intersect2Test() {
        RatePoint one_ = newPt(3, 2);
        RatePoint two_ = newPt(5, 4);
        RatePoint three_ = newPt(0, 0);
        RatePoint four_ = newPt(4, 2);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(!twoEd_.intersect(oneEd_));
    }

    @Test
    public void intersect3Test() {
        RatePoint one_ = newPt(2, 1);
        RatePoint two_ = newPt(6, 3);
        RatePoint three_ = newPt(0, 0);
        RatePoint four_ = newPt(4, 2);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(twoEd_.intersect(oneEd_));
    }

    @Test
    public void intersect4Test() {
        RatePoint one_ = newPt(4, 2);
        RatePoint two_ = newPt(6, 3);
        RatePoint three_ = newPt(0, 0);
        RatePoint four_ = newPt(4, 2);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(twoEd_.intersect(oneEd_));
    }

    @Test
    public void intersect5Test() {
        RatePoint one_ = newPt(2, 1);
        RatePoint two_ = newPt(0, 3);
        RatePoint three_ = newPt(0, 0);
        RatePoint four_ = newPt(4, 2);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(twoEd_.intersect(oneEd_));
    }

    @Test
    public void intersect6Test() {
        RatePoint one_ = newPt(4, 1);
        RatePoint two_ = newPt(6, 3);
        RatePoint three_ = newPt(0, 0);
        RatePoint four_ = newPt(4, 2);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(!twoEd_.intersect(oneEd_));
    }

    @Test
    public void intersect7Test() {
        RatePoint one_ = newPt(0, 0);
        RatePoint two_ = newPt(4, 2);
        RatePoint three_ = newPt(2, 1);
        RatePoint four_ = newPt(6, 3);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(twoEd_.intersect(oneEd_));
    }

    @Test
    public void intersect8Test() {
        RatePoint one_ = newPt(4, 2);
        RatePoint two_ = newPt(0, 0);
        RatePoint three_ = newPt(4, 2);
        RatePoint four_ = newPt(6, 3);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(twoEd_.intersect(oneEd_));
    }

    @Test
    public void intersectNotContainsBound1Test() {
        RatePoint one_ = newPt(4, 2);
        RatePoint two_ = newPt(0, 0);
        RatePoint three_ = newPt(4, 2);
        RatePoint four_ = newPt(6, 3);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(!twoEd_.intersectNotContainsBound(oneEd_));
    }

    @Test
    public void intersectNotContainsBound2Test() {
        RatePoint one_ = newPt(0, 0);
        RatePoint two_ = newPt(4, 2);
        RatePoint three_ = newPt(4, 2);
        RatePoint four_ = newPt(6, 3);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(!twoEd_.intersectNotContainsBound(oneEd_));
    }

    @Test
    public void intersectNotContainsBound3Test() {
        RatePoint one_ = newPt(4, 2);
        RatePoint two_ = newPt(0, 0);
        RatePoint three_ = newPt(4, 2);
        RatePoint four_ = newPt(6, 3);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(!oneEd_.intersectNotContainsBound(twoEd_));
    }

    @Test
    public void intersectNotContainsBound4Test() {
        RatePoint one_ = newPt(0, 0);
        RatePoint two_ = newPt(4, 2);
        RatePoint three_ = newPt(4, 2);
        RatePoint four_ = newPt(6, 3);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(!oneEd_.intersectNotContainsBound(twoEd_));
    }

    @Test
    public void intersectNotContainsBound5Test() {
        RatePoint one_ = newPt(4, 2);
        RatePoint two_ = newPt(0, 0);
        RatePoint three_ = newPt(2, 1);
        RatePoint four_ = newPt(6, 3);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(four_, three_);
        assertTrue(oneEd_.intersectNotContainsBound(twoEd_));
    }

    @Test
    public void intersectNotContainsBound6Test() {
        RatePoint one_ = newPt(4, 2);
        RatePoint two_ = newPt(0, 0);
        RatePoint three_ = newPt(6, 3);
        RatePoint four_ = newPt(2, 1);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(oneEd_.intersectNotContainsBound(twoEd_));
    }

    @Test
    public void intersectNotContainsBound7Test() {
        RatePoint one_ = newPt(4, 2);
        RatePoint two_ = newPt(0, 0);
        RatePoint three_ = newPt(2, 1);
        RatePoint four_ = newPt(-2, 1);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(oneEd_.intersectNotContainsBound(twoEd_));
    }

    @Test
    public void intersectNotContainsBound8Test() {
        RatePoint one_ = newPt(0, 0);
        RatePoint two_ = newPt(4, 2);
        RatePoint three_ = newPt(0, 0);
        RatePoint four_ = newPt(4, 2);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(oneEd_.intersectNotContainsBound(twoEd_));
    }

    @Test
    public void intersectNotContainsBound9Test() {
        RatePoint one_ = newPt(4, 2);
        RatePoint two_ = newPt(0, 0);
        RatePoint three_ = newPt(0, 0);
        RatePoint four_ = newPt(4, 2);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(oneEd_.intersectNotContainsBound(twoEd_));
    }

    @Test
    public void intersectNotContains1Test() {
        RatePoint one_ = newPt(1, 0);
        RatePoint two_ = newPt(3, 2);
        RatePoint three_ = newPt(0, 0);
        RatePoint four_ = newPt(4, 2);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(twoEd_.intersectNotContains(oneEd_));
    }

    @Test
    public void intersectNotContains2Test() {
        RatePoint one_ = newPt(3, 2);
        RatePoint two_ = newPt(5, 4);
        RatePoint three_ = newPt(0, 0);
        RatePoint four_ = newPt(4, 2);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(!twoEd_.intersectNotContains(oneEd_));
    }

    @Test
    public void intersectNotContains3Test() {
        RatePoint one_ = newPt(2, 1);
        RatePoint two_ = newPt(6, 3);
        RatePoint three_ = newPt(0, 0);
        RatePoint four_ = newPt(4, 2);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(!twoEd_.intersectNotContains(oneEd_));
    }

    @Test
    public void intersectNotContains4Test() {
        RatePoint one_ = newPt(4, 2);
        RatePoint two_ = newPt(6, 3);
        RatePoint three_ = newPt(0, 0);
        RatePoint four_ = newPt(4, 2);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(!twoEd_.intersectNotContains(oneEd_));
    }

    @Test
    public void intersectNotContains5Test() {
        RatePoint one_ = newPt(2, 1);
        RatePoint two_ = newPt(0, 3);
        RatePoint three_ = newPt(0, 0);
        RatePoint four_ = newPt(4, 2);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(!twoEd_.intersectNotContains(oneEd_));
    }

    @Test
    public void intersectNotContains6Test() {
        RatePoint one_ = newPt(4, 1);
        RatePoint two_ = newPt(6, 3);
        RatePoint three_ = newPt(0, 0);
        RatePoint four_ = newPt(4, 2);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(!twoEd_.intersectNotContains(oneEd_));
    }

    @Test
    public void intersectNotContains7Test() {
        RatePoint one_ = newPt(0, 0);
        RatePoint two_ = newPt(4, 2);
        RatePoint three_ = newPt(2, 1);
        RatePoint four_ = newPt(6, 3);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(!twoEd_.intersectNotContains(oneEd_));
    }

    @Test
    public void intersectNotContains8Test() {
        RatePoint one_ = newPt(4, 2);
        RatePoint two_ = newPt(0, 0);
        RatePoint three_ = newPt(4, 2);
        RatePoint four_ = newPt(6, 3);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(!twoEd_.intersectNotContains(oneEd_));
    }
    @Test
    public void displayTest() {
        RatePoint one_ = newPt(4, 2);
        RatePoint two_ = newPt(0, 0);
        Edge oneEd_ = new Edge(one_, two_);
        RatePoint three_ = newPt(2, 5);
        RatePoint four_ = newPt(6, 3);
        oneEd_.setFirst(three_);
        oneEd_.setSecond(four_);
        assertEq("2,5 6,3",oneEd_.display());
    }

    private static RatePoint newPt(int _x, int _y) {
        return new RatePoint(new Rate(_x), new Rate(_y));
    }

}
