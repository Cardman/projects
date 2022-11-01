package aiki.beans.simulation;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.db.InitDbConstr;
import aiki.beans.facade.simulation.dto.*;
import aiki.beans.game.DifficultyBeanComGet;
import code.expressionlanguage.structs.Struct;
import code.maths.Rate;

public abstract class InitDbSimulation extends InitDbConstr {

    public static Struct callSimulationBeanAbilitiesAfterFightGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanAbilitiesAfterFightGet(),_str,_args);
    }

    public static Struct callSimulationBeanAbilitiesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanAbilitiesGet(),_str,_args);
    }

    public static Struct callSimulationBeanAbilityAfterFightGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanAbilityAfterFightGet(),_str,_args);
    }

    public static Struct callSimulationBeanAdd(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanAdd(),_str,_args);
    }

    public static Struct callSimulationBeanAddPkTrainer(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanAddPkTrainer(),_str,_args);
    }
//
//    public static Struct callSimulationBeanAllowCatchingKoGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanAllowCatchingKoGet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanAllowedSwitchPlacesEndRoundGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanAllowedSwitchPlacesEndRoundGet(),_str,_args);
//    }

    public static Struct callSimulationBeanAllyChoiceGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanAllyChoiceGet(),_str,_args);
    }

    public static Struct callSimulationBeanAllyTeamGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanAllyTeamGet(),_str,_args);
    }

    public static Struct callSimulationBeanAvailableEvosGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanAvailableEvosGet(),_str,_args);
    }

    public static Struct callSimulationBeanCancelDiffChoice(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanCancelDiffChoice(),_str,_args);
    }

    public static Struct callSimulationBeanCancelEvo(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanCancelEvo(),_str,_args);
    }

    public static Struct callSimulationBeanCancelEvolutions(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanCancelEvolutions(),_str,_args);
    }

    public static Struct callSimulationBeanCancelEvolutionsAfterFight(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanCancelEvolutionsAfterFight(),_str,_args);
    }

    public static Struct callSimulationBeanCancelFrontFighters(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanCancelFrontFighters(),_str,_args);
    }

    public static Struct callSimulationBeanCancelMoves(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanCancelMoves(),_str,_args);
    }

    public static Struct callSimulationBeanCancelMovesEvos(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanCancelMovesEvos(),_str,_args);
    }

    public static Struct callSimulationBeanCancelMovesSets(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanCancelMovesSets(),_str,_args);
    }

    public static Struct callSimulationBeanCancelTeam(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanCancelTeam(),_str,_args);
    }

    public static Struct callSimulationBeanChangeFight(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanChangeFight(),_str,_args);
    }

    public static Struct callSimulationBeanChangeFightWhileEnd(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanChangeFightWhileEnd(),_str,_args);
    }

    public static Struct callSimulationBeanChosenEvoGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanChosenEvoGet(),_str,_args);
    }

    public static Struct callSimulationBeanClickLevel(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanClickLevel(),_str,_args);
    }

    public static Struct callSimulationBeanClickLevelZero(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanClickLevelZero(),_str,_args);
    }

    public static Struct callSimulationBeanCommentsGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanCommentsGet(),_str,_args);
    }

    public static Struct callSimulationBeanCurrentAbilityGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanCurrentAbilityGet(),_str,_args);
    }

//    public static Struct callSimulationBeanDamageRateFoeTableGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanDamageRateFoeTableGet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanDamageRateLawFoeGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanDamageRateLawFoeGet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanDamageRatePlayerGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanDamageRatePlayerGet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanDamageRatePlayerTableGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanDamageRatePlayerTableGet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanDamageRatesGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanDamageRatesGet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanDiffWinningExpPtsFightGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanDiffWinningExpPtsFightGet(),_str,_args);
//    }

    public static Struct callSimulationBeanDisplayComments(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanDisplayComments(),_str,_args);
    }

    public static Struct callSimulationBeanDisplayEvolutions(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanDisplayEvolutions(),_str,_args);
    }

    public static Struct callSimulationBeanDisplayIfErrorGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanDisplayIfErrorGet(),_str,_args);
    }

