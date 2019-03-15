package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.BitAndOperation;
import code.expressionlanguage.structs.NumberStruct;

public final class ExecBitAndOperation extends ExecStdNumericOperation {

    public ExecBitAndOperation(BitAndOperation _b) {
        super(_b);
    }


    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b,
            ExecutableCode _cont) {
        return new Argument(NumberStruct.calculateAnd(_a.getStruct(), _b.getStruct(), _cont, getResultClass()));
    }

    @Override
    Argument calculateOperAna(Argument _a, String _op, Argument _b,
            Analyzable _an) {
        return new Argument(NumberStruct.calculateAnd(_a.getStruct(), _b.getStruct(), _an.getContextEl(), getResultClass()));
    }

}
