package aiki.gui.components.editor;

import code.util.*;

public final class DisplayEntryCustSubImpl<T> implements DisplayEntryCustSub<T> {
    private final SubscribedTranslationMessagesFactoryCore<T> content;
    private final AbsMap<T,String> withEmpty;

    public DisplayEntryCustSubImpl(SubscribedTranslationMessagesFactoryCore<T> _c, AbsMap<T, String> _w) {
        this.content = _c;
        this.withEmpty = _w;
    }

    @Override
    public IdList<SubscribedTranslation> buildSub(AbsMap<T, String> _messages) {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.add(new SubscribedTranslationMessages<T>(_messages,content,withEmpty));
        return ids_;
    }
}
