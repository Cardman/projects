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
import aiki.map.buildings.*;
import aiki.map.characters.*;
import aiki.map.levels.*;
import aiki.map.levels.enums.*;
import aiki.map.places.*;
import aiki.map.pokemon.*;
import aiki.map.pokemon.enums.*;
import aiki.util.*;
import code.bean.nat.*;
import code.maths.*;
import code.maths.montecarlo.*;
import code.scripts.confs.PkScriptPages;
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
    public static final String DUAL = "dual";
    public static final String SINGLE = "single";
    public static final String DUAL_1 = "dual_1";
    public static final String DUAL_2 = "dual_2";
    public static final String SI = "si";
    public static final String T_L_1 = "T L 1";
    public static final String T_L_2 = "T L 2";
    public static final String G_L_1 = "G L 1";
    public static final String D_T_1 = "D T 1";
    public static final String D_T_2 = "D T 2";
    public static final String PL_1 = "PL 1";
    public static final String PL_2 = "PL 2";
    public static final String PL_3 = "PL 3";
    public static final String PL_4 = "PL 4";
    public static final String PL_5 = "PL 5";
    public static final String PL_6 = "PL 6";
    public static final String PL_7 = "PL 7";
    public static final String PL_8 = "PL 8";
    public static final String PL_9 = "PL 9";
    public static final int IMG_00 = 2;
    public static final int IMG_01 = IMG_00 + 1;
    public static final int IMG_02 = IMG_01 + 1;
    public static final int IMG_03 = IMG_02 + 1;
    public static final int IMG_04 = IMG_03 + 1;
    public static final int IMG_05 = IMG_04 + 1;
    public static final int IMG_06 = IMG_05 + 1;
    public static final int IMG_07 = IMG_06 + 1;
    public static final int IMG_08 = IMG_07 + 1;
    public static final int IMG_09 = IMG_08 + 1;
    public static final int IMG_NOTHING = 345282;
    public static final int IMG_MULTI_EXP = IMG_NOTHING + 1;
    public static final int IMG_STONE = IMG_MULTI_EXP + 1;
    public static final int IMG_BALL = IMG_STONE + 1;
    public static final int IMG_SINGLE = 22;
    public static final int IMG_DUAL1 = IMG_SINGLE + 1;
    public static final int IMG_DUAL2 = IMG_DUAL1 + 1;
    public static final int IMG_SI = IMG_DUAL2 + 1;
    public static final int IMG_ITEM = 16777215;

    public static NaSt callSimulationBeanAbilitiesAfterFightGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanAbilitiesAfterFightGet(),_str,_args);
    }

    public static NaSt callSimulationBeanAbilitiesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanAbilitiesGet(),_str,_args);
    }

    public static NaSt callSimulationBeanAbilityAfterFightGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanAbilityAfterFightGet(),_str,_args);
    }

    public static NaSt callSimulationBeanAdd(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanAdd(),_str,_args);
    }

    public static NaSt callSimulationBeanAddPkTrainer(NaSt _str, long... _args) {
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

    public static NaSt callSimulationBeanAllyChoiceGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanAllyChoiceGet(),_str,_args);
    }

    public static NaSt callSimulationBeanAllyTeamGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanAllyTeamGet(),_str,_args);
    }

    public static NaSt callSimulationBeanAvailableEvosGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanAvailableEvosGet(),_str,_args);
    }

    public static NaSt callSimulationBeanCancelDiffChoice(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanCancelDiffChoice(),_str,_args);
    }

    public static NaSt callSimulationBeanCancelEvo(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanCancelEvo(),_str,_args);
    }

    public static NaSt callSimulationBeanCancelEvolutions(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanCancelEvolutions(),_str,_args);
    }

    public static NaSt callSimulationBeanCancelEvolutionsAfterFight(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanCancelEvolutionsAfterFight(),_str,_args);
    }

    public static NaSt callSimulationBeanCancelFrontFighters(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanCancelFrontFighters(),_str,_args);
    }

    public static NaSt callSimulationBeanCancelMoves(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanCancelMoves(),_str,_args);
    }

    public static NaSt callSimulationBeanCancelMovesEvos(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanCancelMovesEvos(),_str,_args);
    }

    public static NaSt callSimulationBeanCancelMovesSets(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanCancelMovesSets(),_str,_args);
    }

    public static NaSt callSimulationBeanCancelTeam(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanCancelTeam(),_str,_args);
    }

    public static NaSt callSimulationBeanChangeFight(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanChangeFight(),_str,_args);
    }

    public static NaSt callSimulationBeanChangeFightWhileEnd(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanChangeFightWhileEnd(),_str,_args);
    }

    public static NaSt callSimulationBeanChosenEvoGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanChosenEvoGet(),_str,_args);
    }

    public static NaSt callSimulationBeanClickLevel(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanClickLevel(),_str,_args);
    }

    public static NaSt callSimulationBeanClickLevelZero(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanClickLevelZero(),_str,_args);
    }

    public static NaSt callSimulationBeanCommentsGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanCommentsGet(),_str,_args);
    }

    public static NaSt callSimulationBeanCurrentAbilityGet(NaSt _str, long... _args) {
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

    public static NaSt callSimulationBeanDisplayComments(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanDisplayComments(),_str,_args);
    }

    public static NaSt callSimulationBeanDisplayEvolutions(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanDisplayEvolutions(),_str,_args);
    }

    public static NaSt callSimulationBeanDisplayIfErrorGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanDisplayIfErrorGet(),_str,_args);
    }

//    public static Struct callSimulationBeanEnabledClosingGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanEnabledClosingGet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanEndFightIfOneTeamKoGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanEndFightIfOneTeamKoGet(),_str,_args);
//    }

    public static NaSt callSimulationBeanEnvironmentGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanEnvironmentGet(),_str,_args);
    }

    public static NaSt callSimulationBeanEnvironmentsGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanEnvironmentsGet(),_str,_args);
    }

    public static NaSt callSimulationBeanEvolutionAfterFightGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanEvolutionAfterFightGet(),_str,_args);
    }

    public static NaSt callSimulationBeanEvolutionsAfterFightGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanEvolutionsAfterFightGet(),_str,_args);
    }

    public static NaSt callSimulationBeanFoeTeamGet(NaSt _str) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanFoeTeamGet(),_str);
    }

    public static NaSt callSimulationBeanFreeTeamsGet(int _team) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanFreeTeamsGet(),initByTeam(_team));
    }

    public static NaSt callSimulationBeanGetAbility(NaSt _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetAbility(),_str,_index);
    }

    public static NaSt callSimulationBeanGetAbilityAfterFight(NaSt _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetAbilityAfterFight(),_str,_index);
    }

    public static NaSt callSimulationBeanGetAbilityAlly(NaSt _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetAbilityAlly(),_str,_index);
    }

    public static NaSt callSimulationBeanGetAbilityFoe(NaSt _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetAbilityFoe(),_str,_index);
    }

    public static NaSt callSimulationBeanGetGender(NaSt _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetGender(),_str,_index);
    }

    public static NaSt callSimulationBeanGetGenderAfterFight(NaSt _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetGenderAfterFight(),_str,_index);
    }

    public static NaSt callSimulationBeanGetGenderAlly(NaSt _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetGenderAlly(),_str,_index);
    }

    public static NaSt callSimulationBeanGetGenderFoe(NaSt _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetGenderFoe(),_str,_index);
    }

    public static NaSt callSimulationBeanGetImage(NaSt _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetImage(),_str,_index);
    }

    public static NaSt callSimulationBeanGetImageAfterFight(NaSt _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetImageAfterFight(),_str,_index);
    }

    public static NaSt callSimulationBeanGetImageAlly(NaSt _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetImageAlly(),_str,_index);
    }

    public static NaSt callSimulationBeanGetImageFoe(NaSt _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetImageFoe(),_str,_index);
    }

    public static NaSt callSimulationBeanGetItem(NaSt _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetItem(),_str,_index);
    }

    public static NaSt callSimulationBeanGetItemAfterFight(NaSt _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetItemAfterFight(),_str,_index);
    }

    public static NaSt callSimulationBeanGetItemAlly(NaSt _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetItemAlly(),_str,_index);
    }

    public static NaSt callSimulationBeanGetItemFoe(NaSt _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetItemFoe(),_str,_index);
    }

    public static NaSt callSimulationBeanGetKoFoes(NaSt _str) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetKoFoes(),_str);
    }

    public static NaSt callSimulationBeanGetKoPlayers(NaSt _str) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetKoPlayers(),_str);
    }

    public static NaSt callSimulationBeanGetLevel(NaSt _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetLevel(),_str,_index);
    }

    public static NaSt callSimulationBeanGetLevelAfterFight(NaSt _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetLevelAfterFight(),_str,_index);
    }

    public static NaSt callSimulationBeanGetLevelAlly(NaSt _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetLevelAlly(),_str,_index);
    }

    public static NaSt callSimulationBeanGetLevelFoe(NaSt _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetLevelFoe(),_str,_index);
    }

    public static NaSt callSimulationBeanGetMoves(NaSt _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetMoves(),_str,_index);
    }

    public static NaSt callSimulationBeanGetMovesAfterFight(NaSt _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetMovesAfterFight(),_str,_index);
    }

    public static NaSt callSimulationBeanGetMovesAlly(NaSt _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetMovesAlly(),_str,_index);
    }

    public static NaSt callSimulationBeanGetMovesFoe(NaSt _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetMovesFoe(),_str,_index);
    }

    public static NaSt callSimulationBeanGetName(NaSt _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetName(),_str,_index);
    }

    public static NaSt callSimulationBeanGetNameAfterFight(NaSt _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetNameAfterFight(),_str,_index);
    }

    public static NaSt callSimulationBeanGetNameAlly(NaSt _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetNameAlly(),_str,_index);
    }

    public static NaSt callSimulationBeanGetNameFoe(NaSt _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetNameFoe(),_str,_index);
    }

    public static NaSt callSimulationBeanGetNotKoFrontFoes(NaSt _str) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetNotKoFrontFoes(),_str);
    }

    public static NaSt callSimulationBeanGetRealStepNumber(NaSt _str) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetRealStepNumber(),_str);
    }

    public static NaSt callSimulationBeanGetRemainingLifeRate(NaSt _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetRemainingLifeRate(),_str,_index);
    }

    public static NaSt callSimulationBeanGetTrainerName(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanGetTrainerName(),_str,_args);
    }

    public static NaSt callSimulationBeanHideComments(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanHideComments(),_str,_args);
    }

    public static NaSt callSimulationBeanIndexTeamGet(NaSt _str) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIndexTeamGet(),_str);
    }

    public static NaSt callSimulationBeanIsAvailableAbilities(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsAvailableAbilities(),_str,_args);
    }

    public static NaSt callSimulationBeanIsAvailableMoves(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsAvailableMoves(),_str,_args);
    }

    public static NaSt callSimulationBeanIsDiffState(NaSt _str) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsDiffState(),_str);
    }

    public static NaSt callSimulationBeanIsEvolutionAfterFightState(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsEvolutionAfterFightState(),_str,_args);
    }

    public static NaSt callSimulationBeanIsEvolutionsState(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsEvolutionsState(),_str,_args);
    }

    public static NaSt callSimulationBeanIsFightAfter(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsFightAfter(),_str,_args);
    }

    public static NaSt callSimulationBeanIsFoeState(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsFoeState(),_str,_args);
    }

    public static NaSt callSimulationBeanIsFrontState(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsFrontState(),_str,_args);
    }

//    public static Struct callSimulationBeanIsHardSimulationIssue(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsHardSimulationIssue(),_str,_args);
//    }
//
    public static NaSt callSimulationBeanIsIssue(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsIssue(),_str,_args);
    }

//    public static Struct callSimulationBeanIsIssueAfterFight(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsIssueAfterFight(),_str,_args);
//    }

    public static NaSt callSimulationBeanIsMovesFightState(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsMovesFightState(),_str,_args);
    }

    public static NaSt callSimulationBeanIsMovesState(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsMovesState(),_str,_args);
    }

    public static NaSt callSimulationBeanIsMultiLayer(NaSt _str, long... _args) {
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

    public static NaSt callSimulationBeanIsSimulationState(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsSimulationState(),_str,_args);
    }

    public static NaSt callSimulationBeanIsTeamState(NaSt _str, long... _args) {
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

    public static NaSt callSimulationBeanKeptMovesAfterFightGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanKeptMovesAfterFightGet(),_str,_args);
    }

    public static NaSt callSimulationBeanKeptMovesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanKeptMovesGet(),_str,_args);
    }

    public static NaSt callSimulationBeanLayers(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanLayers(),_str,_args);
    }

    public static NaSt callSimulationBeanLevelEvoGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanLevelEvoGet(),_str,_args);
    }

    public static NaSt callSimulationBeanMovesSetGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanMovesSetGet(),_str,_args);
    }

    public static NaSt callSimulationBeanMultiplicityGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanMultiplicityGet(),_str,_args);
    }

    public static NaSt callSimulationBeanNbTeamsGet(int _team) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanNbTeamsGet(),initByTeam(_team));
    }

    public static NaSt callSimulationBeanSelectedTeamNumberGet(NaSt _str) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectedTeamNumberGet(),_str);
    }

    public static NaSt callSimulationBeanNextFight(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanNextFight(),_str,_args);
    }

    public static NaSt callSimulationBeanNumberNecessaryPointsForGrowingLevel(NaSt _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanNumberNecessaryPointsForGrowingLevel(),_str,_index);
    }

    public static NaSt callSimulationBeanNumbers(int _teams) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanNumbers(),validateDiff(_teams));
    }

    public static NaSt callSimulationBeanOkGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanOkGet(),_str,_args);
    }

    public static NaSt callSimulationBeanPlaceFightGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanPlaceFightGet(),_str,_args);
    }

    public static NaSt callSimulationBeanPlacesFightGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanPlacesFightGet(),_str,_args);
    }

    public static NaSt callSimulationBeanPlacesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanPlacesGet(),_str,_args);
    }

    public static NaSt callSimulationBeanQuit(NaSt _str, long... _args) {
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

    public static NaSt callSimulationBeanRoundGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanRoundGet(),_str,_args);
    }

    public static NaSt callSimulationBeanSelectAllyPk(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectAllyPk(),_str,_args);
    }

    public static NaSt callSimulationBeanSelectFoePk(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectFoePk(),_str,_args);
    }

    public static NaSt callSimulationBeanSelectPk(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectPk(),_str,_args);
    }

    public static NaSt callSimulationBeanErrorSelectedPkGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanErrorSelectedPkGet(),_str,_args);
    }

    public static NaSt callSimulationBeanSelectPkAfterFight(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectPkAfterFight(),_str,_args);
    }

    public static NaSt callSimulationBeanSelectedActionGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectedActionGet(),_str,_args);
    }

    public static NaSt callSimulationBeanSelectedAllyActionGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectedAllyActionGet(),_str,_args);
    }

    public static NaSt callSimulationBeanErrorSelectedAllyPkGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanErrorSelectedAllyPkGet(),_str,_args);
    }

    public static NaSt callSimulationBeanSelectedAllyPkGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectedAllyPkGet(),_str,_args);
    }

    public static NaSt callSimulationBeanSelectedFoeActionGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectedFoeActionGet(),_str,_args);
    }

    public static NaSt callSimulationBeanErrorSelectedFoePkGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanErrorSelectedFoePkGet(),_str,_args);
    }

    public static NaSt callSimulationBeanSelectedFoePkGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectedFoePkGet(),_str,_args);
    }

    public static NaSt callSimulationBeanSelectedIndex(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectedIndex(),_str,_args);
    }

    public static NaSt callSimulationBeanSelectedIndexForMoves(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectedIndexForMoves(),_str,_args);
    }

    public static NaSt callSimulationBeanSelectedMoveGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectedMoveGet(),_str,_args);
    }

    public static NaSt callSimulationBeanSelectedPkGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectedPkGet(),_str,_args);
    }

    public static NaSt callSimulationBeanSelectedRoundGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectedRoundGet(),_str,_args);
    }

    public static NaSt callSimulationBeanSimulateFight(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanSimulateFight(),_str,_args);
    }

