package aiki.beans.abilities;

import aiki.beans.*;
import aiki.beans.effects.EffectWhileSendingBean;
import aiki.db.*;
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
import code.maths.LgInt;
import code.maths.Rate;
import code.scripts.pages.aiki.MessagesPkBean;
import code.sml.util.TranslationsFile;
import aiki.comparators.*;
import code.util.*;

public abstract class InitDbAbility extends InitDbAbilities {

    public static boolean callAbilityBeanAchievedDisappearedPkGet(AbilityBean _str, int... _args) {
        return _str.getAchievedDisappearedPk();
    }

    public static DictionaryComparator<TranslatedKey,Long> callAbilityBeanBonusStatRankGet() {
        return directCase().getBonusStatRank();
    }

    public static DictionaryComparator<TranslatedKey,Long> callAbilityBeanBoostStatRankEndRoundGet() {
        return directCase().getBoostStatRankEndRound();
    }

    public static DictionaryComparator<TranslatedKey,Long> callAbilityBeanBoostStatRankProtectedGet() {
        return directCase().getBoostStatRankProtected();
    }

    public static CustList<TranslatedKeyPair> callAbilityBeanBreakFoeImmuneGet() {
        return directCase().getBreakFoeImmune();
    }

    public static boolean callAbilityBeanBreakProtectionGet(AbilityBean _str, int... _args) {
        return _str.getBreakProtection();
    }

    public static CustList<TranslatedKey> callAbilityBeanBreakProtectionMovesGet() {
        return directCase().getBreakProtectionMoves();
    }

    public static boolean callAbilityBeanCancelSecEffectOtherGet(AbilityBean _str, int... _args) {
        return _str.getCancelSecEffectOther();
    }

    public static boolean callAbilityBeanCancelSecEffectOwnerGet(AbilityBean _str, int... _args) {
        return _str.getCancelSecEffectOwner();
    }

    public static AbsMap<TranslatedKey,TypeDamageBoostKey> callAbilityBeanChangingBoostTypesGet() {
        return directCase().getChangingBoostTypes();
    }

    public static boolean callAbilityBeanChgtTypeByDamageGet(AbilityBean _str, int... _args) {
        return _str.getChgtTypeByDamage();
    }

    public static DictionaryComparator<TranslatedKey,TranslatedKey> callAbilityBeanChgtTypeByWeatherGet() {
        return directCase().getChgtTypeByWeather();
    }

    public static String callAbilityBeanClickBreakProtectionMoves() {
        return callAbilityBeanClickBreakProtectionMoves(directCase());
    }

    public static String callAbilityBeanClickBreakProtectionMoves(AbilityBean _str) {
        return _str.clickBreakProtectionMoves(0);
    }

    public static String callAbilityBeanClickBreakProtectionMovesId() {
        AbilityBean b_ = directCase();
        callAbilityBeanClickBreakProtectionMoves(b_);
        return getValMoveId(b_);
    }

    public static String callAbilityBeanClickChgtTypeByWeatherKey() {
        return callAbilityBeanClickChgtTypeByWeatherKey(directCase());
    }

    public static String callAbilityBeanClickChgtTypeByWeatherKey(AbilityBean _str) {
        return _str.clickChgtTypeByWeatherKey(1);
    }

    public static String callAbilityBeanClickChgtTypeByWeatherKeyId() {
        AbilityBean b_ = directCase();
        callAbilityBeanClickChgtTypeByWeatherKey(b_);
        return getValMoveId(b_);
    }

    public static String callAbilityBeanClickDivideStatusRoundKey() {
        return callAbilityBeanClickDivideStatusRoundKey(directCase());
    }

    public static String callAbilityBeanClickDivideStatusRoundKey(AbilityBean _str) {
        return _str.clickDivideStatusRoundKey(0);
    }

    public static String callAbilityBeanClickDivideStatusRoundKeyId() {
        AbilityBean b_ = directCase();
        callAbilityBeanClickDivideStatusRoundKey(b_);
        return getValStatusId(b_);
    }

    public static String callAbilityBeanClickFailStatus() {
        return callAbilityBeanClickFailStatus(directCase());
    }

    public static String callAbilityBeanClickFailStatus(AbilityBean _str) {
        return _str.clickFailStatus(0);
    }

    public static String callAbilityBeanClickFailStatusId() {
        AbilityBean b_ = directCase();
        callAbilityBeanClickFailStatus(b_);
        return getValStatusId(b_);
    }

    public static String callAbilityBeanClickForwardStatusKey() {
        return callAbilityBeanClickForwardStatusKey(directCase());
    }

    public static String callAbilityBeanClickForwardStatusKey(AbilityBean _str) {
        return _str.clickForwardStatusKey(0);
    }

    public static String callAbilityBeanClickForwardStatusKeyId() {
        AbilityBean b_ = directCase();
        callAbilityBeanClickForwardStatusKey(b_);
        return getValStatusId(b_);
    }

    public static String callAbilityBeanClickForwardStatusValue() {
        return callAbilityBeanClickForwardStatusValue(directCase());
    }

    public static String callAbilityBeanClickForwardStatusValue(AbilityBean _str) {
        return _str.clickForwardStatusValue(0);
    }

