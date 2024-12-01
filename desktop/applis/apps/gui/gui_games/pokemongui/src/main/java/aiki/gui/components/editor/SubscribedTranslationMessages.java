package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.initialize.*;
import code.util.*;

public final class SubscribedTranslationMessages<T> implements SubscribedTranslation {
    private final AbsMap<T, String> input;
    private final SubscribedTranslationMessagesFactoryCore<T> factory;
    private final AbsMap<T,String> empty;

    public SubscribedTranslationMessages(AbsMap<T, String> _c, SubscribedTranslationMessagesFactoryCore<T> _sub, AbsMap<T,String> _withEmpty) {
        this.input = _c;
        this.factory = _sub;
        this.empty = _withEmpty;
    }

    @Override
    public void update(AbstractProgramInfos _api, FacadeGame _facade, RenamingIdPhase _phase) {
        AbsMap<T, String> messages_ = factory.buildMessages(_api, _facade, empty);
        input.clear();
        input.addAllEntries(messages_);
    }
}
