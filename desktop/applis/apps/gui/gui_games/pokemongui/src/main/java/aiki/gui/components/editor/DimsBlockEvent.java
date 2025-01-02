package aiki.gui.components.editor;

import code.gui.events.*;

public final class DimsBlockEvent implements AbsActionListener {
    private final FormLevelGrid levelGrid;
    public DimsBlockEvent(FormLevelGrid _g) {
        levelGrid = _g;
    }

    @Override
    public void action() {
        levelGrid.checkDims();
    }
}