    public static String callAbilityBeanClickForwardStatusValueId() {
        AbilityBean b_ = directCase();
        callAbilityBeanClickForwardStatusValue(b_);
        return getValStatusId(b_);
    }

    public static String callAbilityBeanClickHealHpByTypeIfWeatherKey() {
        return callAbilityBeanClickHealHpByTypeIfWeatherKey(directCase());
    }

    public static String callAbilityBeanClickHealHpByTypeIfWeatherKey(AbilityBean _str) {
        return _str.clickHealHpByTypeIfWeatherKey(1);
    }

    public static String callAbilityBeanClickHealHpByTypeIfWeatherKeyId() {
        AbilityBean b_ = directCase();
        callAbilityBeanClickHealHpByTypeIfWeatherKey(b_);
        return getValMoveId(b_);
    }

    public static String callAbilityBeanClickHealHpByWeatherKey() {
        return callAbilityBeanClickHealHpByWeatherKey(directCase());
    }

    public static String callAbilityBeanClickHealHpByWeatherKey(AbilityBean _str) {
        return _str.clickHealHpByWeatherKey(1);
    }

    public static String callAbilityBeanClickHealHpByWeatherKeyId() {
        AbilityBean b_ = directCase();
        callAbilityBeanClickHealHpByWeatherKey(b_);
        return getValMoveId(b_);
    }

    public static String callAbilityBeanClickIgnAbility() {
        return callAbilityBeanClickIgnAbility(directCase());
    }

    public static String callAbilityBeanClickIgnAbility(AbilityBean _str) {
        return _str.clickIgnAbility(0);
    }

    public static String callAbilityBeanClickIgnAbilityId() {
        AbilityBean b_ = directCase();
        callAbilityBeanClickIgnAbility(b_);
        return getValAbilityId(b_);
    }

    public static String callAbilityBeanClickIgnFoeTeamMove() {
        return callAbilityBeanClickIgnFoeTeamMove(directCase());
    }

    public static String callAbilityBeanClickIgnFoeTeamMove(AbilityBean _str) {
        return _str.clickIgnFoeTeamMove(0);
    }

    public static String callAbilityBeanClickIgnFoeTeamMoveId() {
        AbilityBean b_ = directCase();
        callAbilityBeanClickIgnFoeTeamMove(b_);
        return getValMoveId(b_);
    }

    public static String callAbilityBeanClickImmuAbility() {
        return callAbilityBeanClickImmuAbility(directCase());
    }

    public static String callAbilityBeanClickImmuAbility(AbilityBean _str) {
        return _str.clickImmuAbility(0);
    }

    public static String callAbilityBeanClickImmuAbilityId() {
        AbilityBean b_ = directCase();
        callAbilityBeanClickImmuAbility(b_);
        return getValAbilityId(b_);
    }

    public static String callAbilityBeanClickImmuAllyFromMoves() {
        return callAbilityBeanClickImmuAllyFromMoves(directCase());
    }

    public static String callAbilityBeanClickImmuAllyFromMoves(AbilityBean _str) {
        return _str.clickImmuAllyFromMoves(0);
    }

    public static String callAbilityBeanClickImmuAllyFromMovesId() {
        AbilityBean b_ = directCase();
        callAbilityBeanClickImmuAllyFromMoves(b_);
        return getValMoveId(b_);
    }

    public static String callAbilityBeanClickImmuLowStatIfStatusKey() {
        return callAbilityBeanClickImmuLowStatIfStatusKey(directCase());
    }

    public static String callAbilityBeanClickImmuLowStatIfStatusKey(AbilityBean _str) {
        return _str.clickImmuLowStatIfStatusKey(0);
    }

    public static String callAbilityBeanClickImmuLowStatIfStatusKeyId() {
        AbilityBean b_ = directCase();
        callAbilityBeanClickImmuLowStatIfStatusKey(b_);
        return getValStatusId(b_);
    }

    public static String callAbilityBeanClickImmuMove() {
        return callAbilityBeanClickImmuMove(directCase());
    }

    public static String callAbilityBeanClickImmuMove(AbilityBean _str) {
        return _str.clickImmuMove(0);
    }

    public static String callAbilityBeanClickImmuMoveId() {
        AbilityBean b_ = directCase();
        callAbilityBeanClickImmuMove(b_);
        return getValMoveId(b_);
    }

    public static String callAbilityBeanClickImmuMoveByWeather() {
        return callAbilityBeanClickImmuMoveByWeather(directCase());
    }

    public static String callAbilityBeanClickImmuMoveByWeather(AbilityBean _str) {
        return _str.clickImmuMoveByWeather(1);
    }

    public static String callAbilityBeanClickImmuMoveByWeatherId() {
        AbilityBean b_ = directCase();
        callAbilityBeanClickImmuMoveByWeather(b_);
        return getValMoveId(b_);
    }

    public static String callAbilityBeanClickImmuRechargeRoundMoves() {
        return callAbilityBeanClickImmuRechargeRoundMoves(directCase());
    }

