package code.expressionlanguage.fcts;

import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.Struct;

public final class DefRadix implements AbsRadix {
    @Override
    public int radix(Struct _arg) {
        return NumParsers.DEFAULT_RADIX;
    }
}
