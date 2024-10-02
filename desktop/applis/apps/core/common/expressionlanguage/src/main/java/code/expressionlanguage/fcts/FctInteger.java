package code.expressionlanguage.fcts;

import code.expressionlanguage.common.LongInfo;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NumberStruct;

public final class FctInteger extends FctNbLongAbs {
    public FctInteger(AbsRadix _radix) {
        this(_radix,new DefEncodeArg());
    }
    public FctInteger(AbsRadix _radix, AbsEncodeArg _e) {
        super(Integer.MIN_VALUE, Integer.MAX_VALUE, _radix, _e);
    }

    @Override
    public NumberStruct build(LongInfo _info) {
        return new IntStruct((int)_info.getValue());
    }

}
