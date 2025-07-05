package aiki.beans.simulation;

import aiki.beans.*;
import aiki.beans.db.*;
import aiki.beans.facade.simulation.dto.*;
import aiki.beans.facade.simulation.enums.*;
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
import aiki.game.fight.util.*;
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
import code.maths.*;
import code.maths.montecarlo.*;
import code.scripts.pages.aiki.MessagesDataSimulation;
import code.scripts.pages.aiki.MessagesPkBean;
import code.sml.util.Translations;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsFile;
import code.sml.util.TranslationsLg;
import aiki.beans.facade.map.dto.*;
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
    public static final String M_POK_08 = "M_POK08";

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
    public static final String M_POK_07_TR = "M_POK_07_TR";
    public static final String M_POK_08_TR = "M_POK_08_TR";
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

    public static AbsMap<String,String> callSimulationBeanAbilitiesAfterFightGet(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).getAbilitiesAfterFight();
    }

    public static AbsMap<String,String> callSimulationBeanAbilitiesGet(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).getAbilities();
    }

    public static String callSimulationBeanAbilityAfterFightGet(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).getAbilityAfterFight().tryRet();
    }

//    public static NaSt callSimulationBeanAdd(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanAdd(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanAddPkTrainer(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanAddPkTrainer(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanAllowCatchingKoGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanAllowCatchingKoGet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanAllowedSwitchPlacesEndRoundGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanAllowedSwitchPlacesEndRoundGet(),_str,_args);
//    }

    public static boolean callSimulationBeanAllyChoiceGet(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).getAllyChoice().isSelected();
    }

    public static CustList<PokemonTrainerDto> callSimulationBeanAllyTeamGet(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).getAllyTeam();
    }

    public static AbsMap<String,String> callSimulationBeanAvailableEvosGet(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).getAvailableEvos();
    }

//    public static NaSt callSimulationBeanCancelDiffChoice(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanCancelDiffChoice(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanCancelEvo(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanCancelEvo(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanCancelEvolutions(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanCancelEvolutions(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanCancelEvolutionsAfterFight(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanCancelEvolutionsAfterFight(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanCancelFrontFighters(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanCancelFrontFighters(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanCancelMoves(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanCancelMoves(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanCancelMovesEvos(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanCancelMovesEvos(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanCancelMovesSets(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanCancelMovesSets(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanCancelTeam(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanCancelTeam(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanChangeFight(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanChangeFight(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanChangeFightWhileEnd(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanChangeFightWhileEnd(),_str,_args);
//    }

    public static String callSimulationBeanChosenEvoGet(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).getChosenEvo().tryRet();
    }

//    public static NaSt callSimulationBeanClickLevel(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanClickLevel(),_str,_args);
//    }

//    public static NaSt callSimulationBeanClickLevelZero(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanClickLevelZero(),_str,_args);
//    }

    public static CustList<String> callSimulationBeanCommentsGet(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).getComments();
    }

    public static String callSimulationBeanCurrentAbilityGet(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).getCurrentAbility().tryRet();
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

//    public static NaSt callSimulationBeanDisplayComments(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanDisplayComments(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanDisplayEvolutions(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanDisplayEvolutions(),_str,_args);
//    }

    public static boolean callSimulationBeanDisplayIfErrorGet(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).getDisplayIfError();
    }

//    public static Struct callSimulationBeanEnabledClosingGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanEnabledClosingGet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanEndFightIfOneTeamKoGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanEndFightIfOneTeamKoGet(),_str,_args);
//    }

    public static String callSimulationBeanEnvironmentGet(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).environment();
    }

    public static AbsMap<String,String> callSimulationBeanEnvironmentsGet(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).getEnvironments();
    }

    public static String callSimulationBeanEvolutionAfterFightGet(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).getEvolutionAfterFight().tryRet();
    }

    public static AbsMap<String,String> callSimulationBeanEvolutionsAfterFightGet(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).getEvolutionsAfterFight();
    }

    public static CustList<PokemonTrainerDto> callSimulationBeanFoeTeamGet(CommonBean _str) {
        return ((SimulationBean) _str).getFoeTeam();
    }

    public static boolean callSimulationBeanFreeTeamsGet(int _team) {
        return ((SimulationBean) initByTeam(_team)).getFreeTeams();
    }

    public static String callSimulationBeanGetAbility(CommonBean _str, int _index) {
        return ((SimulationBean) _str).getAbility(_index);
    }

    public static String callSimulationBeanGetAbilityAfterFight(CommonBean _str, int _index) {
        return ((SimulationBean) _str).getAbilityAfterFight(_index);
    }

    public static String callSimulationBeanGetAbilityAlly(CommonBean _str, int _index) {
        return ((SimulationBean) _str).getAbilityAlly(_index);
    }

    public static String callSimulationBeanGetAbilityFoe(CommonBean _str, int _index) {
        return ((SimulationBean) _str).getAbilityFoe(_index);
    }

    public static String callSimulationBeanGetGender(CommonBean _str, int _index) {
        return ((SimulationBean) _str).getGender(_index);
    }

    public static String callSimulationBeanGetGenderAfterFight(CommonBean _str, int _index) {
        return ((SimulationBean) _str).getGenderAfterFight(_index);
    }

    public static String callSimulationBeanGetGenderAlly(CommonBean _str, int _index) {
        return ((SimulationBean) _str).getGenderAlly(_index);
    }

    public static String callSimulationBeanGetGenderFoe(CommonBean _str, int _index) {
        return ((SimulationBean) _str).getGenderFoe(_index);
    }

    public static int[][] callSimulationBeanGetImage(CommonBean _str, int _index) {
        return ((SimulationBean) _str).getImage(_index);
    }

    public static int[][] callSimulationBeanGetImageAfterFight(CommonBean _str, int _index) {
        return ((SimulationBean) _str).getImageAfterFight(_index);
    }

    public static int[][] callSimulationBeanGetImageAlly(CommonBean _str, int _index) {
        return ((SimulationBean) _str).getImageAlly(_index);
    }

    public static int[][] callSimulationBeanGetImageFoe(CommonBean _str, int _index) {
        return ((SimulationBean) _str).getImageFoe(_index);
    }

    public static String callSimulationBeanGetItem(CommonBean _str, int _index) {
        return ((SimulationBean) _str).getItem(_index);
    }

    public static String callSimulationBeanGetItemAfterFight(CommonBean _str, int _index) {
        return ((SimulationBean) _str).getItemAfterFight(_index);
    }

    public static String callSimulationBeanGetItemAlly(CommonBean _str, int _index) {
        return ((SimulationBean) _str).getItemAlly(_index);
    }

    public static String callSimulationBeanGetItemFoe(CommonBean _str, int _index) {
        return ((SimulationBean) _str).getItemFoe(_index);
    }

    public static CustList<String> callSimulationBeanGetKoFoes(CommonBean _str) {
        return ((SimulationBean) _str).getKoFoes();
    }

    public static CustList<String> callSimulationBeanGetKoPlayers(CommonBean _str) {
        return ((SimulationBean) _str).getKoPlayers();
    }

    public static long callSimulationBeanGetLevel(CommonBean _str, int _index) {
        return ((SimulationBean) _str).getLevel(_index);
    }

    public static long callSimulationBeanGetLevelAfterFight(CommonBean _str, int _index) {
        return ((SimulationBean) _str).getLevelAfterFight(_index);
    }

    public static long callSimulationBeanGetLevelAlly(CommonBean _str, int _index) {
        return ((SimulationBean) _str).getLevelAlly(_index);
    }

    public static long callSimulationBeanGetLevelFoe(CommonBean _str, int _index) {
        return ((SimulationBean) _str).getLevelFoe(_index);
    }

    public static CustList<String> callSimulationBeanGetMoves(CommonBean _str, int _index) {
        return ((SimulationBean) _str).getMoves(_index);
    }

    public static CustList<String> callSimulationBeanGetMovesAfterFight(CommonBean _str, int _index) {
        return ((SimulationBean) _str).getMovesAfterFight(_index);
    }

    public static CustList<String> callSimulationBeanGetMovesAlly(CommonBean _str, int _index) {
        return ((SimulationBean) _str).getMovesAlly(_index);
    }

    public static CustList<String> callSimulationBeanGetMovesFoe(CommonBean _str, int _index) {
        return ((SimulationBean) _str).getMovesFoe(_index);
    }

    public static String callSimulationBeanGetName(CommonBean _str, int _index) {
        return ((SimulationBean) _str).getName(_index);
    }

    public static String callSimulationBeanGetNameAfterFight(CommonBean _str, int _index) {
        return ((SimulationBean) _str).getNameAfterFight(_index);
    }

    public static String callSimulationBeanGetNameAlly(CommonBean _str, int _index) {
        return ((SimulationBean) _str).getNameAlly(_index);
    }

    public static String callSimulationBeanGetNameFoe(CommonBean _str, int _index) {
        return ((SimulationBean) _str).getNameFoe(_index);
    }

    public static CustList<String> callSimulationBeanGetNotKoFrontFoes(CommonBean _str) {
        return ((SimulationBean) _str).getNotKoFrontFoes();
    }

    public static long callSimulationBeanGetRealStepNumber(CommonBean _str) {
        return ((SimulationBean) _str).getRealStepNumber();
    }

    public static LgInt callSimulationBeanGetRemainingLifeRate(CommonBean _str, int _index) {
        return ((SimulationBean) _str).getRemainingLifeRate(_index);
    }

    public static String callSimulationBeanGetTrainerName(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).getTrainerName();
    }

//    public static NaSt callSimulationBeanHideComments(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanHideComments(),_str,_args);
//    }

    public static long callSimulationBeanIndexTeamGet(CommonBean _str) {
        return ((SimulationBean) _str).getIndexTeam();
    }

    public static boolean callSimulationBeanIsAvailableAbilities(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).isAvailableAbilities();
    }

    public static boolean callSimulationBeanIsAvailableMoves(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).isAvailableMoves();
    }

    public static boolean callSimulationBeanIsDiffState(CommonBean _str) {
        return ((SimulationBean) _str).isDiffState();
    }

    public static boolean callSimulationBeanIsEvolutionAfterFightState(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).isEvolutionAfterFightState();
    }

    public static boolean callSimulationBeanIsEvolutionsState(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).isEvolutionsState();
    }

    public static boolean callSimulationBeanIsFightAfter(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).isFightAfter();
    }

    public static boolean callSimulationBeanIsFoeState(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).isFoeState();
    }

    public static boolean callSimulationBeanIsFrontState(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).isFrontState();
    }

    //    public static Struct callSimulationBeanIsHardSimulationIssue(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsHardSimulationIssue(),_str,_args);
//    }
//
    public static boolean callSimulationBeanIsIssue(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).isIssue();
    }

//    public static Struct callSimulationBeanIsIssueAfterFight(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsIssueAfterFight(),_str,_args);
//    }

    public static boolean callSimulationBeanIsMovesFightState(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).isMovesFightState();
    }

    public static boolean callSimulationBeanIsMovesState(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).isMovesState();
    }

    public static boolean callSimulationBeanIsMultiLayer(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).isMultiLayer(_args[0]);
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

    public static boolean callSimulationBeanIsSimulationState(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).isSimulationState();
    }

    public static boolean callSimulationBeanIsTeamState(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).isTeamState();
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

    public static CustList<SelectLineMove> callSimulationBeanKeptMovesAfterFightGet(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).getKeptMovesAfterFight();
    }

    public static CustList<SelectLineMove> callSimulationBeanKeptMovesGet(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).getKeptMoves();
    }

    public static int callSimulationBeanLayers(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).layers(_args[0]).size();
    }

    public static long callSimulationBeanLevelEvoGet(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).getLevelEvo().valueLong();
    }

    public static CustList<RadioLineMove> callSimulationBeanMovesSetGet(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).getMovesSet();
    }

    public static long callSimulationBeanMultiplicityGet(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).multiplicity();
    }

    public static long callSimulationBeanNbTeamsGet(int _team) {
        return ((SimulationBean) initByTeam(_team)).getNbTeams();
    }

    public static long callSimulationBeanSelectedTeamNumberGet(CommonBean _str) {
        return ((SimulationBean) _str).selectedTeamNumber();
    }

//    public static NaSt callSimulationBeanNextFight(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanNextFight(),_str,_args);
//    }

    public static Rate callSimulationBeanNumberNecessaryPointsForGrowingLevel(CommonBean _str, int _index) {
        return ((SimulationBean) _str).numberNecessaryPointsForGrowingLevel(_index);
    }

    public static CustList<Integer> callSimulationBeanNumbers(int _teams) {
        return ((SimulationBean) validateDiff(_teams)).getNumbers();
    }

    public static boolean callSimulationBeanOkGet(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).getOk();
    }

    public static String callSimulationBeanPlaceFightGet(CommonBean _str, int... _args) {
        return Long.toString(((SimulationBean) _str).getPlaceFight().valueInt());
    }

    public static AbsMap<Integer,String> callSimulationBeanPlacesFightGet(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).getPlacesFight();
    }

    public static CustList<PlaceIndex> callSimulationBeanPlacesGet(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).getPlaces();
    }

//    public static NaSt callSimulationBeanQuit(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanQuit(),_str,_args);
//    }

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

    public static AbsMap<Integer,String> callSimulationBeanRoundGet(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).getRound();
    }

//    public static NaSt callSimulationBeanSelectAllyPk(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectAllyPk(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanSelectFoePk(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectFoePk(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanSelectPk(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectPk(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanErrorSelectedPkGet(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanErrorSelectedPkGet(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanSelectPkAfterFight(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectPkAfterFight(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanSelectedActionGet(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectedActionGet(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanSelectedAllyActionGet(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectedAllyActionGet(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanErrorSelectedAllyPkGet(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanErrorSelectedAllyPkGet(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanSelectedAllyPkGet(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectedAllyPkGet(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanSelectedFoeActionGet(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectedFoeActionGet(),_str,_args);
//    }

//    public static NaSt callSimulationBeanErrorSelectedFoePkGet(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanErrorSelectedFoePkGet(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanSelectedFoePkGet(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectedFoePkGet(),_str,_args);
//    }

    public static boolean callSimulationBeanSelectedIndex(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).selectedIndex();
    }

    public static boolean callSimulationBeanSelectedIndexForMoves(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).selectedIndexForMoves();
    }

    public static long callSimulationBeanSelectedMoveGet(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).getSelectedMove();
    }

    public static long callSimulationBeanSelectedPkGet(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).getSelectedPk();
    }

    public static String callSimulationBeanSelectedRoundGet(CommonBean _str, int... _args) {
        return Long.toString(((SimulationBean) _str).getSelectedRound().valueInt());
    }

//    public static NaSt callSimulationBeanSimulateFight(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanSimulateFight(),_str,_args);
//    }

//    public static Struct callSimulationBeanSkipLearningMovesWhileNotGrowingLevelGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanSkipLearningMovesWhileNotGrowingLevelGet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanStillPossibleFleeGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanStillPossibleFleeGet(),_str,_args);
//    }

    public static AbsMap<Integer,String> callSimulationBeanTargetFightGet(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).getTargetFight();
    }

    public static String callSimulationBeanTargetGet(CommonBean _str, int... _args) {
        return Long.toString(((SimulationBean) _str).getTarget().valueInt());
    }

    public static CustList<PokemonPlayer> callSimulationBeanTeamAfterFightGet(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).getTeamAfterFight();
    }

    public static CustList<PokemonPlayerDto> callSimulationBeanTeamGet(CommonBean _str, int... _args) {
        return ((SimulationBean) _str).getTeam();
    }

