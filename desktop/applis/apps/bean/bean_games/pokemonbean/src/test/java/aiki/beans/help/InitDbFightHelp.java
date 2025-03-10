package aiki.beans.help;

import aiki.beans.*;
import aiki.beans.PkData;
import aiki.beans.PokemonBeanStruct;
import aiki.beans.db.InitDbConstr;
import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import code.bean.nat.*;
import code.scripts.pages.aiki.MessagesPkBean;
import code.sml.util.TranslationsFile;

public abstract class InitDbFightHelp extends InitDbConstr {

    public static NaSt callFightHelpBeanAbilitesMultEvtChGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitesMultEvtCh());
    }

    public static NaSt callFightHelpBeanAbilitesMultRateChGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitesMultRateCh());
    }

    public static NaSt callFightHelpBeanAbilitiesAchieveTargetGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesAchieveTarget());
    }

    public static NaSt callFightHelpBeanAbilitiesAllyMultStatGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesAllyMultStat());
    }

    public static NaSt callFightHelpBeanAbilitiesBoostingStatGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesBoostingStat());
    }

    public static NaSt callFightHelpBeanAbilitiesBreakImmuGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesBreakImmu());
    }

    public static NaSt callFightHelpBeanAbilitiesBreakProtectMovesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesBreakProtectMoves());
    }

    public static NaSt callFightHelpBeanAbilitiesBreakableGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesBreakable());
    }

    public static NaSt callFightHelpBeanAbilitiesChangingTypesDamageGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesChangingTypesDamage());
    }

    public static NaSt callFightHelpBeanAbilitiesCopyAbGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesCopyAb());
    }

    public static NaSt callFightHelpBeanAbilitiesDamageStatisGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesDamageStatis());
    }

    public static NaSt callFightHelpBeanAbilitiesEndRoundGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesEndRound());
    }

    public static NaSt callFightHelpBeanAbilitiesFighterStatisGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesFighterStatis());
    }

    public static NaSt callFightHelpBeanAbilitiesFighterStatisVarGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesFighterStatisVar());
    }

    public static NaSt callFightHelpBeanAbilitiesFighterStatusGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesFighterStatus());
    }

    public static NaSt callFightHelpBeanAbilitiesGlobalGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesGlobal());
    }

    public static NaSt callFightHelpBeanAbilitiesImmuAlliesDamGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesImmuAlliesDam());
    }

    public static NaSt callFightHelpBeanAbilitiesImmuAlliesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesImmuAllies());
    }

    public static NaSt callFightHelpBeanAbilitiesImmuChGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesImmuCh());
    }

    public static NaSt callFightHelpBeanAbilitiesImmuGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesImmu());
    }

    public static NaSt callFightHelpBeanAbilitiesImmuMultStatGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesImmuMultStat());
    }

    public static NaSt callFightHelpBeanAbilitiesImmuSecEffOtherGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesImmuSecEffOther());
    }

    public static NaSt callFightHelpBeanAbilitiesImmuSecEffOwnerGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesImmuSecEffOwner());
    }

    public static NaSt callFightHelpBeanAbilitiesImmuTypesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesImmuTypes());
    }

    public static NaSt callFightHelpBeanAbilitiesKoTargetGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesKoTarget());
    }

    public static NaSt callFightHelpBeanAbilitiesMultStatGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesMultStat());
    }

    public static NaSt callFightHelpBeanAbilitiesPartStatisGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesPartStatis());
    }

    public static NaSt callFightHelpBeanAbilitiesPartStatusGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesPartStatus());
    }

    public static NaSt callFightHelpBeanAbilitiesPrioGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesPrio());
    }

    public static NaSt callFightHelpBeanAbilitiesRateStatisGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesRateStatis());
    }

    public static NaSt callFightHelpBeanAbilitiesRevAbsGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesRevAbs());
    }

    public static NaSt callFightHelpBeanAbilitiesSentBeginWeatherGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesSentBeginWeather());
    }

    public static NaSt callFightHelpBeanAbilitiesSentBeginOtherGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesSentBeginOther());
    }

    public static NaSt callFightHelpBeanAbilitiesSentStatisGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesSentStatis());
    }

    public static NaSt callFightHelpBeanAbilitiesStatisVarUserGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesStatisVarUser());
    }

    public static NaSt callFightHelpBeanAbilitiesStatusGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesStatus());
    }

    public static NaSt callFightHelpBeanAbilitiesSwitchGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesSwitch());
    }

    public static NaSt callFightHelpBeanAbilitiesTakingItemGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesTakingItem());
    }

    public static NaSt callFightHelpBeanAbilitiesTargetDamageGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesTargetDamage());
    }

    public static NaSt callFightHelpBeanAbilitiesTypeDefMovesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesTypeDefMoves());
    }

    public static NaSt callFightHelpBeanAbilitiesUserDamageGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesUserDamage());
    }

    public static NaSt callFightHelpBeanAbilitiesUserIgnTargetTeamGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesUserIgnTargetTeam());
    }

    public static NaSt callFightHelpBeanAbilitiesUserPowerGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesUserPower());
    }

    public static NaSt callFightHelpBeanAbilitiesUserStabDamageGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesUserStabDamage());
    }

    public static NaSt callFightHelpBeanAbilitiesUserTargetDamageGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesUserTargetDamage());
    }

    public static NaSt callFightHelpBeanAbilityAllyMultAccuracy(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityAllyMultAccuracy(_args[0]));
    }

    public static NaSt callFightHelpBeanAbilityAllyMultAccuracyAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityAllyMultAccuracyAny());
    }

    public static NaSt callFightHelpBeanAbilityAllyMultEvasiness(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityAllyMultEvasiness(_args[0]));
    }

    public static NaSt callFightHelpBeanAbilityAllyMultEvasinessAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityAllyMultEvasinessAny());
    }

    public static NaSt callFightHelpBeanAbilityAllyMultNormal(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityAllyMultNormal(_args[0]));
    }

    public static NaSt callFightHelpBeanAbilityAllyMultNormalAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityAllyMultNormalAny());
    }

    public static NaSt callFightHelpBeanAbilityAllyMultSpeed(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityAllyMultSpeed(_args[0]));
    }

    public static NaSt callFightHelpBeanAbilityAllyMultSpeedAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityAllyMultSpeedAny());
    }

    public static NaSt callFightHelpBeanAbilityBoostAccuracy(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityBoostAccuracy(_args[0]));
    }

    public static NaSt callFightHelpBeanAbilityBoostAccuracyAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityBoostAccuracyAny());
    }

    public static NaSt callFightHelpBeanAbilityBoostCh(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityBoostCh(_args[0]));
    }

    public static NaSt callFightHelpBeanAbilityBoostChAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityBoostChAny());
    }

    public static NaSt callFightHelpBeanAbilityBoostEvasiness(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityBoostEvasiness(_args[0]));
    }

    public static NaSt callFightHelpBeanAbilityBoostEvasinessAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityBoostEvasinessAny());
    }

    public static NaSt callFightHelpBeanAbilityBoostNormal(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityBoostNormal(_args[0]));
    }

    public static NaSt callFightHelpBeanAbilityBoostNormalAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityBoostNormalAny());
    }

    public static NaSt callFightHelpBeanAbilityBoostSpeed(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityBoostSpeed(_args[0]));
    }

    public static NaSt callFightHelpBeanAbilityBoostSpeedAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityBoostSpeedAny());
    }

    public static NaSt callFightHelpBeanAbilityImmuMultAccuracy(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityImmuMultAccuracy(_args[0]));
    }

    public static NaSt callFightHelpBeanAbilityImmuMultAccuracyAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityImmuMultAccuracyAny());
    }

    public static NaSt callFightHelpBeanAbilityImmuMultEvasiness(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityImmuMultEvasiness(_args[0]));
    }

    public static NaSt callFightHelpBeanAbilityImmuMultEvasinessAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityImmuMultEvasinessAny());
    }

    public static NaSt callFightHelpBeanAbilityImmuMultNormal(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityImmuMultNormal(_args[0]));
    }

    public static NaSt callFightHelpBeanAbilityImmuMultNormalAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityImmuMultNormalAny());
    }

    public static NaSt callFightHelpBeanAbilityImmuMultSpeed(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityImmuMultSpeed(_args[0]));
    }

    public static NaSt callFightHelpBeanAbilityImmuMultSpeedAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityImmuMultSpeedAny());
    }

    public static NaSt callFightHelpBeanAbilityMultAccuracy(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityMultAccuracy(_args[0]));
    }

    public static NaSt callFightHelpBeanAbilityMultAccuracyAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityMultAccuracyAny());
    }

    public static NaSt callFightHelpBeanAbilityMultEvasiness(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityMultEvasiness(_args[0]));
    }

    public static NaSt callFightHelpBeanAbilityMultEvasinessAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityMultEvasinessAny());
    }

    public static NaSt callFightHelpBeanAbilityMultNormal(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityMultNormal(_args[0]));
    }

    public static NaSt callFightHelpBeanAbilityMultNormalAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityMultNormalAny());
    }

    public static NaSt callFightHelpBeanAbilityMultSpeed(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityMultSpeed(_args[0]));
    }

    public static NaSt callFightHelpBeanAbilityMultSpeedAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).abilityMultSpeedAny());
    }

    public static NaSt callFightHelpBeanAttackFirst(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).attackFirst());
    }

    public static NaSt callFightHelpBeanAttackLast(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).attackLast(_args[0]));
    }

    public static NaSt callFightHelpBeanAttackLastAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).attackLastAny());
    }

    public static NaSt callFightHelpBeanAutoDamageGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAutoDamage());
    }

    public static NaSt callFightHelpBeanBeginRoundStatusFoeGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getBeginRoundStatusFoe());
    }

    public static NaSt callFightHelpBeanBeginRoundStatusGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getBeginRoundStatus());
    }

    public static NaSt callFightHelpBeanBerryEndRoundGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getBerryEndRound());
    }

    public static NaSt callFightHelpBeanBerrySpeedGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getBerrySpeed());
    }

    public static NaSt callFightHelpBeanBerryTargetGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getBerryTarget());
    }

    public static NaSt callFightHelpBeanBerryUserGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getBerryUser());
    }

    public static NaSt callFightHelpBeanBoostsChGet(NaSt _str, int... _args) {
        return PokemonStandards.getLongRate(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getBoostsCh());
    }

    public static NaSt callFightHelpBeanBoostsGet(NaSt _str, int... _args) {
        return PokemonStandards.getLongRate(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getBoosts());
    }

    public static NaSt callFightHelpBeanCatchingFormulaGet(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getCatchingFormula());
    }

    public static NaSt callFightHelpBeanChangingTypesAbilitiesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getChangingTypesAbilities());
    }

    public static NaSt callFightHelpBeanClickAbilitiesAchieveTarget(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesAchieveTarget(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesAllyMultStat(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesAllyMultStat(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesBoostingStat(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesBoostingStat(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesBreakImmu(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesBreakImmu(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesBreakProtectMoves(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesBreakProtectMoves(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesBreakable(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesBreakable(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesChangeTypeMoves(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesChangeTypeMoves(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesChangingTypesDamage(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesChangingTypesDamage(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesCopyAb(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesCopyAb(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesDamageStatis(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesDamageStatis(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesEndRound(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesEndRound(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesFighterStatis(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesFighterStatis(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesFighterStatisVar(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesFighterStatisVar(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesFighterStatus(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesFighterStatus(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesGlobal(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesGlobal(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesImmu(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesImmu(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesImmuAllies(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesImmuAllies(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesImmuAlliesDam(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesImmuAlliesDam(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesImmuCh(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesImmuCh(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesImmuMultStat(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesImmuMultStat(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesImmuSecEffOther(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesImmuSecEffOther(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesImmuSecEffOwner(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesImmuSecEffOwner(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesImmuTypes(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesImmuTypes(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesKoTarget(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesKoTarget(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesMultEvtCh(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesMultEvtCh(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesMultRateCh(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesMultRateCh(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesMultStat(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesMultStat(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesPartStatis(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesPartStatis(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesPartStatus(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesPartStatus(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesPrio(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesPrio(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesRateStatis(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesRateStatis(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesRevAbs(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesRevAbs(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesSentBegin(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesSentBegin(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesSentBeginOth(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesSentBeginOth(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesSentStatis(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesSentStatis(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesStatisVarUser(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesStatisVarUser(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesStatus(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesStatus(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesSwitch(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesSwitch(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesTakingItem(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesTakingItem(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesTargetDamage(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesTargetDamage(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesTypeDefMoves(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesTypeDefMoves(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesUserDamage(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesUserDamage(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesUserIgnTargetTeam(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesUserIgnTargetTeam(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesUserPower(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesUserPower(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesUserStabDamage(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesUserStabDamage(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAbilitiesUserTargetDamage(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbilitiesUserTargetDamage(_args[0]));
    }

    public static NaSt callFightHelpBeanClickAutoDamage(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAutoDamage(_args[0]));
    }

    public static NaSt callFightHelpBeanClickBeginRoundStatus(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickBeginRoundStatus(_args[0]));
    }

    public static NaSt callFightHelpBeanClickBeginRoundStatusFoe(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickBeginRoundStatusFoe(_args[0]));
    }

    public static NaSt callFightHelpBeanClickBerryEndRound(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickBerryEndRound(_args[0]));
    }

    public static NaSt callFightHelpBeanClickBerrySpeed(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickBerrySpeed(_args[0]));
    }

    public static NaSt callFightHelpBeanClickBerryTarget(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickBerryTarget(_args[0]));
    }

    public static NaSt callFightHelpBeanClickBerryUser(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickBerryUser(_args[0]));
    }

    public static NaSt callFightHelpBeanClickChangingTypesAbilities(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickChangingTypesAbilities(_args[0]));
    }

    public static NaSt callFightHelpBeanClickComboEvtStat(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickComboEvtStat(_args[0]));
    }

    public static NaSt callFightHelpBeanClickComboMultStat(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickComboMultStat(_args[0]));
    }

    public static NaSt callFightHelpBeanClickCopyAbilities(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickCopyAbilities(_args[0]));
    }

    public static NaSt callFightHelpBeanClickCopyMoveTypesAb(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickCopyMoveTypesAb(_args[0]));
    }

    public static NaSt callFightHelpBeanClickDamagingMoves(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickDamagingMoves(_args[0]));
    }

    public static NaSt callFightHelpBeanClickDefaultMove(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickDefaultMove());
    }

    public static NaSt callFightHelpBeanClickDeleteStatusMove(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickDeleteStatusMove(_args[0]));
    }

    public static NaSt callFightHelpBeanClickDeletedStatusSwitch(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickDeletedStatusSwitch(_args[0]));
    }

    public static NaSt callFightHelpBeanClickEffMoves(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickEffMoves(_args[0]));
    }

    public static NaSt callFightHelpBeanClickEntryHazard(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickEntryHazard(_args[0]));
    }

    public static NaSt callFightHelpBeanClickGlobalMovesStatus(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickGlobalMovesStatus(_args[0]));
    }

    public static NaSt callFightHelpBeanClickImmuRecharging(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickImmuRecharging(_args[0]));
    }

    public static NaSt callFightHelpBeanClickImmuStatusAbility(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickImmuStatusAbility(_args[0]));
    }

    public static NaSt callFightHelpBeanClickItemSpeed(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickItemSpeed(_args[0]));
    }

    public static NaSt callFightHelpBeanClickItemsAbs(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickItemsAbs(_args[0]));
    }

    public static NaSt callFightHelpBeanClickItemsBoostingStat(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickItemsBoostingStat(_args[0]));
    }

    public static NaSt callFightHelpBeanClickItemsCancelImmu(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickItemsCancelImmu(_args[0]));
    }

    public static NaSt callFightHelpBeanClickItemsFighterStatis(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickItemsFighterStatis(_args[0]));
    }

    public static NaSt callFightHelpBeanClickItemsFighterStatus(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickItemsFighterStatus(_args[0]));
    }

    public static NaSt callFightHelpBeanClickItemsImmu(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickItemsImmu(_args[0]));
    }

    public static NaSt callFightHelpBeanClickItemsImmuTypes(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickItemsImmuTypes(_args[0]));
    }

    public static NaSt callFightHelpBeanClickItemsMultStat(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickItemsMultStat(_args[0]));
    }

    public static NaSt callFightHelpBeanClickItemsProtAgainstKo(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickItemsProtAgainstKo(_args[0]));
    }

    public static NaSt callFightHelpBeanClickItemsSentBegin(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickItemsSentBegin(_args[0]));
    }

    public static NaSt callFightHelpBeanClickItemsSentBeginOth(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickItemsSentBeginOth(_args[0]));
    }

    public static NaSt callFightHelpBeanClickItemsTargetDamage(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickItemsTargetDamage(_args[0]));
    }

    public static NaSt callFightHelpBeanClickItemsTypesDef(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickItemsTypesDef(_args[0]));
    }

    public static NaSt callFightHelpBeanClickItemsUserDamage(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickItemsUserDamage(_args[0]));
    }

    public static NaSt callFightHelpBeanClickItemsUserPower(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickItemsUserPower(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesAttracting(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesAttracting(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesBoostCh(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesBoostCh(_args[0]));
    }

    public static NaSt callFightHelpBeanClickChangeTypeMoves(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesChangeTypeMoves(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesCannotKo(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesCannotKo(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesChangingAttOrder(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesChangingAttOrder(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesFoeTeamMultStat(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesFoeTeamMultStat(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesGlobal(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesGlobal(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesGlobalAcc(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesGlobalAcc(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesGlobalBreakImmu(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesGlobalBreakImmu(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesGlobalBreakImmuAb(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesGlobalBreakImmuAb(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesGlobalMultStat(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesGlobalMultStat(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesGlobalPrepaDamage(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesGlobalPrepaDamage(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesHealingSubstitute(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesHealingSubstitute(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesIgnAcc(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesIgnAcc(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesIgnEva(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesIgnEva(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesIgnIncDef(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesIgnIncDef(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesIgnLowAtt(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesIgnLowAtt(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesInvokDamage(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesInvokDamage(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesInvoking(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesInvoking(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesKoTarget(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesKoTarget(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesMirror(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesMirror(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesProtAgainstKo(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesProtAgainstKo(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesProtecting(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesProtecting(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesProtectingTypes(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesProtectingTypes(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesSecEffItems(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesSecEffItems(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesTargetPower(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesTargetPower(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesTargetTeamDamage(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesTargetTeamDamage(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesTeam(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesTeam(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesTeamMultStat(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesTeamMultStat(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesThieving(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesThieving(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesTypeDefMoves(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesTypeDefMoves(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesTypesDefItem(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesTypesDefItem(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesTypesDefWeather(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesTypesDefWeather(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesUnprotectingTypes(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesUnprotectingTypes(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesUserAllyaDamage(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesUserAllyaDamage(_args[0]));
    }

    public static NaSt callFightHelpBeanClickMovesUserPower(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMovesUserPower(_args[0]));
    }

    public static NaSt callFightHelpBeanClickRechargeMoves(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickRechargeMoves(_args[0]));
    }

    public static NaSt callFightHelpBeanClickPrepaRoundMoves(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickPrepaRoundMoves(_args[0]));
    }

    public static NaSt callFightHelpBeanClickPressureAbilities(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickPressureAbilities(_args[0]));
    }

    public static NaSt callFightHelpBeanClickPrivatingMoves(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickPrivatingMoves(_args[0]));
    }

    public static NaSt callFightHelpBeanClickProtectAbilities(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickProtectAbilities(_args[0]));
    }

    public static NaSt callFightHelpBeanClickProtectItems(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickProtectItems(_args[0]));
    }

    public static NaSt callFightHelpBeanClickProtectMoves(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickProtectMoves(_args[0]));
    }

    public static NaSt callFightHelpBeanClickRecoilAbilities(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickRecoilAbilities(_args[0]));
    }

    public static NaSt callFightHelpBeanClickRecoilItems(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickRecoilItems(_args[0]));
    }

    public static NaSt callFightHelpBeanClickReverseSpeedMoves(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickReverseSpeedMoves(_args[0]));
    }

    public static NaSt callFightHelpBeanClickSlowAbilities(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickSlowAbilities(_args[0]));
    }

    public static NaSt callFightHelpBeanClickSlowItems(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickSlowItems(_args[0]));
    }

    public static NaSt callFightHelpBeanClickSpeedPreparingItems(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickSpeedPreparingItems(_args[0]));
    }

    public static NaSt callFightHelpBeanClickStatusDamage(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickStatusDamage(_args[0]));
    }

    public static NaSt callFightHelpBeanClickStatusMultStat(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickStatusMultStat(_args[0]));
    }

    public static NaSt callFightHelpBeanClickSubstitutingMoves(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickSubstitutingMoves(_args[0]));
    }

    public static NaSt callFightHelpBeanClickSuccessfulStatus(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickSuccessfulStatus(_args[0]));
    }

    public static NaSt callFightHelpBeanComboEvtStatGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrList(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getComboEvtStat());
    }

    public static NaSt callFightHelpBeanComboMultAccuracy(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).comboMultAccuracy(_args[0]));
    }

    public static NaSt callFightHelpBeanComboMultAccuracyAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).comboMultAccuracyAny());
    }

    public static NaSt callFightHelpBeanComboMultEvasiness(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).comboMultEvasiness(_args[0]));
    }

    public static NaSt callFightHelpBeanComboMultEvasinessAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).comboMultEvasinessAny());
    }

    public static NaSt callFightHelpBeanComboMultNormal(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).comboMultNormal(_args[0]));
    }

    public static NaSt callFightHelpBeanComboMultNormalAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).comboMultNormalAny());
    }

    public static NaSt callFightHelpBeanComboMultSpeed(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).comboMultSpeed(_args[0]));
    }

    public static NaSt callFightHelpBeanComboMultSpeedAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).comboMultSpeedAny());
    }

    public static NaSt callFightHelpBeanComboMultStatGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrList(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getComboMultStat());
    }

    public static NaSt callFightHelpBeanCopyAbilitiesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getCopyAbilities());
    }

    public static NaSt callFightHelpBeanCopyMoveTypesAbGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getCopyMoveTypesAb());
    }

    public static NaSt callFightHelpBeanDamagingMovesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getDamagingMoves());
    }

    public static NaSt callFightHelpBeanDamgeFormulaGet(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getDamgeFormula());
    }

    public static NaSt callFightHelpBeanDefaultBoostValueGet(NaSt _str, int... _args) {
        return new NaNbSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getDefaultBoostValue());
    }

    public static NaSt callFightHelpBeanDeleteStatusMoveGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getDeleteStatusMove());
    }

    public static NaSt callFightHelpBeanDeletedStatusSwitchGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getDeletedStatusSwitch());
    }

    public static NaSt callFightHelpBeanDisappearingRoundMovesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getDisappearingRoundMoves());
    }

    public static NaSt callFightHelpBeanEffMovesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getEffMoves());
    }

    public static NaSt callFightHelpBeanEfficiencyGet(NaSt _str, int... _args) {
        return PokemonStandards.getWeatherTypeRateMap(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getEfficiency());
    }

    public static NaSt callFightHelpBeanEntryHazardGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getEntryHazard());
    }

    public static NaSt callFightHelpBeanFleeingFormulaGet(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getFleeingFormula());
    }

    public static NaSt callFightHelpBeanGetAnimAbsorb(NaSt _str, int... _args) {
        return new NaImgSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAnimAbsorb());
    }

    public static NaSt callFightHelpBeanGetAnimStatistic(NaSt _str, int... _args) {
        return new NaImgSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getAnimStatistic(_args[0]));
    }

    public static NaSt callFightHelpBeanGetEfficiency(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getEfficiency(_args[0],_args[1]));
    }

    public static NaSt callFightHelpBeanGetFomula(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getFomula(_args[0]));
    }

    public static NaSt callFightHelpBeanGetStab(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getStab());
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesAchieveTarget(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesAchieveTarget(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesAllyMultStat(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesAllyMultStat(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesBoostingStat(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesBoostingStat(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesBreakImmu(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesBreakImmu(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesBreakProtectMoves(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesBreakProtectMoves(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesBreakable(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesBreakable(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesChangeTypeMoves(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesChangeTypeMoves(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesChangingTypesDamage(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesChangingTypesDamage(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesCopyAb(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesCopyAb(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesDamageStatis(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesDamageStatis(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesEndRound(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesEndRound(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesFighterStatis(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesFighterStatis(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesFighterStatisVar(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesFighterStatisVar(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesFighterStatus(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesFighterStatus(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesGlobal(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesGlobal(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesImmu(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesImmu(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesImmuAllies(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesImmuAllies(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesImmuAlliesDam(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesImmuAlliesDam(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesImmuCh(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesImmuCh(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesImmuMultStat(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesImmuMultStat(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesImmuSecEffOther(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesImmuSecEffOther(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesImmuSecEffOwner(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesImmuSecEffOwner(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesImmuTypes(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesImmuTypes(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesKoTarget(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesKoTarget(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesMultEvtCh(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesMultEvtCh(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesMultRateCh(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesMultRateCh(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesMultStat(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesMultStat(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesPartStatis(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesPartStatis(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesPartStatus(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesPartStatus(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesPrio(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesPrio(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesRateStatis(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesRateStatis(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesRevAbs(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesRevAbs(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesSentBegin(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesSentBegin(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesSentBeginOth(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesSentBeginOth(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesSentStatis(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesSentStatis(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesStatisVarUser(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesStatisVarUser(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesStatus(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesStatus(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesSwitch(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesSwitch(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesTakingItem(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesTakingItem(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesTargetDamage(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesTargetDamage(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesTypeDefMoves(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesTypeDefMoves(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesUserDamage(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesUserDamage(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesUserIgnTargetTeam(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesUserIgnTargetTeam(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesUserPower(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesUserPower(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesUserStabDamage(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesUserStabDamage(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAbilitiesUserTargetDamage(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAbilitiesUserTargetDamage(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrAutoDamage(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrAutoDamage(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrBeginRoundStatus(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrBeginRoundStatus(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrBeginRoundStatusFoe(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrBeginRoundStatusFoe(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrBerryEndRound(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrBerryEndRound(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrBerrySpeed(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrBerrySpeed(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrBerryTarget(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrBerryTarget(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrBerryUser(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrBerryUser(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrChangingTypesAbilities(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrChangingTypesAbilities(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrComboEvtStat(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrComboEvtStat(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrComboMultStat(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrComboMultStat(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrCopyAbilities(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrCopyAbilities(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrCopyMoveTypesAb(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrCopyMoveTypesAb(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrDamagingMoves(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrDamagingMoves(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrDefaultMove(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrDefaultMove());
    }

    public static NaSt callFightHelpBeanGetTrDeleteStatusMove(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrDeleteStatusMove(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrDeletedStatusSwitch(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrDeletedStatusSwitch(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrDifficulty(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrDifficulty(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrEffMoves(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrEffMoves(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrEntryHazard(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrEntryHazard(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrGlobalMovesStatus(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrGlobalMovesStatus(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrImmuRecharging(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrImmuRecharging(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrImmuStatusAbility(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrImmuStatusAbility(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrItemSpeed(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrItemSpeed(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrItemsAbs(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrItemsAbs(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrItemsBoostingStat(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrItemsBoostingStat(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrItemsCancelImmu(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrItemsCancelImmu(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrItemsFighterStatis(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrItemsFighterStatis(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrItemsFighterStatus(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrItemsFighterStatus(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrItemsImmu(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrItemsImmu(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrItemsImmuTypes(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrItemsImmuTypes(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrItemsMultStat(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrItemsMultStat(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrItemsProtAgainstKo(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrItemsProtAgainstKo(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrItemsSentBegin(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrItemsSentBegin(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrItemsSentBeginOth(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrItemsSentBeginOth(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrItemsTargetDamage(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrItemsTargetDamage(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrItemsTypesDef(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrItemsTypesDef(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrItemsUserDamage(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrItemsUserDamage(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrItemsUserPower(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrItemsUserPower(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrLawRate(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrLawRate(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesAttracting(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesAttracting(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesBoostCh(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesBoostCh(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesCannotKo(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesCannotKo(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesChangeTypeMoves(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesChangeTypeMoves(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesChangingAttOrder(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesChangingAttOrder(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesFoeTeamMultStat(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesFoeTeamMultStat(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesGlobal(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesGlobal(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesGlobalAcc(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesGlobalAcc(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesGlobalBreakImmu(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesGlobalBreakImmu(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesGlobalBreakImmuAb(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesGlobalBreakImmuAb(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesGlobalMultStat(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesGlobalMultStat(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesGlobalPrepaDamage(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesGlobalPrepaDamage(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesHealingSubstitute(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesHealingSubstitute(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesIgnAcc(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesIgnAcc(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesIgnEva(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesIgnEva(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesIgnIncDef(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesIgnIncDef(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesIgnLowAtt(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesIgnLowAtt(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesInvokDamage(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesInvokDamage(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesInvoking(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesInvoking(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesKoTarget(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesKoTarget(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesMirror(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesMirror(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesProtAgainstKo(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesProtAgainstKo(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesProtecting(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesProtecting(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesProtectingTypes(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesProtectingTypes(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesSecEffItems(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesSecEffItems(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesTargetPower(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesTargetPower(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesTargetTeamDamage(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesTargetTeamDamage(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesTeam(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesTeam(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesTeamMultStat(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesTeamMultStat(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesThieving(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesThieving(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesTypeDefMoves(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesTypeDefMoves(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesTypesDefItem(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesTypesDefItem(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesTypesDefWeather(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesTypesDefWeather(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesUnprotectingTypes(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesUnprotectingTypes(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesUserAllyDamage(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesUserAllyDamage(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrMovesUserPower(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrMovesUserPower(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrPrepaRoundMoves(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrPrepaRoundMoves(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrPressureAbilities(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrPressureAbilities(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrPrivatingMoves(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrPrivatingMoves(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrProtectAbilities(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrProtectAbilities(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrProtectItems(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrProtectItems(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrProtectMoves(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrProtectMoves(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrRechargeMoves(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrRechargeMoves(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrRecoilAbilities(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrRecoilAbilities(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrRecoilItems(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrRecoilItems(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrReverseSpeedMoves(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrReverseSpeedMoves(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrSlowAbilities(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrSlowAbilities(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrSlowItems(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrSlowItems(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrSpeedPreparingItems(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrSpeedPreparingItems(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrStatistic(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrStatistic(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrStatusDamage(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrStatusDamage(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrStatusMultStat(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrStatusMultStat(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrSubstitutingMoves(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrSubstitutingMoves(_args[0]));
    }

    public static NaSt callFightHelpBeanGetTrSuccessfulStatus(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTrSuccessfulStatus(_args[0]));
    }

    public static NaSt callFightHelpBeanGlobalMovesStatusGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getGlobalMovesStatus());
    }

    public static NaSt callFightHelpBeanHappinessPointsGet(NaSt _str, int... _args) {
        return new NaNbSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getHappinessPoints());
    }

    public static NaSt callFightHelpBeanHasLawForAttack(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).hasLawForAttack(_args[0]));
    }

    public static NaSt callFightHelpBeanHasLawForAttackAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).hasLawForAttackAny());
    }

    public static NaSt callFightHelpBeanHasLawForHeal(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).hasLawForHeal(_args[0]));
    }

    public static NaSt callFightHelpBeanHasLawForHealAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).hasLawForHealAny());
    }

    public static NaSt callFightHelpBeanImmuChTeamMove(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).immuChTeamMove(_args[0]));
    }

    public static NaSt callFightHelpBeanImmuChTeamMoveAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).immuChTeamMoveAny());
    }

    public static NaSt callFightHelpBeanImmuRechargingGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getImmuRecharging());
    }

    public static NaSt callFightHelpBeanImmuStatisTeamMove(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).immuStatisTeamMove(_args[0]));
    }

    public static NaSt callFightHelpBeanImmuStatisTeamMoveAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).immuStatisTeamMoveAny());
    }

    public static NaSt callFightHelpBeanImmuStatusAbilityGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getImmuStatusAbility());
    }

    public static NaSt callFightHelpBeanImmuStatusTeamMove(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).immuStatusTeamMove(_args[0]));
    }

    public static NaSt callFightHelpBeanImmuStatusTeamMoveAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).immuStatusTeamMoveAny());
    }

    public static NaSt callFightHelpBeanIsDisappearingUser(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).isDisappearingUser(_args[0]));
    }

    public static NaSt callFightHelpBeanItemBoostAccuracy(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).itemBoostAccuracy(_args[0]));
    }

    public static NaSt callFightHelpBeanItemBoostAccuracyAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).itemBoostAccuracyAny());
    }

    public static NaSt callFightHelpBeanItemBoostCh(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).itemBoostCh(_args[0]));
    }

    public static NaSt callFightHelpBeanItemBoostChAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).itemBoostChAny());
    }

    public static NaSt callFightHelpBeanItemBoostEvasiness(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).itemBoostEvasiness(_args[0]));
    }

    public static NaSt callFightHelpBeanItemBoostEvasinessAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).itemBoostEvasinessAny());
    }

    public static NaSt callFightHelpBeanItemBoostNormal(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).itemBoostNormal(_args[0]));
    }

    public static NaSt callFightHelpBeanItemBoostNormalAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).itemBoostNormalAny());
    }

    public static NaSt callFightHelpBeanItemBoostSpeed(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).itemBoostSpeed(_args[0]));
    }

    public static NaSt callFightHelpBeanItemBoostSpeedAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).itemBoostSpeedAny());
    }

//    public static NaSt callFightHelpBeanItemBoostSpeed(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new FightHelpBeanItemBoostSpeed(),_str,_args);
//    }
//
//    public static NaSt callFightHelpBeanItemBoostSpeedAny(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new FightHelpBeanItemBoostSpeedAny(),_str,_args);
//    }

    public static NaSt callFightHelpBeanItemMultAccuracy(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).itemMult(_args[0], Statistic.ACCURACY));
    }

    public static NaSt callFightHelpBeanItemMultEvasiness(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).itemMult(_args[0], Statistic.EVASINESS));
    }

    public static NaSt callFightHelpBeanItemMultAccuracyAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).itemMultAccuracyAny());
    }

    public static NaSt callFightHelpBeanItemMultEvasinessAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).itemMultEvasinessAny());
    }

    public static NaSt callFightHelpBeanItemMultNormal(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).itemMultNormal(_args[0]));
    }

    public static NaSt callFightHelpBeanItemMultNormalAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).itemMultNormalAny());
    }

    public static NaSt callFightHelpBeanItemMultSpeed(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).itemMult(_args[0], Statistic.SPEED));
    }
//
//    public static NaSt callFightHelpBeanItemMultSpeedAny(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new FightHelpBeanItemMultSpeedAny(),_str,_args);
//    }

    public static NaSt callFightHelpBeanItemMultSpeedAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).itemMultSpeedAny());
    }

    public static NaSt callFightHelpBeanItemSpeedGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getItemSpeed());
    }

    public static NaSt callFightHelpBeanItemsAbsGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getItemsAbs());
    }

    public static NaSt callFightHelpBeanItemsBoostingStatGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getItemsBoostingStat());
    }

    public static NaSt callFightHelpBeanItemsCancelImmuGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getItemsCancelImmu());
    }

    public static NaSt callFightHelpBeanItemsFighterStatisGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getItemsFighterStatis());
    }

    public static NaSt callFightHelpBeanItemsFighterStatusGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getItemsFighterStatus());
    }

    public static NaSt callFightHelpBeanItemsImmuGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getItemsImmu());
    }

    public static NaSt callFightHelpBeanItemsImmuTypesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getItemsImmuTypes());
    }

    public static NaSt callFightHelpBeanItemsMultStatGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getItemsMultStat());
    }

    public static NaSt callFightHelpBeanItemsProtAgainstKoGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getItemsProtAgainstKo());
    }

    public static NaSt callFightHelpBeanItemsSentBeginOtherGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getItemsSentBeginOther());
    }

    public static NaSt callFightHelpBeanItemsSentBeginWeatherGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getItemsSentBeginWeather());
    }

    public static NaSt callFightHelpBeanItemsTargetDamageGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getItemsTargetDamage());
    }

    public static NaSt callFightHelpBeanItemsTypesDefGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getItemsTypesDef());
    }

    public static NaSt callFightHelpBeanItemsUserDamageGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getItemsUserDamage());
    }

    public static NaSt callFightHelpBeanItemsUserPowerGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getItemsUserPower());
    }

    public static NaSt callFightHelpBeanLawsRatesGet(NaSt _str, int... _args) {
        return PokemonStandards.getBigNatMap(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getLawsRates());
    }

    public static NaSt callFightHelpBeanMapAutoDamageGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrStr(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMapAutoDamage());
    }

    public static NaSt callFightHelpBeanMapVarGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrStr(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMapVar());
    }

    public static NaSt callFightHelpBeanMinHpNotKoGet(NaSt _str, int... _args) {
        return new RtSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMinHpNotKo());
    }

    public static NaSt callFightHelpBeanMoveFoeTeamMultAccuracy(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).moveFoeTeamMultAccuracy(_args[0]));
    }

    public static NaSt callFightHelpBeanMoveFoeTeamMultAccuracyAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).moveFoeTeamMultAccuracyAny());
    }

    public static NaSt callFightHelpBeanMoveFoeTeamMultEvasiness(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).moveFoeTeamMultEvasiness(_args[0]));
    }

    public static NaSt callFightHelpBeanMoveFoeTeamMultEvasinessAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).moveFoeTeamMultEvasinessAny());
    }

    public static NaSt callFightHelpBeanMoveFoeTeamMultNormal(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).moveFoeTeamMultNormal(_args[0]));
    }

    public static NaSt callFightHelpBeanMoveFoeTeamMultNormalAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).moveFoeTeamMultNormalAny());
    }

    public static NaSt callFightHelpBeanMoveFoeTeamMultSpeed(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).moveFoeTeamMultSpeed(_args[0]));
    }

    public static NaSt callFightHelpBeanMoveFoeTeamMultSpeedAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).moveFoeTeamMultSpeedAny());
    }

    public static NaSt callFightHelpBeanMoveGlobalMultAccuracy(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).moveGlobalMultAccuracy(_args[0]));
    }

    public static NaSt callFightHelpBeanMoveGlobalMultAccuracyAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).moveGlobalMultAccuracyAny());
    }

    public static NaSt callFightHelpBeanMoveGlobalMultEvasiness(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).moveGlobalMultEvasiness(_args[0]));
    }

    public static NaSt callFightHelpBeanMoveGlobalMultEvasinessAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).moveGlobalMultEvasinessAny());
    }

    public static NaSt callFightHelpBeanMoveGlobalMultNormal(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).moveGlobalMultNormal(_args[0]));
    }

    public static NaSt callFightHelpBeanMoveGlobalMultNormalAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).moveGlobalMultNormalAny());
    }

    public static NaSt callFightHelpBeanMoveGlobalMultSpeed(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).moveGlobalMultSpeed(_args[0]));
    }

    public static NaSt callFightHelpBeanMoveGlobalMultSpeedAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).moveGlobalMultSpeedAny());
    }

    public static NaSt callFightHelpBeanMoveTeamMultAccuracy(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).moveTeamMultAccuracy(_args[0]));
    }

    public static NaSt callFightHelpBeanMoveTeamMultAccuracyAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).moveTeamMultAccuracyAny());
    }

    public static NaSt callFightHelpBeanMoveTeamMultEvasiness(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).moveTeamMultEvasiness(_args[0]));
    }

    public static NaSt callFightHelpBeanMoveTeamMultEvasinessAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).moveTeamMultEvasinessAny());
    }

    public static NaSt callFightHelpBeanMoveTeamMultNormal(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).moveTeamMultNormal(_args[0]));
    }

    public static NaSt callFightHelpBeanMoveTeamMultNormalAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).moveTeamMultNormalAny());
    }

    public static NaSt callFightHelpBeanMoveTeamMultSpeed(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).moveTeamMultSpeed(_args[0]));
    }

    public static NaSt callFightHelpBeanMoveTeamMultSpeedAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).moveTeamMultSpeedAny());
    }

    public static NaSt callFightHelpBeanMovesAttractingGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesAttracting());
    }

    public static NaSt callFightHelpBeanMovesBoostChGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesBoostCh());
    }

    public static NaSt callFightHelpBeanMovesCannotKoGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesCannotKo());
    }

    public static NaSt callFightHelpBeanMovesChangeTypeMovesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesChangeTypeMoves());
    }

    public static NaSt callFightHelpBeanMovesChangingAttOrderGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesChangingAttOrder());
    }

    public static NaSt callFightHelpBeanMovesFoeTeamMultStatGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesFoeTeamMultStat());
    }

    public static NaSt callFightHelpBeanMovesGlobalAccGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesGlobalAcc());
    }

    public static NaSt callFightHelpBeanMovesGlobalBreakImmuAbGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesGlobalBreakImmuAb());
    }

    public static NaSt callFightHelpBeanMovesGlobalBreakImmuGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesGlobalBreakImmu());
    }

    public static NaSt callFightHelpBeanMovesGlobalGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesGlobal());
    }

    public static NaSt callFightHelpBeanMovesGlobalMultStatGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesGlobalMultStat());
    }

    public static NaSt callFightHelpBeanMovesGlobalPrepaDamageGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesGlobalPrepaDamage());
    }

    public static NaSt callFightHelpBeanMovesHealingSubstituteGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesHealingSubstitute());
    }

    public static NaSt callFightHelpBeanMovesIgnAccGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesIgnAcc());
    }

    public static NaSt callFightHelpBeanMovesIgnEvaGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesIgnEva());
    }

    public static NaSt callFightHelpBeanMovesIgnIncDefGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesIgnIncDef());
    }

    public static NaSt callFightHelpBeanMovesIgnLowAttGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesIgnLowAtt());
    }

    public static NaSt callFightHelpBeanMovesInvokDamageGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesInvokDamage());
    }

    public static NaSt callFightHelpBeanMovesInvokingGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesInvoking());
    }

    public static NaSt callFightHelpBeanMovesKoTargetGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesKoTarget());
    }

    public static NaSt callFightHelpBeanMovesMirrorGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesMirror());
    }

    public static NaSt callFightHelpBeanMovesProtAgainstKoGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesProtAgainstKo());
    }

    public static NaSt callFightHelpBeanMovesProtectingGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesProtecting());
    }

    public static NaSt callFightHelpBeanMovesProtectingTypesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesProtectingTypes());
    }

    public static NaSt callFightHelpBeanMovesSecEffItemsGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesSecEffItems());
    }

    public static NaSt callFightHelpBeanMovesTargetPowerGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesTargetPower());
    }

    public static NaSt callFightHelpBeanMovesTargetTeamDamageGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesTargetTeamDamage());
    }

    public static NaSt callFightHelpBeanMovesTeamGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesTeam());
    }

    public static NaSt callFightHelpBeanMovesTeamMultStatGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesTeamMultStat());
    }

    public static NaSt callFightHelpBeanMovesThievingGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesThieving());
    }

    public static NaSt callFightHelpBeanMovesTypeDefMovesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesTypeDefMoves());
    }

    public static NaSt callFightHelpBeanMovesTypesDefItemGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesTypesDefItem());
    }

    public static NaSt callFightHelpBeanMovesTypesDefWeatherGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesTypesDefWeather());
    }

    public static NaSt callFightHelpBeanMovesUnprotectingTypesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesUnprotectingTypes());
    }

    public static NaSt callFightHelpBeanMovesUserAllyDamageGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesUserAllyDamage());
    }

    public static NaSt callFightHelpBeanMovesUserPowerGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getMovesUserPower());
    }

    public static NaSt callFightHelpBeanNextRowAfter(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).nextRowAfter(_args[0]));
    }

    public static NaSt callFightHelpBeanPrepaRoundMovesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getPrepaRoundMoves());
    }

    public static NaSt callFightHelpBeanPressureAbilitiesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getPressureAbilities());
    }

    public static NaSt callFightHelpBeanPrivatingMovesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getPrivatingMoves());
    }

    public static NaSt callFightHelpBeanProtectAbilitiesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getProtectAbilities());
    }

    public static NaSt callFightHelpBeanProtectItemsGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getProtectItems());
    }

    public static NaSt callFightHelpBeanProtectMovesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getProtectMoves());
    }

    public static NaSt callFightHelpBeanRateFormulaChGet(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getRateFormulaCh());
    }

    public static NaSt callFightHelpBeanRateFormulaGet(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getRateFormula());
    }

    public static NaSt callFightHelpBeanBoostVarGet(NaSt _str, int... _args) {
        return new NaStSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getBoostVar());
    }
    public static NaSt callFightHelpBeanRatesGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrStr(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getRates());
    }

    public static NaSt callFightHelpBeanRechargeMovesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getRechargeMoves());
    }

    public static NaSt callFightHelpBeanRecoilAbilitiesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getRecoilAbilities());
    }

    public static NaSt callFightHelpBeanRecoilItemsGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getRecoilItems());
    }

    public static NaSt callFightHelpBeanReverseSpeedMovesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getReverseSpeedMoves());
    }

    public static NaSt callFightHelpBeanSlowAbilitiesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getSlowAbilities());
    }

    public static NaSt callFightHelpBeanSlowItemsGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getSlowItems());
    }

    public static NaSt callFightHelpBeanSpeedPreparingItemsGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getSpeedPreparingItems());
    }

    public static NaSt callFightHelpBeanStatisticAnimGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getStatisticAnim());
    }

    public static NaSt callFightHelpBeanStatusDamageGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getStatusDamage());
    }

    public static NaSt callFightHelpBeanStatusMultAccuracy(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).statusMultAccuracy(_args[0]));
    }

    public static NaSt callFightHelpBeanStatusMultAccuracyAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).statusMultAccuracyAny());
    }

    public static NaSt callFightHelpBeanStatusMultEvasiness(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).statusMultEvasiness(_args[0]));
    }

    public static NaSt callFightHelpBeanStatusMultEvasinessAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).statusMultEvasinessAny());
    }

    public static NaSt callFightHelpBeanStatusMultNormal(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).statusMultNormal(_args[0]));
    }

    public static NaSt callFightHelpBeanStatusMultNormalAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).statusMultNormalAny());
    }

    public static NaSt callFightHelpBeanStatusMultSpeed(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).statusMultSpeed(_args[0]));
    }

    public static NaSt callFightHelpBeanStatusMultSpeedAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).statusMultSpeedAny());
    }

    public static NaSt callFightHelpBeanStatusMultStatGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getStatusMultStat());
    }

    public static NaSt callFightHelpBeanStrongMoveGet(NaSt _str, int... _args) {
        return new RtSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getStrongMove());
    }

    public static NaSt callFightHelpBeanSubstitutingMovesGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getSubstitutingMoves());
    }

    public static NaSt callFightHelpBeanSuccessfulStatusGet(NaSt _str, int... _args) {
        return PokemonStandards.getKeys(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getSuccessfulStatus());
    }

    public static NaSt callFightHelpBeanTypesGet(NaSt _str, int... _args) {
        return PokemonStandards.getValues(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getTypes());
    }

    public static NaSt callFightHelpBeanVarCatchingFormulaGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrStr(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getVarCatchingFormula());
    }

    public static NaSt callFightHelpBeanVarFleeingFormulaGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrStr(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getVarFleeingFormula());
    }

    public static NaSt callFightHelpBeanVarRatesGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrStr(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getVarRates());
    }

    public static NaSt callFightHelpBeanWithConstDamage(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).withConstDamage(_args[0]));
    }

    public static NaSt callFightHelpBeanWithConstDamageAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).withConstDamageAny());
    }

    public static NaSt callFightHelpBeanWithMultDamage(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).withMultDamage(_args[0]));
    }

    public static NaSt callFightHelpBeanWithMultDamageAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).withMultDamageAny());
    }

    public static NaSt callFightHelpBeanWithRandDamage(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).withRandDamage(_args[0]));
    }

    public static NaSt callFightHelpBeanWithRandDamageAny(NaSt _str, int... _args) {
        return NaBoSt.of(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).withRandDamageAny());
    }

    public static NaSt callFightHelpBeanWonHappinessPointsLevelGet(NaSt _str, int... _args) {
        return new RtSt(( (FightHelpBean) ((PokemonBeanStruct)_str).getInstance()).getWonHappinessPointsLevel());
    }
    protected static NaSt bean(FacadeGame _f) {
        PkData pk_ = pkDataByFacade(_f);
        FightHelpBean help_ = new FightHelpBean();
        help_.setBuilder(builder(_f));
        help_.getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.ROUND,new TranslationsFile());
        help_.getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.ROUND,new TranslationsFile());

        pk_.bean(help_, EN);
        beforeDisplaying(help_);
        return new PokemonBeanStruct(help_);
    }
}

