package code.expressionlanguage.common.symbol;

import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.structs.Struct;

public final class CommonOperMinusOne implements CommonOperSymbol {
    @Override
    public Struct calculateOperator(Struct _first, Struct _second, byte _cast) {
        return ExecNumericOperation.removeOne(NumParsers.convertToNumber(_first),_cast);
    }

}
