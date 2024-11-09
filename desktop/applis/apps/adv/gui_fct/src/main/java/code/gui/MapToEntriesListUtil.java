package code.gui;

import code.util.*;

public final class MapToEntriesListUtil<K,V> {
    public CustList<EditedCrudPair<K,V>> build(AbsMap<K,V> _map) {
        CustList<EditedCrudPair<K,V>> ls_ = new CustList<EditedCrudPair<K, V>>();
        for (EntryCust<K,V> e: _map.entryList()) {
            ls_.add(new EditedCrudPair<K, V>(e.getKey(), e.getValue()));
        }
        return ls_;
    }
    public void feedMap(CustList<EditedCrudPair<K,V>> _ls,AbsMap<K,V> _dest) {
        for (EditedCrudPair<K, V> e: _ls) {
            _dest.addEntry(e.getKey(),e.getValue());
        }
    }
}
