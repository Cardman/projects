package aiki.beans.simulation;

import aiki.beans.*;
import aiki.game.fight.*;

public final class SimulationBeanValidateTeamCoreForm implements IntBeanAction {
    private final SimulationBean bean;
    private final Team team;
    private final IntBeanChgLong nbKo;
    private final IntBeanChgLong nbKoPrevious;
    private final IntBeanChgList<String> successfulMovesRound;

    public SimulationBeanValidateTeamCoreForm(SimulationBean _b, Team _t, IntBeanChgLong _k, IntBeanChgLong _p, IntBeanChgList<String> _s) {
        bean = _b;
        team = _t;
        nbKo = _k;
        nbKoPrevious = _p;
        successfulMovesRound = _s;
    }

    @Override
    public String actionBean() {
        bean.validateTeamCoreForm(team,nbKo,nbKoPrevious, successfulMovesRound);
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }

}
