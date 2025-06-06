package aiki.gui.components.editor;

import code.gui.events.AbsActionListener;

public final class RemoveBuildingEltTileEvent implements AbsActionListener {
    private final ContentComponentModelCity form;
    public RemoveBuildingEltTileEvent(ContentComponentModelCity _g) {
        form = _g;
    }

    @Override
    public void action() {
        form.removeBuildingTile();
    }
}
