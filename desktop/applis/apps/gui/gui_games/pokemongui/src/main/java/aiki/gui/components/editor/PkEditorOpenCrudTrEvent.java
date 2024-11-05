package aiki.gui.components.editor;

import code.gui.EnabledMenu;
import code.gui.events.AbsActionListener;

public final class PkEditorOpenCrudTrEvent implements AbsActionListener {
    private final CrudGeneFormTr window;
    private final EnabledMenu menu;

    public PkEditorOpenCrudTrEvent(CrudGeneFormTr _w, EnabledMenu _m) {
        this.window = _w;
        this.menu = _m;
    }
    @Override
    public void action() {
        window.initForm(window.getFactory());
        menu.setEnabled(false);
    }
}
