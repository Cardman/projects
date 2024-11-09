package code.gui;

import code.maths.LgInt;
import code.maths.montecarlo.AbMonteCarlo;
import code.util.*;

public final class MapToEntriesListUtil<K,V> {
    public CustList<EditedCrudPair<K,V>> build(AbsMap<K,V> _map) {
        CustList<EditedCrudPair<K,V>> ls_ = new CustList<EditedCrudPair<K, V>>();
        for (EntryCust<K,V> e: _map.entryList()) {
            ls_.add(new EditedCrudPair<K, V>(e.getKey(), e.getValue()));
        }
        return ls_;
    }
    public CustList<EditedCrudPair<K, LgInt>> build(AbMonteCarlo<K> _map) {
        CustList<EditedCrudPair<K,LgInt>> ls_ = new CustList<EditedCrudPair<K, LgInt>>();
        int len_ = _map.size();
        for (int i = 0; i < len_; i++) {
            ls_.add(new EditedCrudPair<K, LgInt>(_map.getKey(i), _map.getFreq(i)));
        }
        return ls_;
    }
    public void feedMap(CustList<EditedCrudPair<K,V>> _ls,AbsMap<K,V> _dest) {
        for (EditedCrudPair<K, V> e: _ls) {
            _dest.addEntry(e.getKey(),e.getValue());
        }
    }
    public void feedMap(CustList<EditedCrudPair<K,LgInt>> _ls,AbMonteCarlo<K> _dest) {
        for (EditedCrudPair<K, LgInt> e: _ls) {
            _dest.addQuickEvent(e.getKey(),e.getValue());
        }
    }
}
