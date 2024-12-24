package aiki.gui.components.editor;

import code.gui.events.*;

public final class SelectCrudPlaceFormEvent implements AbsActionListener {
    private final CrudGeneFormEntPlace crud;
    private final int index;

    public SelectCrudPlaceFormEvent(CrudGeneFormEntPlace _c, int _i) {
        this.crud = _c;
        this.index = _i;
    }

    @Override
    public void action() {
        crud.selectPlace(index);
    }
}
