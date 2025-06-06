package aiki.gui.components.editor;

import code.gui.events.*;

public final class ChangeMoveKindEvent implements AbsActionListener {
    private final ChangeableFormType form;
    public ChangeMoveKindEvent(ChangeableFormType _f) {
        form = _f;
    }

    @Override
    public void action() {
        form.applyChange();
    }
}
