package code.maths.geo;
import static code.maths.EquallableMathUtil.assertEq;
import static code.util.opers.EquallableUtil.assertEq;

import org.junit.Test;

import code.util.EqList;
import code.util.IdList;

@SuppressWarnings("static-method")
public class DelaunayThreeDimsTest {

    @Test
    public void compute1Test() {
        DelaunayThreeDims d_ = new DelaunayThreeDims();
        EqList<CustPointThreeDims> pts_ = new EqList<CustPointThreeDims>();
        pts_.add(new CustPointThreeDims(0, 0, 0));
        pts_.add(new CustPointThreeDims(2, 4, 0));
        pts_.add(new CustPointThreeDims(1, 5, 0));
        pts_.add(new CustPointThreeDims(3, 2, 1));
        d_.compute(pts_);
        IdList<Tetrahedron> ts_ = d_.getTriangles();
        assertEq(1, ts_.size());
        assertEq(new CustPointThreeDims(0, 0, 0), ts_.get(0).getFirstPoint());
        assertEq(new CustPointThreeDims(2, 4, 0), ts_.get(0).getSecondPoint());
        assertEq(new CustPointThreeDims(1, 5, 0), ts_.get(0).getThirdPoint());
        assertEq(new CustPointThreeDims(3, 2, 1), ts_.get(0).getFourthPoint());
    }

    @Test
    public void compute2Test() {
        DelaunayThreeDims d_ = new DelaunayThreeDims();
        EqList<CustPointThreeDims> pts_ = new EqList<CustPointThreeDims>();
        pts_.add(new CustPointThreeDims(0, 0, 0));
        pts_.add(new CustPointThreeDims(2, 4, 0));
        pts_.add(new CustPointThreeDims(1, 5, 0));
        pts_.add(new CustPointThreeDims(3, 2, 1));
        pts_.add(new CustPointThreeDims(3, 2, -1));
        d_.compute(pts_);
        IdList<Tetrahedron> ts_ = d_.getTriangles();
        assertEq(2, ts_.size());
        assertEq(new CustPointThreeDims(0, 0, 0), ts_.get(0).getFirstPoint());
        assertEq(new CustPointThreeDims(2, 4, 0), ts_.get(0).getSecondPoint());
        assertEq(new CustPointThreeDims(1, 5, 0), ts_.get(0).getThirdPoint());
        assertEq(new CustPointThreeDims(3, 2, 1), ts_.get(0).getFourthPoint());
        assertEq(new CustPointThreeDims(0, 0, 0), ts_.get(1).getFirstPoint());
        assertEq(new CustPointThreeDims(2, 4, 0), ts_.get(1).getSecondPoint());
        assertEq(new CustPointThreeDims(3, 2, 1), ts_.get(1).getThirdPoint());
        assertEq(new CustPointThreeDims(3, 2, -1), ts_.get(1).getFourthPoint());
    }
}
