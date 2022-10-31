package aiki.beans.moves.effects;

import code.maths.Rate;
import org.junit.Test;

public final class EffectDamageRateBeanTest extends InitDbMoveEffectDamageRate {
    @Test
    public void getWinHp1() {
        assertTrue(callEffectDamageRateBeanWinHpGet(dispMoveEffDamageRate(feedDbMoveEffDataDamComp(Rate.one()),0)));
    }
    @Test
    public void getWinHp2() {
        assertFalse(callEffectDamageRateBeanWinHpGet(dispMoveEffDamageRate(feedDbMoveEffDataDamComp(Rate.minusOne()),0)));
    }
    @Test
    public void getRateDamage() {
        assertEq(Rate.one(),callEffectDamageRateBeanRateDamageGet(dispMoveEffDamageRate(feedDbMoveEffDataDamComp(Rate.one()),0)));
    }
}
