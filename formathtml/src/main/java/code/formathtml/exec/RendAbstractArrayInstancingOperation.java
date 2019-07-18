package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.AbstractArrayInstancingOperation;
import code.util.CustList;

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
    public void calculate(ExecutableCode _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (RendDynOperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        Argument argres_ = getArgument(arguments_, _conf);
        Argument res_;
        res_ = argres_;
        setSimpleArgument(res_, _conf);
    }
    abstract Argument getArgument(CustList<Argument> _arguments,
            ExecutableCode _conf);
}
