package code.formathtml.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
import code.util.core.StringUtil;


public final class RendMultOperation extends RendStdNumericOperation {

    public RendMultOperation(ExecOperationContent _content, int _opOffset, String _op) {
        super(_content, _opOffset, _op);
    }

    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b, ContextEl _cont, RendStackCall _stack) {
        if (StringUtil.quickEq(_op.trim(), MULT)) {
            return new Argument(NumParsers.calculateMult(NumParsers.convertToNumber(_a.getStruct()),
                    NumParsers.convertToNumber(_b.getStruct()), getResultClass().getUnwrapObjectNb()));
        }
        if (StringUtil.quickEq(_op.trim(), DIV)) {
            return new Argument(ExecNumericOperation.calculateDivEx(NumParsers.convertToNumber(_a.getStruct()), NumParsers.convertToNumber(_b.getStruct()), _cont, getResultClass().getUnwrapObjectNb(), _stack.getStackCall()));
        }
        return new Argument(ExecNumericOperation.calculateModEx(NumParsers.convertToNumber(_a.getStruct()), NumParsers.convertToNumber(_b.getStruct()), _cont, getResultClass().getUnwrapObjectNb(), _stack.getStackCall()));
    }

}
