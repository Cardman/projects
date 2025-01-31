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

    public void displayDiff(AbsBeanRender _rend, AbsPanel _panel, DifficultyCommon _common, AbstractProgramInfos _api, String _file) {
        _rend.formatMessage(_api,_panel,_file,MessagesGameDifficulty.M_P_93_WIN_PTS);
        winPointsFight = select(_rend, _panel, _api, _common.getWinPointsFight(), _common.getDiffWinningExpPtsFight());
        _rend.formatMessage(_api,_panel,_file,MessagesGameDifficulty.M_P_93_ALLOW_CATCHING_KO);
        allowCatchingKo = check(_rend, _panel, _api, _common.getAllowCatchingKo());
        _rend.formatMessage(_api,_panel,_file,MessagesGameDifficulty.M_P_93_ALLOW_SWITCH_PLACES);
        allowedSwitchPlacesEndRound = check(_rend, _panel, _api, _common.getAllowedSwitchPlacesEndRound());
        _rend.formatMessage(_api,_panel,_file,MessagesGameDifficulty.M_P_93_WIN_TRAINER_EXP);
        winTrainerExp = rate(_rend,_panel,_api,_common.getWinTrainerExp());
        _rend.formatMessage(_api,_panel,_file,MessagesGameDifficulty.M_P_93_WINNING_EXP_PTS_FIGHT);
        rateWinningExpPtsFight = rate(_rend,_panel,_api,_common.getRateWinningExpPtsFight());
        _rend.formatMessage(_api,_panel,_file,MessagesGameDifficulty.M_P_93_END_FIGHT);
        endFightIfOneTeamKo = check(_rend,_panel,_api,_common.getEndFightIfOneTeamKo());
        _rend.formatMessage(_api,_panel,_file,MessagesGameDifficulty.M_P_93_IV_PLAYER);
        ivPlayer = iv(_rend, _panel, _api, _common.getIvPlayer());
        _rend.formatMessage(_api,_panel,_file,MessagesGameDifficulty.M_P_93_IV_FOE);
        ivFoe = iv(_rend, _panel, _api, _common.getIvFoe());
        _rend.formatMessage(_api,_panel,_file,MessagesGameDifficulty.M_P_93_RESTORED_MOVES);
        restoredMovesEndFight = check(_rend, _panel, _api, _common.getRestoredMovesEndFight());
        _rend.formatMessage(_api,_panel,_file,MessagesGameDifficulty.M_P_93_CLOSING);
        enabledClosing = check(_rend, _panel, _api, _common.getEnabledClosing());
        _rend.formatMessage(_api,_panel,_file,MessagesGameDifficulty.M_P_93_RANDOM_WILD);
        randomWildFight = check(_rend, _panel, _api, _common.getRandomWildFight());
        _rend.formatMessage(_api,_panel,_file,MessagesGameDifficulty.M_P_93_FLEE);
        stillPossibleFlee = check(_rend, _panel, _api, _common.getStillPossibleFlee());
        _rend.formatMessage(_api,_panel,_file,MessagesGameDifficulty.M_P_93_SKIP_LEARN);
        skipLearningMovesWhileNotGrowingLevel = check(_rend, _panel, _api, _common.getSkipLearningMovesWhileNotGrowingLevel());
        _rend.formatMessage(_api,_panel,_file,MessagesGameDifficulty.M_P_93_LAW_CHOICE_PLAYER);
        damageRatePlayer = select(_rend, _panel, _api, _common.getDamageRates(), _common.getDamageRatePlayer());
        _panel.add(tableView(_rend, _api, _file, _common.getDamageRatePlayerTable()));
        _rend.formatMessage(_api,_panel,_file,MessagesGameDifficulty.M_P_93_LAW_CHOICE_FOE);
        damageRateLawFoe = select(_rend, _panel, _api, _common.getDamageRates(), _common.getDamageRateLawFoe());
        _panel.add(tableView(_rend, _api, _file, _common.getDamageRateFoeTable()));
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
    private AbsPanel tableView(AbsBeanRender _rend, AbstractProgramInfos _api, String _file, DictionaryComparator<Rate, Rate> _info) {
        AbsPanel grid_ = _api.getCompoFactory().newGrid();
        _rend.headerCols(_api,grid_, _file, _info,MessagesGameDifficulty.M_P_93_RATE_DAMAGE_EV,MessagesGameDifficulty.M_P_93_RATE_DAMAGE);
        new BeanDisplayMap<Rate,Rate>(new BeanDisplayRate(),new BeanDisplayRate()).display(_rend, _api,grid_, _info, 2);
        _rend.nextPart();
        return grid_;
    }

    private AbsCustCheckBox check(AbsBeanRender _rend, AbsPanel _panel, AbstractProgramInfos _api, boolean _value) {
        AbsCustCheckBox check_ = _api.getCompoFactory().newCustCheckBox("", _value);
        _rend.feedParents(_panel,check_);
        _rend.nextPart();
        return check_;
    }

    private GeneComponentModelRate rate(AbsBeanRender _rend, AbsPanel _panel, AbstractProgramInfos _api, Rate _value) {
        GeneComponentModelRate rate_ = new GeneComponentModelRate(_api);
        _rend.feedParents(_panel,rate_.geneRate(_value));
        _rend.nextPart();
        return rate_;
    }

    private GeneComponentModelLong iv(AbsBeanRender _rend, AbsPanel _panel, AbstractProgramInfos _api, long _value) {
        GeneComponentModelLong ivPlayer_ = new GeneComponentModelLong(_api);
        ivPlayer_.geneLong().setValue(_value);
        _rend.feedParents(_panel,ivPlayer_.getTextLong());
        _rend.nextPart();
        return ivPlayer_;
    }

    private GeneComponentModelElt<String> select(AbsBeanRender _rend, AbsPanel _panel, AbstractProgramInfos _api, StringMap<String> _map, String _v) {
        GeneComponentModelElt<String> sel_ = new GeneComponentModelElt<String>(_api, _map,new EmptyDefValue());
        _rend.feedParents(_panel,sel_.geneEnum());
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
