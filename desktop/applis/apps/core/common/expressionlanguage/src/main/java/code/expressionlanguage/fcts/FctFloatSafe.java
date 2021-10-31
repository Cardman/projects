package code.expressionlanguage.fcts;

import code.expressionlanguage.common.DoubleInfo;
import code.expressionlanguage.structs.FloatStruct;
import code.expressionlanguage.structs.NumberStruct;

public final class FctFloatSafe extends FctNbDoubleSafeAbs {
    public FctFloatSafe() {
        super(Float.MIN_VALUE, Float.MAX_VALUE);
    }
    @Override
    public NumberStruct build(DoubleInfo _info) {
        return new FloatStruct((float) _info.getValue());
    }
}
