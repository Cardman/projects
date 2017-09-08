package aiki.comparators;
import java.util.Comparator;

import code.util.Numbers;
import aiki.fight.EndRoundMainElements;

public final class ComparatorEndRoundMainElements implements
        Comparator<EndRoundMainElements> {

    @Override
    public int compare(EndRoundMainElements _o1, EndRoundMainElements _o2) {
        return Numbers.compare(_o1.getNumberIncrement(), _o2.getNumberIncrement());
    }

}
