package code.gui;

import code.gui.events.*;

public final class SelectCrudGeneFormEvent<E> implements AbsActionListener {
    private final AbsCrudGeneFormList<E> crud;
    private final int index;

    public SelectCrudGeneFormEvent(AbsCrudGeneFormList<E> _c, int _i) {
        this.crud = _c;
        this.index = _i;
    }

    @Override
    public void action() {
        crud.select(index);
    }
}
