package code.expressionlanguage.common.symbol;

import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.Struct;

public final class CommonOperNbGt implements CommonOperSymbol {
    @Override
    public Struct calculateOperator(Struct _first, Struct _second) {
        return NumParsers.quickCalculateGreaterNb(_first,_second);
    }

    @Override
    public String getSgn() {
        return Long.toString(SymbolConstants.SYMBOL_GT_PRIO)+"/0";
    }

}
