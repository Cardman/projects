package code.util;
import static code.util.EquallableExUtil.assertEq;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import code.util.classestest.MyEnum;
import org.junit.Test;

import code.util.classestest.KeyExample;
import code.util.ints.Listable;


public class MapTest {

    @Test
    public void put1Test() {
        StringMap<Number> map_ = new StringMap<Number>(new StringMap<Number>(new CollCapacity(0)));
        Listable<EntryCust<String,Number>> l_ = map_.getList();
        assertEq(0, l_.size());
        map_.put("ONE", 1);
        assertEq(1, l_.size());
        assertTrue(containsEntry(l_, new EntryCust<String, Number>("ONE", 1)));
        map_.put("TWO", 2);
        assertEq(2, l_.size());
        assertTrue(containsEntry(l_, new EntryCust<String, Number>("ONE", 1)));
        assertTrue(containsEntry(l_, new EntryCust<String, Number>("TWO", 2)));
        map_.put("TWO", 3);
        assertEq(2, l_.size());
        assertTrue(containsEntry(l_, new EntryCust<String, Number>("ONE", 1)));
        assertTrue(containsEntry(l_, new EntryCust<String, Number>("TWO", 3)));
        ObjectMap<KeyExample,Number> otherMap_ = new ObjectMap<KeyExample,Number>();
        Listable<EntryCust<KeyExample,Number>> lOther_ = otherMap_.getList();
        KeyExample k1_ = new KeyExample(0, 0);
        KeyExample k2_ = new KeyExample(0, 1);
        KeyExample k3_ = new KeyExample(0, 1);
        assertEq(0, lOther_.size());
        otherMap_.put(k1_, 0);
        assertEq(1, lOther_.size());
        assertTrue(containsEntryTwo(lOther_, new EntryCust<KeyExample, Number>(k1_, 0)));
        otherMap_.put(k2_, 1);
        assertEq(2, lOther_.size());
        assertTrue(containsEntryTwo(lOther_, new EntryCust<KeyExample, Number>(k1_, 0)));
        assertTrue(containsEntryTwo(lOther_, new EntryCust<KeyExample, Number>(k2_, 1)));
        otherMap_.put(k3_, 2);
        assertEq(2, lOther_.size());
        assertTrue(containsEntryTwo(lOther_, new EntryCust<KeyExample, Number>(k1_, 0)));
        assertTrue(containsEntryTwo(lOther_, new EntryCust<KeyExample, Number>(k2_, 2)));
    }

    @Test
    public void size1Test() {
        StringMap<Number> map_ = new StringMap<Number>();
        assertEq(0, map_.size());
        map_.put("ONE", 1);
        assertEq(1,map_.size());
        map_.put("TWO", 2);
        assertEq(2,map_.size());
        map_.put("TWO", 3);
        assertEq(2,map_.size());
        ObjectMap<KeyExample,Number> otherMap_ = new ObjectMap<KeyExample,Number>();
        assertEq(0, otherMap_.size());
        otherMap_.put(new KeyExample(0, 0), 0);
        assertEq(1,otherMap_.size());
        otherMap_.put(new KeyExample(0, 1), 1);
        assertEq(2,otherMap_.size());
        otherMap_.put(new KeyExample(0, 1), 2);
        assertEq(2,otherMap_.size());
    }

    @Test
    public void contains1Test() {
        StringMap<Number> map_ = new StringMap<Number>();
        assertEq(0, map_.size());
        map_.put("ONE", 1);
        assertTrue(map_.contains("ONE"));
        map_.put("TWO", 2);
        assertTrue(map_.contains("ONE"));
        assertTrue(map_.contains("TWO"));
        map_.put("TWO", 3);
        assertTrue(map_.contains("ONE"));
        assertTrue(map_.contains("TWO"));
        ObjectMap<KeyExample,Number> otherMap_ = new ObjectMap<KeyExample,Number>();
        assertEq(0, otherMap_.size());
        otherMap_.put(new KeyExample(0, 0), 0);
        assertTrue(otherMap_.contains(new KeyExample(0, 0)));
        otherMap_.put(new KeyExample(0, 1), 1);
        assertTrue(otherMap_.contains(new KeyExample(0, 0)));
        assertTrue(otherMap_.contains(new KeyExample(0, 1)));
        otherMap_.put(new KeyExample(0, 1), 2);
        assertTrue(otherMap_.contains(new KeyExample(0, 0)));
        assertTrue(otherMap_.contains(new KeyExample(0, 1)));
    }

