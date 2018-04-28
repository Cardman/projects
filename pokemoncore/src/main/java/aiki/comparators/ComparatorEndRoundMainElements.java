package aiki.comparators;
import code.util.ints.Comparing;

import aiki.fight.EndRoundMainElements;
import code.util.Numbers;

public final class ComparatorEndRoundMainElements implements
        Comparing<EndRoundMainElements> {

    @Override
    public int compare(EndRoundMainElements _o1, EndRoundMainElements _o2) {
        return Numbers.compare(_o1.getNumberIncrement(), _o2.getNumberIncrement());
    }

}
