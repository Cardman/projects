package aiki.comparators;

import code.util.AbsComparerTreeMap;
import code.util.AbsMap;
import code.util.ints.Comparing;

public final class DictionaryComparator<K,V> extends AbsComparerTreeMap<K, V> {

    public DictionaryComparator(AbsMap<K,String> _tr) {
        this(new ComparatorTrWrapper<K>().wrap(_tr));
    }

    public DictionaryComparator(Comparing<K> _tr) {
        super(_tr);
    }

}