    @Test
    public void getVal1Test() {
        StringMap<Number> map_ = new StringMap<Number>();
        map_.put("ONE", 1);
        assertEq(1,map_.getVal("ONE").intValue());
        assertNull(map_.getVal("TWO"));
        map_.put("TWO", 2);
        assertEq(1,map_.getVal("ONE").intValue());
        assertEq(2,map_.getVal("TWO").intValue());
        map_.put("TWO", 3);
        assertEq(1,map_.getVal("ONE").intValue());
        assertEq(3,map_.getVal("TWO").intValue());
        ObjectMap<KeyExample,Number> otherMap_ = new ObjectMap<KeyExample,Number>();
        otherMap_.put(new KeyExample(0, 0), 0);
        assertEq(0, otherMap_.getVal(new KeyExample(0, 0)).intValue());
        otherMap_.put(new KeyExample(0, 1), 1);
        assertEq(0, otherMap_.getVal(new KeyExample(0, 0)).intValue());
        assertEq(1, otherMap_.getVal(new KeyExample(0, 1)).intValue());
        otherMap_.put(new KeyExample(0, 1), 2);
        assertEq(0, otherMap_.getVal(new KeyExample(0, 0)).intValue());
        assertEq(2, otherMap_.getVal(new KeyExample(0, 1)));
    }

    @Test
    public void removeKey1Test() {
        StringMap<Number> map_ = new StringMap<Number>();
        map_.put("ONE", 1);
        map_.put("TWO", 2);
        map_.removeKey("ZERO");
        assertEq(2,map_.size());
        assertTrue(map_.contains("ONE"));
        assertTrue(map_.contains("TWO"));
        map_.removeKey("TWO");
        assertEq(1,map_.size());
        assertTrue(map_.contains("ONE"));
        assertTrue(!map_.contains("TWO"));
        ObjectMap<KeyExample,Number> otherMap_ = new ObjectMap<KeyExample,Number>();
        otherMap_.put(new KeyExample(0, 0), 0);
        otherMap_.put(new KeyExample(0, 1), 1);
        otherMap_.removeKey(new KeyExample(1, 1));
        assertEq(2,otherMap_.size());
        assertTrue(otherMap_.contains(new KeyExample(0, 0)));
        assertTrue(otherMap_.contains(new KeyExample(0, 1)));
        otherMap_.removeKey(new KeyExample(0, 1));
        assertEq(1,otherMap_.size());
        assertTrue(otherMap_.contains(new KeyExample(0, 0)));
        assertTrue(!otherMap_.contains(new KeyExample(0, 1)));
    }

    @Test
    public void move1Test() {
        StringMap<Number> map_ = new StringMap<Number>();
        map_.put("ONE", 1);
        map_.put("TWO", 3);
        map_.move("TWO", "THREE");
        assertEq(2,map_.size());
        assertTrue(map_.contains("ONE"));
        assertTrue(map_.contains("THREE"));
        assertEq(1,map_.getVal("ONE").intValue());
        assertEq(3,map_.getVal("THREE").intValue());
        map_.move("ZERO", "THREE");
        assertEq(2,map_.size());
        assertTrue(map_.contains("ONE"));
        assertTrue(map_.contains("THREE"));
        assertEq(1,map_.getVal("ONE").intValue());
        assertEq(3,map_.getVal("THREE").intValue());
    }

