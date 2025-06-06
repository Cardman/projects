package aiki.gui.components.editor;

import code.gui.events.*;

public final class RemoveForeTileEvent implements AbsActionListener {
    private final ContentComponentModelLevelWithWild form;
    public RemoveForeTileEvent(ContentComponentModelLevelWithWild _g) {
        form = _g;
    }

    @Override
    public void action() {
        form.removeTile();
    }
}
