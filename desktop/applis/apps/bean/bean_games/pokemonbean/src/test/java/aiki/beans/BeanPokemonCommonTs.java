package aiki.beans;

import aiki.beans.facade.game.dto.*;
import aiki.beans.game.*;
import aiki.facade.FacadeGame;
import code.bean.nat.*;
import code.expressionlanguage.structs.*;
import code.formathtml.Configuration;
import code.maths.LgInt;
import code.maths.Rate;
import code.scripts.confs.EquallablePkBeanUtil;
import code.scripts.confs.PkScriptPagesInit;
import code.util.Longs;
import code.util.StringMap;

public abstract class BeanPokemonCommonTs extends EquallablePkBeanUtil {

    public static final String ACCESS_TO_DEFAULT_FILES = "resources_pk/rom/";
    public static final String EN = "en";


    public static Struct callGameProgressionBeanFullFamiliesBaseGet(Struct _str, long... _args) {
        return callLongs(new GameProgressionBeanFullFamiliesBaseGet(),_str,_args);
    }

    public static Struct callGameProgressionBeanNotAtAllFamiliesBaseGet(Struct _str, long... _args) {
        return callLongs(new GameProgressionBeanNotAtAllFamiliesBaseGet(),_str,_args);
    }


    public static Struct callGameProgressionBeanPartialFamiliesBaseNotCaughtGet(Struct _str, long... _args) {
        return callLongs(new GameProgressionBeanPartialFamiliesBaseNotCaughtGet(),_str,_args);
    }
    public static Struct callGameProgressionBeanUnVisitedPlacesGet(Struct _str, long... _args) {
        return callLongs(new GameProgressionBeanUnVisitedPlacesGet(),_str,_args);
    }

    public static Struct callGameProgressionBeanVisitedPlacesGet(Struct _str, long... _args) {
        return callLongs(new GameProgressionBeanVisitedPlacesGet(),_str,_args);
    }

    public static Struct callGameProgressionBeanNbRemainingNotMaxLevelGet(Struct _str, long... _args) {
        return callLongs(new GameProgressionBeanNbRemainingNotMaxLevelGet(),_str,_args);
    }

    public static Struct callGameProgressionBeanNbRemainingNotMaxHappinessGet(Struct _str, long... _args) {
        return callLongs(new GameProgressionBeanNbRemainingNotMaxHappinessGet(),_str,_args);
    }

    public static Struct callGameProgressionBeanNbRemainingEggsGet(Struct _str, long... _args) {
        return callLongs(new GameProgressionBeanNbRemainingEggsGet(),_str,_args);
    }

    public static Struct callGameProgressionBeanMoneyGet(Struct _str, long... _args) {
        return callLongs(new GameProgressionBeanMoneyGet(),_str,_args);
    }

    public static Struct callGameProgressionBeanRemainStepsRepelGet(Struct _str, long... _args) {
        return callLongs(new GameProgressionBeanRemainStepsRepelGet(),_str,_args);
    }

    public static Struct callGameProgressionBeanEndGameImageGet(Struct _str, long... _args) {
        return callLongs(new GameProgressionBeanEndGameImageGet(),_str,_args);
    }

    public static Struct callGameProgressionBeanFinishedGameGet(Struct _str, long... _args) {
        return callLongs(new GameProgressionBeanFinishedGameGet(),_str,_args);
    }

    public static Struct callGameProgressionBeanHeroImageOppositeSexGet(Struct _str, long... _args) {
        return callLongs(new GameProgressionBeanHeroImageOppositeSexGet(),_str,_args);
    }

    public static Struct callGameProgressionBeanHeroImageGet(Struct _str, long... _args) {
        return callLongs(new GameProgressionBeanHeroImageGet(),_str,_args);
    }

    public static Struct callGameProgressionBeanNicknameGet(Struct _str, long... _args) {
        return callLongs(new GameProgressionBeanNicknameGet(),_str,_args);
    }
    public static Struct beanProg(String _language, FacadeGame _dataBase) {
        PkProg stds_ = new PkProg();
        stds_.setDataBase(_dataBase);
        return stds_.initProg(_language);
    }
    public static String navigateDiffChange(Struct _str, long... _args) {
        return navigateDiff(new DifficultyBeanChange(),CommonBean.DEST_WEB_GAME_HTML_DIFFICULTY_HTML,"",_str,_args);
    }

