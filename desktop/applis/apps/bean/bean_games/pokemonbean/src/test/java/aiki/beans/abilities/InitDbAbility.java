package aiki.beans.abilities;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.PkData;
import aiki.beans.effects.AikiBeansEffectsStd;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.abilities.AbilityData;
import aiki.fight.effects.EffectWhileSendingWithStatistic;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.effects.EffectEndRound;
import aiki.fight.moves.effects.EffectEndRoundSingleRelation;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.game.fight.Fight;
import aiki.instances.Instances;
import code.expressionlanguage.structs.Struct;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.StringList;
import code.util.StringMap;

public abstract class InitDbAbility extends InitDbAbilities {

    public static Struct callAbilityBeanAchievedDisappearedPkGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanAchievedDisappearedPkGet(),_str,_args);
    }

    public static Struct callAbilityBeanBonusStatRankGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanBonusStatRankGet(),_str,_args);
    }

    public static Struct callAbilityBeanBoostStatRankEndRoundGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanBoostStatRankEndRoundGet(),_str,_args);
    }

    public static Struct callAbilityBeanBoostStatRankProtectedGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanBoostStatRankProtectedGet(),_str,_args);
    }

    public static Struct callAbilityBeanBreakFoeImmuneGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanBreakFoeImmuneGet(),_str,_args);
    }

    public static Struct callAbilityBeanBreakProtectionGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanBreakProtectionGet(),_str,_args);
    }

    public static Struct callAbilityBeanBreakProtectionMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanBreakProtectionMovesGet(),_str,_args);
    }

    public static Struct callAbilityBeanCancelSecEffectOtherGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanCancelSecEffectOtherGet(),_str,_args);
    }

    public static Struct callAbilityBeanCancelSecEffectOwnerGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanCancelSecEffectOwnerGet(),_str,_args);
    }

    public static Struct callAbilityBeanChangingBoostTypesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanChangingBoostTypesGet(),_str,_args);
    }

    public static Struct callAbilityBeanChgtTypeByDamageGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanChgtTypeByDamageGet(),_str,_args);
    }

    public static Struct callAbilityBeanChgtTypeByWeatherGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanChgtTypeByWeatherGet(),_str,_args);
    }

    public static Struct callAbilityBeanClickBreakProtectionMoves(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanClickBreakProtectionMoves(),_str,_args);
    }

    public static Struct callAbilityBeanClickChgtTypeByWeatherKey(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanClickChgtTypeByWeatherKey(),_str,_args);
    }

    public static Struct callAbilityBeanClickDivideStatusRoundKey(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanClickDivideStatusRoundKey(),_str,_args);
    }

    public static Struct callAbilityBeanClickFailStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanClickFailStatus(),_str,_args);
    }

    public static Struct callAbilityBeanClickForwardStatusKey(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanClickForwardStatusKey(),_str,_args);
    }

    public static Struct callAbilityBeanClickForwardStatusValue(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanClickForwardStatusValue(),_str,_args);
    }

    public static Struct callAbilityBeanClickHealHpByTypeIfWeatherKey(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanClickHealHpByTypeIfWeatherKey(),_str,_args);
    }

    public static Struct callAbilityBeanClickHealHpByWeatherKey(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanClickHealHpByWeatherKey(),_str,_args);
    }

    public static Struct callAbilityBeanClickIgnAbility(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanClickIgnAbility(),_str,_args);
    }

    public static Struct callAbilityBeanClickIgnFoeTeamMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanClickIgnFoeTeamMove(),_str,_args);
    }

    public static Struct callAbilityBeanClickImmuAbility(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanClickImmuAbility(),_str,_args);
    }

    public static Struct callAbilityBeanClickImmuAllyFromMoves(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanClickImmuAllyFromMoves(),_str,_args);
    }

    public static Struct callAbilityBeanClickImmuLowStatIfStatusKey(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanClickImmuLowStatIfStatusKey(),_str,_args);
    }

    public static Struct callAbilityBeanClickImmuMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanClickImmuMove(),_str,_args);
    }

    public static Struct callAbilityBeanClickImmuMoveByWeather(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanClickImmuMoveByWeather(),_str,_args);
    }

    public static Struct callAbilityBeanClickImmuRechargeRoundMoves(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanClickImmuRechargeRoundMoves(),_str,_args);
    }

    public static Struct callAbilityBeanClickImmuStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanClickImmuStatus(),_str,_args);
    }

    public static Struct callAbilityBeanClickImmuStatusBeginRound(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanClickImmuStatusBeginRound(),_str,_args);
    }

    public static Struct callAbilityBeanClickImmuStatusTypes(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanClickImmuStatusTypes(),_str,_args);
    }

    public static Struct callAbilityBeanClickImmuStatusWeather(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanClickImmuStatusWeather(),_str,_args);
    }

    public static Struct callAbilityBeanClickIndex(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanClickIndex(),_str,_args);
    }

    public static Struct callAbilityBeanClickMultStatIfStatutRankSec(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanClickMultStatIfStatutRankSec(),_str,_args);
    }

    public static Struct callAbilityBeanClickPokemon(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanClickPokemon(),_str,_args);
    }

    public static String callAbilityBeanClickReversePowerTypesAbilities() {
        return callAbilityBeanClickReversePowerTypesAbilities(reverseCase());
    }

    public static String callAbilityBeanClickReversePowerTypesAbilities(Struct _str) {
        return navigateData(new AbilityBeanClickReversePowerTypesAbilities(),_str,0);
    }

    public static String callAbilityBeanClickReversePowerTypesAbilitiesId() {
        Struct bean_ = reverseCase();
        callAbilityBeanClickReversePowerTypesAbilities(bean_);
        return getValAbilityId(bean_);
    }

    public static Struct callAbilityBeanClickSingleStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanClickSingleStatus(),_str,_args);
    }

    public static Struct callAbilityBeanClickWeather(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanClickWeather(),_str,_args);
    }

    public static Struct callAbilityBeanCopyMovesTypesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanCopyMovesTypesGet(),_str,_args);
    }

    public static Struct callAbilityBeanDecreaseNecStepsHatchGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanDecreaseNecStepsHatchGet(),directCase());
    }

    public static Struct callAbilityBeanDecreaseNecStepsHatchInt(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanDecreaseNecStepsHatchInt(),_str,_args);
    }

    public static Struct callAbilityBeanDefEffGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanDefEffGet(),_str,_args);
    }

    public static Struct callAbilityBeanDisplayNameGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanDisplayNameGet(),directCase());
    }

    public static Struct callAbilityBeanDivideStatusRoundGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanDivideStatusRoundGet(),_str,_args);
    }

    public static Struct callAbilityBeanEffectSendBeanGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanEffectSendBeanGet(),_str,_args);
    }

    public static Struct callAbilityBeanEndRoundGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanEndRoundGet(),directCaseEndRound());
    }

    public static Struct callAbilityBeanEndRoundRankGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanEndRoundRankGet(),directCaseEndRound());
    }

    public static Struct callAbilityBeanEndRoundGetNo() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanEndRoundGet(),directCase());
    }

    public static Struct callAbilityBeanEndRoundRankGetNo() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanEndRoundRankGet(),directCase());
    }

    public static Struct callAbilityBeanFailStatusGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanFailStatusGet(),_str,_args);
    }

    public static Struct callAbilityBeanForbidUseBerryAgainstFoesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanForbidUseBerryAgainstFoesGet(),_str,_args);
    }

    public static Struct callAbilityBeanForwardStatusGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanForwardStatusGet(),_str,_args);
    }

    public static Struct callAbilityBeanGetEffectSending(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetEffectSending(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrBonusStatRank(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrBonusStatRank(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrBoostStatRankEndRound(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrBoostStatRankEndRound(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrBoostStatRankProtected(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrBoostStatRankProtected(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrBreakFoeImmuneKey(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrBreakFoeImmuneKey(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrBreakFoeImmuneValue(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrBreakFoeImmuneValue(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrBreakProtectionMoves(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrBreakProtectionMoves(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrChangingBoostTypesNew(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrChangingBoostTypesNew(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrChangingBoostTypesOld(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrChangingBoostTypesOld(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrChgtTypeByWeatherKey(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrChgtTypeByWeatherKey(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrChgtTypeByWeatherValue(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrChgtTypeByWeatherValue(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrDivideStatusRoundKey(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrDivideStatusRoundKey(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrFailStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrFailStatus(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrForwardStatusKey(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrForwardStatusKey(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrForwardStatusValue(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrForwardStatusValue(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrHealHpByTypeIfWeatherKey(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrHealHpByTypeIfWeatherKey(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrHealHpByTypeIfWeatherKeySec(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrHealHpByTypeIfWeatherKeySec(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrHealHpByWeatherKey(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrHealHpByWeatherKey(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrIgnAbility(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrIgnAbility(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrIgnFoeTeamMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrIgnFoeTeamMove(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrImmuAbility(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuAbility(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrImmuAllyFromMoves(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuAllyFromMoves(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrImmuLowStat(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuLowStat(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrImmuLowStatIfStatusKey(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuLowStatIfStatusKey(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrImmuLowStatIfStatusValue(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuLowStatIfStatusValue(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrImmuLowStatisTypes(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuLowStatisTypes(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrImmuLowStatisValue(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuLowStatisValue(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrImmuMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuMove(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrImmuMoveByWeather(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuMoveByWeather(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrImmuRechargeRoundMoves(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuRechargeRoundMoves(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrImmuStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuStatus(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrImmuStatusBeginRound(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuStatusBeginRound(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrImmuStatusTypes(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuStatusTypes(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrImmuStatusValue(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuStatusValue(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrImmuStatusWeather(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuStatusWeather(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrImmuTypeByWeather(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuTypeByWeather(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrIncreasedPrio(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrIncreasedPrio(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrIncreasedPrioTypes(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrIncreasedPrioTypes(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrLowStatFoeHit(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrLowStatFoeHit(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrMaxStatisticsIfCh(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMaxStatisticsIfCh(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrMultDamageFoe(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultDamageFoe(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrMultPowerMovesTypesGlobalKey() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultPowerMovesTypesGlobalKey(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrMultStat(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStat(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrMultStatAlly(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStatAlly(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrMultStatIfCatKey(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStatIfCatKey(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrMultStatIfCatKeySec(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStatIfCatKeySec(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrMultStatIfDamageCatKey(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStatIfDamageCatKey(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrMultStatIfDamageCatKeySec(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStatIfDamageCatKeySec(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrMultStatIfDamgeType(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStatIfDamgeType(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrMultStatIfDamgeTypeSec(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStatIfDamgeTypeSec(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrMultStatIfKoFoe(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStatIfKoFoe(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrMultStatIfLowStat(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStatIfLowStat(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrMultStatIfStatutRank(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStatIfStatutRank(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrMultStatIfStatutRankSec(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStatIfStatutRankSec(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrPokemon(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrPokemon(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrReversePowerTypesAbilities() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrReversePowerTypesAbilities(),reverseCase(),0);
    }

    public static Struct callAbilityBeanGetTrSingleStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrSingleStatus(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrWeather(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrWeather(),_str,_args);
    }

    public static Struct callAbilityBeanGiveItemToAllyHavingUsedGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGiveItemToAllyHavingUsedGet(),_str,_args);
    }

    public static Struct callAbilityBeanHealHpByTypeIfWeatherGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanHealHpByTypeIfWeatherGet(),_str,_args);
    }

    public static Struct callAbilityBeanHealHpByWeatherGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanHealHpByWeatherGet(),_str,_args);
    }

    public static Struct callAbilityBeanHealHpWhileUsingBerryGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanHealHpWhileUsingBerryGet(),_str,_args);
    }

    public static Struct callAbilityBeanHealedHpRateBySwitchGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanHealedHpRateBySwitchGet(),_str,_args);
    }

    public static Struct callAbilityBeanHealedStatusBySwitchGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanHealedStatusBySwitchGet(),_str,_args);
    }

    public static Struct callAbilityBeanIgnAbilityGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanIgnAbilityGet(),_str,_args);
    }

    public static Struct callAbilityBeanIgnFoeStatisBoostGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanIgnFoeStatisBoostGet(),_str,_args);
    }

    public static Struct callAbilityBeanIgnFoeTeamMoveGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanIgnFoeTeamMoveGet(),_str,_args);
    }

    public static Struct callAbilityBeanImmuAbilityGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuAbilityGet(),_str,_args);
    }

    public static Struct callAbilityBeanImmuAllyFromMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuAllyFromMovesGet(),_str,_args);
    }

    public static Struct callAbilityBeanImmuChGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuChGet(),_str,_args);
    }

    public static Struct callAbilityBeanImmuDamageAllyMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuDamageAllyMovesGet(),_str,_args);
    }

    public static Struct callAbilityBeanImmuDamageRecoilGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuDamageRecoilGet(),_str,_args);
    }

    public static Struct callAbilityBeanImmuDamageTrappingMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuDamageTrappingMovesGet(),_str,_args);
    }

    public static Struct callAbilityBeanImmuLowStatGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuLowStatGet(),_str,_args);
    }

    public static Struct callAbilityBeanImmuLowStatIfStatusGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuLowStatIfStatusGet(),_str,_args);
    }

    public static Struct callAbilityBeanImmuLowStatisTypesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuLowStatisTypesGet(),_str,_args);
    }

    public static Struct callAbilityBeanImmuMoveGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuMoveGet(),_str,_args);
    }

    public static Struct callAbilityBeanImmuMoveTypesByWeatherGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuMoveTypesByWeatherGet(),_str,_args);
    }

    public static Struct callAbilityBeanImmuRechargeRoundGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuRechargeRoundGet(),_str,_args);
    }

    public static Struct callAbilityBeanImmuRechargeRoundMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuRechargeRoundMovesGet(),_str,_args);
    }

    public static Struct callAbilityBeanImmuStatusBeginRoundGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuStatusBeginRoundGet(),_str,_args);
    }

    public static Struct callAbilityBeanImmuStatusGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuStatusGet(),_str,_args);
    }

    public static Struct callAbilityBeanImmuStatusTypesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuStatusTypesGet(),_str,_args);
    }

    public static Struct callAbilityBeanImmuSufferedDamageLowEffGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuSufferedDamageLowEffGet(),_str,_args);
    }

    public static Struct callAbilityBeanImmuWeatherGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuWeatherGet(),_str,_args);
    }

    public static Struct callAbilityBeanIncreasedPrioGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanIncreasedPrioGet(),_str,_args);
    }

    public static Struct callAbilityBeanIncreasedPrioTypesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanIncreasedPrioTypesGet(),_str,_args);
    }

    public static Struct callAbilityBeanInflictingDamageInsteadOfSufferingGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanInflictingDamageInsteadOfSufferingGet(),_str,_args);
    }

    public static Struct callAbilityBeanIsChgtTypeByWeather(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanIsChgtTypeByWeather(),_str,_args);
    }

    public static Struct callAbilityBeanIsHealHpByTypeIfWeather(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanIsHealHpByTypeIfWeather(),_str,_args);
    }

    public static Struct callAbilityBeanIsHealHpByWeather(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanIsHealHpByWeather(),_str,_args);
    }

    public static Struct callAbilityBeanIsMoveByStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanIsMoveByStatus(),_str,_args);
    }

    public static Struct callAbilityBeanIsMoveByWeather(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanIsMoveByWeather(),_str,_args);
    }

    public static Struct callAbilityBeanIsStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanIsStatus(),_str,_args);
    }

    public static Struct callAbilityBeanLowStatFoeHitGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanLowStatFoeHitGet(),_str,_args);
    }

    public static Struct callAbilityBeanMapVarsFailEndRoundGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMapVarsFailEndRoundGet(),directCaseEndRound());
    }

    public static Struct callAbilityBeanMapVarsGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMapVarsGet(),_str,_args);
    }

    public static Struct callAbilityBeanMaxHpForUsingBerryGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMaxHpForUsingBerryGet(),_str,_args);
    }

    public static Struct callAbilityBeanMaxStatisticsIfChGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMaxStatisticsIfChGet(),_str,_args);
    }

    public static Struct callAbilityBeanMultAllyDamageGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultAllyDamageGet(),_str,_args);
    }

    public static Struct callAbilityBeanMultDamageChGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultDamageChGet(),_str,_args);
    }

    public static Struct callAbilityBeanMultDamageFoeGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultDamageFoeGet(),_str,_args);
    }

    public static Struct callAbilityBeanMultDamageGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultDamageGet(),_str,_args);
    }

    public static Struct callAbilityBeanMultEvtRateChGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultEvtRateChGet(),_str,_args);
    }

    public static Struct callAbilityBeanMultEvtRateSecEffectOwnerGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultEvtRateSecEffectOwnerGet(),_str,_args);
    }

    public static Struct callAbilityBeanMultPowerGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultPowerGet(),_str,_args);
    }

    public static Struct callAbilityBeanMultPowerMovesTypesGlobalGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultPowerMovesTypesGlobalGet(),directCase());
    }

    public static Struct callAbilityBeanMultStabGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultStabGet(),_str,_args);
    }

    public static Struct callAbilityBeanMultStatAllyGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultStatAllyGet(),_str,_args);
    }

    public static Struct callAbilityBeanMultStatGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultStatGet(),_str,_args);
    }

    public static Struct callAbilityBeanMultStatIfCatGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultStatIfCatGet(),_str,_args);
    }

    public static Struct callAbilityBeanMultStatIfDamageCatGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultStatIfDamageCatGet(),_str,_args);
    }

    public static Struct callAbilityBeanMultStatIfDamgeTypeGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultStatIfDamgeTypeGet(),_str,_args);
    }

    public static Struct callAbilityBeanMultStatIfKoFoeGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultStatIfKoFoeGet(),_str,_args);
    }

    public static Struct callAbilityBeanMultStatIfLowStatGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultStatIfLowStatGet(),_str,_args);
    }

    public static Struct callAbilityBeanMultStatIfStatutRankGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultStatIfStatutRankGet(),_str,_args);
    }

    public static Struct callAbilityBeanMultSufferedDamageSuperEffGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultSufferedDamageSuperEffGet(),_str,_args);
    }

    public static Struct callAbilityBeanMultVarBoostGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultVarBoostGet(),_str,_args);
    }

    public static Struct callAbilityBeanMumyGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMumyGet(),_str,_args);
    }

    public static Struct callAbilityBeanNbHitsGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanNbHitsGet(),_str,_args);
    }

    public static Struct callAbilityBeanNbUsedPpGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanNbUsedPpGet(),directCase());
    }

    public static Struct callAbilityBeanNbUsedPpInt(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanNbUsedPpInt(),_str,_args);
    }

    public static Struct callAbilityBeanPlateGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanPlateGet(),_str,_args);
    }

    public static Struct callAbilityBeanPokemonGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanPokemonGet(),_str,_args);
    }

    public static Struct callAbilityBeanReasonsEndRoundGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanReasonsEndRoundGet(),directCaseEndRound());
    }

    public static Struct callAbilityBeanRecoilDamageFoeByKoOwnerGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanRecoilDamageFoeByKoOwnerGet(),_str,_args);
    }

    public static Struct callAbilityBeanRecoilDamageFoeGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanRecoilDamageFoeGet(),_str,_args);
    }

    public static Struct callAbilityBeanReverseEffectsPowerMovesTypesGlobalAbilitiesGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanReverseEffectsPowerMovesTypesGlobalAbilitiesGet(),reverseCase());
    }

    public static Struct callAbilityBeanReverseEffectsPowerMovesTypesGlobalGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanReverseEffectsPowerMovesTypesGlobalGet(),_str,_args);
    }

    public static Struct callAbilityBeanSendingNoGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanSendingGet(),directCase());
    }

    public static Struct callAbilityBeanSendingGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanSendingGet(),abNoStatSend());
    }

    public static Struct callAbilityBeanSingleStatusGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanSingleStatusGet(),_str,_args);
    }

    public static Struct callAbilityBeanSlowingGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanSlowingGet(),_str,_args);
    }

    public static Struct callAbilityBeanTakeItemByDamagingMoveGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanTakeItemByDamagingMoveGet(),_str,_args);
    }

    public static Struct callAbilityBeanTypeForMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanTypeForMovesGet(),_str,_args);
    }



    public static StringMap<Struct> beanToItBaseSend(PkData _pk) {
        StringMap<Struct> map_ = beanToAbility(_pk);
        map_.addEntry(AikiBeansEffectsStd.EFFECT_SENDING,_pk.beanEffectWhileSendingBean(EN));
        return map_;
    }

