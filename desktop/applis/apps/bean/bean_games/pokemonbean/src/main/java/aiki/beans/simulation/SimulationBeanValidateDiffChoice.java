package aiki.beans.simulation;

import aiki.beans.*;
import aiki.beans.game.*;
import code.bean.nat.*;
import code.scripts.confs.*;
import code.util.core.*;

public final class SimulationBeanValidateDiffChoice implements NatCaller, IntBeanAction {
    private final SimulationBean bean;
    private final DifficultyBeanForm form;
    public SimulationBeanValidateDiffChoice() {
        this(null,null);
    }
    public SimulationBeanValidateDiffChoice(SimulationBean _b, DifficultyBeanForm _f) {
        this.bean = _b;
        this.form = _f;
    }

    @Override
    public String actionBean() {
        PokemonBeanStruct b_ = new PokemonBeanStruct(getBean());
        DifficultyCommonBean bean_ = new DifficultyCommonBean();
        bean_.setOwner(bean);
        bean_.beforeDisplaying();
        new DifficultyCommonBeanRateWinningExpPtsFightSet().re(new PokemonBeanStruct(bean_),new NaSt[]{new RtSt(form.getRateWinningExpPtsFight().valueRate())});
        new DifficultyCommonBeanWinTrainerExpSet().re(new PokemonBeanStruct(bean_),new NaSt[]{new RtSt(form.getWinTrainerExp().valueRate())});
        new DifficultyCommonBeanRateWinMoneyBaseSet().re(new PokemonBeanStruct(bean_),new NaSt[]{new RtSt(form.getRateWinMoneyBase().valueRate())});
        new DifficultyCommonBeanRateLooseMoneyWinSet().re(new PokemonBeanStruct(bean_),new NaSt[]{new RtSt(form.getRateLooseMoneyWin().valueRate())});
        new DifficultyCommonBeanDiffWinningExpPtsFightSet().re(new PokemonBeanStruct(bean_),new NaSt[]{new NaStSt(form.getWinPointsFight().tryRet())});
        new DifficultyCommonBeanDamageRatePlayerSet().re(new PokemonBeanStruct(bean_),new NaSt[]{new NaStSt(form.getDamageRatePlayer().tryRet())});
        new DifficultyCommonBeanDamageRateLawFoeSet().re(new PokemonBeanStruct(bean_),new NaSt[]{new NaStSt(form.getDamageRateLawFoe().tryRet())});
        new DifficultyCommonBeanEnabledClosingSet().re(new PokemonBeanStruct(bean_),new NaSt[]{NaBoSt.of(form.getEnabledClosing().isSelected())});
        new DifficultyCommonBeanAllowCatchingKoSet().re(new PokemonBeanStruct(bean_),new NaSt[]{NaBoSt.of(form.getAllowCatchingKo().isSelected())});
        new DifficultyCommonBeanAllowedSwitchPlacesEndRoundSet().re(new PokemonBeanStruct(bean_),new NaSt[]{NaBoSt.of(form.getAllowedSwitchPlacesEndRound().isSelected())});
        new DifficultyCommonBeanSkipLearningMovesWhileNotGrowingLevelSet().re(new PokemonBeanStruct(bean_),new NaSt[]{NaBoSt.of(form.getSkipLearningMovesWhileNotGrowingLevel().isSelected())});
        new DifficultyCommonBeanStillPossibleFleeSet().re(new PokemonBeanStruct(bean_),new NaSt[]{NaBoSt.of(form.getStillPossibleFlee().isSelected())});
        new DifficultyCommonBeanRandomWildFightSet().re(new PokemonBeanStruct(bean_),new NaSt[]{NaBoSt.of(form.getRandomWildFight().isSelected())});
        new DifficultyCommonBeanEndFightIfOneTeamKoSet().re(new PokemonBeanStruct(bean_),new NaSt[]{NaBoSt.of(form.getEndFightIfOneTeamKo().isSelected())});
        new DifficultyCommonBeanRestoredMovesEndFightSet().re(new PokemonBeanStruct(bean_),new NaSt[]{NaBoSt.of(form.getRestoredMovesEndFight().isSelected())});
        new DifficultyCommonBeanIvFoeSet().re(new PokemonBeanStruct(bean_),new NaSt[]{new NaNbSt(form.getIvFoe().valueLong())});
        new DifficultyCommonBeanIvPlayerSet().re(new PokemonBeanStruct(bean_),new NaSt[]{new NaNbSt(form.getIvPlayer().valueLong())});
        new SimulationBeanNbTeamsSet().re(b_,new NaSt[]{new NaNbSt(NumberUtil.max(0,NumberUtil.min(this.bean.getNbTeamsField().valueLong(),Integer.MAX_VALUE)))});
        return ((NaStSt)re(b_,new NaSt[0])).getInstance();
    }

    @Override
    public NaSt re(NaSt _instance, NaSt[] _args){
        ( (SimulationBean) ((PokemonBeanStruct)_instance).getInstance()).validateDiffChoice();
        return new NaStSt(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML);
    }

    @Override
    public CommonBean getBean() {
        return bean;
    }
}
