package code.expressionlanguage.opers.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.opers.BitAndOperation;
import code.expressionlanguage.structs.NumberStruct;

public final class ExecBitAndOperation extends ExecStdNumericOperation {

    public ExecBitAndOperation(BitAndOperation _b) {
        super(_b);
    }


    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b,
                           ContextEl _cont) {
        return new Argument(NumberStruct.calculateAnd(_a.getStruct(), _b.getStruct(), _cont, getResultClass()));
    }

    @Override
    Argument calculateOperAna(Argument _a, String _op, Argument _b,
            ContextEl _an) {
        return new Argument(NumberStruct.calculateAnd(_a.getStruct(), _b.getStruct(), _an, getResultClass()));
    }

}
