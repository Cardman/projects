package code.expressionlanguage.fcts;

import code.expressionlanguage.common.LongInfo;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NumberStruct;

public final class FctIntegerSafe extends FctNbLongSafeAbs {
    public FctIntegerSafe(AbsRadix _radix) {
        this(_radix, new DefEncodeArg());
    }
    public FctIntegerSafe(AbsRadix _radix, AbsEncodeArg _a) {
        super(Integer.MIN_VALUE, Integer.MAX_VALUE, _radix, _a);
    }
    @Override
    public NumberStruct build(LongInfo _info) {
        return new IntStruct((int)_info.getValue());
    }

}
