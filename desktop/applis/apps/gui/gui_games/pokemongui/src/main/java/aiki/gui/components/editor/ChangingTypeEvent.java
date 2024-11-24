package aiki.gui.components.editor;

import code.gui.ListSelection;
import code.gui.SelectionInfo;

public final class ChangingTypeEvent implements ListSelection {
    private final ChangeableFormType form;
    public ChangingTypeEvent(ChangeableFormType _g) {
        form = _g;
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        form.applyChange();
    }
}