//    public static Struct callSimulationBeanSkipLearningMovesWhileNotGrowingLevelGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanSkipLearningMovesWhileNotGrowingLevelGet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanStillPossibleFleeGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanStillPossibleFleeGet(),_str,_args);
//    }

    public static NaSt callSimulationBeanTargetFightGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanTargetFightGet(),_str,_args);
    }

    public static NaSt callSimulationBeanTargetGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanTargetGet(),_str,_args);
    }

    public static NaSt callSimulationBeanTeamAfterFightGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanTeamAfterFightGet(),_str,_args);
    }

    public static NaSt callSimulationBeanTeamGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanTeamGet(),_str,_args);
    }

    public static NaSt callSimulationBeanValidateDiffChoice(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateDiffChoice(),_str,_args);
    }

    public static NaSt callSimulationBeanValidateEvo(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateEvo(),_str,_args);
    }

    public static NaSt callSimulationBeanValidateEvolutionAfterFight(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateEvolutionAfterFight(),_str,_args);
    }

    public static NaSt callSimulationBeanValidateEvolutions(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateEvolutions(),_str,_args);
    }

    public static NaSt callSimulationBeanValidateFoeChoice(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateFoeChoice(),_str,_args);
    }

    public static NaSt callSimulationBeanValidateFoeChoiceFree(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateFoeChoiceFree(),_str,_args);
    }

    public static NaSt callSimulationBeanValidateFoeChoiceSkipEvolutions(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateFoeChoiceSkipEvolutions(),_str,_args);
    }

    public static NaSt callSimulationBeanValidateFrontFighter(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateFrontFighter(),_str,_args);
    }

    public static NaSt callSimulationBeanValidateFrontFighters(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateFrontFighters(),_str,_args);
    }

    public static NaSt callSimulationBeanValidateMoves(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateMoves(),_str,_args);
    }

    public static NaSt callSimulationBeanValidateMovesAbilityAfterFight(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateMovesAbilityAfterFight(),_str,_args);
    }

    public static NaSt callSimulationBeanValidateMovesAfterFight(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateMovesAfterFight(),_str,_args);
    }

    public static NaSt callSimulationBeanValidateMovesChoice(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateMovesChoice(),_str,_args);
    }

    public static NaSt callSimulationBeanValidateMovesSets(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateMovesSets(),_str,_args);
    }

    public static NaSt callSimulationBeanValidateTeam(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateTeam(),_str,_args);
    }

    public static NaSt callSimulationBeanAbilityAfterFightSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new SimulationBeanAbilityAfterFightSet(),_str,_args);
    }

    public static NaSt callSimulationBeanChosenEvoSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new SimulationBeanChosenEvoSet(),_str,_args);
    }

    public static NaSt callSimulationBeanCurrentAbilitySet(NaSt _str, String _args) {
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

    public static NaSt callSimulationBeanEnvironmentSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new SimulationBeanEnvironmentSet(),_str,_args);
    }

    public static NaSt callSimulationBeanEvolutionAfterFightSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new SimulationBeanEvolutionAfterFightSet(),_str,_args);
    }

    public static NaSt callSimulationBeanPlaceFightSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new SimulationBeanPlaceFightSet(),_str,_args);
    }

    public static NaSt callSimulationBeanSelectedActionSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new SimulationBeanSelectedActionSet(),_str,_args);
    }

    public static NaSt callSimulationBeanSelectedAllyActionSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new SimulationBeanSelectedAllyActionSet(),_str,_args);
    }

    public static NaSt callSimulationBeanSelectedFoeActionSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new SimulationBeanSelectedFoeActionSet(),_str,_args);
    }

    public static NaSt callSimulationBeanSelectedRoundSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new SimulationBeanSelectedRoundSet(),_str,_args);
    }

    public static NaSt callSimulationBeanTargetSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new SimulationBeanTargetSet(),_str,_args);
    }

    public static NaSt callSimulationBeanIndexTeamSet(NaSt _str, int _args) {
        return BeanPokemonCommonTs.callInt(new SimulationBeanIndexTeamSet(),_str,_args);
    }

//    public static Struct callSimulationBeanIvFoeSet(Struct _str, int _args) {
//        return BeanPokemonCommonTs.callInt(new SimulationBeanIvFoeSet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanIvPlayerSet(Struct _str, int _args) {
//        return BeanPokemonCommonTs.callInt(new SimulationBeanIvPlayerSet(),_str,_args);
//    }

    public static NaSt callSimulationBeanLevelEvoSet(NaSt _str, int _args) {
        return BeanPokemonCommonTs.callInt(new SimulationBeanLevelEvoSet(),_str,_args);
    }

    public static NaSt callSimulationBeanMultiplicitySet(NaSt _str, int _args) {
        return BeanPokemonCommonTs.callInt(new SimulationBeanMultiplicitySet(),_str,_args);
    }

    public static NaSt callSimulationBeanNbTeamsSet(NaSt _str, int _args) {
        return BeanPokemonCommonTs.callInt(new SimulationBeanNbTeamsSet(),_str,_args);
    }

    public static NaSt callSimulationBeanSelectedAllyPkSet(NaSt _str, int _args) {
        return BeanPokemonCommonTs.callInt(new SimulationBeanSelectedAllyPkSet(),_str,_args);
    }

    public static NaSt callSimulationBeanSelectedFoePkSet(NaSt _str, int _args) {
        return BeanPokemonCommonTs.callInt(new SimulationBeanSelectedFoePkSet(),_str,_args);
    }

    public static NaSt callSimulationBeanSelectedMoveSet(NaSt _str, int _args) {
        return BeanPokemonCommonTs.callInt(new SimulationBeanSelectedMoveSet(),_str,_args);
    }

    public static NaSt callSimulationBeanSelectedPkSet(NaSt _str, int _args) {
        return BeanPokemonCommonTs.callInt(new SimulationBeanSelectedPkSet(),_str,_args);
    }

//    public static Struct callSimulationBeanAllowCatchingKoSet(Struct _str, boolean _args) {
//        return BeanPokemonCommonTs.callBool(new SimulationBeanAllowCatchingKoSet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanAllowedSwitchPlacesEndRoundSet(Struct _str, boolean _args) {
//        return BeanPokemonCommonTs.callBool(new SimulationBeanAllowedSwitchPlacesEndRoundSet(),_str,_args);
//    }

    public static NaSt callSimulationBeanAllyChoiceSet(NaSt _str, boolean _args) {
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

    public static NaSt callAddPokemonBeanAbilitySet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new AddPokemonBeanAbilitySet(),_str,_args);
    }

    public static NaSt callAddPokemonBeanGenderSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new AddPokemonBeanGenderSet(),_str,_args);
    }

    public static NaSt callAddPokemonBeanHasEvoSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new AddPokemonBeanHasEvoSet(),_str,_args);
    }

    public static NaSt callAddPokemonBeanIsEvoSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new AddPokemonBeanIsEvoSet(),_str,_args);
    }

    public static NaSt callAddPokemonBeanIsLegSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new AddPokemonBeanIsLegSet(),_str,_args);
    }

    public static NaSt callAddPokemonBeanTypedNameSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new AddPokemonBeanTypedNameSet(),_str,_args);
    }

    public static NaSt callAddPokemonBeanTypedTypeSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new AddPokemonBeanTypedTypeSet(),_str,_args);
    }

    public static NaSt callEditPokemonBeanBallSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new EditPokemonBeanBallSet(),_str,_args);
    }

    public static NaSt callEditPokemonMovesBeanCategorySet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new EditPokemonMovesBeanCategorySet(),_str,_args);
    }

    public static NaSt callEditPokemonMovesBeanTypedNameSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new EditPokemonMovesBeanTypedNameSet(),_str,_args);
    }

    public static NaSt callEditPokemonMovesBeanTypedTypeSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new EditPokemonMovesBeanTypedTypeSet(),_str,_args);
    }

    public static NaSt callEditTrainerPokemonBeanGenderSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new EditTrainerPokemonBeanGenderSet(),_str,_args);
    }

    public static NaSt callSelectAbilityBeanTypedAbilitySet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new SelectAbilityBeanTypedAbilitySet(),_str,_args);
    }

    public static NaSt callSelectItemBeanTypedClassSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new SelectItemBeanTypedClassSet(),_str,_args);
    }

    public static NaSt callSelectItemBeanTypedNameSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new SelectItemBeanTypedNameSet(),_str,_args);
    }

    public static NaSt callSelectItemBeanTypedPriceSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new SelectItemBeanTypedPriceSet(),_str,_args);
    }

    public static NaSt callSelectPokemonBeanHasEvoSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new SelectPokemonBeanHasEvoSet(),_str,_args);
    }

    public static NaSt callSelectPokemonBeanIsEvoSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new SelectPokemonBeanIsEvoSet(),_str,_args);
    }

    public static NaSt callSelectPokemonBeanIsLegSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new SelectPokemonBeanIsLegSet(),_str,_args);
    }

    public static NaSt callSelectPokemonBeanTypedNameSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new SelectPokemonBeanTypedNameSet(),_str,_args);
    }

    public static NaSt callSelectPokemonBeanTypedTypeSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new SelectPokemonBeanTypedTypeSet(),_str,_args);
    }

    public static NaSt callAddPokemonBeanClickLink(NaSt _str, int _args) {
        return BeanPokemonCommonTs.callInt(new AddPokemonBeanClickLink(),_str,_args);
    }

    public static NaSt callAddPokemonBeanLevelSet(NaSt _str, int _args) {
        return BeanPokemonCommonTs.callInt(new AddPokemonBeanLevelSet(),_str,_args);
    }

    public static NaSt callEditPokemonBeanHappinessSet(NaSt _str, int _args) {
        return BeanPokemonCommonTs.callInt(new EditPokemonBeanHappinessSet(),_str,_args);
    }

    public static NaSt callEditTrainerPokemonBeanLevelSet(NaSt _str, int _args) {
        return BeanPokemonCommonTs.callInt(new EditTrainerPokemonBeanLevelSet(),_str,_args);
    }


    public static NaSt callSimulationLevelBeanNoFightSet(NaSt _str, int _args) {
        return BeanPokemonCommonTs.callInt(new SimulationLevelBeanNoFightSet(),_str,_args);
    }

    public static NaSt callSelectLineMoveSelectedSet(NaSt _str, boolean _args) {
        return BeanPokemonCommonTs.callBool(new SelectLineMoveSelectedSet(),_str,_args);
    }


    public static NaSt callAddPokemonBeanWholeWordSet(NaSt _str, boolean _args) {
        return BeanPokemonCommonTs.callBool(new AddPokemonBeanWholeWordSet(),_str,_args);
    }

    public static NaSt callEditPokemonBeanHealSet(NaSt _str, boolean _args) {
        return BeanPokemonCommonTs.callBool(new EditPokemonBeanHealSet(),_str,_args);
    }

    public static NaSt callEditPokemonMovesBeanAvailableMovesOnlySet(NaSt _str, boolean _args) {
        return BeanPokemonCommonTs.callBool(new EditPokemonMovesBeanAvailableMovesOnlySet(),_str,_args);
    }

    public static NaSt callEditPokemonMovesBeanWholeWordSet(NaSt _str, boolean _args) {
        return BeanPokemonCommonTs.callBool(new EditPokemonMovesBeanWholeWordSet(),_str,_args);
    }

    public static NaSt callEditTrainerPokemonBeanAllyPkSet(NaSt _str, boolean _args) {
        return BeanPokemonCommonTs.callBool(new EditTrainerPokemonBeanAllyPkSet(),_str,_args);
    }

    public static NaSt callSelectPokemonBeanWholeWordSet(NaSt _str, boolean _args) {
        return BeanPokemonCommonTs.callBool(new SelectPokemonBeanWholeWordSet(),_str,_args);
    }

    public static NaSt callEditPokemonBeanExperienceSet(NaSt _str, Rate _args) {
        return BeanPokemonCommonTs.callRate(new EditPokemonBeanExperienceSet(),_str,_args);
    }

    public static NaSt callEditPokemonBeanRemainingHpSet(NaSt _str, Rate _args) {
        return BeanPokemonCommonTs.callRate(new EditPokemonBeanRemainingHpSet(),_str,_args);
    }

    public static NaSt callEvLineEvGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EvLineEvGet(),_str,_args);
    }
    public static NaSt callEvLineEvSet(NaSt _str, int _args) {
        return BeanPokemonCommonTs.callInt(new EvLineEvSet(),_str,_args);
    }
    public static NaSt callDifficultyBeanComGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyBeanComGet(),_str,_args);
    }
    public static NaSt callDifficultyBeanComSet(NaSt _str, NaSt _diffCom) {
        return BeanPokemonCommonTs.callStruct(new DifficultyBeanComSet(),_str,_diffCom);
    }

    public static NaSt callPokemonPlayerDtoIndexGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonPlayerDtoIndexGet(),_str,_args);
    }

    public static NaSt callPokemonTrainerDtoIndexGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonTrainerDtoIndexGet(),_str,_args);
    }
    public static NaSt callRadioLineMoveIndexGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new RadioLineMoveIndexGet(),_str,_args);
    }

    public static NaSt callSelectLineMoveSelectedGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectLineMoveSelectedGet(),_str,_args);
    }
