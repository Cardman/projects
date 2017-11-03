package code.util.comparators;
import java.util.Comparator;

import code.util.AbEqList;

public final class ComparatorIndexes<T> implements Comparator<T> {

    private AbEqList<T> order;

    public ComparatorIndexes(AbEqList<T> _order) {
        order = _order;
    }

    @Override
    public int compare(T _o1, T _o2) {
        return order.indexOfObj(_o1) - order.indexOfObj(_o2);
    }

}
