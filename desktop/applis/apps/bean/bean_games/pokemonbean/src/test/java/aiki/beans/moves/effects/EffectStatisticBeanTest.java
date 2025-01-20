package aiki.beans.moves.effects;

import aiki.db.MessagesDataBaseConstants;
import aiki.fight.enums.Statistic;
import code.maths.LgInt;
import code.maths.Rate;
import org.junit.Test;

public final class EffectStatisticBeanTest extends InitDbMoveEffectStatistic {
    @Test
    public void evt() {
        assertEq(Rate.one(),callEffectStatisticBeanEvtRateGet(dispMoveEffStatis(feedDbMoveEffDataDamComp(eff(Rate.one(), VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR)),0)));
    }
    @Test
    public void isAlwaysEnabled1() {
        assertTrue(callEffectStatisticBeanIsAlwaysEnabled(dispMoveEffStatis(feedDbMoveEffDataDamComp(eff(Rate.one(), VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR)),0)));
    }
    @Test
    public void isAlwaysEnabled2() {
        assertFalse(callEffectStatisticBeanIsAlwaysEnabled(dispMoveEffStatis(feedDbMoveEffDataDamComp(eff(Rate.newRate("1/2"), VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR)),0)));
    }
    @Test
    public void notEmptyVarBoost1() {
        assertTrue(callEffectStatisticBeanNotEmptyVarBoost(dispMoveEffStatis(feedDbMoveEffDataDamComp(withStatisVarRank(eff(Rate.one(), VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR), Statistic.SPEED,1L)),0)));
    }
    @Test
    public void notEmptyVarBoost2() {
        assertFalse(callEffectStatisticBeanNotEmptyVarBoost(dispMoveEffStatis(feedDbMoveEffDataDamComp(eff(Rate.one(), VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR)),0)));
    }
    @Test
    public void randomStatis1() {
        assertTrue(callEffectStatisticBeanRandomStatis(dispMoveEffStatis(feedDbMoveEffDataDamComp(withLawBoost(eff(Rate.one(), VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR),Statistic.SPEED, LgInt.one())),0)));
    }
    @Test
    public void randomStatis2() {
        assertFalse(callEffectStatisticBeanRandomStatis(dispMoveEffStatis(feedDbMoveEffDataDamComp(eff(Rate.one(), VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR)),0)));
    }
    @Test
    public void evtPercent() {
        assertEq("1.0"+Rate.POWER+"2",callEffectStatisticBeanEvtRatePerCentGet(dispMoveEffStatis(feedDbMoveEffDataDamComp(eff(Rate.one(), VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR)),0)));
    }
    @Test
    public void getDefaultBoost() {
        assertEq(1,callEffectStatisticBeanDefaultBoostGet(dispMoveEffStatis(feedDbMoveEffDataDamComp(eff(Rate.one(), VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR)),0)));
    }
    @Test
    public void getSwapBoostStatis1() {
        assertSizeEq(1,callEffectStatisticBeanSwapBoostStatisGet(dispMoveEffStatis(feedDbMoveEffDataDamComp(eff(Rate.one(), VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR)),0)));
    }
    @Test
    public void getSwapBoostStatis2() {
        assertEq(ST_SPEED_TR,elt(callEffectStatisticBeanSwapBoostStatisGet(dispMoveEffStatis(feedDbMoveEffDataDamComp(eff(Rate.one(), VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR)),0)),0));
    }
    @Test
    public void getCancelLowStat1() {
        assertSizeEq(1,callEffectStatisticBeanCancelLowStatGet(dispMoveEffStatis(feedDbMoveEffDataDamComp(eff(Rate.one(), VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR)),0)));
    }
    @Test
    public void getCancelLowStat2() {
        assertEq(ST_SPEED_TR,elt(callEffectStatisticBeanCancelLowStatGet(dispMoveEffStatis(feedDbMoveEffDataDamComp(eff(Rate.one(), VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR)),0)),0));
    }
    @Test
    public void getCancelChgtStat1() {
        assertSizeEq(1,callEffectStatisticBeanCancelChgtStatGet(dispMoveEffStatis(feedDbMoveEffDataDamComp(eff(Rate.one(), VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR)),0)));
    }
    @Test
    public void getCancelChgtStat2() {
        assertEq(ST_SPEED_TR,elt(callEffectStatisticBeanCancelChgtStatGet(dispMoveEffStatis(feedDbMoveEffDataDamComp(eff(Rate.one(), VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR)),0)),0));
    }
    @Test
    public void getCopyBoost1() {
        assertSizeEq(1,callEffectStatisticBeanCopyBoostGet(dispMoveEffStatis(feedDbMoveEffDataDamComp(eff(Rate.one(), VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR)),0)));
    }
    @Test
    public void getCopyBoost2() {
        assertEq(ST_SPEED_TR,elt(callEffectStatisticBeanCopyBoostGet(dispMoveEffStatis(feedDbMoveEffDataDamComp(eff(Rate.one(), VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR)),0)),0));
    }
    @Test
    public void getStatisVarRank1() {
        assertSizeEq(2,callEffectStatisticBeanStatisVarRankGet(dispMoveEffStatis(feedDbMoveEffDataDamComp(withStatisVarRank(withStatisVarRank(eff(Rate.one(), VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR),Statistic.SPEED,1L),Statistic.ACCURACY,2L)),0)));
    }
    @Test
    public void getStatisVarRank2() {
        assertEq(ST_ACC_TR,first(elt(callEffectStatisticBeanStatisVarRankGet(dispMoveEffStatis(feedDbMoveEffDataDamComp(withStatisVarRank(withStatisVarRank(eff(Rate.one(), VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR),Statistic.SPEED,1L),Statistic.ACCURACY,2L)),0)),0)));
    }
    @Test
    public void getStatisVarRank3() {
        assertEq(2,second(elt(callEffectStatisticBeanStatisVarRankGet(dispMoveEffStatis(feedDbMoveEffDataDamComp(withStatisVarRank(withStatisVarRank(eff(Rate.one(), VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR),Statistic.SPEED,1L),Statistic.ACCURACY,2L)),0)),0)));
    }
    @Test
    public void getStatisVarRank4() {
        assertEq(ST_SPEED_TR,first(elt(callEffectStatisticBeanStatisVarRankGet(dispMoveEffStatis(feedDbMoveEffDataDamComp(withStatisVarRank(withStatisVarRank(eff(Rate.one(), VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR),Statistic.SPEED,1L),Statistic.ACCURACY,2L)),0)),1)));
    }
    @Test
    public void getStatisVarRank5() {
        assertEq(1,second(elt(callEffectStatisticBeanStatisVarRankGet(dispMoveEffStatis(feedDbMoveEffDataDamComp(withStatisVarRank(withStatisVarRank(eff(Rate.one(), VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR),Statistic.SPEED,1L),Statistic.ACCURACY,2L)),0)),1)));
    }
    @Test
    public void getMapVarsStatistics1() {
        assertSizeEq(1,callEffectStatisticBeanMapVarsStatisticsGet(dispMoveEffStatis(feedDbMoveEffDataDamComp(withStatisVarRank(withStatisVarRank(eff(Rate.one(), VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, ""),Statistic.SPEED,1L),Statistic.ACCURACY,2L)),0)));
    }
    @Test
    public void getMapVarsStatistics2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,first(elt(callEffectStatisticBeanMapVarsStatisticsGet(dispMoveEffStatis(feedDbMoveEffDataDamComp(withStatisVarRank(withStatisVarRank(eff(Rate.one(), VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, ""),Statistic.SPEED,1L),Statistic.ACCURACY,2L)),0)),0)));
    }
    @Test
    public void getMapVarsStatistics3() {
        assertEq(TIME,second(elt(callEffectStatisticBeanMapVarsStatisticsGet(dispMoveEffStatis(feedDbMoveEffDataDamComp(withStatisVarRank(withStatisVarRank(eff(Rate.one(), VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, ""),Statistic.SPEED,1L),Statistic.ACCURACY,2L)),0)),0)));
    }
    @Test
    public void getMapVarsStatistics4() {
        assertSizeEq(1,callEffectStatisticBeanMapVarsStatisticsGet(dispMoveEffStatis(feedDbMoveEffDataDamComp(withLawBoost(withLawBoost(eff(Rate.one(), "", VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR),Statistic.SPEED,LgInt.one()),Statistic.ACCURACY,LgInt.newLgInt("3"))),0)));
    }
    @Test
    public void getMapVarsStatistics5() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,first(elt(callEffectStatisticBeanMapVarsStatisticsGet(dispMoveEffStatis(feedDbMoveEffDataDamComp(withLawBoost(withLawBoost(eff(Rate.one(), "", VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR),Statistic.SPEED,LgInt.one()),Statistic.ACCURACY,LgInt.newLgInt("3"))),0)),0)));
    }
    @Test
    public void getMapVarsStatistics6() {
        assertEq(TIME,second(elt(callEffectStatisticBeanMapVarsStatisticsGet(dispMoveEffStatis(feedDbMoveEffDataDamComp(withLawBoost(withLawBoost(eff(Rate.one(), "", VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR),Statistic.SPEED,LgInt.one()),Statistic.ACCURACY,LgInt.newLgInt("3"))),0)),0)));
    }
    @Test
    public void getFail1() {
        assertEq("",callEffectStatisticBeanGetFail(dispMoveEffStatis(feedDbMoveEffDataDamComp(withStatisVarRank(withStatisVarRank(eff(Rate.one(), VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, ""),Statistic.SPEED,1L),Statistic.ACCURACY,2L)),0),0));
    }
    @Test
    public void getFail2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,callEffectStatisticBeanGetFail(dispMoveEffStatis(feedDbMoveEffDataDamComp(withStatisVarRank(withStatisVarRank(eff(Rate.one(), VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR, ""),Statistic.SPEED,1L),Statistic.ACCURACY,2L)),0),1));
    }
    @Test
    public void getSwapFail() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,callEffectStatisticBeanGetSwapFail(dispMoveEffStatis(feedDbMoveEffDataDamComp(withStatisVarRank(withStatisVarRank(eff(Rate.one(), "", VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR),Statistic.SPEED,1L),Statistic.ACCURACY,2L)),0),0));
    }
    @Test
    public void getRate() {
        assertEq(Rate.one(),callEffectStatisticBeanGetRate(dispMoveEffStatis(feedDbMoveEffDataDamComp(withLawBoost(eff(Rate.one(), "", VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR),Statistic.SPEED,LgInt.one())),0),0));
    }
}
