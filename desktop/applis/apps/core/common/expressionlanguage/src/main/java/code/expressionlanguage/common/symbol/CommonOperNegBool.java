package code.expressionlanguage.common.symbol;

import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;

public final class CommonOperNegBool implements CommonOperSymbol {
    @Override
    public Struct calculateOperator(Struct _first, Struct _second) {
        BooleanStruct o_ = NumParsers.convertToBoolean(_first);
        return o_.neg();
    }

    @Override
    public String getSgn() {
        return Long.toString(SymbolConstants.SYMBOL_NEG_BOOL_PRIO);
    }

}
