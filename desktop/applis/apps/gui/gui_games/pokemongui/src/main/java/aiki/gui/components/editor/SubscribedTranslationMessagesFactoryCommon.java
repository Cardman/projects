package aiki.gui.components.editor;

import code.util.AbsMap;

public abstract class SubscribedTranslationMessagesFactoryCommon implements SubscribedTranslationMessagesFactory {
    @Override
    public SubscribedTranslation buildSub(AbsMap<String, String> _map) {
        return new SubscribedTranslationMessages(_map,this);
    }
}
