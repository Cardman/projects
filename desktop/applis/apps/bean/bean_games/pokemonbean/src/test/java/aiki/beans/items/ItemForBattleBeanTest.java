package aiki.beans.items;

import code.maths.LgInt;
import code.maths.Rate;
import code.util.StringList;
import org.junit.Test;

public final class ItemForBattleBeanTest extends InitDbItemsItemForBattle {
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
    public void evtRate1() {
        assertEq(Rate.zero(),callEffectWhileSendingBeanEvtRateGet(healSimpleNoStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one(),false,true)));
    }
    @Test
    public void evtRate2() {
        assertEq(Rate.one(),callEffectWhileSendingBeanEvtRateGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())));
    }
}
