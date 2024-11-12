package code.gui;

import code.gui.events.AbsActionListener;

public final class AddCrudGeneFormEvent<E> implements AbsActionListener {
    private final AbsCrudGeneForm<E> crud;

    public AddCrudGeneFormEvent(AbsCrudGeneForm<E> _c) {
        this.crud = _c;
    }

    @Override
    public void action() {
        crud.formAdd();
    }
}