//    protected static Struct healSimpleEndRound() {
//        return healSimpleEndRound(true,true,true,true,true,true,LgInt.one(),LgInt.one());
//    }
//
//    protected static Struct healSimple() {
//        return healSimple(true,true,true,true,true,true,LgInt.one(),LgInt.one());
//    }

    protected static Struct reverseCase() {
        PkData pk_ = pkDataByFacade(feedDbAbilityReverse());
        StringMap<Struct> all_ = beanToAbility(pk_);
        return transitToAllAbilities(pk_,all_,0);
    }

    protected static Struct directCase() {
        PkData pk_ = pkDataByFacade(feedDbAbility());
        StringMap<Struct> all_ = beanToAbility(pk_);
        return transitToAllAbilities(pk_,all_,0);
    }
    protected static Struct directCase(boolean _forbidUseBerryAgainstFoes, boolean _chgtTypeByDamage, boolean _ignFoeStatisBoost, boolean _immuCh, boolean _immuDamageTrappingMoves, boolean _immuDamageAllyMoves, boolean _immuDamageRecoil, boolean _copyMovesTypes, boolean _reverseEffectsPowerMovesTypesGlobal, boolean _takeItemByDamagingMove, boolean _giveItemToAllyHavingUsed, boolean _inflictingDamageInsteadOfSuffering, boolean _nbHits, boolean _breakProtection, boolean _plate, boolean _healedStatusBySwitch, boolean _achievedDisappearedPk, boolean _mumy, boolean _immuRechargeRound, boolean _slowing, boolean _immuSufferedDamageLowEff, boolean _cancelSecEffectOther, boolean _cancelSecEffectOwner, int _nbUsedPp, int _decreaseNecStepsHatch, String _typeForMoves) {
        PkData pk_ = pkDataByFacade(feedDbAbility(_forbidUseBerryAgainstFoes, _chgtTypeByDamage, _ignFoeStatisBoost, _immuCh, _immuDamageTrappingMoves, _immuDamageAllyMoves, _immuDamageRecoil, _copyMovesTypes, _reverseEffectsPowerMovesTypesGlobal, _takeItemByDamagingMove, _giveItemToAllyHavingUsed, _inflictingDamageInsteadOfSuffering, _nbHits, _breakProtection, _plate, _healedStatusBySwitch, _achievedDisappearedPk, _mumy, _immuRechargeRound, _slowing, _immuSufferedDamageLowEff, _cancelSecEffectOther, _cancelSecEffectOwner, _nbUsedPp, _decreaseNecStepsHatch, _typeForMoves));
        StringMap<Struct> all_ = beanToAbility(pk_);
        return transitToAllAbilities(pk_,all_,0);
    }

    protected static Struct directCaseEndRound() {
        PkData pk_ = pkDataByFacade(feedDbAbilityEndRound());
        StringMap<Struct> all_ = beanToAbility(pk_);
        return transitToAllAbilities(pk_,all_,0);
    }
