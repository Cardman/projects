package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.opers.BitAndOperation;
import code.expressionlanguage.stds.AliasNumber;

public final class ExecBitAndOperation extends ExecStdNumericOperation {

    public ExecBitAndOperation(BitAndOperation _b) {
        super(_b);
    }


    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b,
                           ContextEl _cont) {
        return new Argument(AliasNumber.calculateAnd(_a.getStruct(), _b.getStruct(), _cont, getResultClass()));
    }

}
