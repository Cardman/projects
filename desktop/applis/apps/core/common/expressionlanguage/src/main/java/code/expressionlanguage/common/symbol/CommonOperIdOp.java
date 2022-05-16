package code.expressionlanguage.common.symbol;

import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.Struct;

public final class CommonOperIdOp implements CommonOperSymbol {
    @Override
    public Struct calculateOperator(Struct _first, Struct _second, byte _cast) {
        return NumParsers.idNumber(NumParsers.convertToNumber(_first),_cast);
    }

}