    public static String navigateDiff(NatCaller _caller, String _url, String _concat, Struct _str, long... _args) {
        return navigate(_caller,_url,PkScriptPagesInit.initConfDiff(new Configuration()),_concat,_str,_args);
    }

    public static String navigate(NatCaller _caller, String _url, StringMap<StringMap<String>> _navigation, String _concat, Struct _str, long... _args) {
        Struct res_ = callLongs(_caller, _str, _args);
        return BeanNatCommonLgNames.getString(res_,_url,_navigation,_concat);
    }

    public static Struct callDifficultyBeanDamageRateLawFoeSet(Struct _str, String _args) {
        return callString(new DifficultyBeanDamageRateLawFoeSet(),_str,_args);
    }

    public static Struct callDifficultyBeanDamageRatePlayerSet(Struct _str, String _args) {
        return callString(new DifficultyBeanDamageRatePlayerSet(),_str,_args);
    }

    public static Struct callDifficultyBeanDiffWinningExpPtsFightSet(Struct _str, String _args) {
        return callString(new DifficultyBeanDiffWinningExpPtsFightSet(),_str,_args);
    }

    public static Struct callDifficultyBeanIvFoeSet(Struct _str, int _args) {
        return callInt(new DifficultyBeanIvFoeSet(),_str,_args);
    }

    public static Struct callDifficultyBeanIvPlayerSet(Struct _str, int _args) {
        return callInt(new DifficultyBeanIvPlayerSet(),_str,_args);
    }

    public static Struct callDifficultyBeanRateLooseMoneyWinSet(Struct _str, Rate _args) {
        return callRate(new DifficultyBeanRateLooseMoneyWinSet(),_str,_args);
    }

    public static Struct callDifficultyBeanRateWinMoneyBaseSet(Struct _str, Rate _args) {
        return callRate(new DifficultyBeanRateWinMoneyBaseSet(),_str,_args);
    }

    public static Struct callDifficultyBeanRateWinningExpPtsFightSet(Struct _str, Rate _args) {
        return callRate(new DifficultyBeanRateWinningExpPtsFightSet(),_str,_args);
    }

    public static Struct callDifficultyBeanWinTrainerExpSet(Struct _str, Rate _args) {
        return callRate(new DifficultyBeanWinTrainerExpSet(),_str,_args);
    }

    public static Struct callDifficultyBeanSkipLearningMovesWhileNotGrowingLevelSet(Struct _str, boolean _args) {
        return callBool(new DifficultyBeanSkipLearningMovesWhileNotGrowingLevelSet(),_str,_args);
    }

    public static Struct callDifficultyBeanStillPossibleFleeSet(Struct _str, boolean _args) {
        return callBool(new DifficultyBeanStillPossibleFleeSet(),_str,_args);
    }

    public static Struct callDifficultyBeanRandomWildFightSet(Struct _str, boolean _args) {
        return callBool(new DifficultyBeanRandomWildFightSet(),_str,_args);
    }

    public static Struct callDifficultyBeanEnabledClosingSet(Struct _str, boolean _args) {
        return callBool(new DifficultyBeanEnabledClosingSet(),_str,_args);
    }

    public static Struct callDifficultyBeanRestoredMovesEndFightSet(Struct _str, boolean _args) {
        return callBool(new DifficultyBeanRestoredMovesEndFightSet(),_str,_args);
    }

    public static Struct callDifficultyBeanAllowedSwitchPlacesEndRoundSet(Struct _str, boolean _args) {
        return callBool(new DifficultyBeanAllowedSwitchPlacesEndRoundSet(),_str,_args);
    }

    public static Struct callDifficultyBeanEndFightIfOneTeamKoSet(Struct _str, boolean _args) {
        return callBool(new DifficultyBeanEndFightIfOneTeamKoSet(),_str,_args);
    }

