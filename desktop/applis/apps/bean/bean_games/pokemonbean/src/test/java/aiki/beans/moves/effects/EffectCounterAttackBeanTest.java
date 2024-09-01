package aiki.beans.moves.effects;

import aiki.db.MessagesDataBaseConstants;
import code.maths.Rate;
import org.junit.Test;

public final class EffectCounterAttackBeanTest extends InitDbMoveEffectOther {
    @Test
    public void getSufferingDamageDirectMove() {
        assertEq(Rate.one(),callEffectCounterAttackBeanSufferingDamageDirectMoveGet(dispMoveEffCounterAttack()));
    }
    @Test
    public void getReasonsProtect1() {
        assertSizeEq(1,callEffectCounterAttackBeanReasonsProtectGet(dispMoveEffCounterAttack()));
    }
    @Test
    public void getReasonsProtect2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,elt(callEffectCounterAttackBeanReasonsProtectGet(dispMoveEffCounterAttack()),0));
    }
    @Test
    public void getReasonsCounter1() {
        assertSizeEq(1,callEffectCounterAttackBeanReasonsCounterGet(dispMoveEffCounterAttack()));
    }
    @Test
    public void getReasonsCounter2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,elt(callEffectCounterAttackBeanReasonsCounterGet(dispMoveEffCounterAttack()),0));
    }
    @Test
    public void getMapVarsFailCounter1() {
        assertSizeEq(1,callEffectCounterAttackBeanGetMapVarsFailCounter(dispMoveEffCounterAttack()));
    }
    @Test
    public void getMapVarsFailCounter2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,first(elt(callEffectCounterAttackBeanGetMapVarsFailCounter(dispMoveEffCounterAttack()),0)));
    }
    @Test
    public void getMapVarsFailCounter3() {
        assertEq(TIME,second(elt(callEffectCounterAttackBeanGetMapVarsFailCounter(dispMoveEffCounterAttack()),0)));
    }
    @Test
    public void getSufferingDamageTypes1() {
        assertSizeEq(1,callEffectCounterAttackBeanSufferingDamageTypesGet(dispMoveEffCounterAttack()));
    }
    @Test
    public void getSufferingDamageTypes2() {
        assertEq(T_TYPE1,first(elt(callEffectCounterAttackBeanSufferingDamageTypesGet(dispMoveEffCounterAttack()),0)));
    }
    @Test
    public void getSufferingDamageTypes3() {
        assertEq(Rate.one(),second(elt(callEffectCounterAttackBeanSufferingDamageTypesGet(dispMoveEffCounterAttack()),0)));
    }
    @Test
    public void getTrSufferingDamageTypes() {
        assertEq(T_TYPE1_TR,callEffectCounterAttackBeanGetTrSufferingDamageTypes(dispMoveEffCounterAttack(),0));
    }
    @Test
    public void getDroppedStatDirectMove1() {
        assertSizeEq(1,callEffectCounterAttackBeanDroppedStatDirectMoveGet(dispMoveEffCounterAttack()));
    }
    @Test
    public void getDroppedStatDirectMove2() {
        assertEq(1,second(elt(callEffectCounterAttackBeanDroppedStatDirectMoveGet(dispMoveEffCounterAttack()),0)));
    }
    @Test
    public void getTrDroppedStatDirectMove() {
        assertEq(ST_SPEED_TR,callEffectCounterAttackBeanGetTrDroppedStatDirectMove(dispMoveEffCounterAttack(),0));
    }
}
