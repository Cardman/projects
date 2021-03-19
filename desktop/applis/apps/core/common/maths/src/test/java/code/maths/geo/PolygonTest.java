package code.maths.geo;

import code.maths.EquallableMathUtil;
import code.maths.Rate;
import org.junit.Test;

import code.util.CustList;


public class PolygonTest extends EquallableMathUtil {

    @Test
    public void new_Polygon_Rect_test() {
        Rect r_ = rect(1,2,3,4);
        Polygon p_ = new Polygon(r_);
        assertEq(4, p_.size());
        assertEq(pt(1, 2), p_.get(0));
        assertEq(pt(1, 5), p_.get(1));
        assertEq(pt(3, 5), p_.get(2));
        assertEq(pt(3, 2), p_.get(3));
    }

    @Test
    public void getEdges1Test() {
        Rect r_ = rect(1,2,3,4);
        Polygon p_ = new Polygon(r_);
        assertEq(4, p_.getEdges().size());
    }

    @Test
    public void getEdges2Test() {
        Polygon p_ = new Polygon();
        assertEq(0, p_.getEdges().size());
    }

    @Test
    public void intersectEdge1Test() {
        Rect r_ = rect(1,2,3,4);
        Polygon p_ = new Polygon(r_);
        assertTrue(p_.intersectEdge(new Edge(pt(2, 3), pt(4, 3))));
    }

    @Test
    public void intersectEdge2Test() {
        Rect r_ = rect(1,2,6,8);
        Polygon p_ = new Polygon(r_);
        assertTrue(!p_.intersectEdge(new Edge(pt(2, 3), pt(4, 3))));
    }
    @Test
    public void getConvexHull1Test() {
        Polygon p_ = new Polygon();
        p_.add(pt(0, 0));
        p_.add(pt(0, 1));
        p_.add(pt(1, 1));
        p_.add(pt(1, 2));
        p_.add(pt(2, 2));
        p_.add(pt(2, 0));
        Polygon h_ = p_.getConvexHull();
        assertEq(5, h_.size());
        assertEq(pt(0, 0), h_.get(0));
        assertEq(pt(0, 1), h_.get(1));
        assertEq(pt(1, 2), h_.get(2));
        assertEq(pt(2, 2), h_.get(3));
        assertEq(pt(2, 0), h_.get(4));
    }

    @Test
    public void getConvexHull2Test() {
        Polygon p_ = new Polygon();
        p_.add(pt(0, 0));
        p_.add(pt(0, 1));
        p_.add(pt(1, 1));
        p_.add(pt(0, 2));
        p_.add(pt(1, 2));
        p_.add(pt(2, 2));
        p_.add(pt(2, 0));
        Polygon h_ = p_.getConvexHull();
        assertEq(6, h_.size());
        assertEq(pt(0, 0), h_.get(0));
        assertEq(pt(0, 1), h_.get(1));
        assertEq(pt(0, 2), h_.get(2));
        assertEq(pt(1, 2), h_.get(3));
        assertEq(pt(2, 2), h_.get(4));
        assertEq(pt(2, 0), h_.get(5));
    }

    @Test
    public void getConvexHull3Test() {
        Polygon p_ = new Polygon();
        p_.add(pt(0, 0));
        p_.add(pt(0, 1));
        p_.add(pt(1, 1));
        p_.add(pt(-1, 3));
        p_.add(pt(1, 2));
        p_.add(pt(2, 2));
        p_.add(pt(2, 0));
        Polygon h_ = p_.getConvexHull();
        assertEq(4, h_.size());
        assertEq(pt(-1, 3), h_.get(0));
        assertEq(pt(2, 2), h_.get(1));
        assertEq(pt(2, 0), h_.get(2));
        assertEq(pt(0, 0), h_.get(3));
    }

