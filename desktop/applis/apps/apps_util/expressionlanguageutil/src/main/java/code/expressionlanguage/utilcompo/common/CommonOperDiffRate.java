package code.expressionlanguage.utilcompo.common;

import code.expressionlanguage.common.symbol.CommonOperSymbol;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.RateStruct;
import code.maths.Rate;

public final class CommonOperDiffRate implements CommonOperSymbol {

    @Override
    public Struct calculateOperator(Struct _first, Struct _second, byte _cast) {
        return new RateStruct(Rate.minus(((RateStruct)_first).getRate(),((RateStruct)_second).getRate()));
    }
}