//    public static Struct callSimulationLevelBeanCancel(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationLevelBeanCancel(),_str,_args);
//    }

    public static NaSt callSimulationLevelBeanClickTile(NaSt _str, long... _args) {
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

    public static NaSt callSimulationLevelBeanNoFightGet(NaSt _str, long... _args) {
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


    public static NaSt callAddPokemonBeanAbilitiesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanAbilitiesGet(),_str,_args);
    }

    public static NaSt callAddPokemonBeanAbilityGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanAbilityGet(),_str,_args);
    }

    public static NaSt callAddPokemonBeanAdd(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanAdd(),_str,_args);
    }

    public static NaSt callAddPokemonBeanBooleansGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanBooleansGet(),_str,_args);
    }

    public static NaSt callAddPokemonBeanCancel(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanCancel(),_str,_args);
    }

    public static NaSt callAddPokemonBeanGenderGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanGenderGet(),_str,_args);
    }

    public static NaSt callAddPokemonBeanGendersGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanGendersGet(),_str,_args);
    }

    public static NaSt callAddPokemonBeanGetMiniImage() {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanGetMiniImage(),pkPlayerSelectPkName(""),0);
    }

    public static NaSt callAddPokemonBeanHasEvoGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanHasEvoGet(),_str,_args);
    }

    public static NaSt callAddPokemonBeanIsEvoGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanIsEvoGet(),_str,_args);
    }

    public static NaSt callAddPokemonBeanIsLegGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanIsLegGet(),_str,_args);
    }

    public static NaSt callAddPokemonBeanLevelGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanLevelGet(),_str,_args);
    }

    public static NaSt callAddPokemonBeanNamePkGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanNamePkGet(),_str,_args);
    }

    public static NaSt callAddPokemonBeanPokedexGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanPokedexGet(),_str,_args);
    }

    public static NaSt callAddPokemonBeanSearch(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanSearch(),_str,_args);
    }

    public static NaSt callAddPokemonBeanTypedNameGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanTypedNameGet(),_str,_args);
    }

    public static NaSt callAddPokemonBeanTypedTypeGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanTypedTypeGet(),_str,_args);
    }

    public static NaSt callAddPokemonBeanWholeWordGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanWholeWordGet(),_str,_args);
    }

    public static NaSt callEditPokemonBeanAddMoves(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanAddMoves(),_str,_args);
    }

    public static NaSt callEditPokemonBeanBallGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanBallGet(),_str,_args);
    }

    public static NaSt callEditPokemonBeanBallsGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanBallsGet(),_str,_args);
    }

    public static NaSt callEditPokemonBeanCancel(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanCancel(),_str,_args);
    }

    public static NaSt callEditPokemonBeanChooseItem(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanChooseItem(),_str,_args);
    }

    public static NaSt callEditPokemonBeanDeleteMoves(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanDeleteMoves(),_str,_args);
    }

    public static NaSt callEditPokemonBeanEdit(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanEdit(),_str,_args);
    }

    public static NaSt callEditPokemonBeanEvGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanEvGet(),_str,_args);
    }

    public static NaSt callEditPokemonBeanExperienceGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanExperienceGet(),_str,_args);
    }

    public static NaSt callEditPokemonBeanGetTranslatedStatistic(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanGetTranslatedStatistic(),_str,_args);
    }

    public static NaSt callEditPokemonBeanHappinessGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanHappinessGet(),_str,_args);
    }

    public static NaSt callEditPokemonBeanHealGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanHealGet(),_str,_args);
    }

    public static NaSt callEditPokemonBeanLevelGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanLevelGet(),_str,_args);
    }

    public static NaSt callEditPokemonBeanMovesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanMovesGet(),_str,_args);
    }

    public static NaSt callEditPokemonBeanNamePkGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanNamePkGet(),_str,_args);
    }

    public static NaSt callEditPokemonBeanRemainingHpGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanRemainingHpGet(),_str,_args);
    }

    public static NaSt callEditPokemonBeanTranslateItem(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanTranslateItem(),_str,_args);
    }

    public static NaSt callEditPokemonBeanTranslateName(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanTranslateName(),_str,_args);
    }

    public static NaSt callEditPokemonMovesBeanAddMoves(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonMovesBeanAddMoves(),_str,_args);
    }

    public static NaSt callEditPokemonMovesBeanAvailableMovesOnlyGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonMovesBeanAvailableMovesOnlyGet(),_str,_args);
    }

    public static NaSt callEditPokemonMovesBeanCancel(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonMovesBeanCancel(),_str,_args);
    }

    public static NaSt callEditPokemonMovesBeanCategoriesGet(NaSt _str) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonMovesBeanCategoriesGet(),_str);
    }

    public static NaSt callEditPokemonMovesBeanCategoryGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonMovesBeanCategoryGet(),_str,_args);
    }

    public static NaSt callEditPokemonMovesBeanMovesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonMovesBeanMovesGet(),_str,_args);
    }

    public static NaSt callEditPokemonMovesBeanPlayerGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonMovesBeanPlayerGet(),_str,_args);
    }

    public static NaSt callEditPokemonMovesBeanSearch(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonMovesBeanSearch(),_str,_args);
    }

    public static NaSt callEditPokemonMovesBeanTypedNameGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonMovesBeanTypedNameGet(),_str,_args);
    }

    public static NaSt callEditPokemonMovesBeanTypedTypeGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonMovesBeanTypedTypeGet(),_str,_args);
    }

    public static NaSt callEditPokemonMovesBeanWholeWordGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditPokemonMovesBeanWholeWordGet(),_str,_args);
    }

    public static NaSt callEditTrainerPokemonBeanAddGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanAddGet(),_str,_args);
    }

    public static NaSt callEditTrainerPokemonBeanAddMoves(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanAddMoves(),_str,_args);
    }

    public static NaSt callEditTrainerPokemonBeanAllyPkGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanAllyPkGet(),_str,_args);
    }

    public static NaSt callEditTrainerPokemonBeanCancel(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanCancel(),_str,_args);
    }

    public static NaSt callEditTrainerPokemonBeanChooseAbility(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanChooseAbility(),_str,_args);
    }

    public static NaSt callEditTrainerPokemonBeanChooseItem(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanChooseItem(),_str,_args);
    }

    public static NaSt callEditTrainerPokemonBeanChooseName(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanChooseName(),_str,_args);
    }

    public static NaSt callEditTrainerPokemonBeanDeleteMoves(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanDeleteMoves(),_str,_args);
    }

    public static NaSt callEditTrainerPokemonBeanGenderGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanGenderGet(),_str,_args);
    }

    public static NaSt callEditTrainerPokemonBeanGendersGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanGendersGet(),_str,_args);
    }

    public static NaSt callEditTrainerPokemonBeanGetTranslatedAbility(NaSt _str) {
        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanGetTranslatedAbility(),_str);
    }

    public static NaSt callEditTrainerPokemonBeanGetTranslatedItem(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanGetTranslatedItem(),_str,_args);
    }

    public static NaSt callEditTrainerPokemonBeanGetTranslatedName(NaSt _str) {
        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanGetTranslatedName(),_str);
    }

    public static NaSt callEditTrainerPokemonBeanLevelGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanLevelGet(),_str,_args);
    }

    public static NaSt callEditTrainerPokemonBeanMovesGet(NaSt _str) {
        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanMovesGet(),_str);
    }

    public static NaSt callEditTrainerPokemonBeanValidateTrainerPk(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanValidateTrainerPk(),_str,_args);
    }

    public static NaSt callSelectAbilityBeanCancel(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectAbilityBeanCancel(),_str,_args);
    }

    public static NaSt callSelectAbilityBeanClickAbility(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectAbilityBeanClickAbility(),_str,_args);
    }

    public static NaSt callSelectAbilityBeanGetTrAbility() {
        return BeanPokemonCommonTs.callLongs(new SelectAbilityBeanGetTrAbility(),pkTrainerSelectAb(""),1);
    }

    public static NaSt callSelectAbilityBeanSearch(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectAbilityBeanSearch(),_str,_args);
    }

    public static NaSt callSelectAbilityBeanSortedAbilitiesGet(NaSt _str) {
        return BeanPokemonCommonTs.callLongs(new SelectAbilityBeanSortedAbilitiesGet(),_str);
    }

    public static NaSt callSelectAbilityBeanTypedAbilityGet(NaSt _str) {
        return BeanPokemonCommonTs.callLongs(new SelectAbilityBeanTypedAbilityGet(),_str);
    }

    public static NaSt callSelectItemBeanCancel(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectItemBeanCancel(),_str,_args);
    }

    public static NaSt callSelectItemBeanCancelItem(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectItemBeanCancelItem(),_str,_args);
    }

    public static NaSt callSelectItemBeanClickLink(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectItemBeanClickLink(),_str,_args);
    }

    public static NaSt callSelectItemBeanGetMiniImage() {
        return BeanPokemonCommonTs.callLongs(new SelectItemBeanGetMiniImage(),pkTrainerSelectItName(""),0);
    }

    public static NaSt callSelectItemBeanItemsGet(NaSt _str) {
        return BeanPokemonCommonTs.callLongs(new SelectItemBeanItemsGet(),_str);
    }

    public static NaSt callSelectItemBeanSearch(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectItemBeanSearch(),_str,_args);
    }

    public static NaSt callSelectItemBeanTypedClassGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectItemBeanTypedClassGet(),_str,_args);
    }

    public static NaSt callSelectItemBeanTypedNameGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectItemBeanTypedNameGet(),_str,_args);
    }

    public static NaSt callSelectItemBeanTypedPriceGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectItemBeanTypedPriceGet(),_str,_args);
    }

    public static NaSt callSelectPokemonBeanBooleansGet(NaSt _str) {
        return BeanPokemonCommonTs.callLongs(new SelectPokemonBeanBooleansGet(),_str);
    }

    public static NaSt callSelectPokemonBeanCancel(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectPokemonBeanCancel(),_str,_args);
    }

    public static NaSt callSelectPokemonBeanClickLink(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectPokemonBeanClickLink(),_str,_args);
    }

    public static NaSt callSelectPokemonBeanGetMiniImage() {
        return BeanPokemonCommonTs.callLongs(new SelectPokemonBeanGetMiniImage(),pkTrainerSelectPkName(""),0);
    }

    public static NaSt callSelectPokemonBeanHasEvoGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectPokemonBeanHasEvoGet(),_str,_args);
    }

    public static NaSt callSelectPokemonBeanIsEvoGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectPokemonBeanIsEvoGet(),_str,_args);
    }

    public static NaSt callSelectPokemonBeanIsLegGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectPokemonBeanIsLegGet(),_str,_args);
    }

    public static NaSt callSelectPokemonBeanPokedexGet(NaSt _str) {
        return BeanPokemonCommonTs.callLongs(new SelectPokemonBeanPokedexGet(),_str);
    }

    public static NaSt callSelectPokemonBeanSearch(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectPokemonBeanSearch(),_str,_args);
    }

    public static NaSt callSelectPokemonBeanTypedNameGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectPokemonBeanTypedNameGet(),_str,_args);
    }

    public static NaSt callSelectPokemonBeanTypedTypeGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectPokemonBeanTypedTypeGet(),_str,_args);
    }

    public static NaSt callSelectPokemonBeanWholeWordGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new SelectPokemonBeanWholeWordGet(),_str,_args);
    }
    protected static String quit(){
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = init(pk_, all_, mapping_);
        return navigateData(new SimulationBeanQuit(),simu_);
    }
    protected static NaSt validateDiff(int _nbTeam){
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        return simu(pk_,all_,mapping_,_nbTeam);
    }

    protected static NaSt initByTeam(int _nbTeam) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        return init(_nbTeam,pk_,all_,mappingToSimu());
    }
    protected static NaSt init(int _nbTeam, PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping) {
        NaSt simu_ = init(_pk, _all, _mapping);
        callSimulationBeanNbTeamsSet(simu_, _nbTeam);
        return simu_;
    }
    protected static NaSt selectTeam(NaSt _simu, int _indexTeam) {
        callSimulationBeanIndexTeamSet(_simu, _indexTeam);
        beforeDisplaying(_simu);
        return _simu;
    }
    protected static NaSt pkTrainerTwoTeamsNextOk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
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
    protected static NaSt pkTrainerTwoTeamsNextOkAlly() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 1);
        selectTeam(simu_,0);
        pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_01_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        pkTrainerSelectPkNameCycle(true, P_POK_02_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        pkTrainerSelectPkNameCycle(true, P_POK_03_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        setMult(simu_,2);
        return transitSimu(pk_,all_,mapping_,new SimulationBeanValidateFoeChoiceFree(),simu_);
    }
    protected static NaSt pkTrainerTwoTeamsNextAdjMult() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
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
    protected static NaSt pkTrainerTwoTeamsNextKo() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 1);
        return transitSimu(pk_,all_,mapping_,new SimulationBeanValidateFoeChoiceFree(),simu_);
    }
    protected static NaSt pkTrainerTwoTeams() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        selectTeam(simu_,0);
        pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        selectTeam(simu_,1);
        return pkTrainerSelectPkNameCycle(false,P_POK_01_TR,A_SIM_2_TR,pk_,all_,mapping_,simu_, 5);
    }
    protected static NaSt dispSimu() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        return init(pk_, all_, mappingToSimu());
    }

    private static NaSt init(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping) {
        NaSt from_ = _all.getVal(AikiBeansStd.BEAN_WELCOME);
        NaSt dCom_ = _all.getVal(AikiBeansGameStd.BEAN_DIFFICULTY_COMMON);
        beforeDisplaying(from_);
        NaSt simu_ = transitSimu(_pk, _all, _mapping, new WelcomeBeanClickSimulation(), from_);
        callDifficultyBeanComSet(dCom_,callDifficultyBeanComGet(simu_));
        beforeDisplaying(dCom_);
        return simu_;
    }
    protected static NaSt pkTrainer() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        return goToAddPkTrainer(pk_,all_,mapping_,simu_);
    }
    protected static String editNoFoePk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        callSimulationBeanSelectedFoePkSet(simu_,-1);
        return navigateData(new SimulationBeanSelectFoePk(),simu_);
    }
    protected static NaSt editNoFoePkStateSelectZero() {
        NaSt simu_ = editNoFoePkState();
        callSimulationBeanSelectedFoePkSet(simu_,0);
        callSimulationBeanSelectedFoeActionSet(simu_, TeamCrud.NOTHING.getTeamCrudString());
        return simu_;
    }
    protected static NaSt editNoFoePkStateSelectZero(TeamCrud _tc) {
        NaSt simu_ = editNoFoePkState();
        callSimulationBeanSelectedFoePkSet(simu_,0);
        callSimulationBeanSelectedFoeActionSet(simu_, _tc.getTeamCrudString());
        return simu_;
    }
    protected static NaSt editNoFoePkStateSelectNo() {
        NaSt simu_ = editNoFoePkState();
        callSimulationBeanSelectedFoePkSet(simu_,-1);
        callSimulationBeanSelectedFoeActionSet(simu_, TeamCrud.EDIT.getTeamCrudString());
        return simu_;
    }
    protected static NaSt editNoFoePkState() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        return simu(pk_, all_, mapping_, 2);
    }
    protected static NaSt pkTrainerFoeRemove() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        NaSt second_ = pkTrainerSelectPkNameCycle(false,P_POK_01_TR,A_SIM_2_TR,pk_,all_,mapping_,simu_, 5);
        callSimulationBeanSelectedFoeActionSet(second_, TeamCrud.REMOVE.getTeamCrudString());
        callSimulationBeanSelectedFoePkSet(second_,0);
        return transitSimu(pk_,all_,mapping_,new SimulationBeanSelectFoePk(),second_);
    }
    protected static String editNoSelectedFoePk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt added_ = pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        callSimulationBeanSelectedFoeActionSet(added_, TeamCrud.NOTHING.getTeamCrudString());
        callSimulationBeanSelectedFoePkSet(added_,-1);
        return navigateData(new SimulationBeanSelectFoePk(),added_);
    }
    protected static NaSt editEditSelectedFoePk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt added_ = pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        callSimulationBeanSelectedFoeActionSet(added_, TeamCrud.EDIT.getTeamCrudString());
        callSimulationBeanSelectedFoePkSet(added_,0);
        return transitSimu(pk_,all_,mapping_,new SimulationBeanSelectFoePk(),added_);
    }
    protected static NaSt formEditSelectedFoePk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt added_ = pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        callSimulationBeanSelectedFoeActionSet(added_, TeamCrud.EDIT.getTeamCrudString());
        callSimulationBeanSelectedFoePkSet(added_,0);
        return added_;
    }
    protected static NaSt editEditSelectedFoePkAddMove() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt added_ = pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        callSimulationBeanSelectedFoeActionSet(added_, TeamCrud.EDIT.getTeamCrudString());
        callSimulationBeanSelectedFoePkSet(added_,0);
        NaSt editing_ = transitSimu(pk_, all_, mapping_, new SimulationBeanSelectFoePk(), added_);
        addMoveTrainer(M_POK_01_TR,0,pk_,all_,mapping_,editing_);
        return transitSimu(pk_,all_,mapping_,new EditTrainerPokemonBeanValidateTrainerPk(),editing_);
    }
    protected static String editForgetSelectedFoePk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt added_ = pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        callSimulationBeanSelectedFoeActionSet(added_, TeamCrud.NOTHING.getTeamCrudString());
        callSimulationBeanSelectedFoePkSet(added_,0);
        return navigateData(new SimulationBeanSelectFoePk(),added_);
    }
    protected static String editNoAllyPk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        callSimulationBeanSelectedAllyPkSet(simu_,-1);
        return navigateData(new SimulationBeanSelectAllyPk(),simu_);
    }
    protected static NaSt editNoAllyPkStateSelectZero() {
        NaSt simu_ = editNoAllyPkState();
        callSimulationBeanSelectedAllyPkSet(simu_,0);
        callSimulationBeanSelectedAllyActionSet(simu_, TeamCrud.NOTHING.getTeamCrudString());
        return simu_;
    }
    protected static NaSt editNoAllyPkStateSelectZero(TeamCrud _tc) {
        NaSt simu_ = editNoAllyPkState();
        callSimulationBeanSelectedAllyPkSet(simu_,0);
        callSimulationBeanSelectedAllyActionSet(simu_, _tc.getTeamCrudString());
        return simu_;
    }
    protected static NaSt editNoAllyPkStateSelectNo() {
        NaSt simu_ = editNoAllyPkState();
        callSimulationBeanSelectedAllyPkSet(simu_,-1);
        callSimulationBeanSelectedAllyActionSet(simu_, TeamCrud.EDIT.getTeamCrudString());
        return simu_;
    }
    protected static NaSt editNoAllyPkState() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        return simu(pk_, all_, mapping_, 2);
    }

    protected static NaSt pkTrainerAllyRemove() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        pkTrainerSelectPkNameCycle(true, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        NaSt second_ = pkTrainerSelectPkNameCycle(true,P_POK_01_TR,A_SIM_2_TR,pk_,all_,mapping_,simu_, 5);
        callSimulationBeanSelectedAllyActionSet(second_, TeamCrud.REMOVE.getTeamCrudString());
        callSimulationBeanSelectedAllyPkSet(second_,0);
        return transitSimu(pk_,all_,mapping_,new SimulationBeanSelectAllyPk(),second_);
    }
    protected static String editNoSelectedAllyPk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt added_ = pkTrainerSelectPkNameCycle(true, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        callSimulationBeanSelectedAllyActionSet(added_, TeamCrud.NOTHING.getTeamCrudString());
        callSimulationBeanSelectedAllyPkSet(added_,-1);
        return navigateData(new SimulationBeanSelectAllyPk(),added_);
    }
    protected static NaSt editEditSelectedAllyPk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt added_ = pkTrainerSelectPkNameCycle(true, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        callSimulationBeanSelectedAllyActionSet(added_, TeamCrud.EDIT.getTeamCrudString());
        callSimulationBeanSelectedAllyPkSet(added_,0);
        return transitSimu(pk_,all_,mapping_,new SimulationBeanSelectAllyPk(),added_);
    }
    protected static NaSt formEditSelectedAllyPk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt added_ = pkTrainerSelectPkNameCycle(true, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        callSimulationBeanSelectedAllyActionSet(added_, TeamCrud.EDIT.getTeamCrudString());
        callSimulationBeanSelectedAllyPkSet(added_,0);
        return added_;
    }
    protected static NaSt editEditSelectedAllyPkAddMove() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt added_ = pkTrainerSelectPkNameCycle(true, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        callSimulationBeanSelectedAllyActionSet(added_, TeamCrud.EDIT.getTeamCrudString());
        callSimulationBeanSelectedAllyPkSet(added_,0);
        NaSt editing_ = transitSimu(pk_, all_, mapping_, new SimulationBeanSelectAllyPk(), added_);
        addMoveTrainer(M_POK_01_TR,0,pk_,all_,mapping_,editing_);
        return transitSimu(pk_,all_,mapping_,new EditTrainerPokemonBeanValidateTrainerPk(),editing_);
    }
    protected static String editForgetSelectedAllyPk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt added_ = pkTrainerSelectPkNameCycle(true, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        callSimulationBeanSelectedAllyActionSet(added_, TeamCrud.NOTHING.getTeamCrudString());
        callSimulationBeanSelectedAllyPkSet(added_,0);
        return navigateData(new SimulationBeanSelectAllyPk(),added_);
    }
    protected static NaSt pkTrainerIndex() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
        return pkTrainerSelectPkNameCycle(false,P_POK_01_TR,A_SIM_2_TR,pk_,all_,mapping_,simu_, 5);
    }
    protected static NaSt pkTrainerLevel(int _level) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        return pkTrainerSelectPkNameCycle(false,P_POK_00_TR,A_SIM_1_TR,pk_,all_,mapping_,simu_, _level);
    }
    protected static NaSt pkTrainerLevelCancelAdd() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt editPkTrainer_ = goToAddPkTrainer(pk_,all_,mapping_,simu_);
        return transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanCancel(), editPkTrainer_);
    }
    protected static NaSt pkTrainerLevelRestoreMoves() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt editPkTrainer_ = goToAddPkTrainer(pk_,all_,mapping_,simu_);
        callSelectLineMoveSelectedSet(elt(callEditTrainerPokemonBeanMovesGet(editPkTrainer_),0),true);
        NaSt after_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanDeleteMoves(), editPkTrainer_);
        callEditTrainerPokemonBeanAllyPkSet(after_,false);
        return transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanValidateTrainerPk(), after_);
    }
    private static NaSt pkTrainerSelectPkNameCycle(boolean _ally, String _name, String _ability, PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu, int _level) {
        NaSt editPkTrainer_ = goToAddPkTrainer(_pk, _all, _mapping, _simu);
        NaSt retPk_ = chooseName(_name, _pk, _all, _mapping, editPkTrainer_);
        NaSt selAb_ = chooseAbility(_ability, _pk, _all, _mapping, retPk_);
        callEditTrainerPokemonBeanAllyPkSet(selAb_, _ally);
        callEditTrainerPokemonBeanLevelSet(selAb_, _level);
        genderSet(selAb_);
        NaSt afterAddEdit_ = transitSimu(_pk, _all, _mapping, new EditTrainerPokemonBeanValidateTrainerPk(), selAb_);
        assertSame(afterAddEdit_,_simu);
        return afterAddEdit_;
    }

    private static NaSt pkTrainerSelectPkNameCycle(boolean _ally, String _name, String _ability, String _item, PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu, int _level) {
        NaSt editPkTrainer_ = goToAddPkTrainer(_pk, _all, _mapping, _simu);
        NaSt retPk_ = chooseName(_name, _pk, _all, _mapping, editPkTrainer_);
        NaSt retAb_ = chooseAbility(_ability, _pk, _all, _mapping, retPk_);
        assertSame(retAb_, chooseItemPkTrainer(_item,_pk,_all,_mapping,retAb_));
        callEditTrainerPokemonBeanAllyPkSet(retAb_, _ally);
        callEditTrainerPokemonBeanLevelSet(retAb_, _level);
        genderSet(retAb_);
        NaSt afterAddEdit_ = transitSimu(_pk, _all, _mapping, new EditTrainerPokemonBeanValidateTrainerPk(), retAb_);
        assertSame(afterAddEdit_,_simu);
        return afterAddEdit_;
    }

    private static void genderSet(NaSt _str) {
        callEditTrainerPokemonBeanGenderSet(_str,Gender.NO_GENDER.getGenderName());
    }

    private static void genderSetPl(NaSt _str) {
        callAddPokemonBeanGenderSet(_str,Gender.NO_GENDER.getGenderName());
    }

    private static NaSt chooseName(String _name, PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _str) {
        NaSt selPk_ = transitSimu(_pk, _all, _mapping, new EditTrainerPokemonBeanChooseName(), _str);
        callSelectPokemonBeanTypedNameSet(selPk_, _name);
        NaSt afSel_ = transitSimu(_pk, _all, _mapping, new SelectPokemonBeanSearch(), selPk_);
        assertSame(afSel_,_str);
        return afSel_;
    }

    private static NaSt chooseAbility(String _ability, PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _str) {
        NaSt selAb_ = transitSimu(_pk, _all, _mapping, new EditTrainerPokemonBeanChooseAbility(), _str);
        callSelectAbilityBeanTypedAbilitySet(selAb_, _ability);
        NaSt afSel_ = transitSimu(_pk, _all, _mapping, new SelectAbilityBeanSearch(), selAb_);
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
    protected static NaSt pkTrainerSelectPkCancel() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        NaSt selPk_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseName(), editPkTrainer_);
        return transitSimu(pk_,all_,mapping_,new SelectPokemonBeanCancel(),selPk_);
    }

    protected static NaSt pkTrainerSelectPkName(String _name) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        NaSt selPk_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseName(), editPkTrainer_);
        callSelectPokemonBeanTypedNameSet(selPk_,_name);
        return transitSimu(pk_,all_,mapping_,new SelectPokemonBeanSearch(),selPk_);
    }
    protected static NaSt pkTrainerSelectPkHasEvo(String _name) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        NaSt selPk_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseName(), editPkTrainer_);
        callSelectPokemonBeanHasEvoSet(selPk_,_name);
        return transitSimu(pk_,all_,mapping_,new SelectPokemonBeanSearch(),selPk_);
    }
    protected static NaSt pkTrainerSelectPkIsEvo(String _name) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        NaSt selPk_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseName(), editPkTrainer_);
        callSelectPokemonBeanIsEvoSet(selPk_,_name);
        return transitSimu(pk_,all_,mapping_,new SelectPokemonBeanSearch(),selPk_);
    }
    protected static NaSt pkTrainerSelectPkIsLeg(String _name) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        NaSt selPk_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseName(), editPkTrainer_);
        callSelectPokemonBeanIsLegSet(selPk_,_name);
        return transitSimu(pk_,all_,mapping_,new SelectPokemonBeanSearch(),selPk_);
    }
    protected static NaSt pkTrainerSelectPkRow(int _row) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        NaSt selPk_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseName(), editPkTrainer_);
        callSelectPokemonBeanTypedNameSet(selPk_,"");
        NaSt rSe_ = transitSimu(pk_, all_, mapping_, new SelectPokemonBeanSearch(), selPk_);
        return transitSimu(pk_,all_,mapping_,new SelectPokemonBeanClickLink(),rSe_,_row);
    }
    protected static NaSt pkTrainerSelectPkType(String _type, boolean _wholeWord) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        NaSt selPk_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseName(), editPkTrainer_);
        callSelectPokemonBeanTypedTypeSet(selPk_,_type);
        callSelectPokemonBeanWholeWordSet(selPk_,_wholeWord);
        return transitSimu(pk_,all_,mapping_,new SelectPokemonBeanSearch(),selPk_);
    }
    protected static NaSt pkTrainerSelectPk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        return goToSelectPk(pk_, all_, mapping_, editPkTrainer_);
    }
    protected static NaSt pkTrainerSelectPkAllyInfo(boolean _ally) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        callEditTrainerPokemonBeanAllyPkSet(editPkTrainer_, _ally);
        return editPkTrainer_;
    }
    protected static NaSt pkTrainerSelectAbCancel() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        NaSt selPk_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseAbility(), editPkTrainer_);
        return transitSimu(pk_,all_,mapping_,new SelectAbilityBeanCancel(),selPk_);
    }
    protected static NaSt pkTrainerSelectAb(int _row) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        NaSt selAb_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseAbility(), editPkTrainer_);
        callSelectAbilityBeanTypedAbilitySet(selAb_,"");
        NaSt rSe_ = transitSimu(pk_,all_,mapping_,new SelectAbilityBeanSearch(),selAb_);
        return transitSimu(pk_,all_,mapping_,new SelectAbilityBeanClickAbility(),rSe_,_row);
    }
    protected static NaSt pkTrainerSelectAb(String _name) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        NaSt selAb_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseAbility(), editPkTrainer_);
        callSelectAbilityBeanTypedAbilitySet(selAb_,_name);
        return transitSimu(pk_,all_,mapping_,new SelectAbilityBeanSearch(),selAb_);
    }
    protected static NaSt pkTrainerSelectAb() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        return goToSelectAb(pk_, all_, mapping_, editPkTrainer_);
    }
    protected static NaSt pkTrainerSelectItCancel() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        NaSt selPk_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseItem(), editPkTrainer_);
        NaSt rSe_ = transitSimu(pk_,all_,mapping_,new SelectItemBeanCancelItem(),selPk_);
        NaSt twice_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseItem(), rSe_);
        callSelectItemBeanTypedNameSet(twice_,I_BALL_TR);
        NaSt againEditPk_ = transitSimu(pk_,all_,mapping_,new SelectItemBeanSearch(),twice_);
        NaSt nextIt_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseItem(), againEditPk_);
        return transitSimu(pk_,all_,mapping_,new SelectItemBeanCancel(),nextIt_);
    }
    protected static NaSt pkTrainerSelectItCancelRem() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        NaSt selPk_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseItem(), editPkTrainer_);
        callSelectItemBeanTypedNameSet(selPk_,"");
        NaSt rSe_ = transitSimu(pk_,all_,mapping_,new SelectItemBeanSearch(),selPk_);
        NaSt againEditPk_ = transitSimu(pk_, all_, mapping_, new SelectItemBeanClickLink(), rSe_, 0);
        NaSt nextIt_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseItem(), againEditPk_);
        return transitSimu(pk_,all_,mapping_,new SelectItemBeanCancelItem(),nextIt_);
    }
    protected static NaSt pkTrainerSelectItName(int _row) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        NaSt selAb_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseItem(), editPkTrainer_);
        callSelectItemBeanTypedNameSet(selAb_,"");
        NaSt rSe_ = transitSimu(pk_,all_,mapping_,new SelectItemBeanSearch(),selAb_);
        return transitSimu(pk_,all_,mapping_,new SelectItemBeanClickLink(),rSe_,_row);
    }
    protected static NaSt pkTrainerSelectItName(String _name) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        return chooseItemPkTrainer(_name, pk_, all_, mapping_, editPkTrainer_);
    }

    private static NaSt chooseItemPkTrainer(String _name, PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _editPkTrainer) {
        NaSt selIt_ = transitSimu(_pk, _all, _mapping, new EditTrainerPokemonBeanChooseItem(), _editPkTrainer);
        return chooseItemGene(_name, _pk, _all, _mapping, selIt_);
    }

    private static NaSt chooseItemPkPlayer(String _name, PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _editPkTrainer) {
        NaSt selIt_ = transitSimu(_pk, _all, _mapping, new EditPokemonBeanChooseItem(), _editPkTrainer);
        return chooseItemGene(_name, _pk, _all, _mapping, selIt_);
    }

    private static NaSt chooseItemGene(String _name, PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _selIt) {
        callSelectItemBeanTypedNameSet(_selIt, _name);
        return transitSimu(_pk, _all, _mapping, new SelectItemBeanSearch(), _selIt);
    }
    protected static NaSt pkTrainerSelectItPrice() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        NaSt selAb_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseItem(), editPkTrainer_);
        callSelectItemBeanTypedPriceSet(selAb_,"");
        return transitSimu(pk_,all_,mapping_,new SelectItemBeanSearch(),selAb_);
    }
    protected static NaSt pkTrainerSelectItCl(String _name) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        NaSt selAb_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanChooseItem(), editPkTrainer_);
        callSelectItemBeanTypedClassSet(selAb_,_name);
        return transitSimu(pk_,all_,mapping_,new SelectItemBeanSearch(),selAb_);
    }
    protected static NaSt pkTrainerSelectItName() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        return goToSelectIt(pk_, all_, mapping_, editPkTrainer_);
    }

    protected static NaSt pkTrainerSetMovesName(String _name) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        NaSt selPk_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanAddMoves(), editPkTrainer_);
        callEditPokemonMovesBeanTypedNameSet(selPk_,_name);
        return transitSimu(pk_,all_,mapping_,new EditPokemonMovesBeanSearch(),selPk_);
    }

    protected static NaSt pkTrainerSetMovesCat(String _name) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        NaSt selPk_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanAddMoves(), editPkTrainer_);
        callEditPokemonMovesBeanCategorySet(selPk_,_name);
        return transitSimu(pk_,all_,mapping_,new EditPokemonMovesBeanSearch(),selPk_);
    }
    protected static NaSt pkTrainerSetMovesCancel() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        NaSt selPk_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanAddMoves(), editPkTrainer_);
        return transitSimu(pk_,all_,mapping_,new EditPokemonMovesBeanCancel(),selPk_);
    }
    protected static NaSt pkTrainerSetMovesRemove() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        NaSt re_ = addMoveTrainer(M_POK_01_TR, 0, pk_, all_, mapping_, editPkTrainer_);
        callSelectLineMoveSelectedSet(elt(callEditTrainerPokemonBeanMovesGet(re_),0),true);
        return transitSimu(pk_,all_,mapping_,new EditTrainerPokemonBeanDeleteMoves(),re_);
    }
    protected static NaSt addPkTrainerChangeMoves(boolean _ally) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        NaSt re_ = addMoveTrainer(M_POK_01_TR, 0, pk_, all_, mapping_, editPkTrainer_);
        callSelectLineMoveSelectedSet(elt(callEditTrainerPokemonBeanMovesGet(re_),0),true);
        NaSt afterDel_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanDeleteMoves(), re_);
        callEditTrainerPokemonBeanAllyPkSet(afterDel_,_ally);
        return transitSimu(pk_, all_, mapping_,new EditTrainerPokemonBeanValidateTrainerPk(),afterDel_);
    }
    protected static NaSt addPkTrainerChangeItem(boolean _ally) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        assertSame(editPkTrainer_, chooseItemPkTrainer(I_BALL_TR,pk_,all_,mapping_,editPkTrainer_));
        callEditTrainerPokemonBeanAllyPkSet(editPkTrainer_, _ally);
        return transitSimu(pk_, all_, mapping_,new EditTrainerPokemonBeanValidateTrainerPk(),editPkTrainer_);
    }
    protected static NaSt pkTrainerSetMovesNameAdd(String _name, int _row) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        return addMoveTrainer(_name, _row, pk_, all_, mapping_, editPkTrainer_);
    }

    private static NaSt addMoveTrainer(String _name, int _row, PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _editPkTrainer) {
        NaSt editMoves_ = transitSimu(_pk, _all, _mapping, new EditTrainerPokemonBeanAddMoves(), _editPkTrainer);
        return addMoveGene(_name, _row, _pk, _all, _mapping, editMoves_);
    }

    private static NaSt addMovePlayer(String _name, int _row, PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _editPkPlayer) {
        NaSt editMoves_ = transitSimu(_pk, _all, _mapping, new EditPokemonBeanAddMoves(), _editPkPlayer);
        callEditPokemonMovesBeanAvailableMovesOnlySet(editMoves_,false);
        return addMoveGene(_name, _row, _pk, _all, _mapping, editMoves_);
    }

    private static NaSt searchMovePlayer(String _name, PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _editPkPlayer, boolean _flag) {
        NaSt editMoves_ = transitSimu(_pk, _all, _mapping, new EditPokemonBeanAddMoves(), _editPkPlayer);
        callEditPokemonMovesBeanAvailableMovesOnlySet(editMoves_, _flag);
        callEditPokemonMovesBeanTypedNameSet(editMoves_, _name);
        return transitSimu(_pk, _all, _mapping, new EditPokemonMovesBeanSearch(), editMoves_);
    }

    private static NaSt addMoveGene(String _name, int _row, PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _editMoves) {
        callEditPokemonMovesBeanTypedNameSet(_editMoves, _name);
        NaSt foundMoves_ = transitSimu(_pk, _all, _mapping, new EditPokemonMovesBeanSearch(), _editMoves);
        callSelectLineMoveSelectedSet(elt(callEditPokemonMovesBeanMovesGet(foundMoves_), _row),true);
        return transitSimu(_pk, _all, _mapping, new EditPokemonMovesBeanAddMoves(), foundMoves_);
    }
    protected static NaSt pkTrainerSetMovesType(String _type, boolean _wholeWord) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        NaSt selPk_ = transitSimu(pk_, all_, mapping_, new EditTrainerPokemonBeanAddMoves(), editPkTrainer_);
        callEditPokemonMovesBeanTypedTypeSet(selPk_,_type);
        callEditPokemonMovesBeanWholeWordSet(selPk_,_wholeWord);
        return transitSimu(pk_,all_,mapping_,new EditPokemonMovesBeanSearch(),selPk_);
    }
    protected static NaSt pkTrainerSetMoves() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt editPkTrainer_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        return goToSetMovesTrainer(pk_, all_, mapping_, editPkTrainer_);
    }
    protected static NaSt pkTrainer(boolean _select) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        NaSt edit_ = goToAddPkTrainer(pk_, all_, mapping_, simu_);
        callSelectLineMoveSelectedSet(elt(callEditTrainerPokemonBeanMovesGet(edit_),0),_select);
        return edit_;
    }
    protected static String editNoPlayerPk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        callSimulationBeanSelectedPkSet(simu_,-1);
        return navigateData(new SimulationBeanSelectPk(),simu_);
    }
    protected static NaSt pkPlayerRemove() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        pkTrainerSelectPkPlayerNameCycle(P_POK_00_TR, A_SIM_1, pk_, all_, mapping_, simu_, 4);
        return editPkPlayer(pk_, all_, mapping_, simu_, P_POK_01_TR, A_SIM_2_TR, 0, 5, TeamCrud.REMOVE);
    }
    protected static String editNoSelectedPlayerPk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        NaSt added_ = pkTrainerSelectPkPlayerNameCycle(P_POK_00_TR, A_SIM_1, pk_, all_, mapping_, simu_, 4);
        callSimulationBeanSelectedActionSet(added_, TeamCrud.NOTHING.getTeamCrudString());
        callSimulationBeanSelectedPkSet(added_,-1);
        return navigateData(new SimulationBeanSelectPk(),added_);
    }
    protected static NaSt editNoPkStateSelectZero() {
        NaSt simu_ = editNoPkState();
        callSimulationBeanSelectedPkSet(simu_,0);
        callSimulationBeanSelectedActionSet(simu_, TeamCrud.NOTHING.getTeamCrudString());
        return simu_;
    }
    protected static NaSt editNoPkStateSelectZero(TeamCrud _tc) {
        NaSt simu_ = editNoPkState();
        callSimulationBeanSelectedPkSet(simu_,0);
        callSimulationBeanSelectedActionSet(simu_, _tc.getTeamCrudString());
        return simu_;
    }
    protected static NaSt editNoPkStateSelectNo() {
        NaSt simu_ = editNoPkState();
        callSimulationBeanSelectedPkSet(simu_,-1);
        callSimulationBeanSelectedActionSet(simu_, TeamCrud.EDIT.getTeamCrudString());
        return simu_;
    }
    protected static NaSt editNoPkState() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        return simu(pk_, all_, mapping_, 2);
    }

    protected static NaSt editEditSelectedPlayerPk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        return editPkPlayer(pk_, all_, mapping_, simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
    }
    protected static NaSt editEditSelectedPlayerPkForm(boolean _heal, Rate _exp, Rate _hp) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        NaSt editing_ = editPkPlayer(pk_, all_, mapping_, simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        callEditPokemonBeanHealSet(editing_, _heal);
        callEditPokemonBeanBallSet(editing_,I_BALL);
        callEditPokemonBeanExperienceSet(editing_, _exp);
        callEditPokemonBeanHappinessSet(editing_,1);
        callEditPokemonBeanRemainingHpSet(editing_, _hp);
        callEvLineEvSet(second(elt(callEditPokemonBeanEvGet(editing_),2)),33);
        return transitSimu(pk_,all_,mapping_,new EditPokemonBeanEdit(),editing_);
    }
    protected static NaSt editEditSelectedPlayerPkFormNoMove() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        NaSt editing_ = editPkPlayer(pk_, all_, mapping_, simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        callSelectLineMoveSelectedSet(elt(callEditPokemonBeanMovesGet(editing_),0),true);
        transitSimu(pk_,all_,mapping_,new EditPokemonBeanDeleteMoves(),editing_);
        return transitSimu(pk_,all_,mapping_,new EditPokemonBeanEdit(),editing_);
    }
    protected static NaSt editEditSelectedPlayerPkFormCancel() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        NaSt editing_ = editPkPlayer(pk_, all_, mapping_, simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        callSelectLineMoveSelectedSet(elt(callEditPokemonBeanMovesGet(editing_),0),true);
        transitSimu(pk_,all_,mapping_,new EditPokemonBeanDeleteMoves(),editing_);
        return transitSimu(pk_,all_,mapping_,new EditPokemonBeanCancel(),editing_);
    }
    protected static NaSt editEditSelectedPlayerPkHeal(boolean _heal) {
        NaSt h_ = editEditSelectedPlayerPk();
        callEditPokemonBeanHealSet(h_,_heal);
        return h_;
    }
    protected static NaSt editEditSelectedPlayerPkItem() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        NaSt edit_ = editPkPlayer(pk_, all_, mapping_, simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        assertSame(edit_,chooseItemPkPlayer(I_BALL_TR,pk_, all_, mapping_,edit_));
        return edit_;
    }
    protected static NaSt editEditSelectedPlayerPkItemCancelItem() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        NaSt edit_ = editPkPlayer(pk_, all_, mapping_, simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        NaSt chosen_ = chooseItemPkPlayer(I_BALL_TR, pk_, all_, mapping_, edit_);
        NaSt redo_ = transitSimu(pk_, all_, mapping_, new EditPokemonBeanChooseItem(), chosen_);
        return transitSimu(pk_, all_, mapping_, new SelectItemBeanCancelItem(), redo_);
    }
    protected static NaSt editEditSelectedPlayerPkItemCancel() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        NaSt edit_ = editPkPlayer(pk_, all_, mapping_, simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        NaSt first_ = transitSimu(pk_, all_, mapping_, new EditPokemonBeanChooseItem(), edit_);
        NaSt back_ = transitSimu(pk_, all_, mapping_, new SelectItemBeanCancel(), first_);
        return chooseItemPkPlayer(I_BALL_TR,pk_, all_, mapping_,back_);
    }
    protected static NaSt editEditSelectedPlayerPkItemPart(int _row) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        NaSt edit_ = editPkPlayer(pk_, all_, mapping_, simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        NaSt chosen_ = chooseItemPkPlayer("", pk_, all_, mapping_, edit_);
        return transitSimu(pk_, all_, mapping_, new SelectItemBeanClickLink(), chosen_,_row);
    }
    protected static NaSt formEditSelectedPlayerPk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        NaSt added_ = pkTrainerSelectPkPlayerNameCycle(P_POK_00_TR, A_SIM_1, pk_, all_, mapping_, simu_, 4);
        callSimulationBeanSelectedActionSet(added_, TeamCrud.EDIT.getTeamCrudString());
        callSimulationBeanSelectedPkSet(added_,0);
        return added_;
    }
    protected static NaSt editEditSelectedPlayerPkAddMove() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        NaSt editing_ = editPkPlayer(pk_, all_, mapping_, simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        addMovePlayer(M_POK_01_TR,0,pk_,all_,mapping_,editing_);
        return transitSimu(pk_,all_,mapping_,new EditPokemonBeanEdit(),editing_);
    }
    protected static NaSt editEditSelectedPlayerPkListMoves() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        NaSt editing_ = editPkPlayer(pk_, all_, mapping_, simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        return transitSimu(pk_,all_,mapping_, new EditPokemonBeanAddMoves(),editing_);
    }
    protected static NaSt editEditSelectedPlayerPkListMoves(String _name, boolean _flag) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        NaSt editing_ = editPkPlayer(pk_, all_, mapping_, simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        return searchMovePlayer(_name,pk_,all_,mapping_,editing_,_flag);
    }
    protected static NaSt editEditSelectedPlayerPkListMovesCancel() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        NaSt editing_ = editPkPlayer(pk_, all_, mapping_, simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        NaSt se_ = searchMovePlayer(M_POK_04_TR, pk_, all_, mapping_, editing_, false);
        callSelectLineMoveSelectedSet(elt(callEditPokemonMovesBeanMovesGet(se_),0),true);
        return transitSimu(pk_,all_,mapping_,new EditPokemonMovesBeanCancel(),se_);
    }

    private static NaSt editPkPlayer(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu, String _name, String _ab, int _index, int _level, TeamCrud _mode) {
        NaSt added_ = pkTrainerSelectPkPlayerNameCycle(_name, _ab, _pk, _all, _mapping, _simu, _level);
        callSimulationBeanSelectedActionSet(added_, _mode.getTeamCrudString());
        callSimulationBeanSelectedPkSet(added_, _index);
        return transitSimu(_pk, _all, _mapping, new SimulationBeanSelectPk(), added_);
    }

    protected static NaSt pkPlayerSetMovesRemove() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        NaSt editing_ = editPkPlayer(pk_, all_, mapping_, simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        NaSt re_ = addMovePlayer(M_POK_01_TR, 0, pk_, all_, mapping_, editing_);
        callSelectLineMoveSelectedSet(elt(callEditPokemonBeanMovesGet(re_),0),true);
        return transitSimu(pk_,all_,mapping_,new EditPokemonBeanDeleteMoves(),re_);
    }
    protected static String editForgetSelectedPlayerPk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        NaSt added_ = pkTrainerSelectPkPlayerNameCycle(P_POK_00_TR, A_SIM_1, pk_, all_, mapping_, simu_, 4);
        callSimulationBeanSelectedActionSet(added_, TeamCrud.NOTHING.getTeamCrudString());
        callSimulationBeanSelectedPkSet(added_,0);
        return navigateData(new SimulationBeanSelectPk(),added_);
    }
    protected static NaSt addPkPlayerChangeMoves() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        NaSt editing_ = editPkPlayer(pk_, all_, mapping_, simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        assertSame(editing_,chooseItemPkPlayer(I_BALL_TR,pk_, all_, mapping_,editing_));
        NaSt re_ = addMovePlayer(M_POK_01_TR, 0, pk_, all_, mapping_, editing_);
        callSelectLineMoveSelectedSet(elt(callEditPokemonBeanMovesGet(re_),0),true);
        NaSt afterDel_ = transitSimu(pk_, all_, mapping_, new EditPokemonBeanDeleteMoves(), re_);
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
    private static NaSt pkTrainerSelectPkPlayerNameCycle(String _name, String _ability, PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu, int _level) {
        NaSt addPk_ = goToAddPkPlayer(_pk, _all, _mapping, _simu);
        callAddPokemonBeanTypedNameSet(addPk_,_name);
        NaSt afSearch_ = transitSimu(_pk, _all, _mapping, new AddPokemonBeanSearch(), addPk_);
        callAddPokemonBeanAbilitySet(afSearch_,_ability);
        callAddPokemonBeanLevelSet(afSearch_,_level);
        NaSt afterAddEdit_ = transitSimu(_pk, _all, _mapping, new AddPokemonBeanAdd(), afSearch_);
        assertSame(afterAddEdit_,_simu);
        return afterAddEdit_;
    }
    protected static NaSt pkPlayerSelectPk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        return goToAddPkPlayer(pk_, all_, mapping_, simu_);
    }
    protected static NaSt pkPlayerSelectPkCancel() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        NaSt addPk_ = goToAddPkPlayer(pk_, all_, mapping_, simu_);
        return transitSimu(pk_,all_,mapping_,new AddPokemonBeanCancel(),addPk_);
    }

    protected static NaSt pkPlayerSelectPkNameAbility(String _name) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        return pkPlAb(_name, pk_, all_, mapping_, simu_);
    }

    protected static NaSt pkPlayerSelectPkNameTwice() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        pkTrainerSelectPkPlayerNameCycle(P_POK_01_TR,A_SIM_1,pk_,all_,mapping_,simu_,40);
        return pkTrainerSelectPkPlayerNameCycle(P_POK_02_TR,A_SIM_2,pk_,all_,mapping_,simu_,39);
    }

    protected static NaSt pkPlayerValidateEvosNoSelect() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        callSimulationBeanSelectedPkSet(simu_,-1);
        return transitSimu(pk_, all_, mapping_,new SimulationBeanDisplayEvolutions(),simu_);
    }

    protected static NaSt pkPlayerValidateEvosSelect() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        callSimulationBeanSelectedPkSet(simu_,1);
        return transitSimu(pk_, all_, mapping_,new SimulationBeanDisplayEvolutions(),simu_);
    }

    protected static NaSt pkPlayerValidateEvoValues() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        callSimulationBeanSelectedPkSet(simu_,1);
        transitSimu(pk_, all_, mapping_,new SimulationBeanDisplayEvolutions(),simu_);
        callSimulationBeanChosenEvoSet(simu_,P_POK_03);
        callSimulationBeanLevelEvoSet(simu_,41);
        return simu_;
    }

    protected static NaSt pkPlayerValidateEvoValidate() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        callSimulationBeanSelectedPkSet(simu_,1);
        transitSimu(pk_, all_, mapping_,new SimulationBeanDisplayEvolutions(),simu_);
        callSimulationBeanChosenEvoSet(simu_,P_POK_03);
        callSimulationBeanLevelEvoSet(simu_,39);
        return transitSimu(pk_, all_, mapping_,new SimulationBeanValidateEvo(),simu_);
    }

    protected static NaSt pkPlayerEvoThenFighters() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        return validEvos(pk_, all_, mapping_,simu_);
    }

    protected static NaSt pkPlayerEvoFightersImmediateValid() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        validEvos(pk_, all_, mapping_,simu_);
        callSimulationBeanSelectedPkSet(simu_,-1);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanValidateFrontFighter(),simu_);
    }

    protected static NaSt pkPlayerEvoFightersFormValid() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        validEvos(pk_, all_, mapping_,simu_);
        return changeFighterPosition(pk_, all_, mapping_, simu_, 0, "0", "0");
    }

    protected static NaSt pkPlayerEvoFightersWithoutFronts() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        validEvos(pk_, all_, mapping_,simu_);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanValidateFrontFighters(), simu_);
    }

    protected static NaSt pkPlayerEvoFightersSufficientFronts() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        validEvos(pk_, all_, mapping_,simu_);
        return fighterPositions(pk_, all_, mapping_, simu_);
    }

    protected static NaSt pkPlayerEvoFightersSufficientFrontsFormMove(int _index) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        validEvos(pk_, all_, mapping_,simu_);
        fighterPositions(pk_, all_, mapping_, simu_);
        callSimulationBeanSelectedPkSet(simu_,_index);
        beforeDisplaying(simu_);
        return simu_;
    }

    protected static NaSt pkPlayerEvoFightersSufficientFrontsFormMoveValidateMovesQuick(int _index, String _ab) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        validEvos(pk_, all_, mapping_,simu_);
        fighterPositions(pk_, all_, mapping_, simu_);
        callSimulationBeanSelectedPkSet(simu_,_index);
        beforeDisplaying(simu_);
        return movesAbilities(_ab, pk_, all_, mapping_, simu_);
    }

    protected static NaSt pkPlayerEvoFightersSufficientFrontsFormMoveValidateMovesQuickTwice(int _index, String _ab) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        validEvos(pk_, all_, mapping_,simu_);
        fighterPositions(pk_, all_, mapping_, simu_);
        callSimulationBeanSelectedPkSet(simu_,_index);
        beforeDisplaying(simu_);
        movesAbilities(_ab, pk_, all_, mapping_, simu_);
        return movesAbilities("", pk_, all_, mapping_, simu_);
    }

    protected static NaSt pkPlayerEvoFightersSufficientFrontsFormMoveValidateMovesAllFightersOk() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        validEvos(pk_, all_, mapping_,simu_);
        fighterPositions(pk_, all_, mapping_, simu_);
        return movesSet(pk_, all_, mapping_, simu_);
    }

    protected static NaSt pkPlayerEvoFighterChoice(int _index, int _round) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
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

    protected static NaSt pkPlayerEvoFighterChoiceAfter(int _index, int _round, boolean _allyChoice, int _move, int _target) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        validEvos(pk_, all_, mapping_,simu_);
        fighterPositions(pk_, all_, mapping_, simu_);
        movesSet(pk_, all_, mapping_, simu_);
        return moveChoice(_index, _round, _allyChoice, _move, _target, pk_, all_, mapping_, simu_);
    }

    protected static NaSt pkPlayerEvoFighterSimulate() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        validEvos(pk_, all_, mapping_,simu_);
        fighterPositions(pk_, all_, mapping_, simu_);
        movesSet(pk_, all_, mapping_, simu_);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanSimulateFight(), moveChoices(pk_, all_, mapping_, simu_));
    }

    protected static NaSt pkPlayerEvoFighterSimulateStMove() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        validEvos(pk_, all_, mapping_,simu_);
        fighterPositions(pk_, all_, mapping_, simu_);
        movesSet(pk_, all_, mapping_, simu_);
        moveChoices(pk_, all_, mapping_, simu_);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanCancelMovesEvos(), simu_);
    }

    protected static NaSt pkPlayerEvoFighterSimulateStMoveCancel() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
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

    protected static NaSt pkPlayerEvoFighterSimulateStMoveCancel2() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
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

    protected static NaSt pkPlayerFighterSimulate() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSampleSkip(pk_, all_, mapping_, simu_);
        return oneFight(pk_, all_, mapping_, simu_);
    }

    protected static NaSt pkPlayerFighterSimulateComment() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSampleSkip(pk_, all_, mapping_, simu_);
        oneFight(pk_, all_, mapping_, simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanDisplayComments(),simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanHideComments(),simu_);
        return callSimulationBeanCommentsGet(simu_);
    }

    protected static NaSt pkPlayerFighterSimulateAfterFight() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSampleSkip(pk_, all_, mapping_, simu_);
        oneFight(pk_, all_, mapping_, simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanNextFight(),simu_);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanSelectPkAfterFight(), simu_);
    }

    protected static NaSt pkPlayerFighterSimulateAfterFightCancel() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSampleSkip(pk_, all_, mapping_, simu_);
        oneFight(pk_, all_, mapping_, simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanNextFight(),simu_);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanChangeFightWhileEnd(), simu_);
    }

    protected static NaSt pkPlayerFighterSimulateAfterFightCancel2() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSampleSkip(pk_, all_, mapping_, simu_);
        oneFight(pk_, all_, mapping_, simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanNextFight(),simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanChangeFightWhileEnd(), simu_);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanChangeFight(), simu_);
    }

    protected static NaSt pkPlayerFighterSimulateAfterFightCancel3() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSampleSkip(pk_, all_, mapping_, simu_);
        oneFight(pk_, all_, mapping_, simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanNextFight(),simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanChangeFightWhileEnd(), simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanChangeFight(), simu_);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanCancelMovesEvos(), simu_);
    }

    protected static NaSt pkPlayerFighterSimulateAfterFightCancel4() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
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

    protected static NaSt pkPlayerFighterSimulateAfterFightOne() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSampleSkip(pk_, all_, mapping_, simu_);
        oneFight(pk_, all_, mapping_, simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanNextFight(),simu_);
        callSimulationBeanSelectedPkSet(simu_,0);
        beforeDisplaying(simu_);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanSelectPkAfterFight(), simu_);
    }

    protected static NaSt pkPlayerFighterSimulateAfterFightOneValidate() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
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

    protected static NaSt pkPlayerFighterSimulateAfterFightCancelOne() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
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

    private static NaSt oneFight(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu) {
        fighterPositions(_pk, _all, _mapping, _simu);
        return transitSimu(_pk, _all, _mapping, new SimulationBeanSimulateFight(), moveChoices(_pk, _all, _mapping, _simu));
    }

    protected static NaSt pkPlayerFighterSimulateOneFight() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 1);
        foeTeamSample(pk_, all_, mapping_, simu_);
        playerTeamSampleSkip(pk_, all_, mapping_, simu_);
        fighterPositions(pk_, all_, mapping_, simu_);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanSimulateFight(), moveChoices(pk_, all_, mapping_, simu_));
    }

    protected static NaSt pkPlayerFighterSkipEvosStateBadNbCount() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        NaSt editing_ = editPkPlayer(pk_, all_, mapping_, simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        assertSame(editing_,chooseItemPkPlayer(I_BALL_TR,pk_, all_, mapping_,editing_));
        NaSt re_ = addMovePlayer(M_POK_01_TR, 0, pk_, all_, mapping_, editing_);
        callSelectLineMoveSelectedSet(elt(callEditPokemonBeanMovesGet(re_),0),true);
        NaSt afterDel_ = transitSimu(pk_, all_, mapping_, new EditPokemonBeanDeleteMoves(), re_);
        transitSimu(pk_, all_, mapping_,new EditPokemonBeanEdit(),afterDel_);
        pkTrainerSelectPkPlayerNameCycle(P_POK_04_TR,A_SIM_1, pk_, all_, mapping_, simu_,41);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanValidateFoeChoiceSkipEvolutions(), simu_);
    }

    protected static NaSt pkPlayerFighterSkipEvosStateEmpty() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanValidateFoeChoiceSkipEvolutions(), simu_);
    }

    protected static NaSt pkPlayerEvoFighterSimulateKo() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        validEvos(pk_, all_, mapping_,simu_);
        fighterPositions(pk_, all_, mapping_, simu_);
        movesSet(pk_, all_, mapping_, simu_);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanSimulateFight(), moveChoicesKo(pk_, all_, mapping_, simu_));
    }

    protected static NaSt pkPlayerEvoFighterSimulateKos() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSampleInv(pk_, all_, mapping_, simu_);
        playerTeamSampleInv(pk_, all_, mapping_, simu_);
        transitSimu(pk_, all_, mapping_,new SimulationBeanValidateEvolutions(),simu_);
        fighterPositions(pk_, all_, mapping_, simu_);
        movesSet(pk_, all_, mapping_, simu_);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanSimulateFight(), moveChoices(pk_, all_, mapping_, simu_));
    }

    private static NaSt moveChoices(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu) {
        moveChoice(0,0,0,0,_pk,_all,_mapping,_simu);
        moveChoice(1,0,0,1,_pk,_all,_mapping,_simu);
        moveChoice(0,1,0,0,_pk,_all,_mapping,_simu);
        return moveChoice(1,1,0,1,_pk,_all,_mapping,_simu);
    }

    private static NaSt moveChoicesKo(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu) {
        moveChoice(0,0,0,0,_pk,_all,_mapping,_simu);
        moveChoice(1,0,0,1,_pk,_all,_mapping,_simu);
        moveChoice(0,0,0,0,_pk,_all,_mapping,_simu);
        return moveChoice(1,0,0,1,_pk,_all,_mapping,_simu);
    }

    private static NaSt moveChoice(int _index, int _round, int _move, int _target, PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu) {
        return moveChoice(_index,_round,false,_move,_target,_pk,_all,_mapping,_simu);
    }
    private static NaSt moveChoice(int _index, int _round, boolean _allyChoice, int _move, int _target, PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu) {
        callSimulationBeanSelectedPkSet(_simu, _index);
        callSimulationBeanSelectedRoundSet(_simu,Long.toString(_round));
        beforeDisplaying(_simu);
        callSimulationBeanAllyChoiceSet(_simu, _allyChoice);
        callSimulationBeanSelectedMoveSet(_simu, _move);
        callSimulationBeanTargetSet(_simu, Long.toString(_target));
        return transitSimu(_pk, _all, _mapping, new SimulationBeanValidateMovesChoice(), _simu);
    }

    protected static NaSt pkPlayerEvoFightersSufficientFrontsFormMoveValidateMovesAllFightersKo() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
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

    protected static NaSt pkPlayerEvoFightersSufficientFrontsFormMoveValidateMovesAllFightersCancel() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
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

    private static NaSt movesSet(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu) {
        withoutAbility(0, _pk, _all, _mapping, _simu);
        withAbility(1,A_SIM_1, _pk, _all, _mapping, _simu);
        withoutAbility(2, _pk, _all, _mapping, _simu);
        withAbility(3,A_SIM_1, _pk, _all, _mapping, _simu);
        return transitSimu(_pk, _all, _mapping, new SimulationBeanValidateMovesSets(), _simu);
    }

    private static NaSt withAbility(int _index, String _ab, PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu) {
        callSimulationBeanSelectedPkSet(_simu, _index);
        beforeDisplaying(_simu);
        movesAbilities(_ab, _pk, _all, _mapping, _simu);
        movesAbilities("", _pk, _all, _mapping, _simu);
        return movesAbilities("", _pk, _all, _mapping, _simu);
    }

    private static NaSt withoutAbility(int _index, PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu) {
        callSimulationBeanSelectedPkSet(_simu, _index);
        beforeDisplaying(_simu);
        movesAbilities("", _pk, _all, _mapping, _simu);
        movesAbilities("", _pk, _all, _mapping, _simu);
        return movesAbilities("", _pk, _all, _mapping, _simu);
    }

    private static NaSt movesAbilities(String _ab, PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu) {
        callSimulationBeanCurrentAbilitySet(_simu, _ab);
        return transitSimu(_pk, _all, _mapping, new SimulationBeanValidateMoves(), _simu);
    }

    private static NaSt fighterPositions(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu) {
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

    private static NaSt changeFighterPosition(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu, int _index, String _round, String _place) {
        callSimulationBeanSelectedPkSet(_simu, _index);
        callSimulationBeanSelectedRoundSet(_simu, _round);
        callSimulationBeanPlaceFightSet(_simu, _place);
        return transitSimu(_pk, _all, _mapping, new SimulationBeanValidateFrontFighter(),_simu);
    }

    private static NaSt validEvos(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu) {
        validEvo(_pk, _all, _mapping, _simu,1);
        validEvo(_pk, _all, _mapping, _simu,3);
        return transitSimu(_pk, _all, _mapping,new SimulationBeanValidateEvolutions(),_simu);
    }

    protected static NaSt pkPlayerValidateEvoValidateThenCancel() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        playerTeamSample(pk_, all_, mapping_, simu_);
        callSimulationBeanSelectedPkSet(simu_,1);
        transitSimu(pk_, all_, mapping_,new SimulationBeanDisplayEvolutions(),simu_);
        callSimulationBeanChosenEvoSet(simu_,P_POK_03);
        callSimulationBeanLevelEvoSet(simu_,39);
        transitSimu(pk_, all_, mapping_,new SimulationBeanValidateEvo(),simu_);
        return transitSimu(pk_, all_, mapping_,new SimulationBeanCancelEvo(),simu_);
    }
    private static void validEvo(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu, int _index) {
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

    protected static NaSt pkPlayerValidateEvos() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        return playerTeamSample(pk_, all_, mapping_, simu_);
    }

    private static NaSt playerTeamSample(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu) {
        pkTrainerSelectPkPlayerNameCycle(P_POK_01_TR,A_SIM_1, _pk, _all, _mapping, _simu,41);
        pkTrainerSelectPkPlayerNameCycle(P_POK_02_TR,A_SIM_1, _pk, _all, _mapping, _simu,41);
        pkTrainerSelectPkPlayerNameCycle(P_POK_01_TR,A_SIM_1, _pk, _all, _mapping, _simu,41);
        pkTrainerSelectPkPlayerNameCycle(P_POK_02_TR,A_SIM_1, _pk, _all, _mapping, _simu,41);
        return transitSimu(_pk, _all, _mapping,new SimulationBeanValidateTeam(),_simu);
    }

    private static NaSt playerTeamSampleSkip(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu) {
        NaSt editing_ = editPkPlayer(_pk, _all, _mapping, _simu, P_POK_04_TR, A_SIM_1, 0, 41, TeamCrud.EDIT);
        assertSame(editing_,chooseItemPkPlayer(I_BALL_TR,_pk, _all, _mapping,editing_));
        assertSame(_simu,transitSimu(_pk, _all, _mapping,new EditPokemonBeanEdit(),editing_));
        pkTrainerSelectPkPlayerNameCycle(P_POK_01_TR,A_SIM_1, _pk, _all, _mapping, _simu,41);
        pkTrainerSelectPkPlayerNameCycle(P_POK_02_TR,A_SIM_1, _pk, _all, _mapping, _simu,41);
        pkTrainerSelectPkPlayerNameCycle(P_POK_02_TR,A_SIM_1, _pk, _all, _mapping, _simu,41);
        return transitSimu(_pk, _all, _mapping,new SimulationBeanValidateFoeChoiceSkipEvolutions(),_simu);
    }

    private static NaSt playerTeamSampleInv(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu) {
        pkTrainerSelectPkPlayerNameCycle(P_POK_01_TR,A_SIM_1, _pk, _all, _mapping, _simu,4);
        pkTrainerSelectPkPlayerNameCycle(P_POK_02_TR,A_SIM_1, _pk, _all, _mapping, _simu,4);
        pkTrainerSelectPkPlayerNameCycle(P_POK_01_TR,A_SIM_1, _pk, _all, _mapping, _simu,4);
        pkTrainerSelectPkPlayerNameCycle(P_POK_02_TR,A_SIM_1, _pk, _all, _mapping, _simu,4);
        return transitSimu(_pk, _all, _mapping,new SimulationBeanValidateTeam(),_simu);
    }

    protected static NaSt pkPlayerValidateEvosKo() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        return transitSimu(pk_,all_,mapping_,new SimulationBeanValidateTeam(),simu_);
    }
    private static NaSt pkPlAb(String _name, PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu) {
        NaSt addPk_ = goToAddPkPlayer(_pk, _all, _mapping, _simu);
        callAddPokemonBeanTypedNameSet(addPk_, _name);
        NaSt afSearch_ = transitSimu(_pk, _all, _mapping, new AddPokemonBeanSearch(), addPk_);
        callAddPokemonBeanAbilitySet(afSearch_,A_SIM_2);
        callAddPokemonBeanLevelSet(afSearch_,40);
        genderSetPl(afSearch_);
        return afSearch_;
    }

    protected static NaSt pkPlayerSelectPkNameAdded(String _name) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
