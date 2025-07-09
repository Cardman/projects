package aiki.beans.simulation;

import aiki.beans.*;
import aiki.db.*;

public final class SimulationBeanValidateFightCoreForm implements IntBeanAction {
    private final SimulationBean bean;

    public SimulationBeanValidateFightCoreForm(SimulationBean _b) {
        bean = _b;
    }

    @Override
    public String actionBean() {
        bean.validateFightCoreForm();
        return DataBase.EMPTY_STRING;
    }

}
