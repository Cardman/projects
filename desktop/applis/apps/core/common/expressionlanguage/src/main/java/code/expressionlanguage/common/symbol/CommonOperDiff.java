package code.expressionlanguage.common.symbol;

import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.Struct;

public final class CommonOperDiff implements CommonOperSymbol {
    private final byte cast;
    public CommonOperDiff(byte _c) {
        cast = _c;
    }

    @Override
    public Struct calculateOperator(Struct _first, Struct _second) {
        return NumParsers.calculateDiff(NumParsers.convertToNumber(_first),
                NumParsers.convertToNumber(_second), cast);
    }

    @Override
    public String getSgn() {
        return Long.toString(SymbolConstants.SYMBOL_DIFF_PRIO)+"/"+cast;
    }

}
