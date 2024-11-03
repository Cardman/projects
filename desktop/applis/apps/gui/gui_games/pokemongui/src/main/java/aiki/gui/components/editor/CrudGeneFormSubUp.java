package aiki.gui.components.editor;

import aiki.facade.FacadeGame;
import code.gui.initialize.AbstractProgramInfos;
import code.util.IdList;

public interface CrudGeneFormSubUp {
    void updateDisplayEntry(AbstractProgramInfos _api, FacadeGame _facade);
    void subscribeAll(IdList<SubscribedTranslation> _sub);
    IdList<SubscribedTranslation> subscribe();
}
