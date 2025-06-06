package aiki.gui.components.editor;

import code.gui.events.*;

public final class SelectCrudJoinFormEvent implements AbsActionListener {
    private final CrudGeneFormEntPlace form;
    public SelectCrudJoinFormEvent(CrudGeneFormEntPlace _c) {
        form = _c;
    }

    @Override
    public void action() {
        form.displayJoinPlaces();
    }
}
