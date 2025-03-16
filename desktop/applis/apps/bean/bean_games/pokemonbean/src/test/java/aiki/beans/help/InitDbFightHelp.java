package aiki.beans.help;

import aiki.beans.*;
import aiki.beans.PkData;
import aiki.beans.PokemonBeanStruct;
import aiki.beans.db.InitDbConstr;
import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import code.maths.*;
import code.scripts.pages.aiki.MessagesPkBean;
import code.sml.util.TranslationsFile;
import aiki.beans.abilities.*;
import code.util.*;

public abstract class InitDbFightHelp extends InitDbConstr {

    public static CustList<TranslatedKey> callFightHelpBeanAbilitesMultEvtChGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitesMultEvtCh();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitesMultRateChGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitesMultRateCh();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesAchieveTargetGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesAchieveTarget();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesAllyMultStatGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesAllyMultStat();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesBoostingStatGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesBoostingStat();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesBreakImmuGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesBreakImmu();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesBreakProtectMovesGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesBreakProtectMoves();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesBreakableGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesBreakable();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesChangingTypesDamageGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesChangingTypesDamage();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesCopyAbGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesCopyAb();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesDamageStatisGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesDamageStatis();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesEndRoundGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesEndRound();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesFighterStatisGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesFighterStatis();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesFighterStatisVarGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesFighterStatisVar();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesFighterStatusGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesFighterStatus();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesGlobalGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesGlobal();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesImmuAlliesDamGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesImmuAlliesDam();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesImmuAlliesGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesImmuAllies();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesImmuChGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesImmuCh();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesImmuGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesImmu();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesImmuMultStatGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesImmuMultStat();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesImmuSecEffOtherGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesImmuSecEffOther();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesImmuSecEffOwnerGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesImmuSecEffOwner();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesImmuTypesGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesImmuTypes();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesKoTargetGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesKoTarget();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesMultStatGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesMultStat();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesPartStatisGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesPartStatis();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesPartStatusGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesPartStatus();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesPrioGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesPrio();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesRateStatisGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesRateStatis();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesRevAbsGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesRevAbs();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesSentBeginWeatherGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesSentBeginWeather();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesSentBeginOtherGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesSentBeginOther();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesSentStatisGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesSentStatis();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesStatisVarUserGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesStatisVarUser();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesStatusGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesStatus();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesSwitchGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesSwitch();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesTakingItemGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesTakingItem();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesTargetDamageGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesTargetDamage();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesTypeDefMovesGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesTypeDefMoves();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesUserDamageGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesUserDamage();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesUserIgnTargetTeamGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesUserIgnTargetTeam();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesUserPowerGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesUserPower();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesUserStabDamageGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesUserStabDamage();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAbilitiesUserTargetDamageGet(FightHelpBean _str, int... _args) {
        return _str.getAbilitiesUserTargetDamage();
    }

    public static boolean callFightHelpBeanAbilityAllyMultAccuracy(FightHelpBean _str, int... _args) {
        return _str.abilityAllyMultAccuracy(_args[0]);
    }

    public static boolean callFightHelpBeanAbilityAllyMultAccuracyAny(FightHelpBean _str, int... _args) {
        return _str.abilityAllyMultAccuracyAny();
    }

    public static boolean callFightHelpBeanAbilityAllyMultEvasiness(FightHelpBean _str, int... _args) {
        return _str.abilityAllyMultEvasiness(_args[0]);
    }

    public static boolean callFightHelpBeanAbilityAllyMultEvasinessAny(FightHelpBean _str, int... _args) {
        return _str.abilityAllyMultEvasinessAny();
    }

    public static boolean callFightHelpBeanAbilityAllyMultNormal(FightHelpBean _str, int... _args) {
        return _str.abilityAllyMultNormal(_args[0]);
    }

    public static boolean callFightHelpBeanAbilityAllyMultNormalAny(FightHelpBean _str, int... _args) {
        return _str.abilityAllyMultNormalAny();
    }

    public static boolean callFightHelpBeanAbilityAllyMultSpeed(FightHelpBean _str, int... _args) {
        return _str.abilityAllyMultSpeed(_args[0]);
    }

    public static boolean callFightHelpBeanAbilityAllyMultSpeedAny(FightHelpBean _str, int... _args) {
        return _str.abilityAllyMultSpeedAny();
    }

    public static boolean callFightHelpBeanAbilityBoostAccuracy(FightHelpBean _str, int... _args) {
        return _str.abilityBoostAccuracy(_args[0]);
    }

    public static boolean callFightHelpBeanAbilityBoostAccuracyAny(FightHelpBean _str, int... _args) {
        return _str.abilityBoostAccuracyAny();
    }

    public static boolean callFightHelpBeanAbilityBoostCh(FightHelpBean _str, int... _args) {
        return _str.abilityBoostCh(_args[0]);
    }

    public static boolean callFightHelpBeanAbilityBoostChAny(FightHelpBean _str, int... _args) {
        return _str.abilityBoostChAny();
    }

    public static boolean callFightHelpBeanAbilityBoostEvasiness(FightHelpBean _str, int... _args) {
        return _str.abilityBoostEvasiness(_args[0]);
    }

    public static boolean callFightHelpBeanAbilityBoostEvasinessAny(FightHelpBean _str, int... _args) {
        return _str.abilityBoostEvasinessAny();
    }

    public static boolean callFightHelpBeanAbilityBoostNormal(FightHelpBean _str, int... _args) {
        return _str.abilityBoostNormal(_args[0]);
    }

    public static boolean callFightHelpBeanAbilityBoostNormalAny(FightHelpBean _str, int... _args) {
        return _str.abilityBoostNormalAny();
    }

    public static boolean callFightHelpBeanAbilityBoostSpeed(FightHelpBean _str, int... _args) {
        return _str.abilityBoostSpeed(_args[0]);
    }

    public static boolean callFightHelpBeanAbilityBoostSpeedAny(FightHelpBean _str, int... _args) {
        return _str.abilityBoostSpeedAny();
    }

    public static boolean callFightHelpBeanAbilityImmuMultAccuracy(FightHelpBean _str, int... _args) {
        return _str.abilityImmuMultAccuracy(_args[0]);
    }

    public static boolean callFightHelpBeanAbilityImmuMultAccuracyAny(FightHelpBean _str, int... _args) {
        return _str.abilityImmuMultAccuracyAny();
    }

    public static boolean callFightHelpBeanAbilityImmuMultEvasiness(FightHelpBean _str, int... _args) {
        return _str.abilityImmuMultEvasiness(_args[0]);
    }

    public static boolean callFightHelpBeanAbilityImmuMultEvasinessAny(FightHelpBean _str, int... _args) {
        return _str.abilityImmuMultEvasinessAny();
    }

    public static boolean callFightHelpBeanAbilityImmuMultNormal(FightHelpBean _str, int... _args) {
        return _str.abilityImmuMultNormal(_args[0]);
    }

    public static boolean callFightHelpBeanAbilityImmuMultNormalAny(FightHelpBean _str, int... _args) {
        return _str.abilityImmuMultNormalAny();
    }

    public static boolean callFightHelpBeanAbilityImmuMultSpeed(FightHelpBean _str, int... _args) {
        return _str.abilityImmuMultSpeed(_args[0]);
    }

    public static boolean callFightHelpBeanAbilityImmuMultSpeedAny(FightHelpBean _str, int... _args) {
        return _str.abilityImmuMultSpeedAny();
    }

    public static boolean callFightHelpBeanAbilityMultAccuracy(FightHelpBean _str, int... _args) {
        return _str.abilityMultAccuracy(_args[0]);
    }

    public static boolean callFightHelpBeanAbilityMultAccuracyAny(FightHelpBean _str, int... _args) {
        return _str.abilityMultAccuracyAny();
    }

    public static boolean callFightHelpBeanAbilityMultEvasiness(FightHelpBean _str, int... _args) {
        return _str.abilityMultEvasiness(_args[0]);
    }

    public static boolean callFightHelpBeanAbilityMultEvasinessAny(FightHelpBean _str, int... _args) {
        return _str.abilityMultEvasinessAny();
    }

    public static boolean callFightHelpBeanAbilityMultNormal(FightHelpBean _str, int... _args) {
        return _str.abilityMultNormal(_args[0]);
    }

    public static boolean callFightHelpBeanAbilityMultNormalAny(FightHelpBean _str, int... _args) {
        return _str.abilityMultNormalAny();
    }

    public static boolean callFightHelpBeanAbilityMultSpeed(FightHelpBean _str, int... _args) {
        return _str.abilityMultSpeed(_args[0]);
    }

    public static boolean callFightHelpBeanAbilityMultSpeedAny(FightHelpBean _str, int... _args) {
        return _str.abilityMultSpeedAny();
    }

    public static boolean callFightHelpBeanAttackFirst(FightHelpBean _str, int... _args) {
        return _str.attackFirst();
    }

    public static boolean callFightHelpBeanAttackLast(FightHelpBean _str, int... _args) {
        return _str.attackLast(_args[0]);
    }

    public static boolean callFightHelpBeanAttackLastAny(FightHelpBean _str, int... _args) {
        return _str.attackLastAny();
    }

    public static CustList<TranslatedKey> callFightHelpBeanAutoDamageGet(FightHelpBean _str, int... _args) {
        return _str.getAutoDamage();
    }