    @Test
    public void putAllMap1Test() {
        StringMap<Number> map_ = new StringMap<Number>();
        map_.put("ONE", 1);
        map_.put("TWO", 2);
        StringMap<Number> mapToPut_ = new StringMap<Number>();
        mapToPut_.put("THREE", 3);
        mapToPut_.put("FOUR", 4);
        map_.putAllMap(mapToPut_);
        assertEq(4, map_.size());
        assertTrue(map_.contains("ONE"));
        assertTrue(map_.contains("TWO"));
        assertTrue(map_.contains("THREE"));
        assertTrue(map_.contains("FOUR"));
        assertEq(1, map_.getVal("ONE").intValue());
        assertEq(2, map_.getVal("TWO").intValue());
        assertEq(3, map_.getVal("THREE").intValue());
        assertEq(4, map_.getVal("FOUR").intValue());
    }

    @Test
    public void putAllMap2Test() {
        StringMap<Number> map_ = new StringMap<Number>();
        map_.put("ONE", 1);
        map_.put("TWO", 2);
        StringMap<Number> mapToPut_ = new StringMap<Number>();
        mapToPut_.put("TWO", 3);
        mapToPut_.put("THREE", 4);
        map_.putAllMap(mapToPut_);
        assertEq(3, map_.size());
        assertTrue(map_.contains("ONE"));
        assertTrue(map_.contains("TWO"));
        assertTrue(map_.contains("THREE"));
        assertEq(1, map_.getVal("ONE").intValue());
        assertEq(3, map_.getVal("TWO").intValue());
        assertEq(4, map_.getVal("THREE").intValue());
    }

    @Test
    public void putAllMap3Test() {
        ObjectMap<KeyExample,Number> map_ = new ObjectMap<KeyExample,Number>(new ObjectMap<KeyExample,Number>(new CollCapacity(0)));
        map_.put(new KeyExample(0, 0), 0);
        map_.put(new KeyExample(0, 1), 1);
        ObjectMap<KeyExample,Number> mapToPut_ = new ObjectMap<KeyExample,Number>();
        mapToPut_.put(new KeyExample(1, 1), 2);
        mapToPut_.put(new KeyExample(1, 0), 3);
        map_.putAllMap(mapToPut_);
        assertEq(4, map_.size());
        assertTrue(map_.contains(new KeyExample(0, 0)));
        assertTrue(map_.contains(new KeyExample(0, 1)));
        assertTrue(map_.contains(new KeyExample(1, 1)));
        assertTrue(map_.contains(new KeyExample(1, 0)));
        assertEq(0, map_.getVal(new KeyExample(0, 0)).intValue());
        assertEq(1, map_.getVal(new KeyExample(0, 1)).intValue());
        assertEq(2, map_.getVal(new KeyExample(1, 1)).intValue());
        assertEq(3, map_.getVal(new KeyExample(1, 0)).intValue());
    }

    @Test
    public void putAllMap4Test() {
        ObjectMap<KeyExample,Number> map_ = new ObjectMap<KeyExample,Number>();
        map_.put(new KeyExample(0, 0), 0);
        map_.put(new KeyExample(0, 1), 1);
        ObjectMap<KeyExample,Number> mapToPut_ = new ObjectMap<KeyExample,Number>();
        mapToPut_.put(new KeyExample(1, 1), 2);
        mapToPut_.put(new KeyExample(0, 1), 3);
        map_.putAllMap(mapToPut_);
        assertEq(3, map_.size());
        assertTrue(map_.contains(new KeyExample(0, 0)));
        assertTrue(map_.contains(new KeyExample(0, 1)));
        assertTrue(map_.contains(new KeyExample(1, 1)));
        assertEq(0, map_.getVal(new KeyExample(0, 0)).intValue());
        assertEq(3, map_.getVal(new KeyExample(0, 1)).intValue());
        assertEq(2, map_.getVal(new KeyExample(1, 1)).intValue());
    }

