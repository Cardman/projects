package code.expressionlanguage.opers.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.opers.BitXorOperation;
import code.expressionlanguage.structs.NumberStruct;

public final class ExecBitXorOperation extends ExecStdNumericOperation {

    public ExecBitXorOperation(BitXorOperation _b) {
        super(_b);
    }


    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b,
                           ContextEl _cont) {
        return new Argument(NumberStruct.calculateXor(_a.getStruct(), _b.getStruct(), _cont, getResultClass()));
    }

}
