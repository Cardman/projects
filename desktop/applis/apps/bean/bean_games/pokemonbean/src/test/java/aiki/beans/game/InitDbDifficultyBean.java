package aiki.beans.game;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.CommonBean;
import aiki.beans.InitDbBean;
import aiki.beans.PkDiff;
import aiki.facade.FacadeGame;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.maths.Rate;
import code.scripts.confs.PkScriptPagesInit;

public abstract class InitDbDifficultyBean extends InitDbBean {
    public static String navigateDiffChange(Struct _str, long... _args) {
        return navigateDiff(new DifficultyBeanChange(), CommonBean.DEST_WEB_GAME_HTML_DIFFICULTY_HTML,"",_str,_args);
    }

    public static String navigateDiff(NatCaller _caller, String _url, String _concat, Struct _str, long... _args) {
        return navigate(_caller,_url, PkScriptPagesInit.initConfDiff(new Configuration()),_concat,_str,_args);
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
        return BeanPokemonCommonTs.callLongs(new DifficultyBeanDamageRatePlayerTableGet(),_str,_args);
    }

    public static Struct callDifficultyBeanDamageRateFoeTableGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyBeanDamageRateFoeTableGet(),_str,_args);
    }

    public static Struct callDifficultyBeanDamageRatesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyBeanDamageRatesGet(),_str,_args);
    }

    public static Struct callDifficultyBeanWinPointsFightGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyBeanWinPointsFightGet(),_str,_args);
    }

    public static Struct callDifficultyBeanDamageRateLawFoeGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyBeanDamageRateLawFoeGet(),_str,_args);
    }

    public static Struct callDifficultyBeanDamageRatePlayerGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyBeanDamageRatePlayerGet(),_str,_args);
    }

    public static Struct callDifficultyBeanDiffWinningExpPtsFightGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyBeanDiffWinningExpPtsFightGet(),_str,_args);
    }

    public static Struct callDifficultyBeanIvFoeGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyBeanIvFoeGet(),_str,_args);
    }

    public static Struct callDifficultyBeanIvPlayerGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyBeanIvPlayerGet(),_str,_args);
    }

    public static Struct callDifficultyBeanRateLooseMoneyWinGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyBeanRateLooseMoneyWinGet(),_str,_args);
    }

    public static Struct callDifficultyBeanRateWinMoneyBaseGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyBeanRateWinMoneyBaseGet(),_str,_args);
    }

    public static Struct callDifficultyBeanRateWinningExpPtsFightGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyBeanRateWinningExpPtsFightGet(),_str,_args);
    }

    public static Struct callDifficultyBeanWinTrainerExpGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyBeanWinTrainerExpGet(),_str,_args);
    }

    public static Struct callDifficultyBeanSkipLearningMovesWhileNotGrowingLevelGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyBeanSkipLearningMovesWhileNotGrowingLevelGet(),_str,_args);
    }

    public static Struct callDifficultyBeanStillPossibleFleeGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyBeanStillPossibleFleeGet(),_str,_args);
    }

    public static Struct callDifficultyBeanRandomWildFightGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyBeanRandomWildFightGet(),_str,_args);
    }

    public static Struct callDifficultyBeanEnabledClosingGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyBeanEnabledClosingGet(),_str,_args);
    }

    public static Struct callDifficultyBeanRestoredMovesEndFightGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyBeanRestoredMovesEndFightGet(),_str,_args);
    }

    public static Struct callDifficultyBeanAllowedSwitchPlacesEndRoundGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyBeanAllowedSwitchPlacesEndRoundGet(),_str,_args);
    }

    public static Struct callDifficultyBeanEndFightIfOneTeamKoGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyBeanEndFightIfOneTeamKoGet(),_str,_args);
    }

    public static Struct callDifficultyBeanAllowCatchingKoGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyBeanAllowCatchingKoGet(),_str,_args);
    }

    public static Struct beanDiff(String _language, FacadeGame _dataBase) {
        PkDiff stds_ = new PkDiff();
        stds_.setDataBase(_dataBase);
        return stds_.beanDiff(_language);
    }
}
