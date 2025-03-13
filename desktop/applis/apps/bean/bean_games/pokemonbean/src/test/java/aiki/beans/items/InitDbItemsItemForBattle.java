package aiki.beans.items;

import aiki.beans.PkData;
import aiki.beans.*;
import aiki.beans.effects.EffectWhileSendingBean;
import aiki.comparators.DictionaryComparator;
import aiki.db.DataBase;
import aiki.db.MessagesDataBaseConstants;
import aiki.facade.*;
import aiki.fight.abilities.AbilityData;
import aiki.fight.effects.EffectWhileSendingWithStatistic;
import aiki.fight.enums.Statistic;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.enums.*;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.maths.*;
import code.scripts.pages.aiki.*;
import code.sml.util.TranslationsFile;
import aiki.beans.abilities.*;
import code.util.*;

public abstract class InitDbItemsItemForBattle extends InitDbItem {

    public static boolean callItemForBattleBeanAgainstEvoGet(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff)).getInstance()).getAgainstEvo();
    }

    public static boolean callItemForBattleBeanAttackLastGet(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff)).getInstance()).getAttackLast();
    }

    public static boolean callItemForBattleBeanAttacksSoonGet(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff)).getInstance()).getAttacksSoon();
    }

    public static boolean callItemForBattleBeanBoostExpGet(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff)).getInstance()).getBoostExp();
    }

    public static DictionaryComparator<TranslatedKey,Long> callItemForBattleBeanBoostStatisSuperEffGet() {
        return (( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getBoostStatisSuperEff());
    }

    public static AbsMap<TranslatedKey, DictionaryComparator<TranslatedKey, Long>> callItemForBattleBeanBoostStatisTypesGet() {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getBoostStatisTypes();
    }

    public static boolean callItemForBattleBeanCancelImmuTypeGet(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff)).getInstance()).getCancelImmuType();
    }

    public static String callItemForBattleBeanClickFailStatus() {
        return callItemForBattleBeanClickFailStatus(healSimple());
    }

    public static String callItemForBattleBeanClickFailStatusId() {
        NaSt b_ = healSimple();
        callItemForBattleBeanClickFailStatus(b_);
        return getValStatusId(b_);
    }

    public static String callItemForBattleBeanClickFailStatus(NaSt _str) {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)_str).getInstance()).clickFailStatus(0);
    }

    public static String callItemForBattleBeanClickGlobalMove() {
        return callItemForBattleBeanClickGlobalMove(healSimple());
    }

    public static String callItemForBattleBeanClickGlobalMoveId() {
        NaSt b_ = healSimple();
        callItemForBattleBeanClickGlobalMove(b_);
        return getValMoveId(b_);
    }
    public static String callItemForBattleBeanClickGlobalMove(NaSt _str) {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)_str).getInstance()).clickGlobalMove(0);
    }

    public static String callItemForBattleBeanClickImmuMove() {
        return callItemForBattleBeanClickImmuMove(healSimple());
    }

    public static String callItemForBattleBeanClickImmuMoveId() {
        NaSt b_ = healSimple();
        callItemForBattleBeanClickImmuMove(b_);
        return getValMoveId(b_);
    }
    public static String callItemForBattleBeanClickImmuMove(NaSt _str) {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)_str).getInstance()).clickImmuMove(0);
    }

    public static String callItemForBattleBeanClickImmuStatus() {
        return callItemForBattleBeanClickImmuStatus(healSimple());
    }

    public static String callItemForBattleBeanClickImmuStatusId() {
        NaSt b_ = healSimple();
        callItemForBattleBeanClickImmuStatus(b_);
        return getValStatusId(b_);
    }
    public static String callItemForBattleBeanClickImmuStatus(NaSt _str) {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)_str).getInstance()).clickImmuStatus(0);
    }

    public static String callItemForBattleBeanClickMultStatPk() {
        return callItemForBattleBeanClickMultStatPk(healSimple());
    }

    public static String callItemForBattleBeanClickMultStatPkId() {
        NaSt b_ = healSimple();
        callItemForBattleBeanClickMultStatPk(b_);
        return getValPkId(b_);
    }
    public static String callItemForBattleBeanClickMultStatPk(NaSt _str) {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)_str).getInstance()).clickMultStatPk(0);
    }

    public static String callItemForBattleBeanClickSynchroStatus() {
        return callItemForBattleBeanClickSynchroStatus(healSimple());
    }

    public static String callItemForBattleBeanClickSynchroStatusId() {
        NaSt b_ = healSimple();
        callItemForBattleBeanClickSynchroStatus(b_);
        return getValStatusId(b_);
    }
    public static String callItemForBattleBeanClickSynchroStatus(NaSt _str) {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)_str).getInstance()).clickSynchroStatus(0);
    }

    public static String callItemForBattleBeanClickTeamMove() {
        return callItemForBattleBeanClickTeamMove(healSimple());
    }

    public static String callItemForBattleBeanClickTeamMoveId() {
        NaSt b_ = healSimple();
        callItemForBattleBeanClickTeamMove(b_);
        return getValMoveId(b_);
    }
    public static String callItemForBattleBeanClickTeamMove(NaSt _str) {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)_str).getInstance()).clickTeamMove(0);
    }

    public static String callItemForBattleBeanClickTrapMove() {
        return callItemForBattleBeanClickTrapMove(healSimple());
    }

    public static String callItemForBattleBeanClickTrapMoveId() {
        NaSt b_ = healSimple();
        callItemForBattleBeanClickTrapMove(b_);
        return getValMoveId(b_);
    }
    public static String callItemForBattleBeanClickTrapMove(NaSt _str) {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)_str).getInstance()).clickTrapMove(0);
    }

    public static String callItemForBattleBeanClickTypesPkAbility() {
        return callItemForBattleBeanClickTypesPkAbility(healSimple());
    }

    public static String callItemForBattleBeanClickTypesPkAbilityId() {
        NaSt b_ = healSimple();
        callItemForBattleBeanClickTypesPkAbility(b_);
        return getValAbilityId(b_);
    }
    public static String callItemForBattleBeanClickTypesPkAbility(NaSt _str) {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)_str).getInstance()).clickTypesPkAbility(0);
    }

    public static String callItemForBattleBeanClickWeather() {
        return callItemForBattleBeanClickWeather(healSimple());
    }

    public static String callItemForBattleBeanClickWeatherId() {
        NaSt b_ = healSimple();
        callItemForBattleBeanClickWeather(b_);
        return getValMoveId(b_);
    }
    public static String callItemForBattleBeanClickWeather(NaSt _str) {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)_str).getInstance()).clickWeather(0);
    }

    public static Rate callItemForBattleBeanDamageRecoilGet() {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getDamageRecoil();
    }

    public static boolean callItemForBattleBeanDeterminated(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff)).getInstance()).determinated();
    }

    public static Rate callItemForBattleBeanDrainedHpByDamageRateGet() {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getDrainedHpByDamageRate();
    }
