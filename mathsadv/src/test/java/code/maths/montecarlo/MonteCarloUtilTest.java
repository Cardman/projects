package code.maths.montecarlo;
import static code.maths.montecarlo.EquallableUtil.assertEq;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNb;
import code.maths.montecarlo.MonteCarloString;
import code.maths.montecarlo.MonteCarloUtil;
import code.util.CustList;
import code.util.IdMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;

@SuppressWarnings("static-method")
public class MonteCarloUtilTest {

    @Test
    public void isPrimitive1Test() {
        assertTrue(MonteCarloUtil.isPrimitive(1));
        assertTrue(MonteCarloUtil.isPrimitive(new Integer(2)));
        assertTrue(MonteCarloUtil.isPrimitive(false));
        assertTrue(MonteCarloUtil.isPrimitive(true));
        assertTrue(MonteCarloUtil.isPrimitive('c'));
        assertTrue(MonteCarloUtil.isPrimitive(new Character('h')));
        assertTrue(MonteCarloUtil.isPrimitive("STRING"));
        assertTrue(MonteCarloUtil.isPrimitive(-1l));
        assertTrue(MonteCarloUtil.isPrimitive(new Long(-2)));
        assertTrue(MonteCarloUtil.isPrimitive((short)3));
        assertTrue(MonteCarloUtil.isPrimitive(new Short((short) -4)));
        assertTrue(MonteCarloUtil.isPrimitive((byte)5));
        assertTrue(MonteCarloUtil.isPrimitive(new Byte((byte) -6)));
        assertTrue(MonteCarloUtil.isPrimitive(Rate.one()));
        assertTrue(MonteCarloUtil.isPrimitive(LgInt.one()));
        assertTrue(MonteCarloUtil.isPrimitive(MyEnum.FIRST));
        assertTrue(MonteCarloUtil.isPrimitive(MyEnum.SECOND));
        assertTrue(MonteCarloUtil.isPrimitive(MyEnum.THIRD));
        assertTrue(MonteCarloUtil.isPrimitive(MyEnum.FOURTH));
        assertTrue(MonteCarloUtil.isPrimitive(null));
        assertTrue(!MonteCarloUtil.isPrimitive(new MonteCarloString()));
        assertTrue(!MonteCarloUtil.isPrimitive(new StringList()));
        assertTrue(!MonteCarloUtil.isPrimitive(new StringMap<String>()));
    }

    @Test
    public void deleteZeroEventsDeeply1Test() {
        MonteCarloNb<Integer> law_ = new MonteCarloNb<Integer>();
        law_.addEvent(3, new LgInt(5));
        MonteCarloUtil.deleteZeroEventsDeeply(law_,false);
        assertEq(1,law_.events().size());
        assertTrue(law_.events().containsObj(3));
        assertTrue(law_.isValid());
    }

    @Test
    public void deleteZeroEventsDeeply2Test() {
        MonteCarloNb<Integer> law_ = new MonteCarloNb<Integer>();
        law_.addEvent(4, new LgInt(0));
        law_.addEvent(3, new LgInt(5));
        MonteCarloUtil.deleteZeroEventsDeeply(law_,false);
        assertEq(1,law_.events().size());
        assertTrue(law_.events().containsObj(3));
        assertTrue(law_.isValid());
    }

    @Test
    public void deleteZeroEventsDeeply3Test() {
        MonteCarloNb<Integer> law_ = new MonteCarloNb<Integer>();
        law_.addEvent(4, new LgInt(0));
        law_.addEvent(3, new LgInt(5));
        CustList<MonteCarloNb<Integer>> laws_ = new CustList<MonteCarloNb<Integer>>();
        laws_.add(law_);
        MonteCarloUtil.deleteZeroEventsDeeply(laws_,false);
        assertEq(1,laws_.first().events().size());
        assertTrue(laws_.first().events().containsObj(3));
        assertTrue(laws_.first().isValid());
    }

    @Test
    public void deleteZeroEventsDeeply4Test() {
        MonteCarloNb<Integer> law_ = new MonteCarloNb<Integer>();
        law_.addEvent(4, new LgInt(0));
        law_.addEvent(3, new LgInt(5));
        StringMap<MonteCarloNb<Integer>> laws_ = new StringMap<MonteCarloNb<Integer>>();
        laws_.put("",law_);
        MonteCarloUtil.deleteZeroEventsDeeply(laws_,false);
        assertEq(1,laws_.values().first().events().size());
        assertTrue(laws_.values().first().events().containsObj(3));
        assertTrue(laws_.values().first().isValid());
    }