    @Test
    public void getConvexHull4Test() {
        Polygon p_ = new Polygon();
        p_.add(pt(0, 1));
        p_.add(pt(1, 1));
        p_.add(pt(1, 2));
        p_.add(pt(2, 2));
        p_.add(pt(2, 0));
        p_.add(pt(0, 0));
        Polygon h_ = p_.getConvexHull();
        assertEq(5, h_.size());
        assertEq(pt(0, 0), h_.get(0));
        assertEq(pt(0, 1), h_.get(1));
        assertEq(pt(1, 2), h_.get(2));
        assertEq(pt(2, 2), h_.get(3));
        assertEq(pt(2, 0), h_.get(4));
    }

    @Test
    public void getConvexHull5Test() {
        Polygon p_ = new Polygon();
        Polygon h_ = p_.getConvexHull();
        assertEq(0, h_.size());
    }

    @Test
    public void getConvexHull6Test() {
        Polygon p_ = new Polygon();
        p_.add(pt(1, 1));
        p_.add(pt(3, 1));
        p_.add(pt(3, 3));
        p_.add(pt(6, 3));
        Polygon h_ = p_.getConvexHull();
        assertEq(4, h_.size());
        assertEq(pt(1, 1), h_.get(0));
        assertEq(pt(3, 3), h_.get(1));
        assertEq(pt(6, 3), h_.get(2));
        assertEq(pt(3, 1), h_.get(3));
    }

    @Test
    public void getStrictHull1Test() {
        Polygon p_ = new Polygon();
        p_.add(pt(0, 0));
        p_.add(pt(0, 1));
        p_.add(pt(1, 1));
        p_.add(pt(1, 2));
        p_.add(pt(2, 2));
        p_.add(pt(2, 0));
        Polygon h_ = p_.getStrictHull();
        assertEq(5, h_.size());
        assertEq(pt(0, 0), h_.get(0));
        assertEq(pt(0, 1), h_.get(1));
        assertEq(pt(1, 2), h_.get(2));
        assertEq(pt(2, 2), h_.get(3));
        assertEq(pt(2, 0), h_.get(4));
    }

    @Test
    public void getStrictHull2Test() {
        Polygon p_ = new Polygon();
        p_.add(pt(0, 0));
        p_.add(pt(0, 1));
        p_.add(pt(1, 1));
        p_.add(pt(0, 2));
        p_.add(pt(1, 2));
        p_.add(pt(2, 2));
        p_.add(pt(2, 0));
        Polygon h_ = p_.getStrictHull();
        assertEq(4, h_.size());
        assertEq(pt(0, 0), h_.get(0));
        assertEq(pt(0, 2), h_.get(1));
        assertEq(pt(2, 2), h_.get(2));
        assertEq(pt(2, 0), h_.get(3));
    }

    @Test
    public void getStrictHull3Test() {
        Polygon p_ = new Polygon();
        p_.add(pt(0, 0));
        p_.add(pt(0, 1));
        p_.add(pt(1, 1));
        p_.add(pt(-1, 3));
        p_.add(pt(1, 2));
        p_.add(pt(2, 2));
        p_.add(pt(2, 0));
        Polygon h_ = p_.getStrictHull();
        assertEq(4, h_.size());
        assertEq(pt(-1, 3), h_.get(0));
        assertEq(pt(2, 2), h_.get(1));
        assertEq(pt(2, 0), h_.get(2));
        assertEq(pt(0, 0), h_.get(3));
    }

    @Test
    public void getStrictHull4Test() {
        Polygon p_ = new Polygon();
        p_.add(pt(0, 1));
        p_.add(pt(1, 1));
        p_.add(pt(1, 2));
        p_.add(pt(2, 2));
        p_.add(pt(2, 0));
        p_.add(pt(0, 0));
        Polygon h_ = p_.getStrictHull();
        assertEq(5, h_.size());
        assertEq(pt(0, 0), h_.get(0));
        assertEq(pt(0, 1), h_.get(1));
        assertEq(pt(1, 2), h_.get(2));
        assertEq(pt(2, 2), h_.get(3));
        assertEq(pt(2, 0), h_.get(4));
    }

