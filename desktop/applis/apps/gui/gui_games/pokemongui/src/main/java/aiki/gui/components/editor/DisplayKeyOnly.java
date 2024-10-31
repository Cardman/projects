package aiki.gui.components.editor;

import code.gui.DisplayEntryCust;
import code.util.AbsMap;
import code.util.core.StringUtil;

public final class DisplayKeyOnly<K,V> implements DisplayEntryCust<K,V> {
    private final AbsMap<K,String> messages;

    public DisplayKeyOnly(AbsMap<K, String> _m) {
        this.messages = _m;
    }

    @Override
    public String display(K _k, V _v) {
        return StringUtil.nullToEmpty(messages.getVal(_k));
    }
}
