package code.expressionlanguage.exec.opers;

import code.expressionlanguage.analyze.opers.MethodOperation;

public abstract class ExecAbstractUnaryOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {

    public ExecAbstractUnaryOperation(MethodOperation _a) {
        super(_a);
    }

}
