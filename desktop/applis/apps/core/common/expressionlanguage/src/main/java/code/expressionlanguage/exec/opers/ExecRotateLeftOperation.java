package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.opers.RotateLeftOperation;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.stds.AliasNumber;

public final class ExecRotateLeftOperation extends ExecStdNumericOperation {

    public ExecRotateLeftOperation(RotateLeftOperation _r) {
        super(_r);
    }


    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b,
                           ContextEl _cont) {
        return new Argument(AliasNumber.calculateRotateLeft(ClassArgumentMatching.convertToNumber(_a.getStruct()),
                ClassArgumentMatching.convertToNumber(_b.getStruct()), getResultClass(), _cont.getStandards()));
    }

}
