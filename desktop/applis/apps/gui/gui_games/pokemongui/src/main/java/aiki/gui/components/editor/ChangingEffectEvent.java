package aiki.gui.components.editor;

import code.gui.*;

public final class ChangingEffectEvent implements ListSelection {
    private final GeneComponentModelEffect form;
    public ChangingEffectEvent(GeneComponentModelEffect _g) {
        form = _g;
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        form.applyChange();
    }
}
