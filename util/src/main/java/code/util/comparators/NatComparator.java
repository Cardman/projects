package code.util.comparators;
import code.util.ints.Cmp;
import code.util.ints.Comparing;

public final class NatComparator<T extends Cmp<T>> implements Comparing<T> {

    @Override
    public int compare(T _o1, T _o2) {
        return _o1.cmp(_o2);
    }

}
