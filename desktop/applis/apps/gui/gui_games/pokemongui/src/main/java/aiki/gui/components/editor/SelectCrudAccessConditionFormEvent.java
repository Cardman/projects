package aiki.gui.components.editor;

import code.gui.events.*;

public final class SelectCrudAccessConditionFormEvent implements AbsActionListener {
    private final CrudGeneFormEntPlace form;
    private final boolean begin;

    public SelectCrudAccessConditionFormEvent(CrudGeneFormEntPlace _c, boolean _b) {
        form = _c;
        begin = _b;
    }

    @Override
    public void action() {
        form.displayAccessCondition(begin);
    }
}