    public static CustList<TranslatedKey> callFightHelpBeanBeginRoundStatusFoeGet(FightHelpBean _str, int... _args) {
        return _str.getBeginRoundStatusFoe();
    }

    public static CustList<TranslatedKey> callFightHelpBeanBeginRoundStatusGet(FightHelpBean _str, int... _args) {
        return _str.getBeginRoundStatus();
    }

    public static CustList<TranslatedKey> callFightHelpBeanBerryEndRoundGet(FightHelpBean _str, int... _args) {
        return _str.getBerryEndRound();
    }

    public static CustList<TranslatedKey> callFightHelpBeanBerrySpeedGet(FightHelpBean _str, int... _args) {
        return _str.getBerrySpeed();
    }

    public static CustList<TranslatedKey> callFightHelpBeanBerryTargetGet(FightHelpBean _str, int... _args) {
        return _str.getBerryTarget();
    }

    public static CustList<TranslatedKey> callFightHelpBeanBerryUserGet(FightHelpBean _str, int... _args) {
        return _str.getBerryUser();
    }

    public static AbsMap<Long,Rate> callFightHelpBeanBoostsChGet(FightHelpBean _str, int... _args) {
        return _str.getBoostsCh();
    }

    public static AbsMap<Long,Rate> callFightHelpBeanBoostsGet(FightHelpBean _str, int... _args) {
        return _str.getBoosts();
    }

    public static String callFightHelpBeanCatchingFormulaGet(FightHelpBean _str, int... _args) {
        return _str.getCatchingFormula();
    }

    public static CustList<TranslatedKey> callFightHelpBeanChangingTypesAbilitiesGet(FightHelpBean _str, int... _args) {
        return _str.getChangingTypesAbilities();
    }

