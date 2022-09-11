package code.util;

import code.util.ints.Comparing;

public abstract class AbsComparerTreeMap<K,V> extends AbsBasicTreeMap<K,V> {
    private final Comparing<K> comparer;

    protected AbsComparerTreeMap(Comparing<K> _cmp) {
        this.comparer = _cmp;
    }

    public Comparing<K> comparator() {
        return comparer;
    }

    @Override
    protected int compare(K _one, K _two) {
        return comparer.compare(_one, _two);
    }
}
