package code.expressionlanguage.fcts;

import code.expressionlanguage.common.LongInfo;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.NumberStruct;

public final class FctLongSafe extends FctNbLongSafeAbs {
    public FctLongSafe(AbsRadix _radix) {
        this(_radix, new DefEncodeArg());
    }
    public FctLongSafe(AbsRadix _radix, AbsEncodeArg _a) {
        super(Long.MIN_VALUE, Long.MAX_VALUE, _radix, _a);
    }
    @Override
    public NumberStruct build(LongInfo _info) {
        return new LongStruct(_info.getValue());
    }

}
