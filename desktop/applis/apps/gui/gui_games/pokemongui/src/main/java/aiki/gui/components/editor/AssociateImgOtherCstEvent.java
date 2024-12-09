package aiki.gui.components.editor;

import aiki.fight.enums.*;
import code.gui.events.*;

public class AssociateImgOtherCstEvent implements AbsActionListener {
    private final CrudGeneFormEntImgCstList window;
    private final Statistic key;
    public AssociateImgOtherCstEvent(CrudGeneFormEntImgCstList _w, Statistic _k) {
        window = _w;
        key = _k;
    }

    @Override
    public void action() {
        window.apply(key);
    }
}
