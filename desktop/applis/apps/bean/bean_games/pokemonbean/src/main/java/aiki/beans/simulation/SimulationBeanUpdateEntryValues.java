package aiki.beans.simulation;

import aiki.beans.*;
import code.util.*;

public final class SimulationBeanUpdateEntryValues<K,V> {
    private final AbsMap<K,V> index;
    private final AbsMap<K,IntBeanChgValue<V>> input;
    public SimulationBeanUpdateEntryValues(AbsMap<K,V> _k, AbsMap<K,IntBeanChgValue<V>> _ch) {
        index = _k;
        input = _ch;
    }

    public void actionBean() {
        for (EntryCust<K,V> e: index.entryList()) {
            e.setValue(input.getVal(e.getKey()).genericValue());
        }

    }
    public AbsMap<K, IntBeanChgValue<V>> getInput() {
        return input;
    }
}
