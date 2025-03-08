package aiki.beans.simulation;

import aiki.beans.CommonBean;
import aiki.beans.IntBeanAction;

public final class SimulationBeanValidateIndexTeamAction implements IntBeanAction {
    private final SimulationBean bean;
    private final int index;

    public SimulationBeanValidateIndexTeamAction(SimulationBean _b, int _i) {
        this.bean = _b;
        this.index = _i;
    }

    @Override
    public String actionBean() {
        bean.setIndexTeam(index);
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }

}
