package code.maths;

import code.util.ints.Comparing;

public final class ComparatorRate implements Comparing<Rate> {
    @Override
    public int compare(Rate _one, Rate _two) {
        return _one.cmp(_two);
    }
}
