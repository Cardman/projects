package code.maths;

import code.util.ints.Comparing;

public final class ComparatorLgInt implements Comparing<LgInt> {
    @Override
    public int compare(LgInt _one, LgInt _two) {
        return _one.cmp(_two);
    }
}
