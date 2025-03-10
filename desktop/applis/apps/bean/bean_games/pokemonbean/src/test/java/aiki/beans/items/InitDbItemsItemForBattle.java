package aiki.beans.items;

import aiki.beans.PkData;
import aiki.beans.*;
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
import code.util.StringList;
import code.util.StringMap;

public abstract class InitDbItemsItemForBattle extends InitDbItem {

    public static NaSt callItemForBattleBeanAgainstEvoGet(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return NaBoSt.of(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff)).getInstance()).getAgainstEvo());
    }

    public static NaSt callItemForBattleBeanAttackLastGet(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return NaBoSt.of(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff)).getInstance()).getAttackLast());
    }

    public static NaSt callItemForBattleBeanAttacksSoonGet(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return NaBoSt.of(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff)).getInstance()).getAttacksSoon());
    }

    public static NaSt callItemForBattleBeanBoostExpGet(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return NaBoSt.of(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff)).getInstance()).getBoostExp());
    }

    public static NaSt callItemForBattleBeanBoostStatisSuperEffGet() {
        return PokemonStandards.getStaByte(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getBoostStatisSuperEff());
    }

    public static NaSt callItemForBattleBeanBoostStatisTypesGet() {
        return PokemonStandards.getBigNatMapSta(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getBoostStatisTypes());
    }

    public static NaSt callItemForBattleBeanCancelImmuTypeGet(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return NaBoSt.of(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff)).getInstance()).getCancelImmuType());
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
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)_str).getInstance()).clickFailStatus(0)).getInstance();
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
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)_str).getInstance()).clickGlobalMove(0)).getInstance();
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
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)_str).getInstance()).clickImmuMove(0)).getInstance();
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
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)_str).getInstance()).clickImmuStatus(0)).getInstance();
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
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)_str).getInstance()).clickMultStatPk(0)).getInstance();
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
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)_str).getInstance()).clickSynchroStatus(0)).getInstance();
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
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)_str).getInstance()).clickTeamMove(0)).getInstance();
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
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)_str).getInstance()).clickTrapMove(0)).getInstance();
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
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)_str).getInstance()).clickTypesPkAbility(0)).getInstance();
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
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)_str).getInstance()).clickWeather(0)).getInstance();
    }

    public static NaSt callItemForBattleBeanDamageRecoilGet() {
        return new RtSt(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getDamageRecoil());
    }

    public static NaSt callItemForBattleBeanDeterminated(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return NaBoSt.of(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff)).getInstance()).determinated());
    }

    public static NaSt callItemForBattleBeanDrainedHpByDamageRateGet() {
        return new RtSt(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getDrainedHpByDamageRate());
    }
//
//    public static NaSt callItemForBattleBeanEffectSendBeanGet(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanEffectSendBeanGet(),_str,_args);
//    }

    public static NaSt callItemForBattleBeanEndRoundGet(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return NaBoSt.of(((ItemForBattleBean) ((PokemonBeanStruct) healSimpleEndRound(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff)).getInstance()).getEndRoundCommon().getEndRound());
    }

    public static NaSt callItemForBattleBeanEndRoundRankGet(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return new NaNbSt(((ItemForBattleBean) ((PokemonBeanStruct) healSimpleEndRound(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff)).getInstance()).getEndRoundCommon().getEndRoundRank());
    }

    public static NaSt callItemForBattleBeanEndRoundGetNo(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return NaBoSt.of(((ItemForBattleBean) ((PokemonBeanStruct) healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff)).getInstance()).getEndRoundCommon().getEndRound());
    }

    public static NaSt callItemForBattleBeanEndRoundRankGetNo(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return new NaNbSt(((ItemForBattleBean) ((PokemonBeanStruct) healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff)).getInstance()).getEndRoundCommon().getEndRoundRank());
    }

    public static NaSt callItemForBattleBeanFailStatusGet() {
        return PokemonStandards.getStrStrOnly(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getFailStatus());
    }

