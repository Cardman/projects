package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;

public final class CrudGeneFormMvTr extends AbsCrudGeneFormTr {
    public CrudGeneFormMvTr(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr) {
        super(_fact, _facade, _sub, _fr, _sub.getFactoryMv());
    }

    @Override
    protected boolean renamed(String _previous, String _next) {
        getFacadeGame().getData().renameMove(_previous, _next);
        return !already(_previous);
    }

    @Override
    protected boolean already(String _key) {
        return getFacadeGame().getData().getMoves().contains(_key);
    }
}
