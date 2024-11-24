package aiki.gui.components.editor;

import code.gui.events.AbsActionListener;

public final class CrudGeneFormTrCstEvent implements AbsActionListener {
    private final AbsCrudGeneFormTrCst crud;

    public CrudGeneFormTrCstEvent(AbsCrudGeneFormTrCst _c) {
        this.crud = _c;
    }

    @Override
    public void action() {
        crud.update();
    }
}
