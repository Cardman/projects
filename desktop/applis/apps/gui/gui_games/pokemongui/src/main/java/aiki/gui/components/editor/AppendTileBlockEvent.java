package aiki.gui.components.editor;

import code.gui.events.*;

public final class AppendTileBlockEvent implements AbsActionListener {
    private final FormLevelGrid formMiniMapGrid;
    public AppendTileBlockEvent(FormLevelGrid _f) {
        formMiniMapGrid = _f;
    }

    @Override
    public void action() {
        formMiniMapGrid.append();
    }
}
