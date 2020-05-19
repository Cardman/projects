package code.expressionlanguage.opers.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.opers.RotateRightOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
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

    @Override
    Argument calculateOperAna(Argument _a, String _op, Argument _b,
            ContextEl _an) {
        return new Argument(NumberStruct.calculateRotateRight(ClassArgumentMatching.convertToNumber(_a.getStruct()),
                ClassArgumentMatching.convertToNumber(_b.getStruct()), _an, getResultClass()));
    }

}
