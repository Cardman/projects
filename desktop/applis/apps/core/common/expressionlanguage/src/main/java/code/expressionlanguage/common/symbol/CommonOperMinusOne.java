package code.expressionlanguage.common.symbol;

import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.structs.Struct;

public final class CommonOperMinusOne implements CommonOperSymbol {
    private final byte cast;
    public CommonOperMinusOne(byte _c) {
        cast = _c;
    }

    @Override
    public Struct calculateOperator(Struct _first, Struct _second) {
        return ExecNumericOperation.removeOne(NumParsers.convertToNumber(_first),cast);
    }

    @Override
    public String getSgn() {
        return Long.toString(SymbolConstants.SYMBOL_MINUS_ONE_PRIO)+"/"+cast;
    }

}
