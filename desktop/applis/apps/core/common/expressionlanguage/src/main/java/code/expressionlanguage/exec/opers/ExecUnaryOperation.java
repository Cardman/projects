package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecUnaryOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {
    private final String oper;

    public ExecUnaryOperation(ExecOperationContent _opCont, String _oper) {
        super(_opCont);
        oper = _oper;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        Argument arg_ = getArgument(_nodes,getFirstChild());
        setRelativeOffsetPossibleLastPage(_stack);
        ExecClassArgumentMatching to_ = getResultClass();
        Argument a_ = getArgument(arg_, to_, oper);
        setSimpleArgument(a_, _conf, _nodes, _stack);
    }

    public static Argument getArgument(Argument _in, ExecClassArgumentMatching _to, String _oper) {
        Argument out_;
        if (StringUtil.quickEq(_oper, PLUS)) {
            out_ = new Argument(NumParsers.idNumber(NumParsers.convertToNumber(_in.getStruct()), _to.getUnwrapObjectNb()));
        } else {
            out_ = new Argument(NumParsers.opposite(NumParsers.convertToNumber(_in.getStruct()), _to.getUnwrapObjectNb()));
        }
        return out_;
    }
}