//    public static NaSt callSimulationBeanValidateDiffChoice(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateDiffChoice(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanValidateEvo(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateEvo(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanValidateEvolutionAfterFight(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateEvolutionAfterFight(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanValidateEvolutions(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateEvolutions(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanValidateFoeChoice(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateFoeChoice(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanValidateFoeChoiceFree(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateFoeChoiceFree(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanValidateFoeChoiceSkipEvolutions(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateFoeChoiceSkipEvolutions(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanValidateFrontFighter(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateFrontFighter(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanValidateFrontFighters(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateFrontFighters(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanValidateMoves(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateMoves(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanValidateMovesAbilityAfterFight(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateMovesAbilityAfterFight(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanValidateMovesAfterFight(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateMovesAfterFight(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanValidateMovesChoice(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateMovesChoice(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanValidateMovesSets(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateMovesSets(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanValidateTeam(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanValidateTeam(),_str,_args);
//    }

    public static void callSimulationBeanAbilityAfterFightSet(CommonBean _str, String _args) {
        ((SimulationBean) _str).getAbilityAfterFight().setupValue(_args);
    }

    public static void callSimulationBeanChosenEvoSet(CommonBean _str, String _args) {
        ((SimulationBean) _str).getChosenEvo().setupValue(_args);
    }

    public static void callSimulationBeanCurrentAbilitySet(CommonBean _str, String _args) {
        ((SimulationBean) _str).getCurrentAbility().setupValue(_args);
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

    public static void callSimulationBeanEnvironmentSet(CommonBean _str, String _args) {
        ((SimulationBean) _str).getEnvironment().setupValue(_args);
        ((SimulationBean) _str).setEnvironment(_args);
    }

    public static void callSimulationBeanEvolutionAfterFightSet(CommonBean _str, String _args) {
        ((SimulationBean) _str).getEvolutionAfterFight().setupValue(_args);
    }
//
//    public static NaSt callSimulationBeanPlaceFightSet(NaSt _str, String _args) {
//        return BeanPokemonCommonTs.callString(new SimulationBeanPlaceFightSet(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanSelectedActionSet(NaSt _str, String _args) {
//        return BeanPokemonCommonTs.callString(new SimulationBeanSelectedActionSet(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanSelectedAllyActionSet(NaSt _str, String _args) {
//        return BeanPokemonCommonTs.callString(new SimulationBeanSelectedAllyActionSet(),_str,_args);
//    }

//    public static NaSt callSimulationBeanSelectedFoeActionSet(NaSt _str, String _args) {
//        return BeanPokemonCommonTs.callString(new SimulationBeanSelectedFoeActionSet(),_str,_args);
//    }

//    public static NaSt callSimulationBeanSelectedRoundSet(NaSt _str, String _args) {
//        return BeanPokemonCommonTs.callString(new SimulationBeanSelectedRoundSet(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanTargetSet(NaSt _str, String _args) {
//        return BeanPokemonCommonTs.callString(new SimulationBeanTargetSet(),_str,_args);
//    }

//    public static NaSt callSimulationBeanIndexTeamSet(NaSt _str, int _args) {
//        return BeanPokemonCommonTs.callInt(new SimulationBeanIndexTeamSet(),_str,_args);
//    }

//    public static Struct callSimulationBeanIvFoeSet(Struct _str, int _args) {
//        return BeanPokemonCommonTs.callInt(new SimulationBeanIvFoeSet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanIvPlayerSet(Struct _str, int _args) {
//        return BeanPokemonCommonTs.callInt(new SimulationBeanIvPlayerSet(),_str,_args);
//    }

    public static void callSimulationBeanLevelEvoSet(CommonBean _str, int _args) {
        ((SimulationBean) _str).getLevelEvo().valueLong(_args);
    }

    public static void callSimulationBeanMultiplicitySet(CommonBean _str, int _args) {
        ((SimulationBean) _str).getMultiplicity().valueLong(_args);
        ((SimulationBean) _str).setMultiplicity(_args);
    }

//    public static NaSt callSimulationBeanNbTeamsSet(NaSt _str, int _args) {
//        return BeanPokemonCommonTs.callInt(new SimulationBeanNbTeamsSet(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanSelectedAllyPkSet(NaSt _str, int _args) {
//        return BeanPokemonCommonTs.callInt(new SimulationBeanSelectedAllyPkSet(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanSelectedFoePkSet(NaSt _str, int _args) {
//        return BeanPokemonCommonTs.callInt(new SimulationBeanSelectedFoePkSet(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanSelectedMoveSet(NaSt _str, int _args) {
//        return BeanPokemonCommonTs.callInt(new SimulationBeanSelectedMoveSet(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanSelectedPkSet(NaSt _str, int _args) {
//        return BeanPokemonCommonTs.callInt(new SimulationBeanSelectedPkSet(),_str,_args);
//    }

//    public static Struct callSimulationBeanAllowCatchingKoSet(Struct _str, boolean _args) {
//        return BeanPokemonCommonTs.callBool(new SimulationBeanAllowCatchingKoSet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanAllowedSwitchPlacesEndRoundSet(Struct _str, boolean _args) {
//        return BeanPokemonCommonTs.callBool(new SimulationBeanAllowedSwitchPlacesEndRoundSet(),_str,_args);
//    }
//
//    public static NaSt callSimulationBeanAllyChoiceSet(NaSt _str, boolean _args) {
//        return BeanPokemonCommonTs.callBool(new SimulationBeanAllyChoiceSet(),_str,_args);
//    }
//
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

    public static void callAddPokemonBeanAbilitySet(CommonBean _str, String _args) {
        ((AddPokemonBean) _str).getAbility().setupValue(_args);
    }

    public static void callAddPokemonBeanGenderSet(CommonBean _str, String _args) {
        ((AddPokemonBean) _str).getCommon().getGender().setupValue(_args);
    }

    public static void callAddPokemonBeanHasEvoSet(CommonBean _str, String _args) {
        ((AddPokemonBean) _str).getHasEvo().setupValue(_args);
    }

    public static void callAddPokemonBeanIsEvoSet(CommonBean _str, String _args) {
        ((AddPokemonBean) _str).getIsEvo().setupValue(_args);
    }

    public static void callAddPokemonBeanIsLegSet(CommonBean _str, String _args) {
        ((AddPokemonBean) _str).getIsLeg().setupValue(_args);
    }

    public static void callAddPokemonBeanTypedNameSet(CommonBean _str, String _args) {
        ((AddPokemonBean) _str).getTypedName().setupValue(_args);
    }

    public static void callAddPokemonBeanTypedTypeSet(CommonBean _str, String _args) {
        ((AddPokemonBean) _str).getTypedType().setupValue(_args);
    }

    public static void callEditPokemonBeanBallSet(CommonBean _str, String _args) {
        ((EditPokemonBean) _str).getBall().setupValue(_args);
    }

    public static void callEditPokemonMovesBeanCategorySet(CommonBean _str, String _args) {
        ((EditPokemonMovesBean) _str).getTypedCategory().setupValue(_args);
    }

    public static void callEditPokemonMovesBeanTypedNameSet(CommonBean _str, String _args) {
        ((EditPokemonMovesBean) _str).getTypedName().setupValue(_args);
    }

    public static void callEditPokemonMovesBeanTypedTypeSet(CommonBean _str, String _args) {
        ((EditPokemonMovesBean) _str).getTypedType().setupValue(_args);
    }

    public static void callEditTrainerPokemonBeanGenderSet(CommonBean _str, String _args) {
        ((EditTrainerPokemonBean) _str).getCommon().getGender().setupValue(_args);
    }

    public static void callSelectAbilityBeanTypedAbilitySet(CommonBean _str, String _args) {
        ((SelectAbilityBean) _str).getTypedAbility().setupValue(_args);
    }

    public static void callSelectItemBeanTypedClassSet(CommonBean _str, String _args) {
        ((SelectItemBean) _str).getTypedClass().setupValue(_args);
    }

    public static void callSelectItemBeanTypedNameSet(CommonBean _str, String _args) {
        ((SelectItemBean) _str).getTypedName().setupValue(_args);
    }

    public static void callSelectItemBeanTypedPriceSet(CommonBean _str, String _args) {
        ((SelectItemBean) _str).getTypedPrice().setupValue(_args);
    }

    public static void callSelectPokemonBeanHasEvoSet(CommonBean _str, String _args) {
        ((SelectPokemonBean) _str).getHasEvo().setupValue(_args);
    }

    public static void callSelectPokemonBeanIsEvoSet(CommonBean _str, String _args) {
        ((SelectPokemonBean) _str).getIsEvo().setupValue(_args);
    }

    public static void callSelectPokemonBeanIsLegSet(CommonBean _str, String _args) {
        ((SelectPokemonBean) _str).getIsLeg().setupValue(_args);
    }

    public static void callSelectPokemonBeanTypedNameSet(CommonBean _str, String _args) {
        ((SelectPokemonBean) _str).getTypedName().setupValue(_args);
    }

    public static void callSelectPokemonBeanTypedTypeSet(CommonBean _str, String _args) {
        ((SelectPokemonBean) _str).getTypedType().setupValue(_args);
    }

//    public static NaSt callAddPokemonBeanClickLink(NaSt _str, int _args) {
//        return BeanPokemonCommonTs.callInt(new AddPokemonBeanClickLink(),_str,_args);
//    }

    public static void callAddPokemonBeanLevelSet(CommonBean _str, int _args) {
        ((AddPokemonBean) _str).getCommon().getLevel().valueLong(_args);
    }

    public static void callEditPokemonBeanHappinessSet(CommonBean _str, int _args) {
        ((EditPokemonBean) _str).getHappiness().valueLong(_args);
    }

    public static void callEditTrainerPokemonBeanLevelSet(CommonBean _str, int _args) {
        ((EditTrainerPokemonBean) _str).getCommon().getLevel().valueLong(_args);
    }


    public static void callSimulationLevelBeanNoFightSet(CommonBean _str, int _args) {
        ((SimulationLevelBean) _str).noFight(_args);
        ((SimulationLevelBean) _str).getNoFight().valueLong(_args);
    }

    public static void callSelectLineMoveSelectedSet(SelectLineMove _str, boolean _args) {
        _str.getSelected().setSelected(_args);
    }


    public static void callAddPokemonBeanWholeWordSet(CommonBean _str, boolean _args) {
        ((AddPokemonBean) _str).getWholeWord().setSelected(_args);
    }

    public static void callEditPokemonBeanHealSet(CommonBean _str, boolean _args) {
        ((EditPokemonBean) _str).getHeal().setSelected(_args);
    }

    public static void callEditPokemonMovesBeanAvailableMovesOnlySet(CommonBean _str, boolean _args) {
        ((EditPokemonMovesBean) _str).getAvailableMovesOnly().setSelected(_args);
    }

    public static void callEditPokemonMovesBeanWholeWordSet(CommonBean _str, boolean _args) {
        ((EditPokemonMovesBean) _str).getWholeWord().setSelected(_args);
    }

    public static void callEditTrainerPokemonBeanAllyPkSet(CommonBean _str, boolean _args) {
        ((EditTrainerPokemonBean) _str).getAllyPk().setSelected(_args);
    }

    public static void callSelectPokemonBeanWholeWordSet(CommonBean _str, boolean _args) {
        ((SelectPokemonBean) _str).getWholeWord().setSelected(_args);
    }

    public static void callEditPokemonBeanExperienceSet(CommonBean _str, Rate _args) {
        ((EditPokemonBean) _str).getExperience().valueRate(_args);
    }

    public static void callEditPokemonBeanRemainingHpSet(CommonBean _str, Rate _args) {
        ((EditPokemonBean) _str).getRemainingHp().valueRate(_args);
    }

//    public static long callEvLineEvGet(NaSt _str, long... _args) {
//        return (((EvLineStruct) _str).getEvLine()).getEv().valueLong();
//    }

    public static long callEvLineEvGet(EvLine _str, long... _args) {
        return _str.getEv().valueLong();
    }
//    public static NaSt callEvLineEvSet(NaSt _str, int _args) {
//        return BeanPokemonCommonTs.callInt(new EvLineEvSet(),_str,_args);
//    }
//    public static NaSt callDifficultyBeanComGet(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new DifficultyBeanComGet(),_str,_args);
//    }
//    public static NaSt callDifficultyBeanComSet(NaSt _str, NaSt _diffCom) {
//        return BeanPokemonCommonTs.callStruct(new DifficultyBeanComSet(),_str,_diffCom);
//    }
//
//    public static long callPokemonPlayerDtoIndexGet(NaSt _str, long... _args) {
//        return ( ((PokemonPlayerDtoStruct) _str).getPokemonPlayerDto()).getIndex();
//    }
//
//    public static long callPokemonTrainerDtoIndexGet(NaSt _str, long... _args) {
//        return ( ((PokemonTrainerDtoStruct) _str).getPokemonTrainerDto()).getIndex();
//    }
//    public static long callRadioLineMoveIndexGet(NaSt _str, long... _args) {
//        return ( (RadioLineMove) ((MvLineStruct) _str).getWildPk()).getIndex();
//    }
//
//    public static boolean callSelectLineMoveSelectedGet(NaSt _str, long... _args) {
//        return ((SelectLineMove) ((MvLineStruct) _str).getWildPk()).isSelected();
//    }

    public static long callPokemonPlayerDtoIndexGet(PokemonPlayerDto _str, long... _args) {
        return _str.getIndex();
    }

    public static long callPokemonTrainerDtoIndexGet(PokemonTrainerDto _str, long... _args) {
        return _str.getIndex();
    }
    public static long callRadioLineMoveIndexGet(RadioLineMove _str, long... _args) {
        return _str.getIndex();
    }

    public static boolean callSelectLineMoveSelectedGet(SelectLineMove _str, long... _args) {
        return _str.isSelected();
    }
//    public static Struct callSimulationLevelBeanCancel(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationLevelBeanCancel(),_str,_args);
//    }

//    public static NaSt callSimulationLevelBeanClickTile(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationLevelBeanClickTile(),_str,_args);
//    }

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

    public static long callSimulationLevelBeanNoFightGet(CommonBean _str, int... _args) {
        return ((SimulationLevelBean) _str).getNoFight().valueLong();
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


    public static AbsMap<String,String> callAddPokemonBeanAbilitiesGet(CommonBean _str, int... _args) {
        return ((AddPokemonBean) _str).getAbilities();
    }

    public static String callAddPokemonBeanAbilityGet(CommonBean _str, int... _args) {
        return ((AddPokemonBean) _str).getAbility().tryRet();
    }

//    public static NaSt callAddPokemonBeanAdd(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanAdd(),_str,_args);
//    }

    public static AbsMap<String,String> callAddPokemonBeanBooleansGet(CommonBean _str, int... _args) {
        return ((AddPokemonBean) _str).getBooleans();
    }

//    public static NaSt callAddPokemonBeanCancel(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanCancel(),_str,_args);
//    }

    public static String callAddPokemonBeanGenderGet(CommonBean _str, int... _args) {
        return ((AddPokemonBean) _str).getCommon().getGender().tryRet();
    }

    public static AbsMap<String,String> callAddPokemonBeanGendersGet(CommonBean _str, int... _args) {
        return ((AddPokemonBean) _str).getCommon().getGenders();
    }

    public static int[][] callAddPokemonBeanGetMiniImage() {
        return ((AddPokemonBean) pkPlayerSelectPkName(DataBase.EMPTY_STRING)).getMiniImagePk(0);
    }

    public static String callAddPokemonBeanHasEvoGet(CommonBean _str, int... _args) {
        return ((AddPokemonBean) _str).getHasEvo().tryRet();
    }

    public static String callAddPokemonBeanIsEvoGet(CommonBean _str, int... _args) {
        return ((AddPokemonBean) _str).getIsEvo().tryRet();
    }

    public static String callAddPokemonBeanIsLegGet(CommonBean _str, int... _args) {
        return ((AddPokemonBean) _str).getIsLeg().tryRet();
    }

    public static long callAddPokemonBeanLevelGet(CommonBean _str, int... _args) {
        return ((AddPokemonBean) _str).getCommon().getLevel().valueLong();
    }

    public static String callAddPokemonBeanNamePkGet(CommonBean _str, int... _args) {
        return ((AddPokemonBean) _str).getNamePk();
    }

    public static int callAddPokemonBeanPokedexGet(CommonBean _str, int... _args) {
        return ((AddPokemonBean) _str).getPokedex().size();
    }

//    public static NaSt callAddPokemonBeanSearch(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanSearch(),_str,_args);
//    }

    public static String callAddPokemonBeanTypedNameGet(CommonBean _str, int... _args) {
        return ((AddPokemonBean) _str).getTypedName().tryRet();
    }

    public static String callAddPokemonBeanTypedTypeGet(CommonBean _str, int... _args) {
        return ((AddPokemonBean) _str).getTypedType().tryRet();
    }

    public static boolean callAddPokemonBeanWholeWordGet(CommonBean _str, int... _args) {
        return ((AddPokemonBean) _str).getWholeWord().isSelected();
    }

//    public static NaSt callEditPokemonBeanAddMoves(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanAddMoves(),_str,_args);
//    }

    public static String callEditPokemonBeanBallGet(CommonBean _str, int... _args) {
        return ((EditPokemonBean) _str).getBall().tryRet();
    }

    public static AbsMap<String,String> callEditPokemonBeanBallsGet(CommonBean _str, int... _args) {
        return ((EditPokemonBean) _str).getBalls();
    }

//    public static NaSt callEditPokemonBeanCancel(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanCancel(),_str,_args);
//    }
//
//    public static NaSt callEditPokemonBeanChooseItem(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanChooseItem(),_str,_args);
//    }
//
//    public static NaSt callEditPokemonBeanDeleteMoves(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanDeleteMoves(),_str,_args);
//    }
//
//    public static NaSt callEditPokemonBeanEdit(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanEdit(),_str,_args);
//    }

    public static AbsMap<Statistic,EvLine> callEditPokemonBeanEvGet(CommonBean _str, int... _args) {
        return ((EditPokemonBean) _str).getEv();
    }

    public static Rate callEditPokemonBeanExperienceGet(CommonBean _str, int... _args) {
        return ((EditPokemonBean) _str).getExperience().valueRate();
    }

    public static String callEditPokemonBeanGetTranslatedStatistic(CommonBean _str, int... _args) {
        return ((EditPokemonBean) _str).getTranslatedStatistic(_args[0]);
    }

    public static long callEditPokemonBeanHappinessGet(CommonBean _str, int... _args) {
        return ((EditPokemonBean) _str).getHappiness().valueLong();
    }

    public static boolean callEditPokemonBeanHealGet(CommonBean _str, int... _args) {
        return ((EditPokemonBean) _str).getHeal().isSelected();
    }

    public static long callEditPokemonBeanLevelGet(CommonBean _str, int... _args) {
        return ((EditPokemonBean) _str).getLevel();
    }

    public static CustList<SelectLineMove> callEditPokemonBeanMovesGet(CommonBean _str, int... _args) {
        return ((EditPokemonBean) _str).getMoves();
    }

    public static String callEditPokemonBeanNamePkGet(CommonBean _str, int... _args) {
        return ((EditPokemonBean) _str).getNamePk();
    }

    public static Rate callEditPokemonBeanRemainingHpGet(CommonBean _str, int... _args) {
        return ((EditPokemonBean) _str).getRemainingHp().valueRate();
    }

    public static String callEditPokemonBeanTranslateItem(CommonBean _str, int... _args) {
        return ((EditPokemonBean) _str).translateItem();
    }

    public static String callEditPokemonBeanTranslateName(CommonBean _str, int... _args) {
        return ((EditPokemonBean) _str).translateName();
    }

//    public static NaSt callEditPokemonMovesBeanAddMoves(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new EditPokemonMovesBeanAddMoves(),_str,_args);
//    }

    public static boolean callEditPokemonMovesBeanAvailableMovesOnlyGet(CommonBean _str, int... _args) {
        return ((EditPokemonMovesBean) _str).getAvailableMovesOnly().isSelected();
    }

//    public static NaSt callEditPokemonMovesBeanCancel(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new EditPokemonMovesBeanCancel(),_str,_args);
//    }

    public static AbsMap<String,String> callEditPokemonMovesBeanCategoriesGet(CommonBean _str) {
        return ((EditPokemonMovesBean) _str).getCategories();
    }

    public static String callEditPokemonMovesBeanCategoryGet(CommonBean _str, int... _args) {
        return ((EditPokemonMovesBean) _str).getTypedCategory().tryRet();
    }

    public static CustList<SelectLineMove> callEditPokemonMovesBeanMovesGet(CommonBean _str, int... _args) {
        return ((EditPokemonMovesBean) _str).getMoves();
    }

    public static boolean callEditPokemonMovesBeanPlayerGet(CommonBean _str, int... _args) {
        return ((EditPokemonMovesBean) _str).getPlayer();
    }

//    public static NaSt callEditPokemonMovesBeanSearch(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new EditPokemonMovesBeanSearch(),_str,_args);
//    }

    public static String callEditPokemonMovesBeanTypedNameGet(CommonBean _str, int... _args) {
        return ((EditPokemonMovesBean) _str).getTypedName().tryRet();
    }

    public static String callEditPokemonMovesBeanTypedTypeGet(CommonBean _str, int... _args) {
        return ((EditPokemonMovesBean) _str).getTypedType().tryRet();
    }

    public static boolean callEditPokemonMovesBeanWholeWordGet(CommonBean _str, int... _args) {
        return ((EditPokemonMovesBean) _str).getWholeWord().isSelected();
    }

    public static boolean callEditTrainerPokemonBeanAddGet(CommonBean _str, int... _args) {
        return ((EditTrainerPokemonBean) _str).getAdd();
    }

//    public static NaSt callEditTrainerPokemonBeanAddMoves(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanAddMoves(),_str,_args);
//    }

    public static boolean callEditTrainerPokemonBeanAllyPkGet(CommonBean _str, int... _args) {
        return ((EditTrainerPokemonBean) _str).getAllyPk().isSelected();
    }

//    public static NaSt callEditTrainerPokemonBeanCancel(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanCancel(),_str,_args);
//    }
//
//    public static NaSt callEditTrainerPokemonBeanChooseAbility(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanChooseAbility(),_str,_args);
//    }
//
//    public static NaSt callEditTrainerPokemonBeanChooseItem(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanChooseItem(),_str,_args);
//    }
//
//    public static NaSt callEditTrainerPokemonBeanChooseName(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanChooseName(),_str,_args);
//    }
//
//    public static NaSt callEditTrainerPokemonBeanDeleteMoves(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanDeleteMoves(),_str,_args);
//    }

    public static String callEditTrainerPokemonBeanGenderGet(CommonBean _str, int... _args) {
        return ((EditTrainerPokemonBean) _str).getCommon().getGender().tryRet();
    }

    public static AbsMap<String,String> callEditTrainerPokemonBeanGendersGet(CommonBean _str, int... _args) {
        return ((EditTrainerPokemonBean) _str).getCommon().getGenders();
    }

    public static String callEditTrainerPokemonBeanGetTranslatedAbility(CommonBean _str) {
        return ((EditTrainerPokemonBean) _str).getTranslatedAbility();
    }

    public static String callEditTrainerPokemonBeanGetTranslatedItem(CommonBean _str, int... _args) {
        return ((EditTrainerPokemonBean) _str).getTranslatedItem();
    }

    public static String callEditTrainerPokemonBeanGetTranslatedName(CommonBean _str) {
        return ((EditTrainerPokemonBean) _str).getTranslatedName();
    }

    public static long callEditTrainerPokemonBeanLevelGet(CommonBean _str, int... _args) {
        return ((EditTrainerPokemonBean) _str).getCommon().getLevel().valueLong();
    }

    public static CustList<SelectLineMove> callEditTrainerPokemonBeanMovesGet(CommonBean _str) {
        return ((EditTrainerPokemonBean) _str).getMoves();
    }

//    public static NaSt callEditTrainerPokemonBeanValidateTrainerPk(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanValidateTrainerPk(),_str,_args);
//    }

//    public static NaSt callSelectAbilityBeanCancel(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SelectAbilityBeanCancel(),_str,_args);
//    }

//    public static NaSt callSelectAbilityBeanClickAbility(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SelectAbilityBeanClickAbility(),_str,_args);
//    }

    public static String callSelectAbilityBeanGetTrAbility() {
        return ((SelectAbilityBean) pkTrainerSelectAb(DataBase.EMPTY_STRING)).getTrSortedAbility(1);
    }

//    public static NaSt callSelectAbilityBeanSearch(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SelectAbilityBeanSearch(),_str,_args);
//    }

    public static CustList<TranslatedKey> callSelectAbilityBeanSortedAbilitiesGet(CommonBean _str) {
        return ((SelectAbilityBean) _str).sortedAbilitiesGet();
    }

    public static String callSelectAbilityBeanTypedAbilityGet(CommonBean _str) {
        return ((SelectAbilityBean) _str).getTypedAbility().tryRet();
    }

//    public static NaSt callSelectItemBeanCancel(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SelectItemBeanCancel(),_str,_args);
//    }
//
//    public static NaSt callSelectItemBeanCancelItem(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SelectItemBeanCancelItem(),_str,_args);
//    }
//
//    public static NaSt callSelectItemBeanClickLink(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SelectItemBeanClickLink(),_str,_args);
//    }

    public static int[][] callSelectItemBeanGetMiniImage() {
        return ((SelectItemBean) pkTrainerSelectItName(DataBase.EMPTY_STRING)).getMiniImage(0);
    }

    public static int callSelectItemBeanItemsGet(CommonBean _str) {
        return ((SelectItemBean) _str).getItems().size();
    }

//    public static NaSt callSelectItemBeanSearch(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SelectItemBeanSearch(),_str,_args);
//    }

    public static String callSelectItemBeanTypedClassGet(CommonBean _str, int... _args) {
        return ((SelectItemBean) _str).getTypedClass().tryRet();
    }

    public static String callSelectItemBeanTypedNameGet(CommonBean _str, int... _args) {
        return ((SelectItemBean) _str).getTypedName().tryRet();
    }

    public static String callSelectItemBeanTypedPriceGet(CommonBean _str, int... _args) {
        return ((SelectItemBean) _str).getTypedPrice().tryRet();
    }

    public static AbsMap<String,String> callSelectPokemonBeanBooleansGet(CommonBean _str) {
        return ((SelectPokemonBean) _str).getBooleans();
    }

//    public static NaSt callSelectPokemonBeanCancel(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SelectPokemonBeanCancel(),_str,_args);
//    }

//    public static NaSt callSelectPokemonBeanClickLink(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SelectPokemonBeanClickLink(),_str,_args);
//    }

    public static int[][] callSelectPokemonBeanGetMiniImage() {
        return ((SelectPokemonBean) pkTrainerSelectPkName(DataBase.EMPTY_STRING)).getMiniImagePk(0);
    }

    public static String callSelectPokemonBeanHasEvoGet(CommonBean _str, int... _args) {
        return ((SelectPokemonBean) _str).getHasEvo().tryRet();
    }

    public static String callSelectPokemonBeanIsEvoGet(CommonBean _str, int... _args) {
        return ((SelectPokemonBean) _str).getIsEvo().tryRet();
    }

    public static String callSelectPokemonBeanIsLegGet(CommonBean _str, int... _args) {
        return ((SelectPokemonBean) _str).getIsLeg().tryRet();
    }

    public static int callSelectPokemonBeanPokedexGet(CommonBean _str) {
        return ((SelectPokemonBean) _str).getPokedex().size();
    }

//    public static NaSt callSelectPokemonBeanSearch(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SelectPokemonBeanSearch(),_str,_args);
//    }

    public static String callSelectPokemonBeanTypedNameGet(CommonBean _str, int... _args) {
        return ((SelectPokemonBean) _str).getTypedName().tryRet();
    }

    public static String callSelectPokemonBeanTypedTypeGet(CommonBean _str, int... _args) {
        return ((SelectPokemonBean) _str).getTypedType().tryRet();
    }

    public static boolean callSelectPokemonBeanWholeWordGet(CommonBean _str, int... _args) {
        return ((SelectPokemonBean) _str).getWholeWord().isSelected();
    }
    protected static String quit(){
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = init(beanToSimu(pk_));
        return navigateData(new SimulationBeanQuit((SimulationBean) simu_),simu_);
    }

    protected static SimulationBean editEditSelectedPlayerPkSimuStepsBack() {
        SimulationBean simu_ = editEditSelectedPlayerPkSimuSteps();
        return (SimulationBean)transitSimu(new SimulationBeanResetFight(simu_), simu_.getBuilder());
    }

    protected static SimulationBean editEditSelectedPlayerPkSimuStepsNext() {
        SimulationBean simu_ = editEditSelectedPlayerPkSimuSteps();
        return (SimulationBean)transitSimu(new SimulationBeanStepFight(simu_), simu_.getBuilder());
    }

    protected static SimulationBean editEditSelectedPlayerPkSimuStepsCore() {
        SimulationBean simu_ = editEditSelectedPlayerPkSimuSteps();
        assertEq("",new TeamPositionsString().def());
        return (SimulationBean)transitSimu(new SimulationBeanValidateFightCoreForm(simu_), simu_.getBuilder());
    }

    protected static SimulationBean editEditSelectedPlayerPkSimuStepsFirstPos1() {
        SimulationBean simu_ = editEditSelectedPlayerPkSimuSteps();
        return (SimulationBean)transitSimu(new SimulationBeanUpdateEntryValue<Integer,Integer>(new EntryCust<Integer, Integer>(0,0),new BeanChgInt()), simu_.getBuilder());
    }

    protected static SimulationBean editEditSelectedPlayerPkSimuStepsFirstPos2() {
        SimulationBean simu_ = editEditSelectedPlayerPkSimuSteps();
        return (SimulationBean)transitSimu(new SimulationBeanUpdateEntryValue<String,ActivityOfMove>(new EntryCust<String, ActivityOfMove>("",new ActivityOfMove()),new BeanChgActivityOfMove()), simu_.getBuilder());
    }

    protected static SimulationBean editEditSelectedPlayerPkSimuStepsFirstPos3() {
        SimulationBean simu_ = editEditSelectedPlayerPkSimuSteps();
        StringMap<Long> map_ = simu_.getSimulation().getGame().getFight().getUsedItemsWhileRound();
        BeanChgString key_ = new BeanChgString();
        key_.setupValue(I_BALL);
        CommonBean after_ = transitSimu(new SimulationBeanAddEntry<String, Long>(map_, key_, new BeanChgLong()), simu_.getBuilder());
        return (SimulationBean) transitSimu(new SimulationBeanRemoveEntry<String, Long>(map_, I_BALL), after_.getBuilder());
    }

    protected static SimulationBean editEditSelectedPlayerPkSimuStepsFirstPos4() {
        SimulationBean simu_ = editEditSelectedPlayerPkSimuSteps();
        IntMap<ChoiceOfEvolutionAndMoves> map_ = simu_.getSimulation().getGame().getFight().getChoices();
        BeanChgInt key_ = new BeanChgInt();
        key_.valueInt(0);
        CommonBean after_ = transitSimu(new SimulationBeanAddEntry<Integer, ChoiceOfEvolutionAndMoves>(map_, key_, new BeanChgChoiceOfEvolutionAndMoves()), simu_.getBuilder());
        return (SimulationBean) transitSimu(new SimulationBeanRemoveEntry<Integer, ChoiceOfEvolutionAndMoves>(map_, 0), after_.getBuilder());
    }
    protected static SimulationBean editEditSelectedPlayerPkSimuStepsFirstPos5() {
        SimulationBean simu_ = editEditSelectedPlayerPkSimuSteps();
        MoveTargets map_ = simu_.getSimulation().getGame().getFight().getAllyChoice();
        BeanChgMoveTarget key_ = new BeanChgMoveTarget();
        MoveTarget d_ = MoveTarget.def();
        key_.valueMt(d_);
        CommonBean after_ = transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget zero0_ = new MoveTarget(M_POK_00, new TargetCoords(Fight.CST_PLAYER,0));
        key_.valueMt(zero0_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget zero01_ = new MoveTarget(M_POK_00, new TargetCoords(Fight.CST_PLAYER,1));
        key_.valueMt(zero01_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget zero1_ = new MoveTarget(M_POK_00, new TargetCoords(Fight.CST_FOE,0));
        key_.valueMt(zero1_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget zero2_ = new MoveTarget(M_POK_00, new TargetCoords(Fight.CST_FOE,1));
        key_.valueMt(zero2_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget one0_ = new MoveTarget(M_POK_01, new TargetCoords(Fight.CST_PLAYER,0));
        key_.valueMt(one0_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget one01_ = new MoveTarget(M_POK_01, new TargetCoords(Fight.CST_PLAYER,1));
        key_.valueMt(one01_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget one1_ = new MoveTarget(M_POK_01, new TargetCoords(Fight.CST_FOE,0));
        key_.valueMt(one1_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget one2_ = new MoveTarget(M_POK_01, new TargetCoords(Fight.CST_FOE,1));
        key_.valueMt(one2_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget two0_ = new MoveTarget(M_POK_02, new TargetCoords(Fight.CST_PLAYER,0));
        key_.valueMt(two0_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget two01_ = new MoveTarget(M_POK_02, new TargetCoords(Fight.CST_PLAYER,1));
        key_.valueMt(two01_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget two1_ = new MoveTarget(M_POK_02, new TargetCoords(Fight.CST_FOE,0));
        key_.valueMt(two1_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget two2_ = new MoveTarget(M_POK_02, new TargetCoords(Fight.CST_FOE,1));
        key_.valueMt(two2_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget three0_ = new MoveTarget(M_POK_03, new TargetCoords(Fight.CST_PLAYER,0));
        key_.valueMt(three0_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget three01_ = new MoveTarget(M_POK_03, new TargetCoords(Fight.CST_PLAYER,1));
        key_.valueMt(three01_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget three1_ = new MoveTarget(M_POK_03, new TargetCoords(Fight.CST_FOE,0));
        key_.valueMt(three1_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget three2_ = new MoveTarget(M_POK_03, new TargetCoords(Fight.CST_FOE,1));
        key_.valueMt(three2_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget four0_ = new MoveTarget(M_POK_04, new TargetCoords(Fight.CST_PLAYER,0));
        key_.valueMt(four0_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget four01_ = new MoveTarget(M_POK_04, new TargetCoords(Fight.CST_PLAYER,1));
        key_.valueMt(four01_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget four1_ = new MoveTarget(M_POK_04, new TargetCoords(Fight.CST_FOE,0));
        key_.valueMt(four1_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget four2_ = new MoveTarget(M_POK_04, new TargetCoords(Fight.CST_FOE,1));
        key_.valueMt(four2_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget five0_ = new MoveTarget(M_POK_05, new TargetCoords(Fight.CST_PLAYER,0));
        key_.valueMt(five0_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget five01_ = new MoveTarget(M_POK_05, new TargetCoords(Fight.CST_PLAYER,1));
        key_.valueMt(five01_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget five1_ = new MoveTarget(M_POK_05, new TargetCoords(Fight.CST_FOE,0));
        key_.valueMt(five1_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget five2_ = new MoveTarget(M_POK_05, new TargetCoords(Fight.CST_FOE,1));
        key_.valueMt(five2_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget six0_ = new MoveTarget(M_POK_06, new TargetCoords(Fight.CST_PLAYER,0));
        key_.valueMt(six0_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget six01_ = new MoveTarget(M_POK_06, new TargetCoords(Fight.CST_PLAYER,1));
        key_.valueMt(six01_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget six1_ = new MoveTarget(M_POK_06, new TargetCoords(Fight.CST_FOE,0));
        key_.valueMt(six1_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget six2_ = new MoveTarget(M_POK_06, new TargetCoords(Fight.CST_FOE,1));
        key_.valueMt(six2_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget seven0_ = new MoveTarget(M_POK_07, new TargetCoords(Fight.CST_PLAYER,0));
        key_.valueMt(seven0_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget seven01_ = new MoveTarget(M_POK_07, new TargetCoords(Fight.CST_PLAYER,1));
        key_.valueMt(seven01_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget seven1_ = new MoveTarget(M_POK_07, new TargetCoords(Fight.CST_FOE,0));
        key_.valueMt(seven1_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget seven2_ = new MoveTarget(M_POK_07, new TargetCoords(Fight.CST_FOE,1));
        key_.valueMt(seven2_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget eight0_ = new MoveTarget(M_POK_08, new TargetCoords(Fight.CST_PLAYER,0));
        key_.valueMt(eight0_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget eight01_ = new MoveTarget(M_POK_08, new TargetCoords(Fight.CST_PLAYER,1));
        key_.valueMt(eight01_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget eight1_ = new MoveTarget(M_POK_08, new TargetCoords(Fight.CST_FOE,0));
        key_.valueMt(eight1_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        MoveTarget eight2_ = new MoveTarget(M_POK_08, new TargetCoords(Fight.CST_FOE,1));
        key_.valueMt(eight2_);
        transitSimu(new SimulationBeanAddEntry<MoveTarget, MoveTarget>(map_, key_, new BeanChgMoveTarget()), simu_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, zero0_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, zero01_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, zero1_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, zero2_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, one0_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, one01_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, one1_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, one2_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, two0_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, two01_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, two1_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, two2_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, three0_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, three01_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, three1_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, three2_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, four0_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, four01_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, four1_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, four2_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, five0_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, five01_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, five1_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, five2_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, six0_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, six01_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, six1_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, six2_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, seven0_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, seven01_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, seven1_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, seven2_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, eight0_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, eight01_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, eight1_), after_.getBuilder());
        transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, eight2_), after_.getBuilder());
        return (SimulationBean) transitSimu(new SimulationBeanRemoveEntry<MoveTarget, MoveTarget>(map_, d_), after_.getBuilder());
    }

    protected static SimulationBean editEditSelectedPlayerPkSimuStepsCoreTeam() {
        SimulationBean simu_ = editEditSelectedPlayerPkSimuSteps();
        assertEq("",new TeamPositionsString().def());
        return (SimulationBean)transitSimu(new SimulationBeanValidateTeamCoreForm(simu_,simu_.getSimulation().getGame().getFight().getUserTeam(),new BeanChgLong(),new BeanChgLong(),new BeanChgList<String>()), simu_.getBuilder());
    }

    protected static SimulationBean editEditSelectedPlayerPkSimuStepsTeam1() {
        SimulationBean simu_ = editEditSelectedPlayerPkSimuSteps();
        return (SimulationBean)transitSimu(new SimulationBeanUpdateEntryValue<Integer,CustList<Integer>>(new EntryCust<Integer,CustList<Integer>>(0,new CustList<Integer>()),new BeanChgList<Integer>()), simu_.getBuilder());
    }

    protected static SimulationBean editEditSelectedPlayerPkSimuStepsTeam2() {
        SimulationBean simu_ = editEditSelectedPlayerPkSimuSteps();
        return (SimulationBean)transitSimu(new SimulationBeanUpdateValue(simu_.getSimulation().getGame().getFight().getUserTeam().getEnabledMovesByGroup(),new StringList(M_POK_07),new BeanChgActivityOfMove()), simu_.getBuilder());
    }

    protected static SimulationBean editEditSelectedPlayerPkSimuStepsTeam3() {
        SimulationBean simu_ = editEditSelectedPlayerPkSimuSteps();
        return (SimulationBean)transitSimu(new SimulationBeanUpdateEntryValue<String,LgInt>(new EntryCust<String, LgInt>("",LgInt.zero()),new BeanChgLgInt()), simu_.getBuilder());
    }

    protected static SimulationBean editEditSelectedPlayerPkSimuStepsTeam4() {
        SimulationBean simu_ = editEditSelectedPlayerPkSimuStepsNbUses();
        return (SimulationBean)transitSimu(new SimulationBeanUpdateEntryValue<String,LgInt>(new EntryCust<String, LgInt>("",LgInt.zero()),new BeanChgLgInt()), simu_.getBuilder());
    }

    protected static SimulationBean editEditSelectedPlayerPkSimuStepsTeam5() {
        SimulationBean simu_ = editEditSelectedPlayerPkSimuStepsHealAfter();
        return (SimulationBean)transitSimu(new SimulationBeanUpdateEntryValue<Integer,StacksOfUses>(new EntryCust<Integer, StacksOfUses>(0,new StacksOfUses()),new BeanChgStackOfUses()), simu_.getBuilder());
    }

    protected static SimulationBean editEditSelectedPlayerPkSimuStepsTeam6() {
        SimulationBean simu_ = editEditSelectedPlayerPkSimuStepsAnt();
        return (SimulationBean)transitSimu(new SimulationBeanUpdateEntryValue<Integer,Anticipation>(new EntryCust<Integer, Anticipation>(0,new Anticipation()),new BeanChgAnticipation()), simu_.getBuilder());
    }


    protected static SimulationBean editEditSelectedPlayerPkSimuSteps() {
        FacadeGame db_ = dbInc();
        StatusMoveData mv_ = Instances.newStatusMoveData();
        EffectGlobal eff_ = Instances.newEffectGlobal();
        eff_.setWeather(true);
        mv_.getEffects().add(eff_);
        db_.getData().completeMembers(M_POK_07,mv_);
        db_.getData().getTranslatedMoves().getVal(EN).addEntry(M_POK_07, M_POK_07_TR);
        db_.getData().getCombos().getEffects().add(new ListEffectCombo(new StringList(M_POK_07),Instances.newEffectCombo()));
        StatusMoveData mvSend_ = Instances.newStatusMoveData();
        mvSend_.getEffects().add(Instances.newEffectTeamWhileSendFoe());
        db_.getData().completeMembers(M_POK_08,mvSend_);
        db_.getData().getTranslatedMoves().getVal(EN).addEntry(M_POK_08, M_POK_08_TR);
        db_.getData().completeVariables();
        db_.getData().completeMembersCombos();
        return intro(db_);
    }

    protected static SimulationBean editEditSelectedPlayerPkSimuStepsNbUses() {
        FacadeGame db_ = dbInc();
        DamagingMoveData mv_ = Instances.newDamagingMoveData();
        EffectDamage eff_ = Instances.newEffectDamage();
        eff_.setPower(MessagesDataBaseConstants.VAR_DEF+DataBase.SEP_BETWEEN_KEYS+MessagesDataBaseConstants.DEF_EQUIPE_NB_UTILISATION+DataBase.SEP_BETWEEN_KEYS+M_POK_07);
        mv_.getEffects().add(eff_);
        db_.getData().completeMembers(M_POK_07,mv_);
        db_.getData().getTranslatedMoves().getVal(EN).addEntry(M_POK_07, M_POK_07_TR);
        db_.getData().completeVariables();
        db_.getData().completeMembersCombos();
        return intro(db_);
    }

    protected static SimulationBean editEditSelectedPlayerPkSimuStepsHealAfter() {
        FacadeGame db_ = dbInc();
        StatusMoveData mv_ = Instances.newStatusMoveData();
        mv_.getEffects().add(Instances.newEffectEndRoundPositionRelation());
        db_.getData().completeMembers(M_POK_07,mv_);
        db_.getData().getTranslatedMoves().getVal(EN).addEntry(M_POK_07, M_POK_07_TR);
        db_.getData().completeVariables();
        db_.getData().completeMembersCombos();
        return intro(db_);
    }

    protected static SimulationBean editEditSelectedPlayerPkSimuStepsAnt() {
        FacadeGame db_ = dbInc();
        StatusMoveData mv_ = Instances.newStatusMoveData();
        mv_.getEffects().add(Instances.newEffectEndRoundPositionTargetRelation());
        db_.getData().completeMembers(M_POK_07,mv_);
        db_.getData().getTranslatedMoves().getVal(EN).addEntry(M_POK_07, M_POK_07_TR);
        db_.getData().completeVariables();
        db_.getData().completeMembersCombos();
        return intro(db_);
    }

    private static SimulationBean intro(FacadeGame _db) {
        FacadeGame pk_ = pkDataByFacade(_db);
        CommonBean simu_ = simBean2(2, pk_);
        foeTeamsSample(simu_);
        pkTrainerSelectPkPlayerNameCycle(P_POK_00_TR, A_SIM_1, simu_, 4);
        ((SimulationBean)simu_).getSeed().setupValue("0");
        return (SimulationBean)transitSimu(new SimulationBeanIntroFight((SimulationBean) simu_), simu_.getBuilder());
    }

    protected static CommonBean validateDiff(int _nbTeam){
        FacadeGame pk_ = pkDataByFacade(db());
        return simBean(_nbTeam, pk_);
    }

    protected static CommonBean initByTeam(int _nbTeam) {
        FacadeGame pk_ = pkDataByFacade(db());
        return init(_nbTeam, beanToSimu(pk_));
    }
    protected static CommonBean init(int _nbTeam, CommonBean _init) {
        CommonBean simu_ = init(_init);
        ((SimulationBean)simu_).setNbTeams(_nbTeam);
        return simu_;
    }
    protected static CommonBean selectTeam(CommonBean _simu, int _indexTeam) {
//        callSimulationBeanIndexTeamSet(_simu, _indexTeam);
//        beforeDisplaying(_simu);
        assertSame(_simu,transitSimu(new SimulationBeanValidateIndexTeamAction((SimulationBean) _simu, _indexTeam), _simu.getBuilder()));
        return _simu;
    }
    protected static CommonBean pkTrainerTwoTeamsNextOk() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        selectTeam(simu_,0);
        pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, simu_, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_01_TR, A_SIM_1_TR, simu_, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_02_TR, A_SIM_1_TR, simu_, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_03_TR, A_SIM_1_TR, simu_, 4);
        setMult(simu_,2);
        selectTeam(simu_,1);
        pkTrainerSelectPkNameCycle(false,P_POK_04_TR,A_SIM_2_TR, simu_, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_05_TR,A_SIM_2_TR, simu_, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_06_TR,A_SIM_2_TR, simu_, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_07_TR,A_SIM_2_TR, simu_, 5);
        setMult(simu_,2);
        return transitSimu(new SimulationBeanValidateFoeChoiceFree((SimulationBean) simu_), simu_.getBuilder());
    }
    protected static CommonBean pkTrainerTwoTeamsNextOkAlly() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(1, pk_);
        selectTeam(simu_,0);
        pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, simu_, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_01_TR, A_SIM_1_TR, simu_, 4);
        pkTrainerSelectPkNameCycle(true, P_POK_02_TR, A_SIM_1_TR, simu_, 4);
        pkTrainerSelectPkNameCycle(true, P_POK_03_TR, A_SIM_1_TR, simu_, 4);
        setMult(simu_,2);
        return transitSimu(new SimulationBeanValidateFoeChoiceFree((SimulationBean) simu_), simu_.getBuilder());
    }
    protected static CommonBean pkTrainerTwoTeamsNextAdjMult() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        selectTeam(simu_,0);
        pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, simu_, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_01_TR, A_SIM_1_TR, simu_, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_02_TR, A_SIM_1_TR, simu_, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_03_TR, A_SIM_1_TR, simu_, 4);
        setMult(simu_,0);
        selectTeam(simu_,1);
        pkTrainerSelectPkNameCycle(false,P_POK_04_TR,A_SIM_2_TR, simu_, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_05_TR,A_SIM_2_TR, simu_, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_06_TR,A_SIM_2_TR, simu_, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_07_TR,A_SIM_2_TR, simu_, 5);
        setMult(simu_,8);
        return transitSimu(new SimulationBeanValidateFoeChoiceFree((SimulationBean) simu_), simu_.getBuilder());
    }
    protected static CommonBean pkTrainerTwoTeamsNextKo() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(1, pk_);
        return transitSimu(new SimulationBeanValidateFoeChoiceFree((SimulationBean) simu_), simu_.getBuilder());
    }
    //    protected static NaSt pkTrainerTwoTeams() {
//        PkData pk_ = pkDataByFacade(db());
//        StringMap<NaSt> all_ = beanToSimu(pk_);
//        StringMap<String> mapping_ = mappingToSimu();
//        NaSt simu_ = simu(pk_, all_, mapping_, 2);
//        selectTeam(simu_,0);
//        pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, pk_, all_, mapping_, simu_, 4);
//        selectTeam(simu_,1);
//        return pkTrainerSelectPkNameCycle(false,P_POK_01_TR,A_SIM_2_TR,pk_,all_,mapping_,simu_, 5);
//    }
    protected static CommonBean dispSimu() {
        FacadeGame pk_ = pkDataByFacade(db());
        return init(beanToSimu(pk_));
    }
    public static SimulationBean beanDiffDis(String _language) {
        FacadeGame fac_ = db();
        fac_.setLanguage(_language);
        SimulationBean b_ = new SimulationBean();
        b_.setDataBase(fac_);
        b_.setLanguage(_language);
        MockBeanBuilderHelper bu_ = new MockBeanBuilderHelper();
        Translations tr_ = new Translations();
        TranslationsLg en_ = new TranslationsLg();
        en_.getMapping().addEntry(MessagesPkBean.APP_BEAN_DATA, new TranslationsAppli());
        en_.getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.DIFFICULTY,new TranslationsFile());
        en_.getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.SIMULATION,MessagesDataSimulation.en());
        tr_.getMapping().addEntry(EN, en_);
        TranslationsLg fr_ = new TranslationsLg();
        fr_.getMapping().addEntry(MessagesPkBean.APP_BEAN_DATA, new TranslationsAppli());
        fr_.getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.DIFFICULTY,new TranslationsFile());
        fr_.getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.SIMULATION,MessagesDataSimulation.fr());
        tr_.getMapping().addEntry(FR, fr_);
        bu_.setTranslations(tr_);
        bu_.setFacade(fac_);
        b_.setBuilder(bu_);
        b_.build(fac_);
        return b_;
    }
    public static SimulationBean callChange(SimulationBean _str, String _args) {
//        inner(_str).setDiffWinningExpPtsFight(_args);
        _str.getForm().getWinPointsFight().setupValue(_args);
        return _str;
    }
    public static String navigateDiffChange(SimulationBean _str, long... _args) {
        _str.getBuilder().getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML,_str);
        IntBeanAction intAct_ = ((AbsBeanChgSubmit) _str.getUpdateValues()).getEvts().get(0);
        _str.getBuilder().build(intAct_);
        return DataBase.EMPTY_STRING;
//        return navigateDiff(new DifficultyBeanChange(), "",_str,_args);
    }
    private static CommonBean init(CommonBean _main) {
        CommonBean from_ = _main;
//        NaSt dCom_ = _all.getVal(AikiBeansGameStd.BEAN_DIFFICULTY_COMMON);
        beforeDisplaying(from_);
        CommonBean simu_ = transitSimu(new BeanAnchorCstEvent(CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML), _main.getBuilder());
//        callDifficultyBeanComSet(dCom_,callDifficultyBeanComGet(simu_));
//        beforeDisplaying(dCom_);
        return simu_;
    }
    protected static CommonBean pkTrainer() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        return goToAddPkTrainer(simu_);
    }
    protected static String editNoFoePk() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
//        callSimulationBeanSelectedFoePkSet(simu_,-1);
        return navigateData(new SimulationBeanSelectFoePkValidation((SimulationBean) simu_,((SimulationBean)simu_).getSelectedFoeAction(),-1),simu_);
    }
    //    protected static PokemonBeanStruct editNoFoePkStateSelectZero() {
//        PokemonBeanStruct simu_ = editNoFoePkState();
////        callSimulationBeanSelectedFoePkSet(simu_,0);
////        callSimulationBeanSelectedFoeActionSet(simu_, TeamCrud.NOTHING.getTeamCrudString());
//        return transitSimu(new SimulationBeanSelectFoePkValidation((SimulationBean) simu_.getBean(), TeamCrud.NOTHING.getTeamCrudString(), 0), simu_.getInstance().getBuilder());
//    }
//    protected static PokemonBeanStruct editNoFoePkStateSelectZero(TeamCrud _tc) {
//        PokemonBeanStruct simu_ = editNoFoePkState();
////        callSimulationBeanSelectedFoePkSet(simu_,0);
////        callSimulationBeanSelectedFoeActionSet(simu_, _tc.getTeamCrudString());
//        return transitSimu(new SimulationBeanSelectFoePkValidation((SimulationBean) simu_.getBean(), _tc.getTeamCrudString(), 0), simu_.getInstance().getBuilder());
//    }
//    protected static PokemonBeanStruct editNoFoePkStateSelectNo() {
//        PokemonBeanStruct simu_ = editNoFoePkState();
////        callSimulationBeanSelectedFoePkSet(simu_,-1);
////        callSimulationBeanSelectedFoeActionSet(simu_, TeamCrud.EDIT.getTeamCrudString());
//        return transitSimu(new SimulationBeanSelectFoePkValidation((SimulationBean) simu_.getBean(), TeamCrud.EDIT.getTeamCrudString(), -1), simu_.getInstance().getBuilder());
//    }
    protected static CommonBean editNoFoePkState() {
        FacadeGame pk_ = pkDataByFacade(db());
        return simBean(2, pk_);
    }
    protected static CommonBean pkTrainerFoeRemove() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, simu_, 4);
        CommonBean second_ = pkTrainerSelectPkNameCycle(false,P_POK_01_TR,A_SIM_2_TR, simu_, 5);
//        callSimulationBeanSelectedFoeActionSet(second_, TeamCrud.REMOVE.getTeamCrudString());
//        callSimulationBeanSelectedFoePkSet(second_,0);
        return transitSimu(new SimulationBeanSelectFoePkValidation((SimulationBean) second_, TeamCrud.REMOVE.getTeamCrudString(), 0), simu_.getBuilder());
    }
    protected static String editNoSelectedFoePk() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean added_ = pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, simu_, 4);
//        callSimulationBeanSelectedFoeActionSet(added_, TeamCrud.NOTHING.getTeamCrudString());
//        callSimulationBeanSelectedFoePkSet(added_,-1);
        return navigateData(new SimulationBeanSelectFoePkValidation((SimulationBean) simu_,TeamCrud.NOTHING.getTeamCrudString(),-1),added_);
    }
    protected static CommonBean editEditSelectedFoePk() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean added_ = pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, simu_, 4);
//        callSimulationBeanSelectedFoeActionSet(added_, TeamCrud.EDIT.getTeamCrudString());
//        callSimulationBeanSelectedFoePkSet(added_,0);
        return transitSimu(new SimulationBeanSelectFoePkValidation((SimulationBean) added_, TeamCrud.EDIT.getTeamCrudString(), 0), simu_.getBuilder());
    }
    //    protected static PokemonBeanStruct formEditSelectedFoePk() {
//        PkData pk_ = pkDataByFacade(db());
//        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
//        PokemonBeanStruct added_ = pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, simu_, 4);
////        callSimulationBeanSelectedFoeActionSet(added_, TeamCrud.EDIT.getTeamCrudString());
////        callSimulationBeanSelectedFoePkSet(added_,0);
//        return transitSimu(new SimulationBeanSelectFoePkValidation((SimulationBean) added_.getBean(), TeamCrud.EDIT.getTeamCrudString(), 0), simu_.getInstance().getBuilder());
//    }
    protected static CommonBean editEditSelectedFoePkAddMove() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean added_ = pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, simu_, 4);
//        callSimulationBeanSelectedFoeActionSet(added_, TeamCrud.EDIT.getTeamCrudString());
//        callSimulationBeanSelectedFoePkSet(added_,0);
        CommonBean editing_ = transitSimu(new SimulationBeanSelectFoePkValidation((SimulationBean) added_, TeamCrud.EDIT.getTeamCrudString(), 0), simu_.getBuilder());
        addMoveTrainer(M_POK_01_TR,0, editing_);
        return transitSimu(new EditTrainerPokemonBeanValidateTrainerPk((EditTrainerPokemonBean) editing_), simu_.getBuilder());
    }
    protected static String editForgetSelectedFoePk() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean added_ = pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, simu_, 4);
//        callSimulationBeanSelectedFoeActionSet(added_, TeamCrud.NOTHING.getTeamCrudString());
//        callSimulationBeanSelectedFoePkSet(added_,0);
        return navigateData(new SimulationBeanSelectFoePkValidation((SimulationBean) simu_,TeamCrud.NOTHING.getTeamCrudString(),0),added_);
    }
    protected static String editNoAllyPk() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
//        callSimulationBeanSelectedAllyPkSet(simu_,-1);
        return navigateData(new SimulationBeanSelectAllyPkValidation((SimulationBean) simu_,((SimulationBean)simu_).getSelectedAllyAction(),-1),simu_);
    }
//    protected static PokemonBeanStruct editNoAllyPkStateSelectZero() {
//        PokemonBeanStruct simu_ = editNoAllyPkState();
//        callSimulationBeanSelectedAllyPkSet(simu_,0);
//        callSimulationBeanSelectedAllyActionSet(simu_, TeamCrud.NOTHING.getTeamCrudString());
//        return simu_;
//    }
//    protected static PokemonBeanStruct editNoAllyPkStateSelectZero(TeamCrud _tc) {
//        PokemonBeanStruct simu_ = editNoAllyPkState();
//        callSimulationBeanSelectedAllyPkSet(simu_,0);
//        callSimulationBeanSelectedAllyActionSet(simu_, _tc.getTeamCrudString());
//        return simu_;
//    }
//    protected static PokemonBeanStruct editNoAllyPkStateSelectNo() {
//        PokemonBeanStruct simu_ = editNoAllyPkState();
//        callSimulationBeanSelectedAllyPkSet(simu_,-1);
//        callSimulationBeanSelectedAllyActionSet(simu_, TeamCrud.EDIT.getTeamCrudString());
//        return simu_;
//    }
//    protected static PokemonBeanStruct editNoAllyPkState() {
//        PkData pk_ = pkDataByFacade(db());
//        return simBean(2, pk_.getDataBase());
//    }

    protected static CommonBean pkTrainerAllyRemove() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        pkTrainerSelectPkNameCycle(true, P_POK_00_TR, A_SIM_1_TR, simu_, 4);
        CommonBean second_ = pkTrainerSelectPkNameCycle(true,P_POK_01_TR,A_SIM_2_TR, simu_, 5);
//        callSimulationBeanSelectedAllyActionSet(second_, TeamCrud.REMOVE.getTeamCrudString());
//        callSimulationBeanSelectedAllyPkSet(second_,0);
        return transitSimu(new SimulationBeanSelectAllyPkValidation((SimulationBean) second_, TeamCrud.REMOVE.getTeamCrudString(), 0), simu_.getBuilder());
    }
    protected static String editNoSelectedAllyPk() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean added_ = pkTrainerSelectPkNameCycle(true, P_POK_00_TR, A_SIM_1_TR, simu_, 4);
//        callSimulationBeanSelectedAllyActionSet(added_, TeamCrud.NOTHING.getTeamCrudString());
//        callSimulationBeanSelectedAllyPkSet(added_,-1);
        return navigateData(new SimulationBeanSelectAllyPkValidation((SimulationBean) simu_,TeamCrud.NOTHING.getTeamCrudString(),-1),added_);
    }
    protected static CommonBean editEditSelectedAllyPk() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean added_ = pkTrainerSelectPkNameCycle(true, P_POK_00_TR, A_SIM_1_TR, simu_, 4);
//        callSimulationBeanSelectedAllyActionSet(added_, TeamCrud.EDIT.getTeamCrudString());
//        callSimulationBeanSelectedAllyPkSet(added_,0);
        return transitSimu(new SimulationBeanSelectAllyPkValidation((SimulationBean) added_, TeamCrud.EDIT.getTeamCrudString(), 0), simu_.getBuilder());
    }
    //    protected static PokemonBeanStruct formEditSelectedAllyPk() {
//        PkData pk_ = pkDataByFacade(db());
//        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
//        PokemonBeanStruct added_ = pkTrainerSelectPkNameCycle(true, P_POK_00_TR, A_SIM_1_TR, simu_, 4);
//        callSimulationBeanSelectedAllyActionSet(added_, TeamCrud.EDIT.getTeamCrudString());
//        callSimulationBeanSelectedAllyPkSet(added_,0);
//        return added_;
//    }
    protected static CommonBean editEditSelectedAllyPkAddMove() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean added_ = pkTrainerSelectPkNameCycle(true, P_POK_00_TR, A_SIM_1_TR, simu_, 4);
//        callSimulationBeanSelectedAllyActionSet(added_, TeamCrud.EDIT.getTeamCrudString());
//        callSimulationBeanSelectedAllyPkSet(added_,0);
        CommonBean editing_ = transitSimu(new SimulationBeanSelectAllyPkValidation((SimulationBean) added_, TeamCrud.EDIT.getTeamCrudString(), 0), simu_.getBuilder());
        addMoveTrainer(M_POK_01_TR,0, editing_);
        return transitSimu(new EditTrainerPokemonBeanValidateTrainerPk((EditTrainerPokemonBean) editing_), simu_.getBuilder());
    }
    protected static String editForgetSelectedAllyPk() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean added_ = pkTrainerSelectPkNameCycle(true, P_POK_00_TR, A_SIM_1_TR, simu_, 4);
//        callSimulationBeanSelectedAllyActionSet(added_, TeamCrud.NOTHING.getTeamCrudString());
//        callSimulationBeanSelectedAllyPkSet(added_,0);
        return navigateData(new SimulationBeanSelectAllyPkValidation((SimulationBean) simu_,TeamCrud.NOTHING.getTeamCrudString(),0),added_);
    }
    protected static CommonBean pkTrainerIndex() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, simu_, 4);
        return pkTrainerSelectPkNameCycle(false,P_POK_01_TR,A_SIM_2_TR, simu_, 5);
    }
    protected static CommonBean pkTrainerLevel(int _level) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        return pkTrainerSelectPkNameCycle(false,P_POK_00_TR,A_SIM_1_TR, simu_, _level);
    }
    protected static CommonBean pkTrainerLevelCancelAdd() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean editPkTrainer_ = goToAddPkTrainer(simu_);
        return transitSimu(new EditTrainerPokemonBeanCancel((EditTrainerPokemonBean) editPkTrainer_), simu_.getBuilder());
    }
    protected static CommonBean pkTrainerLevelRestoreMoves() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean editPkTrainer_ = goToAddPkTrainer(simu_);
        callSelectLineMoveSelectedSet(eltSelectMove(callEditTrainerPokemonBeanMovesGet(editPkTrainer_),0),true);
        CommonBean after_ = transitSimu(new EditTrainerPokemonBeanDeleteMoves((EditTrainerPokemonBean) editPkTrainer_), simu_.getBuilder());
        callEditTrainerPokemonBeanAllyPkSet(after_,false);
        return transitSimu(new EditTrainerPokemonBeanValidateTrainerPk((EditTrainerPokemonBean) after_), simu_.getBuilder());
    }
    private static CommonBean pkTrainerSelectPkNameCycle(boolean _ally, String _name, String _ability, CommonBean _simu, int _level) {
        CommonBean editPkTrainer_ = goToAddPkTrainer(_simu);
        CommonBean retPk_ = chooseName(_name, editPkTrainer_);
        CommonBean selAb_ = chooseAbility(_ability, retPk_);
        callEditTrainerPokemonBeanAllyPkSet(selAb_, _ally);
        callEditTrainerPokemonBeanLevelSet(selAb_, _level);
        genderSet(selAb_);
        CommonBean afterAddEdit_ = transitSimu(new EditTrainerPokemonBeanValidateTrainerPk((EditTrainerPokemonBean) selAb_), _simu.getBuilder());
        assertSame(afterAddEdit_, _simu);
        return afterAddEdit_;
    }

