package aiki.beans.simulation;

import aiki.beans.*;
import aiki.beans.db.*;
import aiki.beans.facade.simulation.dto.*;
import aiki.beans.facade.simulation.enums.*;
import aiki.beans.game.*;
import aiki.db.*;
import aiki.facade.*;
import aiki.facade.enums.*;
import aiki.fight.enums.*;
import aiki.fight.items.*;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.enums.*;
import aiki.fight.pokemon.*;
import aiki.fight.pokemon.enums.*;
import aiki.fight.pokemon.evolution.*;
import aiki.fight.util.*;
import aiki.game.fight.*;
import aiki.game.params.enums.*;
import aiki.instances.*;
import aiki.map.*;
import aiki.map.levels.enums.*;
import aiki.map.pokemon.*;
import aiki.map.pokemon.enums.*;
import aiki.util.*;
import code.bean.nat.*;
import code.expressionlanguage.structs.*;
import code.images.*;
import code.maths.*;
import code.maths.montecarlo.*;
import code.util.*;

public abstract class InitDbSimulation extends InitDbConstr {
    public static final String M_POK_00 = "M_POK00";
    public static final String M_POK_01 = "M_POK01";
    public static final String M_POK_02 = "M_POK02";
    public static final String M_POK_03 = "M_POK03";
    public static final String M_POK_04 = "M_POK04";
    public static final String M_POK_05 = "M_POK05";
    public static final String M_POK_06 = "M_POK06";
    public static final String M_POK_07 = "M_POK07";

    public static final String P_POK_00 = "P_POK00";
    public static final String P_POK_01 = "P_POK01";
    public static final String P_POK_02 = "P_POK02";
    public static final String P_POK_03 = "P_POK03";
    public static final String P_POK_04 = "P_POK04";
    public static final String P_POK_05 = "P_POK05";
    public static final String P_POK_06 = "P_POK06";
    public static final String P_POK_07 = "P_POK07";
    public static final String P_POK_08 = "P_POK08";
    public static final String P_POK_09 = "P_POK09";
    public static final String P_POK_10 = "P_POK10";
    public static final String P_POK_11 = "P_POK11";
    public static final String P_POK_12 = "P_POK12";
    public static final String P_POK_13 = "P_POK13";
    public static final String P_POK_14 = "P_POK14";
    public static final String P_POK_15 = "P_POK15";


    public static final String T_SIM_1 = "T_SIM1";
    public static final String T_SIM_2 = "T_SIM2";
    public static final String C_SIM_1 = "C_SIM1";
    public static final String C_SIM_2 = "C_SIM2";
    public static final String I_STONE = "I_STONE";
    public static final String I_MULT_EXP = "I_MULT_EXP";
    public static final String A_SIM_1 = "A_SIM1";
    public static final String A_SIM_2 = "A_SIM2";
    public static final String I_NOTHING = "I_NOTHING";
    public static final String T_SIM_1_TR = "T_SIM_1_TR";
    public static final String T_SIM_2_TR = "T_SIM_2_TR";
    public static final String P_POK_00_TR = "P_POK_00_TR";
    public static final String P_POK_01_TR = "P_POK_01_TR";
    public static final String P_POK_02_TR = "P_POK_02_TR";
    public static final String P_POK_03_TR = "P_POK_03_TR";
    public static final String P_POK_04_TR = "P_POK_04_TR";
    public static final String P_POK_05_TR = "P_POK_05_TR";
    public static final String P_POK_06_TR = "P_POK_06_TR";
    public static final String P_POK_07_TR = "P_POK_07_TR";
    public static final String P_POK_08_TR = "P_POK_08_TR";
    public static final String P_POK_09_TR = "P_POK_09_TR";
    public static final String M_POK_00_TR = "M_POK_00_TR";
    public static final String M_POK_01_TR = "M_POK_01_TR";
    public static final String M_POK_02_TR = "M_POK_02_TR";
    public static final String M_POK_03_TR = "M_POK_03_TR";
    public static final String M_POK_04_TR = "M_POK_04_TR";
    public static final String M_POK_05_TR = "M_POK_05_TR";
    public static final String M_POK_06_TR = "M_POK_06_TR";
    public static final String I_NOTHING_TR = "I_NOTHING1";
    public static final String I_MULT_EXP_TR = "I_MULT_EXP1";
    public static final String I_STONE_TR = "I_STONE1";
    public static final String A_SIM_1_TR = "A_SIM_1_TR";
    public static final String A_SIM_2_TR = "A_SIM_2_TR";
    public static final String C_SIM_1_TR = "C_SIM_1_TR";
    public static final String C_SIM_2_TR = "C_SIM_2_TR";
    public static final String CI_BATTLE = "CI_BATTLE";
    public static final String CI_STONE = "CI_STONE";
    public static final String I_BALL = "I_BALL";
    public static final String CI_BALL = "CI_BALL";
    public static final String I_BALL_TR = "I_BALL_TR";
    public static final String I_STONE_2 = "I_STONE2";
    public static final String I_STONE_2_TR = "I_STONE_2_TR";

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

    public static Struct callSimulationBeanFoeTeamGet(Struct _str) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanFoeTeamGet(),_str);
    }

    public static Struct callSimulationBeanFreeTeamsGet(int _team) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanFreeTeamsGet(),initByTeam(_team));
    }

    public static Struct callSimulationBeanGetAbility(Struct _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetAbility(),_str,_index);
    }

    public static Struct callSimulationBeanGetAbilityAfterFight(Struct _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetAbilityAfterFight(),_str,_index);
    }

    public static Struct callSimulationBeanGetAbilityAlly(Struct _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetAbilityAlly(),_str,_index);
    }

    public static Struct callSimulationBeanGetAbilityFoe(Struct _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetAbilityFoe(),_str,_index);
    }

    public static Struct callSimulationBeanGetGender(Struct _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetGender(),_str,_index);
    }

    public static Struct callSimulationBeanGetGenderAfterFight(Struct _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetGenderAfterFight(),_str,_index);
    }

    public static Struct callSimulationBeanGetGenderAlly(Struct _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetGenderAlly(),_str,_index);
    }

    public static Struct callSimulationBeanGetGenderFoe(Struct _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetGenderFoe(),_str,_index);
    }

    public static Struct callSimulationBeanGetImage(Struct _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetImage(),_str,_index);
    }

    public static Struct callSimulationBeanGetImageAfterFight(Struct _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetImageAfterFight(),_str,_index);
    }

    public static Struct callSimulationBeanGetImageAlly(Struct _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetImageAlly(),_str,_index);
    }

    public static Struct callSimulationBeanGetImageFoe(Struct _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetImageFoe(),_str,_index);
    }

    public static Struct callSimulationBeanGetItem(Struct _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetItem(),_str,_index);
    }

    public static Struct callSimulationBeanGetItemAfterFight(Struct _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetItemAfterFight(),_str,_index);
    }

    public static Struct callSimulationBeanGetItemAlly(Struct _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetItemAlly(),_str,_index);
    }

    public static Struct callSimulationBeanGetItemFoe(Struct _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetItemFoe(),_str,_index);
    }

    public static Struct callSimulationBeanGetKoFoes(Struct _str) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetKoFoes(),_str);
    }

    public static Struct callSimulationBeanGetKoPlayers(Struct _str) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetKoPlayers(),_str);
    }

    public static Struct callSimulationBeanGetLevel(Struct _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetLevel(),_str,_index);
    }

    public static Struct callSimulationBeanGetLevelAfterFight(Struct _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetLevelAfterFight(),_str,_index);
    }

    public static Struct callSimulationBeanGetLevelAlly(Struct _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetLevelAlly(),_str,_index);
    }

    public static Struct callSimulationBeanGetLevelFoe(Struct _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetLevelFoe(),_str,_index);
    }

    public static Struct callSimulationBeanGetMoves(Struct _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetMoves(),_str,_index);
    }

    public static Struct callSimulationBeanGetMovesAfterFight(Struct _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetMovesAfterFight(),_str,_index);
    }

    public static Struct callSimulationBeanGetMovesAlly(Struct _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetMovesAlly(),_str,_index);
    }

    public static Struct callSimulationBeanGetMovesFoe(Struct _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetMovesFoe(),_str,_index);
    }

    public static Struct callSimulationBeanGetName(Struct _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetName(),_str,_index);
    }

    public static Struct callSimulationBeanGetNameAfterFight(Struct _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetNameAfterFight(),_str,_index);
    }

    public static Struct callSimulationBeanGetNameAlly(Struct _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetNameAlly(),_str,_index);
    }

    public static Struct callSimulationBeanGetNameFoe(Struct _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetNameFoe(),_str,_index);
    }

    public static Struct callSimulationBeanGetNotKoFrontFoes(Struct _str) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetNotKoFrontFoes(),_str);
    }

    public static Struct callSimulationBeanGetRealStepNumber(Struct _str) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetRealStepNumber(),_str);
    }

    public static Struct callSimulationBeanGetRemainingLifeRate(Struct _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetRemainingLifeRate(),_str,_index);
    }

    public static Struct callSimulationBeanGetTrainerName(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetTrainerName(),_str,_args);
    }

    public static Struct callSimulationBeanHideComments(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanHideComments(),_str,_args);
    }

    public static Struct callSimulationBeanIndexTeamGet(Struct _str) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIndexTeamGet(),_str);
    }

    public static Struct callSimulationBeanIsAvailableAbilities(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsAvailableAbilities(),_str,_args);
    }

    public static Struct callSimulationBeanIsAvailableMoves(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsAvailableMoves(),_str,_args);
    }

    public static Struct callSimulationBeanIsDiffState(Struct _str) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsDiffState(),_str);
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

//    public static Struct callSimulationBeanIsHardSimulationIssue(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsHardSimulationIssue(),_str,_args);
//    }
//
    public static Struct callSimulationBeanIsIssue(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsIssue(),_str,_args);
    }

//    public static Struct callSimulationBeanIsIssueAfterFight(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsIssueAfterFight(),_str,_args);
//    }

    public static Struct callSimulationBeanIsMovesFightState(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsMovesFightState(),_str,_args);
    }

    public static Struct callSimulationBeanIsMovesState(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsMovesState(),_str,_args);
    }

    public static Struct callSimulationBeanIsMultiLayer(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsMultiLayer(),_str,_args);
    }
//
//    public static Struct callSimulationBeanIsRandomIssue(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsRandomIssue(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanIsRulesIssue(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsRulesIssue(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanIsRulesLearnIssue(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsRulesLearnIssue(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanIsRulesMovesIssue(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsRulesMovesIssue(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanIsRulesSwitchIssue(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsRulesSwitchIssue(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanIsSendingIssue(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsSendingIssue(),_str,_args);
//    }

    public static Struct callSimulationBeanIsSimulationState(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsSimulationState(),_str,_args);
    }

    public static Struct callSimulationBeanIsTeamState(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsTeamState(),_str,_args);
    }

//    public static Struct callSimulationBeanIsUsingIssue(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsUsingIssue(),_str,_args);
//    }

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

    public static Struct callSimulationBeanNbTeamsGet(int _team) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanNbTeamsGet(),initByTeam(_team));
    }

    public static Struct callSimulationBeanSelectedTeamNumberGet(Struct _str) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectedTeamNumberGet(),_str);
    }

    public static Struct callSimulationBeanNextFight(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanNextFight(),_str,_args);
    }

    public static Struct callSimulationBeanNumberNecessaryPointsForGrowingLevel(Struct _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanNumberNecessaryPointsForGrowingLevel(),_str,_index);
    }

    public static Struct callSimulationBeanNumbers(int _teams) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanNumbers(),validateDiff(_teams));
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

//    public static Struct callSimulationBeanFreeTeamsSet(Struct _str, boolean _args) {
//        return BeanPokemonCommonTs.callBool(new SimulationBeanFreeTeamsSet(),_str,_args);
//    }

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
    public static Struct callDifficultyBeanComSet(Struct _str, Struct _diffCom) {
        return BeanPokemonCommonTs.callStruct(new DifficultyBeanComSet(),_str,_diffCom);
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
//    public static Struct callSimulationLevelBeanCancel(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationLevelBeanCancel(),_str,_args);
//    }

    public static Struct callSimulationLevelBeanClickTile(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationLevelBeanClickTile(),_str,_args);
    }

//    public static Struct callSimulationLevelBeanGetMapWidth(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationLevelBeanGetMapWidth(),_str,_args);
//    }
//
//    public static Struct callSimulationLevelBeanGymGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationLevelBeanGymGet(),_str,_args);
//    }
//
//    public static Struct callSimulationLevelBeanIsFirstRow(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationLevelBeanIsFirstRow(),_str,_args);
//    }
//
//    public static Struct callSimulationLevelBeanLevelIndexGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationLevelBeanLevelIndexGet(),_str,_args);
//    }

    public static Struct callSimulationLevelBeanNoFightGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationLevelBeanNoFightGet(),_str,_args);
    }

