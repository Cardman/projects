package aiki.gui.components.editor;

import code.gui.*;
import code.util.*;

public final class ConverterCommonMap<K,V> {
    public void feed(AbsMap<K,V> _dest, CustList<EditedCrudPair<K,V>> _tree) {
        for (EditedCrudPair<K, V> e: _tree) {
            _dest.addEntry(e.getKey(),e.getValue());
        }
    }
    public void copy(AbsTechnicalCopier<K> _key, AbsTechnicalCopier<V> _value, AbsMap<K,V> _dest, AbsMap<K,V> _tree) {
        for (EntryCust<K, V> e: _tree.entryList()) {
            _dest.addEntry(_key.copy(e.getKey()),_value.copy(e.getValue()));
        }
    }
}
