package code.expressionlanguage.fcts;

import code.expressionlanguage.common.DoubleInfo;
import code.expressionlanguage.structs.DoubleStruct;
import code.expressionlanguage.structs.NumberStruct;

public final class FctDouble extends FctNbDoubleAbs {
    public FctDouble() {
        super(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }
    @Override
    public NumberStruct build(DoubleInfo _info) {
        return new DoubleStruct(_info.getValue());
    }
}
