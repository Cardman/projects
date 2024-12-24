package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.status.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class SubscribedTranslationMessagesFactorySt extends SubscribedTranslationMessagesFactoryCommonParam<Status> {

    private GeneComponentModelStatus geneComponentModelStatus;

    @Override
    public StringMap<StringMap<String>> buildMessages(FacadeGame _facade) {
        return _facade.getData().getTranslatedStatus();
    }

    @Override
    public void rename(FacadeGame _facade, String _previous, String _next) {
        _facade.getData().renameStatus(_previous,_next);
    }

    @Override
    public StringMap<Status> all(FacadeGame _facade) {
        return _facade.getData().getStatus();
    }

    @Override
    public void delete(FacadeGame _facade, String _key) {
        _facade.getData().deleteStatus(_key);
    }

    @Override
    public GeneComponentModel<EditedCrudPair<String,Status>> build(AbsCommonFrame _frame, AbstractProgramInfos _core, CrudGeneFormSubContent _facade) {
        geneComponentModelStatus = new GeneComponentModelStatus(_frame,_core, _facade.getFacadeGame(), _facade.getSubscription());
        return geneComponentModelStatus;
    }

    @Override
    public void removeOpenSub(CrudGeneFormSubContent _base) {
        _base.removeOpenSub();
    }

    @Override
    public IdList<SubscribedTranslation> all() {
        return geneComponentModelStatus.all();
    }

    @Override
    public StringList mids(FacadeGame _facade) {
        return _facade.getData().statusPart();
    }
}
