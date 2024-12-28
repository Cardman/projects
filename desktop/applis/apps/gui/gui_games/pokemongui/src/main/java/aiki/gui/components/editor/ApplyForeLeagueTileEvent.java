package aiki.gui.components.editor;

import code.gui.events.*;

public final class ApplyForeLeagueTileEvent implements AbsActionListener {
    private final ContentComponentModelLevelLeague form;
    public ApplyForeLeagueTileEvent(ContentComponentModelLevelLeague _g) {
        form = _g;
    }

    @Override
    public void action() {
        form.applyTile();
    }
}
