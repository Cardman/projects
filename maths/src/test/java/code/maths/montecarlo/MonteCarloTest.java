package code.maths.montecarlo;
import static code.maths.EquallableMathUtil.assertEq;
import static org.junit.Assert.assertTrue;

import code.util.CollCapacity;
import org.junit.Test;

import code.maths.LgInt;
import code.maths.Rate;
import code.util.EqList;


public class MonteCarloTest {

    @Test
    public void new_MonteCarlo_1Test() {
        MonteCarloNb law_ = new MonteCarloNb();
        assertEq(0, law_.events().size());
        assertTrue(!law_.isValid());
    }

    @Test
    public void new_MonteCarlo_add2Test() {
        MonteCarloNb law_ = new MonteCarloNb();
        law_.addEvent(2L, new LgInt(1));
        assertEq(1,law_.events().size());
        assertTrue(law_.getLaw().contains(2L));
        assertTrue(law_.isValid());
    }

    @Test
    public void deleteZeroEvents1Test() {
        MonteCarloNb law_ = new MonteCarloNb();
        law_.addEvent(2L, new LgInt(1));
        law_.addEvent(3L, new LgInt(0));
        law_.addEvent(4L, new LgInt(-1));
        law_.addEvent(5L, new LgInt(1));
        law_.deleteZeroEvents();
        assertEq(2,law_.events().size());
        assertTrue(law_.getLaw().contains(2L));
        assertTrue(law_.getLaw().contains(5L));
        assertTrue(law_.isValid());
    }

    @Test
    public void sum1Test() {
        MonteCarloNb law_ = new MonteCarloNb();
        law_.addEvent(2L, new LgInt(1));
        assertEq(new LgInt(1),law_.sum());
        law_.addEvent(3L, new LgInt(5));
        assertEq(new LgInt(6),law_.sum());
    }

    @Test
    public void rate1Test() {
        MonteCarloNb law_ = new MonteCarloNb();
        law_.addEvent(2L, new LgInt(1));
        assertEq(new LgInt(1),law_.rate(2L));
        law_.addEvent(3L, new LgInt(5));
        assertEq(new LgInt(5),law_.rate(3L));
    }

    @Test
    public void valid1Test() {
        MonteCarloNb law_ = new MonteCarloNb();
        assertTrue(!law_.isValid());
        law_.addEvent(2L, new LgInt(0));
        assertTrue(!law_.isValid());
        law_ = new MonteCarloNb();
        law_.addEvent(2L, new LgInt(-1));
        law_.addEvent(3L, new LgInt(2));
        assertTrue(!law_.isValid());
        law_ = new MonteCarloNb();
        law_.addEvent(2L, new LgInt(1));
        law_.addEvent(3L, new LgInt(2));
        assertTrue(law_.isValid());
    }

    @Test
    public void booleanLaw1Test() {
        Rate rate_ = new Rate("-1");
        MonteCarloBoolean law_ = AbMonteCarlo.booleanLaw(rate_);
        assertEq(1,law_.events().size());
        assertTrue(law_.getLaw().contains(false));
    }

    @Test
    public void booleanLaw2Test() {
        Rate rate_ = new Rate("0");
        MonteCarloBoolean law_ = AbMonteCarlo.booleanLaw(rate_);
        assertEq(1,law_.events().size());
        assertTrue(law_.getLaw().contains(false));
    }

    @Test
    public void booleanLaw3Test() {
        Rate rate_ = new Rate("2");
        MonteCarloBoolean law_ = AbMonteCarlo.booleanLaw(rate_);
        assertEq(1,law_.events().size());
        assertTrue(law_.getLaw().contains(true));
    }

    @Test
    public void booleanLaw4Test() {
        Rate rate_ = new Rate("1/4");
        MonteCarloBoolean law_ = AbMonteCarlo.booleanLaw(rate_);
        assertEq(2,law_.events().size());
        assertTrue(law_.getLaw().contains(true));
        assertTrue(law_.getLaw().contains(false));
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
        assertEq(LgInt.zero(), AbMonteCarlo.randomNumberSe(list_, new LgInt(8)));
    }

