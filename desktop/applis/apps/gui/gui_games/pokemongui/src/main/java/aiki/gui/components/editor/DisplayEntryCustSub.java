package aiki.gui.components.editor;

import code.util.*;

public interface DisplayEntryCustSub<K> {
    IdList<SubscribedTranslation> buildSub(AbsMap<K, String> _messages);
}
