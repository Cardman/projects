package code.util.comparators;
import code.util.ints.Comparing;

public final class NaturalComparator<T extends Comparable<T>> implements Comparing<T> {

    @Override
    public int compare(T _o1, T _o2) {
        return _o1.compareTo(_o2);
    }

}
