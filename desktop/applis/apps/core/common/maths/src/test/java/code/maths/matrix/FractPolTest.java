package code.maths.matrix;

import code.maths.EquallableMathUtil;
import code.maths.LgInt;
import code.maths.Rate;
import org.junit.Test;

public class FractPolTest extends EquallableMathUtil {
    @Test
    public void zeroTest() {
        assertTrue(Polynom.zero().eq(FractPol.zero().getNumerator()));
        assertTrue(Polynom.one().eq(FractPol.zero().getDenominator()));
    }
    @Test
    public void oneTest() {
        assertTrue(Polynom.one().eq(FractPol.one().getNumerator()));
        assertTrue(Polynom.one().eq(FractPol.one().getDenominator()));
    }

    @Test
    public void new_FractPol_1test() {
        Polynom p_ = new Polynom();
        p_.add(new Rate(2));
        assertTrue(p_.eq(new FractPol(p_).getNumerator()));
        assertTrue(Polynom.one().eq(new FractPol(p_).getDenominator()));
    }

    @Test
    public void new_FractPol_2test() {
        Polynom p_ = new Polynom();
        p_.add(new Rate(2));
        assertTrue(p_.eq(new FractPol(p_,Polynom.one()).getNumerator()));
        assertTrue(Polynom.one().eq(new FractPol(p_,Polynom.one()).getDenominator()));
    }

    @Test
    public void new_FractPol_3test() {
        Polynom p_ = new Polynom();
        p_.add(new Rate(2));
        assertTrue(p_.eq(new FractPol(new FractPol(p_,Polynom.one())).getNumerator()));
        assertTrue(Polynom.one().eq(new FractPol(new FractPol(p_,Polynom.one())).getDenominator()));
    }

    @Test
    public void new_FractPol_4test() {
        FractPol f_ = FractPol.newFract("1 2//3 4");
        Polynom n_ = new Polynom();
        n_.add(new Rate(3,2));
        n_.add(new Rate(3));
        Polynom d_ = new Polynom();
        d_.add(new Rate(9,2));
        d_.add(new Rate(6));
        assertTrue(n_.eq(f_.getNumerator()));
        assertTrue(d_.eq(f_.getDenominator()));
    }

    @Test
    public void eq1Test() {
        FractPol f_ = FractPol.newFract("1 2//3 4");
        FractPol g_ = FractPol.newFract("1 2//3 4");
        Polynom n_ = new Polynom();
        n_.add(new Rate(1));
        n_.add(new Rate(2));
        Polynom d_ = new Polynom();
        d_.add(new Rate(3));
        d_.add(new Rate(4));
        assertTrue(f_.eq(g_));
        assertTrue(g_.eq(f_));
    }
    @Test
    public void isZero1Test() {
        assertTrue(FractPol.zero().isZero());
    }
    @Test
    public void isZero2Test() {
        assertTrue(!FractPol.one().isZero());
    }
    @Test
    public void plus1Test() {
        FractPol f_ = new FractPol(Polynom.one());
        FractPol g_ = new FractPol(Polynom.one());
        assertTrue(new FractPol(new Polynom(new Rate(2))).eq(FractPol.plus(f_,g_)));
    }
    @Test
    public void plus2Test() {
        FractPol f_ = new FractPol(Polynom.one());
        FractPol g_ = new FractPol(Polynom.zero());
        assertTrue(new FractPol(new Polynom(new Rate(1))).eq(FractPol.plus(f_,g_)));
    }
    @Test
    public void oppositeTest() {
        FractPol f_ = new FractPol(Polynom.one());
        assertTrue(new FractPol(new Polynom(new Rate(-1))).eq(f_.opposNb()));
    }
    @Test
    public void minus1Test() {
        FractPol f_ = new FractPol(Polynom.one());
        FractPol g_ = new FractPol(Polynom.one());
        assertTrue(new FractPol(new Polynom(new Rate(0))).eq(FractPol.minus(f_,g_)));
    }
    @Test
    public void minus2Test() {
        FractPol f_ = new FractPol(Polynom.one());
        FractPol g_ = new FractPol(Polynom.zero());
        assertTrue(new FractPol(new Polynom(new Rate(1))).eq(FractPol.minus(f_,g_)));
    }
    @Test
    public void multiply1Test() {
        FractPol f_ = new FractPol(Polynom.one());
        FractPol g_ = new FractPol(Polynom.zero());
        assertTrue(new FractPol(new Polynom(new Rate(0))).eq(FractPol.multiply(f_,g_)));
    }
    @Test
    public void multiply2Test() {
        FractPol f_ = new FractPol(Polynom.zero());
        FractPol g_ = new FractPol(Polynom.one());
        assertTrue(new FractPol(new Polynom(new Rate(0))).eq(FractPol.multiply(f_,g_)));
    }
    @Test
    public void multiply3Test() {
        FractPol f_ = new FractPol(Polynom.zero());
        FractPol g_ = new FractPol(Polynom.zero());
        assertTrue(new FractPol(new Polynom(new Rate(0))).eq(FractPol.multiply(f_,g_)));
    }
    @Test
    public void multiply4Test() {
        FractPol f_ = new FractPol(Polynom.one());
        FractPol g_ = new FractPol(Polynom.one());
        assertTrue(new FractPol(new Polynom(new Rate(1))).eq(FractPol.multiply(f_,g_)));
    }
    @Test
    public void divide1Test() {
        FractPol f_ = new FractPol(Polynom.one());
        FractPol g_ = new FractPol(Polynom.one());
        assertTrue(new FractPol(new Polynom(new Rate(1))).eq(FractPol.divide(f_,g_)));
    }
    @Test
    public void power1Test() {
        FractPol f_ = new FractPol(Polynom.one());
        assertTrue(new FractPol(new Polynom(new Rate(1))).eq(f_.powNb(new LgInt(1))));
    }
    @Test
    public void power2Test() {
        FractPol f_ = new FractPol(Polynom.one());
        assertTrue(new FractPol(new Polynom(new Rate(1))).eq(f_.powNb(new LgInt(-1))));
    }
    @Test
    public void display1Test() {
        FractPol f_ = new FractPol(Polynom.one());
        assertEq("1",f_.display());
    }
    @Test
    public void display2Test() {
        Polynom d_ = new Polynom();
        d_.add(Rate.one());
        d_.add(Rate.zero());
        FractPol f_ = new FractPol(Polynom.one(), d_);
        assertEq("1//1 0",f_.display());
    }
    @Test
    public void intPartTest() {
        FractPol f_ = new FractPol(Polynom.one());
        assertTrue(Polynom.one().eq(f_.intPart()));
    }
    @Test
    public void removeNbTest() {
        FractPol f_ = new FractPol(Polynom.one());
        FractPol g_ = new FractPol(Polynom.zero());
        f_.removeNb(g_);
        assertTrue(new FractPol(new Polynom(new Rate(1))).eq(f_));
    }
    @Test
    public void affectTest() {
        FractPol f_ = new FractPol(Polynom.one());
        FractPol g_ = new FractPol(Polynom.zero());
        f_.affect(g_);
        assertTrue(new FractPol(new Polynom(new Rate(0))).eq(f_));
    }
}
