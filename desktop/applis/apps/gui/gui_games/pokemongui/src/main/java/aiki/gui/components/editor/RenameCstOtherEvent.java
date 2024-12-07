package aiki.gui.components.editor;

import code.gui.*;
import code.gui.events.*;

public class RenameCstOtherEvent implements AbsActionListener {
    private final CrudGeneFormTrOtherCstList window;
    private final String key;
    private final AbsTextField field;
    public RenameCstOtherEvent(CrudGeneFormTrOtherCstList _w, String _k, AbsTextField _f) {
        window = _w;
        key = _k;
        field = _f;
    }

    @Override
    public void action() {
        window.apply(key,field);
    }
}
