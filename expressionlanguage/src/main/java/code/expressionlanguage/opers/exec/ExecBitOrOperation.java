package code.expressionlanguage.opers.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.opers.BitOrOperation;
import code.expressionlanguage.structs.NumberStruct;

public final class ExecBitOrOperation extends ExecStdNumericOperation {

    public ExecBitOrOperation(BitOrOperation _b) {
        super(_b);
    }

    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b,
                           ContextEl _cont) {
        return new Argument(NumberStruct.calculateOr(_a.getStruct(), _b.getStruct(), _cont, getResultClass()));
    }

}
