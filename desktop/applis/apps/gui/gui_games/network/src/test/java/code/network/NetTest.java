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

    public static Longs saveLongs(Longs _l) {
        String info_ = Net.SEP_0 + Net.exportLongList(_l, Net.SEP_1);
        FirstSeparatorFind cs_ = new FirstSeparatorFind(info_, Net.SEP_0);
        assertTrue(cs_.isFound());
        int index_ = cs_.getIndex();
        CustList<IntSplitPartsFields> window_ = new CustList<IntSplitPartsFields>();
        window_.add(new DefSplitPartsFields());
        return Net.importLongList(StringUtil.partsStrQuick(window_,info_,index_,info_.length(),0,cs_).get(0),Net.SEP_1);
    }

    public static CustList<Longs> saveLongsList(CustList<Longs> _l) {
        String info_ = Net.SEP_0 + Net.exportLongsList(_l, Net.SEP_1, Net.SEP_2);
        FirstSeparatorFind cs_ = new FirstSeparatorFind(info_, Net.SEP_0);
        assertTrue(cs_.isFound());
        int index_ = cs_.getIndex();
        CustList<IntSplitPartsFields> window_ = new CustList<IntSplitPartsFields>();
        window_.add(new DefSplitPartsFields());
        return Net.importLongsList(StringUtil.partsStrQuick(window_,info_,index_,info_.length(),0,cs_).get(0),Net.SEP_1, Net.SEP_2);
    }
}
