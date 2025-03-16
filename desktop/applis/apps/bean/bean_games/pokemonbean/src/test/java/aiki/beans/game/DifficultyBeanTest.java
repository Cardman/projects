package aiki.beans.game;

import aiki.beans.*;
import aiki.beans.simulation.SimulationBean;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.Game;
import aiki.game.params.Difficulty;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
import code.maths.Rate;
import org.junit.Test;

public final class DifficultyBeanTest extends InitDbDifficultyBean {

    @Test
    public void getAllowCatchingKo1() {
        FacadeGame fac_ = fac();
        diff(fac_).setAllowCatchingKo(true);
        assertTrue(callDifficultyBeanAllowCatchingKoGet(beanSimu(EN, fac_)));
    }

    @Test
    public void getAllowCatchingKo2() {
        FacadeGame fac_ = fac();
        diff(fac_).setAllowCatchingKo(false);
        assertFalse(callDifficultyBeanAllowCatchingKoGet(beanSimu(EN, fac_)));
    }

    @Test
    public void getEndFightIfOneTeamKo1() {
        FacadeGame fac_ = fac();
        diff(fac_).setEndFightIfOneTeamKo(true);
        assertTrue(callDifficultyBeanEndFightIfOneTeamKoGet(beanSimu(EN, fac_)));
    }

    @Test
    public void getEndFightIfOneTeamKo2() {
        FacadeGame fac_ = fac();
        diff(fac_).setEndFightIfOneTeamKo(false);
        assertFalse(callDifficultyBeanEndFightIfOneTeamKoGet(beanSimu(EN, fac_)));
    }

    @Test
    public void getAllowedSwitchPlacesEndRound1() {
        FacadeGame fac_ = fac();
        diff(fac_).setAllowedSwitchPlacesEndRound(true);
        assertTrue(callDifficultyBeanAllowedSwitchPlacesEndRoundGet(beanSimu(EN, fac_)));
    }

    @Test
    public void getAllowedSwitchPlacesEndRound2() {
        FacadeGame fac_ = fac();
        diff(fac_).setAllowedSwitchPlacesEndRound(false);
        assertFalse(callDifficultyBeanAllowedSwitchPlacesEndRoundGet(beanSimu(EN, fac_)));
    }

    @Test
    public void getRestoredMovesEndFight1() {
        FacadeGame fac_ = fac();
        diff(fac_).setRestoredMovesEndFight(true);
        assertTrue(callDifficultyBeanRestoredMovesEndFightGet(beanSimu(EN, fac_)));
    }

    @Test
    public void getRestoredMovesEndFight2() {
        FacadeGame fac_ = fac();
        diff(fac_).setRestoredMovesEndFight(false);
        assertFalse(callDifficultyBeanRestoredMovesEndFightGet(beanSimu(EN, fac_)));
    }

    @Test
    public void getEnabledClosing1() {
        FacadeGame fac_ = fac();
        diff(fac_).setEnabledClosing(true);
        assertTrue(callDifficultyBeanEnabledClosingGet(beanSimu(EN, fac_)));
    }

    @Test
    public void getEnabledClosing2() {
        FacadeGame fac_ = fac();
        diff(fac_).setEnabledClosing(false);
        assertFalse(callDifficultyBeanEnabledClosingGet(beanSimu(EN, fac_)));
    }

    @Test
    public void getRandomWildFight1() {
        FacadeGame fac_ = fac();
        diff(fac_).setRandomWildFight(true);
        assertTrue(callDifficultyBeanRandomWildFightGet(beanSimu(EN, fac_)));
    }

    @Test
    public void getRandomWildFight2() {
        FacadeGame fac_ = fac();
        diff(fac_).setRandomWildFight(false);
        assertFalse(callDifficultyBeanRandomWildFightGet(beanSimu(EN, fac_)));
    }

    @Test
    public void getStillPossibleFlee1() {
        FacadeGame fac_ = fac();
        diff(fac_).setStillPossibleFlee(true);
        assertTrue(callDifficultyBeanStillPossibleFleeGet(beanSimu(EN, fac_)));
    }

    @Test
    public void getStillPossibleFlee2() {
        FacadeGame fac_ = fac();
        diff(fac_).setStillPossibleFlee(false);
        assertFalse(callDifficultyBeanStillPossibleFleeGet(beanSimu(EN, fac_)));
    }

    @Test
    public void getSkipLearningMovesWhileNotGrowingLevel1() {
        FacadeGame fac_ = fac();
        diff(fac_).setSkipLearningMovesWhileNotGrowingLevel(true);
        assertTrue(callDifficultyBeanSkipLearningMovesWhileNotGrowingLevelGet(beanSimu(EN, fac_)));
    }

