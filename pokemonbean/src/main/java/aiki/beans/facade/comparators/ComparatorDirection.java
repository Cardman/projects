package aiki.beans.facade.comparators;
import code.util.ints.Comparing;

import aiki.map.enums.Direction;
import code.util.Numbers;

public final class ComparatorDirection implements Comparing<Direction> {

    @Override
    public int compare(Direction _o1, Direction _o2) {
        return Numbers.compare(_o1.ordinal(), _o2.ordinal());
    }

}