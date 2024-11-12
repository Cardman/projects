package code.gui;

import code.gui.events.AbsActionListener;

public final class CancelCrudGeneFormEvent<E> implements AbsActionListener {
    private final AbsCrudGeneForm<E> crud;

    public CancelCrudGeneFormEvent(AbsCrudGeneForm<E> _c) {
        this.crud = _c;
    }

    @Override
    public void action() {
        crud.cancel();
    }
}
