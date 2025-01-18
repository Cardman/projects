package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.initialize.*;
import code.util.*;

public final class SubscribedTranslationMessagesNbFactoryHm extends SubscribedTranslationMessagesNbFactory {

    @Override
    public IntMap<String> retrieveMap(AbstractProgramInfos _api, FacadeGame _facade) {
        return _facade.getData().getHm();
    }

    @Override
    public void delete(FacadeGame _facade, int _id) {
        _facade.getData().deleteHm(_id);
    }

    @Override
    public void rename(FacadeGame _facade, int _previous, int _next) {
        _facade.getData().renameHm(_previous,_next);
    }
}
