package code.util;
import static code.util.EquallableExUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.util.classestest.KeyExample;
import code.util.ints.Listable;


public class IdMapTest {


    @Test
    public void put1Test() {
        IdMap<String,Integer> map_ = new IdMap<String,Integer>(new IdMap<String,Integer>(new CollCapacity(0)));
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
        IdMap<KeyExample,Integer> otherMap_ = new IdMap<KeyExample,Integer>();
        IdList<KeyExample> lOther_ = getKeys(otherMap_.getList());
        KeyExample k1_ = new KeyExample(0, 0);
        KeyExample k2_ = new KeyExample(0, 1);
        KeyExample k3_ = new KeyExample(0, 1);
        assertEq(0, lOther_.size());
        otherMap_.put(k1_, 0);
        lOther_ = getKeys(otherMap_.getList());
        assertEq(1, lOther_.size());
        assertTrue(lOther_.containsObj(k1_));
        assertEq(0, getValue(otherMap_.getList(), k1_));
        otherMap_.put(k2_, 1);
        lOther_ = getKeys(otherMap_.getList());
        assertEq(2, lOther_.size());
        assertTrue(lOther_.containsObj(k1_));
        assertEq(0, getValue(otherMap_.getList(), k1_));
        assertTrue(lOther_.containsObj(k2_));
        assertEq(1, getValue(otherMap_.getList(), k2_));
        otherMap_.put(k3_, 2);
        lOther_ = getKeys(otherMap_.getList());
        assertEq(3, lOther_.size());
        assertTrue(lOther_.containsObj(k1_));
        assertEq(0, getValue(otherMap_.getList(), k1_));
        assertTrue(lOther_.containsObj(k2_));
        assertEq(1, getValue(otherMap_.getList(), k2_));
        assertTrue(lOther_.containsObj(k3_));
        assertEq(2, getValue(otherMap_.getList(), k3_));
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
    private static Integer getValue(Listable<EntryCust<KeyExample,Integer>> _l, KeyExample _k) {
        for (EntryCust<KeyExample,Integer> e: _l) {
            if (e.getKey() == _k) {
                return e.getValue();
            }
        }
        return null;
    }

    private static IdList<KeyExample> getKeys(Listable<EntryCust<KeyExample,Integer>> _l) {
        IdList<KeyExample> l_ = new IdList<KeyExample>();
        for (EntryCust<KeyExample,Integer> e: _l) {
            l_.add(e.getKey());
        }
        return l_;
    }

    @Test
    public void size1Test() {
        IdMap<String,Integer> map_ = new IdMap<String,Integer>();
        assertEq(0, map_.size());
        map_.put("ONE", 1);
        assertEq(1,map_.size());
        map_.put("TWO", 2);
        assertEq(2,map_.size());
        map_.put("TWO", 3);
        assertEq(2,map_.size());
        IdMap<KeyExample,Integer> otherMap_ = new IdMap<KeyExample,Integer>();
        assertEq(0, otherMap_.size());
        otherMap_.put(new KeyExample(0, 0), 0);
        assertEq(1,otherMap_.size());
        otherMap_.put(new KeyExample(0, 1), 1);
        assertEq(2,otherMap_.size());
        otherMap_.put(new KeyExample(0, 1), 2);
        assertEq(3,otherMap_.size());
    }

    @Test
    public void contains1Test() {
        IdMap<String,Integer> map_ = new IdMap<String,Integer>();
        assertEq(0, map_.size());
        map_.put("ONE", 1);
        assertTrue(map_.contains("ONE"));
        map_.put("TWO", 2);
        assertTrue(map_.contains("ONE"));
        assertTrue(map_.contains("TWO"));
        map_.put("TWO", 3);
        assertTrue(map_.contains("ONE"));
        assertTrue(map_.contains("TWO"));
        IdMap<KeyExample,Integer> otherMap_ = new IdMap<KeyExample,Integer>();
        KeyExample k1_;
        KeyExample k2_;
        KeyExample k3_;
        assertEq(0, otherMap_.size());
        k1_ = new KeyExample(0, 0);
        k2_ = new KeyExample(0, 1);
        k3_ = new KeyExample(0, 1);
        otherMap_.put(k1_, 0);
        assertTrue(otherMap_.contains(k1_));
        otherMap_.put(k2_, 1);
        assertTrue(otherMap_.contains(k1_));
        assertTrue(otherMap_.contains(k2_));
        otherMap_.put(k2_, 2);
        assertTrue(otherMap_.contains(k1_));
        assertTrue(otherMap_.contains(k2_));
        otherMap_.put(k3_, 2);
        assertTrue(otherMap_.contains(k1_));
        assertTrue(otherMap_.contains(k2_));
        assertTrue(otherMap_.contains(k3_));
    }

    @Test
    public void getVal1Test() {
        IdMap<String,Integer> map_ = new IdMap<String,Integer>();
        map_.put("ONE", 1);
        assertEq(1,map_.getVal("ONE"));
        map_.put("TWO", 2);
        assertEq(1,map_.getVal("ONE"));
        assertEq(2,map_.getVal("TWO"));
        map_.put("TWO", 3);
        assertEq(1,map_.getVal("ONE"));
        assertEq(3,map_.getVal("TWO"));
        IdMap<KeyExample,Integer> otherMap_ = new IdMap<KeyExample,Integer>();
        KeyExample k1_;
        KeyExample k2_;
        k1_ = new KeyExample(0, 0);
        k2_ = new KeyExample(0, 1);
        otherMap_.put(k1_, 0);
        assertEq(0, otherMap_.getVal(k1_));
        otherMap_.put(k2_, 1);
        assertEq(0, otherMap_.getVal(k1_));
        assertEq(1, otherMap_.getVal(k2_));
        otherMap_.put(k2_, 2);
        assertEq(0, otherMap_.getVal(k1_));
        assertEq(2, otherMap_.getVal(k2_));
    }

    @Test
    public void removeKey1Test() {
        IdMap<String,Integer> map_ = new IdMap<String,Integer>();
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
        IdMap<KeyExample,Integer> otherMap_ = new IdMap<KeyExample,Integer>();
        KeyExample k1_;
        KeyExample k2_;
        k1_ = new KeyExample(0, 0);
        k2_ = new KeyExample(0, 1);
        otherMap_.put(k1_, 0);
        otherMap_.put(k2_, 1);
        otherMap_.removeKey(new KeyExample(1, 1));
        assertEq(2,otherMap_.size());
        assertTrue(otherMap_.contains(k1_));
        assertTrue(otherMap_.contains(k2_));
        otherMap_.removeKey(k2_);
        assertEq(1,otherMap_.size());
        assertTrue(otherMap_.contains(k1_));
        assertTrue(!otherMap_.contains(k2_));
    }

    @Test
    public void move1Test() {
        IdMap<String,Integer> map_ = new IdMap<String,Integer>();
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
        IdMap<String,Integer> map_ = new IdMap<String,Integer>();
        map_.put("ONE", 1);
        map_.put("TWO", 2);
        IdMap<String,Integer> mapToPut_ = new IdMap<String,Integer>();
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
        IdMap<String,Integer> map_ = new IdMap<String,Integer>();
        map_.put("ONE", 1);
        map_.put("TWO", 2);
        IdMap<String,Integer> mapToPut_ = new IdMap<String,Integer>();
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
        KeyExample k1_;
        KeyExample k2_;
        KeyExample k3_;
        KeyExample k4_;
        k1_ = new KeyExample(0, 0);
        k2_ = new KeyExample(0, 1);
        k3_ = new KeyExample(1, 1);
        k4_ = new KeyExample(1, 0);
        IdMap<KeyExample,Integer> map_ = new IdMap<KeyExample,Integer>();
        map_.put(k1_, 0);
        map_.put(k2_, 1);
        IdMap<KeyExample,Integer> mapToPut_ = new IdMap<KeyExample,Integer>();
        mapToPut_.put(k3_, 2);
        mapToPut_.put(k4_, 3);
        map_.putAllMap(mapToPut_);
        assertEq(4, map_.size());
        assertTrue(map_.contains(k1_));
        assertTrue(map_.contains(k2_));
        assertTrue(map_.contains(k3_));
        assertTrue(map_.contains(k4_));
        assertEq(0, map_.getVal(k1_));
        assertEq(1, map_.getVal(k2_));
        assertEq(2, map_.getVal(k3_));
        assertEq(3, map_.getVal(k4_));
    }

    @Test
    public void putAllMap4Test() {
        KeyExample k1_;
        KeyExample k2_;
        KeyExample k3_;
        KeyExample k4_;
        k1_ = new KeyExample(0, 0);
        k2_ = new KeyExample(0, 1);
        k3_ = new KeyExample(1, 1);
        k4_ = new KeyExample(0, 1);
        IdMap<KeyExample,Integer> map_ = new IdMap<KeyExample,Integer>();
        map_.put(k1_, 0);
        map_.put(k2_, 1);
        IdMap<KeyExample,Integer> mapToPut_ = new IdMap<KeyExample,Integer>();
        mapToPut_.put(k3_, 2);
        mapToPut_.put(k4_, 3);
        map_.putAllMap(mapToPut_);
        assertEq(4, map_.size());
        assertTrue(map_.contains(k1_));
        assertTrue(map_.contains(k2_));
        assertTrue(map_.contains(k3_));
        assertTrue(map_.contains(k4_));
        assertEq(0, map_.getVal(k1_));
        assertEq(1, map_.getVal(k2_));
        assertEq(2, map_.getVal(k3_));
        assertEq(3, map_.getVal(k4_));
    }

    @Test
    public void getKeysTest() {
        KeyExample k1_;
        KeyExample k2_;
        KeyExample k3_;
        KeyExample k4_;
        k1_ = new KeyExample(0, 0);
        k2_ = new KeyExample(0, 1);
        k3_ = new KeyExample(1, 1);
        k4_ = new KeyExample(0, 1);
        IdMap<KeyExample,Integer> map_ = new IdMap<KeyExample,Integer>();
        map_.put(k1_, 0);
        map_.put(k2_, 1);
        IdMap<KeyExample,Integer> mapToPut_ = new IdMap<KeyExample,Integer>();
        mapToPut_.put(k3_, 2);
        mapToPut_.put(k4_, 3);
        map_.putAllMap(mapToPut_);
        IdList<KeyExample> keys_ = new IdList<KeyExample>(map_.getKeys());
        assertEq(4, keys_.size());
        assertTrue(keys_.containsObj(k1_));
        assertTrue(keys_.containsObj(k2_));
        assertTrue(keys_.containsObj(k3_));
        assertTrue(keys_.containsObj(k4_));
    }
}
