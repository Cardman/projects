package code.util;
import static code.util.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.util.classestest.KeyExample;
import code.util.ints.Listable;

@SuppressWarnings("static-method")
public class MapTest {

    @Test
    public void put1Test() {
        StringMap<Number> map_ = new StringMap<Number>();
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

//    @SuppressWarnings("static-method")
//    @Test
//    public void replace1Test() {
//        Map<String,Integer> map_ = new Map<>();
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
//        Map<String,Integer> map_ = new Map<>();
//        map_.put("ONE", 1);
//        map_.put("TWO", 2);
//        assertTrue(map_.has(1));
//        assertTrue(!map_.has(3));
//    }

//    @SuppressWarnings("static-method")
//    @Test
//    public void getKeys1Test() {
//        Map<String,Integer> map_ = new Map<>();
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
        ObjectMap<KeyExample,Number> map_ = new ObjectMap<KeyExample,Number>();
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
