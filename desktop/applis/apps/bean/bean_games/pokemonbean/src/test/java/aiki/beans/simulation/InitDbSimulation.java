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
import code.scripts.pages.aiki.MessagesDataSimulation;
import code.scripts.pages.aiki.MessagesPkBean;
import code.sml.util.Translations;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsFile;
import code.sml.util.TranslationsLg;
import code.util.*;
import code.util.core.StringUtil;

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

    public static NaSt callSimulationBeanAbilitiesAfterFightGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrStr(( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getAbilitiesAfterFight());
    }

    public static NaSt callSimulationBeanAbilitiesGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrStr(( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getAbilities());
    }

    public static String callSimulationBeanAbilityAfterFightGet(NaSt _str, int... _args) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getAbilityAfterFight().tryRet();
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

    public static boolean callSimulationBeanAllyChoiceGet(NaSt _str, int... _args) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getAllyChoice().isSelected();
    }

    public static NaSt callSimulationBeanAllyTeamGet(NaSt _str, int... _args) {
        return PokemonStandards.getPkTrDto(( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getAllyTeam());
    }

    public static NaSt callSimulationBeanAvailableEvosGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrStr(( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getAvailableEvos());
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

    public static String callSimulationBeanChosenEvoGet(NaSt _str, int... _args) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getChosenEvo().tryRet();
    }

//    public static NaSt callSimulationBeanClickLevel(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanClickLevel(),_str,_args);
//    }

//    public static NaSt callSimulationBeanClickLevelZero(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanClickLevelZero(),_str,_args);
//    }

    public static NaSt callSimulationBeanCommentsGet(NaSt _str, int... _args) {
        return BeanNatCommonLgNames.getStringArray(( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getComments());
    }

    public static String callSimulationBeanCurrentAbilityGet(NaSt _str, int... _args) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getCurrentAbility().tryRet();
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

    public static boolean callSimulationBeanDisplayIfErrorGet(NaSt _str, int... _args) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getDisplayIfError();
    }

//    public static Struct callSimulationBeanEnabledClosingGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanEnabledClosingGet(),_str,_args);
//    }
//
//    public static Struct callSimulationBeanEndFightIfOneTeamKoGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanEndFightIfOneTeamKoGet(),_str,_args);
//    }

    public static String callSimulationBeanEnvironmentGet(NaSt _str, int... _args) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).environment();
    }

    public static NaSt callSimulationBeanEnvironmentsGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrStr(( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getEnvironments());
    }

    public static String callSimulationBeanEvolutionAfterFightGet(NaSt _str, int... _args) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getEvolutionAfterFight().tryRet();
    }

    public static NaSt callSimulationBeanEvolutionsAfterFightGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrStr(( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getEvolutionsAfterFight());
    }

    public static NaSt callSimulationBeanFoeTeamGet(NaSt _str) {
        return PokemonStandards.getPkTrDto(( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getFoeTeam());
    }

    public static boolean callSimulationBeanFreeTeamsGet(int _team) {
        return ( (SimulationBean) ((PokemonBeanStruct)initByTeam(_team)).getInstance()).getFreeTeams();
    }

    public static String callSimulationBeanGetAbility(NaSt _str, int _index) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getAbility(_index);
    }

    public static String callSimulationBeanGetAbilityAfterFight(NaSt _str, int _index) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getAbilityAfterFight(_index);
    }

    public static String callSimulationBeanGetAbilityAlly(NaSt _str, int _index) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getAbilityAlly(_index);
    }

    public static String callSimulationBeanGetAbilityFoe(NaSt _str, int _index) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getAbilityFoe(_index);
    }

    public static String callSimulationBeanGetGender(NaSt _str, int _index) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getGender(_index);
    }

    public static String callSimulationBeanGetGenderAfterFight(NaSt _str, int _index) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getGenderAfterFight(_index);
    }

    public static String callSimulationBeanGetGenderAlly(NaSt _str, int _index) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getGenderAlly(_index);
    }

    public static String callSimulationBeanGetGenderFoe(NaSt _str, int _index) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getGenderFoe(_index);
    }

    public static int[][] callSimulationBeanGetImage(NaSt _str, int _index) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getImage(_index);
    }

    public static int[][] callSimulationBeanGetImageAfterFight(NaSt _str, int _index) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getImageAfterFight(_index);
    }

    public static int[][] callSimulationBeanGetImageAlly(NaSt _str, int _index) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getImageAlly(_index);
    }

    public static int[][] callSimulationBeanGetImageFoe(NaSt _str, int _index) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getImageFoe(_index);
    }

    public static String callSimulationBeanGetItem(NaSt _str, int _index) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getItem(_index);
    }

    public static String callSimulationBeanGetItemAfterFight(NaSt _str, int _index) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getItemAfterFight(_index);
    }

    public static String callSimulationBeanGetItemAlly(NaSt _str, int _index) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getItemAlly(_index);
    }

    public static String callSimulationBeanGetItemFoe(NaSt _str, int _index) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getItemFoe(_index);
    }

    public static NaSt callSimulationBeanGetKoFoes(NaSt _str) {
        return BeanNatCommonLgNames.getStringArray(( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getKoFoes());
    }

    public static NaSt callSimulationBeanGetKoPlayers(NaSt _str) {
        return BeanNatCommonLgNames.getStringArray(( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getKoPlayers());
    }

    public static long callSimulationBeanGetLevel(NaSt _str, int _index) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getLevel(_index);
    }

    public static long callSimulationBeanGetLevelAfterFight(NaSt _str, int _index) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getLevelAfterFight(_index);
    }

    public static long callSimulationBeanGetLevelAlly(NaSt _str, int _index) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getLevelAlly(_index);
    }

    public static long callSimulationBeanGetLevelFoe(NaSt _str, int _index) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getLevelFoe(_index);
    }

    public static NaSt callSimulationBeanGetMoves(NaSt _str, int _index) {
        return BeanNatCommonLgNames.getStringArray(( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getMoves(_index));
    }

    public static NaSt callSimulationBeanGetMovesAfterFight(NaSt _str, int _index) {
        return BeanNatCommonLgNames.getStringArray(( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getMovesAfterFight(_index));
    }

    public static NaSt callSimulationBeanGetMovesAlly(NaSt _str, int _index) {
        return BeanNatCommonLgNames.getStringArray(( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getMovesAlly(_index));
    }

    public static NaSt callSimulationBeanGetMovesFoe(NaSt _str, int _index) {
        return BeanNatCommonLgNames.getStringArray(( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getMovesFoe(_index));
    }

    public static String callSimulationBeanGetName(NaSt _str, int _index) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getName(_index);
    }

    public static String callSimulationBeanGetNameAfterFight(NaSt _str, int _index) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getNameAfterFight(_index);
    }

    public static String callSimulationBeanGetNameAlly(NaSt _str, int _index) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getNameAlly(_index);
    }

    public static String callSimulationBeanGetNameFoe(NaSt _str, int _index) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getNameFoe(_index);
    }

    public static NaSt callSimulationBeanGetNotKoFrontFoes(NaSt _str) {
        return BeanNatCommonLgNames.getStringArray(( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getNotKoFrontFoes());
    }

    public static long callSimulationBeanGetRealStepNumber(NaSt _str) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getRealStepNumber();
    }

    public static LgInt callSimulationBeanGetRemainingLifeRate(NaSt _str, int _index) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getRemainingLifeRate(_index);
    }

    public static String callSimulationBeanGetTrainerName(NaSt _str, int... _args) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getTrainerName();
    }

//    public static NaSt callSimulationBeanHideComments(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanHideComments(),_str,_args);
//    }

    public static long callSimulationBeanIndexTeamGet(NaSt _str) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getIndexTeam();
    }

    public static boolean callSimulationBeanIsAvailableAbilities(NaSt _str, int... _args) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).isAvailableAbilities();
    }

    public static boolean callSimulationBeanIsAvailableMoves(NaSt _str, int... _args) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).isAvailableMoves();
    }

    public static boolean callSimulationBeanIsDiffState(NaSt _str) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).isDiffState();
    }

    public static boolean callSimulationBeanIsEvolutionAfterFightState(NaSt _str, int... _args) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).isEvolutionAfterFightState();
    }

    public static boolean callSimulationBeanIsEvolutionsState(NaSt _str, int... _args) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).isEvolutionsState();
    }

    public static boolean callSimulationBeanIsFightAfter(NaSt _str, int... _args) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).isFightAfter();
    }

    public static boolean callSimulationBeanIsFoeState(NaSt _str, int... _args) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).isFoeState();
    }

    public static boolean callSimulationBeanIsFrontState(NaSt _str, int... _args) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).isFrontState();
    }

    //    public static Struct callSimulationBeanIsHardSimulationIssue(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsHardSimulationIssue(),_str,_args);
//    }
//
    public static boolean callSimulationBeanIsIssue(NaSt _str, int... _args) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).isIssue();
    }

//    public static Struct callSimulationBeanIsIssueAfterFight(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanIsIssueAfterFight(),_str,_args);
//    }

    public static boolean callSimulationBeanIsMovesFightState(NaSt _str, int... _args) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).isMovesFightState();
    }

    public static boolean callSimulationBeanIsMovesState(NaSt _str, int... _args) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).isMovesState();
    }

    public static boolean callSimulationBeanIsMultiLayer(NaSt _str, int... _args) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).isMultiLayer(_args[0]);
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

    public static boolean callSimulationBeanIsSimulationState(NaSt _str, int... _args) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).isSimulationState();
    }

    public static boolean callSimulationBeanIsTeamState(NaSt _str, int... _args) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).isTeamState();
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

    public static NaSt callSimulationBeanKeptMovesAfterFightGet(NaSt _str, int... _args) {
        return PokemonStandards.getSelectLineMove(( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getKeptMovesAfterFight());
    }

    public static NaSt callSimulationBeanKeptMovesGet(NaSt _str, int... _args) {
        return PokemonStandards.getSelectLineMove(( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getKeptMoves());
    }

    public static int callSimulationBeanLayers(NaSt _str, int... _args) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).layers(_args[0]).size();
    }

    public static long callSimulationBeanLevelEvoGet(NaSt _str, int... _args) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getLevelEvo().valueLong();
    }

    public static NaSt callSimulationBeanMovesSetGet(NaSt _str, int... _args) {
        return PokemonStandards.getRdMvLine(( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getMovesSet());
    }

    public static long callSimulationBeanMultiplicityGet(NaSt _str, int... _args) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).multiplicity();
    }

    public static long callSimulationBeanNbTeamsGet(int _team) {
        return ( (SimulationBean) ((PokemonBeanStruct)initByTeam(_team)).getInstance()).getNbTeams();
    }

    public static long callSimulationBeanSelectedTeamNumberGet(NaSt _str) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).selectedTeamNumber();
    }

