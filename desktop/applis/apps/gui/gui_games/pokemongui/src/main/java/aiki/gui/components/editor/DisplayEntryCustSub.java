package aiki.gui.components.editor;

import code.util.AbsMap;
import code.util.IdList;

public interface DisplayEntryCustSub<K> {
    IdList<SubscribedTranslation> buildSub(AbsMap<K, String> _messages);
}
