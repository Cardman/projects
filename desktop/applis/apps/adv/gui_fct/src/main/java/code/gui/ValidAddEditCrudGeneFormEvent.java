package code.gui;

import code.gui.events.AbsActionListener;

public final class ValidAddEditCrudGeneFormEvent<E> implements AbsActionListener {
    private final AbsCrudGeneForm<E> crud;

    public ValidAddEditCrudGeneFormEvent(AbsCrudGeneForm<E> _c) {
        this.crud = _c;
    }

    @Override
    public void action() {
        crud.validAddEdit();
    }
}