    @Test
    public void putAllMap5Test() {
        NumberMap<Integer,Number> map_ = new NumberMap<Integer,Number>(new NumberMap<Integer,Number>(new CollCapacity(0)));
        map_.put(0, 0);
        map_.put(1, 1);
        NumberMap<Integer,Number> mapToPut_ = new NumberMap<Integer,Number>();
        mapToPut_.put(2, 2);
        mapToPut_.put(3, 3);
        map_.putAllMap(mapToPut_);
        assertEq(4, map_.size());
        assertTrue(map_.contains(0));
        assertTrue(map_.contains(1));
        assertTrue(map_.contains(2));
        assertTrue(map_.contains(3));
        assertEq(0, map_.getVal(0).intValue());
        assertEq(1, map_.getVal(1).intValue());
        assertEq(2, map_.getVal(2).intValue());
        assertEq(3, map_.getVal(3).intValue());
    }

    @Test
    public void putAllMap6Test() {
        NumberMap<Integer,Number> map_ = new NumberMap<Integer,Number>();
        map_.put(0, 0);
        map_.put(1, 1);
        NumberMap<Integer,Number> mapToPut_ = new NumberMap<Integer,Number>();
        mapToPut_.put(2, 2);
        mapToPut_.put(1, 3);
        map_.putAllMap(mapToPut_);
        assertEq(3, map_.size());
        assertTrue(map_.contains(0));
        assertTrue(map_.contains(1));
        assertTrue(map_.contains(2));
        assertEq(0, map_.getVal(0).intValue());
        assertEq(3, map_.getVal(1).intValue());
        assertEq(2, map_.getVal(2).intValue());
    }

    @Test
    public void putAllMap7Test() {
        EnumMap<MyEnum,Number> map_ = new EnumMap<MyEnum,Number>(new EnumMap<MyEnum,Number>(new CollCapacity(0)));
        map_.put(MyEnum.ZERO, 0);
        map_.put(MyEnum.ONE, 1);
        EnumMap<MyEnum,Number> mapToPut_ = new EnumMap<MyEnum,Number>();
        mapToPut_.put(MyEnum.TWO, 2);
        mapToPut_.put(MyEnum.THREE, 3);
        map_.putAllMap(mapToPut_);
        assertEq(4, map_.size());
        assertTrue(map_.contains(MyEnum.ZERO));
        assertTrue(map_.contains(MyEnum.ONE));
        assertTrue(map_.contains(MyEnum.TWO));
        assertTrue(map_.contains(MyEnum.THREE));
        assertEq(0, map_.getVal(MyEnum.ZERO).intValue());
        assertEq(1, map_.getVal(MyEnum.ONE).intValue());
        assertEq(2, map_.getVal(MyEnum.TWO).intValue());
        assertEq(3, map_.getVal(MyEnum.THREE).intValue());
    }

    @Test
    public void putAllMap8Test() {
        EnumMap<MyEnum,Number> map_ = new EnumMap<MyEnum,Number>();
        map_.put(MyEnum.ZERO, 0);
        map_.put(MyEnum.ONE, 1);
        EnumMap<MyEnum,Number> mapToPut_ = new EnumMap<MyEnum,Number>();
        mapToPut_.put(MyEnum.TWO, 2);
        mapToPut_.put(MyEnum.ONE, 3);
        map_.putAllMap(mapToPut_);
        assertEq(3, map_.size());
        assertTrue(map_.contains(MyEnum.ZERO));
        assertTrue(map_.contains(MyEnum.ONE));
        assertTrue(map_.contains(MyEnum.TWO));
        assertEq(0, map_.getVal(MyEnum.ZERO).intValue());
        assertEq(3, map_.getVal(MyEnum.ONE).intValue());
        assertEq(2, map_.getVal(MyEnum.TWO).intValue());
    }

    @Test
    public void putAllMap9Test() {
        CharMap<Number> map_ = new CharMap<Number>(new CharMap<Number>(new CollCapacity(0)));
        map_.put((char)0, 0);
        map_.put((char)1, 1);
        CharMap<Number> mapToPut_ = new CharMap<Number>();
        mapToPut_.put((char)2, 2);
        mapToPut_.put((char)3, 3);
        map_.putAllMap(mapToPut_);
        assertEq(4, map_.size());
        assertTrue(map_.contains((char)0));
        assertTrue(map_.contains((char)1));
        assertTrue(map_.contains((char)2));
        assertTrue(map_.contains((char)3));
        assertEq(0, map_.getVal((char)0).intValue());
        assertEq(1, map_.getVal((char)1).intValue());
        assertEq(2, map_.getVal((char)2).intValue());
        assertEq(3, map_.getVal((char)3).intValue());
    }

