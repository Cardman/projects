package aiki.beans.game;

import aiki.beans.*;
import aiki.beans.simulation.SimulationBean;
import aiki.facade.FacadeGame;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
//import code.formathtml.Configuration;
import code.maths.Rate;
import code.scripts.pages.aiki.MessagesDataSimulation;
import code.scripts.pages.aiki.MessagesPkBean;
import code.sml.util.Translations;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsFile;
import code.sml.util.TranslationsLg;
import code.util.*;

public abstract class InitDbDifficultyBean extends InitDbBean {
//    public static String navigateDiffChange(NaSt _str, long... _args) {
//        return navigateDiff(new DifficultyBeanChange(), "",_str,_args);
//    }
//
//    public static NaSt callChange(NaSt _str, String _args) {
//        callString(new DifficultyCommonBeanDiffWinningExpPtsFightSet(),inner(_str),_args);
//        return _str;
//    }

//    public static String navigateDiff(NatCaller _caller, String _concat, NaSt _str, long... _args) {
//        PkScriptPagesInit.initConfDiff(new NatConfigurationCore());
//        return navigate(_caller, _concat,_str,_args);
//    }

//    public static NaSt callDifficultyBeanDamageRateLawFoeSet(NaSt _str, String _args) {
//        return callString(new DifficultyCommonBeanDamageRateLawFoeSet(),inner(_str),_args);
//    }
//
//    public static NaSt callDifficultyBeanDamageRatePlayerSet(NaSt _str, String _args) {
//        return callString(new DifficultyCommonBeanDamageRatePlayerSet(),inner(_str),_args);
//    }
//
//    public static NaSt callDifficultyBeanDiffWinningExpPtsFightSet(NaSt _str, String _args) {
//        return callString(new DifficultyCommonBeanDiffWinningExpPtsFightSet(),inner(_str),_args);
//    }
//
//    public static NaSt callDifficultyBeanIvFoeSet(NaSt _str, int _args) {
//        return callInt(new DifficultyCommonBeanIvFoeSet(),inner(_str),_args);
//    }
//
//    public static NaSt callDifficultyBeanIvPlayerSet(NaSt _str, int _args) {
//        return callInt(new DifficultyCommonBeanIvPlayerSet(),inner(_str),_args);
//    }
//
//    public static NaSt callDifficultyBeanRateLooseMoneyWinSet(NaSt _str, Rate _args) {
//        return callRate(new DifficultyCommonBeanRateLooseMoneyWinSet(),inner(_str),_args);
//    }
//
//    public static NaSt callDifficultyBeanRateWinMoneyBaseSet(NaSt _str, Rate _args) {
//        return callRate(new DifficultyCommonBeanRateWinMoneyBaseSet(),inner(_str),_args);
//    }
//
//    public static NaSt callDifficultyBeanRateWinningExpPtsFightSet(NaSt _str, Rate _args) {
//        return callRate(new DifficultyCommonBeanRateWinningExpPtsFightSet(),inner(_str),_args);
//    }
//
//    public static NaSt callDifficultyBeanWinTrainerExpSet(NaSt _str, Rate _args) {
//        return callRate(new DifficultyCommonBeanWinTrainerExpSet(),inner(_str),_args);
//    }
//
//    public static NaSt callDifficultyBeanSkipLearningMovesWhileNotGrowingLevelSet(NaSt _str, boolean _args) {
//        return callBool(new DifficultyCommonBeanSkipLearningMovesWhileNotGrowingLevelSet(),inner(_str),_args);
//    }
//
//    public static NaSt callDifficultyBeanStillPossibleFleeSet(NaSt _str, boolean _args) {
//        return callBool(new DifficultyCommonBeanStillPossibleFleeSet(),inner(_str),_args);
//    }
//
//    public static NaSt callDifficultyBeanRandomWildFightSet(NaSt _str, boolean _args) {
//        return callBool(new DifficultyCommonBeanRandomWildFightSet(),inner(_str),_args);
//    }
//
//    public static NaSt callDifficultyBeanEnabledClosingSet(NaSt _str, boolean _args) {
//        return callBool(new DifficultyCommonBeanEnabledClosingSet(),inner(_str),_args);
//    }
//
//    public static NaSt callDifficultyBeanRestoredMovesEndFightSet(NaSt _str, boolean _args) {
//        return callBool(new DifficultyCommonBeanRestoredMovesEndFightSet(),inner(_str),_args);
//    }
//
//    public static NaSt callDifficultyBeanAllowedSwitchPlacesEndRoundSet(NaSt _str, boolean _args) {
//        return callBool(new DifficultyCommonBeanAllowedSwitchPlacesEndRoundSet(),inner(_str),_args);
//    }
//
//    public static NaSt callDifficultyBeanEndFightIfOneTeamKoSet(NaSt _str, boolean _args) {
//        return callBool(new DifficultyCommonBeanEndFightIfOneTeamKoSet(),inner(_str),_args);
//    }
//
//    public static NaSt callDifficultyBeanAllowCatchingKoSet(NaSt _str, boolean _args) {
//        return callBool(new DifficultyCommonBeanAllowCatchingKoSet(),inner(_str),_args);
//    }

