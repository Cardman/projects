package code.maths.geo;

import code.maths.EquallableMathUtil;
import org.junit.Test;


public class EdgeTest extends EquallableMathUtil {

    @Test
    public void intersect1Test() {
        CustPoint one_ = new CustPoint(1, 0);
        CustPoint two_ = new CustPoint(3, 2);
        CustPoint three_ = new CustPoint(0, 0);
        CustPoint four_ = new CustPoint(4, 2);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(twoEd_.intersect(oneEd_));
    }

    @Test
    public void intersect2Test() {
        CustPoint one_ = new CustPoint(3, 2);
        CustPoint two_ = new CustPoint(5, 4);
        CustPoint three_ = new CustPoint(0, 0);
        CustPoint four_ = new CustPoint(4, 2);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(!twoEd_.intersect(oneEd_));
    }

    @Test
    public void intersect3Test() {
        CustPoint one_ = new CustPoint(2, 1);
        CustPoint two_ = new CustPoint(6, 3);
        CustPoint three_ = new CustPoint(0, 0);
        CustPoint four_ = new CustPoint(4, 2);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(twoEd_.intersect(oneEd_));
    }

    @Test
    public void intersect4Test() {
        CustPoint one_ = new CustPoint(4, 2);
        CustPoint two_ = new CustPoint(6, 3);
        CustPoint three_ = new CustPoint(0, 0);
        CustPoint four_ = new CustPoint(4, 2);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(twoEd_.intersect(oneEd_));
    }

    @Test
    public void intersect5Test() {
        CustPoint one_ = new CustPoint(2, 1);
        CustPoint two_ = new CustPoint(0, 3);
        CustPoint three_ = new CustPoint(0, 0);
        CustPoint four_ = new CustPoint(4, 2);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(twoEd_.intersect(oneEd_));
    }

    @Test
    public void intersect6Test() {
        CustPoint one_ = new CustPoint(4, 1);
        CustPoint two_ = new CustPoint(6, 3);
        CustPoint three_ = new CustPoint(0, 0);
        CustPoint four_ = new CustPoint(4, 2);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(!twoEd_.intersect(oneEd_));
    }

    @Test
    public void intersect7Test() {
        CustPoint one_ = new CustPoint(0, 0);
        CustPoint two_ = new CustPoint(4, 2);
        CustPoint three_ = new CustPoint(2, 1);
        CustPoint four_ = new CustPoint(6, 3);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(twoEd_.intersect(oneEd_));
    }

    @Test
    public void intersect8Test() {
        CustPoint one_ = new CustPoint(4, 2);
        CustPoint two_ = new CustPoint(0, 0);
        CustPoint three_ = new CustPoint(4, 2);
        CustPoint four_ = new CustPoint(6, 3);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(twoEd_.intersect(oneEd_));
    }

    @Test
    public void intersectNotContainsBound1Test() {
        CustPoint one_ = new CustPoint(4, 2);
        CustPoint two_ = new CustPoint(0, 0);
        CustPoint three_ = new CustPoint(4, 2);
        CustPoint four_ = new CustPoint(6, 3);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(!twoEd_.intersectNotContainsBound(oneEd_));
    }

    @Test
    public void intersectNotContainsBound2Test() {
        CustPoint one_ = new CustPoint(0, 0);
        CustPoint two_ = new CustPoint(4, 2);
        CustPoint three_ = new CustPoint(4, 2);
        CustPoint four_ = new CustPoint(6, 3);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(!twoEd_.intersectNotContainsBound(oneEd_));
    }

    @Test
    public void intersectNotContainsBound3Test() {
        CustPoint one_ = new CustPoint(4, 2);
        CustPoint two_ = new CustPoint(0, 0);
        CustPoint three_ = new CustPoint(4, 2);
        CustPoint four_ = new CustPoint(6, 3);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(!oneEd_.intersectNotContainsBound(twoEd_));
    }

    @Test
    public void intersectNotContainsBound4Test() {
        CustPoint one_ = new CustPoint(0, 0);
        CustPoint two_ = new CustPoint(4, 2);
        CustPoint three_ = new CustPoint(4, 2);
        CustPoint four_ = new CustPoint(6, 3);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(!oneEd_.intersectNotContainsBound(twoEd_));
    }

    @Test
    public void intersectNotContainsBound5Test() {
        CustPoint one_ = new CustPoint(4, 2);
        CustPoint two_ = new CustPoint(0, 0);
        CustPoint three_ = new CustPoint(2, 1);
        CustPoint four_ = new CustPoint(6, 3);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(four_, three_);
        assertTrue(oneEd_.intersectNotContainsBound(twoEd_));
    }

