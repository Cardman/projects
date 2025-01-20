package code.sml;

import code.util.*;
import code.util.core.*;
import org.junit.Test;

public final class DocumentCoreUtilTest extends EquallableSerialUtil {
    @Test
    public void t1() {
        assertFalse(saveBool(BoolVal.FALSE));
    }
    @Test
    public void t2() {
        assertTrue(saveBool(BoolVal.TRUE));
    }
    @Test
    public void t3() {
        assertEq(1,saveByte(1));
    }
    @Test
    public void t4() {
        assertEq(1,saveShort(1));
    }
    @Test
    public void t5() {
        assertEq(1,saveInt(1));
    }
    @Test
    public void t6() {
        assertEq(1,saveLong(1));
    }
    @Test
    public void t7() {
        assertEq("1",saveString("1"));
    }
    @Test
    public void t8() {
        StringList ls_ = saveStringList(new StringList("1"));
        assertEq(1,ls_.size());
        assertEq("1",ls_.get(0));
    }
    @Test
    public void t9() {
        CustList<BoolVal> in_ = new CustList<BoolVal>();
        in_.add(BoolVal.FALSE);
        in_.add(BoolVal.TRUE);
        CustList<BoolVal> ls_ = saveBoolValList(in_);
        assertEq(2,ls_.size());
        assertEq(BoolVal.FALSE,ls_.get(0));
        assertEq(BoolVal.TRUE,ls_.get(1));
    }
    @Test
    public void t10() {
        Bytes ls_ = saveBytes(Bytes.newList((byte)1));
        assertEq(1,ls_.size());
        assertEq(1,ls_.get(0));
    }
    @Test
    public void t11() {
        Shorts ls_ = saveShorts(Shorts.newList((byte)1));
        assertEq(1,ls_.size());
        assertEq(1,ls_.get(0));
    }
    @Test
    public void t12() {
        Ints ls_ = saveInts(Ints.newList(1));
        assertEq(1,ls_.size());
        assertEq(1,ls_.get(0));
    }
    @Test
    public void t13() {
        Longs ls_ = saveLongs(Longs.newList(1));
        assertEq(1,ls_.size());
        assertEq(1,ls_.get(0));
    }
    @Test
    public void t14() {
        CustList<BoolVal> in_ = new CustList<BoolVal>();
        in_.add(BoolVal.FALSE);
        in_.add(BoolVal.TRUE);
        CustList<CustList<BoolVal>> inSingle_ = new CustList<CustList<BoolVal>>();
        inSingle_.add(in_);
        CustList<CustList<BoolVal>> ls_ = saveListBoolValList(inSingle_);
        assertEq(1,ls_.size());
        assertEq(2,ls_.get(0).size());
        assertEq(BoolVal.FALSE,ls_.get(0).get(0));
        assertEq(BoolVal.TRUE,ls_.get(0).get(1));
    }
    @Test
    public void t15() {
        Bytes in_ = Bytes.newList((byte)1);
        CustList<Bytes> inSingle_ = new CustList<Bytes>();
        inSingle_.add(in_);
        CustList<Bytes> ls_ = saveListBytes(inSingle_);
        assertEq(1,ls_.size());
        assertEq(1,ls_.get(0).size());
        assertEq(1,ls_.get(0).get(0));
    }
    @Test
    public void t16() {
        Longs in_ = Longs.newList(1);
        CustList<Longs> inSingle_ = new CustList<Longs>();
        inSingle_.add(in_);
        CustList<Longs> ls_ = saveListLongs(inSingle_);
        assertEq(1,ls_.size());
        assertEq(1,ls_.get(0).size());
        assertEq(1,ls_.get(0).get(0));
    }
    @Test
    public void t17() {
        StringMap<BoolVal> inSingle_ = new StringMap<BoolVal>();
        inSingle_.addEntry("0",BoolVal.FALSE);
        inSingle_.addEntry("1",BoolVal.TRUE);
        StringMap<BoolVal> ls_ = saveStrMapBool(inSingle_);
        assertEq(2,ls_.size());
        assertEq("0",ls_.getKey(0));
        assertEq(BoolVal.FALSE,ls_.getValue(0));
        assertEq("1",ls_.getKey(1));
        assertEq(BoolVal.TRUE,ls_.getValue(1));
    }
    @Test
    public void t19() {
        StringMap<Integer> inSingle_ = new StringMap<Integer>();
        inSingle_.addEntry("1",1);
        StringMap<Integer> ls_ = saveStrMapInt(inSingle_);
        assertEq(1,ls_.size());
        assertEq("1",ls_.getKey(0));
        assertEq(1,ls_.getValue(0));
    }
    @Test
    public void t20() {
        StringMap<String> inSingle_ = new StringMap<String>();
        inSingle_.addEntry("1","2");
        StringMap<String> ls_ = saveStrMapString(inSingle_);
        assertEq(1,ls_.size());
        assertEq("1",ls_.getKey(0));
        assertEq("2",ls_.getValue(0));
    }

