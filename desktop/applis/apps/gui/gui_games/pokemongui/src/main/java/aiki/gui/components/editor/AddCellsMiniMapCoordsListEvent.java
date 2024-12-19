package aiki.gui.components.editor;

import code.gui.events.*;

public class AddCellsMiniMapCoordsListEvent implements AbsActionListener {
    private final FormMiniMapGrid formMiniMapGrid;
    public AddCellsMiniMapCoordsListEvent(FormMiniMapGrid _f) {
        formMiniMapGrid = _f;
    }

    @Override
    public void action() {
        formMiniMapGrid.addCells();
    }
}
