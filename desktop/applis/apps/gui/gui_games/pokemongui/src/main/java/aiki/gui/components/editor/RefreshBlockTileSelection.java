package aiki.gui.components.editor;

import code.gui.*;

public final class RefreshBlockTileSelection implements ListSelection {
    private final FormLevelGrid formLevelGrid;
    public RefreshBlockTileSelection(FormLevelGrid _g) {
        formLevelGrid = _g;
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        formLevelGrid.refreshImg(formLevelGrid.getFormBlockTile().getEdited().getWidth(), formLevelGrid.getFormBlockTile().getEdited().getHeight());
    }
}
