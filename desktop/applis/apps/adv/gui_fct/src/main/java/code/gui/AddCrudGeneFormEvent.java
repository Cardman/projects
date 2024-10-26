package code.gui;

import code.gui.events.AbsActionListener;

public final class AddCrudGeneFormEvent implements AbsActionListener {
    private final AbsCrudGeneForm crud;

    public AddCrudGeneFormEvent(AbsCrudGeneForm _c) {
        this.crud = _c;
    }

    @Override
    public void action() {
        crud.formAdd();
    }
}
