package code.util.comparators;
import code.util.Numbers;
import code.util.ints.Comparing;

public final class ComparatorNatNumber<T extends Number> implements Comparing<T> {

    @Override
    public int compare(T _o1, T _o2) {
        return Numbers.compareLg(_o1.longValue(), _o2.longValue());
    }

}
