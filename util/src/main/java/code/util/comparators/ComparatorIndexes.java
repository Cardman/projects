package code.util.comparators;
import java.util.Comparator;

import code.util.EnumList;

public class ComparatorIndexes<T extends Enum<T>> implements Comparator<T> {

    private EnumList<T> order;

    public ComparatorIndexes(EnumList<T> _order) {
        order = _order;
    }

    @Override
    public int compare(T _o1, T _o2) {
        return order.indexOfObj(_o1) - order.indexOfObj(_o2);
    }

}
