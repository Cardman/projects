package aiki.gui.components.editor;

import code.gui.events.*;

public final class ApplyForeTileEvent implements AbsActionListener {
    private final ContentComponentModelLevelWithWild form;
    public ApplyForeTileEvent(ContentComponentModelLevelWithWild _g) {
        form = _g;
    }

    @Override
    public void action() {
        form.applyTile();
    }
}
