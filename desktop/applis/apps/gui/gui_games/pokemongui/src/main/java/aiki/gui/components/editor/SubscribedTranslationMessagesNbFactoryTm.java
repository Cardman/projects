package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.initialize.*;
import code.util.*;

public final class SubscribedTranslationMessagesNbFactoryTm extends SubscribedTranslationMessagesNbFactory {

    @Override
    public IntMap<String> retrieveMap(AbstractProgramInfos _api, FacadeGame _facade) {
        return _facade.getData().getTm();
    }

    @Override
    public void delete(FacadeGame _facade, int _id) {
        _facade.getData().deleteTm(_id);
    }

    @Override
    public void rename(FacadeGame _facade, int _previous, int _next) {
        _facade.getData().renameTm(_previous,_next);
    }
}
