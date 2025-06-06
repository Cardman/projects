package aiki.gui.components.editor;

import code.gui.events.*;

public final class SelectCrudPlaceCaveFormEvent implements AbsActionListener {
    private final CrudGeneFormEntPlace form;
    public SelectCrudPlaceCaveFormEvent(CrudGeneFormEntPlace _c) {
        form = _c;
    }

    @Override
    public void action() {
        form.displayAllLinksPlaceCave();
    }
}
