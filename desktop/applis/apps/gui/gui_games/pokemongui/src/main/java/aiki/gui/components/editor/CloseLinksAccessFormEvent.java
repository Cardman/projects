package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.events.*;

public final class CloseLinksAccessFormEvent implements AbsActionListener {
    private final FacadeGame facadeGame;
    private final CrudGeneFormEntPlace parent;
    private final AbsButton crud;
    public CloseLinksAccessFormEvent(FacadeGame _fac, CrudGeneFormEntPlace _p, AbsButton _c) {
        facadeGame = _fac;
        parent = _p;
        crud = _c;
    }

    @Override
    public void action() {
        crud.setEnabled(true);
        if (facadeGame.getMap().getAccessCondition().isEmpty()) {
            for (CrudGeneFormLevel c: parent.getLevels()) {
                c.cancel();
            }
            parent.cancel();
        }
    }
}
