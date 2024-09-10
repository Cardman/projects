package aiki.beans.items;

import aiki.beans.effects.EffectWhileSendingBean;
import aiki.db.MessagesDataBaseConstants;
import code.maths.LgInt;
import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
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
    public void getProtectAgainstKo() {
        assertEq(Rate.one(),callItemForBattleBeanProtectAgainstKoGet());
    }
    @Test
    public void getProtectAgainstKoIfFullHp() {
        assertEq(Rate.one(),callItemForBattleBeanProtectAgainstKoIfFullHpGet());
    }
    @Test
    public void getDrainedHpByDamageRate() {
        assertEq(Rate.one(),callItemForBattleBeanDrainedHpByDamageRateGet());
    }
    @Test
    public void getMultTrappingDamage() {
        assertEq(Rate.one(),callItemForBattleBeanMultTrappingDamageGet());
    }
    @Test
    public void getMultWinningHappiness() {
        assertEq(Rate.one(),callItemForBattleBeanMultWinningHappinessGet());
    }
    @Test
    public void getMultWinningEv() {
        assertEq(Rate.one(),callItemForBattleBeanMultWinningEvGet());
    }
    @Test
    public void getMultWinningExp() {
        assertEq(Rate.one(),callItemForBattleBeanMultWinningExpGet());
    }
    @Test
    public void getMultDrainedHp() {
        assertEq(Rate.one(),callItemForBattleBeanMultDrainedHpGet());
    }
    @Test
    public void getDamageRecoil() {
        assertEq(Rate.one(),callItemForBattleBeanDamageRecoilGet());
    }
    @Test
    public void getMultPower() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,callItemForBattleBeanMultPowerGet());
    }
    @Test
    public void getMultDamage() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,callItemForBattleBeanMultDamageGet());
    }
    @Test
    public void getTypesPk1() {
        assertSizeEq(1,callItemForBattleBeanTypesPkGet());
    }
    @Test
    public void getTypesPk2() {
        assertEq(T_TYPE1,elt(callItemForBattleBeanTypesPkGet(),0));
    }
    @Test
    public void getTrTypesPk() {
        assertEq(T_TYPE1_TR,callItemForBattleBeanGetTrTypesPk());
    }
    @Test
    public void getImmuTypes1() {
        assertSizeEq(1,callItemForBattleBeanImmuTypesGet());
    }
    @Test
    public void getImmuTypes2() {
        assertEq(T_TYPE1,elt(callItemForBattleBeanImmuTypesGet(),0));
    }
    @Test
    public void getTrImmuTypes() {
        assertEq(T_TYPE1_TR,callItemForBattleBeanGetTrImmuTypes());
    }
    @Test
    public void getTypesPkAbilities1() {
        assertSizeEq(1,callItemForBattleBeanTypesPkAbilitiesGet());
    }
    @Test
    public void getTypesPkAbilities2() {
        assertEq(A_ABILITY,elt(callItemForBattleBeanTypesPkAbilitiesGet(),0));
    }
    @Test
    public void getTrTypesPkAbility() {
        assertEq(A_ABILITY_TR,callItemForBattleBeanGetTrTypesPkAbility());
    }
    @Test
    public void clickTypesPkAbility1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ABILITY_DATA_HTML,callItemForBattleBeanClickTypesPkAbility());
    }
    @Test
    public void clickTypesPkAbility2() {
        assertEq(A_ABILITY,callItemForBattleBeanClickTypesPkAbilityId());
    }
    @Test
    public void getImmuStatus1() {
        assertSizeEq(1,callItemForBattleBeanImmuStatusGet());
    }
    @Test
    public void getImmuStatus2() {
        assertEq(S_STA_SIM,elt(callItemForBattleBeanImmuStatusGet(),0));
    }
    @Test
    public void getTrImmuStatus() {
        assertEq(S_STA_SIM_TR,callItemForBattleBeanGetTrImmuStatus());
    }
    @Test
    public void clickImmuStatus1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML,callItemForBattleBeanClickImmuStatus());
    }
    @Test
    public void clickImmuStatus2() {
        assertEq(S_STA_SIM,callItemForBattleBeanClickImmuStatusId());
    }
    @Test
    public void getSynchroStatus1() {
        assertSizeEq(1,callItemForBattleBeanSynchroStatusGet());
    }
    @Test
    public void getSynchroStatus2() {
        assertEq(S_STA_SIM,elt(callItemForBattleBeanSynchroStatusGet(),0));
    }
    @Test
    public void getTrSynchroStatus() {
        assertEq(S_STA_SIM_TR,callItemForBattleBeanGetTrSynchroStatus());
    }
    @Test
    public void clickSynchroStatus1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML,callItemForBattleBeanClickSynchroStatus());
    }
    @Test
    public void clickSynchroStatus2() {
        assertEq(S_STA_SIM,callItemForBattleBeanClickSynchroStatusId());
    }
    @Test
    public void getImmuMoves1() {
        assertSizeEq(1,callItemForBattleBeanImmuMovesGet());
    }
    @Test
    public void getImmuMoves2() {
        assertEq(M_DAM,elt(callItemForBattleBeanImmuMovesGet(),0));
    }
    @Test
    public void getTrImmuMove() {
        assertEq(M_DAM_TR,callItemForBattleBeanGetTrImmuMove());
    }
    @Test
    public void clickImmuMove1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callItemForBattleBeanClickImmuMove());
    }
    @Test
    public void clickImmuMove2() {
        assertEq(M_DAM,callItemForBattleBeanClickImmuMoveId());
    }
    @Test
    public void getImmuWeather1() {
        assertSizeEq(1,callItemForBattleBeanImmuWeatherGet());
    }
    @Test
    public void getImmuWeather2() {
        assertEq(M_DAM,elt(callItemForBattleBeanImmuWeatherGet(),0));
    }
    @Test
    public void getTrWeatherInd() {
        assertEq(M_DAM_TR,callItemForBattleBeanGetTrWeather());
    }
    @Test
    public void clickWeatherInd1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callItemForBattleBeanClickWeather());
    }
    @Test
    public void clickWeatherInd2() {
        assertEq(M_DAM,callItemForBattleBeanClickWeatherId());
    }
    @Test
    public void getMultStatRank1() {
        assertSizeEq(1,callItemForBattleBeanMultStatRankGet());
    }
    @Test
    public void getMultStatRank2() {
        assertEq(1,second(elt(callItemForBattleBeanMultStatRankGet(),0)));
    }
    @Test
    public void getTrMultStatRank() {
        assertEq(ST_SPEED_TR,callItemForBattleBeanGetTrMultStatRank());
    }
    @Test
    public void getBoostStatisSuperEff1() {
        assertSizeEq(1,callItemForBattleBeanBoostStatisSuperEffGet());
    }
    @Test
    public void getBoostStatisSuperEff2() {
        assertEq(1,second(elt(callItemForBattleBeanBoostStatisSuperEffGet(),0)));
    }
    @Test
    public void getTrMultStatisSuperEff() {
        assertEq(ST_SPEED_TR,callItemForBattleBeanGetTrMultStatisSuperEff());
    }
    @Test
    public void getWinEvFight1() {
        assertSizeEq(1,callItemForBattleBeanWinEvFightGet());
    }
    @Test
    public void getWinEvFight2() {
        assertEq(1,second(elt(callItemForBattleBeanWinEvFightGet(),0)));
    }
    @Test
    public void getTrWinEvFight() {
        assertEq(ST_SPEED_TR,callItemForBattleBeanGetTrWinEvFight());
    }
    @Test
    public void getMultStat1() {
        assertSizeEq(1,callItemForBattleBeanMultStatGet());
    }
    @Test
    public void getMultStat2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,second(elt(callItemForBattleBeanMultStatGet(),0)));
    }
    @Test
    public void getTrMultStat() {
        assertEq(ST_SPEED_TR,callItemForBattleBeanGetTrMultStat());
    }
    @Test
    public void getMapVars1() {
        assertSizeEq(1,callItemForBattleBeanMapVarsGet());
    }
    @Test
    public void getMapVars2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,first(elt(callItemForBattleBeanMapVarsGet(),0)));
    }
    @Test
    public void getMapVars3() {
        assertEq(TIME,second(elt(callItemForBattleBeanMapVarsGet(),0)));
    }
    @Test
    public void getIncreasingMaxNbRoundTrap1() {
        assertSizeEq(1,callItemForBattleBeanIncreasingMaxNbRoundTrapGet());
    }
    @Test
    public void getIncreasingMaxNbRoundTrap2() {
        assertEq(M_DAM,first(elt(callItemForBattleBeanIncreasingMaxNbRoundTrapGet(),0)));
    }
    @Test
    public void getIncreasingMaxNbRoundTrap3() {
        assertEq(3,second(elt(callItemForBattleBeanIncreasingMaxNbRoundTrapGet(),0)));
    }
    @Test
    public void getTrTrapMove() {
        assertEq(M_DAM_TR,callItemForBattleBeanGetTrTrapMove());
    }
    @Test
    public void clickTrapMove1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callItemForBattleBeanClickTrapMove());
    }
    @Test
    public void clickTrapMove2() {
        assertEq(M_DAM,callItemForBattleBeanClickTrapMoveId());
    }
    @Test
    public void getIncreasingMaxNbRoundTeamMove1() {
        assertSizeEq(1,callItemForBattleBeanIncreasingMaxNbRoundTeamMoveGet());
    }
    @Test
    public void getIncreasingMaxNbRoundTeamMove2() {
        assertEq(M_DAM,first(elt(callItemForBattleBeanIncreasingMaxNbRoundTeamMoveGet(),0)));
    }
    @Test
    public void getIncreasingMaxNbRoundTeamMove3() {
        assertEq(3,second(elt(callItemForBattleBeanIncreasingMaxNbRoundTeamMoveGet(),0)));
    }
    @Test
    public void getTrTeamMove() {
        assertEq(M_DAM_TR,callItemForBattleBeanGetTrTeamMove());
    }
    @Test
    public void clickTeamMove1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callItemForBattleBeanClickTeamMove());
    }
    @Test
    public void clickTeamMove2() {
        assertEq(M_DAM,callItemForBattleBeanClickTeamMoveId());
    }
    @Test
    public void getIncreasingMaxNbRoundGlobalMove1() {
        assertSizeEq(1,callItemForBattleBeanIncreasingMaxNbRoundGlobalMoveGet());
    }
    @Test
    public void getIncreasingMaxNbRoundGlobalMove2() {
        assertEq(M_DAM,first(elt(callItemForBattleBeanIncreasingMaxNbRoundGlobalMoveGet(),0)));
    }
    @Test
    public void getIncreasingMaxNbRoundGlobalMove3() {
        assertEq(3,second(elt(callItemForBattleBeanIncreasingMaxNbRoundGlobalMoveGet(),0)));
    }
    @Test
    public void getTrGlobalMove() {
        assertEq(M_DAM_TR,callItemForBattleBeanGetTrGlobalMove());
    }
    @Test
    public void clickGlobalMove1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callItemForBattleBeanClickGlobalMove());
    }
    @Test
    public void clickGlobalMove2() {
        assertEq(M_DAM,callItemForBattleBeanClickGlobalMoveId());
    }
    @Test
    public void getFailStatus1() {
        assertSizeEq(1,callItemForBattleBeanFailStatusGet());
    }
    @Test
    public void getFailStatus2() {
        assertEq(S_STA_SIM,first(elt(callItemForBattleBeanFailStatusGet(),0)));
    }
    @Test
    public void getFailStatus3() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,second(elt(callItemForBattleBeanFailStatusGet(),0)));
    }
    @Test
    public void getTrFailStatus() {
        assertEq(S_STA_SIM_TR,callItemForBattleBeanGetTrFailStatus());
    }
    @Test
    public void clickFailStatus1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML,callItemForBattleBeanClickFailStatus());
    }
    @Test
    public void clickFailStatus2() {
        assertEq(S_STA_SIM,callItemForBattleBeanClickFailStatusId());
    }
    @Test
    public void getMultStatPokemonRank1() {
        assertSizeEq(1,callItemForBattleBeanMultStatPokemonRankGet());
    }
    @Test
    public void getMultStatPokemonRank2() {
        assertEq(1,second(elt(callItemForBattleBeanMultStatPokemonRankGet(),0)));
    }
    @Test
    public void getTrMultStatPkRank() {
        assertEq(ST_SPEED_TR,callItemForBattleBeanGetTrMultStatPkRank());
    }
    @Test
    public void getTrMultStatPk() {
        assertEq(P_POKEMON_TR,callItemForBattleBeanGetTrMultStatPk());
    }
    @Test
    public void clickMultStatPk1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_DATA_HTML,callItemForBattleBeanClickMultStatPk());
    }
    @Test
    public void clickMultStatPk2() {
        assertEq(P_POKEMON,callItemForBattleBeanClickMultStatPkId());
    }
    @Test
    public void getBoostStatisTypes1() {
        assertSizeEq(1,callItemForBattleBeanBoostStatisTypesGet());
    }
    @Test
    public void getBoostStatisTypes2() {
        assertEq(T_TYPE1,first(elt(callItemForBattleBeanBoostStatisTypesGet(),0)));
    }
    @Test
    public void getBoostStatisTypes3() {
        assertSizeEq(1,second(elt(callItemForBattleBeanBoostStatisTypesGet(),0)));
    }
    @Test
    public void getBoostStatisTypes4() {
        assertEq(1,second(elt(second(elt(callItemForBattleBeanBoostStatisTypesGet(),0)),0)));
    }
    @Test
    public void getTrMultStatisTypes() {
        assertEq(T_TYPE1_TR,callItemForBattleBeanGetTrMultStatisTypes());
    }
    @Test
    public void getTrMultStatisTypesStat() {
        assertEq(ST_SPEED_TR,callItemForBattleBeanGetTrMultStatisTypesStat());
    }
    @Test
    public void getMapVarsFailEndRound1() {
        assertSizeEq(1,callItemForBattleBeanMapVarsFailEndRoundGet());
    }
    @Test
    public void getMapVarsFailEndRound2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,first(elt(callItemForBattleBeanMapVarsFailEndRoundGet(),0)));
    }
    @Test
    public void getMapVarsFailEndRound3() {
        assertEq(TIME,second(elt(callItemForBattleBeanMapVarsFailEndRoundGet(),0)));
    }
    @Test
    public void getReasonsEndRound1() {
        assertSizeEq(1,callItemForBattleBeanReasonsEndRoundGet());
    }
    @Test
    public void getReasonsEndRound2() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,elt(callItemForBattleBeanReasonsEndRoundGet(),0));
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
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callEffectWhileSendingBeanClickWeather(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())));
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
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,first(elt(callEffectWhileSendingBeanMapVarsFailGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())),0)));
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
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,elt(callEffectWhileSendingBeanReasonsGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())),0));
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
        assertEq("1.0"+Rate.POWER+"2",callEffectWhileSendingBeanEvtRatePerCentGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())));
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
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,first(elt(callEffectWhileSendingBeanMapVarsStatisticsGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())),0)));
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
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,first(elt(callEffectWhileSendingBeanMapVarsStatisticsGet(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one())),0)));
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
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,callEffectWhileSendingBeanGetFail(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one()),0));
    }
    @Test
    public void getSwapFail() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,callEffectWhileSendingBeanGetSwapFail(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one()),0));
    }
    @Test
    public void getRate() {
        assertEq(Rate.one(),callEffectWhileSendingBeanGetRate(healSimpleStatSend(true,true,true,true,true,true, LgInt.one(), LgInt.one()),0));
    }
}