//    public static Struct callSimulationBeanEnabledClosingGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanEnabledClosingGet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanEndFightIfOneTeamKoGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanEndFightIfOneTeamKoGet(),_str,_args);
//    }

    public static Struct callSimulationBeanEnvironmentGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanEnvironmentGet(),_str,_args);
    }

    public static Struct callSimulationBeanEnvironmentsGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanEnvironmentsGet(),_str,_args);
    }

    public static Struct callSimulationBeanEvolutionAfterFightGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanEvolutionAfterFightGet(),_str,_args);
    }

    public static Struct callSimulationBeanEvolutionsAfterFightGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanEvolutionsAfterFightGet(),_str,_args);
    }

    public static Struct callSimulationBeanFoeTeamGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanFoeTeamGet(),_str,_args);
    }

    public static Struct callSimulationBeanFreeTeamsGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanFreeTeamsGet(),_str,_args);
    }

    public static Struct callSimulationBeanGetAbility(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetAbility(),_str,_args);
    }

    public static Struct callSimulationBeanGetAbilityAfterFight(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetAbilityAfterFight(),_str,_args);
    }

    public static Struct callSimulationBeanGetAbilityAlly(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetAbilityAlly(),_str,_args);
    }

    public static Struct callSimulationBeanGetAbilityFoe(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetAbilityFoe(),_str,_args);
    }

    public static Struct callSimulationBeanGetGender(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetGender(),_str,_args);
    }

    public static Struct callSimulationBeanGetGenderAfterFight(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetGenderAfterFight(),_str,_args);
    }

    public static Struct callSimulationBeanGetGenderAlly(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetGenderAlly(),_str,_args);
    }

    public static Struct callSimulationBeanGetGenderFoe(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetGenderFoe(),_str,_args);
    }

    public static Struct callSimulationBeanGetImage(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetImage(),_str,_args);
    }

    public static Struct callSimulationBeanGetImageAfterFight(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetImageAfterFight(),_str,_args);
    }

    public static Struct callSimulationBeanGetImageAlly(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetImageAlly(),_str,_args);
    }

    public static Struct callSimulationBeanGetImageFoe(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetImageFoe(),_str,_args);
    }

    public static Struct callSimulationBeanGetItem(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetItem(),_str,_args);
    }

    public static Struct callSimulationBeanGetItemAfterFight(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetItemAfterFight(),_str,_args);
    }

    public static Struct callSimulationBeanGetItemAlly(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetItemAlly(),_str,_args);
    }

    public static Struct callSimulationBeanGetItemFoe(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetItemFoe(),_str,_args);
    }

    public static Struct callSimulationBeanGetKoFoes(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetKoFoes(),_str,_args);
    }

    public static Struct callSimulationBeanGetKoPlayers(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetKoPlayers(),_str,_args);
    }

    public static Struct callSimulationBeanGetLevel(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetLevel(),_str,_args);
    }

    public static Struct callSimulationBeanGetLevelAfterFight(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetLevelAfterFight(),_str,_args);
    }

    public static Struct callSimulationBeanGetLevelAlly(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetLevelAlly(),_str,_args);
    }

    public static Struct callSimulationBeanGetLevelFoe(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetLevelFoe(),_str,_args);
    }

    public static Struct callSimulationBeanGetMoves(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetMoves(),_str,_args);
    }

    public static Struct callSimulationBeanGetMovesAfterFight(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetMovesAfterFight(),_str,_args);
    }

    public static Struct callSimulationBeanGetMovesAlly(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetMovesAlly(),_str,_args);
    }

    public static Struct callSimulationBeanGetMovesFoe(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetMovesFoe(),_str,_args);
    }

    public static Struct callSimulationBeanGetName(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetName(),_str,_args);
    }

    public static Struct callSimulationBeanGetNameAfterFight(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetNameAfterFight(),_str,_args);
    }

    public static Struct callSimulationBeanGetNameAlly(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetNameAlly(),_str,_args);
    }

    public static Struct callSimulationBeanGetNameFoe(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetNameFoe(),_str,_args);
    }

    public static Struct callSimulationBeanGetNotKoFrontFoes(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetNotKoFrontFoes(),_str,_args);
    }

    public static Struct callSimulationBeanGetRealStepNumber(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetRealStepNumber(),_str,_args);
    }

    public static Struct callSimulationBeanGetRemainingLifeRate(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetRemainingLifeRate(),_str,_args);
    }

    public static Struct callSimulationBeanGetTrainerName(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetTrainerName(),_str,_args);
    }

    public static Struct callSimulationBeanHideComments(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanHideComments(),_str,_args);
    }

    public static Struct callSimulationBeanIndexTeamGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIndexTeamGet(),_str,_args);
    }

    public static Struct callSimulationBeanIsAvailableAbilities(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsAvailableAbilities(),_str,_args);
    }

    public static Struct callSimulationBeanIsAvailableMoves(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsAvailableMoves(),_str,_args);
    }

    public static Struct callSimulationBeanIsDiffState(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsDiffState(),_str,_args);
    }

    public static Struct callSimulationBeanIsEvolutionAfterFightState(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsEvolutionAfterFightState(),_str,_args);
    }

    public static Struct callSimulationBeanIsEvolutionsState(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsEvolutionsState(),_str,_args);
    }

    public static Struct callSimulationBeanIsFightAfter(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsFightAfter(),_str,_args);
    }

    public static Struct callSimulationBeanIsFoeState(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsFoeState(),_str,_args);
    }

    public static Struct callSimulationBeanIsFrontState(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsFrontState(),_str,_args);
    }

    public static Struct callSimulationBeanIsHardSimulationIssue(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsHardSimulationIssue(),_str,_args);
    }

    public static Struct callSimulationBeanIsIssue(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsIssue(),_str,_args);
    }

    public static Struct callSimulationBeanIsIssueAfterFight(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsIssueAfterFight(),_str,_args);
    }

    public static Struct callSimulationBeanIsMovesFightState(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsMovesFightState(),_str,_args);
    }

    public static Struct callSimulationBeanIsMovesState(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsMovesState(),_str,_args);
    }

    public static Struct callSimulationBeanIsMultiLayer(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsMultiLayer(),_str,_args);
    }

    public static Struct callSimulationBeanIsRandomIssue(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsRandomIssue(),_str,_args);
    }

    public static Struct callSimulationBeanIsRulesIssue(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsRulesIssue(),_str,_args);
    }

    public static Struct callSimulationBeanIsRulesLearnIssue(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsRulesLearnIssue(),_str,_args);
    }

    public static Struct callSimulationBeanIsRulesMovesIssue(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsRulesMovesIssue(),_str,_args);
    }

    public static Struct callSimulationBeanIsRulesSwitchIssue(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsRulesSwitchIssue(),_str,_args);
    }

    public static Struct callSimulationBeanIsSendingIssue(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsSendingIssue(),_str,_args);
    }

    public static Struct callSimulationBeanIsSimulationState(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsSimulationState(),_str,_args);
    }

    public static Struct callSimulationBeanIsTeamState(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsTeamState(),_str,_args);
    }

    public static Struct callSimulationBeanIsUsingIssue(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsUsingIssue(),_str,_args);
    }

//    public static Struct callSimulationBeanIvFoeGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanIvFoeGet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanIvPlayerGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanIvPlayerGet(),_str,_args);
//    }

    public static Struct callSimulationBeanKeptMovesAfterFightGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanKeptMovesAfterFightGet(),_str,_args);
    }

    public static Struct callSimulationBeanKeptMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanKeptMovesGet(),_str,_args);
    }

    public static Struct callSimulationBeanLayers(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanLayers(),_str,_args);
    }

    public static Struct callSimulationBeanLevelEvoGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanLevelEvoGet(),_str,_args);
    }

    public static Struct callSimulationBeanMovesSetGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanMovesSetGet(),_str,_args);
    }

    public static Struct callSimulationBeanMultiplicityGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanMultiplicityGet(),_str,_args);
    }

    public static Struct callSimulationBeanNbTeamsGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanNbTeamsGet(),_str,_args);
    }

    public static Struct callSimulationBeanNextFight(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanNextFight(),_str,_args);
    }

    public static Struct callSimulationBeanNumberNecessaryPointsForGrowingLevel(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanNumberNecessaryPointsForGrowingLevel(),_str,_args);
    }

    public static Struct callSimulationBeanNumbers(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanNumbers(),_str,_args);
    }

    public static Struct callSimulationBeanOkGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanOkGet(),_str,_args);
    }

    public static Struct callSimulationBeanPlaceFightGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanPlaceFightGet(),_str,_args);
    }

    public static Struct callSimulationBeanPlacesFightGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanPlacesFightGet(),_str,_args);
    }

    public static Struct callSimulationBeanPlacesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanPlacesGet(),_str,_args);
    }

    public static Struct callSimulationBeanQuit(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanQuit(),_str,_args);
    }

