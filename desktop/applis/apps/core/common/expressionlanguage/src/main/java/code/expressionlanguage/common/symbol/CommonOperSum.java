package code.expressionlanguage.common.symbol;

import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.Struct;

public final class CommonOperSum implements CommonOperSymbol {
    private final byte cast;
    public CommonOperSum(byte _c) {
        cast = _c;
    }

    @Override
    public Struct calculateOperator(Struct _first, Struct _second) {
        return NumParsers.calculateSum(NumParsers.convertToNumber(_first),
                NumParsers.convertToNumber(_second), cast);
    }
    @Override
    public String getSgn() {
        return Long.toString(SymbolConstants.SYMBOL_SUM_PRIO)+"/"+cast;
    }

}
