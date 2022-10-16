package aiki.beans;

import aiki.beans.facade.game.dto.*;
import aiki.beans.game.*;
import aiki.facade.FacadeGame;
import code.bean.nat.*;
import code.expressionlanguage.structs.*;
import code.maths.LgInt;
import code.maths.Rate;
import code.scripts.confs.EquallablePkBeanUtil;
import code.util.Longs;

public abstract class BeanPokemonCommonTs extends EquallablePkBeanUtil {

    public static final String ACCESS_TO_DEFAULT_FILES = "resources_pk/rom/";
    public static final String EN = "en";

    public static Struct callDifficultyBeanSkipLearningMovesWhileNotGrowingLevelGet(Struct _str, long... _args) {
        return callLongs(new DifficultyBeanSkipLearningMovesWhileNotGrowingLevelGet(),_str,_args);
    }

    public static Struct callDifficultyBeanStillPossibleFleeGet(Struct _str, long... _args) {
        return callLongs(new DifficultyBeanStillPossibleFleeGet(),_str,_args);
    }

    public static Struct callDifficultyBeanRandomWildFightGet(Struct _str, long... _args) {
        return callLongs(new DifficultyBeanRandomWildFightGet(),_str,_args);
    }

    public static Struct callDifficultyBeanEnabledClosingGet(Struct _str, long... _args) {
        return callLongs(new DifficultyBeanEnabledClosingGet(),_str,_args);
    }

    public static Struct callDifficultyBeanRestoredMovesEndFightGet(Struct _str, long... _args) {
        return callLongs(new DifficultyBeanRestoredMovesEndFightGet(),_str,_args);
    }

    public static Struct callDifficultyBeanAllowedSwitchPlacesEndRoundGet(Struct _str, long... _args) {
        return callLongs(new DifficultyBeanAllowedSwitchPlacesEndRoundGet(),_str,_args);
    }

    public static Struct callDifficultyBeanEndFightIfOneTeamKoGet(Struct _str, long... _args) {
        return callLongs(new DifficultyBeanEndFightIfOneTeamKoGet(),_str,_args);
    }

