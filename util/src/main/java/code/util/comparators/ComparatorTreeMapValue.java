package code.util.comparators;
import java.util.Comparator;

import code.util.Numbers;
import code.util.TreeMap;

public class ComparatorTreeMapValue<T, U extends Number> implements Comparator<T> {

    private TreeMap<T, U> map;

    public ComparatorTreeMapValue(TreeMap<T, U> _map) {
        map = _map;
    }

    @Override
    public int compare(T _o1, T _o2) {
        return Numbers.compare(map.getVal(_o1), map.getVal(_o2));
    }

}