    @Test
    public void t21() {
        assertEq("1",saveStringSec("1"));
    }
    @Test
    public void t22() {
        StringMap<Ints> inSingle_ = new StringMap<Ints>();
        inSingle_.addEntry("1",Ints.newList(2));
        StringMap<Ints> ls_ = saveStrMapInts(inSingle_);
        assertEq(1,ls_.size());
        assertEq("1",ls_.getKey(0));
        assertEq(1,ls_.getValue(0).size());
        assertEq(2,ls_.getValue(0).get(0));
    }
    @Test
    public void t23() {
        StringMap<StringList> inSingle_ = new StringMap<StringList>();
        inSingle_.addEntry("1",new StringList("2"));
        StringMap<StringList> ls_ = saveStrMapStringList(inSingle_);
        assertEq(1,ls_.size());
        assertEq("1",ls_.getKey(0));
        assertEq(1,ls_.getValue(0).size());
        assertEq("2",ls_.getValue(0).get(0));
    }
    @Test
    public void t24() {
        IntTreeMap<Byte> inSingle_ = new IntTreeMap<Byte>();
        inSingle_.addEntry(1,(byte)2);
        IntTreeMap<Byte> ls_ = saveMapIntByte(inSingle_);
        assertEq(1,ls_.size());
        assertEq(1,ls_.getKey(0));
        assertEq(2,ls_.getValue(0));
    }
    @Test
    public void t25() {
        LongMap<String> inSingle_ = new LongMap<String>();
        inSingle_.addEntry(1L,"2");
        LongMap<String> ls_ = saveMapIntString(inSingle_);
        assertEq(1,ls_.size());
        assertEq(1,ls_.getKey(0));
        assertEq("2",ls_.getValue(0));
    }
    @Test
    public void t26() {
        IntMap<BoolVal> inSingle_ = new IntMap<BoolVal>();
        inSingle_.addEntry(0,BoolVal.FALSE);
        inSingle_.addEntry(1,BoolVal.TRUE);
        IntMap<BoolVal> ls_ = saveMapIntBoolVal(inSingle_);
        assertEq(2,ls_.size());
        assertEq(0,ls_.getKey(0));
        assertEq(BoolVal.FALSE,ls_.getValue(0));
        assertEq(1,ls_.getKey(1));
        assertEq(BoolVal.TRUE,ls_.getValue(1));
    }
    @Test
    public void t27() {
        IntMap<Integer> inSingle_ = new IntMap<Integer>();
        inSingle_.addEntry(1,2);
        IntMap<Integer> ls_ = saveMapByteByte(inSingle_);
        assertEq(1,ls_.size());
        assertEq(1,ls_.getKey(0));
        assertEq(2,ls_.getValue(0));
    }
    @Test
    public void t28() {
        IntMap<Ints> inSingle_ = new IntMap<Ints>();
        inSingle_.addEntry(1,Ints.newList(2));
        IntMap<Ints> ls_ = saveMapByteListByte(inSingle_);
        assertEq(1,ls_.size());
        assertEq(1,ls_.getKey(0));
        assertEq(1,ls_.getValue(0).size());
        assertEq(2,ls_.getValue(0).get(0));
    }
}
