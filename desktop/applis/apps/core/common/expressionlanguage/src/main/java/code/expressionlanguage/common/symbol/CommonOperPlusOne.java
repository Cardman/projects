package code.expressionlanguage.common.symbol;

import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.structs.Struct;

public final class CommonOperPlusOne implements CommonOperSymbol {
    private final byte cast;
    public CommonOperPlusOne(byte _c) {
        cast = _c;
    }

    @Override
    public Struct calculateOperator(Struct _first, Struct _second) {
        return ExecNumericOperation.addOne(NumParsers.convertToNumber(_first),cast);
    }
    @Override
    public String getSgn() {
        return Long.toString(SymbolConstants.SYMBOL_PLUS_ONE_PRIO)+"/"+cast;
    }

}
