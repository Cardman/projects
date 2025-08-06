package aiki.gui.components.editor;

import code.gui.*;
import code.gui.events.*;
import code.util.*;

public final class CloseLinksFormEvent implements AbsActionListener {
    private final CrudGeneFormEntPlace crud;
    private final SubscribedTranslationList translationList;
    private final CustList<SubscribedTranslation> list;
    private final AbsCommonFrame frame;
    public CloseLinksFormEvent(CrudGeneFormEntPlace _c, SubscribedTranslationList _trs, CustList<SubscribedTranslation> _ls, AbsCommonFrame _fr) {
        crud = _c;
        translationList = _trs;
        list = _ls;
        frame = _fr;
    }

    @Override
    public void action() {
        translationList.removeSubs(frame,list);
        for (CrudGeneFormLevel c: crud.getLevels()) {
            c.cancel();
        }
        crud.cancel();
    }
}
