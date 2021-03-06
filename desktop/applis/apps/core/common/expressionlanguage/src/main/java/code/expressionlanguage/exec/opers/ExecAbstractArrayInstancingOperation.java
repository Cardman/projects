package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecArrayInstancingContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;
import code.util.IdMap;

public abstract class ExecAbstractArrayInstancingOperation extends ExecInvokingOperation {
    private ExecArrayInstancingContent arrayInstancingContent;


    protected ExecAbstractArrayInstancingOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecArrayInstancingContent _arrayInstancingContent) {
        super(_opCont, _intermediateDottedOperation);
        arrayInstancingContent = _arrayInstancingContent;
    }

    public final String getMethodName() {
        return arrayInstancingContent.getMethodName();
    }
    public final String getClassName() {
        return arrayInstancingContent.getClassName();
    }
}
