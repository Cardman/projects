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
import aiki.game.fight.Fight;
import aiki.instances.Instances;
import code.expressionlanguage.structs.Struct;
import code.images.BaseSixtyFourUtil;
import code.maths.*;
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

    public static Struct callItemForBattleBeanBoostStatisSuperEffGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanBoostStatisSuperEffGet(),_str,_args);
    }

    public static Struct callItemForBattleBeanBoostStatisTypesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanBoostStatisTypesGet(),_str,_args);
    }

    public static Struct callItemForBattleBeanCancelImmuTypeGet(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanCancelImmuTypeGet(),healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff));
    }

    public static Struct callItemForBattleBeanClickFailStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanClickFailStatus(),_str,_args);
    }

    public static Struct callItemForBattleBeanClickGlobalMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanClickGlobalMove(),_str,_args);
    }

    public static Struct callItemForBattleBeanClickImmuMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanClickImmuMove(),_str,_args);
    }

    public static Struct callItemForBattleBeanClickImmuStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanClickImmuStatus(),_str,_args);
    }

    public static Struct callItemForBattleBeanClickMultStatPk(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanClickMultStatPk(),_str,_args);
    }

    public static Struct callItemForBattleBeanClickSynchroStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanClickSynchroStatus(),_str,_args);
    }

    public static Struct callItemForBattleBeanClickTeamMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanClickTeamMove(),_str,_args);
    }

    public static Struct callItemForBattleBeanClickTrapMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanClickTrapMove(),_str,_args);
    }

    public static Struct callItemForBattleBeanClickTypesPkAbility(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanClickTypesPkAbility(),_str,_args);
    }

    public static Struct callItemForBattleBeanClickWeather(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanClickWeather(),_str,_args);
    }

    public static Struct callItemForBattleBeanDamageRecoilGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanDamageRecoilGet(),_str,_args);
    }

    public static Struct callItemForBattleBeanDeterminated(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanDeterminated(),healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff));
    }

    public static Struct callItemForBattleBeanDrainedHpByDamageRateGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanDrainedHpByDamageRateGet(),_str,_args);
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

    public static Struct callItemForBattleBeanFailStatusGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanFailStatusGet(),_str,_args);
    }

    public static Struct callItemForBattleBeanGetEffectSending(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetEffectSending(),_str,_args);
    }

    public static Struct callItemForBattleBeanGetTrFailStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrFailStatus(),_str,_args);
    }

    public static Struct callItemForBattleBeanGetTrGlobalMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrGlobalMove(),_str,_args);
    }

    public static Struct callItemForBattleBeanGetTrImmuMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrImmuMove(),_str,_args);
    }

    public static Struct callItemForBattleBeanGetTrImmuStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrImmuStatus(),_str,_args);
    }

    public static Struct callItemForBattleBeanGetTrImmuTypes(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrImmuTypes(),_str,_args);
    }

    public static Struct callItemForBattleBeanGetTrMultStat(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrMultStat(),_str,_args);
    }

    public static Struct callItemForBattleBeanGetTrMultStatPk(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrMultStatPk(),_str,_args);
    }

    public static Struct callItemForBattleBeanGetTrMultStatPkRank(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrMultStatPkRank(),_str,_args);
    }

    public static Struct callItemForBattleBeanGetTrMultStatRank(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrMultStatRank(),_str,_args);
    }

    public static Struct callItemForBattleBeanGetTrMultStatisSuperEff(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrMultStatisSuperEff(),_str,_args);
    }

    public static Struct callItemForBattleBeanGetTrMultStatisTypes(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrMultStatisTypes(),_str,_args);
    }

    public static Struct callItemForBattleBeanGetTrMultStatisTypesStat(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrMultStatisTypesStat(),_str,_args);
    }

    public static Struct callItemForBattleBeanGetTrSynchroStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrSynchroStatus(),_str,_args);
    }

    public static Struct callItemForBattleBeanGetTrTeamMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrTeamMove(),_str,_args);
    }

    public static Struct callItemForBattleBeanGetTrTrapMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrTrapMove(),_str,_args);
    }

    public static Struct callItemForBattleBeanGetTrTypesPk(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrTypesPk(),_str,_args);
    }

    public static Struct callItemForBattleBeanGetTrTypesPkAbility(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrTypesPkAbility(),_str,_args);
    }

    public static Struct callItemForBattleBeanGetTrWeather(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrWeather(),_str,_args);
    }

    public static Struct callItemForBattleBeanGetTrWinEvFight(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetTrWinEvFight(),_str,_args);
    }

    public static Struct callItemForBattleBeanImmuLowStatisGet(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanImmuLowStatisGet(),healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff));
    }

    public static Struct callItemForBattleBeanImmuMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanImmuMovesGet(),_str,_args);
    }

    public static Struct callItemForBattleBeanImmuStatusGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanImmuStatusGet(),_str,_args);
    }

    public static Struct callItemForBattleBeanImmuTypesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanImmuTypesGet(),_str,_args);
    }

    public static Struct callItemForBattleBeanImmuWeatherGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanImmuWeatherGet(),_str,_args);
    }

    public static Struct callItemForBattleBeanIncreasingMaxNbRoundGlobalMoveGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanIncreasingMaxNbRoundGlobalMoveGet(),_str,_args);
    }

    public static Struct callItemForBattleBeanIncreasingMaxNbRoundTeamMoveGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanIncreasingMaxNbRoundTeamMoveGet(),_str,_args);
    }

    public static Struct callItemForBattleBeanIncreasingMaxNbRoundTrapGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanIncreasingMaxNbRoundTrapGet(),_str,_args);
    }

    public static Struct callItemForBattleBeanMapVarsFailEndRoundGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanMapVarsFailEndRoundGet(),_str,_args);
    }

    public static Struct callItemForBattleBeanMapVarsGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanMapVarsGet(),_str,_args);
    }

    public static Struct callItemForBattleBeanMultDamageGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanMultDamageGet(),_str,_args);
    }

    public static Struct callItemForBattleBeanMultDrainedHpGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanMultDrainedHpGet(),_str,_args);
    }

    public static Struct callItemForBattleBeanMultPowerGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanMultPowerGet(),_str,_args);
    }

    public static Struct callItemForBattleBeanMultStatGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanMultStatGet(),_str,_args);
    }

    public static Struct callItemForBattleBeanMultStatPokemonRankGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanMultStatPokemonRankGet(),_str,_args);
    }

    public static Struct callItemForBattleBeanMultStatRankGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanMultStatRankGet(),_str,_args);
    }

    public static Struct callItemForBattleBeanMultTrappingDamageGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanMultTrappingDamageGet(),_str,_args);
    }

    public static Struct callItemForBattleBeanMultWinningEvGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanMultWinningEvGet(),_str,_args);
    }

    public static Struct callItemForBattleBeanMultWinningExpGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanMultWinningExpGet(),_str,_args);
    }

    public static Struct callItemForBattleBeanMultWinningHappinessGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanMultWinningHappinessGet(),_str,_args);
    }

    public static Struct callItemForBattleBeanMultWinningMoneyGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanMultWinningMoneyGet(),_str,_args);
    }

    public static Struct callItemForBattleBeanProtectAgainstKoGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanProtectAgainstKoGet(),_str,_args);
    }

    public static Struct callItemForBattleBeanProtectAgainstKoIfFullHpGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanProtectAgainstKoIfFullHpGet(),_str,_args);
    }

    public static Struct callItemForBattleBeanRateForAttackFirst(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanRateForAttackFirst(),healSimple(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff));
    }

    public static Struct callItemForBattleBeanReasonsEndRoundGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanReasonsEndRoundGet(),_str,_args);
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

    public static Struct callItemForBattleBeanSynchroStatusGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanSynchroStatusGet(),_str,_args);
    }

    public static Struct callItemForBattleBeanTypesPkAbilitiesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanTypesPkAbilitiesGet(),_str,_args);
    }

    public static Struct callItemForBattleBeanTypesPkGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanTypesPkGet(),_str,_args);
    }

    public static Struct callItemForBattleBeanWinEvFightGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanWinEvFightGet(),_str,_args);
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
        _facade.getData().completeMembers(M_DAM, rep_);
        _facade.getData().completeMembers(A_ABILITY, abilityPlate(true));
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
        return e_;
    }

    protected static EffectEndRound effEndRound() {
        EffectEndRound e_ = lawEndRound();
        e_.setFailEndRound(DataBase.VAR_PREFIX+Fight.TEMPS_TOUR);
        e_.setEndRoundRank(1);
        return e_;
    }
}
