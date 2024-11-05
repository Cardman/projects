package aiki.gui.components.editor;

import aiki.facade.FacadeGame;
import code.gui.AbsCommonFrame;
import code.gui.initialize.AbstractProgramInfos;

public final class CrudGeneFormHm extends AbsCrudGeneFormNb {
    public CrudGeneFormHm(AbstractProgramInfos _fact, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _fr) {
        super(_fact, _facade, _sub, _fr, _sub.getFactoryHm());
    }

    @Override
    protected boolean already(short _key) {
        int old_ = getCrudGeneFormSubContent().getFacadeGame().getData().getHm().size();
        getCrudGeneFormSubContent().getFacadeGame().getData().deleteHm(_key);
        return old_ <= getCrudGeneFormSubContent().getFacadeGame().getData().getHm().size();
    }

    @Override
    protected boolean renamed(short _previous, short _next) {
        getCrudGeneFormSubContent().getFacadeGame().getData().renameHm(_previous, _next);
        return !getCrudGeneFormSubContent().getFacadeGame().getData().getHm().contains(_previous);
    }
}