//
//    protected static Struct healSimpleNoStat(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff, boolean _copyingAbility, boolean _disableWeather) {
//        PkData pk_ = pkDataByFacade(feedDbItemNoStat(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff, _copyingAbility, _disableWeather));
//        StringMap<Struct> all_ = beanToItBaseSend(pk_);
//        Struct res_ = dispLine(AikiBeansItemsStd.BEAN_ITEMFORBATTLE, pk_, all_);
//        callItemForBattleBeanEffectSendBeanGet(res_);
//        Struct send_ = all_.getVal(AikiBeansEffectsStd.EFFECT_SENDING);
//        callEffectWhileSendingBeanEffectSet(send_,callItemForBattleBeanGetEffectSending(res_));
//        beforeDisplaying(send_);
//        return res_;
//    }
//
//    public static Struct healSimpleNoStatSend(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
//        return healSimpleNoStatSend(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff,true,true);
//    }
    public static Struct healSimpleNoStatSend() {
        PkData pk_ = pkDataByFacade(feedDbAbilityNoStat());
        StringMap<Struct> all_ = beanToItBaseSend(pk_);
        Struct res_ = transitToAllAbilities(pk_, all_,0);
        callAbilityBeanEffectSendBeanGet(res_);
        Struct send_ = all_.getVal(AikiBeansEffectsStd.EFFECT_SENDING);
        callEffectWhileSendingBeanEffectSet(send_,callAbilityBeanGetEffectSending(res_));
        beforeDisplaying(send_);
        return send_;
    }
    public static Struct abNoStatSend() {
        PkData pk_ = pkDataByFacade(feedDbAbilityNoStat());
        StringMap<Struct> all_ = beanToItBaseSend(pk_);
        return transitToAllAbilities(pk_, all_,0);
    }
