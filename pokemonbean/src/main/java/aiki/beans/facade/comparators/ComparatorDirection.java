package aiki.beans.facade.comparators;
import java.util.Comparator;

import aiki.map.enums.Direction;
import code.util.Numbers;

public final class ComparatorDirection implements Comparator<Direction> {

    @Override
    public int compare(Direction _o1, Direction _o2) {
        return Numbers.compare(_o1.ordinal(), _o2.ordinal());
    }

}