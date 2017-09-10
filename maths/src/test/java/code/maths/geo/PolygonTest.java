package code.maths.geo;
import static code.maths.EquallableMathUtil.assertEq;
import static code.util.opers.EquallableUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import code.util.CustList;

@SuppressWarnings("static-method")
public class PolygonTest {

    @Test
    public void getConvexHull1Test() {
        Polygon p_ = new Polygon();
        p_.add(new CustPoint(0,0));
        p_.add(new CustPoint(0,1));
        p_.add(new CustPoint(1,1));
        p_.add(new CustPoint(1,2));
        p_.add(new CustPoint(2,2));
        p_.add(new CustPoint(2,0));
        Polygon h_ = p_.getConvexHull();
        assertEq(5, h_.size());
        assertEq(new CustPoint(0,0), h_.get(0));
        assertEq(new CustPoint(0,1), h_.get(1));
        assertEq(new CustPoint(1,2), h_.get(2));
        assertEq(new CustPoint(2,2), h_.get(3));
        assertEq(new CustPoint(2,0), h_.get(4));
    }

    @Test
    public void getConvexHull2Test() {
        Polygon p_ = new Polygon();
        p_.add(new CustPoint(0,0));
        p_.add(new CustPoint(0,1));
        p_.add(new CustPoint(1,1));
        p_.add(new CustPoint(0,2));
        p_.add(new CustPoint(1,2));
        p_.add(new CustPoint(2,2));
        p_.add(new CustPoint(2,0));
        Polygon h_ = p_.getConvexHull();
        assertEq(6, h_.size());
        assertEq(new CustPoint(0,0), h_.get(0));
        assertEq(new CustPoint(0,1), h_.get(1));
        assertEq(new CustPoint(0,2), h_.get(2));
        assertEq(new CustPoint(1,2), h_.get(3));
        assertEq(new CustPoint(2,2), h_.get(4));
        assertEq(new CustPoint(2,0), h_.get(5));
    }

    @Test
    public void getConvexHull3Test() {
        Polygon p_ = new Polygon();
        p_.add(new CustPoint(0,0));
        p_.add(new CustPoint(0,1));
        p_.add(new CustPoint(1,1));
        p_.add(new CustPoint(-1,3));
        p_.add(new CustPoint(1,2));
        p_.add(new CustPoint(2,2));
        p_.add(new CustPoint(2,0));
        Polygon h_ = p_.getConvexHull();
        assertEq(4, h_.size());
        assertEq(new CustPoint(-1,3), h_.get(0));
        assertEq(new CustPoint(2,2), h_.get(1));
        assertEq(new CustPoint(2,0), h_.get(2));
        assertEq(new CustPoint(0,0), h_.get(3));
    }

    @Test
    public void getConvexHull4Test() {
        Polygon p_ = new Polygon();
        p_.add(new CustPoint(0,1));
        p_.add(new CustPoint(1,1));
        p_.add(new CustPoint(1,2));
        p_.add(new CustPoint(2,2));
        p_.add(new CustPoint(2,0));
        p_.add(new CustPoint(0,0));
        Polygon h_ = p_.getConvexHull();
        assertEq(5, h_.size());
        assertEq(new CustPoint(0,0), h_.get(0));
        assertEq(new CustPoint(0,1), h_.get(1));
        assertEq(new CustPoint(1,2), h_.get(2));
        assertEq(new CustPoint(2,2), h_.get(3));
        assertEq(new CustPoint(2,0), h_.get(4));
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
        p_.add(new CustPoint(1,1));
        p_.add(new CustPoint(3,1));
        p_.add(new CustPoint(3,3));
        p_.add(new CustPoint(6,3));
        Polygon h_ = p_.getConvexHull();
        assertEq(4, h_.size());
        assertEq(new CustPoint(1,1), h_.get(0));
        assertEq(new CustPoint(3,3), h_.get(1));
        assertEq(new CustPoint(6,3), h_.get(2));
        assertEq(new CustPoint(3,1), h_.get(3));
    }