    public static Struct callDifficultyBeanAllowCatchingKoGet(Struct _str, long... _args) {
        return callLongs(new DifficultyBeanAllowCatchingKoGet(),_str,_args);
    }
    public static Struct beanDiff(String _language, FacadeGame _dataBase) {
        PkDiff stds_ = new PkDiff();
        stds_.setDataBase(_dataBase);
        return stds_.beanDiff(_language);
    }
    public static Struct callPokemonPlayerBeanNameGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanNameGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanUsedBallCatchingGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanUsedBallCatchingGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanLevelGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanLevelGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanGenderGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanGenderGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanAbilityGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanAbilityGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanItemGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanItemGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanImageGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanImageGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanGetEvo(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanGetEvo(),_str,_args);
    }
    public static Struct callPokemonPlayerBeanEvolutionsGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanEvolutionsGet(),_str,_args);
    }
    public static Struct callStatisticInfoPkPlayerGetEv(Struct _str, long... _args) {
        return callLongs(new StatisticInfoPkPlayerGetEv(),_str,_args);
    }
    public static Struct callStatisticInfoPkPlayerGetIv(Struct _str, long... _args) {
        return callLongs(new StatisticInfoPkPlayerGetIv(),_str,_args);
    }
    public static Struct callStatisticInfoPkPlayerGetName(Struct _str, long... _args) {
        return callLongs(new StatisticInfoPkPlayerGetName(),_str,_args);
    }
    public static Struct callStatisticInfoPkPlayerGetRate(Struct _str, long... _args) {
        return callLongs(new StatisticInfoPkPlayerGetRate(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanStatisticsGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanStatisticsGet(),_str,_args);
    }
    public static Struct callUsesOfMoveGetCurrent(Struct _str, long... _args) {
        return callLongs(new UsesOfMoveGetCurrent(),_str,_args);
    }

    public static Struct callUsesOfMoveGetMax(Struct _str, long... _args) {
        return callLongs(new UsesOfMoveGetMax(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanMovesGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanMovesGet(),_str,_args);
    }


    public static Struct callPokemonPlayerBeanStatusGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanStatusGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanTypesGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanTypesGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanNbStepsTeamLeadGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanNbStepsTeamLeadGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanHappinessGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanHappinessGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanWonExpSinceLastLevelGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanWonExpSinceLastLevelGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanNecessaryPointsNextLevelGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanNecessaryPointsNextLevelGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanFullHpGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanFullHpGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanNicknameGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanNicknameGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanRemainingHpGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanRemainingHpGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanRemainingHpPerCentGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanRemainingHpPerCentGet(),_str,_args);
    }
    public static Struct beanPk(String _language, FacadeGame _dataBase) {
        PkInd stds_ = new PkInd();
        stds_.setDataBase(_dataBase);
        return stds_.beanPk(_language);
    }


    public static Struct callLongs(NatCaller _caller, Struct _str, long... _args) {
        return _caller.re(_str,getLongArray(_args));
    }

    public Struct displaying(Struct _b) {
        beforeDisplaying(_b);
        return _b;
    }

    public static void beforeDisplaying(Struct _bean) {
        ((BeanStruct)_bean).beforeDisplaying();
    }
    public static Struct[] getLongArray(long... _ls){
        return BeanNatCommonLgNames.getLongArray(Longs.newList(_ls)).getInstance();
    }

    public static void assertSizeLongsEq(int _exp, Struct _result, int _index) {
        assertEq(_exp,(((NatArrayStruct)((NatArrayStruct)_result).get(_index)).getLength()));
    }
    public static void assertLongsEq(long _exp, Struct _result, int _index, int _second) {
        assertEq(_exp,((NatArrayStruct)(((NatArrayStruct)_result).get(_index))).get(_second));
    }

    public static void assertEq(String _exp, Struct _result) {
        assertEq(_exp,((StringStruct)_result).getInstance());
    }
    public static void assertEq(LgInt _exp, Struct _result) {
        assertEq(_exp.toNumberString(),((LgIntStruct)_result).getInstance().toNumberString());
        assertTrue(_exp.eq(((LgIntStruct)_result).getInstance()));
    }
    public static void assertEq(Rate _exp, Struct _result) {
        assertEq(_exp.toNumberString(),((RateStruct)_result).getInstance().toNumberString());
        assertTrue(_exp.eq(((RateStruct)_result).getInstance()));
    }
    public static void assertEq(long _exp, Struct _result) {
        assertEq(_exp,(((NumberStruct)_result).longStruct()));
    }
    public static void assertTrue(Struct _value) {
        assertSame(BooleanStruct.of(true),_value);
    }
    public static void assertFalse(Struct _value) {
        assertSame(BooleanStruct.of(false),_value);
    }
    public static void assertSizeEq(int _exp, Struct _result) {
        assertEq(_exp,(((NatArrayStruct)_result).getLength()));
    }
    public static void assertEq(String _exp, Struct _result, int _index) {
        assertEq(_exp,(((NatArrayStruct)_result).get(_index)));
    }
    public static void assertEq(Rate _exp, Struct _result, int _index) {
        assertEq(_exp,(((NatArrayStruct)_result).get(_index)));
    }
    public static void assertEq(long _exp, Struct _result, int _index) {
        assertEq(_exp,(((NatArrayStruct)_result).get(_index)));
    }
    public static Struct elt(Struct _arr, int _index) {
        return ((NatArrayStruct)_arr).get(_index);
    }
    public static Struct first(Struct _arr) {
        return ((PairStruct)_arr).getFirst();
    }
    public static Struct second(Struct _arr) {
        return ((PairStruct)_arr).getSecond();
    }
}