    @Test
    public void putAllMap10Test() {
        CharMap<Number> map_ = new CharMap<Number>();
        map_.put((char)0, 0);
        map_.put((char)1, 1);
        CharMap<Number> mapToPut_ = new CharMap<Number>();
        mapToPut_.put((char)2, 2);
        mapToPut_.put((char)1, 3);
        map_.putAllMap(mapToPut_);
        assertEq(3, map_.size());
        assertTrue(map_.contains((char)0));
        assertTrue(map_.contains((char)1));
        assertTrue(map_.contains((char)2));
        assertEq(0, map_.getVal((char)0).intValue());
        assertEq(3, map_.getVal((char)1).intValue());
        assertEq(2, map_.getVal((char)2).intValue());
    }

    @Test
    public void putAllMap11Test() {
        BooleanMap<Number> map_ = new BooleanMap<Number>(new CollCapacity(0));
        map_.put(false, 0);
        BooleanMap<Number> mapToPut_ = new BooleanMap<Number>();
        mapToPut_.put(true, 2);
        map_.putAllMap(mapToPut_);
        assertEq(2, map_.size());
        assertTrue(map_.contains(false));
        assertTrue(map_.contains(true));
        assertEq(0, map_.getVal(false).intValue());
        assertEq(2, map_.getVal(true).intValue());
    }

    @Test
    public void putAllMap12Test() {
        BooleanMap<Number> map_ = new BooleanMap<Number>();
        map_.put(false, 0);
        BooleanMap<Number> mapToPut_ = new BooleanMap<Number>();
        mapToPut_.put(false, 2);
        map_.putAllMap(mapToPut_);
        assertEq(1, map_.size());
        assertTrue(map_.contains(false));
        assertEq(2, map_.getVal(false).intValue());
    }

    @Test
    public void putAllMap13Test() {
        StringMapObject map_ = new StringMapObject();
        map_.put("ONE", 1);
        map_.put("TWO", 2);
        StringMapObject mapToPut_ = new StringMapObject();
        mapToPut_.put("THREE", 3);
        mapToPut_.put("FOUR", 4);
        map_.putAllMap(mapToPut_);
        assertEq(4, map_.size());
        assertTrue(map_.contains("ONE"));
        assertTrue(map_.contains("TWO"));
        assertTrue(map_.contains("THREE"));
        assertTrue(map_.contains("FOUR"));
        assertEq(1, (Integer)map_.getVal("ONE"));
        assertEq(2, (Integer)map_.getVal("TWO"));
        assertEq(3, (Integer)map_.getVal("THREE"));
        assertEq(4, (Integer)map_.getVal("FOUR"));
    }

    @Test
    public void putAllMap14Test() {
        StringMapObject map_ = new StringMapObject();
        map_.put("ONE", 1);
        map_.put("TWO", 2);
        StringMapObject mapToPut_ = new StringMapObject();
        mapToPut_.put("TWO", 3);
        mapToPut_.put("THREE", 4);
        map_.putAllMap(mapToPut_);
        assertEq(3, map_.size());
        assertTrue(map_.contains("ONE"));
        assertTrue(map_.contains("TWO"));
        assertTrue(map_.contains("THREE"));
        assertEq(1, (Integer)map_.getVal("ONE"));
        assertEq(3, (Integer)map_.getVal("TWO"));
        assertEq(4, (Integer)map_.getVal("THREE"));
    }
    @Test
    public void valuesTest() {
        ObjectMap<KeyExample,Number> map_ = new ObjectMap<KeyExample,Number>();
        map_.put(new KeyExample(0, 0), 0);
        map_.put(new KeyExample(0, 1), 1);
        CustList<Number> values_ = map_.values();
        assertEq(2, values_.size());
        assertEq(0, values_.first());
        assertEq(1, values_.last());
    }

