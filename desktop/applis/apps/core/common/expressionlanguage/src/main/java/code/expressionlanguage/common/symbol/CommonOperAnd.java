package code.expressionlanguage.common.symbol;

import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;

public final class CommonOperAnd implements CommonOperSymbol {
    public static Struct andLogic(Struct _first, Struct _second) {
        if (BooleanStruct.isFalse(_first)) {
            return NumParsers.convertToBoolean(_first);
        }
        return NumParsers.convertToBoolean(_second);
    }

    @Override
    public Struct calculateOperator(Struct _first, Struct _second, byte _cast) {
        return andLogic(_first,_second);
    }

}
