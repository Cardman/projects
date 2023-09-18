package code.expressionlanguage.common.symbol;

import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.Struct;

public final class CommonOperOpposite implements CommonOperSymbol {
    private final byte cast;
    public CommonOperOpposite(byte _c) {
        cast = _c;
    }

    @Override
    public Struct calculateOperator(Struct _first, Struct _second) {
        return NumParsers.opposite(NumParsers.convertToNumber(_first),cast);
    }
    @Override
    public String getSgn() {
        return Long.toString(SymbolConstants.SYMBOL_OPPOSITE_PRIO)+"/"+cast;
    }

}
