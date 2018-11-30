package aiki.comparators;
import aiki.fight.EndRoundMainElements;
import code.util.Numbers;
import code.util.ints.Comparing;

public final class ComparatorEndRoundMainElements implements
        Comparing<EndRoundMainElements> {

    @Override
    public int compare(EndRoundMainElements _o1, EndRoundMainElements _o2) {
        return Numbers.compare(_o1.getNumberIncrement(), _o2.getNumberIncrement());
    }

}
