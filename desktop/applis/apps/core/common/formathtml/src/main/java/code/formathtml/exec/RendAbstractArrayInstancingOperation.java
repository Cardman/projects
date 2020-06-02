package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.AbstractArrayInstancingOperation;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public abstract class RendAbstractArrayInstancingOperation extends RendInvokingOperation implements RendCalculableOperation {
    private String methodName;

    private String className;

    public RendAbstractArrayInstancingOperation(AbstractArrayInstancingOperation _abs) {
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
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes,this);
        Argument argres_ = getArgument(arguments_, _conf);
        Argument res_;
        res_ = argres_;
        setSimpleArgument(res_, _conf,_nodes);
    }

    abstract Argument getArgument(CustList<Argument> _arguments,
                                  Configuration _conf);
}
