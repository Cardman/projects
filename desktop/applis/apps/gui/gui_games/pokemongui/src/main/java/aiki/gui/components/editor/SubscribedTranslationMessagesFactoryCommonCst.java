package aiki.gui.components.editor;

import aiki.facade.*;
import code.util.*;

public abstract class SubscribedTranslationMessagesFactoryCommonCst<T> implements SubscribedTranslationMessagesFactoryCoreMessages<T> {

    private final SubscribedTranslationMessagesFactoryCoreContainer<T> container = new SubscribedTranslationMessagesFactoryCoreContainer<T>(this);

    public SubscribedTranslationMessagesFactoryCoreContainer<T> getContainer() {
        return container;
    }

    @Override
    public AbsMap<T, String> buildMessagesCopy(FacadeGame _facade, String _lg) {
        return new IdMap<T, String>(buildMessages(_facade).getVal(_lg));
    }
    public abstract StringMap<IdMap<T,String>> buildMessages(FacadeGame _facade);
}
