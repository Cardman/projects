package code.gui;

import code.gui.events.AbsActionListener;

public final class SelectCrudGeneFormEvent implements AbsActionListener {
    private final AbsCrudGeneForm crud;
    private final int index;

    public SelectCrudGeneFormEvent(AbsCrudGeneForm _c, int _i) {
        this.crud = _c;
        this.index = _i;
    }

    @Override
    public void action() {
        crud.select(index);
    }
}
