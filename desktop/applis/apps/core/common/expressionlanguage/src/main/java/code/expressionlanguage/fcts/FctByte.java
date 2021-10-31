package code.expressionlanguage.fcts;

import code.expressionlanguage.common.LongInfo;
import code.expressionlanguage.structs.ByteStruct;
import code.expressionlanguage.structs.NumberStruct;

public final class FctByte extends FctNbLongAbs {
    public FctByte(AbsRadix _radix) {
        super(Byte.MIN_VALUE, Byte.MAX_VALUE, _radix);
    }

    @Override
    public NumberStruct build(LongInfo _info) {
        return new ByteStruct((byte)_info.getValue());
    }

}
