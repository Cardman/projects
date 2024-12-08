package aiki.gui.components.editor;

import aiki.facade.*;

public abstract class SubscribedTranslationMessagesFactoryCommonParam<T> extends SubscribedTranslationMessagesFactoryCommon implements SubscribedTranslationMessagesFactoryCommonInt<T> {
    @Override
    public boolean contains(FacadeGame _facade, String _key) {
        return all(_facade).contains(_key);
    }
}
