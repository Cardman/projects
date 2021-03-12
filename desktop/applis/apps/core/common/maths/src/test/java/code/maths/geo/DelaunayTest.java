package code.maths.geo;

import code.maths.EquallableMathUtil;
import code.maths.Rate;
import org.junit.Test;

import code.util.CustList;
import code.util.IdList;
import code.util.IdMap;


public class DelaunayTest extends EquallableMathUtil {

    @Test
    public void addTest() {
        Delaunay d_ = new Delaunay();
        Polygon p_ =new Polygon();
        assertEq(-1, Delaunay.addPoint(p_, pt(0, 0),-1,0,new Edge(pt(0, 0), pt(0, 0))));
    }
    @Test
    public void addIfNotIntersect1Test() {
        Delaunay d_ = new Delaunay();
        Polygon p_ =new Polygon(rect(0,0,2,2));
        d_.addIfNotIntersect(p_, pt(0, 1),1,2,new Edge(pt(3, 3), pt(4, 4)));
        assertEq(0,d_.getTriangles().size());
        assertEq(0,d_.getEdges().size());
        assertEq(0,d_.getNextTriangles().size());
        assertEq(0,d_.getConvexHull().size());
    }
    @Test
    public void compute0Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        d_.compute(pts_, false);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(0, ts_.size());
    }
    @Test
    public void compute1Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(0, 0));
        pts_.add(pt(2, 4));
        pts_.add(pt(1, 5));
        pts_.add(pt(3, 2));
        d_.compute(pts_, false);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(2, ts_.size());
        assertEq(pt(0, 0), ts_.get(0).getFirstPoint());
        assertEq(pt(2, 4), ts_.get(0).getSecondPoint());
        assertEq(pt(1, 5), ts_.get(0).getThirdPoint());
        assertEq(pt(2, 4), ts_.get(1).getFirstPoint());
        assertEq(pt(0, 0), ts_.get(1).getSecondPoint());
        assertEq(pt(3, 2), ts_.get(1).getThirdPoint());
    }

    @Test
    public void compute2Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(1, 1));
        pts_.add(pt(2, 4));
        pts_.add(pt(0, 3));
        pts_.add(pt(3, 2));
        d_.compute(pts_, false);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(2, ts_.size());
        assertEq(pt(1, 1), ts_.get(0).getFirstPoint());
        assertEq(pt(2, 4), ts_.get(0).getSecondPoint());
        assertEq(pt(0, 3), ts_.get(0).getThirdPoint());
        assertEq(pt(2, 4), ts_.get(1).getFirstPoint());
        assertEq(pt(1, 1), ts_.get(1).getSecondPoint());
        assertEq(pt(3, 2), ts_.get(1).getThirdPoint());
    }

    @Test
    public void compute3Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(3, 2));
        pts_.add(pt(0, 0));
        pts_.add(pt(2, 4));
        pts_.add(pt(1, 5));
        d_.compute(pts_, false);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(2, ts_.size());
        assertEq(pt(0, 0), ts_.get(0).getFirstPoint());
        assertEq(pt(3, 2), ts_.get(0).getSecondPoint());
        assertEq(pt(2, 4), ts_.get(0).getThirdPoint());
        assertEq(pt(0, 0), ts_.get(1).getFirstPoint());
        assertEq(pt(2, 4), ts_.get(1).getSecondPoint());
        assertEq(pt(1, 5), ts_.get(1).getThirdPoint());
    }

    @Test
    public void compute4Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(3, 2));
        pts_.add(pt(1, 1));
        pts_.add(pt(2, 4));
        pts_.add(pt(0, 3));
        d_.compute(pts_, false);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(2, ts_.size());
        assertEq(pt(1, 1), ts_.get(0).getFirstPoint());
        assertEq(pt(3, 2), ts_.get(0).getSecondPoint());
        assertEq(pt(2, 4), ts_.get(0).getThirdPoint());
        assertEq(pt(1, 1), ts_.get(1).getFirstPoint());
        assertEq(pt(2, 4), ts_.get(1).getSecondPoint());
        assertEq(pt(0, 3), ts_.get(1).getThirdPoint());
    }

    @Test
    public void compute5Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(0, 0));
        pts_.add(pt(1, 1));
        pts_.add(pt(2, 2));
        d_.compute(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(0, ts_.size());
    }

    @Test
    public void compute6Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(2, 2));
        pts_.add(pt(4, 0));
        pts_.add(pt(6, 0));
        pts_.add(pt(2, -2));
        d_.compute(pts_, true);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(3, ts_.size());
    }

    @Test
    public void compute7Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(0, 0));
        pts_.add(pt(2, 4));
        pts_.add(pt(1, 5));
        d_.compute(pts_, false);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(1, ts_.size());
        assertEq(pt(0, 0), ts_.get(0).getFirstPoint());
        assertEq(pt(2, 4), ts_.get(0).getSecondPoint());
        assertEq(pt(1, 5), ts_.get(0).getThirdPoint());
    }

    @Test
    public void compute8Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(1, 1));
        pts_.add(pt(2, 4));
        pts_.add(pt(0, 3));
        d_.compute(pts_, false);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(1, ts_.size());
        assertEq(pt(1, 1), ts_.get(0).getFirstPoint());
        assertEq(pt(2, 4), ts_.get(0).getSecondPoint());
        assertEq(pt(0, 3), ts_.get(0).getThirdPoint());
    }

    @Test
    public void compute9Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(0, 3));
        pts_.add(pt(2, 4));
        pts_.add(pt(1, 5));
        d_.compute(pts_, false);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(1, ts_.size());
        assertEq(pt(0, 3), ts_.get(0).getFirstPoint());
        assertEq(pt(2, 4), ts_.get(0).getSecondPoint());
        assertEq(pt(1, 5), ts_.get(0).getThirdPoint());
    }

    @Test
    public void compute10Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(67, 57));
        pts_.add(pt(145, 185));
        pts_.add(pt(333, 264));
        d_.compute(pts_, false);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(1, ts_.size());
        assertEq(pt(67, 57), ts_.get(0).getFirstPoint());
        assertEq(pt(145, 185), ts_.get(0).getSecondPoint());
        assertEq(pt(333, 264), ts_.get(0).getThirdPoint());
    }

    @Test
    public void mainComputeIncr0Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        d_.mainComputeIncr(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(0, ts_.size());
    }
    @Test
    public void mainComputeIncr1Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(0, 0));
        pts_.add(pt(2, 4));
        pts_.add(pt(1, 5));
        pts_.add(pt(3, 2));
        d_.mainComputeIncr(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(2, ts_.size());
        assertEq(pt(0, 0), ts_.get(0).getFirstPoint());
        assertEq(pt(2, 4), ts_.get(0).getSecondPoint());
        assertEq(pt(1, 5), ts_.get(0).getThirdPoint());
        assertEq(pt(0, 0), ts_.get(1).getFirstPoint());
        assertEq(pt(2, 4), ts_.get(1).getSecondPoint());
        assertEq(pt(3, 2), ts_.get(1).getThirdPoint());
    }
    @Test
    public void mainComputeIncr1_Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(0, 0));
        pts_.add(pt(2, 4));
        pts_.add(pt(2, 4));
        pts_.add(pt(1, 5));
        pts_.add(pt(3, 2));
        d_.mainComputeIncr(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(2, ts_.size());
        assertEq(pt(0, 0), ts_.get(0).getFirstPoint());
        assertEq(pt(2, 4), ts_.get(0).getSecondPoint());
        assertEq(pt(1, 5), ts_.get(0).getThirdPoint());
        assertEq(pt(0, 0), ts_.get(1).getFirstPoint());
        assertEq(pt(2, 4), ts_.get(1).getSecondPoint());
        assertEq(pt(3, 2), ts_.get(1).getThirdPoint());
    }
    @Test
    public void mainComputeIncr2Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(1, 1));
        pts_.add(pt(2, 4));
        pts_.add(pt(0, 3));
        pts_.add(pt(3, 2));
        d_.mainComputeIncr(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(2, ts_.size());
        assertEq(pt(1, 1), ts_.get(0).getFirstPoint());
        assertEq(pt(2, 4), ts_.get(0).getSecondPoint());
        assertEq(pt(0, 3), ts_.get(0).getThirdPoint());
        assertEq(pt(2, 4), ts_.get(1).getFirstPoint());
        assertEq(pt(1, 1), ts_.get(1).getSecondPoint());
        assertEq(pt(3, 2), ts_.get(1).getThirdPoint());
    }

    @Test
    public void mainComputeIncr3Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(3, 2));
        pts_.add(pt(0, 0));
        pts_.add(pt(2, 4));
        pts_.add(pt(1, 5));
        d_.mainComputeIncr(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(2, ts_.size());
        assertEq(pt(3, 2), ts_.get(0).getFirstPoint());
        assertEq(pt(0, 0), ts_.get(0).getSecondPoint());
        assertEq(pt(2, 4), ts_.get(0).getThirdPoint());
        assertEq(pt(0, 0), ts_.get(1).getFirstPoint());
        assertEq(pt(2, 4), ts_.get(1).getSecondPoint());
        assertEq(pt(1, 5), ts_.get(1).getThirdPoint());
    }

    @Test
    public void mainComputeIncr4Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(3, 2));
        pts_.add(pt(1, 1));
        pts_.add(pt(2, 4));
        pts_.add(pt(0, 3));
        d_.mainComputeIncr(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(2, ts_.size());
        assertEq(pt(3, 2), ts_.get(0).getFirstPoint());
        assertEq(pt(1, 1), ts_.get(0).getSecondPoint());
        assertEq(pt(2, 4), ts_.get(0).getThirdPoint());
        assertEq(pt(1, 1), ts_.get(1).getFirstPoint());
        assertEq(pt(2, 4), ts_.get(1).getSecondPoint());
        assertEq(pt(0, 3), ts_.get(1).getThirdPoint());
    }

    @Test
    public void mainComputeIncr5Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(0, 0));
        pts_.add(pt(1, 1));
        pts_.add(pt(2, 2));
        d_.mainComputeIncr(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(0, ts_.size());
    }

    @Test
    public void mainComputeIncr6Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(2, 2));
        pts_.add(pt(4, 0));
        pts_.add(pt(6, 0));
        pts_.add(pt(2, -2));
        d_.mainComputeIncr(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(3, ts_.size());
    }

    @Test
    public void mainComputeIncr7Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(0, 0));
        pts_.add(pt(2, 4));
        pts_.add(pt(1, 5));
        d_.mainComputeIncr(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(1, ts_.size());
        assertEq(pt(0, 0), ts_.get(0).getFirstPoint());
        assertEq(pt(2, 4), ts_.get(0).getSecondPoint());
        assertEq(pt(1, 5), ts_.get(0).getThirdPoint());
    }

    @Test
    public void mainComputeIncr8Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(1, 1));
        pts_.add(pt(2, 4));
        pts_.add(pt(0, 3));
        d_.mainComputeIncr(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(1, ts_.size());
        assertEq(pt(1, 1), ts_.get(0).getFirstPoint());
        assertEq(pt(2, 4), ts_.get(0).getSecondPoint());
        assertEq(pt(0, 3), ts_.get(0).getThirdPoint());
    }

    @Test
    public void mainComputeIncr9Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(0, 3));
        pts_.add(pt(2, 4));
        pts_.add(pt(1, 5));
        d_.mainComputeIncr(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(1, ts_.size());
        assertEq(pt(0, 3), ts_.get(0).getFirstPoint());
        assertEq(pt(2, 4), ts_.get(0).getSecondPoint());
        assertEq(pt(1, 5), ts_.get(0).getThirdPoint());
    }

    @Test
    public void mainComputeIncr10Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(67, 57));
        pts_.add(pt(145, 185));
        pts_.add(pt(333, 264));
        d_.mainComputeIncr(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(1, ts_.size());
        assertEq(pt(67, 57), ts_.get(0).getFirstPoint());
        assertEq(pt(145, 185), ts_.get(0).getSecondPoint());
        assertEq(pt(333, 264), ts_.get(0).getThirdPoint());
    }

    @Test
    public void mainComputeIncr11Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(1, 1));
        pts_.add(pt(2, 4));
        pts_.add(pt(1, 3));
        pts_.add(pt(3, 2));
        d_.mainComputeIncr(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(2, ts_.size());
        assertEq(pt(2, 4), ts_.get(0).getFirstPoint());
        assertEq(pt(1, 3), ts_.get(0).getSecondPoint());
        assertEq(pt(3, 2), ts_.get(0).getThirdPoint());
        assertEq(pt(1, 3), ts_.get(1).getFirstPoint());
        assertEq(pt(1, 1), ts_.get(1).getSecondPoint());
        assertEq(pt(3, 2), ts_.get(1).getThirdPoint());
    }

    @Test
    public void mainComputeIncr12Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(2, 2));
        pts_.add(pt(6, 0));
        pts_.add(pt(2, -2));
        pts_.add(pt(4, 0));
        d_.mainComputeIncr(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(3, ts_.size());
    }

    @Test
    public void mainComputeIncr13Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(1, 2));
        pts_.add(pt(2, 1));
        pts_.add(pt(3, 2));
        pts_.add(pt(4, 1));
        pts_.add(pt(5, 2));
        pts_.add(pt(3, 0));
        d_.mainComputeIncr(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(4, ts_.size());
    }

    @Test
    public void mainComputeIncr14Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(1, 2));
        pts_.add(pt(3, 1));
        pts_.add(pt(5, 2));
        pts_.add(pt(7, 1));
        pts_.add(pt(9, 2));
        pts_.add(pt(5, 0));
        d_.mainComputeIncr(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(4, ts_.size());
    }

    @Test
    public void mainComputeIncr15Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(1, 2));
        pts_.add(pt(1, 3));
        pts_.add(pt(1, 4));
        pts_.add(pt(0, 3));
        d_.mainComputeIncr(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(2, ts_.size());
    }

    @Test
    public void mainComputeIncr16Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(1, 2));
        pts_.add(pt(2, 1));
        pts_.add(pt(3, 2));
        pts_.add(pt(3, 0));
        pts_.add(pt(5, 2));
        pts_.add(pt(5, 0));
        pts_.add(pt(4, 1));
        d_.mainComputeIncr(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(6, ts_.size());
    }

    @Test
    public void mainComputeIncr17Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(1, 2));
        pts_.add(pt(2, 1));
        pts_.add(pt(3, 1));
        pts_.add(pt(4, 2));
        pts_.add(pt(4, 3));
        pts_.add(pt(3, 4));
        pts_.add(pt(2, 4));
        pts_.add(pt(1, 3));
        pts_.add(pt(6, 2));
        d_.mainComputeIncr(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(9, ts_.size());
    }

    @Test
    public void mainComputeIncrConvex0Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        d_.mainComputeIncrConvex(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(0, ts_.size());
    }
    @Test
    public void mainComputeIncrConvex1Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(0, 0));
        pts_.add(pt(2, 4));
        pts_.add(pt(1, 5));
        pts_.add(pt(3, 2));
        d_.mainComputeIncrConvex(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(2, ts_.size());
        assertEq(pt(0, 0), ts_.get(0).getFirstPoint());
        assertEq(pt(1, 5), ts_.get(0).getSecondPoint());
        assertEq(pt(2, 4), ts_.get(0).getThirdPoint());
        assertEq(pt(0, 0), ts_.get(1).getFirstPoint());
        assertEq(pt(2, 4), ts_.get(1).getSecondPoint());
        assertEq(pt(3, 2), ts_.get(1).getThirdPoint());
    }

    @Test
    public void mainComputeIncrConvex2Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(1, 1));
        pts_.add(pt(2, 4));
        pts_.add(pt(0, 3));
        pts_.add(pt(3, 2));
        d_.mainComputeIncrConvex(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(2, ts_.size());
        assertEq(pt(0, 3), ts_.get(0).getFirstPoint());
        assertEq(pt(2, 4), ts_.get(0).getSecondPoint());
        assertEq(pt(3, 2), ts_.get(0).getThirdPoint());
        assertEq(pt(0, 3), ts_.get(1).getFirstPoint());
        assertEq(pt(3, 2), ts_.get(1).getSecondPoint());
        assertEq(pt(1, 1), ts_.get(1).getThirdPoint());
    }

    @Test
    public void mainComputeIncrConvex3Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(3, 2));
        pts_.add(pt(0, 0));
        pts_.add(pt(2, 4));
        pts_.add(pt(1, 5));
        d_.mainComputeIncrConvex(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(2, ts_.size());
        assertEq(pt(0, 0), ts_.get(0).getFirstPoint());
        assertEq(pt(1, 5), ts_.get(0).getSecondPoint());
        assertEq(pt(2, 4), ts_.get(0).getThirdPoint());
        assertEq(pt(0, 0), ts_.get(1).getFirstPoint());
        assertEq(pt(2, 4), ts_.get(1).getSecondPoint());
        assertEq(pt(3, 2), ts_.get(1).getThirdPoint());
    }

    @Test
    public void mainComputeIncrConvex4Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(3, 2));
        pts_.add(pt(1, 1));
        pts_.add(pt(2, 4));
        pts_.add(pt(0, 3));
        d_.mainComputeIncrConvex(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(2, ts_.size());
        assertEq(pt(0, 3), ts_.get(0).getFirstPoint());
        assertEq(pt(2, 4), ts_.get(0).getSecondPoint());
        assertEq(pt(3, 2), ts_.get(0).getThirdPoint());
        assertEq(pt(0, 3), ts_.get(1).getFirstPoint());
        assertEq(pt(3, 2), ts_.get(1).getSecondPoint());
        assertEq(pt(1, 1), ts_.get(1).getThirdPoint());
    }

    @Test
    public void mainComputeIncrConvex5Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(0, 0));
        pts_.add(pt(1, 1));
        pts_.add(pt(2, 2));
        d_.mainComputeIncrConvex(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(0, ts_.size());
    }

    @Test
    public void mainComputeIncrConvex6Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(2, 2));
        pts_.add(pt(4, 0));
        pts_.add(pt(6, 0));
        pts_.add(pt(2, -2));
        d_.mainComputeIncrConvex(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(3, ts_.size());
    }

    @Test
    public void mainComputeIncrConvex7Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(0, 0));
        pts_.add(pt(2, 4));
        pts_.add(pt(1, 5));
        d_.mainComputeIncrConvex(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(1, ts_.size());
        assertEq(pt(0, 0), ts_.get(0).getFirstPoint());
        assertEq(pt(1, 5), ts_.get(0).getSecondPoint());
        assertEq(pt(2, 4), ts_.get(0).getThirdPoint());
    }

    @Test
    public void mainComputeIncrConvex8Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(1, 1));
        pts_.add(pt(2, 4));
        pts_.add(pt(0, 3));
        d_.mainComputeIncrConvex(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(1, ts_.size());
        assertEq(pt(0, 3), ts_.get(0).getFirstPoint());
        assertEq(pt(2, 4), ts_.get(0).getSecondPoint());
        assertEq(pt(1, 1), ts_.get(0).getThirdPoint());
    }

    @Test
    public void mainComputeIncrConvex9Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(0, 3));
        pts_.add(pt(2, 4));
        pts_.add(pt(1, 5));
        d_.mainComputeIncrConvex(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(1, ts_.size());
        assertEq(pt(0, 3), ts_.get(0).getFirstPoint());
        assertEq(pt(1, 5), ts_.get(0).getSecondPoint());
        assertEq(pt(2, 4), ts_.get(0).getThirdPoint());
    }

    @Test
    public void mainComputeIncrConvex10Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(67, 57));
        pts_.add(pt(145, 185));
        pts_.add(pt(333, 264));
        d_.mainComputeIncrConvex(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(1, ts_.size());
        assertEq(pt(67, 57), ts_.get(0).getFirstPoint());
        assertEq(pt(145, 185), ts_.get(0).getSecondPoint());
        assertEq(pt(333, 264), ts_.get(0).getThirdPoint());
    }

    @Test
    public void mainComputeIncrConvex11Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(1, 1));
        pts_.add(pt(2, 4));
        pts_.add(pt(1, 3));
        pts_.add(pt(3, 2));
        d_.mainComputeIncrConvex(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(2, ts_.size());
        assertEq(pt(1, 1), ts_.get(0).getFirstPoint());
        assertEq(pt(1, 3), ts_.get(0).getSecondPoint());
        assertEq(pt(2, 4), ts_.get(0).getThirdPoint());
        assertEq(pt(1, 1), ts_.get(1).getFirstPoint());
        assertEq(pt(2, 4), ts_.get(1).getSecondPoint());
        assertEq(pt(3, 2), ts_.get(1).getThirdPoint());
    }

    @Test
    public void mainComputeIncrConvex12Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(2, 2));
        pts_.add(pt(6, 0));
        pts_.add(pt(2, -2));
        pts_.add(pt(4, 0));
        d_.mainComputeIncrConvex(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(3, ts_.size());
    }

    @Test
    public void mainComputeIncrConvex13Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(1, 2));
        pts_.add(pt(2, 1));
        pts_.add(pt(3, 2));
        pts_.add(pt(4, 1));
        pts_.add(pt(5, 2));
        pts_.add(pt(3, 0));
        d_.mainComputeIncr(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(4, ts_.size());
    }

    @Test
    public void mainComputeIncrConvex14Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(1, 2));
        pts_.add(pt(3, 1));
        pts_.add(pt(5, 2));
        pts_.add(pt(7, 1));
        pts_.add(pt(9, 2));
        pts_.add(pt(5, 0));
        d_.mainComputeIncrConvex(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(4, ts_.size());
    }


    @Test
    public void mainComputeIncrConvex15Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(1, 2));
        pts_.add(pt(1, 3));
        pts_.add(pt(1, 4));
        pts_.add(pt(0, 3));
        d_.mainComputeIncrConvex(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(2, ts_.size());
    }

    @Test
    public void mainComputeIncrConvex16Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(1, 2));
        pts_.add(pt(2, 1));
        pts_.add(pt(3, 2));
        pts_.add(pt(3, 0));
        pts_.add(pt(5, 2));
        pts_.add(pt(5, 0));
        pts_.add(pt(4, 1));
        d_.mainComputeIncrConvex(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(6, ts_.size());
    }

    @Test
    public void mainComputeIncrConvex17Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(1, 2));
        pts_.add(pt(2, 1));
        pts_.add(pt(3, 1));
        pts_.add(pt(4, 2));
        pts_.add(pt(4, 3));
        pts_.add(pt(3, 4));
        pts_.add(pt(2, 4));
        pts_.add(pt(1, 3));
        pts_.add(pt(6, 2));
        d_.mainComputeIncrConvex(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(9, ts_.size());
    }

    @Test
    public void mainComputeIncrSuperTriangle0Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        d_.mainComputeIncrSuperTriangle(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(0, ts_.size());
    }
    @Test
    public void mainComputeIncrSuperTriangle1Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(0, 0));
        pts_.add(pt(2, 4));
        pts_.add(pt(1, 5));
        pts_.add(pt(3, 2));
        d_.mainComputeIncrSuperTriangle(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(2, ts_.size());
        assertEq(pt(0, 0), ts_.get(0).getFirstPoint());
        assertEq(pt(2, 4), ts_.get(0).getSecondPoint());
        assertEq(pt(1, 5), ts_.get(0).getThirdPoint());
        assertEq(pt(0, 0), ts_.get(1).getFirstPoint());
        assertEq(pt(2, 4), ts_.get(1).getSecondPoint());
        assertEq(pt(3, 2), ts_.get(1).getThirdPoint());
    }

    @Test
    public void mainComputeIncrSuperTriangle2Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(1, 1));
        pts_.add(pt(2, 4));
        pts_.add(pt(0, 3));
        pts_.add(pt(3, 2));
        d_.mainComputeIncrSuperTriangle(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(2, ts_.size());
        assertEq(pt(1, 1), ts_.get(0).getFirstPoint());
        assertEq(pt(2, 4), ts_.get(0).getSecondPoint());
        assertEq(pt(0, 3), ts_.get(0).getThirdPoint());
        assertEq(pt(2, 4), ts_.get(1).getFirstPoint());
        assertEq(pt(1, 1), ts_.get(1).getSecondPoint());
        assertEq(pt(3, 2), ts_.get(1).getThirdPoint());
    }

    @Test
    public void mainComputeIncrSuperTriangle3Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(3, 2));
        pts_.add(pt(0, 0));
        pts_.add(pt(2, 4));
        pts_.add(pt(1, 5));
        d_.mainComputeIncrSuperTriangle(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(2, ts_.size());
        assertEq(pt(3, 2), ts_.get(0).getFirstPoint());
        assertEq(pt(0, 0), ts_.get(0).getSecondPoint());
        assertEq(pt(2, 4), ts_.get(0).getThirdPoint());
        assertEq(pt(0, 0), ts_.get(1).getFirstPoint());
        assertEq(pt(2, 4), ts_.get(1).getSecondPoint());
        assertEq(pt(1, 5), ts_.get(1).getThirdPoint());
    }

    @Test
    public void mainComputeIncrSuperTriangle4Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(3, 2));
        pts_.add(pt(1, 1));
        pts_.add(pt(2, 4));
        pts_.add(pt(0, 3));
        d_.mainComputeIncrSuperTriangle(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(2, ts_.size());
        assertEq(pt(3, 2), ts_.get(0).getFirstPoint());
        assertEq(pt(1, 1), ts_.get(0).getSecondPoint());
        assertEq(pt(2, 4), ts_.get(0).getThirdPoint());
        assertEq(pt(1, 1), ts_.get(1).getFirstPoint());
        assertEq(pt(2, 4), ts_.get(1).getSecondPoint());
        assertEq(pt(0, 3), ts_.get(1).getThirdPoint());
    }

    @Test
    public void mainComputeIncrSuperTriangle5Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(0, 0));
        pts_.add(pt(1, 1));
        pts_.add(pt(2, 2));
        d_.mainComputeIncrSuperTriangle(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(0, ts_.size());
    }

    @Test
    public void mainComputeIncrSuperTriangle6Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(2, 2));
        pts_.add(pt(4, 0));
        pts_.add(pt(6, 0));
        pts_.add(pt(2, -2));
        d_.mainComputeIncrSuperTriangle(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(3, ts_.size());
    }

    @Test
    public void mainComputeIncrSuperTriangle7Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(0, 0));
        pts_.add(pt(2, 4));
        pts_.add(pt(1, 5));
        d_.mainComputeIncrSuperTriangle(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(1, ts_.size());
        assertEq(pt(0, 0), ts_.get(0).getFirstPoint());
        assertEq(pt(2, 4), ts_.get(0).getSecondPoint());
        assertEq(pt(1, 5), ts_.get(0).getThirdPoint());
    }

    @Test
    public void mainComputeIncrSuperTriangle8Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(1, 1));
        pts_.add(pt(2, 4));
        pts_.add(pt(0, 3));
        d_.mainComputeIncrSuperTriangle(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(1, ts_.size());
        assertEq(pt(1, 1), ts_.get(0).getFirstPoint());
        assertEq(pt(2, 4), ts_.get(0).getSecondPoint());
        assertEq(pt(0, 3), ts_.get(0).getThirdPoint());
    }

    @Test
    public void mainComputeIncrSuperTriangle9Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(0, 3));
        pts_.add(pt(2, 4));
        pts_.add(pt(1, 5));
        d_.mainComputeIncrSuperTriangle(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(1, ts_.size());
        assertEq(pt(0, 3), ts_.get(0).getFirstPoint());
        assertEq(pt(2, 4), ts_.get(0).getSecondPoint());
        assertEq(pt(1, 5), ts_.get(0).getThirdPoint());
    }

    @Test
    public void mainComputeIncrSuperTriangle10Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(67, 57));
        pts_.add(pt(145, 185));
        pts_.add(pt(333, 264));
        d_.mainComputeIncrSuperTriangle(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(1, ts_.size());
        assertEq(pt(67, 57), ts_.get(0).getFirstPoint());
        assertEq(pt(145, 185), ts_.get(0).getSecondPoint());
        assertEq(pt(333, 264), ts_.get(0).getThirdPoint());
    }

    @Test
    public void mainComputeIncrSuperTriangle11Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(1, 1));
        pts_.add(pt(2, 4));
        pts_.add(pt(1, 3));
        pts_.add(pt(3, 2));
        d_.mainComputeIncrSuperTriangle(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(2, ts_.size());
        assertEq(pt(2, 4), ts_.get(0).getFirstPoint());
        assertEq(pt(1, 3), ts_.get(0).getSecondPoint());
        assertEq(pt(3, 2), ts_.get(0).getThirdPoint());
        assertEq(pt(1, 3), ts_.get(1).getFirstPoint());
        assertEq(pt(1, 1), ts_.get(1).getSecondPoint());
        assertEq(pt(3, 2), ts_.get(1).getThirdPoint());
    }

    @Test
    public void mainComputeIncrSuperTriangle12Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(2, 2));
        pts_.add(pt(6, 0));
        pts_.add(pt(2, -2));
        pts_.add(pt(4, 0));
        d_.mainComputeIncrSuperTriangle(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(3, ts_.size());
    }

    @Test
    public void mainComputeIncrSuperTriangle13Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(1, 2));
        pts_.add(pt(2, 1));
        pts_.add(pt(3, 2));
        pts_.add(pt(4, 1));
        pts_.add(pt(5, 2));
        pts_.add(pt(3, 0));
        d_.mainComputeIncrSuperTriangle(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(4, ts_.size());
    }

    @Test
    public void mainComputeIncrSuperTriangle14Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(1, 2));
        pts_.add(pt(3, 1));
        pts_.add(pt(5, 2));
        pts_.add(pt(7, 1));
        pts_.add(pt(9, 2));
        pts_.add(pt(5, 0));
        d_.mainComputeIncrSuperTriangle(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(4, ts_.size());
    }


    @Test
    public void mainComputeIncrSuperTriangle15Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(1, 2));
        pts_.add(pt(1, 3));
        pts_.add(pt(1, 4));
        pts_.add(pt(0, 3));
        d_.mainComputeIncrSuperTriangle(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(2, ts_.size());
    }

    @Test
    public void mainComputeIncrSuperTriangle16Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        pts_.add(pt(1, 2));
        pts_.add(pt(2, 1));
        pts_.add(pt(3, 2));
        pts_.add(pt(3, 0));
        pts_.add(pt(5, 2));
        pts_.add(pt(5, 0));
        pts_.add(pt(4, 1));
        d_.mainComputeIncrSuperTriangle(pts_);
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(6, ts_.size());
    }

    @Test
    public void getNextPoints1Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        RatePoint one_= pt(1, 1);
        pts_.add(one_);
        RatePoint two_= pt(2, 4);
        pts_.add(two_);
        RatePoint three_= pt(0, 3);
        pts_.add(three_);
        RatePoint four_ = pt(3, 2);
        pts_.add(four_);
        d_.compute(pts_, false);
        IdMap<RatePoint, IdList<RatePoint>> ids_ = d_.getNextPoints();
        assertEq(4, ids_.size());
        IdList<RatePoint> nextOne_ = ids_.getVal(one_);
        assertEq(3, nextOne_.size());
        assertSame(three_, nextOne_.get(0));
        assertSame(two_, nextOne_.get(1));
        assertSame(four_, nextOne_.get(2));
        IdList<RatePoint> nextTwo_ = ids_.getVal(two_);
        assertEq(3, nextTwo_.size());
        assertSame(four_, nextTwo_.get(0));
        assertSame(one_, nextTwo_.get(1));
        assertSame(three_, nextTwo_.get(2));
        IdList<RatePoint> nextThree_ = ids_.getVal(three_);
        assertEq(2, nextThree_.size());
        assertSame(two_, nextThree_.get(0));
        assertSame(one_, nextThree_.get(1));
        IdList<RatePoint> nextFour_ = ids_.getVal(four_);
        assertEq(2, nextFour_.size());
        assertSame(one_, nextFour_.get(0));
        assertSame(two_, nextFour_.get(1));
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(2, ts_.size());
        assertEq(pt(1, 1), ts_.get(0).getFirstPoint());
        assertEq(pt(2, 4), ts_.get(0).getSecondPoint());
        assertEq(pt(0, 3), ts_.get(0).getThirdPoint());
        assertEq(pt(2, 4), ts_.get(1).getFirstPoint());
        assertEq(pt(1, 1), ts_.get(1).getSecondPoint());
        assertEq(pt(3, 2), ts_.get(1).getThirdPoint());
    }

    @Test
    public void getNextPoints2Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        RatePoint one_= pt(0, 0);
        pts_.add(one_);
        RatePoint two_= pt(2, 4);
        pts_.add(two_);
        RatePoint three_= pt(1, 5);
        pts_.add(three_);
        RatePoint four_ = pt(3, 2);
        pts_.add(four_);
        d_.compute(pts_, false);
        IdMap<RatePoint, IdList<RatePoint>> ids_ = d_.getNextPoints();
        assertEq(4, ids_.size());
        IdList<RatePoint> nextOne_ = ids_.getVal(one_);
        assertEq(3, nextOne_.size());
        assertSame(three_, nextOne_.get(0));
        assertSame(two_, nextOne_.get(1));
        assertSame(four_, nextOne_.get(2));
        IdList<RatePoint> nextTwo_ = ids_.getVal(two_);
        assertEq(3, nextTwo_.size());
        assertSame(four_, nextTwo_.get(0));
        assertSame(one_, nextTwo_.get(1));
        assertSame(three_, nextTwo_.get(2));
        IdList<RatePoint> nextThree_ = ids_.getVal(three_);
        assertEq(2, nextThree_.size());
        assertSame(two_, nextThree_.get(0));
        assertSame(one_, nextThree_.get(1));
        IdList<RatePoint> nextFour_ = ids_.getVal(four_);
        assertEq(2, nextFour_.size());
        assertSame(one_, nextFour_.get(0));
        assertSame(two_, nextFour_.get(1));
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(2, ts_.size());
        assertEq(pt(0, 0), ts_.get(0).getFirstPoint());
        assertEq(pt(2, 4), ts_.get(0).getSecondPoint());
        assertEq(pt(1, 5), ts_.get(0).getThirdPoint());
        assertEq(pt(2, 4), ts_.get(1).getFirstPoint());
        assertEq(pt(0, 0), ts_.get(1).getSecondPoint());
        assertEq(pt(3, 2), ts_.get(1).getThirdPoint());
    }

    @Test
    public void getNextPoints3Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> points_ = new CustList<RatePoint>();
        RatePoint one_ = pt(0, 0);
        points_.add(one_);
        RatePoint two_ = pt(-2, 0);
        points_.add(two_);
        RatePoint three_ = pt(-2, 1);
        points_.add(three_);
        RatePoint four_ = pt(-2, 2);
        points_.add(four_);
        RatePoint five_ = pt(-1, 2);
        points_.add(five_);
        RatePoint six_ = pt(0, 2);
        points_.add(six_);
        RatePoint seven_ = pt(1, 2);
        points_.add(seven_);
        RatePoint eight_ = pt(2, 2);
        points_.add(eight_);
        RatePoint nine_ = pt(2, 1);
        points_.add(nine_);
        RatePoint ten_ = pt(2, 0);
        points_.add(ten_);
        RatePoint eleven_ = pt(2, -1);
        points_.add(eleven_);
        RatePoint twelve_ = pt(2, -2);
        points_.add(twelve_);
        RatePoint thirteen_ = pt(1, -2);
        points_.add(thirteen_);
        RatePoint fourteen_ = pt(0, -2);
        points_.add(fourteen_);
        RatePoint fifteen_ = pt(-1, -2);
        points_.add(fifteen_);
        RatePoint sixteen_ = pt(-2, -2);
        points_.add(sixteen_);
        RatePoint seventeen_ = pt(-2, -1);
        points_.add(seventeen_);
        d_.compute(points_);
        IdMap<RatePoint, IdList<RatePoint>> ids_ = d_.getNextPoints();
        assertEq(17, ids_.size());
        IdList<RatePoint> nextOne_ = ids_.getVal(one_);
        assertEq(12, nextOne_.size());
        assertSame(two_, nextOne_.get(0));
        assertSame(three_, nextOne_.get(1));
        assertSame(five_, nextOne_.get(2));
        assertSame(six_, nextOne_.get(3));
        assertSame(seven_, nextOne_.get(4));
        assertSame(nine_, nextOne_.get(5));
        assertSame(ten_, nextOne_.get(6));
        assertSame(eleven_, nextOne_.get(7));
        assertSame(thirteen_, nextOne_.get(8));
        assertSame(fourteen_, nextOne_.get(9));
        assertSame(fifteen_, nextOne_.get(10));
        assertSame(seventeen_, nextOne_.get(11));
    }

    @Test
    public void getNextPoints4Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        RatePoint one_= pt(1, 1);
        pts_.add(one_);
        RatePoint two_= pt(3, 2);
        pts_.add(two_);
        RatePoint three_= pt(0, 3);
        pts_.add(three_);
        RatePoint four_ = pt(2, 4);
        pts_.add(four_);
        d_.compute(pts_, false);
        IdMap<RatePoint, IdList<RatePoint>> ids_ = d_.getNextPoints();
        assertEq(4, ids_.size());
        IdList<RatePoint> nextOne_ = ids_.getVal(one_);
        assertEq(2, nextOne_.size());
        assertSame(three_, nextOne_.get(0));
        assertSame(two_, nextOne_.get(1));
        IdList<RatePoint> nextTwo_ = ids_.getVal(two_);
        assertEq(3, nextTwo_.size());
        assertSame(one_, nextTwo_.get(0));
        assertSame(three_, nextTwo_.get(1));
        assertSame(four_, nextTwo_.get(2));
        IdList<RatePoint> nextThree_ = ids_.getVal(three_);
        assertEq(3, nextThree_.size());
        assertSame(four_, nextThree_.get(0));
        assertSame(two_, nextThree_.get(1));
        assertSame(one_, nextThree_.get(2));
        IdList<RatePoint> nextFour_ = ids_.getVal(four_);
        assertEq(2, nextFour_.size());
        assertSame(two_, nextFour_.get(0));
        assertSame(three_, nextFour_.get(1));
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(2, ts_.size());
        assertEq(pt(1, 1), ts_.get(0).getFirstPoint());
        assertEq(pt(3, 2), ts_.get(0).getSecondPoint());
        assertEq(pt(0, 3), ts_.get(0).getThirdPoint());
        assertEq(pt(0, 3), ts_.get(1).getFirstPoint());
        assertEq(pt(3, 2), ts_.get(1).getSecondPoint());
        assertEq(pt(2, 4), ts_.get(1).getThirdPoint());
    }

    @Test
    public void getNextPoints5Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        RatePoint one_= pt(0, 0);
        pts_.add(one_);
        RatePoint two_= pt(3, 2);
        pts_.add(two_);
        RatePoint three_= pt(1, 5);
        pts_.add(three_);
        RatePoint four_ = pt(2, 4);
        pts_.add(four_);
        d_.compute(pts_, false);
        IdMap<RatePoint, IdList<RatePoint>> ids_ = d_.getNextPoints();
        assertEq(4, ids_.size());
        IdList<RatePoint> nextOne_ = ids_.getVal(one_);
        assertEq(3, nextOne_.size());
        assertSame(three_, nextOne_.get(0));
        assertSame(four_, nextOne_.get(1));
        assertSame(two_, nextOne_.get(2));
        IdList<RatePoint> nextTwo_ = ids_.getVal(two_);
        assertEq(2, nextTwo_.size());
        assertSame(one_, nextTwo_.get(0));
        assertSame(four_, nextTwo_.get(1));
        IdList<RatePoint> nextThree_ = ids_.getVal(three_);
        assertEq(2, nextThree_.size());
        assertSame(four_, nextThree_.get(0));
        assertSame(one_, nextThree_.get(1));
        IdList<RatePoint> nextFour_ = ids_.getVal(four_);
        assertEq(3, nextFour_.size());
        assertSame(two_, nextFour_.get(0));
        assertSame(one_, nextFour_.get(1));
        assertSame(three_, nextFour_.get(2));
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(2, ts_.size());
        assertEq(pt(0, 0), ts_.get(0).getFirstPoint());
        assertEq(pt(3, 2), ts_.get(0).getSecondPoint());
        assertEq(pt(2, 4), ts_.get(0).getThirdPoint());
        assertEq(pt(1, 5), ts_.get(1).getFirstPoint());
        assertEq(pt(0, 0), ts_.get(1).getSecondPoint());
        assertEq(pt(2, 4), ts_.get(1).getThirdPoint());
    }

    @Test
    public void getNextPoints6Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        RatePoint one_= pt(-2, -4);
        pts_.add(one_);
        RatePoint two_= pt(-1, -5);
        pts_.add(two_);
        RatePoint three_= pt(3, 2);
        pts_.add(three_);
        RatePoint four_ = pt(5, 1);
        pts_.add(four_);
        RatePoint five_ = pt(-5, -6);
        pts_.add(five_);
        d_.compute(pts_, false);
        IdMap<RatePoint, IdList<RatePoint>> ids_ = d_.getNextPoints();
        assertEq(5, ids_.size());
        IdList<RatePoint> nextOne_ = ids_.getVal(one_);
        assertEq(3, nextOne_.size());
        assertSame(two_, nextOne_.get(0));
        assertSame(five_, nextOne_.get(1));
        assertSame(three_, nextOne_.get(2));
        IdList<RatePoint> nextTwo_ = ids_.getVal(two_);
        assertEq(4, nextTwo_.size());
        assertSame(five_, nextTwo_.get(0));
        assertSame(one_, nextTwo_.get(1));
        IdList<RatePoint> nextThree_ = ids_.getVal(three_);
        assertEq(3, nextThree_.size());
        assertSame(four_, nextThree_.get(0));
        assertSame(two_, nextThree_.get(1));
        IdList<RatePoint> nextFour_ = ids_.getVal(four_);
        assertEq(2, nextFour_.size());
        assertSame(two_, nextFour_.get(0));
        assertSame(three_, nextFour_.get(1));
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(3, ts_.size());
        assertEq(one_, ts_.get(0).getFirstPoint());
        assertEq(two_, ts_.get(0).getSecondPoint());
        assertEq(three_, ts_.get(0).getThirdPoint());
        assertEq(three_, ts_.get(1).getFirstPoint());
        assertEq(two_, ts_.get(1).getSecondPoint());
        assertEq(four_, ts_.get(1).getThirdPoint());
    }

    @Test
    public void getNextPoints7Test() {
        Delaunay d_ = new Delaunay();
        CustList<RatePoint> pts_ = new CustList<RatePoint>();
        RatePoint one_= pt(-2, -5);
        pts_.add(one_);
        RatePoint two_= pt(-4, -7);
        pts_.add(two_);
        RatePoint three_= pt(5, 1);
        pts_.add(three_);
        RatePoint four_ = pt(-2, -4);
        pts_.add(four_);
        RatePoint five_ = pt(4, -4);
        pts_.add(five_);
        RatePoint six_ = pt(-3, 2);
        pts_.add(six_);
        d_.compute(pts_, false);
        IdMap<RatePoint, IdList<RatePoint>> ids_ = d_.getNextPoints();
        assertEq(6, ids_.size());
        IdList<RatePoint> nextOne_ = ids_.getVal(one_);
        assertEq(3, nextOne_.size());
        assertSame(two_, nextOne_.get(0));
        assertSame(four_, nextOne_.get(1));
        assertSame(five_, nextOne_.get(2));
        IdList<RatePoint> nextTwo_ = ids_.getVal(two_);
        assertEq(4, nextTwo_.size());
        assertSame(six_, nextTwo_.get(0));
        assertSame(four_, nextTwo_.get(1));
        IdList<RatePoint> nextThree_ = ids_.getVal(three_);
        assertEq(3, nextThree_.size());
        assertSame(five_, nextThree_.get(0));
        assertSame(four_, nextThree_.get(1));
        IdList<RatePoint> nextFour_ = ids_.getVal(four_);
        assertEq(5, nextFour_.size());
        assertSame(two_, nextFour_.get(0));
        assertSame(six_, nextFour_.get(1));
        assertSame(three_, nextFour_.get(2));
        IdList<Triangle> ts_ = d_.getTriangles();
        assertEq(6, ts_.size());
        assertEq(two_, ts_.get(0).getFirstPoint());
        assertEq(one_, ts_.get(0).getSecondPoint());
        assertEq(four_, ts_.get(0).getThirdPoint());
        assertEq(one_, ts_.get(1).getFirstPoint());
        assertEq(two_, ts_.get(1).getSecondPoint());
        assertEq(five_, ts_.get(1).getThirdPoint());
    }

    private static Rect rect(int _one, int _two, int _three, int _four) {
        return new Rect(new Rate(_one), new Rate(_two), new Rate(_three), new Rate(_four));
    }
    private static RatePoint pt(int _x, int _y) {
        return new RatePoint(new Rate(_x), new Rate(_y));
    }
}