//    public static NaSt callSimulationBeanNextFight(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanNextFight(),_str,_args);
//    }

    public static Rate callSimulationBeanNumberNecessaryPointsForGrowingLevel(NaSt _str, int _index) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).numberNecessaryPointsForGrowingLevel(_index);
    }

    public static NaSt callSimulationBeanNumbers(int _teams) {
        return PokemonStandards.getIntArray(( (SimulationBean) ((PokemonBeanStruct)validateDiff(_teams)).getInstance()).getNumbers());
    }

    public static boolean callSimulationBeanOkGet(NaSt _str, int... _args) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getOk();
    }

    public static String callSimulationBeanPlaceFightGet(NaSt _str, int... _args) {
        return Long.toString(( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getPlaceFight().valueInt());
    }

    public static NaSt callSimulationBeanPlacesFightGet(NaSt _str, int... _args) {
        return PokemonStandards.getIntStr(( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getPlacesFight());
    }

    public static NaSt callSimulationBeanPlacesGet(NaSt _str, int... _args) {
        return PokemonStandards.getPlInd(( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getPlaces());
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

    public static NaSt callSimulationBeanRoundGet(NaSt _str, int... _args) {
        return PokemonStandards.getIntStr(( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getRound());
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

    public static boolean callSimulationBeanSelectedIndex(NaSt _str, int... _args) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).selectedIndex();
    }

    public static boolean callSimulationBeanSelectedIndexForMoves(NaSt _str, int... _args) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).selectedIndexForMoves();
    }

    public static long callSimulationBeanSelectedMoveGet(NaSt _str, int... _args) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getSelectedMove();
    }

    public static long callSimulationBeanSelectedPkGet(NaSt _str, int... _args) {
        return ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getSelectedPk();
    }

    public static String callSimulationBeanSelectedRoundGet(NaSt _str, int... _args) {
        return Long.toString(( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getSelectedRound().valueInt());
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

    public static NaSt callSimulationBeanTargetFightGet(NaSt _str, int... _args) {
        return PokemonStandards.getIntStr(( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getTargetFight());
    }

    public static String callSimulationBeanTargetGet(NaSt _str, int... _args) {
        return Long.toString(( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getTarget().valueInt());
    }

    public static NaSt callSimulationBeanTeamAfterFightGet(NaSt _str, int... _args) {
        return PokemonStandards.getPkPlayerArray(( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getTeamAfterFight());
    }

    public static NaSt callSimulationBeanTeamGet(NaSt _str, int... _args) {
        return PokemonStandards.getPkPlDto(( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getTeam());
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

    public static void callSimulationBeanAbilityAfterFightSet(NaSt _str, String _args) {
        ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getAbilityAfterFight().setupValue(_args);
    }

    public static void callSimulationBeanChosenEvoSet(NaSt _str, String _args) {
        ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getChosenEvo().setupValue(_args);
    }

    public static void callSimulationBeanCurrentAbilitySet(NaSt _str, String _args) {
        ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getCurrentAbility().setupValue(_args);
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

    public static void callSimulationBeanEnvironmentSet(NaSt _str, String _args) {
        ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getEnvironment().setupValue(_args);
        ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).setEnvironment(_args);
    }

    public static void callSimulationBeanEvolutionAfterFightSet(NaSt _str, String _args) {
        ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getEvolutionAfterFight().setupValue(_args);
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

    public static void callSimulationBeanLevelEvoSet(NaSt _str, int _args) {
        ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getLevelEvo().valueLong(_args);
    }

    public static void callSimulationBeanMultiplicitySet(NaSt _str, int _args) {
        ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).getMultiplicity().valueLong(_args);
        ( (SimulationBean) ((PokemonBeanStruct)_str).getInstance()).setMultiplicity(_args);
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

    public static void callAddPokemonBeanAbilitySet(NaSt _str, String _args) {
        ( (AddPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getAbility().setupValue(_args);
    }

    public static void callAddPokemonBeanGenderSet(NaSt _str, String _args) {
        ( (AddPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getCommon().getGender().setupValue(_args);
    }

    public static void callAddPokemonBeanHasEvoSet(NaSt _str, String _args) {
        ( (AddPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getHasEvo().setupValue(_args);
    }

    public static void callAddPokemonBeanIsEvoSet(NaSt _str, String _args) {
        ( (AddPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getIsEvo().setupValue(_args);
    }

    public static void callAddPokemonBeanIsLegSet(NaSt _str, String _args) {
        ( (AddPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getIsLeg().setupValue(_args);
    }

    public static void callAddPokemonBeanTypedNameSet(NaSt _str, String _args) {
        ( (AddPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getTypedName().setupValue(_args);
    }

    public static void callAddPokemonBeanTypedTypeSet(NaSt _str, String _args) {
        ( (AddPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getTypedType().setupValue(_args);
    }

    public static void callEditPokemonBeanBallSet(NaSt _str, String _args) {
        ( (EditPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getBall().setupValue(_args);
    }

    public static void callEditPokemonMovesBeanCategorySet(NaSt _str, String _args) {
        ( (EditPokemonMovesBean) ((PokemonBeanStruct)_str).getInstance()).getTypedCategory().setupValue(_args);
    }

    public static void callEditPokemonMovesBeanTypedNameSet(NaSt _str, String _args) {
        ( (EditPokemonMovesBean) ((PokemonBeanStruct)_str).getInstance()).getTypedName().setupValue(_args);
    }

    public static void callEditPokemonMovesBeanTypedTypeSet(NaSt _str, String _args) {
        ( (EditPokemonMovesBean) ((PokemonBeanStruct)_str).getInstance()).getTypedType().setupValue(_args);
    }

    public static void callEditTrainerPokemonBeanGenderSet(NaSt _str, String _args) {
        ( (EditTrainerPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getCommon().getGender().setupValue(_args);
    }

    public static void callSelectAbilityBeanTypedAbilitySet(NaSt _str, String _args) {
        ( (SelectAbilityBean) ((PokemonBeanStruct)_str).getInstance()).getTypedAbility().setupValue(_args);
    }

    public static void callSelectItemBeanTypedClassSet(NaSt _str, String _args) {
        ( (SelectItemBean) ((PokemonBeanStruct)_str).getInstance()).getTypedClass().setupValue(_args);
    }

    public static void callSelectItemBeanTypedNameSet(NaSt _str, String _args) {
        ( (SelectItemBean) ((PokemonBeanStruct)_str).getInstance()).getTypedName().setupValue(_args);
    }

    public static void callSelectItemBeanTypedPriceSet(NaSt _str, String _args) {
        ( (SelectItemBean) ((PokemonBeanStruct)_str).getInstance()).getTypedPrice().setupValue(_args);
    }

    public static void callSelectPokemonBeanHasEvoSet(NaSt _str, String _args) {
        ( (SelectPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getHasEvo().setupValue(_args);
    }

    public static void callSelectPokemonBeanIsEvoSet(NaSt _str, String _args) {
        ( (SelectPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getIsEvo().setupValue(_args);
    }

    public static void callSelectPokemonBeanIsLegSet(NaSt _str, String _args) {
        ( (SelectPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getIsLeg().setupValue(_args);
    }

    public static void callSelectPokemonBeanTypedNameSet(NaSt _str, String _args) {
        ( (SelectPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getTypedName().setupValue(_args);
    }

    public static void callSelectPokemonBeanTypedTypeSet(NaSt _str, String _args) {
        ( (SelectPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getTypedType().setupValue(_args);
    }

//    public static NaSt callAddPokemonBeanClickLink(NaSt _str, int _args) {
//        return BeanPokemonCommonTs.callInt(new AddPokemonBeanClickLink(),_str,_args);
//    }

    public static void callAddPokemonBeanLevelSet(NaSt _str, int _args) {
        ( (AddPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getCommon().getLevel().valueLong(_args);
    }

    public static void callEditPokemonBeanHappinessSet(NaSt _str, int _args) {
        ( (EditPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getHappiness().valueLong(_args);
    }

    public static void callEditTrainerPokemonBeanLevelSet(NaSt _str, int _args) {
        ( (EditTrainerPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getCommon().getLevel().valueLong(_args);
    }


    public static void callSimulationLevelBeanNoFightSet(NaSt _str, int _args) {
        ( (SimulationLevelBean) ((PokemonBeanStruct)_str).getInstance()).noFight(_args);
        ( (SimulationLevelBean) ((PokemonBeanStruct)_str).getInstance()).getNoFight().valueLong(_args);
    }

    public static void callSelectLineMoveSelectedSet(NaSt _str, boolean _args) {
        ( (SelectLineMove) ((MvLineStruct) _str).getWildPk()).getSelected().setSelected(_args);
    }


    public static void callAddPokemonBeanWholeWordSet(NaSt _str, boolean _args) {
        ( (AddPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getWholeWord().setSelected(_args);
    }

    public static void callEditPokemonBeanHealSet(NaSt _str, boolean _args) {
        ( (EditPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getHeal().setSelected(_args);
    }

    public static void callEditPokemonMovesBeanAvailableMovesOnlySet(NaSt _str, boolean _args) {
        ( (EditPokemonMovesBean) ((PokemonBeanStruct)_str).getInstance()).getAvailableMovesOnly().setSelected(_args);
    }

    public static void callEditPokemonMovesBeanWholeWordSet(NaSt _str, boolean _args) {
        ( (EditPokemonMovesBean) ((PokemonBeanStruct)_str).getInstance()).getWholeWord().setSelected(_args);
    }

    public static void callEditTrainerPokemonBeanAllyPkSet(NaSt _str, boolean _args) {
        ( (EditTrainerPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getAllyPk().setSelected(_args);
    }

    public static void callSelectPokemonBeanWholeWordSet(NaSt _str, boolean _args) {
        ( (SelectPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getWholeWord().setSelected(_args);
    }

    public static void callEditPokemonBeanExperienceSet(NaSt _str, Rate _args) {
        ( (EditPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getExperience().valueRate(_args);
    }

    public static void callEditPokemonBeanRemainingHpSet(NaSt _str, Rate _args) {
        ( (EditPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getRemainingHp().valueRate(_args);
    }

    public static long callEvLineEvGet(NaSt _str, long... _args) {
        return (((EvLineStruct) _str).getEvLine()).getEv().valueLong();
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

    public static long callPokemonPlayerDtoIndexGet(NaSt _str, long... _args) {
        return ( ((PokemonPlayerDtoStruct) _str).getPokemonPlayerDto()).getIndex();
    }

    public static long callPokemonTrainerDtoIndexGet(NaSt _str, long... _args) {
        return ( ((PokemonTrainerDtoStruct) _str).getPokemonTrainerDto()).getIndex();
    }
    public static long callRadioLineMoveIndexGet(NaSt _str, long... _args) {
        return ( (RadioLineMove) ((MvLineStruct) _str).getWildPk()).getIndex();
    }

    public static boolean callSelectLineMoveSelectedGet(NaSt _str, long... _args) {
        return ((SelectLineMove) ((MvLineStruct) _str).getWildPk()).isSelected();
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

    public static long callSimulationLevelBeanNoFightGet(NaSt _str, int... _args) {
        return ( (SimulationLevelBean) ((PokemonBeanStruct)_str).getInstance()).getNoFight().valueLong();
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


    public static NaSt callAddPokemonBeanAbilitiesGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrStr(( (AddPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getAbilities());
    }

    public static String callAddPokemonBeanAbilityGet(NaSt _str, int... _args) {
        return ( (AddPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getAbility().tryRet();
    }

//    public static NaSt callAddPokemonBeanAdd(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanAdd(),_str,_args);
//    }

    public static NaSt callAddPokemonBeanBooleansGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrStr(( (AddPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getBooleans());
    }

//    public static NaSt callAddPokemonBeanCancel(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanCancel(),_str,_args);
//    }

    public static String callAddPokemonBeanGenderGet(NaSt _str, int... _args) {
        return ((AddPokemonBean) ((PokemonBeanStruct) _str).getInstance()).getCommon().getGender().tryRet();
    }

    public static NaSt callAddPokemonBeanGendersGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrStr(((AddPokemonBean) ((PokemonBeanStruct) _str).getInstance()).getCommon().getGenders());
    }

    public static int[][] callAddPokemonBeanGetMiniImage() {
        return ( (AddPokemonBean) ((PokemonBeanStruct)pkPlayerSelectPkName(DataBase.EMPTY_STRING)).getInstance()).getMiniImagePk(0);
    }

    public static String callAddPokemonBeanHasEvoGet(NaSt _str, int... _args) {
        return ( (AddPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getHasEvo().tryRet();
    }

    public static String callAddPokemonBeanIsEvoGet(NaSt _str, int... _args) {
        return ( (AddPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getIsEvo().tryRet();
    }

    public static String callAddPokemonBeanIsLegGet(NaSt _str, int... _args) {
        return ( (AddPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getIsLeg().tryRet();
    }

    public static long callAddPokemonBeanLevelGet(NaSt _str, int... _args) {
        return ((AddPokemonBean) ((PokemonBeanStruct) _str).getInstance()).getCommon().getLevel().valueLong();
    }

    public static String callAddPokemonBeanNamePkGet(NaSt _str, int... _args) {
        return ( (AddPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getNamePk();
    }

    public static int callAddPokemonBeanPokedexGet(NaSt _str, int... _args) {
        return ( (AddPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getPokedex().size();
    }

//    public static NaSt callAddPokemonBeanSearch(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new AddPokemonBeanSearch(),_str,_args);
//    }

    public static String callAddPokemonBeanTypedNameGet(NaSt _str, int... _args) {
        return ( (AddPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getTypedName().tryRet();
    }

    public static String callAddPokemonBeanTypedTypeGet(NaSt _str, int... _args) {
        return ( (AddPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getTypedType().tryRet();
    }

    public static boolean callAddPokemonBeanWholeWordGet(NaSt _str, int... _args) {
        return ( (AddPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getWholeWord().isSelected();
    }

//    public static NaSt callEditPokemonBeanAddMoves(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new EditPokemonBeanAddMoves(),_str,_args);
//    }

    public static String callEditPokemonBeanBallGet(NaSt _str, int... _args) {
        return ( (EditPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getBall().tryRet();
    }

    public static NaSt callEditPokemonBeanBallsGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrStr(( (EditPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getBalls());
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

    public static NaSt callEditPokemonBeanEvGet(NaSt _str, int... _args) {
        return PokemonStandards.getEvLine(( (EditPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getEv());
    }

    public static Rate callEditPokemonBeanExperienceGet(NaSt _str, int... _args) {
        return ( (EditPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getExperience().valueRate();
    }

    public static String callEditPokemonBeanGetTranslatedStatistic(NaSt _str, int... _args) {
        return ( (EditPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getTranslatedStatistic(_args[0]);
    }

    public static long callEditPokemonBeanHappinessGet(NaSt _str, int... _args) {
        return ( (EditPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getHappiness().valueLong();
    }

    public static boolean callEditPokemonBeanHealGet(NaSt _str, int... _args) {
        return ( (EditPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getHeal().isSelected();
    }

    public static long callEditPokemonBeanLevelGet(NaSt _str, int... _args) {
        return ( (EditPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getLevel();
    }

    public static NaSt callEditPokemonBeanMovesGet(NaSt _str, int... _args) {
        return PokemonStandards.getSelectLineMove(( (EditPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getMoves());
    }

    public static String callEditPokemonBeanNamePkGet(NaSt _str, int... _args) {
        return ( (EditPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getNamePk();
    }

    public static Rate callEditPokemonBeanRemainingHpGet(NaSt _str, int... _args) {
        return ( (EditPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getRemainingHp().valueRate();
    }

    public static String callEditPokemonBeanTranslateItem(NaSt _str, int... _args) {
        return ( (EditPokemonBean) ((PokemonBeanStruct)_str).getInstance()).translateItem();
    }

    public static String callEditPokemonBeanTranslateName(NaSt _str, int... _args) {
        return ( (EditPokemonBean) ((PokemonBeanStruct)_str).getInstance()).translateName();
    }

//    public static NaSt callEditPokemonMovesBeanAddMoves(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new EditPokemonMovesBeanAddMoves(),_str,_args);
//    }

    public static boolean callEditPokemonMovesBeanAvailableMovesOnlyGet(NaSt _str, int... _args) {
        return ( (EditPokemonMovesBean) ((PokemonBeanStruct)_str).getInstance()).getAvailableMovesOnly().isSelected();
    }

//    public static NaSt callEditPokemonMovesBeanCancel(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new EditPokemonMovesBeanCancel(),_str,_args);
//    }

    public static NaSt callEditPokemonMovesBeanCategoriesGet(NaSt _str) {
        return PokemonStandards.getStrStr(( (EditPokemonMovesBean) ((PokemonBeanStruct)_str).getInstance()).getCategories());
    }

    public static String callEditPokemonMovesBeanCategoryGet(NaSt _str, int... _args) {
        return ((EditPokemonMovesBean) ((PokemonBeanStruct) _str).getInstance()).getTypedCategory().tryRet();
    }

    public static NaSt callEditPokemonMovesBeanMovesGet(NaSt _str, int... _args) {
        return PokemonStandards.getSelectLineMove(( (EditPokemonMovesBean) ((PokemonBeanStruct)_str).getInstance()).getMoves());
    }

    public static boolean callEditPokemonMovesBeanPlayerGet(NaSt _str, int... _args) {
        return ( (EditPokemonMovesBean) ((PokemonBeanStruct)_str).getInstance()).getPlayer();
    }

//    public static NaSt callEditPokemonMovesBeanSearch(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new EditPokemonMovesBeanSearch(),_str,_args);
//    }

    public static String callEditPokemonMovesBeanTypedNameGet(NaSt _str, int... _args) {
        return ( (EditPokemonMovesBean) ((PokemonBeanStruct)_str).getInstance()).getTypedName().tryRet();
    }

    public static String callEditPokemonMovesBeanTypedTypeGet(NaSt _str, int... _args) {
        return ( (EditPokemonMovesBean) ((PokemonBeanStruct)_str).getInstance()).getTypedType().tryRet();
    }

    public static boolean callEditPokemonMovesBeanWholeWordGet(NaSt _str, int... _args) {
        return ( (EditPokemonMovesBean) ((PokemonBeanStruct)_str).getInstance()).getWholeWord().isSelected();
    }

    public static boolean callEditTrainerPokemonBeanAddGet(NaSt _str, int... _args) {
        return ( (EditTrainerPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getAdd();
    }

//    public static NaSt callEditTrainerPokemonBeanAddMoves(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new EditTrainerPokemonBeanAddMoves(),_str,_args);
//    }

    public static boolean callEditTrainerPokemonBeanAllyPkGet(NaSt _str, int... _args) {
        return ( (EditTrainerPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getAllyPk().isSelected();
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

    public static String callEditTrainerPokemonBeanGenderGet(NaSt _str, int... _args) {
        return ((EditTrainerPokemonBean) ((PokemonBeanStruct) _str).getInstance()).getCommon().getGender().tryRet();
    }

    public static NaSt callEditTrainerPokemonBeanGendersGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrStr(((EditTrainerPokemonBean) ((PokemonBeanStruct) _str).getInstance()).getCommon().getGenders());
    }

    public static String callEditTrainerPokemonBeanGetTranslatedAbility(NaSt _str) {
        return ( (EditTrainerPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getTranslatedAbility();
    }

    public static String callEditTrainerPokemonBeanGetTranslatedItem(NaSt _str, int... _args) {
        return ( (EditTrainerPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getTranslatedItem();
    }

    public static String callEditTrainerPokemonBeanGetTranslatedName(NaSt _str) {
        return ( (EditTrainerPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getTranslatedName();
    }

    public static long callEditTrainerPokemonBeanLevelGet(NaSt _str, int... _args) {
        return ((EditTrainerPokemonBean) ((PokemonBeanStruct) _str).getInstance()).getCommon().getLevel().valueLong();
    }

    public static NaSt callEditTrainerPokemonBeanMovesGet(NaSt _str) {
        return PokemonStandards.getSelectLineMove(( (EditTrainerPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getMoves());
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
        return ( (SelectAbilityBean) ((PokemonBeanStruct)pkTrainerSelectAb(DataBase.EMPTY_STRING)).getInstance()).getTrSortedAbility(1);
    }

//    public static NaSt callSelectAbilityBeanSearch(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SelectAbilityBeanSearch(),_str,_args);
//    }

    public static NaSt callSelectAbilityBeanSortedAbilitiesGet(NaSt _str) {
        return PokemonStandards.getKeys(( (SelectAbilityBean) ((PokemonBeanStruct)_str).getInstance()).sortedAbilitiesGet());
    }

    public static String callSelectAbilityBeanTypedAbilityGet(NaSt _str) {
        return ( (SelectAbilityBean) ((PokemonBeanStruct)_str).getInstance()).getTypedAbility().tryRet();
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
        return ( (SelectItemBean) ((PokemonBeanStruct)pkTrainerSelectItName(DataBase.EMPTY_STRING)).getInstance()).getMiniImage(0);
    }

    public static int callSelectItemBeanItemsGet(NaSt _str) {
        return ( (SelectItemBean) ((PokemonBeanStruct)_str).getInstance()).getItems().size();
    }

//    public static NaSt callSelectItemBeanSearch(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SelectItemBeanSearch(),_str,_args);
//    }

    public static String callSelectItemBeanTypedClassGet(NaSt _str, int... _args) {
        return ( (SelectItemBean) ((PokemonBeanStruct)_str).getInstance()).getTypedClass().tryRet();
    }

    public static String callSelectItemBeanTypedNameGet(NaSt _str, int... _args) {
        return ( (SelectItemBean) ((PokemonBeanStruct)_str).getInstance()).getTypedName().tryRet();
    }

    public static String callSelectItemBeanTypedPriceGet(NaSt _str, int... _args) {
        return ( (SelectItemBean) ((PokemonBeanStruct)_str).getInstance()).getTypedPrice().tryRet();
    }

    public static NaSt callSelectPokemonBeanBooleansGet(NaSt _str) {
        return PokemonStandards.getStrStr(( (SelectPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getBooleans());
    }

//    public static NaSt callSelectPokemonBeanCancel(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SelectPokemonBeanCancel(),_str,_args);
//    }

//    public static NaSt callSelectPokemonBeanClickLink(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SelectPokemonBeanClickLink(),_str,_args);
//    }

    public static int[][] callSelectPokemonBeanGetMiniImage() {
        return ( (SelectPokemonBean) ((PokemonBeanStruct)pkTrainerSelectPkName(DataBase.EMPTY_STRING)).getInstance()).getMiniImagePk(0);
    }

    public static String callSelectPokemonBeanHasEvoGet(NaSt _str, int... _args) {
        return ( (SelectPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getHasEvo().tryRet();
    }

    public static String callSelectPokemonBeanIsEvoGet(NaSt _str, int... _args) {
        return ( (SelectPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getIsEvo().tryRet();
    }

    public static String callSelectPokemonBeanIsLegGet(NaSt _str, int... _args) {
        return ( (SelectPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getIsLeg().tryRet();
    }

    public static int callSelectPokemonBeanPokedexGet(NaSt _str) {
        return ( (SelectPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getPokedex().size();
    }

//    public static NaSt callSelectPokemonBeanSearch(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SelectPokemonBeanSearch(),_str,_args);
//    }

    public static String callSelectPokemonBeanTypedNameGet(NaSt _str, int... _args) {
        return ( (SelectPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getTypedName().tryRet();
    }

    public static String callSelectPokemonBeanTypedTypeGet(NaSt _str, int... _args) {
        return ( (SelectPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getTypedType().tryRet();
    }

    public static boolean callSelectPokemonBeanWholeWordGet(NaSt _str, int... _args) {
        return ( (SelectPokemonBean) ((PokemonBeanStruct)_str).getInstance()).getWholeWord().isSelected();
    }
    protected static String quit(){
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = init(beanToSimu(pk_.getDataBase()));
        return navigateData(new SimulationBeanQuit((SimulationBean) simu_.getBean()),simu_);
    }
    protected static PokemonBeanStruct validateDiff(int _nbTeam){
        PkData pk_ = pkDataByFacade(db());
        return simBean(_nbTeam, pk_.getDataBase());
    }

    protected static PokemonBeanStruct initByTeam(int _nbTeam) {
        PkData pk_ = pkDataByFacade(db());
        return init(_nbTeam, beanToSimu(pk_.getDataBase()));
    }
    protected static PokemonBeanStruct init(int _nbTeam, PokemonBeanStruct _init) {
        PokemonBeanStruct simu_ = init(_init);
        ((SimulationBean)simu_.getInstance()).setNbTeams(_nbTeam);
        return simu_;
    }
    protected static PokemonBeanStruct selectTeam(PokemonBeanStruct _simu, int _indexTeam) {
//        callSimulationBeanIndexTeamSet(_simu, _indexTeam);
//        beforeDisplaying(_simu);
        assertSame(_simu,transitSimu(new SimulationBeanValidateIndexTeamAction((SimulationBean) _simu.getBean(), _indexTeam), _simu.getInstance().getBuilder()));
        return _simu;
    }
    protected static PokemonBeanStruct pkTrainerTwoTeamsNextOk() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
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
        return transitSimu(new SimulationBeanValidateFoeChoiceFree((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct pkTrainerTwoTeamsNextOkAlly() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(1, pk_.getDataBase());
        selectTeam(simu_,0);
        pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, simu_, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_01_TR, A_SIM_1_TR, simu_, 4);
        pkTrainerSelectPkNameCycle(true, P_POK_02_TR, A_SIM_1_TR, simu_, 4);
        pkTrainerSelectPkNameCycle(true, P_POK_03_TR, A_SIM_1_TR, simu_, 4);
        setMult(simu_,2);
        return transitSimu(new SimulationBeanValidateFoeChoiceFree((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct pkTrainerTwoTeamsNextAdjMult() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
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
        return transitSimu(new SimulationBeanValidateFoeChoiceFree((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct pkTrainerTwoTeamsNextKo() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(1, pk_.getDataBase());
        return transitSimu(new SimulationBeanValidateFoeChoiceFree((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
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
    protected static PokemonBeanStruct dispSimu() {
        PkData pk_ = pkDataByFacade(db());
        return init(beanToSimu(pk_.getDataBase()));
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
    private static PokemonBeanStruct init(PokemonBeanStruct _main) {
        PokemonBeanStruct from_ = _main;
//        NaSt dCom_ = _all.getVal(AikiBeansGameStd.BEAN_DIFFICULTY_COMMON);
        beforeDisplaying(from_);
        PokemonBeanStruct simu_ =transitSimu(new BeanAnchorCstEvent(CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML), _main.getInstance().getBuilder());
//        callDifficultyBeanComSet(dCom_,callDifficultyBeanComGet(simu_));
//        beforeDisplaying(dCom_);
        return simu_;
    }
    protected static PokemonBeanStruct pkTrainer() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        return goToAddPkTrainer(simu_);
    }
    protected static String editNoFoePk() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
//        callSimulationBeanSelectedFoePkSet(simu_,-1);
        return navigateData(new SimulationBeanSelectFoePkValidation((SimulationBean) simu_.getBean(),((SimulationBean)simu_.getBean()).getSelectedFoeAction(),-1),simu_);
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
    protected static PokemonBeanStruct editNoFoePkState() {
        PkData pk_ = pkDataByFacade(db());
        return simBean(2, pk_.getDataBase());
    }
    protected static PokemonBeanStruct pkTrainerFoeRemove() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, simu_, 4);
        PokemonBeanStruct second_ = pkTrainerSelectPkNameCycle(false,P_POK_01_TR,A_SIM_2_TR, simu_, 5);
//        callSimulationBeanSelectedFoeActionSet(second_, TeamCrud.REMOVE.getTeamCrudString());
//        callSimulationBeanSelectedFoePkSet(second_,0);
        return transitSimu(new SimulationBeanSelectFoePkValidation((SimulationBean) second_.getBean(), TeamCrud.REMOVE.getTeamCrudString(), 0), simu_.getInstance().getBuilder());
    }
    protected static String editNoSelectedFoePk() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct added_ = pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, simu_, 4);
//        callSimulationBeanSelectedFoeActionSet(added_, TeamCrud.NOTHING.getTeamCrudString());
//        callSimulationBeanSelectedFoePkSet(added_,-1);
        return navigateData(new SimulationBeanSelectFoePkValidation((SimulationBean) simu_.getBean(),TeamCrud.NOTHING.getTeamCrudString(),-1),added_);
    }
    protected static PokemonBeanStruct editEditSelectedFoePk() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct added_ = pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, simu_, 4);
//        callSimulationBeanSelectedFoeActionSet(added_, TeamCrud.EDIT.getTeamCrudString());
//        callSimulationBeanSelectedFoePkSet(added_,0);
        return transitSimu(new SimulationBeanSelectFoePkValidation((SimulationBean) added_.getBean(), TeamCrud.EDIT.getTeamCrudString(), 0), simu_.getInstance().getBuilder());
    }
    //    protected static PokemonBeanStruct formEditSelectedFoePk() {
//        PkData pk_ = pkDataByFacade(db());
//        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
//        PokemonBeanStruct added_ = pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, simu_, 4);
////        callSimulationBeanSelectedFoeActionSet(added_, TeamCrud.EDIT.getTeamCrudString());
////        callSimulationBeanSelectedFoePkSet(added_,0);
//        return transitSimu(new SimulationBeanSelectFoePkValidation((SimulationBean) added_.getBean(), TeamCrud.EDIT.getTeamCrudString(), 0), simu_.getInstance().getBuilder());
//    }
    protected static PokemonBeanStruct editEditSelectedFoePkAddMove() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct added_ = pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, simu_, 4);
//        callSimulationBeanSelectedFoeActionSet(added_, TeamCrud.EDIT.getTeamCrudString());
//        callSimulationBeanSelectedFoePkSet(added_,0);
        PokemonBeanStruct editing_ =transitSimu(new SimulationBeanSelectFoePkValidation((SimulationBean) added_.getBean(), TeamCrud.EDIT.getTeamCrudString(), 0), simu_.getInstance().getBuilder());
        addMoveTrainer(M_POK_01_TR,0, editing_);
        return transitSimu(new EditTrainerPokemonBeanValidateTrainerPk((EditTrainerPokemonBean) editing_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static String editForgetSelectedFoePk() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct added_ = pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, simu_, 4);
//        callSimulationBeanSelectedFoeActionSet(added_, TeamCrud.NOTHING.getTeamCrudString());
//        callSimulationBeanSelectedFoePkSet(added_,0);
        return navigateData(new SimulationBeanSelectFoePkValidation((SimulationBean) simu_.getBean(),TeamCrud.NOTHING.getTeamCrudString(),0),added_);
    }
    protected static String editNoAllyPk() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
//        callSimulationBeanSelectedAllyPkSet(simu_,-1);
        return navigateData(new SimulationBeanSelectAllyPkValidation((SimulationBean) simu_.getBean(),((SimulationBean)simu_.getBean()).getSelectedAllyAction(),-1),simu_);
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

    protected static PokemonBeanStruct pkTrainerAllyRemove() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        pkTrainerSelectPkNameCycle(true, P_POK_00_TR, A_SIM_1_TR, simu_, 4);
        PokemonBeanStruct second_ = pkTrainerSelectPkNameCycle(true,P_POK_01_TR,A_SIM_2_TR, simu_, 5);
//        callSimulationBeanSelectedAllyActionSet(second_, TeamCrud.REMOVE.getTeamCrudString());
//        callSimulationBeanSelectedAllyPkSet(second_,0);
        return transitSimu(new SimulationBeanSelectAllyPkValidation((SimulationBean) second_.getBean(), TeamCrud.REMOVE.getTeamCrudString(), 0), simu_.getInstance().getBuilder());
    }
    protected static String editNoSelectedAllyPk() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct added_ = pkTrainerSelectPkNameCycle(true, P_POK_00_TR, A_SIM_1_TR, simu_, 4);
//        callSimulationBeanSelectedAllyActionSet(added_, TeamCrud.NOTHING.getTeamCrudString());
//        callSimulationBeanSelectedAllyPkSet(added_,-1);
        return navigateData(new SimulationBeanSelectAllyPkValidation((SimulationBean) simu_.getBean(),TeamCrud.NOTHING.getTeamCrudString(),-1),added_);
    }
    protected static PokemonBeanStruct editEditSelectedAllyPk() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct added_ = pkTrainerSelectPkNameCycle(true, P_POK_00_TR, A_SIM_1_TR, simu_, 4);
//        callSimulationBeanSelectedAllyActionSet(added_, TeamCrud.EDIT.getTeamCrudString());
//        callSimulationBeanSelectedAllyPkSet(added_,0);
        return transitSimu(new SimulationBeanSelectAllyPkValidation((SimulationBean) added_.getBean(), TeamCrud.EDIT.getTeamCrudString(), 0), simu_.getInstance().getBuilder());
    }
    //    protected static PokemonBeanStruct formEditSelectedAllyPk() {
//        PkData pk_ = pkDataByFacade(db());
//        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
//        PokemonBeanStruct added_ = pkTrainerSelectPkNameCycle(true, P_POK_00_TR, A_SIM_1_TR, simu_, 4);
//        callSimulationBeanSelectedAllyActionSet(added_, TeamCrud.EDIT.getTeamCrudString());
//        callSimulationBeanSelectedAllyPkSet(added_,0);
//        return added_;
//    }
    protected static PokemonBeanStruct editEditSelectedAllyPkAddMove() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct added_ = pkTrainerSelectPkNameCycle(true, P_POK_00_TR, A_SIM_1_TR, simu_, 4);
//        callSimulationBeanSelectedAllyActionSet(added_, TeamCrud.EDIT.getTeamCrudString());
//        callSimulationBeanSelectedAllyPkSet(added_,0);
        PokemonBeanStruct editing_ =transitSimu(new SimulationBeanSelectAllyPkValidation((SimulationBean) added_.getBean(), TeamCrud.EDIT.getTeamCrudString(), 0), simu_.getInstance().getBuilder());
        addMoveTrainer(M_POK_01_TR,0, editing_);
        return transitSimu(new EditTrainerPokemonBeanValidateTrainerPk((EditTrainerPokemonBean) editing_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static String editForgetSelectedAllyPk() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct added_ = pkTrainerSelectPkNameCycle(true, P_POK_00_TR, A_SIM_1_TR, simu_, 4);
//        callSimulationBeanSelectedAllyActionSet(added_, TeamCrud.NOTHING.getTeamCrudString());
//        callSimulationBeanSelectedAllyPkSet(added_,0);
        return navigateData(new SimulationBeanSelectAllyPkValidation((SimulationBean) simu_.getBean(),TeamCrud.NOTHING.getTeamCrudString(),0),added_);
    }
    protected static PokemonBeanStruct pkTrainerIndex() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, simu_, 4);
        return pkTrainerSelectPkNameCycle(false,P_POK_01_TR,A_SIM_2_TR, simu_, 5);
    }
    protected static PokemonBeanStruct pkTrainerLevel(int _level) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        return pkTrainerSelectPkNameCycle(false,P_POK_00_TR,A_SIM_1_TR, simu_, _level);
    }
    protected static PokemonBeanStruct pkTrainerLevelCancelAdd() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct editPkTrainer_ = goToAddPkTrainer(simu_);
        return transitSimu(new EditTrainerPokemonBeanCancel((EditTrainerPokemonBean) editPkTrainer_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct pkTrainerLevelRestoreMoves() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct editPkTrainer_ = goToAddPkTrainer(simu_);
        callSelectLineMoveSelectedSet(elt(callEditTrainerPokemonBeanMovesGet(editPkTrainer_),0),true);
        PokemonBeanStruct after_ =transitSimu(new EditTrainerPokemonBeanDeleteMoves((EditTrainerPokemonBean) editPkTrainer_.getBean()), simu_.getInstance().getBuilder());
        callEditTrainerPokemonBeanAllyPkSet(after_,false);
        return transitSimu(new EditTrainerPokemonBeanValidateTrainerPk((EditTrainerPokemonBean) after_.getBean()), simu_.getInstance().getBuilder());
    }
    private static PokemonBeanStruct pkTrainerSelectPkNameCycle(boolean _ally, String _name, String _ability, PokemonBeanStruct _simu, int _level) {
        PokemonBeanStruct editPkTrainer_ = goToAddPkTrainer(_simu);
        PokemonBeanStruct retPk_ = chooseName(_name, editPkTrainer_);
        PokemonBeanStruct selAb_ = chooseAbility(_ability, retPk_);
        callEditTrainerPokemonBeanAllyPkSet(selAb_, _ally);
        callEditTrainerPokemonBeanLevelSet(selAb_, _level);
        genderSet(selAb_);
        PokemonBeanStruct afterAddEdit_ =transitSimu(new EditTrainerPokemonBeanValidateTrainerPk((EditTrainerPokemonBean) selAb_.getBean()), _simu.getInstance().getBuilder());
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

    private static void genderSet(NaSt _str) {
        callEditTrainerPokemonBeanGenderSet(_str,Gender.NO_GENDER.getGenderName());
    }

    private static void genderSetPl(NaSt _str) {
        callAddPokemonBeanGenderSet(_str,Gender.NO_GENDER.getGenderName());
    }

    private static PokemonBeanStruct chooseName(String _name, PokemonBeanStruct _str) {
        PokemonBeanStruct selPk_ =transitSimu(new EditTrainerPokemonBeanChooseName((EditTrainerPokemonBean) _str.getBean()), _str.getInstance().getBuilder());
        callSelectPokemonBeanTypedNameSet(selPk_, _name);
        PokemonBeanStruct afSel_ =transitSimu(new SelectPokemonBeanSearch((SelectPokemonBean) selPk_.getBean()), _str.getInstance().getBuilder());
        assertSame(afSel_, _str);
        return afSel_;
    }

    private static PokemonBeanStruct chooseAbility(String _ability, PokemonBeanStruct _str) {
        PokemonBeanStruct selAb_ =transitSimu(new EditTrainerPokemonBeanChooseAbility((EditTrainerPokemonBean) _str.getBean()), _str.getInstance().getBuilder());
        callSelectAbilityBeanTypedAbilitySet(selAb_, _ability);
        PokemonBeanStruct afSel_ =transitSimu(new SelectAbilityBeanSearch((SelectAbilityBean) selAb_.getBean()), _str.getInstance().getBuilder());
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
    protected static PokemonBeanStruct pkTrainerSelectPkCancel() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct editPkTrainer_ = goToAddPkTrainer(simu_);
        PokemonBeanStruct selPk_ =transitSimu(new EditTrainerPokemonBeanChooseName((EditTrainerPokemonBean) editPkTrainer_.getBean()), simu_.getInstance().getBuilder());
        return transitSimu(new BeanAnchorCstEvent(CommonBean.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML), simu_.getInstance().getBuilder());
    }

    protected static PokemonBeanStruct pkTrainerSelectPkName(String _name) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct editPkTrainer_ = goToAddPkTrainer(simu_);
        PokemonBeanStruct selPk_ =transitSimu(new EditTrainerPokemonBeanChooseName((EditTrainerPokemonBean) editPkTrainer_.getBean()), simu_.getInstance().getBuilder());
        callSelectPokemonBeanTypedNameSet(selPk_,_name);
        return transitSimu(new SelectPokemonBeanSearch((SelectPokemonBean) selPk_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct pkTrainerSelectPkHasEvo(String _name) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct editPkTrainer_ = goToAddPkTrainer(simu_);
        PokemonBeanStruct selPk_ =transitSimu(new EditTrainerPokemonBeanChooseName((EditTrainerPokemonBean) editPkTrainer_.getBean()), simu_.getInstance().getBuilder());
        callSelectPokemonBeanHasEvoSet(selPk_,_name);
        return transitSimu(new SelectPokemonBeanSearch((SelectPokemonBean) selPk_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct pkTrainerSelectPkIsEvo(String _name) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct editPkTrainer_ = goToAddPkTrainer(simu_);
        PokemonBeanStruct selPk_ =transitSimu(new EditTrainerPokemonBeanChooseName((EditTrainerPokemonBean) editPkTrainer_.getBean()), simu_.getInstance().getBuilder());
        callSelectPokemonBeanIsEvoSet(selPk_,_name);
        return transitSimu(new SelectPokemonBeanSearch((SelectPokemonBean) selPk_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct pkTrainerSelectPkIsLeg(String _name) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct editPkTrainer_ = goToAddPkTrainer(simu_);
        PokemonBeanStruct selPk_ =transitSimu(new EditTrainerPokemonBeanChooseName((EditTrainerPokemonBean) editPkTrainer_.getBean()), simu_.getInstance().getBuilder());
        callSelectPokemonBeanIsLegSet(selPk_,_name);
        return transitSimu(new SelectPokemonBeanSearch((SelectPokemonBean) selPk_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct pkTrainerSelectPkRow(int _row) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct editPkTrainer_ = goToAddPkTrainer(simu_);
        PokemonBeanStruct selPk_ =transitSimu(new EditTrainerPokemonBeanChooseName((EditTrainerPokemonBean) editPkTrainer_.getBean()), simu_.getInstance().getBuilder());
        callSelectPokemonBeanTypedNameSet(selPk_,DataBase.EMPTY_STRING);
        PokemonBeanStruct rSe_ =transitSimu(new SelectPokemonBeanSearch((SelectPokemonBean) selPk_.getBean()), simu_.getInstance().getBuilder());
        return transitSimu(new SelectPokemonBeanClickLink((SelectPokemonBean) rSe_.getBean(), ((SelectPokemonBean) rSe_.getBean()).getPokedex().get(_row).getName()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct pkTrainerSelectPkType(String _type, boolean _wholeWord) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct editPkTrainer_ = goToAddPkTrainer(simu_);
        PokemonBeanStruct selPk_ =transitSimu(new EditTrainerPokemonBeanChooseName((EditTrainerPokemonBean) editPkTrainer_.getBean()), simu_.getInstance().getBuilder());
        callSelectPokemonBeanTypedTypeSet(selPk_,_type);
        callSelectPokemonBeanWholeWordSet(selPk_,_wholeWord);
        return transitSimu(new SelectPokemonBeanSearch((SelectPokemonBean) selPk_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct pkTrainerSelectPk() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct editPkTrainer_ = goToAddPkTrainer(simu_);
        return goToSelectPk(editPkTrainer_);
    }
    protected static PokemonBeanStruct pkTrainerSelectPkAllyInfo(boolean _ally) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct editPkTrainer_ = goToAddPkTrainer(simu_);
        callEditTrainerPokemonBeanAllyPkSet(editPkTrainer_, _ally);
        return editPkTrainer_;
    }
    protected static PokemonBeanStruct pkTrainerSelectAbCancel() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct editPkTrainer_ = goToAddPkTrainer(simu_);
        PokemonBeanStruct selPk_ =transitSimu(new EditTrainerPokemonBeanChooseAbility((EditTrainerPokemonBean) editPkTrainer_.getBean()), simu_.getInstance().getBuilder());
        return transitSimu(new BeanAnchorCstEvent(CommonBean.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct pkTrainerSelectAb(int _row) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct editPkTrainer_ = goToAddPkTrainer(simu_);
        PokemonBeanStruct selAb_ =transitSimu(new EditTrainerPokemonBeanChooseAbility((EditTrainerPokemonBean) editPkTrainer_.getBean()), simu_.getInstance().getBuilder());
        callSelectAbilityBeanTypedAbilitySet(selAb_,DataBase.EMPTY_STRING);
        PokemonBeanStruct rSe_ =transitSimu(new SelectAbilityBeanSearch((SelectAbilityBean) selAb_.getBean()), simu_.getInstance().getBuilder());
        return transitSimu(new SelectAbilityBeanClickAbility((SelectAbilityBean) rSe_.getBean(), _row), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct pkTrainerSelectAb(String _name) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct editPkTrainer_ = goToAddPkTrainer(simu_);
        PokemonBeanStruct selAb_ =transitSimu(new EditTrainerPokemonBeanChooseAbility((EditTrainerPokemonBean) editPkTrainer_.getBean()), simu_.getInstance().getBuilder());
        callSelectAbilityBeanTypedAbilitySet(selAb_,_name);
        return transitSimu(new SelectAbilityBeanSearch((SelectAbilityBean) selAb_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct pkTrainerSelectAb() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct editPkTrainer_ = goToAddPkTrainer(simu_);
        return goToSelectAb(editPkTrainer_);
    }
    protected static PokemonBeanStruct pkTrainerSelectItCancel() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct editPkTrainer_ = goToAddPkTrainer(simu_);
        PokemonBeanStruct selPk_ =transitSimu(new EditTrainerPokemonBeanChooseItem((EditTrainerPokemonBean) editPkTrainer_.getBean()), simu_.getInstance().getBuilder());
        PokemonBeanStruct rSe_ =transitSimu(new SelectItemBeanCancelItem((SelectItemBean) selPk_.getBean()), simu_.getInstance().getBuilder());
        PokemonBeanStruct twice_ =transitSimu(new EditTrainerPokemonBeanChooseItem((EditTrainerPokemonBean) rSe_.getBean()), simu_.getInstance().getBuilder());
        callSelectItemBeanTypedNameSet(twice_,I_BALL_TR);
        PokemonBeanStruct againEditPk_ =transitSimu(new SelectItemBeanSearch((SelectItemBean) twice_.getBean()), simu_.getInstance().getBuilder());
        PokemonBeanStruct nextIt_ =transitSimu(new EditTrainerPokemonBeanChooseItem((EditTrainerPokemonBean) againEditPk_.getBean()), simu_.getInstance().getBuilder());
        return transitSimu(new SelectItemBeanCancel((SelectItemBean) nextIt_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct pkTrainerSelectItCancelRem() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct editPkTrainer_ = goToAddPkTrainer(simu_);
        PokemonBeanStruct selPk_ =transitSimu(new EditTrainerPokemonBeanChooseItem((EditTrainerPokemonBean) editPkTrainer_.getBean()), simu_.getInstance().getBuilder());
        callSelectItemBeanTypedNameSet(selPk_,DataBase.EMPTY_STRING);
        PokemonBeanStruct rSe_ =transitSimu(new SelectItemBeanSearch((SelectItemBean) selPk_.getBean()), simu_.getInstance().getBuilder());
        PokemonBeanStruct againEditPk_ =transitSimu(new SelectItemBeanClickLink((SelectItemBean) rSe_.getBean(), ((SelectItemBean) rSe_.getBean()).getItems().get(0).getName()), simu_.getInstance().getBuilder());
        PokemonBeanStruct nextIt_ =transitSimu(new EditTrainerPokemonBeanChooseItem((EditTrainerPokemonBean) againEditPk_.getBean()), simu_.getInstance().getBuilder());
        return transitSimu(new SelectItemBeanCancelItem((SelectItemBean) nextIt_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct pkTrainerSelectItName(int _row) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct editPkTrainer_ = goToAddPkTrainer(simu_);
        PokemonBeanStruct selAb_ =transitSimu(new EditTrainerPokemonBeanChooseItem((EditTrainerPokemonBean) editPkTrainer_.getBean()), simu_.getInstance().getBuilder());
        callSelectItemBeanTypedNameSet(selAb_,DataBase.EMPTY_STRING);
        PokemonBeanStruct rSe_ =transitSimu(new SelectItemBeanSearch((SelectItemBean) selAb_.getBean()), simu_.getInstance().getBuilder());
        return transitSimu(new SelectItemBeanClickLink((SelectItemBean) rSe_.getBean(), ((SelectItemBean) rSe_.getBean()).getItems().get(_row).getName()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct pkTrainerSelectItName(String _name) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct editPkTrainer_ = goToAddPkTrainer(simu_);
        return chooseItemPkTrainer(_name, editPkTrainer_);
    }

    private static PokemonBeanStruct chooseItemPkTrainer(String _name, PokemonBeanStruct _editPkTrainer) {
        PokemonBeanStruct selIt_ =transitSimu(new EditTrainerPokemonBeanChooseItem((EditTrainerPokemonBean) _editPkTrainer.getBean()), _editPkTrainer.getInstance().getBuilder());
        return chooseItemGene(_name, selIt_);
    }

    private static PokemonBeanStruct chooseItemPkPlayer(String _name, PokemonBeanStruct _editPkTrainer) {
        PokemonBeanStruct selIt_ =transitSimu(new EditPokemonBeanChooseItem((EditPokemonBean) _editPkTrainer.getBean()), _editPkTrainer.getInstance().getBuilder());
        return chooseItemGene(_name, selIt_);
    }

    private static PokemonBeanStruct chooseItemGene(String _name, PokemonBeanStruct _selIt) {
        callSelectItemBeanTypedNameSet(_selIt, _name);
        return transitSimu(new SelectItemBeanSearch((SelectItemBean) _selIt.getBean()), _selIt.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct pkTrainerSelectItPrice() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct editPkTrainer_ = goToAddPkTrainer(simu_);
        PokemonBeanStruct selAb_ =transitSimu(new EditTrainerPokemonBeanChooseItem((EditTrainerPokemonBean) editPkTrainer_.getBean()), simu_.getInstance().getBuilder());
        callSelectItemBeanTypedPriceSet(selAb_,DataBase.EMPTY_STRING);
        return transitSimu(new SelectItemBeanSearch((SelectItemBean) selAb_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct pkTrainerSelectItCl(String _name) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct editPkTrainer_ = goToAddPkTrainer(simu_);
        PokemonBeanStruct selAb_ =transitSimu(new EditTrainerPokemonBeanChooseItem((EditTrainerPokemonBean) editPkTrainer_.getBean()), simu_.getInstance().getBuilder());
        callSelectItemBeanTypedClassSet(selAb_,_name);
        return transitSimu(new SelectItemBeanSearch((SelectItemBean) selAb_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct pkTrainerSelectItName() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct editPkTrainer_ = goToAddPkTrainer(simu_);
        return goToSelectIt(editPkTrainer_);
    }

    protected static PokemonBeanStruct pkTrainerSetMovesName(String _name) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct editPkTrainer_ = goToAddPkTrainer(simu_);
        PokemonBeanStruct selPk_ =transitSimu(new EditTrainerPokemonBeanAddMoves((EditTrainerPokemonBean) editPkTrainer_.getBean()), simu_.getInstance().getBuilder());
        callEditPokemonMovesBeanTypedNameSet(selPk_,_name);
        return transitSimu(new EditPokemonMovesBeanSearch((EditPokemonMovesBean) selPk_.getBean()), simu_.getInstance().getBuilder());
    }

    protected static PokemonBeanStruct pkTrainerSetMovesCat(String _name) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct editPkTrainer_ = goToAddPkTrainer(simu_);
        PokemonBeanStruct selPk_ =transitSimu(new EditTrainerPokemonBeanAddMoves((EditTrainerPokemonBean) editPkTrainer_.getBean()), simu_.getInstance().getBuilder());
        callEditPokemonMovesBeanCategorySet(selPk_,_name);
        return transitSimu(new EditPokemonMovesBeanSearch((EditPokemonMovesBean) selPk_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct pkTrainerSetMovesCancel() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct editPkTrainer_ = goToAddPkTrainer(simu_);
        PokemonBeanStruct selPk_ =transitSimu(new EditTrainerPokemonBeanAddMoves((EditTrainerPokemonBean) editPkTrainer_.getBean()), simu_.getInstance().getBuilder());
        return transitSimu(new EditPokemonMovesBeanCancel((EditPokemonMovesBean) selPk_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct pkTrainerSetMovesRemove() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct editPkTrainer_ = goToAddPkTrainer(simu_);
        PokemonBeanStruct re_ = addMoveTrainer(M_POK_01_TR, 0, editPkTrainer_);
        callSelectLineMoveSelectedSet(elt(callEditTrainerPokemonBeanMovesGet(re_),0),true);
        return transitSimu(new EditTrainerPokemonBeanDeleteMoves((EditTrainerPokemonBean) re_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct addPkTrainerChangeMoves(boolean _ally) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct editPkTrainer_ = goToAddPkTrainer(simu_);
        PokemonBeanStruct re_ = addMoveTrainer(M_POK_01_TR, 0, editPkTrainer_);
        callSelectLineMoveSelectedSet(elt(callEditTrainerPokemonBeanMovesGet(re_),0),true);
        PokemonBeanStruct afterDel_ =transitSimu(new EditTrainerPokemonBeanDeleteMoves((EditTrainerPokemonBean) re_.getBean()), simu_.getInstance().getBuilder());
        callEditTrainerPokemonBeanAllyPkSet(afterDel_,_ally);
        return transitSimu(new EditTrainerPokemonBeanValidateTrainerPk((EditTrainerPokemonBean) afterDel_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct addPkTrainerChangeItem(boolean _ally) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct editPkTrainer_ = goToAddPkTrainer(simu_);
        assertSame(editPkTrainer_, chooseItemPkTrainer(I_BALL_TR, editPkTrainer_));
        callEditTrainerPokemonBeanAllyPkSet(editPkTrainer_, _ally);
        return transitSimu(new EditTrainerPokemonBeanValidateTrainerPk((EditTrainerPokemonBean) editPkTrainer_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct pkTrainerSetMovesNameAdd(String _name, int _row) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct editPkTrainer_ = goToAddPkTrainer(simu_);
        return addMoveTrainer(_name, _row, editPkTrainer_);
    }

    private static PokemonBeanStruct addMoveTrainer(String _name, int _row, PokemonBeanStruct _editPkTrainer) {
        PokemonBeanStruct editMoves_ =transitSimu(new EditTrainerPokemonBeanAddMoves((EditTrainerPokemonBean) _editPkTrainer.getBean()), _editPkTrainer.getInstance().getBuilder());
        return addMoveGene(_name, _row, editMoves_);
    }

    private static PokemonBeanStruct addMovePlayer(String _name, int _row, PokemonBeanStruct _editPkPlayer) {
        PokemonBeanStruct editMoves_ =transitSimu(new EditPokemonBeanAddMoves((EditPokemonBean) _editPkPlayer.getBean()), _editPkPlayer.getInstance().getBuilder());
        callEditPokemonMovesBeanAvailableMovesOnlySet(editMoves_,false);
        return addMoveGene(_name, _row, editMoves_);
    }

    private static PokemonBeanStruct searchMovePlayer(String _name, PokemonBeanStruct _editPkPlayer, boolean _flag) {
        PokemonBeanStruct editMoves_ =transitSimu(new EditPokemonBeanAddMoves((EditPokemonBean) _editPkPlayer.getBean()), _editPkPlayer.getInstance().getBuilder());
        callEditPokemonMovesBeanAvailableMovesOnlySet(editMoves_, _flag);
        callEditPokemonMovesBeanTypedNameSet(editMoves_, _name);
        return transitSimu(new EditPokemonMovesBeanSearch((EditPokemonMovesBean) editMoves_.getBean()), _editPkPlayer.getInstance().getBuilder());
    }

    private static PokemonBeanStruct addMoveGene(String _name, int _row, PokemonBeanStruct _editMoves) {
        callEditPokemonMovesBeanTypedNameSet(_editMoves, _name);
        PokemonBeanStruct foundMoves_ =transitSimu(new EditPokemonMovesBeanSearch((EditPokemonMovesBean) _editMoves.getBean()), _editMoves.getInstance().getBuilder());
        callSelectLineMoveSelectedSet(elt(callEditPokemonMovesBeanMovesGet(foundMoves_), _row),true);
        return transitSimu(new EditPokemonMovesBeanAddMoves((EditPokemonMovesBean) foundMoves_.getBean()), _editMoves.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct pkTrainerSetMovesType(String _type, boolean _wholeWord) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct editPkTrainer_ = goToAddPkTrainer(simu_);
        PokemonBeanStruct selPk_ =transitSimu(new EditTrainerPokemonBeanAddMoves((EditTrainerPokemonBean) editPkTrainer_.getBean()), simu_.getInstance().getBuilder());
        callEditPokemonMovesBeanTypedTypeSet(selPk_,_type);
        callEditPokemonMovesBeanWholeWordSet(selPk_,_wholeWord);
        return transitSimu(new EditPokemonMovesBeanSearch((EditPokemonMovesBean) selPk_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct pkTrainerSetMoves() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct editPkTrainer_ = goToAddPkTrainer(simu_);
        return goToSetMovesTrainer(editPkTrainer_);
    }
    protected static PokemonBeanStruct pkTrainer(boolean _select) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        PokemonBeanStruct edit_ = goToAddPkTrainer(simu_);
        callSelectLineMoveSelectedSet(elt(callEditTrainerPokemonBeanMovesGet(edit_),0),_select);
        return edit_;
    }
    protected static String editNoPlayerPk() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
//        callSimulationBeanSelectedPkSet(simu_,-1);
        return navigateData(new SimulationBeanSelectPkValidation((SimulationBean) simu_.getBean(),((SimulationBean)simu_.getBean()).getSelectedAction(),-1),simu_);
    }
    protected static PokemonBeanStruct pkPlayerRemove() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        pkTrainerSelectPkPlayerNameCycle(P_POK_00_TR, A_SIM_1, simu_, 4);
        return editPkPlayer(simu_, P_POK_01_TR, A_SIM_2_TR, 0, 5, TeamCrud.REMOVE);
    }
    protected static String editNoSelectedPlayerPk() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        PokemonBeanStruct added_ = pkTrainerSelectPkPlayerNameCycle(P_POK_00_TR, A_SIM_1, simu_, 4);
//        callSimulationBeanSelectedActionSet(added_, TeamCrud.NOTHING.getTeamCrudString());
//        callSimulationBeanSelectedPkSet(added_,-1);
        return navigateData(new SimulationBeanSelectPkValidation((SimulationBean) simu_.getBean(),TeamCrud.NOTHING.getTeamCrudString(),-1),added_);
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

    protected static PokemonBeanStruct editEditSelectedPlayerPk() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        return editPkPlayer(simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
    }
    protected static PokemonBeanStruct editEditSelectedPlayerPkForm(boolean _heal, Rate _exp, Rate _hp) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        PokemonBeanStruct editing_ = editPkPlayer(simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        callEditPokemonBeanHealSet(editing_, _heal);
        callEditPokemonBeanBallSet(editing_,I_BALL);
        callEditPokemonBeanExperienceSet(editing_, _exp);
        callEditPokemonBeanHappinessSet(editing_,1);
        callEditPokemonBeanRemainingHpSet(editing_, _hp);
        ( ((EvLineStruct) second(elt(callEditPokemonBeanEvGet(editing_),2))).getEvLine()).getEv().valueLong(33);
//        callEvLineEvSet(second(elt(callEditPokemonBeanEvGet(editing_),2)),33);
        return transitSimu(new EditPokemonBeanEdit((EditPokemonBean) editing_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct editEditSelectedPlayerPkFormNoMove() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        PokemonBeanStruct editing_ = editPkPlayer(simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        callSelectLineMoveSelectedSet(elt(callEditPokemonBeanMovesGet(editing_),0),true);
        transitSimu(new EditPokemonBeanDeleteMoves((EditPokemonBean) editing_.getBean()), simu_.getInstance().getBuilder());
        return transitSimu(new EditPokemonBeanEdit((EditPokemonBean) editing_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct editEditSelectedPlayerPkFormCancel() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        PokemonBeanStruct editing_ = editPkPlayer(simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        callSelectLineMoveSelectedSet(elt(callEditPokemonBeanMovesGet(editing_),0),true);
        transitSimu(new EditPokemonBeanDeleteMoves((EditPokemonBean) editing_.getBean()), simu_.getInstance().getBuilder());
        return transitSimu(new EditPokemonBeanCancel((EditPokemonBean) editing_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct editEditSelectedPlayerPkHeal(boolean _heal) {
        PokemonBeanStruct h_ = editEditSelectedPlayerPk();
        callEditPokemonBeanHealSet(h_,_heal);
        return h_;
    }
    protected static PokemonBeanStruct editEditSelectedPlayerPkItem() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        PokemonBeanStruct edit_ = editPkPlayer(simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        assertSame(edit_, chooseItemPkPlayer(I_BALL_TR, edit_));
        return edit_;
    }
    protected static PokemonBeanStruct editEditSelectedPlayerPkItemCancelItem() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        PokemonBeanStruct edit_ = editPkPlayer(simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        PokemonBeanStruct chosen_ = chooseItemPkPlayer(I_BALL_TR, edit_);
        PokemonBeanStruct redo_ =transitSimu(new EditPokemonBeanChooseItem((EditPokemonBean) chosen_.getBean()), simu_.getInstance().getBuilder());
        return transitSimu(new SelectItemBeanCancelItem((SelectItemBean) redo_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct editEditSelectedPlayerPkItemCancel() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        PokemonBeanStruct edit_ = editPkPlayer(simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        PokemonBeanStruct first_ =transitSimu(new EditPokemonBeanChooseItem((EditPokemonBean) edit_.getBean()), simu_.getInstance().getBuilder());
        PokemonBeanStruct back_ =transitSimu(new SelectItemBeanCancel((SelectItemBean) first_.getBean()), simu_.getInstance().getBuilder());
        return chooseItemPkPlayer(I_BALL_TR, back_);
    }
    protected static PokemonBeanStruct editEditSelectedPlayerPkItemPart(int _row) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        PokemonBeanStruct edit_ = editPkPlayer(simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        PokemonBeanStruct chosen_ = chooseItemPkPlayer(DataBase.EMPTY_STRING, edit_);
        return transitSimu(new SelectItemBeanClickLink((SelectItemBean) chosen_.getBean(), ((SelectItemBean) chosen_.getBean()).getItems().get(_row).getName()), simu_.getInstance().getBuilder());
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
    protected static PokemonBeanStruct editEditSelectedPlayerPkListMoves() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        PokemonBeanStruct editing_ = editPkPlayer(simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        return transitSimu(new EditPokemonBeanAddMoves((EditPokemonBean) editing_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct editEditSelectedPlayerPkListMoves(String _name, boolean _flag) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        PokemonBeanStruct editing_ = editPkPlayer(simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        return searchMovePlayer(_name, editing_,_flag);
    }
    protected static PokemonBeanStruct editEditSelectedPlayerPkListMovesCancel() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        PokemonBeanStruct editing_ = editPkPlayer(simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        PokemonBeanStruct se_ = searchMovePlayer(M_POK_04_TR, editing_, false);
        callSelectLineMoveSelectedSet(elt(callEditPokemonMovesBeanMovesGet(se_),0),true);
        return transitSimu(new EditPokemonMovesBeanCancel((EditPokemonMovesBean) se_.getBean()), simu_.getInstance().getBuilder());
    }

    private static PokemonBeanStruct editPkPlayer(PokemonBeanStruct _simu, String _name, String _ab, int _index, int _level, TeamCrud _mode) {
        PokemonBeanStruct added_ = pkTrainerSelectPkPlayerNameCycle(_name, _ab, _simu, _level);
//        callSimulationBeanSelectedActionSet(added_, _mode.getTeamCrudString());
//        callSimulationBeanSelectedPkSet(added_, _index);
        return transitSimu(new SimulationBeanSelectPkValidation((SimulationBean) added_.getBean(), _mode.getTeamCrudString(), _index), _simu.getInstance().getBuilder());
    }

    protected static PokemonBeanStruct pkPlayerSetMovesRemove() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        PokemonBeanStruct editing_ = editPkPlayer(simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        PokemonBeanStruct re_ = addMovePlayer(M_POK_01_TR, 0, editing_);
        callSelectLineMoveSelectedSet(elt(callEditPokemonBeanMovesGet(re_),0),true);
        return transitSimu(new EditPokemonBeanDeleteMoves((EditPokemonBean) re_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static String editForgetSelectedPlayerPk() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        PokemonBeanStruct added_ = pkTrainerSelectPkPlayerNameCycle(P_POK_00_TR, A_SIM_1, simu_, 4);
//        callSimulationBeanSelectedActionSet(added_, TeamCrud.NOTHING.getTeamCrudString());
//        callSimulationBeanSelectedPkSet(added_,0);
        return navigateData(new SimulationBeanSelectPkValidation((SimulationBean)added_.getBean(),TeamCrud.NOTHING.getTeamCrudString(),0),added_);
    }
    protected static PokemonBeanStruct addPkPlayerChangeMoves() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        PokemonBeanStruct editing_ = editPkPlayer(simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        assertSame(editing_, chooseItemPkPlayer(I_BALL_TR, editing_));
        PokemonBeanStruct re_ = addMovePlayer(M_POK_01_TR, 0, editing_);
        callSelectLineMoveSelectedSet(elt(callEditPokemonBeanMovesGet(re_),0),true);
        PokemonBeanStruct afterDel_ =transitSimu(new EditPokemonBeanDeleteMoves((EditPokemonBean) re_.getBean()), simu_.getInstance().getBuilder());
        return transitSimu(new EditPokemonBeanEdit((EditPokemonBean) afterDel_.getBean()), simu_.getInstance().getBuilder());
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
    private static PokemonBeanStruct pkTrainerSelectPkPlayerNameCycle(String _name, String _ability, PokemonBeanStruct _simu, int _level) {
        PokemonBeanStruct addPk_ = goToAddPkPlayer(_simu);
        callAddPokemonBeanTypedNameSet(addPk_,_name);
        PokemonBeanStruct afSearch_ =transitSimu(new AddPokemonBeanSearch((AddPokemonBean) addPk_.getBean()), _simu.getInstance().getBuilder());
        callAddPokemonBeanAbilitySet(afSearch_,_ability);
        callAddPokemonBeanLevelSet(afSearch_,_level);
        PokemonBeanStruct afterAddEdit_ =transitSimu(new AddPokemonBeanAdd((AddPokemonBean) afSearch_.getBean()), _simu.getInstance().getBuilder());
        assertSame(afterAddEdit_, _simu);
        return afterAddEdit_;
    }
    protected static PokemonBeanStruct pkPlayerSelectPk() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        return goToAddPkPlayer(simu_);
    }
    protected static PokemonBeanStruct pkPlayerSelectPkCancel() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        PokemonBeanStruct addPk_ = goToAddPkPlayer(simu_);
        return transitSimu(new AddPokemonBeanCancel((AddPokemonBean) addPk_.getBean()), simu_.getInstance().getBuilder());
    }

    protected static PokemonBeanStruct pkPlayerSelectPkNameAbility(String _name) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        return pkPlAb(_name, simu_);
    }

    protected static PokemonBeanStruct pkPlayerSelectPkNameTwice() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        pkTrainerSelectPkPlayerNameCycle(P_POK_01_TR,A_SIM_1, simu_,40);
        return pkTrainerSelectPkPlayerNameCycle(P_POK_02_TR,A_SIM_2, simu_,39);
    }

    protected static PokemonBeanStruct pkPlayerValidateEvosNoSelect() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
//        callSimulationBeanSelectedPkSet(simu_,-1);
        return transitSimu(new SimulationBeanSelectPkEvosValidation(((SimulationBean) simu_.getBean()), -1), simu_.getInstance().getBuilder());
    }

    protected static PokemonBeanStruct pkPlayerValidateEvosSelect() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
//        callSimulationBeanSelectedPkSet(simu_,1);
        return transitSimu(new SimulationBeanSelectPkEvosValidation(((SimulationBean) simu_.getBean()), 1), simu_.getInstance().getBuilder());
    }

    protected static PokemonBeanStruct pkPlayerValidateEvoValues() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
//        callSimulationBeanSelectedPkSet(simu_,1);
        transitSimu(new SimulationBeanSelectPkEvosValidation(((SimulationBean) simu_.getBean()), 1), simu_.getInstance().getBuilder());
        callSimulationBeanChosenEvoSet(simu_,P_POK_03);
        callSimulationBeanLevelEvoSet(simu_,41);
        return simu_;
    }

    protected static PokemonBeanStruct pkPlayerValidateEvoValidate() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
//        callSimulationBeanSelectedPkSet(simu_,1);
        transitSimu(new SimulationBeanSelectPkEvosValidation(((SimulationBean) simu_.getBean()), 1), simu_.getInstance().getBuilder());
        callSimulationBeanChosenEvoSet(simu_,P_POK_03);
        callSimulationBeanLevelEvoSet(simu_,39);
        return transitSimu(new SimulationBeanValidateEvo(((SimulationBean) simu_.getBean())), simu_.getInstance().getBuilder());
    }

    protected static PokemonBeanStruct pkPlayerEvoThenFighters() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        return validEvos(simu_);
    }

    protected static PokemonBeanStruct pkPlayerEvoFightersImmediateValid() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
//        callSimulationBeanSelectedPkSet(simu_,-1);
        return transitSimu(new SimulationBeanSelectPkFrontValidation(((SimulationBean) simu_.getBean()), -1), simu_.getInstance().getBuilder());
    }

    protected static PokemonBeanStruct pkPlayerEvoFightersFormValid() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
        return changeFighterPosition(simu_, 0, 0, 0);
    }

    protected static PokemonBeanStruct pkPlayerEvoFightersWithoutFronts() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
        return transitSimu(new SimulationBeanValidateFrontFighters((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
    }

    protected static PokemonBeanStruct pkPlayerEvoFightersSufficientFronts() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
        return fighterPositions(simu_);
    }

    protected static PokemonBeanStruct pkPlayerEvoFightersSufficientFrontsFormMove(int _index) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
        fighterPositions(simu_);
//        callSimulationBeanSelectedPkSet(simu_,_index);
        assertSame(simu_,transitSimu(new SimulationBeanSelectPkMovesValidation((SimulationBean) simu_.getBean(), _index), simu_.getInstance().getBuilder()));
        return simu_;
    }

    protected static PokemonBeanStruct pkPlayerEvoFightersSufficientFrontsFormMoveValidateMovesQuick(int _index, String _ab) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
        fighterPositions(simu_);
//        callSimulationBeanSelectedPkSet(simu_,_index);
        assertSame(simu_,transitSimu(new SimulationBeanSelectPkMovesValidation((SimulationBean) simu_.getBean(), _index), simu_.getInstance().getBuilder()));
        return movesAbilities(_ab, simu_);
    }

    protected static PokemonBeanStruct pkPlayerEvoFightersSufficientFrontsFormMoveValidateMovesQuickTwice(int _index, String _ab) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
        fighterPositions(simu_);
//        callSimulationBeanSelectedPkSet(simu_,_index);
        assertSame(simu_,transitSimu(new SimulationBeanSelectPkMovesValidation((SimulationBean) simu_.getBean(), _index), simu_.getInstance().getBuilder()));
        movesAbilities(_ab, simu_);
        return movesAbilities(DataBase.EMPTY_STRING, simu_);
    }

    protected static PokemonBeanStruct pkPlayerEvoFightersSufficientFrontsFormMoveValidateMovesAllFightersOk() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
        fighterPositions(simu_);
        return movesSet(simu_);
    }

    protected static PokemonBeanStruct pkPlayerEvoFighterChoice(int _index, int _round) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
        fighterPositions(simu_);
        movesSet(simu_);
//        transitSimu(new SimulationBeanSelectPkMovesValidation((SimulationBean) simu_.getInstance(),_index),simu_.getInstance().getBuilder());
//        callSimulationBeanSelectedPkSet(simu_,_index);
//        callSimulationBeanSelectedRoundSet(simu_,Long.toString(_round));
        ( (SimulationBean) simu_.getInstance()).getSelectedRound().valueInt(_round);
        return transitSimu(new SimulationBeanSelectPkMovesValidation((SimulationBean) simu_.getInstance(),_index),simu_.getInstance().getBuilder());
    }

    protected static PokemonBeanStruct pkPlayerEvoFighterChoiceAfter(int _index, int _round, boolean _allyChoice, int _move, int _target) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
        fighterPositions(simu_);
        movesSet(simu_);
        return moveChoice(_index, _round, _allyChoice, _move, _target, simu_);
    }

    protected static PokemonBeanStruct pkPlayerEvoFighterSimulate() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
        fighterPositions(simu_);
        movesSet(simu_);
        return mvChs(simu_);
    }

    protected static PokemonBeanStruct pkPlayerEvoFighterSimulateStMove() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
        fighterPositions(simu_);
        movesSet(simu_);
        moveChoices(simu_);
        return transitSimu(new SimulationBeanCancelMovesEvos((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
    }

    protected static PokemonBeanStruct pkPlayerEvoFighterSimulateStMoveCancel() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
        fighterPositions(simu_);
        movesSet(simu_);
        moveChoices(simu_);
        transitSimu(new SimulationBeanCancelMovesEvos((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
        transitSimu(new SimulationBeanCancelMovesSets((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
        return transitSimu(new SimulationBeanCancelFrontFighters((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
    }

    protected static PokemonBeanStruct pkPlayerEvoFighterSimulateStMoveCancel2() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
        fighterPositions(simu_);
        movesSet(simu_);
        moveChoices(simu_);
        transitSimu(new SimulationBeanCancelMovesEvos((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
        transitSimu(new SimulationBeanCancelMovesSets((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
        transitSimu(new SimulationBeanCancelFrontFighters((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
        return transitSimu(new SimulationBeanCancelEvolutions((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
    }

    protected static PokemonBeanStruct pkPlayerFighterSimulate() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        playerTeamSampleSkip(simu_);
        return oneFight(simu_);
    }

    protected static NaSt pkPlayerFighterSimulateComment() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        playerTeamSampleSkip(simu_);
        oneFight(simu_);
        transitSimu(new SimulationBeanDisplayComments((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
        transitSimu(new SimulationBeanHideComments((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
        return callSimulationBeanCommentsGet(simu_);
    }

    protected static PokemonBeanStruct pkPlayerFighterSimulateAfterFight() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        playerTeamSampleSkip(simu_);
        oneFight(simu_);
        transitSimu(new SimulationBeanNextFight((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
        return transitSimu(new SimulationBeanSelectPkEvosAfterValidation((SimulationBean) simu_.getBean(), (((SimulationBean) simu_.getBean()).getSelectedPk())), simu_.getInstance().getBuilder());
    }

    protected static PokemonBeanStruct pkPlayerFighterSimulateAfterFightCancel() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        playerTeamSampleSkip(simu_);
        oneFight(simu_);
        transitSimu(new SimulationBeanNextFight((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
        return transitSimu(new SimulationBeanChangeFightWhileEnd((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
    }

    protected static PokemonBeanStruct pkPlayerFighterSimulateAfterFightCancel2() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        playerTeamSampleSkip(simu_);
        oneFight(simu_);
        transitSimu(new SimulationBeanNextFight((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
        transitSimu(new SimulationBeanChangeFightWhileEnd((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
        return transitSimu(new SimulationBeanChangeFight((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
    }

    protected static PokemonBeanStruct pkPlayerFighterSimulateAfterFightCancel3() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        playerTeamSampleSkip(simu_);
        oneFight(simu_);
        transitSimu(new SimulationBeanNextFight((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
        transitSimu(new SimulationBeanChangeFightWhileEnd((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
        transitSimu(new SimulationBeanChangeFight((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
        return transitSimu(new SimulationBeanCancelMovesEvos((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
    }

    protected static PokemonBeanStruct pkPlayerFighterSimulateAfterFightCancel4() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        playerTeamSampleSkip(simu_);
        oneFight(simu_);
        transitSimu(new SimulationBeanNextFight((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
        transitSimu(new SimulationBeanChangeFightWhileEnd((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
        transitSimu(new SimulationBeanChangeFight((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
        transitSimu(new SimulationBeanCancelMovesEvos((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
        transitSimu(new SimulationBeanCancelFrontFighters((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
        transitSimu(new SimulationBeanCancelTeam(((SimulationBean) simu_.getBean())), simu_.getInstance().getBuilder());
        return transitSimu(new SimulationBeanCancelDiffChoice((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
    }

    protected static PokemonBeanStruct pkPlayerFighterSimulateAfterFightOne() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        playerTeamSampleSkip(simu_);
        oneFight(simu_);
        transitSimu(new SimulationBeanNextFight((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
//        callSimulationBeanSelectedPkSet(simu_,0);
        return transitSimu(new SimulationBeanSelectPkEvosAfterValidation((SimulationBean) simu_.getBean(), 0), simu_.getInstance().getBuilder());
    }

    protected static PokemonBeanStruct pkPlayerFighterSimulateAfterFightOneValidate() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        playerTeamSampleSkip(simu_);
        oneFight(simu_);
        transitSimu(new SimulationBeanNextFight((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
//        callSimulationBeanSelectedPkSet(simu_,0);
        transitSimu(new SimulationBeanSelectPkEvosAfterValidation((SimulationBean) simu_.getBean(), 0), simu_.getInstance().getBuilder());
        callSimulationBeanEvolutionAfterFightSet(simu_,P_POK_05);
        transitSimu(new SimulationBeanValidateEvolutionAfterFight((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
        callSimulationBeanAbilityAfterFightSet(simu_,A_SIM_2);
        transitSimu(new SimulationBeanValidateMovesAbilityAfterFight((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
        return transitSimu(new SimulationBeanValidateMovesAfterFight((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
    }

    protected static PokemonBeanStruct pkPlayerFighterSimulateAfterFightCancelOne() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        playerTeamSampleSkip(simu_);
        oneFight(simu_);
        transitSimu(new SimulationBeanNextFight((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
//        callSimulationBeanSelectedPkSet(simu_,0);
        transitSimu(new SimulationBeanSelectPkEvosAfterValidation((SimulationBean) simu_.getBean(), 0), simu_.getInstance().getBuilder());
        callSimulationBeanEvolutionAfterFightSet(simu_,P_POK_05);
        transitSimu(new SimulationBeanValidateEvolutionAfterFight((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
        return transitSimu(new SimulationBeanCancelEvolutionsAfterFight((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
    }

    private static PokemonBeanStruct oneFight(PokemonBeanStruct _simu) {
        fighterPositions(_simu);
        return mvChs(_simu);
    }

    protected static PokemonBeanStruct pkPlayerFighterSimulateOneFight() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(1, pk_.getDataBase());
        foeTeamSample(simu_);
        playerTeamSampleSkip(simu_);
        fighterPositions(simu_);
        return mvChs(simu_);
    }

    protected static PokemonBeanStruct pkPlayerFighterSkipEvosStateBadNbCount() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        PokemonBeanStruct editing_ = editPkPlayer(simu_, P_POK_00_TR, A_SIM_1, 0, 4, TeamCrud.EDIT);
        assertSame(editing_, chooseItemPkPlayer(I_BALL_TR, editing_));
        PokemonBeanStruct re_ = addMovePlayer(M_POK_01_TR, 0, editing_);
        callSelectLineMoveSelectedSet(elt(callEditPokemonBeanMovesGet(re_),0),true);
        PokemonBeanStruct afterDel_ =transitSimu(new EditPokemonBeanDeleteMoves((EditPokemonBean) re_.getBean()), simu_.getInstance().getBuilder());
        transitSimu(new EditPokemonBeanEdit((EditPokemonBean) afterDel_.getBean()), simu_.getInstance().getBuilder());
        pkTrainerSelectPkPlayerNameCycle(P_POK_04_TR,A_SIM_1, simu_,41);
        return transitSimu(new SimulationBeanValidateFoeChoiceSkipEvolutions(((SimulationBean) simu_.getBean())), simu_.getInstance().getBuilder());
    }

    protected static PokemonBeanStruct pkPlayerFighterSkipEvosStateEmpty() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        return transitSimu(new SimulationBeanValidateFoeChoiceSkipEvolutions(((SimulationBean) simu_.getBean())), simu_.getInstance().getBuilder());
    }

    protected static PokemonBeanStruct pkPlayerEvoFighterSimulateKo() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
        fighterPositions(simu_);
        movesSet(simu_);
        PokemonBeanStruct koChs_ = moveChoicesKo(simu_);
        return transitSimu(new SimulationBeanSimulateFight((SimulationBean) koChs_.getBean()), simu_.getInstance().getBuilder());
    }

    protected static PokemonBeanStruct pkPlayerEvoFighterSimulateKos() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSampleInv(simu_);
        playerTeamSampleInv(simu_);
        transitSimu(new SimulationBeanValidateEvolutions(((SimulationBean) simu_.getBean())), simu_.getInstance().getBuilder());
        fighterPositions(simu_);
        movesSet(simu_);
        return mvChs(simu_);
    }

    private static PokemonBeanStruct mvChs(PokemonBeanStruct _simu) {
        PokemonBeanStruct first_ = moveChoices(_simu);
        return transitSimu(new SimulationBeanSimulateFight((SimulationBean) first_.getBean()), _simu.getInstance().getBuilder());
    }

    private static PokemonBeanStruct moveChoices(PokemonBeanStruct _simu) {
        moveChoice(0,0,0,0, _simu);
        moveChoice(1,0,0,1, _simu);
        moveChoice(0,1,0,0, _simu);
        return moveChoice(1,1,0,1, _simu);
    }

    private static PokemonBeanStruct moveChoicesKo(PokemonBeanStruct _simu) {
        moveChoice(0,0,0,0, _simu);
        moveChoice(1,0,0,1, _simu);
        moveChoice(0,0,0,0, _simu);
        return moveChoice(1,0,0,1, _simu);
    }

    private static PokemonBeanStruct moveChoice(int _index, int _round, int _move, int _target, PokemonBeanStruct _simu) {
        return moveChoice(_index,_round,false,_move,_target, _simu);
    }
    private static PokemonBeanStruct moveChoice(int _index, int _round, boolean _allyChoice, int _move, int _target, PokemonBeanStruct _simu) {
//        callSimulationBeanSelectedPkSet(_simu, _index);
        ( (SimulationBean) _simu.getInstance()).getSelectedRound().valueInt(_round);
        transitSimu(new SimulationBeanSelectPkMovesValidation((SimulationBean) _simu.getInstance(),_index),_simu.getInstance().getBuilder());
//        callSimulationBeanSelectedRoundSet(_simu,Long.toString(_round));
//        beforeDisplaying(_simu);
//        callSimulationBeanAllyChoiceSet(_simu, _allyChoice);
        ( (SimulationBean) _simu.getInstance()).getAllyChoice().setSelected(_allyChoice);
//        callSimulationBeanSelectedMoveSet(_simu, _move);
//        callSimulationBeanTargetSet(_simu, Long.toString(_target));
        ( (SimulationBean) _simu.getInstance()).getTarget().valueInt(_target);
        return transitSimu(new SimulationBeanSelectMovesValidation((SimulationBean) _simu.getBean(), _move), _simu.getInstance().getBuilder());
    }

    protected static PokemonBeanStruct pkPlayerEvoFightersSufficientFrontsFormMoveValidateMovesAllFightersKo() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
        fighterPositions(simu_);
//        callSimulationBeanSelectedPkSet(simu_, 1);
        assertSame(simu_,transitSimu(new SimulationBeanSelectPkMovesValidation((SimulationBean) simu_.getBean(), 1), simu_.getInstance().getBuilder()));
        callSimulationBeanCurrentAbilitySet(simu_, A_SIM_1);
        callSelectLineMoveSelectedSet(elt(callSimulationBeanKeptMovesGet(simu_),0),true);
        callSelectLineMoveSelectedSet(elt(callSimulationBeanKeptMovesGet(simu_),1),true);
        callSelectLineMoveSelectedSet(elt(callSimulationBeanKeptMovesGet(simu_),2),true);
        transitSimu(new SimulationBeanValidateMoves((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
        return transitSimu(new SimulationBeanValidateMovesSets((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
    }

    protected static PokemonBeanStruct pkPlayerEvoFightersSufficientFrontsFormMoveValidateMovesAllFightersCancel() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
        validEvos(simu_);
        fighterPositions(simu_);
//        callSimulationBeanSelectedPkSet(simu_, 1);
        assertSame(simu_,transitSimu(new SimulationBeanSelectPkMovesValidation((SimulationBean) simu_.getBean(), 1), simu_.getInstance().getBuilder()));
        callSimulationBeanCurrentAbilitySet(simu_, A_SIM_1);
        callSelectLineMoveSelectedSet(elt(callSimulationBeanKeptMovesGet(simu_),0),true);
        callSelectLineMoveSelectedSet(elt(callSimulationBeanKeptMovesGet(simu_),1),true);
        callSelectLineMoveSelectedSet(elt(callSimulationBeanKeptMovesGet(simu_),2),true);
        transitSimu(new SimulationBeanValidateMoves((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
        transitSimu(new SimulationBeanCancelMoves((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
        return transitSimu(new SimulationBeanValidateMovesSets((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
    }

    private static PokemonBeanStruct movesSet(PokemonBeanStruct _simu) {
        withoutAbility(0, _simu);
        withAbility(1,A_SIM_1, _simu);
        withoutAbility(2, _simu);
        withAbility(3,A_SIM_1, _simu);
        return transitSimu(new SimulationBeanValidateMovesSets((SimulationBean) _simu.getBean()), _simu.getInstance().getBuilder());
    }

    private static PokemonBeanStruct withAbility(int _index, String _ab, PokemonBeanStruct _simu) {
//        callSimulationBeanSelectedPkSet(_simu, _index);
        assertSame(_simu,transitSimu(new SimulationBeanSelectPkMovesValidation((SimulationBean) _simu.getBean(), _index), _simu.getInstance().getBuilder()));
        movesAbilities(_ab, _simu);
        movesAbilities(DataBase.EMPTY_STRING, _simu);
        return movesAbilities(DataBase.EMPTY_STRING, _simu);
    }

    private static PokemonBeanStruct withoutAbility(int _index, PokemonBeanStruct _simu) {
//        callSimulationBeanSelectedPkSet(_simu, _index);
        assertSame(_simu,transitSimu(new SimulationBeanSelectPkMovesValidation((SimulationBean) _simu.getBean(), _index), _simu.getInstance().getBuilder()));
        movesAbilities(DataBase.EMPTY_STRING, _simu);
        movesAbilities(DataBase.EMPTY_STRING, _simu);
        return movesAbilities(DataBase.EMPTY_STRING, _simu);
    }

    private static PokemonBeanStruct movesAbilities(String _ab, PokemonBeanStruct _simu) {
        callSimulationBeanCurrentAbilitySet(_simu, _ab);
        return transitSimu(new SimulationBeanValidateMoves((SimulationBean) _simu.getBean()), _simu.getInstance().getBuilder());
    }

    private static PokemonBeanStruct fighterPositions(PokemonBeanStruct _simu) {
        changeFighterPosition(_simu,0,0,0);
        changeFighterPosition(_simu,1,0,1);
        changeFighterPosition(_simu,2,0,Fighter.BACK);
        changeFighterPosition(_simu,3,0,Fighter.BACK);
        changeFighterPosition(_simu,0,1,0);
        changeFighterPosition(_simu,1,1,1);
        changeFighterPosition(_simu,2,1,Fighter.BACK);
        changeFighterPosition(_simu,3,1,Fighter.BACK);
        return transitSimu(new SimulationBeanValidateFrontFighters((SimulationBean) _simu.getBean()), _simu.getInstance().getBuilder());
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

    private static PokemonBeanStruct changeFighterPosition(PokemonBeanStruct _simu, int _index, int _round, int _place) {
//        callSimulationBeanSelectedPkSet(_simu, _index);
//        callSimulationBeanSelectedRoundSet(_simu, _round);
        ( (SimulationBean) _simu.getInstance()).getSelectedRound().valueInt(_round);
//        callSimulationBeanPlaceFightSet(_simu, _place);
        ( (SimulationBean) _simu.getInstance()).getPlaceFight().valueInt(_place);
        return transitSimu(new SimulationBeanSelectPkFrontValidation(((SimulationBean) _simu.getBean()), _index), _simu.getInstance().getBuilder());
    }

    private static PokemonBeanStruct validEvos(PokemonBeanStruct _simu) {
        validEvo(_simu,1);
        validEvo(_simu,3);
        return transitSimu(new SimulationBeanValidateEvolutions(((SimulationBean) _simu.getBean())), _simu.getInstance().getBuilder());
    }

    protected static PokemonBeanStruct pkPlayerValidateEvoValidateThenCancel() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        playerTeamSample(simu_);
//        callSimulationBeanSelectedPkSet(simu_,1);
        transitSimu(new SimulationBeanSelectPkEvosValidation(((SimulationBean) simu_.getBean()), 1), simu_.getInstance().getBuilder());
        callSimulationBeanChosenEvoSet(simu_,P_POK_03);
        callSimulationBeanLevelEvoSet(simu_,39);
        transitSimu(new SimulationBeanValidateEvo(((SimulationBean) simu_.getBean())), simu_.getInstance().getBuilder());
        return transitSimu(new SimulationBeanCancelEvo(((SimulationBean) simu_.getBean())), simu_.getInstance().getBuilder());
    }
    private static void validEvo(PokemonBeanStruct _simu, int _index) {
//        callSimulationBeanSelectedPkSet(_simu,_index);
        transitSimu(new SimulationBeanSelectPkEvosValidation(((SimulationBean) _simu.getBean()), _index), _simu.getInstance().getBuilder());
        callSimulationBeanChosenEvoSet(_simu,P_POK_03);
        callSimulationBeanLevelEvoSet(_simu,41);
        transitSimu(new SimulationBeanValidateEvo(((SimulationBean) _simu.getBean())), _simu.getInstance().getBuilder());
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

    protected static PokemonBeanStruct pkPlayerValidateEvos() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        return playerTeamSample(simu_);
    }

    private static PokemonBeanStruct playerTeamSample(PokemonBeanStruct _simu) {
        pkTrainerSelectPkPlayerNameCycle(P_POK_01_TR,A_SIM_1, _simu,41);
        pkTrainerSelectPkPlayerNameCycle(P_POK_02_TR,A_SIM_1, _simu,41);
        pkTrainerSelectPkPlayerNameCycle(P_POK_01_TR,A_SIM_1, _simu,41);
        pkTrainerSelectPkPlayerNameCycle(P_POK_02_TR,A_SIM_1, _simu,41);
        return transitSimu(new SimulationBeanValidateTeam((SimulationBean) _simu.getBean()), _simu.getInstance().getBuilder());
    }

    private static PokemonBeanStruct playerTeamSampleSkip(PokemonBeanStruct _simu) {
        PokemonBeanStruct editing_ = editPkPlayer(_simu, P_POK_04_TR, A_SIM_1, 0, 41, TeamCrud.EDIT);
        assertSame(editing_, chooseItemPkPlayer(I_BALL_TR, editing_));
        assertSame(_simu, transitSimu(new EditPokemonBeanEdit((EditPokemonBean) editing_.getBean()), _simu.getInstance().getBuilder()));
        pkTrainerSelectPkPlayerNameCycle(P_POK_01_TR,A_SIM_1, _simu,41);
        pkTrainerSelectPkPlayerNameCycle(P_POK_02_TR,A_SIM_1, _simu,41);
        pkTrainerSelectPkPlayerNameCycle(P_POK_02_TR,A_SIM_1, _simu,41);
        return transitSimu(new SimulationBeanValidateFoeChoiceSkipEvolutions(((SimulationBean) _simu.getBean())), _simu.getInstance().getBuilder());
    }

    private static PokemonBeanStruct playerTeamSampleInv(PokemonBeanStruct _simu) {
        pkTrainerSelectPkPlayerNameCycle(P_POK_01_TR,A_SIM_1, _simu,4);
        pkTrainerSelectPkPlayerNameCycle(P_POK_02_TR,A_SIM_1, _simu,4);
        pkTrainerSelectPkPlayerNameCycle(P_POK_01_TR,A_SIM_1, _simu,4);
        pkTrainerSelectPkPlayerNameCycle(P_POK_02_TR,A_SIM_1, _simu,4);
        return transitSimu(new SimulationBeanValidateTeam((SimulationBean) _simu.getBean()), _simu.getInstance().getBuilder());
    }

    protected static PokemonBeanStruct pkPlayerValidateEvosKo() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        return transitSimu(new SimulationBeanValidateTeam((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
    }
    private static PokemonBeanStruct pkPlAb(String _name, PokemonBeanStruct _simu) {
        PokemonBeanStruct addPk_ = goToAddPkPlayer(_simu);
        callAddPokemonBeanTypedNameSet(addPk_, _name);
        PokemonBeanStruct afSearch_ =transitSimu(new AddPokemonBeanSearch((AddPokemonBean) addPk_.getBean()), _simu.getInstance().getBuilder());
        callAddPokemonBeanAbilitySet(afSearch_,A_SIM_2);
        callAddPokemonBeanLevelSet(afSearch_,40);
        genderSetPl(afSearch_);
        return afSearch_;
    }

    protected static PokemonBeanStruct pkPlayerSelectPkNameAdded(String _name) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
//        Struct addPk_ = goToAddPkPlayer(pk_, all_, mapping_, simu_);
//        callAddPokemonBeanTypedNameSet(addPk_,_name);
//        Struct afSearch_ = transitSimu(pk_, all_, mapping_, new AddPokemonBeanSearch(), addPk_);
        PokemonBeanStruct first_ = pkPlAb(_name, simu_);
        return transitSimu(new AddPokemonBeanAdd((AddPokemonBean) first_.getBean()), simu_.getInstance().getBuilder());
    }

    protected static PokemonBeanStruct pkPlayerSelectPkNameQuickAdded() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        PokemonBeanStruct addPk_ = goToAddPkPlayer(simu_);
//        callAddPokemonBeanTypedNameSet(addPk_,_name);
//        Struct afSearch_ = transitSimu(pk_, all_, mapping_, new AddPokemonBeanSearch(), addPk_);
        return transitSimu(new AddPokemonBeanAdd((AddPokemonBean) addPk_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct pkPlayerSelectPkName(String _name) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        PokemonBeanStruct addPk_ = goToAddPkPlayer(simu_);
        callAddPokemonBeanTypedNameSet(addPk_,_name);
        return transitSimu(new AddPokemonBeanSearch((AddPokemonBean) addPk_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct pkPlayerSelectPkHasEvo(String _name) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        PokemonBeanStruct addPk_ = goToAddPkPlayer(simu_);
        callAddPokemonBeanHasEvoSet(addPk_,_name);
        return transitSimu(new AddPokemonBeanSearch((AddPokemonBean) addPk_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct pkPlayerSelectPkIsEvo(String _name) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        PokemonBeanStruct addPk_ = goToAddPkPlayer(simu_);
        callAddPokemonBeanIsEvoSet(addPk_,_name);
        return transitSimu(new AddPokemonBeanSearch((AddPokemonBean) addPk_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct pkPlayerSelectPkIsLeg(String _name) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        PokemonBeanStruct addPk_ = goToAddPkPlayer(simu_);
        callAddPokemonBeanIsLegSet(addPk_,_name);
        return transitSimu(new AddPokemonBeanSearch((AddPokemonBean) addPk_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct pkPlayerSelectPkRow(int _row) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        PokemonBeanStruct addPk_ = goToAddPkPlayer(simu_);
        callAddPokemonBeanTypedNameSet(addPk_,DataBase.EMPTY_STRING);
        PokemonBeanStruct rSe_ =transitSimu(new AddPokemonBeanSearch((AddPokemonBean) addPk_.getBean()), simu_.getInstance().getBuilder());
        AddPokemonBean add_ = (AddPokemonBean) rSe_.getBean();
        return transitSimu(new AddPokemonBeanClickLink(add_, add_.getPokedex().get(_row).getName()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct pkPlayerSelectPkType(String _type, boolean _wholeWord) {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        PokemonBeanStruct addPk_ = goToAddPkPlayer(simu_);
        callAddPokemonBeanTypedTypeSet(addPk_,_type);
        callAddPokemonBeanWholeWordSet(addPk_,_wholeWord);
        return transitSimu(new AddPokemonBeanSearch((AddPokemonBean) addPk_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct pkPlayer() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSample(simu_);
        return goToAddPkPlayer(simu_);
    }

    private static void foeTeamsSample(PokemonBeanStruct _simu) {
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
        transitSimu(new SimulationBeanValidateFoeChoiceFree((SimulationBean) _simu.getBean()), _simu.getInstance().getBuilder());
    }

    private static void foeTeamSample(PokemonBeanStruct _simu) {
        selectTeam(_simu,0);
        pkTrainerSelectPkNameCycle(false, P_POK_00_TR, A_SIM_1_TR, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_01_TR, A_SIM_1_TR, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_02_TR, A_SIM_1_TR, _simu, 4);
        pkTrainerSelectPkNameCycle(false, P_POK_03_TR, A_SIM_1_TR, _simu, 4);
        setMult(_simu,2);
        transitSimu(new SimulationBeanValidateFoeChoiceFree((SimulationBean) _simu.getBean()), _simu.getInstance().getBuilder());
    }

    private static void foeTeamsSampleInv(PokemonBeanStruct _simu) {
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
        transitSimu(new SimulationBeanValidateFoeChoiceFree((SimulationBean) _simu.getBean()), _simu.getInstance().getBuilder());
    }
    private static void setMult(PokemonBeanStruct _simu, int _value) {
        callSimulationBeanEnvironmentSet(_simu, EnvironmentType.ROAD.getEnvName());
        callSimulationBeanMultiplicitySet(_simu,_value);
        assertSame(_simu, transitSimu(new SimulationBeanValidateMultiplicityEnvAction((SimulationBean) _simu.getBean()), _simu.getInstance().getBuilder()));
//        beforeDisplaying(_simu);
    }

    private static PokemonBeanStruct goToSelectPk(PokemonBeanStruct _struct) {
        return transitSimu(new EditTrainerPokemonBeanChooseName((EditTrainerPokemonBean) _struct.getBean()), _struct.getInstance().getBuilder());
    }
    private static PokemonBeanStruct goToSelectAb(PokemonBeanStruct _struct) {
        return transitSimu(new EditTrainerPokemonBeanChooseAbility((EditTrainerPokemonBean) _struct.getBean()), _struct.getInstance().getBuilder());
    }
    private static PokemonBeanStruct goToSelectIt(PokemonBeanStruct _struct) {
        return transitSimu(new EditTrainerPokemonBeanChooseItem((EditTrainerPokemonBean) _struct.getBean()), _struct.getInstance().getBuilder());
    }
    //    private static NaSt goToSelectItPlayer(PkData _pk, StringMap<NaSt> _all, StringMap<String> _mapping, NaSt _struct) {
//        return transitSimu(_pk, _all, _mapping, new EditPokemonBeanChooseItem(), _struct);
//    }
    private static PokemonBeanStruct goToSetMovesTrainer(PokemonBeanStruct _struct) {
        return transitSimu(new EditTrainerPokemonBeanAddMoves((EditTrainerPokemonBean) _struct.getBean()), _struct.getInstance().getBuilder());
    }
    private static PokemonBeanStruct simu(int _nbTeam, PokemonBeanStruct _simu) {
        PokemonBeanStruct simu_ = init(_nbTeam, _simu);
        SimulationBean si_ = ((SimulationBean) simu_.getBean());
        si_.getForm().getRateWinningExpPtsFight().valueRate(si_.getDifficultyCommon().getRateWinningExpPtsFight());
        si_.getForm().getWinTrainerExp().valueRate(si_.getDifficultyCommon().getWinTrainerExp());
        si_.getForm().getRateWinMoneyBase().valueRate(si_.getDifficultyCommon().getRateWinMoneyBase());
        si_.getForm().getRateLooseMoneyWin().valueRate(si_.getDifficultyCommon().getRateLooseMoneyWin());
        si_.getForm().getWinPointsFight().setupValue(si_.getDifficultyCommon().getDiffWinningExpPtsFight());
        si_.getForm().getDamageRatePlayer().setupValue(si_.getDifficultyCommon().getDamageRatePlayer());
        si_.getForm().getDamageRateLawFoe().setupValue(si_.getDifficultyCommon().getDamageRateLawFoe());
        si_.getForm().getEnabledClosing().setSelected(si_.getDifficultyCommon().getEnabledClosing());
        si_.getForm().getAllowCatchingKo().setSelected(si_.getDifficultyCommon().getAllowCatchingKo());
        si_.getForm().getAllowedSwitchPlacesEndRound().setSelected(si_.getDifficultyCommon().getAllowedSwitchPlacesEndRound());
        si_.getForm().getSkipLearningMovesWhileNotGrowingLevel().setSelected(si_.getDifficultyCommon().getSkipLearningMovesWhileNotGrowingLevel());
        si_.getForm().getStillPossibleFlee().setSelected(si_.getDifficultyCommon().getStillPossibleFlee());
        si_.getForm().getRandomWildFight().setSelected(si_.getDifficultyCommon().getRandomWildFight());
        si_.getForm().getEndFightIfOneTeamKo().setSelected(si_.getDifficultyCommon().getEndFightIfOneTeamKo());
        si_.getForm().getRestoredMovesEndFight().setSelected(si_.getDifficultyCommon().getRestoredMovesEndFight());
        si_.getForm().getIvPlayer().valueLong(si_.getDifficultyCommon().getIvPlayer());
        si_.getForm().getIvFoe().valueLong(si_.getDifficultyCommon().getIvFoe());
        si_.getNbTeamsField().valueLong(si_.getNbTeams());
        transitSimu(new SimulationBeanValidateDiffChoice(si_, si_.getForm()), simu_.getInstance().getBuilder());
        return simu_;
    }

    protected static PokemonBeanStruct goToAddPkTrainer(PokemonBeanStruct _simu) {
        return transitSimu(new SimulationBeanAddPkTrainer((SimulationBean) _simu.getBean()), _simu.getInstance().getBuilder());
    }

    protected static PokemonBeanStruct goToAddPkPlayer(PokemonBeanStruct _simu) {
        return transitSimu(new SimulationBeanAdd((SimulationBean) _simu.getBean()), _simu.getInstance().getBuilder());
    }

//    public static NaSt transitSimu(PokemonStandards _stds, StringMap<NaSt> _all, StringMap<String> _mapping, NatCaller _caller, NaSt _first, long... _args) {
//        String url_ = navigateData(_caller, _first, _args);
//        NaSt dest_ = _all.getVal(_mapping.getVal(url_));
//        setFormsBy(_stds,dest_,_first);
//        CommonBean s_ = (CommonBean) ((BeanStruct) dest_).getBean();
//        s_.build(_stds.getDataBase(), s_.getForms());
//        return dest_;
//    }

    public static PokemonBeanStruct transitSimu(IntBeanAction _caller, IntBeanBuilderHelper _builder) {
        String url_ = _caller.actionBean();
        _builder.build(url_);
//        NaSt dest_ = _all.getVal(_mapping.getVal(url_));
//        setFormsBy((CommonBean) ((PokemonBeanStruct)dest_).getBean(),_caller.getBean());
//        CommonBean s_ = (CommonBean) ((BeanStruct) dest_).getBean();
//        s_.build(_stds.getDataBase(), s_.getForms());
        return new PokemonBeanStruct((CommonBean) _builder.getRenders().getVal(url_));// _all.getVal(_mapping.getVal(url_));
    }
    protected static Rate integration() {
        PkData pk_ = pkDataByFacade(db());
        PokemonBeanStruct simu_ = init(2, beanToSimu(pk_.getDataBase()));
//        ((SimulationBean)((PokemonBeanStruct)simu_).getBean()).getDifficultyCommon().setWinTrainerExp(Rate.newRate("5/7"));
        ((SimulationBean) simu_.getBean()).getForm().getWinTrainerExp().valueRate(Rate.newRate("5/7"));
//        ((DifficultyCommonBean)((PokemonBeanStruct)all_.getVal(AikiBeansGameStd.BEAN_DIFFICULTY_COMMON)).getBean()).setWinTrainerExp(Rate.newRate("5/7"));
//        callRate(new DifficultyCommonBeanWinTrainerExpSet(),all_.getVal(AikiBeansGameStd.BEAN_DIFFICULTY_COMMON),Rate.newRate("5/7"));
        PokemonBeanStruct result_ =transitSimu(new SimulationBeanValidateDiffChoice((SimulationBean) simu_.getBean(), ((SimulationBean) simu_.getBean()).getForm()), simu_.getInstance().getBuilder());
        return ((SimulationBean) result_.getBean()).getDifficultyCommon().getWinTrainerExp();
    }

    //    public static NaSt transitSimuRem(PokemonStandards _stds, NatCaller _caller, NaSt _first, long... _args) {
//        String url_ = navigateData(_caller, _first, _args);
//        assertTrue(url_.isEmpty());
////        Struct dest_ = _all.getVal(_mapping.getVal(url_));
//        setFormsBy(_stds, _first,_first);
//        beforeDisplaying(_first);
//        return _first;
//    }
    public static PokemonBeanStruct beanToSimu(FacadeGame _facade) {
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
        return new PokemonBeanStruct(simu_);
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

    protected static PokemonBeanStruct pkPlayerValidateEvosSelectTwo() {
        PkData pk_ = pkDataByFacade(dbLight());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        return evolutions(simu_);
    }


    protected static PokemonBeanStruct pkPlayerValidateEvosSelectTree() {
        PkData pk_ = pkDataByFacade(dbLightSec());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        return evolutionsTree(simu_);
    }

    protected static PokemonBeanStruct pkPlayerValidateEvosSelectTwoOnce() {
        PkData pk_ = pkDataByFacade(dbLight());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        evolutions(simu_);
        callSimulationBeanChosenEvoSet(simu_,P_POK_01);
        callSimulationBeanLevelEvoSet(simu_,41);
        return transitSimu(new SimulationBeanValidateEvo(((SimulationBean) simu_.getBean())), simu_.getInstance().getBuilder());
    }

    protected static PokemonBeanStruct pkPlayerValidateEvosSelectTwoTwice() {
        PkData pk_ = pkDataByFacade(dbLight());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        evolutions(simu_);
        callSimulationBeanChosenEvoSet(simu_,P_POK_01);
        callSimulationBeanLevelEvoSet(simu_,41);
        transitSimu(new SimulationBeanValidateEvo(((SimulationBean) simu_.getBean())), simu_.getInstance().getBuilder());
        callSimulationBeanChosenEvoSet(simu_,P_POK_02);
        callSimulationBeanLevelEvoSet(simu_,41);
        return transitSimu(new SimulationBeanValidateEvo(((SimulationBean) simu_.getBean())), simu_.getInstance().getBuilder());
    }

    protected static PokemonBeanStruct pkPlayerValidateEvosSelectTwoThreeTimes() {
        PkData pk_ = pkDataByFacade(dbLight());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        evolutions(simu_);
        callSimulationBeanChosenEvoSet(simu_,P_POK_01);
        callSimulationBeanLevelEvoSet(simu_,41);
        transitSimu(new SimulationBeanValidateEvo(((SimulationBean) simu_.getBean())), simu_.getInstance().getBuilder());
        callSimulationBeanChosenEvoSet(simu_,P_POK_02);
        callSimulationBeanLevelEvoSet(simu_,41);
        transitSimu(new SimulationBeanValidateEvo(((SimulationBean) simu_.getBean())), simu_.getInstance().getBuilder());
        callSimulationBeanChosenEvoSet(simu_,P_POK_03);
        callSimulationBeanLevelEvoSet(simu_,41);
        return transitSimu(new SimulationBeanValidateEvo(((SimulationBean) simu_.getBean())), simu_.getInstance().getBuilder());
    }

    private static PokemonBeanStruct evolutions(PokemonBeanStruct _simu) {
        foeTeamsSampleLight(_simu);
        playerTeamSampleLight(_simu);
//        callSimulationBeanSelectedPkSet(_simu,0);
        return transitSimu(new SimulationBeanSelectPkEvosValidation(((SimulationBean) _simu.getBean()), 0), _simu.getInstance().getBuilder());
    }

    private static PokemonBeanStruct evolutionsTree(PokemonBeanStruct _simu) {
        foeTeamsSampleLightTree(_simu);
        playerTeamSampleLight(_simu);
//        callSimulationBeanSelectedPkSet(_simu,0);
        return transitSimu(new SimulationBeanSelectPkEvosValidation(((SimulationBean) _simu.getBean()), 0), _simu.getInstance().getBuilder());
    }

    private static void foeTeamsSampleLight(PokemonBeanStruct _simu) {
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
        transitSimu(new SimulationBeanValidateFoeChoiceFree((SimulationBean) _simu.getBean()), _simu.getInstance().getBuilder());
    }


    private static void foeTeamsSampleLightTree(PokemonBeanStruct _simu) {
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
        transitSimu(new SimulationBeanValidateFoeChoiceFree((SimulationBean) _simu.getBean()), _simu.getInstance().getBuilder());
    }

    private static PokemonBeanStruct playerTeamSampleLight(PokemonBeanStruct _simu) {
        pkTrainerSelectPkPlayerNameCycle(P_POK_00_TR,A_SIM_1, _simu,41);
        return transitSimu(new SimulationBeanValidateTeam((SimulationBean) _simu.getBean()), _simu.getInstance().getBuilder());
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

    protected static PokemonBeanStruct pkPlayerFighterSimulateAfterFightOneLight() {
        PkData pk_ = pkDataByFacade(dbLightThree());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSampleVeryLight(simu_);
        playerTeamSampleLightSkip(simu_);
        fighterPositionsLight(simu_);
        PokemonBeanStruct light_ = moveChoicesLight(simu_);
        transitSimu(new SimulationBeanSimulateFight((SimulationBean) light_.getBean()), simu_.getInstance().getBuilder());
        transitSimu(new SimulationBeanNextFight((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
//        callSimulationBeanSelectedPkSet(simu_,0);
        return transitSimu(new SimulationBeanSelectPkEvosAfterValidation((SimulationBean) simu_.getBean(), 0), simu_.getInstance().getBuilder());
    }

    private static PokemonBeanStruct moveChoicesLight(PokemonBeanStruct _simu) {
        return moveChoice(0,0,0,0, _simu);
    }
    private static void foeTeamsSampleVeryLight(PokemonBeanStruct _simu) {
        selectTeam(_simu,0);
        pkTrainerSelectPkNameCycle(false, P_POK_03_TR, A_SIM_1_TR, _simu, 4);
        selectTeam(_simu,1);
        pkTrainerSelectPkNameCycle(false,P_POK_03_TR,A_SIM_2_TR, _simu, 5);
        transitSimu(new SimulationBeanValidateFoeChoiceFree((SimulationBean) _simu.getBean()), _simu.getInstance().getBuilder());
    }

    private static PokemonBeanStruct playerTeamSampleLightSkip(PokemonBeanStruct _simu) {
        pkTrainerSelectPkPlayerNameCycle(P_POK_00_TR,A_SIM_1, _simu,41);
        return transitSimu(new SimulationBeanValidateFoeChoiceSkipEvolutions(((SimulationBean) _simu.getBean())), _simu.getInstance().getBuilder());
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

    private static PokemonBeanStruct fighterPositionsLight(PokemonBeanStruct _simu) {
        changeFighterPosition(_simu,0,0,0);
        return transitSimu(new SimulationBeanValidateFrontFighters((SimulationBean) _simu.getBean()), _simu.getInstance().getBuilder());
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
    protected static PokemonBeanStruct chooseTrainer() {
        PkData pk_ = pkDataByFacade(dbFull());
        return simBean(0, pk_.getDataBase());
    }
    protected static PokemonBeanStruct chooseTrainerLevel(int _place, int _level) {
        PkData pk_ = pkDataByFacade(dbFull());
        PokemonBeanStruct simu_ = simBean(0, pk_.getDataBase());
        return transitSimu(new SimulationBeanClickLevel(((SimulationBean) simu_.getBean()), _place, _level), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct chooseTrainerLevelZero(int _place) {
        PkData pk_ = pkDataByFacade(dbFull());
        PokemonBeanStruct simu_ = simBean(0, pk_.getDataBase());
        return transitSimu(new SimulationBeanClickLevel((SimulationBean) simu_.getBean(), _place, 0), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct chooseTrainerLevel(int _level, int _noFight, int _tile) {
        PkData pk_ = pkDataByFacade(dbFull());
        PokemonBeanStruct simu_ = simBean(0, pk_.getDataBase());
        PokemonBeanStruct sel_ =transitSimu(new SimulationBeanClickLevel(((SimulationBean) simu_.getBean()), 2, _level), simu_.getInstance().getBuilder());
        callSimulationLevelBeanNoFightSet(sel_,_noFight);
        assertSame(sel_,transitSimu(new SimulationLevelBeanValidateNoFightAction((SimulationLevelBean) sel_.getBean()), simu_.getInstance().getBuilder()));
        return transitSimu(new SimulationLevelBeanClickTile((SimulationLevelBean) sel_.getBean(), _tile), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct chooseTrainerLevelZero(int _place, int _noFight, int _tile) {
        PkData pk_ = pkDataByFacade(dbFull());
        PokemonBeanStruct simu_ = simBean(0, pk_.getDataBase());
        PokemonBeanStruct sel_ =transitSimu(new SimulationBeanClickLevel((SimulationBean) simu_.getBean(), _place, 0), simu_.getInstance().getBuilder());
        callSimulationLevelBeanNoFightSet(sel_,_noFight);
        assertSame(sel_,transitSimu(new SimulationLevelBeanValidateNoFightAction((SimulationLevelBean) sel_.getBean()), simu_.getInstance().getBuilder()));
        return transitSimu(new SimulationLevelBeanClickTile((SimulationLevelBean) sel_.getBean(), _tile), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct chooseTrainerLevelDualValidate() {
        PkData pk_ = pkDataByFacade(dbFull());
        PokemonBeanStruct simu_ = simBean(0, pk_.getDataBase());
        PokemonBeanStruct sel_ =transitSimu(new SimulationBeanClickLevel(((SimulationBean) simu_.getBean()), 2, 0), simu_.getInstance().getBuilder());
        callSimulationLevelBeanNoFightSet(sel_,0);
        assertSame(sel_,transitSimu(new SimulationLevelBeanValidateNoFightAction((SimulationLevelBean) sel_.getBean()), simu_.getInstance().getBuilder()));
        transitSimu(new SimulationLevelBeanClickTile((SimulationLevelBean) sel_.getBean(), 1), simu_.getInstance().getBuilder());
        return transitSimu(new SimulationBeanValidateFoeChoice((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct chooseTrainerLevelDualValidateKo() {
        PkData pk_ = pkDataByFacade(dbFull());
        PokemonBeanStruct simu_ = simBean(0, pk_.getDataBase());
        return transitSimu(new SimulationBeanValidateFoeChoice((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
    }
    protected static PokemonBeanStruct simuLeagueReal() {
        PkData pk_ = pkDataByFacade(dbFull());
        PokemonBeanStruct simu_ = simBean(0, pk_.getDataBase());
        transitSimu(new SimulationBeanClickLevel(((SimulationBean) simu_.getBean()), 3, 0), simu_.getInstance().getBuilder());
        transitSimu(new SimulationBeanValidateFoeChoice((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
        simpleTeam(simu_);
        return simu_;
    }
    protected static PokemonBeanStruct simuLeagueRealSec() {
        PkData pk_ = pkDataByFacade(dbFull());
        PokemonBeanStruct simu_ = simBean(0, pk_.getDataBase());
        transitSimu(new SimulationBeanClickLevel(((SimulationBean) simu_.getBean()), 3, 1), simu_.getInstance().getBuilder());
        transitSimu(new SimulationBeanValidateFoeChoice((SimulationBean) simu_.getBean()), simu_.getInstance().getBuilder());
        simpleTeamLight(simu_);
        return simu_;
    }

    private static PokemonBeanStruct simBean(int _n, FacadeGame _facade) {
        return simu(_n, beanToSimu(_facade));
    }

    protected static PokemonBeanStruct simuLeagueVirtual() {
        PkData pk_ = pkDataByFacade(dbFull());
        PokemonBeanStruct simu_ = simBean(2, pk_.getDataBase());
        foeTeamsSampleSec(simu_);
        simpleTeam(simu_);
        return simu_;
    }
    private static void foeTeamsSampleSec(PokemonBeanStruct _simu) {
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
        transitSimu(new SimulationBeanValidateFoeChoiceFree((SimulationBean) _simu.getBean()), _simu.getInstance().getBuilder());
    }
    private static void simpleTeam(PokemonBeanStruct _simu) {
        pk(_simu,0);
        pk(_simu,1);
        transitSimu(new SimulationBeanValidateFoeChoiceSkipEvolutions(((SimulationBean) _simu.getBean())), _simu.getInstance().getBuilder());
        changeFighterPosition(_simu,0,0,0);
        changeFighterPosition(_simu,1,0,1);
        changeFighterPosition(_simu,0,1,0);
        changeFighterPosition(_simu,1,1,1);
        transitSimu(new SimulationBeanValidateFrontFighters((SimulationBean) _simu.getBean()), _simu.getInstance().getBuilder());
        moveChoice(0,0,0,0, _simu);
        moveChoice(1,0,0,1, _simu);
        moveChoice(0,1,0,0, _simu);
        moveChoice(1,1,0,1, _simu);
        transitSimu(new SimulationBeanSimulateFight((SimulationBean) _simu.getBean()), _simu.getInstance().getBuilder());
        transitSimu(new SimulationBeanNextFight((SimulationBean) _simu.getBean()), _simu.getInstance().getBuilder());
        transitSimu(new SimulationBeanValidateMovesAfterFight((SimulationBean) _simu.getBean()), _simu.getInstance().getBuilder());
        transitSimu(new SimulationBeanValidateFoeChoiceSkipEvolutions(((SimulationBean) _simu.getBean())), _simu.getInstance().getBuilder());
        changeFighterPosition(_simu,0,0,0);
        changeFighterPosition(_simu,1,0,1);
        changeFighterPosition(_simu,0,1,0);
        changeFighterPosition(_simu,1,1,1);
        transitSimu(new SimulationBeanValidateFrontFighters((SimulationBean) _simu.getBean()), _simu.getInstance().getBuilder());
        moveChoice(0,0,0,0, _simu);
        moveChoice(1,0,0,1, _simu);
        moveChoice(0,1,0,0, _simu);
        moveChoice(1,1,0,1, _simu);
        transitSimu(new SimulationBeanSimulateFight((SimulationBean) _simu.getBean()), _simu.getInstance().getBuilder());
    }
    private static void simpleTeamLight(PokemonBeanStruct _simu) {
        pk(_simu,0);
        pk(_simu,1);
        transitSimu(new SimulationBeanValidateFoeChoiceSkipEvolutions(((SimulationBean) _simu.getBean())), _simu.getInstance().getBuilder());
        changeFighterPosition(_simu,0,0,0);
        changeFighterPosition(_simu,1,0,1);
        changeFighterPosition(_simu,0,1,0);
        changeFighterPosition(_simu,1,1,1);
        transitSimu(new SimulationBeanValidateFrontFighters((SimulationBean) _simu.getBean()), _simu.getInstance().getBuilder());
        moveChoice(0,0,0,0, _simu);
        moveChoice(1,0,0,1, _simu);
        moveChoice(0,1,0,0, _simu);
        moveChoice(1,1,0,1, _simu);
        transitSimu(new SimulationBeanSimulateFight((SimulationBean) _simu.getBean()), _simu.getInstance().getBuilder());
    }

    private static void pk(PokemonBeanStruct _simu, int _teamIndex) {
        PokemonBeanStruct editing_ = editPkPlayer(_simu, P_POK_01_TR, A_SIM_1, _teamIndex, 41, TeamCrud.EDIT);
        assertSame(editing_, chooseItemPkPlayer(I_BALL_TR, editing_));
        PokemonBeanStruct re_ = addMovePlayer(M_POK_01_TR, 0, editing_);
        callSelectLineMoveSelectedSet(elt(callEditPokemonBeanMovesGet(re_),0),true);
        PokemonBeanStruct afterDel_ =transitSimu(new EditPokemonBeanDeleteMoves((EditPokemonBean) re_.getBean()), _simu.getInstance().getBuilder());
        PokemonBeanStruct af_ =transitSimu(new EditPokemonBeanEdit((EditPokemonBean) afterDel_.getBean()), _simu.getInstance().getBuilder());
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