    @Ignore
    @Test
    public void getStrictHull1Test() {
        Polygon p_ = new Polygon();
        p_.add(new CustPoint(0,0));
        p_.add(new CustPoint(0,1));
        p_.add(new CustPoint(1,1));
        p_.add(new CustPoint(1,2));
        p_.add(new CustPoint(2,2));
        p_.add(new CustPoint(2,0));
        Polygon h_ = p_.getStrictHull();
        assertEq(5, h_.size());
        assertEq(new CustPoint(0,0), h_.get(0));
        assertEq(new CustPoint(0,1), h_.get(1));
        assertEq(new CustPoint(1,2), h_.get(2));
        assertEq(new CustPoint(2,2), h_.get(3));
        assertEq(new CustPoint(2,0), h_.get(4));
    }

    @Ignore
    @Test
    public void getStrictHull2Test() {
        Polygon p_ = new Polygon();
        p_.add(new CustPoint(0,0));
        p_.add(new CustPoint(0,1));
        p_.add(new CustPoint(1,1));
        p_.add(new CustPoint(0,2));
        p_.add(new CustPoint(1,2));
        p_.add(new CustPoint(2,2));
        p_.add(new CustPoint(2,0));
        Polygon h_ = p_.getStrictHull();
        assertEq(6, h_.size());
        assertEq(new CustPoint(0,0), h_.get(0));
        assertEq(new CustPoint(0,1), h_.get(1));
        assertEq(new CustPoint(0,2), h_.get(2));
        assertEq(new CustPoint(1,2), h_.get(3));
        assertEq(new CustPoint(2,2), h_.get(4));
        assertEq(new CustPoint(2,0), h_.get(5));
    }

    @Ignore
    @Test
    public void getStrictHull3Test() {
        Polygon p_ = new Polygon();
        p_.add(new CustPoint(0,0));
        p_.add(new CustPoint(0,1));
        p_.add(new CustPoint(1,1));
        p_.add(new CustPoint(-1,3));
        p_.add(new CustPoint(1,2));
        p_.add(new CustPoint(2,2));
        p_.add(new CustPoint(2,0));
        Polygon h_ = p_.getStrictHull();
        assertEq(4, h_.size());
        assertEq(new CustPoint(-1,3), h_.get(0));
        assertEq(new CustPoint(2,2), h_.get(1));
        assertEq(new CustPoint(2,0), h_.get(2));
        assertEq(new CustPoint(0,0), h_.get(3));
    }

    @Ignore
    @Test
    public void getStrictHull4Test() {
        Polygon p_ = new Polygon();
        p_.add(new CustPoint(0,1));
        p_.add(new CustPoint(1,1));
        p_.add(new CustPoint(1,2));
        p_.add(new CustPoint(2,2));
        p_.add(new CustPoint(2,0));
        p_.add(new CustPoint(0,0));
        Polygon h_ = p_.getStrictHull();
        assertEq(5, h_.size());
        assertEq(new CustPoint(0,0), h_.get(0));
        assertEq(new CustPoint(0,1), h_.get(1));
        assertEq(new CustPoint(1,2), h_.get(2));
        assertEq(new CustPoint(2,2), h_.get(3));
        assertEq(new CustPoint(2,0), h_.get(4));
    }

    @Ignore
    @Test
    public void getStrictHull5Test() {
        Polygon p_ = new Polygon();
        Polygon h_ = p_.getStrictHull();
        assertEq(0, h_.size());
    }

    @Test
    public void containsInsideConvexHull1Test() {
        Polygon p_ = new Polygon();
        p_.add(new CustPoint(0,0));
        p_.add(new CustPoint(0,1));
        p_.add(new CustPoint(1,1));
        p_.add(new CustPoint(1,2));
        p_.add(new CustPoint(2,2));
        p_.add(new CustPoint(2,0));
        assertTrue(p_.containsInsideConvexHull(new CustPoint(1, 1)));
    }

    @Test
    public void containsInsideConvexHull2Test() {
        Polygon p_ = new Polygon();
        p_.add(new CustPoint(0,0));
        p_.add(new CustPoint(0,1));
        p_.add(new CustPoint(1,2));
        p_.add(new CustPoint(2,2));
        p_.add(new CustPoint(2,0));
        assertTrue(p_.containsInsideConvexHull(new CustPoint(1, 1)));
    }

