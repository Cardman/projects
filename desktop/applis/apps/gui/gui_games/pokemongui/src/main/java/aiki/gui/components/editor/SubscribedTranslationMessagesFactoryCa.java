package aiki.gui.components.editor;

import aiki.facade.FacadeGame;
import code.util.StringMap;

public final class SubscribedTranslationMessagesFactoryCa extends SubscribedTranslationMessagesFactoryCommon {

    @Override
    public StringMap<StringMap<String>> buildMessages(FacadeGame _facade) {
        return _facade.getData().getTranslatedCategories();
    }

    @Override
    public boolean contains(FacadeGame _facade, String _key) {
        return _facade.getData().usedCategory(_key);
    }

    @Override
    public void rename(FacadeGame _facade, String _previous, String _next) {
        _facade.getData().renameCategory(_previous,_next);
    }
}
