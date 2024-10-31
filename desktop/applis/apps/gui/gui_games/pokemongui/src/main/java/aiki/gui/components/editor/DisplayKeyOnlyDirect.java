package aiki.gui.components.editor;

import code.gui.*;
import code.util.core.*;

public final class DisplayKeyOnlyDirect<V> implements DisplayEntryCust<String,V> {

    @Override
    public String display(String _k, V _v) {
        return StringUtil.nullToEmpty(_k);
    }
}
