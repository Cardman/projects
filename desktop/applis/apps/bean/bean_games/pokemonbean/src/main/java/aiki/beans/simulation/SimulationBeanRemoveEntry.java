package aiki.beans.simulation;

import aiki.beans.*;
import code.util.*;

public final class SimulationBeanRemoveEntry<K,V> implements IntBeanAction {
    private final AbsMap<K,V> index;
    private final K input;
    public SimulationBeanRemoveEntry(AbsMap<K,V> _k, K _ch) {
        index = _k;
        input = _ch;
    }

    @Override
    public String actionBean() {
        index.removeKey(input);
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
}
