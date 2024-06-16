package code.util.core;

import code.util.ints.IntSplitPartsFields;

public final class DefSplitPartsFields implements IntSplitPartsFields {
    @Override
    public boolean split(FirstSeparatorFind _found, char _ch, int[] _nb) {
        return _found.getUsed() == _ch;
    }
}
