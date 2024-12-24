package aiki.gui.components.editor;

import code.gui.events.*;

public class SelectCrudLevelFormEvent implements AbsActionListener {
    private final CrudGeneFormLevel crud;
    private final int index;
    public SelectCrudLevelFormEvent(CrudGeneFormLevel _c, int _i) {
        crud = _c;
        index = _i;
    }

    @Override
    public void action() {
        crud.selectLevel(index);
    }
}
