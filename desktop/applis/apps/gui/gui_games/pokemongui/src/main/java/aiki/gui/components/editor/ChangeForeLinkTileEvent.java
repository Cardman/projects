package aiki.gui.components.editor;

import code.gui.events.*;

public final class ChangeForeLinkTileEvent implements AbsActionListener {
    private final AbsContentComponentModelLevelLinks form;
    private final int option;

    public ChangeForeLinkTileEvent(AbsContentComponentModelLevelLinks _g, int _o) {
        form = _g;
        option = _o;
    }

    @Override
    public void action() {
        form.applyLinks(option);
    }
}
