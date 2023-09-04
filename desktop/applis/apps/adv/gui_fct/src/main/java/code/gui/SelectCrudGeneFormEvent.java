package code.gui;

import code.gui.events.AbsActionListener;
import code.util.EntryCust;

public final class SelectCrudGeneFormEvent<K,V> implements AbsActionListener {
    private final CrudGeneForm<K,V> crud;
    private final EntryCust<K,V> entry;

    public SelectCrudGeneFormEvent(CrudGeneForm<K, V> _c, EntryCust<K, V> _e) {
        this.crud = _c;
        this.entry = _e;
    }

    @Override
    public void action() {
        crud.select(entry);
    }
}
