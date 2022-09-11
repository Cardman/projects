package code.util;
import code.util.ints.Comparing;

/**
    @author Cardman
*/

public final class TreeMap<K, V> extends AbsComparerTreeMap<K, V> {


    public TreeMap(Comparing<K> _cmp) {
        super(_cmp);
    }

}
