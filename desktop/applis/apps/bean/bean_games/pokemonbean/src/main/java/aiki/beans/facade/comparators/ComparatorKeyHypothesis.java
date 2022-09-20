package aiki.beans.facade.comparators;

import aiki.beans.facade.fight.KeyHypothesis;
import code.util.ints.Comparing;

public final class ComparatorKeyHypothesis implements Comparing<KeyHypothesis> {
    @Override
    public int compare(KeyHypothesis _one, KeyHypothesis _two) {
        return _one.cmp(_two);
    }
}
