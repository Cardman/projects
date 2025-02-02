package code.gui.document;

import aiki.beans.*;
import aiki.comparators.*;
import code.maths.*;
import code.util.*;

public final class DifficultyBeanForm {

    private IntBeanChgString winPointsFight;
    private IntBeanChgBool allowCatchingKo;
    private IntBeanChgBool allowedSwitchPlacesEndRound;
    private IntBeanChgRate winTrainerExp;
    private IntBeanChgRate rateWinningExpPtsFight;
    private IntBeanChgBool endFightIfOneTeamKo;
    private IntBeanChgLong ivPlayer;
    private IntBeanChgLong ivFoe;
    private IntBeanChgBool restoredMovesEndFight;
    private IntBeanChgBool enabledClosing;
    private IntBeanChgBool randomWildFight;
    private IntBeanChgBool stillPossibleFlee;
    private IntBeanChgBool skipLearningMovesWhileNotGrowingLevel;
    private IntBeanChgString damageRatePlayer;
    private IntBeanChgString damageRateLawFoe;

    public void displayDiff(IntBeanGeneInput _genInput, AbsBeanRender _rend, DifficultyCommon _common, String _file) {
        _rend.formatMessage(_file,MessagesGameDifficulty.M_P_93_WIN_PTS);
        winPointsFight = select(_genInput,_rend, _common.getWinPointsFight(), _common.getDiffWinningExpPtsFight());
        _rend.formatMessage(_file,MessagesGameDifficulty.M_P_93_ALLOW_CATCHING_KO);
        allowCatchingKo = check(_genInput,_rend, _common.getAllowCatchingKo());
        _rend.formatMessage(_file,MessagesGameDifficulty.M_P_93_ALLOW_SWITCH_PLACES);
        allowedSwitchPlacesEndRound = check(_genInput,_rend, _common.getAllowedSwitchPlacesEndRound());
        _rend.formatMessage(_file,MessagesGameDifficulty.M_P_93_WIN_TRAINER_EXP);
        winTrainerExp = rate(_genInput, _rend, _common.getWinTrainerExp());
        _rend.formatMessage(_file,MessagesGameDifficulty.M_P_93_WINNING_EXP_PTS_FIGHT);
        rateWinningExpPtsFight = rate(_genInput, _rend, _common.getRateWinningExpPtsFight());
        _rend.formatMessage(_file,MessagesGameDifficulty.M_P_93_END_FIGHT);
        endFightIfOneTeamKo = check(_genInput,_rend, _common.getEndFightIfOneTeamKo());
        _rend.formatMessage(_file,MessagesGameDifficulty.M_P_93_IV_PLAYER);
        ivPlayer = iv(_genInput, _rend, _common.getIvPlayer());
        _rend.formatMessage(_file,MessagesGameDifficulty.M_P_93_IV_FOE);
        ivFoe = iv(_genInput, _rend, _common.getIvFoe());
        _rend.formatMessage(_file,MessagesGameDifficulty.M_P_93_RESTORED_MOVES);
        restoredMovesEndFight = check(_genInput,_rend, _common.getRestoredMovesEndFight());
        _rend.formatMessage(_file,MessagesGameDifficulty.M_P_93_CLOSING);
        enabledClosing = check(_genInput,_rend, _common.getEnabledClosing());
        _rend.formatMessage(_file,MessagesGameDifficulty.M_P_93_RANDOM_WILD);
        randomWildFight = check(_genInput,_rend, _common.getRandomWildFight());
        _rend.formatMessage(_file,MessagesGameDifficulty.M_P_93_FLEE);
        stillPossibleFlee = check(_genInput,_rend, _common.getStillPossibleFlee());
        _rend.formatMessage(_file,MessagesGameDifficulty.M_P_93_SKIP_LEARN);
        skipLearningMovesWhileNotGrowingLevel = check(_genInput,_rend, _common.getSkipLearningMovesWhileNotGrowingLevel());
        _rend.formatMessage(_file,MessagesGameDifficulty.M_P_93_LAW_CHOICE_PLAYER);
        damageRatePlayer = select(_genInput,_rend, _common.getDamageRates(), _common.getDamageRatePlayer());
        tableView(_rend, _file, _common.getDamageRatePlayerTable());
        _rend.feedParents();
        _rend.formatMessage(_file,MessagesGameDifficulty.M_P_93_LAW_CHOICE_FOE);
        damageRateLawFoe = select(_genInput,_rend, _common.getDamageRates(), _common.getDamageRateLawFoe());
        tableView(_rend, _file, _common.getDamageRateFoeTable());
        _rend.feedParents();
    }