    @Test
    public void getStrictHull5Test() {
        Polygon p_ = new Polygon();
        Polygon h_ = p_.getStrictHull();
        assertEq(0, h_.size());
    }

    @Test
    public void containsInsideConvexHull1Test() {
        Polygon p_ = new Polygon();
        p_.add(pt(0, 0));
        p_.add(pt(0, 1));
        p_.add(pt(1, 1));
        p_.add(pt(1, 2));
        p_.add(pt(2, 2));
        p_.add(pt(2, 0));
        assertTrue(p_.containsInsideConvexHull(pt(1, 1)));
    }

    @Test
    public void containsInsideConvexHull2Test() {
        Polygon p_ = new Polygon();
        p_.add(pt(0, 0));
        p_.add(pt(0, 1));
        p_.add(pt(1, 2));
        p_.add(pt(2, 2));
        p_.add(pt(2, 0));
        assertTrue(p_.containsInsideConvexHull(pt(1, 1)));
    }

    @Test
    public void containsInsideConvexHull3Test() {
        Polygon p_ = new Polygon();
        p_.add(pt(0, 0));
        p_.add(pt(0, 1));
        p_.add(pt(1, 2));
        p_.add(pt(2, 2));
        p_.add(pt(2, 0));
        assertTrue(!p_.containsInsideConvexHull(pt(0, 2)));
    }

    @Test
    public void containsInsideConvexHull4Test() {
        Polygon p_ = new Polygon();
        p_.add(pt(0, 0));
        p_.add(pt(0, 1));
        p_.add(pt(1, 2));
        p_.add(pt(2, 2));
        p_.add(pt(2, 0));
        assertTrue(!p_.containsInsideConvexHull(pt(3, 0)));
    }

    @Test
    public void containsInsideConvexHull5Test() {
        Polygon p_ = new Polygon();
        assertTrue(!p_.containsInsideConvexHull(pt(0, 0)));
    }

    @Test
    public void containsInsideConvexHull6Test() {
        Polygon p_ = new Polygon();
        p_.add(pt(0, 0));
        p_.add(pt(0, 1));
        p_.add(pt(1, 2));
        p_.add(pt(2, 2));
        p_.add(pt(2, 0));
        assertTrue(!p_.containsInsideConvexHull(pt(0, 0)));
    }

    @Test
    public void isConvex1Test() {
        Polygon p_ = new Polygon();
        p_.add(pt(0, 0));
        p_.add(pt(0, 1));
        p_.add(pt(1, 2));
        assertTrue(p_.isConvex());
    }

    @Test
    public void isConvex2Test() {
        Polygon p_ = new Polygon();
        p_.add(pt(0, 0));
        p_.add(pt(0, 1));
        p_.add(pt(1, 2));
        p_.add(pt(2, 0));
        assertTrue(p_.isConvex());
    }

    @Test
    public void isConvex3Test() {
        Polygon p_ = new Polygon();
        p_.add(pt(0, 0));
        p_.add(pt(0, 1));
        p_.add(pt(1, 1));
        p_.add(pt(1, 2));
        p_.add(pt(2, 2));
        p_.add(pt(2, 0));
        assertTrue(!p_.isConvex());
    }

    @Test
    public void getTriangles1Test() {
        Polygon p_ = new Polygon();
        p_.add(pt(0, 0));
        p_.add(pt(0, 1));
        p_.add(pt(1, 2));
        CustList<Triangle> t_ = p_.getTriangles();
        assertEq(0, t_.size());
    }

    @Test
    public void getTriangles2Test() {
        Polygon p_ = new Polygon();
        p_.add(pt(0, 0));
        p_.add(pt(0, 1));
        p_.add(pt(1, 2));
        p_.add(pt(2, 0));
        CustList<Triangle> t_ = p_.getTriangles();
        assertEq(0, t_.size());
    }

