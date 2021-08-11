package code.bean.nat.exec.opers;

import code.bean.nat.fwd.opers.NatExecFieldOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.opers.RendCalculableOperation;
import code.formathtml.exec.opers.RendLeafOperation;
import code.formathtml.exec.opers.RendPossibleIntermediateDotted;

public abstract class NatAbstractFieldOperation extends RendLeafOperation implements RendCalculableOperation, RendPossibleIntermediateDotted {

    private final NatExecFieldOperationContent fieldOperationContent;

    protected NatAbstractFieldOperation(ExecOperationContent _content, NatExecFieldOperationContent _fieldOperationContent) {
        super(_content);
        fieldOperationContent = _fieldOperationContent;
    }

    public int getOff() {
        return fieldOperationContent.getOff();
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return fieldOperationContent.isIntermediate();
    }


}
