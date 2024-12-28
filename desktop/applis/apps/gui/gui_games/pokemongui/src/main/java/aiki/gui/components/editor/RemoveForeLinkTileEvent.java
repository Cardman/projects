package aiki.gui.components.editor;

import code.gui.events.*;

public final class RemoveForeLinkTileEvent implements AbsActionListener {
    private final AbsContentComponentModelLevelLinks form;
    private final int option;

    public RemoveForeLinkTileEvent(AbsContentComponentModelLevelLinks _g, int _o) {
        form = _g;
        option = _o;
    }

    @Override
    public void action() {
        form.removeTile(option);
    }
}
