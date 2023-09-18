package code.expressionlanguage.common.symbol;

import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.Struct;

public final class CommonOperIdOp implements CommonOperSymbol {
    private final byte cast;
    public CommonOperIdOp(byte _c) {
        cast = _c;
    }

    @Override
    public Struct calculateOperator(Struct _first, Struct _second) {
        return NumParsers.idNumber(NumParsers.convertToNumber(_first),cast);
    }

    @Override
    public String getSgn() {
        return Long.toString(SymbolConstants.SYMBOL_ID_PRIO)+"/"+cast;
    }

}
