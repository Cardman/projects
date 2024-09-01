package aiki.beans.moves.effects;

import aiki.db.MessagesDataBaseConstants;
import org.junit.Test;

public final class EffectEndRoundMoveBeanTest extends InitDbMoveEffectEndRound {
    @Test
    public void getEndRoundRank() {
        assertEq(1,callEffectEndRoundMoveBeanEndRoundRankGet(dispMoveEffEndRound()));
    }
    @Test
    public void getReasonsEndRound1() {
        assertSizeEq(1,callEffectEndRoundMoveBeanReasonsEndRoundGet(dispMoveEffEndRound()));
    }
    @Test
    public void getReasonsEndRound2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,elt(callEffectEndRoundMoveBeanReasonsEndRoundGet(dispMoveEffEndRound()),0));
    }
    @Test
    public void getMapVarsFailEndRound1() {
        assertSizeEq(1,callEffectEndRoundMoveBeanMapVarsFailEndRoundGet(dispMoveEffEndRound()));
    }
    @Test
    public void getMapVarsFailEndRound2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,first(elt(callEffectEndRoundMoveBeanMapVarsFailEndRoundGet(dispMoveEffEndRound()),0)));
    }
    @Test
    public void getMapVarsFailEndRound3() {
        assertEq(TIME,second(elt(callEffectEndRoundMoveBeanMapVarsFailEndRoundGet(dispMoveEffEndRound()),0)));
    }
}
