package code.gui;

import code.gui.events.AbsActionListener;

public final class ValidRemoveCrudGeneFormEvent implements AbsActionListener {
    private final AbsCrudGeneForm crud;

    public ValidRemoveCrudGeneFormEvent(AbsCrudGeneForm _c) {
        this.crud = _c;
    }

    @Override
    public void action() {
        crud.validRemove();
    }
}
