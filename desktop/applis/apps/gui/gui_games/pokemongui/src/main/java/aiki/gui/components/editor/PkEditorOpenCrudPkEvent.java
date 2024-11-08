package aiki.gui.components.editor;

import code.gui.*;
import code.gui.events.*;

public final class PkEditorOpenCrudPkEvent<T> implements AbsActionListener {
    private final CrudGeneFormEnt<T> crudGeneFormPk;
    private final EnabledMenu menu;

    public PkEditorOpenCrudPkEvent(CrudGeneFormEnt<T> _c, EnabledMenu _m) {
        crudGeneFormPk = _c;
        menu = _m;
    }

    @Override
    public void action() {
        crudGeneFormPk.initForm(crudGeneFormPk.getFactory());
        menu.setEnabled(false);
    }
}