    @Test
    public void getSkipLearningMovesWhileNotGrowingLevel2() {
        FacadeGame fac_ = fac();
        diff(fac_).setSkipLearningMovesWhileNotGrowingLevel(false);
        assertFalse(callDifficultyBeanSkipLearningMovesWhileNotGrowingLevelGet(beanSimu(EN, fac_)));
    }

    @Test
    public void getRateLooseMoneyWin() {
        FacadeGame fac_ = fac();
        diff(fac_).setRateLooseMoneyWin(rt());
        assertEq(rt(),callDifficultyBeanRateLooseMoneyWinGet(beanSimu(EN, fac_)));
    }

    @Test
    public void getWinTrainerExp() {
        FacadeGame fac_ = fac();
        diff(fac_).setWinTrainerExp(rt());
        assertEq(rt(),callDifficultyBeanWinTrainerExpGet(beanSimu(EN, fac_)));
    }

    @Test
    public void getRateWinningExpPtsFight() {
        FacadeGame fac_ = fac();
        diff(fac_).setRateWinningExpPtsFight(rt());
        assertEq(rt(),callDifficultyBeanRateWinningExpPtsFightGet(beanSimu(EN, fac_)));
    }

    @Test
    public void getRateWinMoneyBase() {
        FacadeGame fac_ = fac();
        diff(fac_).setRateWinMoneyBase(rt());
        assertEq(rt(),callDifficultyBeanRateWinMoneyBaseGet(beanSimu(EN, fac_)));
    }

    @Test
    public void getIvFoe() {
        FacadeGame fac_ = fac();
        diff(fac_).setIvFoe( 1);
        assertEq(1,callDifficultyBeanIvFoeGet(beanSimu(EN, fac_)));
    }

    @Test
    public void getIvPlayer() {
        FacadeGame fac_ = fac();
        diff(fac_).setIvPlayer( 1);
        assertEq(1,callDifficultyBeanIvPlayerGet(beanSimu(EN, fac_)));
    }

    @Test
    public void getDiffWinningExpPtsFight() {
        FacadeGame fac_ = fac();
        diff(fac_).setDiffWinningExpPtsFight(DifficultyWinPointsFight.FACILE);
        assertEq(DifficultyWinPointsFight.FACILE.getWinName(),callDifficultyBeanDiffWinningExpPtsFightGet(beanSimu(EN, fac_)));
    }

