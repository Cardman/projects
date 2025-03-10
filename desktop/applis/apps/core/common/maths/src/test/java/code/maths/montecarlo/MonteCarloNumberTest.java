package code.maths.montecarlo;

import code.maths.EquallableMathUtil;
import code.util.IdMap;
import code.util.core.BoolVal;
import org.junit.Test;

import code.maths.LgInt;
import code.maths.Rate;


public class MonteCarloNumberTest extends EquallableMathUtil {

    @Test
    public void valid1Test() {
//        MonteCarloNumber law_ = new MonteCarloNumber();
//        assertTrue(!law_.isValid());
//        law_.addEvent(new Rate(2), new LgInt(0));
//        assertTrue(!law_.isValid());
////        law_ = new MonteCarloNumber();
////        law_.addEvent(null, new LgInt(2));
////        assertTrue(law_.isValid());
//        law_ = new MonteCarloNumber();
//        law_.addEvent(new Rate(2), new LgInt(-1));
//        law_.addEvent(new Rate(3), new LgInt(2));
//        assertTrue(!law_.isValid());
//        law_ = new MonteCarloNumber();
//        law_.addEvent(new Rate(2), new LgInt(1));
//        law_.addEvent(new Rate(3), new LgInt(2));
//        assertTrue(law_.isValid());
        MonteCarloNumber law_ = new MonteCarloNumber();
        law_.addQuickEvent(Rate.one(),LgInt.one());
        assertEq(1,law_.copy().nbEvents());
        assertEq(0,new MonteCarloNumber(new IdMap<Rate, LgInt>()).nbEvents());
//        assertTrue(!law_.isValid());
    }

    @Test
    public void min1Test() {
        MonteCarloNumber law_ = new MonteCarloNumber();
        law_.addEvent(new Rate(2), new LgInt(1));
        law_.addEvent(new Rate(3), new LgInt(5));
        assertEq(new Rate(2),law_.minimum());
        law_ = new MonteCarloNumber();
        law_.addEvent(new Rate(3), new LgInt(1));
        law_.addEvent(new Rate(2), new LgInt(5));
        assertEq(new Rate(2),law_.minimum());
        law_ = new MonteCarloNumber();
        law_.addEvent(new Rate(3), new LgInt(5));
        assertEq(new Rate(3),law_.minimum());
    }

    @Test
    public void min2Test() {
        MonteCarloNumber law_ = new MonteCarloNumber();
        assertEq(new Rate(0),law_.minimum());
    }
    @Test
    public void max1Test() {
        MonteCarloNumber law_ = new MonteCarloNumber();
        law_.addEvent(new Rate(2), new LgInt(1));
        law_.addEvent(new Rate(3), new LgInt(5));
        assertEq(new Rate(3),law_.maximum());
        law_ = new MonteCarloNumber();
        law_.addEvent(new Rate(3), new LgInt(1));
        law_.addEvent(new Rate(2), new LgInt(5));
        assertEq(new Rate(3),law_.maximum());
        law_ = new MonteCarloNumber();
        law_.addEvent(new Rate(3), new LgInt(5));
        assertEq(new Rate(3),law_.maximum());
    }
    @Test
    public void max2Test() {
        MonteCarloNumber law_ = new MonteCarloNumber();
        assertEq(new Rate(0),law_.maximum());
    }
    @Test
    public void avg1Test() {
        MonteCarloNumber law_ = new MonteCarloNumber();
        law_.addEvent(new Rate(2), new LgInt(1));
        law_.addEvent(new Rate(3), new LgInt(5));
        assertEq(new Rate(17,6),law_.getAvg());
        law_ = new MonteCarloNumber();
        law_.addEvent(new Rate(3), new LgInt(5));
        assertEq(new Rate(3),law_.getAvg());
    }

    @Test
    public void var1Test() {
        MonteCarloNumber law_ = new MonteCarloNumber();
        law_.addEvent(new Rate(2), new LgInt(1));
        law_.addEvent(new Rate(3), new LgInt(5));
        assertEq(new Rate(5,36),law_.getVar());
        law_ = new MonteCarloNumber();
        law_.addEvent(new Rate(3), new LgInt(5));
        assertEq(Rate.zero(),law_.getVar());
    }

