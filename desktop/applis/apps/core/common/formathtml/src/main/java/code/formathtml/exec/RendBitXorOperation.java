package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.fwd.opers.ExecOperationContent;

public final class RendBitXorOperation extends RendStdNumericOperation {

    public RendBitXorOperation(ExecOperationContent _content, int _opOffset, String _op) {
        super(_content, _opOffset, _op);
    }


    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b,
            ContextEl _cont) {
        return new Argument(NumParsers.calculateXor(_a.getStruct(), _b.getStruct(), getResultClass().getUnwrapObjectNb()));
    }

}
