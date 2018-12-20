package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.AbstractArrayInstancingOperation;
import code.util.CustList;
import code.util.IdMap;

public abstract class ExecAbstractArrayInstancingOperation extends ExecAbstractInstancingOperation {
    private String methodName;

    private String className;

    public ExecAbstractArrayInstancingOperation(AbstractArrayInstancingOperation _abs) {
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
    public final void setClassName(String _className) {
        className = _className;
    }

    @Override
    public final Argument calculate(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument res_ = getArgument(arguments_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
        return res_;
    }
    @Override
    public void calculate(ExecutableCode _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (ExecOperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        Argument argres_ = getArgument(arguments_, _conf);
        Argument res_;
        res_ = argres_;
        if (_conf.getContextEl().hasException()) {
            return;
        }
        setSimpleArgument(res_, _conf);
    }
    abstract Argument getArgument(CustList<Argument> _arguments,
            ExecutableCode _conf);
}