//    public static Struct callSimulationBeanRandomWildFightGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanRandomWildFightGet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanRateLooseMoneyWinGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanRateLooseMoneyWinGet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanRateWinMoneyBaseGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanRateWinMoneyBaseGet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanRateWinningExpPtsFightGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanRateWinningExpPtsFightGet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanRestoredMovesEndFightGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanRestoredMovesEndFightGet(),_str,_args);
//    }

    public static Struct callSimulationBeanRoundGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanRoundGet(),_str,_args);
    }

    public static Struct callSimulationBeanSelectAllyPk(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectAllyPk(),_str,_args);
    }

    public static Struct callSimulationBeanSelectFoePk(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectFoePk(),_str,_args);
    }

    public static Struct callSimulationBeanSelectPk(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectPk(),_str,_args);
    }

    public static Struct callSimulationBeanSelectPkAfterFight(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectPkAfterFight(),_str,_args);
    }

    public static Struct callSimulationBeanSelectedActionGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectedActionGet(),_str,_args);
    }

    public static Struct callSimulationBeanSelectedAllyActionGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectedAllyActionGet(),_str,_args);
    }

    public static Struct callSimulationBeanSelectedAllyPkGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectedAllyPkGet(),_str,_args);
    }

    public static Struct callSimulationBeanSelectedFoeActionGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectedFoeActionGet(),_str,_args);
    }

    public static Struct callSimulationBeanSelectedFoePkGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectedFoePkGet(),_str,_args);
    }

    public static Struct callSimulationBeanSelectedIndex(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectedIndex(),_str,_args);
    }

    public static Struct callSimulationBeanSelectedIndexForMoves(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectedIndexForMoves(),_str,_args);
    }

    public static Struct callSimulationBeanSelectedMoveGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectedMoveGet(),_str,_args);
    }

    public static Struct callSimulationBeanSelectedPkGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectedPkGet(),_str,_args);
    }

    public static Struct callSimulationBeanSelectedRoundGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectedRoundGet(),_str,_args);
    }

    public static Struct callSimulationBeanSimulateFight(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanSimulateFight(),_str,_args);
    }

