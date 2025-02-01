package code.gui.document;

import aiki.beans.*;
import aiki.comparators.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;

public final class DifficultyBeanForm {

    private GeneComponentModelElt<String> winPointsFight;
    private AbsCustCheckBox allowCatchingKo;
    private AbsCustCheckBox allowedSwitchPlacesEndRound;
    private GeneComponentModelRate winTrainerExp;
    private GeneComponentModelRate rateWinningExpPtsFight;
    private AbsCustCheckBox endFightIfOneTeamKo;
    private GeneComponentModelLong ivPlayer;
    private GeneComponentModelLong ivFoe;
    private AbsCustCheckBox restoredMovesEndFight;
    private AbsCustCheckBox enabledClosing;
    private AbsCustCheckBox randomWildFight;
    private AbsCustCheckBox stillPossibleFlee;
    private AbsCustCheckBox skipLearningMovesWhileNotGrowingLevel;
    private GeneComponentModelElt<String> damageRatePlayer;
    private GeneComponentModelElt<String> damageRateLawFoe;

    public void displayDiff(AbsBeanRender _rend, DifficultyCommon _common, AbstractProgramInfos _api, String _file) {
        _rend.formatMessage(_file,MessagesGameDifficulty.M_P_93_WIN_PTS);
        winPointsFight = select(_rend, _api, _common.getWinPointsFight(), _common.getDiffWinningExpPtsFight());
        _rend.formatMessage(_file,MessagesGameDifficulty.M_P_93_ALLOW_CATCHING_KO);
        allowCatchingKo = check(_rend, _api, _common.getAllowCatchingKo());
        _rend.formatMessage(_file,MessagesGameDifficulty.M_P_93_ALLOW_SWITCH_PLACES);
        allowedSwitchPlacesEndRound = check(_rend, _api, _common.getAllowedSwitchPlacesEndRound());
        _rend.formatMessage(_file,MessagesGameDifficulty.M_P_93_WIN_TRAINER_EXP);
        winTrainerExp = rate(_rend, _api,_common.getWinTrainerExp());
        _rend.formatMessage(_file,MessagesGameDifficulty.M_P_93_WINNING_EXP_PTS_FIGHT);
        rateWinningExpPtsFight = rate(_rend, _api,_common.getRateWinningExpPtsFight());
        _rend.formatMessage(_file,MessagesGameDifficulty.M_P_93_END_FIGHT);
        endFightIfOneTeamKo = check(_rend, _api,_common.getEndFightIfOneTeamKo());
        _rend.formatMessage(_file,MessagesGameDifficulty.M_P_93_IV_PLAYER);
        ivPlayer = iv(_rend, _api, _common.getIvPlayer());
        _rend.formatMessage(_file,MessagesGameDifficulty.M_P_93_IV_FOE);
        ivFoe = iv(_rend, _api, _common.getIvFoe());
        _rend.formatMessage(_file,MessagesGameDifficulty.M_P_93_RESTORED_MOVES);
        restoredMovesEndFight = check(_rend, _api, _common.getRestoredMovesEndFight());
        _rend.formatMessage(_file,MessagesGameDifficulty.M_P_93_CLOSING);
        enabledClosing = check(_rend, _api, _common.getEnabledClosing());
        _rend.formatMessage(_file,MessagesGameDifficulty.M_P_93_RANDOM_WILD);
        randomWildFight = check(_rend, _api, _common.getRandomWildFight());
        _rend.formatMessage(_file,MessagesGameDifficulty.M_P_93_FLEE);
        stillPossibleFlee = check(_rend, _api, _common.getStillPossibleFlee());
        _rend.formatMessage(_file,MessagesGameDifficulty.M_P_93_SKIP_LEARN);
        skipLearningMovesWhileNotGrowingLevel = check(_rend, _api, _common.getSkipLearningMovesWhileNotGrowingLevel());
        _rend.formatMessage(_file,MessagesGameDifficulty.M_P_93_LAW_CHOICE_PLAYER);
        damageRatePlayer = select(_rend, _api, _common.getDamageRates(), _common.getDamageRatePlayer());
        tableView(_rend, _file, _common.getDamageRatePlayerTable());
        _rend.feedParents();
        _rend.formatMessage(_file,MessagesGameDifficulty.M_P_93_LAW_CHOICE_FOE);
        damageRateLawFoe = select(_rend, _api, _common.getDamageRates(), _common.getDamageRateLawFoe());
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

    private AbsCustCheckBox check(AbsBeanRender _rend, AbstractProgramInfos _api, boolean _value) {
        AbsCustCheckBox check_ = _api.getCompoFactory().newCustCheckBox("", _value);
        _rend.feedParent(check_);
        _rend.nextPart();
        return check_;
    }

    private GeneComponentModelRate rate(AbsBeanRender _rend, AbstractProgramInfos _api, Rate _value) {
        GeneComponentModelRate rate_ = new GeneComponentModelRate(_api);
        _rend.feedParent(rate_.geneRate(_value));
        _rend.nextPart();
        return rate_;
    }

    private GeneComponentModelLong iv(AbsBeanRender _rend, AbstractProgramInfos _api, long _value) {
        GeneComponentModelLong ivPlayer_ = new GeneComponentModelLong(_api);
        ivPlayer_.geneLong().setValue(_value);
        _rend.feedParent(ivPlayer_.getTextLong());
        _rend.nextPart();
        return ivPlayer_;
    }

    private GeneComponentModelElt<String> select(AbsBeanRender _rend, AbstractProgramInfos _api, StringMap<String> _map, String _v) {
        GeneComponentModelElt<String> sel_ = new GeneComponentModelElt<String>(_api, _map,new EmptyDefValue());
        _rend.feedParent(sel_.geneEnum());
        sel_.setupValue(_v);
        _rend.nextPart();
        return sel_;
    }

    public AbsCustCheckBox getAllowCatchingKo() {
        return allowCatchingKo;
    }

    public AbsCustCheckBox getAllowedSwitchPlacesEndRound() {
        return allowedSwitchPlacesEndRound;
    }

    public AbsCustCheckBox getEndFightIfOneTeamKo() {
        return endFightIfOneTeamKo;
    }

    public AbsCustCheckBox getRestoredMovesEndFight() {
        return restoredMovesEndFight;
    }

    public AbsCustCheckBox getEnabledClosing() {
        return enabledClosing;
    }

    public AbsCustCheckBox getRandomWildFight() {
        return randomWildFight;
    }

    public AbsCustCheckBox getStillPossibleFlee() {
        return stillPossibleFlee;
    }

    public AbsCustCheckBox getSkipLearningMovesWhileNotGrowingLevel() {
        return skipLearningMovesWhileNotGrowingLevel;
    }
}
