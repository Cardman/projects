package aiki.beans.game;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.InitDbBean;
import aiki.beans.PkDiff;
import aiki.beans.PokemonBeanStruct;
import aiki.facade.FacadeGame;
import code.bean.nat.*;
import code.bean.nat.analyze.NatConfigurationCore;
import code.bean.nat.*;
//import code.formathtml.Configuration;
import code.maths.Rate;
import code.scripts.confs.PkScriptPagesInit;

public abstract class InitDbDifficultyBean extends InitDbBean {
    public static String navigateDiffChange(NaSt _str, long... _args) {
        return navigateDiff(new DifficultyBeanChange(), "",_str,_args);
    }

    public static NaSt callChange(NaSt _str, String _args) {
        callString(new DifficultyCommonBeanDiffWinningExpPtsFightSet(),inner(_str),_args);
        return _str;
    }

    public static String navigateDiff(NatCaller _caller, String _concat, NaSt _str, long... _args) {
        PkScriptPagesInit.initConfDiff(new NatConfigurationCore());
        return navigate(_caller, _concat,_str,_args);
    }

    public static NaSt callDifficultyBeanDamageRateLawFoeSet(NaSt _str, String _args) {
        return callString(new DifficultyCommonBeanDamageRateLawFoeSet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanDamageRatePlayerSet(NaSt _str, String _args) {
        return callString(new DifficultyCommonBeanDamageRatePlayerSet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanDiffWinningExpPtsFightSet(NaSt _str, String _args) {
        return callString(new DifficultyCommonBeanDiffWinningExpPtsFightSet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanIvFoeSet(NaSt _str, int _args) {
        return callInt(new DifficultyCommonBeanIvFoeSet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanIvPlayerSet(NaSt _str, int _args) {
        return callInt(new DifficultyCommonBeanIvPlayerSet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanRateLooseMoneyWinSet(NaSt _str, Rate _args) {
        return callRate(new DifficultyCommonBeanRateLooseMoneyWinSet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanRateWinMoneyBaseSet(NaSt _str, Rate _args) {
        return callRate(new DifficultyCommonBeanRateWinMoneyBaseSet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanRateWinningExpPtsFightSet(NaSt _str, Rate _args) {
        return callRate(new DifficultyCommonBeanRateWinningExpPtsFightSet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanWinTrainerExpSet(NaSt _str, Rate _args) {
        return callRate(new DifficultyCommonBeanWinTrainerExpSet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanSkipLearningMovesWhileNotGrowingLevelSet(NaSt _str, boolean _args) {
        return callBool(new DifficultyCommonBeanSkipLearningMovesWhileNotGrowingLevelSet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanStillPossibleFleeSet(NaSt _str, boolean _args) {
        return callBool(new DifficultyCommonBeanStillPossibleFleeSet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanRandomWildFightSet(NaSt _str, boolean _args) {
        return callBool(new DifficultyCommonBeanRandomWildFightSet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanEnabledClosingSet(NaSt _str, boolean _args) {
        return callBool(new DifficultyCommonBeanEnabledClosingSet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanRestoredMovesEndFightSet(NaSt _str, boolean _args) {
        return callBool(new DifficultyCommonBeanRestoredMovesEndFightSet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanAllowedSwitchPlacesEndRoundSet(NaSt _str, boolean _args) {
        return callBool(new DifficultyCommonBeanAllowedSwitchPlacesEndRoundSet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanEndFightIfOneTeamKoSet(NaSt _str, boolean _args) {
        return callBool(new DifficultyCommonBeanEndFightIfOneTeamKoSet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanAllowCatchingKoSet(NaSt _str, boolean _args) {
        return callBool(new DifficultyCommonBeanAllowCatchingKoSet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanDamageRatePlayerTableGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanDamageRatePlayerTableGet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanDamageRateFoeTableGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanDamageRateFoeTableGet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanDamageRatesGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanDamageRatesGet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanWinPointsFightGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanWinPointsFightGet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanDamageRateLawFoeGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanDamageRateLawFoeGet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanDamageRatePlayerGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanDamageRatePlayerGet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanDiffWinningExpPtsFightGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanDiffWinningExpPtsFightGet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanIvFoeGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanIvFoeGet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanIvPlayerGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanIvPlayerGet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanRateLooseMoneyWinGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanRateLooseMoneyWinGet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanRateWinMoneyBaseGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanRateWinMoneyBaseGet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanRateWinningExpPtsFightGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanRateWinningExpPtsFightGet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanWinTrainerExpGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanWinTrainerExpGet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanSkipLearningMovesWhileNotGrowingLevelGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanSkipLearningMovesWhileNotGrowingLevelGet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanStillPossibleFleeGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanStillPossibleFleeGet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanRandomWildFightGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanRandomWildFightGet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanEnabledClosingGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanEnabledClosingGet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanRestoredMovesEndFightGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanRestoredMovesEndFightGet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanAllowedSwitchPlacesEndRoundGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanAllowedSwitchPlacesEndRoundGet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanEndFightIfOneTeamKoGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanEndFightIfOneTeamKoGet(),inner(_str),_args);
    }

    public static NaSt callDifficultyBeanAllowCatchingKoGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new DifficultyCommonBeanAllowCatchingKoGet(),inner(_str),_args);
    }

    private static NaSt inner(NaSt _str) {
        DifficultyCommonBean b_ = new DifficultyCommonBean();
        b_.setOwner(((DifficultyBean)((PokemonBeanStruct)_str).getInstance()).getDifficultyCommon());
        b_.beforeDisplaying();
        return new PokemonBeanStruct(b_);
    }

    public static NaSt beanDiff(String _language, FacadeGame _dataBase) {
        PkDiff stds_ = new PkDiff();
        stds_.setDataBase(_dataBase);
        return stds_.beanDiff(_language);
    }
}
