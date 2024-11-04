package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;

public final class CrudGeneFormTm extends AbsCrudGeneFormNb {
    public CrudGeneFormTm(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr) {
        super(_fact, _facade, _sub, _fr, _sub.getFactoryTm());
    }

    @Override
    protected boolean already(short _key) {
        int old_ = getCrudGeneFormSubContent().getFacadeGame().getData().getTm().size();
        getCrudGeneFormSubContent().getFacadeGame().getData().deleteTm(_key);
        return old_ <= getCrudGeneFormSubContent().getFacadeGame().getData().getTm().size();
    }

    @Override
    protected boolean renamed(short _previous, short _next) {
        getCrudGeneFormSubContent().getFacadeGame().getData().renameTm(_previous, _next);
        return !getCrudGeneFormSubContent().getFacadeGame().getData().getTm().contains(_previous);
    }
}
