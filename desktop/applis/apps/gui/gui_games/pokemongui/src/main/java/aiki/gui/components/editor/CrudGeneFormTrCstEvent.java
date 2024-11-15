package aiki.gui.components.editor;

import code.gui.events.AbsActionListener;

public final class CrudGeneFormTrCstEvent<T> implements AbsActionListener {
    private final CrudGeneFormTrCst<T> crud;

    public CrudGeneFormTrCstEvent(CrudGeneFormTrCst<T> _c) {
        this.crud = _c;
    }

    @Override
    public void action() {
        crud.update();
    }
}
