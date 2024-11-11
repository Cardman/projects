package aiki.gui.components.editor;

import code.util.AbsMap;

public interface DisplayEntryCustSub<K> {
    SubscribedTranslation buildSub(AbsMap<K, String> _messages);
}
