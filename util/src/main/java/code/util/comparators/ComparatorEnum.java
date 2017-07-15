package code.util.comparators;
import java.util.Comparator;

import code.util.Numbers;

public class ComparatorEnum<T extends Enum<T>> implements Comparator<T> {

    @Override
    public int compare(T _e1, T _e2) {
        return Numbers.compare(_e1.ordinal(), _e2.ordinal());
    }

}
