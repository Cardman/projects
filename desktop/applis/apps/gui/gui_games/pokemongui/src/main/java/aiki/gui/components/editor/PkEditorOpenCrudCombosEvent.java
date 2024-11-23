package aiki.gui.components.editor;

import code.gui.*;
import code.gui.events.*;

public final class PkEditorOpenCrudCombosEvent implements AbsActionListener {
    private final CrudGeneFormCombos crudGeneFormPk;
    private final EnabledMenu menu;

    public PkEditorOpenCrudCombosEvent(CrudGeneFormCombos _c, EnabledMenu _m) {
        crudGeneFormPk = _c;
        menu = _m;
    }

    @Override
    public void action() {
        crudGeneFormPk.initForm(crudGeneFormPk.getFactory());
        menu.setEnabled(false);
    }
}
