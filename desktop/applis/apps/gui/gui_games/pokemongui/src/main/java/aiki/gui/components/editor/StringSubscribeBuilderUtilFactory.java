package aiki.gui.components.editor;

import code.util.StringList;

public final class StringSubscribeBuilderUtilFactory implements AbsSubscribeBuilderUtilFactory<String, StringList> {
    @Override
    public AbsSubscribeBuilderUtil<String, StringList> build(SubscribedTranslationMessagesFactoryCore<String> _message) {
        return new StringSubscribeBuilderUtil(_message);
    }
}
