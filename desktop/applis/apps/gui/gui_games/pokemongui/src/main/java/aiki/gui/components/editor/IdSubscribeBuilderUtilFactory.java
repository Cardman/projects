package aiki.gui.components.editor;

import code.util.*;

public final class IdSubscribeBuilderUtilFactory<E> implements AbsSubscribeBuilderUtilFactory<E, IdList<E>> {
    @Override
    public AbsSubscribeBuilderUtil<E, IdList<E>> build(SubscribedTranslationMessagesFactoryCoreMessages<E> _message) {
        return new SubscribeBuilderUtil<E>(_message);
    }
}
