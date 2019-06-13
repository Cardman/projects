package code.util.comparators;
import code.util.Numbers;
import code.util.TreeMap;
import code.util.ints.Comparing;

public final class ComparatorTreeMapValue<T, U extends Number> implements Comparing<T> {

    private TreeMap<T, U> map;

    public ComparatorTreeMapValue(TreeMap<T, U> _map) {
        map = _map;
    }

    @Override
    public int compare(T _o1, T _o2) {
        return Numbers.compareLg(map.getVal(_o1).longValue(), map.getVal(_o2).longValue());
    }

}