//    public static Struct callSimulationBeanSkipLearningMovesWhileNotGrowingLevelGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanSkipLearningMovesWhileNotGrowingLevelGet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanStillPossibleFleeGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanStillPossibleFleeGet(),_str,_args);
//    }

    public static Struct callSimulationBeanTargetFightGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanTargetFightGet(),_str,_args);
    }

    public static Struct callSimulationBeanTargetGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanTargetGet(),_str,_args);
    }

    public static Struct callSimulationBeanTeamAfterFightGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanTeamAfterFightGet(),_str,_args);
    }

    public static Struct callSimulationBeanTeamGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanTeamGet(),_str,_args);
    }

    public static Struct callSimulationBeanValidateDiffChoice(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateDiffChoice(),_str,_args);
    }

    public static Struct callSimulationBeanValidateEvo(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateEvo(),_str,_args);
    }

    public static Struct callSimulationBeanValidateEvolutionAfterFight(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateEvolutionAfterFight(),_str,_args);
    }

    public static Struct callSimulationBeanValidateEvolutions(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateEvolutions(),_str,_args);
    }

    public static Struct callSimulationBeanValidateFoeChoice(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateFoeChoice(),_str,_args);
    }

    public static Struct callSimulationBeanValidateFoeChoiceFree(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateFoeChoiceFree(),_str,_args);
    }

    public static Struct callSimulationBeanValidateFoeChoiceSkipEvolutions(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateFoeChoiceSkipEvolutions(),_str,_args);
    }

    public static Struct callSimulationBeanValidateFrontFighter(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateFrontFighter(),_str,_args);
    }

    public static Struct callSimulationBeanValidateFrontFighters(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateFrontFighters(),_str,_args);
    }

    public static Struct callSimulationBeanValidateMoves(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateMoves(),_str,_args);
    }

    public static Struct callSimulationBeanValidateMovesAbilityAfterFight(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateMovesAbilityAfterFight(),_str,_args);
    }

    public static Struct callSimulationBeanValidateMovesAfterFight(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateMovesAfterFight(),_str,_args);
    }

    public static Struct callSimulationBeanValidateMovesChoice(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateMovesChoice(),_str,_args);
    }

    public static Struct callSimulationBeanValidateMovesSets(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateMovesSets(),_str,_args);
    }

    public static Struct callSimulationBeanValidateTeam(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateTeam(),_str,_args);
    }

    public static Struct callSimulationBeanAbilityAfterFightSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new SimulationBeanAbilityAfterFightSet(),_str,_args);
    }

    public static Struct callSimulationBeanChosenEvoSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new SimulationBeanChosenEvoSet(),_str,_args);
    }

    public static Struct callSimulationBeanCurrentAbilitySet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new SimulationBeanCurrentAbilitySet(),_str,_args);
    }

//    public static Struct callSimulationBeanDamageRateLawFoeSet(Struct _str, String _args) {
//        return BeanPokemonCommonTs.callString(new SimulationBeanDamageRateLawFoeSet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanDamageRatePlayerSet(Struct _str, String _args) {
//        return BeanPokemonCommonTs.callString(new SimulationBeanDamageRatePlayerSet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanDiffWinningExpPtsFightSet(Struct _str, String _args) {
//        return BeanPokemonCommonTs.callString(new SimulationBeanDiffWinningExpPtsFightSet(),_str,_args);
//    }

    public static Struct callSimulationBeanEnvironmentSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new SimulationBeanEnvironmentSet(),_str,_args);
    }

    public static Struct callSimulationBeanEvolutionAfterFightSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new SimulationBeanEvolutionAfterFightSet(),_str,_args);
    }

    public static Struct callSimulationBeanPlaceFightSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new SimulationBeanPlaceFightSet(),_str,_args);
    }

    public static Struct callSimulationBeanSelectedActionSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new SimulationBeanSelectedActionSet(),_str,_args);
    }

    public static Struct callSimulationBeanSelectedAllyActionSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new SimulationBeanSelectedAllyActionSet(),_str,_args);
    }

    public static Struct callSimulationBeanSelectedFoeActionSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new SimulationBeanSelectedFoeActionSet(),_str,_args);
    }

    public static Struct callSimulationBeanSelectedRoundSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new SimulationBeanSelectedRoundSet(),_str,_args);
    }

    public static Struct callSimulationBeanTargetSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new SimulationBeanTargetSet(),_str,_args);
    }

    public static Struct callSimulationBeanIndexTeamSet(Struct _str, int _args) {
        return BeanPokemonCommonTs.callInt(new SimulationBeanIndexTeamSet(),_str,_args);
    }

