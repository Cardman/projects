package code.formathtml.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.core.StringUtil;


public final class RendAddOperation extends RendStdNumericOperation {

    private final boolean catString;

    public RendAddOperation(ExecOperationContent _content, int _opOffset, String _op, boolean _catString) {
        super(_content, _opOffset, _op);
        catString = _catString;
    }

    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b, ContextEl _cont, StackCall _stack) {
        if (StringUtil.quickEq(_op.trim(), PLUS)) {
            if (catString) {
                return ExecCatOperation.localSumDiff(_a, _b, _cont);
            }
            return new Argument(NumParsers.calculateSum(NumParsers.convertToNumber(_a.getStruct()),
                    NumParsers.convertToNumber(_b.getStruct()), getResultClass().getUnwrapObjectNb()));
        }
        return new Argument(NumParsers.calculateDiff(NumParsers.convertToNumber(_a.getStruct()),
                NumParsers.convertToNumber(_b.getStruct()), getResultClass().getUnwrapObjectNb()));
    }


}
