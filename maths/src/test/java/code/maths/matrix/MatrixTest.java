package code.maths.matrix;
import static code.maths.EquallableMathUtil.assertEq;
import static code.util.opers.EquallableUtil.assertEq;

import org.junit.Test;

import code.maths.Rate;

@SuppressWarnings("static-method")
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
}
