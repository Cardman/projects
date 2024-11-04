package aiki.gui.components.editor;

import code.gui.*;
import code.gui.events.*;

public final class PkEditorOpenCrudNbEvent implements AbsActionListener {
    private final AbsCrudGeneFormNb window;
    private final EnabledMenu menu;

    public PkEditorOpenCrudNbEvent(AbsCrudGeneFormNb _w, EnabledMenu _m) {
        this.window = _w;
        this.menu = _m;
    }
    @Override
    public void action() {
        window.initFormTr();
        menu.setEnabled(false);
    }
}
