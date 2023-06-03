package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public final class ExecUnclassedBracedBlock extends ExecBracedBlock implements WithEl {

    private final int off;
    public ExecUnclassedBracedBlock(int _o) {
        off = _o;
    }
    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        _stack.getLastPage().globalOffset(off);
        ExecHelperBlocks.processUnclassed(_stack, this);
    }

}
