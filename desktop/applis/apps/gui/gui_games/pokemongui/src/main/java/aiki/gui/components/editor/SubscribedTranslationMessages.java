package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.initialize.*;
import code.util.*;

public final class SubscribedTranslationMessages<T> implements SubscribedTranslation {
    private final AbsMap<T, String> input;
    private final SubscribedTranslationMessagesFactoryCoreMessages<T> factory;
    private final AbsMap<T,String> empty;

    public SubscribedTranslationMessages(AbsMap<T, String> _c, SubscribedTranslationMessagesFactoryCoreMessages<T> _sub, AbsMap<T,String> _withEmpty) {
        this.input = _c;
        this.factory = _sub;
        this.empty = _withEmpty;
    }

    @Override
    public void update(AbstractProgramInfos _api, FacadeGame _facade, SubscribedTranslationList _current) {
        AbsMap<T, String> messages_ = factory.getContainer().buildMessages(_api, _facade, empty);
        input.clear();
        input.addAllEntries(messages_);
    }
}
