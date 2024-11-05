package aiki.gui.components.editor;

import aiki.facade.*;
import code.util.*;

public final class SubscribedTranslationMessagesFactoryTy extends SubscribedTranslationMessagesFactoryCommon {

    @Override
    public StringMap<StringMap<String>> buildMessages(FacadeGame _facade) {
        return _facade.getData().getTranslatedTypes();
    }

    @Override
    public boolean contains(FacadeGame _facade, String _key) {
        return _facade.getData().usedType(_key);
    }

    @Override
    public void rename(FacadeGame _facade, String _previous, String _next) {
        _facade.getData().renameType(_previous,_next);
    }
}
