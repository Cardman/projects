package aiki.beans;

import aiki.beans.simulation.*;

public class PageFormSimu {
    private final SimulationBean simulationBean;

    private int formGroup;
    public PageFormSimu(SimulationBean _s) {
        this.simulationBean = _s;
    }

    public int getFormGroup() {
        return formGroup;
    }

    public void setFormGroup(int _v) {
        this.formGroup = _v;
    }

    public SimulationBean init() {
        return getSimulationBean();
    }

    public SimulationBean getSimulationBean() {
        return simulationBean;
    }
}
