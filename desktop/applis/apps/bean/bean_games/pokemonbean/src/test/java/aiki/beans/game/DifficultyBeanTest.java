package aiki.beans.game;

import aiki.beans.InitDbBean;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.Game;
import aiki.game.params.Difficulty;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
import code.maths.Rate;
import org.junit.Test;

public final class DifficultyBeanTest extends InitDbBean {

    @Test
    public void getAllowCatchingKo1() {
        FacadeGame fac_ = fac();
        diff(fac_).setAllowCatchingKo(true);
        assertTrue(callDifficultyBeanAllowCatchingKoGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getAllowCatchingKo2() {
        FacadeGame fac_ = fac();
        diff(fac_).setAllowCatchingKo(false);
        assertFalse(callDifficultyBeanAllowCatchingKoGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getEndFightIfOneTeamKo1() {
        FacadeGame fac_ = fac();
        diff(fac_).setEndFightIfOneTeamKo(true);
        assertTrue(callDifficultyBeanEndFightIfOneTeamKoGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getEndFightIfOneTeamKo2() {
        FacadeGame fac_ = fac();
        diff(fac_).setEndFightIfOneTeamKo(false);
        assertFalse(callDifficultyBeanEndFightIfOneTeamKoGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getAllowedSwitchPlacesEndRound1() {
        FacadeGame fac_ = fac();
        diff(fac_).setAllowedSwitchPlacesEndRound(true);
        assertTrue(callDifficultyBeanAllowedSwitchPlacesEndRoundGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getAllowedSwitchPlacesEndRound2() {
        FacadeGame fac_ = fac();
        diff(fac_).setAllowedSwitchPlacesEndRound(false);
        assertFalse(callDifficultyBeanAllowedSwitchPlacesEndRoundGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getRestoredMovesEndFight1() {
        FacadeGame fac_ = fac();
        diff(fac_).setRestoredMovesEndFight(true);
        assertTrue(callDifficultyBeanRestoredMovesEndFightGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getRestoredMovesEndFight2() {
        FacadeGame fac_ = fac();
        diff(fac_).setRestoredMovesEndFight(false);
        assertFalse(callDifficultyBeanRestoredMovesEndFightGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getEnabledClosing1() {
        FacadeGame fac_ = fac();
        diff(fac_).setEnabledClosing(true);
        assertTrue(callDifficultyBeanEnabledClosingGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getEnabledClosing2() {
        FacadeGame fac_ = fac();
        diff(fac_).setEnabledClosing(false);
        assertFalse(callDifficultyBeanEnabledClosingGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getRandomWildFight1() {
        FacadeGame fac_ = fac();
        diff(fac_).setRandomWildFight(true);
        assertTrue(callDifficultyBeanRandomWildFightGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getRandomWildFight2() {
        FacadeGame fac_ = fac();
        diff(fac_).setRandomWildFight(false);
        assertFalse(callDifficultyBeanRandomWildFightGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getStillPossibleFlee1() {
        FacadeGame fac_ = fac();
        diff(fac_).setStillPossibleFlee(true);
        assertTrue(callDifficultyBeanStillPossibleFleeGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getStillPossibleFlee2() {
        FacadeGame fac_ = fac();
        diff(fac_).setStillPossibleFlee(false);
        assertFalse(callDifficultyBeanStillPossibleFleeGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getSkipLearningMovesWhileNotGrowingLevel1() {
        FacadeGame fac_ = fac();
        diff(fac_).setSkipLearningMovesWhileNotGrowingLevel(true);
        assertTrue(callDifficultyBeanSkipLearningMovesWhileNotGrowingLevelGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getSkipLearningMovesWhileNotGrowingLevel2() {
        FacadeGame fac_ = fac();
        diff(fac_).setSkipLearningMovesWhileNotGrowingLevel(false);
        assertFalse(callDifficultyBeanSkipLearningMovesWhileNotGrowingLevelGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getRateLooseMoneyWin() {
        FacadeGame fac_ = fac();
        diff(fac_).setRateLooseMoneyWin(rt());
        assertEq(rt(),callDifficultyBeanRateLooseMoneyWinGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getWinTrainerExp() {
        FacadeGame fac_ = fac();
        diff(fac_).setWinTrainerExp(rt());
        assertEq(rt(),callDifficultyBeanWinTrainerExpGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getRateWinningExpPtsFight() {
        FacadeGame fac_ = fac();
        diff(fac_).setRateWinningExpPtsFight(rt());
        assertEq(rt(),callDifficultyBeanRateWinningExpPtsFightGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getRateWinMoneyBase() {
        FacadeGame fac_ = fac();
        diff(fac_).setRateWinMoneyBase(rt());
        assertEq(rt(),callDifficultyBeanRateWinMoneyBaseGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getIvFoe() {
        FacadeGame fac_ = fac();
        diff(fac_).setIvFoe((short) 1);
        assertEq(1,callDifficultyBeanIvFoeGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getIvPlayer() {
        FacadeGame fac_ = fac();
        diff(fac_).setIvPlayer((short) 1);
        assertEq(1,callDifficultyBeanIvPlayerGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getDiffWinningExpPtsFight() {
        FacadeGame fac_ = fac();
        diff(fac_).setDiffWinningExpPtsFight(DifficultyWinPointsFight.FACILE);
        assertEq(DifficultyWinPointsFight.FACILE.getWinName(),callDifficultyBeanDiffWinningExpPtsFightGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getDamageRateLawFoe() {
        FacadeGame fac_ = fac();
        diff(fac_).setDamageRateLawFoe(DifficultyModelLaw.CONSTANT_MIN);
        assertEq(DifficultyModelLaw.CONSTANT_MIN.getModelName(),callDifficultyBeanDamageRateLawFoeGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getDamageRatePlayer() {
        FacadeGame fac_ = fac();
        diff(fac_).setDamageRatePlayer(DifficultyModelLaw.CONSTANT_MAX);
        assertEq(DifficultyModelLaw.CONSTANT_MAX.getModelName(),callDifficultyBeanDamageRatePlayerGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getWinPointsFight1() {
        FacadeGame fac_ = facTr();
        assertSizeEq(4,callDifficultyBeanWinPointsFightGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getWinPointsFight2() {
        FacadeGame fac_ = facTr();
        assertEq(DifficultyWinPointsFight.TRES_FACILE.getWinName(),first(elt(callDifficultyBeanWinPointsFightGet(displaying(beanDiff(EN, fac_))),0)));
    }

    @Test
    public void getWinPointsFight3() {
        FacadeGame fac_ = facTr();
        assertEq("W1",second(elt(callDifficultyBeanWinPointsFightGet(displaying(beanDiff(EN, fac_))),0)));
    }

    @Test
    public void getWinPointsFight4() {
        FacadeGame fac_ = facTr();
        assertEq(DifficultyWinPointsFight.FACILE.getWinName(),first(elt(callDifficultyBeanWinPointsFightGet(displaying(beanDiff(EN, fac_))),1)));
    }

    @Test
    public void getWinPointsFight5() {
        FacadeGame fac_ = facTr();
        assertEq("W2",second(elt(callDifficultyBeanWinPointsFightGet(displaying(beanDiff(EN, fac_))),1)));
    }

    @Test
    public void getWinPointsFight6() {
        FacadeGame fac_ = facTr();
        assertEq(DifficultyWinPointsFight.DIFFICILE.getWinName(),first(elt(callDifficultyBeanWinPointsFightGet(displaying(beanDiff(EN, fac_))),2)));
    }

    @Test
    public void getWinPointsFight7() {
        FacadeGame fac_ = facTr();
        assertEq("W3",second(elt(callDifficultyBeanWinPointsFightGet(displaying(beanDiff(EN, fac_))),2)));
    }

    @Test
    public void getWinPointsFight8() {
        FacadeGame fac_ = facTr();
        assertEq(DifficultyWinPointsFight.TRES_DIFFICILE.getWinName(),first(elt(callDifficultyBeanWinPointsFightGet(displaying(beanDiff(EN, fac_))),3)));
    }

    @Test
    public void getWinPointsFight9() {
        FacadeGame fac_ = facTr();
        assertEq("W4",second(elt(callDifficultyBeanWinPointsFightGet(displaying(beanDiff(EN, fac_))),3)));
    }

    @Test
    public void getDamageRates1() {
        FacadeGame fac_ = facTr();
        assertSizeEq(5,callDifficultyBeanDamageRatesGet(displaying(beanDiff(EN, fac_))));
    }

    @Test
    public void getDamageRates2() {
        FacadeGame fac_ = facTr();
        assertEq(DifficultyModelLaw.CONSTANT_MIN.getModelName(),first(elt(callDifficultyBeanDamageRatesGet(displaying(beanDiff(EN, fac_))),0)));
    }

    @Test
    public void getDamageRates3() {
        FacadeGame fac_ = facTr();
        assertEq("M1",second(elt(callDifficultyBeanDamageRatesGet(displaying(beanDiff(EN, fac_))),0)));
    }

    @Test
    public void getDamageRates4() {
        FacadeGame fac_ = facTr();
        assertEq(DifficultyModelLaw.CROISSANT.getModelName(),first(elt(callDifficultyBeanDamageRatesGet(displaying(beanDiff(EN, fac_))),1)));
    }

    @Test
    public void getDamageRates5() {
        FacadeGame fac_ = facTr();
        assertEq("M2",second(elt(callDifficultyBeanDamageRatesGet(displaying(beanDiff(EN, fac_))),1)));
    }

    @Test
    public void getDamageRates6() {
        FacadeGame fac_ = facTr();
        assertEq(DifficultyModelLaw.UNIFORME.getModelName(),first(elt(callDifficultyBeanDamageRatesGet(displaying(beanDiff(EN, fac_))),2)));
    }

    @Test
    public void getDamageRates7() {
        FacadeGame fac_ = facTr();
        assertEq("M3",second(elt(callDifficultyBeanDamageRatesGet(displaying(beanDiff(EN, fac_))),2)));
    }

    @Test
    public void getDamageRates8() {
        FacadeGame fac_ = facTr();
        assertEq(DifficultyModelLaw.DECROISSANT.getModelName(),first(elt(callDifficultyBeanDamageRatesGet(displaying(beanDiff(EN, fac_))),3)));
    }

    @Test
    public void getDamageRates9() {
        FacadeGame fac_ = facTr();
        assertEq("M4",second(elt(callDifficultyBeanDamageRatesGet(displaying(beanDiff(EN, fac_))),3)));
    }

    @Test
    public void getDamageRates10() {
        FacadeGame fac_ = facTr();
        assertEq(DifficultyModelLaw.CONSTANT_MAX.getModelName(),first(elt(callDifficultyBeanDamageRatesGet(displaying(beanDiff(EN, fac_))),4)));
    }

    @Test
    public void getDamageRates11() {
        FacadeGame fac_ = facTr();
        assertEq("M5",second(elt(callDifficultyBeanDamageRatesGet(displaying(beanDiff(EN, fac_))),4)));
    }

    private Rate rt() {
        return Rate.newRate("2");
    }

    private Difficulty diff(FacadeGame _fac) {
        return _fac.getGame().getDifficulty();
    }

    private FacadeGame facTr() {
        DataBase di_ = diff(2);
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
