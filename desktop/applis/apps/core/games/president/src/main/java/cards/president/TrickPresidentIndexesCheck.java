package cards.president;

import code.util.Ints;

public final class TrickPresidentIndexesCheck {
    private final TrickPresident trick;
    private final Ints indexes;

    public TrickPresidentIndexesCheck(TrickPresident _t, Ints _i) {
        this.trick = _t;
        this.indexes = _i;
    }

    public Ints getIndexes() {
        return indexes;
    }

    public TrickPresident getTrick() {
        return trick;
    }
}
