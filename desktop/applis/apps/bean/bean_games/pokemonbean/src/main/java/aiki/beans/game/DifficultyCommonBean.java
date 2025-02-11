package aiki.beans.game;

import aiki.beans.*;
import aiki.beans.simulation.*;
import aiki.comparators.*;
import code.maths.*;
import code.util.*;

public class DifficultyCommonBean extends CommonBean {
    private SimulationBean owner;
    private SimulationBean difficultyCommon;
    @Override
    public void beforeDisplaying() {
        difficultyCommon = owner;
    }

    public SimulationBean getDifficultyCommon() {
        return difficultyCommon;
    }

    public StringMap<String> getWinPointsFight() {
        return difficultyCommon.getDifficultyCommon().getWinPointsFight();
    }

    public String getDiffWinningExpPtsFight() {
        return difficultyCommon.getDifficultyCommon().getDiffWinningExpPtsFight();
    }

    public void setDiffWinningExpPtsFight(String _diffWinningExpPtsFight) {
        difficultyCommon.getDifficultyCommon().setDiffWinningExpPtsFight(_diffWinningExpPtsFight);
    }

    public void setAllowCatchingKo(boolean _allowCatchingKo) {
        difficultyCommon.getDifficultyCommon().setAllowCatchingKo(_allowCatchingKo);
    }

    public boolean getAllowCatchingKo() {
        return difficultyCommon.getDifficultyCommon().getAllowCatchingKo();
    }

    public void setAllowedSwitchPlacesEndRound(boolean _allowedSwitchPlacesEndRound) {
        difficultyCommon.getDifficultyCommon().setAllowedSwitchPlacesEndRound(_allowedSwitchPlacesEndRound);
    }

    public boolean getAllowedSwitchPlacesEndRound() {
        return difficultyCommon.getDifficultyCommon().getAllowedSwitchPlacesEndRound();
    }

    public void setWinTrainerExp(Rate _winTrainerExp) {
        difficultyCommon.getDifficultyCommon().setWinTrainerExp(_winTrainerExp);
    }

    public Rate getWinTrainerExp() {
        return difficultyCommon.getDifficultyCommon().getWinTrainerExp();
    }

    public void setRateWinningExpPtsFight(Rate _rateWinningExpPtsFight) {
        difficultyCommon.getDifficultyCommon().setRateWinningExpPtsFight(_rateWinningExpPtsFight);
    }

    public Rate getRateWinningExpPtsFight() {
        return difficultyCommon.getDifficultyCommon().getRateWinningExpPtsFight();
    }

    public void setEndFightIfOneTeamKo(boolean _endFightIfOneTeamKo) {
        difficultyCommon.getDifficultyCommon().setEndFightIfOneTeamKo(_endFightIfOneTeamKo);
    }

    public boolean getEndFightIfOneTeamKo() {
        return difficultyCommon.getDifficultyCommon().getEndFightIfOneTeamKo();
    }

    public void setIvPlayer(long _ivPlayer) {
        difficultyCommon.getDifficultyCommon().setIvPlayer(_ivPlayer);
    }

    public long getIvPlayer() {
        return difficultyCommon.getDifficultyCommon().getIvPlayer();
    }

    public void setIvFoe(long _ivFoe) {
        difficultyCommon.getDifficultyCommon().setIvFoe(_ivFoe);
    }

    public long getIvFoe() {
        return difficultyCommon.getDifficultyCommon().getIvFoe();
    }

    public void setRateWinMoneyBase(Rate _rateWinMoneyBase) {
        difficultyCommon.getDifficultyCommon().setRateWinMoneyBase(_rateWinMoneyBase);
    }

    public Rate getRateWinMoneyBase() {
        return difficultyCommon.getDifficultyCommon().getRateWinMoneyBase();
    }

    public void setRateLooseMoneyWin(Rate _rateLooseMoneyWin) {
        difficultyCommon.getDifficultyCommon().setRateLooseMoneyWin(_rateLooseMoneyWin);
    }

    public Rate getRateLooseMoneyWin() {
        return difficultyCommon.getDifficultyCommon().getRateLooseMoneyWin();
    }

    public void setRestoredMovesEndFight(boolean _restoredMovesEndFight) {
        difficultyCommon.getDifficultyCommon().setRestoredMovesEndFight(_restoredMovesEndFight);
    }

    public boolean getRestoredMovesEndFight() {
        return difficultyCommon.getDifficultyCommon().getRestoredMovesEndFight();
    }

    public void setEnabledClosing(boolean _enabledClosing) {
        difficultyCommon.getDifficultyCommon().setEnabledClosing(_enabledClosing);
    }

    public boolean getEnabledClosing() {
        return difficultyCommon.getDifficultyCommon().getEnabledClosing();
    }

    public void setRandomWildFight(boolean _randomWildFight) {
        difficultyCommon.getDifficultyCommon().setRandomWildFight(_randomWildFight);
    }

    public boolean getRandomWildFight() {
        return difficultyCommon.getDifficultyCommon().getRandomWildFight();
    }

    public void setStillPossibleFlee(boolean _stillPossibleFlee) {
        difficultyCommon.getDifficultyCommon().setStillPossibleFlee(_stillPossibleFlee);
    }

    public boolean getStillPossibleFlee() {
        return difficultyCommon.getDifficultyCommon().getStillPossibleFlee();
    }

    public void setSkipLearningMovesWhileNotGrowingLevel(boolean _skipLearningMovesWhileNotGrowingLevel) {
        difficultyCommon.getDifficultyCommon().setSkipLearningMovesWhileNotGrowingLevel(_skipLearningMovesWhileNotGrowingLevel);
    }

    public boolean getSkipLearningMovesWhileNotGrowingLevel() {
        return difficultyCommon.getDifficultyCommon().getSkipLearningMovesWhileNotGrowingLevel();
    }

    public StringMap<String> getDamageRates() {
        return difficultyCommon.getDifficultyCommon().getDamageRates();
    }

    public String getDamageRatePlayer() {
        return difficultyCommon.getDifficultyCommon().getDamageRatePlayer();
    }

    public void setDamageRatePlayer(String _damageRatePlayer) {
        difficultyCommon.getDifficultyCommon().setDamageRatePlayer(_damageRatePlayer);
    }

    public DictionaryComparator<Rate,Rate> getDamageRatePlayerTable() {
        return difficultyCommon.getDifficultyCommon().getDamageRatePlayerTable();
    }

    public String getDamageRateLawFoe() {
        return difficultyCommon.getDifficultyCommon().getDamageRateLawFoe();
    }

    public void setDamageRateLawFoe(String _damageRateLawFoe) {
        difficultyCommon.getDifficultyCommon().setDamageRateLawFoe(_damageRateLawFoe);
    }

    public DictionaryComparator<Rate,Rate> getDamageRateFoeTable() {
        return difficultyCommon.getDifficultyCommon().getDamageRateFoeTable();
    }

    public void setOwner(SimulationBean _o) {
        this.owner = _o;
    }
}
