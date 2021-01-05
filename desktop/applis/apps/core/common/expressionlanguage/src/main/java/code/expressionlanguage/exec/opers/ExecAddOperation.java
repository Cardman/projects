package code.expressionlanguage.exec.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.NumberStruct;
import code.util.core.StringUtil;


public final class ExecAddOperation extends ExecStdNumericOperation {

    public ExecAddOperation(ExecOperationContent _opCont, int _opOffset, String _op) {
        super(_opCont, _opOffset, _op);
    }

    static NumberStruct addOne(NumberStruct _arg, byte _cast) {
        return NumParsers.calculateIncr(_arg, 1, _cast);
    }

    static NumberStruct removeOne(NumberStruct _arg, byte _cast) {
        return NumParsers.calculateIncr(_arg, -1, _cast);
    }

    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b, ContextEl _cont, StackCall _stack) {
        if (StringUtil.quickEq(_op.trim(), PLUS)) {
            return new Argument(NumParsers.calculateSum(NumParsers.convertToNumber(_a.getStruct()),
                    NumParsers.convertToNumber(_b.getStruct()), getResultClass().getUnwrapObjectNb()));
        }
        return new Argument(NumParsers.calculateDiff(NumParsers.convertToNumber(_a.getStruct()),
                NumParsers.convertToNumber(_b.getStruct()), getResultClass().getUnwrapObjectNb()));
    }


}
