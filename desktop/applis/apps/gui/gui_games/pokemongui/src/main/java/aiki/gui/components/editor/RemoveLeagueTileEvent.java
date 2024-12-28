package aiki.gui.components.editor;

import code.gui.events.*;

public final class RemoveLeagueTileEvent implements AbsActionListener {
    private final ContentComponentModelLevelLeague form;
    public RemoveLeagueTileEvent(ContentComponentModelLevelLeague _g) {
        form = _g;
    }

    @Override
    public void action() {
        form.removeTile();
    }
}
