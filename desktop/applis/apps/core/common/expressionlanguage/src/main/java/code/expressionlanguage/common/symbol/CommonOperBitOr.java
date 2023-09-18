package code.expressionlanguage.common.symbol;

import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.Struct;

public final class CommonOperBitOr implements CommonOperSymbol {
    private final byte cast;
    public CommonOperBitOr(byte _c) {
        cast = _c;
    }

    @Override
    public Struct calculateOperator(Struct _first, Struct _second) {
        return NumParsers.calculateOr(_first,
                _second, cast);
    }

    @Override
    public String getSgn() {
        return Long.toString(SymbolConstants.SYMBOL_BIT_OR_PRIO)+"/"+cast;
    }
}
