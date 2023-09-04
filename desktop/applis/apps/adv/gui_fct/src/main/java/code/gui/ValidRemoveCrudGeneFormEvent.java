package code.gui;

import code.gui.events.AbsActionListener;

public final class ValidRemoveCrudGeneFormEvent<K,V> implements AbsActionListener {
    private final CrudGeneForm<K,V> crud;

    public ValidRemoveCrudGeneFormEvent(CrudGeneForm<K, V> _c) {
        this.crud = _c;
    }

    @Override
    public void action() {
        crud.validRemove();
    }
}
