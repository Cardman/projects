package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public final class ExecLine extends ExecLeaf implements WithEl {

    private final ExecOperationNodeListOff exp;
    private final boolean callSuper;
    private final boolean callInts;
    public ExecLine(boolean _callSuper, boolean _callInts, ExecOperationNodeListOff _exp) {
        exp = _exp;
        callSuper = _callSuper;
        callInts = _callInts;
    }

    public boolean isCallSuper() {
        return callSuper;
    }

    public boolean isCallInts() {
        return callInts;
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.processLine(_cont, _stack, exp, this);
    }

}