    public static Struct callDifficultyBeanAllowCatchingKoSet(Struct _str, boolean _args) {
        return callBool(new DifficultyBeanAllowCatchingKoSet(),_str,_args);
    }
    public static Struct callDifficultyBeanDamageRatePlayerTableGet(Struct _str, long... _args) {
        return callLongs(new DifficultyBeanDamageRatePlayerTableGet(),_str,_args);
    }

    public static Struct callDifficultyBeanDamageRateFoeTableGet(Struct _str, long... _args) {
        return callLongs(new DifficultyBeanDamageRateFoeTableGet(),_str,_args);
    }

    public static Struct callDifficultyBeanDamageRatesGet(Struct _str, long... _args) {
        return callLongs(new DifficultyBeanDamageRatesGet(),_str,_args);
    }

    public static Struct callDifficultyBeanWinPointsFightGet(Struct _str, long... _args) {
        return callLongs(new DifficultyBeanWinPointsFightGet(),_str,_args);
    }

    public static Struct callDifficultyBeanDamageRateLawFoeGet(Struct _str, long... _args) {
        return callLongs(new DifficultyBeanDamageRateLawFoeGet(),_str,_args);
    }

    public static Struct callDifficultyBeanDamageRatePlayerGet(Struct _str, long... _args) {
        return callLongs(new DifficultyBeanDamageRatePlayerGet(),_str,_args);
    }

    public static Struct callDifficultyBeanDiffWinningExpPtsFightGet(Struct _str, long... _args) {
        return callLongs(new DifficultyBeanDiffWinningExpPtsFightGet(),_str,_args);
    }

    public static Struct callDifficultyBeanIvFoeGet(Struct _str, long... _args) {
        return callLongs(new DifficultyBeanIvFoeGet(),_str,_args);
    }

    public static Struct callDifficultyBeanIvPlayerGet(Struct _str, long... _args) {
        return callLongs(new DifficultyBeanIvPlayerGet(),_str,_args);
    }

    public static Struct callDifficultyBeanRateLooseMoneyWinGet(Struct _str, long... _args) {
        return callLongs(new DifficultyBeanRateLooseMoneyWinGet(),_str,_args);
    }

    public static Struct callDifficultyBeanRateWinMoneyBaseGet(Struct _str, long... _args) {
        return callLongs(new DifficultyBeanRateWinMoneyBaseGet(),_str,_args);
    }

    public static Struct callDifficultyBeanRateWinningExpPtsFightGet(Struct _str, long... _args) {
        return callLongs(new DifficultyBeanRateWinningExpPtsFightGet(),_str,_args);
    }

    public static Struct callDifficultyBeanWinTrainerExpGet(Struct _str, long... _args) {
        return callLongs(new DifficultyBeanWinTrainerExpGet(),_str,_args);
    }

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

    public static Struct callRate(NatCaller _caller, Struct _str, Rate _args) {
        _caller.re(_str,new Struct[]{new RateStruct(_args)});
        return _str;
    }
    public static Struct callBool(NatCaller _caller, Struct _str, boolean _args) {
        _caller.re(_str,new Struct[]{BooleanStruct.of(_args)});
        return _str;
    }
    public static Struct callInt(NatCaller _caller, Struct _str, int _args) {
        _caller.re(_str,new Struct[]{new IntStruct(_args)});
        return _str;
    }
    public static Struct callString(NatCaller _caller, Struct _str, String _args) {
        _caller.re(_str,new Struct[]{new StringStruct(_args)});
        return _str;
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
        assertEq(_exp,((LgIntStruct)_result).getInstance());
    }
    public static void assertEq(Rate _exp, Struct _result) {
        assertEq(_exp,((RateStruct)_result).getInstance());
    }
    public static void assertEq(LgInt _exp, LgInt _result) {
        assertEq(_exp.toNumberString(),_result.toNumberString());
        assertTrue(_exp.eq(_result));
    }
    public static void assertEq(Rate _exp, Rate _result) {
        assertEq(_exp.toNumberString(),_result.toNumberString());
        assertTrue(_exp.eq(_result));
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
