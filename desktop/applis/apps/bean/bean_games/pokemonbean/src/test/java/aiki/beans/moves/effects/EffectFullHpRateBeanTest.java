package aiki.beans.moves.effects;

import aiki.db.MessagesDataBaseConstants;
import code.maths.Rate;
import org.junit.Test;

public final class EffectFullHpRateBeanTest extends InitDbMoveEffectOther {
    @Test
    public void getLeftUserHp() {
        assertEq(Rate.one(),callEffectFullHpRateBeanLeftUserHpGet(dispMoveEffFullHpRate()));
    }
    @Test
    public void getClosestFoeDamageRateHp() {
        assertEq(Rate.one(),callEffectFullHpRateBeanClosestFoeDamageRateHpGet(dispMoveEffFullHpRate()));
    }
    @Test
    public void getRestoredHp() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,callEffectFullHpRateBeanRestoredHpGet(dispMoveEffFullHpRate()));
    }
    @Test
    public void getMapVarsRestored1() {
        assertSizeEq(1,callEffectFullHpRateBeanMapVarsRestoredGet(dispMoveEffFullHpRate()));
    }
    @Test
    public void getMapVarsRestored2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,first(elt(callEffectFullHpRateBeanMapVarsRestoredGet(dispMoveEffFullHpRate()),0)));
    }
    @Test
    public void getMapVarsRestored3() {
        assertEq(TIME,second(elt(callEffectFullHpRateBeanMapVarsRestoredGet(dispMoveEffFullHpRate()),0)));
    }
}