    public static String callAbilityBeanClickImmuRechargeRoundMoves(AbilityBean _str) {
        return _str.clickImmuRechargeRoundMoves(0);
    }

    public static String callAbilityBeanClickImmuRechargeRoundMovesId() {
        AbilityBean b_ = directCase();
        callAbilityBeanClickImmuRechargeRoundMoves(b_);
        return getValMoveId(b_);
    }

    public static String callAbilityBeanClickImmuStatus() {
        return callAbilityBeanClickImmuStatus(directCase());
    }

    public static String callAbilityBeanClickImmuStatus(AbilityBean _str) {
        return _str.clickImmuStatus(1);
    }

    public static String callAbilityBeanClickImmuStatusId() {
        AbilityBean b_ = directCase();
        callAbilityBeanClickImmuStatus(b_);
        return getValStatusId(b_);
    }

    public static String callAbilityBeanClickImmuStatusBeginRound() {
        return callAbilityBeanClickImmuStatusBeginRound(directCase());
    }

    public static String callAbilityBeanClickImmuStatusBeginRound(AbilityBean _str) {
        return _str.clickImmuStatusBeginRound(0);
    }

    public static String callAbilityBeanClickImmuStatusBeginRoundId() {
        AbilityBean b_ = directCase();
        callAbilityBeanClickImmuStatusBeginRound(b_);
        return getValStatusId(b_);
    }

    public static String callAbilityBeanClickImmuStatusTypes() {
        return callAbilityBeanClickImmuStatusTypes(directCase());
    }

    public static String callAbilityBeanClickImmuStatusTypes(AbilityBean _str) {
        return _str.clickImmuStatusTypes(0);
    }

    public static String callAbilityBeanClickImmuStatusTypesId() {
        AbilityBean b_ = directCase();
        callAbilityBeanClickImmuStatusTypes(b_);
        return getValStatusId(b_);
    }

    public static String callAbilityBeanClickImmuStatusWeather() {
        return callAbilityBeanClickImmuStatusWeather(directCase());
    }

    public static String callAbilityBeanClickImmuStatusWeather(AbilityBean _str) {
        return _str.clickImmuStatusWeather(1);
    }

    public static String callAbilityBeanClickImmuStatusWeatherId() {
        AbilityBean b_ = directCase();
        callAbilityBeanClickImmuStatusWeather(b_);
        return getValMoveId(b_);
    }

    public static String callAbilityBeanClickIndex() {
        AbilityBean b_ = directCase();
        return navigateData(new AbilityBeanClickIndex(b_), b_);
    }

    public static String callAbilityBeanClickMultStatIfStatutRankSec() {
        return callAbilityBeanClickMultStatIfStatutRankSec(directCase());
    }

    public static String callAbilityBeanClickMultStatIfStatutRankSec(AbilityBean _str) {
        return _str.clickMultStatIfStatutRankSec(0);
    }

    public static String callAbilityBeanClickMultStatIfStatutRankSecId() {
        AbilityBean b_ = directCase();
        callAbilityBeanClickMultStatIfStatutRankSec(b_);
        return getValStatusId(b_);
    }

    public static String callAbilityBeanClickPokemon() {
        return callAbilityBeanClickPokemon(directCase());
    }

    public static String callAbilityBeanClickPokemon(AbilityBean _str) {
        return _str.clickPokemon(0);
    }

    public static String callAbilityBeanClickPokemonId() {
        AbilityBean b_ = directCase();
        callAbilityBeanClickPokemon(b_);
        return getValPkId(b_);
    }

    public static String callAbilityBeanClickReversePowerTypesAbilities() {
        return callAbilityBeanClickReversePowerTypesAbilities(reverseCase());
    }

    public static String callAbilityBeanClickReversePowerTypesAbilities(AbilityBean _str) {
        return _str.clickReversePowerTypesAbilities(0);
    }

    public static String callAbilityBeanClickReversePowerTypesAbilitiesId() {
        AbilityBean bean_ = reverseCase();
        callAbilityBeanClickReversePowerTypesAbilities(bean_);
        return getValAbilityId(bean_);
    }

    public static String callAbilityBeanClickSingleStatus() {
        return callAbilityBeanClickSingleStatus(directCase());
    }

    public static String callAbilityBeanClickSingleStatus(AbilityBean _str) {
        return _str.clickSingleStatus(1);
    }

    public static String callAbilityBeanClickSingleStatusId() {
        AbilityBean b_ = directCase();
        callAbilityBeanClickSingleStatus(b_);
        return getValStatusId(b_);
    }

    public static String callAbilityBeanClickWeather() {
        return callAbilityBeanClickWeather(directCase());
    }

    public static String callAbilityBeanClickWeather(AbilityBean _str) {
        return _str.clickWeather(0);
    }

    public static String callAbilityBeanClickWeatherId() {
        AbilityBean b_ = directCase();
        callAbilityBeanClickWeather(b_);
        return getValMoveId(b_);
    }

    public static boolean callAbilityBeanCopyMovesTypesGet(AbilityBean _str, int... _args) {
        return _str.getCopyMovesTypes();
    }

    public static long callAbilityBeanDecreaseNecStepsHatchGet() {
        return directCase().getDecreaseNecStepsHatch();
    }

