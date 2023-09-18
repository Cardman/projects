package code.expressionlanguage.common.symbol;

import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.Struct;

public final class CommonOperStrLe implements CommonOperSymbol {
    @Override
    public Struct calculateOperator(Struct _first, Struct _second) {
        return NumParsers.quickCalculateGreaterStr(_first,_second).neg();
    }

    @Override
    public String getSgn() {
        return Long.toString(SymbolConstants.SYMBOL_LE_PRIO)+"/1";
    }

}
