package code.util.comparators;
import java.util.Comparator;

import code.util.ints.Cmp;

public class NatComparator<T extends Cmp<T>> implements Comparator<T> {

    @Override
    public int compare(T _o1, T _o2) {
        return _o1.cmp(_o2);
    }

}