//
//    public static Struct healSimpleStatSend(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
//        PkData pk_ = pkDataByFacade(feedDbItemStat(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff));
//        StringMap<Struct> all_ = beanToItBaseSend(pk_);
//        Struct res_ = dispLine(AikiBeansItemsStd.BEAN_ITEMFORBATTLE, pk_, all_);
//        callItemForBattleBeanEffectSendBeanGet(res_);
//        Struct send_ = all_.getVal(AikiBeansEffectsStd.EFFECT_SENDING);
//        callEffectWhileSendingBeanEffectSet(send_,callItemForBattleBeanGetEffectSending(res_));
//        beforeDisplaying(send_);
//        return send_;
//    }
//
//    protected static Struct healSimpleStat(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
//        PkData pk_ = pkDataByFacade(feedDbItemStat(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff));
//        StringMap<Struct> all_ = beanToItBaseSend(pk_);
//        Struct res_ = dispLine(AikiBeansItemsStd.BEAN_ITEMFORBATTLE, pk_, all_);
//        callItemForBattleBeanEffectSendBeanGet(res_);
//        Struct send_ = all_.getVal(AikiBeansEffectsStd.EFFECT_SENDING);
//        callEffectWhileSendingBeanEffectSet(send_,callItemForBattleBeanGetEffectSending(res_));
//        beforeDisplaying(send_);
//        return res_;
//    }

