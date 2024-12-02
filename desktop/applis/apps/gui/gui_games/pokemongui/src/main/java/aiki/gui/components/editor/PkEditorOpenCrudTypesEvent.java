package aiki.gui.components.editor;

import code.gui.*;
import code.gui.events.*;

public final class PkEditorOpenCrudTypesEvent implements AbsActionListener {
    private final CrudGeneFormTypes crudGeneFormPk;
    private final EnabledMenu menu;

    public PkEditorOpenCrudTypesEvent(CrudGeneFormTypes _c, EnabledMenu _m) {
        crudGeneFormPk = _c;
        menu = _m;
    }

    @Override
    public void action() {
        crudGeneFormPk.initForm(crudGeneFormPk.getFactory());
        menu.setEnabled(false);
    }
}
