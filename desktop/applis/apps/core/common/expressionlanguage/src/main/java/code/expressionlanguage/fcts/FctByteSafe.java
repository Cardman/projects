package code.expressionlanguage.fcts;

import code.expressionlanguage.common.LongInfo;
import code.expressionlanguage.structs.ByteStruct;
import code.expressionlanguage.structs.NumberStruct;

public final class FctByteSafe extends FctNbLongSafeAbs {
    public FctByteSafe(AbsRadix _radix) {
        this(_radix, new DefEncodeArg());
    }
    public FctByteSafe(AbsRadix _radix, AbsEncodeArg _a) {
        super(Byte.MIN_VALUE, Byte.MAX_VALUE, _radix, _a);
    }

    @Override
    public NumberStruct build(LongInfo _info) {
        return new ByteStruct((byte)_info.getValue());
    }

}
