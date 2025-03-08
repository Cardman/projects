package aiki.beans.game;

import aiki.beans.*;
import aiki.beans.simulation.*;
import code.util.core.*;

public final class SimulationBeanValidDiffFormEvent implements IntBeanAction {
    private final SimulationBean bean;
    private final DifficultyBeanForm form;
    public SimulationBeanValidDiffFormEvent(SimulationBean _b, DifficultyBeanForm _f) {
        this.bean = _b;
        this.form = _f;
    }

    @Override
    public String actionBean() {
        form.update(bean.getDifficultyCommon());
        bean.setNbTeams((int) NumberUtil.max(0,NumberUtil.min(bean.getNbTeamsField().valueLong(),Integer.MAX_VALUE)));
        bean.validateDiffChoice();
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }

}