    @Test
    public void getDamageRateLawFoe() {
        FacadeGame fac_ = fac();
        diff(fac_).setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MIN);
        assertEq(DifficultyModelLaw.CONSTANT_MIN.getModelName(),callDifficultyBeanDamageRateLawFoeGet(beanSimu(EN, fac_)));
    }

    @Test
    public void getDamageRatePlayer() {
        FacadeGame fac_ = fac();
        diff(fac_).setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        assertEq(DifficultyModelLaw.CONSTANT_MAX.getModelName(),callDifficultyBeanDamageRatePlayerGet(beanSimu(EN, fac_)));
    }

    @Test
    public void getWinPointsFight1() {
        assertSizeEq(4,callDifficultyBeanWinPointsFightGet(displaying(beanDiff(EN, facTr()))));
    }

    @Test
    public void getWinPointsFight2() {
        assertEq(DifficultyWinPointsFight.TRES_FACILE.getWinName(),firstStrStr(eltStrStr(callDifficultyBeanWinPointsFightGet(displaying(beanDiff(EN, facTr()))),0)));
    }

    @Test
    public void getWinPointsFight3() {
        assertEq("W1",secondStrStr(eltStrStr(callDifficultyBeanWinPointsFightGet(displaying(beanDiff(EN, facTr()))),0)));
    }

    @Test
    public void getWinPointsFight4() {
        assertEq(DifficultyWinPointsFight.FACILE.getWinName(),firstStrStr(eltStrStr(callDifficultyBeanWinPointsFightGet(displaying(beanDiff(EN, facTr()))),1)));
    }

    @Test
    public void getWinPointsFight5() {
        assertEq("W2",secondStrStr(eltStrStr(callDifficultyBeanWinPointsFightGet(displaying(beanDiff(EN, facTr()))),1)));
    }

    @Test
    public void getWinPointsFight6() {
        assertEq(DifficultyWinPointsFight.DIFFICILE.getWinName(),firstStrStr(eltStrStr(callDifficultyBeanWinPointsFightGet(displaying(beanDiff(EN, facTr()))),2)));
    }

    @Test
    public void getWinPointsFight7() {
        assertEq("W3",secondStrStr(eltStrStr(callDifficultyBeanWinPointsFightGet(displaying(beanDiff(EN, facTr()))),2)));
    }

    @Test
    public void getWinPointsFight8() {
        assertEq(DifficultyWinPointsFight.TRES_DIFFICILE.getWinName(),firstStrStr(eltStrStr(callDifficultyBeanWinPointsFightGet(displaying(beanDiff(EN, facTr()))),3)));
    }

    @Test
    public void getWinPointsFight9() {
        assertEq("W4",secondStrStr(eltStrStr(callDifficultyBeanWinPointsFightGet(displaying(beanDiff(EN, facTr()))),3)));
    }

    @Test
    public void getDamageRates1() {
        assertSizeEq(5,callDifficultyBeanDamageRatesGet(displaying(beanDiff(EN, facTr()))));
    }

    @Test
    public void getDamageRates2() {
        assertEq(DifficultyModelLaw.CONSTANT_MIN.getModelName(),firstStrStr(eltStrStr(callDifficultyBeanDamageRatesGet(displaying(beanDiff(EN, facTr()))),0)));
    }

    @Test
    public void getDamageRates3() {
        assertEq("M1",secondStrStr(eltStrStr(callDifficultyBeanDamageRatesGet(displaying(beanDiff(EN, facTr()))),0)));
    }

    @Test
    public void getDamageRates4() {
        assertEq(DifficultyModelLaw.CROISSANT.getModelName(),firstStrStr(eltStrStr(callDifficultyBeanDamageRatesGet(displaying(beanDiff(EN, facTr()))),1)));
    }

    @Test
    public void getDamageRates5() {
        assertEq("M2",secondStrStr(eltStrStr(callDifficultyBeanDamageRatesGet(displaying(beanDiff(EN, facTr()))),1)));
    }

    @Test
    public void getDamageRates6() {
        assertEq(DifficultyModelLaw.UNIFORME.getModelName(),firstStrStr(eltStrStr(callDifficultyBeanDamageRatesGet(displaying(beanDiff(EN, facTr()))),2)));
    }

    @Test
    public void getDamageRates7() {
        assertEq("M3",secondStrStr(eltStrStr(callDifficultyBeanDamageRatesGet(displaying(beanDiff(EN, facTr()))),2)));
    }

    @Test
    public void getDamageRates8() {
        assertEq(DifficultyModelLaw.DECROISSANT.getModelName(),firstStrStr(eltStrStr(callDifficultyBeanDamageRatesGet(displaying(beanDiff(EN, facTr()))),3)));
    }

    @Test
    public void getDamageRates9() {
        assertEq("M4",secondStrStr(eltStrStr(callDifficultyBeanDamageRatesGet(displaying(beanDiff(EN, facTr()))),3)));
    }

    @Test
    public void getDamageRates10() {
        assertEq(DifficultyModelLaw.CONSTANT_MAX.getModelName(),firstStrStr(eltStrStr(callDifficultyBeanDamageRatesGet(displaying(beanDiff(EN, facTr()))),4)));
    }

    @Test
    public void getDamageRates11() {
        assertEq("M5",secondStrStr(eltStrStr(callDifficultyBeanDamageRatesGet(displaying(beanDiff(EN, facTr()))),4)));
    }

    @Test
    public void getDamageRatePlayerTable1() {
        FacadeGame fac_ = facTr();
        diff(fac_).setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MIN);
        assertSizeEq(1,callDifficultyBeanDamageRatePlayerTableGet(beanSimu(EN, fac_)));
    }

    @Test
    public void getDamageRatePlayerTable2() {
        FacadeGame fac_ = facTr();
        diff(fac_).setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MIN);
        assertEq(fac_.getData().getLawsDamageRate().getVal(DifficultyModelLaw.CONSTANT_MIN).getLaw().getEvent(0),firstRtRtKey(eltRtRt(callDifficultyBeanDamageRatePlayerTableGet(beanSimu(EN, fac_)),0)));
    }

    @Test
    public void getDamageRatePlayerTable3() {
        FacadeGame fac_ = facTr();
        diff(fac_).setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MIN);
        assertEq(Rate.one(),secondRtRt(eltRtRt(callDifficultyBeanDamageRatePlayerTableGet(beanSimu(EN, fac_)),0)));
    }

    @Test
    public void getDamageRateLawFoe1() {
        FacadeGame fac_ = facTr();
        diff(fac_).setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MIN);
        assertSizeEq(1,callDifficultyBeanDamageRateFoeTableGet(beanSimu(EN, fac_)));
    }

    @Test
    public void getDamageRateLawFoe2() {
        FacadeGame fac_ = facTr();
        diff(fac_).setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MIN);
        assertEq(fac_.getData().getLawsDamageRate().getVal(DifficultyModelLaw.CONSTANT_MIN).getLaw().getEvent(0),firstRtRtKey(eltRtRt(callDifficultyBeanDamageRateFoeTableGet(beanSimu(EN, fac_)),0)));
    }

    @Test
    public void getDamageRateLawFoe3() {
        FacadeGame fac_ = facTr();
        diff(fac_).setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MIN);
        assertEq(Rate.one(),secondRtRt(eltRtRt(callDifficultyBeanDamageRateFoeTableGet(beanSimu(EN, fac_)),0)));
    }
