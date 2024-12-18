package code.maths.montecarlo;

import code.maths.EquallableMathUtil;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.BoolVal;
import code.util.core.NumberUtil;
import org.junit.Test;

import code.maths.LgInt;
import code.maths.Rate;


public class MonteCarloTest extends EquallableMathUtil {

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
    public void sum2Test() {
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
        MonteCarloBoolean law_ = MonteCarloUtil.booleanLaw(rate_);
        assertEq(1,law_.events().size());
        assertTrue(law_.containsEvent(BoolVal.FALSE));
        assertTrue(!law_.containsEvent(BoolVal.TRUE));
    }

    @Test
    public void booleanLaw2Test() {
        Rate rate_ = new Rate("0");
        MonteCarloBoolean law_ = MonteCarloUtil.booleanLaw(rate_);
        assertEq(1,law_.events().size());
        assertTrue(law_.containsEvent(BoolVal.FALSE));
    }

    @Test
    public void booleanLaw3Test() {
        Rate rate_ = new Rate("2");
        MonteCarloBoolean law_ = MonteCarloUtil.booleanLaw(rate_);
        assertEq(1,law_.events().size());
        assertTrue(law_.containsEvent(BoolVal.TRUE));
    }

    @Test
    public void booleanLaw4Test() {
        Rate rate_ = new Rate("1/4");
        MonteCarloBoolean law_ = MonteCarloUtil.booleanLaw(rate_);
        assertEq(2,law_.events().size());
        assertTrue(law_.containsEvent(BoolVal.TRUE));
        assertTrue(law_.containsEvent(BoolVal.FALSE));
        assertEq(new LgInt("1"),law_.rate(BoolVal.TRUE));
        assertEq(new LgInt("3"),law_.rate(BoolVal.FALSE));
        assertEq(new Rate("1/4"),law_.normalizedRate(BoolVal.TRUE));
        assertEq(new Rate("3/4"),law_.normalizedRate(BoolVal.FALSE));
    }

    @Test
    public void randomNumber1Test() {
        CustList<LgInt> list_ = new CustList<LgInt>();
        list_.add(LgInt.zero());
        list_.add(LgInt.zero());
        list_.add(LgInt.zero());
        list_.add(LgInt.zero());
        assertEq(LgInt.zero(), MonteCarloUtil.randomNumberSe(list_, new LgInt(8)));
    }

    @Test
    public void randomNumber2Test() {
        CustList<LgInt> list_ = new CustList<LgInt>();
        list_.add(LgInt.zero());
        list_.add(LgInt.zero());
        list_.add(LgInt.one());
        list_.add(LgInt.zero());
        assertEq(new LgInt(1024), MonteCarloUtil.randomNumberSe(list_, new LgInt(1024)));
    }

    @Test
    public void randomNumber3Test() {
        CustList<LgInt> list_ = new CustList<LgInt>();
        LgInt int_ = new LgInt(Long.MAX_VALUE);
        list_.add(LgInt.zero());
        list_.add(LgInt.zero());
        list_.add(LgInt.zero());
        list_.add(int_);
        assertEq(int_, MonteCarloUtil.randomNumberSe(list_, new LgInt(8)));
    }

    @Test
    public void randomLong() {
        assertEq(-1, NumberUtil.signum(MonteCarloUtil.randomLong(2, DefaultGenerator.oneElt()) - 2));
    }

    @Test
    public void randomLgInt() {
        assertEq(-1, NumberUtil.signum(MonteCarloUtil.randomLgInt(new LgInt(2), DefaultGenerator.oneElt()).ll() - 2));
    }
    @Test
    public void editNumber1Test() {
        MonteCarloNb law_ = new MonteCarloNb();
        law_.addEvent(2L, new LgInt(1));
        assertEq(2, law_.editNumber(new LgInt(8), DefaultGenerator.oneElt()));
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
        assertEq(3, law_.editNumber(new LgInt(8), DefaultGenerator.oneElt()));
    }

    @Test
    public void editNumber10Test() {
        MonteCarloNb law_ = new MonteCarloNb();
        law_.addEvent(2L, new LgInt(0));
        law_.addEvent(3L, new LgInt(5));
        assertEq(3, law_.editNumber(new LgInt(8), DefaultGenerator.oneElt()));
    }

    @Test
    public void editNumber11Test() {
        MonteCarloNb law_ = new MonteCarloNb();
        law_.addQuickEvent(2L, new LgInt(1));
        law_.addQuickEvent(2L, new LgInt(1));
        assertEq(2, law_.editNumber(new LgInt(8), DefaultGenerator.oneElt()));
    }

    @Test
    public void editNumber12Test() {
        MonteCarloNb law_ = new MonteCarloNb();
        law_.addQuickEvent(2L, new LgInt(1));
        law_.addQuickEvent(2L, new LgInt(2));
        assertEq(2, law_.editNumber(new LgInt(8), DefaultGenerator.oneElt()));
    }

    @Test
    public void editNumber13Test() {
        MonteCarloNb law_ = new MonteCarloNb();
        law_.addQuickEvent(2L, new LgInt(2));
        law_.addQuickEvent(2L, new LgInt(1));
        assertEq(2, law_.editNumber(new LgInt(8), DefaultGenerator.oneElt()));
    }

