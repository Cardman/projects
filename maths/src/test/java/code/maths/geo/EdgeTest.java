package code.maths.geo;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.maths.geo.CustPoint;
import code.maths.geo.Edge;

@SuppressWarnings("static-method")
public class EdgeTest {

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
}
