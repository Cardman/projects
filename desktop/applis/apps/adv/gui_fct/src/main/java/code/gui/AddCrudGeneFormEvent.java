package code.gui;

import code.gui.events.AbsActionListener;

public final class AddCrudGeneFormEvent<K,V> implements AbsActionListener {
    private final CrudGeneForm<K,V> crud;

    public AddCrudGeneFormEvent(CrudGeneForm<K, V> _c) {
        this.crud = _c;
    }

    @Override
    public void action() {
        crud.formAdd();
    }
}
