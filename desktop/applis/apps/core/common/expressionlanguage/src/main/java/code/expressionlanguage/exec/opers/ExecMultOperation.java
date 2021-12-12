package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.core.StringUtil;


public final class ExecMultOperation extends ExecStdNumericOperation {

    public ExecMultOperation(ExecOperationContent _opCont, int _opOffset, String _op) {
        super(_opCont, _opOffset, _op);
    }

    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b, ContextEl _cont, StackCall _stack) {
        if (StringUtil.quickEq(_op.trim(), MULT)) {
            return new Argument(NumParsers.calculateMult(NumParsers.convertToNumber(_a.getStruct()),
                    NumParsers.convertToNumber(_b.getStruct()), getResultClass().getUnwrapObjectNb()));
        }
        if (StringUtil.quickEq(_op.trim(), DIV)) {
            return new Argument(calculateDivEx(NumParsers.convertToNumber(_a.getStruct()), NumParsers.convertToNumber(_b.getStruct()), _cont, getResultClass().getUnwrapObjectNb(), _stack));
        }
        return new Argument(calculateModEx(NumParsers.convertToNumber(_a.getStruct()), NumParsers.convertToNumber(_b.getStruct()), _cont, getResultClass().getUnwrapObjectNb(), _stack));
    }

}
