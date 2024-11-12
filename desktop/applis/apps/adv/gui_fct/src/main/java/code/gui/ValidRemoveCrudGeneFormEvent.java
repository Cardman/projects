package code.gui;

import code.gui.events.AbsActionListener;

public final class ValidRemoveCrudGeneFormEvent<E> implements AbsActionListener {
    private final AbsCrudGeneForm<E> crud;

    public ValidRemoveCrudGeneFormEvent(AbsCrudGeneForm<E> _c) {
        this.crud = _c;
    }

    @Override
    public void action() {
        crud.validRemove();
    }
}
