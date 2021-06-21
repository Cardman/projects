package code.expressionlanguage.exec.opers;

import code.expressionlanguage.fwd.opers.ExecArrayInstancingContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;

public abstract class ExecAbstractArrayInstancingOperation extends ExecInvokingOperation {
    private final ExecArrayInstancingContent arrayInstancingContent;


    protected ExecAbstractArrayInstancingOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecArrayInstancingContent _arrayInstancingContent) {
        super(_opCont, _intermediateDottedOperation);
        arrayInstancingContent = _arrayInstancingContent;
    }

    public final int getMethodName() {
        return arrayInstancingContent.getMethodName();
    }
    public final String getClassName() {
        return arrayInstancingContent.getClassName();
    }
}
