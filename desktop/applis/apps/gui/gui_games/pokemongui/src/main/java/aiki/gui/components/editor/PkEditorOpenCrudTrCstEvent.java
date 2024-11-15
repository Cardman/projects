package aiki.gui.components.editor;

import code.gui.EnabledMenu;
import code.gui.events.AbsActionListener;

public final class PkEditorOpenCrudTrCstEvent<T> implements AbsActionListener {
    private final CrudGeneFormTrCst<T> window;
    private final EnabledMenu menu;

    public PkEditorOpenCrudTrCstEvent(CrudGeneFormTrCst<T> _w, EnabledMenu _m) {
        this.window = _w;
        this.menu = _m;
    }
    @Override
    public void action() {
        window.initForm();
        menu.setEnabled(false);
    }
}
