package aiki.beans.items;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.PkData;
import aiki.beans.effects.AikiBeansEffectsStd;
import aiki.db.DataBase;
import aiki.facade.*;
import aiki.fight.abilities.AbilityData;
import aiki.fight.effects.EffectWhileSendingWithStatistic;
import aiki.fight.enums.Statistic;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.enums.*;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.game.fight.Fight;
import aiki.instances.Instances;
import code.expressionlanguage.structs.Struct;
import code.images.BaseSixtyFourUtil;
import code.maths.*;
import code.util.StringList;
import code.util.StringMap;

public abstract class InitDbItemsItemForBattle extends InitDbItem {

    public static Struct callItemForBattleBeanAgainstEvoGet(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanAgainstEvoGet(),healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff));
    }

    public static Struct callItemForBattleBeanAttackLastGet(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanAttackLastGet(),healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff));
    }

    public static Struct callItemForBattleBeanAttacksSoonGet(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanAttacksSoonGet(),healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff));
    }

    public static Struct callItemForBattleBeanBoostExpGet(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanBoostExpGet(),healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff));
    }

    public static Struct callItemForBattleBeanBoostStatisSuperEffGet() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanBoostStatisSuperEffGet(),healSimple());
    }

    public static Struct callItemForBattleBeanBoostStatisTypesGet() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanBoostStatisTypesGet(),healSimple());
    }

    public static Struct callItemForBattleBeanCancelImmuTypeGet(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanCancelImmuTypeGet(),healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff));
    }

    public static String callItemForBattleBeanClickFailStatus() {
        return callItemForBattleBeanClickFailStatus(healSimple());
    }

    public static String callItemForBattleBeanClickFailStatusId() {
        Struct b_ = healSimple();
        callItemForBattleBeanClickFailStatus(b_);
        return getValStatusId(b_);
    }

    public static String callItemForBattleBeanClickFailStatus(Struct _str) {
        return navigateData(new ItemForBattleBeanClickFailStatus(),_str,0);
    }

    public static String callItemForBattleBeanClickGlobalMove() {
        return callItemForBattleBeanClickGlobalMove(healSimple());
    }

    public static String callItemForBattleBeanClickGlobalMoveId() {
        Struct b_ = healSimple();
        callItemForBattleBeanClickGlobalMove(b_);
        return getValMoveId(b_);
    }
    public static String callItemForBattleBeanClickGlobalMove(Struct _str) {
        return navigateData(new ItemForBattleBeanClickGlobalMove(),_str,0);
    }

    public static String callItemForBattleBeanClickImmuMove() {
        return callItemForBattleBeanClickImmuMove(healSimple());
    }

    public static String callItemForBattleBeanClickImmuMoveId() {
        Struct b_ = healSimple();
        callItemForBattleBeanClickImmuMove(b_);
        return getValMoveId(b_);
    }
    public static String callItemForBattleBeanClickImmuMove(Struct _str) {
        return navigateData(new ItemForBattleBeanClickImmuMove(),_str,0);
    }

    public static String callItemForBattleBeanClickImmuStatus() {
        return callItemForBattleBeanClickImmuStatus(healSimple());
    }

    public static String callItemForBattleBeanClickImmuStatusId() {
        Struct b_ = healSimple();
        callItemForBattleBeanClickImmuStatus(b_);
        return getValStatusId(b_);
    }
    public static String callItemForBattleBeanClickImmuStatus(Struct _str) {
        return navigateData(new ItemForBattleBeanClickImmuStatus(),_str,0);
    }

    public static String callItemForBattleBeanClickMultStatPk() {
        return callItemForBattleBeanClickMultStatPk(healSimple());
    }

    public static String callItemForBattleBeanClickMultStatPkId() {
        Struct b_ = healSimple();
        callItemForBattleBeanClickMultStatPk(b_);
        return getValPkId(b_);
    }
    public static String callItemForBattleBeanClickMultStatPk(Struct _str) {
        return navigateData(new ItemForBattleBeanClickMultStatPk(),_str,0);
    }

    public static String callItemForBattleBeanClickSynchroStatus() {
        return callItemForBattleBeanClickSynchroStatus(healSimple());
    }

    public static String callItemForBattleBeanClickSynchroStatusId() {
        Struct b_ = healSimple();
        callItemForBattleBeanClickSynchroStatus(b_);
        return getValStatusId(b_);
    }
    public static String callItemForBattleBeanClickSynchroStatus(Struct _str) {
        return navigateData(new ItemForBattleBeanClickSynchroStatus(),_str,0);
    }

    public static String callItemForBattleBeanClickTeamMove() {
        return callItemForBattleBeanClickTeamMove(healSimple());
    }

    public static String callItemForBattleBeanClickTeamMoveId() {
        Struct b_ = healSimple();
        callItemForBattleBeanClickTeamMove(b_);
        return getValMoveId(b_);
    }
    public static String callItemForBattleBeanClickTeamMove(Struct _str) {
        return navigateData(new ItemForBattleBeanClickTeamMove(),_str,0);
    }

    public static String callItemForBattleBeanClickTrapMove() {
        return callItemForBattleBeanClickTrapMove(healSimple());
    }

    public static String callItemForBattleBeanClickTrapMoveId() {
        Struct b_ = healSimple();
        callItemForBattleBeanClickTrapMove(b_);
        return getValMoveId(b_);
    }
    public static String callItemForBattleBeanClickTrapMove(Struct _str) {
        return navigateData(new ItemForBattleBeanClickTrapMove(),_str,0);
    }

    public static String callItemForBattleBeanClickTypesPkAbility() {
        return callItemForBattleBeanClickTypesPkAbility(healSimple());
    }

    public static String callItemForBattleBeanClickTypesPkAbilityId() {
        Struct b_ = healSimple();
        callItemForBattleBeanClickTypesPkAbility(b_);
        return getValAbilityId(b_);
    }
    public static String callItemForBattleBeanClickTypesPkAbility(Struct _str) {
        return navigateData(new ItemForBattleBeanClickTypesPkAbility(),_str,0);
    }

    public static String callItemForBattleBeanClickWeather() {
        return callItemForBattleBeanClickWeather(healSimple());
    }

    public static String callItemForBattleBeanClickWeatherId() {
        Struct b_ = healSimple();
        callItemForBattleBeanClickWeather(b_);
        return getValMoveId(b_);
    }
    public static String callItemForBattleBeanClickWeather(Struct _str) {
        return navigateData(new ItemForBattleBeanClickWeather(),_str,0);
    }

    public static Struct callItemForBattleBeanDamageRecoilGet() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanDamageRecoilGet(),healSimple());
    }

    public static Struct callItemForBattleBeanDeterminated(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanDeterminated(),healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff));
    }

    public static Struct callItemForBattleBeanDrainedHpByDamageRateGet() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanDrainedHpByDamageRateGet(),healSimple());
    }

    public static Struct callItemForBattleBeanEffectSendBeanGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanEffectSendBeanGet(),_str,_args);
    }

    public static Struct callItemForBattleBeanEndRoundGet(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanEndRoundGet(),healSimpleEndRound(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff));
    }

    public static Struct callItemForBattleBeanEndRoundRankGet(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanEndRoundRankGet(),healSimpleEndRound(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff));
    }

    public static Struct callItemForBattleBeanEndRoundGetNo(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanEndRoundGet(),healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff));
    }

    public static Struct callItemForBattleBeanEndRoundRankGetNo(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanEndRoundRankGet(),healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff));
    }

    public static Struct callItemForBattleBeanFailStatusGet() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanFailStatusGet(),healSimple());
    }

    public static Struct callItemForBattleBeanGetEffectSending(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetEffectSending(),_str,_args);
    }

    public static Struct callItemForBattleBeanGetTrFailStatus() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrFailStatus(),healSimple(),0);
    }

    public static Struct callItemForBattleBeanGetTrGlobalMove() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrGlobalMove(),healSimple(),0);
    }

    public static Struct callItemForBattleBeanGetTrImmuMove() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrImmuMove(),healSimple(),0);
    }

    public static Struct callItemForBattleBeanGetTrImmuStatus() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrImmuStatus(),healSimple(),0);
    }

    public static Struct callItemForBattleBeanGetTrImmuTypes() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrImmuTypes(),healSimple(),0);
    }

    public static Struct callItemForBattleBeanGetTrMultStat() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrMultStat(),healSimple(),0);
    }

    public static Struct callItemForBattleBeanGetTrMultStatPk() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrMultStatPk(),healSimple(),0);
    }

    public static Struct callItemForBattleBeanGetTrMultStatPkRank() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrMultStatPkRank(),healSimple(),0);
    }

    public static Struct callItemForBattleBeanGetTrMultStatRank() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrMultStatRank(),healSimple(),0);
    }

    public static Struct callItemForBattleBeanGetTrMultStatisSuperEff() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrMultStatisSuperEff(),healSimple(),0);
    }

    public static Struct callItemForBattleBeanGetTrMultStatisTypes() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrMultStatisTypes(),healSimple(),0);
    }

    public static Struct callItemForBattleBeanGetTrMultStatisTypesStat() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrMultStatisTypesStat(),healSimple(),0);
    }

    public static Struct callItemForBattleBeanGetTrSynchroStatus() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrSynchroStatus(),healSimple(),0);
    }

    public static Struct callItemForBattleBeanGetTrTeamMove() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrTeamMove(),healSimple(),0);
    }

    public static Struct callItemForBattleBeanGetTrTrapMove() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrTrapMove(),healSimple(),0);
    }

    public static Struct callItemForBattleBeanGetTrTypesPk() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrTypesPk(),healSimple(),0);
    }

    public static Struct callItemForBattleBeanGetTrTypesPkAbility() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrTypesPkAbility(),healSimple(),0);
    }

    public static Struct callItemForBattleBeanGetTrWeather() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrWeather(),healSimple(),0);
    }

    public static Struct callItemForBattleBeanGetTrWinEvFight() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrWinEvFight(),healSimple(),0);
    }

    public static Struct callItemForBattleBeanImmuLowStatisGet(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanImmuLowStatisGet(),healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff));
    }

    public static Struct callItemForBattleBeanImmuMovesGet() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanImmuMovesGet(),healSimple());
    }

    public static Struct callItemForBattleBeanImmuStatusGet() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanImmuStatusGet(),healSimple());
    }

    public static Struct callItemForBattleBeanImmuTypesGet() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanImmuTypesGet(),healSimple());
    }

    public static Struct callItemForBattleBeanImmuWeatherGet() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanImmuWeatherGet(),healSimple());
    }

    public static Struct callItemForBattleBeanIncreasingMaxNbRoundGlobalMoveGet() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanIncreasingMaxNbRoundGlobalMoveGet(),healSimple());
    }

    public static Struct callItemForBattleBeanIncreasingMaxNbRoundTeamMoveGet() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanIncreasingMaxNbRoundTeamMoveGet(),healSimple());
    }

    public static Struct callItemForBattleBeanIncreasingMaxNbRoundTrapGet() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanIncreasingMaxNbRoundTrapGet(),healSimple());
    }

    public static Struct callItemForBattleBeanMapVarsFailEndRoundGet() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanMapVarsFailEndRoundGet(),healSimpleEndRound());
    }

    public static Struct callItemForBattleBeanMapVarsGet() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanMapVarsGet(),healSimple());
    }

    public static Struct callItemForBattleBeanMultDamageGet() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanMultDamageGet(),healSimple());
    }

    public static Struct callItemForBattleBeanMultDrainedHpGet() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanMultDrainedHpGet(),healSimple());
    }

    public static Struct callItemForBattleBeanMultPowerGet() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanMultPowerGet(),healSimple());
    }

    public static Struct callItemForBattleBeanMultStatGet() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanMultStatGet(),healSimple());
    }

    public static Struct callItemForBattleBeanMultStatPokemonRankGet() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanMultStatPokemonRankGet(),healSimple());
    }

    public static Struct callItemForBattleBeanMultStatRankGet() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanMultStatRankGet(),healSimple());
    }

    public static Struct callItemForBattleBeanMultTrappingDamageGet() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanMultTrappingDamageGet(),healSimple());
    }

    public static Struct callItemForBattleBeanMultWinningEvGet() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanMultWinningEvGet(),healSimple());
    }

    public static Struct callItemForBattleBeanMultWinningExpGet() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanMultWinningExpGet(),healSimple());
    }

    public static Struct callItemForBattleBeanMultWinningHappinessGet() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanMultWinningHappinessGet(),healSimple());
    }

