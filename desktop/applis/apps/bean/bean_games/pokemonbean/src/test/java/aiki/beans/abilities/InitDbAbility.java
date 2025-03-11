package aiki.beans.abilities;

import aiki.beans.*;
import aiki.beans.CommonBean;
import aiki.beans.PkData;
import aiki.beans.PokemonBeanStruct;
import aiki.beans.effects.EffectWhileSendingBean;
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
import code.scripts.pages.aiki.MessagesPkBean;
import code.sml.util.TranslationsFile;
import code.util.StringList;
import code.util.StringMap;

public abstract class InitDbAbility extends InitDbAbilities {

    public static boolean callAbilityBeanAchievedDisappearedPkGet(NaSt _str, int... _args) {
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).getAchievedDisappearedPk();
    }

    public static NaSt callAbilityBeanBonusStatRankGet() {
        return PokemonStandards.getStaByte(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getBonusStatRank());
    }

    public static NaSt callAbilityBeanBoostStatRankEndRoundGet() {
        return PokemonStandards.getStaByte(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getBoostStatRankEndRound());
    }

    public static NaSt callAbilityBeanBoostStatRankProtectedGet() {
        return PokemonStandards.getStaByte(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getBoostStatRankProtected());
    }

    public static NaSt callAbilityBeanBreakFoeImmuneGet() {
        return PokemonStandards.getTypesDuo(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getBreakFoeImmune());
    }

    public static boolean callAbilityBeanBreakProtectionGet(NaSt _str, int... _args) {
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).getBreakProtection();
    }

    public static NaSt callAbilityBeanBreakProtectionMovesGet() {
        return PokemonStandards.getKeys(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getBreakProtectionMoves());
    }

    public static boolean callAbilityBeanCancelSecEffectOtherGet(NaSt _str, int... _args) {
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).getCancelSecEffectOther();
    }

    public static boolean callAbilityBeanCancelSecEffectOwnerGet(NaSt _str, int... _args) {
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).getCancelSecEffectOwner();
    }

    public static NaSt callAbilityBeanChangingBoostTypesGet() {
        return PokemonStandards.getStrTpDam(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getChangingBoostTypes());
    }

    public static boolean callAbilityBeanChgtTypeByDamageGet(NaSt _str, int... _args) {
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).getChgtTypeByDamage();
    }

    public static NaSt callAbilityBeanChgtTypeByWeatherGet() {
        return PokemonStandards.getStrStrKey(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getChgtTypeByWeather());
    }

    public static String callAbilityBeanClickBreakProtectionMoves() {
        return callAbilityBeanClickBreakProtectionMoves(directCase());
    }

    public static String callAbilityBeanClickBreakProtectionMoves(NaSt _str) {
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).clickBreakProtectionMoves(0);
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
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).clickChgtTypeByWeatherKey(1);
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
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).clickDivideStatusRoundKey(0);
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
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).clickFailStatus(0);
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
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).clickForwardStatusKey(0);
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
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).clickForwardStatusValue(0);
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
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).clickHealHpByTypeIfWeatherKey(1);
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
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).clickHealHpByWeatherKey(1);
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
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).clickIgnAbility(0);
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
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).clickIgnFoeTeamMove(0);
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
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).clickImmuAbility(0);
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
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).clickImmuAllyFromMoves(0);
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
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).clickImmuLowStatIfStatusKey(0);
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
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).clickImmuMove(0);
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
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).clickImmuMoveByWeather(1);
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
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).clickImmuRechargeRoundMoves(0);
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
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).clickImmuStatus(1);
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
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).clickImmuStatusBeginRound(0);
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
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).clickImmuStatusTypes(0);
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
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).clickImmuStatusWeather(1);
    }

    public static String callAbilityBeanClickImmuStatusWeatherId() {
        NaSt b_ = directCase();
        callAbilityBeanClickImmuStatusWeather(b_);
        return getValMoveId(b_);
    }

    public static String callAbilityBeanClickIndex() {
        NaSt b_ = directCase();
        return navigateData(new AbilityBeanClickIndex((AbilityBean) ((PokemonBeanStruct) b_).getBean()), b_);
    }

    public static String callAbilityBeanClickMultStatIfStatutRankSec() {
        return callAbilityBeanClickMultStatIfStatutRankSec(directCase());
    }

    public static String callAbilityBeanClickMultStatIfStatutRankSec(NaSt _str) {
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).clickMultStatIfStatutRankSec(0);
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
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).clickPokemon(0);
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
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).clickReversePowerTypesAbilities(0);
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
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).clickSingleStatus(1);
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
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).clickWeather(0);
    }

    public static String callAbilityBeanClickWeatherId() {
        NaSt b_ = directCase();
        callAbilityBeanClickWeather(b_);
        return getValMoveId(b_);
    }

    public static boolean callAbilityBeanCopyMovesTypesGet(NaSt _str, int... _args) {
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).getCopyMovesTypes();
    }

    public static long callAbilityBeanDecreaseNecStepsHatchGet() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getDecreaseNecStepsHatch();
    }

    public static boolean callAbilityBeanDecreaseNecStepsHatchInt(NaSt _str, int... _args) {
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).decreaseNecStepsHatchInt();
    }

    public static Rate callAbilityBeanDefEffGet() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getDefEff();
    }

    public static String callAbilityBeanDisplayNameGet() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getDisplayName();
    }

    public static NaSt callAbilityBeanDivideStatusRoundGet() {
        return PokemonStandards.getStrRate(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getDivideStatusRound());
    }

