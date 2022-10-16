package aiki.beans.game;

import aiki.beans.InitDbBean;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.Game;
import aiki.game.params.Difficulty;
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
    private Rate rt() {
        return Rate.newRate("2");
    }

    private Difficulty diff(FacadeGame _fac) {
        return _fac.getGame().getDifficulty();
    }

    private FacadeGame fac() {
        DataBase di_ = diff(2);
        FacadeGame f_ = new FacadeGame();
        f_.setData(di_);
        f_.setGame(new Game());
        return f_;
    }
}
