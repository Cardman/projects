package aiki.gui.components.editor;

import code.gui.ListSelection;
import code.gui.SelectionInfo;

public class ChangePlaceListEvent implements ListSelection {
    private final ContentComponentModelUniqLevelLinks grid;
    private final boolean left;
    public ChangePlaceListEvent(ContentComponentModelUniqLevelLinks _g, boolean _l) {
        grid = _g;
        left = _l;
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        grid.placeChanged(left);
    }
}