//    public static NaSt callAbilityBeanEffectSendBeanGet(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new AbilityBeanEffectSendBeanGet(),_str,_args);
//    }

    public static boolean callAbilityBeanEndRoundGet() {
        return ((AbilityBean) ((PokemonBeanStruct) directCaseEndRound()).getInstance()).getEndRoundCommon().getEndRound();
    }

    public static long callAbilityBeanEndRoundRankGet() {
        return ((AbilityBean) ((PokemonBeanStruct) directCaseEndRound()).getInstance()).getEndRoundCommon().getEndRoundRank();
    }

    public static boolean callAbilityBeanEndRoundGetNo() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getEndRoundCommon().getEndRound();
    }

    public static long callAbilityBeanEndRoundRankGetNo() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getEndRoundCommon().getEndRoundRank();
    }

    public static NaSt callAbilityBeanFailStatusGet() {
        return PokemonStandards.getStrStrOnly(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getFailStatus());
    }

    public static boolean callAbilityBeanForbidUseBerryAgainstFoesGet(NaSt _str, int... _args) {
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).getForbidUseBerryAgainstFoes();
    }

    public static NaSt callAbilityBeanForwardStatusGet() {
        return PokemonStandards.getStrStrKey(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getForwardStatus());
    }

//    public static NaSt callAbilityBeanGetEffectSending(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetEffectSending(),_str,_args);
//    }

    public static String callAbilityBeanGetTrBonusStatRank() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrBonusStatRank(0);
    }

    public static String callAbilityBeanGetTrBoostStatRankEndRound() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrBoostStatRankEndRound(0);
    }

    public static String callAbilityBeanGetTrBoostStatRankProtected() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrBoostStatRankProtected(0);
    }

    public static String callAbilityBeanGetTrBreakFoeImmuneKey() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrBreakFoeImmuneKey(0);
    }

    public static String callAbilityBeanGetTrBreakFoeImmuneValue() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrBreakFoeImmuneValue(0);
    }

    public static String callAbilityBeanGetTrBreakProtectionMoves() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrBreakProtectionMoves(0);
    }

    public static String callAbilityBeanGetTrChangingBoostTypesNew() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrChangingBoostTypesNew(0);
    }

    public static String callAbilityBeanGetTrChangingBoostTypesOld() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrChangingBoostTypesOld(0);
    }

    public static String callAbilityBeanGetTrChgtTypeByWeatherKey() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrChgtTypeByWeatherKey(1);
    }

    public static String callAbilityBeanGetTrChgtTypeByWeatherValue() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrChgtTypeByWeatherValue(1);
    }

    public static String callAbilityBeanGetTrDivideStatusRoundKey() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrDivideStatusRoundKey(0);
    }

    public static String callAbilityBeanGetTrFailStatus() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrFailStatus(0);
    }

    public static String callAbilityBeanGetTrForwardStatusKey() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrForwardStatusKey(0);
    }

    public static String callAbilityBeanGetTrForwardStatusValue() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrForwardStatusValue(0);
    }

    public static String callAbilityBeanGetTrHealHpByTypeIfWeatherKey() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrHealHpByTypeIfWeatherKey(1);
    }

    public static String callAbilityBeanGetTrHealHpByTypeIfWeatherKeySec() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrHealHpByTypeIfWeatherKeySec(1);
    }

    public static String callAbilityBeanGetTrHealHpByWeatherKey() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrHealHpByWeatherKey(1);
    }

    public static String callAbilityBeanGetTrIgnAbility() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrIgnAbility(0);
    }

    public static String callAbilityBeanGetTrIgnFoeTeamMove() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrIgnFoeTeamMove(0);
    }

    public static String callAbilityBeanGetTrImmuAbility() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrImmuAbility(0);
    }

    public static String callAbilityBeanGetTrImmuAllyFromMoves() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrImmuAllyFromMoves(0);
    }

    public static String callAbilityBeanGetTrImmuLowStat() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrImmuLowStat(0);
    }

    public static String callAbilityBeanGetTrImmuLowStatIfStatusKey() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrImmuLowStatIfStatusKey(0);
    }

    public static String callAbilityBeanGetTrImmuLowStatIfStatusValue() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrImmuLowStatIfStatusValue(0);
    }

    public static String callAbilityBeanGetTrImmuLowStatisTypes() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrImmuLowStatisTypes(0);
    }

    public static String callAbilityBeanGetTrImmuLowStatisValue() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrImmuLowStatisValue(0);
    }

    public static String callAbilityBeanGetTrImmuMove() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrImmuMove(0);
    }

    public static String callAbilityBeanGetTrImmuMoveByWeather() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrImmuMoveByWeather(1);
    }

    public static String callAbilityBeanGetTrImmuRechargeRoundMoves() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrImmuRechargeRoundMoves(0);
    }

    public static String callAbilityBeanGetTrImmuStatus() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrImmuStatus(1);
    }

    public static String callAbilityBeanGetTrImmuStatusBeginRound() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrImmuStatusBeginRound(0);
    }

    public static String callAbilityBeanGetTrImmuStatusTypes() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrImmuStatusTypes(0);
    }

    public static String callAbilityBeanGetTrImmuStatusValue() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrImmuStatusValue(0);
    }

    public static String callAbilityBeanGetTrImmuStatusWeather() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrImmuStatusWeather(1);
    }

    public static String callAbilityBeanGetTrImmuTypeByWeather() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrImmuTypeByWeather(1);
    }

    public static String callAbilityBeanGetTrIncreasedPrio() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrIncreasedPrio(0);
    }

    public static String callAbilityBeanGetTrIncreasedPrioTypes() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrIncreasedPrioTypes(0);
    }

    public static String callAbilityBeanGetTrLowStatFoeHit() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrLowStatFoeHit(0);
    }

    public static String callAbilityBeanGetTrMaxStatisticsIfCh() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrMaxStatisticsIfCh(0);
    }

    public static String callAbilityBeanGetTrMultDamageFoe() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrMultDamageFoe(0);
    }

    public static String callAbilityBeanGetTrMultPowerMovesTypesGlobalKey() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrMultPowerMovesTypesGlobalKey(0);
    }

    public static String callAbilityBeanGetTrMultStat() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrMultStat(0);
    }

    public static String callAbilityBeanGetTrMultStatAlly() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrMultStatAlly(0);
    }

    public static String callAbilityBeanGetTrMultStatIfCatKey() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrMultStatIfCatKey(0);
    }

    public static String callAbilityBeanGetTrMultStatIfCatKeySec() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrMultStatIfCatKeySec(0);
    }

    public static String callAbilityBeanGetTrMultStatIfDamageCatKey() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrMultStatIfDamageCatKey(0);
    }

    public static String callAbilityBeanGetTrMultStatIfDamageCatKeySec() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrMultStatIfDamageCatKeySec(0);
    }

    public static String callAbilityBeanGetTrMultStatIfDamgeType() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrMultStatIfDamgeType(0);
    }

    public static String callAbilityBeanGetTrMultStatIfDamgeTypeSec() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrMultStatIfDamgeTypeSec(0);
    }

    public static String callAbilityBeanGetTrMultStatIfKoFoe() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrMultStatIfKoFoe(0);
    }

    public static String callAbilityBeanGetTrMultStatIfLowStat() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrMultStatIfLowStat(0);
    }

    public static String callAbilityBeanGetTrMultStatIfStatutRank() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrMultStatIfStatutRank(0);
    }

    public static String callAbilityBeanGetTrMultStatIfStatutRankSec() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrMultStatIfStatutRankSec(0);
    }

    public static String callAbilityBeanGetTrPokemon() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrPokemon(0);
    }

    public static String callAbilityBeanGetTrReversePowerTypesAbilities() {
        return ((AbilityBean) ((PokemonBeanStruct) reverseCase()).getInstance()).getTrReversePowerTypesAbilities(0);
    }

    public static String callAbilityBeanGetTrSingleStatus() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrSingleStatus(1);
    }

    public static String callAbilityBeanGetTrWeather() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getTrWeather(0);
    }

    public static boolean callAbilityBeanGiveItemToAllyHavingUsedGet(NaSt _str, int... _args) {
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).getGiveItemToAllyHavingUsed();
    }

    public static NaSt callAbilityBeanHealHpByTypeIfWeatherGet() {
        return PokemonStandards.getWeatherTypeRateMap(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getHealHpByTypeIfWeather());
    }

    public static NaSt callAbilityBeanHealHpByWeatherGet() {
        return PokemonStandards.getStrRate(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getHealHpByWeather());
    }

    public static Rate callAbilityBeanHealHpWhileUsingBerryGet() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getHealHpWhileUsingBerry();
    }

    public static Rate callAbilityBeanHealedHpRateBySwitchGet() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getHealedHpRateBySwitch();
    }

    public static boolean callAbilityBeanHealedStatusBySwitchGet(NaSt _str, int... _args) {
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).getHealedStatusBySwitch();
    }

    public static NaSt callAbilityBeanIgnAbilityGet() {
        return PokemonStandards.getKeys(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getIgnAbility());
    }

    public static boolean callAbilityBeanIgnFoeStatisBoostGet(NaSt _str, int... _args) {
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).getIgnFoeStatisBoost();
    }

    public static NaSt callAbilityBeanIgnFoeTeamMoveGet() {
        return PokemonStandards.getKeys(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getIgnFoeTeamMove());
    }

    public static NaSt callAbilityBeanImmuAbilityGet() {
        return PokemonStandards.getKeys(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getImmuAbility());
    }

    public static NaSt callAbilityBeanImmuAllyFromMovesGet() {
        return PokemonStandards.getKeys(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getImmuAllyFromMoves());
    }

    public static boolean callAbilityBeanImmuChGet(NaSt _str, int... _args) {
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).getImmuCh();
    }

    public static boolean callAbilityBeanImmuDamageAllyMovesGet(NaSt _str, int... _args) {
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).getImmuDamageAllyMoves();
    }

    public static boolean callAbilityBeanImmuDamageRecoilGet(NaSt _str, int... _args) {
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).getImmuDamageRecoil();
    }

    public static boolean callAbilityBeanImmuDamageTrappingMovesGet(NaSt _str, int... _args) {
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).getImmuDamageTrappingMoves();
    }

    public static NaSt callAbilityBeanImmuLowStatGet() {
        return PokemonStandards.getKeys(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getImmuLowStat());
    }

    public static NaSt callAbilityBeanImmuLowStatIfStatusGet() {
        return PokemonStandards.arrId(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getImmuLowStatIfStatus().size());
    }

    public static NaSt callAbilityBeanImmuLowStatisTypesGet() {
        return PokemonStandards.getStrListStaList(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getImmuLowStatisTypes());
    }

    public static NaSt callAbilityBeanImmuMoveGet() {
        return PokemonStandards.getKeys(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getImmuMove());
    }

    public static NaSt callAbilityBeanImmuMoveTypesByWeatherGet() {
        return PokemonStandards.getStrListStaList(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getImmuMoveTypesByWeather());
    }

    public static boolean callAbilityBeanImmuRechargeRoundGet(NaSt _str, int... _args) {
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).getImmuRechargeRound();
    }

    public static NaSt callAbilityBeanImmuRechargeRoundMovesGet() {
        return PokemonStandards.getKeys(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getImmuRechargeRoundMoves());
    }

    public static NaSt callAbilityBeanImmuStatusBeginRoundGet() {
        return PokemonStandards.getKeys(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getImmuStatusBeginRound());
    }

    public static NaSt callAbilityBeanImmuStatusGet() {
        return PokemonStandards.getStrListStaList(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getImmuStatus());
    }

    public static NaSt callAbilityBeanImmuStatusTypesGet() {
        return PokemonStandards.getStrListStaList(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getImmuStatusTypes());
    }

    public static boolean callAbilityBeanImmuSufferedDamageLowEffGet(NaSt _str, int... _args) {
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).getImmuSufferedDamageLowEff();
    }

    public static NaSt callAbilityBeanImmuWeatherGet() {
        return PokemonStandards.getKeys(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getImmuWeather());
    }

    public static NaSt callAbilityBeanIncreasedPrioGet() {
        return PokemonStandards.getStrLong(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getIncreasedPrio());
    }

    public static NaSt callAbilityBeanIncreasedPrioTypesGet() {
        return PokemonStandards.getStrLong(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getIncreasedPrioTypes());
    }

    public static boolean callAbilityBeanInflictingDamageInsteadOfSufferingGet(NaSt _str, int... _args) {
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).getInflictingDamageInsteadOfSuffering();
    }

    public static boolean callAbilityBeanIsChgtTypeByWeather(int _v) {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).isChgtTypeByWeather(_v);
    }

    public static boolean callAbilityBeanIsHealHpByTypeIfWeather(int _v) {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).isHealHpByTypeIfWeather(_v);
    }

    public static boolean callAbilityBeanIsHealHpByWeather(int _v) {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).isHealHpByWeather(_v);
    }

    public static boolean callAbilityBeanIsMoveByStatus(int _v) {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).isMoveByStatus(_v);
    }

    public static boolean callAbilityBeanIsMoveByWeather(int _v) {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).isMoveByWeather(_v);
    }

    public static boolean callAbilityBeanIsStatus(int _v) {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).isStatus(_v);
    }

    public static NaSt callAbilityBeanLowStatFoeHitGet() {
        return PokemonStandards.getStaByte(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getLowStatFoeHit());
    }

    public static NaSt callAbilityBeanMapVarsFailEndRoundGet() {
        return PokemonStandards.getStrStr(((AbilityBean) ((PokemonBeanStruct) directCaseEndRound()).getInstance()).getEndRoundCommon().getMapVarsFailEndRound());
    }

    public static NaSt callAbilityBeanMapVarsGet() {
        return PokemonStandards.getStrStr(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getMapVars());
    }

    public static Rate callAbilityBeanMaxHpForUsingBerryGet() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getMaxHpForUsingBerry();
    }

    public static NaSt callAbilityBeanMaxStatisticsIfChGet() {
        return PokemonStandards.getKeys(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getMaxStatisticsIfCh());
    }

    public static Rate callAbilityBeanMultAllyDamageGet() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getMultAllyDamage();
    }

    public static Rate callAbilityBeanMultDamageChGet() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getMultDamageCh();
    }

    public static NaSt callAbilityBeanMultDamageFoeGet() {
        return PokemonStandards.getStrRate(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getMultDamageFoe());
    }

    public static String callAbilityBeanMultDamageGet() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getMultDamage();
    }

    public static Rate callAbilityBeanMultEvtRateChGet() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getMultEvtRateCh();
    }

    public static Rate callAbilityBeanMultEvtRateSecEffectOwnerGet() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getMultEvtRateSecEffectOwner();
    }

    public static String callAbilityBeanMultPowerGet() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getMultPower();
    }

    public static NaSt callAbilityBeanMultPowerMovesTypesGlobalGet() {
        return PokemonStandards.getStrRate(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getMultPowerMovesTypesGlobal());
    }

    public static Rate callAbilityBeanMultStabGet() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getMultStab();
    }

    public static NaSt callAbilityBeanMultStatAllyGet() {
        return PokemonStandards.getStaRate(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getMultStatAlly());
    }

    public static NaSt callAbilityBeanMultStatGet() {
        return PokemonStandards.getStaStr(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getMultStat());
    }

    public static NaSt callAbilityBeanMultStatIfCatGet() {
        return PokemonStandards.getWcRateMap(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getMultStatIfCat());
    }

    public static NaSt callAbilityBeanMultStatIfDamageCatGet() {
        return PokemonStandards.getWcByteMap(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getMultStatIfDamageCat());
    }

    public static NaSt callAbilityBeanMultStatIfDamgeTypeGet() {
        return PokemonStandards.getWcByteMap(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getMultStatIfDamgeType());
    }

    public static NaSt callAbilityBeanMultStatIfKoFoeGet() {
        return PokemonStandards.getStaByte(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getMultStatIfKoFoe());
    }

    public static NaSt callAbilityBeanMultStatIfLowStatGet() {
        return PokemonStandards.getStaByte(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getMultStatIfLowStat());
    }

    public static NaSt callAbilityBeanMultStatIfStatutRankGet() {
        return PokemonStandards.getWcByteMap(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getMultStatIfStatutRank());
    }

    public static Rate callAbilityBeanMultSufferedDamageSuperEffGet() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getMultSufferedDamageSuperEff();
    }

    public static Rate callAbilityBeanMultVarBoostGet() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getMultVarBoost();
    }

    public static boolean callAbilityBeanMumyGet(NaSt _str, int... _args) {
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).getMumy();
    }

    public static boolean callAbilityBeanNbHitsGet(NaSt _str, int... _args) {
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).getNbHits();
    }

    public static long callAbilityBeanNbUsedPpGet() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getNbUsedPp();
    }

    public static boolean callAbilityBeanNbUsedPpInt(NaSt _str, int... _args) {
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).nbUsedPpInt();
    }

    public static boolean callAbilityBeanPlateGet(NaSt _str, int... _args) {
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).getPlate();
    }

    public static NaSt callAbilityBeanPokemonGet() {
        return PokemonStandards.getKeys(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getPokemon());
    }

    public static NaSt callAbilityBeanReasonsEndRoundGet() {
        return BeanNatCommonLgNames.getStringArray(((AbilityBean) ((PokemonBeanStruct) directCaseEndRound()).getInstance()).getEndRoundCommon().getReasonsEndRound());
    }

