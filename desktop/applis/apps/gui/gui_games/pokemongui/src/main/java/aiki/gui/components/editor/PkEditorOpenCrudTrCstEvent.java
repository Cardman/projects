package aiki.gui.components.editor;

import code.gui.EnabledMenu;
import code.gui.events.AbsActionListener;

public final class PkEditorOpenCrudTrCstEvent implements AbsActionListener {
    private final AbsCrudGeneFormTrCstOpen window;
    private final EnabledMenu menu;

    public PkEditorOpenCrudTrCstEvent(AbsCrudGeneFormTrCstOpen _w, EnabledMenu _m) {
        this.window = _w;
        this.menu = _m;
    }
    @Override
    public void action() {
        window.initForm();
        menu.setEnabled(false);
    }
}
