package aiki.gui.components.editor;

import code.gui.events.*;

public final class RemoveBuildingTileEvent implements AbsActionListener {
    private final ContentComponentModelCity form;
    public RemoveBuildingTileEvent(ContentComponentModelCity _g) {
        form = _g;
    }

    @Override
    public void action() {
        form.removeTile();
    }
}
