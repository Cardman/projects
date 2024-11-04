package aiki.gui.components.editor;

import code.gui.events.*;

public final class RenameNbEvent implements AbsChangeListener {
    private final AbsCrudGeneFormNb crud;

    public RenameNbEvent(AbsCrudGeneFormNb _c) {
        this.crud = _c;
    }

    @Override
    public void stateChanged() {
        crud.tryRename();
    }
}
