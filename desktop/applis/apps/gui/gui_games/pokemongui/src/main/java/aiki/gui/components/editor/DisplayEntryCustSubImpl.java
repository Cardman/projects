package aiki.gui.components.editor;

import code.util.*;

public final class DisplayEntryCustSubImpl implements DisplayEntryCustSub<String> {
    private final SubscribedTranslationMessagesFactory content;
    private final AbsMap<String,String> withEmpty;

    public DisplayEntryCustSubImpl(SubscribedTranslationMessagesFactory _c, AbsMap<String, String> _w) {
        this.content = _c;
        this.withEmpty = _w;
    }

    @Override
    public SubscribedTranslation buildSub(AbsMap<String, String> _messages) {
        return new SubscribedTranslationMessages(_messages,content,withEmpty);
    }
}
