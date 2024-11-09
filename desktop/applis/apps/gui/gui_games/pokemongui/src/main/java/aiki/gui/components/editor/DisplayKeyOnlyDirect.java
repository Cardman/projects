package aiki.gui.components.editor;

import code.gui.*;
import code.util.core.*;

public final class DisplayKeyOnlyDirect<V> implements DisplayEntryCust<Integer,EditedCrudPair<String,V>> {

    @Override
    public String display(Integer _k, EditedCrudPair<String,V> _v) {
        return StringUtil.nullToEmpty(_v.getKey());
    }
}
