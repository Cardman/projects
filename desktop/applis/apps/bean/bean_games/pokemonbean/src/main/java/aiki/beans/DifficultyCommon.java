package aiki.beans;

import aiki.db.DataBase;
import aiki.game.params.Difficulty;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
import code.maths.ComparatorRate;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.AbsMap;
import code.util.StringMap;
import code.util.TreeMap;

public final class DifficultyCommon {
    private boolean allowCatchingKo;
    private boolean allowedSwitchPlacesEndRound;
    private String diffWinningExpPtsFight;
    private StringMap<String> winPointsFight;
    private Rate rateWinningExpPtsFight;
    private Rate winTrainerExp;
    private StringMap<String> damageRates;
    private String damageRatePlayer;
    private TreeMap<Rate,Rate> damageRatePlayerTable;
    private String damageRateLawFoe;
    private TreeMap<Rate,Rate> damageRateFoeTable;
    private boolean endFightIfOneTeamKo;
    private Rate rateWinMoneyBase;
    private Rate rateLooseMoneyWin;
    private int ivPlayer;
    private int ivFoe;
    private boolean stillPossibleFlee;
    private boolean restoredMovesEndFight;
    private boolean enabledClosing;
    private boolean randomWildFight;
    private boolean skipLearningMovesWhileNotGrowingLevel;
    public void init(DataBase _data, String _lg, Difficulty _diff) {
        damageRates = new StringMap<String>();
        winPointsFight = new StringMap<String>();
        AbsMap<DifficultyWinPointsFight, String> trWinPts_ = _data.getTranslatedDiffWinPts().getVal(_lg);
        for (DifficultyWinPointsFight k: trWinPts_.getKeys()) {
//                winPointsFight.put(k, XmlParser.transformSpecialChars(trWinPts_.getVal(k)));
            winPointsFight.addEntry(k.getWinName(), trWinPts_.getVal(k));
        }
        AbsMap<DifficultyModelLaw, String> trWinLaw_ = _data.getTranslatedDiffModelLaw().getVal(_lg);
        for (DifficultyModelLaw k: trWinLaw_.getKeys()) {
//                damageRates.put(k, XmlParser.transformSpecialChars(trWinLaw_.getVal(k)));
            damageRates.addEntry(k.getModelName(), trWinLaw_.getVal(k));
        }
        diffWinningExpPtsFight = _diff.getDiffWinningExpPtsFight().getWinName();
        allowCatchingKo = _diff.getAllowCatchingKo();
        allowedSwitchPlacesEndRound = _diff.getAllowedSwitchPlacesEndRound();
        winTrainerExp = _diff.getWinTrainerExp();
        rateWinningExpPtsFight = _diff.getRateWinningExpPtsFight();
        endFightIfOneTeamKo = _diff.getEndFightIfOneTeamKo();
        ivPlayer = _diff.getIvPlayer();
        ivFoe = _diff.getIvFoe();
        rateWinMoneyBase = _diff.getRateWinMoneyBase();
        rateLooseMoneyWin = _diff.getRateLooseMoneyWin();
        stillPossibleFlee = _diff.getStillPossibleFlee();
        restoredMovesEndFight = _diff.getRestoredMovesEndFight();
        enabledClosing = _diff.getEnabledClosing();
        randomWildFight = _diff.getRandomWildFight();
        skipLearningMovesWhileNotGrowingLevel = _diff.isSkipLearningMovesWhileNotGrowingLevel();
        damageRatePlayer = _diff.getDamageRatePlayer().getModelName();
        damageRateLawFoe = _diff.getDamageRateLawFoe().getModelName();
        damageRatePlayerTable = new TreeMap<Rate, Rate>(new ComparatorRate());
        MonteCarloNumber law_;
        law_ = _data.getLawsDamageRate().getVal(PokemonStandards.getModelByName(damageRatePlayer)).getLaw();
        for (Rate e: law_.events()) {
            damageRatePlayerTable.put(e, law_.normalizedRate(e));
        }
        damageRateFoeTable = new TreeMap<Rate, Rate>(new ComparatorRate());
        law_ = _data.getLawsDamageRate().getVal(PokemonStandards.getModelByName(damageRateLawFoe)).getLaw();
        for (Rate e: law_.events()) {
            damageRateFoeTable.put(e, law_.normalizedRate(e));
        }
    }
    public void apply(DataBase _data, Difficulty _diff) {
        _diff.setDiffWinningExpPtsFight(PokemonStandards.getDiffWonPtsByName(diffWinningExpPtsFight));
        _diff.setAllowCatchingKo(allowCatchingKo);
        _diff.setAllowedSwitchPlacesEndRound(allowedSwitchPlacesEndRound);
        _diff.setWinTrainerExp(winTrainerExp);
        _diff.setRateWinningExpPtsFight(rateWinningExpPtsFight);
        _diff.setEndFightIfOneTeamKo(endFightIfOneTeamKo);
        _diff.setIvPlayer((short) ivPlayer);
        _diff.setIvFoe((short) ivFoe);
        _diff.setRateWinMoneyBase(rateWinMoneyBase);
        _diff.setRateLooseMoneyWin(rateLooseMoneyWin);
        _diff.setRestoredMovesEndFight(restoredMovesEndFight);
        _diff.setEnabledClosing(enabledClosing);
        _diff.setRandomWildFight(randomWildFight);
        _diff.setStillPossibleFlee(stillPossibleFlee);
        _diff.setSkipLearningMovesWhileNotGrowingLevel(skipLearningMovesWhileNotGrowingLevel);
        _diff.setDamageRateLawFoe(PokemonStandards.getModelByName(damageRateLawFoe));
        _diff.setDamageRatePlayer(PokemonStandards.getModelByName(damageRatePlayer));
        _diff.validate(_data);
    }

