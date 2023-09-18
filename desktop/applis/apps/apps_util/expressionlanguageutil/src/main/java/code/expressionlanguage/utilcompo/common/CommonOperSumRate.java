package code.expressionlanguage.utilcompo.common;

import code.expressionlanguage.common.symbol.CommonOperSymbol;
import code.expressionlanguage.common.symbol.SymbolConstants;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.RateStruct;
import code.maths.Rate;

public final class CommonOperSumRate implements CommonOperSymbol {

    @Override
    public Struct calculateOperator(Struct _first, Struct _second) {
        return new RateStruct(Rate.plus(((RateStruct)_first).getRate(),((RateStruct)_second).getRate()));
    }
    @Override
    public String getSgn() {
        return Long.toString(SymbolConstants.SYMBOL_SUM_PRIO)+"/"+PrimitiveTypes.RATE;
    }

}
