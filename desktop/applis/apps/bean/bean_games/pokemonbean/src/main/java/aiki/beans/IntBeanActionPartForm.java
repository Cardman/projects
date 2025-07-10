package aiki.beans;

import aiki.beans.simulation.*;

public abstract class IntBeanActionPartForm {
    private final SimulationBean simu;

    protected IntBeanActionPartForm(SimulationBean _s) {
        this.simu = _s;
    }

    public SimulationBean getSimu() {
        return simu;
    }
    public abstract PageFormSimu actionBean(PageFormSimu _form);
}
