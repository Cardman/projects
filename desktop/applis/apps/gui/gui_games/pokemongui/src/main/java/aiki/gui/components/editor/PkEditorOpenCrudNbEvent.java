package aiki.gui.components.editor;

import code.gui.*;
import code.gui.events.*;

public final class PkEditorOpenCrudNbEvent implements AbsActionListener {
    private final AbsCrudGeneFormNb window;
    private final EnabledMenu menu;
    private final boolean tm;

    public PkEditorOpenCrudNbEvent(AbsCrudGeneFormNb _w, EnabledMenu _m, boolean _t) {
        this.window = _w;
        this.menu = _m;
        this.tm = _t;
    }
    @Override
    public void action() {
        window.initFormTr(tm);
        menu.setEnabled(false);
    }
}
