package aiki.beans.abilities;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.PkData;
import aiki.beans.effects.AikiBeansEffectsStd;
import aiki.db.MessagesDataBaseConstants;
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
import aiki.instances.Instances;
import code.bean.nat.*;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.StringList;
import code.util.StringMap;

public abstract class InitDbAbility extends InitDbAbilities {

    public static NaSt callAbilityBeanAchievedDisappearedPkGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanAchievedDisappearedPkGet(),_str,_args);
    }

    public static NaSt callAbilityBeanBonusStatRankGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanBonusStatRankGet(),directCase());
    }

    public static NaSt callAbilityBeanBoostStatRankEndRoundGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanBoostStatRankEndRoundGet(),directCase());
    }

    public static NaSt callAbilityBeanBoostStatRankProtectedGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanBoostStatRankProtectedGet(),directCase());
    }

    public static NaSt callAbilityBeanBreakFoeImmuneGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanBreakFoeImmuneGet(),directCase());
    }

    public static NaSt callAbilityBeanBreakProtectionGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanBreakProtectionGet(),_str,_args);
    }

    public static NaSt callAbilityBeanBreakProtectionMovesGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanBreakProtectionMovesGet(),directCase());
    }

    public static NaSt callAbilityBeanCancelSecEffectOtherGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanCancelSecEffectOtherGet(),_str,_args);
    }

    public static NaSt callAbilityBeanCancelSecEffectOwnerGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanCancelSecEffectOwnerGet(),_str,_args);
    }

    public static NaSt callAbilityBeanChangingBoostTypesGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanChangingBoostTypesGet(),directCase());
    }

    public static NaSt callAbilityBeanChgtTypeByDamageGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanChgtTypeByDamageGet(),_str,_args);
    }

    public static NaSt callAbilityBeanChgtTypeByWeatherGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanChgtTypeByWeatherGet(),directCase());
    }

    public static String callAbilityBeanClickBreakProtectionMoves() {
        return callAbilityBeanClickBreakProtectionMoves(directCase());
    }

    public static String callAbilityBeanClickBreakProtectionMoves(NaSt _str) {
        return navigateData(new AbilityBeanClickBreakProtectionMoves(),_str,0);
    }

    public static String callAbilityBeanClickBreakProtectionMovesId() {
        NaSt b_ = directCase();
        callAbilityBeanClickBreakProtectionMoves(b_);
        return getValMoveId(b_);
    }
    public static String callAbilityBeanClickChgtTypeByWeatherKey() {
        return callAbilityBeanClickChgtTypeByWeatherKey(directCase());
    }
    public static String callAbilityBeanClickChgtTypeByWeatherKey(NaSt _str) {
        return navigateData(new AbilityBeanClickChgtTypeByWeatherKey(),_str,1);
    }

    public static String callAbilityBeanClickChgtTypeByWeatherKeyId() {
        NaSt b_ = directCase();
        callAbilityBeanClickChgtTypeByWeatherKey(b_);
        return getValMoveId(b_);
    }
    public static String callAbilityBeanClickDivideStatusRoundKey() {
        return callAbilityBeanClickDivideStatusRoundKey(directCase());
    }
    public static String callAbilityBeanClickDivideStatusRoundKey(NaSt _str) {
        return navigateData(new AbilityBeanClickDivideStatusRoundKey(),_str,0);
    }

    public static String callAbilityBeanClickDivideStatusRoundKeyId() {
        NaSt b_ = directCase();
        callAbilityBeanClickDivideStatusRoundKey(b_);
        return getValStatusId(b_);
    }
    public static String callAbilityBeanClickFailStatus() {
        return callAbilityBeanClickFailStatus(directCase());
    }
    public static String callAbilityBeanClickFailStatus(NaSt _str) {
        return navigateData(new AbilityBeanClickFailStatus(),_str,0);
    }

    public static String callAbilityBeanClickFailStatusId() {
        NaSt b_ = directCase();
        callAbilityBeanClickFailStatus(b_);
        return getValStatusId(b_);
    }
    public static String callAbilityBeanClickForwardStatusKey() {
        return callAbilityBeanClickForwardStatusKey(directCase());
    }
    public static String callAbilityBeanClickForwardStatusKey(NaSt _str) {
        return navigateData(new AbilityBeanClickForwardStatusKey(),_str,0);
    }

    public static String callAbilityBeanClickForwardStatusKeyId() {
        NaSt b_ = directCase();
        callAbilityBeanClickForwardStatusKey(b_);
        return getValStatusId(b_);
    }
    public static String callAbilityBeanClickForwardStatusValue() {
        return callAbilityBeanClickForwardStatusValue(directCase());
    }
    public static String callAbilityBeanClickForwardStatusValue(NaSt _str) {
        return navigateData(new AbilityBeanClickForwardStatusValue(),_str,0);
    }

    public static String callAbilityBeanClickForwardStatusValueId() {
        NaSt b_ = directCase();
        callAbilityBeanClickForwardStatusValue(b_);
        return getValStatusId(b_);
    }
    public static String callAbilityBeanClickHealHpByTypeIfWeatherKey() {
        return callAbilityBeanClickHealHpByTypeIfWeatherKey(directCase());
    }
    public static String callAbilityBeanClickHealHpByTypeIfWeatherKey(NaSt _str) {
        return navigateData(new AbilityBeanClickHealHpByTypeIfWeatherKey(),_str,1);
    }

    public static String callAbilityBeanClickHealHpByTypeIfWeatherKeyId() {
        NaSt b_ = directCase();
        callAbilityBeanClickHealHpByTypeIfWeatherKey(b_);
        return getValMoveId(b_);
    }
    public static String callAbilityBeanClickHealHpByWeatherKey() {
        return callAbilityBeanClickHealHpByWeatherKey(directCase());
    }
    public static String callAbilityBeanClickHealHpByWeatherKey(NaSt _str) {
        return navigateData(new AbilityBeanClickHealHpByWeatherKey(),_str,1);
    }

    public static String callAbilityBeanClickHealHpByWeatherKeyId() {
        NaSt b_ = directCase();
        callAbilityBeanClickHealHpByWeatherKey(b_);
        return getValMoveId(b_);
    }
    public static String callAbilityBeanClickIgnAbility() {
        return callAbilityBeanClickIgnAbility(directCase());
    }
    public static String callAbilityBeanClickIgnAbility(NaSt _str) {
        return navigateData(new AbilityBeanClickIgnAbility(),_str,0);
    }

    public static String callAbilityBeanClickIgnAbilityId() {
        NaSt b_ = directCase();
        callAbilityBeanClickIgnAbility(b_);
        return getValAbilityId(b_);
    }
    public static String callAbilityBeanClickIgnFoeTeamMove() {
        return callAbilityBeanClickIgnFoeTeamMove(directCase());
    }
    public static String callAbilityBeanClickIgnFoeTeamMove(NaSt _str) {
        return navigateData(new AbilityBeanClickIgnFoeTeamMove(),_str,0);
    }

    public static String callAbilityBeanClickIgnFoeTeamMoveId() {
        NaSt b_ = directCase();
        callAbilityBeanClickIgnFoeTeamMove(b_);
        return getValMoveId(b_);
    }
    public static String callAbilityBeanClickImmuAbility() {
        return callAbilityBeanClickImmuAbility(directCase());
    }
    public static String callAbilityBeanClickImmuAbility(NaSt _str) {
        return navigateData(new AbilityBeanClickImmuAbility(),_str,0);
    }

    public static String callAbilityBeanClickImmuAbilityId() {
        NaSt b_ = directCase();
        callAbilityBeanClickImmuAbility(b_);
        return getValAbilityId(b_);
    }
    public static String callAbilityBeanClickImmuAllyFromMoves() {
        return callAbilityBeanClickImmuAllyFromMoves(directCase());
    }
    public static String callAbilityBeanClickImmuAllyFromMoves(NaSt _str) {
        return navigateData(new AbilityBeanClickImmuAllyFromMoves(),_str,0);
    }

    public static String callAbilityBeanClickImmuAllyFromMovesId() {
        NaSt b_ = directCase();
        callAbilityBeanClickImmuAllyFromMoves(b_);
        return getValMoveId(b_);
    }
    public static String callAbilityBeanClickImmuLowStatIfStatusKey() {
        return callAbilityBeanClickImmuLowStatIfStatusKey(directCase());
    }
    public static String callAbilityBeanClickImmuLowStatIfStatusKey(NaSt _str) {
        return navigateData(new AbilityBeanClickImmuLowStatIfStatusKey(),_str,0);
    }

    public static String callAbilityBeanClickImmuLowStatIfStatusKeyId() {
        NaSt b_ = directCase();
        callAbilityBeanClickImmuLowStatIfStatusKey(b_);
        return getValStatusId(b_);
    }
    public static String callAbilityBeanClickImmuMove() {
        return callAbilityBeanClickImmuMove(directCase());
    }

    public static String callAbilityBeanClickImmuMove(NaSt _str) {
        return navigateData(new AbilityBeanClickImmuMove(),_str,0);
    }

    public static String callAbilityBeanClickImmuMoveId() {
        NaSt b_ = directCase();
        callAbilityBeanClickImmuMove(b_);
        return getValMoveId(b_);
    }

    public static String callAbilityBeanClickImmuMoveByWeather() {
        return callAbilityBeanClickImmuMoveByWeather(directCase());
    }
    public static String callAbilityBeanClickImmuMoveByWeather(NaSt _str) {
        return navigateData(new AbilityBeanClickImmuMoveByWeather(),_str,1);
    }

    public static String callAbilityBeanClickImmuMoveByWeatherId() {
        NaSt b_ = directCase();
        callAbilityBeanClickImmuMoveByWeather(b_);
        return getValMoveId(b_);
    }
    public static String callAbilityBeanClickImmuRechargeRoundMoves() {
        return callAbilityBeanClickImmuRechargeRoundMoves(directCase());
    }
    public static String callAbilityBeanClickImmuRechargeRoundMoves(NaSt _str) {
        return navigateData(new AbilityBeanClickImmuRechargeRoundMoves(),_str,0);
    }

    public static String callAbilityBeanClickImmuRechargeRoundMovesId() {
        NaSt b_ = directCase();
        callAbilityBeanClickImmuRechargeRoundMoves(b_);
        return getValMoveId(b_);
    }
    public static String callAbilityBeanClickImmuStatus() {
        return callAbilityBeanClickImmuStatus(directCase());
    }
    public static String callAbilityBeanClickImmuStatus(NaSt _str) {
        return navigateData(new AbilityBeanClickImmuStatus(),_str,1,0);
    }

    public static String callAbilityBeanClickImmuStatusId() {
        NaSt b_ = directCase();
        callAbilityBeanClickImmuStatus(b_);
        return getValStatusId(b_);
    }
    public static String callAbilityBeanClickImmuStatusBeginRound() {
        return callAbilityBeanClickImmuStatusBeginRound(directCase());
    }
    public static String callAbilityBeanClickImmuStatusBeginRound(NaSt _str) {
        return navigateData(new AbilityBeanClickImmuStatusBeginRound(),_str,0);
    }

    public static String callAbilityBeanClickImmuStatusBeginRoundId() {
        NaSt b_ = directCase();
        callAbilityBeanClickImmuStatusBeginRound(b_);
        return getValStatusId(b_);
    }
    public static String callAbilityBeanClickImmuStatusTypes() {
        return callAbilityBeanClickImmuStatusTypes(directCase());
    }
    public static String callAbilityBeanClickImmuStatusTypes(NaSt _str) {
        return navigateData(new AbilityBeanClickImmuStatusTypes(),_str,0,0);
    }

    public static String callAbilityBeanClickImmuStatusTypesId() {
        NaSt b_ = directCase();
        callAbilityBeanClickImmuStatusTypes(b_);
        return getValStatusId(b_);
    }
    public static String callAbilityBeanClickImmuStatusWeather() {
        return callAbilityBeanClickImmuStatusWeather(directCase());
    }
    public static String callAbilityBeanClickImmuStatusWeather(NaSt _str) {
        return navigateData(new AbilityBeanClickImmuStatusWeather(),_str,1);
    }

    public static String callAbilityBeanClickImmuStatusWeatherId() {
        NaSt b_ = directCase();
        callAbilityBeanClickImmuStatusWeather(b_);
        return getValMoveId(b_);
    }
    public static String callAbilityBeanClickIndex() {
        return navigateData(new AbilityBeanClickIndex(),directCase());
    }

    public static String callAbilityBeanClickMultStatIfStatutRankSec() {
        return callAbilityBeanClickMultStatIfStatutRankSec(directCase());
    }
    public static String callAbilityBeanClickMultStatIfStatutRankSec(NaSt _str) {
        return navigateData(new AbilityBeanClickMultStatIfStatutRankSec(),_str,0);
    }

    public static String callAbilityBeanClickMultStatIfStatutRankSecId() {
        NaSt b_ = directCase();
        callAbilityBeanClickMultStatIfStatutRankSec(b_);
        return getValStatusId(b_);
    }
    public static String callAbilityBeanClickPokemon() {
        return callAbilityBeanClickPokemon(directCase());
    }
    public static String callAbilityBeanClickPokemon(NaSt _str) {
        return navigateData(new AbilityBeanClickPokemon(),_str,0);
    }

    public static String callAbilityBeanClickPokemonId() {
        NaSt b_ = directCase();
        callAbilityBeanClickPokemon(b_);
        return getValPkId(b_);
    }
    public static String callAbilityBeanClickReversePowerTypesAbilities() {
        return callAbilityBeanClickReversePowerTypesAbilities(reverseCase());
    }

    public static String callAbilityBeanClickReversePowerTypesAbilities(NaSt _str) {
        return navigateData(new AbilityBeanClickReversePowerTypesAbilities(),_str,0);
    }

    public static String callAbilityBeanClickReversePowerTypesAbilitiesId() {
        NaSt bean_ = reverseCase();
        callAbilityBeanClickReversePowerTypesAbilities(bean_);
        return getValAbilityId(bean_);
    }

    public static String callAbilityBeanClickSingleStatus() {
        return callAbilityBeanClickSingleStatus(directCase());
    }
    public static String callAbilityBeanClickSingleStatus(NaSt _str) {
        return navigateData(new AbilityBeanClickSingleStatus(),_str,1);
    }

    public static String callAbilityBeanClickSingleStatusId() {
        NaSt b_ = directCase();
        callAbilityBeanClickSingleStatus(b_);
        return getValStatusId(b_);
    }
    public static String callAbilityBeanClickWeather() {
        return callAbilityBeanClickWeather(directCase());
    }
    public static String callAbilityBeanClickWeather(NaSt _str) {
        return navigateData(new AbilityBeanClickWeather(),_str,0);
    }

    public static String callAbilityBeanClickWeatherId() {
        NaSt b_ = directCase();
        callAbilityBeanClickWeather(b_);
        return getValMoveId(b_);
    }
    public static NaSt callAbilityBeanCopyMovesTypesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanCopyMovesTypesGet(),_str,_args);
    }

    public static NaSt callAbilityBeanDecreaseNecStepsHatchGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanDecreaseNecStepsHatchGet(),directCase());
    }

    public static NaSt callAbilityBeanDecreaseNecStepsHatchInt(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanDecreaseNecStepsHatchInt(),_str,_args);
    }

    public static NaSt callAbilityBeanDefEffGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanDefEffGet(),directCase());
    }

    public static NaSt callAbilityBeanDisplayNameGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanDisplayNameGet(),directCase());
    }

    public static NaSt callAbilityBeanDivideStatusRoundGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanDivideStatusRoundGet(),directCase());
    }

    public static NaSt callAbilityBeanEffectSendBeanGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanEffectSendBeanGet(),_str,_args);
    }

    public static NaSt callAbilityBeanEndRoundGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanEndRoundGet(),directCaseEndRound());
    }

    public static NaSt callAbilityBeanEndRoundRankGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanEndRoundRankGet(),directCaseEndRound());
    }

    public static NaSt callAbilityBeanEndRoundGetNo() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanEndRoundGet(),directCase());
    }

    public static NaSt callAbilityBeanEndRoundRankGetNo() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanEndRoundRankGet(),directCase());
    }

    public static NaSt callAbilityBeanFailStatusGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanFailStatusGet(),directCase());
    }

    public static NaSt callAbilityBeanForbidUseBerryAgainstFoesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanForbidUseBerryAgainstFoesGet(),_str,_args);
    }

    public static NaSt callAbilityBeanForwardStatusGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanForwardStatusGet(),directCase());
    }

    public static NaSt callAbilityBeanGetEffectSending(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetEffectSending(),_str,_args);
    }

    public static NaSt callAbilityBeanGetTrBonusStatRank() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrBonusStatRank(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrBoostStatRankEndRound() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrBoostStatRankEndRound(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrBoostStatRankProtected() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrBoostStatRankProtected(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrBreakFoeImmuneKey() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrBreakFoeImmuneKey(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrBreakFoeImmuneValue() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrBreakFoeImmuneValue(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrBreakProtectionMoves() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrBreakProtectionMoves(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrChangingBoostTypesNew() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrChangingBoostTypesNew(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrChangingBoostTypesOld() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrChangingBoostTypesOld(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrChgtTypeByWeatherKey() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrChgtTypeByWeatherKey(),directCase(),1);
    }

    public static NaSt callAbilityBeanGetTrChgtTypeByWeatherValue() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrChgtTypeByWeatherValue(),directCase(),1);
    }

    public static NaSt callAbilityBeanGetTrDivideStatusRoundKey() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrDivideStatusRoundKey(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrFailStatus() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrFailStatus(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrForwardStatusKey() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrForwardStatusKey(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrForwardStatusValue() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrForwardStatusValue(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrHealHpByTypeIfWeatherKey() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrHealHpByTypeIfWeatherKey(),directCase(),1);
    }

    public static NaSt callAbilityBeanGetTrHealHpByTypeIfWeatherKeySec() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrHealHpByTypeIfWeatherKeySec(),directCase(),1);
    }

    public static NaSt callAbilityBeanGetTrHealHpByWeatherKey() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrHealHpByWeatherKey(),directCase(),1);
    }

    public static NaSt callAbilityBeanGetTrIgnAbility() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrIgnAbility(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrIgnFoeTeamMove() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrIgnFoeTeamMove(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrImmuAbility() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuAbility(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrImmuAllyFromMoves() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuAllyFromMoves(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrImmuLowStat() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuLowStat(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrImmuLowStatIfStatusKey() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuLowStatIfStatusKey(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrImmuLowStatIfStatusValue() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuLowStatIfStatusValue(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrImmuLowStatisTypes() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuLowStatisTypes(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrImmuLowStatisValue() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuLowStatisValue(),directCase(),0,0);
    }

    public static NaSt callAbilityBeanGetTrImmuMove() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuMove(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrImmuMoveByWeather() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuMoveByWeather(),directCase(),1);
    }

    public static NaSt callAbilityBeanGetTrImmuRechargeRoundMoves() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuRechargeRoundMoves(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrImmuStatus() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuStatus(),directCase(),1,0);
    }

    public static NaSt callAbilityBeanGetTrImmuStatusBeginRound() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuStatusBeginRound(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrImmuStatusTypes() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuStatusTypes(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrImmuStatusValue() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuStatusValue(),directCase(),0,0);
    }

    public static NaSt callAbilityBeanGetTrImmuStatusWeather() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuStatusWeather(),directCase(),1);
    }

    public static NaSt callAbilityBeanGetTrImmuTypeByWeather() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrImmuTypeByWeather(),directCase(),1,0);
    }

    public static NaSt callAbilityBeanGetTrIncreasedPrio() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrIncreasedPrio(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrIncreasedPrioTypes() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrIncreasedPrioTypes(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrLowStatFoeHit() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrLowStatFoeHit(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrMaxStatisticsIfCh() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMaxStatisticsIfCh(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrMultDamageFoe() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultDamageFoe(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrMultPowerMovesTypesGlobalKey() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultPowerMovesTypesGlobalKey(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrMultStat() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStat(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrMultStatAlly() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStatAlly(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrMultStatIfCatKey() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStatIfCatKey(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrMultStatIfCatKeySec() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStatIfCatKeySec(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrMultStatIfDamageCatKey() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStatIfDamageCatKey(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrMultStatIfDamageCatKeySec() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStatIfDamageCatKeySec(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrMultStatIfDamgeType() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStatIfDamgeType(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrMultStatIfDamgeTypeSec() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStatIfDamgeTypeSec(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrMultStatIfKoFoe() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStatIfKoFoe(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrMultStatIfLowStat() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStatIfLowStat(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrMultStatIfStatutRank() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStatIfStatutRank(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrMultStatIfStatutRankSec() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrMultStatIfStatutRankSec(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrPokemon() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrPokemon(),directCase(),0);
    }

    public static NaSt callAbilityBeanGetTrReversePowerTypesAbilities() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrReversePowerTypesAbilities(),reverseCase(),0);
    }

    public static NaSt callAbilityBeanGetTrSingleStatus() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrSingleStatus(),directCase(),1);
    }

    public static NaSt callAbilityBeanGetTrWeather() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetTrWeather(),directCase(),0);
    }

    public static NaSt callAbilityBeanGiveItemToAllyHavingUsedGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanGiveItemToAllyHavingUsedGet(),_str,_args);
    }

    public static NaSt callAbilityBeanHealHpByTypeIfWeatherGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanHealHpByTypeIfWeatherGet(),directCase());
    }

    public static NaSt callAbilityBeanHealHpByWeatherGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanHealHpByWeatherGet(),directCase());
    }

    public static NaSt callAbilityBeanHealHpWhileUsingBerryGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanHealHpWhileUsingBerryGet(),directCase());
    }

    public static NaSt callAbilityBeanHealedHpRateBySwitchGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanHealedHpRateBySwitchGet(),directCase());
    }

    public static NaSt callAbilityBeanHealedStatusBySwitchGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanHealedStatusBySwitchGet(),_str,_args);
    }

    public static NaSt callAbilityBeanIgnAbilityGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanIgnAbilityGet(),directCase());
    }

    public static NaSt callAbilityBeanIgnFoeStatisBoostGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanIgnFoeStatisBoostGet(),_str,_args);
    }

    public static NaSt callAbilityBeanIgnFoeTeamMoveGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanIgnFoeTeamMoveGet(),directCase());
    }

    public static NaSt callAbilityBeanImmuAbilityGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuAbilityGet(),directCase());
    }

    public static NaSt callAbilityBeanImmuAllyFromMovesGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuAllyFromMovesGet(),directCase());
    }

    public static NaSt callAbilityBeanImmuChGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuChGet(),_str,_args);
    }

    public static NaSt callAbilityBeanImmuDamageAllyMovesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuDamageAllyMovesGet(),_str,_args);
    }

    public static NaSt callAbilityBeanImmuDamageRecoilGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuDamageRecoilGet(),_str,_args);
    }

    public static NaSt callAbilityBeanImmuDamageTrappingMovesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuDamageTrappingMovesGet(),_str,_args);
    }

    public static NaSt callAbilityBeanImmuLowStatGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuLowStatGet(),directCase());
    }

    public static NaSt callAbilityBeanImmuLowStatIfStatusGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuLowStatIfStatusGet(),directCase());
    }

    public static NaSt callAbilityBeanImmuLowStatisTypesGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuLowStatisTypesGet(),directCase());
    }

    public static NaSt callAbilityBeanImmuMoveGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuMoveGet(),directCase());
    }

    public static NaSt callAbilityBeanImmuMoveTypesByWeatherGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuMoveTypesByWeatherGet(),directCase());
    }

    public static NaSt callAbilityBeanImmuRechargeRoundGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuRechargeRoundGet(),_str,_args);
    }

    public static NaSt callAbilityBeanImmuRechargeRoundMovesGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuRechargeRoundMovesGet(),directCase());
    }

    public static NaSt callAbilityBeanImmuStatusBeginRoundGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuStatusBeginRoundGet(),directCase());
    }

    public static NaSt callAbilityBeanImmuStatusGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuStatusGet(),directCase());
    }

    public static NaSt callAbilityBeanImmuStatusTypesGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuStatusTypesGet(),directCase());
    }

    public static NaSt callAbilityBeanImmuSufferedDamageLowEffGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuSufferedDamageLowEffGet(),_str,_args);
    }

    public static NaSt callAbilityBeanImmuWeatherGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanImmuWeatherGet(),directCase());
    }

    public static NaSt callAbilityBeanIncreasedPrioGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanIncreasedPrioGet(),directCase());
    }

    public static NaSt callAbilityBeanIncreasedPrioTypesGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanIncreasedPrioTypesGet(),directCase());
    }

    public static NaSt callAbilityBeanInflictingDamageInsteadOfSufferingGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanInflictingDamageInsteadOfSufferingGet(),_str,_args);
    }

    public static NaSt callAbilityBeanIsChgtTypeByWeather(int _v) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanIsChgtTypeByWeather(),directCase(),_v);
    }

    public static NaSt callAbilityBeanIsHealHpByTypeIfWeather(int _v) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanIsHealHpByTypeIfWeather(),directCase(),_v);
    }

    public static NaSt callAbilityBeanIsHealHpByWeather(int _v) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanIsHealHpByWeather(),directCase(),_v);
    }

    public static NaSt callAbilityBeanIsMoveByStatus(int _v) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanIsMoveByStatus(),directCase(),_v);
    }

    public static NaSt callAbilityBeanIsMoveByWeather(int _v) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanIsMoveByWeather(),directCase(),_v);
    }

    public static NaSt callAbilityBeanIsStatus(int _v) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanIsStatus(),directCase(),_v);
    }

    public static NaSt callAbilityBeanLowStatFoeHitGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanLowStatFoeHitGet(),directCase());
    }

    public static NaSt callAbilityBeanMapVarsFailEndRoundGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMapVarsFailEndRoundGet(),directCaseEndRound());
    }

    public static NaSt callAbilityBeanMapVarsGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMapVarsGet(),directCase());
    }

    public static NaSt callAbilityBeanMaxHpForUsingBerryGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMaxHpForUsingBerryGet(),directCase());
    }

    public static NaSt callAbilityBeanMaxStatisticsIfChGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMaxStatisticsIfChGet(),directCase());
    }

    public static NaSt callAbilityBeanMultAllyDamageGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultAllyDamageGet(),directCase());
    }

    public static NaSt callAbilityBeanMultDamageChGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultDamageChGet(),directCase());
    }

    public static NaSt callAbilityBeanMultDamageFoeGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultDamageFoeGet(),directCase());
    }

    public static NaSt callAbilityBeanMultDamageGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultDamageGet(),directCase());
    }

    public static NaSt callAbilityBeanMultEvtRateChGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultEvtRateChGet(),directCase());
    }

    public static NaSt callAbilityBeanMultEvtRateSecEffectOwnerGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultEvtRateSecEffectOwnerGet(),directCase());
    }

    public static NaSt callAbilityBeanMultPowerGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultPowerGet(),directCase());
    }

    public static NaSt callAbilityBeanMultPowerMovesTypesGlobalGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultPowerMovesTypesGlobalGet(),directCase());
    }

    public static NaSt callAbilityBeanMultStabGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultStabGet(),directCase());
    }

    public static NaSt callAbilityBeanMultStatAllyGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultStatAllyGet(),directCase());
    }

    public static NaSt callAbilityBeanMultStatGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultStatGet(),directCase());
    }

    public static NaSt callAbilityBeanMultStatIfCatGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultStatIfCatGet(),directCase());
    }

    public static NaSt callAbilityBeanMultStatIfDamageCatGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultStatIfDamageCatGet(),directCase());
    }

    public static NaSt callAbilityBeanMultStatIfDamgeTypeGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultStatIfDamgeTypeGet(),directCase());
    }

    public static NaSt callAbilityBeanMultStatIfKoFoeGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultStatIfKoFoeGet(),directCase());
    }

    public static NaSt callAbilityBeanMultStatIfLowStatGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultStatIfLowStatGet(),directCase());
    }

    public static NaSt callAbilityBeanMultStatIfStatutRankGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultStatIfStatutRankGet(),directCase());
    }

    public static NaSt callAbilityBeanMultSufferedDamageSuperEffGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultSufferedDamageSuperEffGet(),directCase());
    }

    public static NaSt callAbilityBeanMultVarBoostGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMultVarBoostGet(),directCase());
    }

    public static NaSt callAbilityBeanMumyGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanMumyGet(),_str,_args);
    }

    public static NaSt callAbilityBeanNbHitsGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanNbHitsGet(),_str,_args);
    }

    public static NaSt callAbilityBeanNbUsedPpGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanNbUsedPpGet(),directCase());
    }

    public static NaSt callAbilityBeanNbUsedPpInt(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanNbUsedPpInt(),_str,_args);
    }

    public static NaSt callAbilityBeanPlateGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanPlateGet(),_str,_args);
    }

    public static NaSt callAbilityBeanPokemonGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanPokemonGet(),directCase());
    }

    public static NaSt callAbilityBeanReasonsEndRoundGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanReasonsEndRoundGet(),directCaseEndRound());
    }

