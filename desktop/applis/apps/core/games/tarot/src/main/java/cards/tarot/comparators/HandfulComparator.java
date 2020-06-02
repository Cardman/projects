package cards.tarot.comparators;

import cards.tarot.enumerations.Handfuls;
import code.util.Numbers;
import code.util.ints.Comparing;

public final class HandfulComparator implements Comparing<Handfuls> {
    @Override
    public int compare(Handfuls _one, Handfuls _two) {
        return Numbers.compareLg(_one.ordinal(), _two.ordinal());
    }
}