//    private static NaSt pkTrainerSelectPkNameCycle(boolean _ally, String _name, String _ability, String _item, PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _simu, int _level) {
//        NaSt editPkTrainer_ = goToAddPkTrainer(_pk, _all, _mapping, _simu);
//        NaSt retPk_ = chooseName(_name, _pk, _all, _mapping, editPkTrainer_);
//        NaSt retAb_ = chooseAbility(_ability, _pk, _all, _mapping, retPk_);
//        assertSame(retAb_, chooseItemPkTrainer(_item,_pk,_all,_mapping,retAb_));
//        callEditTrainerPokemonBeanAllyPkSet(retAb_, _ally);
//        callEditTrainerPokemonBeanLevelSet(retAb_, _level);
//        genderSet(retAb_);
//        NaSt afterAddEdit_ = transitSimu(_pk, _all, _mapping, new EditTrainerPokemonBeanValidateTrainerPk((EditTrainerPokemonBean) ((PokemonBeanStruct)retAb_).getBean()), retAb_);
//        assertSame(afterAddEdit_,_simu);
//        return afterAddEdit_;
//    }

    private static void genderSet(CommonBean _str) {
        callEditTrainerPokemonBeanGenderSet(_str,Gender.NO_GENDER.getGenderName());
    }

    private static void genderSetPl(CommonBean _str) {
        callAddPokemonBeanGenderSet(_str,Gender.NO_GENDER.getGenderName());
    }

    private static CommonBean chooseName(String _name, CommonBean _str) {
        CommonBean selPk_ = transitSimu(new EditTrainerPokemonBeanChooseName((EditTrainerPokemonBean) _str), _str.getBuilder());
        callSelectPokemonBeanTypedNameSet(selPk_, _name);
        CommonBean afSel_ = transitSimu(new SelectPokemonBeanSearch((SelectPokemonBean) selPk_), _str.getBuilder());
        assertSame(afSel_, _str);
        return afSel_;
    }

    private static CommonBean chooseAbility(String _ability, CommonBean _str) {
        CommonBean selAb_ = transitSimu(new EditTrainerPokemonBeanChooseAbility((EditTrainerPokemonBean) _str), _str.getBuilder());
        callSelectAbilityBeanTypedAbilitySet(selAb_, _ability);
        CommonBean afSel_ = transitSimu(new SelectAbilityBeanSearch((SelectAbilityBean) selAb_), _str.getBuilder());
        assertSame(afSel_, _str);
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
    protected static CommonBean pkTrainerSelectPkCancel() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean editPkTrainer_ = goToAddPkTrainer(simu_);
        CommonBean selPk_ = transitSimu(new EditTrainerPokemonBeanChooseName((EditTrainerPokemonBean) editPkTrainer_), simu_.getBuilder());
        return transitSimu(new BeanAnchorCstEvent(CommonBean.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML), simu_.getBuilder());
    }

    protected static CommonBean pkTrainerSelectPkName(String _name) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean editPkTrainer_ = goToAddPkTrainer(simu_);
        CommonBean selPk_ = transitSimu(new EditTrainerPokemonBeanChooseName((EditTrainerPokemonBean) editPkTrainer_), simu_.getBuilder());
        callSelectPokemonBeanTypedNameSet(selPk_,_name);
        return transitSimu(new SelectPokemonBeanSearch((SelectPokemonBean) selPk_), simu_.getBuilder());
    }
    protected static CommonBean pkTrainerSelectPkHasEvo(String _name) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean editPkTrainer_ = goToAddPkTrainer(simu_);
        CommonBean selPk_ = transitSimu(new EditTrainerPokemonBeanChooseName((EditTrainerPokemonBean) editPkTrainer_), simu_.getBuilder());
        callSelectPokemonBeanHasEvoSet(selPk_,_name);
        return transitSimu(new SelectPokemonBeanSearch((SelectPokemonBean) selPk_), simu_.getBuilder());
    }
    protected static CommonBean pkTrainerSelectPkIsEvo(String _name) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean editPkTrainer_ = goToAddPkTrainer(simu_);
        CommonBean selPk_ = transitSimu(new EditTrainerPokemonBeanChooseName((EditTrainerPokemonBean) editPkTrainer_), simu_.getBuilder());
        callSelectPokemonBeanIsEvoSet(selPk_,_name);
        return transitSimu(new SelectPokemonBeanSearch((SelectPokemonBean) selPk_), simu_.getBuilder());
    }
    protected static CommonBean pkTrainerSelectPkIsLeg(String _name) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean editPkTrainer_ = goToAddPkTrainer(simu_);
        CommonBean selPk_ = transitSimu(new EditTrainerPokemonBeanChooseName((EditTrainerPokemonBean) editPkTrainer_), simu_.getBuilder());
        callSelectPokemonBeanIsLegSet(selPk_,_name);
        return transitSimu(new SelectPokemonBeanSearch((SelectPokemonBean) selPk_), simu_.getBuilder());
    }
    protected static CommonBean pkTrainerSelectPkRow(int _row) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean editPkTrainer_ = goToAddPkTrainer(simu_);
        CommonBean selPk_ = transitSimu(new EditTrainerPokemonBeanChooseName((EditTrainerPokemonBean) editPkTrainer_), simu_.getBuilder());
        callSelectPokemonBeanTypedNameSet(selPk_,DataBase.EMPTY_STRING);
        CommonBean rSe_ = transitSimu(new SelectPokemonBeanSearch((SelectPokemonBean) selPk_), simu_.getBuilder());
        return transitSimu(new SelectPokemonBeanClickLink((SelectPokemonBean) rSe_, ((SelectPokemonBean) rSe_).getPokedex().get(_row).getName()), simu_.getBuilder());
    }
    protected static CommonBean pkTrainerSelectPkType(String _type, boolean _wholeWord) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean editPkTrainer_ = goToAddPkTrainer(simu_);
        CommonBean selPk_ = transitSimu(new EditTrainerPokemonBeanChooseName((EditTrainerPokemonBean) editPkTrainer_), simu_.getBuilder());
        callSelectPokemonBeanTypedTypeSet(selPk_,_type);
        callSelectPokemonBeanWholeWordSet(selPk_,_wholeWord);
        return transitSimu(new SelectPokemonBeanSearch((SelectPokemonBean) selPk_), simu_.getBuilder());
    }
    protected static CommonBean pkTrainerSelectPk() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean editPkTrainer_ = goToAddPkTrainer(simu_);
        return goToSelectPk(editPkTrainer_);
    }
    protected static CommonBean pkTrainerSelectPkAllyInfo(boolean _ally) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean editPkTrainer_ = goToAddPkTrainer(simu_);
        callEditTrainerPokemonBeanAllyPkSet(editPkTrainer_, _ally);
        return editPkTrainer_;
    }
    protected static CommonBean pkTrainerSelectAbCancel() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean editPkTrainer_ = goToAddPkTrainer(simu_);
        CommonBean selPk_ = transitSimu(new EditTrainerPokemonBeanChooseAbility((EditTrainerPokemonBean) editPkTrainer_), simu_.getBuilder());
        return transitSimu(new BeanAnchorCstEvent(CommonBean.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML), simu_.getBuilder());
    }
    protected static CommonBean pkTrainerSelectAb(int _row) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean editPkTrainer_ = goToAddPkTrainer(simu_);
        CommonBean selAb_ = transitSimu(new EditTrainerPokemonBeanChooseAbility((EditTrainerPokemonBean) editPkTrainer_), simu_.getBuilder());
        callSelectAbilityBeanTypedAbilitySet(selAb_,DataBase.EMPTY_STRING);
        CommonBean rSe_ = transitSimu(new SelectAbilityBeanSearch((SelectAbilityBean) selAb_), simu_.getBuilder());
        return transitSimu(new SelectAbilityBeanClickAbility((SelectAbilityBean) rSe_, _row), simu_.getBuilder());
    }
    protected static CommonBean pkTrainerSelectAb(String _name) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean editPkTrainer_ = goToAddPkTrainer(simu_);
        CommonBean selAb_ = transitSimu(new EditTrainerPokemonBeanChooseAbility((EditTrainerPokemonBean) editPkTrainer_), simu_.getBuilder());
        callSelectAbilityBeanTypedAbilitySet(selAb_,_name);
        return transitSimu(new SelectAbilityBeanSearch((SelectAbilityBean) selAb_), simu_.getBuilder());
    }
    protected static CommonBean pkTrainerSelectAb() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean editPkTrainer_ = goToAddPkTrainer(simu_);
        return goToSelectAb(editPkTrainer_);
    }
    protected static CommonBean pkTrainerSelectItCancel() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean editPkTrainer_ = goToAddPkTrainer(simu_);
        CommonBean selPk_ = transitSimu(new EditTrainerPokemonBeanChooseItem((EditTrainerPokemonBean) editPkTrainer_), simu_.getBuilder());
        CommonBean rSe_ = transitSimu(new SelectItemBeanCancelItem((SelectItemBean) selPk_), simu_.getBuilder());
        CommonBean twice_ = transitSimu(new EditTrainerPokemonBeanChooseItem((EditTrainerPokemonBean) rSe_), simu_.getBuilder());
        callSelectItemBeanTypedNameSet(twice_,I_BALL_TR);
        CommonBean againEditPk_ = transitSimu(new SelectItemBeanSearch((SelectItemBean) twice_), simu_.getBuilder());
        CommonBean nextIt_ = transitSimu(new EditTrainerPokemonBeanChooseItem((EditTrainerPokemonBean) againEditPk_), simu_.getBuilder());
        return transitSimu(new SelectItemBeanCancel((SelectItemBean) nextIt_), simu_.getBuilder());
    }
    protected static CommonBean pkTrainerSelectItCancelRem() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean editPkTrainer_ = goToAddPkTrainer(simu_);
        CommonBean selPk_ = transitSimu(new EditTrainerPokemonBeanChooseItem((EditTrainerPokemonBean) editPkTrainer_), simu_.getBuilder());
        callSelectItemBeanTypedNameSet(selPk_,DataBase.EMPTY_STRING);
        CommonBean rSe_ = transitSimu(new SelectItemBeanSearch((SelectItemBean) selPk_), simu_.getBuilder());
        CommonBean againEditPk_ = transitSimu(new SelectItemBeanClickLink((SelectItemBean) rSe_, ((SelectItemBean) rSe_).getItems().get(0).getName()), simu_.getBuilder());
        CommonBean nextIt_ = transitSimu(new EditTrainerPokemonBeanChooseItem((EditTrainerPokemonBean) againEditPk_), simu_.getBuilder());
        return transitSimu(new SelectItemBeanCancelItem((SelectItemBean) nextIt_), simu_.getBuilder());
    }
    protected static CommonBean pkTrainerSelectItName(int _row) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean editPkTrainer_ = goToAddPkTrainer(simu_);
        CommonBean selAb_ = transitSimu(new EditTrainerPokemonBeanChooseItem((EditTrainerPokemonBean) editPkTrainer_), simu_.getBuilder());
        callSelectItemBeanTypedNameSet(selAb_,DataBase.EMPTY_STRING);
        CommonBean rSe_ = transitSimu(new SelectItemBeanSearch((SelectItemBean) selAb_), simu_.getBuilder());
        return transitSimu(new SelectItemBeanClickLink((SelectItemBean) rSe_, ((SelectItemBean) rSe_).getItems().get(_row).getName()), simu_.getBuilder());
    }
    protected static CommonBean pkTrainerSelectItName(String _name) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean editPkTrainer_ = goToAddPkTrainer(simu_);
        return chooseItemPkTrainer(_name, editPkTrainer_);
    }

    private static CommonBean chooseItemPkTrainer(String _name, CommonBean _editPkTrainer) {
        CommonBean selIt_ = transitSimu(new EditTrainerPokemonBeanChooseItem((EditTrainerPokemonBean) _editPkTrainer), _editPkTrainer.getBuilder());
        return chooseItemGene(_name, selIt_);
    }

    private static CommonBean chooseItemPkPlayer(String _name, CommonBean _editPkTrainer) {
        CommonBean selIt_ = transitSimu(new EditPokemonBeanChooseItem((EditPokemonBean) _editPkTrainer), _editPkTrainer.getBuilder());
        return chooseItemGene(_name, selIt_);
    }

    private static CommonBean chooseItemGene(String _name, CommonBean _selIt) {
        callSelectItemBeanTypedNameSet(_selIt, _name);
        return transitSimu(new SelectItemBeanSearch((SelectItemBean) _selIt), _selIt.getBuilder());
    }
    protected static CommonBean pkTrainerSelectItPrice() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean editPkTrainer_ = goToAddPkTrainer(simu_);
        CommonBean selAb_ = transitSimu(new EditTrainerPokemonBeanChooseItem((EditTrainerPokemonBean) editPkTrainer_), simu_.getBuilder());
        callSelectItemBeanTypedPriceSet(selAb_,DataBase.EMPTY_STRING);
        return transitSimu(new SelectItemBeanSearch((SelectItemBean) selAb_), simu_.getBuilder());
    }
    protected static CommonBean pkTrainerSelectItCl(String _name) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean editPkTrainer_ = goToAddPkTrainer(simu_);
        CommonBean selAb_ = transitSimu(new EditTrainerPokemonBeanChooseItem((EditTrainerPokemonBean) editPkTrainer_), simu_.getBuilder());
        callSelectItemBeanTypedClassSet(selAb_,_name);
        return transitSimu(new SelectItemBeanSearch((SelectItemBean) selAb_), simu_.getBuilder());
    }
    protected static CommonBean pkTrainerSelectItName() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean editPkTrainer_ = goToAddPkTrainer(simu_);
        return goToSelectIt(editPkTrainer_);
    }

    protected static CommonBean pkTrainerSetMovesName(String _name) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean editPkTrainer_ = goToAddPkTrainer(simu_);
        CommonBean selPk_ = transitSimu(new EditTrainerPokemonBeanAddMoves((EditTrainerPokemonBean) editPkTrainer_), simu_.getBuilder());
        callEditPokemonMovesBeanTypedNameSet(selPk_,_name);
        return transitSimu(new EditPokemonMovesBeanSearch((EditPokemonMovesBean) selPk_), simu_.getBuilder());
    }

    protected static CommonBean pkTrainerSetMovesCat(String _name) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean editPkTrainer_ = goToAddPkTrainer(simu_);
        CommonBean selPk_ = transitSimu(new EditTrainerPokemonBeanAddMoves((EditTrainerPokemonBean) editPkTrainer_), simu_.getBuilder());
        callEditPokemonMovesBeanCategorySet(selPk_,_name);
        return transitSimu(new EditPokemonMovesBeanSearch((EditPokemonMovesBean) selPk_), simu_.getBuilder());
    }
    protected static CommonBean pkTrainerSetMovesCancel() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean editPkTrainer_ = goToAddPkTrainer(simu_);
        CommonBean selPk_ = transitSimu(new EditTrainerPokemonBeanAddMoves((EditTrainerPokemonBean) editPkTrainer_), simu_.getBuilder());
        return transitSimu(new EditPokemonMovesBeanCancel((EditPokemonMovesBean) selPk_), simu_.getBuilder());
    }
    protected static CommonBean pkTrainerSetMovesRemove() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean editPkTrainer_ = goToAddPkTrainer(simu_);
        CommonBean re_ = addMoveTrainer(M_POK_01_TR, 0, editPkTrainer_);
        callSelectLineMoveSelectedSet(eltSelectMove(callEditTrainerPokemonBeanMovesGet(re_),0),true);
        return transitSimu(new EditTrainerPokemonBeanDeleteMoves((EditTrainerPokemonBean) re_), simu_.getBuilder());
    }
    protected static CommonBean addPkTrainerChangeMoves(boolean _ally) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean editPkTrainer_ = goToAddPkTrainer(simu_);
        CommonBean re_ = addMoveTrainer(M_POK_01_TR, 0, editPkTrainer_);
        callSelectLineMoveSelectedSet(eltSelectMove(callEditTrainerPokemonBeanMovesGet(re_),0),true);
        CommonBean afterDel_ = transitSimu(new EditTrainerPokemonBeanDeleteMoves((EditTrainerPokemonBean) re_), simu_.getBuilder());
        callEditTrainerPokemonBeanAllyPkSet(afterDel_,_ally);
        return transitSimu(new EditTrainerPokemonBeanValidateTrainerPk((EditTrainerPokemonBean) afterDel_), simu_.getBuilder());
    }
    protected static CommonBean addPkTrainerChangeItem(boolean _ally) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean editPkTrainer_ = goToAddPkTrainer(simu_);
        assertSame(editPkTrainer_, chooseItemPkTrainer(I_BALL_TR, editPkTrainer_));
        callEditTrainerPokemonBeanAllyPkSet(editPkTrainer_, _ally);
        return transitSimu(new EditTrainerPokemonBeanValidateTrainerPk((EditTrainerPokemonBean) editPkTrainer_), simu_.getBuilder());
    }
    protected static CommonBean pkTrainerSetMovesNameAdd(String _name, int _row) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean editPkTrainer_ = goToAddPkTrainer(simu_);
        return addMoveTrainer(_name, _row, editPkTrainer_);
    }

    private static CommonBean addMoveTrainer(String _name, int _row, CommonBean _editPkTrainer) {
        CommonBean editMoves_ = transitSimu(new EditTrainerPokemonBeanAddMoves((EditTrainerPokemonBean) _editPkTrainer), _editPkTrainer.getBuilder());
        return addMoveGene(_name, _row, editMoves_);
    }

    private static CommonBean addMovePlayer(String _name, int _row, CommonBean _editPkPlayer) {
        CommonBean editMoves_ = transitSimu(new EditPokemonBeanAddMoves((EditPokemonBean) _editPkPlayer), _editPkPlayer.getBuilder());
        callEditPokemonMovesBeanAvailableMovesOnlySet(editMoves_,false);
        return addMoveGene(_name, _row, editMoves_);
    }

    private static CommonBean searchMovePlayer(String _name, CommonBean _editPkPlayer, boolean _flag) {
        CommonBean editMoves_ = transitSimu(new EditPokemonBeanAddMoves((EditPokemonBean) _editPkPlayer), _editPkPlayer.getBuilder());
        callEditPokemonMovesBeanAvailableMovesOnlySet(editMoves_, _flag);
        callEditPokemonMovesBeanTypedNameSet(editMoves_, _name);
        return transitSimu(new EditPokemonMovesBeanSearch((EditPokemonMovesBean) editMoves_), _editPkPlayer.getBuilder());
    }

    private static CommonBean addMoveGene(String _name, int _row, CommonBean _editMoves) {
        callEditPokemonMovesBeanTypedNameSet(_editMoves, _name);
        CommonBean foundMoves_ = transitSimu(new EditPokemonMovesBeanSearch((EditPokemonMovesBean) _editMoves), _editMoves.getBuilder());
        callSelectLineMoveSelectedSet(eltSelectMove(callEditPokemonMovesBeanMovesGet(foundMoves_), _row),true);
        return transitSimu(new EditPokemonMovesBeanAddMoves((EditPokemonMovesBean) foundMoves_), _editMoves.getBuilder());
    }
    protected static CommonBean pkTrainerSetMovesType(String _type, boolean _wholeWord) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean editPkTrainer_ = goToAddPkTrainer(simu_);
        CommonBean selPk_ = transitSimu(new EditTrainerPokemonBeanAddMoves((EditTrainerPokemonBean) editPkTrainer_), simu_.getBuilder());
        callEditPokemonMovesBeanTypedTypeSet(selPk_,_type);
        callEditPokemonMovesBeanWholeWordSet(selPk_,_wholeWord);
        return transitSimu(new EditPokemonMovesBeanSearch((EditPokemonMovesBean) selPk_), simu_.getBuilder());
    }
    protected static CommonBean pkTrainerSetMoves() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean editPkTrainer_ = goToAddPkTrainer(simu_);
        return goToSetMovesTrainer(editPkTrainer_);
    }
    protected static CommonBean pkTrainer(boolean _select) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        CommonBean edit_ = goToAddPkTrainer(simu_);
        callSelectLineMoveSelectedSet(eltSelectMove(callEditTrainerPokemonBeanMovesGet(edit_),0),_select);
        return edit_;
    }
    protected static String editNoPlayerPk() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