//
//    @Test
//    public void setAllowCatchingKo1() {
//        assertTrue(result(callDifficultyBeanAllowCatchingKoSet(displaying(beanDiff(EN, fac())),true)).getAllowCatchingKo());
//    }
//
//    @Test
//    public void setAllowCatchingKo2() {
//        assertFalse(result(callDifficultyBeanAllowCatchingKoSet(displaying(beanDiff(EN, fac())),false)).getAllowCatchingKo());
//    }
//
//    @Test
//    public void setEndFightIfOneTeamKo1() {
//        assertTrue(result(callDifficultyBeanEndFightIfOneTeamKoSet(displaying(beanDiff(EN, fac())),true)).getEndFightIfOneTeamKo());
//    }
//
//    @Test
//    public void setEndFightIfOneTeamKo2() {
//        assertFalse(result(callDifficultyBeanEndFightIfOneTeamKoSet(displaying(beanDiff(EN, fac())),false)).getEndFightIfOneTeamKo());
//    }
//
//    @Test
//    public void setAllowedSwitchPlacesEndRound1() {
//        assertTrue(result(callDifficultyBeanAllowedSwitchPlacesEndRoundSet(displaying(beanDiff(EN, fac())),true)).getAllowedSwitchPlacesEndRound());
//    }
//
//    @Test
//    public void setAllowedSwitchPlacesEndRound2() {
//        assertFalse(result(callDifficultyBeanAllowedSwitchPlacesEndRoundSet(displaying(beanDiff(EN, fac())),false)).getAllowedSwitchPlacesEndRound());
//    }
//
//    @Test
//    public void setRestoredMovesEndFight1() {
//        assertTrue(result(callDifficultyBeanRestoredMovesEndFightSet(displaying(beanDiff(EN, fac())),true)).getRestoredMovesEndFight());
//    }
//
//    @Test
//    public void setRestoredMovesEndFight2() {
//        assertFalse(result(callDifficultyBeanRestoredMovesEndFightSet(displaying(beanDiff(EN, fac())),false)).getRestoredMovesEndFight());
//    }
//
//    @Test
//    public void setEnabledClosing1() {
//        assertTrue(result(callDifficultyBeanEnabledClosingSet(displaying(beanDiff(EN, fac())),true)).getEnabledClosing());
//    }
//
//    @Test
//    public void setEnabledClosing2() {
//        assertFalse(result(callDifficultyBeanEnabledClosingSet(displaying(beanDiff(EN, fac())),false)).getEnabledClosing());
//    }
//
//    @Test
//    public void setRandomWildFight1() {
//        assertTrue(result(callDifficultyBeanRandomWildFightSet(displaying(beanDiff(EN, fac())),true)).getRandomWildFight());
//    }
//
//    @Test
//    public void setRandomWildFight2() {
//        assertFalse(result(callDifficultyBeanRandomWildFightSet(displaying(beanDiff(EN, fac())),false)).getRandomWildFight());
//    }
//
//    @Test
//    public void setStillPossibleFlee1() {
//        assertTrue(result(callDifficultyBeanStillPossibleFleeSet(displaying(beanDiff(EN, fac())),true)).getStillPossibleFlee());
//    }
//
//    @Test
//    public void setStillPossibleFlee2() {
//        assertFalse(result(callDifficultyBeanStillPossibleFleeSet(displaying(beanDiff(EN, fac())),false)).getStillPossibleFlee());
//    }
//
//    @Test
//    public void setSkipLearningMovesWhileNotGrowingLevel1() {
//        assertTrue(result(callDifficultyBeanSkipLearningMovesWhileNotGrowingLevelSet(displaying(beanDiff(EN, fac())),true)).getSkipLearningMovesWhileNotGrowingLevel());
//    }
//
//    @Test
//    public void setSkipLearningMovesWhileNotGrowingLevel2() {
//        assertFalse(result(callDifficultyBeanSkipLearningMovesWhileNotGrowingLevelSet(displaying(beanDiff(EN, fac())),false)).getSkipLearningMovesWhileNotGrowingLevel());
//    }
//
//    @Test
//    public void setWinTrainerExp() {
//        assertEq(rt(),result(callDifficultyBeanWinTrainerExpSet(displaying(beanDiff(EN, fac())),rt())).getWinTrainerExp());
//    }
//
//    @Test
//    public void setRateWinningExpPtsFight() {
//        assertEq(rt(),result(callDifficultyBeanRateWinningExpPtsFightSet(displaying(beanDiff(EN, fac())),rt())).getRateWinningExpPtsFight());
//    }
//
//    @Test
//    public void setRateWinMoneyBase() {
//        assertEq(rt(),result(callDifficultyBeanRateWinMoneyBaseSet(displaying(beanDiff(EN, fac())),rt())).getRateWinMoneyBase());
//    }
//
//    @Test
//    public void setRateLooseMoneyWin() {
//        assertEq(rt(),result(callDifficultyBeanRateLooseMoneyWinSet(displaying(beanDiff(EN, fac())),rt())).getRateLooseMoneyWin());
//    }
//
//    @Test
//    public void setIvFoe() {
//        assertEq(1,result(callDifficultyBeanIvFoeSet(displaying(beanDiff(EN, fac())),1)).getIvFoe());
//    }
//
//    @Test
//    public void setIvPlayer() {
//        assertEq(1,result(callDifficultyBeanIvPlayerSet(displaying(beanDiff(EN, fac())),1)).getIvPlayer());
//    }
//    @Test
//    public void setDiffWinningExpPtsFight() {
//        assertEq(DifficultyWinPointsFight.FACILE.getWinName(),result(callDifficultyBeanDiffWinningExpPtsFightSet(displaying(beanDiff(EN, fac())),DifficultyWinPointsFight.FACILE.getWinName())).getDiffWinningExpPtsFight());
//    }
//    @Test
//    public void setDamageRateLawFoe() {
//        assertEq(DifficultyModelLaw.CONSTANT_MIN.getModelName(),result(callDifficultyBeanDamageRateLawFoeSet(displaying(beanDiff(EN, fac())),DifficultyModelLaw.CONSTANT_MIN.getModelName())).getDamageRateLawFoe());
//    }
//    @Test
//    public void setDamageRatePlayer() {
//        assertEq(DifficultyModelLaw.CONSTANT_MAX.getModelName(),result(callDifficultyBeanDamageRatePlayerSet(displaying(beanDiff(EN, fac())),DifficultyModelLaw.CONSTANT_MAX.getModelName())).getDamageRatePlayer());
//    }
//    @Test
//    public void change() {
//        FacadeGame fac_ = fac();
//        assertEq(PkScriptPages.WEB_GAME_HTML_DIFFICULTY_HTML,navigateDiffChange(callChange(displaying(beanDiff(EN, fac_)),DifficultyWinPointsFight.FACILE.getWinName())));
//        assertEq(DifficultyWinPointsFight.FACILE.getWinName(),diff(fac_).getDiffWinningExpPtsFight().getWinName());
//    }
    @Test
    public void heart() {
        FacadeGame fac_ = fac();
        assertEq("",navigateDiffChange(callChange(beanDiffDis(EN, fac_),DifficultyWinPointsFight.FACILE.getWinName())));
        assertEq(DifficultyWinPointsFight.FACILE.getWinName(),diff(fac_).getDiffWinningExpPtsFight().getWinName());
    }

    @Test
    public void setAllowCatchingKo_1() {
        assertTrue(result(callDifficultyBeanAllowCatchingKoSet(beanDiffDis(EN, fac()),true)).getAllowCatchingKo());
    }

    @Test
    public void setAllowCatchingKo_2() {
        assertFalse(result(callDifficultyBeanAllowCatchingKoSet(beanDiffDis(EN, fac()),false)).getAllowCatchingKo());
    }

    @Test
    public void setEndFightIfOneTeamKo_1() {
        assertTrue(result(callDifficultyBeanEndFightIfOneTeamKoSet(beanDiffDis(EN, fac()),true)).getEndFightIfOneTeamKo());
    }

    @Test
    public void setEndFightIfOneTeamKo_2() {
        assertFalse(result(callDifficultyBeanEndFightIfOneTeamKoSet(beanDiffDis(EN, fac()),false)).getEndFightIfOneTeamKo());
    }

    @Test
    public void setAllowedSwitchPlacesEndRound_1() {
        assertTrue(result(callDifficultyBeanAllowedSwitchPlacesEndRoundSet(beanDiffDis(EN, fac()),true)).getAllowedSwitchPlacesEndRound());
    }

    @Test
    public void setAllowedSwitchPlacesEndRound_2() {
        assertFalse(result(callDifficultyBeanAllowedSwitchPlacesEndRoundSet(beanDiffDis(EN, fac()),false)).getAllowedSwitchPlacesEndRound());
    }

    @Test
    public void setRestoredMovesEndFight_1() {
        assertTrue(result(callDifficultyBeanRestoredMovesEndFightSet(beanDiffDis(EN, fac()),true)).getRestoredMovesEndFight());
    }

    @Test
    public void setRestoredMovesEndFight_2() {
        assertFalse(result(callDifficultyBeanRestoredMovesEndFightSet(beanDiffDis(EN, fac()),false)).getRestoredMovesEndFight());
    }

    @Test
    public void setEnabledClosing_1() {
        assertTrue(result(callDifficultyBeanEnabledClosingSet(beanDiffDis(EN, fac()),true)).getEnabledClosing());
    }

    @Test
    public void setEnabledClosing_2() {
        assertFalse(result(callDifficultyBeanEnabledClosingSet(beanDiffDis(EN, fac()),false)).getEnabledClosing());
    }

    @Test
    public void setRandomWildFight_1() {
        assertTrue(result(callDifficultyBeanRandomWildFightSet(beanDiffDis(EN, fac()),true)).getRandomWildFight());
    }

    @Test
    public void setRandomWildFight_2() {
        assertFalse(result(callDifficultyBeanRandomWildFightSet(beanDiffDis(EN, fac()),false)).getRandomWildFight());
    }

    @Test
    public void setStillPossibleFlee_1() {
        assertTrue(result(callDifficultyBeanStillPossibleFleeSet(beanDiffDis(EN, fac()),true)).getStillPossibleFlee());
    }

    @Test
    public void setStillPossibleFlee_2() {
        assertFalse(result(callDifficultyBeanStillPossibleFleeSet(beanDiffDis(EN, fac()),false)).getStillPossibleFlee());
    }

    @Test
    public void setSkipLearningMovesWhileNotGrowingLevel_1() {
        assertTrue(result(callDifficultyBeanSkipLearningMovesWhileNotGrowingLevelSet(beanDiffDis(EN, fac()),true)).getSkipLearningMovesWhileNotGrowingLevel());
    }

    @Test
    public void setSkipLearningMovesWhileNotGrowingLevel_2() {
        assertFalse(result(callDifficultyBeanSkipLearningMovesWhileNotGrowingLevelSet(beanDiffDis(EN, fac()),false)).getSkipLearningMovesWhileNotGrowingLevel());
    }
