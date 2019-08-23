package aiki.game.params;
import aiki.db.DataBase;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.util.*;


public final class Difficulty {

    /***/
    private boolean allowCatchingKo;

    /***/
    private boolean allowedSwitchPlacesEndRound;

    /***/
    private DifficultyWinPointsFight diffWinningExpPtsFight;

    /***/
    private Rate rateWinningExpPtsFight;

    /***/
    private Rate winTrainerExp;


    private DifficultyModelLaw damageRatePlayer;

    private DifficultyModelLaw damageRateLawFoe;

    /***/
    private boolean endFightIfOneTeamKo;

    /***/
    private Rate rateWinMoneyBase;

    /***/
    private Rate rateLooseMoneyWin;

    /***/
    private short ivPlayer;

    /***/
    private short ivFoe;

    /***/
    private boolean stillPossibleFlee;

    private boolean restoredMovesEndFight;

    /**Les attaques dependant des positions sont differentes en fonction de cette variable*/
    private boolean enabledClosing;

    private boolean randomWildFight;

    private boolean skipLearningMovesWhileNotGrowingLevel;

    public Difficulty() {
        allowCatchingKo=true;
        allowedSwitchPlacesEndRound=false;
        rateWinningExpPtsFight=DataBase.defRateProduct();
        winTrainerExp=new Rate(3,2);
        damageRatePlayer=DifficultyModelLaw.CONSTANT_MAX;
        damageRateLawFoe=DifficultyModelLaw.CONSTANT_MIN;
        rateWinMoneyBase=DataBase.defRateProduct();
        rateLooseMoneyWin=DataBase.defRateProduct();
        ivPlayer=31;
        ivFoe=0;
        stillPossibleFlee=true;
        randomWildFight=false;
        restoredMovesEndFight=false;
        diffWinningExpPtsFight=DifficultyWinPointsFight.DIFFICILE;
        endFightIfOneTeamKo=true;
        enabledClosing=true;
        skipLearningMovesWhileNotGrowingLevel = true;
    }

    public void validate(DataBase _data) {
        if (rateWinningExpPtsFight.isZeroOrLt()) {
            rateWinningExpPtsFight=DataBase.defRateProduct();
        }
        if (winTrainerExp.isZeroOrLt()) {
            winTrainerExp=new Rate(3,2);
        }
        if (rateWinMoneyBase.isZeroOrLt()) {
            rateWinMoneyBase=DataBase.defRateProduct();
        }
        if (rateLooseMoneyWin.isZeroOrLt()) {
            rateLooseMoneyWin=DataBase.defRateProduct();
        }
        if (ivPlayer < 0) {
            ivPlayer = 0;
        }
        if (ivPlayer > _data.getMaxIv()) {
            ivPlayer = (short) _data.getMaxIv();
        }
        if (ivFoe < 0) {
            ivFoe = 0;
        }
        if (ivFoe > _data.getMaxIv()) {
            ivFoe = (short) _data.getMaxIv();
        }
    }

    public MonteCarloNumber loi(short _team, short _player, DataBase _import) {
        if (Numbers.eq(_team,_player)) {
            return _import.getLawsDamageRate().getVal(getDamageRatePlayer()).getLaw();
        }
        return _import.getLawsDamageRate().getVal(getDamageRateLawFoe()).getLaw();
    }

    public boolean getAllowCatchingKo() {
        return allowCatchingKo;
    }

    public void setAllowCatchingKo(boolean _allowCatchingKo) {
        allowCatchingKo = _allowCatchingKo;
    }

    public boolean getAllowedSwitchPlacesEndRound() {
        return allowedSwitchPlacesEndRound;
    }

    public void setAllowedSwitchPlacesEndRound(boolean _allowedSwitchPlacesEndRound) {
        allowedSwitchPlacesEndRound = _allowedSwitchPlacesEndRound;
    }

    public DifficultyWinPointsFight getDiffWinningExpPtsFight() {
        return diffWinningExpPtsFight;
    }

    public void setDiffWinningExpPtsFight(DifficultyWinPointsFight _diffWinningExpPtsFight) {
        diffWinningExpPtsFight = _diffWinningExpPtsFight;
    }

    public Rate getRateWinningExpPtsFight() {
        return rateWinningExpPtsFight;
    }

    public void setRateWinningExpPtsFight(Rate _rateWinningExpPtsFight) {
        rateWinningExpPtsFight = _rateWinningExpPtsFight;
    }

    public Rate getWinTrainerExp() {
        return winTrainerExp;
    }

    public void setWinTrainerExp(Rate _winTrainerExp) {
        winTrainerExp = _winTrainerExp;
    }

    public DifficultyModelLaw getDamageRatePlayer() {
        return damageRatePlayer;
    }

    public void setDamageRatePlayer(DifficultyModelLaw _damageRatePlayer) {
        damageRatePlayer = _damageRatePlayer;
    }

    public DifficultyModelLaw getDamageRateLawFoe() {
        return damageRateLawFoe;
    }

    public void setDamageRateLawFoe(DifficultyModelLaw _damageRateLawFoe) {
        damageRateLawFoe = _damageRateLawFoe;
    }

    public boolean getEndFightIfOneTeamKo() {
        return endFightIfOneTeamKo;
    }

    public void setEndFightIfOneTeamKo(boolean _endFightIfOneTeamKo) {
        endFightIfOneTeamKo = _endFightIfOneTeamKo;
    }

    public Rate getRateWinMoneyBase() {
        return rateWinMoneyBase;
    }

    public void setRateWinMoneyBase(Rate _rateWinMoneyBase) {
        rateWinMoneyBase = _rateWinMoneyBase;
    }

    public Rate getRateLooseMoneyWin() {
        return rateLooseMoneyWin;
    }

    public void setRateLooseMoneyWin(Rate _rateLooseMoneyWin) {
        rateLooseMoneyWin = _rateLooseMoneyWin;
    }

    public short getIvPlayer() {
        return ivPlayer;
    }

    public void setIvPlayer(short _ivPlayer) {
        ivPlayer = _ivPlayer;
    }

    public short getIvFoe() {
        return ivFoe;
    }

    public void setIvFoe(short _ivFoe) {
        ivFoe = _ivFoe;
    }

    public boolean getStillPossibleFlee() {
        return stillPossibleFlee;
    }

    public void setStillPossibleFlee(boolean _stillPossibleFlee) {
        stillPossibleFlee = _stillPossibleFlee;
    }

    public boolean getRestoredMovesEndFight() {
        return restoredMovesEndFight;
    }

    public void setRestoredMovesEndFight(boolean _restoredMovesEndFight) {
        restoredMovesEndFight = _restoredMovesEndFight;
    }

    public boolean getEnabledClosing() {
        return enabledClosing;
    }

    public void setEnabledClosing(boolean _enabledClosing) {
        enabledClosing = _enabledClosing;
    }

    public boolean getRandomWildFight() {
        return randomWildFight;
    }

    public void setRandomWildFight(boolean _randomWildFight) {
        randomWildFight = _randomWildFight;
    }

    public boolean isSkipLearningMovesWhileNotGrowingLevel() {
        return skipLearningMovesWhileNotGrowingLevel;
    }

    public void setSkipLearningMovesWhileNotGrowingLevel(boolean _skipLearningMovesWhileNotGrowingLevel) {
        skipLearningMovesWhileNotGrowingLevel = _skipLearningMovesWhileNotGrowingLevel;
    }
}
