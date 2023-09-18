package code.expressionlanguage.common.symbol;

import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.Struct;

public final class CommonOperStrGe implements CommonOperSymbol {
    @Override
    public Struct calculateOperator(Struct _first, Struct _second) {
        return NumParsers.quickCalculateLowerStr(_first,_second).neg();
    }

    @Override
    public String getSgn() {
        return Long.toString(SymbolConstants.SYMBOL_GE_PRIO)+"/1";
    }

}
