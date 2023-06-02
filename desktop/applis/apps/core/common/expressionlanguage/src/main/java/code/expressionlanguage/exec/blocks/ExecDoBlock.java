package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public final class ExecDoBlock extends ExecBracedBlock implements WithEl {

    private final int off;
    private final String label;
    public ExecDoBlock(int _o, String _label) {
        off = _o;
        label = _label;
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        _stack.getLastPage().globalOffset(off);
        ExecHelperBlocks.processDo(_cont, _stack, label, this);
    }

}