    @Test
    public void deleteZeroEventsDeeply5Test() {
        MonteCarloNb<Integer> law_ = new MonteCarloNb<Integer>();
        law_.addEvent(4, new LgInt(0));
        law_.addEvent(3, new LgInt(5));
        CustList<MonteCarloNb<Integer>> laws_ = new CustList<MonteCarloNb<Integer>>();
        laws_.add(law_);
        laws_.add(law_);
        MonteCarloUtil.deleteZeroEventsDeeply(laws_,false);
        assertEq(1,laws_.first().events().size());
        assertTrue(laws_.first().events().containsObj(3));
        assertTrue(laws_.first().isValid());
    }

    @Test
    public void deleteZeroEventsDeeply6Test() {
        MonteCarloNb<Integer> law_ = new MonteCarloNb<Integer>();
        law_.addEvent(4, new LgInt(0));
        law_.addEvent(3, new LgInt(5));
        StringMap<MonteCarloNb<Integer>> laws_ = new StringMap<MonteCarloNb<Integer>>();
        laws_.put("FIRST",law_);
        laws_.put("SECOND",law_);
        MonteCarloUtil.deleteZeroEventsDeeply(laws_,false);
        assertEq(1,laws_.values().first().events().size());
        assertTrue(laws_.values().first().events().containsObj(3));
        assertTrue(laws_.values().first().isValid());
    }

    @Test
    public void deleteZeroEventsDeeply7Test() {
        MonteCarloNb<Integer> law_ = new MonteCarloNb<Integer>();
        law_.addEvent(4, new LgInt(0));
        law_.addEvent(3, new LgInt(5));
        MonteCarloNb<?>[] laws_ = new MonteCarloNb<?>[1];
        laws_[0] = law_;
        MonteCarloUtil.deleteZeroEventsDeeply(laws_,false);
        MonteCarloNb<Integer> mc_;
        mc_ = (MonteCarloNb<Integer>) laws_[0];
        assertEq(1,mc_.events().size());
        assertTrue(mc_.events().containsObj(3));
        assertTrue(mc_.isValid());
    }

    @Test
    public void deleteZeroEventsDeeply8Test() {
        Composite composite_ = new Composite();
        MonteCarloString law_ = new MonteCarloString();
        law_.addEvent("FIRST", new LgInt(0));
        law_.addEvent("SECOND", new LgInt(5));
        composite_.setLaw(law_);
        composite_.setArray(new int[]{1,5,9});
        composite_.setNumbers(new Numbers<Integer>());
        composite_.getNumbers().add(2);
        composite_.getNumbers().add(-2);
        composite_.setPrimitiveField(5);
        CustList<MonteCarloString> laws_ = new CustList<MonteCarloString>();
        law_ = new MonteCarloString();
        law_.addEvent("SECOND", new LgInt(0));
        law_.addEvent("FIRST", new LgInt(5));
        laws_.add(law_);
        composite_.setLaws(laws_);
        MonteCarloUtil.deleteZeroEventsDeeply(composite_,false);
        MonteCarloString res_ = composite_.getLaw();
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
        MonteCarloString law_ = new MonteCarloString();
        law_.addEvent("FIRST", new LgInt(0));
        law_.addEvent("SECOND", new LgInt(5));
        composite_.setLaw(law_);
        composite_.setArray(new int[]{1,5,9});
        composite_.setNumbers(new Numbers<Integer>());
        composite_.getNumbers().add(2);
        composite_.getNumbers().add(-2);
        composite_.setPrimitiveField(5);
        CustList<MonteCarloString> laws_ = new CustList<MonteCarloString>();
        laws_.add(law_);
        composite_.setLaws(laws_);
        MonteCarloUtil.deleteZeroEventsDeeply(composite_,false);
        MonteCarloString res_ = composite_.getLaw();
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
        IdMap<Composite,MonteCarloString> superComposites_;
        superComposites_ = new IdMap<Composite,MonteCarloString>();
        Composite composite_ = new Composite();
        MonteCarloString law_ = new MonteCarloString();
        law_.addEvent("FIRST", new LgInt(0));
        law_.addEvent("SECOND", new LgInt(5));
        composite_.setLaw(law_);
        composite_.setArray(new int[]{1,5,9});
        composite_.setNumbers(new Numbers<Integer>());
        composite_.getNumbers().add(2);
        composite_.getNumbers().add(-2);
        composite_.setPrimitiveField(5);
        CustList<MonteCarloString> laws_ = new CustList<MonteCarloString>();
        laws_.add(law_);
        composite_.setLaws(laws_);
        superComposites_.put(composite_, law_);
        MonteCarloUtil.deleteZeroEventsDeeply(superComposites_,false);
        Composite resComposite_ = superComposites_.getKeys().first();
        MonteCarloString res_ = resComposite_.getLaw();
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
        MonteCarloString law_ = new MonteCarloString();
        law_.addEvent("FIRST", new LgInt(0));
        law_.addEvent("SECOND", new LgInt(5));
        array_[2] = law_;
        ((Object[])array_[1])[0] = 6;
        ((Object[])array_[1])[1] = null;
        ((Object[])array_[1])[2] = law_;
        MonteCarloUtil.deleteZeroEventsDeeply(array_,false);
        MonteCarloString res_ = (MonteCarloString) array_[2];
        assertEq(1,res_.events().size());
        assertTrue(res_.events().containsObj("SECOND"));
        assertTrue(res_.isValid());
        res_ = (MonteCarloString) ((Object[])array_[1])[2];
        assertEq(1,res_.events().size());
        assertTrue(res_.events().containsObj("SECOND"));
        assertTrue(res_.isValid());
        assertEq(5, (Number)array_[0]);
        assertEq(6, (Number)((Object[])array_[1])[0]);
        assertNull(((Object[])array_[1])[1]);
        assertSame(((Object[])array_[1])[2], array_[2]);
    }