    public static boolean callAbilityBeanDecreaseNecStepsHatchInt(AbilityBean _str, int... _args) {
        return _str.decreaseNecStepsHatchInt();
    }

    public static Rate callAbilityBeanDefEffGet() {
        return directCase().getDefEff();
    }

    public static String callAbilityBeanDisplayNameGet() {
        return directCase().getDisplayName();
    }

    public static DictionaryComparator<TranslatedKey,Rate> callAbilityBeanDivideStatusRoundGet() {
        return directCase().getDivideStatusRound();
    }

//    public static NaSt callAbilityBeanEffectSendBeanGet(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new AbilityBeanEffectSendBeanGet(),_str,_args);
//    }

    public static boolean callAbilityBeanEndRoundGet() {
        return directCaseEndRound().getEndRoundCommon().getEndRound();
    }

    public static long callAbilityBeanEndRoundRankGet() {
        return directCaseEndRound().getEndRoundCommon().getEndRoundRank();
    }

    public static boolean callAbilityBeanEndRoundGetNo() {
        return directCase().getEndRoundCommon().getEndRound();
    }

    public static long callAbilityBeanEndRoundRankGetNo() {
        return directCase().getEndRoundCommon().getEndRoundRank();
    }

    public static DictionaryComparator<TranslatedKey,String> callAbilityBeanFailStatusGet() {
        return directCase().getFailStatus();
    }

    public static boolean callAbilityBeanForbidUseBerryAgainstFoesGet(AbilityBean _str, int... _args) {
        return _str.getForbidUseBerryAgainstFoes();
    }

    public static DictionaryComparator<TranslatedKey,TranslatedKey> callAbilityBeanForwardStatusGet() {
        return directCase().getForwardStatus();
    }

//    public static NaSt callAbilityBeanGetEffectSending(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetEffectSending(),_str,_args);
//    }

    public static String callAbilityBeanGetTrBonusStatRank() {
        return directCase().getTrBonusStatRank(0);
    }

    public static String callAbilityBeanGetTrBoostStatRankEndRound() {
        return directCase().getTrBoostStatRankEndRound(0);
    }

    public static String callAbilityBeanGetTrBoostStatRankProtected() {
        return directCase().getTrBoostStatRankProtected(0);
    }

    public static String callAbilityBeanGetTrBreakFoeImmuneKey() {
        return directCase().getTrBreakFoeImmuneKey(0);
    }

    public static String callAbilityBeanGetTrBreakFoeImmuneValue() {
        return directCase().getTrBreakFoeImmuneValue(0);
    }

    public static String callAbilityBeanGetTrBreakProtectionMoves() {
        return directCase().getTrBreakProtectionMoves(0);
    }

    public static String callAbilityBeanGetTrChangingBoostTypesNew() {
        return directCase().getTrChangingBoostTypesNew(0);
    }

    public static String callAbilityBeanGetTrChangingBoostTypesOld() {
        return directCase().getTrChangingBoostTypesOld(0);
    }

    public static String callAbilityBeanGetTrChgtTypeByWeatherKey() {
        return directCase().getTrChgtTypeByWeatherKey(1);
    }

    public static String callAbilityBeanGetTrChgtTypeByWeatherValue() {
        return directCase().getTrChgtTypeByWeatherValue(1);
    }

    public static String callAbilityBeanGetTrDivideStatusRoundKey() {
        return directCase().getTrDivideStatusRoundKey(0);
    }

    public static String callAbilityBeanGetTrFailStatus() {
        return directCase().getTrFailStatus(0);
    }

    public static String callAbilityBeanGetTrForwardStatusKey() {
        return directCase().getTrForwardStatusKey(0);
    }

    public static String callAbilityBeanGetTrForwardStatusValue() {
        return directCase().getTrForwardStatusValue(0);
    }

    public static String callAbilityBeanGetTrHealHpByTypeIfWeatherKey() {
        return directCase().getTrHealHpByTypeIfWeatherKey(1);
    }

    public static String callAbilityBeanGetTrHealHpByTypeIfWeatherKeySec() {
        return directCase().getTrHealHpByTypeIfWeatherKeySec(1);
    }

    public static String callAbilityBeanGetTrHealHpByWeatherKey() {
        return directCase().getTrHealHpByWeatherKey(1);
    }

    public static String callAbilityBeanGetTrIgnAbility() {
        return directCase().getTrIgnAbility(0);
    }

    public static String callAbilityBeanGetTrIgnFoeTeamMove() {
        return directCase().getTrIgnFoeTeamMove(0);
    }

    public static String callAbilityBeanGetTrImmuAbility() {
        return directCase().getTrImmuAbility(0);
    }

    public static String callAbilityBeanGetTrImmuAllyFromMoves() {
        return directCase().getTrImmuAllyFromMoves(0);
    }

    public static String callAbilityBeanGetTrImmuLowStat() {
        return directCase().getTrImmuLowStat(0);
    }

    public static String callAbilityBeanGetTrImmuLowStatIfStatusKey() {
        return directCase().getTrImmuLowStatIfStatusKey(0);
    }

