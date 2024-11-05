package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.initialize.*;
import code.util.*;

public final class SubscribedTranslationMessagesNbFactoryHm extends SubscribedTranslationMessagesNbFactory {

    @Override
    public ShortMap<String> retrieveMap(AbstractProgramInfos _api, FacadeGame _facade) {
        return _facade.getData().getHm();
    }

    @Override
    public void delete(FacadeGame _facade, short _id) {
        _facade.getData().deleteHm(_id);
    }

    @Override
    public void rename(FacadeGame _facade, short _previous, short _next) {
        _facade.getData().renameHm(_previous,_next);
    }
}
