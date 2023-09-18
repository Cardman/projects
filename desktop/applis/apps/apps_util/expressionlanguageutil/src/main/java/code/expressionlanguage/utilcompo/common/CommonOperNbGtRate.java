package code.expressionlanguage.utilcompo.common;

import code.expressionlanguage.common.symbol.CommonOperSymbol;
import code.expressionlanguage.common.symbol.SymbolConstants;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.RateStruct;
import code.maths.Rate;

public final class CommonOperNbGtRate implements CommonOperSymbol {
    @Override
    public Struct calculateOperator(Struct _first, Struct _second) {
        return BooleanStruct.of(Rate.strGreater(((RateStruct)_first).getRate(),((RateStruct)_second).getRate()));
    }

    @Override
    public String getSgn() {
        return Long.toString(SymbolConstants.SYMBOL_GT_PRIO)+"/2";
    }

}
