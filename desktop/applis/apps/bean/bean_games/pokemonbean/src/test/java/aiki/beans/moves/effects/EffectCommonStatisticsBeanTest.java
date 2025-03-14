package aiki.beans.moves.effects;

import aiki.db.MessagesDataBaseConstants;
import org.junit.Test;

public final class EffectCommonStatisticsBeanTest extends InitDbMoveEffectOther {
    @Test
    public void getCommonValue1() {
        assertSizeEq(1,callEffectCommonStatisticsBeanCommonValueGet(dispMoveEffCommonStatistics()));
    }
    @Test
    public void getCommonValue2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,secondTkStr(eltTkStr(callEffectCommonStatisticsBeanCommonValueGet(dispMoveEffCommonStatistics()),0)));
    }
    @Test
    public void getTrStatistic() {
        assertEq(ST_SPEED_TR,callEffectCommonStatisticsBeanGetTrStatistic(dispMoveEffCommonStatistics(),0));
    }
    @Test
    public void getMapVarsCommonStatistics1() {
        assertSizeEq(1,callEffectCommonStatisticsBeanMapVarsCommonStatisticsGet(dispMoveEffCommonStatistics()));
    }
    @Test
    public void getMapVarsCommonStatistics2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,firstStrStr(eltStrStr(callEffectCommonStatisticsBeanMapVarsCommonStatisticsGet(dispMoveEffCommonStatistics()),0)));
    }
    @Test
    public void getMapVarsCommonStatistics3() {
        assertEq(TIME,secondStrStr(eltStrStr(callEffectCommonStatisticsBeanMapVarsCommonStatisticsGet(dispMoveEffCommonStatistics()),0)));
    }
}
