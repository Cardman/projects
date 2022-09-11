package cards.tarot.comparators;

import cards.tarot.enumerations.Miseres;
import code.util.AbsComparerTreeMap;

public final class SortedMiseres extends AbsComparerTreeMap<Miseres, Short> {
    public SortedMiseres() {
        super(new MiseresComparator());
    }
}
