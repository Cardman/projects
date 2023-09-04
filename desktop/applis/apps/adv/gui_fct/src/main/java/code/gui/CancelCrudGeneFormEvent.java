package code.gui;

import code.gui.events.AbsActionListener;

public final class CancelCrudGeneFormEvent<K,V> implements AbsActionListener {
    private final CrudGeneForm<K,V> crud;

    public CancelCrudGeneFormEvent(CrudGeneForm<K, V> _c) {
        this.crud = _c;
    }

    @Override
    public void action() {
        crud.cancel();
    }
}
