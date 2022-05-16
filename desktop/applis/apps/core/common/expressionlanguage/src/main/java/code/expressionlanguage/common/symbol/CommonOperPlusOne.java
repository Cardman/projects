package code.expressionlanguage.common.symbol;

import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.structs.Struct;

public final class CommonOperPlusOne implements CommonOperSymbol {
    @Override
    public Struct calculateOperator(Struct _first, Struct _second, byte _cast) {
        return ExecNumericOperation.addOne(NumParsers.convertToNumber(_first),_cast);
    }

}
