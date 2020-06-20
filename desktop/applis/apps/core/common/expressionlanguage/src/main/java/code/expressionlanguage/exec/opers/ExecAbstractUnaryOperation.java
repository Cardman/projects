package code.expressionlanguage.exec.opers;

import code.expressionlanguage.analyze.opers.AbstractUnaryOperation;

public abstract class ExecAbstractUnaryOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {

    public ExecAbstractUnaryOperation(AbstractUnaryOperation _a) {
        super(_a);
    }

}
