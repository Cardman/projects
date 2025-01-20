package aiki.beans.game;

import aiki.beans.CommonBean;
import aiki.beans.DifficultyCommon;
import aiki.comparators.DictionaryComparator;
import code.maths.Rate;
import code.util.StringMap;

public class DifficultyCommonBean extends CommonBean {
    private DifficultyCommon owner;
    private DifficultyCommon difficultyCommon;
    @Override
    public void beforeDisplaying() {
        difficultyCommon = owner;
    }

    public DifficultyCommon getDifficultyCommon() {
        return difficultyCommon;
    }

    public StringMap<String> getWinPointsFight() {
        return difficultyCommon.getWinPointsFight();
    }

    public String getDiffWinningExpPtsFight() {
        return difficultyCommon.getDiffWinningExpPtsFight();
    }

    public void setDiffWinningExpPtsFight(String _diffWinningExpPtsFight) {
        difficultyCommon.setDiffWinningExpPtsFight(_diffWinningExpPtsFight);
    }

    public void setAllowCatchingKo(boolean _allowCatchingKo) {
        difficultyCommon.setAllowCatchingKo(_allowCatchingKo);
    }

    public boolean getAllowCatchingKo() {
        return difficultyCommon.getAllowCatchingKo();
    }

    public void setAllowedSwitchPlacesEndRound(boolean _allowedSwitchPlacesEndRound) {
        difficultyCommon.setAllowedSwitchPlacesEndRound(_allowedSwitchPlacesEndRound);
    }

    public boolean getAllowedSwitchPlacesEndRound() {
        return difficultyCommon.getAllowedSwitchPlacesEndRound();
    }

    public void setWinTrainerExp(Rate _winTrainerExp) {
        difficultyCommon.setWinTrainerExp(_winTrainerExp);
    }

    public Rate getWinTrainerExp() {
        return difficultyCommon.getWinTrainerExp();
    }

    public void setRateWinningExpPtsFight(Rate _rateWinningExpPtsFight) {
        difficultyCommon.setRateWinningExpPtsFight(_rateWinningExpPtsFight);
    }

    public Rate getRateWinningExpPtsFight() {
        return difficultyCommon.getRateWinningExpPtsFight();
    }

    public void setEndFightIfOneTeamKo(boolean _endFightIfOneTeamKo) {
        difficultyCommon.setEndFightIfOneTeamKo(_endFightIfOneTeamKo);
    }

    public boolean getEndFightIfOneTeamKo() {
        return difficultyCommon.getEndFightIfOneTeamKo();
    }

    public void setIvPlayer(long _ivPlayer) {
        difficultyCommon.setIvPlayer(_ivPlayer);
    }

    public long getIvPlayer() {
        return difficultyCommon.getIvPlayer();
    }

    public void setIvFoe(long _ivFoe) {
        difficultyCommon.setIvFoe(_ivFoe);
    }

    public long getIvFoe() {
        return difficultyCommon.getIvFoe();
    }

    public void setRateWinMoneyBase(Rate _rateWinMoneyBase) {
        difficultyCommon.setRateWinMoneyBase(_rateWinMoneyBase);
    }

    public Rate getRateWinMoneyBase() {
        return difficultyCommon.getRateWinMoneyBase();
    }

    public void setRateLooseMoneyWin(Rate _rateLooseMoneyWin) {
        difficultyCommon.setRateLooseMoneyWin(_rateLooseMoneyWin);
    }

    public Rate getRateLooseMoneyWin() {
        return difficultyCommon.getRateLooseMoneyWin();
    }

    public void setRestoredMovesEndFight(boolean _restoredMovesEndFight) {
        difficultyCommon.setRestoredMovesEndFight(_restoredMovesEndFight);
    }

    public boolean getRestoredMovesEndFight() {
        return difficultyCommon.getRestoredMovesEndFight();
    }

    public void setEnabledClosing(boolean _enabledClosing) {
        difficultyCommon.setEnabledClosing(_enabledClosing);
    }

    public boolean getEnabledClosing() {
        return difficultyCommon.getEnabledClosing();
    }

    public void setRandomWildFight(boolean _randomWildFight) {
        difficultyCommon.setRandomWildFight(_randomWildFight);
    }

    public boolean getRandomWildFight() {
        return difficultyCommon.getRandomWildFight();
    }

    public void setStillPossibleFlee(boolean _stillPossibleFlee) {
        difficultyCommon.setStillPossibleFlee(_stillPossibleFlee);
    }

    public boolean getStillPossibleFlee() {
        return difficultyCommon.getStillPossibleFlee();
    }

    public void setSkipLearningMovesWhileNotGrowingLevel(boolean _skipLearningMovesWhileNotGrowingLevel) {
        difficultyCommon.setSkipLearningMovesWhileNotGrowingLevel(_skipLearningMovesWhileNotGrowingLevel);
    }

    public boolean getSkipLearningMovesWhileNotGrowingLevel() {
        return difficultyCommon.getSkipLearningMovesWhileNotGrowingLevel();
    }

    public StringMap<String> getDamageRates() {
        return difficultyCommon.getDamageRates();
    }

    public String getDamageRatePlayer() {
        return difficultyCommon.getDamageRatePlayer();
    }

    public void setDamageRatePlayer(String _damageRatePlayer) {
        difficultyCommon.setDamageRatePlayer(_damageRatePlayer);
    }

    public DictionaryComparator<Rate,Rate> getDamageRatePlayerTable() {
        return difficultyCommon.getDamageRatePlayerTable();
    }

    public String getDamageRateLawFoe() {
        return difficultyCommon.getDamageRateLawFoe();
    }

    public void setDamageRateLawFoe(String _damageRateLawFoe) {
        difficultyCommon.setDamageRateLawFoe(_damageRateLawFoe);
    }

    public DictionaryComparator<Rate,Rate> getDamageRateFoeTable() {
        return difficultyCommon.getDamageRateFoeTable();
    }

    public void setOwner(DifficultyCommon _o) {
        this.owner = _o;
    }
}
