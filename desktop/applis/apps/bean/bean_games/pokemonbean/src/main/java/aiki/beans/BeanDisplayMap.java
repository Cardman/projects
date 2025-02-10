package aiki.beans;

import code.util.*;

public final class BeanDisplayMap<K,V> {
    private final BeanDisplay<K> displayKey;
    private final BeanDisplay<V> displayValue;

    public BeanDisplayMap(BeanDisplay<K> _k, BeanDisplay<V> _v) {
        this.displayKey = _k;
        this.displayValue = _v;
    }
    public void displayGrid(CommonBean _rend, AbsMap<K, V> _info, String _file, String _keyTitle, String... _cols) {
        _rend.displayHead(_info, _file, _keyTitle, _cols);
        display(_rend, _info);
        _rend.feedParents();
    }

    public void display(CommonBean _rend, AbsMap<K, V> _info) {
        for (EntryCust<K,V> e: _info.entryList()) {
            int count_ = displayKey.display(_rend, e.getKey(), 0);
            displayValue.display(_rend, e.getValue(),count_);
        }
    }
}
