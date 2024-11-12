package code.gui;

import code.gui.events.AbsActionListener;

public final class SelectCrudGeneFormEvent<E> implements AbsActionListener {
    private final AbsCrudGeneForm<E> crud;
    private final int index;

    public SelectCrudGeneFormEvent(AbsCrudGeneForm<E> _c, int _i) {
        this.crud = _c;
        this.index = _i;
    }

    @Override
    public void action() {
        crud.select(index);
    }
}
