package code.maths.matrix;

import code.maths.EquallableMathUtil;
import code.maths.LgInt;
import org.junit.Test;

import code.maths.Rate;


public class MatrixTest extends EquallableMathUtil {

    @Test
    public void nbCols1Test() {
        Matrix mat_ = new Matrix();
        assertEq(0, mat_.nbCols());
    }
    @Test
    public void nbCols2Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        assertEq(2, mat_.nbCols());
    }

    @Test
    public void nbLines1Test() {
        Matrix mat_ = new Matrix();
        assertEq(0, mat_.nbLines());
    }
    @Test
    public void nbLines2Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        assertEq(1, mat_.nbLines());
    }
    @Test
    public void transposeRef1Test() {
        Matrix mat_ = new Matrix();
        Matrix t_ = mat_.transposeRef();
        assertEq(0, t_.nbCols());
        assertEq(0, t_.nbLines());
    }
    @Test
    public void transposeRef2Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        Matrix t_ = mat_.transposeRef();
        assertEq(2, t_.nbCols());
        assertEq(2, t_.nbLines());
        assertEq(Rate.one(), t_.cell(0, 0));
        assertEq(Rate.one(), t_.cell(0, 1));
        assertEq(Rate.zero(), t_.cell(1, 0));
        assertEq(Rate.zero(), t_.cell(1, 1));
    }
    @Test
    public void transpose1Test() {
        Matrix mat_ = new Matrix();
        Matrix t_ = mat_.transpose();
        assertEq(0, t_.nbCols());
        assertEq(0, t_.nbLines());
    }
    @Test
    public void transpose2Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        Matrix t_ = mat_.transpose();
        assertEq(2, t_.nbCols());
        assertEq(2, t_.nbLines());
        assertEq(Rate.one(), t_.cell(0, 0));
        assertEq(Rate.one(), t_.cell(0, 1));
        assertEq(Rate.zero(), t_.cell(1, 0));
        assertEq(Rate.zero(), t_.cell(1, 1));
    }
    @Test
    public void power() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        Matrix t_ = mat_.power(new LgInt(2));
        assertEq(2, t_.nbCols());
        assertEq(2, t_.nbLines());
        assertEq(Rate.one(), t_.cell(0, 0));
        assertEq(Rate.zero(), t_.cell(0, 1));
        assertEq(Rate.one(), t_.cell(1, 0));
        assertEq(Rate.zero(), t_.cell(1, 1));
    }
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
        assertEq(new Rate(1),t_.getRates().get(0).getValue());
        assertEq(2,t_.getRates().get(0).getDegree());
        assertEq(2,t_.getRates().get(0).getSpace());
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
        assertEq(new Rate(1),t_.getRates().get(0).getValue());
        assertEq(2,t_.getRates().get(0).getDegree());
        assertEq(1,t_.getRates().get(0).getSpace());
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
        assertEq(new Rate(-1),t_.getRates().get(0).getValue());
        assertEq(1,t_.getRates().get(0).getDegree());
        assertEq(1,t_.getRates().get(0).getSpace());
        assertEq(new Rate(1),t_.getRates().get(1).getValue());
        assertEq(1,t_.getRates().get(1).getDegree());
        assertEq(1,t_.getRates().get(1).getSpace());
    }