//
//    @Test
//    public void init() {
//        FacadeGame fac_ = facTr();
//        diff(fac_).setDiffWinningExpPtsFight(DifficultyWinPointsFight.FACILE);
//        diff(fac_).setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
//        diff(fac_).setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MIN);
//        diff(fac_).setRateLooseMoneyWin(rt());
//        diff(fac_).setWinTrainerExp(rt());
//        diff(fac_).setRateWinningExpPtsFight(rt());
//        diff(fac_).setRateWinMoneyBase(rt());
//        StringMap<TranslationsAppli> builtMessages_ = new StringMap<TranslationsAppli>();
//        builtMessages_.addEntry(EN,new TranslationsAppli());
//        builtMessages_.addEntry(FR,new TranslationsAppli());
////        StringMap<String> builtOther_ = CssInit.ms();
//        PkData pk_ = new PkData();
//        NatNavigation nav_ = pk_.nav(new StringList(EN,FR), new DataGameInit(), PagesInit.build(),new StringMap<String>(),builtMessages_);
//        nav_.getSession().setFirstUrl(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML);
//        nav_.setLanguage(EN);
//        pk_.setDataBase(fac_);
//        pk_.initializeRendSessionDoc(nav_);
//        assertFalse(nav_.getHtmlText().isEmpty());
////        assertEq("<html xmlns:c=\"javahtml\"><head><title>Simulation of fights 1/8</title><link href=\"web/css/simulation.css\" rel=\"stylesheet\" type=\"text/css\"/><style>p{\n" +
////                "\ttext-indent:25px;\n" +
////                "}\n" +
////                "body{\n" +
////                "\ttext-align:justify;\n" +
////                "}\n" +
////                "td{\n" +
////                "\tborder:1px solid black;\n" +
////                "}\n" +
////                "th{\n" +
////                "\tbackground: yellow;\n" +
////                "\tborder:1px solid black;\n" +
////                "}\n" +
////                "table{\n" +
////                "\tborder-spacing:0;\n" +
////                "}\n" +
////                "h1{\n" +
////                "\tcolor:red;\n" +
////                "}\n" +
////                "h2{\n" +
////                "\tcolor:blue;\n" +
////                "}\n" +
////                "span.errormessage{\n" +
////                "\tcolor:red;\n" +
////                "}\n" +
////                "</style></head><body><a c:command=\"simulation.quit\" href=\"\" n-a=\"0\">Return to the index</a><br/><br/><form action=\"\" c:command=\"simulation.validateDiffChoice\" method=\"post\" n-f=\"0\">Difficulty of winning points<select name=\"difficulty_common.diffWinningExpPtsFight\" n-i=\"0\"><option value=\"0\">W1</option><option value=\"1\">W2</option><option value=\"2\" selected=\"selected\">W3</option><option value=\"3\">W4</option></select><br/>Allow catching ko pokemon<input name=\"difficulty_common.allowCatchingKo\" type=\"checkbox\" n-i=\"1\" checked=\"checked\"/><br/>Allow swicthing places at the front of battle at the end of round<input name=\"difficulty_common.allowedSwitchPlacesEndRound\" type=\"checkbox\" n-i=\"2\"/><br/>Rate of winning experience points de gain de points while a fight against a foe<input id=\"winTrainerExpRate\" name=\"difficulty_common.winTrainerExp\" type=\"text\" n-i=\"3\" value=\"3/2\"/><br/>Rate of winning experience points<input id=\"winExpRateFight\" name=\"difficulty_common.rateWinningExpPtsFight\" type=\"text\" n-i=\"4\" value=\"1\"/><br/>End of fight if a team is ko.<input name=\"difficulty_common.endFightIfOneTeamKo\" type=\"checkbox\" n-i=\"5\" checked=\"checked\"/><br/>Iv of your pokemon<input id=\"ivPlayerId\" name=\"difficulty_common.ivPlayer\" type=\"text\" n-i=\"6\" value=\"31\"/><br/>Iv of the pokemon of your foes<input id=\"ivFoeId\" name=\"difficulty_common.ivFoe\" type=\"text\" n-i=\"7\" value=\"0\"/><br/>Rate of won money between winning money while a victory and winning base<input id=\"rateWinMoneyBaseId\" name=\"difficulty_common.rateWinMoneyBase\" type=\"text\" n-i=\"8\" value=\"1\"/><br/>Rate of lost money between loss while a defeat and winning money while a victory<input id=\"rateLooseMoneyWinId\" name=\"difficulty_common.rateLooseMoneyWin\" type=\"text\" n-i=\"9\" value=\"1\"/><br/>Healed moves of your pokemon at the end of fight<input name=\"difficulty_common.restoredMovesEndFight\" type=\"checkbox\" n-i=\"10\"/><br/>The moves wth single target can achieve any foe<input name=\"difficulty_common.enabledClosing\" type=\"checkbox\" n-i=\"11\" checked=\"checked\"/><br/>Random appearing pokemon<input name=\"difficulty_common.randomWildFight\" type=\"checkbox\" n-i=\"12\"/><br/>Flee always possible<input name=\"difficulty_common.stillPossibleFlee\" type=\"checkbox\" n-i=\"13\" checked=\"checked\"/><br/>Do not learnt the already known moves<input name=\"difficulty_common.skipLearningMovesWhileNotGrowingLevel\" type=\"checkbox\" n-i=\"14\" checked=\"checked\"/><br/>Choice of averages of damage rate for your pokemon<select name=\"difficulty_common.damageRatePlayer\" n-i=\"15\"><option value=\"0\">M1</option><option value=\"1\">M2</option><option value=\"2\">M3</option><option value=\"3\">M4</option><option value=\"4\" selected=\"selected\">M5</option></select><br/><table><thead><tr><th>Rate</th><th>Probability</th></tr></thead><tbody><tr><td>1</td><td>1</td></tr></tbody></table>Choix of averages of damage rate for the pokemon of your foes<select name=\"difficulty_common.damageRateLawFoe\" n-i=\"16\"><option value=\"0\" selected=\"selected\">M1</option><option value=\"1\">M2</option><option value=\"2\">M3</option><option value=\"3\">M4</option><option value=\"4\">M5</option></select><br/><table><thead><tr><th>Rate</th><th>Probability</th></tr></thead><tbody><tr><td>17/20</td><td>1</td></tr></tbody></table>Free teams<input id=\"nbTeams\" name=\"simulation.nbTeams\" type=\"text\" n-i=\"17\" value=\"0\"/><br/><input value=\"&#62;&#62;\" type=\"submit\"/><br/></form></body></html>",nav_.getHtmlText());
//    }
//    @Test
//    public void nav() {
//        FacadeGame fac_ = facTr(16);
//        fac_.getData().getTranslatedEnvironment().addEntry(EN,new IdMap<EnvironmentType, String>());
//        fac_.getData().getTranslatedEnvironment().addEntry(FR,new IdMap<EnvironmentType, String>());
//        fac_.getData().setMap(Instances.newDataMap());
//        StringMap<TranslationsAppli> builtMessages_ = new StringMap<TranslationsAppli>();
//        builtMessages_.addEntry(EN,new TranslationsAppli());
//        builtMessages_.addEntry(FR,new TranslationsAppli());
////        StringMap<String> builtOther_ = CssInit.ms();
//        PkData pk_ = new PkData();
//        NatNavigation nav_ = pk_.nav(new StringList(EN,FR), new DataGameInit(), PagesInit.build(),new StringMap<String>(),builtMessages_);
//        nav_.getSession().setFirstUrl(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML);
//        nav_.setLanguage(EN);
//        pk_.setDataBase(fac_);
//        pk_.initializeRendSessionDoc(nav_);
////        NatHtmlPage htmlPage_ = pk_.getNatPage();
////        choose(htmlPage_, 0, DifficultyWinPointsFight.FACILE.getWinName());
////        choose(htmlPage_, 3, rt().toNumberString());
////        choose(htmlPage_, 4, rt().toNumberString());
////        choose(htmlPage_, 6, "12");
////        choose(htmlPage_, 7, "4");
////        choose(htmlPage_, 8, rt().toNumberString());
////        choose(htmlPage_, 9, rt().toNumberString());
////        choose(htmlPage_, 15, DifficultyModelLaw.CONSTANT_MAX.getModelName());
////        choose(htmlPage_, 16, DifficultyModelLaw.CONSTANT_MIN.getModelName());
////        htmlPage_.setUrl(0);
////
////        pk_.execute(true, nav_);
//
//
//        assertFalse(nav_.getHtmlText().isEmpty());
////        assertEq("<html xmlns:c=\"javahtml\"><head><title>Simulation of fights 2/8</title><link href=\"web/css/simulation.css\" rel=\"stylesheet\" type=\"text/css\"/><style>p{\n" +
////                "\ttext-indent:25px;\n" +
////                "}\n" +
////                "body{\n" +
////                "\ttext-align:justify;\n" +
////                "}\n" +
////                "td{\n" +
////                "\tborder:1px solid black;\n" +
////                "}\n" +
////                "th{\n" +
////                "\tbackground: yellow;\n" +
////                "\tborder:1px solid black;\n" +
////                "}\n" +
////                "table{\n" +
////                "\tborder-spacing:0;\n" +
////                "}\n" +
////                "h1{\n" +
////                "\tcolor:red;\n" +
////                "}\n" +
////                "h2{\n" +
////                "\tcolor:blue;\n" +
////                "}\n" +
////                "span.errormessage{\n" +
////                "\tcolor:red;\n" +
////                "}\n" +
////                "</style></head><body><a c:command=\"simulation.quit\" href=\"\" n-a=\"0\">Return to the index</a><br/><br/><ul/>Fight number<br/><form action=\"\" c:command=\"simulation.cancelDiffChoice\" method=\"post\" name=\"cancel\" n-f=\"0\"><input value=\"&#60;&#60;\" type=\"submit\"/></form><form action=\"\" c:command=\"simulation.validateFoeChoice\" method=\"post\" name=\"ok\" n-f=\"1\"><input value=\"&#62;&#62;\" type=\"submit\"/></form></body></html>",nav_.getHtmlText());
//    }
//
//    private void choose(NatHtmlPage _page, int _nbId, String _value) {
//        _page.getContainer(0, _nbId).setEnabled(true);
//        _page.getContainer(0, _nbId).setValue(new StringList(_value));
//    }

