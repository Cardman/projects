package aiki.beans.moves.effects;

import org.junit.Test;

public final class EffectProtectFromTypesBeanTest extends InitDbMoveEffectChangeProtect {
    @Test
    public void getImmuAgainstTypes1() {
        assertSizeEq(1,callEffectProtectFromTypesBeanImmuAgainstTypesGet(dispMoveEffProtectFromTypes()));
    }
    @Test
    public void getImmuAgainstTypes2() {
        assertEq(T_TYPE1,elt(callEffectProtectFromTypesBeanImmuAgainstTypesGet(dispMoveEffProtectFromTypes()),0));
    }
    @Test
    public void getTrType() {
        assertEq(T_TYPE1_TR,callEffectProtectFromTypesBeanGetTrType(dispMoveEffProtectFromTypes(),0));
    }
}
