package code.util;

import code.threads.ConcreteInteger;
import code.util.ints.Listable;
import org.junit.Test;


public class MapTest extends EquallableExUtil {

    @Test
    public void put1Test() {
        StringMap<Integer> map_ = new StringMap<Integer>(new StringMap<Integer>(0));
        Listable<EntryCust<String,Integer>> l_ = map_.getList();
        assertEq(0, l_.size());
        map_.put("ONE", 1);
        assertEq(1, l_.size());
        assertEq("ONE", map_.getKey(0));
        assertEq(1, map_.getValue(0));
        map_.put("TWO", 2);
        assertEq(2, l_.size());
        assertEq("ONE", map_.getKey(0));
        assertEq(1, map_.getValue(0));
        assertEq("TWO", map_.getKey(1));
        assertEq(2, map_.getValue(1));
        map_.put("TWO", 3);
        assertEq(2, l_.size());
        assertEq("ONE", map_.getKey(0));
        assertEq(1, map_.getValue(0));
        assertEq("TWO", map_.getKey(1));
        assertEq(3, map_.getValue(1));
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
        IdMap<ConcreteInteger,Integer> map_ = new IdMap<ConcreteInteger,Integer>(new IdMap<ConcreteInteger,Integer>(new CollCapacity(0)));
        ConcreteInteger zero_ = new ConcreteInteger(0);
        map_.put(zero_, 0);
        ConcreteInteger one_ = new ConcreteInteger(1);
        map_.put(one_, 1);
        IdMap<ConcreteInteger,Integer> mapToPut_ = new IdMap<ConcreteInteger,Integer>();
        ConcreteInteger two_ = new ConcreteInteger(2);
        mapToPut_.put(two_, 2);
        ConcreteInteger three_ = new ConcreteInteger(3);
        mapToPut_.put(three_, 3);
        map_.putAllMap(mapToPut_);
        assertEq(4, map_.size());
        assertTrue(map_.contains(zero_));
        assertTrue(map_.contains(one_));
        assertTrue(map_.contains(two_));
        assertTrue(map_.contains(three_));
        assertEq(0, map_.getVal(zero_));
        assertEq(1, map_.getVal(one_));
        assertEq(2, map_.getVal(two_));
        assertEq(3, map_.getVal(three_));
    }

    @Test
    public void putAllMap8Test() {
        IdMap<ConcreteInteger,Integer> map_ = new IdMap<ConcreteInteger,Integer>();
        ConcreteInteger zero_ = new ConcreteInteger(0);
        map_.put(zero_, 0);
        ConcreteInteger one_ = new ConcreteInteger(1);
        map_.put(one_, 1);
        IdMap<ConcreteInteger,Integer> mapToPut_ = new IdMap<ConcreteInteger,Integer>();
        ConcreteInteger two_ = new ConcreteInteger(2);
        mapToPut_.put(two_, 2);
        mapToPut_.put(one_, 3);
        map_.putAllMap(mapToPut_);
        assertEq(3, map_.size());
        assertTrue(map_.contains(zero_));
        assertTrue(map_.contains(one_));
        assertTrue(map_.contains(two_));
        assertEq(0, map_.getVal(zero_));
        assertEq(3, map_.getVal(one_));
        assertEq(2, map_.getVal(two_));
    }

//    @Test
//    public void putAllMap9Test() {
//        CharMap<Integer> map_ = new CharMap<Integer>(new CharMap<Integer>(new CollCapacity(0)));
//        map_.put((char)0, 0);
//        map_.put((char)1, 1);
//        CharMap<Integer> mapToPut_ = new CharMap<Integer>();
//        mapToPut_.put((char)2, 2);
//        mapToPut_.put((char)3, 3);
//        map_.putAllMap(mapToPut_);
//        assertEq(4, map_.size());
//        assertTrue(map_.contains((char)0));
//        assertTrue(map_.contains((char)1));
//        assertTrue(map_.contains((char)2));
//        assertTrue(map_.contains((char)3));
//        assertEq(0, map_.getVal((char)0));
//        assertEq(1, map_.getVal((char)1));
//        assertEq(2, map_.getVal((char)2));
//        assertEq(3, map_.getVal((char)3));
//    }
//
//    @Test
//    public void putAllMap10Test() {
//        CharMap<Integer> map_ = new CharMap<Integer>();
//        map_.put((char)0, 0);
//        map_.put((char)1, 1);
//        CharMap<Integer> mapToPut_ = new CharMap<Integer>();
//        mapToPut_.put((char)2, 2);
//        mapToPut_.put((char)1, 3);
//        map_.putAllMap(mapToPut_);
//        assertEq(3, map_.size());
//        assertTrue(map_.contains((char)0));
//        assertTrue(map_.contains((char)1));
//        assertTrue(map_.contains((char)2));
//        assertEq(0, map_.getVal((char)0));
//        assertEq(3, map_.getVal((char)1));
//        assertEq(2, map_.getVal((char)2));
//    }

//    @Test
//    public void putAllMap11Test() {
//        BooleanMap<Integer> map_ = new BooleanMap<Integer>(new CollCapacity(0));
//        map_.put(false, 0);
//        BooleanMap<Integer> mapToPut_ = new BooleanMap<Integer>();
//        mapToPut_.put(true, 2);
//        map_.putAllMap(mapToPut_);
//        assertEq(2, map_.size());
//        assertTrue(map_.contains(false));
//        assertTrue(map_.contains(true));
//        assertEq(0, map_.getVal(false));
//        assertEq(2, map_.getVal(true));
//    }
//
//    @Test
//    public void putAllMap12Test() {
//        BooleanMap<Integer> map_ = new BooleanMap<Integer>();
//        map_.put(false, 0);
//        BooleanMap<Integer> mapToPut_ = new BooleanMap<Integer>();
//        mapToPut_.put(false, 2);
//        map_.putAllMap(mapToPut_);
//        assertEq(1, map_.size());
//        assertTrue(map_.contains(false));
//        assertEq(2, map_.getVal(false));
//    }

