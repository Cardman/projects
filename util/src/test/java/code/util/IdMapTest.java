package code.util;
import static code.util.opers.EquallableUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.util.EntryCust;
import code.util.IdList;
import code.util.IdMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.classestest.KeyExample;
import code.util.ints.Listable;

@SuppressWarnings("static-method")
public class IdMapTest {


    @Test
    public void put1Test() {
        IdMap<String,Number> map_ = new IdMap<String,Number>();
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
        IdMap<KeyExample,Number> otherMap_ = new IdMap<KeyExample,Number>();
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
    private static Number getValue(Listable<EntryCust<KeyExample,Number>> _l, KeyExample _k) {
        for (EntryCust<KeyExample,Number> e: _l) {
            if (e.getKey() == _k) {
                return e.getValue();
            }
        }
        return null;
    }

    private static IdList<KeyExample> getKeys(Listable<EntryCust<KeyExample,Number>> _l) {
        IdList<KeyExample> l_ = new IdList<KeyExample>();
        for (EntryCust<KeyExample,Number> e: _l) {
            l_.add(e.getKey());
        }
        return l_;
    }

    @Test
    public void size1Test() {
        IdMap<String,Number> map_ = new IdMap<String,Number>();
        assertEq(0, map_.size());
        map_.put("ONE", 1);
        assertEq(1,map_.size());
        map_.put("TWO", 2);
        assertEq(2,map_.size());
        map_.put("TWO", 3);
        assertEq(2,map_.size());
        IdMap<KeyExample,Number> otherMap_ = new IdMap<KeyExample,Number>();
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
        IdMap<String,Number> map_ = new IdMap<String,Number>();
        assertEq(0, map_.size());
        map_.put("ONE", 1);
        assertTrue(map_.contains("ONE"));
        map_.put("TWO", 2);
        assertTrue(map_.contains("ONE"));
        assertTrue(map_.contains("TWO"));
        map_.put("TWO", 3);
        assertTrue(map_.contains("ONE"));
        assertTrue(map_.contains("TWO"));
        IdMap<KeyExample,Number> otherMap_ = new IdMap<KeyExample,Number>();
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
        IdMap<String,Number> map_ = new IdMap<String,Number>();
        map_.put("ONE", 1);
        assertEq(1,map_.getVal("ONE").intValue());
        map_.put("TWO", 2);
        assertEq(1,map_.getVal("ONE").intValue());
        assertEq(2,map_.getVal("TWO").intValue());
        map_.put("TWO", 3);
        assertEq(1,map_.getVal("ONE").intValue());
        assertEq(3,map_.getVal("TWO").intValue());
        IdMap<KeyExample,Number> otherMap_ = new IdMap<KeyExample,Number>();
        KeyExample k1_;
        KeyExample k2_;
        k1_ = new KeyExample(0, 0);
        k2_ = new KeyExample(0, 1);
        otherMap_.put(k1_, 0);
        assertEq(0, otherMap_.getVal(k1_).intValue());
        otherMap_.put(k2_, 1);
        assertEq(0, otherMap_.getVal(k1_).intValue());
        assertEq(1, otherMap_.getVal(k2_).intValue());
        otherMap_.put(k2_, 2);
        assertEq(0, otherMap_.getVal(k1_).intValue());
        assertEq(2, otherMap_.getVal(k2_));
    }

    @Test
    public void removeKey1Test() {
        IdMap<String,Number> map_ = new IdMap<String,Number>();
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
        IdMap<KeyExample,Number> otherMap_ = new IdMap<KeyExample,Number>();
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
        IdMap<String,Number> map_ = new IdMap<String,Number>();
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

//    @SuppressWarnings("static-method")
//    @Test
//    public void replace1Test() {
//        IdMap<String,Integer> map_ = new IdMap<>();
//        map_.put("ONE", 1);
//        map_.put("TWO", 3);
//        map_.replaceValue(3, 2);
//        assertEq(2,map_.size());
//        assertTrue(map_.contains("ONE"));
//        assertTrue(map_.contains("TWO"));
//        assertEq(1, map_.getVal("ONE").intValue());
//        assertEq(2, map_.getVal("TWO").intValue());
//    }

//    @SuppressWarnings("static-method")
//    @Test
//    public void has1Test() {
//        IdMap<String,Integer> map_ = new IdMap<>();
//        map_.put("ONE", 1);
//        map_.put("TWO", 2);
//        assertTrue(map_.has(1));
//        assertTrue(!map_.has(3));
//    }

//    @SuppressWarnings("static-method")
//    @Test
//    public void getKeys1Test() {
//        IdMap<String,Integer> map_ = new IdMap<>();
//        map_.put("ONE", 1);
//        map_.put("TWO", 2);
//        map_.put("THREE", null);
//        List<String> keys_ = map_.getKeys(1);
//        assertEq(1, keys_.size());
//        assertTrue(keys_.containsObj("ONE"));
//        keys_ = map_.getKeys(3);
//        assertEq(0, keys_.size());
//        keys_ = map_.getKeys(null);
//        assertEq(1, keys_.size());
//        assertTrue(keys_.containsObj("THREE"));
//    }

    @Test
    public void putAllMap1Test() {
        IdMap<String,Number> map_ = new IdMap<String,Number>();
        map_.put("ONE", 1);
        map_.put("TWO", 2);
        IdMap<String,Number> mapToPut_ = new IdMap<String,Number>();
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
        IdMap<String,Number> map_ = new IdMap<String,Number>();
        map_.put("ONE", 1);
        map_.put("TWO", 2);
        IdMap<String,Number> mapToPut_ = new IdMap<String,Number>();
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
        KeyExample k1_;
        KeyExample k2_;
        KeyExample k3_;
        KeyExample k4_;
        k1_ = new KeyExample(0, 0);
        k2_ = new KeyExample(0, 1);
        k3_ = new KeyExample(1, 1);
        k4_ = new KeyExample(1, 0);
        IdMap<KeyExample,Number> map_ = new IdMap<KeyExample,Number>();
        map_.put(k1_, 0);
        map_.put(k2_, 1);
        IdMap<KeyExample,Number> mapToPut_ = new IdMap<KeyExample,Number>();
        mapToPut_.put(k3_, 2);
        mapToPut_.put(k4_, 3);
        map_.putAllMap(mapToPut_);
        assertEq(4, map_.size());
        assertTrue(map_.contains(k1_));
        assertTrue(map_.contains(k2_));
        assertTrue(map_.contains(k3_));
        assertTrue(map_.contains(k4_));
        assertEq(0, map_.getVal(k1_).intValue());
        assertEq(1, map_.getVal(k2_).intValue());
        assertEq(2, map_.getVal(k3_).intValue());
        assertEq(3, map_.getVal(k4_).intValue());
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
        IdMap<KeyExample,Number> map_ = new IdMap<KeyExample,Number>();
        map_.put(k1_, 0);
        map_.put(k2_, 1);
        IdMap<KeyExample,Number> mapToPut_ = new IdMap<KeyExample,Number>();
        mapToPut_.put(k3_, 2);
        mapToPut_.put(k4_, 3);
        map_.putAllMap(mapToPut_);
        assertEq(4, map_.size());
        assertTrue(map_.contains(k1_));
        assertTrue(map_.contains(k2_));
        assertTrue(map_.contains(k3_));
        assertTrue(map_.contains(k4_));
        assertEq(0, map_.getVal(k1_).intValue());
        assertEq(1, map_.getVal(k2_).intValue());
        assertEq(2, map_.getVal(k3_).intValue());
        assertEq(3, map_.getVal(k4_).intValue());
    }
}
