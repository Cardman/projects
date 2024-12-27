package aiki.gui.components.editor;

import code.gui.*;

public class ChangeItemBuildingTileEvent implements ListSelection {
    private final ContentComponentModelCity form;
    public ChangeItemBuildingTileEvent(ContentComponentModelCity _g) {
        form = _g;
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        form.applySelectItem();
    }
}