    @Test
    public void nbEventsTest() {
        MonteCarloNb law_ = new MonteCarloNb();
        assertEq(0, law_.nbEvents());
        assertTrue(law_.isEmpty());
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
        assertTrue(!law_.isEmpty());
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
        l_.addEvent(BoolVal.TRUE,LgInt.one());
        assertEq(1,l_.nbEvents());
        IdMap<BoolVal, LgInt> id_ = new IdMap<BoolVal, LgInt>();
        id_.addEntry(BoolVal.TRUE,LgInt.one());
        l_ = new MonteCarloBoolean(id_);
        assertEq(1,l_.nbEvents());
    }
    @Test
    public void new_MonteCarloEnum_test() {
        MonteCarloEnum<BoolVal> l_ = new MonteCarloEnum<BoolVal>();
        l_.addEvent(BoolVal.TRUE,LgInt.one());
        assertEq(1,l_.nbEvents());
        l_ = new MonteCarloEnum<BoolVal>(new CollCapacity(1));
        l_.addEvent(BoolVal.FALSE,LgInt.one());
        assertEq(1,l_.nbEvents());
        l_ = new MonteCarloEnum<BoolVal>(l_.getLaw());
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
        l_ = new MonteCarloString(l_.getLaw());
        assertEq(1,l_.nbEvents());
    }

    @Test
    public void editNumber14Test() {
        MonteCarloString law_ = new MonteCarloString();
        law_.addEvent("2", new LgInt(1));
        law_.addEvent("3", new LgInt(1));
        law_.addEvent("2", new LgInt(1));
        law_.addEvent("3", new LgInt(1));
        law_.events();
        assertEq("2", law_.editNumberSeed(LgInt.zero()));
        assertEq("3", law_.editNumberSeed(LgInt.one()));
        assertEq("2", law_.editNumberSeed(new LgInt(2)));
        assertEq("3", law_.editNumberSeed(new LgInt(3)));
        assertEq("2", law_.editNumberSeed(new LgInt(4)));
        assertEq("3", law_.editNumberSeed(new LgInt(5)));
        assertEq("2", law_.editNumberSeed(new LgInt(6)));
        assertEq("3", law_.editNumberSeed(new LgInt(7)));
    }

    @Test
    public void editNumber15Test() {
        MonteCarloString law_ = new MonteCarloString();
        law_.addQuickEvent("2", new LgInt(1));
        law_.addQuickEvent("3", new LgInt(2));
        law_.addQuickEvent("2", new LgInt(3));
        law_.addQuickEvent("3", new LgInt(4));
        law_.events();
        assertEq(2,law_.eventsDiff().size());
        assertEq("2", law_.editNumberSeed(LgInt.zero()));
        assertEq("3", law_.editNumberSeed(LgInt.one()));
        assertEq("3", law_.editNumberSeed(new LgInt(2)));
        assertEq("2", law_.editNumberSeed(new LgInt(3)));
        assertEq("2", law_.editNumberSeed(new LgInt(4)));
        assertEq("2", law_.editNumberSeed(new LgInt(5)));
        assertEq("3", law_.editNumberSeed(new LgInt(6)));
        assertEq("3", law_.editNumberSeed(new LgInt(7)));
        assertEq("3", law_.editNumberSeed(new LgInt(8)));
        assertEq("3", law_.editNumberSeed(new LgInt(9)));
        assertEq("2", law_.editNumberSeed(new LgInt(10)));
        assertEq("3", law_.editNumberSeed(new LgInt(11)));
        assertEq("3", law_.editNumberSeed(new LgInt(12)));
        assertEq("2", law_.editNumberSeed(new LgInt(13)));
        assertEq("2", law_.editNumberSeed(new LgInt(14)));
        assertEq("2", law_.editNumberSeed(new LgInt(15)));
        assertEq("3", law_.editNumberSeed(new LgInt(16)));
        assertEq("3", law_.editNumberSeed(new LgInt(17)));
        assertEq("3", law_.editNumberSeed(new LgInt(18)));
        assertEq("3", law_.editNumberSeed(new LgInt(19)));
    }

    @Test
    public void editNumber16Test() {
        MonteCarloString law_ = new MonteCarloString();
        law_.addEvent("2", new LgInt(1));
        law_.events();
        assertEq("2", law_.editNumber(LgInt.one(),DefaultGenerator.oneElt()));
    }

    @Test
    public void editNumber17Test() {
        MonteCarloNumber law_ = new MonteCarloNumber();
        law_.addQuickEvent(new Rate(2), new LgInt(2));
        law_.addQuickEvent(new Rate(2), new LgInt(1));
        law_.events();
        assertEq(1,law_.eventsDiff().size());
        assertEq(new Rate(2), law_.editNumber(new LgInt(8), DefaultGenerator.oneElt()));
    }

    @Test
    public void editNumber18Test() {
        MonteCarloBoolean law_ = new MonteCarloBoolean();
        law_.addEvent(BoolVal.TRUE, new LgInt(1));
        law_.addEvent(BoolVal.FALSE, new LgInt(1));
        law_.events();
        assertSame(BoolVal.TRUE, law_.editNumber(new LgInt(8), DefaultGenerator.oneElt()));
    }

    @Test
    public void eventsDiff() {
        MonteCarloNumber law_ = new MonteCarloNumber();
        law_.addQuickEvent(new Rate(2), new LgInt(9));
        law_.addQuickEvent(new Rate(3), new LgInt(3));
        law_.addQuickEvent(new Rate(3), new LgInt(5));
        law_.addQuickEvent(new Rate(2), new LgInt(1));
        assertEq(2,law_.eventsDiff().size());
    }

    @Test
    public void normalRate1() {
        MonteCarloString law_ = new MonteCarloString();
        law_.addEvent("2", new LgInt(1));
        law_.events();
        assertTrue(law_.containsEvent("2"));
        assertEq(new Rate(1), law_.normalizedRate("2"));
    }

    @Test
    public void normalRate2() {
        MonteCarloNumber law_ = new MonteCarloNumber();
        law_.addEvent(new Rate(2), new LgInt(1));
        assertTrue(law_.containsEvent(new Rate(2)));
        assertEq(new Rate(1), law_.normalizedRate(new Rate(2)));
    }

}
