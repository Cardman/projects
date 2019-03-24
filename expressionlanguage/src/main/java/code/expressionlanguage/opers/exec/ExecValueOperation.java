package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.MethodPageEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.ValueOperation;
import code.util.IdMap;

public final class ExecValueOperation extends ExecVariableLeafOperation {
    public ExecValueOperation(ValueOperation _v) {
        super(_v);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        MethodPageEl m_ = (MethodPageEl) _conf.getLastPage();
        Argument arg_ = m_.getRightArgument();
        setSimpleArgument(arg_, _conf, _nodes);
    }
}