//
//    public static NaSt callItemForBattleBeanEffectSendBeanGet(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanEffectSendBeanGet(),_str,_args);
//    }

    public static boolean callItemForBattleBeanEndRoundGet(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return ((ItemForBattleBean) ((PokemonBeanStruct) healSimpleEndRound(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff)).getInstance()).getEndRoundCommon().getEndRound();
    }

    public static long callItemForBattleBeanEndRoundRankGet(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return ((ItemForBattleBean) ((PokemonBeanStruct) healSimpleEndRound(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff)).getInstance()).getEndRoundCommon().getEndRoundRank();
    }

    public static boolean callItemForBattleBeanEndRoundGetNo(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return ((ItemForBattleBean) ((PokemonBeanStruct) healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff)).getInstance()).getEndRoundCommon().getEndRound();
    }

    public static long callItemForBattleBeanEndRoundRankGetNo(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return ((ItemForBattleBean) ((PokemonBeanStruct) healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff)).getInstance()).getEndRoundCommon().getEndRoundRank();
    }

    public static DictionaryComparator<TranslatedKey,String> callItemForBattleBeanFailStatusGet() {
        return (( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getFailStatus());
    }

//    public static NaSt callItemForBattleBeanGetEffectSending(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetEffectSending(),_str,_args);
//    }

    public static String callItemForBattleBeanGetTrFailStatus() {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrFailStatus(0);
    }

    public static String callItemForBattleBeanGetTrGlobalMove() {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrGlobalMove(0);
    }

    public static String callItemForBattleBeanGetTrImmuMove() {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrImmuMove(0);
    }

    public static String callItemForBattleBeanGetTrImmuStatus() {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrImmuStatus(0);
    }

    public static String callItemForBattleBeanGetTrImmuTypes() {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrImmuTypes(0);
    }

    public static String callItemForBattleBeanGetTrMultStat() {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrMultStat(0);
    }

    public static String callItemForBattleBeanGetTrMultStatPk() {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrMultStatPk(0);
    }

    public static String callItemForBattleBeanGetTrMultStatPkRank() {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrMultStatPkRank(0);
    }

    public static String callItemForBattleBeanGetTrMultStatRank() {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrMultStatRank(0);
    }

    public static String callItemForBattleBeanGetTrMultStatisSuperEff() {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrMultStatisSuperEff(0);
    }

    public static String callItemForBattleBeanGetTrMultStatisTypes() {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrMultStatisTypes(0);
    }

    public static String callItemForBattleBeanGetTrMultStatisTypesStat() {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrMultStatisTypesStat(0,0);
    }

    public static String callItemForBattleBeanGetTrSynchroStatus() {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrSynchroStatus(0);
    }

    public static String callItemForBattleBeanGetTrTeamMove() {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrTeamMove(0);
    }

    public static String callItemForBattleBeanGetTrTrapMove() {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrTrapMove(0);
    }

    public static String callItemForBattleBeanGetTrTypesPk() {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrTypesPk(0);
    }

    public static String callItemForBattleBeanGetTrTypesPkAbility() {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrTypesPkAbility(0);
    }

    public static String callItemForBattleBeanGetTrWeather() {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrWeather(0);
    }

    public static String callItemForBattleBeanGetTrWinEvFight() {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrWinEvFight(0);
    }

    public static boolean callItemForBattleBeanImmuLowStatisGet(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff)).getInstance()).getImmuLowStatis();
    }

    public static CustList<TranslatedKey> callItemForBattleBeanImmuMovesGet() {
        return (( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getImmuMoves());
    }

    public static CustList<TranslatedKey> callItemForBattleBeanImmuStatusGet() {
        return (( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getImmuStatus());
    }

    public static CustList<TranslatedKey> callItemForBattleBeanImmuTypesGet() {
        return (( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getImmuTypes());
    }

    public static CustList<TranslatedKey> callItemForBattleBeanImmuWeatherGet() {
        return (( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getImmuWeather());
    }

    public static DictionaryComparator<TranslatedKey,Long> callItemForBattleBeanIncreasingMaxNbRoundGlobalMoveGet() {
        return (( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getIncreasingMaxNbRoundGlobalMove());
    }

    public static DictionaryComparator<TranslatedKey,Long> callItemForBattleBeanIncreasingMaxNbRoundTeamMoveGet() {
        return (( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getIncreasingMaxNbRoundTeamMove());
    }

    public static DictionaryComparator<TranslatedKey,Long> callItemForBattleBeanIncreasingMaxNbRoundTrapGet() {
        return (( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getIncreasingMaxNbRoundTrap());
    }

    public static AbsMap<String,String> callItemForBattleBeanMapVarsFailEndRoundGet() {
        return (((ItemForBattleBean) ((PokemonBeanStruct) healSimpleEndRound()).getInstance()).getEndRoundCommon().getMapVarsFailEndRound());
    }

    public static AbsMap<String,String> callItemForBattleBeanMapVarsGet() {
        return (( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getMapVars());
    }

    public static String callItemForBattleBeanMultDamageGet() {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getMultDamage();
    }

    public static Rate callItemForBattleBeanMultDrainedHpGet() {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getMultDrainedHp();
    }

    public static String callItemForBattleBeanMultPowerGet() {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getMultPower();
    }

    public static DictionaryComparator<TranslatedKey,String> callItemForBattleBeanMultStatGet() {
        return (( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getMultStat());
    }

    public static AbsMap<TranslatedKeyPair,Long> callItemForBattleBeanMultStatPokemonRankGet() {
        return (( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getMultStatPokemonRank());
    }

    public static DictionaryComparator<TranslatedKey,Long> callItemForBattleBeanMultStatRankGet() {
        return (( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getMultStatRank());
    }

    public static Rate callItemForBattleBeanMultTrappingDamageGet() {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getMultTrappingDamage();
    }

    public static Rate callItemForBattleBeanMultWinningEvGet() {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getMultWinningEv();
    }

    public static Rate callItemForBattleBeanMultWinningExpGet() {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getMultWinningExp();
    }

    public static Rate callItemForBattleBeanMultWinningHappinessGet() {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getMultWinningHappiness();
    }

//    public static Struct callItemForBattleBeanMultWinningMoneyGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanMultWinningMoneyGet(),_str,_args);
//    }

    public static Rate callItemForBattleBeanProtectAgainstKoGet() {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getProtectAgainstKo();
    }

    public static Rate callItemForBattleBeanProtectAgainstKoIfFullHpGet() {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getProtectAgainstKoIfFullHp();
    }

    public static Rate callItemForBattleBeanRateForAttackFirst(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff)).getInstance()).rateForAttackFirst();
    }

    public static CustList<String> callItemForBattleBeanReasonsEndRoundGet() {
        return (((ItemForBattleBean) ((PokemonBeanStruct) healSimpleEndRound()).getInstance()).getEndRoundCommon().getReasonsEndRound());
    }

//    public static Struct callItemForBattleBeanRepellingWildPkGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanRepellingWildPkGet(),_str,_args);
//    }

    public static boolean callItemForBattleBeanSendingGet(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff)).getInstance()).getSending();
    }

    public static boolean callItemForBattleBeanSendingGetNoStat(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimpleNoStat(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff, true, true)).getInstance()).getSending();
    }

    public static boolean callItemForBattleBeanSendingGetStat(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return ( (ItemForBattleBean) ((PokemonBeanStruct)healSimpleStat(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff)).getInstance()).getSending();
    }

    public static CustList<TranslatedKey> callItemForBattleBeanSynchroStatusGet() {
        return (( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getSynchroStatus());
    }

    public static CustList<TranslatedKey> callItemForBattleBeanTypesPkAbilitiesGet() {
        return (( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTypesPkAbilities());
    }

    public static CustList<TranslatedKey> callItemForBattleBeanTypesPkGet() {
        return (( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTypesPk());
    }

    public static DictionaryComparator<TranslatedKey,Long> callItemForBattleBeanWinEvFightGet() {
        return (( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getWinEvFight());
    }

    public static StringMap<NaSt> beanToItBaseSend(PkData _pk) {
        StringMap<NaSt> map_ = beanToItBase(_pk);
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.SENDING,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.SENDING,new TranslationsFile());
//        EffectWhileSendingBean send_ = new EffectWhileSendingBean();
//        send_.setBuilder(((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder());
//        map_.addEntry(AikiBeansEffectsStd.EFFECT_SENDING, _pk.bean(send_, EN));
        return map_;
    }

    public static StringMap<NaSt> beanToItBase(PkData _pk) {
        StringMap<NaSt> map_ = beanToItem(_pk);
        ItemForBattleBean it_ = new ItemForBattleBean();
        map_.addEntry(InitDbItems.BEAN_ITEMFORBATTLE, _pk.bean(it_, EN));
        it_.setBuilder(((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_ITEMFORBATTLE,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.IT_ITEMFORBATTLE,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ITEMS_ITEMFORBATTLE_HTML,it_);
        return map_;
    }

    protected static NaSt healSimpleEndRound() {
        return healSimpleEndRound(true,true,true,true,true,true,LgInt.one(),LgInt.one());
    }

    protected static NaSt healSimple() {
        return healSimple(true,true,true,true,true,true,LgInt.one(),LgInt.one());
    }
    protected static NaSt healSimple(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        PkData pk_ = pkDataByFacade(feedDbItem(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff));
        StringMap<NaSt> all_ = beanToItBase(pk_);
        return dispLineQuick(InitDbItems.BEAN_ITEMFORBATTLE, pk_, all_);
    }

    protected static NaSt healSimpleEndRound(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        PkData pk_ = pkDataByFacade(feedDbItemEndRound(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff));
        StringMap<NaSt> all_ = beanToItBase(pk_);
        ((CommonBean)((PokemonBeanStruct)all_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_ENDROUND,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)all_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_ENDROUND,new TranslationsFile());
        return dispLineQuick(InitDbItems.BEAN_ITEMFORBATTLE, pk_, all_);
    }

    protected static NaSt healSimpleNoStat(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff, boolean _copyingAbility, boolean _disableWeather) {
        PkData pk_ = pkDataByFacade(feedDbItemNoStat(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff, _copyingAbility, _disableWeather));
        StringMap<NaSt> all_ = beanToItBaseSend(pk_);
        NaSt res_ = dispLineQuick(InitDbItems.BEAN_ITEMFORBATTLE, pk_, all_);
//        callItemForBattleBeanEffectSendBeanGet(res_);
//        NaSt send_ = all_.getVal(AikiBeansEffectsStd.EFFECT_SENDING);
//        callEffectWhileSendingBeanEffectSet(send_,callItemForBattleBeanGetEffectSending(res_));
//        beforeDisplaying(send_);
        return res_;
    }

    public static EffectWhileSendingBean healSimpleNoStatSend(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return healSimpleNoStatSend(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff,true,true);
    }
    public static EffectWhileSendingBean healSimpleNoStatSend(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff, boolean _copyingAbility, boolean _disableWeather) {
        PkData pk_ = pkDataByFacade(feedDbItemNoStat(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff, _copyingAbility, _disableWeather));
        StringMap<NaSt> all_ = beanToItBaseSend(pk_);
        NaSt res_ = dispLineQuick(InitDbItems.BEAN_ITEMFORBATTLE, pk_, all_);
//        callItemForBattleBeanEffectSendBeanGet(res_);
//        NaSt send_ = all_.getVal(AikiBeansEffectsStd.EFFECT_SENDING);
//        callEffectWhileSendingBeanEffectSet(send_,callItemForBattleBeanGetEffectSending(res_));
//        beforeDisplaying(send_);
        return effSend(res_);
    }

    public static EffectWhileSendingBean healSimpleStatSend(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        PkData pk_ = pkDataByFacade(feedDbItemStat(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff));
        StringMap<NaSt> all_ = beanToItBaseSend(pk_);
        ((CommonBean)((PokemonBeanStruct)all_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_STATIS,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)all_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_STATIS,new TranslationsFile());
        NaSt res_ = dispLineQuick(InitDbItems.BEAN_ITEMFORBATTLE, pk_, all_);
//        callItemForBattleBeanEffectSendBeanGet(res_);
//        NaSt send_ = all_.getVal(AikiBeansEffectsStd.EFFECT_SENDING);
//        callEffectWhileSendingBeanEffectSet(send_,callItemForBattleBeanGetEffectSending(res_));
//        beforeDisplaying(send_);
        return effSend(res_);
    }

    protected static NaSt healSimpleStat(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        PkData pk_ = pkDataByFacade(feedDbItemStat(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff));
        StringMap<NaSt> all_ = beanToItBaseSend(pk_);
        ((CommonBean)((PokemonBeanStruct)all_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_STATIS,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)all_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_STATIS,new TranslationsFile());
        NaSt res_ = dispLineQuick(InitDbItems.BEAN_ITEMFORBATTLE, pk_, all_);
//        callItemForBattleBeanEffectSendBeanGet(res_);
//        NaSt send_ = all_.getVal(AikiBeansEffectsStd.EFFECT_SENDING);
//        callEffectWhileSendingBeanEffectSet(send_,callItemForBattleBeanGetEffectSending(res_));
//        beforeDisplaying(send_);
        return res_;
    }
    protected static EffectWhileSendingBean effSend(NaSt _it) {
        ItemForBattleBean it_ = (ItemForBattleBean) ((PokemonBeanStruct) _it).getInstance();
        return it_.effSending(it_.getEffectSending());
    }

    protected static FacadeGame feedDbItem(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        FacadeGame facade_ = facade();
        facade_.getData().completeMembers(I_BASE,itemForBattle(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff));
        otherElts(facade_);
        facade_.getData().completeVariables();
        return facade_;
    }

    protected static FacadeGame feedDbItemStat(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return feedDbItem(eff(), true,_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff, true, true);
    }

    protected static FacadeGame feedDbItemNoStat(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff, boolean _copyingAbility, boolean _disableWeather) {
        return feedDbItem(eff(), false,_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff, _copyingAbility, _disableWeather);
    }

    protected static FacadeGame feedDbItemEndRound(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return feedDbItem(effEndRound(),_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff);
    }

    protected static FacadeGame feedDbItem(EffectStatistic _eff, boolean _withEffect, boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff, boolean _copyingAbility, boolean _disableWeather) {
        FacadeGame facade_ = facade();
        ItemForBattle it_ = itemForBattle(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff);
        EffectWhileSendingWithStatistic e_ = Instances.newEffectWhileSendingWithStatistic();
        e_.setEffect(_eff);
        e_.setWithEffect(_withEffect);
        e_.setCopyingAbility(_copyingAbility);
        e_.setDisableWeather(_disableWeather);
        e_.setEnabledWeather(M_DAM);
        e_.setMultWeight(Rate.one());
        it_.getEffectSending().add(e_);
        facade_.getData().completeMembers(I_BASE, it_);
        otherElts(facade_);
        facade_.getData().completeVariables();
        return facade_;
    }

    protected static FacadeGame feedDbItem(EffectEndRound _eff,boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        FacadeGame facade_ = facade();
        ItemForBattle it_ = itemForBattle(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff);
        it_.getEffectEndRound().add(_eff);
        facade_.getData().completeMembers(I_BASE, it_);
        otherElts(facade_);
        facade_.getData().completeVariables();
        return facade_;
    }

    private static void otherElts(FacadeGame _facade) {
        StatusMoveData rep_ = moveSta(TargetChoice.ANY_FOE);
        rep_.getEffects().add(lawEndRound());
        rep_.getRepeatRoundLaw().addQuickEvent(Rate.one(), LgInt.one());
        rep_.getRepeatRoundLaw().addQuickEvent(Rate.newRate("2"), LgInt.newLgInt("3"));
        _facade.getData().completeMembers(M_DAM, rep_);
        _facade.getData().completeMembers(A_ABILITY, abilityPlate(true));
        _facade.getData().completeMembers(P_POKEMON, pk(new StringList("__"), GenderRepartition.NO_GENDER));
        _facade.getData().completeMembers(S_STA_SIM, staSimple(""));
        trsCore(_facade);
        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_BASE,I_BASE_TR);
        _facade.getData().getTranslatedClassesDescriptions().addEntry(LANGUAGE,new StringMap<String>());
        _facade.getData().getTranslatedClassesDescriptions().getVal(EN).addEntry(_facade.getData().getItem(I_BASE).getItemType(),CI_ITEMBATTLE_TR);
        _facade.getData().getLitterals().addEntry(EN,new StringMap<String>());
        _facade.getData().getLitterals().getVal(EN).addEntry(MessagesDataBaseConstants.DEF_TEMPS_TOUR, TAB+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +TAB+TIME);
        _facade.getData().getMiniItems().addEntry(I_BASE, instance(IMG_MAX_RAI));
        _facade.getData().addConstNumTest(DataBase.VALEUR_DEF_STATIS,Rate.one());
    }

    protected static FacadeGame feedDbMove() {
        FacadeGame facade_ = facade();
        StatusMoveData rep_ = moveSta(TargetChoice.ANY_FOE);
        rep_.getEffects().add(Instances.newEffectStatistic());
        rep_.getEffects().add(lawEndRound());
        facade_.getData().completeMembers(M_DAM, rep_);
        facade_.getData().completeVariables();
        return facade_;
    }

    private static EffectEndRoundSingleRelation lawEndRound() {
        EffectEndRoundSingleRelation e_ = Instances.newEffectEndRoundSingleRelation();
        e_.getLawForEnablingEffect().addQuickEvent(Rate.one(), LgInt.one());
        e_.getLawForEnablingEffect().addQuickEvent(Rate.newRate("2"), LgInt.newLgInt("3"));
        return e_;
    }

    protected static FacadeGame feedDbAbility(boolean _plate) {
        FacadeGame facade_ = facade();
        facade_.getData().completeMembers(A_ABILITY, abilityPlate(_plate));
        facade_.getData().getTranslatedAbilities().addEntry(EN,new StringMap<String>());
        facade_.getData().completeVariables();
        return facade_;
    }

    private static AbilityData abilityPlate(boolean _plate) {
        AbilityData ab_ = Instances.newAbilityData();
        ab_.setPlate(_plate);
        return ab_;
    }

    protected static EffectStatistic eff() {
        EffectStatistic e_ = Instances.newEffectStatistic();
        e_.setEvtRate(Rate.one());
        e_.getCancelLowStat().add(Statistic.SPEED);
        e_.getCancelChgtStat().add(Statistic.SPEED);
        e_.getCopyBoost().add(Statistic.SPEED);
        e_.getSwapBoostStatis().add(Statistic.SPEED);
        e_.getStatisVarRank().addEntry(Statistic.SPEED,1L);
        e_.getLocalFailStatis().addEntry(Statistic.SPEED, VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        e_.getLocalFailSwapBoostStatis().addEntry(Statistic.SPEED, VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        e_.getLawBoost().addQuickEvent(Statistic.SPEED,LgInt.one());
        e_.setFail(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        return e_;
    }

    protected static EffectEndRound effEndRound() {
        EffectEndRound e_ = lawEndRound();
        e_.setFailEndRound(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        e_.setEndRoundRank(1);
        return e_;
    }
}