//    public static Struct callSimulationLevelBeanOutsideGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationLevelBeanOutsideGet(),_str,_args);
//    }
//
//    public static Struct callSimulationLevelBeanPlaceNameGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationLevelBeanPlaceNameGet(),_str,_args);
//    }
//
//    public static Struct callSimulationLevelBeanPokemonCenterGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationLevelBeanPokemonCenterGet(),_str,_args);
//    }
//
//    public static Struct callSimulationLevelBeanPossibleMultiLayerGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationLevelBeanPossibleMultiLayerGet(),_str,_args);
//    }
//
//    public static Struct callSimulationLevelBeanRoadGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationLevelBeanRoadGet(),_str,_args);
//    }
//
//    public static Struct callSimulationLevelBeanTilesGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationLevelBeanTilesGet(),_str,_args);
//    }


    public static Struct callAddPokemonBeanAbilitiesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanAbilitiesGet(),_str,_args);
    }

    public static Struct callAddPokemonBeanAbilityGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanAbilityGet(),_str,_args);
    }

    public static Struct callAddPokemonBeanAdd(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanAdd(),_str,_args);
    }

    public static Struct callAddPokemonBeanBooleansGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanBooleansGet(),_str,_args);
    }

    public static Struct callAddPokemonBeanCancel(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanCancel(),_str,_args);
    }

    public static Struct callAddPokemonBeanGenderGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanGenderGet(),_str,_args);
    }

    public static Struct callAddPokemonBeanGendersGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanGendersGet(),_str,_args);
    }

    public static Struct callAddPokemonBeanGetMiniImage() {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanGetMiniImage(),pkPlayerSelectPkName(""),0);
    }

    public static Struct callAddPokemonBeanHasEvoGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanHasEvoGet(),_str,_args);
    }

    public static Struct callAddPokemonBeanIsEvoGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanIsEvoGet(),_str,_args);
    }

    public static Struct callAddPokemonBeanIsLegGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanIsLegGet(),_str,_args);
    }

    public static Struct callAddPokemonBeanLevelGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanLevelGet(),_str,_args);
    }

    public static Struct callAddPokemonBeanNamePkGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanNamePkGet(),_str,_args);
    }

    public static Struct callAddPokemonBeanPokedexGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanPokedexGet(),_str,_args);
    }

    public static Struct callAddPokemonBeanSearch(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanSearch(),_str,_args);
    }

    public static Struct callAddPokemonBeanTypedNameGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanTypedNameGet(),_str,_args);
    }

    public static Struct callAddPokemonBeanTypedTypeGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanTypedTypeGet(),_str,_args);
    }

    public static Struct callAddPokemonBeanWholeWordGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanWholeWordGet(),_str,_args);
    }

    public static Struct callEditPokemonBeanAddMoves(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanAddMoves(),_str,_args);
    }

    public static Struct callEditPokemonBeanBallGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanBallGet(),_str,_args);
    }

    public static Struct callEditPokemonBeanBallsGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanBallsGet(),_str,_args);
    }

    public static Struct callEditPokemonBeanCancel(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanCancel(),_str,_args);
    }

    public static Struct callEditPokemonBeanChooseItem(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanChooseItem(),_str,_args);
    }

    public static Struct callEditPokemonBeanDeleteMoves(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanDeleteMoves(),_str,_args);
    }

    public static Struct callEditPokemonBeanEdit(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanEdit(),_str,_args);
    }

    public static Struct callEditPokemonBeanEvGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanEvGet(),_str,_args);
    }

    public static Struct callEditPokemonBeanExperienceGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanExperienceGet(),_str,_args);
    }

    public static Struct callEditPokemonBeanGetTranslatedStatistic(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanGetTranslatedStatistic(),_str,_args);
    }

    public static Struct callEditPokemonBeanHappinessGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanHappinessGet(),_str,_args);
    }

    public static Struct callEditPokemonBeanHealGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanHealGet(),_str,_args);
    }

    public static Struct callEditPokemonBeanLevelGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanLevelGet(),_str,_args);
    }

    public static Struct callEditPokemonBeanMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanMovesGet(),_str,_args);
    }

    public static Struct callEditPokemonBeanNamePkGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanNamePkGet(),_str,_args);
    }

    public static Struct callEditPokemonBeanRemainingHpGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanRemainingHpGet(),_str,_args);
    }

    public static Struct callEditPokemonBeanTranslateItem(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanTranslateItem(),_str,_args);
    }

    public static Struct callEditPokemonBeanTranslateName(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanTranslateName(),_str,_args);
    }

    public static Struct callEditPokemonMovesBeanAddMoves(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonMovesBeanAddMoves(),_str,_args);
    }

    public static Struct callEditPokemonMovesBeanAvailableMovesOnlyGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonMovesBeanAvailableMovesOnlyGet(),_str,_args);
    }

    public static Struct callEditPokemonMovesBeanCancel(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonMovesBeanCancel(),_str,_args);
    }

    public static Struct callEditPokemonMovesBeanCategoriesGet(Struct _str) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonMovesBeanCategoriesGet(),_str);
    }

    public static Struct callEditPokemonMovesBeanCategoryGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonMovesBeanCategoryGet(),_str,_args);
    }

    public static Struct callEditPokemonMovesBeanMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonMovesBeanMovesGet(),_str,_args);
    }

    public static Struct callEditPokemonMovesBeanPlayerGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonMovesBeanPlayerGet(),_str,_args);
    }

    public static Struct callEditPokemonMovesBeanSearch(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonMovesBeanSearch(),_str,_args);
    }

    public static Struct callEditPokemonMovesBeanTypedNameGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonMovesBeanTypedNameGet(),_str,_args);
    }

    public static Struct callEditPokemonMovesBeanTypedTypeGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonMovesBeanTypedTypeGet(),_str,_args);
    }

    public static Struct callEditPokemonMovesBeanWholeWordGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonMovesBeanWholeWordGet(),_str,_args);
    }

    public static Struct callEditTrainerPokemonBeanAddGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanAddGet(),_str,_args);
    }

    public static Struct callEditTrainerPokemonBeanAddMoves(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanAddMoves(),_str,_args);
    }

    public static Struct callEditTrainerPokemonBeanAllyPkGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanAllyPkGet(),_str,_args);
    }

    public static Struct callEditTrainerPokemonBeanCancel(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanCancel(),_str,_args);
    }

    public static Struct callEditTrainerPokemonBeanChooseAbility(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanChooseAbility(),_str,_args);
    }

    public static Struct callEditTrainerPokemonBeanChooseItem(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanChooseItem(),_str,_args);
    }

    public static Struct callEditTrainerPokemonBeanChooseName(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanChooseName(),_str,_args);
    }

    public static Struct callEditTrainerPokemonBeanDeleteMoves(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanDeleteMoves(),_str,_args);
    }

    public static Struct callEditTrainerPokemonBeanGenderGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanGenderGet(),_str,_args);
    }

    public static Struct callEditTrainerPokemonBeanGendersGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanGendersGet(),_str,_args);
    }

    public static Struct callEditTrainerPokemonBeanGetTranslatedAbility(Struct _str) {
        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanGetTranslatedAbility(),_str);
    }

    public static Struct callEditTrainerPokemonBeanGetTranslatedItem(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanGetTranslatedItem(),_str,_args);
    }

    public static Struct callEditTrainerPokemonBeanGetTranslatedName(Struct _str) {
        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanGetTranslatedName(),_str);
    }

    public static Struct callEditTrainerPokemonBeanLevelGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanLevelGet(),_str,_args);
    }

    public static Struct callEditTrainerPokemonBeanMovesGet(Struct _str) {
        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanMovesGet(),_str);
    }

    public static Struct callEditTrainerPokemonBeanValidateTrainerPk(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanValidateTrainerPk(),_str,_args);
    }

    public static Struct callSelectAbilityBeanCancel(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectAbilityBeanCancel(),_str,_args);
    }

    public static Struct callSelectAbilityBeanClickAbility(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectAbilityBeanClickAbility(),_str,_args);
    }

    public static Struct callSelectAbilityBeanGetTrAbility() {
        return BeanPokemonCommonTs.callLongs(new SelectAbilityBeanGetTrAbility(),pkTrainerSelectAb(""),1);
    }

    public static Struct callSelectAbilityBeanSearch(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectAbilityBeanSearch(),_str,_args);
    }

    public static Struct callSelectAbilityBeanSortedAbilitiesGet(Struct _str) {
        return BeanPokemonCommonTs.callLongs(new SelectAbilityBeanSortedAbilitiesGet(),_str);
    }

    public static Struct callSelectAbilityBeanTypedAbilityGet(Struct _str) {
        return BeanPokemonCommonTs.callLongs(new SelectAbilityBeanTypedAbilityGet(),_str);
    }

    public static Struct callSelectItemBeanCancel(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectItemBeanCancel(),_str,_args);
    }

    public static Struct callSelectItemBeanCancelItem(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectItemBeanCancelItem(),_str,_args);
    }

    public static Struct callSelectItemBeanClickLink(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectItemBeanClickLink(),_str,_args);
    }

    public static Struct callSelectItemBeanGetMiniImage() {
        return BeanPokemonCommonTs.callLongs(new SelectItemBeanGetMiniImage(),pkTrainerSelectItName(""),0);
    }

    public static Struct callSelectItemBeanItemsGet(Struct _str) {
        return BeanPokemonCommonTs.callLongs(new SelectItemBeanItemsGet(),_str);
    }

    public static Struct callSelectItemBeanSearch(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectItemBeanSearch(),_str,_args);
    }

    public static Struct callSelectItemBeanTypedClassGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectItemBeanTypedClassGet(),_str,_args);
    }

    public static Struct callSelectItemBeanTypedNameGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectItemBeanTypedNameGet(),_str,_args);
    }

    public static Struct callSelectItemBeanTypedPriceGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectItemBeanTypedPriceGet(),_str,_args);
    }

    public static Struct callSelectPokemonBeanBooleansGet(Struct _str) {
        return BeanPokemonCommonTs.callLongs(new SelectPokemonBeanBooleansGet(),_str);
    }

    public static Struct callSelectPokemonBeanCancel(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectPokemonBeanCancel(),_str,_args);
    }

    public static Struct callSelectPokemonBeanClickLink(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectPokemonBeanClickLink(),_str,_args);
    }

    public static Struct callSelectPokemonBeanGetMiniImage() {
        return BeanPokemonCommonTs.callLongs(new SelectPokemonBeanGetMiniImage(),pkTrainerSelectPkName(""),0);
    }

    public static Struct callSelectPokemonBeanHasEvoGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectPokemonBeanHasEvoGet(),_str,_args);
    }

    public static Struct callSelectPokemonBeanIsEvoGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectPokemonBeanIsEvoGet(),_str,_args);
    }

    public static Struct callSelectPokemonBeanIsLegGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectPokemonBeanIsLegGet(),_str,_args);
    }

    public static Struct callSelectPokemonBeanPokedexGet(Struct _str) {
        return BeanPokemonCommonTs.callLongs(new SelectPokemonBeanPokedexGet(),_str);
    }

    public static Struct callSelectPokemonBeanSearch(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectPokemonBeanSearch(),_str,_args);
    }

    public static Struct callSelectPokemonBeanTypedNameGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectPokemonBeanTypedNameGet(),_str,_args);
    }

    public static Struct callSelectPokemonBeanTypedTypeGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectPokemonBeanTypedTypeGet(),_str,_args);
    }

    public static Struct callSelectPokemonBeanWholeWordGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectPokemonBeanWholeWordGet(),_str,_args);
    }
    protected static Struct validateDiff(int _nbTeam){
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        return simu(pk_,all_,mapping_,_nbTeam);
    }

    protected static Struct initByTeam(int _nbTeam) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        return init(_nbTeam,pk_,all_,mappingToSimu());
    }
    protected static Struct init(int _nbTeam, PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping) {
        Struct simu_ = init(_pk, _all, _mapping);
        callSimulationBeanNbTeamsSet(simu_, _nbTeam);
        return simu_;
    }
    protected static Struct selectTeam(Struct _simu, int _indexTeam) {
        callSimulationBeanIndexTeamSet(_simu, _indexTeam);
        beforeDisplaying(_simu);
        return _simu;
    }
    protected static Struct pkTrainerTwoTeamsNextOk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        selectTeam(simu_,0);
        pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_01_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_02_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_03_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        setMult(simu_,2);
        selectTeam(simu_,1);
        pkTrainerSelectPkNameCycle(false,P_POK_04_TR,A_SIM_2_TR,pk_,all_,mapping_,simu_, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_05_TR,A_SIM_2_TR,pk_,all_,mapping_,simu_, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_06_TR,A_SIM_2_TR,pk_,all_,mapping_,simu_, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_07_TR,A_SIM_2_TR,pk_,all_,mapping_,simu_, 5);
        setMult(simu_,2);
        return transitSimu(pk_,all_,mapping_,new SimulationBeanValidateFoeChoiceFree(),simu_);
    }
    protected static Struct pkTrainerTwoTeamsNextOkAlly() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 1);
        selectTeam(simu_,0);
        pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_01_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        pkTrainerSelectPkNameCycle(true, P_POK_02_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        pkTrainerSelectPkNameCycle(true, P_POK_03_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        setMult(simu_,2);
        return transitSimu(pk_,all_,mapping_,new SimulationBeanValidateFoeChoiceFree(),simu_);
    }
    protected static Struct pkTrainerTwoTeamsNextAdjMult() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        selectTeam(simu_,0);
        pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_01_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_02_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_03_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        setMult(simu_,0);
        selectTeam(simu_,1);
        pkTrainerSelectPkNameCycle(false,P_POK_04_TR,A_SIM_2_TR,pk_,all_,mapping_,simu_, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_05_TR,A_SIM_2_TR,pk_,all_,mapping_,simu_, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_06_TR,A_SIM_2_TR,pk_,all_,mapping_,simu_, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_07_TR,A_SIM_2_TR,pk_,all_,mapping_,simu_, 5);
        setMult(simu_,8);
        return transitSimu(pk_,all_,mapping_,new SimulationBeanValidateFoeChoiceFree(),simu_);
    }
    protected static Struct pkTrainerTwoTeamsNextKo() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 1);
        return transitSimu(pk_,all_,mapping_,new SimulationBeanValidateFoeChoiceFree(),simu_);
    }
    protected static Struct pkTrainerTwoTeams() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        selectTeam(simu_,0);
        pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        selectTeam(simu_,1);
        return pkTrainerSelectPkNameCycle(false,P_POK_01_TR,A_SIM_2_TR,pk_,all_,mapping_,simu_, 5);
    }
    protected static Struct dispSimu() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        return init(pk_, all_, mappingToSimu());
    }

    private static Struct init(PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping) {
        Struct from_ = _all.getVal(AikiBeansStd.BEAN_WELCOME);
        Struct dCom_ = _all.getVal(AikiBeansGameStd.BEAN_DIFFICULTY_COMMON);
        beforeDisplaying(from_);
        Struct simu_ = transitSimu(_pk, _all, _mapping, new WelcomeBeanClickSimulation(), from_);
        callDifficultyBeanComSet(dCom_,callDifficultyBeanComGet(simu_));
        beforeDisplaying(dCom_);
        return simu_;
    }
    protected static Struct pkTrainer() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        return goToAddPkTrainer(pk_,all_,mapping_,simu_);
    }
    protected static String editNoFoePk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        callSimulationBeanSelectedFoePkSet(simu_,-1);
        return navigateData(new SimulationBeanSelectFoePk(),simu_);
    }
    protected static Struct pkTrainerFoeRemove() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        Struct second_ = pkTrainerSelectPkNameCycle(false,P_POK_01_TR,A_SIM_2_TR,pk_,all_,mapping_,simu_, 5);
        callSimulationBeanSelectedFoeActionSet(second_, TeamCrud.REMOVE.getTeamCrudString());
        callSimulationBeanSelectedFoePkSet(second_,0);
        return transitSimu(pk_,all_,mapping_,new SimulationBeanSelectFoePk(),second_);
    }
    protected static String editNoSelectedFoePk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct added_ = pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        callSimulationBeanSelectedFoeActionSet(added_, TeamCrud.NOTHING.getTeamCrudString());
        callSimulationBeanSelectedFoePkSet(added_,-1);
        return navigateData(new SimulationBeanSelectFoePk(),added_);
    }
    protected static Struct editEditSelectedFoePk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct added_ = pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        callSimulationBeanSelectedFoeActionSet(added_, TeamCrud.EDIT.getTeamCrudString());
        callSimulationBeanSelectedFoePkSet(added_,0);
        return transitSimu(pk_,all_,mapping_,new SimulationBeanSelectFoePk(),added_);
    }
    protected static Struct formEditSelectedFoePk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct added_ = pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        callSimulationBeanSelectedFoeActionSet(added_, TeamCrud.EDIT.getTeamCrudString());
        callSimulationBeanSelectedFoePkSet(added_,0);
        return added_;
    }
    protected static Struct editEditSelectedFoePkAddMove() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct added_ = pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        callSimulationBeanSelectedFoeActionSet(added_, TeamCrud.EDIT.getTeamCrudString());
        callSimulationBeanSelectedFoePkSet(added_,0);
        Struct editing_ = transitSimu(pk_, all_, mapping_, new SimulationBeanSelectFoePk(), added_);
        addMoveTrainer(M_POK_01_TR,0,pk_,all_,mapping_,editing_);
        return transitSimu(pk_,all_,mapping_,new EditTrainerPokemonBeanValidateTrainerPk(),editing_);
    }
    protected static String editForgetSelectedFoePk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct added_ = pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        callSimulationBeanSelectedFoeActionSet(added_, TeamCrud.NOTHING.getTeamCrudString());
        callSimulationBeanSelectedFoePkSet(added_,0);
        return navigateData(new SimulationBeanSelectFoePk(),added_);
    }
    protected static String editNoAllyPk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        callSimulationBeanSelectedAllyPkSet(simu_,-1);
        return navigateData(new SimulationBeanSelectAllyPk(),simu_);
    }
    protected static Struct pkTrainerAllyRemove() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        pkTrainerSelectPkNameCycle(true, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        Struct second_ = pkTrainerSelectPkNameCycle(true,P_POK_01_TR,A_SIM_2_TR,pk_,all_,mapping_,simu_, 5);
        callSimulationBeanSelectedAllyActionSet(second_, TeamCrud.REMOVE.getTeamCrudString());
        callSimulationBeanSelectedAllyPkSet(second_,0);
        return transitSimu(pk_,all_,mapping_,new SimulationBeanSelectAllyPk(),second_);
    }
    protected static String editNoSelectedAllyPk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct added_ = pkTrainerSelectPkNameCycle(true, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        callSimulationBeanSelectedAllyActionSet(added_, TeamCrud.NOTHING.getTeamCrudString());
        callSimulationBeanSelectedAllyPkSet(added_,-1);
        return navigateData(new SimulationBeanSelectAllyPk(),added_);
    }
    protected static Struct editEditSelectedAllyPk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct added_ = pkTrainerSelectPkNameCycle(true, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        callSimulationBeanSelectedAllyActionSet(added_, TeamCrud.EDIT.getTeamCrudString());
        callSimulationBeanSelectedAllyPkSet(added_,0);
        return transitSimu(pk_,all_,mapping_,new SimulationBeanSelectAllyPk(),added_);
    }
    protected static Struct formEditSelectedAllyPk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct added_ = pkTrainerSelectPkNameCycle(true, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        callSimulationBeanSelectedAllyActionSet(added_, TeamCrud.EDIT.getTeamCrudString());
        callSimulationBeanSelectedAllyPkSet(added_,0);
        return added_;
    }
    protected static Struct editEditSelectedAllyPkAddMove() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct added_ = pkTrainerSelectPkNameCycle(true, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        callSimulationBeanSelectedAllyActionSet(added_, TeamCrud.EDIT.getTeamCrudString());
        callSimulationBeanSelectedAllyPkSet(added_,0);
        Struct editing_ = transitSimu(pk_, all_, mapping_, new SimulationBeanSelectAllyPk(), added_);
        addMoveTrainer(M_POK_01_TR,0,pk_,all_,mapping_,editing_);
        return transitSimu(pk_,all_,mapping_,new EditTrainerPokemonBeanValidateTrainerPk(),editing_);
    }
    protected static String editForgetSelectedAllyPk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct added_ = pkTrainerSelectPkNameCycle(true, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        callSimulationBeanSelectedAllyActionSet(added_, TeamCrud.NOTHING.getTeamCrudString());
        callSimulationBeanSelectedAllyPkSet(added_,0);
        return navigateData(new SimulationBeanSelectAllyPk(),added_);
    }
    protected static Struct pkTrainerIndex() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        return pkTrainerSelectPkNameCycle(false,P_POK_01_TR,A_SIM_2_TR,pk_,all_,mapping_,simu_, 5);
    }
    protected static Struct pkTrainerLevel(int _level) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        return pkTrainerSelectPkNameCycle(false,P_POK_00_TR,A_SIM_1_TR,pk_,all_,mapping_,simu_, _level);
    }
    protected static Struct pkTrainerLevelCancelAdd() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct editPkTrainer_ = goToAddPkTrainer(pk_,all_,mapping_,simu_);
        return transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanCancel(), editPkTrainer_);
    }
    protected static Struct pkTrainerLevelRestoreMoves() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct editPkTrainer_ = goToAddPkTrainer(pk_,all_,mapping_,simu_);
        callSelectLineMoveSelectedSet(elt(callEditTrainerPokemonBeanMovesGet(editPkTrainer_),0),true);
        Struct after_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanDeleteMoves(), editPkTrainer_);
        callEditTrainerPokemonBeanAllyPkSet(after_,false);
        return transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanValidateTrainerPk(), after_);
    }
    private static Struct pkTrainerSelectPkNameCycle(boolean _ally, String _name, String _ability, PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu, int _level) {
        Struct editPkTrainer_ = goToAddPkTrainer(_pk, _all, _mapping, _simu);
        Struct retPk_ = chooseName(_name, _pk, _all, _mapping, editPkTrainer_);
        Struct selAb_ = chooseAbility(_ability, _pk, _all, _mapping, retPk_);
        callEditTrainerPokemonBeanAllyPkSet(selAb_, _ally);
        callEditTrainerPokemonBeanLevelSet(selAb_, _level);
        genderSet(selAb_);
        Struct afterAddEdit_ = transitSimu(_pk, _all, _mapping, new EditTrainerPokemonBeanValidateTrainerPk(), selAb_);
        assertSame(afterAddEdit_,_simu);
        return afterAddEdit_;
    }

    private static Struct pkTrainerSelectPkNameCycle(boolean _ally, String _name, String _ability, String _item, PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu, int _level) {
        Struct editPkTrainer_ = goToAddPkTrainer(_pk, _all, _mapping, _simu);
        Struct retPk_ = chooseName(_name, _pk, _all, _mapping, editPkTrainer_);
        Struct retAb_ = chooseAbility(_ability, _pk, _all, _mapping, retPk_);
        assertSame(retAb_, chooseItemPkTrainer(_item,_pk,_all,_mapping,retAb_));
        callEditTrainerPokemonBeanAllyPkSet(retAb_, _ally);
        callEditTrainerPokemonBeanLevelSet(retAb_, _level);
        genderSet(retAb_);
        Struct afterAddEdit_ = transitSimu(_pk, _all, _mapping, new EditTrainerPokemonBeanValidateTrainerPk(), retAb_);
        assertSame(afterAddEdit_,_simu);
        return afterAddEdit_;
    }

    private static void genderSet(Struct _str) {
        callEditTrainerPokemonBeanGenderSet(_str,Gender.NO_GENDER.getGenderName());
    }

    private static void genderSetPl(Struct _str) {
        callAddPokemonBeanGenderSet(_str,Gender.NO_GENDER.getGenderName());
    }

    private static Struct chooseName(String _name, PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _str) {
        Struct selPk_ = transitSimu(_pk, _all, _mapping, new EditTrainerPokemonBeanChooseName(), _str);
        callSelectPokemonBeanTypedNameSet(selPk_, _name);
        Struct afSel_ = transitSimu(_pk, _all, _mapping, new SelectPokemonBeanSearch(), selPk_);
        assertSame(afSel_,_str);
        return afSel_;
    }

    private static Struct chooseAbility(String _ability, PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _str) {
        Struct selAb_ = transitSimu(_pk, _all, _mapping, new EditTrainerPokemonBeanChooseAbility(), _str);
        callSelectAbilityBeanTypedAbilitySet(selAb_, _ability);
        Struct afSel_ = transitSimu(_pk, _all, _mapping, new SelectAbilityBeanSearch(), selAb_);
        assertSame(afSel_,_str);
        return afSel_;
    }

    /*protected static Struct pkTrainerSelectPkNameOne(String _name) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        Struct selPk_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseName(), editPkTrainer_);
        callSelectPokemonBeanTypedNameSet(selPk_,_name);
        return transitSimu(pk_,all_,mapping_,new SelectPokemonBeanSearch(),selPk_);
    }*/
    protected static Struct pkTrainerSelectPkCancel() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        Struct selPk_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseName(), editPkTrainer_);
        return transitSimu(pk_,all_,mapping_,new SelectPokemonBeanCancel(),selPk_);
    }

    protected static Struct pkTrainerSelectPkName(String _name) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        Struct selPk_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseName(), editPkTrainer_);
        callSelectPokemonBeanTypedNameSet(selPk_,_name);
        return transitSimu(pk_,all_,mapping_,new SelectPokemonBeanSearch(),selPk_);
    }
    protected static Struct pkTrainerSelectPkHasEvo(String _name) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        Struct selPk_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseName(), editPkTrainer_);
        callSelectPokemonBeanHasEvoSet(selPk_,_name);
        return transitSimu(pk_,all_,mapping_,new SelectPokemonBeanSearch(),selPk_);
    }
    protected static Struct pkTrainerSelectPkIsEvo(String _name) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        Struct selPk_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseName(), editPkTrainer_);
        callSelectPokemonBeanIsEvoSet(selPk_,_name);
        return transitSimu(pk_,all_,mapping_,new SelectPokemonBeanSearch(),selPk_);
    }
    protected static Struct pkTrainerSelectPkIsLeg(String _name) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        Struct selPk_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseName(), editPkTrainer_);
        callSelectPokemonBeanIsLegSet(selPk_,_name);
        return transitSimu(pk_,all_,mapping_,new SelectPokemonBeanSearch(),selPk_);
    }
    protected static Struct pkTrainerSelectPkRow(int _row) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        Struct selPk_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseName(), editPkTrainer_);
        callSelectPokemonBeanTypedNameSet(selPk_,"");
        Struct rSe_ = transitSimu(pk_, all_, mapping_, new SelectPokemonBeanSearch(), selPk_);
        return transitSimu(pk_,all_,mapping_,new SelectPokemonBeanClickLink(),rSe_,_row);
    }
    protected static Struct pkTrainerSelectPkType(String _type, boolean _wholeWord) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        Struct selPk_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseName(), editPkTrainer_);
        callSelectPokemonBeanTypedTypeSet(selPk_,_type);
        callSelectPokemonBeanWholeWordSet(selPk_,_wholeWord);
        return transitSimu(pk_,all_,mapping_,new SelectPokemonBeanSearch(),selPk_);
    }
    protected static Struct pkTrainerSelectPk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        return goToSelectPk(pk_, all_, mapping_, editPkTrainer_);
    }
    protected static Struct pkTrainerSelectPkAllyInfo(boolean _ally) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        callEditTrainerPokemonBeanAllyPkSet(editPkTrainer_, _ally);
        return editPkTrainer_;
    }
    protected static Struct pkTrainerSelectAbCancel() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        Struct selPk_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseAbility(), editPkTrainer_);
        return transitSimu(pk_,all_,mapping_,new SelectAbilityBeanCancel(),selPk_);
    }
    protected static Struct pkTrainerSelectAb(int _row) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        Struct selAb_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseAbility(), editPkTrainer_);
        callSelectAbilityBeanTypedAbilitySet(selAb_,"");
        Struct rSe_ = transitSimu(pk_,all_,mapping_,new SelectAbilityBeanSearch(),selAb_);
        return transitSimu(pk_,all_,mapping_,new SelectAbilityBeanClickAbility(),rSe_,_row);
    }
    protected static Struct pkTrainerSelectAb(String _name) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        Struct selAb_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseAbility(), editPkTrainer_);
        callSelectAbilityBeanTypedAbilitySet(selAb_,_name);
        return transitSimu(pk_,all_,mapping_,new SelectAbilityBeanSearch(),selAb_);
    }
    protected static Struct pkTrainerSelectAb() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        return goToSelectAb(pk_, all_, mapping_, editPkTrainer_);
    }
    protected static Struct pkTrainerSelectItCancel() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        Struct selPk_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseItem(), editPkTrainer_);
        Struct rSe_ = transitSimu(pk_,all_,mapping_,new SelectItemBeanCancelItem(),selPk_);
        Struct twice_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseItem(), rSe_);
        callSelectItemBeanTypedNameSet(twice_,I_BALL_TR);
        Struct againEditPk_ = transitSimu(pk_,all_,mapping_,new SelectItemBeanSearch(),twice_);
        Struct nextIt_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseItem(), againEditPk_);
        return transitSimu(pk_,all_,mapping_,new SelectItemBeanCancel(),nextIt_);
    }
    protected static Struct pkTrainerSelectItCancelRem() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        Struct selPk_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseItem(), editPkTrainer_);
        callSelectItemBeanTypedNameSet(selPk_,"");
        Struct rSe_ = transitSimu(pk_,all_,mapping_,new SelectItemBeanSearch(),selPk_);
        Struct againEditPk_ = transitSimu(pk_, all_, mapping_, new SelectItemBeanClickLink(), rSe_, 0);
        Struct nextIt_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseItem(), againEditPk_);
        return transitSimu(pk_,all_,mapping_,new SelectItemBeanCancelItem(),nextIt_);
    }
    protected static Struct pkTrainerSelectItName(int _row) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        Struct selAb_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseItem(), editPkTrainer_);
        callSelectItemBeanTypedNameSet(selAb_,"");
        Struct rSe_ = transitSimu(pk_,all_,mapping_,new SelectItemBeanSearch(),selAb_);
        return transitSimu(pk_,all_,mapping_,new SelectItemBeanClickLink(),rSe_,_row);
    }
    protected static Struct pkTrainerSelectItName(String _name) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        return chooseItemPkTrainer(_name, pk_, all_, mapping_, editPkTrainer_);
    }

    private static Struct chooseItemPkTrainer(String _name, PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _editPkTrainer) {
        Struct selIt_ = transitSimu(_pk, _all, _mapping, new EditTrainerPokemonBeanChooseItem(), _editPkTrainer);
        return chooseItemGene(_name, _pk, _all, _mapping, selIt_);
    }

    private static Struct chooseItemPkPlayer(String _name, PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _editPkTrainer) {
        Struct selIt_ = transitSimu(_pk, _all, _mapping, new EditPokemonBeanChooseItem(), _editPkTrainer);
        return chooseItemGene(_name, _pk, _all, _mapping, selIt_);
    }

    private static Struct chooseItemGene(String _name, PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _selIt) {
        callSelectItemBeanTypedNameSet(_selIt, _name);
        return transitSimu(_pk, _all, _mapping, new SelectItemBeanSearch(), _selIt);
    }
    protected static Struct pkTrainerSelectItPrice() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        Struct selAb_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseItem(), editPkTrainer_);
        callSelectItemBeanTypedPriceSet(selAb_,"");
        return transitSimu(pk_,all_,mapping_,new SelectItemBeanSearch(),selAb_);
    }
    protected static Struct pkTrainerSelectItCl(String _name) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        Struct selAb_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseItem(), editPkTrainer_);
        callSelectItemBeanTypedClassSet(selAb_,_name);
        return transitSimu(pk_,all_,mapping_,new SelectItemBeanSearch(),selAb_);
    }
    protected static Struct pkTrainerSelectItName() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        return goToSelectIt(pk_, all_, mapping_, editPkTrainer_);
    }

    protected static Struct pkTrainerSetMovesName(String _name) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        Struct selPk_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanAddMoves(), editPkTrainer_);
        callEditPokemonMovesBeanTypedNameSet(selPk_,_name);
        return transitSimu(pk_,all_,mapping_,new EditPokemonMovesBeanSearch(),selPk_);
    }

    protected static Struct pkTrainerSetMovesCat(String _name) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        Struct selPk_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanAddMoves(), editPkTrainer_);
        callEditPokemonMovesBeanCategorySet(selPk_,_name);
        return transitSimu(pk_,all_,mapping_,new EditPokemonMovesBeanSearch(),selPk_);
    }
    protected static Struct pkTrainerSetMovesCancel() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        Struct selPk_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanAddMoves(), editPkTrainer_);
        return transitSimu(pk_,all_,mapping_,new EditPokemonMovesBeanCancel(),selPk_);
    }
    protected static Struct pkTrainerSetMovesRemove() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        Struct re_ = addMoveTrainer(M_POK_01_TR, 0, pk_, all_, mapping_, editPkTrainer_);
        callSelectLineMoveSelectedSet(elt(callEditTrainerPokemonBeanMovesGet(re_),0),true);
        return transitSimu(pk_,all_,mapping_,new EditTrainerPokemonBeanDeleteMoves(),re_);
    }
    protected static Struct addPkTrainerChangeMoves(boolean _ally) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        Struct re_ = addMoveTrainer(M_POK_01_TR, 0, pk_, all_, mapping_, editPkTrainer_);
        callSelectLineMoveSelectedSet(elt(callEditTrainerPokemonBeanMovesGet(re_),0),true);
        Struct afterDel_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanDeleteMoves(), re_);
        callEditTrainerPokemonBeanAllyPkSet(afterDel_,_ally);
        return transitSimu(pk_, all_, mapping_,new EditTrainerPokemonBeanValidateTrainerPk(),afterDel_);
    }
    protected static Struct addPkTrainerChangeItem(boolean _ally) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        assertSame(editPkTrainer_, chooseItemPkTrainer(I_BALL_TR,pk_,all_,mapping_,editPkTrainer_));
        callEditTrainerPokemonBeanAllyPkSet(editPkTrainer_, _ally);
        return transitSimu(pk_, all_, mapping_,new EditTrainerPokemonBeanValidateTrainerPk(),editPkTrainer_);
    }
    protected static Struct pkTrainerSetMovesNameAdd(String _name, int _row) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        return addMoveTrainer(_name, _row, pk_, all_, mapping_, editPkTrainer_);
    }

    private static Struct addMoveTrainer(String _name, int _row, PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _editPkTrainer) {
        Struct editMoves_ = transitSimu(_pk, _all, _mapping, new EditTrainerPokemonBeanAddMoves(), _editPkTrainer);
        return addMoveGene(_name, _row, _pk, _all, _mapping, editMoves_);
    }

    private static Struct addMovePlayer(String _name, int _row, PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _editPkPlayer) {
        Struct editMoves_ = transitSimu(_pk, _all, _mapping, new EditPokemonBeanAddMoves(), _editPkPlayer);
        callEditPokemonMovesBeanAvailableMovesOnlySet(editMoves_,false);
        return addMoveGene(_name, _row, _pk, _all, _mapping, editMoves_);
    }

    private static Struct searchMovePlayer(String _name, PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _editPkPlayer, boolean _flag) {
        Struct editMoves_ = transitSimu(_pk, _all, _mapping, new EditPokemonBeanAddMoves(), _editPkPlayer);
        callEditPokemonMovesBeanAvailableMovesOnlySet(editMoves_, _flag);
        callEditPokemonMovesBeanTypedNameSet(editMoves_, _name);
        return transitSimu(_pk, _all, _mapping, new EditPokemonMovesBeanSearch(), editMoves_);
    }

    private static Struct addMoveGene(String _name, int _row, PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _editMoves) {
        callEditPokemonMovesBeanTypedNameSet(_editMoves, _name);
        Struct foundMoves_ = transitSimu(_pk, _all, _mapping, new EditPokemonMovesBeanSearch(), _editMoves);
        callSelectLineMoveSelectedSet(elt(callEditPokemonMovesBeanMovesGet(foundMoves_), _row),true);
        return transitSimu(_pk, _all, _mapping, new EditPokemonMovesBeanAddMoves(), foundMoves_);
    }
    protected static Struct pkTrainerSetMovesType(String _type, boolean _wholeWord) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        Struct selPk_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanAddMoves(), editPkTrainer_);
        callEditPokemonMovesBeanTypedTypeSet(selPk_,_type);
        callEditPokemonMovesBeanWholeWordSet(selPk_,_wholeWord);
        return transitSimu(pk_,all_,mapping_,new EditPokemonMovesBeanSearch(),selPk_);
    }
    protected static Struct pkTrainerSetMoves() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        return goToSetMovesTrainer(pk_, all_, mapping_, editPkTrainer_);
    }
    protected static Struct pkTrainer(boolean _select) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        Struct edit_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        callSelectLineMoveSelectedSet(elt(callEditTrainerPokemonBeanMovesGet(edit_),0),_select);
        return edit_;
    }
    protected static String editNoPlayerPk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        callSimulationBeanSelectedPkSet(simu_,-1);
        return navigateData(new SimulationBeanSelectPk(),simu_);
    }
    protected static Struct pkPlayerRemove() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        pkTrainerSelectPkPlayerNameCycle(P_POK_00_TR, A_SIM_1, pk_, all_, mapping_, simu_, 4);
        return editPkPlayer(pk_, all_, mapping_, simu_, P_POK_01_TR, A_SIM_2_TR, 0, 5, TeamCrud.REMOVE);
    }
    protected static String editNoSelectedPlayerPk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        Struct added_ = pkTrainerSelectPkPlayerNameCycle(P_POK_00_TR, A_SIM_1, pk_, all_, mapping_, simu_, 4);
        callSimulationBeanSelectedActionSet(added_, TeamCrud.NOTHING.getTeamCrudString());
        callSimulationBeanSelectedPkSet(added_,-1);
        return navigateData(new SimulationBeanSelectPk(),added_);
    }
    protected static Struct editEditSelectedPlayerPk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        return editPkPlayer(pk_, all_, mapping_, simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
    }
    protected static Struct editEditSelectedPlayerPkForm(boolean _heal, Rate _exp, Rate _hp) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        Struct editing_ = editPkPlayer(pk_, all_, mapping_, simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        callEditPokemonBeanHealSet(editing_, _heal);
        callEditPokemonBeanBallSet(editing_,I_BALL);
        callEditPokemonBeanExperienceSet(editing_, _exp);
        callEditPokemonBeanHappinessSet(editing_,1);
        callEditPokemonBeanRemainingHpSet(editing_, _hp);
        callEvLineEvSet(second(elt(callEditPokemonBeanEvGet(editing_),2)),33);
        return transitSimu(pk_,all_,mapping_,new EditPokemonBeanEdit(),editing_);
    }
    protected static Struct editEditSelectedPlayerPkFormNoMove() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        Struct editing_ = editPkPlayer(pk_, all_, mapping_, simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        callSelectLineMoveSelectedSet(elt(callEditPokemonBeanMovesGet(editing_),0),true);
        transitSimu(pk_,all_,mapping_,new EditPokemonBeanDeleteMoves(),editing_);
        return transitSimu(pk_,all_,mapping_,new EditPokemonBeanEdit(),editing_);
    }
    protected static Struct editEditSelectedPlayerPkFormCancel() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        Struct editing_ = editPkPlayer(pk_, all_, mapping_, simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        callSelectLineMoveSelectedSet(elt(callEditPokemonBeanMovesGet(editing_),0),true);
        transitSimu(pk_,all_,mapping_,new EditPokemonBeanDeleteMoves(),editing_);
        return transitSimu(pk_,all_,mapping_,new EditPokemonBeanCancel(),editing_);
    }
    protected static Struct editEditSelectedPlayerPkHeal(boolean _heal) {
        Struct h_ = editEditSelectedPlayerPk();
        callEditPokemonBeanHealSet(h_,_heal);
        return h_;
    }
    protected static Struct editEditSelectedPlayerPkItem() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        Struct edit_ = editPkPlayer(pk_, all_, mapping_, simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        assertSame(edit_,chooseItemPkPlayer(I_BALL_TR,pk_, all_, mapping_,edit_));
        return edit_;
    }
    protected static Struct editEditSelectedPlayerPkItemCancelItem() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        Struct edit_ = editPkPlayer(pk_, all_, mapping_, simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        Struct chosen_ = chooseItemPkPlayer(I_BALL_TR, pk_, all_, mapping_, edit_);
        Struct redo_ = transitSimu(pk_, all_, mapping_, new EditPokemonBeanChooseItem(), chosen_);
        return transitSimu(pk_, all_, mapping_, new SelectItemBeanCancelItem(), redo_);
    }
    protected static Struct editEditSelectedPlayerPkItemCancel() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        Struct edit_ = editPkPlayer(pk_, all_, mapping_, simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        Struct first_ = transitSimu(pk_, all_, mapping_, new EditPokemonBeanChooseItem(), edit_);
        Struct back_ = transitSimu(pk_, all_, mapping_, new SelectItemBeanCancel(), first_);
        return chooseItemPkPlayer(I_BALL_TR,pk_, all_, mapping_,back_);
    }
    protected static Struct editEditSelectedPlayerPkItemPart(int _row) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        Struct edit_ = editPkPlayer(pk_, all_, mapping_, simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        Struct chosen_ = chooseItemPkPlayer("", pk_, all_, mapping_, edit_);
        return transitSimu(pk_, all_, mapping_, new SelectItemBeanClickLink(), chosen_,_row);
    }
    protected static Struct formEditSelectedPlayerPk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        Struct added_ = pkTrainerSelectPkPlayerNameCycle(P_POK_00_TR, A_SIM_1, pk_, all_, mapping_, simu_, 4);
        callSimulationBeanSelectedActionSet(added_, TeamCrud.EDIT.getTeamCrudString());
        callSimulationBeanSelectedPkSet(added_,0);
        return added_;
    }
    protected static Struct editEditSelectedPlayerPkAddMove() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        Struct editing_ = editPkPlayer(pk_, all_, mapping_, simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        addMovePlayer(M_POK_01_TR,0,pk_,all_,mapping_,editing_);
        return transitSimu(pk_,all_,mapping_,new EditPokemonBeanEdit(),editing_);
    }
    protected static Struct editEditSelectedPlayerPkListMoves() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        Struct editing_ = editPkPlayer(pk_, all_, mapping_, simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        return transitSimu(pk_,all_,mapping_, new EditPokemonBeanAddMoves(),editing_);
    }
    protected static Struct editEditSelectedPlayerPkListMoves(String _name, boolean _flag) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        Struct editing_ = editPkPlayer(pk_, all_, mapping_, simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        return searchMovePlayer(_name,pk_,all_,mapping_,editing_,_flag);
    }
    protected static Struct editEditSelectedPlayerPkListMovesCancel() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        Struct editing_ = editPkPlayer(pk_, all_, mapping_, simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        Struct se_ = searchMovePlayer(M_POK_04_TR, pk_, all_, mapping_, editing_, false);
        callSelectLineMoveSelectedSet(elt(callEditPokemonMovesBeanMovesGet(se_),0),true);
        return transitSimu(pk_,all_,mapping_,new EditPokemonMovesBeanCancel(),se_);
    }

    private static Struct editPkPlayer(PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu, String _name, String _ab, int _index, int _level, TeamCrud _mode) {
        Struct added_ = pkTrainerSelectPkPlayerNameCycle(_name, _ab, _pk, _all, _mapping, _simu, _level);
        callSimulationBeanSelectedActionSet(added_, _mode.getTeamCrudString());
        callSimulationBeanSelectedPkSet(added_, _index);
        return transitSimu(_pk, _all, _mapping, new SimulationBeanSelectPk(), added_);
    }

    protected static Struct pkPlayerSetMovesRemove() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        Struct editing_ = editPkPlayer(pk_, all_, mapping_, simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        Struct re_ = addMovePlayer(M_POK_01_TR, 0, pk_, all_, mapping_, editing_);
        callSelectLineMoveSelectedSet(elt(callEditPokemonBeanMovesGet(re_),0),true);
        return transitSimu(pk_,all_,mapping_,new EditPokemonBeanDeleteMoves(),re_);
    }
    protected static String editForgetSelectedPlayerPk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        Struct added_ = pkTrainerSelectPkPlayerNameCycle(P_POK_00_TR, A_SIM_1, pk_, all_, mapping_, simu_, 4);
        callSimulationBeanSelectedActionSet(added_, TeamCrud.NOTHING.getTeamCrudString());
        callSimulationBeanSelectedPkSet(added_,0);
        return navigateData(new SimulationBeanSelectPk(),added_);
    }
    protected static Struct addPkPlayerChangeMoves() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        Struct editing_ = editPkPlayer(pk_, all_, mapping_, simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        assertSame(editing_,chooseItemPkPlayer(I_BALL_TR,pk_, all_, mapping_,editing_));
        Struct re_ = addMovePlayer(M_POK_01_TR, 0, pk_, all_, mapping_, editing_);
        callSelectLineMoveSelectedSet(elt(callEditPokemonBeanMovesGet(re_),0),true);
        Struct afterDel_ = transitSimu(pk_, all_, mapping_, new EditPokemonBeanDeleteMoves(), re_);
        return transitSimu(pk_, all_, mapping_,new EditPokemonBeanEdit(),afterDel_);
    }
