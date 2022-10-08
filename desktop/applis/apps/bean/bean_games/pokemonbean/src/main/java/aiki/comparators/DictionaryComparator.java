package aiki.comparators;

import code.util.AbsComparerTreeMap;
import code.util.AbsMap;

public final class DictionaryComparator<K,V> extends AbsComparerTreeMap<K, V> {

    public DictionaryComparator(AbsMap<K,String> _tr) {
        super(new ComparatorTr<K>(_tr));
    }

}
