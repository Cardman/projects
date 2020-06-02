package code.maths.matrix;

import code.maths.Rate;
import org.junit.Test;

import static code.maths.EquallableMathUtil.assertEq;
import static org.junit.Assert.assertTrue;

public class VectTest {
    @Test
    public void scaleTest() {
        Vect v_ = new Vect();
        v_.add(Rate.zero());
        v_.setNb(0,Rate.one());
        assertEq(Rate.one(),v_.scale(v_));
    }
    @Test
    public void eq1Test() {
        Vect v_ = new Vect();
        v_.add(Rate.one());
        assertTrue(v_.eq(v_));
    }
    @Test
    public void eq2Test() {
        Vect v_ = new Vect();
        v_.add(Rate.one());
        Vect w_ = new Vect();
        w_.add(Rate.one());
        w_.add(Rate.one());
        assertTrue(!v_.eq(w_));
    }
}
