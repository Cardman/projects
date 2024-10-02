package code.expressionlanguage.fcts;

import code.expressionlanguage.common.LongInfo;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.NumberStruct;

public final class FctLong extends FctNbLongAbs {
    public FctLong(AbsRadix _radix) {
        this(_radix, new DefEncodeArg());
    }
    public FctLong(AbsRadix _radix, AbsEncodeArg _e) {
        super(Long.MIN_VALUE, Long.MAX_VALUE, _radix, _e);
    }
    @Override
    public NumberStruct build(LongInfo _info) {
        return new LongStruct(_info.getValue());
    }

}