//    public static NaSt callItemForBattleBeanGetEffectSending(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetEffectSending(),_str,_args);
//    }

    public static NaSt callItemForBattleBeanGetTrFailStatus() {
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrFailStatus(0));
    }

    public static NaSt callItemForBattleBeanGetTrGlobalMove() {
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrGlobalMove(0));
    }

    public static NaSt callItemForBattleBeanGetTrImmuMove() {
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrImmuMove(0));
    }

    public static NaSt callItemForBattleBeanGetTrImmuStatus() {
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrImmuStatus(0));
    }

    public static NaSt callItemForBattleBeanGetTrImmuTypes() {
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrImmuTypes(0));
    }

    public static NaSt callItemForBattleBeanGetTrMultStat() {
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrMultStat(0));
    }

    public static NaSt callItemForBattleBeanGetTrMultStatPk() {
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrMultStatPk(0));
    }

    public static NaSt callItemForBattleBeanGetTrMultStatPkRank() {
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrMultStatPkRank(0));
    }

    public static NaSt callItemForBattleBeanGetTrMultStatRank() {
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrMultStatRank(0));
    }

    public static NaSt callItemForBattleBeanGetTrMultStatisSuperEff() {
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrMultStatisSuperEff(0));
    }

    public static NaSt callItemForBattleBeanGetTrMultStatisTypes() {
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrMultStatisTypes(0));
    }

    public static NaSt callItemForBattleBeanGetTrMultStatisTypesStat() {
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrMultStatisTypesStat(0,0));
    }

    public static NaSt callItemForBattleBeanGetTrSynchroStatus() {
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrSynchroStatus(0));
    }

    public static NaSt callItemForBattleBeanGetTrTeamMove() {
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrTeamMove(0));
    }

    public static NaSt callItemForBattleBeanGetTrTrapMove() {
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrTrapMove(0));
    }

    public static NaSt callItemForBattleBeanGetTrTypesPk() {
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrTypesPk(0));
    }

    public static NaSt callItemForBattleBeanGetTrTypesPkAbility() {
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrTypesPkAbility(0));
    }

    public static NaSt callItemForBattleBeanGetTrWeather() {
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrWeather(0));
    }

    public static NaSt callItemForBattleBeanGetTrWinEvFight() {
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTrWinEvFight(0));
    }

    public static NaSt callItemForBattleBeanImmuLowStatisGet(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return NaBoSt.of(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff)).getInstance()).getImmuLowStatis());
    }

    public static NaSt callItemForBattleBeanImmuMovesGet() {
        return PokemonStandards.getKeys(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getImmuMoves());
    }

    public static NaSt callItemForBattleBeanImmuStatusGet() {
        return PokemonStandards.getKeys(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getImmuStatus());
    }

    public static NaSt callItemForBattleBeanImmuTypesGet() {
        return PokemonStandards.getKeys(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getImmuTypes());
    }

    public static NaSt callItemForBattleBeanImmuWeatherGet() {
        return PokemonStandards.getKeys(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getImmuWeather());
    }

    public static NaSt callItemForBattleBeanIncreasingMaxNbRoundGlobalMoveGet() {
        return PokemonStandards.getStrLong(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getIncreasingMaxNbRoundGlobalMove());
    }

    public static NaSt callItemForBattleBeanIncreasingMaxNbRoundTeamMoveGet() {
        return PokemonStandards.getStrLong(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getIncreasingMaxNbRoundTeamMove());
    }

    public static NaSt callItemForBattleBeanIncreasingMaxNbRoundTrapGet() {
        return PokemonStandards.getStrLong(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getIncreasingMaxNbRoundTrap());
    }

    public static NaSt callItemForBattleBeanMapVarsFailEndRoundGet() {
        return PokemonStandards.getStrStr(((ItemForBattleBean) ((PokemonBeanStruct) healSimpleEndRound()).getInstance()).getEndRoundCommon().getMapVarsFailEndRound());
    }

    public static NaSt callItemForBattleBeanMapVarsGet() {
        return PokemonStandards.getStrStr(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getMapVars());
    }

    public static NaSt callItemForBattleBeanMultDamageGet() {
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getMultDamage());
    }

    public static NaSt callItemForBattleBeanMultDrainedHpGet() {
        return new RtSt(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getMultDrainedHp());
    }

    public static NaSt callItemForBattleBeanMultPowerGet() {
        return new NaStSt(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getMultPower());
    }

    public static NaSt callItemForBattleBeanMultStatGet() {
        return PokemonStandards.getStaStr(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getMultStat());
    }

    public static NaSt callItemForBattleBeanMultStatPokemonRankGet() {
        return PokemonStandards.getWcByteMap(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getMultStatPokemonRank());
    }

    public static NaSt callItemForBattleBeanMultStatRankGet() {
        return PokemonStandards.getStaByte(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getMultStatRank());
    }

    public static NaSt callItemForBattleBeanMultTrappingDamageGet() {
        return new RtSt(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getMultTrappingDamage());
    }

    public static NaSt callItemForBattleBeanMultWinningEvGet() {
        return new RtSt(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getMultWinningEv());
    }

    public static NaSt callItemForBattleBeanMultWinningExpGet() {
        return new RtSt(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getMultWinningExp());
    }

    public static NaSt callItemForBattleBeanMultWinningHappinessGet() {
        return new RtSt(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getMultWinningHappiness());
    }