//    protected static FacadeGame feedDbItem(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
//        FacadeGame facade_ = facade();
//        facade_.getData().completeMembers(I_BASE,itemForBattle(_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff));
//        otherElts(facade_);
//        facade_.getData().completeVariables();
//        return facade_;
//    }
//
//    protected static FacadeGame feedDbItemStat(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
//        return feedDbAbility(eff(),_againstEvo, _attackLast, _attacksSoon, _boostExp, _cancelImmuType, _immuLowStatis, _trueEff, _falseEff, true, true);
//    }
//
    protected static FacadeGame feedDbAbilityNoStat() {
        return feedDbAbilityStat();
    }
//
    protected static FacadeGame feedDbAbilityEndRound() {
        return feedDbAbility(effEndRound());
    }
//
    protected static FacadeGame feedDbAbilityStat() {
        FacadeGame facade_ = facade();
        AbilityData it_ = ability();
        EffectWhileSendingWithStatistic e_ = Instances.newEffectWhileSendingWithStatistic();
        e_.setEffect(null);
        e_.setMultWeight(Rate.one());
        it_.getEffectSending().add(e_);
        facade_.getData().completeMembers(A_ABILITY, it_);
        return common(true, A_ABILITY, facade_);
    }
//
    protected static FacadeGame feedDbAbility(EffectEndRound _eff) {
        FacadeGame facade_ = facade();
        AbilityData it_ = ability();
        it_.getEffectEndRound().add(_eff);
        facade_.getData().completeMembers(A_ABILITY, it_);
        return common(true, A_ABILITY, facade_);
    }


    protected static FacadeGame feedDbAbility() {
        return feedDbAbility(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,"");
    }

    protected static FacadeGame feedDbAbility(boolean _rech, String _ab) {
        return feedDbAbility(_rech,_ab,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,"");
    }

    protected static FacadeGame feedDbAbility(boolean _forbidUseBerryAgainstFoes, boolean _chgtTypeByDamage, boolean _ignFoeStatisBoost, boolean _immuCh, boolean _immuDamageTrappingMoves, boolean _immuDamageAllyMoves, boolean _immuDamageRecoil, boolean _copyMovesTypes, boolean _reverseEffectsPowerMovesTypesGlobal, boolean _takeItemByDamagingMove, boolean _giveItemToAllyHavingUsed, boolean _inflictingDamageInsteadOfSuffering, boolean _nbHits, boolean _breakProtection, boolean _plate, boolean _healedStatusBySwitch, boolean _achievedDisappearedPk, boolean _mumy, boolean _immuRechargeRound, boolean _slowing, boolean _immuSufferedDamageLowEff, boolean _cancelSecEffectOther, boolean _cancelSecEffectOwner, int _nbUsedPp, int _decreaseNecStepsHatch, String _typeForMoves) {
        return feedDbAbility(true,A_ABILITY,_forbidUseBerryAgainstFoes, _chgtTypeByDamage, _ignFoeStatisBoost, _immuCh, _immuDamageTrappingMoves, _immuDamageAllyMoves, _immuDamageRecoil, _copyMovesTypes, _reverseEffectsPowerMovesTypesGlobal, _takeItemByDamagingMove, _giveItemToAllyHavingUsed, _inflictingDamageInsteadOfSuffering, _nbHits, _breakProtection, _plate, _healedStatusBySwitch, _achievedDisappearedPk, _mumy, _immuRechargeRound, _slowing, _immuSufferedDamageLowEff, _cancelSecEffectOther, _cancelSecEffectOwner, _nbUsedPp, _decreaseNecStepsHatch, _typeForMoves);
    }
    protected static FacadeGame feedDbAbility(boolean _rech, String _ab,boolean _forbidUseBerryAgainstFoes, boolean _chgtTypeByDamage, boolean _ignFoeStatisBoost, boolean _immuCh, boolean _immuDamageTrappingMoves, boolean _immuDamageAllyMoves, boolean _immuDamageRecoil, boolean _copyMovesTypes, boolean _reverseEffectsPowerMovesTypesGlobal, boolean _takeItemByDamagingMove, boolean _giveItemToAllyHavingUsed, boolean _inflictingDamageInsteadOfSuffering, boolean _nbHits, boolean _breakProtection, boolean _plate, boolean _healedStatusBySwitch, boolean _achievedDisappearedPk, boolean _mumy, boolean _immuRechargeRound, boolean _slowing, boolean _immuSufferedDamageLowEff, boolean _cancelSecEffectOther, boolean _cancelSecEffectOwner, int _nbUsedPp, int _decreaseNecStepsHatch, String _typeForMoves) {
        FacadeGame facade_ = facade();
        facade_.getData().completeMembers(A_ABILITY, ability(_forbidUseBerryAgainstFoes, _chgtTypeByDamage, _ignFoeStatisBoost, _immuCh, _immuDamageTrappingMoves, _immuDamageAllyMoves, _immuDamageRecoil, _copyMovesTypes, _reverseEffectsPowerMovesTypesGlobal, _takeItemByDamagingMove, _giveItemToAllyHavingUsed, _inflictingDamageInsteadOfSuffering, _nbHits, _breakProtection, _plate, _healedStatusBySwitch, _achievedDisappearedPk, _mumy, _immuRechargeRound, _slowing, _immuSufferedDamageLowEff, _cancelSecEffectOther, _cancelSecEffectOwner, _nbUsedPp, _decreaseNecStepsHatch, _typeForMoves));
        AbilityData ab_ = ability();
        ab_.setMultPowerMovesTypesGlobal(new StringMap<Rate>());
        facade_.getData().completeMembers(A_ABILITY2, ab_);
        return common(_rech, _ab, facade_);
    }
    protected static FacadeGame feedDbAbilityReverse() {
        FacadeGame facade_ = facade();
        AbilityData d_ = ability();
        d_.setMultPowerMovesTypesGlobal(new StringMap<Rate>());
        facade_.getData().completeMembers(A_ABILITY, d_);
        AbilityData ab_ = ability();
        facade_.getData().completeMembers(A_ABILITY2, ab_);
        return common(true, A_ABILITY, facade_);
    }

    private static FacadeGame common(boolean _rech, String _ab, FacadeGame _facade) {
        _facade.getData().completeMembers(S_STA_REL,staRel(""));
        _facade.getData().completeMembers(S_STA_SIM,staSimple(""));
        DamagingMoveData rech_ = moveDam(TargetChoice.ANY_FOE);
        rech_.setRechargeRound(_rech);
        _facade.getData().completeMembers(M_DAM, rech_);
        PokemonData pk_ = pk(new StringList("__"), GenderRepartition.NO_GENDER);
        pk_.setAbilities(new StringList(_ab));
        _facade.getData().completeMembers(P_POKEMON, pk_);
        trsCore(_facade);
        _facade.getData().completeVariables();
        return _facade;
    }

    protected static EffectEndRound effEndRound() {
        EffectEndRound e_ = lawEndRound();
        e_.setFailEndRound(DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR);
        e_.setEndRoundRank(1);
        return e_;
    }

    private static EffectEndRoundSingleRelation lawEndRound() {
        EffectEndRoundSingleRelation e_ = Instances.newEffectEndRoundSingleRelation();
        e_.getLawForEnablingEffect().addQuickEvent(Rate.one(), LgInt.one());
        e_.getLawForEnablingEffect().addQuickEvent(Rate.newRate("2"), LgInt.newLgInt("3"));
        return e_;
    }
}
