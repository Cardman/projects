package aiki.beans.facade.comparators;
import java.util.Comparator;

import code.util.Numbers;
import aiki.map.enums.Direction;

public final class ComparatorDirection implements Comparator<Direction> {

    @Override
    public int compare(Direction _o1, Direction _o2) {
        return Numbers.compare(_o1.ordinal(), _o2.ordinal());
    }

}