    @Test
    public void containsInsideConvexHull3Test() {
        Polygon p_ = new Polygon();
        p_.add(new CustPoint(0,0));
        p_.add(new CustPoint(0,1));
        p_.add(new CustPoint(1,2));
        p_.add(new CustPoint(2,2));
        p_.add(new CustPoint(2,0));
        assertTrue(!p_.containsInsideConvexHull(new CustPoint(0, 2)));
    }

    @Test
    public void containsInsideConvexHull4Test() {
        Polygon p_ = new Polygon();
        p_.add(new CustPoint(0,0));
        p_.add(new CustPoint(0,1));
        p_.add(new CustPoint(1,2));
        p_.add(new CustPoint(2,2));
        p_.add(new CustPoint(2,0));
        assertTrue(!p_.containsInsideConvexHull(new CustPoint(3, 0)));
    }

    @Test
    public void containsInsideConvexHull5Test() {
        Polygon p_ = new Polygon();
        assertTrue(!p_.containsInsideConvexHull(new CustPoint(0, 0)));
    }

    @Test
    public void containsInsideConvexHull6Test() {
        Polygon p_ = new Polygon();
        p_.add(new CustPoint(0,0));
        p_.add(new CustPoint(0,1));
        p_.add(new CustPoint(1,2));
        p_.add(new CustPoint(2,2));
        p_.add(new CustPoint(2,0));
        assertTrue(!p_.containsInsideConvexHull(new CustPoint(0, 0)));
    }

    @Test
    public void isConvex1Test() {
        Polygon p_ = new Polygon();
        p_.add(new CustPoint(0,0));
        p_.add(new CustPoint(0,1));
        p_.add(new CustPoint(1,2));
        assertTrue(p_.isConvex());
    }

    @Test
    public void isConvex2Test() {
        Polygon p_ = new Polygon();
        p_.add(new CustPoint(0,0));
        p_.add(new CustPoint(0,1));
        p_.add(new CustPoint(1,2));
        p_.add(new CustPoint(2,0));
        assertTrue(p_.isConvex());
    }

    @Test
    public void isConvex3Test() {
        Polygon p_ = new Polygon();
        p_.add(new CustPoint(0,0));
        p_.add(new CustPoint(0,1));
        p_.add(new CustPoint(1,1));
        p_.add(new CustPoint(1,2));
        p_.add(new CustPoint(2,2));
        p_.add(new CustPoint(2,0));
        assertTrue(!p_.isConvex());
    }

    @Test
    public void getTriangles1Test() {
        Polygon p_ = new Polygon();
        p_.add(new CustPoint(0,0));
        p_.add(new CustPoint(0,1));
        p_.add(new CustPoint(1,2));
        CustList<Triangle> t_ = p_.getTriangles();
        assertEq(0, t_.size());
    }

    @Test
    public void getTriangles2Test() {
        Polygon p_ = new Polygon();
        p_.add(new CustPoint(0,0));
        p_.add(new CustPoint(0,1));
        p_.add(new CustPoint(1,2));
        p_.add(new CustPoint(2,0));
        CustList<Triangle> t_ = p_.getTriangles();
        assertEq(0, t_.size());
    }

    @Test
    public void getTriangles3Test() {
        Polygon p_ = new Polygon();
        p_.add(new CustPoint(0,0));
        p_.add(new CustPoint(0,1));
        p_.add(new CustPoint(1,1));
        p_.add(new CustPoint(1,2));
        p_.add(new CustPoint(2,2));
        p_.add(new CustPoint(2,0));
        CustList<Triangle> t_ = p_.getTriangles();
        assertEq(1, t_.size());
        assertEq(new CustPoint(0,1), t_.get(0).getFirstPoint());
        assertEq(new CustPoint(1,1), t_.get(0).getSecondPoint());
        assertEq(new CustPoint(1,2), t_.get(0).getThirdPoint());
    }

