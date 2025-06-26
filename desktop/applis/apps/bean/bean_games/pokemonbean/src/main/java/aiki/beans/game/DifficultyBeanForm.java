package aiki.beans.game;

import aiki.beans.*;
import aiki.comparators.*;
import code.maths.*;
import code.scripts.pages.aiki.*;
import code.util.*;
import code.util.comparators.*;
import code.util.core.*;

public final class DifficultyBeanForm {

    private IntBeanChgString winPointsFight;
    private IntBeanChgBool allowCatchingKo;
    private IntBeanChgBool allowedSwitchPlacesEndRound;
    private IntBeanChgRate winTrainerExp;
    private IntBeanChgRate rateWinningExpPtsFight;
    private IntBeanChgBool endFightIfOneTeamKo;
    private IntBeanChgLong ivPlayer;
    private IntBeanChgLong ivFoe;
    private IntBeanChgRate rateWinMoneyBase;
    private IntBeanChgRate rateLooseMoneyWin;
    private IntBeanChgBool restoredMovesEndFight;
    private IntBeanChgBool enabledClosing;
    private IntBeanChgBool randomWildFight;
    private IntBeanChgBool stillPossibleFlee;
    private IntBeanChgBool skipLearningMovesWhileNotGrowingLevel;
    private IntBeanChgString damageRatePlayer;
    private IntBeanChgString damageRateLawFoe;
    public void displayDiff(IntBeanGeneInput _genInput, CommonBean _rend, DifficultyCommon _common, String _file) {
        formatMessage(_rend, _file, MessagesGameDifficulty.M_P_93_WIN_PTS);
        winPointsFight = select(_genInput,_rend, _common.getWinPointsFight(), _common.getDiffWinningExpPtsFight());
        formatMessage(_rend, _file, MessagesGameDifficulty.M_P_93_ALLOW_CATCHING_KO);
        allowCatchingKo = check(_genInput,_rend, _common.getAllowCatchingKo());
        formatMessage(_rend, _file, MessagesGameDifficulty.M_P_93_ALLOW_SWITCH_PLACES);
        allowedSwitchPlacesEndRound = check(_genInput,_rend, _common.getAllowedSwitchPlacesEndRound());
        formatMessage(_rend, _file, MessagesGameDifficulty.M_P_93_WIN_TRAINER_EXP);
        winTrainerExp = rate(_genInput, _rend, _common.getWinTrainerExp());
        formatMessage(_rend, _file, MessagesGameDifficulty.M_P_93_WINNING_EXP_PTS_FIGHT);
        rateWinningExpPtsFight = rate(_genInput, _rend, _common.getRateWinningExpPtsFight());
        formatMessage(_rend, _file, MessagesGameDifficulty.M_P_93_END_FIGHT);
        endFightIfOneTeamKo = check(_genInput,_rend, _common.getEndFightIfOneTeamKo());
        formatMessage(_rend, _file, MessagesGameDifficulty.M_P_93_IV_PLAYER);
        ivPlayer = iv(_genInput, _rend, _common.getIvPlayer());
        formatMessage(_rend, _file, MessagesGameDifficulty.M_P_93_IV_FOE);
        ivFoe = iv(_genInput, _rend, _common.getIvFoe());
        formatMessage(_rend, _file, MessagesGameDifficulty.M_P_93_RATE_WIN_MONEY_BASE);
        rateWinMoneyBase = rate(_genInput, _rend, _common.getRateWinMoneyBase());
        formatMessage(_rend, _file, MessagesGameDifficulty.M_P_93_RATE_WIN_MONEY_LOOSE);
        rateLooseMoneyWin = rate(_genInput, _rend, _common.getRateLooseMoneyWin());
        formatMessage(_rend, _file, MessagesGameDifficulty.M_P_93_RESTORED_MOVES);
        restoredMovesEndFight = check(_genInput,_rend, _common.getRestoredMovesEndFight());
        formatMessage(_rend, _file, MessagesGameDifficulty.M_P_93_CLOSING);
        enabledClosing = check(_genInput,_rend, _common.getEnabledClosing());
        formatMessage(_rend, _file, MessagesGameDifficulty.M_P_93_RANDOM_WILD);
        randomWildFight = check(_genInput,_rend, _common.getRandomWildFight());
        formatMessage(_rend, _file, MessagesGameDifficulty.M_P_93_FLEE);
        stillPossibleFlee = check(_genInput,_rend, _common.getStillPossibleFlee());
        formatMessage(_rend, _file, MessagesGameDifficulty.M_P_93_SKIP_LEARN);
        skipLearningMovesWhileNotGrowingLevel = check(_genInput,_rend, _common.getSkipLearningMovesWhileNotGrowingLevel());
        formatMessage(_rend, _file, MessagesGameDifficulty.M_P_93_LAW_CHOICE_PLAYER);
        damageRatePlayer = select(_genInput,_rend, _common.getDamageRates(), _common.getDamageRatePlayer());
        tableView(_rend, _file, _common.getDamageRatePlayerTable());
        formatMessage(_rend, _file, MessagesGameDifficulty.M_P_93_LAW_CHOICE_FOE);
        damageRateLawFoe = select(_genInput,_rend, _common.getDamageRates(), _common.getDamageRateLawFoe());
        tableView(_rend, _file, _common.getDamageRateFoeTable());
    }

