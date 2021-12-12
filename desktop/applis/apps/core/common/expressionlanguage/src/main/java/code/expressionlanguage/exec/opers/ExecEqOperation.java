package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecEqOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {

    private final String oper;
    public ExecEqOperation(ExecOperationContent _opCont, String _oper) {
        super(_opCont);
        oper = _oper;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        Argument first_ = getFirstArgument(_nodes,this);
        Argument second_ = getLastArgument(_nodes,this);
        String op_ = oper.trim();
        boolean b_ = first_.getStruct().sameReference(second_.getStruct());
        if (StringUtil.quickEq(op_, DIFF)) {
            b_ = !b_;
        }
        Argument arg_ = new Argument(BooleanStruct.of(b_));
        setSimpleArgument(arg_, _conf, _nodes, _stack);
    }

}
