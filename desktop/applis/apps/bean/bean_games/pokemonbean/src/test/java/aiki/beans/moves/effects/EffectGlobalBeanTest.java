package aiki.beans.moves.effects;

import code.maths.Rate;
import code.util.StringList;
import org.junit.Test;

public final class EffectGlobalBeanTest extends InitDbMoveEffectGlobal {
    @Test
    public void invokingMovesChangingTypes() {
        StringList moves_ = EffectGlobalBean.invokingMovesChangingTypes(feedDbMoveEffDataDamMovesChangingTypes(eff(true, true, true, true, true)).getData());
        assertEq(1,moves_.size());
        assertEq(M_WEA,moves_.get(0));
    }
    @Test
    public void invokingMoves() {
        StringList moves_ = EffectGlobalBean.invokingMoves(feedDbMoveEffDataDamMoves(eff(true, true, true, true, true)).getData());
        assertEq(1,moves_.size());
        assertEq(M_WEA,moves_.get(0));
    }
    @Test
    public void weather1() {
        assertTrue(callEffectGlobalBeanWeatherGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)));
    }
    @Test
    public void weather2() {
        assertFalse(callEffectGlobalBeanWeatherGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(false, true, true, true, true)),0)));
    }
    @Test
    public void getCanceledIfUsed1() {
        assertTrue(callEffectGlobalBeanCanceledIfUsedGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)));
    }
    @Test
    public void getCanceledIfUsed2() {
        assertFalse(callEffectGlobalBeanCanceledIfUsedGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, false, true)),0)));
    }
    @Test
    public void getReverseOrderOfSortBySpeed1() {
        assertTrue(callEffectGlobalBeanReverseOrderOfSortBySpeedGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)));
    }
    @Test
    public void getReverseOrderOfSortBySpeed2() {
        assertFalse(callEffectGlobalBeanReverseOrderOfSortBySpeedGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, false)),0)));
    }
    @Test
    public void getUnusableItem1() {
        assertTrue(callEffectGlobalBeanUnusableItemGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)));
    }
    @Test
    public void getUnusableItem2() {
        assertFalse(callEffectGlobalBeanUnusableItemGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, false, true, true)),0)));
    }
    @Test
    public void getPuttingKo1() {
        assertTrue(callEffectGlobalBeanPuttingKoGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)));
    }
    @Test
    public void getPuttingKo2() {
        assertFalse(callEffectGlobalBeanPuttingKoGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, false, true, true, true)),0)));
    }
    @Test
    public void getMultAccuracy1() {
        assertEq(Rate.one(),callEffectGlobalBeanMultAccuracyGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)));
    }
    @Test
    public void getDamageEndRound1() {
        assertEq(Rate.one(),callEffectGlobalBeanDamageEndRoundGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)));
    }
    @Test
    public void getHealingEndRoundGround1() {
        assertEq(Rate.one(),callEffectGlobalBeanHealingEndRoundGroundGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)));
    }
    @Test
    public void getHealingEndRound1() {
        assertEq(Rate.one(),callEffectGlobalBeanHealingEndRoundGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)));
    }
    @Test
    public void getMultEffectLovingAlly1() {
        assertEq(Rate.one(),callEffectGlobalBeanMultEffectLovingAllyGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)));
    }
    @Test
    public void getInvokedMoveTerrain() {
        assertEq(M_STA,callEffectGlobalBeanInvokedMoveTerrainGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)));
    }
    @Test
    public void getInvokingMovesChangingTypes1() {
        assertSizeEq(1,callEffectGlobalBeanInvokingMovesChangingTypesGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)));
    }
    @Test
    public void getInvokingMovesChangingTypes2() {
        assertEq(M_STA,elt(callEffectGlobalBeanInvokingMovesChangingTypesGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)),0));
    }
    @Test
    public void getInvokingMoves1() {
        assertSizeEq(1,callEffectGlobalBeanInvokingMovesGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)));
    }
    @Test
    public void getInvokingMoves2() {
        assertEq(M_WEA,elt(callEffectGlobalBeanInvokingMovesGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)),0));
    }
    @Test
    public void getUnusableMoves1() {
        assertSizeEq(1,callEffectGlobalBeanUnusableMovesGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)));
    }
    @Test
    public void getUnusableMoves2() {
        assertEq(M_STA,elt(callEffectGlobalBeanUnusableMovesGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)),0));
    }
    @Test
    public void getCancelEffects1() {
        assertSizeEq(1,callEffectGlobalBeanCancelEffectsGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)));
    }
    @Test
    public void getCancelEffects2() {
        assertEq(M_STA,elt(callEffectGlobalBeanCancelEffectsGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)),0));
    }
    @Test
    public void getMovesUsedByTargetedFighters1() {
        assertSizeEq(1,callEffectGlobalBeanMovesUsedByTargetedFightersGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)));
    }
    @Test
    public void getMovesUsedByTargetedFighters2() {
        assertEq(M_STA,elt(callEffectGlobalBeanMovesUsedByTargetedFightersGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)),0));
    }
    @Test
    public void getDisableImmuAgainstTypes1() {
        assertSizeEq(1,callEffectGlobalBeanDisableImmuAgainstTypesGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)));
    }
    @Test
    public void getDisableImmuAgainstTypes2() {
        assertEq(T_TYPE1_TR,elt(callEffectGlobalBeanDisableImmuAgainstTypesGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)),0));
    }
    @Test
    public void getChangedTypesTerrain1() {
        assertSizeEq(1,callEffectGlobalBeanChangedTypesTerrainGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)));
    }
    @Test
    public void getChangedTypesTerrain2() {
        assertEq(T_TYPE1_TR,elt(callEffectGlobalBeanChangedTypesTerrainGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)),0));
    }
    @Test
    public void getImmuneTypes1() {
        assertSizeEq(1,callEffectGlobalBeanImmuneTypesGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)));
    }
    @Test
    public void getImmuneTypes2() {
        assertEq(T_TYPE1_TR,elt(callEffectGlobalBeanImmuneTypesGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)),0));
    }
    @Test
    public void getPreventStatus1() {
        assertSizeEq(1,callEffectGlobalBeanPreventStatusGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)));
    }
    @Test
    public void getPreventStatus2() {
        assertEq(S_STA_SIM,elt(callEffectGlobalBeanPreventStatusGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)),0));
    }
    @Test
    public void getCancelProtectingAbilities1() {
        assertSizeEq(1,callEffectGlobalBeanCancelProtectingAbilitiesGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)));
    }
    @Test
    public void getCancelProtectingAbilities2() {
        assertEq(A_ABILITY,elt(callEffectGlobalBeanCancelProtectingAbilitiesGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)),0));
    }
    @Test
    public void getCancelChgtStat1() {
        assertSizeEq(1,callEffectGlobalBeanCancelChgtStatGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)));
    }
    @Test
    public void getCancelChgtStat2() {
        assertEq(ST_SPEED_TR,elt(callEffectGlobalBeanCancelChgtStatGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)),0));
    }
    @Test
    public void getMultPowerMoves1() {
        assertSizeEq(1,callEffectGlobalBeanMultPowerMovesGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)));
    }
    @Test
    public void getMultPowerMoves2() {
        assertEq(M_STA,first(elt(callEffectGlobalBeanMultPowerMovesGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)),0)));
    }
    @Test
    public void getMultPowerMoves3() {
        assertEq(Rate.one(),second(elt(callEffectGlobalBeanMultPowerMovesGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)),0)));
    }
    @Test
    public void getMultDamageTypesMoves1() {
        assertSizeEq(1,callEffectGlobalBeanMultDamageTypesMovesGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)));
    }
    @Test
    public void getMultDamageTypesMoves2() {
        assertEq(T_TYPE1_TR,first(elt(callEffectGlobalBeanMultDamageTypesMovesGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)),0)));
    }
    @Test
    public void getMultDamageTypesMoves3() {
        assertEq(Rate.one(),second(elt(callEffectGlobalBeanMultDamageTypesMovesGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)),0)));
    }
    @Test
    public void getMultDamagePrepaRound1() {
        assertSizeEq(1,callEffectGlobalBeanMultDamagePrepaRoundGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)));
    }
    @Test
    public void getMultDamagePrepaRound2() {
        assertEq(T_TYPE1_TR,first(elt(callEffectGlobalBeanMultDamagePrepaRoundGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)),0)));
    }
    @Test
    public void getMultDamagePrepaRound3() {
        assertEq(Rate.one(),second(elt(callEffectGlobalBeanMultDamagePrepaRoundGet(dispMoveEffGlobal(feedDbMoveEffDataDam(eff(true, true, true, true, true)),0)),0)));
    }
}
