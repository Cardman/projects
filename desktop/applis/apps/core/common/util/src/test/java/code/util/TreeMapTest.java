package code.util;

import org.junit.Test;

import code.util.comparators.NaturalComparator;


public class TreeMapTest extends EquallableExUtil {

    @Test
    public void put1Test() {
        TreeMap<String,Integer> map_ = new TreeMap<String,Integer>(new NaturalComparator());
        CustList<EntryCust<String,Integer>> l_ = map_.getList();
        assertEq(0, l_.size());
        map_.put("ONE", 1);
        assertEq(1, l_.size());
        assertEq("ONE", l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        map_.put("TWO", 2);
        assertEq(2, l_.size());
        assertEq("ONE", l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        assertEq("TWO", l_.get(1).getKey());
        assertEq(2, l_.get(1).getValue());
        map_.put("TWO", 3);
        assertEq(2, l_.size());
        assertEq("ONE", l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        assertEq("TWO", l_.get(1).getKey());
        assertEq(3, l_.get(1).getValue());
        map_.put("THREE", 2);
        assertEq(3, l_.size());
        assertEq("ONE", l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        assertEq("THREE", l_.get(1).getKey());
        assertEq(2, l_.get(1).getValue());
        assertEq("TWO", l_.get(2).getKey());
        assertEq(3, l_.get(2).getValue());
    }

    @Test
    public void put2Test() {
        TreeMap<String,Integer> map_ = new TreeMap<String,Integer>(new NaturalComparator(-1));
        CustList<EntryCust<String,Integer>> l_ = map_.getList();
        assertEq(0, l_.size());
        map_.put("ONE", 1);
        assertEq(1, l_.size());
        assertEq("ONE", l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        map_.put("TWO", 2);
        assertEq(2, l_.size());
        assertEq("TWO", l_.get(0).getKey());
        assertEq(2, l_.get(0).getValue());
        assertEq("ONE", l_.get(1).getKey());
        assertEq(1, l_.get(1).getValue());
        map_.put("TWO", 3);
        assertEq(2, l_.size());
        assertEq("TWO", l_.get(0).getKey());
        assertEq(3, l_.get(0).getValue());
        assertEq("ONE", l_.get(1).getKey());
        assertEq(1, l_.get(1).getValue());
        map_.put("THREE", 2);
        assertEq(3, l_.size());
        assertEq("TWO", l_.get(0).getKey());
        assertEq(3, l_.get(0).getValue());
        assertEq("THREE", l_.get(1).getKey());
        assertEq(2, l_.get(1).getValue());
        assertEq("ONE", l_.get(2).getKey());
        assertEq(1, l_.get(2).getValue());
    }

    @Test
    public void size1Test() {
        TreeMap<String,Integer> map_ = new TreeMap<String,Integer>(new NaturalComparator());
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
        TreeMap<String,Integer> map_ = new TreeMap<String,Integer>(new NaturalComparator());
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
        TreeMap<String,Integer> map_ = new TreeMap<String,Integer>(new NaturalComparator());
        map_.put("ONE", 1);
        assertEq(1,map_.getVal("ONE"));
        map_.put("TWO", 2);
        assertEq(1,map_.getVal("ONE"));
        assertEq(2,map_.getVal("TWO"));
        map_.put("TWO", 3);
        assertEq(1,map_.getVal("ONE"));
        assertEq(3,map_.getVal("TWO"));
    }

    @Test
    public void removeKey1Test() {
        TreeMap<String,Integer> map_ = new TreeMap<String,Integer>(new NaturalComparator());
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
    public void getKeys1Test() {
        TreeMap<String,Integer> map_ = new TreeMap<String,Integer>(new NaturalComparator());
        map_.put("ONE", 1);
        map_.put("TWO", 2);
        CustList<String> keys_ = map_.getKeys();
        assertEq(2, keys_.size());
        assertEq("ONE", keys_.first());
        assertEq("TWO", keys_.last());
    }

    @Test
    public void putAllMap1Test() {
        TreeMap<String,Integer> map_ = new TreeMap<String,Integer>(new NaturalComparator());
        map_.put("ONE", 1);
        map_.put("TWO", 2);
        TreeMap<String,Integer> mapToPut_ = new TreeMap<String,Integer>(new NaturalComparator());
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
        TreeMap<String,Integer> map_ = new TreeMap<String,Integer>(new NaturalComparator());
        map_.put("ONE", 1);
        map_.put("TWO", 2);
        TreeMap<String,Integer> mapToPut_ = new TreeMap<String,Integer>(new NaturalComparator());
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
    public void getKey1Test() {
        TreeMap<String,Integer> map_ = new TreeMap<String,Integer>(new NaturalComparator());
        map_.put("ONE", 1);
        map_.put("TWO", 2);
        TreeMap<String,Integer> mapToPut_ = new TreeMap<String,Integer>(new NaturalComparator());
        mapToPut_.put("TWO", 3);
        mapToPut_.put("THREE", 4);
        assertNotNull(map_.comparator());
        assertEq(2, map_.size());
        assertEq("ONE", map_.getKey(0));
        assertEq("TWO", map_.getKey(1));
        assertNotNull(mapToPut_.comparator());
        assertEq(2, mapToPut_.size());
        assertEq("THREE", mapToPut_.getKey(0));
        assertEq("TWO", mapToPut_.getKey(1));
    }

    @Test
    public void getKey2Test() {
        TreeMap<String,Integer> map_ = new TreeMap<String,Integer>(new NaturalComparator(-1));
        map_.put("ONE", 1);
        map_.put("TWO", 2);
        TreeMap<String,Integer> mapToPut_ = new TreeMap<String,Integer>(new NaturalComparator(-1));
        mapToPut_.put("TWO", 3);
        mapToPut_.put("THREE", 4);
        assertNotNull(map_.comparator());
        assertEq(2, map_.size());
        assertEq("TWO", map_.getKey(0));
        assertEq("ONE", map_.getKey(1));
        assertNotNull(mapToPut_.comparator());
        assertEq(2, mapToPut_.size());
        assertEq("TWO", mapToPut_.getKey(0));
        assertEq("THREE", mapToPut_.getKey(1));
    }

    @Test
    public void getValue1Test() {
        TreeMap<String,Integer> map_ = new TreeMap<String,Integer>(new NaturalComparator());
        map_.put("ONE", 1);
        map_.put("TWO", 2);
        TreeMap<String,Integer> mapToPut_ = new TreeMap<String,Integer>(new NaturalComparator());
        mapToPut_.put("TWO", 3);
        mapToPut_.put("THREE", 4);
        assertNotNull(map_.comparator());
        assertEq(2, map_.size());
        assertEq(1, map_.getValue(0));
        assertEq(2, map_.getValue(1));
        assertNotNull(mapToPut_.comparator());
        assertEq(2, mapToPut_.size());
        assertEq(4, mapToPut_.getValue(0));
        assertEq(3, mapToPut_.getValue(1));
    }

    @Test
    public void getValue2Test() {
        TreeMap<String,Integer> map_ = new TreeMap<String,Integer>(new NaturalComparator(-1));
        map_.put("ONE", 1);
        map_.put("TWO", 2);
        TreeMap<String,Integer> mapToPut_ = new TreeMap<String,Integer>(new NaturalComparator(-1));
        mapToPut_.put("TWO", 3);
        mapToPut_.put("THREE", 4);
        assertNotNull(map_.comparator());
        assertEq(2, map_.size());
        assertEq(2, map_.getValue(0));
        assertEq(1, map_.getValue(1));
        assertNotNull(mapToPut_.comparator());
        assertEq(2, mapToPut_.size());
        assertEq(3, mapToPut_.getValue(0));
        assertEq(4, mapToPut_.getValue(1));
    }

}
