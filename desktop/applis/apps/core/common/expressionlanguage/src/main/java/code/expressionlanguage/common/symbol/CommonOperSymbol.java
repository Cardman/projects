package code.expressionlanguage.common.symbol;

import code.expressionlanguage.structs.Struct;

public interface CommonOperSymbol {
    Struct calculateOperator(
            Struct _first, Struct _second, byte _cast);
}