    @Test
    public void putAllEntries1Test() {
        StringMap<Integer> map_ = new StringMap<Integer>();
        map_.put("ONE", 1);
        map_.put("TWO", 2);
        StringMap<Integer> mapToPut_ = new StringMap<Integer>();
        mapToPut_.put("THREE", 3);
        mapToPut_.put("FOUR", 4);
        map_.addAllEntries(mapToPut_);
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
    public void isValidIndexTest() {
        StringMap<Integer> map_ = new StringMap<Integer>();
        map_.put("ONE", 1);
        assertTrue(map_.isValidIndex(0));
        assertTrue(!map_.isValidIndex(-1));
    }

    @Test
    public void removeIndexTest() {
        StringMap<Integer> map_ = new StringMap<Integer>();
        map_.put("ONE", 1);
        map_.remove(0);
        assertTrue(map_.isEmpty());
    }
    @Test
    public void valuesTest() {
        StringMap<Integer> map_ = new StringMap<Integer>();
        map_.put("0, 0", 0);
        map_.put("0, 1", 1);
        CustList<Integer> values_ = map_.values();
        assertEq(2, values_.size());
        assertEq(0, values_.first());
        assertEq(1, values_.last());
    }
    @Test
    public void valuesLs1Test() {
        StringMap<Integer> map_ = new StringMap<Integer>();
        map_.put("0, 0", 0);
        map_.put("0, 1", 1);
        CustList<Integer> values_ = map_.valuesKey("0, 0");
        assertEq(1, values_.size());
        assertEq(0, values_.first());
    }
    @Test
    public void valuesLs2Test() {
        StringMap<Integer> map_ = new StringMap<Integer>();
        map_.put("0, 0", 0);
        map_.put("0, 1", 1);
        CustList<Integer> values_ = map_.valuesKey("0, 1");
        assertEq(1, values_.size());
        assertEq(1, values_.first());
    }
    @Test
    public void differentKeys1() {
        StringMap<Integer> map_ = new StringMap<Integer>();
        map_.put("0, 0", 0);
        map_.put("0, 1", 1);
        CustList<String> values_ = map_.differentKeys();
        assertEq(2, values_.size());
        assertEq("0, 0", values_.first());
        assertEq("0, 1", values_.last());
    }
    @Test
    public void differentKeys2() {
        StringMap<Integer> map_ = new StringMap<Integer>();
        map_.put("0, 0", 0);
        map_.addEntry("0, 0", 2);
        map_.put("0, 1", 1);
        map_.addEntry("0, 1", 3);
        CustList<String> values_ = map_.differentKeys();
        assertEq(2, values_.size());
        assertEq("0, 0", values_.first());
        assertEq("0, 1", values_.last());
    }
    @Test
    public void add1Test() {
        StringMap<Integer> map_ = new StringMap<Integer>();
        map_.put("0, 0", 0);
        map_.put("0, 1", 1);
        map_.tryAdd("0, 0", 2);
        assertEq(2,map_.size());
        assertTrue(map_.contains("0, 0"));
        assertTrue(map_.contains("0, 1"));
        assertEq(0, map_.getVal("0, 0"));
        assertEq(1, map_.getVal("0, 1"));
    }

    @Test
    public void add2Test() {
        StringMap<Integer> map_ = new StringMap<Integer>();
        map_.put("0, 0", 0);
        map_.put("0, 1", 1);
        map_.tryAdd("0, 2", 2);
        assertEq(3,map_.size());
        assertTrue(map_.contains("0, 0"));
        assertTrue(map_.contains("0, 1"));
        assertTrue(map_.contains("0, 2"));
        assertEq(0, map_.getVal("0, 0"));
        assertEq(1, map_.getVal("0, 1"));
        assertEq(2, map_.getVal("0, 2"));
    }

    @Test
    public void set1Test() {
        StringMap<Integer> map_ = new StringMap<Integer>();
        map_.put("0, 0", 0);
        map_.put("0, 1", 1);
        map_.set("0, 0", 2);
        assertEq(2,map_.size());
        assertTrue(map_.contains("0, 0"));
        assertTrue(map_.contains("0, 1"));
        assertEq(2, map_.getVal("0, 0"));
        assertEq(1, map_.getVal("0, 1"));
    }

    @Test
    public void set2Test() {
        StringMap<Integer> map_ = new StringMap<Integer>();
        map_.put("0, 0", 0);
        map_.put("0, 1", 1);
        map_.set("0, 2", 2);
        assertEq(2,map_.size());
        assertTrue(map_.contains("0, 0"));
        assertTrue(map_.contains("0, 1"));
        assertTrue(!map_.contains("0, 2"));
        assertEq(0, map_.getVal("0, 0"));
        assertEq(1, map_.getVal("0, 1"));
    }
    @Test
    public void containsAllAsKeys1Test() {
        StringMap<Integer> map_ = new StringMap<Integer>();
        map_.put("0, 0", 0);
        map_.put("0, 1", 1);
        CustList<String> elts_ =new CustList<String>();
        elts_.add("0, 0");
        elts_.add("0, 2");
        assertTrue(!map_.containsAllAsKeys(elts_));
    }
    @Test
    public void containsAllAsKeys2Test() {
        StringMap<Integer> map_ = new StringMap<Integer>();
        map_.put("0, 0", 0);
        map_.put("0, 1", 1);
        CustList<String> elts_ =new CustList<String>();
        elts_.add("0, 0");
        assertTrue(map_.containsAllAsKeys(elts_));
    }
    @Test
    public void containsAllAsKeys3Test() {
        StringMap<Integer> map_ = new StringMap<Integer>();
        map_.put("0, 0", 0);
        map_.put("0, 1", 1);
        CustList<String> elts_ =new CustList<String>();
        assertTrue(map_.containsAllAsKeys(elts_));
    }
    @Test
    public void methods1Test() {
        StringMap<Integer> map_ = new StringMap<Integer>();
        map_.put("0, 0", 0);
        map_.put("0, 1", 1);
        assertTrue(!map_.isEmpty());
        assertEq("0, 0",map_.firstKey());
        assertEq("0, 1",map_.lastKey());
        assertEq(0,map_.firstValue());
        assertEq(1,map_.lastValue());
        map_.setKey(0,"0, 2");
        assertEq("0, 2",map_.firstKey());
        assertEq("0, 2",map_.getKey(0));
        assertEq("0, 1",map_.lastKey());
    }
    @Test
    public void methods2Test() {
        StringMap<Integer> map_ = new StringMap<Integer>();
        map_.put("0, 0", 0);
        map_.put("0, 1", 1);
        map_.clear();
        assertTrue(map_.isEmpty());
    }
    @Test
    public void getKeysEqTest() {
        StringMap<Integer> map_ = new StringMap<Integer>();
        map_.put("0, 0", 0);
        map_.put("0, 1", 1);
        CustList<String> elts_ = map_.getKeys();
        assertEq(2,elts_.size());
        assertEq("0, 0",elts_.first());
        assertEq("0, 1",elts_.last());
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
        ByteMap<Integer> mapEmpty_ = new ByteMap<Integer>(2);
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
        LongMap<Integer> mapEmpty_ = new LongMap<Integer>(2);
        elts_ = mapEmpty_.getKeys();
        assertEq(0,elts_.size());
    }
//    @Test
//    public void getKeysBoolTest() {
//        BooleanMap<Integer> map_ = new BooleanMap<Integer>();
//        map_.put(true, 0);
//        map_.put(false, 1);
//        CustList<Boolean> elts_ = map_.getKeys();
//        assertEq(2,elts_.size());
//        assertEq(true,elts_.first());
//        assertEq(false,elts_.last());
//    }
//    @Test
//    public void getKeysCharTest() {
//        CharMap<Integer> map_ = new CharMap<Integer>();
//        map_.put((char)0, 0);
//        map_.put((char)1, 1);
//        CustList<Character> elts_ = map_.getKeys();
//        assertEq(2,elts_.size());
//        assertEq(0,elts_.first());
//        assertEq(1,elts_.last());
//    }
    @Test
    public void getKeysEmTest() {
        IdMap<ConcreteInteger,Integer> map_ = new IdMap<ConcreteInteger,Integer>();
        ConcreteInteger zero_ = new ConcreteInteger(0);
        map_.put(zero_, 0);
        ConcreteInteger one_ = new ConcreteInteger(1);
        map_.put(one_, 1);
        CustList<ConcreteInteger> elts_ = map_.getKeys();
        assertEq(2,elts_.size());
        assertSame(zero_,elts_.first());
        assertSame(one_,elts_.last());
    }
}
