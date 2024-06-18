package code.network;

import cards.network.threads.*;
import code.util.*;
import code.util.core.*;
import code.util.ints.*;
import org.junit.Test;

public final class NetTest extends EquallableNetworkUtil {

    @Test
    public void longs1() {
        assertTrue(saveLongs(new Longs()).isEmpty());
    }

    @Test
    public void longs2() {
        Longs res_ = saveLongs(Longs.newList(1, 2, -3));
        assertEq(3, res_.size());
        assertEq(1, res_.get(0));
        assertEq(2, res_.get(1));
        assertEq(-3, res_.get(2));
    }

    @Test
    public void longs3() {
        assertTrue(saveLongsList(new CustList<Longs>()).isEmpty());
    }

    @Test
    public void longs4() {
        CustList<Longs> in_ = new CustList<Longs>();
        in_.add(Longs.newList(1, 2, -3));
        in_.add(Longs.newList(-1, 2, 5));
        CustList<Longs> res_ = saveLongsList(in_);
        assertEq(2, res_.size());
        assertEq(3, res_.get(0).size());
        assertEq(1, res_.get(0).get(0));
        assertEq(2, res_.get(0).get(1));
        assertEq(-3, res_.get(0).get(2));
        assertEq(3, res_.get(1).size());
        assertEq(-1, res_.get(1).get(0));
        assertEq(2, res_.get(1).get(1));
        assertEq(5, res_.get(1).get(2));
    }

    @Test
    public void longs5() {
        CustList<Longs> in_ = new CustList<Longs>();
        in_.add(Longs.newList(1));
        in_.add(Longs.newList(-1, 2));
        CustList<Longs> res_ = saveLongsList(in_);
        assertEq(2, res_.size());
        assertEq(1, res_.get(0).size());
        assertEq(1, res_.get(0).get(0));
        assertEq(2, res_.get(1).size());
        assertEq(-1, res_.get(1).get(0));
        assertEq(2, res_.get(1).get(1));
    }

    @Test
    public void bytes1() {
        assertTrue(saveBytes(new Bytes()).isEmpty());
    }

    @Test
    public void bytes2() {
        Bytes res_ = saveBytes(Bytes.newList((byte)1,(byte) 2,(byte) -3));
        assertEq(3, res_.size());
        assertEq(1, res_.get(0));
        assertEq(2, res_.get(1));
        assertEq(-3, res_.get(2));
    }

    @Test
    public void bytes3() {
        assertTrue(saveBytesList(new CustList<Bytes>()).isEmpty());
    }

    @Test
    public void bytes4() {
        CustList<Bytes> in_ = new CustList<Bytes>();
        in_.add(Bytes.newList((byte)1,(byte) 2,(byte) -3));
        in_.add(Bytes.newList((byte)-1,(byte) 2,(byte) 5));
        CustList<Bytes> res_ = saveBytesList(in_);
        assertEq(2, res_.size());
        assertEq(3, res_.get(0).size());
        assertEq(1, res_.get(0).get(0));
        assertEq(2, res_.get(0).get(1));
        assertEq(-3, res_.get(0).get(2));
        assertEq(3, res_.get(1).size());
        assertEq(-1, res_.get(1).get(0));
        assertEq(2, res_.get(1).get(1));
        assertEq(5, res_.get(1).get(2));
    }

    @Test
    public void bytes5() {
        CustList<Bytes> in_ = new CustList<Bytes>();
        in_.add(Bytes.newList((byte)1));
        in_.add(Bytes.newList((byte)-1,(byte) 2));
        CustList<Bytes> res_ = saveBytesList(in_);
        assertEq(2, res_.size());
        assertEq(1, res_.get(0).size());
        assertEq(1, res_.get(0).get(0));
        assertEq(2, res_.get(1).size());
        assertEq(-1, res_.get(1).get(0));
        assertEq(2, res_.get(1).get(1));
    }

    @Test
    public void shorts1() {
        assertTrue(saveShorts(new Shorts()).isEmpty());
    }

    @Test
    public void shorts2() {
        Shorts res_ = saveShorts(Shorts.newList((short) 1,(short) 2,(short) -3));
        assertEq(3, res_.size());
        assertEq(1, res_.get(0));
        assertEq(2, res_.get(1));
        assertEq(-3, res_.get(2));
    }
    public static Longs saveLongs(Longs _l) {
        return Net.importLongList(parse(Net.exportLongList(_l, Net.SEP_1)),Net.SEP_1);
    }

    public static CustList<Longs> saveLongsList(CustList<Longs> _l) {
        return Net.importLongsList(parse(Net.exportLongsList(_l, Net.SEP_1, Net.SEP_2)),Net.SEP_1, Net.SEP_2);
    }
    public static Bytes saveBytes(Bytes _l) {
        return Net.importByteList(parse(Net.exportByteList(_l, Net.SEP_1)),Net.SEP_1);
    }

    public static CustList<Bytes> saveBytesList(CustList<Bytes> _l) {
        return Net.importByteLists(parse(Net.exportByteLists(_l, Net.SEP_1, Net.SEP_2)),Net.SEP_1, Net.SEP_2);
    }
    public static Shorts saveShorts(Shorts _l) {
        return Net.importShortList(parse(Net.exportShortList(_l, Net.SEP_1)),Net.SEP_1);
    }

    private static String parse(String _in) {
        String i_ = Net.SEP_0 + _in;
        FirstSeparatorFind cs_ = new FirstSeparatorFind(i_, Net.SEP_0);
        return StringUtil.partsStrQuick(splitter(), i_, index(cs_), i_.length(), 0, cs_).get(0);
    }

    private static int index(FirstSeparatorFind _cs) {
        assertTrue(_cs.isFound());
        return _cs.getIndex();
    }

    private static CustList<IntSplitPartsFields> splitter() {
        CustList<IntSplitPartsFields> window_ = new CustList<IntSplitPartsFields>();
        window_.add(new DefSplitPartsFields());
        return window_;
    }
}
