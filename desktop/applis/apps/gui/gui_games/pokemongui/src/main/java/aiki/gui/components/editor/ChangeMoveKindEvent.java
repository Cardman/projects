package aiki.gui.components.editor;

import code.gui.events.*;

public class ChangeMoveKindEvent implements AbsActionListener {
    private final GeneComponentModelMoveData form;
    public ChangeMoveKindEvent(GeneComponentModelMoveData _f) {
        form = _f;
    }

    @Override
    public void action() {
        form.applyChanges();
    }
}
