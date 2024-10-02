package code.expressionlanguage.fcts;

import code.expressionlanguage.common.LongInfo;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.ShortStruct;

public final class FctShort extends FctNbLongAbs {
    public FctShort(AbsRadix _radix) {
        this(_radix, new DefEncodeArg());
    }
    public FctShort(AbsRadix _radix, AbsEncodeArg _e) {
        super(Short.MIN_VALUE, Short.MAX_VALUE, _radix, _e);
    }

    @Override
    public NumberStruct build(LongInfo _info) {
        return new ShortStruct((short)_info.getValue());
    }

}
