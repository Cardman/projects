package code.expressionlanguage.common.symbol;

import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class CommonOperNullSafe implements CommonOperSymbol {

    @Override
    public Struct calculateOperator(Struct _first, Struct _second, byte _cast) {
        if (_first == NullStruct.NULL_VALUE) {
            return _second;
        }
        return _first;
    }

}