//        Struct addPk_ = goToAddPkPlayer(pk_, all_, mapping_, simu_);
//        callAddPokemonBeanTypedNameSet(addPk_,_name);
//        Struct afSearch_ = transitSimu(pk_, all_, mapping_, new AddPokemonBeanSearch(), addPk_);
        return transitSimu(pk_, all_, mapping_, new AddPokemonBeanAdd(),pkPlAb(_name,pk_,all_,mapping_,simu_));
    }

    protected static NaSt pkPlayerSelectPkNameQuickAdded() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        NaSt addPk_ = goToAddPkPlayer(pk_, all_, mapping_, simu_);
//        callAddPokemonBeanTypedNameSet(addPk_,_name);
//        Struct afSearch_ = transitSimu(pk_, all_, mapping_, new AddPokemonBeanSearch(), addPk_);
        return transitSimu(pk_, all_, mapping_, new AddPokemonBeanAdd(),addPk_);
    }
    protected static NaSt pkPlayerSelectPkName(String _name) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        NaSt addPk_ = goToAddPkPlayer(pk_, all_, mapping_, simu_);
        callAddPokemonBeanTypedNameSet(addPk_,_name);
        return transitSimu(pk_,all_,mapping_,new AddPokemonBeanSearch(),addPk_);
    }
    protected static NaSt pkPlayerSelectPkHasEvo(String _name) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        NaSt addPk_ = goToAddPkPlayer(pk_, all_, mapping_, simu_);
        callAddPokemonBeanHasEvoSet(addPk_,_name);
        return transitSimu(pk_,all_,mapping_,new AddPokemonBeanSearch(),addPk_);
    }
    protected static NaSt pkPlayerSelectPkIsEvo(String _name) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        NaSt addPk_ = goToAddPkPlayer(pk_, all_, mapping_, simu_);
        callAddPokemonBeanIsEvoSet(addPk_,_name);
        return transitSimu(pk_,all_,mapping_,new AddPokemonBeanSearch(),addPk_);
    }
    protected static NaSt pkPlayerSelectPkIsLeg(String _name) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        NaSt addPk_ = goToAddPkPlayer(pk_, all_, mapping_, simu_);
        callAddPokemonBeanIsLegSet(addPk_,_name);
        return transitSimu(pk_,all_,mapping_,new AddPokemonBeanSearch(),addPk_);
    }
    protected static NaSt pkPlayerSelectPkRow(int _row) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        NaSt addPk_ = goToAddPkPlayer(pk_, all_, mapping_, simu_);
        callAddPokemonBeanTypedNameSet(addPk_,"");
        NaSt rSe_ = transitSimu(pk_, all_, mapping_, new AddPokemonBeanSearch(), addPk_);
        return transitSimu(pk_,all_,mapping_,new AddPokemonBeanClickLink(),rSe_,_row);
    }
    protected static NaSt pkPlayerSelectPkType(String _type, boolean _wholeWord) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        NaSt addPk_ = goToAddPkPlayer(pk_, all_, mapping_, simu_);
        callAddPokemonBeanTypedTypeSet(addPk_,_type);
        callAddPokemonBeanWholeWordSet(addPk_,_wholeWord);
        return transitSimu(pk_,all_,mapping_,new AddPokemonBeanSearch(),addPk_);
    }
    protected static NaSt pkPlayer() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSample(pk_, all_, mapping_, simu_);
        return goToAddPkPlayer(pk_,all_,mapping_,simu_);
    }

    private static void foeTeamsSample(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu) {
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

    private static void foeTeamSample(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu) {
        selectTeam(_simu,0);
        pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, _pk, _all, _mapping, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_01_TR, A_SIM_1_TR, _pk, _all, _mapping, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_02_TR, A_SIM_1_TR, _pk, _all, _mapping, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_03_TR, A_SIM_1_TR, _pk, _all, _mapping, _simu, 4);
        setMult(_simu,2);
        transitSimu(_pk, _all, _mapping,new SimulationBeanValidateFoeChoiceFree(), _simu);
    }

    private static void foeTeamsSampleInv(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu) {
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
    private static void setMult(NaSt _simu, int _value) {
        callSimulationBeanEnvironmentSet(_simu, EnvironmentType.ROAD.getEnvName());
        callSimulationBeanMultiplicitySet(_simu,_value);
        beforeDisplaying(_simu);
    }

    private static NaSt goToSelectPk(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _struct) {
        return transitSimu(_pk, _all, _mapping, new EditTrainerPokemonBeanChooseName(), _struct);
    }
    private static NaSt goToSelectAb(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _struct) {
        return transitSimu(_pk, _all, _mapping, new EditTrainerPokemonBeanChooseAbility(), _struct);
    }
    private static NaSt goToSelectIt(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _struct) {
        return transitSimu(_pk, _all, _mapping, new EditTrainerPokemonBeanChooseItem(), _struct);
    }
    private static NaSt goToSelectItPlayer(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _struct) {
        return transitSimu(_pk, _all, _mapping, new EditPokemonBeanChooseItem(), _struct);
    }
    private static NaSt goToSetMovesTrainer(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _struct) {
        return transitSimu(_pk, _all, _mapping, new EditTrainerPokemonBeanAddMoves(), _struct);
    }
    private static NaSt simu(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, int _nbTeam) {
        NaSt simu_ = init(_nbTeam, _pk, _all, _mapping);
        transitSimu(_pk,_all,_mapping, new SimulationBeanValidateDiffChoice(), simu_);
        return simu_;
    }

    protected static NaSt goToAddPkTrainer(PokemonStandards _stds, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu) {
        return transitSimu(_stds,_all,_mapping,new SimulationBeanAddPkTrainer(),_simu);
    }

    protected static NaSt goToAddPkPlayer(PokemonStandards _stds, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu) {
        return transitSimu(_stds,_all,_mapping,new SimulationBeanAdd(),_simu);
    }

    public static NaSt transitSimu(PokemonStandards _stds, StringMap<NaSt> _all, StringMap<String> _mapping, NatCaller _caller, NaSt _first, long... _args) {
        String url_ = navigateData(_caller, _first, _args);
        NaSt dest_ = _all.getVal(_mapping.getVal(url_));
        setFormsBy(_stds,dest_,_first);
        beforeDisplaying(dest_);
        return dest_;
    }
    protected static Rate integration() {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = init(pk_, all_, mapping_);
        callSimulationBeanNbTeamsSet(simu_, 2);
        callRate(new DifficultyCommonBeanWinTrainerExpSet(),all_.getVal(AikiBeansGameStd.BEAN_DIFFICULTY_COMMON),Rate.newRate("5/7"));
        NaSt result_ = callDifficultyBeanComGet(transitSimu(pk_,all_,mapping_, new SimulationBeanValidateDiffChoice(), simu_));
        return ((DifficultyCommonStruct)result_).getDifficultyCommon().getWinTrainerExp();
    }

    public static NaSt transitSimuRem(PokemonStandards _stds, NatCaller _caller, NaSt _first, long... _args) {
        String url_ = navigateData(_caller, _first, _args);
        assertTrue(url_.isEmpty());
//        Struct dest_ = _all.getVal(_mapping.getVal(url_));
        setFormsBy(_stds, _first,_first);
        beforeDisplaying(_first);
        return _first;
    }
    public static StringMap<NaSt> beanToSimu(PkData _pk) {
        StringMap<NaSt> map_ = new StringMap<NaSt>();
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
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_INDEX_HTML,AikiBeansStd.BEAN_WELCOME);
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML,AikiBeansSimulationStd.BEAN_SIMULATION);
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_ADDPOKEMON_HTML,AikiBeansSimulationStd.BEAN_ADDPOKEMON);
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SELECTABILITY_HTML,AikiBeansSimulationStd.BEAN_SELECTABILITY);
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SELECTPOKEMON_HTML,AikiBeansSimulationStd.BEAN_SELECTPOKEMON);
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SELECTITEM_HTML,AikiBeansSimulationStd.BEAN_SELECTITEM);
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMON_HTML,AikiBeansSimulationStd.BEAN_EDITPOKEMON);
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONMOVES_HTML,AikiBeansSimulationStd.BEAN_EDITPOKEMONMOVES);
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML,AikiBeansSimulationStd.BEAN_EDITTRAINERPOKEMON);
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATIONLEVEL_HTML,AikiBeansSimulationStd.BEAN_LEVEL_SIMU);
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
        facade_.getData().getExpGrowth().addEntry(ExpType.E,VAR_PREFIX+ MessagesDataBaseConstants.DEF_NIVEAU);
        facade_.getData().getRates().put(DifficultyWinPointsFight.TRES_FACILE, "1");
        facade_.getData().getRates().put(DifficultyWinPointsFight.FACILE, "1");
        facade_.getData().getRates().put(DifficultyWinPointsFight.DIFFICILE, "1");
        facade_.getData().getRates().put(DifficultyWinPointsFight.TRES_DIFFICILE, "1");
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.CONSTANT_MIN, new LawNumber(lawOne(),0));
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.CROISSANT, new LawNumber(lawOne(),1));
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.UNIFORME, new LawNumber(lawOne(),2));
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.DECROISSANT, new LawNumber(lawOne(),3));
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.CONSTANT_MAX, new LawNumber(lawOne(),4));
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

    protected static NaSt pkPlayerValidateEvosSelectTwo() {
        PkData pk_ = pkDataByFacade(dbLight());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        return evolutions(pk_, all_, mapping_, simu_);
    }


    protected static NaSt pkPlayerValidateEvosSelectTree() {
        PkData pk_ = pkDataByFacade(dbLightSec());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        return evolutionsTree(pk_, all_, mapping_, simu_);
    }

    protected static NaSt pkPlayerValidateEvosSelectTwoOnce() {
        PkData pk_ = pkDataByFacade(dbLight());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        evolutions(pk_, all_, mapping_, simu_);
        callSimulationBeanChosenEvoSet(simu_,P_POK_01);
        callSimulationBeanLevelEvoSet(simu_,41);
        return transitSimu(pk_, all_, mapping_,new SimulationBeanValidateEvo(),simu_);
    }

    protected static NaSt pkPlayerValidateEvosSelectTwoTwice() {
        PkData pk_ = pkDataByFacade(dbLight());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        evolutions(pk_, all_, mapping_, simu_);
        callSimulationBeanChosenEvoSet(simu_,P_POK_01);
        callSimulationBeanLevelEvoSet(simu_,41);
        transitSimu(pk_, all_, mapping_,new SimulationBeanValidateEvo(),simu_);
        callSimulationBeanChosenEvoSet(simu_,P_POK_02);
        callSimulationBeanLevelEvoSet(simu_,41);
        return transitSimu(pk_, all_, mapping_,new SimulationBeanValidateEvo(),simu_);
    }

    protected static NaSt pkPlayerValidateEvosSelectTwoThreeTimes() {
        PkData pk_ = pkDataByFacade(dbLight());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
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

    private static NaSt evolutions(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu) {
        foeTeamsSampleLight(_pk, _all, _mapping, _simu);
        playerTeamSampleLight(_pk, _all, _mapping, _simu);
        callSimulationBeanSelectedPkSet(_simu,0);
        return transitSimu(_pk, _all, _mapping, new SimulationBeanDisplayEvolutions(), _simu);
    }

    private static NaSt evolutionsTree(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu) {
        foeTeamsSampleLightTree(_pk, _all, _mapping, _simu);
        playerTeamSampleLight(_pk, _all, _mapping, _simu);
        callSimulationBeanSelectedPkSet(_simu,0);
        return transitSimu(_pk, _all, _mapping, new SimulationBeanDisplayEvolutions(), _simu);
    }

    private static void foeTeamsSampleLight(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu) {
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


    private static void foeTeamsSampleLightTree(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu) {
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

    private static NaSt playerTeamSampleLight(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu) {
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
        facade_.getData().getExpGrowth().addEntry(ExpType.E,VAR_PREFIX+ MessagesDataBaseConstants.DEF_NIVEAU);
        facade_.getData().getRates().put(DifficultyWinPointsFight.TRES_FACILE, "1");
        facade_.getData().getRates().put(DifficultyWinPointsFight.FACILE, "1");
        facade_.getData().getRates().put(DifficultyWinPointsFight.DIFFICILE, "1");
        facade_.getData().getRates().put(DifficultyWinPointsFight.TRES_DIFFICILE, "1");
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.CONSTANT_MIN, new LawNumber(lawOne(),0));
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.CROISSANT, new LawNumber(lawOne(),1));
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.UNIFORME, new LawNumber(lawOne(),2));
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.DECROISSANT, new LawNumber(lawOne(),3));
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.CONSTANT_MAX, new LawNumber(lawOne(),4));
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
        facade_.getData().getExpGrowth().addEntry(ExpType.E,VAR_PREFIX+ MessagesDataBaseConstants.DEF_NIVEAU);
        facade_.getData().getRates().put(DifficultyWinPointsFight.TRES_FACILE, "1");
        facade_.getData().getRates().put(DifficultyWinPointsFight.FACILE, "1");
        facade_.getData().getRates().put(DifficultyWinPointsFight.DIFFICILE, "1");
        facade_.getData().getRates().put(DifficultyWinPointsFight.TRES_DIFFICILE, "1");
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.CONSTANT_MIN, new LawNumber(lawOne(),0));
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.CROISSANT, new LawNumber(lawOne(),1));
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.UNIFORME, new LawNumber(lawOne(),2));
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.DECROISSANT, new LawNumber(lawOne(),3));
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.CONSTANT_MAX, new LawNumber(lawOne(),4));
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

    protected static NaSt pkPlayerFighterSimulateAfterFightOneLight() {
        PkData pk_ = pkDataByFacade(dbLightThree());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSampleVeryLight(pk_, all_, mapping_, simu_);
        playerTeamSampleLightSkip(pk_, all_, mapping_, simu_);
        fighterPositionsLight(pk_, all_, mapping_, simu_);
        transitSimu(pk_, all_, mapping_, new SimulationBeanSimulateFight(), moveChoicesLight(pk_, all_, mapping_, simu_));
        transitSimu(pk_, all_, mapping_, new SimulationBeanNextFight(),simu_);
        callSimulationBeanSelectedPkSet(simu_,0);
        beforeDisplaying(simu_);
        return transitSimu(pk_, all_, mapping_, new SimulationBeanSelectPkAfterFight(), simu_);
    }

    private static NaSt moveChoicesLight(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu) {
        return moveChoice(0,0,0,0,_pk,_all,_mapping,_simu);
    }
    private static void foeTeamsSampleVeryLight(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu) {
        selectTeam(_simu,0);
        pkTrainerSelectPkNameCycle(false, P_POK_03_TR, A_SIM_1_TR, _pk, _all, _mapping, _simu, 4);
        selectTeam(_simu,1);
        pkTrainerSelectPkNameCycle(false,P_POK_03_TR,A_SIM_2_TR, _pk, _all, _mapping, _simu, 5);
        transitSimu(_pk, _all, _mapping,new SimulationBeanValidateFoeChoiceFree(), _simu);
    }

    private static NaSt playerTeamSampleLightSkip(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu) {
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
        facade_.getData().getExpGrowth().addEntry(ExpType.E,VAR_PREFIX+ MessagesDataBaseConstants.DEF_NIVEAU);
        facade_.getData().getRates().put(DifficultyWinPointsFight.TRES_FACILE, "1");
        facade_.getData().getRates().put(DifficultyWinPointsFight.FACILE, "1");
        facade_.getData().getRates().put(DifficultyWinPointsFight.DIFFICILE, "1");
        facade_.getData().getRates().put(DifficultyWinPointsFight.TRES_DIFFICILE, "1");
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.CONSTANT_MIN, new LawNumber(lawOne(),0));
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.CROISSANT, new LawNumber(lawOne(),1));
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.UNIFORME, new LawNumber(lawOne(),2));
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.DECROISSANT, new LawNumber(lawOne(),3));
        facade_.getData().getLawsDamageRate().put(DifficultyModelLaw.CONSTANT_MAX, new LawNumber(lawOne(),4));
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

    private static NaSt fighterPositionsLight(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu) {
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
        pkm_.setLevel( 7);
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
        data_.getLitterals().getVal(EN).addEntry(MessagesDataBaseConstants.DEF_TEMPS_TOUR, TAB+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +TAB+TIME);
        data_.getMiniPk().addEntry(P_POK_00, instance(IMG_00));
        data_.getMiniPk().addEntry(P_POK_01, instance(IMG_01));
        data_.getMiniPk().addEntry(P_POK_02, instance(IMG_02));
        data_.getMiniPk().addEntry(P_POK_03, instance(IMG_03));
        data_.getMiniPk().addEntry(P_POK_04, instance(IMG_04));
        data_.getMiniPk().addEntry(P_POK_05, instance(IMG_05));
        data_.getMiniPk().addEntry(P_POK_06, instance(IMG_06));
        data_.getMiniPk().addEntry(P_POK_07, instance(IMG_07));
        data_.getMiniPk().addEntry(P_POK_08, instance(IMG_08));
        data_.getMiniPk().addEntry(P_POK_09, instance(IMG_09));
        data_.getMiniItems().addEntry(I_NOTHING, instance(IMG_NOTHING));
        data_.getMiniItems().addEntry(I_MULT_EXP, instance(IMG_MULTI_EXP));
        data_.getMiniItems().addEntry(I_STONE, instance(IMG_STONE));
        data_.getMiniItems().addEntry(I_BALL, instance(IMG_BALL));
    }
    private static DamagingMoveData power(String _type, String _cat, String _power) {
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        move_.setAccuracy("1");
        move_.setPp(15);
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
        move_.setPp(5);
        move_.setAccuracy(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        move_.setTypes(new StringList(T_SIM_2));
        move_.setBoostedTypes(new StringList(T_SIM_2));
        move_.setCategory(C_SIM_2);
        move_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectDamage eff_ = Instances.newEffectDamage();
        eff_.setPower(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        eff_.patch();
        target(move_,eff_);
        return move_;
    }
    private static StatusMoveData noEff() {
        StatusMoveData st_ = Instances.newStatusMoveData();
        st_.setPp(1);
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
        pk_.setHiddenMoves(Ints.newList());
        pk_.setTechnicalMoves(Ints.newList());
        pk_.setMoveTutors(new StringList(M_POK_03));
        return pk_;
    }

    private static PokemonData specPk(String _base, String _evo, int _lev, CustList<LevelMove> _moves) {
        PokemonData pk_ = pk(new StringList("__"), GenderRepartition.NO_GENDER);
        pk_.setTypes(new StringList(T_SIM_1));
        pk_.setAbilities(new StringList(A_SIM_1,A_SIM_2));
        pk_.setBaseEvo(_base);
        EvolutionLevelSimple e_ = Instances.newEvolutionLevelSimple();
        e_.setLevel( _lev);
        pk_.getEvolutions().addEntry(_evo, e_);
        pk_.setLevMoves(_moves);
        pk_.setExpEvo(ExpType.E);
        pk_.setHiddenMoves(Ints.newList());
        pk_.setTechnicalMoves(Ints.newList());
        pk_.setMoveTutors(new StringList(M_POK_03));
        return pk_;
    }

    private static PokemonData specPk(String _base, String _evo, int _lev, String _evo2, int _lev2, CustList<LevelMove> _moves) {
        PokemonData pk_ = pk(new StringList("__"), GenderRepartition.NO_GENDER);
        pk_.setTypes(new StringList(T_SIM_1));
        pk_.setAbilities(new StringList(A_SIM_1,A_SIM_2));
        pk_.setBaseEvo(_base);
        EvolutionLevelSimple e_ = Instances.newEvolutionLevelSimple();
        e_.setLevel( _lev);
        pk_.getEvolutions().addEntry(_evo, e_);
        EvolutionLevelSimple e2_ = Instances.newEvolutionLevelSimple();
        e2_.setLevel( _lev2);
        pk_.getEvolutions().addEntry(_evo2, e2_);
        pk_.setLevMoves(_moves);
        pk_.setExpEvo(ExpType.E);
        pk_.setHiddenMoves(Ints.newList());
        pk_.setTechnicalMoves(Ints.newList());
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
        pk_.setHiddenMoves(Ints.newList());
        pk_.setTechnicalMoves(Ints.newList());
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
        pk_.setHiddenMoves(Ints.newList());
        pk_.setTechnicalMoves(Ints.newList());
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
        pk_.setHiddenMoves(Ints.newList());
        pk_.setTechnicalMoves(Ints.newList());
        pk_.setMoveTutors(new StringList(M_POK_03));
        return pk_;
    }
    private static CustList<LevelMove> withLearn(CustList<LevelMove> _set, int _level, String _move) {
        _set.add(new LevelMove(_level,_move));
        return _set;
    }
    protected static NaSt chooseTrainer() {
        PkData pk_ = pkDataByFacade(dbFull());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        return simu(pk_, all_, mapping_, 0);
    }
    protected static NaSt chooseTrainerLevel(int _place, int _level) {
        PkData pk_ = pkDataByFacade(dbFull());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 0);
        return transitSimu(pk_,all_,mapping_,new SimulationBeanClickLevel(),simu_,_place,_level);
    }
    protected static NaSt chooseTrainerLevelZero(int _place) {
        PkData pk_ = pkDataByFacade(dbFull());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 0);
        return transitSimu(pk_,all_,mapping_,new SimulationBeanClickLevelZero(),simu_,_place);
    }
    protected static NaSt chooseTrainerLevel(int _level, int _noFight, int _tile) {
        PkData pk_ = pkDataByFacade(dbFull());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 0);
        NaSt sel_ = transitSimu(pk_, all_, mapping_, new SimulationBeanClickLevel(), simu_, 2, _level);
        callSimulationLevelBeanNoFightSet(sel_,_noFight);
        return transitSimu(pk_,all_,mapping_,new SimulationLevelBeanClickTile(),sel_,_tile);
    }
    protected static NaSt chooseTrainerLevelZero(int _place, int _noFight, int _tile) {
        PkData pk_ = pkDataByFacade(dbFull());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 0);
        NaSt sel_ = transitSimu(pk_,all_,mapping_,new SimulationBeanClickLevelZero(),simu_,_place);
        callSimulationLevelBeanNoFightSet(sel_,_noFight);
        return transitSimu(pk_,all_,mapping_,new SimulationLevelBeanClickTile(),sel_,_tile);
    }
    protected static NaSt chooseTrainerLevelDualValidate() {
        PkData pk_ = pkDataByFacade(dbFull());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 0);
        NaSt sel_ = transitSimu(pk_, all_, mapping_, new SimulationBeanClickLevel(), simu_, 2, 0);
        callSimulationLevelBeanNoFightSet(sel_,0);
        transitSimu(pk_,all_,mapping_,new SimulationLevelBeanClickTile(),sel_,1);
        return transitSimu(pk_,all_,mapping_,new SimulationBeanValidateFoeChoice(),simu_);
    }
    protected static NaSt chooseTrainerLevelDualValidateKo() {
        PkData pk_ = pkDataByFacade(dbFull());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 0);
        return transitSimu(pk_,all_,mapping_,new SimulationBeanValidateFoeChoice(),simu_);
    }
    protected static NaSt simuLeagueReal() {
        PkData pk_ = pkDataByFacade(dbFull());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 0);
        transitSimu(pk_, all_, mapping_, new SimulationBeanClickLevel(), simu_, 3, 0);
        transitSimu(pk_, all_, mapping_, new SimulationBeanValidateFoeChoice(), simu_);
        simpleTeam(pk_, all_, mapping_, simu_);
        return simu_;
    }
    protected static NaSt simuLeagueRealSec() {
        PkData pk_ = pkDataByFacade(dbFull());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 0);
        transitSimu(pk_, all_, mapping_, new SimulationBeanClickLevel(), simu_, 3, 1);
        transitSimu(pk_, all_, mapping_, new SimulationBeanValidateFoeChoice(), simu_);
        simpleTeamLight(pk_, all_, mapping_, simu_);
        return simu_;
    }
    protected static NaSt simuLeagueVirtual() {
        PkData pk_ = pkDataByFacade(dbFull());
        StringMap<NaSt> all_ = beanToSimu(pk_);
        StringMap<String> mapping_ = mappingToSimu();
        NaSt simu_ = simu(pk_, all_, mapping_, 2);
        foeTeamsSampleSec(pk_, all_, mapping_, simu_);
        simpleTeam(pk_, all_, mapping_, simu_);
        return simu_;
    }
    private static void foeTeamsSampleSec(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu) {
        selectTeam(_simu,0);
        pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, _pk, _all, _mapping, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_01_TR, A_SIM_2_TR, _pk, _all, _mapping, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_02_TR, A_SIM_1_TR, _pk, _all, _mapping, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_03_TR, A_SIM_2_TR, _pk, _all, _mapping, _simu, 4);
        setMult(_simu,2);
        selectTeam(_simu,1);
        pkTrainerSelectPkNameCycle(false,P_POK_04_TR,A_SIM_1_TR, _pk, _all, _mapping, _simu, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_05_TR,A_SIM_2_TR, _pk, _all, _mapping, _simu, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_06_TR,A_SIM_1_TR, _pk, _all, _mapping, _simu, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_07_TR,A_SIM_2_TR, _pk, _all, _mapping, _simu, 5);
        setMult(_simu,2);
        transitSimu(_pk, _all, _mapping,new SimulationBeanValidateFoeChoiceFree(), _simu);
    }
    private static void simpleTeam(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu) {
        pk(_pk, _all, _mapping, _simu,0);
        pk(_pk, _all, _mapping, _simu,1);
        transitSimu(_pk,_all,_mapping,new SimulationBeanValidateFoeChoiceSkipEvolutions(),_simu);
        changeFighterPosition(_pk, _all, _mapping, _simu,0,"0","0");
        changeFighterPosition(_pk, _all, _mapping, _simu,1,"0","1");
        changeFighterPosition(_pk, _all, _mapping, _simu,0,"1","0");
        changeFighterPosition(_pk, _all, _mapping, _simu,1,"1","1");
        transitSimu(_pk, _all, _mapping, new SimulationBeanValidateFrontFighters(), _simu);
        moveChoice(0,0,0,0,_pk,_all,_mapping,_simu);
        moveChoice(1,0,0,1,_pk,_all,_mapping,_simu);
        moveChoice(0,1,0,0,_pk,_all,_mapping,_simu);
        moveChoice(1,1,0,1,_pk,_all,_mapping,_simu);
        transitSimu(_pk, _all, _mapping, new SimulationBeanSimulateFight(),_simu);
        transitSimu(_pk, _all, _mapping, new SimulationBeanNextFight(),_simu);
        transitSimu(_pk, _all, _mapping, new SimulationBeanValidateMovesAfterFight(),_simu);
        transitSimu(_pk,_all,_mapping,new SimulationBeanValidateFoeChoiceSkipEvolutions(),_simu);
        changeFighterPosition(_pk, _all, _mapping, _simu,0,"0","0");
        changeFighterPosition(_pk, _all, _mapping, _simu,1,"0","1");
        changeFighterPosition(_pk, _all, _mapping, _simu,0,"1","0");
        changeFighterPosition(_pk, _all, _mapping, _simu,1,"1","1");
        transitSimu(_pk, _all, _mapping, new SimulationBeanValidateFrontFighters(), _simu);
        moveChoice(0,0,0,0,_pk,_all,_mapping,_simu);
        moveChoice(1,0,0,1,_pk,_all,_mapping,_simu);
        moveChoice(0,1,0,0,_pk,_all,_mapping,_simu);
        moveChoice(1,1,0,1,_pk,_all,_mapping,_simu);
        transitSimu(_pk, _all, _mapping, new SimulationBeanSimulateFight(),_simu);
    }
    private static void simpleTeamLight(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu) {
        pk(_pk, _all, _mapping, _simu,0);
        pk(_pk, _all, _mapping, _simu,1);
        transitSimu(_pk,_all,_mapping,new SimulationBeanValidateFoeChoiceSkipEvolutions(),_simu);
        changeFighterPosition(_pk, _all, _mapping, _simu,0,"0","0");
        changeFighterPosition(_pk, _all, _mapping, _simu,1,"0","1");
        changeFighterPosition(_pk, _all, _mapping, _simu,0,"1","0");
        changeFighterPosition(_pk, _all, _mapping, _simu,1,"1","1");
        transitSimu(_pk, _all, _mapping, new SimulationBeanValidateFrontFighters(), _simu);
        moveChoice(0,0,0,0,_pk,_all,_mapping,_simu);
        moveChoice(1,0,0,1,_pk,_all,_mapping,_simu);
        moveChoice(0,1,0,0,_pk,_all,_mapping,_simu);
        moveChoice(1,1,0,1,_pk,_all,_mapping,_simu);
        transitSimu(_pk, _all, _mapping, new SimulationBeanSimulateFight(),_simu);
    }

    private static void pk(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu, int _teamIndex) {
        NaSt editing_ = editPkPlayer(_pk, _all, _mapping, _simu, P_POK_01_TR, A_SIM_1, _teamIndex, 41, TeamCrud.EDIT);
        assertSame(editing_,chooseItemPkPlayer(I_BALL_TR, _pk, _all, _mapping,editing_));
        NaSt re_ = addMovePlayer(M_POK_01_TR, 0, _pk, _all, _mapping, editing_);
        callSelectLineMoveSelectedSet(elt(callEditPokemonBeanMovesGet(re_),0),true);
        NaSt afterDel_ = transitSimu(_pk, _all, _mapping, new EditPokemonBeanDeleteMoves(), re_);
        NaSt af_ = transitSimu(_pk, _all, _mapping, new EditPokemonBeanEdit(), afterDel_);
        assertSame(af_, _simu);
    }

    //    protected static Struct simuLeagueReal() {