//        callSimulationBeanSelectedPkSet(simu_,-1);
        return navigateData(new SimulationBeanSelectPkValidation((SimulationBean) simu_,((SimulationBean)simu_).getSelectedAction(),-1),simu_);
    }
    protected static CommonBean pkPlayerRemove() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        pkTrainerSelectPkPlayerNameCycle(P_POK_00_TR, A_SIM_1, simu_, 4);
        return editPkPlayer(simu_, P_POK_01_TR, A_SIM_2_TR, 0, 5, TeamCrud.REMOVE);
    }
    protected static String editNoSelectedPlayerPk() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        CommonBean added_ = pkTrainerSelectPkPlayerNameCycle(P_POK_00_TR, A_SIM_1, simu_, 4);
//        callSimulationBeanSelectedActionSet(added_, TeamCrud.NOTHING.getTeamCrudString());
//        callSimulationBeanSelectedPkSet(added_,-1);
        return navigateData(new SimulationBeanSelectPkValidation((SimulationBean) simu_,TeamCrud.NOTHING.getTeamCrudString(),-1),added_);
    }
//    protected static PokemonBeanStruct editNoPkStateSelectZero() {
//        PokemonBeanStruct simu_ = editNoPkState();
//        callSimulationBeanSelectedPkSet(simu_,0);
//        callSimulationBeanSelectedActionSet(simu_, TeamCrud.NOTHING.getTeamCrudString());
//        return simu_;
//    }
//    protected static PokemonBeanStruct editNoPkStateSelectZero(TeamCrud _tc) {
//        PokemonBeanStruct simu_ = editNoPkState();
//        callSimulationBeanSelectedPkSet(simu_,0);
//        callSimulationBeanSelectedActionSet(simu_, _tc.getTeamCrudString());
//        return simu_;
//    }
//    protected static PokemonBeanStruct editNoPkStateSelectNo() {
//        PokemonBeanStruct simu_ = editNoPkState();
//        callSimulationBeanSelectedPkSet(simu_,-1);
//        callSimulationBeanSelectedActionSet(simu_, TeamCrud.EDIT.getTeamCrudString());
//        return simu_;
//    }
//    protected static PokemonBeanStruct editNoPkState() {
//        PkData pk_ = pkDataByFacade(db());
//        return simBean(2, pk_.getDataBase());
//    }

    protected static CommonBean editEditSelectedPlayerPk() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean2(2, pk_);
        foeTeamsSample(simu_);
        return editPkPlayer(simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
    }
    protected static CommonBean editEditSelectedPlayerPkForm(boolean _heal, Rate _exp, Rate _hp) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        CommonBean editing_ = editPkPlayer(simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        callEditPokemonBeanHealSet(editing_, _heal);
        callEditPokemonBeanBallSet(editing_,I_BALL);
        callEditPokemonBeanExperienceSet(editing_, _exp);
        callEditPokemonBeanHappinessSet(editing_,1);
        callEditPokemonBeanRemainingHpSet(editing_, _hp);
        secondStatisticEv(eltStatisticEv(callEditPokemonBeanEvGet(editing_),2)).getEv().valueLong(33);
//        callEvLineEvSet(second(elt(callEditPokemonBeanEvGet(editing_),2)),33);
        return transitSimu(new EditPokemonBeanEdit((EditPokemonBean) editing_), simu_.getBuilder());
    }
    protected static CommonBean editEditSelectedPlayerPkFormNoMove() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        CommonBean editing_ = editPkPlayer(simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        callSelectLineMoveSelectedSet(eltSelectMove(callEditPokemonBeanMovesGet(editing_),0),true);
        transitSimu(new EditPokemonBeanDeleteMoves((EditPokemonBean) editing_), simu_.getBuilder());
        return transitSimu(new EditPokemonBeanEdit((EditPokemonBean) editing_), simu_.getBuilder());
    }
    protected static CommonBean editEditSelectedPlayerPkFormCancel() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        CommonBean editing_ = editPkPlayer(simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        callSelectLineMoveSelectedSet(eltSelectMove(callEditPokemonBeanMovesGet(editing_),0),true);
        transitSimu(new EditPokemonBeanDeleteMoves((EditPokemonBean) editing_), simu_.getBuilder());
        return transitSimu(new EditPokemonBeanCancel((EditPokemonBean) editing_), simu_.getBuilder());
    }
    protected static CommonBean editEditSelectedPlayerPkHeal(boolean _heal) {
        CommonBean h_ = editEditSelectedPlayerPk();
        callEditPokemonBeanHealSet(h_,_heal);
        return h_;
    }
    protected static CommonBean editEditSelectedPlayerPkItem() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        CommonBean edit_ = editPkPlayer(simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        assertSame(edit_, chooseItemPkPlayer(I_BALL_TR, edit_));
        return edit_;
    }
    protected static CommonBean editEditSelectedPlayerPkItemCancelItem() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        CommonBean edit_ = editPkPlayer(simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        CommonBean chosen_ = chooseItemPkPlayer(I_BALL_TR, edit_);
        CommonBean redo_ = transitSimu(new EditPokemonBeanChooseItem((EditPokemonBean) chosen_), simu_.getBuilder());
        return transitSimu(new SelectItemBeanCancelItem((SelectItemBean) redo_), simu_.getBuilder());
    }
    protected static CommonBean editEditSelectedPlayerPkItemCancel() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        CommonBean edit_ = editPkPlayer(simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        CommonBean first_ = transitSimu(new EditPokemonBeanChooseItem((EditPokemonBean) edit_), simu_.getBuilder());
        CommonBean back_ = transitSimu(new SelectItemBeanCancel((SelectItemBean) first_), simu_.getBuilder());
        return chooseItemPkPlayer(I_BALL_TR, back_);
    }
    protected static CommonBean editEditSelectedPlayerPkItemPart(int _row) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        CommonBean edit_ = editPkPlayer(simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        CommonBean chosen_ = chooseItemPkPlayer(DataBase.EMPTY_STRING, edit_);
        return transitSimu(new SelectItemBeanClickLink((SelectItemBean) chosen_, ((SelectItemBean) chosen_).getItems().get(_row).getName()), simu_.getBuilder());
    }
    //    protected static PokemonBeanStruct formEditSelectedPlayerPk() {
