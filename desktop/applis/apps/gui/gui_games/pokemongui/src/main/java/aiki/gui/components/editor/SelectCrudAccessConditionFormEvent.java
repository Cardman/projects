package aiki.gui.components.editor;

import code.gui.events.*;

public final class SelectCrudAccessConditionFormEvent implements AbsActionListener {
    private final CrudGeneFormEntPlace form;
    public SelectCrudAccessConditionFormEvent(CrudGeneFormEntPlace _c) {
        form = _c;
    }

    @Override
    public void action() {
        form.displayAccessCondition();
    }
}
