package aiki.gui.components.editor;

import code.gui.*;
import code.gui.events.*;

public class RenameCstNumEvent implements AbsAdvActionListener {
    private final CrudGeneFormTrCstNumList crud;
    private final String key;
    private final GeneComponentModelRate field;
    public RenameCstNumEvent(CrudGeneFormTrCstNumList _c, String _k, GeneComponentModelRate _f) {
        crud = _c;
        key = _k;
        field = _f;
    }

    @Override
    public void action(AbsCtrlKeyState _state, String _command) {
        crud.apply(key,field);
    }
}
