package aiki.gui.components.editor;

import code.gui.events.*;

public final class ApplyBuildingEltTileEvent implements AbsActionListener {
    private final ContentComponentModelCity form;
    public ApplyBuildingEltTileEvent(ContentComponentModelCity _g) {
        form = _g;
    }

    @Override
    public void action() {
        form.applyBuildingTile();
    }
}