//    protected static Struct editEditSelectedPlayerPkAddMove() {
//        PkData pk_ = pkDataByFacade(db());
//        StringMap<Struct> all_ = beanToSimu(pk_);
//        StringMap<String> mapping_ = mappingToSimu();
//        Struct simu_ = simu(pk_, all_, mapping_, 2);
//        Struct added_ = pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
//        callSimulationBeanSelectedFoeActionSet(added_, TeamCrud.EDIT.getTeamCrudString());
//        callSimulationBeanSelectedFoePkSet(added_,0);
//        Struct editing_ = transitSimu(pk_, all_, mapping_, new SimulationBeanSelectFoePk(), added_);
//        addMoveTrainer(M_POK_01_TR,0,pk_,all_,mapping_,editing_);
//        return transitSimu(pk_,all_,mapping_,new EditTrainerPokemonBeanValidateTrainerPk(),editing_);
//    }
    private static Struct pkTrainerSelectPkPlayerNameCycle(String _name, String _ability, PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu, int _level) {
        Struct addPk_ = goToAddPkPlayer(_pk, _all, _mapping, _simu);
        callAddPokemonBeanTypedNameSet(addPk_,_name);
        Struct afSearch_ = transitSimu(_pk, _all, _mapping, new AddPokemonBeanSearch(), addPk_);
        callAddPokemonBeanAbilitySet(afSearch_,_ability);
        callAddPokemonBeanLevelSet(afSearch_,_level);
        Struct afterAddEdit_ = transitSimu(_pk, _all, _mapping, new AddPokemonBeanAdd(), afSearch_);
        assertSame(afterAddEdit_,_simu);
        return afterAddEdit_;
    }
    protected static Struct pkPlayerSelectPk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        return goToAddPkPlayer(pk_, all_, mapping_, simu_);
    }
    protected static Struct pkPlayerSelectPkCancel() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        Struct addPk_ = goToAddPkPlayer(pk_, all_, mapping_, simu_);
        return transitSimu(pk_,all_,mapping_,new AddPokemonBeanCancel(),addPk_);
    }

    protected static Struct pkPlayerSelectPkNameAbility(String _name) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        return pkPlAb(_name, pk_, all_, mapping_, simu_);
    }

    protected static Struct pkPlayerSelectPkNameTwice() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        pkTrainerSelectPkPlayerNameCycle(P_POK_01_TR,A_SIM_1,pk_,all_,mapping_,simu_,40);
        return pkTrainerSelectPkPlayerNameCycle(P_POK_02_TR,A_SIM_2,pk_,all_,mapping_,simu_,39);
    }

    protected static Struct pkPlayerValidateEvosNoSelect() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        callSimulationBeanSelectedPkSet(simu_,-1);
        return transitSimu(pk_, all_, mapping_,new SimulationBeanDisplayEvolutions(),simu_);
    }

    protected static Struct pkPlayerValidateEvosSelect() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        callSimulationBeanSelectedPkSet(simu_,1);
        return transitSimu(pk_, all_, mapping_,new SimulationBeanDisplayEvolutions(),simu_);
    }

    protected static Struct pkPlayerValidateEvoValues() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        callSimulationBeanSelectedPkSet(simu_,1);
        transitSimu(pk_, all_, mapping_,new SimulationBeanDisplayEvolutions(),simu_);
        callSimulationBeanChosenEvoSet(simu_,P_POK_03);
        callSimulationBeanLevelEvoSet(simu_,41);
        return simu_;
    }

    protected static Struct pkPlayerValidateEvoValidate() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        callSimulationBeanSelectedPkSet(simu_,1);
        transitSimu(pk_, all_, mapping_,new SimulationBeanDisplayEvolutions(),simu_);
        callSimulationBeanChosenEvoSet(simu_,P_POK_03);
        callSimulationBeanLevelEvoSet(simu_,39);
        return transitSimu(pk_, all_, mapping_,new SimulationBeanValidateEvo(),simu_);
    }

    protected static Struct pkPlayerEvoThenFighters() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        return validEvos(pk_, all_, mapping_,simu_);
    }

    protected static Struct pkPlayerEvoFightersImmediateValid() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        validEvos(pk_, all_, mapping_,simu_);
        callSimulationBeanSelectedPkSet(simu_,-1);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanValidateFrontFighter(),simu_);
    }

    protected static Struct pkPlayerEvoFightersFormValid() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        validEvos(pk_, all_, mapping_,simu_);
        return changeFighterPosition(pk_, all_, mapping_, simu_, 0, "0", "0");
    }

    protected static Struct pkPlayerEvoFightersWithoutFronts() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        validEvos(pk_, all_, mapping_,simu_);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanValidateFrontFighters(), simu_);
    }

    protected static Struct pkPlayerEvoFightersSufficientFronts() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        validEvos(pk_, all_, mapping_,simu_);
        return fighterPositions(pk_, all_, mapping_, simu_);
    }

    protected static Struct pkPlayerEvoFightersSufficientFrontsFormMove(int _index) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        validEvos(pk_, all_, mapping_,simu_);
        fighterPositions(pk_, all_, mapping_, simu_);
        callSimulationBeanSelectedPkSet(simu_,_index);
        beforeDisplaying(simu_);
        return simu_;
    }

    protected static Struct pkPlayerEvoFightersSufficientFrontsFormMoveValidateMovesQuick(int _index, String _ab) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        validEvos(pk_, all_, mapping_,simu_);
        fighterPositions(pk_, all_, mapping_, simu_);
        callSimulationBeanSelectedPkSet(simu_,_index);
        beforeDisplaying(simu_);
        return movesAbilities(_ab, pk_, all_, mapping_, simu_);
    }

    protected static Struct pkPlayerEvoFightersSufficientFrontsFormMoveValidateMovesQuickTwice(int _index, String _ab) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        validEvos(pk_, all_, mapping_,simu_);
        fighterPositions(pk_, all_, mapping_, simu_);
        callSimulationBeanSelectedPkSet(simu_,_index);
        beforeDisplaying(simu_);
        movesAbilities(_ab, pk_, all_, mapping_, simu_);
        return movesAbilities("", pk_, all_, mapping_, simu_);
    }

    protected static Struct pkPlayerEvoFightersSufficientFrontsFormMoveValidateMovesAllFightersOk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        validEvos(pk_, all_, mapping_,simu_);
        fighterPositions(pk_, all_, mapping_, simu_);
        return movesSet(pk_, all_, mapping_, simu_);
    }

    protected static Struct pkPlayerEvoFighterChoice(int _index, int _round) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        validEvos(pk_, all_, mapping_,simu_);
        fighterPositions(pk_, all_, mapping_, simu_);
        movesSet(pk_, all_, mapping_, simu_);
        callSimulationBeanSelectedPkSet(simu_,_index);
        callSimulationBeanSelectedRoundSet(simu_,Long.toString(_round));
        beforeDisplaying(simu_);
        return simu_;
    }

    protected static Struct pkPlayerEvoFighterChoiceAfter(int _index, int _round, boolean _allyChoice, int _move, int _target) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        validEvos(pk_, all_, mapping_,simu_);
        fighterPositions(pk_, all_, mapping_, simu_);
        movesSet(pk_, all_, mapping_, simu_);
        return moveChoice(_index, _round, _allyChoice, _move, _target, pk_, all_, mapping_, simu_);
    }

    protected static Struct pkPlayerEvoFighterSimulate() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        validEvos(pk_, all_, mapping_,simu_);
        fighterPositions(pk_, all_, mapping_, simu_);
        movesSet(pk_, all_, mapping_, simu_);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanSimulateFight(), moveChoices(pk_, all_, mapping_, simu_));
    }

    protected static Struct pkPlayerEvoFighterSimulateStMove() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        validEvos(pk_, all_, mapping_,simu_);
        fighterPositions(pk_, all_, mapping_, simu_);
        movesSet(pk_, all_, mapping_, simu_);
        moveChoices(pk_, all_, mapping_, simu_);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanCancelMovesEvos(), simu_);
    }

    protected static Struct pkPlayerEvoFighterSimulateStMoveCancel() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        validEvos(pk_, all_, mapping_,simu_);
        fighterPositions(pk_, all_, mapping_, simu_);
        movesSet(pk_, all_, mapping_, simu_);
        moveChoices(pk_, all_, mapping_, simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanCancelMovesEvos(), simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanCancelMovesSets(), simu_);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanCancelFrontFighters(), simu_);
    }

    protected static Struct pkPlayerEvoFighterSimulateStMoveCancel2() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        validEvos(pk_, all_, mapping_,simu_);
        fighterPositions(pk_, all_, mapping_, simu_);
        movesSet(pk_, all_, mapping_, simu_);
        moveChoices(pk_, all_, mapping_, simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanCancelMovesEvos(), simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanCancelMovesSets(), simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanCancelFrontFighters(), simu_);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanCancelEvolutions(), simu_);
    }

    protected static Struct pkPlayerFighterSimulate() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSampleSkip(pk_, all_, mapping_, simu_);
        return oneFight(pk_, all_, mapping_, simu_);
    }

    protected static Struct pkPlayerFighterSimulateComment() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSampleSkip(pk_, all_, mapping_, simu_);
        oneFight(pk_, all_, mapping_, simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanDisplayComments(),simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanHideComments(),simu_);
        return callSimulationBeanCommentsGet(simu_);
    }

    protected static Struct pkPlayerFighterSimulateAfterFight() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSampleSkip(pk_, all_, mapping_, simu_);
        oneFight(pk_, all_, mapping_, simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanNextFight(),simu_);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanSelectPkAfterFight(), simu_);
    }

    protected static Struct pkPlayerFighterSimulateAfterFightCancel() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSampleSkip(pk_, all_, mapping_, simu_);
        oneFight(pk_, all_, mapping_, simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanNextFight(),simu_);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanChangeFightWhileEnd(), simu_);
    }

    protected static Struct pkPlayerFighterSimulateAfterFightCancel2() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSampleSkip(pk_, all_, mapping_, simu_);
        oneFight(pk_, all_, mapping_, simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanNextFight(),simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanChangeFightWhileEnd(), simu_);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanChangeFight(), simu_);
    }

    protected static Struct pkPlayerFighterSimulateAfterFightCancel3() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSampleSkip(pk_, all_, mapping_, simu_);
        oneFight(pk_, all_, mapping_, simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanNextFight(),simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanChangeFightWhileEnd(), simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanChangeFight(), simu_);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanCancelMovesEvos(), simu_);
    }

    protected static Struct pkPlayerFighterSimulateAfterFightCancel4() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSampleSkip(pk_, all_, mapping_, simu_);
        oneFight(pk_, all_, mapping_, simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanNextFight(),simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanChangeFightWhileEnd(), simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanChangeFight(), simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanCancelMovesEvos(), simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanCancelFrontFighters(), simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanCancelTeam(), simu_);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanCancelDiffChoice(), simu_);
    }

    protected static Struct pkPlayerFighterSimulateAfterFightOne() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSampleSkip(pk_, all_, mapping_, simu_);
        oneFight(pk_, all_, mapping_, simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanNextFight(),simu_);
        callSimulationBeanSelectedPkSet(simu_,0);
        beforeDisplaying(simu_);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanSelectPkAfterFight(), simu_);
    }

    protected static Struct pkPlayerFighterSimulateAfterFightOneValidate() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSampleSkip(pk_, all_, mapping_, simu_);
        oneFight(pk_, all_, mapping_, simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanNextFight(),simu_);
        callSimulationBeanSelectedPkSet(simu_,0);
        beforeDisplaying(simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanSelectPkAfterFight(), simu_);
        callSimulationBeanEvolutionAfterFightSet(simu_,P_POK_05);
        transitSimu(pk_, all_, mapping_, new SimulationBeanValidateEvolutionAfterFight(), simu_);
        callSimulationBeanAbilityAfterFightSet(simu_,A_SIM_2);
        transitSimu(pk_, all_, mapping_, new SimulationBeanValidateMovesAbilityAfterFight(), simu_);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanValidateMovesAfterFight(), simu_);
    }

    protected static Struct pkPlayerFighterSimulateAfterFightCancelOne() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSampleSkip(pk_, all_, mapping_, simu_);
        oneFight(pk_, all_, mapping_, simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanNextFight(),simu_);
        callSimulationBeanSelectedPkSet(simu_,0);
        beforeDisplaying(simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanSelectPkAfterFight(), simu_);
        callSimulationBeanEvolutionAfterFightSet(simu_,P_POK_05);
        transitSimu(pk_, all_, mapping_, new SimulationBeanValidateEvolutionAfterFight(), simu_);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanCancelEvolutionsAfterFight(), simu_);
    }

    private static Struct oneFight(PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu) {
        fighterPositions(_pk, _all, _mapping, _simu);
        return transitSimu(_pk, _all, _mapping, new SimulationBeanSimulateFight(), moveChoices(_pk, _all, _mapping, _simu));
    }

    protected static Struct pkPlayerFighterSimulateOneFight() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 1);
        foeTeamSample(pk_, all_, mapping_, simu_);
        playerTeamSampleSkip(pk_, all_, mapping_, simu_);
        fighterPositions(pk_, all_, mapping_, simu_);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanSimulateFight(), moveChoices(pk_, all_, mapping_, simu_));
    }

    protected static Struct pkPlayerFighterSkipEvosStateBadNbCount() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        Struct editing_ = editPkPlayer(pk_, all_, mapping_, simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        assertSame(editing_,chooseItemPkPlayer(I_BALL_TR,pk_, all_, mapping_,editing_));
        Struct re_ = addMovePlayer(M_POK_01_TR, 0, pk_, all_, mapping_, editing_);
        callSelectLineMoveSelectedSet(elt(callEditPokemonBeanMovesGet(re_),0),true);
        Struct afterDel_ = transitSimu(pk_, all_, mapping_, new EditPokemonBeanDeleteMoves(), re_);
        transitSimu(pk_, all_, mapping_,new EditPokemonBeanEdit(),afterDel_);
        pkTrainerSelectPkPlayerNameCycle(P_POK_04_TR,A_SIM_1, pk_, all_, mapping_, simu_,41);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanValidateFoeChoiceSkipEvolutions(), simu_);
    }

    protected static Struct pkPlayerFighterSkipEvosStateEmpty() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanValidateFoeChoiceSkipEvolutions(), simu_);
    }

    protected static Struct pkPlayerEvoFighterSimulateKo() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        validEvos(pk_, all_, mapping_,simu_);
        fighterPositions(pk_, all_, mapping_, simu_);
        movesSet(pk_, all_, mapping_, simu_);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanSimulateFight(), moveChoicesKo(pk_, all_, mapping_, simu_));
    }

    protected static Struct pkPlayerEvoFighterSimulateKos() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSampleInv(pk_, all_, mapping_, simu_);
        playerTeamSampleInv(pk_, all_, mapping_, simu_);
        transitSimu(pk_, all_, mapping_,new SimulationBeanValidateEvolutions(),simu_);
        fighterPositions(pk_, all_, mapping_, simu_);
        movesSet(pk_, all_, mapping_, simu_);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanSimulateFight(), moveChoices(pk_, all_, mapping_, simu_));
    }

    private static Struct moveChoices(PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu) {
        moveChoice(0,0,0,0,_pk,_all,_mapping,_simu);
        moveChoice(1,0,0,1,_pk,_all,_mapping,_simu);
        moveChoice(0,1,0,0,_pk,_all,_mapping,_simu);
        return moveChoice(1,1,0,1,_pk,_all,_mapping,_simu);
    }

    private static Struct moveChoicesKo(PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu) {
        moveChoice(0,0,0,0,_pk,_all,_mapping,_simu);
        moveChoice(1,0,0,1,_pk,_all,_mapping,_simu);
        moveChoice(0,0,0,0,_pk,_all,_mapping,_simu);
        return moveChoice(1,0,0,1,_pk,_all,_mapping,_simu);
    }

    private static Struct moveChoice(int _index, int _round, int _move, int _target, PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu) {
        return moveChoice(_index,_round,false,_move,_target,_pk,_all,_mapping,_simu);
    }
    private static Struct moveChoice(int _index, int _round, boolean _allyChoice, int _move, int _target, PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu) {
        callSimulationBeanSelectedPkSet(_simu, _index);
        callSimulationBeanSelectedRoundSet(_simu,Long.toString(_round));
        beforeDisplaying(_simu);
        callSimulationBeanAllyChoiceSet(_simu, _allyChoice);
        callSimulationBeanSelectedMoveSet(_simu, _move);
        callSimulationBeanTargetSet(_simu, Long.toString(_target));
        return transitSimu(_pk, _all, _mapping, new SimulationBeanValidateMovesChoice(), _simu);
    }

    protected static Struct pkPlayerEvoFightersSufficientFrontsFormMoveValidateMovesAllFightersKo() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        validEvos(pk_, all_, mapping_,simu_);
        fighterPositions(pk_, all_, mapping_, simu_);
        callSimulationBeanSelectedPkSet(simu_, 1);
        beforeDisplaying(simu_);
        callSimulationBeanCurrentAbilitySet(simu_, A_SIM_1);
        callSelectLineMoveSelectedSet(elt(callSimulationBeanKeptMovesGet(simu_),0),true);
        callSelectLineMoveSelectedSet(elt(callSimulationBeanKeptMovesGet(simu_),1),true);
        callSelectLineMoveSelectedSet(elt(callSimulationBeanKeptMovesGet(simu_),2),true);
        transitSimu(pk_, all_, mapping_, new SimulationBeanValidateMoves(), simu_);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanValidateMovesSets(), simu_);
    }

    protected static Struct pkPlayerEvoFightersSufficientFrontsFormMoveValidateMovesAllFightersCancel() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        validEvos(pk_, all_, mapping_,simu_);
        fighterPositions(pk_, all_, mapping_, simu_);
        callSimulationBeanSelectedPkSet(simu_, 1);
        beforeDisplaying(simu_);
        callSimulationBeanCurrentAbilitySet(simu_, A_SIM_1);
        callSelectLineMoveSelectedSet(elt(callSimulationBeanKeptMovesGet(simu_),0),true);
        callSelectLineMoveSelectedSet(elt(callSimulationBeanKeptMovesGet(simu_),1),true);
        callSelectLineMoveSelectedSet(elt(callSimulationBeanKeptMovesGet(simu_),2),true);
        transitSimu(pk_, all_, mapping_, new SimulationBeanValidateMoves(), simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanCancelMoves(), simu_);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanValidateMovesSets(), simu_);
    }

    private static Struct movesSet(PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu) {
        withoutAbility(0, _pk, _all, _mapping, _simu);
        withAbility(1,A_SIM_1, _pk, _all, _mapping, _simu);
        withoutAbility(2, _pk, _all, _mapping, _simu);
        withAbility(3,A_SIM_1, _pk, _all, _mapping, _simu);
        return transitSimu(_pk, _all, _mapping, new SimulationBeanValidateMovesSets(), _simu);
    }

    private static Struct withAbility(int _index, String _ab, PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu) {
        callSimulationBeanSelectedPkSet(_simu, _index);
        beforeDisplaying(_simu);
        movesAbilities(_ab, _pk, _all, _mapping, _simu);
        movesAbilities("", _pk, _all, _mapping, _simu);
        return movesAbilities("", _pk, _all, _mapping, _simu);
    }

    private static Struct withoutAbility(int _index, PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu) {
        callSimulationBeanSelectedPkSet(_simu, _index);
        beforeDisplaying(_simu);
        movesAbilities("", _pk, _all, _mapping, _simu);
        movesAbilities("", _pk, _all, _mapping, _simu);
        return movesAbilities("", _pk, _all, _mapping, _simu);
    }

    private static Struct movesAbilities(String _ab, PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu) {
        callSimulationBeanCurrentAbilitySet(_simu, _ab);
        return transitSimu(_pk, _all, _mapping, new SimulationBeanValidateMoves(), _simu);
    }

    private static Struct fighterPositions(PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu) {
        changeFighterPosition(_pk, _all, _mapping, _simu,0,"0","0");
        changeFighterPosition(_pk, _all, _mapping, _simu,1,"0","1");
        changeFighterPosition(_pk, _all, _mapping, _simu,2,"0",Long.toString(Fighter.BACK));
        changeFighterPosition(_pk, _all, _mapping, _simu,3,"0",Long.toString(Fighter.BACK));
        changeFighterPosition(_pk, _all, _mapping, _simu,0,"1","0");
        changeFighterPosition(_pk, _all, _mapping, _simu,1,"1","1");
        changeFighterPosition(_pk, _all, _mapping, _simu,2,"1",Long.toString(Fighter.BACK));
        changeFighterPosition(_pk, _all, _mapping, _simu,3,"1",Long.toString(Fighter.BACK));
        return transitSimu(_pk, _all, _mapping, new SimulationBeanValidateFrontFighters(), _simu);
    }

    private static Struct changeFighterPosition(PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu, int _index, String _round, String _place) {
        callSimulationBeanSelectedPkSet(_simu, _index);
        callSimulationBeanSelectedRoundSet(_simu, _round);
        callSimulationBeanPlaceFightSet(_simu, _place);
        return transitSimu(_pk, _all, _mapping, new SimulationBeanValidateFrontFighter(),_simu);
    }

    private static Struct validEvos(PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu) {
        validEvo(_pk, _all, _mapping, _simu,1);
        validEvo(_pk, _all, _mapping, _simu,3);
        return transitSimu(_pk, _all, _mapping,new SimulationBeanValidateEvolutions(),_simu);
    }

    protected static Struct pkPlayerValidateEvoValidateThenCancel() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        callSimulationBeanSelectedPkSet(simu_,1);
        transitSimu(pk_, all_, mapping_,new SimulationBeanDisplayEvolutions(),simu_);
        callSimulationBeanChosenEvoSet(simu_,P_POK_03);
        callSimulationBeanLevelEvoSet(simu_,39);
        transitSimu(pk_, all_, mapping_,new SimulationBeanValidateEvo(),simu_);
        return transitSimu(pk_, all_, mapping_,new SimulationBeanCancelEvo(),simu_);
    }
    private static void validEvo(PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu, int _index) {
        callSimulationBeanSelectedPkSet(_simu,_index);
        transitSimu(_pk, _all, _mapping,new SimulationBeanDisplayEvolutions(),_simu);
        callSimulationBeanChosenEvoSet(_simu,P_POK_03);
        callSimulationBeanLevelEvoSet(_simu,41);
        transitSimu(_pk, _all, _mapping,new SimulationBeanValidateEvo(),_simu);
    }