//    public static Struct callSimulationBeanIvFoeSet(Struct _str, int _args) {
//        return BeanPokemonCommonTs.callInt(new SimulationBeanIvFoeSet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanIvPlayerSet(Struct _str, int _args) {
//        return BeanPokemonCommonTs.callInt(new SimulationBeanIvPlayerSet(),_str,_args);
//    }

    public static Struct callSimulationBeanLevelEvoSet(Struct _str, int _args) {
        return BeanPokemonCommonTs.callInt(new SimulationBeanLevelEvoSet(),_str,_args);
    }

    public static Struct callSimulationBeanMultiplicitySet(Struct _str, int _args) {
        return BeanPokemonCommonTs.callInt(new SimulationBeanMultiplicitySet(),_str,_args);
    }

    public static Struct callSimulationBeanNbTeamsSet(Struct _str, int _args) {
        return BeanPokemonCommonTs.callInt(new SimulationBeanNbTeamsSet(),_str,_args);
    }

    public static Struct callSimulationBeanSelectedAllyPkSet(Struct _str, int _args) {
        return BeanPokemonCommonTs.callInt(new SimulationBeanSelectedAllyPkSet(),_str,_args);
    }

    public static Struct callSimulationBeanSelectedFoePkSet(Struct _str, int _args) {
        return BeanPokemonCommonTs.callInt(new SimulationBeanSelectedFoePkSet(),_str,_args);
    }

    public static Struct callSimulationBeanSelectedMoveSet(Struct _str, int _args) {
        return BeanPokemonCommonTs.callInt(new SimulationBeanSelectedMoveSet(),_str,_args);
    }

    public static Struct callSimulationBeanSelectedPkSet(Struct _str, int _args) {
        return BeanPokemonCommonTs.callInt(new SimulationBeanSelectedPkSet(),_str,_args);
    }

//    public static Struct callSimulationBeanAllowCatchingKoSet(Struct _str, boolean _args) {
//        return BeanPokemonCommonTs.callBool(new SimulationBeanAllowCatchingKoSet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanAllowedSwitchPlacesEndRoundSet(Struct _str, boolean _args) {
//        return BeanPokemonCommonTs.callBool(new SimulationBeanAllowedSwitchPlacesEndRoundSet(),_str,_args);
//    }

    public static Struct callSimulationBeanAllyChoiceSet(Struct _str, boolean _args) {
        return BeanPokemonCommonTs.callBool(new SimulationBeanAllyChoiceSet(),_str,_args);
    }

//    public static Struct callSimulationBeanEnabledClosingSet(Struct _str, boolean _args) {
//        return BeanPokemonCommonTs.callBool(new SimulationBeanEnabledClosingSet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanEndFightIfOneTeamKoSet(Struct _str, boolean _args) {
//        return BeanPokemonCommonTs.callBool(new SimulationBeanEndFightIfOneTeamKoSet(),_str,_args);
//    }

    public static Struct callSimulationBeanFreeTeamsSet(Struct _str, boolean _args) {
        return BeanPokemonCommonTs.callBool(new SimulationBeanFreeTeamsSet(),_str,_args);
    }

