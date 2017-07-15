package code.util.comparators;
import java.util.Comparator;

import code.util.AbsMap;

public class ComparatorMapValue<T> implements Comparator<T> {

    private AbsMap<T, String> map;

    public ComparatorMapValue(AbsMap<T, String> _map) {
        map = _map;
    }

    @Override
    public int compare(T _o1, T _o2) {
        return map.getVal(_o1).compareTo(map.getVal(_o2));
    }

}
