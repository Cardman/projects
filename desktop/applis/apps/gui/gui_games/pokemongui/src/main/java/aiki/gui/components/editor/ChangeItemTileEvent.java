package aiki.gui.components.editor;

import code.gui.*;

public final class ChangeItemTileEvent implements ListSelection {
    private final ContentComponentModelLevelWithWild form;
    public ChangeItemTileEvent(ContentComponentModelLevelWithWild _g) {
        form = _g;
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        form.applySelectItem();
    }
}
