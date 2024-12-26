package aiki.gui.components.editor;

import code.gui.events.*;

public class CloseLinksFormEvent implements AbsActionListener {
    private final CrudGeneFormLevelCave crud;
    public CloseLinksFormEvent(CrudGeneFormLevelCave _c) {
        crud = _c;
    }

    @Override
    public void action() {
        for (CrudGeneFormLevel c: crud.getParent().getLevels()) {
            c.cancel();
        }
        crud.getParent().cancel();
    }
}