    public static DifficultyBean callDifficultyBeanSkipLearningMovesWhileNotGrowingLevelSet(DifficultyBean _str, boolean _args) {
        inner(_str).setSkipLearningMovesWhileNotGrowingLevel(_args);
        _str.getForm().getSkipLearningMovesWhileNotGrowingLevel().setSelected(_args);
        return _str;
    }

    public static DifficultyBean callDifficultyBeanStillPossibleFleeSet(DifficultyBean _str, boolean _args) {
        inner(_str).setStillPossibleFlee(_args);
        _str.getForm().getStillPossibleFlee().setSelected(_args);
        return _str;
    }

    public static DifficultyBean callDifficultyBeanRandomWildFightSet(DifficultyBean _str, boolean _args) {
        inner(_str).setRandomWildFight(_args);
        _str.getForm().getRandomWildFight().setSelected(_args);
        return _str;
    }

    public static DifficultyBean callDifficultyBeanEnabledClosingSet(DifficultyBean _str, boolean _args) {
        inner(_str).setEnabledClosing(_args);
        _str.getForm().getEnabledClosing().setSelected(_args);
        return _str;
    }

    public static DifficultyBean callDifficultyBeanRestoredMovesEndFightSet(DifficultyBean _str, boolean _args) {
        inner(_str).setRestoredMovesEndFight(_args);
        _str.getForm().getRestoredMovesEndFight().setSelected(_args);
        return _str;
    }

    public static DifficultyBean callDifficultyBeanAllowedSwitchPlacesEndRoundSet(DifficultyBean _str, boolean _args) {
        inner(_str).setAllowedSwitchPlacesEndRound(_args);
        _str.getForm().getAllowedSwitchPlacesEndRound().setSelected(_args);
        return _str;
    }

    public static DifficultyBean callDifficultyBeanEndFightIfOneTeamKoSet(DifficultyBean _str, boolean _args) {
        inner(_str).setEndFightIfOneTeamKo(_args);
        _str.getForm().getEndFightIfOneTeamKo().setSelected(_args);
        return _str;
    }

    public static DifficultyBean callDifficultyBeanAllowCatchingKoSet(DifficultyBean _str, boolean _args) {
        inner(_str).setAllowCatchingKo(_args);
        _str.getForm().getAllowCatchingKo().setSelected(_args);
        return _str;
    }

    public static AbsMap<Rate,Rate> callDifficultyBeanDamageRatePlayerTableGet(SimulationBean _str, int... _args) {
        return inner(_str).getDamageRatePlayerTable();
    }

    public static AbsMap<Rate,Rate> callDifficultyBeanDamageRateFoeTableGet(SimulationBean _str, int... _args) {
        return inner(_str).getDamageRateFoeTable();
    }

    public static AbsMap<String,String> callDifficultyBeanDamageRatesGet(SimulationBean _str, int... _args) {
        return inner(_str).getDamageRates();
    }

    public static AbsMap<String,String> callDifficultyBeanWinPointsFightGet(SimulationBean _str, int... _args) {
        return inner(_str).getWinPointsFight();
    }

    public static String callDifficultyBeanDamageRateLawFoeGet(SimulationBean _str, int... _args) {
        return inner(_str).getDamageRateLawFoe();
    }

    public static String callDifficultyBeanDamageRatePlayerGet(SimulationBean _str, int... _args) {
        return inner(_str).getDamageRatePlayer();
    }

    public static String callDifficultyBeanDiffWinningExpPtsFightGet(SimulationBean _str, int... _args) {
        return inner(_str).getDiffWinningExpPtsFight();
    }

    public static long callDifficultyBeanIvFoeGet(SimulationBean _str, int... _args) {
        return inner(_str).getIvFoe();
    }

    public static long callDifficultyBeanIvPlayerGet(SimulationBean _str, int... _args) {
        return inner(_str).getIvPlayer();
    }