    @Test
    public void knowingLower1Test() {
        MonteCarloNumber law_ = new MonteCarloNumber();
        law_.addEvent(new Rate(2), new LgInt(1));
        law_.addEvent(new Rate(3), new LgInt(5));
        MonteCarloBoolean resLaw_ = law_.knowingLower(new Rate(2));
        assertTrue(resLaw_.containsEvent(BoolVal.FALSE));
        assertEq(1,resLaw_.events().size());
        resLaw_ = law_.knowingLower(new Rate(5,2));
        assertTrue(resLaw_.containsEvent(BoolVal.TRUE));
        assertEq(1,resLaw_.events().size());
        resLaw_ = law_.knowingLower(new Rate(3));
        assertEq(2,resLaw_.events().size());
        assertEq(new LgInt(1),resLaw_.rate(BoolVal.TRUE));
        assertEq(new LgInt(5),resLaw_.rate(BoolVal.FALSE));
        resLaw_ = law_.knowingLower(new Rate(4));
        assertEq(1,resLaw_.events().size());
        assertTrue(resLaw_.containsEvent(BoolVal.TRUE));
        resLaw_ = law_.knowingLower(new Rate(3,2));
        assertEq(1,resLaw_.events().size());
        assertTrue(resLaw_.containsEvent(BoolVal.FALSE));
    }

    @Test
    public void knowingGreater1Test() {
        MonteCarloNumber law_ = new MonteCarloNumber();
        law_.addEvent(new Rate(2), new LgInt(1));
        law_.addEvent(new Rate(3), new LgInt(5));
        MonteCarloBoolean resLaw_ = law_.knowingGreater(new Rate(2));
        assertEq(2,resLaw_.events().size());
        assertEq(new LgInt(5),resLaw_.rate(BoolVal.TRUE));
        assertEq(new LgInt(1),resLaw_.rate(BoolVal.FALSE));
        resLaw_ = law_.knowingGreater(new Rate(5,2));
        assertTrue(resLaw_.containsEvent(BoolVal.TRUE));
        assertEq(1,resLaw_.events().size());
        resLaw_ = law_.knowingGreater(new Rate(3));
        assertEq(1,resLaw_.events().size());
        assertTrue(resLaw_.containsEvent(BoolVal.FALSE));
        resLaw_ = law_.knowingGreater(new Rate(4));
        assertEq(1,resLaw_.events().size());
        assertTrue(resLaw_.containsEvent(BoolVal.FALSE));
        resLaw_ = law_.knowingGreater(new Rate(3,2));
        assertEq(1,resLaw_.events().size());
        assertTrue(resLaw_.containsEvent(BoolVal.TRUE));
    }

    @Test
    public void realAvg1Test() {
        assertEq(Rate.zero(), new MonteCarloNumber().getRealAvg(new LgInt(8)));
        MonteCarloNumber law_ = new MonteCarloNumber();
        law_.addEvent(new Rate(2), new LgInt(1));
        law_.addEvent(new Rate(3), new LgInt(5));
        LgInt max_ = LgInt.getMaxLongPlusOne();
        assertEq(new Rate("20504849135774742939590695261955150349016559784540516548653947168067950040405/7237005577332262213973186563042994240829374041602535252466099000494570602496"),law_.getRealAvg(max_));
        law_ = new MonteCarloNumber();
        law_.addEvent(new Rate(2), new LgInt(3));
        law_.addEvent(new Rate(3), new LgInt(5));
        assertEq(new Rate(21,8),law_.getRealAvg(max_));
        law_ = new MonteCarloNumber();
        law_.addEvent(new Rate(2), new LgInt(3));
        law_.addEvent(new Rate(3), new LgInt(3));
        assertEq(new Rate("18092513943330655534932966407607485602073435104006338131165247501236426506239/7237005577332262213973186563042994240829374041602535252466099000494570602496"),law_.getRealAvg(max_));
        law_ = new MonteCarloNumber();
        law_.addEvent(new Rate(3), new LgInt(5));
        assertEq(new Rate(3),law_.getRealAvg(max_));
        law_ = new MonteCarloNumber();
        law_.addEvent(new Rate(2), new LgInt(5));
        law_.addEvent(new Rate(3), new LgInt(1));
        assertEq(new Rate("7840089375443284065137618776629910427565155211736079856838273917202451486037/3618502788666131106986593281521497120414687020801267626233049500247285301248"),law_.getRealAvg(max_));
        law_ = new MonteCarloNumber();
        law_.addEvent(new Rate(2), new LgInt(0));
        assertEq(Rate.zero(), law_.getRealAvg(max_));
    }

}
