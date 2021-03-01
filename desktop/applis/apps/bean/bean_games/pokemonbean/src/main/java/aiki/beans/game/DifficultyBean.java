package aiki.beans.game;
import aiki.beans.PokemonStandards;
import aiki.beans.WithFacade;
import aiki.beans.facade.comparators.ComparatorDifficultyModelLaw;
import aiki.beans.facade.comparators.ComparatorDifficultyWinPointsFight;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.params.Difficulty;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
import code.bean.Bean;
import code.maths.ComparatorLgInt;
import code.maths.ComparatorRate;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.EnumMap;
import code.util.NatCmpTreeMap;
import code.util.TreeMap;

public class DifficultyBean extends Bean implements WithFacade {
    private boolean allowCatchingKo;
    private boolean allowedSwitchPlacesEndRound;
    private String diffWinningExpPtsFight;
    private TreeMap<String, String> winPointsFight;
    private Rate rateWinningExpPtsFight;
    private Rate winTrainerExp;
    private TreeMap<String, String> damageRates;
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

    private FacadeGame dataBase;

    public FacadeGame getDataBase() {
        return db();
    }

    @Override
    public FacadeGame db() {
        return dataBase;
    }

    @Override
    public void setDataBase(FacadeGame _dataBase) {
        dataBase = _dataBase;
    }

    @Override
    public void beforeDisplaying() {
        FacadeGame facadeGame_ = getDataBase();
        DataBase data_ = facadeGame_.getData();
        Difficulty diff_ = facadeGame_.getGame().getDifficulty();
        damageRates = new TreeMap<String, String>(new ComparatorDifficultyModelLaw());
        winPointsFight = new TreeMap<String, String>(new ComparatorDifficultyWinPointsFight());
        EnumMap<DifficultyWinPointsFight, String> trWinPts_ = data_.getTranslatedDiffWinPts().getVal(getLanguage());
        for (DifficultyWinPointsFight k: trWinPts_.getKeys()) {
//            winPointsFight.put(k, XmlParser.transformSpecialChars(trWinPts_.getVal(k)));
            winPointsFight.put(k.name(), trWinPts_.getVal(k));
        }
        EnumMap<DifficultyModelLaw, String> trWinLaw_ = data_.getTranslatedDiffModelLaw().getVal(getLanguage());
        for (DifficultyModelLaw k: trWinLaw_.getKeys()) {
//            damageRates.put(k, XmlParser.transformSpecialChars(trWinLaw_.getVal(k)));
            damageRates.put(k.name(), trWinLaw_.getVal(k));
        }

//        damageRates = new TreeMap<new>(new);
//        winPointsFight = new TreeMap<new>(new);
//        Map<DifficultyWinPointsFight, String> trWinPts_ = data_.getTranslatedDiffWinPts().getVal(getLanguage());
//        winPointsFight.putAll(trWinPts_);
//        damageRates.putAll(data_.getTranslatedDiffModelLaw().getVal(getLanguage()));
        diffWinningExpPtsFight = diff_.getDiffWinningExpPtsFight().name();
        allowCatchingKo = diff_.getAllowCatchingKo();
        allowedSwitchPlacesEndRound = diff_.getAllowedSwitchPlacesEndRound();
        winTrainerExp = diff_.getWinTrainerExp();
        rateWinningExpPtsFight = diff_.getRateWinningExpPtsFight();
        endFightIfOneTeamKo = diff_.getEndFightIfOneTeamKo();
        ivPlayer = diff_.getIvPlayer();
        ivFoe = diff_.getIvFoe();
        rateWinMoneyBase = diff_.getRateWinMoneyBase();
        rateLooseMoneyWin = diff_.getRateLooseMoneyWin();
        stillPossibleFlee = diff_.getStillPossibleFlee();
        restoredMovesEndFight = diff_.getRestoredMovesEndFight();
        enabledClosing = diff_.getEnabledClosing();
        randomWildFight = diff_.getRandomWildFight();
        skipLearningMovesWhileNotGrowingLevel = diff_.isSkipLearningMovesWhileNotGrowingLevel();
        damageRatePlayer = diff_.getDamageRatePlayer().name();
        damageRateLawFoe = diff_.getDamageRateLawFoe().name();
        damageRatePlayerTable = new TreeMap<Rate, Rate>(new ComparatorRate());
        MonteCarloNumber law_;
        law_ = data_.getLawsDamageRate().getVal(PokemonStandards.getModelByName(damageRatePlayer)).getLaw();
        for (Rate e: law_.events()) {
            damageRatePlayerTable.put(e, law_.normalizedRate(e));
        }
        damageRateFoeTable = new TreeMap<Rate, Rate>(new ComparatorRate());
        law_ = data_.getLawsDamageRate().getVal(PokemonStandards.getModelByName(damageRateLawFoe)).getLaw();
        for (Rate e: law_.events()) {
            damageRateFoeTable.put(e, law_.normalizedRate(e));
        }
    }
    public void change() {
        FacadeGame facadeGame_ = getDataBase();
        Difficulty diff_ = facadeGame_.getGame().getDifficulty();
        diff_.setDiffWinningExpPtsFight(PokemonStandards.getDiffWonPtsByName(diffWinningExpPtsFight));
        diff_.setAllowCatchingKo(allowCatchingKo);
        diff_.setAllowedSwitchPlacesEndRound(allowedSwitchPlacesEndRound);
        diff_.setWinTrainerExp(winTrainerExp);
        diff_.setRateWinningExpPtsFight(rateWinningExpPtsFight);
        diff_.setEndFightIfOneTeamKo(endFightIfOneTeamKo);
        diff_.setIvPlayer((short) ivPlayer);
        diff_.setIvFoe((short) ivFoe);
        diff_.setRateWinMoneyBase(rateWinMoneyBase);
        diff_.setRateLooseMoneyWin(rateLooseMoneyWin);
        diff_.setRestoredMovesEndFight(restoredMovesEndFight);
        diff_.setEnabledClosing(enabledClosing);
        diff_.setRandomWildFight(randomWildFight);
        diff_.setStillPossibleFlee(stillPossibleFlee);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(skipLearningMovesWhileNotGrowingLevel);
        diff_.setDamageRateLawFoe(PokemonStandards.getModelByName(damageRateLawFoe));
        diff_.setDamageRatePlayer(PokemonStandards.getModelByName(damageRatePlayer));
        diff_.validate(facadeGame_.getData());
    }

    public TreeMap<String,String> getWinPointsFight() {
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

    public TreeMap<String,String> getDamageRates() {
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