package code.expressionlanguage.exec.opers;

import code.expressionlanguage.fwd.opers.ExecFieldOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;

public abstract class ExecAbstractFieldOperation extends ExecLeafOperation implements AtomicExecCalculableOperation,ExecPossibleIntermediateDotted {

    private ExecFieldOperationContent fieldContent;

    public ExecAbstractFieldOperation(ExecOperationContent _a, ExecFieldOperationContent _fieldContent) {
        super(_a);
        fieldContent = _fieldContent;
    }

    public int getOff() {
        return fieldContent.getOff();
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return fieldContent.isIntermediate();
    }
}
