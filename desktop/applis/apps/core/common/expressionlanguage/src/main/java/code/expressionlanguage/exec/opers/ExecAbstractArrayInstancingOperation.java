package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.opers.AbstractArrayInstancingOperation;
import code.util.CustList;
import code.util.IdMap;

public abstract class ExecAbstractArrayInstancingOperation extends ExecInvokingOperation {
    private String methodName;

    private String className;

    protected ExecAbstractArrayInstancingOperation(AbstractArrayInstancingOperation _abs) {
        super(_abs);
        methodName = _abs.getMethodName();
        className = _abs.getClassName();
    }

    public final String getMethodName() {
        return methodName;
    }
    public final String getClassName() {
        return className;
    }

    @Override
    public final void calculate(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument res_ = getArgument(arguments_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }
    abstract Argument getArgument(CustList<Argument> _arguments,
                                  ContextEl _conf);
}
