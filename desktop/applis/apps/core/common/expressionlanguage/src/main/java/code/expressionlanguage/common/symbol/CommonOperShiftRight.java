package code.expressionlanguage.common.symbol;

import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.Struct;

public final class CommonOperShiftRight implements CommonOperSymbol {
    @Override
    public Struct calculateOperator(Struct _first, Struct _second, byte _cast) {
        return NumParsers.calculateShiftRight(NumParsers.convertToNumber(_first),
                NumParsers.convertToNumber(_second), _cast);
    }

}