    @Test
    public void randomNumber2Test() {
        EqList<LgInt> list_ = new EqList<LgInt>();
        list_.add(LgInt.zero());
        list_.add(LgInt.zero());
        list_.add(LgInt.one());
        list_.add(LgInt.zero());
        assertEq(new LgInt(1024), AbMonteCarlo.randomNumberSe(list_, new LgInt(1024)));
    }

    @Test
    public void randomNumber3Test() {
        EqList<LgInt> list_ = new EqList<LgInt>();
        LgInt int_ = new LgInt(Long.MAX_VALUE);
        list_.add(LgInt.zero());
        list_.add(LgInt.zero());
        list_.add(LgInt.zero());
        list_.add(int_);
        assertEq(int_, AbMonteCarlo.randomNumberSe(list_, new LgInt(8)));
    }

    @Test
    public void editNumber1Test() {
        MonteCarloNb law_ = new MonteCarloNb();
        law_.addEvent(2L, new LgInt(1));
        assertEq(2, law_.editNumber(new LgInt(8)));
    }

    @Test
    public void editNumber2Test() {
        MonteCarloNb law_ = new MonteCarloNb();
        law_.addEvent(2L, new LgInt(1));
        assertEq(2, law_.editNumberSeed(LgInt.zero()));
        assertEq(2, law_.editNumberSeed(LgInt.one()));
        assertEq(2, law_.editNumberSeed(new LgInt(2)));
    }

    @Test
    public void editNumber3Test() {
        MonteCarloNb law_ = new MonteCarloNb();
        law_.addEvent(2L, new LgInt(1));
        law_.addEvent(3L, new LgInt(1));
        assertEq(2, law_.editNumberSeed(LgInt.zero()));
        assertEq(3, law_.editNumberSeed(LgInt.one()));
        assertEq(2, law_.editNumberSeed(new LgInt(2)));
        assertEq(3, law_.editNumberSeed(new LgInt(3)));
    }

    @Test
    public void editNumber4Test() {
        MonteCarloNb law_ = new MonteCarloNb();
        law_.addEvent(2L, new LgInt(3));
        law_.addEvent(3L, new LgInt(5));
        assertEq(2, law_.editNumberSeed(LgInt.zero()));
        assertEq(2, law_.editNumberSeed(LgInt.one()));
        assertEq(2, law_.editNumberSeed(new LgInt(2)));
        assertEq(3, law_.editNumberSeed(new LgInt(3)));
        assertEq(3, law_.editNumberSeed(new LgInt(4)));
        assertEq(3, law_.editNumberSeed(new LgInt(5)));
        assertEq(3, law_.editNumberSeed(new LgInt(6)));
        assertEq(3, law_.editNumberSeed(new LgInt(7)));
        assertEq(2, law_.editNumberSeed(new LgInt(8)));
    }

    @Test
    public void editNumber5Test() {
        MonteCarloNb law_ = new MonteCarloNb();
        law_.addEvent(2L, new LgInt(0));
        law_.addEvent(3L, new LgInt(5));
        assertEq(3, law_.editNumberSeed(LgInt.zero()));
        assertEq(3, law_.editNumberSeed(LgInt.one()));
        assertEq(3, law_.editNumberSeed(new LgInt(2)));
        assertEq(3, law_.editNumberSeed(new LgInt(3)));
        assertEq(3, law_.editNumberSeed(new LgInt(4)));
        assertEq(3, law_.editNumberSeed(new LgInt(5)));
        assertEq(3, law_.editNumberSeed(new LgInt(6)));
        assertEq(3, law_.editNumberSeed(new LgInt(7)));
        assertEq(3, law_.editNumberSeed(new LgInt(8)));
    }

    @Test
    public void editNumber6Test() {
        MonteCarloNb law_ = new MonteCarloNb();
        law_.addEvent(3L, new LgInt(5));
        law_.addEvent(2L, new LgInt(0));
        assertEq(3, law_.editNumberSeed(LgInt.zero()));
        assertEq(3, law_.editNumberSeed(LgInt.one()));
        assertEq(3, law_.editNumberSeed(new LgInt(2)));
        assertEq(3, law_.editNumberSeed(new LgInt(3)));
        assertEq(3, law_.editNumberSeed(new LgInt(4)));
        assertEq(3, law_.editNumberSeed(new LgInt(5)));
        assertEq(3, law_.editNumberSeed(new LgInt(6)));
        assertEq(3, law_.editNumberSeed(new LgInt(7)));
        assertEq(3, law_.editNumberSeed(new LgInt(8)));
    }

