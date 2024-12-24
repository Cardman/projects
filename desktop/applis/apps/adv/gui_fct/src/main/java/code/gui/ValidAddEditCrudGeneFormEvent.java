package code.gui;

import code.gui.events.AbsActionListener;

public final class ValidAddEditCrudGeneFormEvent implements AbsActionListener {
    private final AbsCrudGeneForm crud;

    public ValidAddEditCrudGeneFormEvent(AbsCrudGeneForm _c) {
        this.crud = _c;
    }

    @Override
    public void action() {
        crud.validAddEdit();
    }
}
