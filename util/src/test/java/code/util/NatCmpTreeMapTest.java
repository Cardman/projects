package code.util;

import code.util.classestest.MyCmp;
import code.util.ints.Listable;
import org.junit.Test;

import static code.util.EquallableExUtil.assertEq;
import static org.junit.Assert.assertTrue;

public final class NatCmpTreeMapTest {

    @Test
    public void put1Test() {
        NatCmpTreeMap<MyCmp,Integer> map_ = new NatCmpTreeMap<MyCmp,Integer>();
        Listable<EntryCust<MyCmp,Integer>> l_ = map_.getList();
        assertEq(0, l_.size());
        map_.put(new MyCmp(2), 1);
        assertEq(1, l_.size());
        assertEq(new MyCmp(2), l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        map_.put(new MyCmp(4), 2);
        assertEq(2, l_.size());
        assertEq(new MyCmp(2), l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        assertEq(new MyCmp(4), l_.get(1).getKey());
        assertEq(2, l_.get(1).getValue());
        map_.put(new MyCmp(4), 3);
        assertEq(2, l_.size());
        assertEq(new MyCmp(2), l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        assertEq(new MyCmp(4), l_.get(1).getKey());
        assertEq(3, l_.get(1).getValue());
        map_.put(new MyCmp(3), 2);
        assertEq(3, l_.size());
        assertEq(new MyCmp(2), l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        assertEq(new MyCmp(3), l_.get(1).getKey());
        assertEq(2, l_.get(1).getValue());
        assertEq(new MyCmp(4), l_.get(2).getKey());
        assertEq(3, l_.get(2).getValue());
    }

    @Test
    public void put2Test() {
        NatCmpTreeMap<MyCmp,Integer> map_ = new NatCmpTreeMap<MyCmp,Integer>();
        Listable<EntryCust<MyCmp,Integer>> l_ = map_.getList();
        assertEq(0, l_.size());
        map_.put(new MyCmp(2), 2);
        map_.put(new MyCmp(2), 1);
        assertEq(1, l_.size());
        assertEq(new MyCmp(2), l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        map_.put(new MyCmp(4), 2);
        assertEq(2, l_.size());
        assertEq(new MyCmp(2), l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        assertEq(new MyCmp(4), l_.get(1).getKey());
        assertEq(2, l_.get(1).getValue());
        map_.put(new MyCmp(4), 3);
        assertEq(2, l_.size());
        assertEq(new MyCmp(2), l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        assertEq(new MyCmp(4), l_.get(1).getKey());
        assertEq(3, l_.get(1).getValue());
        map_.put(new MyCmp(3), 2);
        assertEq(3, l_.size());
        assertEq(new MyCmp(2), l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        assertEq(new MyCmp(3), l_.get(1).getKey());
        assertEq(2, l_.get(1).getValue());
        assertEq(new MyCmp(4), l_.get(2).getKey());
        assertEq(3, l_.get(2).getValue());
    }

    @Test
    public void put3Test() {
        NatCmpTreeMap<MyCmp,Integer> map_ = new NatCmpTreeMap<MyCmp,Integer>();
        Listable<EntryCust<MyCmp,Integer>> l_ = map_.getList();
        assertEq(0, l_.size());
        map_.put(new MyCmp(2), 2);
        map_.put(new MyCmp(2), 1);
        assertEq(1, l_.size());
        assertEq(new MyCmp(2), l_.get(0).getKey());
        assertEq(1, l_.get(0).getValue());
        map_.put(new MyCmp(1), 2);
        assertEq(2, l_.size());
        assertEq(new MyCmp(1), l_.get(0).getKey());
        assertEq(2, l_.get(0).getValue());
        assertEq(new MyCmp(2), l_.get(1).getKey());
        assertEq(1, l_.get(1).getValue());
    }
    @Test
    public void size1Test() {
        NatCmpTreeMap<MyCmp,Integer> map_ = new NatCmpTreeMap<MyCmp,Integer>();
        assertEq(0, map_.size());
        map_.put(new MyCmp(2), 1);
        assertEq(1,map_.size());
        map_.put(new MyCmp(4), 2);
        assertEq(2,map_.size());
        map_.put(new MyCmp(4), 3);
        assertEq(2,map_.size());
    }

    @Test
    public void contains1Test() {
        NatCmpTreeMap<MyCmp,Integer> map_ = new NatCmpTreeMap<MyCmp,Integer>();
        assertEq(0, map_.size());
        map_.put(new MyCmp(2), 1);
        assertTrue(map_.contains(new MyCmp(2)));
        map_.put(new MyCmp(4), 2);
        assertTrue(map_.contains(new MyCmp(2)));
        assertTrue(map_.contains(new MyCmp(4)));
        map_.put(new MyCmp(4), 3);
        assertTrue(map_.contains(new MyCmp(2)));
        assertTrue(map_.contains(new MyCmp(4)));
    }

    @Test
    public void getVal1Test() {
        NatCmpTreeMap<MyCmp,Integer> map_ = new NatCmpTreeMap<MyCmp,Integer>();
        map_.put(new MyCmp(2), 1);
        assertEq(1,map_.getVal(new MyCmp(2)));
        map_.put(new MyCmp(4), 2);
        assertEq(1,map_.getVal(new MyCmp(2)));
        assertEq(2,map_.getVal(new MyCmp(4)));
        map_.put(new MyCmp(4), 3);
        assertEq(1,map_.getVal(new MyCmp(2)));
        assertEq(3,map_.getVal(new MyCmp(4)));
    }

    @Test
    public void removeKey1Test() {
        NatCmpTreeMap<MyCmp,Integer> map_ = new NatCmpTreeMap<MyCmp,Integer>();
        map_.put(new MyCmp(2), 1);
        map_.put(new MyCmp(4), 2);
        map_.removeKey(new MyCmp(5));
        assertEq(2,map_.size());
        assertTrue(map_.contains(new MyCmp(2)));
        assertTrue(map_.contains(new MyCmp(4)));
        map_.removeKey(new MyCmp(4));
        assertEq(1,map_.size());
        assertTrue(map_.contains(new MyCmp(2)));
        assertTrue(!map_.contains(new MyCmp(4)));
    }

    @Test
    public void getKeys1Test() {
        NatCmpTreeMap<MyCmp,Integer> map_ = new NatCmpTreeMap<MyCmp,Integer>();
        map_.put(new MyCmp(2), 1);
        map_.put(new MyCmp(4), 2);
        CustList<MyCmp> keys_ = map_.getKeys();
        assertEq(2, keys_.size());
        assertEq(new MyCmp(2), keys_.first());
        assertEq(new MyCmp(4), keys_.last());
    }

    @Test
    public void putAllMap1Test() {
        NatCmpTreeMap<MyCmp,Integer> map_ = new NatCmpTreeMap<MyCmp,Integer>();
        map_.put(new MyCmp(2), 1);
        map_.put(new MyCmp(4), 2);
        NatCmpTreeMap<MyCmp,Integer> mapToPut_ = new NatCmpTreeMap<MyCmp,Integer>();
        mapToPut_.put(new MyCmp(3), 3);
        mapToPut_.put(new MyCmp(1), 4);
        map_.putAllMap(mapToPut_);
        assertEq(4, map_.size());
        assertTrue(map_.contains(new MyCmp(2)));
        assertTrue(map_.contains(new MyCmp(4)));
        assertTrue(map_.contains(new MyCmp(3)));
        assertTrue(map_.contains(new MyCmp(1)));
        assertEq(1, map_.getVal(new MyCmp(2)));
        assertEq(2, map_.getVal(new MyCmp(4)));
        assertEq(3, map_.getVal(new MyCmp(3)));
        assertEq(4, map_.getVal(new MyCmp(1)));
    }

    @Test
    public void putAllMap2Test() {
        NatCmpTreeMap<MyCmp,Integer> map_ = new NatCmpTreeMap<MyCmp,Integer>();
        map_.put(new MyCmp(2), 1);
        map_.put(new MyCmp(4), 2);
        NatCmpTreeMap<MyCmp,Integer> mapToPut_ = new NatCmpTreeMap<MyCmp,Integer>();
        mapToPut_.put(new MyCmp(4), 3);
        mapToPut_.put(new MyCmp(3), 4);
        map_.putAllMap(mapToPut_);
        assertEq(3, map_.size());
        assertTrue(map_.contains(new MyCmp(2)));
        assertTrue(map_.contains(new MyCmp(4)));
        assertTrue(map_.contains(new MyCmp(3)));
        assertEq(1, map_.getVal(new MyCmp(2)));
        assertEq(3, map_.getVal(new MyCmp(4)));
        assertEq(4, map_.getVal(new MyCmp(3)));
    }

    @Test
    public void getKey1Test() {
        NatCmpTreeMap<MyCmp,Integer> map_ = new NatCmpTreeMap<MyCmp,Integer>();
        map_.put(new MyCmp(2), 1);
        map_.put(new MyCmp(4), 2);
        NatCmpTreeMap<MyCmp,Integer> mapToPut_ = new NatCmpTreeMap<MyCmp,Integer>();
        mapToPut_.put(new MyCmp(4), 3);
        mapToPut_.put(new MyCmp(3), 4);
        assertEq(2, map_.size());
        assertEq(new MyCmp(2), map_.getKey(0));
        assertEq(new MyCmp(4), map_.getKey(1));
        assertEq(2, mapToPut_.size());
        assertEq(new MyCmp(3), mapToPut_.getKey(0));
        assertEq(new MyCmp(4), mapToPut_.getKey(1));
    }

    @Test
    public void getValue1Test() {
        NatCmpTreeMap<MyCmp,Integer> map_ = new NatCmpTreeMap<MyCmp,Integer>();
        map_.put(new MyCmp(2), 1);
        map_.put(new MyCmp(4), 2);
        NatCmpTreeMap<MyCmp,Integer> mapToPut_ = new NatCmpTreeMap<MyCmp,Integer>();
        mapToPut_.put(new MyCmp(4), 3);
        mapToPut_.put(new MyCmp(3), 4);
        assertEq(2, map_.size());
        assertEq(1, map_.getValue(0));
        assertEq(2, map_.getValue(1));
        assertEq(2, mapToPut_.size());
        assertEq(4, mapToPut_.getValue(0));
        assertEq(3, mapToPut_.getValue(1));
    }
}