    @Test
    public void editNumber7Test() {
        MonteCarloNb law_ = new MonteCarloNb();
        law_.addEvent(3L, new LgInt(5));
        law_.addEvent(2L, new LgInt(0));
        law_.addEvent(4L, new LgInt(3));
        assertEq(3, law_.editNumberSeed(LgInt.zero()));
        assertEq(3, law_.editNumberSeed(LgInt.one()));
        assertEq(3, law_.editNumberSeed(new LgInt(2)));
        assertEq(3, law_.editNumberSeed(new LgInt(3)));
        assertEq(3, law_.editNumberSeed(new LgInt(4)));
        assertEq(4, law_.editNumberSeed(new LgInt(5)));
        assertEq(4, law_.editNumberSeed(new LgInt(6)));
        assertEq(4, law_.editNumberSeed(new LgInt(7)));
        assertEq(3, law_.editNumberSeed(new LgInt(8)));
    }

    @Test
    public void editNumber9Test() {
        MonteCarloNb law_ = new MonteCarloNb();
        law_.addEvent(3L, new LgInt(5));
        assertEq(3, law_.editNumber(new LgInt(8)));
    }

    @Test
    public void editNumber10Test() {
        MonteCarloNb law_ = new MonteCarloNb();
        law_.addEvent(2L, new LgInt(0));
        law_.addEvent(3L, new LgInt(5));
        assertEq(3, law_.editNumber(new LgInt(8)));
    }

    @Test
    public void nbEventsTest() {
        MonteCarloNb law_ = new MonteCarloNb();
        assertEq(0, law_.nbEvents());
    }

    @Test
    public void isZero1Test() {
        MonteCarloNb law_ = new MonteCarloNb();
        assertTrue(law_.isZero());
    }

    @Test
    public void isZero2Test() {
        MonteCarloNb law_ = new MonteCarloNb();
        law_.addEvent(1L,LgInt.one());
        assertTrue(!law_.isZero());
    }

    @Test
    public void checkEvents1Test() {
        MonteCarloNb law_ = new MonteCarloNb();
        law_.addEvent(1L,LgInt.one());
        assertTrue(law_.checkEvents());
    }

    @Test
    public void checkEvents2Test() {
        MonteCarloNb law_ = new MonteCarloNb();
        assertTrue(law_.checkEvents());
    }

    @Test
    public void checkEvents3Test() {
        MonteCarloNb law_ = new MonteCarloNb();
        law_.addEvent(1L,LgInt.zero());
        assertTrue(!law_.checkEvents());
    }

    @Test
    public void checkEvents4Test() {
        MonteCarloNb law_ = new MonteCarloNb();
        law_.addEvent(1L,LgInt.minusOne());
        assertTrue(!law_.checkEvents());
    }
    @Test
    public void new_MonteCarloBoolean_test() {
        MonteCarloBoolean l_ = new MonteCarloBoolean(new CollCapacity(1));
        l_.addEvent(true,LgInt.one());
        assertEq(1,l_.nbEvents());
    }
    @Test
    public void new_MonteCarloEnum_test() {
        MonteCarloEnum<SampleEnum> l_ = new MonteCarloEnum<SampleEnum>();
        l_.addEvent(SampleEnum.ONE,LgInt.one());
        assertEq(1,l_.nbEvents());
        l_ = new MonteCarloEnum<SampleEnum>(new CollCapacity(1));
        l_.addEvent(SampleEnum.TWO,LgInt.one());
        assertEq(1,l_.nbEvents());
    }
    @Test
    public void new_MonteCarloEq_test() {
        MonteCarloEq<SampleEquallable> l_ = new MonteCarloEq<SampleEquallable>(new CollCapacity(1));
        l_.addEvent(new SampleEquallable(1),LgInt.one());
        assertEq(1,l_.nbEvents());
    }
    @Test
    public void new_MonteCarloString_test() {
        MonteCarloString l_ = new MonteCarloString();
        l_.addEvent("1",LgInt.one());
        assertEq(1,l_.nbEvents());
        l_ = new MonteCarloString(new CollCapacity(1));
        l_.addEvent("1",LgInt.one());
        assertEq(1,l_.nbEvents());
    }
}
