package code.bean.nat.exec.opers;

import code.bean.nat.fwd.opers.NatExecFieldOperationContent;

public abstract class NatAbstractFieldOperation extends NatExecLeafOperation implements NatRendCalculableOperation, NatExecPossibleIntermediateDotted {

    private final NatExecFieldOperationContent fieldOperationContent;

    protected NatAbstractFieldOperation(int _o, NatExecFieldOperationContent _fieldOperationContent) {
        super(_o);
        fieldOperationContent = _fieldOperationContent;
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return fieldOperationContent.isIntermediate();
    }


}
