package aiki.gui.components.editor;

import code.gui.*;
import code.gui.events.*;

public final class RenameEntPkEvent implements AbsAdvActionListener {
    private final CrudGeneFormTr crud;

    public RenameEntPkEvent(CrudGeneFormTr _c) {
        this.crud = _c;
    }

    @Override
    public void action(AbsCtrlKeyState _state, String _command) {
        crud.tryRename();
    }
}
