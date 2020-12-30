package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.fwd.opers.ExecOperationContent;

public final class ExecRotateLeftOperation extends ExecStdNumericOperation {

    public ExecRotateLeftOperation(ExecOperationContent _opCont, int _opOffset, String _op) {
        super(_opCont, _opOffset, _op);
    }


    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b,
                           ContextEl _cont, StackCall _stack) {
        return new Argument(NumParsers.calculateRotateLeft(NumParsers.convertToNumber(_a.getStruct()),
                NumParsers.convertToNumber(_b.getStruct()), getResultClass().getUnwrapObjectNb()));
    }

}