//    @Test
//    public void ownVects1Test() {
//        Matrix mat_ = new Matrix();
//        Vect vect_ = new Vect();
//        vect_.add(Rate.one());
//        vect_.add(Rate.zero());
//        mat_.addLine(vect_);
//        vect_ = new Vect();
//        vect_.add(Rate.zero());
//        vect_.add(Rate.one());
//        mat_.addLine(vect_);
//        Matrix m_ = mat_.ownVects(Rate.one(),2);
//        assertEq(2, m_.nbLines());
//        assertEq(2, m_.nbCols());
//        assertEq(Rate.one(), m_.cell(0, 0));
//        assertEq(Rate.zero(), m_.cell(0, 1));
//        assertEq(Rate.zero(), m_.cell(1, 0));
//        assertEq(Rate.one(), m_.cell(1, 1));
//    }
//    @Test
//    public void ownVects2Test() {
//        Matrix mat_ = new Matrix();
//        Vect vect_ = new Vect();
//        vect_.add(Rate.one());
//        vect_.add(Rate.zero());
//        mat_.addLine(vect_);
//        vect_ = new Vect();
//        vect_.add(Rate.zero());
//        vect_.add(Rate.minusOne());
//        mat_.addLine(vect_);
//        Matrix m_ = mat_.ownVects(Rate.one(),1);
//        assertEq(1, m_.nbLines());
//        assertEq(2, m_.nbCols());
//        assertEq(Rate.zero(), m_.cell(0, 0));
//        assertEq(new Rate(-2), m_.cell(0, 1));
//    }
//    @Test
//    public void ownVects3Test() {
//        Matrix mat_ = new Matrix();
//        Vect vect_ = new Vect();
//        vect_.add(Rate.one());
//        vect_.add(Rate.zero());
//        mat_.addLine(vect_);
//        vect_ = new Vect();
//        vect_.add(Rate.zero());
//        vect_.add(Rate.minusOne());
//        mat_.addLine(vect_);
//        Matrix m_ = mat_.ownVects(Rate.minusOne(),1);
//        assertEq(1, m_.nbLines());
//        assertEq(2, m_.nbCols());
//        assertEq(new Rate(2), m_.cell(0, 0));
//        assertEq(Rate.zero(), m_.cell(0, 1));
//    }
//    @Test
//    public void passMatTest() {
//        Matrix mat_ = new Matrix();
//        Vect vect_ = new Vect();
//        vect_.add(Rate.one());
//        vect_.add(Rate.zero());
//        mat_.addLine(vect_);
//        vect_ = new Vect();
//        vect_.add(Rate.zero());
//        vect_.add(Rate.one());
//        mat_.addLine(vect_);
//        Matrix m_ = mat_.passMat();
//        assertEq(2, m_.nbLines());
//        assertEq(2, m_.nbCols());
//        assertEq(Rate.one(), m_.cell(0, 0));
//        assertEq(Rate.zero(), m_.cell(0, 1));
//        assertEq(Rate.zero(), m_.cell(1, 0));
//        assertEq(Rate.one(), m_.cell(1, 1));
//    }
    @Test
    public void detInv1Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(Rate.one());
        mat_.addLine(vect_);
        Rate d_ = mat_.detInv();
        assertEq(Rate.one(), d_);
    }
    @Test
    public void detInv2Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        Rate d_ = mat_.detInv();
        assertEq(Rate.zero(), d_);
    }
    @Test
    public void detSquareIntPartTest() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(Rate.one());
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(Rate.one());
        mat_.addLine(vect_);
        LgInt d_ = mat_.detSquareIntPart();
        assertEq(LgInt.one(), d_);
    }
    @Test
    public void trace1Test() {
        Matrix mat_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(new Rate(2));
        vect_.add(Rate.zero());
        mat_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(new Rate(3));
        mat_.addLine(vect_);
        Rate d_ = mat_.trace();
        assertEq(new Rate(5), d_);
    }
    @Test
    public void trace2Test() {
        Matrix mat_ = new Matrix();
        Rate d_ = mat_.trace();
        assertEq(new Rate(0), d_);
    }
    @Test
    public void eq1Test() {
        Matrix m_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(new Rate(2));
        vect_.add(Rate.zero());
        m_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(new Rate(3));
        m_.addLine(vect_);
        Matrix n_ = new Matrix();
        vect_ = new Vect();
        vect_.add(new Rate(2));
        vect_.add(Rate.zero());
        n_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(new Rate(3));
        n_.addLine(vect_);
        assertTrue(m_.eq(n_));
    }
    @Test
    public void eq2Test() {
        Matrix m_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(new Rate(3));
        vect_.add(Rate.zero());
        m_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(new Rate(2));
        m_.addLine(vect_);
        Matrix n_ = new Matrix();
        vect_ = new Vect();
        vect_.add(new Rate(2));
        vect_.add(Rate.zero());
        n_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(new Rate(3));
        n_.addLine(vect_);
        assertTrue(!m_.eq(n_));
    }
    @Test
    public void eq3Test() {
        Matrix m_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(new Rate(3));
        vect_.add(Rate.zero());
        m_.addLine(vect_);
        Matrix n_ = new Matrix();
        vect_ = new Vect();
        vect_.add(new Rate(2));
        vect_.add(Rate.zero());
        n_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(new Rate(3));
        n_.addLine(vect_);
        assertTrue(!m_.eq(n_));
    }
    @Test
    public void eq4Test() {
        Matrix m_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(new Rate(3));
        m_.addLine(vect_);
        Matrix n_ = new Matrix();
        vect_ = new Vect();
        vect_.add(new Rate(2));
        vect_.add(new Rate(3));
        n_.addLine(vect_);
        assertTrue(!m_.eq(n_));
    }
    @Test
    public void eq5Test() {
        Matrix m_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(new Rate(3));
        m_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.zero());
        m_.addLine(vect_);
        Matrix n_ = new Matrix();
        vect_ = new Vect();
        vect_.add(new Rate(2));
        vect_.add(Rate.zero());
        n_.addLine(vect_);
        assertTrue(!m_.eq(n_));
    }
    @Test
    public void eq6Test() {
        Matrix m_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(new Rate(3));
        m_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.zero());
        m_.addLine(vect_);
        Matrix n_ = new Matrix();
        vect_ = new Vect();
        vect_.add(new Rate(2));
        vect_.add(Rate.zero());
        n_.addLine(vect_);
        assertTrue(!Matrix.eq(m_,n_));
    }
    @Test
    public void eq7Test() {
        Matrix m_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(new Rate(2));
        vect_.add(Rate.zero());
        m_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(new Rate(3));
        m_.addLine(vect_);
        Matrix n_ = new Matrix();
        vect_ = new Vect();
        vect_.add(new Rate(2));
        vect_.add(Rate.zero());
        n_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(Rate.zero());
        vect_.add(new Rate(3));
        n_.addLine(vect_);
        assertTrue(Matrix.eq(m_,n_));
    }
    @Test
    public void new_Matrix_Test() {
        Matrix m_ = Matrix.newMatrix("1 2;3 4");
        Matrix n_ = new Matrix();
        Vect vect_ = new Vect();
        vect_.add(new Rate(1));
        vect_.add(new Rate(2));
        n_.addLine(vect_);
        vect_ = new Vect();
        vect_.add(new Rate(3));
        vect_.add(new Rate(4));
        n_.addLine(vect_);
        assertTrue(m_.eq(n_));
    }
    @Test
    public void display1Test() {
        Matrix m_ = Matrix.newMatrix("1 2;3 4");
        assertEq("1 2;3 4",m_.display());
    }
    @Test
    public void display2Test() {
        Matrix m_ = new Matrix();
        assertEq("",m_.display());
    }
    @Test
    public void display3Test() {
        Matrix m_ = new Matrix();
        m_.addLine(new Vect());
        assertEq("",m_.display());
    }
}
