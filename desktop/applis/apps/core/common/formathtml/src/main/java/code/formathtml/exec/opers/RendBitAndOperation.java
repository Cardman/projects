package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;

public final class RendBitAndOperation extends RendStdNumericOperation {

    public RendBitAndOperation(ExecOperationContent _content, int _opOffset, String _op) {
        super(_content, _opOffset, _op);
    }


    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b,
                           ContextEl _cont, RendStackCall _stack) {
        return new Argument(NumParsers.calculateAnd(_a.getStruct(), _b.getStruct(), getResultClass().getUnwrapObjectNb()));
    }


}