//    public static Struct callAbilityBeanRecoilDamageFoeByKoOwnerGet() {
//        return BeanPokemonCommonTs.callLongs(new AbilityBeanRecoilDamageFoeByKoOwnerGet(),directCase());
//    }

    public static Rate callAbilityBeanRecoilDamageFoeGet() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getRecoilDamageFoe();
    }

    public static NaSt callAbilityBeanReverseEffectsPowerMovesTypesGlobalAbilitiesGet() {
        return PokemonStandards.getKeys(((AbilityBean) ((PokemonBeanStruct) reverseCase()).getInstance()).getReverseEffectsPowerMovesTypesGlobalAbilities());
    }

    public static boolean callAbilityBeanReverseEffectsPowerMovesTypesGlobalGet(NaSt _str, int... _args) {
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).getReverseEffectsPowerMovesTypesGlobal();
    }

    public static boolean callAbilityBeanSendingNoGet() {
        return ((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getSending();
    }

    public static boolean callAbilityBeanSendingGet() {
        return ((AbilityBean) ((PokemonBeanStruct) abNoStatSend()).getInstance()).getSending();
    }

    public static NaSt callAbilityBeanSingleStatusGet() {
        return PokemonStandards.getStrRate(((AbilityBean) ((PokemonBeanStruct) directCase()).getInstance()).getSingleStatus());
    }

    public static boolean callAbilityBeanSlowingGet(NaSt _str, int... _args) {
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).getSlowing();
    }

    public static boolean callAbilityBeanTakeItemByDamagingMoveGet(NaSt _str, int... _args) {
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).getTakeItemByDamagingMove();
    }

    public static String callAbilityBeanTypeForMovesGet(NaSt _str, int... _args) {
        return ((AbilityBean) ((PokemonBeanStruct) _str).getInstance()).getTypeForMoves().getTranslation();
    }


    public static StringMap<NaSt> beanToItBaseSend(PkData _pk) {
        StringMap<NaSt> map_ = beanToAbility(_pk);
        ((CommonBean) ((PokemonBeanStruct) map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.SENDING, new TranslationsFile());
        ((CommonBean) ((PokemonBeanStruct) map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.SENDING, new TranslationsFile());
//        EffectWhileSendingBean send_ = new EffectWhileSendingBean();
//        send_.setBuilder(((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder());
//        map_.addEntry(AikiBeansEffectsStd.EFFECT_SENDING, _pk.bean(send_, EN));
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
        return transitToAllAbilities(pk_, all_, 0);
    }

    protected static NaSt directCase() {
        PkData pk_ = pkDataByFacade(feedDbAbility());
        StringMap<NaSt> all_ = beanToAbility(pk_);
        return transitToAllAbilities(pk_, all_, 0);
    }

    protected static NaSt directCase(boolean _forbidUseBerryAgainstFoes, boolean _chgtTypeByDamage, boolean _ignFoeStatisBoost, boolean _immuCh, boolean _immuDamageTrappingMoves, boolean _immuDamageAllyMoves, boolean _immuDamageRecoil, boolean _copyMovesTypes, boolean _reverseEffectsPowerMovesTypesGlobal, boolean _takeItemByDamagingMove, boolean _giveItemToAllyHavingUsed, boolean _inflictingDamageInsteadOfSuffering, boolean _nbHits, boolean _breakProtection, boolean _plate, boolean _healedStatusBySwitch, boolean _achievedDisappearedPk, boolean _mumy, boolean _immuRechargeRound, boolean _slowing, boolean _immuSufferedDamageLowEff, boolean _cancelSecEffectOther, boolean _cancelSecEffectOwner, int _nbUsedPp, int _decreaseNecStepsHatch, String _typeForMoves) {
        PkData pk_ = pkDataByFacade(feedDbAbility(_forbidUseBerryAgainstFoes, _chgtTypeByDamage, _ignFoeStatisBoost, _immuCh, _immuDamageTrappingMoves, _immuDamageAllyMoves, _immuDamageRecoil, _copyMovesTypes, _reverseEffectsPowerMovesTypesGlobal, _takeItemByDamagingMove, _giveItemToAllyHavingUsed, _inflictingDamageInsteadOfSuffering, _nbHits, _breakProtection, _plate, _healedStatusBySwitch, _achievedDisappearedPk, _mumy, _immuRechargeRound, _slowing, _immuSufferedDamageLowEff, _cancelSecEffectOther, _cancelSecEffectOwner, _nbUsedPp, _decreaseNecStepsHatch, _typeForMoves));
        StringMap<NaSt> all_ = beanToAbility(pk_);
        return transitToAllAbilities(pk_, all_, 0);
    }

    protected static NaSt directCaseEndRound() {
        PkData pk_ = pkDataByFacade(feedDbAbilityEndRound());
        StringMap<NaSt> all_ = beanToAbility(pk_);
        ((CommonBean) ((PokemonBeanStruct) all_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_ENDROUND, new TranslationsFile());
        ((CommonBean) ((PokemonBeanStruct) all_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_ENDROUND, new TranslationsFile());
        return transitToAllAbilitiesQuick(pk_, all_, 0);
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
    public static EffectWhileSendingBean healSimpleNoStatSend() {
        PkData pk_ = pkDataByFacade(feedDbAbilityNoStat());
        StringMap<NaSt> all_ = beanToItBaseSend(pk_);
        NaSt res_ = transitToAllAbilitiesQuick(pk_, all_, 0);
//        callAbilityBeanEffectSendBeanGet(res_);
//        NaSt send_ = all_.getVal(AikiBeansEffectsStd.EFFECT_SENDING);
//        callEffectWhileSendingBeanEffectSet(send_,callAbilityBeanGetEffectSending(res_));
//        beforeDisplaying(send_);
        return ((PokemonBeanStruct) res_).getInstance().effSending(((AbilityBean) ((PokemonBeanStruct) res_).getInstance()).getEffectSending());
    }

    public static NaSt abNoStatSend() {
        PkData pk_ = pkDataByFacade(feedDbAbilityNoStat());
        StringMap<NaSt> all_ = beanToItBaseSend(pk_);
        return transitToAllAbilitiesQuick(pk_, all_, 0);
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
        return feedDbAbility(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 1, 1, "");
    }

    protected static FacadeGame feedDbAbility(boolean _rech, String _ab) {
        return feedDbAbility(_rech, _ab, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, 1, 1, "");
    }

    protected static FacadeGame feedDbAbility(boolean _forbidUseBerryAgainstFoes, boolean _chgtTypeByDamage, boolean _ignFoeStatisBoost, boolean _immuCh, boolean _immuDamageTrappingMoves, boolean _immuDamageAllyMoves, boolean _immuDamageRecoil, boolean _copyMovesTypes, boolean _reverseEffectsPowerMovesTypesGlobal, boolean _takeItemByDamagingMove, boolean _giveItemToAllyHavingUsed, boolean _inflictingDamageInsteadOfSuffering, boolean _nbHits, boolean _breakProtection, boolean _plate, boolean _healedStatusBySwitch, boolean _achievedDisappearedPk, boolean _mumy, boolean _immuRechargeRound, boolean _slowing, boolean _immuSufferedDamageLowEff, boolean _cancelSecEffectOther, boolean _cancelSecEffectOwner, int _nbUsedPp, int _decreaseNecStepsHatch, String _typeForMoves) {
        return feedDbAbility(true, A_ABILITY, _forbidUseBerryAgainstFoes, _chgtTypeByDamage, _ignFoeStatisBoost, _immuCh, _immuDamageTrappingMoves, _immuDamageAllyMoves, _immuDamageRecoil, _copyMovesTypes, _reverseEffectsPowerMovesTypesGlobal, _takeItemByDamagingMove, _giveItemToAllyHavingUsed, _inflictingDamageInsteadOfSuffering, _nbHits, _breakProtection, _plate, _healedStatusBySwitch, _achievedDisappearedPk, _mumy, _immuRechargeRound, _slowing, _immuSufferedDamageLowEff, _cancelSecEffectOther, _cancelSecEffectOwner, _nbUsedPp, _decreaseNecStepsHatch, _typeForMoves);
    }

    protected static FacadeGame feedDbAbility(boolean _rech, String _ab, boolean _forbidUseBerryAgainstFoes, boolean _chgtTypeByDamage, boolean _ignFoeStatisBoost, boolean _immuCh, boolean _immuDamageTrappingMoves, boolean _immuDamageAllyMoves, boolean _immuDamageRecoil, boolean _copyMovesTypes, boolean _reverseEffectsPowerMovesTypesGlobal, boolean _takeItemByDamagingMove, boolean _giveItemToAllyHavingUsed, boolean _inflictingDamageInsteadOfSuffering, boolean _nbHits, boolean _breakProtection, boolean _plate, boolean _healedStatusBySwitch, boolean _achievedDisappearedPk, boolean _mumy, boolean _immuRechargeRound, boolean _slowing, boolean _immuSufferedDamageLowEff, boolean _cancelSecEffectOther, boolean _cancelSecEffectOwner, int _nbUsedPp, int _decreaseNecStepsHatch, String _typeForMoves) {
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
        _facade.getData().completeMembers(S_STA_REL, staRel(""));
        _facade.getData().completeMembers(S_STA_SIM, staSimple(""));
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
        e_.setFailEndRound(VAR_PREFIX + MessagesDataBaseConstants.DEF_TEMPS_TOUR);
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
