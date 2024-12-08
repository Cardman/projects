package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.FacadeGame;
import code.util.AbsMap;
import code.util.StringMap;

public abstract class SubscribedTranslationMessagesFactoryImg implements SubscribedTranslationMessagesFactoryCommonInt<ImageArrayBaseSixtyFour> {

    private final SubscribedTranslationMessagesFactoryCoreContainer<String> container = new SubscribedTranslationMessagesFactoryCoreContainer<String>(this);

    public SubscribedTranslationMessagesFactoryCoreContainer<String> getContainer() {
        return container;
    }
    public abstract StringMap<StringMap<String>> buildMessages(FacadeGame _facade);

    @Override
    public void delete(FacadeGame _facade, String _key) {
        all(_facade).removeKey(_key);
    }

    @Override
    public AbsMap<String, String> buildMessagesCopy(FacadeGame _facade, String _lg) {
        return new StringMap<String>(buildMessages(_facade).getVal(_lg));
    }

}
