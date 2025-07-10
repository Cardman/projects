package aiki.beans.simulation;

import aiki.beans.*;
import aiki.db.*;
import code.util.*;

public final class SimulationBeanAddEntry<K,V> implements IntBeanAction, IntBeanActionPart {
    private final AbsMap<K,V> map;
    private final IntBeanChgValue<K>  key;
    private final IntBeanChgValue<V> value;
    private final IntBeanActionPartForm alter;
    private final PageFormSimu form;
    public SimulationBeanAddEntry(AbsMap<K, V> _m, IntBeanChgValue<K> _k, IntBeanChgValue<V> _ch, IntBeanActionPartForm _alt, PageFormSimu _page) {
        map = _m;
        key = _k;
        value = _ch;
        alter = _alt;
        form = _page;
    }

    @Override
    public String actionBean() {
        map.addEntry(key.genericValue(), value.genericValue());
        return DataBase.EMPTY_STRING;
    }

    @Override
    public PageFormSimu special() {
        return form.getSimulationBean().endReset(alter.actionBean(form.getSimulationBean().resetFormSimu(form)));
    }
}
