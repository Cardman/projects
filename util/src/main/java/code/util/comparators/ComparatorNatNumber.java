package code.util.comparators;
import code.util.Numbers;
import code.util.ints.Comparing;

public final class ComparatorNatNumber<T extends Number> implements Comparing<T> {

    @Override
    public int compare(T _o1, T _o2) {
        return Numbers.compare(_o1, _o2);
    }

}
