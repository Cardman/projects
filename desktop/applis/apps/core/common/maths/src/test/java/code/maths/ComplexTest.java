package code.maths;

import org.junit.Test;

public class ComplexTest extends EquallableMathUtil {
    @Test
    public void new_Complex_1test() {
        Complex c_ = new Complex();
        assertEq(new Rate(0), c_.getReal());
        assertEq(new Rate(0), c_.getImag());
    }
    @Test
    public void new_Complex_2test() {
        Complex c_ = new Complex(Rate.one());
        assertEq(new Rate(1), c_.getReal());
        assertEq(new Rate(0), c_.getImag());
    }
    @Test
    public void new_Complex_3test() {
        Complex c_ = new Complex(Rate.one(),Rate.one());
        assertEq(new Rate(1), c_.getReal());
        assertEq(new Rate(1), c_.getImag());
    }
    @Test
    public void new_Complex_4test() {
        Complex c_ = new Complex(Rate.one(),Rate.one());
        Complex d_ = new Complex(c_);
        assertEq(new Rate(1), d_.getReal());
        assertEq(new Rate(1), d_.getImag());
        assertNotSame(c_.getImag(),d_.getImag());
        assertNotSame(c_.getReal(),d_.getReal());
    }
    @Test
    public void newFractTest() {
        Complex c_ = Complex.newFract("2:3");
        assertEq(new Rate(2), c_.getReal());
        assertEq(new Rate(3), c_.getImag());
    }
    @Test
    public void eq1Test() {
        Complex c_ = new Complex(new Rate(2),Rate.one());
        Complex d_ = new Complex(new Rate(2),Rate.one());
        assertTrue(Complex.eq(c_,d_));
    }
    @Test
    public void eq2Test() {
        Complex c_ = new Complex(new Rate(2),new Rate(2));
        Complex d_ = new Complex(new Rate(2),Rate.one());
        assertTrue(!Complex.eq(c_,d_));
    }
    @Test
    public void eq3Test() {
        Complex c_ = new Complex(Rate.one(),Rate.one());
        Complex d_ = new Complex(new Rate(2),Rate.one());
        assertTrue(!Complex.eq(c_,d_));
    }
    @Test
    public void displayTest() {
        Complex c_ = new Complex(new Rate(2),Rate.one());
        assertEq("2:1",c_.display());
    }
    @Test
    public void conj1Test() {
        Complex c_ = new Complex(new Rate(2),Rate.zero());
        assertEq(new Complex(new Rate(2),Rate.zero()),c_.conjug());
    }
    @Test
    public void conj2Test() {
        Complex c_ = new Complex(new Rate(2),Rate.one());
        assertEq(new Complex(new Rate(2),new Rate(-1)),c_.conjug());
    }
    @Test
    public void squareModTest() {
        Complex c_ = new Complex(new Rate(2),Rate.one());
        assertEq(new Rate(5),c_.squareMod());
    }
    @Test
    public void oppositeTest() {
        Complex c_ = new Complex(new Rate(2),Rate.one());
        assertEq(new Complex(new Rate(-2),new Rate(-1)),c_.opposite());
    }
    @Test
    public void addTest() {
        Complex c_ = new Complex(new Rate(2),Rate.one());
        Complex d_ = new Complex(new Rate(3),new Rate(3));
        assertEq(new Complex(new Rate(5),new Rate(4)),c_.add(d_));
    }
    @Test
    public void minusTest() {
        Complex c_ = new Complex(new Rate(2),Rate.one());
        Complex d_ = new Complex(new Rate(3),new Rate(3));
        assertEq(new Complex(new Rate(-1),new Rate(-2)),c_.minus(d_));
    }
    @Test
    public void multiplyTest() {
        Complex c_ = new Complex(new Rate(2),Rate.one());
        Complex d_ = new Complex(new Rate(4),new Rate(3));
        assertEq(new Complex(new Rate(5),new Rate(10)),c_.multiply(d_));
    }
    @Test
    public void invTest() {
        Complex d_ = new Complex(new Rate(4),new Rate(3));
        assertEq(new Complex(new Rate(4,25),new Rate(-3,25)),d_.inv());
    }
    @Test
    public void divideTest() {
        Complex c_ = new Complex(new Rate(2),Rate.one());
        Complex d_ = new Complex(new Rate(4),new Rate(3));
        assertEq(new Complex(new Rate(11,25),new Rate(-2,25)),c_.divide(d_));
    }
}
