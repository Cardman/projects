package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.fwd.opers.ExecOperationContent;

public final class RendShiftLeftOperation extends RendStdNumericOperation {

    public RendShiftLeftOperation(ExecOperationContent _content, int _opOffset, String _op) {
        super(_content, _opOffset, _op);
    }

    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b,
            ContextEl _cont) {
        return new Argument(NumParsers.calculateShiftLeft(NumParsers.convertToNumber(_a.getStruct()),
                NumParsers.convertToNumber(_b.getStruct()), getResultClass().getUnwrapObjectNb()));
    }

}