    public static String callFightHelpBeanClickAbilitiesAchieveTarget(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesAchieveTarget(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesAllyMultStat(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesAllyMultStat(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesBoostingStat(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesBoostingStat(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesBreakImmu(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesBreakImmu(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesBreakProtectMoves(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesBreakProtectMoves(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesBreakable(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesBreakable(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesChangeTypeMoves(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesChangeTypeMoves(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesChangingTypesDamage(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesChangingTypesDamage(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesCopyAb(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesCopyAb(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesDamageStatis(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesDamageStatis(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesEndRound(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesEndRound(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesFighterStatis(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesFighterStatis(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesFighterStatisVar(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesFighterStatisVar(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesFighterStatus(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesFighterStatus(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesGlobal(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesGlobal(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesImmu(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesImmu(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesImmuAllies(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesImmuAllies(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesImmuAlliesDam(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesImmuAlliesDam(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesImmuCh(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesImmuCh(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesImmuMultStat(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesImmuMultStat(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesImmuSecEffOther(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesImmuSecEffOther(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesImmuSecEffOwner(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesImmuSecEffOwner(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesImmuTypes(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesImmuTypes(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesKoTarget(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesKoTarget(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesMultEvtCh(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesMultEvtCh(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesMultRateCh(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesMultRateCh(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesMultStat(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesMultStat(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesPartStatis(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesPartStatis(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesPartStatus(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesPartStatus(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesPrio(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesPrio(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesRateStatis(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesRateStatis(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesRevAbs(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesRevAbs(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesSentBegin(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesSentBegin(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesSentBeginOth(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesSentBeginOth(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesSentStatis(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesSentStatis(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesStatisVarUser(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesStatisVarUser(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesStatus(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesStatus(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesSwitch(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesSwitch(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesTakingItem(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesTakingItem(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesTargetDamage(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesTargetDamage(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesTypeDefMoves(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesTypeDefMoves(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesUserDamage(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesUserDamage(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesUserIgnTargetTeam(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesUserIgnTargetTeam(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesUserPower(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesUserPower(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesUserStabDamage(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesUserStabDamage(_args[0]);
    }

    public static String callFightHelpBeanClickAbilitiesUserTargetDamage(FightHelpBean _str, int... _args) {
        return _str.clickAbilitiesUserTargetDamage(_args[0]);
    }

    public static String callFightHelpBeanClickAutoDamage(FightHelpBean _str, int... _args) {
        return _str.clickAutoDamage(_args[0]);
    }

    public static String callFightHelpBeanClickBeginRoundStatus(FightHelpBean _str, int... _args) {
        return _str.clickBeginRoundStatus(_args[0]);
    }

    public static String callFightHelpBeanClickBeginRoundStatusFoe(FightHelpBean _str, int... _args) {
        return _str.clickBeginRoundStatusFoe(_args[0]);
    }

    public static String callFightHelpBeanClickBerryEndRound(FightHelpBean _str, int... _args) {
        return _str.clickBerryEndRound(_args[0]);
    }

    public static String callFightHelpBeanClickBerrySpeed(FightHelpBean _str, int... _args) {
        return _str.clickBerrySpeed(_args[0]);
    }

    public static String callFightHelpBeanClickBerryTarget(FightHelpBean _str, int... _args) {
        return _str.clickBerryTarget(_args[0]);
    }

    public static String callFightHelpBeanClickBerryUser(FightHelpBean _str, int... _args) {
        return _str.clickBerryUser(_args[0]);
    }

    public static String callFightHelpBeanClickChangingTypesAbilities(FightHelpBean _str, int... _args) {
        return _str.clickChangingTypesAbilities(_args[0]);
    }

    public static String callFightHelpBeanClickComboEvtStat(FightHelpBean _str, int... _args) {
        return _str.clickComboEvtStat(_args[0]);
    }

    public static String callFightHelpBeanClickComboMultStat(FightHelpBean _str, int... _args) {
        return _str.clickComboMultStat(_args[0]);
    }

    public static String callFightHelpBeanClickCopyAbilities(FightHelpBean _str, int... _args) {
        return _str.clickCopyAbilities(_args[0]);
    }

    public static String callFightHelpBeanClickCopyMoveTypesAb(FightHelpBean _str, int... _args) {
        return _str.clickCopyMoveTypesAb(_args[0]);
    }

    public static String callFightHelpBeanClickDamagingMoves(FightHelpBean _str, int... _args) {
        return _str.clickDamagingMoves(_args[0]);
    }

    public static String callFightHelpBeanClickDefaultMove(FightHelpBean _str, int... _args) {
        return _str.clickDefaultMove();
    }

    public static String callFightHelpBeanClickDeleteStatusMove(FightHelpBean _str, int... _args) {
        return _str.clickDeleteStatusMove(_args[0]);
    }

    public static String callFightHelpBeanClickDeletedStatusSwitch(FightHelpBean _str, int... _args) {
        return _str.clickDeletedStatusSwitch(_args[0]);
    }

    public static String callFightHelpBeanClickEffMoves(FightHelpBean _str, int... _args) {
        return _str.clickEffMoves(_args[0]);
    }

    public static String callFightHelpBeanClickEntryHazard(FightHelpBean _str, int... _args) {
        return _str.clickEntryHazard(_args[0]);
    }

    public static String callFightHelpBeanClickGlobalMovesStatus(FightHelpBean _str, int... _args) {
        return _str.clickGlobalMovesStatus(_args[0]);
    }

    public static String callFightHelpBeanClickImmuRecharging(FightHelpBean _str, int... _args) {
        return _str.clickImmuRecharging(_args[0]);
    }

    public static String callFightHelpBeanClickImmuStatusAbility(FightHelpBean _str, int... _args) {
        return _str.clickImmuStatusAbility(_args[0]);
    }

    public static String callFightHelpBeanClickItemSpeed(FightHelpBean _str, int... _args) {
        return _str.clickItemSpeed(_args[0]);
    }

    public static String callFightHelpBeanClickItemsAbs(FightHelpBean _str, int... _args) {
        return _str.clickItemsAbs(_args[0]);
    }

    public static String callFightHelpBeanClickItemsBoostingStat(FightHelpBean _str, int... _args) {
        return _str.clickItemsBoostingStat(_args[0]);
    }

    public static String callFightHelpBeanClickItemsCancelImmu(FightHelpBean _str, int... _args) {
        return _str.clickItemsCancelImmu(_args[0]);
    }

    public static String callFightHelpBeanClickItemsFighterStatis(FightHelpBean _str, int... _args) {
        return _str.clickItemsFighterStatis(_args[0]);
    }

    public static String callFightHelpBeanClickItemsFighterStatus(FightHelpBean _str, int... _args) {
        return _str.clickItemsFighterStatus(_args[0]);
    }

    public static String callFightHelpBeanClickItemsImmu(FightHelpBean _str, int... _args) {
        return _str.clickItemsImmu(_args[0]);
    }

    public static String callFightHelpBeanClickItemsImmuTypes(FightHelpBean _str, int... _args) {
        return _str.clickItemsImmuTypes(_args[0]);
    }

    public static String callFightHelpBeanClickItemsMultStat(FightHelpBean _str, int... _args) {
        return _str.clickItemsMultStat(_args[0]);
    }

    public static String callFightHelpBeanClickItemsProtAgainstKo(FightHelpBean _str, int... _args) {
        return _str.clickItemsProtAgainstKo(_args[0]);
    }

    public static String callFightHelpBeanClickItemsSentBegin(FightHelpBean _str, int... _args) {
        return _str.clickItemsSentBegin(_args[0]);
    }

    public static String callFightHelpBeanClickItemsSentBeginOth(FightHelpBean _str, int... _args) {
        return _str.clickItemsSentBeginOth(_args[0]);
    }

    public static String callFightHelpBeanClickItemsTargetDamage(FightHelpBean _str, int... _args) {
        return _str.clickItemsTargetDamage(_args[0]);
    }

    public static String callFightHelpBeanClickItemsTypesDef(FightHelpBean _str, int... _args) {
        return _str.clickItemsTypesDef(_args[0]);
    }

    public static String callFightHelpBeanClickItemsUserDamage(FightHelpBean _str, int... _args) {
        return _str.clickItemsUserDamage(_args[0]);
    }

    public static String callFightHelpBeanClickItemsUserPower(FightHelpBean _str, int... _args) {
        return _str.clickItemsUserPower(_args[0]);
    }

    public static String callFightHelpBeanClickMovesAttracting(FightHelpBean _str, int... _args) {
        return _str.clickMovesAttracting(_args[0]);
    }

    public static String callFightHelpBeanClickMovesBoostCh(FightHelpBean _str, int... _args) {
        return _str.clickMovesBoostCh(_args[0]);
    }

    public static String callFightHelpBeanClickChangeTypeMoves(FightHelpBean _str, int... _args) {
        return _str.clickMovesChangeTypeMoves(_args[0]);
    }

    public static String callFightHelpBeanClickMovesCannotKo(FightHelpBean _str, int... _args) {
        return _str.clickMovesCannotKo(_args[0]);
    }

    public static String callFightHelpBeanClickMovesChangingAttOrder(FightHelpBean _str, int... _args) {
        return _str.clickMovesChangingAttOrder(_args[0]);
    }

    public static String callFightHelpBeanClickMovesFoeTeamMultStat(FightHelpBean _str, int... _args) {
        return _str.clickMovesFoeTeamMultStat(_args[0]);
    }

    public static String callFightHelpBeanClickMovesGlobal(FightHelpBean _str, int... _args) {
        return _str.clickMovesGlobal(_args[0]);
    }

    public static String callFightHelpBeanClickMovesGlobalAcc(FightHelpBean _str, int... _args) {
        return _str.clickMovesGlobalAcc(_args[0]);
    }

    public static String callFightHelpBeanClickMovesGlobalBreakImmu(FightHelpBean _str, int... _args) {
        return _str.clickMovesGlobalBreakImmu(_args[0]);
    }

    public static String callFightHelpBeanClickMovesGlobalBreakImmuAb(FightHelpBean _str, int... _args) {
        return _str.clickMovesGlobalBreakImmuAb(_args[0]);
    }

    public static String callFightHelpBeanClickMovesGlobalMultStat(FightHelpBean _str, int... _args) {
        return _str.clickMovesGlobalMultStat(_args[0]);
    }

    public static String callFightHelpBeanClickMovesGlobalPrepaDamage(FightHelpBean _str, int... _args) {
        return _str.clickMovesGlobalPrepaDamage(_args[0]);
    }

    public static String callFightHelpBeanClickMovesHealingSubstitute(FightHelpBean _str, int... _args) {
        return _str.clickMovesHealingSubstitute(_args[0]);
    }

    public static String callFightHelpBeanClickMovesIgnAcc(FightHelpBean _str, int... _args) {
        return _str.clickMovesIgnAcc(_args[0]);
    }

    public static String callFightHelpBeanClickMovesIgnEva(FightHelpBean _str, int... _args) {
        return _str.clickMovesIgnEva(_args[0]);
    }

    public static String callFightHelpBeanClickMovesIgnIncDef(FightHelpBean _str, int... _args) {
        return _str.clickMovesIgnIncDef(_args[0]);
    }

    public static String callFightHelpBeanClickMovesIgnLowAtt(FightHelpBean _str, int... _args) {
        return _str.clickMovesIgnLowAtt(_args[0]);
    }

    public static String callFightHelpBeanClickMovesInvokDamage(FightHelpBean _str, int... _args) {
        return _str.clickMovesInvokDamage(_args[0]);
    }

    public static String callFightHelpBeanClickMovesInvoking(FightHelpBean _str, int... _args) {
        return _str.clickMovesInvoking(_args[0]);
    }

    public static String callFightHelpBeanClickMovesKoTarget(FightHelpBean _str, int... _args) {
        return _str.clickMovesKoTarget(_args[0]);
    }

    public static String callFightHelpBeanClickMovesMirror(FightHelpBean _str, int... _args) {
        return _str.clickMovesMirror(_args[0]);
    }

    public static String callFightHelpBeanClickMovesProtAgainstKo(FightHelpBean _str, int... _args) {
        return _str.clickMovesProtAgainstKo(_args[0]);
    }

    public static String callFightHelpBeanClickMovesProtecting(FightHelpBean _str, int... _args) {
        return _str.clickMovesProtecting(_args[0]);
    }

    public static String callFightHelpBeanClickMovesProtectingTypes(FightHelpBean _str, int... _args) {
        return _str.clickMovesProtectingTypes(_args[0]);
    }

    public static String callFightHelpBeanClickMovesSecEffItems(FightHelpBean _str, int... _args) {
        return _str.clickMovesSecEffItems(_args[0]);
    }

    public static String callFightHelpBeanClickMovesTargetPower(FightHelpBean _str, int... _args) {
        return _str.clickMovesTargetPower(_args[0]);
    }

    public static String callFightHelpBeanClickMovesTargetTeamDamage(FightHelpBean _str, int... _args) {
        return _str.clickMovesTargetTeamDamage(_args[0]);
    }

    public static String callFightHelpBeanClickMovesTeam(FightHelpBean _str, int... _args) {
        return _str.clickMovesTeam(_args[0]);
    }

    public static String callFightHelpBeanClickMovesTeamMultStat(FightHelpBean _str, int... _args) {
        return _str.clickMovesTeamMultStat(_args[0]);
    }

    public static String callFightHelpBeanClickMovesThieving(FightHelpBean _str, int... _args) {
        return _str.clickMovesThieving(_args[0]);
    }

    public static String callFightHelpBeanClickMovesTypeDefMoves(FightHelpBean _str, int... _args) {
        return _str.clickMovesTypeDefMoves(_args[0]);
    }

    public static String callFightHelpBeanClickMovesTypesDefItem(FightHelpBean _str, int... _args) {
        return _str.clickMovesTypesDefItem(_args[0]);
    }

    public static String callFightHelpBeanClickMovesTypesDefWeather(FightHelpBean _str, int... _args) {
        return _str.clickMovesTypesDefWeather(_args[0]);
    }

    public static String callFightHelpBeanClickMovesUnprotectingTypes(FightHelpBean _str, int... _args) {
        return _str.clickMovesUnprotectingTypes(_args[0]);
    }

    public static String callFightHelpBeanClickMovesUserAllyaDamage(FightHelpBean _str, int... _args) {
        return _str.clickMovesUserAllyaDamage(_args[0]);
    }

    public static String callFightHelpBeanClickMovesUserPower(FightHelpBean _str, int... _args) {
        return _str.clickMovesUserPower(_args[0]);
    }

    public static String callFightHelpBeanClickRechargeMoves(FightHelpBean _str, int... _args) {
        return _str.clickRechargeMoves(_args[0]);
    }

    public static String callFightHelpBeanClickPrepaRoundMoves(FightHelpBean _str, int... _args) {
        return _str.clickPrepaRoundMoves(_args[0]);
    }

    public static String callFightHelpBeanClickPressureAbilities(FightHelpBean _str, int... _args) {
        return _str.clickPressureAbilities(_args[0]);
    }

    public static String callFightHelpBeanClickPrivatingMoves(FightHelpBean _str, int... _args) {
        return _str.clickPrivatingMoves(_args[0]);
    }

    public static String callFightHelpBeanClickProtectAbilities(FightHelpBean _str, int... _args) {
        return _str.clickProtectAbilities(_args[0]);
    }

    public static String callFightHelpBeanClickProtectItems(FightHelpBean _str, int... _args) {
        return _str.clickProtectItems(_args[0]);
    }

    public static String callFightHelpBeanClickProtectMoves(FightHelpBean _str, int... _args) {
        return _str.clickProtectMoves(_args[0]);
    }

    public static String callFightHelpBeanClickRecoilAbilities(FightHelpBean _str, int... _args) {
        return _str.clickRecoilAbilities(_args[0]);
    }

    public static String callFightHelpBeanClickRecoilItems(FightHelpBean _str, int... _args) {
        return _str.clickRecoilItems(_args[0]);
    }

    public static String callFightHelpBeanClickReverseSpeedMoves(FightHelpBean _str, int... _args) {
        return _str.clickReverseSpeedMoves(_args[0]);
    }

    public static String callFightHelpBeanClickSlowAbilities(FightHelpBean _str, int... _args) {
        return _str.clickSlowAbilities(_args[0]);
    }

    public static String callFightHelpBeanClickSlowItems(FightHelpBean _str, int... _args) {
        return _str.clickSlowItems(_args[0]);
    }

    public static String callFightHelpBeanClickSpeedPreparingItems(FightHelpBean _str, int... _args) {
        return _str.clickSpeedPreparingItems(_args[0]);
    }

    public static String callFightHelpBeanClickStatusDamage(FightHelpBean _str, int... _args) {
        return _str.clickStatusDamage(_args[0]);
    }

    public static String callFightHelpBeanClickStatusMultStat(FightHelpBean _str, int... _args) {
        return _str.clickStatusMultStat(_args[0]);
    }

    public static String callFightHelpBeanClickSubstitutingMoves(FightHelpBean _str, int... _args) {
        return _str.clickSubstitutingMoves(_args[0]);
    }

    public static String callFightHelpBeanClickSuccessfulStatus(FightHelpBean _str, int... _args) {
        return _str.clickSuccessfulStatus(_args[0]);
    }

    public static CustList<CustList<TranslatedKey>> callFightHelpBeanComboEvtStatGet(FightHelpBean _str, int... _args) {
        return _str.getComboEvtStat();
    }

    public static boolean callFightHelpBeanComboMultAccuracy(FightHelpBean _str, int... _args) {
        return _str.comboMultAccuracy(_args[0]);
    }

    public static boolean callFightHelpBeanComboMultAccuracyAny(FightHelpBean _str, int... _args) {
        return _str.comboMultAccuracyAny();
    }

    public static boolean callFightHelpBeanComboMultEvasiness(FightHelpBean _str, int... _args) {
        return _str.comboMultEvasiness(_args[0]);
    }

    public static boolean callFightHelpBeanComboMultEvasinessAny(FightHelpBean _str, int... _args) {
        return _str.comboMultEvasinessAny();
    }

    public static boolean callFightHelpBeanComboMultNormal(FightHelpBean _str, int... _args) {
        return _str.comboMultNormal(_args[0]);
    }

    public static boolean callFightHelpBeanComboMultNormalAny(FightHelpBean _str, int... _args) {
        return _str.comboMultNormalAny();
    }

    public static boolean callFightHelpBeanComboMultSpeed(FightHelpBean _str, int... _args) {
        return _str.comboMultSpeed(_args[0]);
    }

    public static boolean callFightHelpBeanComboMultSpeedAny(FightHelpBean _str, int... _args) {
        return _str.comboMultSpeedAny();
    }

    public static CustList<CustList<TranslatedKey>> callFightHelpBeanComboMultStatGet(FightHelpBean _str, int... _args) {
        return _str.getComboMultStat();
    }

    public static CustList<TranslatedKey> callFightHelpBeanCopyAbilitiesGet(FightHelpBean _str, int... _args) {
        return _str.getCopyAbilities();
    }

    public static CustList<TranslatedKey> callFightHelpBeanCopyMoveTypesAbGet(FightHelpBean _str, int... _args) {
        return _str.getCopyMoveTypesAb();
    }

    public static CustList<TranslatedKey> callFightHelpBeanDamagingMovesGet(FightHelpBean _str, int... _args) {
        return _str.getDamagingMoves();
    }

    public static String callFightHelpBeanDamgeFormulaGet(FightHelpBean _str, int... _args) {
        return _str.getDamgeFormula();
    }

    public static long callFightHelpBeanDefaultBoostValueGet(FightHelpBean _str, int... _args) {
        return _str.getDefaultBoostValue();
    }

    public static CustList<TranslatedKey> callFightHelpBeanDeleteStatusMoveGet(FightHelpBean _str, int... _args) {
        return _str.getDeleteStatusMove();
    }

    public static CustList<TranslatedKey> callFightHelpBeanDeletedStatusSwitchGet(FightHelpBean _str, int... _args) {
        return _str.getDeletedStatusSwitch();
    }

    public static CustList<TranslatedKey> callFightHelpBeanDisappearingRoundMovesGet(FightHelpBean _str, int... _args) {
        return _str.getDisappearingRoundMoves();
    }

    public static CustList<TranslatedKey> callFightHelpBeanEffMovesGet(FightHelpBean _str, int... _args) {
        return _str.getEffMoves();
    }

    public static AbsMap<TranslatedKeyPair,Rate> callFightHelpBeanEfficiencyGet(FightHelpBean _str, int... _args) {
        return _str.getEfficiency();
    }

    public static CustList<TranslatedKey> callFightHelpBeanEntryHazardGet(FightHelpBean _str, int... _args) {
        return _str.getEntryHazard();
    }

    public static String callFightHelpBeanFleeingFormulaGet(FightHelpBean _str, int... _args) {
        return _str.getFleeingFormula();
    }

    public static int[][] callFightHelpBeanGetAnimAbsorb(FightHelpBean _str, int... _args) {
        return _str.getAnimAbsorb();
    }

    public static int[][] callFightHelpBeanGetAnimStatistic(FightHelpBean _str, int... _args) {
        return _str.getAnimStatistic(_args[0]);
    }

    public static String callFightHelpBeanGetEfficiency(FightHelpBean _str, int... _args) {
        return _str.getEfficiency(_args[0],_args[1]);
    }

    public static String callFightHelpBeanGetFomula(FightHelpBean _str, int... _args) {
        return _str.getFomula(_args[0]);
    }

    public static String callFightHelpBeanGetStab(FightHelpBean _str, int... _args) {
        return _str.getStab();
    }

    public static String callFightHelpBeanGetTrAbilitiesAchieveTarget(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesAchieveTarget(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesAllyMultStat(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesAllyMultStat(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesBoostingStat(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesBoostingStat(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesBreakImmu(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesBreakImmu(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesBreakProtectMoves(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesBreakProtectMoves(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesBreakable(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesBreakable(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesChangeTypeMoves(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesChangeTypeMoves(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesChangingTypesDamage(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesChangingTypesDamage(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesCopyAb(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesCopyAb(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesDamageStatis(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesDamageStatis(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesEndRound(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesEndRound(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesFighterStatis(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesFighterStatis(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesFighterStatisVar(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesFighterStatisVar(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesFighterStatus(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesFighterStatus(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesGlobal(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesGlobal(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesImmu(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesImmu(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesImmuAllies(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesImmuAllies(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesImmuAlliesDam(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesImmuAlliesDam(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesImmuCh(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesImmuCh(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesImmuMultStat(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesImmuMultStat(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesImmuSecEffOther(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesImmuSecEffOther(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesImmuSecEffOwner(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesImmuSecEffOwner(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesImmuTypes(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesImmuTypes(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesKoTarget(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesKoTarget(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesMultEvtCh(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesMultEvtCh(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesMultRateCh(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesMultRateCh(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesMultStat(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesMultStat(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesPartStatis(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesPartStatis(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesPartStatus(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesPartStatus(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesPrio(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesPrio(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesRateStatis(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesRateStatis(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesRevAbs(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesRevAbs(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesSentBegin(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesSentBegin(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesSentBeginOth(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesSentBeginOth(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesSentStatis(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesSentStatis(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesStatisVarUser(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesStatisVarUser(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesStatus(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesStatus(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesSwitch(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesSwitch(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesTakingItem(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesTakingItem(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesTargetDamage(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesTargetDamage(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesTypeDefMoves(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesTypeDefMoves(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesUserDamage(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesUserDamage(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesUserIgnTargetTeam(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesUserIgnTargetTeam(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesUserPower(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesUserPower(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesUserStabDamage(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesUserStabDamage(_args[0]);
    }

    public static String callFightHelpBeanGetTrAbilitiesUserTargetDamage(FightHelpBean _str, int... _args) {
        return _str.getTrAbilitiesUserTargetDamage(_args[0]);
    }

    public static String callFightHelpBeanGetTrAutoDamage(FightHelpBean _str, int... _args) {
        return _str.getTrAutoDamage(_args[0]);
    }

    public static String callFightHelpBeanGetTrBeginRoundStatus(FightHelpBean _str, int... _args) {
        return _str.getTrBeginRoundStatus(_args[0]);
    }

    public static String callFightHelpBeanGetTrBeginRoundStatusFoe(FightHelpBean _str, int... _args) {
        return _str.getTrBeginRoundStatusFoe(_args[0]);
    }

    public static String callFightHelpBeanGetTrBerryEndRound(FightHelpBean _str, int... _args) {
        return _str.getTrBerryEndRound(_args[0]);
    }

    public static String callFightHelpBeanGetTrBerrySpeed(FightHelpBean _str, int... _args) {
        return _str.getTrBerrySpeed(_args[0]);
    }

    public static String callFightHelpBeanGetTrBerryTarget(FightHelpBean _str, int... _args) {
        return _str.getTrBerryTarget(_args[0]);
    }

    public static String callFightHelpBeanGetTrBerryUser(FightHelpBean _str, int... _args) {
        return _str.getTrBerryUser(_args[0]);
    }

    public static String callFightHelpBeanGetTrChangingTypesAbilities(FightHelpBean _str, int... _args) {
        return _str.getTrChangingTypesAbilities(_args[0]);
    }

    public static String callFightHelpBeanGetTrComboEvtStat(FightHelpBean _str, int... _args) {
        return _str.getTrComboEvtStat(_args[0]);
    }

    public static String callFightHelpBeanGetTrComboMultStat(FightHelpBean _str, int... _args) {
        return _str.getTrComboMultStat(_args[0]);
    }

    public static String callFightHelpBeanGetTrCopyAbilities(FightHelpBean _str, int... _args) {
        return _str.getTrCopyAbilities(_args[0]);
    }

    public static String callFightHelpBeanGetTrCopyMoveTypesAb(FightHelpBean _str, int... _args) {
        return _str.getTrCopyMoveTypesAb(_args[0]);
    }

    public static String callFightHelpBeanGetTrDamagingMoves(FightHelpBean _str, int... _args) {
        return _str.getTrDamagingMoves(_args[0]);
    }

    public static String callFightHelpBeanGetTrDefaultMove(FightHelpBean _str, int... _args) {
        return _str.getTrDefaultMove();
    }

    public static String callFightHelpBeanGetTrDeleteStatusMove(FightHelpBean _str, int... _args) {
        return _str.getTrDeleteStatusMove(_args[0]);
    }

    public static String callFightHelpBeanGetTrDeletedStatusSwitch(FightHelpBean _str, int... _args) {
        return _str.getTrDeletedStatusSwitch(_args[0]);
    }

    public static String callFightHelpBeanGetTrDifficulty(FightHelpBean _str, int... _args) {
        return _str.getTrDifficulty(_args[0]);
    }

    public static String callFightHelpBeanGetTrEffMoves(FightHelpBean _str, int... _args) {
        return _str.getTrEffMoves(_args[0]);
    }

    public static String callFightHelpBeanGetTrEntryHazard(FightHelpBean _str, int... _args) {
        return _str.getTrEntryHazard(_args[0]);
    }

    public static String callFightHelpBeanGetTrGlobalMovesStatus(FightHelpBean _str, int... _args) {
        return _str.getTrGlobalMovesStatus(_args[0]);
    }

    public static String callFightHelpBeanGetTrImmuRecharging(FightHelpBean _str, int... _args) {
        return _str.getTrImmuRecharging(_args[0]);
    }

    public static String callFightHelpBeanGetTrImmuStatusAbility(FightHelpBean _str, int... _args) {
        return _str.getTrImmuStatusAbility(_args[0]);
    }

    public static String callFightHelpBeanGetTrItemSpeed(FightHelpBean _str, int... _args) {
        return _str.getTrItemSpeed(_args[0]);
    }

    public static String callFightHelpBeanGetTrItemsAbs(FightHelpBean _str, int... _args) {
        return _str.getTrItemsAbs(_args[0]);
    }

    public static String callFightHelpBeanGetTrItemsBoostingStat(FightHelpBean _str, int... _args) {
        return _str.getTrItemsBoostingStat(_args[0]);
    }

    public static String callFightHelpBeanGetTrItemsCancelImmu(FightHelpBean _str, int... _args) {
        return _str.getTrItemsCancelImmu(_args[0]);
    }

    public static String callFightHelpBeanGetTrItemsFighterStatis(FightHelpBean _str, int... _args) {
        return _str.getTrItemsFighterStatis(_args[0]);
    }

    public static String callFightHelpBeanGetTrItemsFighterStatus(FightHelpBean _str, int... _args) {
        return _str.getTrItemsFighterStatus(_args[0]);
    }

    public static String callFightHelpBeanGetTrItemsImmu(FightHelpBean _str, int... _args) {
        return _str.getTrItemsImmu(_args[0]);
    }

    public static String callFightHelpBeanGetTrItemsImmuTypes(FightHelpBean _str, int... _args) {
        return _str.getTrItemsImmuTypes(_args[0]);
    }

    public static String callFightHelpBeanGetTrItemsMultStat(FightHelpBean _str, int... _args) {
        return _str.getTrItemsMultStat(_args[0]);
    }

    public static String callFightHelpBeanGetTrItemsProtAgainstKo(FightHelpBean _str, int... _args) {
        return _str.getTrItemsProtAgainstKo(_args[0]);
    }

    public static String callFightHelpBeanGetTrItemsSentBegin(FightHelpBean _str, int... _args) {
        return _str.getTrItemsSentBegin(_args[0]);
    }

    public static String callFightHelpBeanGetTrItemsSentBeginOth(FightHelpBean _str, int... _args) {
        return _str.getTrItemsSentBeginOth(_args[0]);
    }

    public static String callFightHelpBeanGetTrItemsTargetDamage(FightHelpBean _str, int... _args) {
        return _str.getTrItemsTargetDamage(_args[0]);
    }

    public static String callFightHelpBeanGetTrItemsTypesDef(FightHelpBean _str, int... _args) {
        return _str.getTrItemsTypesDef(_args[0]);
    }

    public static String callFightHelpBeanGetTrItemsUserDamage(FightHelpBean _str, int... _args) {
        return _str.getTrItemsUserDamage(_args[0]);
    }

    public static String callFightHelpBeanGetTrItemsUserPower(FightHelpBean _str, int... _args) {
        return _str.getTrItemsUserPower(_args[0]);
    }

    public static String callFightHelpBeanGetTrLawRate(FightHelpBean _str, int... _args) {
        return _str.getTrLawRate(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesAttracting(FightHelpBean _str, int... _args) {
        return _str.getTrMovesAttracting(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesBoostCh(FightHelpBean _str, int... _args) {
        return _str.getTrMovesBoostCh(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesCannotKo(FightHelpBean _str, int... _args) {
        return _str.getTrMovesCannotKo(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesChangeTypeMoves(FightHelpBean _str, int... _args) {
        return _str.getTrMovesChangeTypeMoves(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesChangingAttOrder(FightHelpBean _str, int... _args) {
        return _str.getTrMovesChangingAttOrder(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesFoeTeamMultStat(FightHelpBean _str, int... _args) {
        return _str.getTrMovesFoeTeamMultStat(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesGlobal(FightHelpBean _str, int... _args) {
        return _str.getTrMovesGlobal(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesGlobalAcc(FightHelpBean _str, int... _args) {
        return _str.getTrMovesGlobalAcc(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesGlobalBreakImmu(FightHelpBean _str, int... _args) {
        return _str.getTrMovesGlobalBreakImmu(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesGlobalBreakImmuAb(FightHelpBean _str, int... _args) {
        return _str.getTrMovesGlobalBreakImmuAb(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesGlobalMultStat(FightHelpBean _str, int... _args) {
        return _str.getTrMovesGlobalMultStat(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesGlobalPrepaDamage(FightHelpBean _str, int... _args) {
        return _str.getTrMovesGlobalPrepaDamage(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesHealingSubstitute(FightHelpBean _str, int... _args) {
        return _str.getTrMovesHealingSubstitute(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesIgnAcc(FightHelpBean _str, int... _args) {
        return _str.getTrMovesIgnAcc(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesIgnEva(FightHelpBean _str, int... _args) {
        return _str.getTrMovesIgnEva(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesIgnIncDef(FightHelpBean _str, int... _args) {
        return _str.getTrMovesIgnIncDef(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesIgnLowAtt(FightHelpBean _str, int... _args) {
        return _str.getTrMovesIgnLowAtt(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesInvokDamage(FightHelpBean _str, int... _args) {
        return _str.getTrMovesInvokDamage(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesInvoking(FightHelpBean _str, int... _args) {
        return _str.getTrMovesInvoking(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesKoTarget(FightHelpBean _str, int... _args) {
        return _str.getTrMovesKoTarget(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesMirror(FightHelpBean _str, int... _args) {
        return _str.getTrMovesMirror(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesProtAgainstKo(FightHelpBean _str, int... _args) {
        return _str.getTrMovesProtAgainstKo(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesProtecting(FightHelpBean _str, int... _args) {
        return _str.getTrMovesProtecting(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesProtectingTypes(FightHelpBean _str, int... _args) {
        return _str.getTrMovesProtectingTypes(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesSecEffItems(FightHelpBean _str, int... _args) {
        return _str.getTrMovesSecEffItems(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesTargetPower(FightHelpBean _str, int... _args) {
        return _str.getTrMovesTargetPower(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesTargetTeamDamage(FightHelpBean _str, int... _args) {
        return _str.getTrMovesTargetTeamDamage(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesTeam(FightHelpBean _str, int... _args) {
        return _str.getTrMovesTeam(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesTeamMultStat(FightHelpBean _str, int... _args) {
        return _str.getTrMovesTeamMultStat(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesThieving(FightHelpBean _str, int... _args) {
        return _str.getTrMovesThieving(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesTypeDefMoves(FightHelpBean _str, int... _args) {
        return _str.getTrMovesTypeDefMoves(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesTypesDefItem(FightHelpBean _str, int... _args) {
        return _str.getTrMovesTypesDefItem(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesTypesDefWeather(FightHelpBean _str, int... _args) {
        return _str.getTrMovesTypesDefWeather(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesUnprotectingTypes(FightHelpBean _str, int... _args) {
        return _str.getTrMovesUnprotectingTypes(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesUserAllyDamage(FightHelpBean _str, int... _args) {
        return _str.getTrMovesUserAllyDamage(_args[0]);
    }

    public static String callFightHelpBeanGetTrMovesUserPower(FightHelpBean _str, int... _args) {
        return _str.getTrMovesUserPower(_args[0]);
    }

    public static String callFightHelpBeanGetTrPrepaRoundMoves(FightHelpBean _str, int... _args) {
        return _str.getTrPrepaRoundMoves(_args[0]);
    }

    public static String callFightHelpBeanGetTrPressureAbilities(FightHelpBean _str, int... _args) {
        return _str.getTrPressureAbilities(_args[0]);
    }

    public static String callFightHelpBeanGetTrPrivatingMoves(FightHelpBean _str, int... _args) {
        return _str.getTrPrivatingMoves(_args[0]);
    }

    public static String callFightHelpBeanGetTrProtectAbilities(FightHelpBean _str, int... _args) {
        return _str.getTrProtectAbilities(_args[0]);
    }

    public static String callFightHelpBeanGetTrProtectItems(FightHelpBean _str, int... _args) {
        return _str.getTrProtectItems(_args[0]);
    }

    public static String callFightHelpBeanGetTrProtectMoves(FightHelpBean _str, int... _args) {
        return _str.getTrProtectMoves(_args[0]);
    }

    public static String callFightHelpBeanGetTrRechargeMoves(FightHelpBean _str, int... _args) {
        return _str.getTrRechargeMoves(_args[0]);
    }

    public static String callFightHelpBeanGetTrRecoilAbilities(FightHelpBean _str, int... _args) {
        return _str.getTrRecoilAbilities(_args[0]);
    }

    public static String callFightHelpBeanGetTrRecoilItems(FightHelpBean _str, int... _args) {
        return _str.getTrRecoilItems(_args[0]);
    }

    public static String callFightHelpBeanGetTrReverseSpeedMoves(FightHelpBean _str, int... _args) {
        return _str.getTrReverseSpeedMoves(_args[0]);
    }

    public static String callFightHelpBeanGetTrSlowAbilities(FightHelpBean _str, int... _args) {
        return _str.getTrSlowAbilities(_args[0]);
    }

    public static String callFightHelpBeanGetTrSlowItems(FightHelpBean _str, int... _args) {
        return _str.getTrSlowItems(_args[0]);
    }

    public static String callFightHelpBeanGetTrSpeedPreparingItems(FightHelpBean _str, int... _args) {
        return _str.getTrSpeedPreparingItems(_args[0]);
    }

    public static String callFightHelpBeanGetTrStatistic(FightHelpBean _str, int... _args) {
        return _str.getTrStatistic(_args[0]);
    }

    public static String callFightHelpBeanGetTrStatusDamage(FightHelpBean _str, int... _args) {
        return _str.getTrStatusDamage(_args[0]);
    }

    public static String callFightHelpBeanGetTrStatusMultStat(FightHelpBean _str, int... _args) {
        return _str.getTrStatusMultStat(_args[0]);
    }

    public static String callFightHelpBeanGetTrSubstitutingMoves(FightHelpBean _str, int... _args) {
        return _str.getTrSubstitutingMoves(_args[0]);
    }

    public static String callFightHelpBeanGetTrSuccessfulStatus(FightHelpBean _str, int... _args) {
        return _str.getTrSuccessfulStatus(_args[0]);
    }

    public static CustList<TranslatedKey> callFightHelpBeanGlobalMovesStatusGet(FightHelpBean _str, int... _args) {
        return _str.getGlobalMovesStatus();
    }

    public static long callFightHelpBeanHappinessPointsGet(FightHelpBean _str, int... _args) {
        return _str.getHappinessPoints();
    }

    public static boolean callFightHelpBeanHasLawForAttack(FightHelpBean _str, int... _args) {
        return _str.hasLawForAttack(_args[0]);
    }

    public static boolean callFightHelpBeanHasLawForAttackAny(FightHelpBean _str, int... _args) {
        return _str.hasLawForAttackAny();
    }

    public static boolean callFightHelpBeanHasLawForHeal(FightHelpBean _str, int... _args) {
        return _str.hasLawForHeal(_args[0]);
    }

    public static boolean callFightHelpBeanHasLawForHealAny(FightHelpBean _str, int... _args) {
        return _str.hasLawForHealAny();
    }

    public static boolean callFightHelpBeanImmuChTeamMove(FightHelpBean _str, int... _args) {
        return _str.immuChTeamMove(_args[0]);
    }

    public static boolean callFightHelpBeanImmuChTeamMoveAny(FightHelpBean _str, int... _args) {
        return _str.immuChTeamMoveAny();
    }

    public static CustList<TranslatedKey> callFightHelpBeanImmuRechargingGet(FightHelpBean _str, int... _args) {
        return _str.getImmuRecharging();
    }

    public static boolean callFightHelpBeanImmuStatisTeamMove(FightHelpBean _str, int... _args) {
        return _str.immuStatisTeamMove(_args[0]);
    }

    public static boolean callFightHelpBeanImmuStatisTeamMoveAny(FightHelpBean _str, int... _args) {
        return _str.immuStatisTeamMoveAny();
    }

    public static CustList<TranslatedKey> callFightHelpBeanImmuStatusAbilityGet(FightHelpBean _str, int... _args) {
        return _str.getImmuStatusAbility();
    }

    public static boolean callFightHelpBeanImmuStatusTeamMove(FightHelpBean _str, int... _args) {
        return _str.immuStatusTeamMove(_args[0]);
    }

    public static boolean callFightHelpBeanImmuStatusTeamMoveAny(FightHelpBean _str, int... _args) {
        return _str.immuStatusTeamMoveAny();
    }

    public static boolean callFightHelpBeanIsDisappearingUser(FightHelpBean _str, int... _args) {
        return _str.isDisappearingUser(_args[0]);
    }

    public static boolean callFightHelpBeanItemBoostAccuracy(FightHelpBean _str, int... _args) {
        return _str.itemBoostAccuracy(_args[0]);
    }

    public static boolean callFightHelpBeanItemBoostAccuracyAny(FightHelpBean _str, int... _args) {
        return _str.itemBoostAccuracyAny();
    }

    public static boolean callFightHelpBeanItemBoostCh(FightHelpBean _str, int... _args) {
        return _str.itemBoostCh(_args[0]);
    }

    public static boolean callFightHelpBeanItemBoostChAny(FightHelpBean _str, int... _args) {
        return _str.itemBoostChAny();
    }

    public static boolean callFightHelpBeanItemBoostEvasiness(FightHelpBean _str, int... _args) {
        return _str.itemBoostEvasiness(_args[0]);
    }

    public static boolean callFightHelpBeanItemBoostEvasinessAny(FightHelpBean _str, int... _args) {
        return _str.itemBoostEvasinessAny();
    }

    public static boolean callFightHelpBeanItemBoostNormal(FightHelpBean _str, int... _args) {
        return _str.itemBoostNormal(_args[0]);
    }

    public static boolean callFightHelpBeanItemBoostNormalAny(FightHelpBean _str, int... _args) {
        return _str.itemBoostNormalAny();
    }

    public static boolean callFightHelpBeanItemBoostSpeed(FightHelpBean _str, int... _args) {
        return _str.itemBoostSpeed(_args[0]);
    }

    public static boolean callFightHelpBeanItemBoostSpeedAny(FightHelpBean _str, int... _args) {
        return _str.itemBoostSpeedAny();
    }

//    public static NaSt callFightHelpBeanItemBoostSpeed(FightHelpBean _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new FightHelpBeanItemBoostSpeed(),_str,_args);
//    }
//
//    public static NaSt callFightHelpBeanItemBoostSpeedAny(FightHelpBean _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new FightHelpBeanItemBoostSpeedAny(),_str,_args);
//    }

    public static boolean callFightHelpBeanItemMultAccuracy(FightHelpBean _str, int... _args) {
        return _str.itemMult(_args[0], Statistic.ACCURACY);
    }

    public static boolean callFightHelpBeanItemMultEvasiness(FightHelpBean _str, int... _args) {
        return _str.itemMult(_args[0], Statistic.EVASINESS);
    }

    public static boolean callFightHelpBeanItemMultAccuracyAny(FightHelpBean _str, int... _args) {
        return _str.itemMultAccuracyAny();
    }

    public static boolean callFightHelpBeanItemMultEvasinessAny(FightHelpBean _str, int... _args) {
        return _str.itemMultEvasinessAny();
    }

    public static boolean callFightHelpBeanItemMultNormal(FightHelpBean _str, int... _args) {
        return _str.itemMultNormal(_args[0]);
    }

    public static boolean callFightHelpBeanItemMultNormalAny(FightHelpBean _str, int... _args) {
        return _str.itemMultNormalAny();
    }

    public static boolean callFightHelpBeanItemMultSpeed(FightHelpBean _str, int... _args) {
        return _str.itemMult(_args[0], Statistic.SPEED);
    }
//
//    public static NaSt callFightHelpBeanItemMultSpeedAny(FightHelpBean _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new FightHelpBeanItemMultSpeedAny(),_str,_args);
//    }

    public static boolean callFightHelpBeanItemMultSpeedAny(FightHelpBean _str, int... _args) {
        return _str.itemMultSpeedAny();
    }

    public static CustList<TranslatedKey> callFightHelpBeanItemSpeedGet(FightHelpBean _str, int... _args) {
        return _str.getItemSpeed();
    }

    public static CustList<TranslatedKey> callFightHelpBeanItemsAbsGet(FightHelpBean _str, int... _args) {
        return _str.getItemsAbs();
    }

    public static CustList<TranslatedKey> callFightHelpBeanItemsBoostingStatGet(FightHelpBean _str, int... _args) {
        return _str.getItemsBoostingStat();
    }

    public static CustList<TranslatedKey> callFightHelpBeanItemsCancelImmuGet(FightHelpBean _str, int... _args) {
        return _str.getItemsCancelImmu();
    }

    public static CustList<TranslatedKey> callFightHelpBeanItemsFighterStatisGet(FightHelpBean _str, int... _args) {
        return _str.getItemsFighterStatis();
    }

    public static CustList<TranslatedKey> callFightHelpBeanItemsFighterStatusGet(FightHelpBean _str, int... _args) {
        return _str.getItemsFighterStatus();
    }

    public static CustList<TranslatedKey> callFightHelpBeanItemsImmuGet(FightHelpBean _str, int... _args) {
        return _str.getItemsImmu();
    }

    public static CustList<TranslatedKey> callFightHelpBeanItemsImmuTypesGet(FightHelpBean _str, int... _args) {
        return _str.getItemsImmuTypes();
    }

    public static CustList<TranslatedKey> callFightHelpBeanItemsMultStatGet(FightHelpBean _str, int... _args) {
        return _str.getItemsMultStat();
    }

    public static CustList<TranslatedKey> callFightHelpBeanItemsProtAgainstKoGet(FightHelpBean _str, int... _args) {
        return _str.getItemsProtAgainstKo();
    }

    public static CustList<TranslatedKey> callFightHelpBeanItemsSentBeginOtherGet(FightHelpBean _str, int... _args) {
        return _str.getItemsSentBeginOther();
    }

    public static CustList<TranslatedKey> callFightHelpBeanItemsSentBeginWeatherGet(FightHelpBean _str, int... _args) {
        return _str.getItemsSentBeginWeather();
    }

    public static CustList<TranslatedKey> callFightHelpBeanItemsTargetDamageGet(FightHelpBean _str, int... _args) {
        return _str.getItemsTargetDamage();
    }

    public static CustList<TranslatedKey> callFightHelpBeanItemsTypesDefGet(FightHelpBean _str, int... _args) {
        return _str.getItemsTypesDef();
    }

    public static CustList<TranslatedKey> callFightHelpBeanItemsUserDamageGet(FightHelpBean _str, int... _args) {
        return _str.getItemsUserDamage();
    }

    public static CustList<TranslatedKey> callFightHelpBeanItemsUserPowerGet(FightHelpBean _str, int... _args) {
        return _str.getItemsUserPower();
    }

    public static AbsMap<String,AbsBasicTreeMap<Rate,Rate>> callFightHelpBeanLawsRatesGet(FightHelpBean _str, int... _args) {
        return _str.getLawsRates();
    }

    public static AbsMap<String,String> callFightHelpBeanMapAutoDamageGet(FightHelpBean _str, int... _args) {
        return _str.getMapAutoDamage();
    }

    public static AbsMap<String,String> callFightHelpBeanMapVarGet(FightHelpBean _str, int... _args) {
        return _str.getMapVar();
    }

    public static Rate callFightHelpBeanMinHpNotKoGet(FightHelpBean _str, int... _args) {
        return _str.getMinHpNotKo();
    }

    public static boolean callFightHelpBeanMoveFoeTeamMultAccuracy(FightHelpBean _str, int... _args) {
        return _str.moveFoeTeamMultAccuracy(_args[0]);
    }

    public static boolean callFightHelpBeanMoveFoeTeamMultAccuracyAny(FightHelpBean _str, int... _args) {
        return _str.moveFoeTeamMultAccuracyAny();
    }

    public static boolean callFightHelpBeanMoveFoeTeamMultEvasiness(FightHelpBean _str, int... _args) {
        return _str.moveFoeTeamMultEvasiness(_args[0]);
    }

    public static boolean callFightHelpBeanMoveFoeTeamMultEvasinessAny(FightHelpBean _str, int... _args) {
        return _str.moveFoeTeamMultEvasinessAny();
    }

    public static boolean callFightHelpBeanMoveFoeTeamMultNormal(FightHelpBean _str, int... _args) {
        return _str.moveFoeTeamMultNormal(_args[0]);
    }

    public static boolean callFightHelpBeanMoveFoeTeamMultNormalAny(FightHelpBean _str, int... _args) {
        return _str.moveFoeTeamMultNormalAny();
    }

    public static boolean callFightHelpBeanMoveFoeTeamMultSpeed(FightHelpBean _str, int... _args) {
        return _str.moveFoeTeamMultSpeed(_args[0]);
    }

    public static boolean callFightHelpBeanMoveFoeTeamMultSpeedAny(FightHelpBean _str, int... _args) {
        return _str.moveFoeTeamMultSpeedAny();
    }

    public static boolean callFightHelpBeanMoveGlobalMultAccuracy(FightHelpBean _str, int... _args) {
        return _str.moveGlobalMultAccuracy(_args[0]);
    }

    public static boolean callFightHelpBeanMoveGlobalMultAccuracyAny(FightHelpBean _str, int... _args) {
        return _str.moveGlobalMultAccuracyAny();
    }

    public static boolean callFightHelpBeanMoveGlobalMultEvasiness(FightHelpBean _str, int... _args) {
        return _str.moveGlobalMultEvasiness(_args[0]);
    }

    public static boolean callFightHelpBeanMoveGlobalMultEvasinessAny(FightHelpBean _str, int... _args) {
        return _str.moveGlobalMultEvasinessAny();
    }

    public static boolean callFightHelpBeanMoveGlobalMultNormal(FightHelpBean _str, int... _args) {
        return _str.moveGlobalMultNormal(_args[0]);
    }

    public static boolean callFightHelpBeanMoveGlobalMultNormalAny(FightHelpBean _str, int... _args) {
        return _str.moveGlobalMultNormalAny();
    }

    public static boolean callFightHelpBeanMoveGlobalMultSpeed(FightHelpBean _str, int... _args) {
        return _str.moveGlobalMultSpeed(_args[0]);
    }

    public static boolean callFightHelpBeanMoveGlobalMultSpeedAny(FightHelpBean _str, int... _args) {
        return _str.moveGlobalMultSpeedAny();
    }

    public static boolean callFightHelpBeanMoveTeamMultAccuracy(FightHelpBean _str, int... _args) {
        return _str.moveTeamMultAccuracy(_args[0]);
    }

    public static boolean callFightHelpBeanMoveTeamMultAccuracyAny(FightHelpBean _str, int... _args) {
        return _str.moveTeamMultAccuracyAny();
    }

    public static boolean callFightHelpBeanMoveTeamMultEvasiness(FightHelpBean _str, int... _args) {
        return _str.moveTeamMultEvasiness(_args[0]);
    }

    public static boolean callFightHelpBeanMoveTeamMultEvasinessAny(FightHelpBean _str, int... _args) {
        return _str.moveTeamMultEvasinessAny();
    }

    public static boolean callFightHelpBeanMoveTeamMultNormal(FightHelpBean _str, int... _args) {
        return _str.moveTeamMultNormal(_args[0]);
    }

    public static boolean callFightHelpBeanMoveTeamMultNormalAny(FightHelpBean _str, int... _args) {
        return _str.moveTeamMultNormalAny();
    }

    public static boolean callFightHelpBeanMoveTeamMultSpeed(FightHelpBean _str, int... _args) {
        return _str.moveTeamMultSpeed(_args[0]);
    }

    public static boolean callFightHelpBeanMoveTeamMultSpeedAny(FightHelpBean _str, int... _args) {
        return _str.moveTeamMultSpeedAny();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesAttractingGet(FightHelpBean _str, int... _args) {
        return _str.getMovesAttracting();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesBoostChGet(FightHelpBean _str, int... _args) {
        return _str.getMovesBoostCh();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesCannotKoGet(FightHelpBean _str, int... _args) {
        return _str.getMovesCannotKo();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesChangeTypeMovesGet(FightHelpBean _str, int... _args) {
        return _str.getMovesChangeTypeMoves();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesChangingAttOrderGet(FightHelpBean _str, int... _args) {
        return _str.getMovesChangingAttOrder();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesFoeTeamMultStatGet(FightHelpBean _str, int... _args) {
        return _str.getMovesFoeTeamMultStat();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesGlobalAccGet(FightHelpBean _str, int... _args) {
        return _str.getMovesGlobalAcc();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesGlobalBreakImmuAbGet(FightHelpBean _str, int... _args) {
        return _str.getMovesGlobalBreakImmuAb();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesGlobalBreakImmuGet(FightHelpBean _str, int... _args) {
        return _str.getMovesGlobalBreakImmu();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesGlobalGet(FightHelpBean _str, int... _args) {
        return _str.getMovesGlobal();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesGlobalMultStatGet(FightHelpBean _str, int... _args) {
        return _str.getMovesGlobalMultStat();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesGlobalPrepaDamageGet(FightHelpBean _str, int... _args) {
        return _str.getMovesGlobalPrepaDamage();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesHealingSubstituteGet(FightHelpBean _str, int... _args) {
        return _str.getMovesHealingSubstitute();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesIgnAccGet(FightHelpBean _str, int... _args) {
        return _str.getMovesIgnAcc();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesIgnEvaGet(FightHelpBean _str, int... _args) {
        return _str.getMovesIgnEva();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesIgnIncDefGet(FightHelpBean _str, int... _args) {
        return _str.getMovesIgnIncDef();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesIgnLowAttGet(FightHelpBean _str, int... _args) {
        return _str.getMovesIgnLowAtt();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesInvokDamageGet(FightHelpBean _str, int... _args) {
        return _str.getMovesInvokDamage();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesInvokingGet(FightHelpBean _str, int... _args) {
        return _str.getMovesInvoking();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesKoTargetGet(FightHelpBean _str, int... _args) {
        return _str.getMovesKoTarget();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesMirrorGet(FightHelpBean _str, int... _args) {
        return _str.getMovesMirror();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesProtAgainstKoGet(FightHelpBean _str, int... _args) {
        return _str.getMovesProtAgainstKo();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesProtectingGet(FightHelpBean _str, int... _args) {
        return _str.getMovesProtecting();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesProtectingTypesGet(FightHelpBean _str, int... _args) {
        return _str.getMovesProtectingTypes();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesSecEffItemsGet(FightHelpBean _str, int... _args) {
        return _str.getMovesSecEffItems();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesTargetPowerGet(FightHelpBean _str, int... _args) {
        return _str.getMovesTargetPower();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesTargetTeamDamageGet(FightHelpBean _str, int... _args) {
        return _str.getMovesTargetTeamDamage();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesTeamGet(FightHelpBean _str, int... _args) {
        return _str.getMovesTeam();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesTeamMultStatGet(FightHelpBean _str, int... _args) {
        return _str.getMovesTeamMultStat();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesThievingGet(FightHelpBean _str, int... _args) {
        return _str.getMovesThieving();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesTypeDefMovesGet(FightHelpBean _str, int... _args) {
        return _str.getMovesTypeDefMoves();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesTypesDefItemGet(FightHelpBean _str, int... _args) {
        return _str.getMovesTypesDefItem();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesTypesDefWeatherGet(FightHelpBean _str, int... _args) {
        return _str.getMovesTypesDefWeather();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesUnprotectingTypesGet(FightHelpBean _str, int... _args) {
        return _str.getMovesUnprotectingTypes();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesUserAllyDamageGet(FightHelpBean _str, int... _args) {
        return _str.getMovesUserAllyDamage();
    }

    public static CustList<TranslatedKey> callFightHelpBeanMovesUserPowerGet(FightHelpBean _str, int... _args) {
        return _str.getMovesUserPower();
    }

    public static boolean callFightHelpBeanNextRowAfter(FightHelpBean _str, int... _args) {
        return _str.nextRowAfter(_args[0]);
    }

    public static CustList<TranslatedKey> callFightHelpBeanPrepaRoundMovesGet(FightHelpBean _str, int... _args) {
        return _str.getPrepaRoundMoves();
    }

    public static CustList<TranslatedKey> callFightHelpBeanPressureAbilitiesGet(FightHelpBean _str, int... _args) {
        return _str.getPressureAbilities();
    }

    public static CustList<TranslatedKey> callFightHelpBeanPrivatingMovesGet(FightHelpBean _str, int... _args) {
        return _str.getPrivatingMoves();
    }

    public static CustList<TranslatedKey> callFightHelpBeanProtectAbilitiesGet(FightHelpBean _str, int... _args) {
        return _str.getProtectAbilities();
    }

    public static CustList<TranslatedKey> callFightHelpBeanProtectItemsGet(FightHelpBean _str, int... _args) {
        return _str.getProtectItems();
    }

    public static CustList<TranslatedKey> callFightHelpBeanProtectMovesGet(FightHelpBean _str, int... _args) {
        return _str.getProtectMoves();
    }

    public static String callFightHelpBeanRateFormulaChGet(FightHelpBean _str, int... _args) {
        return _str.getRateFormulaCh();
    }

    public static String callFightHelpBeanRateFormulaGet(FightHelpBean _str, int... _args) {
        return _str.getRateFormula();
    }

    public static String callFightHelpBeanBoostVarGet(FightHelpBean _str, int... _args) {
        return _str.getBoostVar();
    }
    public static AbsMap<String,String> callFightHelpBeanRatesGet(FightHelpBean _str, int... _args) {
        return _str.getRates();
    }

    public static CustList<TranslatedKey> callFightHelpBeanRechargeMovesGet(FightHelpBean _str, int... _args) {
        return _str.getRechargeMoves();
    }

    public static CustList<TranslatedKey> callFightHelpBeanRecoilAbilitiesGet(FightHelpBean _str, int... _args) {
        return _str.getRecoilAbilities();
    }

    public static CustList<TranslatedKey> callFightHelpBeanRecoilItemsGet(FightHelpBean _str, int... _args) {
        return _str.getRecoilItems();
    }

    public static CustList<TranslatedKey> callFightHelpBeanReverseSpeedMovesGet(FightHelpBean _str, int... _args) {
        return _str.getReverseSpeedMoves();
    }

    public static CustList<TranslatedKey> callFightHelpBeanSlowAbilitiesGet(FightHelpBean _str, int... _args) {
        return _str.getSlowAbilities();
    }

    public static CustList<TranslatedKey> callFightHelpBeanSlowItemsGet(FightHelpBean _str, int... _args) {
        return _str.getSlowItems();
    }

    public static CustList<TranslatedKey> callFightHelpBeanSpeedPreparingItemsGet(FightHelpBean _str, int... _args) {
        return _str.getSpeedPreparingItems();
    }

    public static CustList<TranslatedKey> callFightHelpBeanStatisticAnimGet(FightHelpBean _str, int... _args) {
        return _str.getStatisticAnim();
    }

    public static CustList<TranslatedKey> callFightHelpBeanStatusDamageGet(FightHelpBean _str, int... _args) {
        return _str.getStatusDamage();
    }

    public static boolean callFightHelpBeanStatusMultAccuracy(FightHelpBean _str, int... _args) {
        return _str.statusMultAccuracy(_args[0]);
    }

    public static boolean callFightHelpBeanStatusMultAccuracyAny(FightHelpBean _str, int... _args) {
        return _str.statusMultAccuracyAny();
    }

    public static boolean callFightHelpBeanStatusMultEvasiness(FightHelpBean _str, int... _args) {
        return _str.statusMultEvasiness(_args[0]);
    }

    public static boolean callFightHelpBeanStatusMultEvasinessAny(FightHelpBean _str, int... _args) {
        return _str.statusMultEvasinessAny();
    }

    public static boolean callFightHelpBeanStatusMultNormal(FightHelpBean _str, int... _args) {
        return _str.statusMultNormal(_args[0]);
    }

    public static boolean callFightHelpBeanStatusMultNormalAny(FightHelpBean _str, int... _args) {
        return _str.statusMultNormalAny();
    }

    public static boolean callFightHelpBeanStatusMultSpeed(FightHelpBean _str, int... _args) {
        return _str.statusMultSpeed(_args[0]);
    }

    public static boolean callFightHelpBeanStatusMultSpeedAny(FightHelpBean _str, int... _args) {
        return _str.statusMultSpeedAny();
    }

    public static CustList<TranslatedKey> callFightHelpBeanStatusMultStatGet(FightHelpBean _str, int... _args) {
        return _str.getStatusMultStat();
    }

    public static Rate callFightHelpBeanStrongMoveGet(FightHelpBean _str, int... _args) {
        return _str.getStrongMove();
    }

    public static CustList<TranslatedKey> callFightHelpBeanSubstitutingMovesGet(FightHelpBean _str, int... _args) {
        return _str.getSubstitutingMoves();
    }

    public static CustList<TranslatedKey> callFightHelpBeanSuccessfulStatusGet(FightHelpBean _str, int... _args) {
        return _str.getSuccessfulStatus();
    }

    public static CustList<TranslatedKey> callFightHelpBeanTypesGet(FightHelpBean _str, int... _args) {
        return _str.getTypes();
    }

    public static AbsMap<String,String> callFightHelpBeanVarCatchingFormulaGet(FightHelpBean _str, int... _args) {
        return _str.getVarCatchingFormula();
    }

    public static AbsMap<String,String> callFightHelpBeanVarFleeingFormulaGet(FightHelpBean _str, int... _args) {
        return _str.getVarFleeingFormula();
    }

    public static AbsMap<String,String> callFightHelpBeanVarRatesGet(FightHelpBean _str, int... _args) {
        return _str.getVarRates();
    }

    public static boolean callFightHelpBeanWithConstDamage(FightHelpBean _str, int... _args) {
        return _str.withConstDamage(_args[0]);
    }

    public static boolean callFightHelpBeanWithConstDamageAny(FightHelpBean _str, int... _args) {
        return _str.withConstDamageAny();
    }

    public static boolean callFightHelpBeanWithMultDamage(FightHelpBean _str, int... _args) {
        return _str.withMultDamage(_args[0]);
    }

    public static boolean callFightHelpBeanWithMultDamageAny(FightHelpBean _str, int... _args) {
        return _str.withMultDamageAny();
    }

    public static boolean callFightHelpBeanWithRandDamage(FightHelpBean _str, int... _args) {
        return _str.withRandDamage(_args[0]);
    }

    public static boolean callFightHelpBeanWithRandDamageAny(FightHelpBean _str, int... _args) {
        return _str.withRandDamageAny();
    }

    public static Rate callFightHelpBeanWonHappinessPointsLevelGet(FightHelpBean _str, int... _args) {
        return _str.getWonHappinessPointsLevel();
    }
    protected static FightHelpBean bean(FacadeGame _f) {
        PkData pk_ = pkDataByFacade(_f);
        FightHelpBean help_ = new FightHelpBean();
        help_.setBuilder(builder(_f));
        help_.getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.ROUND,new TranslationsFile());
        help_.getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.ROUND,new TranslationsFile());

        pk_.bean(help_, EN);
        beforeDisplaying(help_);
        return help_;
    }
}

