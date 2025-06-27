package aiki.beans.simulation;

import aiki.beans.*;
import code.util.*;

public final class SimulationBeanUpdateEntryValue<K,V> implements IntBeanAction {
    private final EntryCust<K,V> index;
    private final IntBeanChgValue<V> input;
    public SimulationBeanUpdateEntryValue(EntryCust<K,V> _k, IntBeanChgValue<V> _ch) {
        index = _k;
        input = _ch;
    }

    @Override
    public String actionBean() {
        index.setValue(input.genericValue());
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
}
