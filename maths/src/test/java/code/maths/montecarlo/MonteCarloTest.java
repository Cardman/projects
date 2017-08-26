package code.maths.montecarlo;
import static code.maths.EquallableMathUtil.assertEq;
import static code.util.opers.EquallableUtil.assertEq;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.maths.LgInt;
import code.maths.Rate;
import code.maths.exceptions.BadDivisionException;
import code.maths.montecarlo.AbMonteCarlo;
import code.maths.montecarlo.MonteCarloBoolean;
import code.maths.montecarlo.MonteCarloNb;
import code.util.EqList;

@SuppressWarnings("static-method")
public class MonteCarloTest {

    @Test
    public void new_MonteCarlo_1Test() {
        MonteCarloNb<Integer> law_ = new MonteCarloNb<Integer>();
        assertEq(0, law_.events().size());
        assertTrue(!law_.isValid());
    }

    @Test
    public void new_MonteCarlo_add2Test() {
        MonteCarloNb<Integer> law_ = new MonteCarloNb<Integer>();
        law_.addEvent(2, new LgInt(1));
        assertEq(1,law_.events().size());
        assertTrue(law_.events().containsObj(2));
        assertTrue(law_.isValid());
    }

    @Test
    public void deleteZeroEvents1Test() {
        MonteCarloNb<Integer> law_ = new MonteCarloNb<Integer>();
        law_.addEvent(2, new LgInt(1));
        law_.addEvent(3, new LgInt(0));
        law_.addEvent(4, new LgInt(-1));
        law_.addEvent(5, new LgInt(1));
        law_.deleteZeroEvents();
        assertEq(2,law_.events().size());
        assertTrue(law_.events().containsObj(2));
        assertTrue(law_.events().containsObj(5));
        assertTrue(law_.isValid());
    }

    @Test
    public void sum1Test() {
        MonteCarloNb<Integer> law_ = new MonteCarloNb<Integer>();
        law_.addEvent(2, new LgInt(1));
        assertEq(new LgInt(1),law_.sum());
        law_.addEvent(3, new LgInt(5));
        assertEq(new LgInt(6),law_.sum());
    }

    @Test
    public void rate1Test() {
        MonteCarloNb<Integer> law_ = new MonteCarloNb<Integer>();
        law_.addEvent(2, new LgInt(1));
        assertEq(new LgInt(1),law_.rate(2));
        law_.addEvent(3, new LgInt(5));
        assertEq(new LgInt(5),law_.rate(3));
    }

    @Test
    public void valid1Test() {
        MonteCarloNb<Integer> law_ = new MonteCarloNb<Integer>();
        assertTrue(!law_.isValid());
        law_.addEvent(2, new LgInt(0));
        assertTrue(!law_.isValid());
        law_ = new MonteCarloNb<Integer>();
        law_.addEvent(2, new LgInt(-1));
        law_.addEvent(3, new LgInt(2));
        assertTrue(!law_.isValid());
        law_ = new MonteCarloNb<Integer>();
        law_.addEvent(2, new LgInt(1));
        law_.addEvent(3, new LgInt(2));
        assertTrue(law_.isValid());
    }

    @Test
    public void booleanLaw1Test() {
        Rate rate_ = new Rate("-1");
        MonteCarloBoolean law_ = AbMonteCarlo.booleanLaw(rate_);
        assertEq(1,law_.events().size());
        assertTrue(law_.events().containsObj(false));
    }

    @Test
    public void booleanLaw2Test() {
        Rate rate_ = new Rate("0");
        MonteCarloBoolean law_ = AbMonteCarlo.booleanLaw(rate_);
        assertEq(1,law_.events().size());
        assertTrue(law_.events().containsObj(false));
    }

    @Test
    public void booleanLaw3Test() {
        Rate rate_ = new Rate("2");
        MonteCarloBoolean law_ = AbMonteCarlo.booleanLaw(rate_);
        assertEq(1,law_.events().size());
        assertTrue(law_.events().containsObj(true));
    }

