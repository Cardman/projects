package aiki.gui.components.editor;

import code.gui.events.*;

public class SelectCrudCavePlaceFormEvent implements AbsActionListener {
    private final CrudGeneFormEntPlace form;
    public SelectCrudCavePlaceFormEvent(CrudGeneFormEntPlace _c) {
        form = _c;
    }

    @Override
    public void action() {
        form.displayAllLinksCavePlace();
    }
}
