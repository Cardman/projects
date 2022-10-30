package aiki.beans.game;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.InitDbBean;
import aiki.beans.PkDiff;
import aiki.beans.PokemonBeanStruct;
import aiki.facade.FacadeGame;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.maths.Rate;
import code.scripts.confs.PkScriptPagesInit;

public abstract class InitDbDifficultyBean extends InitDbBean {
    public static String navigateDiffChange(Struct _str, long... _args) {
        return navigateDiff(new DifficultyBeanChange(), "","",_str,_args);
    }

    public static Struct callChange(Struct _str, String _args) {
        callString(new DifficultyCommonBeanDiffWinningExpPtsFightSet(),inner(_str),_args);
        return _str;
    }

    public static String navigateDiff(NatCaller _caller, String _url, String _concat, Struct _str, long... _args) {
        return navigate(_caller,_url, PkScriptPagesInit.initConfDiff(new Configuration()),_concat,_str,_args);
    }

    public static Struct callDifficultyBeanDamageRateLawFoeSet(Struct _str, String _args) {
        return callString(new DifficultyCommonBeanDamageRateLawFoeSet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanDamageRatePlayerSet(Struct _str, String _args) {
        return callString(new DifficultyCommonBeanDamageRatePlayerSet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanDiffWinningExpPtsFightSet(Struct _str, String _args) {
        return callString(new DifficultyCommonBeanDiffWinningExpPtsFightSet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanIvFoeSet(Struct _str, int _args) {
        return callInt(new DifficultyCommonBeanIvFoeSet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanIvPlayerSet(Struct _str, int _args) {
        return callInt(new DifficultyCommonBeanIvPlayerSet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanRateLooseMoneyWinSet(Struct _str, Rate _args) {
        return callRate(new DifficultyCommonBeanRateLooseMoneyWinSet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanRateWinMoneyBaseSet(Struct _str, Rate _args) {
        return callRate(new DifficultyCommonBeanRateWinMoneyBaseSet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanRateWinningExpPtsFightSet(Struct _str, Rate _args) {
        return callRate(new DifficultyCommonBeanRateWinningExpPtsFightSet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanWinTrainerExpSet(Struct _str, Rate _args) {
        return callRate(new DifficultyCommonBeanWinTrainerExpSet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanSkipLearningMovesWhileNotGrowingLevelSet(Struct _str, boolean _args) {
        return callBool(new DifficultyCommonBeanSkipLearningMovesWhileNotGrowingLevelSet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanStillPossibleFleeSet(Struct _str, boolean _args) {
        return callBool(new DifficultyCommonBeanStillPossibleFleeSet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanRandomWildFightSet(Struct _str, boolean _args) {
        return callBool(new DifficultyCommonBeanRandomWildFightSet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanEnabledClosingSet(Struct _str, boolean _args) {
        return callBool(new DifficultyCommonBeanEnabledClosingSet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanRestoredMovesEndFightSet(Struct _str, boolean _args) {
        return callBool(new DifficultyCommonBeanRestoredMovesEndFightSet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanAllowedSwitchPlacesEndRoundSet(Struct _str, boolean _args) {
        return callBool(new DifficultyCommonBeanAllowedSwitchPlacesEndRoundSet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanEndFightIfOneTeamKoSet(Struct _str, boolean _args) {
        return callBool(new DifficultyCommonBeanEndFightIfOneTeamKoSet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanAllowCatchingKoSet(Struct _str, boolean _args) {
        return callBool(new DifficultyCommonBeanAllowCatchingKoSet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanDamageRatePlayerTableGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanDamageRatePlayerTableGet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanDamageRateFoeTableGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanDamageRateFoeTableGet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanDamageRatesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanDamageRatesGet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanWinPointsFightGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanWinPointsFightGet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanDamageRateLawFoeGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanDamageRateLawFoeGet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanDamageRatePlayerGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanDamageRatePlayerGet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanDiffWinningExpPtsFightGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanDiffWinningExpPtsFightGet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanIvFoeGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanIvFoeGet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanIvPlayerGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanIvPlayerGet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanRateLooseMoneyWinGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanRateLooseMoneyWinGet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanRateWinMoneyBaseGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanRateWinMoneyBaseGet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanRateWinningExpPtsFightGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanRateWinningExpPtsFightGet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanWinTrainerExpGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanWinTrainerExpGet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanSkipLearningMovesWhileNotGrowingLevelGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanSkipLearningMovesWhileNotGrowingLevelGet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanStillPossibleFleeGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanStillPossibleFleeGet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanRandomWildFightGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanRandomWildFightGet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanEnabledClosingGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanEnabledClosingGet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanRestoredMovesEndFightGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanRestoredMovesEndFightGet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanAllowedSwitchPlacesEndRoundGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanAllowedSwitchPlacesEndRoundGet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanEndFightIfOneTeamKoGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanEndFightIfOneTeamKoGet(),inner(_str),_args);
    }

    public static Struct callDifficultyBeanAllowCatchingKoGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanAllowCatchingKoGet(),inner(_str),_args);
    }

    private static Struct inner(Struct _str) {
        DifficultyCommonBean b_ = new DifficultyCommonBean();
        b_.setOwner(((DifficultyBean)((PokemonBeanStruct)_str).getInstance()).getDifficultyCommon());
        b_.beforeDisplaying();
        return new PokemonBeanStruct(b_);
    }

    public static Struct beanDiff(String _language, FacadeGame _dataBase) {
        PkDiff stds_ = new PkDiff();
        stds_.setDataBase(_dataBase);
        return stds_.beanDiff(_language);
    }
}