//
//    protected static Struct pkPlayerValidateEvosSelect(int _index) {
//        PkData pk_ = pkDataByFacade(db());
//        StringMap<Struct> all_ = beanToSimu(pk_);
//        StringMap<String> mapping_ = mappingToSimu();
//        Struct simu_ = simu(pk_, all_, mapping_, 2);
//        foeTeamsSample(pk_, all_, mapping_, simu_);
//        playerTeamSample(pk_, all_, mapping_, simu_);
//        callSimulationBeanSelectedPkSet(simu_,-1);
//        transitSimu(pk_, all_, mapping_,new SimulationBeanDisplayEvolutions(),simu_);
//        callSimulationBeanSelectedPkSet(simu_, _index);
//        return transitSimu();
//    }

    protected static Struct pkPlayerValidateEvos() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        return playerTeamSample(pk_, all_, mapping_, simu_);
    }

    private static Struct playerTeamSample(PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu) {
        pkTrainerSelectPkPlayerNameCycle(P_POK_01_TR,A_SIM_1, _pk, _all, _mapping, _simu,41);
        pkTrainerSelectPkPlayerNameCycle(P_POK_02_TR,A_SIM_1, _pk, _all, _mapping, _simu,41);
        pkTrainerSelectPkPlayerNameCycle(P_POK_01_TR,A_SIM_1, _pk, _all, _mapping, _simu,41);
        pkTrainerSelectPkPlayerNameCycle(P_POK_02_TR,A_SIM_1, _pk, _all, _mapping, _simu,41);
        return transitSimu(_pk, _all, _mapping,new SimulationBeanValidateTeam(),_simu);
    }

    private static Struct playerTeamSampleSkip(PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu) {
        Struct editing_ = editPkPlayer(_pk, _all, _mapping, _simu, P_POK_04_TR, A_SIM_1, 0, 41, TeamCrud.EDIT);
        assertSame(editing_,chooseItemPkPlayer(I_BALL_TR,_pk, _all, _mapping,editing_));
        assertSame(_simu,transitSimu(_pk, _all, _mapping,new EditPokemonBeanEdit(),editing_));
        pkTrainerSelectPkPlayerNameCycle(P_POK_01_TR,A_SIM_1, _pk, _all, _mapping, _simu,41);
        pkTrainerSelectPkPlayerNameCycle(P_POK_02_TR,A_SIM_1, _pk, _all, _mapping, _simu,41);
        pkTrainerSelectPkPlayerNameCycle(P_POK_02_TR,A_SIM_1, _pk, _all, _mapping, _simu,41);
        return transitSimu(_pk, _all, _mapping,new SimulationBeanValidateFoeChoiceSkipEvolutions(),_simu);
    }

    private static Struct playerTeamSampleInv(PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu) {
        pkTrainerSelectPkPlayerNameCycle(P_POK_01_TR,A_SIM_1, _pk, _all, _mapping, _simu,4);
        pkTrainerSelectPkPlayerNameCycle(P_POK_02_TR,A_SIM_1, _pk, _all, _mapping, _simu,4);
        pkTrainerSelectPkPlayerNameCycle(P_POK_01_TR,A_SIM_1, _pk, _all, _mapping, _simu,4);
        pkTrainerSelectPkPlayerNameCycle(P_POK_02_TR,A_SIM_1, _pk, _all, _mapping, _simu,4);
        return transitSimu(_pk, _all, _mapping,new SimulationBeanValidateTeam(),_simu);
    }

    protected static Struct pkPlayerValidateEvosKo() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        return transitSimu(pk_,all_,mapping_,new SimulationBeanValidateTeam(),simu_);
    }
    private static Struct pkPlAb(String _name, PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu) {
        Struct addPk_ = goToAddPkPlayer(_pk, _all, _mapping, _simu);
        callAddPokemonBeanTypedNameSet(addPk_, _name);
        Struct afSearch_ = transitSimu(_pk, _all, _mapping, new AddPokemonBeanSearch(), addPk_);
        callAddPokemonBeanAbilitySet(afSearch_,A_SIM_2);
        callAddPokemonBeanLevelSet(afSearch_,40);
        genderSetPl(afSearch_);
        return afSearch_;
    }

    protected static Struct pkPlayerSelectPkNameAdded(String _name) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