//        PkData pk_ = pkDataByFacade(db());
//        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
//        foeTeamsSample(simu_);
//        PokemonBeanStruct added_ = pkTrainerSelectPkPlayerNameCycle(P_POK_00_TR, A_SIM_1, simu_, 4);
//        callSimulationBeanSelectedActionSet(added_, TeamCrud.EDIT.getTeamCrudString());
//        callSimulationBeanSelectedPkSet(added_,0);
//        return added_;
//    }
//    protected static NaSt editEditSelectedPlayerPkAddMove() {
//        PkData pk_ = pkDataByFacade(db());
//        StringMap<NaSt> all_ = beanToSimu(pk_);
//        StringMap<String> mapping_ = mappingToSimu();
//        NaSt simu_ = simu(pk_, all_, mapping_, 2);
//        foeTeamsSample(pk_, all_, mapping_, simu_);
//        NaSt editing_ = editPkPlayer(pk_, all_, mapping_, simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
//        addMovePlayer(M_POK_01_TR,0,pk_,all_,mapping_,editing_);
//        return transitSimu(pk_,all_,mapping_,new EditPokemonBeanEdit(),editing_);
//    }
    protected static CommonBean editEditSelectedPlayerPkListMoves() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        CommonBean editing_ = editPkPlayer(simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        return transitSimu(new EditPokemonBeanAddMoves((EditPokemonBean) editing_), simu_.getBuilder());
    }
    protected static CommonBean editEditSelectedPlayerPkListMoves(String _name, boolean _flag) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        CommonBean editing_ = editPkPlayer(simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        return searchMovePlayer(_name, editing_,_flag);
    }
    protected static CommonBean editEditSelectedPlayerPkListMovesCancel() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        CommonBean editing_ = editPkPlayer(simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        CommonBean se_ = searchMovePlayer(M_POK_04_TR, editing_, false);
        callSelectLineMoveSelectedSet(eltSelectMove(callEditPokemonMovesBeanMovesGet(se_),0),true);
        return transitSimu(new EditPokemonMovesBeanCancel((EditPokemonMovesBean) se_), simu_.getBuilder());
    }

    private static CommonBean editPkPlayer(CommonBean _simu, String _name, String _ab, int _index, int _level, TeamCrud _mode) {
        CommonBean added_ = pkTrainerSelectPkPlayerNameCycle(_name, _ab, _simu, _level);
//        callSimulationBeanSelectedActionSet(added_, _mode.getTeamCrudString());
//        callSimulationBeanSelectedPkSet(added_, _index);
        return transitSimu(new SimulationBeanSelectPkValidation((SimulationBean) added_, _mode.getTeamCrudString(), _index), _simu.getBuilder());
    }

    protected static CommonBean pkPlayerSetMovesRemove() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        CommonBean editing_ = editPkPlayer(simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        CommonBean re_ = addMovePlayer(M_POK_01_TR, 0, editing_);
        callSelectLineMoveSelectedSet(eltSelectMove(callEditPokemonBeanMovesGet(re_),0),true);
        return transitSimu(new EditPokemonBeanDeleteMoves((EditPokemonBean) re_), simu_.getBuilder());
    }
    protected static String editForgetSelectedPlayerPk() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        CommonBean added_ = pkTrainerSelectPkPlayerNameCycle(P_POK_00_TR, A_SIM_1, simu_, 4);