//    public static Struct callSimulationBeanRateLooseMoneyWinSet(Struct _str, Rate _args) {
//        return BeanPokemonCommonTs.callRate(new SimulationBeanRateLooseMoneyWinSet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanRateWinMoneyBaseSet(Struct _str, Rate _args) {
//        return BeanPokemonCommonTs.callRate(new SimulationBeanRateWinMoneyBaseSet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanRateWinningExpPtsFightSet(Struct _str, Rate _args) {
//        return BeanPokemonCommonTs.callRate(new SimulationBeanRateWinningExpPtsFightSet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanWinTrainerExpSet(Struct _str, Rate _args) {
//        return BeanPokemonCommonTs.callRate(new SimulationBeanWinTrainerExpSet(),_str,_args);
//    }
//    public static Struct callSimulationBeanRandomWildFightSet(Struct _str, boolean _args) {
//        return BeanPokemonCommonTs.callBool(new SimulationBeanRandomWildFightSet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanRestoredMovesEndFightSet(Struct _str, boolean _args) {
//        return BeanPokemonCommonTs.callBool(new SimulationBeanRestoredMovesEndFightSet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanSkipLearningMovesWhileNotGrowingLevelSet(Struct _str, boolean _args) {
//        return BeanPokemonCommonTs.callBool(new SimulationBeanSkipLearningMovesWhileNotGrowingLevelSet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanStillPossibleFleeSet(Struct _str, boolean _args) {
//        return BeanPokemonCommonTs.callBool(new SimulationBeanStillPossibleFleeSet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanWinPointsFightGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanWinPointsFightGet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanWinTrainerExpGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanWinTrainerExpGet(),_str,_args);
//    }

    public static Struct callAddPokemonBeanAbilitySet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new AddPokemonBeanAbilitySet(),_str,_args);
    }

    public static Struct callAddPokemonBeanGenderSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new AddPokemonBeanGenderSet(),_str,_args);
    }

    public static Struct callAddPokemonBeanHasEvoSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new AddPokemonBeanHasEvoSet(),_str,_args);
    }

    public static Struct callAddPokemonBeanIsEvoSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new AddPokemonBeanIsEvoSet(),_str,_args);
    }

    public static Struct callAddPokemonBeanIsLegSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new AddPokemonBeanIsLegSet(),_str,_args);
    }

    public static Struct callAddPokemonBeanTypedNameSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new AddPokemonBeanTypedNameSet(),_str,_args);
    }

    public static Struct callAddPokemonBeanTypedTypeSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new AddPokemonBeanTypedTypeSet(),_str,_args);
    }

    public static Struct callEditPokemonBeanBallSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new EditPokemonBeanBallSet(),_str,_args);
    }

    public static Struct callEditPokemonMovesBeanCategorySet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new EditPokemonMovesBeanCategorySet(),_str,_args);
    }

    public static Struct callEditPokemonMovesBeanTypedNameSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new EditPokemonMovesBeanTypedNameSet(),_str,_args);
    }

    public static Struct callEditPokemonMovesBeanTypedTypeSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new EditPokemonMovesBeanTypedTypeSet(),_str,_args);
    }

    public static Struct callEditTrainerPokemonBeanGenderSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new EditTrainerPokemonBeanGenderSet(),_str,_args);
    }

    public static Struct callSelectAbilityBeanTypedAbilitySet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new SelectAbilityBeanTypedAbilitySet(),_str,_args);
    }

    public static Struct callSelectItemBeanTypedClassSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new SelectItemBeanTypedClassSet(),_str,_args);
    }

    public static Struct callSelectItemBeanTypedNameSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new SelectItemBeanTypedNameSet(),_str,_args);
    }

    public static Struct callSelectItemBeanTypedPriceSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new SelectItemBeanTypedPriceSet(),_str,_args);
    }

    public static Struct callSelectPokemonBeanHasEvoSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new SelectPokemonBeanHasEvoSet(),_str,_args);
    }

    public static Struct callSelectPokemonBeanIsEvoSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new SelectPokemonBeanIsEvoSet(),_str,_args);
    }

    public static Struct callSelectPokemonBeanIsLegSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new SelectPokemonBeanIsLegSet(),_str,_args);
    }

    public static Struct callSelectPokemonBeanTypedNameSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new SelectPokemonBeanTypedNameSet(),_str,_args);
    }

    public static Struct callSelectPokemonBeanTypedTypeSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new SelectPokemonBeanTypedTypeSet(),_str,_args);
    }

    public static Struct callAddPokemonBeanClickLink(Struct _str, int _args) {
        return BeanPokemonCommonTs.callInt(new AddPokemonBeanClickLink(),_str,_args);
    }

    public static Struct callAddPokemonBeanLevelSet(Struct _str, int _args) {
        return BeanPokemonCommonTs.callInt(new AddPokemonBeanLevelSet(),_str,_args);
    }

    public static Struct callEditPokemonBeanHappinessSet(Struct _str, int _args) {
        return BeanPokemonCommonTs.callInt(new EditPokemonBeanHappinessSet(),_str,_args);
    }

    public static Struct callEditTrainerPokemonBeanLevelSet(Struct _str, int _args) {
        return BeanPokemonCommonTs.callInt(new EditTrainerPokemonBeanLevelSet(),_str,_args);
    }


    public static Struct callSimulationLevelBeanNoFightSet(Struct _str, int _args) {
        return BeanPokemonCommonTs.callInt(new SimulationLevelBeanNoFightSet(),_str,_args);
    }

    public static Struct callSelectLineMoveSelectedSet(Struct _str, boolean _args) {
        return BeanPokemonCommonTs.callBool(new SelectLineMoveSelectedSet(),_str,_args);
    }


    public static Struct callAddPokemonBeanWholeWordSet(Struct _str, boolean _args) {
        return BeanPokemonCommonTs.callBool(new AddPokemonBeanWholeWordSet(),_str,_args);
    }

    public static Struct callEditPokemonBeanHealSet(Struct _str, boolean _args) {
        return BeanPokemonCommonTs.callBool(new EditPokemonBeanHealSet(),_str,_args);
    }

    public static Struct callEditPokemonMovesBeanAvailableMovesOnlySet(Struct _str, boolean _args) {
        return BeanPokemonCommonTs.callBool(new EditPokemonMovesBeanAvailableMovesOnlySet(),_str,_args);
    }

    public static Struct callEditPokemonMovesBeanWholeWordSet(Struct _str, boolean _args) {
        return BeanPokemonCommonTs.callBool(new EditPokemonMovesBeanWholeWordSet(),_str,_args);
    }

    public static Struct callEditTrainerPokemonBeanAllyPkSet(Struct _str, boolean _args) {
        return BeanPokemonCommonTs.callBool(new EditTrainerPokemonBeanAllyPkSet(),_str,_args);
    }

    public static Struct callSelectPokemonBeanWholeWordSet(Struct _str, boolean _args) {
        return BeanPokemonCommonTs.callBool(new SelectPokemonBeanWholeWordSet(),_str,_args);
    }

    public static Struct callEditPokemonBeanExperienceSet(Struct _str, Rate _args) {
        return BeanPokemonCommonTs.callRate(new EditPokemonBeanExperienceSet(),_str,_args);
    }

    public static Struct callEditPokemonBeanRemainingHpSet(Struct _str, Rate _args) {
        return BeanPokemonCommonTs.callRate(new EditPokemonBeanRemainingHpSet(),_str,_args);
    }

    public static Struct callEvLineEvGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EvLineEvGet(),_str,_args);
    }
    public static Struct callEvLineEvSet(Struct _str, int _args) {
        return BeanPokemonCommonTs.callInt(new EvLineEvSet(),_str,_args);
    }
    public static Struct callDifficultyBeanComGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyBeanComGet(),_str,_args);
    }

    public static Struct callPokemonPlayerDtoIndexGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonPlayerDtoIndexGet(),_str,_args);
    }

    public static Struct callPokemonTrainerDtoIndexGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonTrainerDtoIndexGet(),_str,_args);
    }
    public static Struct callRadioLineMoveIndexGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new RadioLineMoveIndexGet(),_str,_args);
    }

    public static Struct callSelectLineMoveSelectedGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectLineMoveSelectedGet(),_str,_args);
    }
}