//    public static Struct callAbilityBeanRecoilDamageFoeByKoOwnerGet() {
//        return BeanPokemonCommonTs.callLongs(new AbilityBeanRecoilDamageFoeByKoOwnerGet(),directCase());
//    }

    public static NaSt callAbilityBeanRecoilDamageFoeGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanRecoilDamageFoeGet(),directCase());
    }

    public static NaSt callAbilityBeanReverseEffectsPowerMovesTypesGlobalAbilitiesGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanReverseEffectsPowerMovesTypesGlobalAbilitiesGet(),reverseCase());
    }

    public static NaSt callAbilityBeanReverseEffectsPowerMovesTypesGlobalGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanReverseEffectsPowerMovesTypesGlobalGet(),_str,_args);
    }

    public static NaSt callAbilityBeanSendingNoGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanSendingGet(),directCase());
    }

    public static NaSt callAbilityBeanSendingGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanSendingGet(),abNoStatSend());
    }

    public static NaSt callAbilityBeanSingleStatusGet() {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanSingleStatusGet(),directCase());
    }

    public static NaSt callAbilityBeanSlowingGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanSlowingGet(),_str,_args);
    }

    public static NaSt callAbilityBeanTakeItemByDamagingMoveGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanTakeItemByDamagingMoveGet(),_str,_args);
    }

    public static NaSt callAbilityBeanTypeForMovesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilityBeanTypeForMovesGet(),_str,_args);
    }



    public static StringMap<NaSt> beanToItBaseSend(PkData _pk) {
        StringMap<NaSt> map_ = beanToAbility(_pk);
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

    protected static NaSt reverseCase() {
        PkData pk_ = pkDataByFacade(feedDbAbilityReverse());
        StringMap<NaSt> all_ = beanToAbility(pk_);
        return transitToAllAbilities(pk_,all_,0);
    }

    protected static NaSt directCase() {
        PkData pk_ = pkDataByFacade(feedDbAbility());
        StringMap<NaSt> all_ = beanToAbility(pk_);
        return transitToAllAbilities(pk_,all_,0);
    }
    protected static NaSt directCase(boolean _forbidUseBerryAgainstFoes, boolean _chgtTypeByDamage, boolean _ignFoeStatisBoost, boolean _immuCh, boolean _immuDamageTrappingMoves, boolean _immuDamageAllyMoves, boolean _immuDamageRecoil, boolean _copyMovesTypes, boolean _reverseEffectsPowerMovesTypesGlobal, boolean _takeItemByDamagingMove, boolean _giveItemToAllyHavingUsed, boolean _inflictingDamageInsteadOfSuffering, boolean _nbHits, boolean _breakProtection, boolean _plate, boolean _healedStatusBySwitch, boolean _achievedDisappearedPk, boolean _mumy, boolean _immuRechargeRound, boolean _slowing, boolean _immuSufferedDamageLowEff, boolean _cancelSecEffectOther, boolean _cancelSecEffectOwner, int _nbUsedPp, int _decreaseNecStepsHatch, String _typeForMoves) {
        PkData pk_ = pkDataByFacade(feedDbAbility(_forbidUseBerryAgainstFoes, _chgtTypeByDamage, _ignFoeStatisBoost, _immuCh, _immuDamageTrappingMoves, _immuDamageAllyMoves, _immuDamageRecoil, _copyMovesTypes, _reverseEffectsPowerMovesTypesGlobal, _takeItemByDamagingMove, _giveItemToAllyHavingUsed, _inflictingDamageInsteadOfSuffering, _nbHits, _breakProtection, _plate, _healedStatusBySwitch, _achievedDisappearedPk, _mumy, _immuRechargeRound, _slowing, _immuSufferedDamageLowEff, _cancelSecEffectOther, _cancelSecEffectOwner, _nbUsedPp, _decreaseNecStepsHatch, _typeForMoves));
        StringMap<NaSt> all_ = beanToAbility(pk_);
        return transitToAllAbilities(pk_,all_,0);
    }

    protected static NaSt directCaseEndRound() {
        PkData pk_ = pkDataByFacade(feedDbAbilityEndRound());
        StringMap<NaSt> all_ = beanToAbility(pk_);
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
    public static NaSt healSimpleNoStatSend() {
        PkData pk_ = pkDataByFacade(feedDbAbilityNoStat());
        StringMap<NaSt> all_ = beanToItBaseSend(pk_);
        NaSt res_ = transitToAllAbilities(pk_, all_,0);
        callAbilityBeanEffectSendBeanGet(res_);
        NaSt send_ = all_.getVal(AikiBeansEffectsStd.EFFECT_SENDING);
        callEffectWhileSendingBeanEffectSet(send_,callAbilityBeanGetEffectSending(res_));
        beforeDisplaying(send_);
        return send_;
    }
    public static NaSt abNoStatSend() {
        PkData pk_ = pkDataByFacade(feedDbAbilityNoStat());
        StringMap<NaSt> all_ = beanToItBaseSend(pk_);
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
        e_.setWithEffect(false);
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
        e_.setFailEndRound(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
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
