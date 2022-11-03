package aiki.beans.moves.effects;

import org.junit.Test;

public final class EffectSwitchMoveTypesBeanTest extends InitDbMoveEffectSwitch {
    @Test
    public void getReplacingTypes1() {
        assertSizeEq(1,callEffectSwitchMoveTypesBeanReplacingTypesGet(dispMoveEffSwitchMoveTypes()));
    }
    @Test
    public void getReplacingTypes2() {
        assertEq(T_TYPE1,elt(callEffectSwitchMoveTypesBeanReplacingTypesGet(dispMoveEffSwitchMoveTypes()),0));
    }
    @Test
    public void getTrReplacingTypes() {
        assertEq(T_TYPE1_TR,callEffectSwitchMoveTypesBeanGetTrReplacingTypes(dispMoveEffSwitchMoveTypes(),0));
    }
    @Test
    public void getChangeTypes1() {
        assertSizeEq(1,callEffectSwitchMoveTypesBeanChangeTypesGet(dispMoveEffSwitchMoveTypes()));
    }
    @Test
    public void getChangeTypes2() {
        assertEq(T_TYPE1,first(elt(callEffectSwitchMoveTypesBeanChangeTypesGet(dispMoveEffSwitchMoveTypes()),0)));
    }
    @Test
    public void getChangeTypes3() {
        assertEq(T_TYPE2_TR,second(elt(callEffectSwitchMoveTypesBeanChangeTypesGet(dispMoveEffSwitchMoveTypes()),0)));
    }
    @Test
    public void getTrChangedTypes() {
        assertEq(T_TYPE1_TR,callEffectSwitchMoveTypesBeanGetTrChangedTypes(dispMoveEffSwitchMoveTypes(),0));
    }
}
