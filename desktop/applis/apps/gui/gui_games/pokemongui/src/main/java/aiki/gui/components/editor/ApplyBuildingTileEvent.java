package aiki.gui.components.editor;

import code.gui.events.*;

public final class ApplyBuildingTileEvent implements AbsActionListener {
    private final ContentComponentModelCity form;
    public ApplyBuildingTileEvent(ContentComponentModelCity _g) {
        form = _g;
    }

    @Override
    public void action() {
        form.applyTile();
    }
}
