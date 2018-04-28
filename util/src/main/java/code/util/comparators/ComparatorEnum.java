package code.util.comparators;
import code.util.ints.Comparing;

import code.util.Numbers;

public final class ComparatorEnum<T extends Enum<T>> implements Comparing<T> {

    @Override
    public int compare(T _e1, T _e2) {
        return Numbers.compare(_e1.ordinal(), _e2.ordinal());
    }

}
