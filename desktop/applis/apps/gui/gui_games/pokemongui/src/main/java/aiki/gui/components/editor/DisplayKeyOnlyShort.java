package aiki.gui.components.editor;

import code.gui.*;

public final class DisplayKeyOnlyShort<V> implements DisplayEntryCust<Integer, EditedCrudPair<Long,V>> {

    @Override
    public String display(Integer _k, EditedCrudPair<Long, V> _v) {
        return Long.toString(_v.getKey());
    }
}
