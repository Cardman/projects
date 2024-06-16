package code.util.core;

import code.util.ints.IntSplitPartsFields;

public final class LimSplitPartsFields implements IntSplitPartsFields {
    private final int limit;

    public LimSplitPartsFields(int _l) {
        this.limit = _l;
    }

    @Override
    public boolean split(FirstSeparatorFind _found, char _ch, int[] _nb) {
        char used_ = _found.getUsed();
        if (used_ == _ch) {
            _nb[0]++;
        }
        return used_ == _ch && _nb[0] <= limit;
    }
}
