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

    /*@Test
    public void isPrimitive1Test() {
        assertTrue(MonteCarlo.isPrimitive(1));
        assertTrue(MonteCarlo.isPrimitive(new Integer(2)));
        assertTrue(MonteCarlo.isPrimitive(false));
        assertTrue(MonteCarlo.isPrimitive(true));
        assertTrue(MonteCarlo.isPrimitive('c'));
        assertTrue(MonteCarlo.isPrimitive(new Character('h')));
        assertTrue(MonteCarlo.isPrimitive("STRING"));
        assertTrue(MonteCarlo.isPrimitive(-1l));
        assertTrue(MonteCarlo.isPrimitive(new Long(-2)));
        assertTrue(MonteCarlo.isPrimitive((short)3));
        assertTrue(MonteCarlo.isPrimitive(new Short((short) -4)));
        assertTrue(MonteCarlo.isPrimitive((byte)5));
        assertTrue(MonteCarlo.isPrimitive(new Byte((byte) -6)));
        assertTrue(MonteCarlo.isPrimitive(Rate.one()));
        assertTrue(MonteCarlo.isPrimitive(LgInt.one()));
        assertTrue(MonteCarlo.isPrimitive(MyEnum.FIRST));
        assertTrue(MonteCarlo.isPrimitive(MyEnum.SECOND));
        assertTrue(MonteCarlo.isPrimitive(MyEnum.THIRD));
        assertTrue(MonteCarlo.isPrimitive(MyEnum.FOURTH));
        assertTrue(MonteCarlo.isPrimitive(null));
        assertTrue(!MonteCarlo.isPrimitive(new MonteCarlo<>()));
        assertTrue(!MonteCarlo.isPrimitive(new List<>()));
        assertTrue(!MonteCarlo.isPrimitive(new Map<>()));
    }

    @Test
    public void deleteZeroEventsDeeply1Test() {
        MonteCarlo<Integer> law_ = new MonteCarlo<>();
        law_.addEvent(3, new LgInt(5));
        MonteCarlo.deleteZeroEventsDeeply(law_,false);
        assertEq(1,law_.events().size());
        assertTrue(law_.events().containsObj(3));
        assertTrue(law_.isValid());
    }

    @Test
    public void deleteZeroEventsDeeply2Test() {
        MonteCarlo<Integer> law_ = new MonteCarlo<>();
        law_.addEvent(4, new LgInt(0));
        law_.addEvent(3, new LgInt(5));
        MonteCarlo.deleteZeroEventsDeeply(law_,false);
        assertEq(1,law_.events().size());
        assertTrue(law_.events().containsObj(3));
        assertTrue(law_.isValid());
    }

    @Test
    public void deleteZeroEventsDeeply3Test() {
        MonteCarlo<Integer> law_ = new MonteCarlo<>();
        law_.addEvent(4, new LgInt(0));
        law_.addEvent(3, new LgInt(5));
        List<MonteCarlo<Integer>> laws_ = new List<>();
        laws_.add(law_);
        MonteCarlo.deleteZeroEventsDeeply(laws_,false);
        assertEq(1,laws_.first().events().size());
        assertTrue(laws_.first().events().containsObj(3));
        assertTrue(laws_.first().isValid());
    }

    @Test
    public void deleteZeroEventsDeeply4Test() {
        MonteCarlo<Integer> law_ = new MonteCarlo<>();
        law_.addEvent(4, new LgInt(0));
        law_.addEvent(3, new LgInt(5));
        Map<String,MonteCarlo<Integer>> laws_ = new Map<>();
        laws_.put("",law_);
        MonteCarlo.deleteZeroEventsDeeply(laws_,false);
        assertEq(1,laws_.values().first().events().size());
        assertTrue(laws_.values().first().events().containsObj(3));
        assertTrue(laws_.values().first().isValid());
    }

    @Test
    public void deleteZeroEventsDeeply5Test() {
        MonteCarlo<Integer> law_ = new MonteCarlo<>();
        law_.addEvent(4, new LgInt(0));
        law_.addEvent(3, new LgInt(5));
        List<MonteCarlo<Integer>> laws_ = new List<>();
        laws_.add(law_);
        laws_.add(law_);
        MonteCarlo.deleteZeroEventsDeeply(laws_,false);
        assertEq(1,laws_.first().events().size());
        assertTrue(laws_.first().events().containsObj(3));
        assertTrue(laws_.first().isValid());
    }

    @Test
    public void deleteZeroEventsDeeply6Test() {
        MonteCarlo<Integer> law_ = new MonteCarlo<>();
        law_.addEvent(4, new LgInt(0));
        law_.addEvent(3, new LgInt(5));
        Map<String,MonteCarlo<Integer>> laws_ = new Map<>();
        laws_.put("FIRST",law_);
        laws_.put("SECOND",law_);
        MonteCarlo.deleteZeroEventsDeeply(laws_,false);
        assertEq(1,laws_.values().first().events().size());
        assertTrue(laws_.values().first().events().containsObj(3));
        assertTrue(laws_.values().first().isValid());
    }

    @Test
    public void deleteZeroEventsDeeply7Test() {
        MonteCarlo<Integer> law_ = new MonteCarlo<>();
        law_.addEvent(4, new LgInt(0));
        law_.addEvent(3, new LgInt(5));
        MonteCarlo<?>[] laws_ = new MonteCarlo<?>[1];
        laws_[0] = law_;
        MonteCarlo.deleteZeroEventsDeeply(laws_,false);
        assertEq(1,laws_[0].events().size());
        assertTrue(laws_[0].events().contains(3));
        assertTrue(laws_[0].isValid());
    }

    @Test
    public void deleteZeroEventsDeeply8Test() {
        Composite composite_ = new Composite();
        MonteCarlo<String> law_ = new MonteCarlo<>();
        law_.addEvent("FIRST", new LgInt(0));
        law_.addEvent("SECOND", new LgInt(5));
        composite_.setLaw(law_);
        composite_.setArray(new int[]{1,5,9});
        composite_.setNumbers(new Numbers<Integer>());
        composite_.getNumbers().add(2);
        composite_.getNumbers().add(-2);
        composite_.setPrimitiveField(5);
        List<MonteCarlo<String>> laws_ = new List<>();
        law_ = new MonteCarlo<>();
        law_.addEvent("SECOND", new LgInt(0));
        law_.addEvent("FIRST", new LgInt(5));
        laws_.add(law_);
        composite_.setLaws(laws_);
        MonteCarlo.deleteZeroEventsDeeply(composite_,false);
        MonteCarlo<String> res_ = composite_.getLaw();
        assertEq(1,res_.events().size());
        assertTrue(res_.events().containsObj("SECOND"));
        assertTrue(res_.isValid());
        res_ = composite_.getLaws().first();
        assertEq(1,res_.events().size());
        assertTrue(res_.events().containsObj("FIRST"));
        assertTrue(res_.isValid());
        assertEq(5, composite_.getPrimitiveField());
        int[] array_ = composite_.getArray();
        assertEq(3, array_.length);
        assertEq(1, array_[0]);
        assertEq(5, array_[1]);
        assertEq(9, array_[2]);
        Numbers<Integer> numbers_ = composite_.getNumbers();
        assertEq(2, numbers_.size());
        assertEq(2, numbers_.get(0).intValue());
        assertEq(-2, numbers_.get(1).intValue());
    }

    @Test
    public void deleteZeroEventsDeeply9Test() {
        Composite composite_ = new Composite();
        MonteCarlo<String> law_ = new MonteCarlo<>();
        law_.addEvent("FIRST", new LgInt(0));
        law_.addEvent("SECOND", new LgInt(5));
        composite_.setLaw(law_);
        composite_.setArray(new int[]{1,5,9});
        composite_.setNumbers(new Numbers<Integer>());
        composite_.getNumbers().add(2);
        composite_.getNumbers().add(-2);
        composite_.setPrimitiveField(5);
        List<MonteCarlo<String>> laws_ = new List<>();
        laws_.add(law_);
        composite_.setLaws(laws_);
        MonteCarlo.deleteZeroEventsDeeply(composite_,false);
        MonteCarlo<String> res_ = composite_.getLaw();
        assertEq(1,res_.events().size());
        assertTrue(res_.events().containsObj("SECOND"));
        assertTrue(res_.isValid());
        res_ = composite_.getLaws().first();
        assertEq(1,res_.events().size());
        assertTrue(res_.events().containsObj("SECOND"));
        assertTrue(res_.isValid());
        assertSame(composite_.getLaws().first(), composite_.getLaw());
        assertEq(5, composite_.getPrimitiveField());
        int[] array_ = composite_.getArray();
        assertEq(3, array_.length);
        assertEq(1, array_[0]);
        assertEq(5, array_[1]);
        assertEq(9, array_[2]);
        Numbers<Integer> numbers_ = composite_.getNumbers();
        assertEq(2, numbers_.size());
        assertEq(2, numbers_.get(0).intValue());
        assertEq(-2, numbers_.get(1).intValue());
    }

    @Test
    public void deleteZeroEventsDeeply10Test() {
        Map<Composite, MonteCarlo<String>> superComposites_;
        superComposites_ = new Map<>();
        Composite composite_ = new Composite();
        MonteCarlo<String> law_ = new MonteCarlo<>();
        law_.addEvent("FIRST", new LgInt(0));
        law_.addEvent("SECOND", new LgInt(5));
        composite_.setLaw(law_);
        composite_.setArray(new int[]{1,5,9});
        composite_.setNumbers(new Numbers<Integer>());
        composite_.getNumbers().add(2);
        composite_.getNumbers().add(-2);
        composite_.setPrimitiveField(5);
        List<MonteCarlo<String>> laws_ = new List<>();
        laws_.add(law_);
        composite_.setLaws(laws_);
        superComposites_.put(composite_, law_);
        MonteCarlo.deleteZeroEventsDeeply(superComposites_,false);
        Composite resComposite_ = superComposites_.getKeys().first();
        MonteCarlo<String> res_ = resComposite_.getLaw();
        assertEq(1,res_.events().size());
        assertTrue(res_.events().containsObj("SECOND"));
        assertTrue(res_.isValid());
        res_ = resComposite_.getLaws().first();
        assertEq(1,res_.events().size());
        assertTrue(res_.events().containsObj("SECOND"));
        assertTrue(res_.isValid());
        res_ = superComposites_.values().first();
        assertEq(1,res_.events().size());
        assertTrue(res_.events().containsObj("SECOND"));
        assertTrue(res_.isValid());
        assertSame(resComposite_.getLaws().first(), resComposite_.getLaw());
        assertSame(resComposite_.getLaws().first(), superComposites_.values().first());
        assertEq(5, resComposite_.getPrimitiveField());
        int[] array_ = resComposite_.getArray();
        assertEq(3, array_.length);
        assertEq(1, array_[0]);
        assertEq(5, array_[1]);
        assertEq(9, array_[2]);
        Numbers<Integer> numbers_ = resComposite_.getNumbers();
        assertEq(2, numbers_.size());
        assertEq(2, numbers_.get(0).intValue());
        assertEq(-2, numbers_.get(1).intValue());
    }

    @Test
    public void deleteZeroEventsDeeply11Test() {
        Object[] array_ = new Object[3];
        array_[0] = 5;
        array_[1] = new Object[3];
        MonteCarlo<String> law_ = new MonteCarlo<>();
        law_.addEvent("FIRST", new LgInt(0));
        law_.addEvent("SECOND", new LgInt(5));
        array_[2] = law_;
        ((Object[])array_[1])[0] = 6;
        ((Object[])array_[1])[1] = null;
        ((Object[])array_[1])[2] = law_;
        MonteCarlo.deleteZeroEventsDeeply(array_,false);
        MonteCarlo<?> res_ = (MonteCarlo<?>) array_[2];
        assertEq(1,res_.events().size());
        assertTrue(res_.events().contains("SECOND"));
        assertTrue(res_.isValid());
        res_ = (MonteCarlo<?>) ((Object[])array_[1])[2];
        assertEq(1,res_.events().size());
        assertTrue(res_.events().contains("SECOND"));
        assertTrue(res_.isValid());
        assertEq(5, array_[0]);
        assertEq(6, ((Object[])array_[1])[0]);
        assertNull(((Object[])array_[1])[1]);
        assertSame(((Object[])array_[1])[2], array_[2]);
    }

    @Test
    public void deleteZeroEventsDeeply12Test() {
        List<Object> array_ = new List<>();
        array_.add(5);
        List<Object> contained_ = new List<>();
        contained_.add(6);
        contained_.add(null);
        MonteCarlo<String> law_ = new MonteCarlo<>();
        law_.addEvent("FIRST", new LgInt(0));
        law_.addEvent("SECOND", new LgInt(5));
        contained_.add(law_);
        array_.add(contained_);
        array_.add(law_);
        MonteCarlo.deleteZeroEventsDeeply(array_,false);
        MonteCarlo<?> res_ = (MonteCarlo<?>) array_.get(2);
        assertEq(1,res_.events().size());
        assertTrue(res_.events().contains("SECOND"));
        assertTrue(res_.isValid());
        res_ = (MonteCarlo<?>) ((List<?>)array_.get(1)).get(2);
        assertEq(1,res_.events().size());
        assertTrue(res_.events().contains("SECOND"));
        assertTrue(res_.isValid());
        assertEq(5, array_.get(0));
        assertEq(6, ((List<?>)array_.get(1)).get(0));
        assertNull(((List<?>)array_.get(1)).get(1));
        assertSame(((List<?>)array_.get(1)).get(2), array_.get(2));
    }

    @Test
    public void deleteZeroEventsDeeply13Test() {
        List<Object> array_ = new List<>();
        array_.add(5);
        Map<Object,Object> contained_ = new Map<>();
        contained_.put(6,6);
        contained_.put(null,null);
        MonteCarlo<String> law_ = new MonteCarlo<>();
        law_.addEvent("FIRST", new LgInt(0));
        law_.addEvent("SECOND", new LgInt(5));
        contained_.put("STRING",law_);
        contained_.put(law_,89);
        array_.add(contained_);
        array_.add(law_);
        MonteCarlo.deleteZeroEventsDeeply(array_,false);
        MonteCarlo<?> res_ = (MonteCarlo<?>) array_.get(2);
        assertEq(1,res_.events().size());
        assertTrue(res_.events().contains("SECOND"));
        assertTrue(res_.isValid());
        res_ = (MonteCarlo<?>) ((Map<?,?>)array_.get(1)).get("STRING");
        assertEq(1,res_.events().size());
        assertTrue(res_.events().contains("SECOND"));
        assertTrue(res_.isValid());
        assertEq(5, array_.get(0));
        Map<?,?> obj_ = (Map<?,?>) array_.get(1);
        Object return_ = obj_.get(6);
        assertEq(6, ((Integer)return_).intValue());
        return_ = obj_.get(null);
        assertNull(return_);
        return_ = obj_.get("STRING");
        assertSame(return_, array_.get(2));
        obj_.getKeysObject(89);
        return_ = obj_.getKeysObject(89);
        assertSame(((List<?>)return_).get(0), array_.get(2));
    }*/
}