//    private DifficultyCommon result(NaSt _str) {
//        return (((DifficultyCommonBean) ((PokemonBeanStruct) _str).getInstance()).getDifficultyCommon()).getDifficultyCommon();
//    }

    public SimulationBean displaying(SimulationBean _b) {
        beforeDisplaying(_b);
        return _b;
    }
    private DifficultyCommon result(DifficultyBean _str) {
        return _str.getDifficultyCommon();
    }
    private Rate rt() {
        return Rate.newRate("2");
    }

    private Difficulty diff(FacadeGame _fac) {
        return _fac.getGame().getDifficulty();
    }

    private FacadeGame facTr() {
        return facTr(2);
    }
    private FacadeGame facTr(int _iv) {
        DataBase di_ = diff(_iv);
        di_.getTranslatedDiffWinPts().getVal(EN).clear();
        di_.getTranslatedDiffWinPts().getVal(EN).addEntry(DifficultyWinPointsFight.TRES_FACILE,"W1");
        di_.getTranslatedDiffWinPts().getVal(EN).addEntry(DifficultyWinPointsFight.FACILE,"W2");
        di_.getTranslatedDiffWinPts().getVal(EN).addEntry(DifficultyWinPointsFight.DIFFICILE,"W3");
        di_.getTranslatedDiffWinPts().getVal(EN).addEntry(DifficultyWinPointsFight.TRES_DIFFICILE,"W4");
        di_.getTranslatedDiffModelLaw().getVal(EN).clear();
        di_.getTranslatedDiffModelLaw().getVal(EN).addEntry(DifficultyModelLaw.CONSTANT_MIN,"M1");
        di_.getTranslatedDiffModelLaw().getVal(EN).addEntry(DifficultyModelLaw.CROISSANT,"M2");
        di_.getTranslatedDiffModelLaw().getVal(EN).addEntry(DifficultyModelLaw.UNIFORME,"M3");
        di_.getTranslatedDiffModelLaw().getVal(EN).addEntry(DifficultyModelLaw.DECROISSANT,"M4");
        di_.getTranslatedDiffModelLaw().getVal(EN).addEntry(DifficultyModelLaw.CONSTANT_MAX,"M5");
        return fac(di_);
    }
    private FacadeGame fac() {
        DataBase di_ = diff(2);
        return fac(di_);
    }

    private FacadeGame fac(DataBase _di) {
        FacadeGame f_ = new FacadeGame();
        f_.setData(_di);
        f_.setGame(new Game());
        return f_;
    }
}