//        callSimulationBeanSelectedActionSet(added_, TeamCrud.NOTHING.getTeamCrudString());
//        callSimulationBeanSelectedPkSet(added_,0);
        return navigateData(new SimulationBeanSelectPkValidation((SimulationBean)added_,TeamCrud.NOTHING.getTeamCrudString(),0),added_);
    }
    protected static CommonBean addPkPlayerChangeMoves() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        CommonBean editing_ = editPkPlayer(simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        assertSame(editing_, chooseItemPkPlayer(I_BALL_TR, editing_));
        CommonBean re_ = addMovePlayer(M_POK_01_TR, 0, editing_);
        callSelectLineMoveSelectedSet(eltSelectMove(callEditPokemonBeanMovesGet(re_),0),true);
        CommonBean afterDel_ = transitSimu(new EditPokemonBeanDeleteMoves((EditPokemonBean) re_), simu_.getBuilder());
        return transitSimu(new EditPokemonBeanEdit((EditPokemonBean) afterDel_), simu_.getBuilder());
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
    private static CommonBean pkTrainerSelectPkPlayerNameCycle(String _name, String _ability, CommonBean _simu, int _level) {
        CommonBean addPk_ = goToAddPkPlayer(_simu);
        callAddPokemonBeanTypedNameSet(addPk_,_name);
        CommonBean afSearch_ = transitSimu(new AddPokemonBeanSearch((AddPokemonBean) addPk_), _simu.getBuilder());
        callAddPokemonBeanAbilitySet(afSearch_,_ability);
        callAddPokemonBeanLevelSet(afSearch_,_level);
        CommonBean afterAddEdit_ = transitSimu(new AddPokemonBeanAdd((AddPokemonBean) afSearch_), _simu.getBuilder());
        assertSame(afterAddEdit_, _simu);
        return afterAddEdit_;
    }
    protected static CommonBean pkPlayerSelectPk() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        return goToAddPkPlayer(simu_);
    }
    protected static CommonBean pkPlayerSelectPkCancel() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        CommonBean addPk_ = goToAddPkPlayer(simu_);
        return transitSimu(new AddPokemonBeanCancel((AddPokemonBean) addPk_), simu_.getBuilder());
    }

    protected static CommonBean pkPlayerSelectPkNameAbility(String _name) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        return pkPlAb(_name, simu_);
    }

    protected static CommonBean pkPlayerSelectPkNameTwice() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        pkTrainerSelectPkPlayerNameCycle(P_POK_01_TR,A_SIM_1, simu_,40);
        return pkTrainerSelectPkPlayerNameCycle(P_POK_02_TR,A_SIM_2, simu_,39);
    }

    protected static CommonBean pkPlayerValidateEvosNoSelect() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
//        callSimulationBeanSelectedPkSet(simu_,-1);
        return transitSimu(new SimulationBeanSelectPkEvosValidation((SimulationBean) simu_, -1), simu_.getBuilder());
    }

    protected static CommonBean pkPlayerValidateEvosSelect() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
//        callSimulationBeanSelectedPkSet(simu_,1);
        return transitSimu(new SimulationBeanSelectPkEvosValidation((SimulationBean) simu_, 1), simu_.getBuilder());
    }

    protected static CommonBean pkPlayerValidateEvoValues() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
//        callSimulationBeanSelectedPkSet(simu_,1);
        transitSimu(new SimulationBeanSelectPkEvosValidation((SimulationBean) simu_, 1), simu_.getBuilder());
        callSimulationBeanChosenEvoSet(simu_,P_POK_03);
        callSimulationBeanLevelEvoSet(simu_,41);
        return simu_;
    }

    protected static CommonBean pkPlayerValidateEvoValidate() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
//        callSimulationBeanSelectedPkSet(simu_,1);
        transitSimu(new SimulationBeanSelectPkEvosValidation((SimulationBean) simu_, 1), simu_.getBuilder());
        callSimulationBeanChosenEvoSet(simu_,P_POK_03);
        callSimulationBeanLevelEvoSet(simu_,39);
        return transitSimu(new SimulationBeanValidateEvo((SimulationBean) simu_), simu_.getBuilder());
    }

    protected static CommonBean pkPlayerEvoThenFighters() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        return validEvos(simu_);
    }

    protected static CommonBean pkPlayerEvoFightersImmediateValid() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
//        callSimulationBeanSelectedPkSet(simu_,-1);
        return transitSimu(new SimulationBeanSelectPkFrontValidation((SimulationBean) simu_, -1), simu_.getBuilder());
    }

    protected static CommonBean pkPlayerEvoFightersFormValid() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
        return changeFighterPosition(simu_, 0, 0, 0);
    }

    protected static CommonBean pkPlayerEvoFightersWithoutFronts() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
        return transitSimu(new SimulationBeanValidateFrontFighters((SimulationBean) simu_), simu_.getBuilder());
    }

    protected static CommonBean pkPlayerEvoFightersSufficientFronts() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
        return fighterPositions(simu_);
    }

    protected static CommonBean pkPlayerEvoFightersSufficientFrontsFormMove(int _index) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
        fighterPositions(simu_);
//        callSimulationBeanSelectedPkSet(simu_,_index);
        assertSame(simu_,transitSimu(new SimulationBeanSelectPkMovesValidation((SimulationBean) simu_, _index), simu_.getBuilder()));
        return simu_;
    }

    protected static CommonBean pkPlayerEvoFightersSufficientFrontsFormMoveValidateMovesQuick(int _index, String _ab) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
        fighterPositions(simu_);
//        callSimulationBeanSelectedPkSet(simu_,_index);
        assertSame(simu_,transitSimu(new SimulationBeanSelectPkMovesValidation((SimulationBean) simu_, _index), simu_.getBuilder()));
        return movesAbilities(_ab, simu_);
    }

    protected static CommonBean pkPlayerEvoFightersSufficientFrontsFormMoveValidateMovesQuickTwice(int _index, String _ab) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
        fighterPositions(simu_);
//        callSimulationBeanSelectedPkSet(simu_,_index);
        assertSame(simu_,transitSimu(new SimulationBeanSelectPkMovesValidation((SimulationBean) simu_, _index), simu_.getBuilder()));
        movesAbilities(_ab, simu_);
        return movesAbilities(DataBase.EMPTY_STRING, simu_);
    }

    protected static CommonBean pkPlayerEvoFightersSufficientFrontsFormMoveValidateMovesAllFightersOk() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
        fighterPositions(simu_);
        return movesSet(simu_);
    }

    protected static CommonBean pkPlayerEvoFighterChoice(int _index, int _round) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
        fighterPositions(simu_);
        movesSet(simu_);
//        transitSimu(new SimulationBeanSelectPkMovesValidation((SimulationBean) simu_.getInstance(),_index),simu_.getInstance().getBuilder());
//        callSimulationBeanSelectedPkSet(simu_,_index);
//        callSimulationBeanSelectedRoundSet(simu_,Long.toString(_round));
        ((SimulationBean) simu_).getSelectedRound().valueInt(_round);
        return transitSimu(new SimulationBeanSelectPkMovesValidation((SimulationBean) simu_,_index),simu_.getBuilder());
    }

    protected static CommonBean pkPlayerEvoFighterChoiceAfter(int _index, int _round, boolean _allyChoice, int _move, int _target) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
        fighterPositions(simu_);
        movesSet(simu_);
        return moveChoice(_index, _round, _allyChoice, _move, _target, simu_);
    }

    protected static CommonBean pkPlayerEvoFighterSimulate() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
        fighterPositions(simu_);
        movesSet(simu_);
        return mvChs(simu_);
    }

    protected static CommonBean pkPlayerEvoFighterSimulateStMove() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
        fighterPositions(simu_);
        movesSet(simu_);
        moveChoices(simu_);
        return transitSimu(new SimulationBeanCancelMovesEvos((SimulationBean) simu_), simu_.getBuilder());
    }

    protected static CommonBean pkPlayerEvoFighterSimulateStMoveCancel() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
        fighterPositions(simu_);
        movesSet(simu_);
        moveChoices(simu_);
        transitSimu(new SimulationBeanCancelMovesEvos((SimulationBean) simu_), simu_.getBuilder());
        transitSimu(new SimulationBeanCancelMovesSets((SimulationBean) simu_), simu_.getBuilder());
        return transitSimu(new SimulationBeanCancelFrontFighters((SimulationBean) simu_), simu_.getBuilder());
    }

    protected static CommonBean pkPlayerEvoFighterSimulateStMoveCancel2() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
        fighterPositions(simu_);
        movesSet(simu_);
        moveChoices(simu_);
        transitSimu(new SimulationBeanCancelMovesEvos((SimulationBean) simu_), simu_.getBuilder());
        transitSimu(new SimulationBeanCancelMovesSets((SimulationBean) simu_), simu_.getBuilder());
        transitSimu(new SimulationBeanCancelFrontFighters((SimulationBean) simu_), simu_.getBuilder());
        return transitSimu(new SimulationBeanCancelEvolutions((SimulationBean) simu_), simu_.getBuilder());
    }

    protected static CommonBean pkPlayerFighterSimulate() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        playerTeamSampleSkip(simu_);
        return oneFight(simu_);
    }

    protected static CustList<String> pkPlayerFighterSimulateComment() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        playerTeamSampleSkip(simu_);
        oneFight(simu_);
        transitSimu(new SimulationBeanDisplayComments((SimulationBean) simu_), simu_.getBuilder());
        transitSimu(new SimulationBeanHideComments((SimulationBean) simu_), simu_.getBuilder());
        return callSimulationBeanCommentsGet(simu_);
    }

    protected static CommonBean pkPlayerFighterSimulateAfterFight() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        playerTeamSampleSkip(simu_);
        oneFight(simu_);
        transitSimu(new SimulationBeanNextFight((SimulationBean) simu_), simu_.getBuilder());
        return transitSimu(new SimulationBeanSelectPkEvosAfterValidation((SimulationBean) simu_, ((SimulationBean) simu_).getSelectedPk()), simu_.getBuilder());
    }

    protected static CommonBean pkPlayerFighterSimulateAfterFightCancel() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        playerTeamSampleSkip(simu_);
        oneFight(simu_);
        transitSimu(new SimulationBeanNextFight((SimulationBean) simu_), simu_.getBuilder());
        return transitSimu(new SimulationBeanChangeFightWhileEnd((SimulationBean) simu_), simu_.getBuilder());
    }

    protected static CommonBean pkPlayerFighterSimulateAfterFightCancel2() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        playerTeamSampleSkip(simu_);
        oneFight(simu_);
        transitSimu(new SimulationBeanNextFight((SimulationBean) simu_), simu_.getBuilder());
        transitSimu(new SimulationBeanChangeFightWhileEnd((SimulationBean) simu_), simu_.getBuilder());
        return transitSimu(new SimulationBeanChangeFight((SimulationBean) simu_), simu_.getBuilder());
    }

    protected static CommonBean pkPlayerFighterSimulateAfterFightCancel3() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        playerTeamSampleSkip(simu_);
        oneFight(simu_);
        transitSimu(new SimulationBeanNextFight((SimulationBean) simu_), simu_.getBuilder());
        transitSimu(new SimulationBeanChangeFightWhileEnd((SimulationBean) simu_), simu_.getBuilder());
        transitSimu(new SimulationBeanChangeFight((SimulationBean) simu_), simu_.getBuilder());
        return transitSimu(new SimulationBeanCancelMovesEvos((SimulationBean) simu_), simu_.getBuilder());
    }

    protected static CommonBean pkPlayerFighterSimulateAfterFightCancel4() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        playerTeamSampleSkip(simu_);
        oneFight(simu_);
        transitSimu(new SimulationBeanNextFight((SimulationBean) simu_), simu_.getBuilder());
        transitSimu(new SimulationBeanChangeFightWhileEnd((SimulationBean) simu_), simu_.getBuilder());
        transitSimu(new SimulationBeanChangeFight((SimulationBean) simu_), simu_.getBuilder());
        transitSimu(new SimulationBeanCancelMovesEvos((SimulationBean) simu_), simu_.getBuilder());
        transitSimu(new SimulationBeanCancelFrontFighters((SimulationBean) simu_), simu_.getBuilder());
        transitSimu(new SimulationBeanCancelTeam((SimulationBean) simu_), simu_.getBuilder());
        return transitSimu(new SimulationBeanCancelDiffChoice((SimulationBean) simu_), simu_.getBuilder());
    }

    protected static CommonBean pkPlayerFighterSimulateAfterFightOne() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        playerTeamSampleSkip(simu_);
        oneFight(simu_);
        transitSimu(new SimulationBeanNextFight((SimulationBean) simu_), simu_.getBuilder());
//        callSimulationBeanSelectedPkSet(simu_,0);
        return transitSimu(new SimulationBeanSelectPkEvosAfterValidation((SimulationBean) simu_, 0), simu_.getBuilder());
    }

    protected static CommonBean pkPlayerFighterSimulateAfterFightOneValidate() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        playerTeamSampleSkip(simu_);
        oneFight(simu_);
        transitSimu(new SimulationBeanNextFight((SimulationBean) simu_), simu_.getBuilder());
//        callSimulationBeanSelectedPkSet(simu_,0);
        transitSimu(new SimulationBeanSelectPkEvosAfterValidation((SimulationBean) simu_, 0), simu_.getBuilder());
        callSimulationBeanEvolutionAfterFightSet(simu_,P_POK_05);
        transitSimu(new SimulationBeanValidateEvolutionAfterFight((SimulationBean) simu_), simu_.getBuilder());
        callSimulationBeanAbilityAfterFightSet(simu_,A_SIM_2);
        transitSimu(new SimulationBeanValidateMovesAbilityAfterFight((SimulationBean) simu_), simu_.getBuilder());
        return transitSimu(new SimulationBeanValidateMovesAfterFight((SimulationBean) simu_), simu_.getBuilder());
    }

    protected static CommonBean pkPlayerFighterSimulateAfterFightCancelOne() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        playerTeamSampleSkip(simu_);
        oneFight(simu_);
        transitSimu(new SimulationBeanNextFight((SimulationBean) simu_), simu_.getBuilder());
//        callSimulationBeanSelectedPkSet(simu_,0);
        transitSimu(new SimulationBeanSelectPkEvosAfterValidation((SimulationBean) simu_, 0), simu_.getBuilder());
        callSimulationBeanEvolutionAfterFightSet(simu_,P_POK_05);
        transitSimu(new SimulationBeanValidateEvolutionAfterFight((SimulationBean) simu_), simu_.getBuilder());
        return transitSimu(new SimulationBeanCancelEvolutionsAfterFight((SimulationBean) simu_), simu_.getBuilder());
    }

    private static CommonBean oneFight(CommonBean _simu) {
        fighterPositions(_simu);
        return mvChs(_simu);
    }

    protected static CommonBean pkPlayerFighterSimulateOneFight() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(1, pk_);
        foeTeamSample(simu_);
        playerTeamSampleSkip(simu_);
        fighterPositions(simu_);
        return mvChs(simu_);
    }

    protected static CommonBean pkPlayerFighterSkipEvosStateBadNbCount() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        CommonBean editing_ = editPkPlayer(simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        assertSame(editing_, chooseItemPkPlayer(I_BALL_TR, editing_));
        CommonBean re_ = addMovePlayer(M_POK_01_TR, 0, editing_);
        callSelectLineMoveSelectedSet(eltSelectMove(callEditPokemonBeanMovesGet(re_),0),true);
        CommonBean afterDel_ = transitSimu(new EditPokemonBeanDeleteMoves((EditPokemonBean) re_), simu_.getBuilder());
        transitSimu(new EditPokemonBeanEdit((EditPokemonBean) afterDel_), simu_.getBuilder());
        pkTrainerSelectPkPlayerNameCycle(P_POK_04_TR,A_SIM_1, simu_,41);
        return transitSimu(new SimulationBeanValidateFoeChoiceSkipEvolutions((SimulationBean) simu_), simu_.getBuilder());
    }

    protected static CommonBean pkPlayerFighterSkipEvosStateEmpty() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        return transitSimu(new SimulationBeanValidateFoeChoiceSkipEvolutions((SimulationBean) simu_), simu_.getBuilder());
    }

    protected static CommonBean pkPlayerEvoFighterSimulateKo() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
        fighterPositions(simu_);
        movesSet(simu_);
        CommonBean koChs_ = moveChoicesKo(simu_);
        return transitSimu(new SimulationBeanSimulateFight((SimulationBean) koChs_), simu_.getBuilder());
    }

    protected static CommonBean pkPlayerEvoFighterSimulateKos() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSampleInv(simu_);
        playerTeamSampleInv(simu_);
        transitSimu(new SimulationBeanValidateEvolutions((SimulationBean) simu_), simu_.getBuilder());
        fighterPositions(simu_);
        movesSet(simu_);
        return mvChs(simu_);
    }

    private static CommonBean mvChs(CommonBean _simu) {
        CommonBean first_ = moveChoices(_simu);
        return transitSimu(new SimulationBeanSimulateFight((SimulationBean) first_), _simu.getBuilder());
    }

    private static CommonBean moveChoices(CommonBean _simu) {
        moveChoice(0,0,0,0, _simu);
        moveChoice(1,0,0,1, _simu);
        moveChoice(0,1,0,0, _simu);
        return moveChoice(1,1,0,1, _simu);
    }

    private static CommonBean moveChoicesKo(CommonBean _simu) {
        moveChoice(0,0,0,0, _simu);
        moveChoice(1,0,0,1, _simu);
        moveChoice(0,0,0,0, _simu);
        return moveChoice(1,0,0,1, _simu);
    }

    private static CommonBean moveChoice(int _index, int _round, int _move, int _target, CommonBean _simu) {
        return moveChoice(_index,_round,false,_move,_target, _simu);
    }
    private static CommonBean moveChoice(int _index, int _round, boolean _allyChoice, int _move, int _target, CommonBean _simu) {
//        callSimulationBeanSelectedPkSet(_simu, _index);
        ((SimulationBean) _simu).getSelectedRound().valueInt(_round);
        transitSimu(new SimulationBeanSelectPkMovesValidation((SimulationBean) _simu,_index),_simu.getBuilder());
//        callSimulationBeanSelectedRoundSet(_simu,Long.toString(_round));
//        beforeDisplaying(_simu);
//        callSimulationBeanAllyChoiceSet(_simu, _allyChoice);
        ((SimulationBean) _simu).getAllyChoice().setSelected(_allyChoice);
//        callSimulationBeanSelectedMoveSet(_simu, _move);
//        callSimulationBeanTargetSet(_simu, Long.toString(_target));
        ((SimulationBean) _simu).getTarget().valueInt(_target);
        return transitSimu(new SimulationBeanSelectMovesValidation((SimulationBean) _simu, _move), _simu.getBuilder());
    }

    protected static CommonBean pkPlayerEvoFightersSufficientFrontsFormMoveValidateMovesAllFightersKo() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
        fighterPositions(simu_);
//        callSimulationBeanSelectedPkSet(simu_, 1);
        assertSame(simu_,transitSimu(new SimulationBeanSelectPkMovesValidation((SimulationBean) simu_, 1), simu_.getBuilder()));
        callSimulationBeanCurrentAbilitySet(simu_, A_SIM_1);
        callSelectLineMoveSelectedSet(eltSelectMove(callSimulationBeanKeptMovesGet(simu_),0),true);
        callSelectLineMoveSelectedSet(eltSelectMove(callSimulationBeanKeptMovesGet(simu_),1),true);
        callSelectLineMoveSelectedSet(eltSelectMove(callSimulationBeanKeptMovesGet(simu_),2),true);
        transitSimu(new SimulationBeanValidateMoves((SimulationBean) simu_), simu_.getBuilder());
        return transitSimu(new SimulationBeanValidateMovesSets((SimulationBean) simu_), simu_.getBuilder());
    }

    protected static CommonBean pkPlayerEvoFightersSufficientFrontsFormMoveValidateMovesAllFightersCancel() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
        fighterPositions(simu_);
