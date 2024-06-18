package code.network;

import code.util.CustList;
import code.util.core.BoolVal;
import org.junit.Test;

public final class NetCommonTest extends EquallableNetworkUtil {
    @Test
    public void toBoolEquals1() {
        assertFalse(NetCommon.toBoolEquals('0'));
    }
    @Test
    public void toBoolEquals2() {
        assertTrue(NetCommon.toBoolEquals('1'));
    }
    @Test
    public void toBoolEquals3() {
        assertFalse(NetCommon.toBoolEquals("0"));
    }
    @Test
    public void toBoolEquals4() {
        assertTrue(NetCommon.toBoolEquals("1"));
    }
    @Test
    public void toBoolEquals5() {
        assertFalse(NetCommon.toBoolEquals("0",0));
    }
    @Test
    public void toBoolEquals6() {
        assertTrue(NetCommon.toBoolEquals("1",0));
    }
    @Test
    public void exportBool1() {
        assertEq("0",NetCommon.exportBool(false));
    }
    @Test
    public void exportBool2() {
        assertEq("1",NetCommon.exportBool(true));
    }
    @Test
    public void exportBool3() {
        assertEq("0",NetCommon.exportBool(BoolVal.FALSE));
    }
    @Test
    public void exportBool4() {
        assertEq("1",NetCommon.exportBool(BoolVal.TRUE));
    }
    @Test
    public void saveExit1() {
        Exiting res_ = save(init(false, true, false, true));
        assertFalse(res_.isClosing());
        assertTrue(res_.isForced());
        assertFalse(res_.isServer());
        assertTrue(res_.isTooManyPlayers());
    }
    @Test
    public void saveExit2() {
        Exiting res_ = save(init(true, false, true, false));
        assertTrue(res_.isClosing());
        assertFalse(res_.isForced());
        assertTrue(res_.isServer());
        assertFalse(res_.isTooManyPlayers());
    }
    private Exiting init(boolean _c, boolean _f, boolean _s, boolean _t) {
        Exiting e_ = new Exiting();
        e_.setClosing(_c);
        e_.setForced(_f);
        e_.setServer(_s);
        e_.setTooManyPlayers(_t);
        return e_;
    }

    public static Exiting save(Exiting _e) {
        String s_ = NetCommon.exportExiting(_e);
        CustList<String> ls_ = new CustList<String>();
        ls_.add(s_);
        return NetCommon.importExiting(ls_);
    }
}
