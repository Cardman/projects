package cards.tarot.comparators;

import cards.tarot.enumerations.Handfuls;
import code.util.core.NumberUtil;
import code.util.ints.Comparing;

public final class HandfulComparator implements Comparing<Handfuls> {
    @Override
    public int compare(Handfuls _one, Handfuls _two) {
        return NumberUtil.compareLg(_one.ordinal(), _two.ordinal());
    }
}