//        callSimulationBeanSelectedPkSet(simu_, 1);
        assertSame(simu_,transitSimu(new SimulationBeanSelectPkMovesValidation((SimulationBean) simu_, 1), simu_.getBuilder()));
        callSimulationBeanCurrentAbilitySet(simu_, A_SIM_1);
        callSelectLineMoveSelectedSet(eltSelectMove(callSimulationBeanKeptMovesGet(simu_),0),true);
        callSelectLineMoveSelectedSet(eltSelectMove(callSimulationBeanKeptMovesGet(simu_),1),true);
        callSelectLineMoveSelectedSet(eltSelectMove(callSimulationBeanKeptMovesGet(simu_),2),true);
        transitSimu(new SimulationBeanValidateMoves((SimulationBean) simu_), simu_.getBuilder());
        transitSimu(new SimulationBeanCancelMoves((SimulationBean) simu_), simu_.getBuilder());
        return transitSimu(new SimulationBeanValidateMovesSets((SimulationBean) simu_), simu_.getBuilder());
    }

    private static CommonBean movesSet(CommonBean _simu) {
        withoutAbility(0, _simu);
        withAbility(1,A_SIM_1, _simu);
        withoutAbility(2, _simu);
        withAbility(3,A_SIM_1, _simu);
        return transitSimu(new SimulationBeanValidateMovesSets((SimulationBean) _simu), _simu.getBuilder());
    }

    private static CommonBean withAbility(int _index, String _ab, CommonBean _simu) {
//        callSimulationBeanSelectedPkSet(_simu, _index);
        assertSame(_simu,transitSimu(new SimulationBeanSelectPkMovesValidation((SimulationBean) _simu, _index), _simu.getBuilder()));
        movesAbilities(_ab, _simu);
        movesAbilities(DataBase.EMPTY_STRING, _simu);
        return movesAbilities(DataBase.EMPTY_STRING, _simu);
    }

    private static CommonBean withoutAbility(int _index, CommonBean _simu) {
//        callSimulationBeanSelectedPkSet(_simu, _index);
        assertSame(_simu,transitSimu(new SimulationBeanSelectPkMovesValidation((SimulationBean) _simu, _index), _simu.getBuilder()));
        movesAbilities(DataBase.EMPTY_STRING, _simu);
        movesAbilities(DataBase.EMPTY_STRING, _simu);
        return movesAbilities(DataBase.EMPTY_STRING, _simu);
    }

    private static CommonBean movesAbilities(String _ab, CommonBean _simu) {
        callSimulationBeanCurrentAbilitySet(_simu, _ab);
        return transitSimu(new SimulationBeanValidateMoves((SimulationBean) _simu), _simu.getBuilder());
    }

    private static CommonBean fighterPositions(CommonBean _simu) {
        changeFighterPosition(_simu,0,0,0);
        changeFighterPosition(_simu,1,0,1);
        changeFighterPosition(_simu,2,0,Fighter.BACK);
        changeFighterPosition(_simu,3,0,Fighter.BACK);
        changeFighterPosition(_simu,0,1,0);
        changeFighterPosition(_simu,1,1,1);
        changeFighterPosition(_simu,2,1,Fighter.BACK);
        changeFighterPosition(_simu,3,1,Fighter.BACK);
        return transitSimu(new SimulationBeanValidateFrontFighters((SimulationBean) _simu), _simu.getBuilder());
    }
//
//    private static PokemonBeanStruct changeFighterPosition(PokemonBeanStruct _simu, int _index, String _round, String _place) {
////        callSimulationBeanSelectedPkSet(_simu, _index);
////        callSimulationBeanSelectedRoundSet(_simu, _round);
//        ( (SimulationBean) _simu.getInstance()).getSelectedRound().valueInt(NumberUtil.parseInt(_round));
////        callSimulationBeanPlaceFightSet(_simu, _place);
//        ( (SimulationBean) _simu.getInstance()).getPlaceFight().valueInt(NumberUtil.parseInt(_place));
//        return transitSimu(new SimulationBeanSelectPkFrontValidation(((SimulationBean) _simu.getBean()), _index), _simu.getInstance().getBuilder());
//    }
//
//    private static PokemonBeanStruct changeFighterPosition(PokemonBeanStruct _simu, int _index, int _round, String _place) {
////        callSimulationBeanSelectedPkSet(_simu, _index);
////        callSimulationBeanSelectedRoundSet(_simu, _round);
//        ( (SimulationBean) _simu.getInstance()).getSelectedRound().valueInt(_round);
////        callSimulationBeanPlaceFightSet(_simu, _place);
//        ( (SimulationBean) _simu.getInstance()).getPlaceFight().valueInt(NumberUtil.parseInt(_place));
//        return transitSimu(new SimulationBeanSelectPkFrontValidation(((SimulationBean) _simu.getBean()), _index), _simu.getInstance().getBuilder());
//    }

    private static CommonBean changeFighterPosition(CommonBean _simu, int _index, int _round, int _place) {
//        callSimulationBeanSelectedPkSet(_simu, _index);
//        callSimulationBeanSelectedRoundSet(_simu, _round);
        ((SimulationBean) _simu).getSelectedRound().valueInt(_round);
//        callSimulationBeanPlaceFightSet(_simu, _place);
        ((SimulationBean) _simu).getPlaceFight().valueInt(_place);
        return transitSimu(new SimulationBeanSelectPkFrontValidation((SimulationBean) _simu, _index), _simu.getBuilder());
    }

    private static CommonBean validEvos(CommonBean _simu) {
        validEvo(_simu,1);
        validEvo(_simu,3);
        return transitSimu(new SimulationBeanValidateEvolutions((SimulationBean) _simu), _simu.getBuilder());
    }

    protected static CommonBean pkPlayerValidateEvoValidateThenCancel() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
//        callSimulationBeanSelectedPkSet(simu_,1);
        transitSimu(new SimulationBeanSelectPkEvosValidation((SimulationBean) simu_, 1), simu_.getBuilder());
        callSimulationBeanChosenEvoSet(simu_,P_POK_03);
        callSimulationBeanLevelEvoSet(simu_,39);
        transitSimu(new SimulationBeanValidateEvo((SimulationBean) simu_), simu_.getBuilder());
        return transitSimu(new SimulationBeanCancelEvo((SimulationBean) simu_), simu_.getBuilder());
    }
    private static void validEvo(CommonBean _simu, int _index) {
//        callSimulationBeanSelectedPkSet(_simu,_index);
        transitSimu(new SimulationBeanSelectPkEvosValidation((SimulationBean) _simu, _index), _simu.getBuilder());
        callSimulationBeanChosenEvoSet(_simu,P_POK_03);
        callSimulationBeanLevelEvoSet(_simu,41);
        transitSimu(new SimulationBeanValidateEvo((SimulationBean) _simu), _simu.getBuilder());
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

    protected static CommonBean pkPlayerValidateEvos() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        return playerTeamSample(simu_);
    }

    private static CommonBean playerTeamSample(CommonBean _simu) {
        pkTrainerSelectPkPlayerNameCycle(P_POK_01_TR,A_SIM_1, _simu,41);
        pkTrainerSelectPkPlayerNameCycle(P_POK_02_TR,A_SIM_1, _simu,41);
        pkTrainerSelectPkPlayerNameCycle(P_POK_01_TR,A_SIM_1, _simu,41);
        pkTrainerSelectPkPlayerNameCycle(P_POK_02_TR,A_SIM_1, _simu,41);
        return transitSimu(new SimulationBeanValidateTeam((SimulationBean) _simu), _simu.getBuilder());
    }

    private static CommonBean playerTeamSampleSkip(CommonBean _simu) {
        CommonBean editing_ = editPkPlayer(_simu, P_POK_04_TR, A_SIM_1, 0, 41, TeamCrud.EDIT);
        assertSame(editing_, chooseItemPkPlayer(I_BALL_TR, editing_));
        assertSame(_simu, transitSimu(new EditPokemonBeanEdit((EditPokemonBean) editing_), _simu.getBuilder()));
        pkTrainerSelectPkPlayerNameCycle(P_POK_01_TR,A_SIM_1, _simu,41);
        pkTrainerSelectPkPlayerNameCycle(P_POK_02_TR,A_SIM_1, _simu,41);
        pkTrainerSelectPkPlayerNameCycle(P_POK_02_TR,A_SIM_1, _simu,41);
        return transitSimu(new SimulationBeanValidateFoeChoiceSkipEvolutions((SimulationBean) _simu), _simu.getBuilder());
    }

    private static CommonBean playerTeamSampleInv(CommonBean _simu) {
        pkTrainerSelectPkPlayerNameCycle(P_POK_01_TR,A_SIM_1, _simu,4);
        pkTrainerSelectPkPlayerNameCycle(P_POK_02_TR,A_SIM_1, _simu,4);
        pkTrainerSelectPkPlayerNameCycle(P_POK_01_TR,A_SIM_1, _simu,4);
        pkTrainerSelectPkPlayerNameCycle(P_POK_02_TR,A_SIM_1, _simu,4);
        return transitSimu(new SimulationBeanValidateTeam((SimulationBean) _simu), _simu.getBuilder());
    }

    protected static CommonBean pkPlayerValidateEvosKo() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        return transitSimu(new SimulationBeanValidateTeam((SimulationBean) simu_), simu_.getBuilder());
    }
    private static CommonBean pkPlAb(String _name, CommonBean _simu) {
        CommonBean addPk_ = goToAddPkPlayer(_simu);
        callAddPokemonBeanTypedNameSet(addPk_, _name);
        CommonBean afSearch_ = transitSimu(new AddPokemonBeanSearch((AddPokemonBean) addPk_), _simu.getBuilder());
        callAddPokemonBeanAbilitySet(afSearch_,A_SIM_2);
        callAddPokemonBeanLevelSet(afSearch_,40);
        genderSetPl(afSearch_);
        return afSearch_;
    }

    protected static CommonBean pkPlayerSelectPkNameAdded(String _name) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
//        Struct addPk_ = goToAddPkPlayer(pk_, all_, mapping_, simu_);
//        callAddPokemonBeanTypedNameSet(addPk_,_name);
//        Struct afSearch_ = transitSimu(pk_, all_, mapping_, new AddPokemonBeanSearch(), addPk_);
        CommonBean first_ = pkPlAb(_name, simu_);
        return transitSimu(new AddPokemonBeanAdd((AddPokemonBean) first_), simu_.getBuilder());
    }

    protected static CommonBean pkPlayerSelectPkNameQuickAdded() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        CommonBean addPk_ = goToAddPkPlayer(simu_);
//        callAddPokemonBeanTypedNameSet(addPk_,_name);
//        Struct afSearch_ = transitSimu(pk_, all_, mapping_, new AddPokemonBeanSearch(), addPk_);
        return transitSimu(new AddPokemonBeanAdd((AddPokemonBean) addPk_), simu_.getBuilder());
    }
    protected static CommonBean pkPlayerSelectPkName(String _name) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        CommonBean addPk_ = goToAddPkPlayer(simu_);
        callAddPokemonBeanTypedNameSet(addPk_,_name);
        return transitSimu(new AddPokemonBeanSearch((AddPokemonBean) addPk_), simu_.getBuilder());
    }
    protected static CommonBean pkPlayerSelectPkHasEvo(String _name) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        CommonBean addPk_ = goToAddPkPlayer(simu_);
        callAddPokemonBeanHasEvoSet(addPk_,_name);
        return transitSimu(new AddPokemonBeanSearch((AddPokemonBean) addPk_), simu_.getBuilder());
    }
    protected static CommonBean pkPlayerSelectPkIsEvo(String _name) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        CommonBean addPk_ = goToAddPkPlayer(simu_);
        callAddPokemonBeanIsEvoSet(addPk_,_name);
        return transitSimu(new AddPokemonBeanSearch((AddPokemonBean) addPk_), simu_.getBuilder());
    }
    protected static CommonBean pkPlayerSelectPkIsLeg(String _name) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        CommonBean addPk_ = goToAddPkPlayer(simu_);
        callAddPokemonBeanIsLegSet(addPk_,_name);
        return transitSimu(new AddPokemonBeanSearch((AddPokemonBean) addPk_), simu_.getBuilder());
    }
    protected static CommonBean pkPlayerSelectPkRow(int _row) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        CommonBean addPk_ = goToAddPkPlayer(simu_);
        callAddPokemonBeanTypedNameSet(addPk_,DataBase.EMPTY_STRING);
        CommonBean rSe_ = transitSimu(new AddPokemonBeanSearch((AddPokemonBean) addPk_), simu_.getBuilder());
        AddPokemonBean add_ = (AddPokemonBean) rSe_;
        return transitSimu(new AddPokemonBeanClickLink(add_, add_.getPokedex().get(_row).getName()), simu_.getBuilder());
    }
    protected static CommonBean pkPlayerSelectPkType(String _type, boolean _wholeWord) {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        CommonBean addPk_ = goToAddPkPlayer(simu_);
        callAddPokemonBeanTypedTypeSet(addPk_,_type);
        callAddPokemonBeanWholeWordSet(addPk_,_wholeWord);
        return transitSimu(new AddPokemonBeanSearch((AddPokemonBean) addPk_), simu_.getBuilder());
    }
    protected static CommonBean pkPlayer() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSample(simu_);
        return goToAddPkPlayer(simu_);
    }

    private static void foeTeamsSample(CommonBean _simu) {
        selectTeam(_simu,0);
        pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_01_TR, A_SIM_1_TR, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_02_TR, A_SIM_1_TR, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_03_TR, A_SIM_1_TR, _simu, 4);
        setMult(_simu,2);
        selectTeam(_simu,1);
        pkTrainerSelectPkNameCycle(false,P_POK_04_TR,A_SIM_2_TR, _simu, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_05_TR,A_SIM_2_TR, _simu, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_06_TR,A_SIM_2_TR, _simu, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_07_TR,A_SIM_2_TR, _simu, 5);
        setMult(_simu,2);
        transitSimu(new SimulationBeanValidateFoeChoiceFree((SimulationBean) _simu), _simu.getBuilder());
    }

    private static void foeTeamSample(CommonBean _simu) {
        selectTeam(_simu,0);
        pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_01_TR, A_SIM_1_TR, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_02_TR, A_SIM_1_TR, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_03_TR, A_SIM_1_TR, _simu, 4);
        setMult(_simu,2);
        transitSimu(new SimulationBeanValidateFoeChoiceFree((SimulationBean) _simu), _simu.getBuilder());
    }

    private static void foeTeamsSampleInv(CommonBean _simu) {
        selectTeam(_simu,0);
        pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, _simu, 40);
        pkTrainerSelectPkNameCycle(false, P_POK_01_TR, A_SIM_1_TR, _simu, 40);
        pkTrainerSelectPkNameCycle(false, P_POK_02_TR, A_SIM_1_TR, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_03_TR, A_SIM_1_TR, _simu, 4);
        setMult(_simu,2);
        selectTeam(_simu,1);
        pkTrainerSelectPkNameCycle(false,P_POK_04_TR,A_SIM_2_TR, _simu, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_05_TR,A_SIM_2_TR, _simu, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_06_TR,A_SIM_2_TR, _simu, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_07_TR,A_SIM_2_TR, _simu, 5);
        setMult(_simu,2);
        transitSimu(new SimulationBeanValidateFoeChoiceFree((SimulationBean) _simu), _simu.getBuilder());
    }
    private static void setMult(CommonBean _simu, int _value) {
        callSimulationBeanEnvironmentSet(_simu, EnvironmentType.ROAD.getEnvName());
        callSimulationBeanMultiplicitySet(_simu,_value);
        assertSame(_simu, transitSimu(new SimulationBeanValidateMultiplicityEnvAction((SimulationBean) _simu), _simu.getBuilder()));
//        beforeDisplaying(_simu);
    }

    private static CommonBean goToSelectPk(CommonBean _struct) {
        return transitSimu(new EditTrainerPokemonBeanChooseName((EditTrainerPokemonBean) _struct), _struct.getBuilder());
    }
    private static CommonBean goToSelectAb(CommonBean _struct) {
        return transitSimu(new EditTrainerPokemonBeanChooseAbility((EditTrainerPokemonBean) _struct), _struct.getBuilder());
    }
    private static CommonBean goToSelectIt(CommonBean _struct) {
        return transitSimu(new EditTrainerPokemonBeanChooseItem((EditTrainerPokemonBean) _struct), _struct.getBuilder());
    }
    //    private static NaSt goToSelectItPlayer(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _struct) {
//        return transitSimu(_pk, _all, _mapping, new EditPokemonBeanChooseItem(), _struct);
//    }
    private static CommonBean goToSetMovesTrainer(CommonBean _struct) {
        return transitSimu(new EditTrainerPokemonBeanAddMoves((EditTrainerPokemonBean) _struct), _struct.getBuilder());
    }
    private static CommonBean simu(int _nbTeam, CommonBean _simu) {
        CommonBean simu_ = init(_nbTeam, _simu);
        SimulationBean si_ = ((SimulationBean) simu_);
        return update(simu_, si_);
    }

    private static CommonBean update(CommonBean _com, SimulationBean _si) {
        _si.getForm().getRateWinningExpPtsFight().valueRate(_si.getDifficultyCommon().getRateWinningExpPtsFight());
        _si.getForm().getWinTrainerExp().valueRate(_si.getDifficultyCommon().getWinTrainerExp());
        _si.getForm().getRateWinMoneyBase().valueRate(_si.getDifficultyCommon().getRateWinMoneyBase());
        _si.getForm().getRateLooseMoneyWin().valueRate(_si.getDifficultyCommon().getRateLooseMoneyWin());
        _si.getForm().getWinPointsFight().setupValue(_si.getDifficultyCommon().getDiffWinningExpPtsFight());
        _si.getForm().getDamageRatePlayer().setupValue(_si.getDifficultyCommon().getDamageRatePlayer());
        _si.getForm().getDamageRateLawFoe().setupValue(_si.getDifficultyCommon().getDamageRateLawFoe());
        _si.getForm().getEnabledClosing().setSelected(_si.getDifficultyCommon().getEnabledClosing());
        _si.getForm().getAllowCatchingKo().setSelected(_si.getDifficultyCommon().getAllowCatchingKo());
        _si.getForm().getAllowedSwitchPlacesEndRound().setSelected(_si.getDifficultyCommon().getAllowedSwitchPlacesEndRound());
        _si.getForm().getSkipLearningMovesWhileNotGrowingLevel().setSelected(_si.getDifficultyCommon().getSkipLearningMovesWhileNotGrowingLevel());
        _si.getForm().getStillPossibleFlee().setSelected(_si.getDifficultyCommon().getStillPossibleFlee());
        _si.getForm().getRandomWildFight().setSelected(_si.getDifficultyCommon().getRandomWildFight());
        _si.getForm().getEndFightIfOneTeamKo().setSelected(_si.getDifficultyCommon().getEndFightIfOneTeamKo());
        _si.getForm().getRestoredMovesEndFight().setSelected(_si.getDifficultyCommon().getRestoredMovesEndFight());
        _si.getForm().getIvPlayer().valueLong(_si.getDifficultyCommon().getIvPlayer());
        _si.getForm().getIvFoe().valueLong(_si.getDifficultyCommon().getIvFoe());
        _si.getNbTeamsField().valueLong(_si.getNbTeams());
        transitSimu(new SimulationBeanValidateDiffChoice(_si, _si.getForm()), _com.getBuilder());
        return _com;
    }

    protected static CommonBean goToAddPkTrainer(CommonBean _simu) {
        return transitSimu(new SimulationBeanAddPkTrainer((SimulationBean) _simu), _simu.getBuilder());
    }

    protected static CommonBean goToAddPkPlayer(CommonBean _simu) {
        return transitSimu(new SimulationBeanAdd((SimulationBean) _simu), _simu.getBuilder());
    }

//    public static NaSt transitSimu(PokemonStandards _stds, StringMap<NaSt> _all, StringMap<String> _mapping, NatCaller _caller, NaSt _first, long... _args) {
//        String url_ = navigateData(_caller, _first, _args);
//        NaSt dest_ = _all.getVal(_mapping.getVal(url_));
//        setFormsBy(_stds,dest_,_first);
//        CommonBean s_ = (CommonBean) ((BeanStruct) dest_).getBean();
//        s_.build(_stds.getDataBase(), s_.getForms());
//        return dest_;
//    }

    public static CommonBean transitSimu(IntBeanAction _caller, IntBeanBuilderHelper _builder) {
        String url_ = _caller.actionBean();
        _builder.build(url_);
//        NaSt dest_ = _all.getVal(_mapping.getVal(url_));
//        setFormsBy((CommonBean) ((PokemonBeanStruct)dest_).getBean(),_caller.getBean());
//        CommonBean s_ = (CommonBean) ((BeanStruct) dest_).getBean();
//        s_.build(_stds.getDataBase(), s_.getForms());
        return (CommonBean) _builder.getRenders().getVal(url_);// _all.getVal(_mapping.getVal(url_));
    }
    protected static Rate integration() {
        FacadeGame pk_ = pkDataByFacade(db());
        CommonBean simu_ = init(2, beanToSimu(pk_));
//        ((SimulationBean)((PokemonBeanStruct)simu_).getBean()).getDifficultyCommon().setWinTrainerExp(Rate.newRate("5/7"));
        ((SimulationBean) simu_).getForm().getWinTrainerExp().valueRate(Rate.newRate("5/7"));
//        ((DifficultyCommonBean)((PokemonBeanStruct)all_.getVal(AikiBeansGameStd.BEAN_DIFFICULTY_COMMON)).getBean()).setWinTrainerExp(Rate.newRate("5/7"));
//        callRate(new DifficultyCommonBeanWinTrainerExpSet(),all_.getVal(AikiBeansGameStd.BEAN_DIFFICULTY_COMMON),Rate.newRate("5/7"));
        CommonBean result_ = transitSimu(new SimulationBeanValidateDiffChoice((SimulationBean) simu_, ((SimulationBean) simu_).getForm()), simu_.getBuilder());
        return ((SimulationBean) result_).getDifficultyCommon().getWinTrainerExp();
    }

    //    public static NaSt transitSimuRem(PokemonStandards _stds, NatCaller _caller, NaSt _first, long... _args) {
