package aiki.gui.components.editor;

import code.gui.events.*;

public class DisplayLinksCaveEvent implements AbsActionListener {
    private final CrudGeneFormLevelCave crud;
    private final int index;
    public DisplayLinksCaveEvent(CrudGeneFormLevelCave _c, int _i) {
        crud = _c;
        index = _i;
    }

    @Override
    public void action() {
        crud.displayGrid(index);
    }
}
