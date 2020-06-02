package code.util;
import static code.util.EquallableExUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.util.ints.Listable;


public final class NatTreeMapTest {

    @Test
    public void put1Test() {
        IntTreeMap<Integer> map_ = new IntTreeMap<Integer>();
        CustList<EntryCust<Integer,Integer>> l_ = map_.getList();
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
        IntTreeMap<Integer> map_ = new IntTreeMap<Integer>(new CollCapacity(2));
        CustList<EntryCust<Integer,Integer>> l_ = map_.getList();
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
        IntTreeMap<Integer> map_ = new IntTreeMap<Integer>();
        CustList<EntryCust<Integer,Integer>> l_ = map_.getList();
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
    public void put4Test() {
        ByteTreeMap<Integer> map_ = new ByteTreeMap<Integer>(new CollCapacity(2));
        CustList<EntryCust<Byte,Integer>> l_ = map_.getList();
        assertEq(0, l_.size());
        map_.put((byte) 2, 2);
        map_.put((byte) 2, 1);
        assertEq(1, l_.size());
        assertEq(2, l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        map_.put((byte) 4, 2);
        assertEq(2, l_.size());
        assertEq(2, l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        assertEq(4, l_.get(1).getKey());
        assertEq(2, l_.get(1).getValue());
        map_.put((byte) 4, 3);
        assertEq(2, l_.size());
        assertEq(2, l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        assertEq(4, l_.get(1).getKey());
        assertEq(3, l_.get(1).getValue());
        map_.put((byte) 3, 2);
        assertEq(3, l_.size());
        assertEq(2, l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        assertEq(3, l_.get(1).getKey());
        assertEq(2, l_.get(1).getValue());
        assertEq(4, l_.get(2).getKey());
        assertEq(3, l_.get(2).getValue());
        map_ = new ByteTreeMap<Integer>();
        assertEq(0, map_.size());
    }

    @Test
    public void put5Test() {
        LongTreeMap<Integer> map_ = new LongTreeMap<Integer>();
        CustList<EntryCust<Long,Integer>> l_ = map_.getList();
        assertEq(0, l_.size());
        map_.put(2L, 2);
        map_.put(2L, 1);
        assertEq(1, l_.size());
        assertEq(2, l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        map_.put(4L, 2);
        assertEq(2, l_.size());
        assertEq(2, l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        assertEq(4, l_.get(1).getKey());
        assertEq(2, l_.get(1).getValue());
        map_.put(4L, 3);
        assertEq(2, l_.size());
        assertEq(2, l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        assertEq(4, l_.get(1).getKey());
        assertEq(3, l_.get(1).getValue());
        map_.put(3L, 2);
        assertEq(3, l_.size());
        assertEq(2, l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        assertEq(3, l_.get(1).getKey());
        assertEq(2, l_.get(1).getValue());
        assertEq(4, l_.get(2).getKey());
        assertEq(3, l_.get(2).getValue());
    }

    @Test
    public void put6Test() {
        ShortTreeMap<Integer> map_ = new ShortTreeMap<Integer>();
        CustList<EntryCust<Short,Integer>> l_ = map_.getList();
        assertEq(0, l_.size());
        map_.put((short) 2, 2);
        map_.put((short) 2, 1);
        assertEq(1, l_.size());
        assertEq(2, l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        map_.put((short) 4, 2);
        assertEq(2, l_.size());
        assertEq(2, l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        assertEq(4, l_.get(1).getKey());
        assertEq(2, l_.get(1).getValue());
        map_.put((short) 4, 3);
        assertEq(2, l_.size());
        assertEq(2, l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        assertEq(4, l_.get(1).getKey());
        assertEq(3, l_.get(1).getValue());
        map_.put((short) 3, 2);
        assertEq(3, l_.size());
        assertEq(2, l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        assertEq(3, l_.get(1).getKey());
        assertEq(2, l_.get(1).getValue());
        assertEq(4, l_.get(2).getKey());
        assertEq(3, l_.get(2).getValue());
    }
    @Test
    public void size1Test() {
        IntTreeMap<Integer> map_ = new IntTreeMap<Integer>();
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
        IntTreeMap<Integer> map_ = new IntTreeMap<Integer>();
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
        IntTreeMap<Integer> map_ = new IntTreeMap<Integer>();
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
        IntTreeMap<Integer> map_ = new IntTreeMap<Integer>();
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
        IntTreeMap<Integer> map_ = new IntTreeMap<Integer>();
        map_.put(2, 1);
        map_.put(4, 2);
        CustList<Integer> keys_ = map_.getKeys();
        assertEq(2, keys_.size());
        assertEq(2, keys_.first());
        assertEq(4, keys_.last());
    }

    @Test
    public void putAllMap1Test() {
        IntTreeMap<Integer> map_ = new IntTreeMap<Integer>();
        map_.put(2, 1);
        map_.put(4, 2);
        IntTreeMap<Integer> mapToPut_ = new IntTreeMap<Integer>();
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
        IntTreeMap<Integer> map_ = new IntTreeMap<Integer>();
        map_.put(2, 1);
        map_.put(4, 2);
        IntTreeMap<Integer> mapToPut_ = new IntTreeMap<Integer>();
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
        IntTreeMap<Integer> map_ = new IntTreeMap<Integer>();
        map_.put(2, 1);
        map_.put(4, 2);
        IntTreeMap<Integer> mapToPut_ = new IntTreeMap<Integer>();
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
        IntTreeMap<Integer> map_ = new IntTreeMap<Integer>();
        map_.put(2, 1);
        map_.put(4, 2);
        IntTreeMap<Integer> mapToPut_ = new IntTreeMap<Integer>();
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
