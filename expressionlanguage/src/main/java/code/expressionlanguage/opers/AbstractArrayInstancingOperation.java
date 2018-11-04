package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.ConstructorId;
import code.util.CustList;
import code.util.IdMap;

public abstract class AbstractArrayInstancingOperation extends AbstractInstancingOperation {
    private String methodName;

    private String className;

    public AbstractArrayInstancingOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        methodName = getOperations().getFctName();
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
    public final ConstructorId getConstId() {
        return null;
    }

    @Override
    final boolean isCallMethodCtor(Analyzable _an) {
        return false;
    }

    @Override
    public final Argument calculate(IdMap<OperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(_nodes.getVal(o).getArgument());
        }
        Argument res_ = getArgument(arguments_, _conf);
        if (_conf.callsOrException()) {
            return res_;
        }
        setSimpleArgument(res_, _conf, _nodes);
        return res_;
    }
    @Override
    public void calculate(ExecutableCode _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        Argument argres_ = getArgument(arguments_, _conf);
        Argument res_;
        res_ = argres_;
        if (_conf.getException() != null) {
            return;
        }
        setSimpleArgument(res_, _conf);
    }
    abstract Argument getArgument(CustList<Argument> _arguments,
            ExecutableCode _conf);
}
