package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;

public final class CrudGeneFormPkTr extends AbsCrudGeneFormTr {
    public CrudGeneFormPkTr(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr) {
        super(_fact, _facade, _sub, _fr, _sub.getFactoryPk());
    }

    @Override
    protected boolean renamed(String _previous, String _next) {
        getFacadeGame().getData().renamePokemon(_previous, _next);
        return !already(_previous);
    }

    @Override
    protected boolean already(String _key) {
        return getFacadeGame().getData().getPokedex().contains(_key);
    }
}
