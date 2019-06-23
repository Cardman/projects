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
        StringMap<Integer> map_ = new StringMap<Integer>(new StringMap<Integer>(new CollCapacity(0)));
        Listable<EntryCust<String,Integer>> l_ = map_.getList();
        assertEq(0, l_.size());
        map_.put("ONE", 1);
        assertEq(1, l_.size());
        assertTrue(containsEntry(l_, new EntryCust<String, Integer>("ONE", 1)));
        map_.put("TWO", 2);
        assertEq(2, l_.size());
        assertTrue(containsEntry(l_, new EntryCust<String, Integer>("ONE", 1)));
        assertTrue(containsEntry(l_, new EntryCust<String, Integer>("TWO", 2)));
        map_.put("TWO", 3);
        assertEq(2, l_.size());
        assertTrue(containsEntry(l_, new EntryCust<String, Integer>("ONE", 1)));
        assertTrue(containsEntry(l_, new EntryCust<String, Integer>("TWO", 3)));
        ObjectMap<KeyExample,Integer> otherMap_ = new ObjectMap<KeyExample,Integer>();
        Listable<EntryCust<KeyExample,Integer>> lOther_ = otherMap_.getList();
        KeyExample k1_ = new KeyExample(0, 0);
        KeyExample k2_ = new KeyExample(0, 1);
        KeyExample k3_ = new KeyExample(0, 1);
        assertEq(0, lOther_.size());
        otherMap_.put(k1_, 0);
        assertEq(1, lOther_.size());
        assertTrue(containsEntryTwo(lOther_, new EntryCust<KeyExample, Integer>(k1_, 0)));
        otherMap_.put(k2_, 1);
        assertEq(2, lOther_.size());
        assertTrue(containsEntryTwo(lOther_, new EntryCust<KeyExample, Integer>(k1_, 0)));
        assertTrue(containsEntryTwo(lOther_, new EntryCust<KeyExample, Integer>(k2_, 1)));
        otherMap_.put(k3_, 2);
        assertEq(2, lOther_.size());
        assertTrue(containsEntryTwo(lOther_, new EntryCust<KeyExample, Integer>(k1_, 0)));
        assertTrue(containsEntryTwo(lOther_, new EntryCust<KeyExample, Integer>(k2_, 2)));
    }

    @Test
    public void size1Test() {
        StringMap<Integer> map_ = new StringMap<Integer>();
        assertEq(0, map_.size());
        map_.put("ONE", 1);
        assertEq(1,map_.size());
        map_.put("TWO", 2);
        assertEq(2,map_.size());
        map_.put("TWO", 3);
        assertEq(2,map_.size());
        ObjectMap<KeyExample,Integer> otherMap_ = new ObjectMap<KeyExample,Integer>();
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
        StringMap<Integer> map_ = new StringMap<Integer>();
        assertEq(0, map_.size());
        map_.put("ONE", 1);
        assertTrue(map_.contains("ONE"));
        map_.put("TWO", 2);
        assertTrue(map_.contains("ONE"));
        assertTrue(map_.contains("TWO"));
        map_.put("TWO", 3);
        assertTrue(map_.contains("ONE"));
        assertTrue(map_.contains("TWO"));
        ObjectMap<KeyExample,Integer> otherMap_ = new ObjectMap<KeyExample,Integer>();
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
        StringMap<Integer> map_ = new StringMap<Integer>();
        map_.put("ONE", 1);
        assertEq(1,map_.getVal("ONE"));
        assertNull(map_.getVal("TWO"));
        map_.put("TWO", 2);
        assertEq(1,map_.getVal("ONE"));
        assertEq(2,map_.getVal("TWO"));
        map_.put("TWO", 3);
        assertEq(1,map_.getVal("ONE"));
        assertEq(3,map_.getVal("TWO"));
        ObjectMap<KeyExample,Integer> otherMap_ = new ObjectMap<KeyExample,Integer>();
        otherMap_.put(new KeyExample(0, 0), 0);
        assertEq(0, otherMap_.getVal(new KeyExample(0, 0)));
        otherMap_.put(new KeyExample(0, 1), 1);
        assertEq(0, otherMap_.getVal(new KeyExample(0, 0)));
        assertEq(1, otherMap_.getVal(new KeyExample(0, 1)));
        otherMap_.put(new KeyExample(0, 1), 2);
        assertEq(0, otherMap_.getVal(new KeyExample(0, 0)));
        assertEq(2, otherMap_.getVal(new KeyExample(0, 1)));
    }

    @Test
    public void removeKey1Test() {
        StringMap<Integer> map_ = new StringMap<Integer>();
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
        ObjectMap<KeyExample,Integer> otherMap_ = new ObjectMap<KeyExample,Integer>();
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
        StringMap<Integer> map_ = new StringMap<Integer>();
        map_.put("ONE", 1);
        map_.put("TWO", 3);
        map_.move("TWO", "THREE");
        assertEq(2,map_.size());
        assertTrue(map_.contains("ONE"));
        assertTrue(map_.contains("THREE"));
        assertEq(1,map_.getVal("ONE"));
        assertEq(3,map_.getVal("THREE"));
        map_.move("ZERO", "THREE");
        assertEq(2,map_.size());
        assertTrue(map_.contains("ONE"));
        assertTrue(map_.contains("THREE"));
        assertEq(1,map_.getVal("ONE"));
        assertEq(3,map_.getVal("THREE"));
    }

    @Test
    public void putAllMap1Test() {
        StringMap<Integer> map_ = new StringMap<Integer>();
        map_.put("ONE", 1);
        map_.put("TWO", 2);
        StringMap<Integer> mapToPut_ = new StringMap<Integer>();
        mapToPut_.put("THREE", 3);
        mapToPut_.put("FOUR", 4);
        map_.putAllMap(mapToPut_);
        assertEq(4, map_.size());
        assertTrue(map_.contains("ONE"));
        assertTrue(map_.contains("TWO"));
        assertTrue(map_.contains("THREE"));
        assertTrue(map_.contains("FOUR"));
        assertEq(1, map_.getVal("ONE"));
        assertEq(2, map_.getVal("TWO"));
        assertEq(3, map_.getVal("THREE"));
        assertEq(4, map_.getVal("FOUR"));
    }

    @Test
    public void putAllMap2Test() {
        StringMap<Integer> map_ = new StringMap<Integer>();
        map_.put("ONE", 1);
        map_.put("TWO", 2);
        StringMap<Integer> mapToPut_ = new StringMap<Integer>();
        mapToPut_.put("TWO", 3);
        mapToPut_.put("THREE", 4);
        map_.putAllMap(mapToPut_);
        assertEq(3, map_.size());
        assertTrue(map_.contains("ONE"));
        assertTrue(map_.contains("TWO"));
        assertTrue(map_.contains("THREE"));
        assertEq(1, map_.getVal("ONE"));
        assertEq(3, map_.getVal("TWO"));
        assertEq(4, map_.getVal("THREE"));
    }

    @Test
    public void putAllMap3Test() {
        ObjectMap<KeyExample,Integer> map_ = new ObjectMap<KeyExample,Integer>(new ObjectMap<KeyExample,Integer>(new CollCapacity(0)));
        map_.put(new KeyExample(0, 0), 0);
        map_.put(new KeyExample(0, 1), 1);
        ObjectMap<KeyExample,Integer> mapToPut_ = new ObjectMap<KeyExample,Integer>();
        mapToPut_.put(new KeyExample(1, 1), 2);
        mapToPut_.put(new KeyExample(1, 0), 3);
        map_.putAllMap(mapToPut_);
        assertEq(4, map_.size());
        assertTrue(map_.contains(new KeyExample(0, 0)));
        assertTrue(map_.contains(new KeyExample(0, 1)));
        assertTrue(map_.contains(new KeyExample(1, 1)));
        assertTrue(map_.contains(new KeyExample(1, 0)));
        assertEq(0, map_.getVal(new KeyExample(0, 0)));
        assertEq(1, map_.getVal(new KeyExample(0, 1)));
        assertEq(2, map_.getVal(new KeyExample(1, 1)));
        assertEq(3, map_.getVal(new KeyExample(1, 0)));
    }

    @Test
    public void putAllMap4Test() {
        ObjectMap<KeyExample,Integer> map_ = new ObjectMap<KeyExample,Integer>();
        map_.put(new KeyExample(0, 0), 0);
        map_.put(new KeyExample(0, 1), 1);
        ObjectMap<KeyExample,Integer> mapToPut_ = new ObjectMap<KeyExample,Integer>();
        mapToPut_.put(new KeyExample(1, 1), 2);
        mapToPut_.put(new KeyExample(0, 1), 3);
        map_.putAllMap(mapToPut_);
        assertEq(3, map_.size());
        assertTrue(map_.contains(new KeyExample(0, 0)));
        assertTrue(map_.contains(new KeyExample(0, 1)));
        assertTrue(map_.contains(new KeyExample(1, 1)));
        assertEq(0, map_.getVal(new KeyExample(0, 0)));
        assertEq(3, map_.getVal(new KeyExample(0, 1)));
        assertEq(2, map_.getVal(new KeyExample(1, 1)));
    }

    @Test
    public void putAllMap5Test() {
        IntMap<Integer> map_ = new IntMap<Integer>(new IntMap<Integer>(new CollCapacity(0)));
        map_.put(0, 0);
        map_.put(1, 1);
        IntMap<Integer> mapToPut_ = new IntMap<Integer>();
        mapToPut_.put(2, 2);
        mapToPut_.put(3, 3);
        map_.putAllMap(mapToPut_);
        assertEq(4, map_.size());
        assertTrue(map_.contains(0));
        assertTrue(map_.contains(1));
        assertTrue(map_.contains(2));
        assertTrue(map_.contains(3));
        assertEq(0, map_.getVal(0));
        assertEq(1, map_.getVal(1));
        assertEq(2, map_.getVal(2));
        assertEq(3, map_.getVal(3));
    }

    @Test
    public void putAllMap6Test() {
        IntMap<Integer> map_ = new IntMap<Integer>();
        map_.put(0, 0);
        map_.put(1, 1);
        IntMap<Integer> mapToPut_ = new IntMap<Integer>();
        mapToPut_.put(2, 2);
        mapToPut_.put(1, 3);
        map_.putAllMap(mapToPut_);
        assertEq(3, map_.size());
        assertTrue(map_.contains(0));
        assertTrue(map_.contains(1));
        assertTrue(map_.contains(2));
        assertEq(0, map_.getVal(0));
        assertEq(3, map_.getVal(1));
        assertEq(2, map_.getVal(2));
    }

    @Test
    public void putAllMap7Test() {
        EnumMap<MyEnum,Integer> map_ = new EnumMap<MyEnum,Integer>(new EnumMap<MyEnum,Integer>(new CollCapacity(0)));
        map_.put(MyEnum.ZERO, 0);
        map_.put(MyEnum.ONE, 1);
        EnumMap<MyEnum,Integer> mapToPut_ = new EnumMap<MyEnum,Integer>();
        mapToPut_.put(MyEnum.TWO, 2);
        mapToPut_.put(MyEnum.THREE, 3);
        map_.putAllMap(mapToPut_);
        assertEq(4, map_.size());
        assertTrue(map_.contains(MyEnum.ZERO));
        assertTrue(map_.contains(MyEnum.ONE));
        assertTrue(map_.contains(MyEnum.TWO));
        assertTrue(map_.contains(MyEnum.THREE));
        assertEq(0, map_.getVal(MyEnum.ZERO));
        assertEq(1, map_.getVal(MyEnum.ONE));
        assertEq(2, map_.getVal(MyEnum.TWO));
        assertEq(3, map_.getVal(MyEnum.THREE));
    }

    @Test
    public void putAllMap8Test() {
        EnumMap<MyEnum,Integer> map_ = new EnumMap<MyEnum,Integer>();
        map_.put(MyEnum.ZERO, 0);
        map_.put(MyEnum.ONE, 1);
        EnumMap<MyEnum,Integer> mapToPut_ = new EnumMap<MyEnum,Integer>();
        mapToPut_.put(MyEnum.TWO, 2);
        mapToPut_.put(MyEnum.ONE, 3);
        map_.putAllMap(mapToPut_);
        assertEq(3, map_.size());
        assertTrue(map_.contains(MyEnum.ZERO));
        assertTrue(map_.contains(MyEnum.ONE));
        assertTrue(map_.contains(MyEnum.TWO));
        assertEq(0, map_.getVal(MyEnum.ZERO));
        assertEq(3, map_.getVal(MyEnum.ONE));
        assertEq(2, map_.getVal(MyEnum.TWO));
    }

    @Test
    public void putAllMap9Test() {
        CharMap<Integer> map_ = new CharMap<Integer>(new CharMap<Integer>(new CollCapacity(0)));
        map_.put((char)0, 0);
        map_.put((char)1, 1);
        CharMap<Integer> mapToPut_ = new CharMap<Integer>();
        mapToPut_.put((char)2, 2);
        mapToPut_.put((char)3, 3);
        map_.putAllMap(mapToPut_);
        assertEq(4, map_.size());
        assertTrue(map_.contains((char)0));
        assertTrue(map_.contains((char)1));
        assertTrue(map_.contains((char)2));
        assertTrue(map_.contains((char)3));
        assertEq(0, map_.getVal((char)0));
        assertEq(1, map_.getVal((char)1));
        assertEq(2, map_.getVal((char)2));
        assertEq(3, map_.getVal((char)3));
    }

    @Test
    public void putAllMap10Test() {
        CharMap<Integer> map_ = new CharMap<Integer>();
        map_.put((char)0, 0);
        map_.put((char)1, 1);
        CharMap<Integer> mapToPut_ = new CharMap<Integer>();
        mapToPut_.put((char)2, 2);
        mapToPut_.put((char)1, 3);
        map_.putAllMap(mapToPut_);
        assertEq(3, map_.size());
        assertTrue(map_.contains((char)0));
        assertTrue(map_.contains((char)1));
        assertTrue(map_.contains((char)2));
        assertEq(0, map_.getVal((char)0));
        assertEq(3, map_.getVal((char)1));
        assertEq(2, map_.getVal((char)2));
    }

    @Test
    public void putAllMap11Test() {
        BooleanMap<Integer> map_ = new BooleanMap<Integer>(new CollCapacity(0));
        map_.put(false, 0);
        BooleanMap<Integer> mapToPut_ = new BooleanMap<Integer>();
        mapToPut_.put(true, 2);
        map_.putAllMap(mapToPut_);
        assertEq(2, map_.size());
        assertTrue(map_.contains(false));
        assertTrue(map_.contains(true));
        assertEq(0, map_.getVal(false));
        assertEq(2, map_.getVal(true));
    }

    @Test
    public void putAllMap12Test() {
        BooleanMap<Integer> map_ = new BooleanMap<Integer>();
        map_.put(false, 0);
        BooleanMap<Integer> mapToPut_ = new BooleanMap<Integer>();
        mapToPut_.put(false, 2);
        map_.putAllMap(mapToPut_);
        assertEq(1, map_.size());
        assertTrue(map_.contains(false));
        assertEq(2, map_.getVal(false));
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
        ObjectMap<KeyExample,Integer> map_ = new ObjectMap<KeyExample,Integer>();
        map_.put(new KeyExample(0, 0), 0);
        map_.put(new KeyExample(0, 1), 1);
        CustList<Integer> values_ = map_.values();
        assertEq(2, values_.size());
        assertEq(0, values_.first());
        assertEq(1, values_.last());
    }

    @Test
    public void add1Test() {
        ObjectMap<KeyExample,Integer> map_ = new ObjectMap<KeyExample,Integer>();
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
        ObjectMap<KeyExample,Integer> map_ = new ObjectMap<KeyExample,Integer>();
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
        ObjectMap<KeyExample,Integer> map_ = new ObjectMap<KeyExample,Integer>();
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
        ObjectMap<KeyExample,Integer> map_ = new ObjectMap<KeyExample,Integer>();
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
        ObjectMap<KeyExample,Integer> map_ = new ObjectMap<KeyExample,Integer>();
        map_.put(new KeyExample(0, 0), 0);
        map_.put(new KeyExample(0, 1), 1);
        CustList<KeyExample> elts_ =new CustList<KeyExample>();
        elts_.add(new KeyExample(0, 0));
        elts_.add(new KeyExample(0, 2));
        assertTrue(!map_.containsAllAsKeys(elts_));
    }
    @Test
    public void containsAllAsKeys2Test() {
        ObjectMap<KeyExample,Integer> map_ = new ObjectMap<KeyExample,Integer>();
        map_.put(new KeyExample(0, 0), 0);
        map_.put(new KeyExample(0, 1), 1);
        CustList<KeyExample> elts_ =new CustList<KeyExample>();
        elts_.add(new KeyExample(0, 0));
        assertTrue(map_.containsAllAsKeys(elts_));
    }
    @Test
    public void containsAllAsKeys3Test() {
        ObjectMap<KeyExample,Integer> map_ = new ObjectMap<KeyExample,Integer>();
        map_.put(new KeyExample(0, 0), 0);
        map_.put(new KeyExample(0, 1), 1);
        CustList<KeyExample> elts_ =new CustList<KeyExample>();
        assertTrue(map_.containsAllAsKeys(elts_));
    }
    @Test
    public void methods1Test() {
        ObjectMap<KeyExample,Integer> map_ = new ObjectMap<KeyExample,Integer>();
        map_.put(new KeyExample(0, 0), 0);
        map_.put(new KeyExample(0, 1), 1);
        assertTrue(!map_.isEmpty());
        assertEq(new KeyExample(0, 0),map_.firstKey());
        assertEq(new KeyExample(0, 1),map_.lastKey());
        assertEq(0,map_.firstValue());
        assertEq(1,map_.lastValue());
        map_.setKey(0,new KeyExample(0, 2));
        assertEq(new KeyExample(0, 2),map_.firstKey());
        assertNotNull(map_.getObj(0));
        assertEq(new KeyExample(0, 2),map_.getKey(0));
        assertEq(new KeyExample(0, 1),map_.lastKey());
        assertNotNull(map_.entries());
    }
    @Test
    public void methods2Test() {
        ObjectMap<KeyExample,Integer> map_ = new ObjectMap<KeyExample,Integer>();
        map_.put(new KeyExample(0, 0), 0);
        map_.put(new KeyExample(0, 1), 1);
        map_.clear();
        assertTrue(map_.isEmpty());
    }
    @Test
    public void getKeysEqTest() {
        ObjectMap<KeyExample,Integer> map_ = new ObjectMap<KeyExample,Integer>();
        map_.put(new KeyExample(0, 0), 0);
        map_.put(new KeyExample(0, 1), 1);
        CustList<KeyExample> elts_ = map_.getKeys();
        assertEq(2,elts_.size());
        assertEq(new KeyExample(0, 0),elts_.first());
        assertEq(new KeyExample(0, 1),elts_.last());
    }
    @Test
    public void getKeysNb1Test() {
        IntMap<Integer> map_ = new IntMap<Integer>();
        map_.put(0, 0);
        map_.put(1, 1);
        CustList<Integer> elts_ = map_.getKeys();
        assertEq(2,elts_.size());
        assertEq(0,elts_.first());
        assertEq(1,elts_.last());
    }
    @Test
    public void getKeysNb2Test() {
        ByteMap<Integer> mapEmpty_ = new ByteMap<Integer>(new CollCapacity(2));
        ByteMap<Integer> map_ = new ByteMap<Integer>(mapEmpty_);
        map_.put((byte) 0, 0);
        map_.put((byte) 1, 1);
        ByteMap<Integer> mapTwo_ = new ByteMap<Integer>(map_);
        CustList<Byte> elts_ = mapTwo_.getKeys();
        assertEq(2,elts_.size());
        assertEq(0,elts_.first());
        assertEq(1,elts_.last());
        mapEmpty_ = new ByteMap<Integer>();
        elts_ = mapEmpty_.getKeys();
        assertEq(0,elts_.size());
    }
    @Test
    public void getKeysNb3Test() {
        LongMap<Integer> map_ = new LongMap<Integer>();
        map_.put(0L, 0);
        map_.put(1L, 1);
        CustList<Long> elts_ = map_.getKeys();
        assertEq(2,elts_.size());
        assertEq(0,elts_.first());
        assertEq(1,elts_.last());
        LongMap<Integer> mapEmpty_ = new LongMap<Integer>(new CollCapacity(2));
        elts_ = mapEmpty_.getKeys();
        assertEq(0,elts_.size());
    }
    @Test
    public void getKeysNb4Test() {
        ShortMap<Integer> map_ = new ShortMap<Integer>();
        map_.put((short) 0, 0);
        map_.put((short) 1, 1);
        CustList<Short> elts_ = map_.getKeys();
        assertEq(2,elts_.size());
        assertEq(0,elts_.first());
        assertEq(1,elts_.last());
        ShortMap<Integer> mapEmpty_ = new ShortMap<Integer>(new CollCapacity(2));
        elts_ = mapEmpty_.getKeys();
        assertEq(0,elts_.size());
    }
    @Test
    public void getKeysBoolTest() {
        BooleanMap<Integer> map_ = new BooleanMap<Integer>();
        map_.put(true, 0);
        map_.put(false, 1);
        CustList<Boolean> elts_ = map_.getKeys();
        assertEq(2,elts_.size());
        assertEq(true,elts_.first());
        assertEq(false,elts_.last());
    }
    @Test
    public void getKeysCharTest() {
        CharMap<Integer> map_ = new CharMap<Integer>();
        map_.put((char)0, 0);
        map_.put((char)1, 1);
        CustList<Character> elts_ = map_.getKeys();
        assertEq(2,elts_.size());
        assertEq(0,elts_.first());
        assertEq(1,elts_.last());
    }
    @Test
    public void getKeysEmTest() {
        EnumMap<MyEnum,Integer> map_ = new EnumMap<MyEnum,Integer>();
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
    private static boolean containsEntry(Listable<EntryCust<String,Integer>> _l, EntryCust<String,Integer> _e) {
        for (EntryCust<String,Integer> e: _l) {
            if (StringList.quickEq(e.getKey(), _e.getKey())) {
                if (Numbers.eq(e.getValue(), _e.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }
    private static boolean containsEntryTwo(Listable<EntryCust<KeyExample,Integer>> _l, EntryCust<KeyExample,Integer> _e) {
        for (EntryCust<KeyExample,Integer> e: _l) {
            if (e.getKey().eq(_e.getKey())) {
                if (Numbers.eq(e.getValue(), _e.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }
}
