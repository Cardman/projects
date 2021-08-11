package code.bean.nat.exec.opers;

import code.expressionlanguage.fwd.opers.ExecOperationContent;

public abstract class NatSettableCallFctOperation extends NatInvokingOperation {
    protected NatSettableCallFctOperation(ExecOperationContent _content, boolean _intermediateDottedOperation) {
        super(_content, _intermediateDottedOperation);
    }

}
