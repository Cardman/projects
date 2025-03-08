package aiki.beans.simulation;

import aiki.beans.IntBeanAction;
import code.scripts.confs.PkScriptPages;

public final class SimulationBeanValidateMultiplicityEnvAction implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanValidateMultiplicityEnvAction(SimulationBean _b) {
        this.bean = _b;
    }

    @Override
    public String actionBean() {
        bean.setMultiplicity((int) bean.getMultiplicity().valueLong());
        bean.setEnvironment(bean.getEnvironment().tryRet());
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }

}
