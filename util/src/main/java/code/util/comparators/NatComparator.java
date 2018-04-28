package code.util.comparators;
import code.util.ints.Comparing;

import code.util.ints.Cmp;

public final class NatComparator<T extends Cmp<T>> implements Comparing<T> {

    @Override
    public int compare(T _o1, T _o2) {
        return _o1.cmp(_o2);
    }

}
