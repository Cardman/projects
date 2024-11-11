package aiki.gui.components.editor;

import aiki.facade.FacadeGame;
import code.gui.initialize.AbstractProgramInfos;
import code.util.AbsMap;
import code.util.StringMap;

public final class SubscribedTranslationMessages implements SubscribedTranslation {
    private final AbsMap<String, String> input;
    private final SubscribedTranslationMessagesFactory factory;
    private final AbsMap<String,String> empty;

    public SubscribedTranslationMessages(AbsMap<String, String> _c, SubscribedTranslationMessagesFactory _sub, AbsMap<String,String> _withEmpty) {
        this.input = _c;
        this.factory = _sub;
        this.empty = _withEmpty;
    }

    @Override
    public void update(AbstractProgramInfos _api, FacadeGame _facade) {
        StringMap<String> messages_ = factory.buildMessages(_api, _facade, empty);
        input.clear();
        input.addAllEntries(messages_);
    }
}
