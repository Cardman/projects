package code.util;
import static code.util.EquallableExUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.util.ints.Listable;


public final class NatTreeMapTest {

    @Test
    public void put1Test() {
        NatTreeMap<Integer,Integer> map_ = new NatTreeMap<Integer,Integer>();
        Listable<EntryCust<Integer,Integer>> l_ = map_.getList();
        assertEq(0, l_.size());
        map_.put(2, 1);
        assertEq(1, l_.size());
        assertEq(2, l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        map_.put(4, 2);
        assertEq(2, l_.size());
        assertEq(2, l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        assertEq(4, l_.get(1).getKey());
        assertEq(2, l_.get(1).getValue());
        map_.put(4, 3);
        assertEq(2, l_.size());
        assertEq(2, l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        assertEq(4, l_.get(1).getKey());
        assertEq(3, l_.get(1).getValue());
        map_.put(3, 2);
        assertEq(3, l_.size());
        assertEq(2, l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        assertEq(3, l_.get(1).getKey());
        assertEq(2, l_.get(1).getValue());
        assertEq(4, l_.get(2).getKey());
        assertEq(3, l_.get(2).getValue());
    }

    @Test
    public void put2Test() {
        NatTreeMap<Integer,Integer> map_ = new NatTreeMap<Integer,Integer>(new CollCapacity(2));
        Listable<EntryCust<Integer,Integer>> l_ = map_.getList();
        assertEq(0, l_.size());
        map_.put(2, 2);
        map_.put(2, 1);
        assertEq(1, l_.size());
        assertEq(2, l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        map_.put(4, 2);
        assertEq(2, l_.size());
        assertEq(2, l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        assertEq(4, l_.get(1).getKey());
        assertEq(2, l_.get(1).getValue());
        map_.put(4, 3);
        assertEq(2, l_.size());
        assertEq(2, l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        assertEq(4, l_.get(1).getKey());
        assertEq(3, l_.get(1).getValue());
        map_.put(3, 2);
        assertEq(3, l_.size());
        assertEq(2, l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        assertEq(3, l_.get(1).getKey());
        assertEq(2, l_.get(1).getValue());
        assertEq(4, l_.get(2).getKey());
        assertEq(3, l_.get(2).getValue());
    }

    @Test
    public void put3Test() {
        NatTreeMap<Integer,Integer> map_ = new NatTreeMap<Integer,Integer>();
        Listable<EntryCust<Integer,Integer>> l_ = map_.getList();
        assertEq(0, l_.size());
        map_.put(2, 2);
        map_.put(2, 1);
        assertEq(1, l_.size());
        assertEq(2, l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        map_.put(1, 2);
        assertEq(2, l_.size());
        assertEq(1, l_.get(0).getKey());
        assertEq(2, l_.get(0).getValue());
        assertEq(2, l_.get(1).getKey());
        assertEq(1, l_.get(1).getValue());
    }
    @Test
    public void size1Test() {
        NatTreeMap<Integer,Integer> map_ = new NatTreeMap<Integer,Integer>();
        assertEq(0, map_.size());
        map_.put(2, 1);
        assertEq(1,map_.size());
        map_.put(4, 2);
        assertEq(2,map_.size());
        map_.put(4, 3);
        assertEq(2,map_.size());
    }

    @Test
    public void contains1Test() {
        NatTreeMap<Integer,Integer> map_ = new NatTreeMap<Integer,Integer>();
        assertEq(0, map_.size());
        map_.put(2, 1);
        assertTrue(map_.contains(2));
        map_.put(4, 2);
        assertTrue(map_.contains(2));
        assertTrue(map_.contains(4));
        map_.put(4, 3);
        assertTrue(map_.contains(2));
        assertTrue(map_.contains(4));
    }

    @Test
    public void getVal1Test() {
        NatTreeMap<Integer,Integer> map_ = new NatTreeMap<Integer,Integer>();
        map_.put(2, 1);
        assertEq(1, map_.getVal(2));
        map_.put(4, 2);
        assertEq(1, map_.getVal(2));
        assertEq(2, map_.getVal(4));
        map_.put(4, 3);
        assertEq(1, map_.getVal(2));
        assertEq(3, map_.getVal(4));
    }

    @Test
    public void removeKey1Test() {
        NatTreeMap<Integer,Integer> map_ = new NatTreeMap<Integer,Integer>();
        map_.put(2, 1);
        map_.put(4, 2);
        map_.removeKey(5);
        assertEq(2,map_.size());
        assertTrue(map_.contains(2));
        assertTrue(map_.contains(4));
        map_.removeKey(4);
        assertEq(1,map_.size());
        assertTrue(map_.contains(2));
        assertTrue(!map_.contains(4));
    }

    @Test
    public void getKeys1Test() {
        NatTreeMap<Integer,Integer> map_ = new NatTreeMap<Integer,Integer>();
        map_.put(2, 1);
        map_.put(4, 2);
        CustList<Integer> keys_ = map_.getKeys();
        assertEq(2, keys_.size());
        assertEq(2, keys_.first());
        assertEq(4, keys_.last());
    }

    @Test
    public void putAllMap1Test() {
        NatTreeMap<Integer,Integer> map_ = new NatTreeMap<Integer,Integer>();
        map_.put(2, 1);
        map_.put(4, 2);
        NatTreeMap<Integer,Integer> mapToPut_ = new NatTreeMap<Integer,Integer>();
        mapToPut_.put(3, 3);
        mapToPut_.put(1, 4);
        map_.putAllMap(mapToPut_);
        assertEq(4, map_.size());
        assertTrue(map_.contains(2));
        assertTrue(map_.contains(4));
        assertTrue(map_.contains(3));
        assertTrue(map_.contains(1));
        assertEq(1, map_.getVal(2));
        assertEq(2, map_.getVal(4));
        assertEq(3, map_.getVal(3));
        assertEq(4, map_.getVal(1));
    }

    @Test
    public void putAllMap2Test() {
        NatTreeMap<Integer,Integer> map_ = new NatTreeMap<Integer,Integer>();
        map_.put(2, 1);
        map_.put(4, 2);
        NatTreeMap<Integer,Integer> mapToPut_ = new NatTreeMap<Integer,Integer>();
        mapToPut_.put(4, 3);
        mapToPut_.put(3, 4);
        map_.putAllMap(mapToPut_);
        assertEq(3, map_.size());
        assertTrue(map_.contains(2));
        assertTrue(map_.contains(4));
        assertTrue(map_.contains(3));
        assertEq(1, map_.getVal(2));
        assertEq(3, map_.getVal(4));
        assertEq(4, map_.getVal(3));
    }

    @Test
    public void getKey1Test() {
        NatTreeMap<Integer,Integer> map_ = new NatTreeMap<Integer,Integer>();
        map_.put(2, 1);
        map_.put(4, 2);
        NatTreeMap<Integer,Integer> mapToPut_ = new NatTreeMap<Integer,Integer>();
        mapToPut_.put(4, 3);
        mapToPut_.put(3, 4);
        assertEq(2, map_.size());
        assertEq(2, map_.getKey(0));
        assertEq(4, map_.getKey(1));
        assertEq(2, mapToPut_.size());
        assertEq(3, mapToPut_.getKey(0));
        assertEq(4, mapToPut_.getKey(1));
    }

    @Test
    public void getValue1Test() {
        NatTreeMap<Integer,Integer> map_ = new NatTreeMap<Integer,Integer>();
        map_.put(2, 1);
        map_.put(4, 2);
        NatTreeMap<Integer,Integer> mapToPut_ = new NatTreeMap<Integer,Integer>();
        mapToPut_.put(4, 3);
        mapToPut_.put(3, 4);
        assertEq(2, map_.size());
        assertEq(1, map_.getValue(0));
        assertEq(2, map_.getValue(1));
        assertEq(2, mapToPut_.size());
        assertEq(4, mapToPut_.getValue(0));
        assertEq(3, mapToPut_.getValue(1));
    }
}