    public static String callAbilityBeanGetTrImmuLowStatIfStatusValue() {
        return directCase().getTrImmuLowStatIfStatusValue(0);
    }

    public static String callAbilityBeanGetTrImmuLowStatisTypes() {
        return directCase().getTrImmuLowStatisTypes(0);
    }

    public static String callAbilityBeanGetTrImmuLowStatisValue() {
        return directCase().getTrImmuLowStatisValue(0);
    }

    public static String callAbilityBeanGetTrImmuMove() {
        return directCase().getTrImmuMove(0);
    }

    public static String callAbilityBeanGetTrImmuMoveByWeather() {
        return directCase().getTrImmuMoveByWeather(1);
    }

    public static String callAbilityBeanGetTrImmuRechargeRoundMoves() {
        return directCase().getTrImmuRechargeRoundMoves(0);
    }

    public static String callAbilityBeanGetTrImmuStatus() {
        return directCase().getTrImmuStatus(1);
    }

    public static String callAbilityBeanGetTrImmuStatusBeginRound() {
        return directCase().getTrImmuStatusBeginRound(0);
    }

    public static String callAbilityBeanGetTrImmuStatusTypes() {
        return directCase().getTrImmuStatusTypes(0);
    }

    public static String callAbilityBeanGetTrImmuStatusValue() {
        return directCase().getTrImmuStatusValue(0);
    }

    public static String callAbilityBeanGetTrImmuStatusWeather() {
        return directCase().getTrImmuStatusWeather(1);
    }

    public static String callAbilityBeanGetTrImmuTypeByWeather() {
        return directCase().getTrImmuTypeByWeather(1);
    }

    public static String callAbilityBeanGetTrIncreasedPrio() {
        return directCase().getTrIncreasedPrio(0);
    }

    public static String callAbilityBeanGetTrIncreasedPrioTypes() {
        return directCase().getTrIncreasedPrioTypes(0);
    }

    public static String callAbilityBeanGetTrLowStatFoeHit() {
        return directCase().getTrLowStatFoeHit(0);
    }

    public static String callAbilityBeanGetTrMaxStatisticsIfCh() {
        return directCase().getTrMaxStatisticsIfCh(0);
    }

    public static String callAbilityBeanGetTrMultDamageFoe() {
        return directCase().getTrMultDamageFoe(0);
    }

    public static String callAbilityBeanGetTrMultPowerMovesTypesGlobalKey() {
        return directCase().getTrMultPowerMovesTypesGlobalKey(0);
    }

    public static String callAbilityBeanGetTrMultStat() {
        return directCase().getTrMultStat(0);
    }

    public static String callAbilityBeanGetTrMultStatAlly() {
        return directCase().getTrMultStatAlly(0);
    }

    public static String callAbilityBeanGetTrMultStatIfCatKey() {
        return directCase().getTrMultStatIfCatKey(0);
    }

    public static String callAbilityBeanGetTrMultStatIfCatKeySec() {
        return directCase().getTrMultStatIfCatKeySec(0);
    }

    public static String callAbilityBeanGetTrMultStatIfDamageCatKey() {
        return directCase().getTrMultStatIfDamageCatKey(0);
    }

    public static String callAbilityBeanGetTrMultStatIfDamageCatKeySec() {
        return directCase().getTrMultStatIfDamageCatKeySec(0);
    }

    public static String callAbilityBeanGetTrMultStatIfDamgeType() {
        return directCase().getTrMultStatIfDamgeType(0);
    }

    public static String callAbilityBeanGetTrMultStatIfDamgeTypeSec() {
        return directCase().getTrMultStatIfDamgeTypeSec(0);
    }

    public static String callAbilityBeanGetTrMultStatIfKoFoe() {
        return directCase().getTrMultStatIfKoFoe(0);
    }

    public static String callAbilityBeanGetTrMultStatIfLowStat() {
        return directCase().getTrMultStatIfLowStat(0);
    }

    public static String callAbilityBeanGetTrMultStatIfStatutRank() {
        return directCase().getTrMultStatIfStatutRank(0);
    }

    public static String callAbilityBeanGetTrMultStatIfStatutRankSec() {
        return directCase().getTrMultStatIfStatutRankSec(0);
    }

    public static String callAbilityBeanGetTrPokemon() {
        return directCase().getTrPokemon(0);
    }

    public static String callAbilityBeanGetTrReversePowerTypesAbilities() {
        return reverseCase().getTrReversePowerTypesAbilities(0);
    }

    public static String callAbilityBeanGetTrSingleStatus() {
        return directCase().getTrSingleStatus(1);
    }

    public static String callAbilityBeanGetTrWeather() {
        return directCase().getTrWeather(0);
    }

    public static boolean callAbilityBeanGiveItemToAllyHavingUsedGet(AbilityBean _str, int... _args) {
        return _str.getGiveItemToAllyHavingUsed();
    }

    public static AbsMap<TranslatedKeyPair,Rate> callAbilityBeanHealHpByTypeIfWeatherGet() {
        return directCase().getHealHpByTypeIfWeather();
    }

    public static DictionaryComparator<TranslatedKey,Rate> callAbilityBeanHealHpByWeatherGet() {
        return directCase().getHealHpByWeather();
    }

