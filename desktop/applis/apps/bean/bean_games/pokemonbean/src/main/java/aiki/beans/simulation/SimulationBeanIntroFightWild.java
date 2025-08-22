package aiki.beans.simulation;

import aiki.beans.*;

public final class SimulationBeanIntroFightWild implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanIntroFightWild(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.introFightWild();
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }


}
