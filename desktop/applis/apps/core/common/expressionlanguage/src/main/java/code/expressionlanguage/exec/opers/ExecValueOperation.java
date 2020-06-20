package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.ValueOperation;
import code.util.IdMap;

public final class ExecValueOperation extends ExecLeafOperation implements
        AtomicExecCalculableOperation {
    public ExecValueOperation(ValueOperation _v) {
        super(_v);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        Argument arg_ = Argument.getNullableValue(_conf.getLastPage().getRightArgument());
        setSimpleArgument(arg_, _conf, _nodes);
    }
}
