package code.expressionlanguage.exec.opers;

import code.expressionlanguage.fwd.opers.ExecOperationContent;

public abstract class ExecAbstractUnaryOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {

    protected ExecAbstractUnaryOperation(ExecOperationContent _opCont) {
        super(_opCont);
    }

}