//    public static Struct callItemForBattleBeanMultWinningMoneyGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanMultWinningMoneyGet(),_str,_args);
//    }

    public static NaSt callItemForBattleBeanProtectAgainstKoGet() {
        return new RtSt(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getProtectAgainstKo());
    }

    public static NaSt callItemForBattleBeanProtectAgainstKoIfFullHpGet() {
        return new RtSt(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getProtectAgainstKoIfFullHp());
    }

    public static NaSt callItemForBattleBeanRateForAttackFirst(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return new RtSt(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff)).getInstance()).rateForAttackFirst());
    }

    public static NaSt callItemForBattleBeanReasonsEndRoundGet() {
        return BeanNatCommonLgNames.getStringArray(((ItemForBattleBean) ((PokemonBeanStruct) healSimpleEndRound()).getInstance()).getEndRoundCommon().getReasonsEndRound());
    }

//    public static Struct callItemForBattleBeanRepellingWildPkGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanRepellingWildPkGet(),_str,_args);
//    }

    public static NaSt callItemForBattleBeanSendingGet(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return NaBoSt.of(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff)).getInstance()).getSending());
    }

    public static NaSt callItemForBattleBeanSendingGetNoStat(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return NaBoSt.of(( (ItemForBattleBean) ((PokemonBeanStruct)healSimpleNoStat(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff, true, true)).getInstance()).getSending());
    }

    public static NaSt callItemForBattleBeanSendingGetStat(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return NaBoSt.of(( (ItemForBattleBean) ((PokemonBeanStruct)healSimpleStat(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff)).getInstance()).getSending());
    }

    public static NaSt callItemForBattleBeanSynchroStatusGet() {
        return PokemonStandards.getKeys(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getSynchroStatus());
    }

    public static NaSt callItemForBattleBeanTypesPkAbilitiesGet() {
        return PokemonStandards.getKeys(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTypesPkAbilities());
    }

    public static NaSt callItemForBattleBeanTypesPkGet() {
        return PokemonStandards.getKeys(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getTypesPk());
    }

    public static NaSt callItemForBattleBeanWinEvFightGet() {
        return PokemonStandards.getStaByte(( (ItemForBattleBean) ((PokemonBeanStruct)healSimple()).getInstance()).getWinEvFight());
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

    public static NaSt healSimpleNoStatSend(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return healSimpleNoStatSend(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff,true,true);
    }
    public static NaSt healSimpleNoStatSend(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff, boolean _copyingAbility, boolean _disableWeather) {
        PkData pk_ = pkDataByFacade(feedDbItemNoStat(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff, _copyingAbility, _disableWeather));
        StringMap<NaSt> all_ = beanToItBaseSend(pk_);
        NaSt res_ = dispLineQuick(InitDbItems.BEAN_ITEMFORBATTLE, pk_, all_);
//        callItemForBattleBeanEffectSendBeanGet(res_);
//        NaSt send_ = all_.getVal(AikiBeansEffectsStd.EFFECT_SENDING);
//        callEffectWhileSendingBeanEffectSet(send_,callItemForBattleBeanGetEffectSending(res_));
//        beforeDisplaying(send_);
        return effSend(res_);
    }

    public static NaSt healSimpleStatSend(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
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
    protected static NaSt effSend(NaSt _it) {
        ItemForBattleBean it_ = (ItemForBattleBean) ((PokemonBeanStruct) _it).getInstance();
        return new PokemonBeanStruct(it_.effSending(it_.getEffectSending()));
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