//        String url_ = navigateData(_caller, _first, _args);
//        assertTrue(url_.isEmpty());
////        Struct dest_ = _all.getVal(_mapping.getVal(url_));
//        setFormsBy(_stds, _first,_first);
//        beforeDisplaying(_first);
//        return _first;
//    }
    public static CommonBean beanToSimu(FacadeGame _facade) {
//        StringMap<NaSt> map_ = new StringMap<NaSt>();
//        map_.addEntry(AikiBeansStd.BEAN_WELCOME,_pk.beanWelcomeBean(EN));
//        map_.addEntry(AikiBeansGameStd.BEAN_DIFFICULTY_COMMON,_pk.beanDiffCommon(EN));
        SimulationBean simu_ = new SimulationBean();
        update(_facade,simu_);
        AddPokemonBean add_ = new AddPokemonBean();
        update(_facade,add_);
        SelectAbilityBean selAb_ = new SelectAbilityBean();
        update(_facade,selAb_);
        SelectPokemonBean selPk_ = new SelectPokemonBean();
        update(_facade,selPk_);
        SelectItemBean selIt_ = new SelectItemBean();
        update(_facade,selIt_);
        EditPokemonBean editPk_ = new EditPokemonBean();
        update(_facade,editPk_);
        EditPokemonMovesBean editMv_ = new EditPokemonMovesBean();
        update(_facade,editMv_);
        EditTrainerPokemonBean editTr_ = new EditTrainerPokemonBean();
        update(_facade,editTr_);
        SimulationLevelBean level_ = new SimulationLevelBean();
        update(_facade,level_);
        MockBeanBuilderHelper bu_ = new MockBeanBuilderHelper();
        Translations tr_ = new Translations();
        TranslationsLg en_ = new TranslationsLg();
        en_.getMapping().addEntry(MessagesPkBean.APP_BEAN_DATA, new TranslationsAppli());
        en_.getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.DIFFICULTY,new TranslationsFile());
        en_.getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.SIMULATION,new TranslationsFile());
        en_.getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.SIMU_LEVEL,new TranslationsFile());
        en_.getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.POKEDEX,new TranslationsFile());
        en_.getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.MOVES,new TranslationsFile());
        en_.getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.ABILITIES,new TranslationsFile());
        en_.getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.ITEMS,new TranslationsFile());
        en_.getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.MAP,new TranslationsFile());
        tr_.getMapping().addEntry(EN, en_);
        TranslationsLg fr_ = new TranslationsLg();
        fr_.getMapping().addEntry(MessagesPkBean.APP_BEAN_DATA, new TranslationsAppli());
        fr_.getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.DIFFICULTY,new TranslationsFile());
        fr_.getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.SIMULATION,new TranslationsFile());
        fr_.getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.SIMU_LEVEL,new TranslationsFile());
        fr_.getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.POKEDEX,new TranslationsFile());
        fr_.getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.MOVES,new TranslationsFile());
        fr_.getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.ABILITIES,new TranslationsFile());
        fr_.getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.ITEMS,new TranslationsFile());
        fr_.getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.MAP,new TranslationsFile());
        tr_.getMapping().addEntry(FR, fr_);
        bu_.setTranslations(tr_);
        bu_.setFacade(_facade);
        simu_.setBuilder(bu_);
        level_.setBuilder(bu_);
        add_.setBuilder(bu_);
        selAb_.setBuilder(bu_);
        selPk_.setBuilder(bu_);
        selIt_.setBuilder(bu_);
        editPk_.setBuilder(bu_);
        editMv_.setBuilder(bu_);
        editTr_.setBuilder(bu_);
        bu_.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML,simu_);
        bu_.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_SIMULATION_ADDPOKEMON_HTML,add_);
        bu_.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_SIMULATION_SELECTABILITY_HTML,selAb_);
        bu_.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_SIMULATION_SELECTPOKEMON_HTML,selPk_);
        bu_.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_SIMULATION_SELECTITEM_HTML,selIt_);
        bu_.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMON_HTML,editPk_);
        bu_.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONMOVES_HTML,editMv_);
        bu_.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML,editTr_);
        bu_.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATIONLEVEL_HTML,level_);
        return simu_;
    }

    protected static void update(FacadeGame _facade, CommonBean _bean) {
        _bean.setDataBase(_facade);
        _bean.setLanguage(EN);
    }
    //    public static StringMap<String> mappingToSimu() {
//        StringMap<String> map_ = new StringMap<String>();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_INDEX_HTML,AikiBeansStd.BEAN_WELCOME);
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML,AikiBeansSimulationStd.BEAN_SIMULATION);
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_ADDPOKEMON_HTML,AikiBeansSimulationStd.BEAN_ADDPOKEMON);
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SELECTABILITY_HTML,AikiBeansSimulationStd.BEAN_SELECTABILITY);
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SELECTPOKEMON_HTML,AikiBeansSimulationStd.BEAN_SELECTPOKEMON);
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SELECTITEM_HTML,AikiBeansSimulationStd.BEAN_SELECTITEM);
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMON_HTML,AikiBeansSimulationStd.BEAN_EDITPOKEMON);
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONMOVES_HTML,AikiBeansSimulationStd.BEAN_EDITPOKEMONMOVES);
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML,AikiBeansSimulationStd.BEAN_EDITTRAINERPOKEMON);
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATIONLEVEL_HTML,AikiBeansSimulationStd.BEAN_LEVEL_SIMU);
//        return map_;
//    }
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
    private static FacadeGame dbInc() {
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

    protected static CommonBean pkPlayerValidateEvosSelectTwo() {
        FacadeGame pk_ = pkDataByFacade(dbLight());
        CommonBean simu_ = simBean(2, pk_);
        return evolutions(simu_);
    }


    protected static CommonBean pkPlayerValidateEvosSelectTree() {
        FacadeGame pk_ = pkDataByFacade(dbLightSec());
        CommonBean simu_ = simBean(2, pk_);
        return evolutionsTree(simu_);
    }

    protected static CommonBean pkPlayerValidateEvosSelectTwoOnce() {
        FacadeGame pk_ = pkDataByFacade(dbLight());
        CommonBean simu_ = simBean(2, pk_);
        evolutions(simu_);
        callSimulationBeanChosenEvoSet(simu_,P_POK_01);
        callSimulationBeanLevelEvoSet(simu_,41);
        return transitSimu(new SimulationBeanValidateEvo((SimulationBean) simu_), simu_.getBuilder());
    }

    protected static CommonBean pkPlayerValidateEvosSelectTwoTwice() {
        FacadeGame pk_ = pkDataByFacade(dbLight());
        CommonBean simu_ = simBean(2, pk_);
        evolutions(simu_);
        callSimulationBeanChosenEvoSet(simu_,P_POK_01);
        callSimulationBeanLevelEvoSet(simu_,41);
        transitSimu(new SimulationBeanValidateEvo((SimulationBean) simu_), simu_.getBuilder());
        callSimulationBeanChosenEvoSet(simu_,P_POK_02);
        callSimulationBeanLevelEvoSet(simu_,41);
        return transitSimu(new SimulationBeanValidateEvo((SimulationBean) simu_), simu_.getBuilder());
    }

    protected static CommonBean pkPlayerValidateEvosSelectTwoThreeTimes() {
        FacadeGame pk_ = pkDataByFacade(dbLight());
        CommonBean simu_ = simBean(2, pk_);
        evolutions(simu_);
        callSimulationBeanChosenEvoSet(simu_,P_POK_01);
        callSimulationBeanLevelEvoSet(simu_,41);
        transitSimu(new SimulationBeanValidateEvo((SimulationBean) simu_), simu_.getBuilder());
        callSimulationBeanChosenEvoSet(simu_,P_POK_02);
        callSimulationBeanLevelEvoSet(simu_,41);
        transitSimu(new SimulationBeanValidateEvo((SimulationBean) simu_), simu_.getBuilder());
        callSimulationBeanChosenEvoSet(simu_,P_POK_03);
        callSimulationBeanLevelEvoSet(simu_,41);
        return transitSimu(new SimulationBeanValidateEvo((SimulationBean) simu_), simu_.getBuilder());
    }

    private static CommonBean evolutions(CommonBean _simu) {
        foeTeamsSampleLight(_simu);
        playerTeamSampleLight(_simu);
//        callSimulationBeanSelectedPkSet(_simu,0);
        return transitSimu(new SimulationBeanSelectPkEvosValidation((SimulationBean) _simu, 0), _simu.getBuilder());
    }

    private static CommonBean evolutionsTree(CommonBean _simu) {
        foeTeamsSampleLightTree(_simu);
        playerTeamSampleLight(_simu);
//        callSimulationBeanSelectedPkSet(_simu,0);
        return transitSimu(new SimulationBeanSelectPkEvosValidation((SimulationBean) _simu, 0), _simu.getBuilder());
    }

    private static void foeTeamsSampleLight(CommonBean _simu) {
        selectTeam(_simu,0);
        pkTrainerSelectPkNameCycle(false, P_POK_04_TR, A_SIM_1_TR, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_04_TR, A_SIM_1_TR, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_04_TR, A_SIM_1_TR, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_04_TR, A_SIM_1_TR, _simu, 4);
        selectTeam(_simu,1);
        pkTrainerSelectPkNameCycle(false,P_POK_04_TR,A_SIM_2_TR, _simu, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_04_TR,A_SIM_2_TR, _simu, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_04_TR,A_SIM_2_TR, _simu, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_04_TR,A_SIM_2_TR, _simu, 5);
        transitSimu(new SimulationBeanValidateFoeChoiceFree((SimulationBean) _simu), _simu.getBuilder());
    }


    private static void foeTeamsSampleLightTree(CommonBean _simu) {
        selectTeam(_simu,0);
        pkTrainerSelectPkNameCycle(false, P_POK_03_TR, A_SIM_1_TR, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_03_TR, A_SIM_1_TR, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_03_TR, A_SIM_1_TR, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_03_TR, A_SIM_1_TR, _simu, 4);
        selectTeam(_simu,1);
        pkTrainerSelectPkNameCycle(false,P_POK_03_TR,A_SIM_2_TR, _simu, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_03_TR,A_SIM_2_TR, _simu, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_03_TR,A_SIM_2_TR, _simu, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_03_TR,A_SIM_2_TR, _simu, 5);
        transitSimu(new SimulationBeanValidateFoeChoiceFree((SimulationBean) _simu), _simu.getBuilder());
    }

    private static CommonBean playerTeamSampleLight(CommonBean _simu) {
        pkTrainerSelectPkPlayerNameCycle(P_POK_00_TR,A_SIM_1, _simu,41);
        return transitSimu(new SimulationBeanValidateTeam((SimulationBean) _simu), _simu.getBuilder());
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

    protected static CommonBean pkPlayerFighterSimulateAfterFightOneLight() {
        FacadeGame pk_ = pkDataByFacade(dbLightThree());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSampleVeryLight(simu_);
        playerTeamSampleLightSkip(simu_);
        fighterPositionsLight(simu_);
        CommonBean light_ = moveChoicesLight(simu_);
        transitSimu(new SimulationBeanSimulateFight((SimulationBean) light_), simu_.getBuilder());
        transitSimu(new SimulationBeanNextFight((SimulationBean) simu_), simu_.getBuilder());
//        callSimulationBeanSelectedPkSet(simu_,0);
        return transitSimu(new SimulationBeanSelectPkEvosAfterValidation((SimulationBean) simu_, 0), simu_.getBuilder());
    }

    private static CommonBean moveChoicesLight(CommonBean _simu) {
        return moveChoice(0,0,0,0, _simu);
    }
    private static void foeTeamsSampleVeryLight(CommonBean _simu) {
        selectTeam(_simu,0);
        pkTrainerSelectPkNameCycle(false, P_POK_03_TR, A_SIM_1_TR, _simu, 4);
        selectTeam(_simu,1);
        pkTrainerSelectPkNameCycle(false,P_POK_03_TR,A_SIM_2_TR, _simu, 5);
        transitSimu(new SimulationBeanValidateFoeChoiceFree((SimulationBean) _simu), _simu.getBuilder());
    }

    private static CommonBean playerTeamSampleLightSkip(CommonBean _simu) {
        pkTrainerSelectPkPlayerNameCycle(P_POK_00_TR,A_SIM_1, _simu,41);
        return transitSimu(new SimulationBeanValidateFoeChoiceSkipEvolutions((SimulationBean) _simu), _simu.getBuilder());
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

    private static CommonBean fighterPositionsLight(CommonBean _simu) {
        changeFighterPosition(_simu,0,0,0);
        return transitSimu(new SimulationBeanValidateFrontFighters((SimulationBean) _simu), _simu.getBuilder());
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
    protected static CommonBean chooseTrainer() {
        FacadeGame pk_ = pkDataByFacade(dbFull());
        return simBean(0, pk_);
    }
    protected static CommonBean chooseTrainerLevel(int _place, int _level) {
        FacadeGame pk_ = pkDataByFacade(dbFull());
        CommonBean simu_ = simBean(0, pk_);
        return transitSimu(new SimulationBeanClickLevel((SimulationBean) simu_, _place, _level), simu_.getBuilder());
    }
    protected static CommonBean chooseTrainerLevelZero(int _place) {
        FacadeGame pk_ = pkDataByFacade(dbFull());
        CommonBean simu_ = simBean(0, pk_);
        return transitSimu(new SimulationBeanClickLevel((SimulationBean) simu_, _place, 0), simu_.getBuilder());
    }
    protected static CommonBean chooseTrainerLevel(int _level, int _noFight, int _tile) {
        FacadeGame pk_ = pkDataByFacade(dbFull());
        CommonBean simu_ = simBean(0, pk_);
        CommonBean sel_ = transitSimu(new SimulationBeanClickLevel((SimulationBean) simu_, 2, _level), simu_.getBuilder());
        callSimulationLevelBeanNoFightSet(sel_,_noFight);
        assertSame(sel_,transitSimu(new SimulationLevelBeanValidateNoFightAction((SimulationLevelBean) sel_), simu_.getBuilder()));
        return transitSimu(new SimulationLevelBeanClickTile((SimulationLevelBean) sel_, _tile), simu_.getBuilder());
    }
    protected static CommonBean chooseTrainerLevelZero(int _place, int _noFight, int _tile) {
        FacadeGame pk_ = pkDataByFacade(dbFull());
        CommonBean simu_ = simBean(0, pk_);
        CommonBean sel_ = transitSimu(new SimulationBeanClickLevel((SimulationBean) simu_, _place, 0), simu_.getBuilder());
        callSimulationLevelBeanNoFightSet(sel_,_noFight);
        assertSame(sel_,transitSimu(new SimulationLevelBeanValidateNoFightAction((SimulationLevelBean) sel_), simu_.getBuilder()));
        return transitSimu(new SimulationLevelBeanClickTile((SimulationLevelBean) sel_, _tile), simu_.getBuilder());
    }
    protected static CommonBean chooseTrainerLevelDualValidate() {
        FacadeGame pk_ = pkDataByFacade(dbFull());
        CommonBean simu_ = simBean(0, pk_);
        CommonBean sel_ = transitSimu(new SimulationBeanClickLevel((SimulationBean) simu_, 2, 0), simu_.getBuilder());
        callSimulationLevelBeanNoFightSet(sel_,0);
        assertSame(sel_,transitSimu(new SimulationLevelBeanValidateNoFightAction((SimulationLevelBean) sel_), simu_.getBuilder()));
        transitSimu(new SimulationLevelBeanClickTile((SimulationLevelBean) sel_, 1), simu_.getBuilder());
        return transitSimu(new SimulationBeanValidateFoeChoice((SimulationBean) simu_), simu_.getBuilder());
    }
    protected static CommonBean chooseTrainerLevelDualValidateKo() {
        FacadeGame pk_ = pkDataByFacade(dbFull());
        CommonBean simu_ = simBean(0, pk_);
        return transitSimu(new SimulationBeanValidateFoeChoice((SimulationBean) simu_), simu_.getBuilder());
    }
    protected static CommonBean simuLeagueReal() {
        FacadeGame pk_ = pkDataByFacade(dbFull());
        CommonBean simu_ = simBean(0, pk_);
        transitSimu(new SimulationBeanClickLevel((SimulationBean) simu_, 3, 0), simu_.getBuilder());
        transitSimu(new SimulationBeanValidateFoeChoice((SimulationBean) simu_), simu_.getBuilder());
        simpleTeam(simu_);
        return simu_;
    }
    protected static CommonBean simuLeagueRealSec() {
        FacadeGame pk_ = pkDataByFacade(dbFull());
        CommonBean simu_ = simBean(0, pk_);
        transitSimu(new SimulationBeanClickLevel((SimulationBean) simu_, 3, 1), simu_.getBuilder());
        transitSimu(new SimulationBeanValidateFoeChoice((SimulationBean) simu_), simu_.getBuilder());
        simpleTeamLight(simu_);
        return simu_;
    }

    private static CommonBean simBean(int _n, FacadeGame _facade) {
        return simu(_n, beanToSimu(_facade));
    }

    private static CommonBean simBean2(int _n, FacadeGame _facade) {
        CommonBean simu_ = beanToSimu(_facade);
        init(_n, simu_);
        ((SimulationBean)simu_).getDifficultyCommon().setIvPlayer(32);
        SimulationBean si_ = ((SimulationBean) simu_);
        return update(simu_, si_);
    }

    protected static CommonBean simuLeagueVirtual() {
        FacadeGame pk_ = pkDataByFacade(dbFull());
        CommonBean simu_ = simBean(2, pk_);
        foeTeamsSampleSec(simu_);
        simpleTeam(simu_);
        return simu_;
    }
    private static void foeTeamsSampleSec(CommonBean _simu) {
        selectTeam(_simu,0);
        pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_01_TR, A_SIM_2_TR, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_02_TR, A_SIM_1_TR, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_03_TR, A_SIM_2_TR, _simu, 4);
        setMult(_simu,2);
        selectTeam(_simu,1);
        pkTrainerSelectPkNameCycle(false,P_POK_04_TR,A_SIM_1_TR, _simu, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_05_TR,A_SIM_2_TR, _simu, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_06_TR,A_SIM_1_TR, _simu, 5);
        pkTrainerSelectPkNameCycle(false,P_POK_07_TR,A_SIM_2_TR, _simu, 5);
        setMult(_simu,2);
        transitSimu(new SimulationBeanValidateFoeChoiceFree((SimulationBean) _simu), _simu.getBuilder());
    }
    private static void simpleTeam(CommonBean _simu) {
        pk(_simu,0);
        pk(_simu,1);
        transitSimu(new SimulationBeanValidateFoeChoiceSkipEvolutions((SimulationBean) _simu), _simu.getBuilder());
        changeFighterPosition(_simu,0,0,0);
        changeFighterPosition(_simu,1,0,1);
        changeFighterPosition(_simu,0,1,0);
        changeFighterPosition(_simu,1,1,1);
        transitSimu(new SimulationBeanValidateFrontFighters((SimulationBean) _simu), _simu.getBuilder());
        moveChoice(0,0,0,0, _simu);
        moveChoice(1,0,0,1, _simu);
        moveChoice(0,1,0,0, _simu);
        moveChoice(1,1,0,1, _simu);
        transitSimu(new SimulationBeanSimulateFight((SimulationBean) _simu), _simu.getBuilder());
        transitSimu(new SimulationBeanNextFight((SimulationBean) _simu), _simu.getBuilder());
        transitSimu(new SimulationBeanValidateMovesAfterFight((SimulationBean) _simu), _simu.getBuilder());
        transitSimu(new SimulationBeanValidateFoeChoiceSkipEvolutions((SimulationBean) _simu), _simu.getBuilder());
        changeFighterPosition(_simu,0,0,0);
        changeFighterPosition(_simu,1,0,1);
        changeFighterPosition(_simu,0,1,0);
        changeFighterPosition(_simu,1,1,1);
        transitSimu(new SimulationBeanValidateFrontFighters((SimulationBean) _simu), _simu.getBuilder());
        moveChoice(0,0,0,0, _simu);
        moveChoice(1,0,0,1, _simu);
        moveChoice(0,1,0,0, _simu);
        moveChoice(1,1,0,1, _simu);
        transitSimu(new SimulationBeanSimulateFight((SimulationBean) _simu), _simu.getBuilder());
    }
    private static void simpleTeamLight(CommonBean _simu) {
        pk(_simu,0);
        pk(_simu,1);
        transitSimu(new SimulationBeanValidateFoeChoiceSkipEvolutions((SimulationBean) _simu), _simu.getBuilder());
        changeFighterPosition(_simu,0,0,0);
        changeFighterPosition(_simu,1,0,1);
        changeFighterPosition(_simu,0,1,0);
        changeFighterPosition(_simu,1,1,1);
        transitSimu(new SimulationBeanValidateFrontFighters((SimulationBean) _simu), _simu.getBuilder());
        moveChoice(0,0,0,0, _simu);
        moveChoice(1,0,0,1, _simu);
        moveChoice(0,1,0,0, _simu);
        moveChoice(1,1,0,1, _simu);
        transitSimu(new SimulationBeanSimulateFight((SimulationBean) _simu), _simu.getBuilder());
    }

    private static void pk(CommonBean _simu, int _teamIndex) {
        CommonBean editing_ = editPkPlayer(_simu, P_POK_01_TR, A_SIM_1, _teamIndex, 41, TeamCrud.EDIT);
        assertSame(editing_, chooseItemPkPlayer(I_BALL_TR, editing_));
        CommonBean re_ = addMovePlayer(M_POK_01_TR, 0, editing_);
        callSelectLineMoveSelectedSet(eltSelectMove(callEditPokemonBeanMovesGet(re_),0),true);
        CommonBean afterDel_ = transitSimu(new EditPokemonBeanDeleteMoves((EditPokemonBean) re_), _simu.getBuilder());
        CommonBean af_ = transitSimu(new EditPokemonBeanEdit((EditPokemonBean) afterDel_), _simu.getBuilder());
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
//        callSelectLineMoveSelectedSet(eltSelectMove(callEditPokemonBeanMovesGet(re_),0),true);
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
        bl_.setTileFileName(DataBase.EMPTY_STRING);
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