    @Test
    public void add1Test() {
        ObjectMap<KeyExample,Number> map_ = new ObjectMap<KeyExample,Number>();
        map_.put(new KeyExample(0, 0), 0);
        map_.put(new KeyExample(0, 1), 1);
        map_.add(new KeyExample(0, 0), 2);
        assertEq(2,map_.size());
        assertTrue(map_.contains(new KeyExample(0, 0)));
        assertTrue(map_.contains(new KeyExample(0, 1)));
        assertEq(0, map_.getVal(new KeyExample(0, 0)));
        assertEq(1, map_.getVal(new KeyExample(0, 1)));
    }

    @Test
    public void add2Test() {
        ObjectMap<KeyExample,Number> map_ = new ObjectMap<KeyExample,Number>();
        map_.put(new KeyExample(0, 0), 0);
        map_.put(new KeyExample(0, 1), 1);
        map_.add(new KeyExample(0, 2), 2);
        assertEq(3,map_.size());
        assertTrue(map_.contains(new KeyExample(0, 0)));
        assertTrue(map_.contains(new KeyExample(0, 1)));
        assertTrue(map_.contains(new KeyExample(0, 2)));
        assertEq(0, map_.getVal(new KeyExample(0, 0)));
        assertEq(1, map_.getVal(new KeyExample(0, 1)));
        assertEq(2, map_.getVal(new KeyExample(0, 2)));
    }

    @Test
    public void set1Test() {
        ObjectMap<KeyExample,Number> map_ = new ObjectMap<KeyExample,Number>();
        map_.put(new KeyExample(0, 0), 0);
        map_.put(new KeyExample(0, 1), 1);
        map_.set(new KeyExample(0, 0), 2);
        assertEq(2,map_.size());
        assertTrue(map_.contains(new KeyExample(0, 0)));
        assertTrue(map_.contains(new KeyExample(0, 1)));
        assertEq(2, map_.getVal(new KeyExample(0, 0)));
        assertEq(1, map_.getVal(new KeyExample(0, 1)));
    }

