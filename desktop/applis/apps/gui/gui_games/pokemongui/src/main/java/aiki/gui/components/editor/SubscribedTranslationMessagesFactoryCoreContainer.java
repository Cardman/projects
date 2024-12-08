package aiki.gui.components.editor;

import aiki.facade.*;
import code.gui.initialize.*;
import code.util.*;

public final class SubscribedTranslationMessagesFactoryCoreContainer<T> {
    private final SubscribedTranslationMessagesFactoryCoreMessages<T> content;

    public SubscribedTranslationMessagesFactoryCoreContainer(SubscribedTranslationMessagesFactoryCoreMessages<T> _c) {
        this.content = _c;
    }

    public SubscribedTranslation buildSub(AbsMap<T, String> _map, AbsMap<T,String> _withEmpty) {
        return new SubscribedTranslationMessages<T>(_map,content, _withEmpty);
    }

    public AbsMap<T,String> buildMessages(AbstractProgramInfos _api, FacadeGame _facade) {
        return content.buildMessagesCopy(_facade,_api.getLanguage());
    }

    public AbsMap<T,String> buildMessages(AbstractProgramInfos _api, FacadeGame _facade, AbsMap<T,String> _withEmpty) {
        AbsMap<T,String> out_ = content.buildMessagesCopy(_facade,_api.getLanguage());
        out_.addAllEntries(_withEmpty);
        return out_;
    }
}
