package aiki.gui.components.editor;

import aiki.comparators.*;
import code.gui.*;

public final class KeyRetriever<K,V> implements IntRetriever<EditedCrudPair<K,V>,K> {
    @Override
    public K retrieve(EditedCrudPair<K, V> _elt) {
        return _elt.getKey();
    }
}
