package code.gui.document;

import code.util.*;

public final class BeanDisplayMap<K,V> {
    private final BeanDisplay<K> displayKey;
    private final BeanDisplay<V> displayValue;

    public BeanDisplayMap(BeanDisplay<K> _k, BeanDisplay<V> _v) {
        this.displayKey = _k;
        this.displayValue = _v;
    }
    public void display(AbsBeanRender _rend, AbsMap<K, V> _info) {
        for (EntryCust<K,V> e: _info.entryList()) {
            int count_ = displayKey.display(_rend, e.getKey(), 0);
            displayValue.display(_rend, e.getValue(),count_);
        }
    }
}