    public void update(DifficultyCommon _common) {
        _common.setDiffWinningExpPtsFight(winPointsFight.tryRet());
        _common.setAllowCatchingKo(allowCatchingKo.isSelected());
        _common.setAllowedSwitchPlacesEndRound(allowedSwitchPlacesEndRound.isSelected());
        _common.setWinTrainerExp(winTrainerExp.valueRate());
        _common.setRateWinningExpPtsFight(rateWinningExpPtsFight.valueRate());
        _common.setEndFightIfOneTeamKo(endFightIfOneTeamKo.isSelected());
        _common.setIvPlayer(ivPlayer.valueLong());
        _common.setIvFoe(ivFoe.valueLong());
        _common.setRestoredMovesEndFight(restoredMovesEndFight.isSelected());
        _common.setEnabledClosing(enabledClosing.isSelected());
        _common.setRandomWildFight(randomWildFight.isSelected());
        _common.setStillPossibleFlee(stillPossibleFlee.isSelected());
        _common.setSkipLearningMovesWhileNotGrowingLevel(skipLearningMovesWhileNotGrowingLevel.isSelected());
        _common.setDamageRatePlayer(damageRatePlayer.tryRet());
        _common.setDamageRateLawFoe(damageRateLawFoe.tryRet());
    }
    private void tableView(AbsBeanRender _rend, String _file, DictionaryComparator<Rate, Rate> _info) {
        _rend.initGrid();
        _rend.headerCols(_file, _info,MessagesGameDifficulty.M_P_93_RATE_DAMAGE_EV,MessagesGameDifficulty.M_P_93_RATE_DAMAGE);
        new BeanDisplayMap<Rate,Rate>(new BeanDisplayRate(),new BeanDisplayRate()).display(_rend, _info);
        _rend.nextPart();
    }

    private IntBeanChgBool check(IntBeanGeneInput _genInput, AbsBeanRender _rend, boolean _value) {
        IntBeanChgBool check_ = _genInput.newBool();
        check_.setSelected(_value);
        _rend.nextPart();
        return check_;
    }

    private IntBeanChgRate rate(IntBeanGeneInput _genInput, AbsBeanRender _rend, Rate _value) {
        IntBeanChgRate rate_ = _genInput.newRate();
        rate_.valueRate(_value);
        _rend.nextPart();
        return rate_;
    }

    private IntBeanChgLong iv(IntBeanGeneInput _genInput, AbsBeanRender _rend, long _value) {
        IntBeanChgLong ivPlayer_ = _genInput.newLong();
        ivPlayer_.valueLong(_value);
        _rend.nextPart();
        return ivPlayer_;
    }

    private IntBeanChgString select(IntBeanGeneInput _genInput, AbsBeanRender _rend, StringMap<String> _map, String _v) {
        IntBeanChgString sel_ = _genInput.newString(_map);
        sel_.setupValue(_v);
        _rend.nextPart();
        return sel_;
    }

    public IntBeanChgBool getAllowCatchingKo() {
        return allowCatchingKo;
    }

    public IntBeanChgBool getAllowedSwitchPlacesEndRound() {
        return allowedSwitchPlacesEndRound;
    }

    public IntBeanChgBool getEndFightIfOneTeamKo() {
        return endFightIfOneTeamKo;
    }

    public IntBeanChgBool getRestoredMovesEndFight() {
        return restoredMovesEndFight;
    }

    public IntBeanChgBool getEnabledClosing() {
        return enabledClosing;
    }

    public IntBeanChgBool getRandomWildFight() {
        return randomWildFight;
    }

    public IntBeanChgBool getStillPossibleFlee() {
        return stillPossibleFlee;
    }

    public IntBeanChgBool getSkipLearningMovesWhileNotGrowingLevel() {
        return skipLearningMovesWhileNotGrowingLevel;
    }
}
