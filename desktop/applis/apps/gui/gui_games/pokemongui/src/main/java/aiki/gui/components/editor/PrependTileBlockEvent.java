package aiki.gui.components.editor;

import code.gui.events.*;

public class PrependTileBlockEvent implements AbsActionListener {
    private final FormLevelGrid formMiniMapGrid;
    public PrependTileBlockEvent(FormLevelGrid _f) {
        formMiniMapGrid = _f;
    }

    @Override
    public void action() {
        formMiniMapGrid.prepend();
    }
}
