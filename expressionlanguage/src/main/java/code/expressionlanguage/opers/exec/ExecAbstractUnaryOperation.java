package code.expressionlanguage.opers.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.AbstractUnaryOperation;

public abstract class ExecAbstractUnaryOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {

    public ExecAbstractUnaryOperation(AbstractUnaryOperation _a) {
        super(_a);
    }

    @Override
    public void tryCalculateNode(ContextEl _conf) {
        AbstractUnaryOperation.setArg(_conf,this);
    }
}