//        PkData pk_ = pkDataByFacade(dbFull());
//        StringMap<Struct> all_ = beanToSimu(pk_);
//        StringMap<String> mapping_ = mappingToSimu();
//        Struct simu_ = simu(pk_, all_, mapping_, 2);
//        foeTeamsSample(pk_, all_, mapping_, simu_);
//        Struct editing_ = editPkPlayer(pk_, all_, mapping_, simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
//        assertSame(editing_,chooseItemPkPlayer(I_BALL_TR,pk_, all_, mapping_,editing_));
//        Struct re_ = addMovePlayer(M_POK_01_TR, 0, pk_, all_, mapping_, editing_);
//        callSelectLineMoveSelectedSet(elt(callEditPokemonBeanMovesGet(re_),0),true);
//        Struct afterDel_ = transitSimu(pk_, all_, mapping_, new EditPokemonBeanDeleteMoves(), re_);
//        return transitSimu(pk_, all_, mapping_,new EditPokemonBeanEdit(),afterDel_);
//    }
    private static FacadeGame dbFull() {
        FacadeGame d_ = db();
        d_.getMap().addPlace(city());
        d_.getMap().addPlace(city(trGymTrainer(),trGymLeader()));
        d_.getMap().addPlace(cave());
        d_.getMap().addPlace(league());
        d_.getData().getTrainers().addEntry(DUAL,instance(line(IMG_SINGLE,IMG_DUAL1)));
        d_.getData().getTrainers().addEntry(SINGLE,instance(IMG_SINGLE));
        d_.getData().getPeople().addEntry(DUAL_1,instance(IMG_DUAL1));
        d_.getData().getPeople().addEntry(DUAL_2,instance(IMG_DUAL2));
        d_.getData().getPeople().addEntry(SI,instance(IMG_SI));
        d_.getData().getPeople().addEntry(NULL_REF,instance(IMG_ITEM));
        d_.getData().getImages().addEntry(NULL_REF,instance(IMG_ITEM));
        d_.getData().getLinks().addEntry(NULL_REF,instance(IMG_ITEM));
        return d_;
    }
    private static City city(GymTrainer _tr, GymLeader _leader) {
        City c_ = city();
        c_.setName(PL_2);
        Gym g_ = Instances.newGym();
        sqThree(g_.getIndoor());
        g_.getIndoor().getGymTrainers().addEntry(newPoint(0,0),_tr);
        g_.getIndoor().setGymLeader(_leader);
        g_.getIndoor().setGymLeaderCoords(newPoint(2,0));
        c_.getBuildings().addEntry(newPoint(2,0), g_);
        return c_;
    }
    private static City city() {
        City c_ = Instances.newCity();
        c_.setName(PL_1);
        sqThree(c_.getLevel());
        PokemonCenter center_ = Instances.newPokemonCenter();
        sqThree(center_.getLevel());
        c_.getBuildings().addEntry(newPoint(0,0), center_);
        return c_;
    }
    private static Cave cave() {
        Cave c_ = Instances.newCave();
        c_.setName(PL_3);
        LevelCave first_ = Instances.newLevelCave();
        sqThree(first_);
        first_.getDualFights().addEntry(newPoint(2,0),dual());
        c_.getLevels().add(first_);
        LevelCave sec_ = Instances.newLevelCave();
        sqThree(sec_);
        sec_.getCharacters().addEntry(newPoint(1,0),trMult());
        DealerItem dOne_ = Instances.newDealerItem();
        dOne_.getItems().add(I_BALL);
        sec_.getCharacters().addEntry(newPoint(1,1), dOne_);
        c_.getLevels().add(sec_);
        return c_;
    }
    protected static League league() {
        League l_ = Instances.newLeague();
        l_.setName(PL_4);
        LevelLeague one_ = Instances.newLevelLeague();
        one_.setTrainer(trLeagueOne());
        sqThree(one_);
        one_.setTrainerCoords(newPoint(1,1));
        one_.setAccessPoint(newPoint(1,2));
        one_.setNextLevelTarget(newPoint(1,0));
        l_.getRooms().add(one_);
        LevelLeague two_ = Instances.newLevelLeague();
        two_.setTrainer(trLeagueTwo());
        sqThree(two_);
        two_.setAccessPoint(newPoint(1,2));
        two_.setTrainerCoords(newPoint(1,1));
        l_.getRooms().add(two_);
        l_.setAccessCoords(newCoords(2,0,0,0));
        return l_;
    }
    private static void sqThree(Level _l) {
        Block bl_ = Instances.newBlock();
        bl_.setHeight(1);
        bl_.setWidth(1);
        bl_.setTileFileName("");
        _l.getBlocks().addEntry(newPoint(0,0),bl_);
        _l.getBlocks().addEntry(newPoint(0,1),bl_);
        _l.getBlocks().addEntry(newPoint(0,2),bl_);
        _l.getBlocks().addEntry(newPoint(1,0),bl_);
        _l.getBlocks().addEntry(newPoint(1,1),bl_);
        _l.getBlocks().addEntry(newPoint(1,2),bl_);
        _l.getBlocks().addEntry(newPoint(2,0),bl_);
        _l.getBlocks().addEntry(newPoint(2,1),bl_);
        _l.getBlocks().addEntry(newPoint(2,2),bl_);
    }
    protected static DualFight dual() {
        DualFight d_ = Instances.newDualFight();
        d_.setNames(new StringList(D_T_1, D_T_2));
        d_.getFoeTrainer().setImageMaxiFileName(DUAL);
        d_.getFoeTrainer().setImageMiniFileName(DUAL_1);
        d_.getFoeTrainer().setImageMiniSecondTrainerFileName(DUAL_2);
        d_.setPt(newPoint(1,0));
        d_.getAlly().getTeam().add(wpOne(P_POK_02,A_SIM_1,18));
        d_.getAlly().getTeam().add(wpTwo(P_POK_03,A_SIM_2,19));
        d_.getFoeTrainer().getTeam().add(wpOne(P_POK_00,A_SIM_2,18));
        d_.getFoeTrainer().getTeam().add(wpTwo(P_POK_01,A_SIM_1,19));
        d_.getFoeTrainer().setReward( 25);
        return d_;
    }
    protected static TrainerLeague trLeagueOne() {
        TrainerLeague tmf_ = Instances.newTrainerLeague();
        tmf_.setName(T_L_1);
        tmf_.setImageMaxiFileName(SINGLE);
        tmf_.setImageMiniFileName(SI);
        mult(2, tmf_);
        tmf_.getTeam().add(trp(P_POK_00,A_SIM_1,4));
        tmf_.getTeam().add(trp(P_POK_01,A_SIM_2,4));
        tmf_.getTeam().add(trp(P_POK_02,A_SIM_1,4));
        tmf_.getTeam().add(trp(P_POK_03,A_SIM_2,4));
        return tmf_;
    }
    protected static TrainerLeague trLeagueTwo() {
        TrainerLeague tmf_ = Instances.newTrainerLeague();
        tmf_.setName(T_L_2);
        tmf_.setImageMaxiFileName(SINGLE);
        tmf_.setImageMiniFileName(SI);
        mult(2, tmf_);
        tmf_.getTeam().add(trp(P_POK_04,A_SIM_1,5));
        tmf_.getTeam().add(trp(P_POK_05,A_SIM_2,5));
        tmf_.getTeam().add(trp(P_POK_06,A_SIM_1,5));
        tmf_.getTeam().add(trp(P_POK_07,A_SIM_2,5));
        return tmf_;
    }
    protected static GymLeader trGymLeader() {
        GymLeader tmf_ = Instances.newGymLeader();
        tmf_.setName(G_L_1);
        tmf_.setImageMaxiFileName(SINGLE);
        tmf_.setImageMiniFileName(SI);
        mult(1, tmf_);
        tmf_.getTeam().add(wpOne(P_POK_00,A_SIM_2,18));
        tmf_.getTeam().add(wpTwo(P_POK_01,A_SIM_1,19));
        tmf_.setTm(2);
        return tmf_;
    }
    protected static GymTrainer trGymTrainer() {
        GymTrainer tmf_ = Instances.newGymTrainer();
        tmf_.setImageMaxiFileName(SINGLE);
        tmf_.setImageMiniFileName(SI);
        mult(1, tmf_);
        tmf_.getTeam().add(wpOne(P_POK_02,A_SIM_1,18));
        tmf_.getTeam().add(wpTwo(P_POK_03,A_SIM_2,19));
        return tmf_;
    }
    protected static TrainerMultiFights trMult() {
        TrainerMultiFights tmf_ = Instances.newTrainerMultiFights();
        tmf_.setImageMaxiFileName(SINGLE);
        tmf_.setImageMiniFileName(SI);
        mult(2, tmf_);
        tmf_.getTeamsRewards().add(teamOne());
        tmf_.getTeamsRewards().add(teamTwo());
        return tmf_;
    }

    private static void mult(int _m, Trainer _t) {
        _t.setMultiplicityFight(_m);
    }

    protected static PokemonTeam teamOne() {
        PokemonTeam t_ = teamBase(20);
        t_.getTeam().add(wpOne(P_POK_00,A_SIM_1,7));
        t_.getTeam().add(wpTwo(P_POK_01,A_SIM_2,9));
        return t_;
    }
    protected static PokemonTeam teamTwo() {
        PokemonTeam t_ = teamBase(15);
        t_.getTeam().add(wpOne(P_POK_02,A_SIM_2,17));
        t_.getTeam().add(wpTwo(P_POK_03,A_SIM_1,19));
        return t_;
    }

    private static PokemonTeam teamBase(int _v) {
        PokemonTeam t_ = Instances.newPokemonTeam();
        t_.setReward( _v);
        return t_;
    }

    protected static PkTrainer wpOne(String _name, String _ab, int _level) {
        return trp(_name, _ab, _level, M_POK_04, M_POK_05);
    }

    protected static PkTrainer wpTwo(String _name, String _ab, int _level) {
        return trp(_name, _ab, _level, M_POK_06, M_POK_07);
    }
    protected static PkTrainer trp(String _name, String _ab, int _level, String _one, String _second) {
        PkTrainer pk_ = Instances.newPkTrainer();
        pk_.setName(_name);
        pk_.setLevel( _level);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(_ab);
        pk_.setItem(NULL_REF);
        pk_.getMoves().add(_one);
        pk_.getMoves().add(_second);
        return pk_;
    }
    protected static PkTrainer trp(String _name, String _ab, int _level) {
        PkTrainer pk_ = Instances.newPkTrainer();
        pk_.setName(_name);
        pk_.setLevel( _level);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(_ab);
        pk_.setItem(NULL_REF);
        pk_.getMoves().add(M_POK_00);
        return pk_;
    }
    public static int[][] line(int _c, int _d) {
        return new int[][]{new int[]{_c, _d}};
    }
}
