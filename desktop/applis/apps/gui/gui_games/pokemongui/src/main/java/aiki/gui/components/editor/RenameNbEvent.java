package aiki.gui.components.editor;

import code.gui.events.*;

public final class RenameNbEvent implements AbsChangeListener {
    private final CrudGeneFormNb crud;

    public RenameNbEvent(CrudGeneFormNb _c) {
        this.crud = _c;
    }

    @Override
    public void stateChanged() {
        crud.tryRename();
    }
}