    @Test
    public void getTriangles3Test() {
        Polygon p_ = new Polygon();
        p_.add(pt(0, 0));
        p_.add(pt(0, 1));
        p_.add(pt(1, 1));
        p_.add(pt(1, 2));
        p_.add(pt(2, 2));
        p_.add(pt(2, 0));
        CustList<Triangle> t_ = p_.getTriangles();
        assertEq(1, t_.size());
        assertEq(pt(0, 1), t_.get(0).getFirstPoint());
        assertEq(pt(1, 1), t_.get(0).getSecondPoint());
        assertEq(pt(1, 2), t_.get(0).getThirdPoint());
    }

    @Test
    public void getTriangles4Test() {
        Polygon p_ = new Polygon();
        p_.add(pt(0, 0));
        p_.add(pt(0, 1));
        p_.add(pt(1, 1));
        p_.add(pt(1, 2));
        p_.add(pt(2, 2));
        p_.add(pt(3, 2));
        p_.add(pt(2, 0));
        CustList<Triangle> t_ = p_.getTriangles();
        assertEq(1, t_.size());
        assertEq(pt(0, 1), t_.get(0).getFirstPoint());
        assertEq(pt(1, 1), t_.get(0).getSecondPoint());
        assertEq(pt(1, 2), t_.get(0).getThirdPoint());
    }

    @Test
    public void getTriangles5Test() {
        Polygon p_ = new Polygon();
        p_.add(pt(2, 0));
        p_.add(pt(0, 0));
        p_.add(pt(0, 1));
        p_.add(pt(1, 1));
        p_.add(pt(1, 2));
        p_.add(pt(2, 2));
        p_.add(pt(3, 2));
        CustList<Triangle> t_ = p_.getTriangles();
        assertEq(1, t_.size());
        assertEq(pt(0, 1), t_.get(0).getFirstPoint());
        assertEq(pt(1, 1), t_.get(0).getSecondPoint());
        assertEq(pt(1, 2), t_.get(0).getThirdPoint());
    }

    @Test
    public void getTriangles6Test() {
        Polygon p_ = new Polygon();
        p_.add(pt(0, 1));
        p_.add(pt(1, 1));
        p_.add(pt(1, 2));
        p_.add(pt(2, 2));
        p_.add(pt(3, 2));
        p_.add(pt(2, 0));
        p_.add(pt(0, 0));
        CustList<Triangle> t_ = p_.getTriangles();
        assertEq(1, t_.size());
        assertEq(pt(0, 1), t_.get(0).getFirstPoint());
        assertEq(pt(1, 1), t_.get(0).getSecondPoint());
        assertEq(pt(1, 2), t_.get(0).getThirdPoint());
    }

    @Test
    public void getTriangles7Test() {
        Polygon p_ = new Polygon();
        p_.add(pt(0, 0));
        p_.add(pt(2, 0));
        p_.add(pt(1, 1));
        p_.add(pt(2, 2));
        p_.add(pt(0, 2));
        CustList<Triangle> t_ = p_.getTriangles();
        assertEq(1, t_.size());
        assertEq(pt(2, 2), t_.get(0).getFirstPoint());
        assertEq(pt(1, 1), t_.get(0).getSecondPoint());
        assertEq(pt(2, 0), t_.get(0).getThirdPoint());
    }

