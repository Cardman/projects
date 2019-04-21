package code.maths.matrix;
import static code.maths.EquallableMathUtil.assertEq;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import code.maths.Rate;


public class MatrixTest {

    @Test
    public void inv1Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(Rate.one());
        mat_.addLine(vect_);
        Matrix res_ = mat_.inv();
        assertEq(2, res_.nbLines());
        assertEq(2, res_.nbCols());
        assertEq(Rate.one(), res_.cell(0, 0));
        assertEq(Rate.zero(), res_.cell(0, 1));
        assertEq(Rate.zero(), res_.cell(1, 0));
        assertEq(Rate.one(), res_.cell(1, 1));
    }

    @Test
    public void inv2Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.one());
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.one());
        mat_.addLine(vect_);
        Matrix res_ = mat_.inv();
        assertEq(2, res_.nbLines());
        assertEq(2, res_.nbCols());
        assertEq(new Rate("1/4"), res_.cell(0, 0));
        assertEq(new Rate("1/4"), res_.cell(0, 1));
        assertEq(new Rate("1/4"), res_.cell(1, 0));
        assertEq(new Rate("1/4"), res_.cell(1, 1));
    }

    @Test
    public void inv3Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        Matrix res_ = mat_.inv();
        assertEq(2, res_.nbLines());
        assertEq(2, res_.nbCols());
        assertEq(Rate.zero(), res_.cell(0, 0));
        assertEq(Rate.one(), res_.cell(0, 1));
        assertEq(Rate.zero(), res_.cell(1, 0));
        assertEq(Rate.zero(), res_.cell(1, 1));
    }

    @Test
    public void inv4Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        Matrix res_ = mat_.inv();
        assertEq(2, res_.nbLines());
        assertEq(1, res_.nbCols());
        assertEq(Rate.one(), res_.cell(0, 0));
        assertEq(Rate.zero(), res_.cell(1, 0));
    }

    @Test
    public void inv5Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        Matrix res_ = mat_.inv();
        assertEq(2, res_.nbLines());
        assertEq(2, res_.nbCols());
        assertEq(Rate.zero(), res_.cell(0, 0));
        assertEq(Rate.zero(), res_.cell(0, 1));
        assertEq(Rate.zero(), res_.cell(1, 0));
        assertEq(Rate.zero(), res_.cell(1, 1));
    }

    @Test
    public void inv6Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(Rate.zero());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(Rate.zero());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        Matrix res_ = mat_.inv();
        assertEq(3, res_.nbLines());
        assertEq(2, res_.nbCols());
        assertEq(Rate.zero(), res_.cell(0, 0));
        assertEq(Rate.zero(), res_.cell(0, 1));
        assertEq(Rate.zero(), res_.cell(1, 0));
        assertEq(Rate.zero(), res_.cell(1, 1));
        assertEq(Rate.zero(), res_.cell(2, 0));
        assertEq(Rate.zero(), res_.cell(2, 1));
    }
    @Test
    public void rank1Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(Rate.one());
        mat_.addLine(vect_);
        assertEq(2, mat_.rank());
    }
    @Test
    public void rank2Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.one());
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.one());
        mat_.addLine(vect_);
        assertEq(1, mat_.rank());
    }
    @Test
    public void rank3Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        assertEq(1, mat_.rank());
    }
    @Test
    public void rank4Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        assertEq(1, mat_.rank());
    }
    @Test
    public void rank5Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        assertEq(0, mat_.rank());
    }
    @Test
    public void rank6Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(Rate.zero());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(Rate.zero());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        assertEq(0, mat_.rank());
    }
    @Test
    public void quickRank1Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.one());
        mat_.addLine(vect_);
        assertEq(1, mat_.quickRank());
    }
    @Test
    public void quickRank2Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.one());
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.one());
        mat_.addLine(vect_);
        assertEq(1, mat_.quickRank());
    }
    @Test
    public void quickRank3Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(Rate.zero());
        vect_.add(Rate.one());
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(Rate.zero());
        vect_.add(Rate.one());
        mat_.addLine(vect_);
        assertEq(1, mat_.quickRank());
    }
    @Test
    public void det1Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        assertEq(Rate.one(), mat_.det());
    }

    @Test
    public void det2Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(Rate.one());
        mat_.addLine(vect_);
        assertEq(Rate.one(), mat_.det());
    }

    @Test
    public void det3Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_ = new Vect();
        vect_.add(Rate.one());
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        assertEq(Rate.one(), mat_.det());
    }
    @Test
    public void detSquare1Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        assertEq(Rate.zero(), mat_.det());
    }
    @Test
    public void polCaract1Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(Rate.one());
        mat_.addLine(vect_);
        Polynom p_ = mat_.polCaract();
        assertEq(3, p_.size());
        assertEq(new Rate(1), p_.get(0));
        assertEq(new Rate(-2), p_.get(1));
        assertEq(new Rate(1), p_.get(2));
    }
    @Test
    public void polCaract2Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(new Rate(2));
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(new Rate(2));
        vect_.add(Rate.one());
        mat_.addLine(vect_);
        Polynom p_ = mat_.polCaract();
        assertEq(3, p_.size());
        assertEq(new Rate(1), p_.get(0));
        assertEq(new Rate(-2), p_.get(1));
        assertEq(new Rate(-3), p_.get(2));
    }
    @Test
    public void diagTrig1Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(Rate.one());
        mat_.addLine(vect_);
        Trigonal t_ = mat_.diagTrig();
        assertSame(Diagonal.DIAGO,t_.getDiagonal());
        assertEq(1,t_.getRates().size());
        assertEq(new Rate(1),t_.getRates().get(0).getFirst());
        assertEq(2,t_.getRates().get(0).getSecond().getFirst());
        assertEq(2,t_.getRates().get(0).getSecond().getSecond());
    }
    @Test
    public void diagTrig2Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.one());
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(Rate.one());
        mat_.addLine(vect_);
        Trigonal t_ = mat_.diagTrig();
        assertSame(Diagonal.TRIGO,t_.getDiagonal());
        assertEq(1,t_.getRates().size());
        assertEq(new Rate(1),t_.getRates().get(0).getFirst());
        assertEq(2,t_.getRates().get(0).getSecond().getFirst());
        assertEq(1,t_.getRates().get(0).getSecond().getSecond());
    }
    @Test
    public void diagTrig3Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(Rate.one());
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.minusOne());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        Trigonal t_ = mat_.diagTrig();
        assertSame(Diagonal.UN_TRIGO,t_.getDiagonal());
        assertEq(0,t_.getRates().size());
    }
    @Test
    public void diagTrig4Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(Rate.minusOne());
        mat_.addLine(vect_);
        Trigonal t_ = mat_.diagTrig();
        assertSame(Diagonal.DIAGO,t_.getDiagonal());
        assertEq(2,t_.getRates().size());
        assertEq(new Rate(-1),t_.getRates().get(0).getFirst());
        assertEq(1,t_.getRates().get(0).getSecond().getFirst());
        assertEq(1,t_.getRates().get(0).getSecond().getSecond());
        assertEq(new Rate(1),t_.getRates().get(1).getFirst());
        assertEq(1,t_.getRates().get(1).getSecond().getFirst());
        assertEq(1,t_.getRates().get(1).getSecond().getSecond());
    }
    @Test
    public void ownVects1Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(Rate.one());
        mat_.addLine(vect_);
        Matrix m_ = mat_.ownVects(Rate.one());
        assertEq(2, m_.nbLines());
        assertEq(2, m_.nbCols());
        assertEq(Rate.one(), m_.cell(0, 0));
        assertEq(Rate.zero(), m_.cell(0, 1));
        assertEq(Rate.zero(), m_.cell(1, 0));
        assertEq(Rate.one(), m_.cell(1, 1));
    }
    @Test
    public void ownVects2Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(Rate.minusOne());
        mat_.addLine(vect_);
        Matrix m_ = mat_.ownVects(Rate.one());
        assertEq(1, m_.nbLines());
        assertEq(2, m_.nbCols());
        assertEq(Rate.zero(), m_.cell(0, 0));
        assertEq(Rate.one(), m_.cell(0, 1));
    }
    @Test
    public void ownVects3Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(Rate.minusOne());
        mat_.addLine(vect_);
        Matrix m_ = mat_.ownVects(Rate.minusOne());
        assertEq(1, m_.nbLines());
        assertEq(2, m_.nbCols());
    }
}