    @Test
    public void intersectNotContainsBound6Test() {
        CustPoint one_ = new CustPoint(4, 2);
        CustPoint two_ = new CustPoint(0, 0);
        CustPoint three_ = new CustPoint(6, 3);
        CustPoint four_ = new CustPoint(2, 1);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(oneEd_.intersectNotContainsBound(twoEd_));
    }

    @Test
    public void intersectNotContainsBound7Test() {
        CustPoint one_ = new CustPoint(4, 2);
        CustPoint two_ = new CustPoint(0, 0);
        CustPoint three_ = new CustPoint(2, 1);
        CustPoint four_ = new CustPoint(-2, 1);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(oneEd_.intersectNotContainsBound(twoEd_));
    }

    @Test
    public void intersectNotContainsBound8Test() {
        CustPoint one_ = new CustPoint(0, 0);
        CustPoint two_ = new CustPoint(4, 2);
        CustPoint three_ = new CustPoint(0, 0);
        CustPoint four_ = new CustPoint(4, 2);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(oneEd_.intersectNotContainsBound(twoEd_));
    }

    @Test
    public void intersectNotContainsBound9Test() {
        CustPoint one_ = new CustPoint(4, 2);
        CustPoint two_ = new CustPoint(0, 0);
        CustPoint three_ = new CustPoint(0, 0);
        CustPoint four_ = new CustPoint(4, 2);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(oneEd_.intersectNotContainsBound(twoEd_));
    }

    @Test
    public void intersectNotContains1Test() {
        CustPoint one_ = new CustPoint(1, 0);
        CustPoint two_ = new CustPoint(3, 2);
        CustPoint three_ = new CustPoint(0, 0);
        CustPoint four_ = new CustPoint(4, 2);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(twoEd_.intersectNotContains(oneEd_));
    }

    @Test
    public void intersectNotContains2Test() {
        CustPoint one_ = new CustPoint(3, 2);
        CustPoint two_ = new CustPoint(5, 4);
        CustPoint three_ = new CustPoint(0, 0);
        CustPoint four_ = new CustPoint(4, 2);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(!twoEd_.intersectNotContains(oneEd_));
    }

    @Test
    public void intersectNotContains3Test() {
        CustPoint one_ = new CustPoint(2, 1);
        CustPoint two_ = new CustPoint(6, 3);
        CustPoint three_ = new CustPoint(0, 0);
        CustPoint four_ = new CustPoint(4, 2);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(!twoEd_.intersectNotContains(oneEd_));
    }

    @Test
    public void intersectNotContains4Test() {
        CustPoint one_ = new CustPoint(4, 2);
        CustPoint two_ = new CustPoint(6, 3);
        CustPoint three_ = new CustPoint(0, 0);
        CustPoint four_ = new CustPoint(4, 2);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(!twoEd_.intersectNotContains(oneEd_));
    }

    @Test
    public void intersectNotContains5Test() {
        CustPoint one_ = new CustPoint(2, 1);
        CustPoint two_ = new CustPoint(0, 3);
        CustPoint three_ = new CustPoint(0, 0);
        CustPoint four_ = new CustPoint(4, 2);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(!twoEd_.intersectNotContains(oneEd_));
    }

    @Test
    public void intersectNotContains6Test() {
        CustPoint one_ = new CustPoint(4, 1);
        CustPoint two_ = new CustPoint(6, 3);
        CustPoint three_ = new CustPoint(0, 0);
        CustPoint four_ = new CustPoint(4, 2);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(!twoEd_.intersectNotContains(oneEd_));
    }

    @Test
    public void intersectNotContains7Test() {
        CustPoint one_ = new CustPoint(0, 0);
        CustPoint two_ = new CustPoint(4, 2);
        CustPoint three_ = new CustPoint(2, 1);
        CustPoint four_ = new CustPoint(6, 3);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(!twoEd_.intersectNotContains(oneEd_));
    }

    @Test
    public void intersectNotContains8Test() {
        CustPoint one_ = new CustPoint(4, 2);
        CustPoint two_ = new CustPoint(0, 0);
        CustPoint three_ = new CustPoint(4, 2);
        CustPoint four_ = new CustPoint(6, 3);
        Edge oneEd_ = new Edge(one_, two_);
        Edge twoEd_ = new Edge(three_, four_);
        assertTrue(!twoEd_.intersectNotContains(oneEd_));
    }
    @Test
    public void displayTest() {
        CustPoint one_ = new CustPoint(4, 2);
        CustPoint two_ = new CustPoint(0, 0);
        Edge oneEd_ = new Edge(one_, two_);
        CustPoint three_ = new CustPoint(2, 5);
        CustPoint four_ = new CustPoint(6, 3);
        oneEd_.setFirst(three_);
        oneEd_.setSecond(four_);
        assertEq("2,5 6,3",oneEd_.display());
    }
}
