package code.util.comparators;
import code.util.Numbers;
import code.util.TreeMap;
import code.util.ints.Comparing;

public final class ComparatorTreeMapValue<T> implements Comparing<T> {

    private TreeMap<T, Integer> map;

    public ComparatorTreeMapValue(TreeMap<T, Integer> _map) {
        map = _map;
    }

    @Override
    public int compare(T _o1, T _o2) {
        return Numbers.compareLg(map.getVal(_o1), map.getVal(_o2));
    }

}
