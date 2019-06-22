package aiki.beans.facade.comparators;
import aiki.map.enums.Direction;
import code.util.*;
import code.util.ints.Comparing;

public final class ComparatorDirection implements Comparing<Direction> {

    @Override
    public int compare(Direction _o1, Direction _o2) {
        return Numbers.compareLg(_o1.ordinal(), _o2.ordinal());
    }

}