package code.util;
import code.util.ints.Comparing;

/**
    @author Cardman
*/

public final class TreeMap<K, V> extends AbsBasicTreeMap<K, V> {

    private final Comparing<K> comparator;

    public TreeMap(Comparing<K> _cmp) {
        comparator = _cmp;
    }

    public Comparing<K> comparator() {
        return comparator;
    }

    @Override
    protected int compare(K _one, K _two) {
        return comparator.compare(_one,_two);
    }
}