//    public static Struct callItemForBattleBeanMultWinningMoneyGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanMultWinningMoneyGet(),_str,_args);
//    }

    public static Struct callItemForBattleBeanProtectAgainstKoGet() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanProtectAgainstKoGet(),healSimple());
    }

    public static Struct callItemForBattleBeanProtectAgainstKoIfFullHpGet() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanProtectAgainstKoIfFullHpGet(),healSimple());
    }

    public static Struct callItemForBattleBeanRateForAttackFirst(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanRateForAttackFirst(),healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff));
    }

    public static Struct callItemForBattleBeanReasonsEndRoundGet() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanReasonsEndRoundGet(),healSimpleEndRound());
    }

//    public static Struct callItemForBattleBeanRepellingWildPkGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanRepellingWildPkGet(),_str,_args);
//    }

    public static Struct callItemForBattleBeanSendingGet(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanSendingGet(),healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff));
    }

    public static Struct callItemForBattleBeanSendingGetNoStat(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanSendingGet(),healSimpleNoStat(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff, true, true));
    }

    public static Struct callItemForBattleBeanSendingGetStat(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanSendingGet(),healSimpleStat(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff));
    }

    public static Struct callItemForBattleBeanSynchroStatusGet() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanSynchroStatusGet(),healSimple());
    }

    public static Struct callItemForBattleBeanTypesPkAbilitiesGet() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanTypesPkAbilitiesGet(),healSimple());
    }

    public static Struct callItemForBattleBeanTypesPkGet() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanTypesPkGet(),healSimple());
    }

    public static Struct callItemForBattleBeanWinEvFightGet() {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanWinEvFightGet(),healSimple());
    }

    public static StringMap<Struct> beanToItBaseSend(PkData _pk) {
        StringMap<Struct> map_ = beanToItBase(_pk);
        map_.addEntry(AikiBeansEffectsStd.EFFECT_SENDING,_pk.beanEffectWhileSendingBean(EN));
        return map_;
    }

    public static StringMap<Struct> beanToItBase(PkData _pk) {
        StringMap<Struct> map_ = beanToItem(_pk);
        map_.addEntry(AikiBeansItemsStd.BEAN_ITEMFORBATTLE,_pk.beanItemForBattleBean(EN));
        return map_;
    }

    protected static Struct healSimpleEndRound() {
        return healSimpleEndRound(true,true,true,true,true,true,LgInt.one(),LgInt.one());
    }

    protected static Struct healSimple() {
        return healSimple(true,true,true,true,true,true,LgInt.one(),LgInt.one());
    }
    protected static Struct healSimple(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        PkData pk_ = pkDataByFacade(feedDbItem(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff));
        StringMap<Struct> all_ = beanToItBase(pk_);
        return dispLine(AikiBeansItemsStd.BEAN_ITEMFORBATTLE, pk_, all_);
    }

    protected static Struct healSimpleEndRound(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        PkData pk_ = pkDataByFacade(feedDbItemEndRound(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff));
        StringMap<Struct> all_ = beanToItBase(pk_);
        return dispLine(AikiBeansItemsStd.BEAN_ITEMFORBATTLE, pk_, all_);
    }

    protected static Struct healSimpleNoStat(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff, boolean _copyingAbility, boolean _disableWeather) {
        PkData pk_ = pkDataByFacade(feedDbItemNoStat(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff, _copyingAbility, _disableWeather));
        StringMap<Struct> all_ = beanToItBaseSend(pk_);
        Struct res_ = dispLine(AikiBeansItemsStd.BEAN_ITEMFORBATTLE, pk_, all_);
        callItemForBattleBeanEffectSendBeanGet(res_);
        Struct send_ = all_.getVal(AikiBeansEffectsStd.EFFECT_SENDING);
        callEffectWhileSendingBeanEffectSet(send_,callItemForBattleBeanGetEffectSending(res_));
        beforeDisplaying(send_);
        return res_;
    }

    public static Struct healSimpleNoStatSend(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return healSimpleNoStatSend(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff,true,true);
    }
    public static Struct healSimpleNoStatSend(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff, boolean _copyingAbility, boolean _disableWeather) {
        PkData pk_ = pkDataByFacade(feedDbItemNoStat(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff, _copyingAbility, _disableWeather));
        StringMap<Struct> all_ = beanToItBaseSend(pk_);
        Struct res_ = dispLine(AikiBeansItemsStd.BEAN_ITEMFORBATTLE, pk_, all_);
        callItemForBattleBeanEffectSendBeanGet(res_);
        Struct send_ = all_.getVal(AikiBeansEffectsStd.EFFECT_SENDING);
        callEffectWhileSendingBeanEffectSet(send_,callItemForBattleBeanGetEffectSending(res_));
        beforeDisplaying(send_);
        return send_;
    }

    public static Struct healSimpleStatSend(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        PkData pk_ = pkDataByFacade(feedDbItemStat(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff));
        StringMap<Struct> all_ = beanToItBaseSend(pk_);
        Struct res_ = dispLine(AikiBeansItemsStd.BEAN_ITEMFORBATTLE, pk_, all_);
        callItemForBattleBeanEffectSendBeanGet(res_);
        Struct send_ = all_.getVal(AikiBeansEffectsStd.EFFECT_SENDING);
        callEffectWhileSendingBeanEffectSet(send_,callItemForBattleBeanGetEffectSending(res_));
        beforeDisplaying(send_);
        return send_;
    }

    protected static Struct healSimpleStat(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        PkData pk_ = pkDataByFacade(feedDbItemStat(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff));
        StringMap<Struct> all_ = beanToItBaseSend(pk_);
        Struct res_ = dispLine(AikiBeansItemsStd.BEAN_ITEMFORBATTLE, pk_, all_);
        callItemForBattleBeanEffectSendBeanGet(res_);
        Struct send_ = all_.getVal(AikiBeansEffectsStd.EFFECT_SENDING);
        callEffectWhileSendingBeanEffectSet(send_,callItemForBattleBeanGetEffectSending(res_));
        beforeDisplaying(send_);
        return res_;
    }

    protected static FacadeGame feedDbItem(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        FacadeGame facade_ = facade();
        facade_.getData().completeMembers(I_BASE,itemForBattle(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff));
        otherElts(facade_);
        facade_.getData().completeVariables();
        return facade_;
    }

    protected static FacadeGame feedDbItemStat(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return feedDbItem(eff(),_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff, true, true);
    }

    protected static FacadeGame feedDbItemNoStat(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff, boolean _copyingAbility, boolean _disableWeather) {
        return feedDbItem(null,_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff, _copyingAbility, _disableWeather);
    }

    protected static FacadeGame feedDbItemEndRound(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return feedDbItem(effEndRound(),_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff);
    }

    protected static FacadeGame feedDbItem(EffectStatistic _eff, boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff, boolean _copyingAbility, boolean _disableWeather) {
        FacadeGame facade_ = facade();
        ItemForBattle it_ = itemForBattle(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff);
        EffectWhileSendingWithStatistic e_ = Instances.newEffectWhileSendingWithStatistic();
        e_.setEffect(_eff);
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
        _facade.getData().getLitterals().getVal(EN).addEntry(Fight.TEMPS_TOUR, TAB+Fight.TEMPS_TOUR+TAB+TIME);
        _facade.getData().getMiniItems().addEntry(I_BASE, BaseSixtyFourUtil.getImageByString(MAX_RAI));
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
        e_.getStatisVarRank().addEntry(Statistic.SPEED,(byte)1);
        e_.getLocalFailStatis().addEntry(Statistic.SPEED, DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR);
        e_.getLocalFailSwapBoostStatis().addEntry(Statistic.SPEED, DataBase.VAR_PREFIX+Fight.TEMPS_TOUR);
        e_.getLawBoost().addQuickEvent(Statistic.SPEED,LgInt.one());
        e_.setFail(DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR);
        return e_;
    }

    protected static EffectEndRound effEndRound() {
        EffectEndRound e_ = lawEndRound();
        e_.setFailEndRound(DataBase.VAR_PREFIX+Fight.TEMPS_TOUR);
        e_.setEndRoundRank(1);
        return e_;
    }
}