    @Test
    public void booleanLaw4Test() {
        Rate rate_ = new Rate("1/4");
        MonteCarloBoolean law_ = AbMonteCarlo.booleanLaw(rate_);
        assertEq(2,law_.events().size());
        assertTrue(law_.events().containsObj(true));
        assertTrue(law_.events().containsObj(false));
        assertEq(new LgInt("1"),law_.rate(true));
        assertEq(new LgInt("3"),law_.rate(false));
    }

    @Test
    public void randomNumber1Test() {
        EqList<LgInt> list_ = new EqList<LgInt>();
        list_.add(LgInt.zero());
        list_.add(LgInt.zero());
        list_.add(LgInt.zero());
        list_.add(LgInt.zero());
        assertEq(LgInt.zero(), AbMonteCarlo.randomNumber(list_));
    }

    @Test
    public void randomNumber2Test() {
        EqList<LgInt> list_ = new EqList<LgInt>();
        list_.add(LgInt.zero());
        list_.add(LgInt.zero());
        list_.add(LgInt.one());
        list_.add(LgInt.zero());
        assertEq(AbMonteCarlo.getMaxRandom(), AbMonteCarlo.randomNumber(list_));
    }

    @Test
    public void randomNumber3Test() {
        EqList<LgInt> list_ = new EqList<LgInt>();
        LgInt int_ = new LgInt(Long.MAX_VALUE);
        list_.add(LgInt.zero());
        list_.add(LgInt.zero());
        list_.add(LgInt.zero());
        list_.add(int_);
        assertEq(int_, AbMonteCarlo.randomNumber(list_));
    }

    @Test
    public void editNumber1Test() {
        MonteCarloNb<Integer> law_ = new MonteCarloNb<Integer>();
        law_.addEvent(2, new LgInt(1));
        assertEq(2, law_.editNumber().intValue());
    }

    @Test
    public void editNumber2Test() {
        MonteCarloNb<Integer> law_ = new MonteCarloNb<Integer>();
        law_.addEvent(2, new LgInt(1));
        assertEq(2, law_.editNumber(LgInt.zero()).intValue());
        assertEq(2, law_.editNumber(LgInt.one()).intValue());
        assertEq(2, law_.editNumber(new LgInt(2)).intValue());
    }

    @Test
    public void editNumber3Test() {
        MonteCarloNb<Integer> law_ = new MonteCarloNb<Integer>();
        law_.addEvent(2, new LgInt(1));
        law_.addEvent(3, new LgInt(1));
        assertEq(2, law_.editNumber(LgInt.zero()).intValue());
        assertEq(3, law_.editNumber(LgInt.one()).intValue());
        assertEq(2, law_.editNumber(new LgInt(2)).intValue());
        assertEq(3, law_.editNumber(new LgInt(3)).intValue());
    }

    @Test
    public void editNumber4Test() {
        MonteCarloNb<Integer> law_ = new MonteCarloNb<Integer>();
        law_.addEvent(2, new LgInt(3));
        law_.addEvent(3, new LgInt(5));
        assertEq(2, law_.editNumber(LgInt.zero()).intValue());
        assertEq(2, law_.editNumber(LgInt.one()).intValue());
        assertEq(2, law_.editNumber(new LgInt(2)).intValue());
        assertEq(3, law_.editNumber(new LgInt(3)).intValue());
        assertEq(3, law_.editNumber(new LgInt(4)).intValue());
        assertEq(3, law_.editNumber(new LgInt(5)).intValue());
        assertEq(3, law_.editNumber(new LgInt(6)).intValue());
        assertEq(3, law_.editNumber(new LgInt(7)).intValue());
        assertEq(2, law_.editNumber(new LgInt(8)).intValue());
    }