    public static Rate callAbilityBeanHealHpWhileUsingBerryGet() {
        return directCase().getHealHpWhileUsingBerry();
    }

    public static Rate callAbilityBeanHealedHpRateBySwitchGet() {
        return directCase().getHealedHpRateBySwitch();
    }

    public static boolean callAbilityBeanHealedStatusBySwitchGet(AbilityBean _str, int... _args) {
        return _str.getHealedStatusBySwitch();
    }

    public static CustList<TranslatedKey> callAbilityBeanIgnAbilityGet() {
        return directCase().getIgnAbility();
    }

    public static boolean callAbilityBeanIgnFoeStatisBoostGet(AbilityBean _str, int... _args) {
        return _str.getIgnFoeStatisBoost();
    }

    public static CustList<TranslatedKey> callAbilityBeanIgnFoeTeamMoveGet() {
        return directCase().getIgnFoeTeamMove();
    }

    public static CustList<TranslatedKey> callAbilityBeanImmuAbilityGet() {
        return directCase().getImmuAbility();
    }

    public static CustList<TranslatedKey> callAbilityBeanImmuAllyFromMovesGet() {
        return directCase().getImmuAllyFromMoves();
    }

    public static boolean callAbilityBeanImmuChGet(AbilityBean _str, int... _args) {
        return _str.getImmuCh();
    }

    public static boolean callAbilityBeanImmuDamageAllyMovesGet(AbilityBean _str, int... _args) {
        return _str.getImmuDamageAllyMoves();
    }

    public static boolean callAbilityBeanImmuDamageRecoilGet(AbilityBean _str, int... _args) {
        return _str.getImmuDamageRecoil();
    }

    public static boolean callAbilityBeanImmuDamageTrappingMovesGet(AbilityBean _str, int... _args) {
        return _str.getImmuDamageTrappingMoves();
    }

    public static CustList<TranslatedKey> callAbilityBeanImmuLowStatGet() {
        return directCase().getImmuLowStat();
    }

    public static int callAbilityBeanImmuLowStatIfStatusGet() {
        return directCase().getImmuLowStatIfStatus().size();
    }

    public static CustList<TranslatedKeyPair> callAbilityBeanImmuLowStatisTypesGet() {
        return directCase().getImmuLowStatisTypes();
    }

    public static CustList<TranslatedKey> callAbilityBeanImmuMoveGet() {
        return directCase().getImmuMove();
    }

    public static CustList<TranslatedKeyPair> callAbilityBeanImmuMoveTypesByWeatherGet() {
        return directCase().getImmuMoveTypesByWeather();
    }

    public static boolean callAbilityBeanImmuRechargeRoundGet(AbilityBean _str, int... _args) {
        return _str.getImmuRechargeRound();
    }

    public static CustList<TranslatedKey> callAbilityBeanImmuRechargeRoundMovesGet() {
        return directCase().getImmuRechargeRoundMoves();
    }

    public static CustList<TranslatedKey> callAbilityBeanImmuStatusBeginRoundGet() {
        return directCase().getImmuStatusBeginRound();
    }

    public static CustList<TranslatedKeyPair> callAbilityBeanImmuStatusGet() {
        return directCase().getImmuStatus();
    }

    public static CustList<TranslatedKeyPair> callAbilityBeanImmuStatusTypesGet() {
        return directCase().getImmuStatusTypes();
    }

    public static boolean callAbilityBeanImmuSufferedDamageLowEffGet(AbilityBean _str, int... _args) {
        return _str.getImmuSufferedDamageLowEff();
    }

    public static CustList<TranslatedKey> callAbilityBeanImmuWeatherGet() {
        return directCase().getImmuWeather();
    }

    public static DictionaryComparator<TranslatedKey,Long> callAbilityBeanIncreasedPrioGet() {
        return directCase().getIncreasedPrio();
    }

    public static DictionaryComparator<TranslatedKey,Long> callAbilityBeanIncreasedPrioTypesGet() {
        return directCase().getIncreasedPrioTypes();
    }

    public static boolean callAbilityBeanInflictingDamageInsteadOfSufferingGet(AbilityBean _str, int... _args) {
        return _str.getInflictingDamageInsteadOfSuffering();
    }

    public static boolean callAbilityBeanIsChgtTypeByWeather(int _v) {
        return directCase().isChgtTypeByWeather(_v);
    }

    public static boolean callAbilityBeanIsHealHpByTypeIfWeather(int _v) {
        return directCase().isHealHpByTypeIfWeather(_v);
    }

    public static boolean callAbilityBeanIsHealHpByWeather(int _v) {
        return directCase().isHealHpByWeather(_v);
    }

    public static boolean callAbilityBeanIsMoveByStatus(int _v) {
        return directCase().isMoveByStatus(_v);
    }

    public static boolean callAbilityBeanIsMoveByWeather(int _v) {
        return directCase().isMoveByWeather(_v);
    }

    public static boolean callAbilityBeanIsStatus(int _v) {
        return directCase().isStatus(_v);
    }

