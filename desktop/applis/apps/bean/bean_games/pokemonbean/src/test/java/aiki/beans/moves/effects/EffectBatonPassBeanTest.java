package aiki.beans.moves.effects;

import code.scripts.confs.PkScriptPages;
import org.junit.Test;

public final class EffectBatonPassBeanTest extends InitDbMoveEffectOther {
    @Test
    public void getMoves1() {
        assertSizeEq(1,callEffectBatonPassBeanMovesGet(dispMoveEffBatonPass()));
    }
    @Test
    public void getMoves2() {
        assertEq(M_WEA,elt(callEffectBatonPassBeanMovesGet(dispMoveEffBatonPass()),0));
    }
    @Test
    public void getTrMove() {
        assertEq(M_WEA_TR,callEffectBatonPassBeanGetTrMove(dispMoveEffBatonPass(),0));
    }
    @Test
    public void clickMove1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callEffectBatonPassBeanClickMove(dispMoveEffBatonPass(),0));
    }
    @Test
    public void clickMove2() {
        assertEq(M_WEA,callEffectBatonPassBeanClickMoveId(dispMoveEffBatonPass(),0));
    }
}