//        Struct addPk_ = goToAddPkPlayer(pk_, all_, mapping_, simu_);
//        callAddPokemonBeanTypedNameSet(addPk_,_name);
//        Struct afSearch_ = transitSimu(pk_, all_, mapping_, new AddPokemonBeanSearch(), addPk_);
        return transitSimu(pk_, all_, mapping_, new AddPokemonBeanAdd(),pkPlAb(_name,pk_,all_,mapping_,simu_));
    }

    protected static Struct pkPlayerSelectPkNameQuickAdded() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        Struct addPk_ = goToAddPkPlayer(pk_, all_, mapping_, simu_);
//        callAddPokemonBeanTypedNameSet(addPk_,_name);
//        Struct afSearch_ = transitSimu(pk_, all_, mapping_, new AddPokemonBeanSearch(), addPk_);
        return transitSimu(pk_, all_, mapping_, new AddPokemonBeanAdd(),addPk_);
    }
    protected static Struct pkPlayerSelectPkName(String _name) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        Struct addPk_ = goToAddPkPlayer(pk_, all_, mapping_, simu_);
        callAddPokemonBeanTypedNameSet(addPk_,_name);
        return transitSimu(pk_,all_,mapping_,new AddPokemonBeanSearch(),addPk_);
    }
    protected static Struct pkPlayerSelectPkHasEvo(String _name) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        Struct addPk_ = goToAddPkPlayer(pk_, all_, mapping_, simu_);
        callAddPokemonBeanHasEvoSet(addPk_,_name);
        return transitSimu(pk_,all_,mapping_,new AddPokemonBeanSearch(),addPk_);
    }
    protected static Struct pkPlayerSelectPkIsEvo(String _name) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        Struct addPk_ = goToAddPkPlayer(pk_, all_, mapping_, simu_);
        callAddPokemonBeanIsEvoSet(addPk_,_name);
        return transitSimu(pk_,all_,mapping_,new AddPokemonBeanSearch(),addPk_);
    }
    protected static Struct pkPlayerSelectPkIsLeg(String _name) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        Struct addPk_ = goToAddPkPlayer(pk_, all_, mapping_, simu_);
        callAddPokemonBeanIsLegSet(addPk_,_name);
        return transitSimu(pk_,all_,mapping_,new AddPokemonBeanSearch(),addPk_);
    }
    protected static Struct pkPlayerSelectPkRow(int _row) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        Struct addPk_ = goToAddPkPlayer(pk_, all_, mapping_, simu_);
        callAddPokemonBeanTypedNameSet(addPk_,"");
        Struct rSe_ = transitSimu(pk_, all_, mapping_, new AddPokemonBeanSearch(), addPk_);
        return transitSimu(pk_,all_,mapping_,new AddPokemonBeanClickLink(),rSe_,_row);
    }
    protected static Struct pkPlayerSelectPkType(String _type, boolean _wholeWord) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        Struct addPk_ = goToAddPkPlayer(pk_, all_, mapping_, simu_);
        callAddPokemonBeanTypedTypeSet(addPk_,_type);
        callAddPokemonBeanWholeWordSet(addPk_,_wholeWord);
        return transitSimu(pk_,all_,mapping_,new AddPokemonBeanSearch(),addPk_);
    }
    protected static Struct pkPlayer() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        return goToAddPkPlayer(pk_,all_,mapping_,simu_);
    }

    private static void foeTeamsSample(PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu) {
        selectTeam(_simu,0);
        pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, _pk, _all, _mapping, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_01_TR, A_SIM_1_TR, _pk, _all, _mapping, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_02_TR, A_SIM_1_TR, _pk, _all, _mapping, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_03_TR, A_SIM_1_TR, _pk, _all, _mapping, _simu, 4);
        setMult(_simu,2);
        selectTeam(_simu,1);
        pkTrainerSelectPkNameCycle(false,P_POK_04_TR,A_SIM_2_TR, _pk, _all, _mapping, _simu, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_05_TR,A_SIM_2_TR, _pk, _all, _mapping, _simu, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_06_TR,A_SIM_2_TR, _pk, _all, _mapping, _simu, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_07_TR,A_SIM_2_TR, _pk, _all, _mapping, _simu, 5);
        setMult(_simu,2);
        transitSimu(_pk, _all, _mapping,new SimulationBeanValidateFoeChoiceFree(), _simu);
    }

    private static void foeTeamSample(PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu) {
        selectTeam(_simu,0);
        pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, _pk, _all, _mapping, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_01_TR, A_SIM_1_TR, _pk, _all, _mapping, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_02_TR, A_SIM_1_TR, _pk, _all, _mapping, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_03_TR, A_SIM_1_TR, _pk, _all, _mapping, _simu, 4);
        setMult(_simu,2);
        transitSimu(_pk, _all, _mapping,new SimulationBeanValidateFoeChoiceFree(), _simu);
    }

    private static void foeTeamsSampleInv(PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu) {
        selectTeam(_simu,0);
        pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, _pk, _all, _mapping, _simu, 40);
        pkTrainerSelectPkNameCycle(false, P_POK_01_TR, A_SIM_1_TR, _pk, _all, _mapping, _simu, 40);
        pkTrainerSelectPkNameCycle(false, P_POK_02_TR, A_SIM_1_TR, _pk, _all, _mapping, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_03_TR, A_SIM_1_TR, _pk, _all, _mapping, _simu, 4);
        setMult(_simu,2);
        selectTeam(_simu,1);
        pkTrainerSelectPkNameCycle(false,P_POK_04_TR,A_SIM_2_TR, _pk, _all, _mapping, _simu, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_05_TR,A_SIM_2_TR, _pk, _all, _mapping, _simu, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_06_TR,A_SIM_2_TR, _pk, _all, _mapping, _simu, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_07_TR,A_SIM_2_TR, _pk, _all, _mapping, _simu, 5);
        setMult(_simu,2);
        transitSimu(_pk, _all, _mapping,new SimulationBeanValidateFoeChoiceFree(), _simu);
    }
    private static void setMult(Struct _simu, int _value) {
        callSimulationBeanEnvironmentSet(_simu, EnvironmentType.ROAD.getEnvName());
        callSimulationBeanMultiplicitySet(_simu,_value);
        beforeDisplaying(_simu);
    }

    private static Struct goToSelectPk(PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _struct) {
        return transitSimu(_pk, _all, _mapping, new EditTrainerPokemonBeanChooseName(), _struct);
    }
    private static Struct goToSelectAb(PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _struct) {
        return transitSimu(_pk, _all, _mapping, new EditTrainerPokemonBeanChooseAbility(), _struct);
    }
    private static Struct goToSelectIt(PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _struct) {
        return transitSimu(_pk, _all, _mapping, new EditTrainerPokemonBeanChooseItem(), _struct);
    }
    private static Struct goToSelectItPlayer(PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _struct) {
        return transitSimu(_pk, _all, _mapping, new EditPokemonBeanChooseItem(), _struct);
    }
    private static Struct goToSetMovesTrainer(PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _struct) {
        return transitSimu(_pk, _all, _mapping, new EditTrainerPokemonBeanAddMoves(), _struct);
    }
    private static Struct simu(PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, int _nbTeam) {
        Struct simu_ = init(_nbTeam, _pk, _all, _mapping);
        transitSimu(_pk,_all,_mapping, new SimulationBeanValidateDiffChoice(), simu_);
        return simu_;
    }

    protected static Struct goToAddPkTrainer(PokemonStandards _stds, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu) {
        return transitSimu(_stds,_all,_mapping,new SimulationBeanAddPkTrainer(),_simu);
    }

    protected static Struct goToAddPkPlayer(PokemonStandards _stds, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu) {
        return transitSimu(_stds,_all,_mapping,new SimulationBeanAdd(),_simu);
    }

    public static Struct transitSimu(PokemonStandards _stds, StringMap<Struct> _all, StringMap<String> _mapping, NatCaller _caller, Struct _first, long... _args) {
        String url_ = navigateData(_caller, _first, _args);
        Struct dest_ = _all.getVal(_mapping.getVal(url_));
        setFormsBy(_stds,dest_,_first);
        beforeDisplaying(dest_);
        return dest_;
    }
    protected static Rate integration() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = init(pk_, all_, mapping_);
        callSimulationBeanNbTeamsSet(simu_, 2);
        callRate(new DifficultyCommonBeanWinTrainerExpSet(),all_.getVal(AikiBeansGameStd.BEAN_DIFFICULTY_COMMON),Rate.newRate("5/7"));
        Struct result_ = callDifficultyBeanComGet(transitSimu(pk_,all_,mapping_, new SimulationBeanValidateDiffChoice(), simu_));
        return ((DifficultyCommonStruct)result_).getInstance().getWinTrainerExp();
    }

    public static Struct transitSimuRem(PokemonStandards _stds, NatCaller _caller, Struct _first, long... _args) {
        String url_ = navigateData(_caller, _first, _args);
        assertTrue(url_.isEmpty());
//        Struct dest_ = _all.getVal(_mapping.getVal(url_));
        setFormsBy(_stds, _first,_first);
        beforeDisplaying(_first);
        return _first;
    }
    public static StringMap<Struct> beanToSimu(PkData _pk) {
        StringMap<Struct> map_ = new StringMap<Struct>();
        map_.addEntry(AikiBeansStd.BEAN_WELCOME,_pk.beanWelcomeBean(EN));
        map_.addEntry(AikiBeansGameStd.BEAN_DIFFICULTY_COMMON,_pk.beanDiffCommon(EN));
        map_.addEntry(AikiBeansSimulationStd.BEAN_SIMULATION,_pk.beanSimulationBean(EN));
        map_.addEntry(AikiBeansSimulationStd.BEAN_ADDPOKEMON,_pk.beanAddPokemonBean(EN));
        map_.addEntry(AikiBeansSimulationStd.BEAN_SELECTABILITY,_pk.beanSelectAbilityBean(EN));
        map_.addEntry(AikiBeansSimulationStd.BEAN_SELECTPOKEMON,_pk.beanSelectPokemonBean(EN));
        map_.addEntry(AikiBeansSimulationStd.BEAN_SELECTITEM,_pk.beanSelectItemBean(EN));
        map_.addEntry(AikiBeansSimulationStd.BEAN_EDITPOKEMON,_pk.beanEditPokemonBean(EN));
        map_.addEntry(AikiBeansSimulationStd.BEAN_EDITPOKEMONMOVES,_pk.beanEditPokemonMovesBean(EN));
        map_.addEntry(AikiBeansSimulationStd.BEAN_EDITTRAINERPOKEMON,_pk.beanEditTrainerPokemonBean(EN));
        map_.addEntry(AikiBeansSimulationStd.BEAN_LEVEL_SIMU,_pk.beanSimulationLevelBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToSimu() {
        StringMap<String> map_ = new StringMap<String>();
        map_.addEntry(AikiBeansStd.WEB_HTML_INDEX_HTML,AikiBeansStd.BEAN_WELCOME);
        map_.addEntry(AikiBeansSimulationStd.WEB_HTML_SIMULATION_SIMULATION_HTML,AikiBeansSimulationStd.BEAN_SIMULATION);
        map_.addEntry(AikiBeansSimulationStd.WEB_HTML_SIMULATION_ADDPOKEMON_HTML,AikiBeansSimulationStd.BEAN_ADDPOKEMON);
        map_.addEntry(AikiBeansSimulationStd.WEB_HTML_SIMULATION_SELECTABILITY_HTML,AikiBeansSimulationStd.BEAN_SELECTABILITY);
        map_.addEntry(AikiBeansSimulationStd.WEB_HTML_SIMULATION_SELECTPOKEMON_HTML,AikiBeansSimulationStd.BEAN_SELECTPOKEMON);
        map_.addEntry(AikiBeansSimulationStd.WEB_HTML_SIMULATION_SELECTITEM_HTML,AikiBeansSimulationStd.BEAN_SELECTITEM);
        map_.addEntry(AikiBeansSimulationStd.WEB_HTML_SIMULATION_EDITPOKEMON_HTML,AikiBeansSimulationStd.BEAN_EDITPOKEMON);
        map_.addEntry(AikiBeansSimulationStd.WEB_HTML_SIMULATION_EDITPOKEMONMOVES_HTML,AikiBeansSimulationStd.BEAN_EDITPOKEMONMOVES);
        map_.addEntry(AikiBeansSimulationStd.WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML,AikiBeansSimulationStd.BEAN_EDITTRAINERPOKEMON);
        map_.addEntry(AikiBeansSimulationStd.WEB_HTML_SIMULATION_SIMULATIONLEVEL_HTML,AikiBeansSimulationStd.BEAN_LEVEL_SIMU);
        return map_;
    }
    protected static DataBase dbView() {
        return db().getData();
    }
    private static FacadeGame db() {
        FacadeGame facade_ = facade();
        facade_.getData().completeMembers(M_POK_00,power(T_SIM_1, C_SIM_1, "10"));
        facade_.getData().completeMembers(M_POK_01,power(T_SIM_1, C_SIM_1, "64"));
        facade_.getData().completeMembers(M_POK_02,power(T_SIM_2, C_SIM_2, "192"));
        facade_.getData().completeMembers(M_POK_03,power(T_SIM_2, C_SIM_2, "256"));
        facade_.getData().completeMembers(M_POK_04,powerBad());
        facade_.getData().completeMembers(M_POK_05,noEff());
        facade_.getData().completeMembers(M_POK_06,noEff());
        facade_.getData().completeMembers(P_POK_00,specPk(P_POK_00,P_POK_01,20, withLearn(withLearn(withLearn(new CustList<LevelMove>(),1,M_POK_00),10,M_POK_01),20,M_POK_02)));
        facade_.getData().completeMembers(P_POK_01,specPk(P_POK_00, withLearn(withLearn(withLearn(new CustList<LevelMove>(),1,M_POK_03),10,M_POK_01),20,M_POK_02)));
        facade_.getData().completeMembers(P_POK_02,specPk(P_POK_02,P_POK_03,40, withLearn(withLearn(withLearn(new CustList<LevelMove>(),1,M_POK_00),10,M_POK_01),20,M_POK_02)));
        facade_.getData().completeMembers(P_POK_03,specPk(P_POK_02, withLearn(withLearn(withLearn(new CustList<LevelMove>(),1,M_POK_03),10,M_POK_01),20,M_POK_02)));
        facade_.getData().completeMembers(P_POK_04,specPk(P_POK_04,P_POK_05, withLearn(withLearn(withLearn(new CustList<LevelMove>(),1,M_POK_00),10,M_POK_01),20,M_POK_02)));
        facade_.getData().completeMembers(P_POK_05,specPk(P_POK_04, withLearn(withLearn(withLearn(new CustList<LevelMove>(),1,M_POK_03),10,M_POK_01),20,M_POK_02)));
        facade_.getData().completeMembers(P_POK_06,specLeg(P_POK_06, withLearn(new CustList<LevelMove>(),1,M_POK_03)));
        facade_.getData().completeMembers(P_POK_07,specLeg(P_POK_07, withLearn(new CustList<LevelMove>(),1,M_POK_03)));
        facade_.getData().completeMembers(P_POK_08,specPk(P_POK_08, withLearn(new CustList<LevelMove>(),1,M_POK_03)));
        facade_.getData().completeMembers(P_POK_09,specPk(P_POK_09, withLearn(new CustList<LevelMove>(),1,M_POK_03)));
        facade_.getData().completeMembers(I_STONE,Instances.newEvolvingStone());
        facade_.getData().getExpGrowth().addEntry(ExpType.E,DataBase.VAR_PREFIX+Fighter.NIVEAU);
        facade_.getData().getRates().put(DifficultyWinPointsFight.TRES_FACILE, "1");
        facade_.getData().getRates().put(DifficultyWinPointsFight.FACILE, "1");
        facade_.getData().getRates().put(DifficultyWinPointsFight.DIFFICILE, "1");
        facade_.getData().getRates().put(DifficultyWinPointsFight.TRES_DIFFICILE, "1");
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.CONSTANT_MIN, new LawNumber(lawOne(),(short)0));
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.CROISSANT, new LawNumber(lawOne(),(short)1));
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.UNIFORME, new LawNumber(lawOne(),(short)2));
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.DECROISSANT, new LawNumber(lawOne(),(short)3));
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.CONSTANT_MAX, new LawNumber(lawOne(),(short)4));
        TypesDuos t_ = new TypesDuos();
        t_.addEntry(new TypesDuo(T_SIM_1,T_SIM_1),Rate.one());
        t_.addEntry(new TypesDuo(T_SIM_1,T_SIM_2),Rate.one());
        t_.addEntry(new TypesDuo(T_SIM_2,T_SIM_1),Rate.one());
        t_.addEntry(new TypesDuo(T_SIM_2,T_SIM_2),Rate.one());
        facade_.getData().setTableTypes(t_);
        facade_.getData().setTypes(new StringList(T_SIM_1,T_SIM_2));
        ItemForBattle it_ = Instances.newItemForBattle();
        it_.setBoostExp(true);
        facade_.getData().completeMembers(I_MULT_EXP,it_);
        facade_.getData().completeMembers(I_NOTHING,Instances.newItemForBattle());
        facade_.getData().completeMembers(I_BALL,Instances.newBall());
        facade_.getData().completeMembers(A_SIM_1,Instances.newAbilityData());
        facade_.getData().completeMembers(A_SIM_2,Instances.newAbilityData());
        facade_.getData().setCombos(Instances.newCombos());
        facade_.getData().completeVariables();
        facade_.getData().completeMembersCombos();