    @Test
    public void set2Test() {
        ObjectMap<KeyExample,Number> map_ = new ObjectMap<KeyExample,Number>();
        map_.put(new KeyExample(0, 0), 0);
        map_.put(new KeyExample(0, 1), 1);
        map_.set(new KeyExample(0, 2), 2);
        assertEq(2,map_.size());
        assertTrue(map_.contains(new KeyExample(0, 0)));
        assertTrue(map_.contains(new KeyExample(0, 1)));
        assertTrue(!map_.contains(new KeyExample(0, 2)));
        assertEq(0, map_.getVal(new KeyExample(0, 0)));
        assertEq(1, map_.getVal(new KeyExample(0, 1)));
    }
    @Test
    public void containsAllAsKeys1Test() {
        ObjectMap<KeyExample,Number> map_ = new ObjectMap<KeyExample,Number>();
        map_.put(new KeyExample(0, 0), 0);
        map_.put(new KeyExample(0, 1), 1);
        CustList<KeyExample> elts_ =new CustList<KeyExample>();
        elts_.add(new KeyExample(0, 0));
        elts_.add(new KeyExample(0, 2));
        assertTrue(!map_.containsAllAsKeys(elts_));
    }
    @Test
    public void containsAllAsKeys2Test() {
        ObjectMap<KeyExample,Number> map_ = new ObjectMap<KeyExample,Number>();
        map_.put(new KeyExample(0, 0), 0);
        map_.put(new KeyExample(0, 1), 1);
        CustList<KeyExample> elts_ =new CustList<KeyExample>();
        elts_.add(new KeyExample(0, 0));
        assertTrue(map_.containsAllAsKeys(elts_));
    }
    @Test
    public void containsAllAsKeys3Test() {
        ObjectMap<KeyExample,Number> map_ = new ObjectMap<KeyExample,Number>();
        map_.put(new KeyExample(0, 0), 0);
        map_.put(new KeyExample(0, 1), 1);
        CustList<KeyExample> elts_ =new CustList<KeyExample>();
        assertTrue(map_.containsAllAsKeys(elts_));
    }
    @Test
    public void methods1Test() {
        ObjectMap<KeyExample,Number> map_ = new ObjectMap<KeyExample,Number>();
        map_.put(new KeyExample(0, 0), 0);
        map_.put(new KeyExample(0, 1), 1);
        assertTrue(!map_.isEmpty());
        assertEq(new KeyExample(0, 0),map_.firstKey());
        assertEq(new KeyExample(0, 1),map_.lastKey());
        assertEq(0,map_.firstValue());
        assertEq(1,map_.lastValue());
        map_.setKey(0,new KeyExample(0, 2));
        assertEq(new KeyExample(0, 2),map_.firstKey());
        assertEq(new KeyExample(0, 2),map_.get(0).getKey());
        assertEq(new KeyExample(0, 1),map_.lastKey());
        assertNotNull(map_.entries());
    }
    @Test
    public void methods2Test() {
        ObjectMap<KeyExample,Number> map_ = new ObjectMap<KeyExample,Number>();
        map_.put(new KeyExample(0, 0), 0);
        map_.put(new KeyExample(0, 1), 1);
        map_.clear();
        assertTrue(map_.isEmpty());
    }
    @Test
    public void getKeysEqTest() {
        ObjectMap<KeyExample,Number> map_ = new ObjectMap<KeyExample,Number>();
        map_.put(new KeyExample(0, 0), 0);
        map_.put(new KeyExample(0, 1), 1);
        CustList<KeyExample> elts_ = map_.getKeys();
        assertEq(2,elts_.size());
        assertEq(new KeyExample(0, 0),elts_.first());
        assertEq(new KeyExample(0, 1),elts_.last());
    }
    @Test
    public void getKeysNbTest() {
        NumberMap<Integer,Number> map_ = new NumberMap<Integer,Number>();
        map_.put(0, 0);
        map_.put(1, 1);
        CustList<Integer> elts_ = map_.getKeys();
        assertEq(2,elts_.size());
        assertEq(0,elts_.first());
        assertEq(1,elts_.last());
    }
    @Test
    public void getKeysBoolTest() {
        BooleanMap<Number> map_ = new BooleanMap<Number>();
        map_.put(true, 0);
        map_.put(false, 1);
        CustList<Boolean> elts_ = map_.getKeys();
        assertEq(2,elts_.size());
        assertEq(true,elts_.first());
        assertEq(false,elts_.last());
    }
    @Test
    public void getKeysCharTest() {
        CharMap<Number> map_ = new CharMap<Number>();
        map_.put((char)0, 0);
        map_.put((char)1, 1);
        CustList<Character> elts_ = map_.getKeys();
        assertEq(2,elts_.size());
        assertEq(0,elts_.first());
        assertEq(1,elts_.last());
    }
    @Test
    public void getKeysEmTest() {
        EnumMap<MyEnum,Number> map_ = new EnumMap<MyEnum,Number>();
        map_.put(MyEnum.ZERO, 0);
        map_.put(MyEnum.ONE, 1);
        CustList<MyEnum> elts_ = map_.getKeys();
        assertEq(2,elts_.size());
        assertSame(MyEnum.ZERO,elts_.first());
        assertSame(MyEnum.ONE,elts_.last());
    }
    @Test
    public void getKeysStrObjTest() {
        StringMapObject map_ = new StringMapObject();
        map_.put("", 0);
        CustList<String> elts_ = map_.getKeys();
        assertEq(1,elts_.size());
        assertEq("",elts_.first());
    }
    private static boolean containsEntry(Listable<EntryCust<String,Number>> _l, EntryCust<String,Number> _e) {
        for (EntryCust<String,Number> e: _l) {
            if (StringList.quickEq(e.getKey(), _e.getKey())) {
                if (Numbers.eq(e.getValue(), _e.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }
    private static boolean containsEntryTwo(Listable<EntryCust<KeyExample,Number>> _l, EntryCust<KeyExample,Number> _e) {
        for (EntryCust<KeyExample,Number> e: _l) {
            if (e.getKey().eq(_e.getKey())) {
                if (Numbers.eq(e.getValue(), _e.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }
}