    public StringMap<String> getWinPointsFight() {
        return winPointsFight;
    }

    public String getDiffWinningExpPtsFight() {
        return diffWinningExpPtsFight;
    }

    public void setDiffWinningExpPtsFight(String _diffWinningExpPtsFight) {
        diffWinningExpPtsFight = _diffWinningExpPtsFight;
    }

    public void setAllowCatchingKo(boolean _allowCatchingKo) {
        allowCatchingKo = _allowCatchingKo;
    }

    public boolean getAllowCatchingKo() {
        return allowCatchingKo;
    }

    public void setAllowedSwitchPlacesEndRound(boolean _allowedSwitchPlacesEndRound) {
        allowedSwitchPlacesEndRound = _allowedSwitchPlacesEndRound;
    }

    public boolean getAllowedSwitchPlacesEndRound() {
        return allowedSwitchPlacesEndRound;
    }

    public void setWinTrainerExp(Rate _winTrainerExp) {
        winTrainerExp = _winTrainerExp;
    }

    public Rate getWinTrainerExp() {
        return winTrainerExp;
    }

    public void setRateWinningExpPtsFight(Rate _rateWinningExpPtsFight) {
        rateWinningExpPtsFight = _rateWinningExpPtsFight;
    }

    public Rate getRateWinningExpPtsFight() {
        return rateWinningExpPtsFight;
    }

    public void setEndFightIfOneTeamKo(boolean _endFightIfOneTeamKo) {
        endFightIfOneTeamKo = _endFightIfOneTeamKo;
    }

    public boolean getEndFightIfOneTeamKo() {
        return endFightIfOneTeamKo;
    }

    public void setIvPlayer(int _ivPlayer) {
        ivPlayer = _ivPlayer;
    }

    public int getIvPlayer() {
        return ivPlayer;
    }

    public void setIvFoe(int _ivFoe) {
        ivFoe = _ivFoe;
    }

    public int getIvFoe() {
        return ivFoe;
    }

    public void setRateWinMoneyBase(Rate _rateWinMoneyBase) {
        rateWinMoneyBase = _rateWinMoneyBase;
    }

    public Rate getRateWinMoneyBase() {
        return rateWinMoneyBase;
    }

    public void setRateLooseMoneyWin(Rate _rateLooseMoneyWin) {
        rateLooseMoneyWin = _rateLooseMoneyWin;
    }

    public Rate getRateLooseMoneyWin() {
        return rateLooseMoneyWin;
    }

    public void setRestoredMovesEndFight(boolean _restoredMovesEndFight) {
        restoredMovesEndFight = _restoredMovesEndFight;
    }

    public boolean getRestoredMovesEndFight() {
        return restoredMovesEndFight;
    }

    public void setEnabledClosing(boolean _enabledClosing) {
        enabledClosing = _enabledClosing;
    }

    public boolean getEnabledClosing() {
        return enabledClosing;
    }

    public void setRandomWildFight(boolean _randomWildFight) {
        randomWildFight = _randomWildFight;
    }

    public boolean getRandomWildFight() {
        return randomWildFight;
    }

    public void setStillPossibleFlee(boolean _stillPossibleFlee) {
        stillPossibleFlee = _stillPossibleFlee;
    }

    public boolean getStillPossibleFlee() {
        return stillPossibleFlee;
    }

    public void setSkipLearningMovesWhileNotGrowingLevel(boolean _skipLearningMovesWhileNotGrowingLevel) {
        skipLearningMovesWhileNotGrowingLevel = _skipLearningMovesWhileNotGrowingLevel;
    }

    public boolean getSkipLearningMovesWhileNotGrowingLevel() {
        return skipLearningMovesWhileNotGrowingLevel;
    }

    public StringMap<String> getDamageRates() {
        return damageRates;
    }

    public String getDamageRatePlayer() {
        return damageRatePlayer;
    }

    public void setDamageRatePlayer(String _damageRatePlayer) {
        damageRatePlayer = _damageRatePlayer;
    }

    public TreeMap<Rate,Rate> getDamageRatePlayerTable() {
        return damageRatePlayerTable;
    }

    public String getDamageRateLawFoe() {
        return damageRateLawFoe;
    }

    public void setDamageRateLawFoe(String _damageRateLawFoe) {
        damageRateLawFoe = _damageRateLawFoe;
    }

    public TreeMap<Rate,Rate> getDamageRateFoeTable() {
        return damageRateFoeTable;
    }

}
