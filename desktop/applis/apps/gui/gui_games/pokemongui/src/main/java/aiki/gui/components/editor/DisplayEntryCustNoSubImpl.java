package aiki.gui.components.editor;

import code.util.*;

public final class DisplayEntryCustNoSubImpl<K> implements DisplayEntryCustSub<K> {

    @Override
    public IdList<SubscribedTranslation> buildSub(AbsMap<K, String> _messages) {
        return new IdList<SubscribedTranslation>();
    }
}
