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
}
