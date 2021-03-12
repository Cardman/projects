package code.maths.geo;

import code.maths.EquallableMathUtil;
import code.maths.Rate;
import org.junit.Test;

import code.util.CustList;
import code.util.IdList;


public class DelaunayThreeDimsTest extends EquallableMathUtil {

    @Test
    public void compute0Test() {
        DelaunayThreeDims d_ = new DelaunayThreeDims();
        CustList<RatePointThreeDims> pts_ = new CustList<RatePointThreeDims>();
        d_.compute(pts_);
        IdList<Tetrahedron> ts_ = d_.getTriangles();
        assertEq(0, ts_.size());
    }
    @Test
    public void compute1Test() {
        DelaunayThreeDims d_ = new DelaunayThreeDims();
        CustList<RatePointThreeDims> pts_ = new CustList<RatePointThreeDims>();
        pts_.add(pt(0, 0, 0));
        pts_.add(pt(2, 4, 0));
        pts_.add(pt(1, 5, 0));
        pts_.add(pt(3, 2, 1));
        d_.compute(pts_);
        IdList<Tetrahedron> ts_ = d_.getTriangles();
        assertEq(1, ts_.size());
        assertEq(pt(0, 0, 0), ts_.get(0).getFirstPoint());
        assertEq(pt(2, 4, 0), ts_.get(0).getSecondPoint());
        assertEq(pt(1, 5, 0), ts_.get(0).getThirdPoint());
        assertEq(pt(3, 2, 1), ts_.get(0).getFourthPoint());
    }

    @Test
    public void compute2Test() {
        DelaunayThreeDims d_ = new DelaunayThreeDims();
        CustList<RatePointThreeDims> pts_ = new CustList<RatePointThreeDims>();
        pts_.add(pt(0, 0, 0));
        pts_.add(pt(2, 4, 0));
        pts_.add(pt(1, 5, 0));
        pts_.add(pt(3, 2, 1));
        pts_.add(pt(3, 2, -1));
        d_.compute(pts_);
        IdList<Tetrahedron> ts_ = d_.getTriangles();
        assertEq(2, ts_.size());
        assertEq(pt(0, 0, 0), ts_.get(0).getFirstPoint());
        assertEq(pt(2, 4, 0), ts_.get(0).getSecondPoint());
        assertEq(pt(1, 5, 0), ts_.get(0).getThirdPoint());
        assertEq(pt(3, 2, 1), ts_.get(0).getFourthPoint());
        assertEq(pt(0, 0, 0), ts_.get(1).getFirstPoint());
        assertEq(pt(2, 4, 0), ts_.get(1).getSecondPoint());
        assertEq(pt(3, 2, 1), ts_.get(1).getThirdPoint());
        assertEq(pt(3, 2, -1), ts_.get(1).getFourthPoint());
    }

    @Test
    public void compute3Test() {
        DelaunayThreeDims d_ = new DelaunayThreeDims();
        CustList<RatePointThreeDims> pts_ = new CustList<RatePointThreeDims>();
        pts_.add(pt(2, 4, 0));
        pts_.add(pt(0, 0, 0));
        pts_.add(pt(1, 5, 0));
        pts_.add(pt(3, 2, 1));
        pts_.add(pt(3, 2, -1));
        d_.compute(pts_);
        IdList<Tetrahedron> ts_ = d_.getTriangles();
        assertEq(2, ts_.size());
    }

    private static RatePointThreeDims pt(int _x, int _y, int _z) {
        return new RatePointThreeDims(new Rate(_x), new Rate(_y), new Rate(_z));
    }

}
