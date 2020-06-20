package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.opers.RotateRightOperation;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.structs.NumberStruct;

public final class ExecRotateRightOperation extends ExecStdNumericOperation {

    public ExecRotateRightOperation(RotateRightOperation _r) {
        super(_r);
    }

    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b,
                           ContextEl _cont) {
        return new Argument(NumberStruct.calculateRotateRight(ClassArgumentMatching.convertToNumber(_a.getStruct()),
                ClassArgumentMatching.convertToNumber(_b.getStruct()), _cont, getResultClass()));
    }

}
