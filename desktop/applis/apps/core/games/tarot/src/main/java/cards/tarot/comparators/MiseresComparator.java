package cards.tarot.comparators;

import cards.tarot.enumerations.Miseres;
import code.util.Numbers;
import code.util.ints.Comparing;

public final class MiseresComparator implements Comparing<Miseres> {
    @Override
    public int compare(Miseres _one, Miseres _two) {
        return Numbers.compareLg(_one.ordinal(), _two.ordinal());
    }
}
