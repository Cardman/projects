package cards.tarot.comparators;

import cards.tarot.enumerations.Miseres;
import code.util.core.NumberUtil;
import code.util.ints.Comparing;

public final class MiseresComparator implements Comparing<Miseres> {
    @Override
    public int compare(Miseres _one, Miseres _two) {
        return NumberUtil.compareLg(_one.getOrder(), _two.getOrder());
    }
}
