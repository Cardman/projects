package aiki.beans.simulation;

import aiki.beans.*;
import code.util.*;

public final class SimulationBeanValidateFightPosit implements IntBeanAction {
    private final EntryCust<Integer,Integer> index;
    private final IntBeanChgInt input;
    public SimulationBeanValidateFightPosit(EntryCust<Integer, Integer> _k, IntBeanChgInt _ch) {
        index = _k;
        input = _ch;
    }

    @Override
    public String actionBean() {
        SimulationBean.validateFightPositPlayer(index,input);
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
}