    public static void formatMessage(CommonBean _rend, String _file, String _key) {
        _rend.formatMessage(_file, _key);
        _rend.getBuilder().nextPart();
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
        _common.setRateWinMoneyBase(rateWinMoneyBase.valueRate());
        _common.setRateLooseMoneyWin(rateLooseMoneyWin.valueRate());
        _common.setRestoredMovesEndFight(restoredMovesEndFight.isSelected());
        _common.setEnabledClosing(enabledClosing.isSelected());
        _common.setRandomWildFight(randomWildFight.isSelected());
        _common.setStillPossibleFlee(stillPossibleFlee.isSelected());
        _common.setSkipLearningMovesWhileNotGrowingLevel(skipLearningMovesWhileNotGrowingLevel.isSelected());
        _common.setDamageRatePlayer(damageRatePlayer.tryRet());
        _common.setDamageRateLawFoe(damageRateLawFoe.tryRet());
    }
    private void tableView(CommonBean _rend, String _file, DictionaryComparator<Rate, Rate> _info) {
        new BeanDisplayMap<Rate,Rate>(new BeanDisplayRate(),new BeanDisplayRate()).displayGrid(_rend, _info,_file,"",MessagesGameDifficulty.M_P_93_RATE_DAMAGE_EV,MessagesGameDifficulty.M_P_93_RATE_DAMAGE);
    }

    public static IntBeanChgBool check(IntBeanGeneInput _genInput, CommonBean _rend, boolean _value) {
        return check(_genInput, _rend, ComparatorBoolean.of(_value));
    }

    public static IntBeanChgBool check(IntBeanGeneInput _genInput, CommonBean _rend, BoolVal _value) {
        IntBeanChgBool check_ = _genInput.newBool();
        check_.setSelected(_value == BoolVal.TRUE);
        _rend.getBuilder().nextPart();
        return check_;
    }

    public static IntBeanChgRate rate(IntBeanGeneInput _genInput, CommonBean _rend, Rate _value) {
        IntBeanChgRate rate_ = _genInput.newRate();
        rate_.valueRate(_value);
        _rend.getBuilder().nextPart();
        return rate_;
    }

    public static IntBeanChgLgInt lgInt(IntBeanGeneInput _genInput, CommonBean _rend, LgInt _value) {
        IntBeanChgLgInt rate_ = _genInput.newLgInt();
        rate_.valueLgInt(_value);
        _rend.getBuilder().nextPart();
        return rate_;
    }

    public static IntBeanChgLong iv(IntBeanGeneInput _genInput, CommonBean _rend, long _value) {
        IntBeanChgLong ivPlayer_ = _genInput.newLong();
        ivPlayer_.valueLong(_value);
        _rend.getBuilder().nextPart();
        return ivPlayer_;
    }

    public static IntBeanChgString select(IntBeanGeneInput _genInput, CommonBean _rend, AbsMap<String,String> _map, String _v) {
        IntBeanChgString sel_ = _genInput.newString(_map);
        sel_.setupValue(_v);
        _rend.getBuilder().nextPart();
        return sel_;
    }

    public static IntBeanChgStringList selectLs(IntBeanGeneInput _genInput, CommonBean _rend, AbsMap<String,String> _map, StringList _v) {
        IntBeanChgStringList sel_ = _genInput.newStringList(_map);
        sel_.setupValue(_v);
        _rend.getBuilder().nextPart();
        return sel_;
    }

    public static IntBeanChgInt selectInt(IntBeanGeneInput _genInput, CommonBean _rend, AbsMap<Integer,String> _map, int _v) {
        IntBeanChgInt sel_ = _genInput.newInt(_map);
        sel_.valueInt(_v);
        _rend.getBuilder().nextPart();
        return sel_;
    }

    public static IntBeanChgString txt(IntBeanGeneInput _genInput, CommonBean _rend, String _v) {
        IntBeanChgString sel_ = _genInput.newText();
        sel_.setupValue(_v);
        _rend.getBuilder().nextPart();
        return sel_;
    }

    public IntBeanChgString getWinPointsFight() {
        return winPointsFight;
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

    public IntBeanChgLong getIvFoe() {
        return ivFoe;
    }

    public IntBeanChgLong getIvPlayer() {
        return ivPlayer;
    }

    public IntBeanChgRate getRateWinMoneyBase() {
        return rateWinMoneyBase;
    }

    public IntBeanChgRate getRateLooseMoneyWin() {
        return rateLooseMoneyWin;
    }

    public IntBeanChgRate getRateWinningExpPtsFight() {
        return rateWinningExpPtsFight;
    }

    public IntBeanChgRate getWinTrainerExp() {
        return winTrainerExp;
    }

    public IntBeanChgString getDamageRateLawFoe() {
        return damageRateLawFoe;
    }

    public IntBeanChgString getDamageRatePlayer() {
        return damageRatePlayer;
    }
}
