package aiki.gui.components.editor;

import aiki.facade.FacadeGame;
import code.util.StringMap;

public abstract class SubscribedTranslationMessagesFactoryCommonParam<T> extends SubscribedTranslationMessagesFactoryCommon {
    @Override
    public boolean contains(FacadeGame _facade, String _key) {
        return all(_facade).contains(_key);
    }
    public abstract StringMap<T> all(FacadeGame _facade);
}
