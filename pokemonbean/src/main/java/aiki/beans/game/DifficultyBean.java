package aiki.beans.game;
import aiki.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.params.Difficulty;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
import code.bean.Accessible;
import code.bean.Bean;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.CustList;
import code.util.EnumMap;
import code.util.NatCmpTreeMap;
import code.util.NatTreeMap;

public class DifficultyBean extends Bean {

    @Accessible
    private boolean allowCatchingKo;

    @Accessible
    private boolean allowedSwitchPlacesEndRound;

    @Accessible
    private DifficultyWinPointsFight diffWinningExpPtsFight;

    @Accessible
    private NatTreeMap<DifficultyWinPointsFight, String> winPointsFight;

    @Accessible
    private Rate rateWinningExpPtsFight;

    @Accessible
    private Rate winTrainerExp;

    @Accessible
    private NatTreeMap<DifficultyModelLaw, String> damageRates;

    @Accessible
    private DifficultyModelLaw damageRatePlayer;

    @Accessible
    private NatCmpTreeMap<Rate,Rate> damageRatePlayerTable;

    @Accessible
    private DifficultyModelLaw damageRateLawFoe;

    @Accessible
    private NatCmpTreeMap<Rate,Rate> damageRateFoeTable;

    @Accessible
    private boolean endFightIfOneTeamKo;

    @Accessible
    private Rate rateWinMoneyBase;

    @Accessible
    private Rate rateLooseMoneyWin;

    @Accessible
    private short ivPlayer;

    @Accessible
    private short ivFoe;

    @Accessible
    private boolean stillPossibleFlee;

    @Accessible
    private boolean restoredMovesEndFight;

    @Accessible
    private boolean enabledClosing;

    @Accessible
    private boolean randomWildFight;

    @Accessible
    private boolean skipLearningMovesWhileNotGrowingLevel;

    @Accessible
    private short index = CustList.INDEX_NOT_FOUND_ELT;

//    @Accessible
//    private CustList<Integer> numbers = new CustList<>();

    @Override
    public void beforeDisplaying() {
        FacadeGame facadeGame_ = (FacadeGame) getDataBase();
        DataBase data_ = facadeGame_.getData();
        Difficulty diff_ = facadeGame_.getGame().getDifficulty();
        damageRates = new NatTreeMap<DifficultyModelLaw, String>();
        winPointsFight = new NatTreeMap<DifficultyWinPointsFight, String>();
        EnumMap<DifficultyWinPointsFight, String> trWinPts_ = data_.getTranslatedDiffWinPts().getVal(getLanguage());
        for (DifficultyWinPointsFight k: trWinPts_.getKeys()) {
//            winPointsFight.put(k, XmlParser.transformSpecialChars(trWinPts_.getVal(k)));
            winPointsFight.put(k, trWinPts_.getVal(k));
        }
        EnumMap<DifficultyModelLaw, String> trWinLaw_ = data_.getTranslatedDiffModelLaw().getVal(getLanguage());
        for (DifficultyModelLaw k: trWinLaw_.getKeys()) {
//            damageRates.put(k, XmlParser.transformSpecialChars(trWinLaw_.getVal(k)));
            damageRates.put(k, trWinLaw_.getVal(k));
        }

//        damageRates = new TreeMap<new>(new);
//        winPointsFight = new TreeMap<new>(new);
//        Map<DifficultyWinPointsFight, String> trWinPts_ = data_.getTranslatedDiffWinPts().getVal(getLanguage());
//        winPointsFight.putAll(trWinPts_);
//        damageRates.putAll(data_.getTranslatedDiffModelLaw().getVal(getLanguage()));
        diffWinningExpPtsFight = diff_.getDiffWinningExpPtsFight();
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
        damageRatePlayer = diff_.getDamageRatePlayer();
        damageRateLawFoe = diff_.getDamageRateLawFoe();
        damageRatePlayerTable = new NatCmpTreeMap<Rate, Rate>();
        MonteCarloNumber law_;
        law_ = data_.getLawsDamageRate().getVal(damageRatePlayer).getLaw();
        for (Rate e: law_.events()) {
            damageRatePlayerTable.put(e, law_.normalizedRate(e));
        }
        damageRateFoeTable = new NatCmpTreeMap<Rate, Rate>();
        law_ = data_.getLawsDamageRate().getVal(damageRateLawFoe).getLaw();
        for (Rate e: law_.events()) {
            damageRateFoeTable.put(e, law_.normalizedRate(e));
        }
    }

    @Accessible
    private void change() {
        FacadeGame facadeGame_ = (FacadeGame) getDataBase();
        Difficulty diff_ = facadeGame_.getGame().getDifficulty();
        diff_.setDiffWinningExpPtsFight(diffWinningExpPtsFight);
        diff_.setAllowCatchingKo(allowCatchingKo);
        diff_.setAllowedSwitchPlacesEndRound(allowedSwitchPlacesEndRound);
        diff_.setWinTrainerExp(winTrainerExp);
        diff_.setRateWinningExpPtsFight(rateWinningExpPtsFight);
        diff_.setEndFightIfOneTeamKo(endFightIfOneTeamKo);
        diff_.setIvPlayer(ivPlayer);
        diff_.setIvFoe(ivFoe);
        diff_.setRateWinMoneyBase(rateWinMoneyBase);
        diff_.setRateLooseMoneyWin(rateLooseMoneyWin);
        diff_.setRestoredMovesEndFight(restoredMovesEndFight);
        diff_.setEnabledClosing(enabledClosing);
        diff_.setRandomWildFight(randomWildFight);
        diff_.setStillPossibleFlee(stillPossibleFlee);
        diff_.setSkipLearningMovesWhileNotGrowingLevel(skipLearningMovesWhileNotGrowingLevel);
        diff_.setDamageRateLawFoe(damageRateLawFoe);
        diff_.setDamageRatePlayer(damageRatePlayer);
        diff_.validate(facadeGame_.getData());
    }
}