    public static DictionaryComparator<TranslatedKey,Long> callAbilityBeanLowStatFoeHitGet() {
        return directCase().getLowStatFoeHit();
    }

    public static AbsMap<String,String> callAbilityBeanMapVarsFailEndRoundGet() {
        return directCaseEndRound().getEndRoundCommon().getMapVarsFailEndRound();
    }

    public static AbsMap<String,String> callAbilityBeanMapVarsGet() {
        return directCase().getMapVars();
    }

    public static Rate callAbilityBeanMaxHpForUsingBerryGet() {
        return directCase().getMaxHpForUsingBerry();
    }

    public static CustList<TranslatedKey> callAbilityBeanMaxStatisticsIfChGet() {
        return directCase().getMaxStatisticsIfCh();
    }

    public static Rate callAbilityBeanMultAllyDamageGet() {
        return directCase().getMultAllyDamage();
    }

    public static Rate callAbilityBeanMultDamageChGet() {
        return directCase().getMultDamageCh();
    }

    public static DictionaryComparator<TranslatedKey,Rate> callAbilityBeanMultDamageFoeGet() {
        return directCase().getMultDamageFoe();
    }

    public static String callAbilityBeanMultDamageGet() {
        return directCase().getMultDamage();
    }

    public static Rate callAbilityBeanMultEvtRateChGet() {
        return directCase().getMultEvtRateCh();
    }

    public static Rate callAbilityBeanMultEvtRateSecEffectOwnerGet() {
        return directCase().getMultEvtRateSecEffectOwner();
    }

    public static String callAbilityBeanMultPowerGet() {
        return directCase().getMultPower();
    }

    public static DictionaryComparator<TranslatedKey,Rate> callAbilityBeanMultPowerMovesTypesGlobalGet() {
        return directCase().getMultPowerMovesTypesGlobal();
    }

    public static Rate callAbilityBeanMultStabGet() {
        return directCase().getMultStab();
    }

    public static AbsMap<TranslatedKey,Rate> callAbilityBeanMultStatAllyGet() {
        return directCase().getMultStatAlly();
    }

    public static DictionaryComparator<TranslatedKey,String> callAbilityBeanMultStatGet() {
        return directCase().getMultStat();
    }

    public static AbsMap<TranslatedKeyPair,Rate> callAbilityBeanMultStatIfCatGet() {
        return directCase().getMultStatIfCat();
    }

    public static AbsMap<TranslatedKeyPair,Long> callAbilityBeanMultStatIfDamageCatGet() {
        return directCase().getMultStatIfDamageCat();
    }

    public static AbsMap<TranslatedKeyPair,Long> callAbilityBeanMultStatIfDamgeTypeGet() {
        return directCase().getMultStatIfDamgeType();
    }

    public static DictionaryComparator<TranslatedKey,Long> callAbilityBeanMultStatIfKoFoeGet() {
        return directCase().getMultStatIfKoFoe();
    }

    public static DictionaryComparator<TranslatedKey,Long> callAbilityBeanMultStatIfLowStatGet() {
        return directCase().getMultStatIfLowStat();
    }

    public static AbsMap<TranslatedKeyPair,Long> callAbilityBeanMultStatIfStatutRankGet() {
        return directCase().getMultStatIfStatutRank();
    }

    public static Rate callAbilityBeanMultSufferedDamageSuperEffGet() {
        return directCase().getMultSufferedDamageSuperEff();
    }

    public static Rate callAbilityBeanMultVarBoostGet() {
        return directCase().getMultVarBoost();
    }

    public static boolean callAbilityBeanMumyGet(AbilityBean _str, int... _args) {
        return _str.getMumy();
    }

    public static boolean callAbilityBeanNbHitsGet(AbilityBean _str, int... _args) {
        return _str.getNbHits();
    }

    public static long callAbilityBeanNbUsedPpGet() {
        return directCase().getNbUsedPp();
    }

    public static boolean callAbilityBeanNbUsedPpInt(AbilityBean _str, int... _args) {
        return _str.nbUsedPpInt();
    }

    public static boolean callAbilityBeanPlateGet(AbilityBean _str, int... _args) {
        return _str.getPlate();
    }

    public static CustList<TranslatedKey> callAbilityBeanPokemonGet() {
        return directCase().getPokemon();
    }

    public static CustList<String> callAbilityBeanReasonsEndRoundGet() {
        return directCaseEndRound().getEndRoundCommon().getReasonsEndRound();
    }

//    public static Struct callAbilityBeanRecoilDamageFoeByKoOwnerGet() {
//        return BeanPokemonCommonTs.callLongs(new AbilityBeanRecoilDamageFoeByKoOwnerGet(),directCase());
//    }

    public static Rate callAbilityBeanRecoilDamageFoeGet() {
        return directCase().getRecoilDamageFoe();
    }

    public static CustList<TranslatedKey> callAbilityBeanReverseEffectsPowerMovesTypesGlobalAbilitiesGet() {
        return reverseCase().getReverseEffectsPowerMovesTypesGlobalAbilities();
    }

    public static boolean callAbilityBeanReverseEffectsPowerMovesTypesGlobalGet(AbilityBean _str, int... _args) {
        return _str.getReverseEffectsPowerMovesTypesGlobal();
    }