    @Test
    public void deleteZeroEventsDeeply12Test() {
        CustList<Object> array_ = new CustList<Object>();
        array_.add(5);
        CustList<Object> contained_ = new CustList<Object>();
        contained_.add(6);
        contained_.add(null);
        MonteCarloString law_ = new MonteCarloString();
        law_.addEvent("FIRST", new LgInt(0));
        law_.addEvent("SECOND", new LgInt(5));
        contained_.add(law_);
        array_.add(contained_);
        array_.add(law_);
        MonteCarloUtil.deleteZeroEventsDeeply(array_,false);
        MonteCarloString res_ = (MonteCarloString) array_.get(2);
        assertEq(1,res_.events().size());
        assertTrue(res_.events().containsObj("SECOND"));
        assertTrue(res_.isValid());
        res_ = (MonteCarloString) ((CustList<?>)array_.get(1)).get(2);
        assertEq(1,res_.events().size());
        assertTrue(res_.events().containsObj("SECOND"));
        assertTrue(res_.isValid());
        assertEq(5, (Number)array_.get(0));
        assertEq(6, (Number)((CustList<?>)array_.get(1)).get(0));
        assertNull(((CustList<?>)array_.get(1)).get(1));
        assertSame(((CustList<?>)array_.get(1)).get(2), array_.get(2));
    }

    @Test
    public void deleteZeroEventsDeeply13Test() {
        CustList<Object> array_ = new CustList<Object>();
        array_.add(5);
        IdMap<Object,Object> contained_ = new IdMap<Object,Object>();
        contained_.put(6,6);
        contained_.put(null,null);
        MonteCarloString law_ = new MonteCarloString();
        law_.addEvent("FIRST", new LgInt(0));
        law_.addEvent("SECOND", new LgInt(5));
        contained_.put("STRING",law_);
        contained_.put(law_,89);
        array_.add(contained_);
        array_.add(law_);
        MonteCarloUtil.deleteZeroEventsDeeply(array_,false);
        MonteCarloString res_ = (MonteCarloString) array_.get(2);
        assertEq(1,res_.events().size());
        assertTrue(res_.events().containsObj("SECOND"));
        assertTrue(res_.isValid());
        res_ = (MonteCarloString) ((IdMap<? super Object,?>)array_.get(1)).getVal("STRING");
        assertEq(1,res_.events().size());
        assertTrue(res_.events().containsObj("SECOND"));
        assertTrue(res_.isValid());
        assertEq(5, (Number)array_.get(0));
        IdMap<? super Object,?> obj_ = (IdMap<? super Object,?>) array_.get(1);
        Object return_ = obj_.getVal(6);
        assertEq(6, ((Integer)return_).intValue());
        return_ = obj_.getVal(null);
        assertNull(return_);
        return_ = obj_.getVal("STRING");
        assertSame(return_, array_.get(2));
//        return_ = obj_.getKeysObject(89);
//        assertSame(((List<?>)return_).get(0), array_.get(2));
        assertEq(89, (Number)obj_.getVal(array_.get(2)));
    }

    @Test
    public void deleteZeroEventsDeeply14Test() {
        NotAccessibleClass array_ = new NotAccessibleClass();
        array_.setPrimitiveField(4);
        MonteCarloUtil.deleteZeroEventsDeeply(array_,false);
        assertEq(4, array_.getPrimitiveField());
    }

    @Test
    public void deleteZeroEventsDeeply15Test() {
        NotAccessibleClass array_ = new NotAccessibleClass();
        array_.setPrimitiveField(4);
        MonteCarloUtil.deleteZeroEventsDeeply(array_,true);
        assertEq(4, array_.getPrimitiveField());
    }
}