    public static Rate callDifficultyBeanRateLooseMoneyWinGet(SimulationBean _str, int... _args) {
        return inner(_str).getRateLooseMoneyWin();
    }

    public static Rate callDifficultyBeanRateWinMoneyBaseGet(SimulationBean _str, int... _args) {
        return inner(_str).getRateWinMoneyBase();
    }

    public static Rate callDifficultyBeanRateWinningExpPtsFightGet(SimulationBean _str, int... _args) {
        return inner(_str).getRateWinningExpPtsFight();
    }

    public static Rate callDifficultyBeanWinTrainerExpGet(SimulationBean _str, int... _args) {
        return inner(_str).getWinTrainerExp();
    }

    public static boolean callDifficultyBeanSkipLearningMovesWhileNotGrowingLevelGet(SimulationBean _str, int... _args) {
        return inner(_str).getSkipLearningMovesWhileNotGrowingLevel();
    }

    public static boolean callDifficultyBeanStillPossibleFleeGet(SimulationBean _str, int... _args) {
        return inner(_str).getStillPossibleFlee();
    }

    public static boolean callDifficultyBeanRandomWildFightGet(SimulationBean _str, int... _args) {
        return inner(_str).getRandomWildFight();
    }

    public static boolean callDifficultyBeanEnabledClosingGet(SimulationBean _str, int... _args) {
        return inner(_str).getEnabledClosing();
    }

    public static boolean callDifficultyBeanRestoredMovesEndFightGet(SimulationBean _str, int... _args) {
        return inner(_str).getRestoredMovesEndFight();
    }

    public static boolean callDifficultyBeanAllowedSwitchPlacesEndRoundGet(SimulationBean _str, int... _args) {
        return inner(_str).getAllowedSwitchPlacesEndRound();
    }

    public static boolean callDifficultyBeanEndFightIfOneTeamKoGet(SimulationBean _str, int... _args) {
        return inner(_str).getEndFightIfOneTeamKo();
    }

    public static boolean callDifficultyBeanAllowCatchingKoGet(SimulationBean _str, int... _args) {
        return inner(_str).getAllowCatchingKo();
    }

    private static DifficultyCommonBean inner(SimulationBean _str) {
        DifficultyCommonBean b_ = new DifficultyCommonBean();
        b_.setOwner(_str);
        b_.beforeDisplaying();
        return b_;
    }
    //
    private static DifficultyCommon inner(DifficultyBean _str) {
//        DifficultyCommonBean b_ = new DifficultyCommonBean();
//        b_.setOwner(_str.getDifficultyCommon());
//        b_.beforeDisplaying();
        return _str.getDifficultyCommon();
    }

