package aiki.gui.components.editor;

import code.gui.events.*;

public final class CloseLinksFormEvent implements AbsActionListener {
    private final CrudGeneFormEntPlace crud;
    public CloseLinksFormEvent(CrudGeneFormEntPlace _c) {
        crud = _c;
    }

    @Override
    public void action() {
        for (CrudGeneFormLevel c: crud.getLevels()) {
            c.cancel();
        }
        crud.cancel();
    }
}