    @Test
    public void getTriangles8Test() {
        Polygon p_ = new Polygon();
        p_.add(pt(0, 0));
        p_.add(pt(0, 2));
        p_.add(pt(2, 2));
        p_.add(pt(1, 1));
        p_.add(pt(2, 0));
        CustList<Triangle> t_ = p_.getTriangles();
        assertEq(1, t_.size());
        assertEq(pt(2, 2), t_.get(0).getFirstPoint());
        assertEq(pt(1, 1), t_.get(0).getSecondPoint());
        assertEq(pt(2, 0), t_.get(0).getThirdPoint());
    }
    @Test
    public void containsInside1Test() {
        Polygon p_ = new Polygon();
        p_.add(pt(0, 0));
        p_.add(pt(0, 2));
        p_.add(pt(2, 2));
        p_.add(pt(2, 4));
        p_.add(pt(4, 4));
        p_.add(pt(4, 0));
        assertTrue(p_.containsInside(pt(3, 1)));
    }

    @Test
    public void containsInside2Test() {
        Polygon p_ = new Polygon();
        p_.add(pt(0, 0));
        p_.add(pt(0, 10));
        p_.add(pt(10, 10));
        p_.add(pt(10, 20));
        p_.add(pt(20, 20));
        p_.add(pt(20, 0));
        assertTrue(!p_.containsInside(pt(8, 12)));
    }

    @Test
    public void containsInside3Test() {
        Polygon p_ = new Polygon();
        p_.add(pt(0, 0));
        p_.add(pt(0, 10));
        p_.add(pt(10, 10));
        p_.add(pt(10, 20));
        p_.add(pt(20, 20));
        p_.add(pt(20, 0));
        assertTrue(!p_.containsInside(pt(0, 20)));
    }

    @Test
    public void containsInside4Test() {
        Polygon p_ = new Polygon();
        p_.add(pt(0, 0));
        p_.add(pt(0, 10));
        p_.add(pt(10, 10));
        assertTrue(p_.containsInside(pt(2, 8)));
    }

    @Test
    public void containsInside5Test() {
        Polygon p_ = new Polygon();
        p_.add(pt(0, 0));
        p_.add(pt(0, 10));
        p_.add(pt(10, 10));
        assertTrue(!p_.containsInside(pt(8, 2)));
    }

    @Test
    public void intersect1Test() {
        Rect r_ = rect(1,2,3,4);
        Polygon p_ = new Polygon(r_);
        Rect r2_ = rect(2,3,1,1);
        Polygon p2_ = new Polygon(r2_);
        assertTrue(!p_.intersect(p2_));
    }

    @Test
    public void intersect2Test() {
        Rect r_ = rect(1,2,3,4);
        Polygon p_ = new Polygon(r_);
        Rect r2_ = rect(2,3,5,6);
        Polygon p2_ = new Polygon(r2_);
        assertTrue(p_.intersect(p2_));
    }

    @Test
    public void intersectEdgeNotBound1Test() {
        Rect r_ = rect(1,2,6,8);
        Polygon p_ = new Polygon(r_);
        assertTrue(!p_.intersectEdgeNotBound(new Edge(pt(2, 3), pt(4, 3))));
    }

    @Test
    public void intersectEdgeNotBound2Test() {
        Rect r_ = rect(1,2,3,4);
        Polygon p_ = new Polygon(r_);
        assertTrue(p_.intersectEdgeNotBound(new Edge(pt(2, 3), pt(4, 3))));
    }

    @Test
    public void displayTest() {
        Rect r_ = rect(1,2,3,4);
        Polygon p_ = new Polygon(r_);
        Polygon q_ = new Polygon(p_);
        q_.set(0, pt(1, 2));
        assertTrue(q_.containsObj(pt(1, 2)));
        assertTrue(!q_.containsObj(pt(1, 3)));
        assertEq("1,2;1,5;3,5;3,2",q_.display());
        q_.setPoints(new CustList<RatePoint>());
        assertEq("",q_.display());
    }

    private static Rect rect(int _one, int _two, int _three, int _four) {
        return new Rect(new Rate(_one), new Rate(_two), new Rate(_three), new Rate(_four));
    }
    private static RatePoint pt(int _x, int _y) {
        return new RatePoint(new Rate(_x), new Rate(_y));
    }

}
