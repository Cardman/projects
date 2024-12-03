package aiki.gui.components.editor;

import code.gui.AbsCtrlKeyState;
import code.gui.AbsTextField;
import code.gui.events.AbsAdvActionListener;

public class RenameCstEvent implements AbsAdvActionListener {
    private final CrudGeneFormTrCstList crud;
    private final String key;
    private final AbsTextField field;
    public RenameCstEvent(CrudGeneFormTrCstList _c, String _k, AbsTextField _f) {
        crud = _c;
        key = _k;
        field = _f;
    }

    @Override
    public void action(AbsCtrlKeyState _state, String _command) {
        crud.apply(key,field);
    }
}
