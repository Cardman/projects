package code.expressionlanguage.common.symbol;

import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.Struct;

public final class CommonOperBitShiftLeft implements CommonOperSymbol {
    private final byte cast;
    public CommonOperBitShiftLeft(byte _c) {
        cast = _c;
    }

    @Override
    public Struct calculateOperator(Struct _first, Struct _second) {
        return NumParsers.calculateBitShiftLeft(NumParsers.convertToNumber(_first),
                NumParsers.convertToNumber(_second), cast);
    }

    @Override
    public String getSgn() {
        return Long.toString(SymbolConstants.SYMBOL_BIT_SHIFT_LEFT)+"/"+cast;
    }

}