    public static boolean callAbilityBeanSendingNoGet() {
        return directCase().getSending();
    }

    public static boolean callAbilityBeanSendingGet() {
        return abNoStatSend().getSending();
    }

    public static DictionaryComparator<TranslatedKey,Rate> callAbilityBeanSingleStatusGet() {
        return directCase().getSingleStatus();
    }

    public static boolean callAbilityBeanSlowingGet(AbilityBean _str, int... _args) {
        return _str.getSlowing();
    }

    public static boolean callAbilityBeanTakeItemByDamagingMoveGet(AbilityBean _str, int... _args) {
        return _str.getTakeItemByDamagingMove();
    }

    public static String callAbilityBeanTypeForMovesGet(AbilityBean _str, int... _args) {
        return _str.getTypeForMoves().getTranslation();
    }
    public static String eltTkPairFirst(CustList<TranslatedKeyPair> _ls, int _i) {
        return _ls.get(_i).getFirst().getKey();
    }
    public static String eltTkPairSecond(CustList<TranslatedKeyPair> _ls, int _i) {
        return _ls.get(_i).getSecond().getKey();
    }


    public static StringMap<BeanRenderWithAppName> beanToItBaseSend(FacadeGame _pk) {
        StringMap<BeanRenderWithAppName> map_ = beanToAbility(_pk);
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.SENDING, new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.SENDING, new TranslationsFile());
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

    protected static AbilityBean reverseCase() {
        FacadeGame pk_ = pkDataByFacade(feedDbAbilityReverse());
        StringMap<BeanRenderWithAppName> all_ = beanToAbility(pk_);
        return transitToAllAbilities(pk_, all_, 0);
    }

    protected static AbilityBean directCase() {
        FacadeGame pk_ = pkDataByFacade(feedDbAbility());
        StringMap<BeanRenderWithAppName> all_ = beanToAbility(pk_);
        return transitToAllAbilities(pk_, all_, 0);
    }

    protected static AbilityBean directCase(boolean _forbidUseBerryAgainstFoes, boolean _chgtTypeByDamage, boolean _ignFoeStatisBoost, boolean _immuCh, boolean _immuDamageTrappingMoves, boolean _immuDamageAllyMoves, boolean _immuDamageRecoil, boolean _copyMovesTypes, boolean _reverseEffectsPowerMovesTypesGlobal, boolean _takeItemByDamagingMove, boolean _giveItemToAllyHavingUsed, boolean _inflictingDamageInsteadOfSuffering, boolean _nbHits, boolean _breakProtection, boolean _plate, boolean _healedStatusBySwitch, boolean _achievedDisappearedPk, boolean _mumy, boolean _immuRechargeRound, boolean _slowing, boolean _immuSufferedDamageLowEff, boolean _cancelSecEffectOther, boolean _cancelSecEffectOwner, int _nbUsedPp, int _decreaseNecStepsHatch, String _typeForMoves) {
        FacadeGame pk_ = pkDataByFacade(feedDbAbility(_forbidUseBerryAgainstFoes, _chgtTypeByDamage, _ignFoeStatisBoost, _immuCh, _immuDamageTrappingMoves, _immuDamageAllyMoves, _immuDamageRecoil, _copyMovesTypes, _reverseEffectsPowerMovesTypesGlobal, _takeItemByDamagingMove, _giveItemToAllyHavingUsed, _inflictingDamageInsteadOfSuffering, _nbHits, _breakProtection, _plate, _healedStatusBySwitch, _achievedDisappearedPk, _mumy, _immuRechargeRound, _slowing, _immuSufferedDamageLowEff, _cancelSecEffectOther, _cancelSecEffectOwner, _nbUsedPp, _decreaseNecStepsHatch, _typeForMoves));
        StringMap<BeanRenderWithAppName> all_ = beanToAbility(pk_);
        return transitToAllAbilities(pk_, all_, 0);
    }

    protected static AbilityBean directCaseEndRound() {
        FacadeGame pk_ = pkDataByFacade(feedDbAbilityEndRound());
        StringMap<BeanRenderWithAppName> all_ = beanToAbility(pk_);
        all_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_ENDROUND, new TranslationsFile());
        all_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EFF_ENDROUND, new TranslationsFile());
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
        FacadeGame pk_ = pkDataByFacade(feedDbAbilityNoStat());
        StringMap<BeanRenderWithAppName> all_ = beanToItBaseSend(pk_);
        AbilityBean res_ = transitToAllAbilitiesQuick(pk_, all_, 0);
//        callAbilityBeanEffectSendBeanGet(res_);
//        NaSt send_ = all_.getVal(AikiBeansEffectsStd.EFFECT_SENDING);
//        callEffectWhileSendingBeanEffectSet(send_,callAbilityBeanGetEffectSending(res_));
//        beforeDisplaying(send_);
        return res_.effSending(res_.getEffectSending());
    }

    public static AbilityBean abNoStatSend() {
        FacadeGame pk_ = pkDataByFacade(feedDbAbilityNoStat());
        StringMap<BeanRenderWithAppName> all_ = beanToItBaseSend(pk_);
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
