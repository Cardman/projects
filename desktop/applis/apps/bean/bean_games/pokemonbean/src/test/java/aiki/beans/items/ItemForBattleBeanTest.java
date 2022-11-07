package aiki.beans.items;

import aiki.beans.effects.EffectWhileSendingBean;
import aiki.beans.moves.AikiBeansMovesStd;
import aiki.game.fight.Fight;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.StringList;
import org.junit.Test;

public final class ItemForBattleBeanTest extends InitDbItemsItemForBattle {
    @Test
    public void patch() {
        assertEq(Rate.zero(), EffectWhileSendingBean.patch(null).getMultWeight());
    }
    @Test
    public void bonusEffect1() {
        assertEq(2,ItemForBattleBean.bonusEffect(feedDbMove().getData(),M_DAM));
    }
    @Test
    public void initTypesPkAbilities1() {
        StringList ls_ = ItemForBattleBean.initTypesPkAbilities(feedDbAbility(false).getData());
        assertEq(0,ls_.size());
    }
    @Test
    public void initTypesPkAbilities2() {
        StringList ls_ = ItemForBattleBean.initTypesPkAbilities(feedDbAbility(true).getData());
        assertEq(1,ls_.size());
        assertEq(A_ABILITY,ls_.get(0));
    }
    @Test
    public void determinated1() {
        assertFalse(callItemForBattleBeanDeterminated(true,true,true,true,true,true, LgInt.one(), LgInt.one()));
    }
    @Test
    public void determinated2() {
        assertTrue(callItemForBattleBeanDeterminated(true,true,true,true,true,true, LgInt.one(), LgInt.zero()));
    }
    @Test
    public void getSending1() {
        assertFalse(callItemForBattleBeanSendingGet(true,true,true,true,true,true, LgInt.one(), LgInt.one()));
    }
    @Test
    public void getSending2() {
        assertTrue(callItemForBattleBeanSendingGetStat(true,true,true,true,true,true, LgInt.one(), LgInt.zero()));
    }
    @Test
    public void getSending3() {
        assertTrue(callItemForBattleBeanSendingGetNoStat(true,true,true,true,true,true, LgInt.one(), LgInt.zero()));
    }
    @Test
    public void getEndRound1() {
        assertFalse(callItemForBattleBeanEndRoundGetNo(true,true,true,true,true,true, LgInt.one(), LgInt.one()));
    }
    @Test
    public void getEndRound2() {
        assertTrue(callItemForBattleBeanEndRoundGet(true,true,true,true,true,true, LgInt.one(), LgInt.zero()));
    }
    @Test
    public void roundRank1() {
        assertEq(0,callItemForBattleBeanEndRoundRankGetNo(true,true,true,true,true,true, LgInt.one(), LgInt.one()));
    }
    @Test
    public void roundRank2() {
        assertEq(1,callItemForBattleBeanEndRoundRankGet(true,true,true,true,true,true, LgInt.one(), LgInt.zero()));
    }
    @Test
    public void getCancelImmuType1() {
        assertFalse(callItemForBattleBeanCancelImmuTypeGet(true,true,true,true,false,true, LgInt.one(), LgInt.one()));
    }
    @Test
    public void getCancelImmuType2() {
        assertTrue(callItemForBattleBeanCancelImmuTypeGet(true,true,true,true,true,true, LgInt.one(), LgInt.one()));
    }
    @Test
    public void getAgainstEvo1() {
        assertFalse(callItemForBattleBeanAgainstEvoGet(false,true,true,true,true,true, LgInt.one(), LgInt.one()));
    }
    @Test
    public void getAgainstEvo2() {
        assertTrue(callItemForBattleBeanAgainstEvoGet(true,true,true,true,true,true, LgInt.one(), LgInt.one()));
    }
    @Test
    public void getAttackLast1() {
        assertFalse(callItemForBattleBeanAttackLastGet(true,false,true,true,true,true, LgInt.one(), LgInt.one()));
    }
    @Test
    public void getAttackLast2() {
        assertTrue(callItemForBattleBeanAttackLastGet(true,true,true,true,true,true, LgInt.one(), LgInt.one()));
    }
    @Test
    public void getBoostExp1() {
        assertFalse(callItemForBattleBeanBoostExpGet(true,true,true,false,true,true, LgInt.one(), LgInt.one()));
    }
    @Test
    public void getBoostExp2() {
        assertTrue(callItemForBattleBeanBoostExpGet(true,true,true,true,true,true, LgInt.one(), LgInt.one()));
    }
    @Test
    public void getImmuLowStatis1() {
        assertFalse(callItemForBattleBeanImmuLowStatisGet(true,true,true,true,true,false, LgInt.one(), LgInt.one()));
    }
    @Test
    public void getImmuLowStatis2() {
        assertTrue(callItemForBattleBeanImmuLowStatisGet(true,true,true,true,true,true, LgInt.one(), LgInt.one()));
    }
    @Test
    public void getAttacksSoon1() {
        assertFalse(callItemForBattleBeanAttacksSoonGet(true,true,false,true,true,true, LgInt.one(), LgInt.one()));
    }
    @Test
    public void getAttacksSoon2() {
        assertTrue(callItemForBattleBeanAttacksSoonGet(true,true,true,true,true,true, LgInt.one(), LgInt.one()));
    }
    @Test
    public void rateForAttackFirst() {
        assertEq(Rate.newRate("1/4"),callItemForBattleBeanRateForAttackFirst(true,true,true,true,true,true, LgInt.one(), LgInt.newLgInt("3")));
    }
    @Test
    public void getDisableWeather1() {
        assertFalse(callEffectWhileSendingBeanDisableWeatherGet(healSimpleNoStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one(),true,false)));
    }
    @Test
    public void getDisableWeather2() {
        assertTrue(callEffectWhileSendingBeanDisableWeatherGet(healSimpleNoStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one(),true,true)));
    }
    @Test
    public void getCopyingAbility1() {
        assertFalse(callEffectWhileSendingBeanCopyingAbilityGet(healSimpleNoStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one(),false,true)));
    }
    @Test
    public void getCopyingAbility2() {
        assertTrue(callEffectWhileSendingBeanCopyingAbilityGet(healSimpleNoStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one(),true,true)));
    }
    @Test
    public void getStatistic1() {
        assertFalse(callEffectWhileSendingBeanStatisticGet(healSimpleNoStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one(),false,true)));
    }
    @Test
    public void getStatistic2() {
        assertTrue(callEffectWhileSendingBeanStatisticGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())));
    }
    @Test
    public void getMultWeight() {
        assertEq(Rate.one(),callEffectWhileSendingBeanMultWeightGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())));
    }
    @Test
    public void getEnabledWeather() {
        assertEq(M_DAM,callEffectWhileSendingBeanEnabledWeatherGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())));
    }
    @Test
    public void getTrWeather() {
        assertEq(M_DAM_TR,callEffectWhileSendingBeanGetTrWeather(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())));
    }
    @Test
    public void clickWeather1() {
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_DATA_HTML,callEffectWhileSendingBeanClickWeather(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())));
    }
    @Test
    public void clickWeather2() {
        assertEq(M_DAM,callEffectWhileSendingBeanClickWeatherId(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())));
    }
    @Test
    public void getMapVarsFail1() {
        assertSizeEq(1,callEffectWhileSendingBeanMapVarsFailGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())));
    }
    @Test
    public void getMapVarsFail2() {
        assertEq(Fight.TEMPS_TOUR,first(elt(callEffectWhileSendingBeanMapVarsFailGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())),0)));
    }
    @Test
    public void getMapVarsFail3() {
        assertEq(TIME,second(elt(callEffectWhileSendingBeanMapVarsFailGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())),0)));
    }
    @Test
    public void getReasons1() {
        assertSizeEq(1,callEffectWhileSendingBeanReasonsGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())));
    }
    @Test
    public void getReasons2() {
        assertEq(Fight.TEMPS_TOUR,elt(callEffectWhileSendingBeanReasonsGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())),0));
    }
    @Test
    public void evtRate1() {
        assertEq(Rate.zero(),callEffectWhileSendingBeanEvtRateGet(healSimpleNoStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one(),false,true)));
    }
    @Test
    public void evtRate2() {
        assertEq(Rate.one(),callEffectWhileSendingBeanEvtRateGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())));
    }
    @Test
    public void isAlwaysEnabled1() {
        assertTrue(callEffectWhileSendingBeanIsAlwaysEnabled(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())));
    }
    @Test
    public void isAlwaysEnabled2() {
        assertFalse(callEffectWhileSendingBeanIsAlwaysEnabled(healSimpleNoStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one(),false,true)));
    }
    @Test
    public void notEmptyVarBoost1() {
        assertTrue(callEffectWhileSendingBeanNotEmptyVarBoost(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())));
    }
    @Test
    public void notEmptyVarBoost2() {
        assertFalse(callEffectWhileSendingBeanNotEmptyVarBoost(healSimpleNoStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one(),false,true)));
    }
    @Test
    public void randomStatis1() {
        assertTrue(callEffectWhileSendingBeanRandomStatis(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())));
    }
    @Test
    public void randomStatis2() {
        assertFalse(callEffectWhileSendingBeanRandomStatis(healSimpleNoStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one(),false,true)));
    }
    @Test
    public void evtPercent() {
        assertEq("1.0E2",callEffectWhileSendingBeanEvtRatePerCentGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())));
    }
    @Test
    public void getDefaultBoost1() {
        assertEq(1,callEffectWhileSendingBeanDefaultBoostGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())));
    }
    @Test
    public void getDefaultBoost2() {
        assertEq(0,callEffectWhileSendingBeanDefaultBoostGet(healSimpleNoStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())));
    }
    @Test
    public void getSwapBoostStatis1() {
        assertSizeEq(1,callEffectWhileSendingBeanSwapBoostStatisGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())));
    }
    @Test
    public void getSwapBoostStatis2() {
        assertEq(ST_SPEED_TR,elt(callEffectWhileSendingBeanSwapBoostStatisGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())),0));
    }
    @Test
    public void getSwapBoostStatis3() {
        assertSizeEq(0,callEffectWhileSendingBeanSwapBoostStatisGet(healSimpleNoStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())));
    }
    @Test
    public void getCancelLowStat1() {
        assertSizeEq(1,callEffectWhileSendingBeanCancelLowStatGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())));
    }
    @Test
    public void getCancelLowStat2() {
        assertEq(ST_SPEED_TR,elt(callEffectWhileSendingBeanCancelLowStatGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())),0));
    }
    @Test
    public void getCancelLowStat3() {
        assertSizeEq(0,callEffectWhileSendingBeanCancelLowStatGet(healSimpleNoStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())));
    }
    @Test
    public void getCancelChgtStat1() {
        assertSizeEq(1,callEffectWhileSendingBeanCancelChgtStatGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())));
    }
    @Test
    public void getCancelChgtStat2() {
        assertEq(ST_SPEED_TR,elt(callEffectWhileSendingBeanCancelChgtStatGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())),0));
    }
    @Test
    public void getCancelChgtStat3() {
        assertSizeEq(0,callEffectWhileSendingBeanCancelChgtStatGet(healSimpleNoStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())));
    }
    @Test
    public void getCopyBoost1() {
        assertSizeEq(1,callEffectWhileSendingBeanCopyBoostGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())));
    }
    @Test
    public void getCopyBoost2() {
        assertEq(ST_SPEED_TR,elt(callEffectWhileSendingBeanCopyBoostGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())),0));
    }
    @Test
    public void getCopyBoost3() {
        assertSizeEq(0,callEffectWhileSendingBeanCopyBoostGet(healSimpleNoStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())));
    }
    @Test
    public void getStatisVarRank1() {
        assertSizeEq(1,callEffectWhileSendingBeanStatisVarRankGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())));
    }
    @Test
    public void getStatisVarRank2() {
        assertEq(ST_SPEED_TR,first(elt(callEffectWhileSendingBeanStatisVarRankGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())),0)));
    }
    @Test
    public void getStatisVarRank3() {
        assertEq(1,second(elt(callEffectWhileSendingBeanStatisVarRankGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())),0)));
    }
    @Test
    public void getStatisVarRank4() {
        assertSizeEq(0,callEffectWhileSendingBeanStatisVarRankGet(healSimpleNoStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())));
    }
    @Test
    public void getMapVarsStatistics1() {
        assertSizeEq(1,callEffectWhileSendingBeanMapVarsStatisticsGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())));
    }
    @Test
    public void getMapVarsStatistics2() {
        assertEq(Fight.TEMPS_TOUR,first(elt(callEffectWhileSendingBeanMapVarsStatisticsGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())),0)));
    }
    @Test
    public void getMapVarsStatistics3() {
        assertEq(TIME,second(elt(callEffectWhileSendingBeanMapVarsStatisticsGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())),0)));
    }
    @Test
    public void getMapVarsStatistics4() {
        assertSizeEq(1,callEffectWhileSendingBeanMapVarsStatisticsGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())));
    }
    @Test
    public void getMapVarsStatistics5() {
        assertEq(Fight.TEMPS_TOUR,first(elt(callEffectWhileSendingBeanMapVarsStatisticsGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())),0)));
    }
    @Test
    public void getMapVarsStatistics6() {
        assertEq(TIME,second(elt(callEffectWhileSendingBeanMapVarsStatisticsGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())),0)));
    }
    @Test
    public void getMapVarsStatistics7() {
        assertSizeEq(0,callEffectWhileSendingBeanMapVarsStatisticsGet(healSimpleNoStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())));
    }
    @Test
    public void getFail() {
        assertEq(Fight.TEMPS_TOUR,callEffectWhileSendingBeanGetFail(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one()),0));
    }
    @Test
    public void getSwapFail() {
        assertEq(Fight.TEMPS_TOUR,callEffectWhileSendingBeanGetSwapFail(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one()),0));
    }
    @Test
    public void getRate() {
        assertEq(Rate.one(),callEffectWhileSendingBeanGetRate(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one()),0));
    }
}
