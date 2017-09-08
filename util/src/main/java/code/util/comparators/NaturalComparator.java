package code.util.comparators;
import java.util.Comparator;

public final class NaturalComparator<T extends Comparable<T>> implements Comparator<T> {

    @Override
    public int compare(T _o1, T _o2) {
        return _o1.compareTo(_o2);
    }

}
