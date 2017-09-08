package code.util.comparators;
import java.util.Comparator;

import code.util.Numbers;

public final class ComparatorNatNumber<T extends Number> implements Comparator<T> {

    @Override
    public int compare(T _o1, T _o2) {
        return Numbers.compare(_o1, _o2);
    }

}
