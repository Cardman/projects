package code.util.comparators;
import code.util.Numbers;
import code.util.ints.Comparing;

public final class ComparatorEnum<T extends Enum<T>> implements Comparing<T> {

    @Override
    public int compare(T _e1, T _e2) {
        return Numbers.compareLg(_e1.ordinal(), _e2.ordinal());
    }

}
