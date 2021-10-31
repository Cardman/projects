package code.expressionlanguage.fcts;

import code.expressionlanguage.common.LongInfo;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NumberStruct;

public final class FctIntegerSafe extends FctNbLongSafeAbs {
    public FctIntegerSafe(AbsRadix _radix) {
        super(Integer.MIN_VALUE, Integer.MAX_VALUE, _radix);
    }

    @Override
    public NumberStruct build(LongInfo _info) {
        return new IntStruct((int)_info.getValue());
    }

}
