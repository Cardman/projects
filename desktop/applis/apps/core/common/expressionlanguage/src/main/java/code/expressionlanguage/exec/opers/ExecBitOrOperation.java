package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.opers.BitOrOperation;
import code.expressionlanguage.stds.AliasNumber;

public final class ExecBitOrOperation extends ExecStdNumericOperation {

    public ExecBitOrOperation(BitOrOperation _b) {
        super(_b);
    }

    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b,
                           ContextEl _cont) {
        return new Argument(AliasNumber.calculateOr(_a.getStruct(), _b.getStruct(), _cont, getResultClass()));
    }

}
