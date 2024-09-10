package aiki.beans.moves.effects;

import code.scripts.confs.PkScriptPages;
import org.junit.Test;

public final class EffectUnprotectFromTypesBeanTest extends InitDbMoveEffectChangeProtect {
    @Test
    public void getDisableImmuAgainstTypes1() {
        assertSizeEq(1,callEffectUnprotectFromTypesBeanDisableImmuAgainstTypesGet(dispMoveEffUnprotectFromTypes()));
    }
    @Test
    public void getDisableImmuAgainstTypes2() {
        assertEq(T_TYPE1,elt(callEffectUnprotectFromTypesBeanDisableImmuAgainstTypesGet(dispMoveEffUnprotectFromTypes()),0));
    }
    @Test
    public void getTrDisableImmuType() {
        assertEq(T_TYPE1_TR,callEffectUnprotectFromTypesBeanGetTrDisableImmuType(dispMoveEffUnprotectFromTypes(),0));
    }
    @Test
    public void getAttackTargetWithTypes1() {
        assertSizeEq(1,callEffectUnprotectFromTypesBeanAttackTargetWithTypesGet(dispMoveEffUnprotectFromTypes()));
    }
    @Test
    public void getAttackTargetWithTypes2() {
        assertEq(T_TYPE1,elt(callEffectUnprotectFromTypesBeanAttackTargetWithTypesGet(dispMoveEffUnprotectFromTypes()),0));
    }
    @Test
    public void getTrAttackTargetType() {
        assertEq(T_TYPE1_TR,callEffectUnprotectFromTypesBeanGetTrAttackTargetType(dispMoveEffUnprotectFromTypes(),0));
    }
    @Test
    public void getDisableImmuFromMoves1() {
        assertSizeEq(1,callEffectUnprotectFromTypesBeanDisableImmuFromMovesGet(dispMoveEffUnprotectFromTypes()));
    }
    @Test
    public void getDisableImmuFromMoves2() {
        assertEq(M_STA,elt(callEffectUnprotectFromTypesBeanDisableImmuFromMovesGet(dispMoveEffUnprotectFromTypes()),0));
    }
    @Test
    public void getTrDisableImmuMove() {
        assertEq(M_STA_TR,callEffectUnprotectFromTypesBeanGetTrDisableImmuMove(dispMoveEffUnprotectFromTypes(),0));
    }
    @Test
    public void clickMove1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callEffectUnprotectFromTypesBeanClickMove(dispMoveEffUnprotectFromTypes(),0,0));
    }
    @Test
    public void clickMove2() {
        assertEq(M_STA,callEffectUnprotectFromTypesBeanClickMoveId(dispMoveEffUnprotectFromTypes(),0,0));
    }
    @Test
    public void getTypes1() {
        assertSizeEq(1,callEffectUnprotectFromTypesBeanTypesGet(dispMoveEffUnprotectFromTypes()));
    }
    @Test
    public void getTypes2() {
        assertEq(T_TYPE1,callTypesDuoGetDamageType(elt(callEffectUnprotectFromTypesBeanTypesGet(dispMoveEffUnprotectFromTypes()),0)));
    }
    @Test
    public void getTypes3() {
        assertEq(T_TYPE2,callTypesDuoGetPokemonType(elt(callEffectUnprotectFromTypesBeanTypesGet(dispMoveEffUnprotectFromTypes()),0)));
    }
    @Test
    public void getTrDamageType() {
        assertEq(T_TYPE1_TR,callEffectUnprotectFromTypesBeanGetTrDamageType(dispMoveEffUnprotectFromTypes(),0));
    }
    @Test
    public void getTrPokemonType() {
        assertEq(T_TYPE2_TR,callEffectUnprotectFromTypesBeanGetTrPokemonType(dispMoveEffUnprotectFromTypes(),0));
    }
}
