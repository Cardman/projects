package code.expressionlanguage.common.symbol;

import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.Struct;

public final class CommonOperDiv implements CommonOperSymbol {
    private final byte cast;
    public CommonOperDiv(byte _c) {
        cast = _c;
    }

    @Override
    public Struct calculateOperator(Struct _first, Struct _second) {
        return NumParsers.calculateDiv(NumParsers.convertToNumber(_first),
                NumParsers.convertToNumber(_second), cast);
    }

    @Override
    public String getSgn() {
        return Long.toString(SymbolConstants.SYMBOL_DIV_PRIO)+"/"+cast;
    }
}
