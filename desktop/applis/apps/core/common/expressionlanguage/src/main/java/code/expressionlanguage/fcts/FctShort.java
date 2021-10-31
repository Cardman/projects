package code.expressionlanguage.fcts;

import code.expressionlanguage.common.LongInfo;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.ShortStruct;

public final class FctShort extends FctNbLongAbs {
    public FctShort(AbsRadix _radix) {
        super(Short.MIN_VALUE, Short.MAX_VALUE, _radix);
    }

    @Override
    public NumberStruct build(LongInfo _info) {
        return new ShortStruct((short)_info.getValue());
    }

}