//        _db.setBallDef(_ballDef);
//        _db.setRateCatching(_rateCatching);
//        _db.setRateFleeing(_rateFleeing);
        facade_.getData().setRateBoost("1");
        facade_.getData().setRateBoostCriticalHit("2");
        facade_.getData().setDamageFormula("21");
//        _db.setDefMove(_defMove);
//        _db.setDefaultEggGroup(_defaultEggGoup);
//        _db.setDefCategory("AUTRE");
        facade_.getData().addConstNumTest(DataBase.PP_MAX,new Rate(20));
        facade_.getData().addConstNumTest(DataBase.DEF_MAX_ATT,new Rate(2));
        facade_.getData().addConstNumTest(DataBase.NIVEAU_PK_ECLOSION,new Rate(1));
        facade_.getData().addConstNumTest(DataBase.NIVEAU_PK_MAX,new Rate(255));
        facade_.getData().addConstNumTest(DataBase.DEF_PKEQ,new Rate(4));
        facade_.getData().addConstNumTest(DataBase.MAX_BONHEUR,new Rate(128));
        facade_.getData().addConstNumTest(DataBase.MAX_IV,new Rate(32));
        facade_.getData().addConstNumTest(DataBase.MAX_EV,new Rate(32));
        facade_.getData().addConstNumTest(DataBase.GAIN_BONHEUR_NIV, new Rate(2));
        facade_.getData().addConstNumTest(DataBase.VALEUR_DEF_STATIS, new Rate(0));
        facade_.getData().addConstNumTest(DataBase.MAX_BOOST, new Rate(6));
        facade_.getData().addConstNumTest(DataBase.MIN_BOOST, new Rate(-6));
        facade_.getData().addConstNumTest(DataBase.MIN_HP, new Rate(1));
        facade_.getData().addConstNumTest(DataBase.BONUS_BOOST, new Rate("3/2"));
        facade_.getData().addConstNumTest(DataBase.DEF_BASE_MOVE, new Rate("1"));
        facade_.getData().setMap(dm());
        trCore(facade_);
        return facade_;
    }

    protected static Struct pkPlayerValidateEvosSelectTwo() {
        PkData pk_ = pkDataByFacade(dbLight());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        return evolutions(pk_, all_, mapping_, simu_);
    }


    protected static Struct pkPlayerValidateEvosSelectTree() {
        PkData pk_ = pkDataByFacade(dbLightSec());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        return evolutionsTree(pk_, all_, mapping_, simu_);
    }

    protected static Struct pkPlayerValidateEvosSelectTwoOnce() {
        PkData pk_ = pkDataByFacade(dbLight());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        evolutions(pk_, all_, mapping_, simu_);
        callSimulationBeanChosenEvoSet(simu_,P_POK_01);
        callSimulationBeanLevelEvoSet(simu_,41);
        return transitSimu(pk_, all_, mapping_,new SimulationBeanValidateEvo(),simu_);
    }

    protected static Struct pkPlayerValidateEvosSelectTwoTwice() {
        PkData pk_ = pkDataByFacade(dbLight());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        evolutions(pk_, all_, mapping_, simu_);
        callSimulationBeanChosenEvoSet(simu_,P_POK_01);
        callSimulationBeanLevelEvoSet(simu_,41);
        transitSimu(pk_, all_, mapping_,new SimulationBeanValidateEvo(),simu_);
        callSimulationBeanChosenEvoSet(simu_,P_POK_02);
        callSimulationBeanLevelEvoSet(simu_,41);
        return transitSimu(pk_, all_, mapping_,new SimulationBeanValidateEvo(),simu_);
    }

    protected static Struct pkPlayerValidateEvosSelectTwoThreeTimes() {
        PkData pk_ = pkDataByFacade(dbLight());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        evolutions(pk_, all_, mapping_, simu_);
        callSimulationBeanChosenEvoSet(simu_,P_POK_01);
        callSimulationBeanLevelEvoSet(simu_,41);
        transitSimu(pk_, all_, mapping_,new SimulationBeanValidateEvo(),simu_);
        callSimulationBeanChosenEvoSet(simu_,P_POK_02);
        callSimulationBeanLevelEvoSet(simu_,41);
        transitSimu(pk_, all_, mapping_,new SimulationBeanValidateEvo(),simu_);
        callSimulationBeanChosenEvoSet(simu_,P_POK_03);
        callSimulationBeanLevelEvoSet(simu_,41);
        return transitSimu(pk_, all_, mapping_,new SimulationBeanValidateEvo(),simu_);
    }

    private static Struct evolutions(PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu) {
        foeTeamsSampleLight(_pk, _all, _mapping, _simu);
        playerTeamSampleLight(_pk, _all, _mapping, _simu);
        callSimulationBeanSelectedPkSet(_simu,0);
        return transitSimu(_pk, _all, _mapping, new SimulationBeanDisplayEvolutions(), _simu);
    }

    private static Struct evolutionsTree(PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu) {
        foeTeamsSampleLightTree(_pk, _all, _mapping, _simu);
        playerTeamSampleLight(_pk, _all, _mapping, _simu);
        callSimulationBeanSelectedPkSet(_simu,0);
        return transitSimu(_pk, _all, _mapping, new SimulationBeanDisplayEvolutions(), _simu);
    }

    private static void foeTeamsSampleLight(PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu) {
        selectTeam(_simu,0);
        pkTrainerSelectPkNameCycle(false, P_POK_04_TR, A_SIM_1_TR, _pk, _all, _mapping, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_04_TR, A_SIM_1_TR, _pk, _all, _mapping, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_04_TR, A_SIM_1_TR, _pk, _all, _mapping, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_04_TR, A_SIM_1_TR, _pk, _all, _mapping, _simu, 4);
        selectTeam(_simu,1);
        pkTrainerSelectPkNameCycle(false,P_POK_04_TR,A_SIM_2_TR, _pk, _all, _mapping, _simu, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_04_TR,A_SIM_2_TR, _pk, _all, _mapping, _simu, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_04_TR,A_SIM_2_TR, _pk, _all, _mapping, _simu, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_04_TR,A_SIM_2_TR, _pk, _all, _mapping, _simu, 5);
        transitSimu(_pk, _all, _mapping,new SimulationBeanValidateFoeChoiceFree(), _simu);
    }


    private static void foeTeamsSampleLightTree(PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu) {
        selectTeam(_simu,0);
        pkTrainerSelectPkNameCycle(false, P_POK_03_TR, A_SIM_1_TR, _pk, _all, _mapping, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_03_TR, A_SIM_1_TR, _pk, _all, _mapping, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_03_TR, A_SIM_1_TR, _pk, _all, _mapping, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_03_TR, A_SIM_1_TR, _pk, _all, _mapping, _simu, 4);
        selectTeam(_simu,1);
        pkTrainerSelectPkNameCycle(false,P_POK_03_TR,A_SIM_2_TR, _pk, _all, _mapping, _simu, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_03_TR,A_SIM_2_TR, _pk, _all, _mapping, _simu, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_03_TR,A_SIM_2_TR, _pk, _all, _mapping, _simu, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_03_TR,A_SIM_2_TR, _pk, _all, _mapping, _simu, 5);
        transitSimu(_pk, _all, _mapping,new SimulationBeanValidateFoeChoiceFree(), _simu);
    }

    private static Struct playerTeamSampleLight(PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu) {
        pkTrainerSelectPkPlayerNameCycle(P_POK_00_TR,A_SIM_1, _pk, _all, _mapping, _simu,41);
        return transitSimu(_pk, _all, _mapping,new SimulationBeanValidateTeam(),_simu);
    }
    private static FacadeGame dbLight() {
        FacadeGame facade_ = facade();
        facade_.getData().completeMembers(M_POK_00,power(T_SIM_1, C_SIM_1, "10"));
        facade_.getData().completeMembers(P_POK_00,specPk(P_POK_00,P_POK_01,20, withLearn(new CustList<LevelMove>(),1,M_POK_00)));
        facade_.getData().completeMembers(P_POK_01,specPk(P_POK_00,P_POK_02,21, withLearn(new CustList<LevelMove>(),1,M_POK_00)));
        facade_.getData().completeMembers(P_POK_02,specPk(P_POK_00,P_POK_03,22, withLearn(new CustList<LevelMove>(),1,M_POK_00)));
        facade_.getData().completeMembers(P_POK_03,specPk(P_POK_00,withLearn(new CustList<LevelMove>(),1,M_POK_00)));
        facade_.getData().completeMembers(P_POK_04,specPk(P_POK_04,withLearn(new CustList<LevelMove>(),1,M_POK_00)));
        facade_.getData().getExpGrowth().addEntry(ExpType.E,DataBase.VAR_PREFIX+Fighter.NIVEAU);
        facade_.getData().getRates().put(DifficultyWinPointsFight.TRES_FACILE, "1");
        facade_.getData().getRates().put(DifficultyWinPointsFight.FACILE, "1");
        facade_.getData().getRates().put(DifficultyWinPointsFight.DIFFICILE, "1");
        facade_.getData().getRates().put(DifficultyWinPointsFight.TRES_DIFFICILE, "1");
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.CONSTANT_MIN, new LawNumber(lawOne(),(short)0));
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.CROISSANT, new LawNumber(lawOne(),(short)1));
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.UNIFORME, new LawNumber(lawOne(),(short)2));
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.DECROISSANT, new LawNumber(lawOne(),(short)3));
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.CONSTANT_MAX, new LawNumber(lawOne(),(short)4));
        facade_.getData().completeMembers(I_NOTHING,Instances.newItemForBattle());
        TypesDuos t_ = new TypesDuos();
        t_.addEntry(new TypesDuo(T_SIM_1,T_SIM_1),Rate.one());
        t_.addEntry(new TypesDuo(T_SIM_1,T_SIM_2),Rate.one());
        t_.addEntry(new TypesDuo(T_SIM_2,T_SIM_1),Rate.one());
        t_.addEntry(new TypesDuo(T_SIM_2,T_SIM_2),Rate.one());
        facade_.getData().setTableTypes(t_);
        facade_.getData().setTypes(new StringList(T_SIM_1,T_SIM_2));
        facade_.getData().completeMembers(A_SIM_1,Instances.newAbilityData());
        facade_.getData().completeMembers(A_SIM_2,Instances.newAbilityData());
        facade_.getData().completeMembers(I_BALL,Instances.newBall());
        facade_.getData().completeMembers(I_STONE,Instances.newItemForBattle());
        facade_.getData().setCombos(Instances.newCombos());
        facade_.getData().completeVariables();
        facade_.getData().completeMembersCombos();
        facade_.getData().setRateBoost("1");
        facade_.getData().setRateBoostCriticalHit("2");
        facade_.getData().setDamageFormula("21");
        facade_.getData().addConstNumTest(DataBase.PP_MAX,new Rate(20));
        facade_.getData().addConstNumTest(DataBase.DEF_MAX_ATT,new Rate(2));
        facade_.getData().addConstNumTest(DataBase.NIVEAU_PK_ECLOSION,new Rate(1));
        facade_.getData().addConstNumTest(DataBase.NIVEAU_PK_MAX,new Rate(255));
        facade_.getData().addConstNumTest(DataBase.DEF_PKEQ,new Rate(4));
        facade_.getData().addConstNumTest(DataBase.MAX_BONHEUR,new Rate(128));
        facade_.getData().addConstNumTest(DataBase.MAX_IV,new Rate(32));
        facade_.getData().addConstNumTest(DataBase.MAX_EV,new Rate(32));
        facade_.getData().addConstNumTest(DataBase.GAIN_BONHEUR_NIV, new Rate(2));
        facade_.getData().addConstNumTest(DataBase.VALEUR_DEF_STATIS, new Rate(0));
        facade_.getData().addConstNumTest(DataBase.MAX_BOOST, new Rate(6));
        facade_.getData().addConstNumTest(DataBase.MIN_BOOST, new Rate(-6));
        facade_.getData().addConstNumTest(DataBase.MIN_HP, new Rate(1));
        facade_.getData().addConstNumTest(DataBase.BONUS_BOOST, new Rate("3/2"));
        facade_.getData().addConstNumTest(DataBase.DEF_BASE_MOVE, new Rate("1"));
        facade_.getData().setMap(dm());
        trCore(facade_);
        return facade_;
    }
    private static FacadeGame dbLightSec() {
        FacadeGame facade_ = facade();
        facade_.getData().completeMembers(M_POK_00,power(T_SIM_1, C_SIM_1, "10"));
        facade_.getData().completeMembers(P_POK_00,specPk(P_POK_00,P_POK_01,20, P_POK_02,20,withLearn(new CustList<LevelMove>(),1,M_POK_00)));
        facade_.getData().completeMembers(P_POK_01,specPk(P_POK_00, withLearn(new CustList<LevelMove>(),1,M_POK_00)));
        facade_.getData().completeMembers(P_POK_02,specPk(P_POK_00,withLearn(new CustList<LevelMove>(),1,M_POK_00)));
        facade_.getData().completeMembers(P_POK_03,specPk(P_POK_03,withLearn(new CustList<LevelMove>(),1,M_POK_00)));
        facade_.getData().getExpGrowth().addEntry(ExpType.E,DataBase.VAR_PREFIX+Fighter.NIVEAU);
        facade_.getData().getRates().put(DifficultyWinPointsFight.TRES_FACILE, "1");
        facade_.getData().getRates().put(DifficultyWinPointsFight.FACILE, "1");
        facade_.getData().getRates().put(DifficultyWinPointsFight.DIFFICILE, "1");
        facade_.getData().getRates().put(DifficultyWinPointsFight.TRES_DIFFICILE, "1");
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.CONSTANT_MIN, new LawNumber(lawOne(),(short)0));
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.CROISSANT, new LawNumber(lawOne(),(short)1));
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.UNIFORME, new LawNumber(lawOne(),(short)2));
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.DECROISSANT, new LawNumber(lawOne(),(short)3));
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.CONSTANT_MAX, new LawNumber(lawOne(),(short)4));
        facade_.getData().completeMembers(I_NOTHING,Instances.newItemForBattle());
        TypesDuos t_ = new TypesDuos();
        t_.addEntry(new TypesDuo(T_SIM_1,T_SIM_1),Rate.one());
        t_.addEntry(new TypesDuo(T_SIM_1,T_SIM_2),Rate.one());
        t_.addEntry(new TypesDuo(T_SIM_2,T_SIM_1),Rate.one());
        t_.addEntry(new TypesDuo(T_SIM_2,T_SIM_2),Rate.one());
        facade_.getData().setTableTypes(t_);
        facade_.getData().setTypes(new StringList(T_SIM_1,T_SIM_2));
        facade_.getData().completeMembers(A_SIM_1,Instances.newAbilityData());
        facade_.getData().completeMembers(A_SIM_2,Instances.newAbilityData());
        facade_.getData().completeMembers(I_BALL,Instances.newBall());
        facade_.getData().completeMembers(I_STONE,Instances.newItemForBattle());
        facade_.getData().setCombos(Instances.newCombos());
        facade_.getData().completeVariables();
        facade_.getData().completeMembersCombos();
        facade_.getData().setRateBoost("1");
        facade_.getData().setRateBoostCriticalHit("2");
        facade_.getData().setDamageFormula("21");
        facade_.getData().addConstNumTest(DataBase.PP_MAX,new Rate(20));
        facade_.getData().addConstNumTest(DataBase.DEF_MAX_ATT,new Rate(2));
        facade_.getData().addConstNumTest(DataBase.NIVEAU_PK_ECLOSION,new Rate(1));
        facade_.getData().addConstNumTest(DataBase.NIVEAU_PK_MAX,new Rate(255));
        facade_.getData().addConstNumTest(DataBase.DEF_PKEQ,new Rate(4));
        facade_.getData().addConstNumTest(DataBase.MAX_BONHEUR,new Rate(128));
        facade_.getData().addConstNumTest(DataBase.MAX_IV,new Rate(32));
        facade_.getData().addConstNumTest(DataBase.MAX_EV,new Rate(32));
        facade_.getData().addConstNumTest(DataBase.GAIN_BONHEUR_NIV, new Rate(2));
        facade_.getData().addConstNumTest(DataBase.VALEUR_DEF_STATIS, new Rate(0));
        facade_.getData().addConstNumTest(DataBase.MAX_BOOST, new Rate(6));
        facade_.getData().addConstNumTest(DataBase.MIN_BOOST, new Rate(-6));
        facade_.getData().addConstNumTest(DataBase.MIN_HP, new Rate(1));
        facade_.getData().addConstNumTest(DataBase.BONUS_BOOST, new Rate("3/2"));
        facade_.getData().addConstNumTest(DataBase.DEF_BASE_MOVE, new Rate("1"));
        facade_.getData().setMap(dm());
        trCore(facade_);
        return facade_;
    }

    protected static Struct pkPlayerFighterSimulateAfterFightOneLight() {
        PkData pk_ = pkDataByFacade(dbLightThree());
        StringMap<Struct> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        Struct simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSampleVeryLight(pk_, all_, mapping_, simu_);
        playerTeamSampleLightSkip(pk_, all_, mapping_, simu_);
        fighterPositionsLight(pk_, all_, mapping_, simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanSimulateFight(), moveChoicesLight(pk_, all_, mapping_, simu_));
        transitSimu(pk_, all_, mapping_, new SimulationBeanNextFight(),simu_);
        callSimulationBeanSelectedPkSet(simu_,0);
        beforeDisplaying(simu_);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanSelectPkAfterFight(), simu_);
    }

    private static Struct moveChoicesLight(PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu) {
        return moveChoice(0,0,0,0,_pk,_all,_mapping,_simu);
    }
    private static void foeTeamsSampleVeryLight(PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu) {
        selectTeam(_simu,0);
        pkTrainerSelectPkNameCycle(false, P_POK_03_TR, A_SIM_1_TR, _pk, _all, _mapping, _simu, 4);
        selectTeam(_simu,1);
        pkTrainerSelectPkNameCycle(false,P_POK_03_TR,A_SIM_2_TR, _pk, _all, _mapping, _simu, 5);
        transitSimu(_pk, _all, _mapping,new SimulationBeanValidateFoeChoiceFree(), _simu);
    }

    private static Struct playerTeamSampleLightSkip(PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu) {
        pkTrainerSelectPkPlayerNameCycle(P_POK_00_TR,A_SIM_1, _pk, _all, _mapping, _simu,41);
        return transitSimu(_pk, _all, _mapping,new SimulationBeanValidateFoeChoiceSkipEvolutions(),_simu);
    }
    private static FacadeGame dbLightThree() {
        FacadeGame facade_ = facade();
        facade_.getData().completeMembers(M_POK_00,power(T_SIM_1, C_SIM_1, "10"));
        facade_.getData().completeMembers(M_POK_01,power(T_SIM_1, C_SIM_1, "256"));
        facade_.getData().completeMembers(P_POK_00,specPk(P_POK_00,P_POK_01, P_POK_02, withLearn(new CustList<LevelMove>(),1,M_POK_01)));
        facade_.getData().completeMembers(P_POK_01,specPk(P_POK_00, withLearn(new CustList<LevelMove>(),1,M_POK_01)));
        facade_.getData().completeMembers(P_POK_02,specPk(P_POK_00,withLearn(new CustList<LevelMove>(),1,M_POK_01)));
        facade_.getData().completeMembers(P_POK_03,specPk(P_POK_03,withLearn(new CustList<LevelMove>(),1,M_POK_00)));
        facade_.getData().getExpGrowth().addEntry(ExpType.E,DataBase.VAR_PREFIX+Fighter.NIVEAU);
        facade_.getData().getRates().put(DifficultyWinPointsFight.TRES_FACILE, "1");
        facade_.getData().getRates().put(DifficultyWinPointsFight.FACILE, "1");
        facade_.getData().getRates().put(DifficultyWinPointsFight.DIFFICILE, "1");
        facade_.getData().getRates().put(DifficultyWinPointsFight.TRES_DIFFICILE, "1");
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.CONSTANT_MIN, new LawNumber(lawOne(),(short)0));
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.CROISSANT, new LawNumber(lawOne(),(short)1));
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.UNIFORME, new LawNumber(lawOne(),(short)2));
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.DECROISSANT, new LawNumber(lawOne(),(short)3));
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.CONSTANT_MAX, new LawNumber(lawOne(),(short)4));
        facade_.getData().completeMembers(I_NOTHING,Instances.newItemForBattle());
        TypesDuos t_ = new TypesDuos();
        t_.addEntry(new TypesDuo(T_SIM_1,T_SIM_1),Rate.one());
        t_.addEntry(new TypesDuo(T_SIM_1,T_SIM_2),Rate.one());
        t_.addEntry(new TypesDuo(T_SIM_2,T_SIM_1),Rate.one());
        t_.addEntry(new TypesDuo(T_SIM_2,T_SIM_2),Rate.one());
        facade_.getData().setTableTypes(t_);
        facade_.getData().setTypes(new StringList(T_SIM_1,T_SIM_2));
        facade_.getData().completeMembers(A_SIM_1,Instances.newAbilityData());
        facade_.getData().completeMembers(A_SIM_2,Instances.newAbilityData());
        facade_.getData().completeMembers(I_BALL,Instances.newBall());
        facade_.getData().completeMembers(I_STONE,Instances.newEvolvingStone());
        facade_.getData().completeMembers(I_STONE_2,Instances.newEvolvingStone());
        facade_.getData().setCombos(Instances.newCombos());
        facade_.getData().completeVariables();
        facade_.getData().completeMembersCombos();
        facade_.getData().setRateBoost("1");
        facade_.getData().setRateBoostCriticalHit("2");
        facade_.getData().setDamageFormula("21");
        facade_.getData().addConstNumTest(DataBase.PP_MAX,new Rate(20));
        facade_.getData().addConstNumTest(DataBase.DEF_MAX_ATT,new Rate(2));
        facade_.getData().addConstNumTest(DataBase.NIVEAU_PK_ECLOSION,new Rate(1));
        facade_.getData().addConstNumTest(DataBase.NIVEAU_PK_MAX,new Rate(255));
        facade_.getData().addConstNumTest(DataBase.DEF_PKEQ,new Rate(4));
        facade_.getData().addConstNumTest(DataBase.MAX_BONHEUR,new Rate(128));
        facade_.getData().addConstNumTest(DataBase.MAX_IV,new Rate(32));
        facade_.getData().addConstNumTest(DataBase.MAX_EV,new Rate(32));
        facade_.getData().addConstNumTest(DataBase.GAIN_BONHEUR_NIV, new Rate(2));
        facade_.getData().addConstNumTest(DataBase.VALEUR_DEF_STATIS, new Rate(0));
        facade_.getData().addConstNumTest(DataBase.MAX_BOOST, new Rate(6));
        facade_.getData().addConstNumTest(DataBase.MIN_BOOST, new Rate(-6));
        facade_.getData().addConstNumTest(DataBase.MIN_HP, new Rate(1));
        facade_.getData().addConstNumTest(DataBase.BONUS_BOOST, new Rate("3/2"));
        facade_.getData().addConstNumTest(DataBase.DEF_BASE_MOVE, new Rate("1"));
        facade_.getData().setMap(dm());
        trCore(facade_);
        facade_.getData().getTranslatedItems().getVal(EN).addEntry(I_STONE_2, I_STONE_2_TR);
        return facade_;
    }

    private static Struct fighterPositionsLight(PkData _pk, StringMap<Struct> _all, StringMap<String> _mapping, Struct _simu) {
        changeFighterPosition(_pk, _all, _mapping, _simu,0,"0","0");
        return transitSimu(_pk, _all, _mapping, new SimulationBeanValidateFrontFighters(), _simu);
    }
    private static MonteCarloNumber lawOne() {
        MonteCarloNumber mcn_ = new MonteCarloNumber();
        mcn_.addQuickEvent(Rate.one(),LgInt.one());
        return mcn_;
    }

    private static DataMap dm() {
        DataMap dm_ = Instances.newDataMap();
        dm_.setAccessCondition(new CoordsLists());
        WildPk pkm_ = new WildPk();
        pkm_.setName(P_POK_00);
        pkm_.setAbility(A_SIM_1);
        pkm_.setGender(Gender.NO_GENDER);
        pkm_.setItem(NULL_REF);
        pkm_.setLevel((short) 7);
        dm_.setFirstPokemon(pkm_);
        dm_.setBegin(newCoords(0, 0, 0, 1));
        return dm_;
    }

    private static void trCore(FacadeGame _facade) {
        DataBase data_ = _facade.getData();
        IdMap<Gender, String> gdrs_ = new IdMap<Gender, String>();
        gdrs_.addEntry(Gender.NO_GENDER, NO_G);
        data_.getTranslatedGenders().addEntry(EN, gdrs_);
        IdMap<SelectedBoolean, String> bools_ = new IdMap<SelectedBoolean, String>();
        bools_.addEntry(SelectedBoolean.NO,"NO");
        bools_.addEntry(SelectedBoolean.YES,"YES");
        bools_.addEntry(SelectedBoolean.YES_AND_NO,"YES_AND_NO");
        data_.getTranslatedBooleans().addEntry(EN, bools_);
        IdMap<DifficultyWinPointsFight, String> wfn_ = new IdMap<DifficultyWinPointsFight, String>();
        wfn_.addEntry(DifficultyWinPointsFight.TRES_DIFFICILE,"TRES_DIFFICILE");
        wfn_.addEntry(DifficultyWinPointsFight.DIFFICILE,"DIFFICILE");
        wfn_.addEntry(DifficultyWinPointsFight.FACILE,"FACILE");
        wfn_.addEntry(DifficultyWinPointsFight.TRES_FACILE,"TRES_FACILE");
        data_.getTranslatedDiffWinPts().addEntry(EN, wfn_);
        IdMap<DifficultyModelLaw, String> ml_ = new IdMap<DifficultyModelLaw, String>();
        ml_.addEntry(DifficultyModelLaw.CONSTANT_MIN,"CONSTANT_MIN");
        ml_.addEntry(DifficultyModelLaw.CROISSANT,"CROISSANT");
        ml_.addEntry(DifficultyModelLaw.UNIFORME,"UNIFORME");
        ml_.addEntry(DifficultyModelLaw.DECROISSANT,"DECROISSANT");
        ml_.addEntry(DifficultyModelLaw.CONSTANT_MAX,"CONSTANT_MAX");
        data_.getTranslatedDiffModelLaw().addEntry(EN, ml_);
        IdMap<EnvironmentType, String> et_ = new IdMap<EnvironmentType, String>();
        et_.addEntry(EnvironmentType.ROAD,"ROAD");
        et_.addEntry(EnvironmentType.ROCK,"ROCK");
        et_.addEntry(EnvironmentType.NOTHING,"NOTHING");
        et_.addEntry(EnvironmentType.BUILDING,"BUILDING");
        et_.addEntry(EnvironmentType.GRASS,"GRASS");
        et_.addEntry(EnvironmentType.DESERT,"DESERT");
        et_.addEntry(EnvironmentType.ICE,"ICE");
        et_.addEntry(EnvironmentType.SNOW,"SNOW");
        et_.addEntry(EnvironmentType.WATER,"WATER");
        data_.getTranslatedEnvironment().addEntry(EN, et_);
        IdMap<Statistic, String> stats_ = new IdMap<Statistic, String>();
        stats_.addEntry(Statistic.ATTACK,"ATTACK1");
        stats_.addEntry(Statistic.SPECIAL_ATTACK,"SPECIAL_ATTACK1");
        stats_.addEntry(Statistic.DEFENSE,"DEFENSE1");
        stats_.addEntry(Statistic.SPECIAL_DEFENSE,"SPECIAL_DEFENSE1");
        stats_.addEntry(Statistic.SPEED, "SPEED1");
        stats_.addEntry(Statistic.ACCURACY,"ACCURACY1");
        stats_.addEntry(Statistic.EVASINESS,"EVASINESS1");
        stats_.addEntry(Statistic.HP,"HP1");
        stats_.addEntry(Statistic.PV_RESTANTS,"PV_RESTANTS1");
        stats_.addEntry(Statistic.CRITICAL_HIT,"CRITICAL_HIT1");
        data_.getTranslatedStatistics().addEntry(EN, stats_);
        IdMap<TargetChoice, String> tar_ = new IdMap<TargetChoice, String>();
        tar_.addEntry(TargetChoice.ADJ_ADV,"ADJ_ADV");
        tar_.addEntry(TargetChoice.ADJ_MULT,"ADJ_MULT");
        tar_.addEntry(TargetChoice.ANY_FOE,"ANY_FOE");
        tar_.addEntry(TargetChoice.ALLIE,"ALLIE");
        tar_.addEntry(TargetChoice.ALLIES,"ALLIES");
        tar_.addEntry(TargetChoice.AUTRE_UNIQ,"AUTRE_UNIQ");
        tar_.addEntry(TargetChoice.ADJ_UNIQ,"ADJ_UNIQ");
        tar_.addEntry(TargetChoice.GLOBALE,"GLOBALE");
        tar_.addEntry(TargetChoice.LANCEUR,"LANCEUR");
        tar_.addEntry(TargetChoice.NOTHING,"NOTHING");
        tar_.addEntry(TargetChoice.TOUS_ADV,"TOUS_ADV");
        tar_.addEntry(TargetChoice.PSEUDO_GLOBALE,"PSEUDO_GLOBALE");
        tar_.addEntry(TargetChoice.UNIQUE_IMPORTE,"UNIQUE_IMPORTE");
        data_.getTranslatedTargets().addEntry(EN, tar_);
        data_.getTranslatedTypes().addEntry(EN,new StringMap<String>());
        data_.getTranslatedTypes().getVal(EN).addEntry(T_SIM_1, T_SIM_1_TR);
        data_.getTranslatedTypes().getVal(EN).addEntry(T_SIM_2, T_SIM_2_TR);
        data_.getTranslatedPokemon().addEntry(EN,new StringMap<String>());
        data_.getTranslatedPokemon().getVal(EN).addEntry(P_POK_00, P_POK_00_TR);
        data_.getTranslatedPokemon().getVal(EN).addEntry(P_POK_01, P_POK_01_TR);
        data_.getTranslatedPokemon().getVal(EN).addEntry(P_POK_02, P_POK_02_TR);
        data_.getTranslatedPokemon().getVal(EN).addEntry(P_POK_03, P_POK_03_TR);
        data_.getTranslatedPokemon().getVal(EN).addEntry(P_POK_04, P_POK_04_TR);
        data_.getTranslatedPokemon().getVal(EN).addEntry(P_POK_05, P_POK_05_TR);
        data_.getTranslatedPokemon().getVal(EN).addEntry(P_POK_06, P_POK_06_TR);
        data_.getTranslatedPokemon().getVal(EN).addEntry(P_POK_07, P_POK_07_TR);
        data_.getTranslatedPokemon().getVal(EN).addEntry(P_POK_08, P_POK_08_TR);
        data_.getTranslatedPokemon().getVal(EN).addEntry(P_POK_09, P_POK_09_TR);
        data_.getTranslatedTypes().getVal(EN).addEntry(T_SIM_2, T_SIM_2_TR);
        data_.getTranslatedMoves().addEntry(EN,new StringMap<String>());
        data_.getTranslatedMoves().getVal(EN).addEntry(M_POK_00, M_POK_00_TR);
        data_.getTranslatedMoves().getVal(EN).addEntry(M_POK_01, M_POK_01_TR);
        data_.getTranslatedMoves().getVal(EN).addEntry(M_POK_02, M_POK_02_TR);
        data_.getTranslatedMoves().getVal(EN).addEntry(M_POK_03, M_POK_03_TR);
        data_.getTranslatedMoves().getVal(EN).addEntry(M_POK_04, M_POK_04_TR);
        data_.getTranslatedMoves().getVal(EN).addEntry(M_POK_05, M_POK_05_TR);
        data_.getTranslatedMoves().getVal(EN).addEntry(M_POK_06, M_POK_06_TR);
        data_.getTranslatedItems().addEntry(EN,new StringMap<String>());
        data_.getTranslatedItems().getVal(EN).addEntry(I_NOTHING, I_NOTHING_TR);
        data_.getTranslatedItems().getVal(EN).addEntry(I_MULT_EXP, I_MULT_EXP_TR);
        data_.getTranslatedItems().getVal(EN).addEntry(I_STONE, I_STONE_TR);
        data_.getTranslatedItems().getVal(EN).addEntry(I_BALL, I_BALL_TR);
        data_.getTranslatedAbilities().addEntry(EN,new StringMap<String>());
        data_.getTranslatedAbilities().getVal(EN).addEntry(A_SIM_1, A_SIM_1_TR);
        data_.getTranslatedAbilities().getVal(EN).addEntry(A_SIM_2, A_SIM_2_TR);
        data_.getTranslatedCategories().addEntry(EN,new StringMap<String>());
        data_.getTranslatedCategories().getVal(EN).addEntry(C_SIM_1, C_SIM_1_TR);
        data_.getTranslatedCategories().getVal(EN).addEntry(C_SIM_2, C_SIM_2_TR);
        data_.getTranslatedClassesDescriptions().addEntry(EN,new StringMap<String>());
        data_.getTranslatedClassesDescriptions().getVal(EN).addEntry(data_.getItem(I_NOTHING).getItemType(), CI_BATTLE);
        data_.getTranslatedClassesDescriptions().getVal(EN).addEntry(data_.getItem(I_STONE).getItemType(), CI_STONE);
        data_.getTranslatedClassesDescriptions().getVal(EN).addEntry(data_.getItem(I_BALL).getItemType(), CI_BALL);
        data_.getLitterals().addEntry(EN,new StringMap<String>());
        data_.getLitterals().getVal(EN).addEntry(Fight.TEMPS_TOUR, TAB+Fight.TEMPS_TOUR+TAB+TIME);
        data_.getMiniPk().addEntry(P_POK_00, BaseSixtyFourUtil.getImageByString("AAABAAAC"));
        data_.getMiniPk().addEntry(P_POK_01, BaseSixtyFourUtil.getImageByString("AAABAAAD"));
        data_.getMiniPk().addEntry(P_POK_02, BaseSixtyFourUtil.getImageByString("AAABAAAE"));
        data_.getMiniPk().addEntry(P_POK_03, BaseSixtyFourUtil.getImageByString("AAABAAAF"));
        data_.getMiniPk().addEntry(P_POK_04, BaseSixtyFourUtil.getImageByString("AAABAAAG"));
        data_.getMiniPk().addEntry(P_POK_05, BaseSixtyFourUtil.getImageByString("AAABAAAH"));
        data_.getMiniPk().addEntry(P_POK_06, BaseSixtyFourUtil.getImageByString("AAABAAAI"));
        data_.getMiniPk().addEntry(P_POK_07, BaseSixtyFourUtil.getImageByString("AAABAAAJ"));
        data_.getMiniPk().addEntry(P_POK_08, BaseSixtyFourUtil.getImageByString("AAABAAAK"));
        data_.getMiniPk().addEntry(P_POK_09, BaseSixtyFourUtil.getImageByString("AAABAAAL"));
        data_.getMiniItems().addEntry(I_NOTHING, BaseSixtyFourUtil.getImageByString("AAABBUTC"));
        data_.getMiniItems().addEntry(I_MULT_EXP, BaseSixtyFourUtil.getImageByString("AAABBUTD"));
        data_.getMiniItems().addEntry(I_STONE, BaseSixtyFourUtil.getImageByString("AAABBUTE"));
        data_.getMiniItems().addEntry(I_BALL, BaseSixtyFourUtil.getImageByString("AAABBUTF"));
    }
    private static DamagingMoveData power(String _type, String _cat, String _power) {
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        move_.setAccuracy("1");
        move_.setPp((short)15);
        move_.setTypes(new StringList(_type));
        move_.setBoostedTypes(new StringList(_type));
        move_.setCategory(_cat);
        move_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectDamage eff_ = Instances.newEffectDamage();
        eff_.setConstDamage(true);
        eff_.setPower(_power);
        eff_.patch();
        target(move_,eff_);
        return move_;
    }
    private static DamagingMoveData powerBad() {
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        move_.setPp((short)5);
        move_.setAccuracy(DataBase.VAR_PREFIX+Fight.TEMPS_TOUR);
        move_.setTypes(new StringList(T_SIM_2));
        move_.setBoostedTypes(new StringList(T_SIM_2));
        move_.setCategory(C_SIM_2);
        move_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectDamage eff_ = Instances.newEffectDamage();
        eff_.setPower(DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR);
        eff_.patch();
        target(move_,eff_);
        return move_;
    }
    private static StatusMoveData noEff() {
        StatusMoveData st_ = Instances.newStatusMoveData();
        st_.setPp((short)1);
        return st_;
    }
    private static void target(MoveData _dam, Effect _ef) {
        _ef.setTargetChoice(_dam.getTargetChoice());
        _dam.getEffects().add(_ef);
    }

    private static PokemonData specPk(String _base, CustList<LevelMove> _moves) {
        PokemonData pk_ = pk(new StringList("__"), GenderRepartition.NO_GENDER);
        pk_.setTypes(new StringList(T_SIM_1));
        pk_.setAbilities(new StringList(A_SIM_1,A_SIM_2));
        pk_.setBaseEvo(_base);
        pk_.setLevMoves(_moves);
        pk_.setExpEvo(ExpType.E);
        pk_.setHiddenMoves(Shorts.newList());
        pk_.setTechnicalMoves(Shorts.newList());
        pk_.setMoveTutors(new StringList(M_POK_03));
        return pk_;
    }

    private static PokemonData specPk(String _base, String _evo, int _lev, CustList<LevelMove> _moves) {
        PokemonData pk_ = pk(new StringList("__"), GenderRepartition.NO_GENDER);
        pk_.setTypes(new StringList(T_SIM_1));
        pk_.setAbilities(new StringList(A_SIM_1,A_SIM_2));
        pk_.setBaseEvo(_base);
        EvolutionLevelSimple e_ = Instances.newEvolutionLevelSimple();
        e_.setLevel((short) _lev);
        pk_.getEvolutions().addEntry(_evo, e_);
        pk_.setLevMoves(_moves);
        pk_.setExpEvo(ExpType.E);
        pk_.setHiddenMoves(Shorts.newList());
        pk_.setTechnicalMoves(Shorts.newList());
        pk_.setMoveTutors(new StringList(M_POK_03));
        return pk_;
    }

    private static PokemonData specPk(String _base, String _evo, int _lev, String _evo2, int _lev2, CustList<LevelMove> _moves) {
        PokemonData pk_ = pk(new StringList("__"), GenderRepartition.NO_GENDER);
        pk_.setTypes(new StringList(T_SIM_1));
        pk_.setAbilities(new StringList(A_SIM_1,A_SIM_2));
        pk_.setBaseEvo(_base);
        EvolutionLevelSimple e_ = Instances.newEvolutionLevelSimple();
        e_.setLevel((short) _lev);
        pk_.getEvolutions().addEntry(_evo, e_);
        EvolutionLevelSimple e2_ = Instances.newEvolutionLevelSimple();
        e2_.setLevel((short) _lev2);
        pk_.getEvolutions().addEntry(_evo2, e2_);
        pk_.setLevMoves(_moves);
        pk_.setExpEvo(ExpType.E);
        pk_.setHiddenMoves(Shorts.newList());
        pk_.setTechnicalMoves(Shorts.newList());
        pk_.setMoveTutors(new StringList(M_POK_03));
        return pk_;
    }

    private static PokemonData specPk(String _base, String _evo, String _evo2, CustList<LevelMove> _moves) {
        PokemonData pk_ = pk(new StringList("__"), GenderRepartition.NO_GENDER);
        pk_.setTypes(new StringList(T_SIM_1));
        pk_.setAbilities(new StringList(A_SIM_1,A_SIM_2));
        pk_.setBaseEvo(_base);
        EvolutionStoneSimple e_ = Instances.newEvolutionStoneSimple();
        e_.setStone(I_STONE);
        pk_.getEvolutions().addEntry(_evo, e_);
        EvolutionStoneSimple e2_ = Instances.newEvolutionStoneSimple();
        e2_.setStone(I_STONE_2);
        pk_.getEvolutions().addEntry(_evo2, e2_);
        pk_.setLevMoves(_moves);
        pk_.setExpEvo(ExpType.E);
        pk_.setHiddenMoves(Shorts.newList());
        pk_.setTechnicalMoves(Shorts.newList());
        pk_.setMoveTutors(new StringList(M_POK_03));
        return pk_;
    }

    private static PokemonData specPk(String _base, String _evo, CustList<LevelMove> _moves) {
        PokemonData pk_ = pk(new StringList("__"), GenderRepartition.NO_GENDER);
        pk_.setTypes(new StringList(T_SIM_1));
        pk_.setAbilities(new StringList(A_SIM_1,A_SIM_2));
        pk_.setBaseEvo(_base);
        EvolutionStoneSimple e_ = Instances.newEvolutionStoneSimple();
        e_.setStone(I_STONE);
        pk_.getEvolutions().addEntry(_evo, e_);
        pk_.setLevMoves(_moves);
        pk_.setExpEvo(ExpType.E);
        pk_.setHiddenMoves(Shorts.newList());
        pk_.setTechnicalMoves(Shorts.newList());
        pk_.setMoveTutors(new StringList(M_POK_03));
        return pk_;
    }

    private static PokemonData specLeg(String _base, CustList<LevelMove> _moves) {
        PokemonData pk_ = pk(new StringList("__"), GenderRepartition.LEGENDARY);
        pk_.setTypes(new StringList(T_SIM_2));
        pk_.setAbilities(new StringList(A_SIM_1,A_SIM_2));
        pk_.setBaseEvo(_base);
        pk_.setLevMoves(_moves);
        pk_.setExpEvo(ExpType.E);
        pk_.setHiddenMoves(Shorts.newList());
        pk_.setTechnicalMoves(Shorts.newList());
        pk_.setMoveTutors(new StringList(M_POK_03));
        return pk_;
    }
    private static CustList<LevelMove> withLearn(CustList<LevelMove> _set, int _level, String _move) {
        _set.add(new LevelMove((short)_level,_move));
        return _set;
    }
}
