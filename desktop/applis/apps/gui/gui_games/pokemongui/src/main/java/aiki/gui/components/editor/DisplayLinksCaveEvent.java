package aiki.gui.components.editor;

import code.gui.events.*;

public final class DisplayLinksCaveEvent implements AbsActionListener {
    private final CrudGeneFormLevel crud;
    private final int index;
    public DisplayLinksCaveEvent(CrudGeneFormLevel _c, int _i) {
        crud = _c;
        index = _i;
    }

    @Override
    public void action() {
        crud.displayGrid(index);
    }
}
