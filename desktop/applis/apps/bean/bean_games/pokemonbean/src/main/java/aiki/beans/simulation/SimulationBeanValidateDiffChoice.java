package aiki.beans.simulation;

import aiki.beans.*;
import aiki.beans.game.*;
import code.util.core.*;

public final class SimulationBeanValidateDiffChoice implements IntBeanAction {
    private final SimulationBean bean;
    private final DifficultyBeanForm form;

    public SimulationBeanValidateDiffChoice(SimulationBean _b, DifficultyBeanForm _f) {
        this.bean = _b;
        this.form = _f;
    }

    @Override
    public String actionBean() {
        bean.getDifficultyCommon().setRateWinningExpPtsFight(form.getRateWinningExpPtsFight().valueRate());
        bean.getDifficultyCommon().setWinTrainerExp(form.getWinTrainerExp().valueRate());
        bean.getDifficultyCommon().setRateWinMoneyBase(form.getRateWinMoneyBase().valueRate());
        bean.getDifficultyCommon().setRateLooseMoneyWin(form.getRateLooseMoneyWin().valueRate());
        bean.getDifficultyCommon().setDiffWinningExpPtsFight(form.getWinPointsFight().tryRet());
        bean.getDifficultyCommon().setDamageRatePlayer(form.getDamageRatePlayer().tryRet());
        bean.getDifficultyCommon().setDamageRateLawFoe(form.getDamageRateLawFoe().tryRet());
        bean.getDifficultyCommon().setEnabledClosing(form.getEnabledClosing().isSelected());
        bean.getDifficultyCommon().setAllowCatchingKo(form.getAllowCatchingKo().isSelected());
        bean.getDifficultyCommon().setAllowedSwitchPlacesEndRound(form.getAllowedSwitchPlacesEndRound().isSelected());
        bean.getDifficultyCommon().setSkipLearningMovesWhileNotGrowingLevel(form.getSkipLearningMovesWhileNotGrowingLevel().isSelected());
        bean.getDifficultyCommon().setStillPossibleFlee(form.getStillPossibleFlee().isSelected());
        bean.getDifficultyCommon().setRandomWildFight(form.getRandomWildFight().isSelected());
        bean.getDifficultyCommon().setEndFightIfOneTeamKo(form.getEndFightIfOneTeamKo().isSelected());
        bean.getDifficultyCommon().setRestoredMovesEndFight(form.getRestoredMovesEndFight().isSelected());
        bean.getDifficultyCommon().setIvFoe(form.getIvFoe().valueLong());
        bean.getDifficultyCommon().setIvPlayer(form.getIvPlayer().valueLong());
        bean.setNbTeams((int) NumberUtil.max(0,NumberUtil.min(this.bean.getNbTeamsField().valueLong(),Integer.MAX_VALUE)));
        bean.validateDiffChoice();
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }

}
