package code.util;

import org.junit.Test;


public class IdMapTest extends EquallableExUtil {


    @Test
    public void put1Test() {
        IdMap<Ints,Integer> otherMap_ = new IdMap<Ints,Integer>(new IdMap<Ints,Integer>(new CollCapacity(0)));
        Ints k1_ = Ints.newList(0, 0);
        Ints k2_ = Ints.newList(0, 1);
        Ints k3_ = Ints.newList(0, 1);
        assertEq(0, otherMap_.getList().size());
        otherMap_.put(k1_, 0);
        assertEq(1, otherMap_.getList().size());
        assertSame(k1_, otherMap_.getKey(0));
        assertEq(0, otherMap_.getValue(0));
        otherMap_.put(k2_, 1);
        assertEq(2, otherMap_.getList().size());
        assertSame(k1_, otherMap_.getKey(0));
        assertEq(0, otherMap_.getValue(0));
        assertSame(k2_, otherMap_.getKey(1));
        assertEq(1, otherMap_.getValue(1));
        otherMap_.put(k3_, 2);
        assertEq(3, otherMap_.getList().size());
        assertEq(0, otherMap_.getValue(0));
        assertSame(k1_, otherMap_.getKey(0));
        assertEq(1, otherMap_.getValue(1));
        assertSame(k2_, otherMap_.getKey(1));
        assertEq(2, otherMap_.getValue(2));
        assertSame(k3_, otherMap_.getKey(2));
    }

    @Test
    public void size1Test() {
        IdMap<Ints,Integer> otherMap_ = new IdMap<Ints,Integer>();
        assertEq(0, otherMap_.size());
        otherMap_.put(Ints.newList(0, 0), 0);
        assertEq(1,otherMap_.size());
        otherMap_.put(Ints.newList(0, 1), 1);
        assertEq(2,otherMap_.size());
        otherMap_.put(Ints.newList(0, 1), 2);
        assertEq(3,otherMap_.size());
    }

    @Test
    public void contains1Test() {
        IdMap<Ints,Integer> otherMap_ = new IdMap<Ints,Integer>();
        Ints k1_;
        Ints k2_;
        Ints k3_;
        assertEq(0, otherMap_.size());
        k1_ = Ints.newList(0, 0);
        k2_ = Ints.newList(0, 1);
        k3_ = Ints.newList(0, 1);
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
        IdMap<Ints,Integer> otherMap_ = new IdMap<Ints,Integer>();
        Ints k1_;
        Ints k2_;
        k1_ = Ints.newList(0, 0);
        k2_ = Ints.newList(0, 1);
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
        IdMap<Ints,Integer> otherMap_ = new IdMap<Ints,Integer>();
        Ints k1_;
        Ints k2_;
        k1_ = Ints.newList(0, 0);
        k2_ = Ints.newList(0, 1);
        otherMap_.put(k1_, 0);
        otherMap_.put(k2_, 1);
        otherMap_.removeKey(Ints.newList(1, 1));
        assertEq(2,otherMap_.size());
        assertTrue(otherMap_.contains(k1_));
        assertTrue(otherMap_.contains(k2_));
        otherMap_.removeKey(k2_);
        assertEq(1,otherMap_.size());
        assertTrue(otherMap_.contains(k1_));
        assertTrue(!otherMap_.contains(k2_));
    }

    @Test
    public void putAllMap3Test() {
        Ints k1_;
        Ints k2_;
        Ints k3_;
        Ints k4_;
        k1_ = Ints.newList(0, 0);
        k2_ = Ints.newList(0, 1);
        k3_ = Ints.newList(1, 1);
        k4_ = Ints.newList(1, 0);
        IdMap<Ints,Integer> map_ = new IdMap<Ints,Integer>();
        map_.put(k1_, 0);
        map_.put(k2_, 1);
        IdMap<Ints,Integer> mapToPut_ = new IdMap<Ints,Integer>();
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
        Ints k1_;
        Ints k2_;
        Ints k3_;
        Ints k4_;
        k1_ = Ints.newList(0, 0);
        k2_ = Ints.newList(0, 1);
        k3_ = Ints.newList(1, 1);
        k4_ = Ints.newList(0, 1);
        IdMap<Ints,Integer> map_ = new IdMap<Ints,Integer>();
        map_.put(k1_, 0);
        map_.put(k2_, 1);
        IdMap<Ints,Integer> mapToPut_ = new IdMap<Ints,Integer>();
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
        Ints k1_;
        Ints k2_;
        Ints k3_;
        Ints k4_;
        k1_ = Ints.newList(0, 0);
        k2_ = Ints.newList(0, 1);
        k3_ = Ints.newList(1, 1);
        k4_ = Ints.newList(0, 1);
        IdMap<Ints,Integer> map_ = new IdMap<Ints,Integer>();
        map_.put(k1_, 0);
        map_.put(k2_, 1);
        IdMap<Ints,Integer> mapToPut_ = new IdMap<Ints,Integer>();
        mapToPut_.put(k3_, 2);
        mapToPut_.put(k4_, 3);
        map_.putAllMap(mapToPut_);
        IdList<Ints> keys_ = new IdList<Ints>(map_.getKeys());
        assertEq(4, keys_.size());
        assertTrue(keys_.containsObj(k1_));
        assertTrue(keys_.containsObj(k2_));
        assertTrue(keys_.containsObj(k3_));
        assertTrue(keys_.containsObj(k4_));
    }
}
