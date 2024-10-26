package code.gui;

import code.gui.events.AbsActionListener;

public final class CancelCrudGeneFormEvent implements AbsActionListener {
    private final AbsCrudGeneForm crud;

    public CancelCrudGeneFormEvent(AbsCrudGeneForm _c) {
        this.crud = _c;
    }

    @Override
    public void action() {
        crud.cancel();
    }
}