    @Test
    public void editNumber5Test() {
        MonteCarloNb<Integer> law_ = new MonteCarloNb<Integer>();
        law_.addEvent(2, new LgInt(0));
        law_.addEvent(3, new LgInt(5));
        assertEq(3, law_.editNumber(LgInt.zero()).intValue());
        assertEq(3, law_.editNumber(LgInt.one()).intValue());
        assertEq(3, law_.editNumber(new LgInt(2)).intValue());
        assertEq(3, law_.editNumber(new LgInt(3)).intValue());
        assertEq(3, law_.editNumber(new LgInt(4)).intValue());
        assertEq(3, law_.editNumber(new LgInt(5)).intValue());
        assertEq(3, law_.editNumber(new LgInt(6)).intValue());
        assertEq(3, law_.editNumber(new LgInt(7)).intValue());
        assertEq(3, law_.editNumber(new LgInt(8)).intValue());
    }

    @Test
    public void editNumber6Test() {
        MonteCarloNb<Integer> law_ = new MonteCarloNb<Integer>();
        law_.addEvent(3, new LgInt(5));
        law_.addEvent(2, new LgInt(0));
        assertEq(3, law_.editNumber(LgInt.zero()).intValue());
        assertEq(3, law_.editNumber(LgInt.one()).intValue());
        assertEq(3, law_.editNumber(new LgInt(2)).intValue());
        assertEq(3, law_.editNumber(new LgInt(3)).intValue());
        assertEq(3, law_.editNumber(new LgInt(4)).intValue());
        assertEq(3, law_.editNumber(new LgInt(5)).intValue());
        assertEq(3, law_.editNumber(new LgInt(6)).intValue());
        assertEq(3, law_.editNumber(new LgInt(7)).intValue());
        assertEq(3, law_.editNumber(new LgInt(8)).intValue());
    }

    @Test
    public void editNumber7Test() {
        MonteCarloNb<Integer> law_ = new MonteCarloNb<Integer>();
        law_.addEvent(3, new LgInt(5));
        law_.addEvent(2, new LgInt(0));
        law_.addEvent(4, new LgInt(3));
        assertEq(3, law_.editNumber(LgInt.zero()).intValue());
        assertEq(3, law_.editNumber(LgInt.one()).intValue());
        assertEq(3, law_.editNumber(new LgInt(2)).intValue());
        assertEq(3, law_.editNumber(new LgInt(3)).intValue());
        assertEq(3, law_.editNumber(new LgInt(4)).intValue());
        assertEq(4, law_.editNumber(new LgInt(5)).intValue());
        assertEq(4, law_.editNumber(new LgInt(6)).intValue());
        assertEq(4, law_.editNumber(new LgInt(7)).intValue());
        assertEq(3, law_.editNumber(new LgInt(8)).intValue());
    }

    @Test(expected=BadDivisionException.class)
    public void editNumber1FailTest() {
        MonteCarloNb<Integer> law_ = new MonteCarloNb<Integer>();
        law_.addEvent(3, new LgInt(0));
        law_.addEvent(2, new LgInt(0));
        law_.editNumber(new LgInt(0));
    }

    @Test(expected=BadDivisionException.class)
    public void editNumber2FailTest() {
        MonteCarloNb<Integer> law_ = new MonteCarloNb<Integer>();
        law_.addEvent(2, new LgInt(0));
        law_.editNumber(new LgInt(0));
    }

    @Test(expected=BadDivisionException.class)
    public void editNumber3FailTest() {
        MonteCarloNb<Integer> law_ = new MonteCarloNb<Integer>();
        assertNull(law_.editNumber(new LgInt(0)));
    }

    @Test
    public void editNumber9Test() {
        MonteCarloNb<Integer> law_ = new MonteCarloNb<Integer>();
        assertNull(law_.editNumber());
    }

    @Test
    public void editNumber10Test() {
        MonteCarloNb<Integer> law_ = new MonteCarloNb<Integer>();
        law_.addEvent(3, new LgInt(5));
        assertEq(3, law_.editNumber().intValue());
    }

    @Test
    public void editNumber11Test() {
        MonteCarloNb<Integer> law_ = new MonteCarloNb<Integer>();
        law_.addEvent(2, new LgInt(0));
        law_.addEvent(3, new LgInt(5));
        assertEq(3, law_.editNumber().intValue());
    }
}
