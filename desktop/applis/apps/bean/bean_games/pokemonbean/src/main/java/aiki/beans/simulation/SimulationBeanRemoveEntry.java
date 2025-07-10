package aiki.beans.simulation;

import aiki.beans.*;
import aiki.db.*;
import code.util.*;

public final class SimulationBeanRemoveEntry<K,V> implements IntBeanAction,IntBeanActionPart {
    private final AbsMap<K,V> index;
    private final K input;
    private final IntBeanActionPartForm alter;
    private final PageFormSimu form;
    public SimulationBeanRemoveEntry(AbsMap<K, V> _k, K _ch, IntBeanActionPartForm _alt, PageFormSimu _page) {
        index = _k;
        input = _ch;
        alter = _alt;
        form = _page;
    }

    @Override
    public String actionBean() {
        index.removeKey(input);
        return DataBase.EMPTY_STRING;
    }

    @Override
    public PageFormSimu special() {
        return form.getSimulationBean().endReset(alter.actionBean(form.getSimulationBean().resetFormSimu(form)));
    }
}
