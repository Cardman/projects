package aiki.beans.simulation;

import aiki.beans.*;
import code.util.*;

public final class SimulationBeanAddEntry<K,V> implements IntBeanAction {
    private final AbsMap<K,V> map;
    private final IntBeanChgValue<K>  key;
    private final IntBeanChgValue<V> value;
    public SimulationBeanAddEntry(AbsMap<K,V> _m, IntBeanChgValue<K> _k, IntBeanChgValue<V> _ch) {
        map = _m;
        key = _k;
        value = _ch;
    }

    @Override
    public String actionBean() {
        map.addEntry(key.genericValue(), value.genericValue());
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
}
