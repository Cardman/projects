package cards.tarot.comparators;

import cards.tarot.enumerations.Handfuls;
import code.util.AbsComparerTreeMap;

public final class SortedHandfuls extends AbsComparerTreeMap<Handfuls, Short> {
    public SortedHandfuls() {
        super(new HandfulComparator());
    }
}
