package code.expressionlanguage.common.symbol;

import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.Struct;

public final class CommonOperNbLt implements CommonOperSymbol {
    @Override
    public Struct calculateOperator(Struct _first, Struct _second) {
        return NumParsers.quickCalculateLowerNb(_first,_second);
    }

    @Override
    public String getSgn() {
        return Long.toString(SymbolConstants.SYMBOL_LT_PRIO)+"/0";
    }

}
