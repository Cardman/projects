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
import aiki.fight.moves.effects.EffectProtection;
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

    public static Struct callAbilityBeanBonusStatRankGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanBonusStatRankGet(),directCase());
    }

    public static Struct callAbilityBeanBoostStatRankEndRoundGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanBoostStatRankEndRoundGet(),directCase());
    }

    public static Struct callAbilityBeanBoostStatRankProtectedGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanBoostStatRankProtectedGet(),directCase());
    }

    public static Struct callAbilityBeanBreakFoeImmuneGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanBreakFoeImmuneGet(),directCase());
    }

    public static Struct callAbilityBeanBreakProtectionGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanBreakProtectionGet(),_str,_args);
    }

    public static Struct callAbilityBeanBreakProtectionMovesGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanBreakProtectionMovesGet(),directCase());
    }

    public static Struct callAbilityBeanCancelSecEffectOtherGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanCancelSecEffectOtherGet(),_str,_args);
    }

    public static Struct callAbilityBeanCancelSecEffectOwnerGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanCancelSecEffectOwnerGet(),_str,_args);
    }

    public static Struct callAbilityBeanChangingBoostTypesGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanChangingBoostTypesGet(),directCase());
    }

    public static Struct callAbilityBeanChgtTypeByDamageGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanChgtTypeByDamageGet(),_str,_args);
    }

    public static Struct callAbilityBeanChgtTypeByWeatherGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanChgtTypeByWeatherGet(),directCase());
    }

    public static String callAbilityBeanClickBreakProtectionMoves() {
        return callAbilityBeanClickBreakProtectionMoves(directCase());
    }

    public static String callAbilityBeanClickBreakProtectionMoves(Struct _str) {
        return navigateData(new AbilityBeanClickBreakProtectionMoves(),_str,0);
    }

    public static String callAbilityBeanClickBreakProtectionMovesId() {
        Struct b_ = directCase();
        callAbilityBeanClickBreakProtectionMoves(b_);
        return getValMoveId(b_);
    }
    public static String callAbilityBeanClickChgtTypeByWeatherKey() {
        return callAbilityBeanClickChgtTypeByWeatherKey(directCase());
    }
    public static String callAbilityBeanClickChgtTypeByWeatherKey(Struct _str) {
        return navigateData(new AbilityBeanClickChgtTypeByWeatherKey(),_str,0);
    }

    public static String callAbilityBeanClickChgtTypeByWeatherKeyId() {
        Struct b_ = directCase();
        callAbilityBeanClickChgtTypeByWeatherKey(b_);
        return getValMoveId(b_);
    }
    public static String callAbilityBeanClickDivideStatusRoundKey() {
        return callAbilityBeanClickDivideStatusRoundKey(directCase());
    }
    public static String callAbilityBeanClickDivideStatusRoundKey(Struct _str) {
        return navigateData(new AbilityBeanClickDivideStatusRoundKey(),_str,0);
    }

    public static String callAbilityBeanClickDivideStatusRoundKeyId() {
        Struct b_ = directCase();
        callAbilityBeanClickDivideStatusRoundKey(b_);
        return getValStatusId(b_);
    }
    public static String callAbilityBeanClickFailStatus() {
        return callAbilityBeanClickFailStatus(directCase());
    }
    public static String callAbilityBeanClickFailStatus(Struct _str) {
        return navigateData(new AbilityBeanClickFailStatus(),_str,0);
    }

    public static String callAbilityBeanClickFailStatusId() {
        Struct b_ = directCase();
        callAbilityBeanClickFailStatus(b_);
        return getValStatusId(b_);
    }
    public static String callAbilityBeanClickForwardStatusKey() {
        return callAbilityBeanClickForwardStatusKey(directCase());
    }
    public static String callAbilityBeanClickForwardStatusKey(Struct _str) {
        return navigateData(new AbilityBeanClickForwardStatusKey(),_str,0);
    }

    public static String callAbilityBeanClickForwardStatusKeyId() {
        Struct b_ = directCase();
        callAbilityBeanClickForwardStatusKey(b_);
        return getValStatusId(b_);
    }
    public static String callAbilityBeanClickForwardStatusValue() {
        return callAbilityBeanClickForwardStatusValue(directCase());
    }
    public static String callAbilityBeanClickForwardStatusValue(Struct _str) {
        return navigateData(new AbilityBeanClickForwardStatusValue(),_str,0);
    }

    public static String callAbilityBeanClickForwardStatusValueId() {
        Struct b_ = directCase();
        callAbilityBeanClickForwardStatusValue(b_);
        return getValStatusId(b_);
    }
    public static String callAbilityBeanClickHealHpByTypeIfWeatherKey() {
        return callAbilityBeanClickHealHpByTypeIfWeatherKey(directCase());
    }
    public static String callAbilityBeanClickHealHpByTypeIfWeatherKey(Struct _str) {
        return navigateData(new AbilityBeanClickHealHpByTypeIfWeatherKey(),_str,0);
    }

    public static String callAbilityBeanClickHealHpByTypeIfWeatherKeyId() {
        Struct b_ = directCase();
        callAbilityBeanClickHealHpByTypeIfWeatherKey(b_);
        return getValMoveId(b_);
    }
    public static String callAbilityBeanClickHealHpByWeatherKey() {
        return callAbilityBeanClickHealHpByWeatherKey(directCase());
    }
    public static String callAbilityBeanClickHealHpByWeatherKey(Struct _str) {
        return navigateData(new AbilityBeanClickHealHpByWeatherKey(),_str,0);
    }

    public static String callAbilityBeanClickHealHpByWeatherKeyId() {
        Struct b_ = directCase();
        callAbilityBeanClickHealHpByWeatherKey(b_);
        return getValMoveId(b_);
    }
    public static String callAbilityBeanClickIgnAbility() {
        return callAbilityBeanClickIgnAbility(directCase());
    }
    public static String callAbilityBeanClickIgnAbility(Struct _str) {
        return navigateData(new AbilityBeanClickIgnAbility(),_str,0);
    }

    public static String callAbilityBeanClickIgnAbilityId() {
        Struct b_ = directCase();
        callAbilityBeanClickIgnAbility(b_);
        return getValAbilityId(b_);
    }
    public static String callAbilityBeanClickIgnFoeTeamMove() {
        return callAbilityBeanClickIgnFoeTeamMove(directCase());
    }
    public static String callAbilityBeanClickIgnFoeTeamMove(Struct _str) {
        return navigateData(new AbilityBeanClickIgnFoeTeamMove(),_str,0);
    }

    public static String callAbilityBeanClickIgnFoeTeamMoveId() {
        Struct b_ = directCase();
        callAbilityBeanClickIgnFoeTeamMove(b_);
        return getValMoveId(b_);
    }
    public static String callAbilityBeanClickImmuAbility() {
        return callAbilityBeanClickImmuAbility(directCase());
    }
    public static String callAbilityBeanClickImmuAbility(Struct _str) {
        return navigateData(new AbilityBeanClickImmuAbility(),_str,0);
    }

    public static String callAbilityBeanClickImmuAbilityId() {
        Struct b_ = directCase();
        callAbilityBeanClickImmuAbility(b_);
        return getValAbilityId(b_);
    }
    public static String callAbilityBeanClickImmuAllyFromMoves() {
        return callAbilityBeanClickImmuAllyFromMoves(directCase());
    }
    public static String callAbilityBeanClickImmuAllyFromMoves(Struct _str) {
        return navigateData(new AbilityBeanClickImmuAllyFromMoves(),_str,0);
    }

    public static String callAbilityBeanClickImmuAllyFromMovesId() {
        Struct b_ = directCase();
        callAbilityBeanClickImmuAllyFromMoves(b_);
        return getValMoveId(b_);
    }
    public static String callAbilityBeanClickImmuLowStatIfStatusKey() {
        return callAbilityBeanClickImmuLowStatIfStatusKey(directCase());
    }
    public static String callAbilityBeanClickImmuLowStatIfStatusKey(Struct _str) {
        return navigateData(new AbilityBeanClickImmuLowStatIfStatusKey(),_str,0);
    }

    public static String callAbilityBeanClickImmuLowStatIfStatusKeyId() {
        Struct b_ = directCase();
        callAbilityBeanClickImmuLowStatIfStatusKey(b_);
        return getValStatusId(b_);
    }
    public static String callAbilityBeanClickImmuMove() {
        return callAbilityBeanClickImmuMove(directCase());
    }

    public static String callAbilityBeanClickImmuMove(Struct _str) {
        return navigateData(new AbilityBeanClickImmuMove(),_str,0);
    }

    public static String callAbilityBeanClickImmuMoveId() {
        Struct b_ = directCase();
        callAbilityBeanClickImmuMove(b_);
        return getValMoveId(b_);
    }

    public static String callAbilityBeanClickImmuMoveByWeather() {
        return callAbilityBeanClickImmuMoveByWeather(directCase());
    }
    public static String callAbilityBeanClickImmuMoveByWeather(Struct _str) {
        return navigateData(new AbilityBeanClickImmuMoveByWeather(),_str,0);
    }

    public static String callAbilityBeanClickImmuMoveByWeatherId() {
        Struct b_ = directCase();
        callAbilityBeanClickImmuMoveByWeather(b_);
        return getValMoveId(b_);
    }
    public static String callAbilityBeanClickImmuRechargeRoundMoves() {
        return callAbilityBeanClickImmuRechargeRoundMoves(directCase());
    }
    public static String callAbilityBeanClickImmuRechargeRoundMoves(Struct _str) {
        return navigateData(new AbilityBeanClickImmuRechargeRoundMoves(),_str,0);
    }

    public static String callAbilityBeanClickImmuRechargeRoundMovesId() {
        Struct b_ = directCase();
        callAbilityBeanClickImmuRechargeRoundMoves(b_);
        return getValMoveId(b_);
    }
    public static String callAbilityBeanClickImmuStatus() {
        return callAbilityBeanClickImmuStatus(directCase());
    }
    public static String callAbilityBeanClickImmuStatus(Struct _str) {
        return navigateData(new AbilityBeanClickImmuStatus(),_str,0,0);
    }

    public static String callAbilityBeanClickImmuStatusId() {
        Struct b_ = directCase();
        callAbilityBeanClickImmuStatus(b_);
        return getValStatusId(b_);
    }
    public static String callAbilityBeanClickImmuStatusBeginRound() {
        return callAbilityBeanClickImmuStatusBeginRound(directCase());
    }
    public static String callAbilityBeanClickImmuStatusBeginRound(Struct _str) {
        return navigateData(new AbilityBeanClickImmuStatusBeginRound(),_str,0);
    }

    public static String callAbilityBeanClickImmuStatusBeginRoundId() {
        Struct b_ = directCase();
        callAbilityBeanClickImmuStatusBeginRound(b_);
        return getValStatusId(b_);
    }
    public static String callAbilityBeanClickImmuStatusTypes() {
        return callAbilityBeanClickImmuStatusTypes(directCase());
    }
    public static String callAbilityBeanClickImmuStatusTypes(Struct _str) {
        return navigateData(new AbilityBeanClickImmuStatusTypes(),_str,0,0);
    }

    public static String callAbilityBeanClickImmuStatusTypesId() {
        Struct b_ = directCase();
        callAbilityBeanClickImmuStatusTypes(b_);
        return getValStatusId(b_);
    }
    public static String callAbilityBeanClickImmuStatusWeather() {
        return callAbilityBeanClickImmuStatusWeather(directCase());
    }
    public static String callAbilityBeanClickImmuStatusWeather(Struct _str) {
        return navigateData(new AbilityBeanClickImmuStatusWeather(),_str,0);
    }

    public static String callAbilityBeanClickImmuStatusWeatherId() {
        Struct b_ = directCase();
        callAbilityBeanClickImmuStatusWeather(b_);
        return getValMoveId(b_);
    }
    public static String callAbilityBeanClickIndex() {
        return navigateData(new AbilityBeanClickIndex(),directCase());
    }

    public static String callAbilityBeanClickMultStatIfStatutRankSec() {
        return callAbilityBeanClickMultStatIfStatutRankSec(directCase());
    }
    public static String callAbilityBeanClickMultStatIfStatutRankSec(Struct _str) {
        return navigateData(new AbilityBeanClickMultStatIfStatutRankSec(),_str,0);
    }

    public static String callAbilityBeanClickMultStatIfStatutRankSecId() {
        Struct b_ = directCase();
        callAbilityBeanClickMultStatIfStatutRankSec(b_);
        return getValStatusId(b_);
    }
    public static String callAbilityBeanClickPokemon() {
        return callAbilityBeanClickPokemon(directCase());
    }
    public static String callAbilityBeanClickPokemon(Struct _str) {
        return navigateData(new AbilityBeanClickPokemon(),_str,0);
    }

    public static String callAbilityBeanClickPokemonId() {
        Struct b_ = directCase();
        callAbilityBeanClickPokemon(b_);
        return getValPkId(b_);
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

    public static String callAbilityBeanClickSingleStatus() {
        return callAbilityBeanClickSingleStatus(directCase());
    }
    public static String callAbilityBeanClickSingleStatus(Struct _str) {
        return navigateData(new AbilityBeanClickSingleStatus(),_str,0);
    }

    public static String callAbilityBeanClickSingleStatusId() {
        Struct b_ = directCase();
        callAbilityBeanClickSingleStatus(b_);
        return getValStatusId(b_);
    }
    public static String callAbilityBeanClickWeather() {
        return callAbilityBeanClickWeather(directCase());
    }
    public static String callAbilityBeanClickWeather(Struct _str) {
        return navigateData(new AbilityBeanClickWeather(),_str,0);
    }

    public static String callAbilityBeanClickWeatherId() {
        Struct b_ = directCase();
        callAbilityBeanClickWeather(b_);
        return getValMoveId(b_);
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

    public static Struct callAbilityBeanDefEffGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanDefEffGet(),directCase());
    }

    public static Struct callAbilityBeanDisplayNameGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanDisplayNameGet(),directCase());
    }

    public static Struct callAbilityBeanDivideStatusRoundGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanDivideStatusRoundGet(),directCase());
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

    public static Struct callAbilityBeanFailStatusGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanFailStatusGet(),directCase());
    }

    public static Struct callAbilityBeanForbidUseBerryAgainstFoesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanForbidUseBerryAgainstFoesGet(),_str,_args);
    }

    public static Struct callAbilityBeanForwardStatusGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanForwardStatusGet(),directCase());
    }

    public static Struct callAbilityBeanGetEffectSending(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetEffectSending(),_str,_args);
    }

    public static Struct callAbilityBeanGetTrBonusStatRank() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrBonusStatRank(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrBoostStatRankEndRound() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrBoostStatRankEndRound(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrBoostStatRankProtected() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrBoostStatRankProtected(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrBreakFoeImmuneKey() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrBreakFoeImmuneKey(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrBreakFoeImmuneValue() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrBreakFoeImmuneValue(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrBreakProtectionMoves() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrBreakProtectionMoves(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrChangingBoostTypesNew() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrChangingBoostTypesNew(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrChangingBoostTypesOld() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrChangingBoostTypesOld(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrChgtTypeByWeatherKey() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrChgtTypeByWeatherKey(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrChgtTypeByWeatherValue() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrChgtTypeByWeatherValue(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrDivideStatusRoundKey() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrDivideStatusRoundKey(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrFailStatus() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrFailStatus(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrForwardStatusKey() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrForwardStatusKey(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrForwardStatusValue() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrForwardStatusValue(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrHealHpByTypeIfWeatherKey() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrHealHpByTypeIfWeatherKey(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrHealHpByTypeIfWeatherKeySec() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrHealHpByTypeIfWeatherKeySec(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrHealHpByWeatherKey() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrHealHpByWeatherKey(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrIgnAbility() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrIgnAbility(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrIgnFoeTeamMove() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrIgnFoeTeamMove(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrImmuAbility() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuAbility(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrImmuAllyFromMoves() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuAllyFromMoves(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrImmuLowStat() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuLowStat(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrImmuLowStatIfStatusKey() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuLowStatIfStatusKey(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrImmuLowStatIfStatusValue() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuLowStatIfStatusValue(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrImmuLowStatisTypes() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuLowStatisTypes(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrImmuLowStatisValue() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuLowStatisValue(),directCase(),0,0);
    }

    public static Struct callAbilityBeanGetTrImmuMove() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuMove(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrImmuMoveByWeather() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuMoveByWeather(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrImmuRechargeRoundMoves() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuRechargeRoundMoves(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrImmuStatus() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuStatus(),directCase(),0,0);
    }

    public static Struct callAbilityBeanGetTrImmuStatusBeginRound() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuStatusBeginRound(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrImmuStatusTypes() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuStatusTypes(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrImmuStatusValue() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuStatusValue(),directCase(),0,0);
    }

    public static Struct callAbilityBeanGetTrImmuStatusWeather() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuStatusWeather(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrImmuTypeByWeather() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuTypeByWeather(),directCase(),0,0);
    }

    public static Struct callAbilityBeanGetTrIncreasedPrio() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrIncreasedPrio(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrIncreasedPrioTypes() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrIncreasedPrioTypes(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrLowStatFoeHit() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrLowStatFoeHit(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrMaxStatisticsIfCh() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMaxStatisticsIfCh(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrMultDamageFoe() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultDamageFoe(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrMultPowerMovesTypesGlobalKey() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultPowerMovesTypesGlobalKey(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrMultStat() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStat(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrMultStatAlly() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStatAlly(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrMultStatIfCatKey() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStatIfCatKey(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrMultStatIfCatKeySec() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStatIfCatKeySec(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrMultStatIfDamageCatKey() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStatIfDamageCatKey(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrMultStatIfDamageCatKeySec() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStatIfDamageCatKeySec(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrMultStatIfDamgeType() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStatIfDamgeType(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrMultStatIfDamgeTypeSec() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStatIfDamgeTypeSec(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrMultStatIfKoFoe() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStatIfKoFoe(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrMultStatIfLowStat() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStatIfLowStat(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrMultStatIfStatutRank() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStatIfStatutRank(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrMultStatIfStatutRankSec() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStatIfStatutRankSec(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrPokemon() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrPokemon(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrReversePowerTypesAbilities() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrReversePowerTypesAbilities(),reverseCase(),0);
    }

    public static Struct callAbilityBeanGetTrSingleStatus() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrSingleStatus(),directCase(),0);
    }

    public static Struct callAbilityBeanGetTrWeather() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrWeather(),directCase(),0);
    }

    public static Struct callAbilityBeanGiveItemToAllyHavingUsedGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGiveItemToAllyHavingUsedGet(),_str,_args);
    }

    public static Struct callAbilityBeanHealHpByTypeIfWeatherGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanHealHpByTypeIfWeatherGet(),directCase());
    }

    public static Struct callAbilityBeanHealHpByWeatherGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanHealHpByWeatherGet(),directCase());
    }

    public static Struct callAbilityBeanHealHpWhileUsingBerryGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanHealHpWhileUsingBerryGet(),directCase());
    }

    public static Struct callAbilityBeanHealedHpRateBySwitchGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanHealedHpRateBySwitchGet(),directCase());
    }

    public static Struct callAbilityBeanHealedStatusBySwitchGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanHealedStatusBySwitchGet(),_str,_args);
    }

    public static Struct callAbilityBeanIgnAbilityGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanIgnAbilityGet(),directCase());
    }

    public static Struct callAbilityBeanIgnFoeStatisBoostGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanIgnFoeStatisBoostGet(),_str,_args);
    }

    public static Struct callAbilityBeanIgnFoeTeamMoveGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanIgnFoeTeamMoveGet(),directCase());
    }

    public static Struct callAbilityBeanImmuAbilityGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuAbilityGet(),directCase());
    }

    public static Struct callAbilityBeanImmuAllyFromMovesGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuAllyFromMovesGet(),directCase());
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

    public static Struct callAbilityBeanImmuLowStatGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuLowStatGet(),directCase());
    }

    public static Struct callAbilityBeanImmuLowStatIfStatusGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuLowStatIfStatusGet(),directCase());
    }

    public static Struct callAbilityBeanImmuLowStatisTypesGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuLowStatisTypesGet(),directCase());
    }

    public static Struct callAbilityBeanImmuMoveGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuMoveGet(),directCase());
    }

    public static Struct callAbilityBeanImmuMoveTypesByWeatherGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuMoveTypesByWeatherGet(),directCase());
    }

    public static Struct callAbilityBeanImmuRechargeRoundGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuRechargeRoundGet(),_str,_args);
    }

    public static Struct callAbilityBeanImmuRechargeRoundMovesGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuRechargeRoundMovesGet(),directCase());
    }

    public static Struct callAbilityBeanImmuStatusBeginRoundGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuStatusBeginRoundGet(),directCase());
    }

    public static Struct callAbilityBeanImmuStatusGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuStatusGet(),directCase());
    }

    public static Struct callAbilityBeanImmuStatusTypesGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuStatusTypesGet(),directCase());
    }

    public static Struct callAbilityBeanImmuSufferedDamageLowEffGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuSufferedDamageLowEffGet(),_str,_args);
    }

    public static Struct callAbilityBeanImmuWeatherGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuWeatherGet(),directCase());
    }

    public static Struct callAbilityBeanIncreasedPrioGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanIncreasedPrioGet(),directCase());
    }

    public static Struct callAbilityBeanIncreasedPrioTypesGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanIncreasedPrioTypesGet(),directCase());
    }

    public static Struct callAbilityBeanInflictingDamageInsteadOfSufferingGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanInflictingDamageInsteadOfSufferingGet(),_str,_args);
    }

    public static Struct callAbilityBeanIsChgtTypeByWeather(int _v) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanIsChgtTypeByWeather(),directCase(),_v);
    }

    public static Struct callAbilityBeanIsHealHpByTypeIfWeather(int _v) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanIsHealHpByTypeIfWeather(),directCase(),_v);
    }

    public static Struct callAbilityBeanIsHealHpByWeather(int _v) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanIsHealHpByWeather(),directCase(),_v);
    }

    public static Struct callAbilityBeanIsMoveByStatus(int _v) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanIsMoveByStatus(),directCase(),_v);
    }

    public static Struct callAbilityBeanIsMoveByWeather(int _v) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanIsMoveByWeather(),directCase(),_v);
    }

    public static Struct callAbilityBeanIsStatus(int _v) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanIsStatus(),directCase(),_v);
    }

    public static Struct callAbilityBeanLowStatFoeHitGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanLowStatFoeHitGet(),directCase());
    }

    public static Struct callAbilityBeanMapVarsFailEndRoundGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMapVarsFailEndRoundGet(),directCaseEndRound());
    }

    public static Struct callAbilityBeanMapVarsGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMapVarsGet(),directCase());
    }

    public static Struct callAbilityBeanMaxHpForUsingBerryGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMaxHpForUsingBerryGet(),directCase());
    }

    public static Struct callAbilityBeanMaxStatisticsIfChGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMaxStatisticsIfChGet(),directCase());
    }

    public static Struct callAbilityBeanMultAllyDamageGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultAllyDamageGet(),directCase());
    }

    public static Struct callAbilityBeanMultDamageChGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultDamageChGet(),directCase());
    }

    public static Struct callAbilityBeanMultDamageFoeGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultDamageFoeGet(),directCase());
    }

    public static Struct callAbilityBeanMultDamageGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultDamageGet(),directCase());
    }

    public static Struct callAbilityBeanMultEvtRateChGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultEvtRateChGet(),directCase());
    }

    public static Struct callAbilityBeanMultEvtRateSecEffectOwnerGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultEvtRateSecEffectOwnerGet(),directCase());
    }

    public static Struct callAbilityBeanMultPowerGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultPowerGet(),directCase());
    }

    public static Struct callAbilityBeanMultPowerMovesTypesGlobalGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultPowerMovesTypesGlobalGet(),directCase());
    }

    public static Struct callAbilityBeanMultStabGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultStabGet(),directCase());
    }

    public static Struct callAbilityBeanMultStatAllyGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultStatAllyGet(),directCase());
    }

    public static Struct callAbilityBeanMultStatGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultStatGet(),directCase());
    }

    public static Struct callAbilityBeanMultStatIfCatGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultStatIfCatGet(),directCase());
    }

    public static Struct callAbilityBeanMultStatIfDamageCatGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultStatIfDamageCatGet(),directCase());
    }

    public static Struct callAbilityBeanMultStatIfDamgeTypeGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultStatIfDamgeTypeGet(),directCase());
    }

    public static Struct callAbilityBeanMultStatIfKoFoeGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultStatIfKoFoeGet(),directCase());
    }

    public static Struct callAbilityBeanMultStatIfLowStatGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultStatIfLowStatGet(),directCase());
    }

    public static Struct callAbilityBeanMultStatIfStatutRankGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultStatIfStatutRankGet(),directCase());
    }

    public static Struct callAbilityBeanMultSufferedDamageSuperEffGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultSufferedDamageSuperEffGet(),directCase());
    }

    public static Struct callAbilityBeanMultVarBoostGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultVarBoostGet(),directCase());
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

    public static Struct callAbilityBeanPokemonGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanPokemonGet(),directCase());
    }

    public static Struct callAbilityBeanReasonsEndRoundGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanReasonsEndRoundGet(),directCaseEndRound());
    }

//    public static Struct callAbilityBeanRecoilDamageFoeByKoOwnerGet() {
//        return BeanPokemonCommonTs.callLongs(new AbilityBeanRecoilDamageFoeByKoOwnerGet(),directCase());
//    }

    public static Struct callAbilityBeanRecoilDamageFoeGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanRecoilDamageFoeGet(),directCase());
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

    public static Struct callAbilityBeanSingleStatusGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanSingleStatusGet(),directCase());
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
        EffectProtection e_ = Instances.newEffectProtection();
        e_.setProtTeamAgainstPrio(true);
        rech_.getEffects().add(e_);
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