    @Test
    public void getTriangles4Test() {
        Polygon p_ = new Polygon();
        p_.add(new CustPoint(0,0));
        p_.add(new CustPoint(0,1));
        p_.add(new CustPoint(1,1));
        p_.add(new CustPoint(1,2));
        p_.add(new CustPoint(2,2));
        p_.add(new CustPoint(3,2));
        p_.add(new CustPoint(2,0));
        CustList<Triangle> t_ = p_.getTriangles();
        assertEq(1, t_.size());
        assertEq(new CustPoint(0,1), t_.get(0).getFirstPoint());
        assertEq(new CustPoint(1,1), t_.get(0).getSecondPoint());
        assertEq(new CustPoint(1,2), t_.get(0).getThirdPoint());
    }

    @Test
    public void getTriangles5Test() {
        Polygon p_ = new Polygon();
        p_.add(new CustPoint(2,0));
        p_.add(new CustPoint(0,0));
        p_.add(new CustPoint(0,1));
        p_.add(new CustPoint(1,1));
        p_.add(new CustPoint(1,2));
        p_.add(new CustPoint(2,2));
        p_.add(new CustPoint(3,2));
        CustList<Triangle> t_ = p_.getTriangles();
        assertEq(1, t_.size());
        assertEq(new CustPoint(0,1), t_.get(0).getFirstPoint());
        assertEq(new CustPoint(1,1), t_.get(0).getSecondPoint());
        assertEq(new CustPoint(1,2), t_.get(0).getThirdPoint());
    }

    @Test
    public void getTriangles6Test() {
        Polygon p_ = new Polygon();
        p_.add(new CustPoint(0,1));
        p_.add(new CustPoint(1,1));
        p_.add(new CustPoint(1,2));
        p_.add(new CustPoint(2,2));
        p_.add(new CustPoint(3,2));
        p_.add(new CustPoint(2,0));
        p_.add(new CustPoint(0,0));
        CustList<Triangle> t_ = p_.getTriangles();
        assertEq(1, t_.size());
        assertEq(new CustPoint(0,1), t_.get(0).getFirstPoint());
        assertEq(new CustPoint(1,1), t_.get(0).getSecondPoint());
        assertEq(new CustPoint(1,2), t_.get(0).getThirdPoint());
    }

    @Test
    public void containsInside1Test() {
        Polygon p_ = new Polygon();
        p_.add(new CustPoint(0,0));
        p_.add(new CustPoint(0,2));
        p_.add(new CustPoint(2,2));
        p_.add(new CustPoint(2,4));
        p_.add(new CustPoint(4,4));
        p_.add(new CustPoint(4,0));
        assertTrue(p_.containsInside(new CustPoint(3, 1)));
    }

    @Test
    public void containsInside2Test() {
        Polygon p_ = new Polygon();
        p_.add(new CustPoint(0,0));
        p_.add(new CustPoint(0,10));
        p_.add(new CustPoint(10,10));
        p_.add(new CustPoint(10,20));
        p_.add(new CustPoint(20,20));
        p_.add(new CustPoint(20,0));
        assertTrue(!p_.containsInside(new CustPoint(8, 12)));
    }

    @Test
    public void containsInside3Test() {
        Polygon p_ = new Polygon();
        p_.add(new CustPoint(0,0));
        p_.add(new CustPoint(0,10));
        p_.add(new CustPoint(10,10));
        p_.add(new CustPoint(10,20));
        p_.add(new CustPoint(20,20));
        p_.add(new CustPoint(20,0));
        assertTrue(!p_.containsInside(new CustPoint(0, 20)));
    }

    @Test
    public void containsInside4Test() {
        Polygon p_ = new Polygon();
        p_.add(new CustPoint(0,0));
        p_.add(new CustPoint(0,10));
        p_.add(new CustPoint(10,10));
        assertTrue(p_.containsInside(new CustPoint(2, 8)));
    }

    @Test
    public void containsInside5Test() {
        Polygon p_ = new Polygon();
        p_.add(new CustPoint(0,0));
        p_.add(new CustPoint(0,10));
        p_.add(new CustPoint(10,10));
        assertTrue(!p_.containsInside(new CustPoint(8, 2)));
    }
}