    public static SimulationBean beanDiff(String _language, FacadeGame _dataBase) {
        SimulationBean si_ = new SimulationBean();
        MockBeanBuilderHelper bu_ = new MockBeanBuilderHelper();
        Translations tr_ = new Translations();
        TranslationsLg en_ = new TranslationsLg();
        en_.getMapping().addEntry(MessagesPkBean.APP_BEAN_DATA, new TranslationsAppli());
        en_.getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.DIFFICULTY,new TranslationsFile());
        en_.getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.SIMULATION, MessagesDataSimulation.en());
        tr_.getMapping().addEntry(EN, en_);
        TranslationsLg fr_ = new TranslationsLg();
        fr_.getMapping().addEntry(MessagesPkBean.APP_BEAN_DATA, new TranslationsAppli());
        fr_.getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.DIFFICULTY,new TranslationsFile());
        fr_.getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.SIMULATION,MessagesDataSimulation.fr());
        tr_.getMapping().addEntry(FR, fr_);
        bu_.setTranslations(tr_);
        bu_.setFacade(_dataBase);
        si_.setBuilder(bu_);
        _dataBase.setLanguage(_language);
        initBean(si_,_language,_dataBase);
        _dataBase.getData().getTranslatedDiffModelLaw().tryAdd(_language,new IdMap<DifficultyModelLaw, String>());
        _dataBase.getData().getTranslatedDiffWinPts().tryAdd(_language,new IdMap<DifficultyWinPointsFight, String>());
        _dataBase.updateTrs();
        return si_;
    }

    public static SimulationBean beanSimu(String _language, FacadeGame _dataBase) {
        SimulationBean si_ = new SimulationBean();
        si_.setBuilder(new MockBeanBuilderHelper());
//        si_.getDifficultyCommon().
        initBean(si_,_language,_dataBase);
        si_.beforeDisplaying();
        si_.getDifficultyCommon().setRateWinningExpPtsFight(_dataBase.getGame().getDifficulty().getRateWinningExpPtsFight());
        si_.getDifficultyCommon().setWinTrainerExp(_dataBase.getGame().getDifficulty().getWinTrainerExp());
        si_.getDifficultyCommon().setRateWinMoneyBase(_dataBase.getGame().getDifficulty().getRateWinMoneyBase());
        si_.getDifficultyCommon().setRateLooseMoneyWin(_dataBase.getGame().getDifficulty().getRateLooseMoneyWin());
        si_.getDifficultyCommon().setDiffWinningExpPtsFight(_dataBase.getGame().getDifficulty().getDiffWinningExpPtsFight().getWinName());
        si_.getDifficultyCommon().setDamageRatePlayer(_dataBase.getGame().getDifficulty().getDamageRatePlayer().getModelName());
        si_.getDifficultyCommon().setDamageRateLawFoe(_dataBase.getGame().getDifficulty().getDamageRateLawFoe().getModelName());
        si_.getDifficultyCommon().setEnabledClosing(_dataBase.getGame().getDifficulty().getEnabledClosing());
        si_.getDifficultyCommon().setAllowCatchingKo(_dataBase.getGame().getDifficulty().getAllowCatchingKo());
        si_.getDifficultyCommon().setAllowedSwitchPlacesEndRound(_dataBase.getGame().getDifficulty().getAllowedSwitchPlacesEndRound());
        si_.getDifficultyCommon().setSkipLearningMovesWhileNotGrowingLevel(_dataBase.getGame().getDifficulty().isSkipLearningMovesWhileNotGrowingLevel());
        si_.getDifficultyCommon().setStillPossibleFlee(_dataBase.getGame().getDifficulty().getStillPossibleFlee());
        si_.getDifficultyCommon().setRandomWildFight(_dataBase.getGame().getDifficulty().getRandomWildFight());
        si_.getDifficultyCommon().setEndFightIfOneTeamKo(_dataBase.getGame().getDifficulty().getEndFightIfOneTeamKo());
        si_.getDifficultyCommon().setRestoredMovesEndFight(_dataBase.getGame().getDifficulty().getRestoredMovesEndFight());
        si_.getDifficultyCommon().setIvPlayer(_dataBase.getGame().getDifficulty().getIvPlayer());
        si_.getDifficultyCommon().setIvFoe(_dataBase.getGame().getDifficulty().getIvFoe());
        si_.getDifficultyCommon().init(_dataBase.getData(),EN,_dataBase.getGame().getDifficulty());
        return si_;
    }

    public static void initBean(CommonBean _bean, String _lg, FacadeGame _fac) {
        _bean.setDataBase(_fac);
        _bean.setLanguage(_lg);
    }
    public static String navigateDiffChange(DifficultyBean _str, long... _args) {
        _str.getBuilder().getRenders().addEntry("",_str);
        IntBeanAction intAct_ = ((AbsBeanChgSubmit) _str.getUpdateValues()).getEvts().get(0);
        _str.getBuilder().build(intAct_);
        return "";
//        return navigateDiff(new DifficultyBeanChange(), "",_str,_args);
    }
    public static DifficultyBean callChange(DifficultyBean _str, String _args) {
//        inner(_str).setDiffWinningExpPtsFight(_args);
        _str.getForm().getWinPointsFight().setupValue(_args);
        return _str;
    }
    public static DifficultyBean beanDiffDis(String _language, FacadeGame _dataBase) {
        _dataBase.setLanguage(_language);
        DifficultyBean b_ = new DifficultyBean();
        b_.setDataBase(_dataBase);
        b_.setLanguage(_language);
        MockBeanBuilderHelper bu_ = new MockBeanBuilderHelper();
        Translations tr_ = new Translations();
        TranslationsLg en_ = new TranslationsLg();
        en_.getMapping().addEntry(MessagesPkBean.APP_BEAN, MessagesPkBean.en());
        tr_.getMapping().addEntry(EN, en_);
        TranslationsLg fr_ = new TranslationsLg();
        fr_.getMapping().addEntry(MessagesPkBean.APP_BEAN, MessagesPkBean.fr());
        tr_.getMapping().addEntry(FR, fr_);
        bu_.setTranslations(tr_);
        bu_.setFacade(_dataBase);
        b_.setBuilder(bu_);
        b_.build(_dataBase);
        return b_;
    }
}
