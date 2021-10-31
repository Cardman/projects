package code.expressionlanguage.fcts;

import code.expressionlanguage.common.LongInfo;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.NumberStruct;

public final class FctLongSafe extends FctNbLongSafeAbs {
    public FctLongSafe(AbsRadix _radix) {
        super(Long.MIN_VALUE, Long.MAX_VALUE, _radix);
    }

    @Override
    public NumberStruct build(LongInfo _info) {
        return new LongStruct(_info.getValue());
    }

}
