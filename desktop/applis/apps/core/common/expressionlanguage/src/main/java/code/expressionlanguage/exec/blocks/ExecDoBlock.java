package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public final class ExecDoBlock extends ExecBracedBlock implements WithEl {

    private final String label;
    public ExecDoBlock(String _label) {
        label = _label;
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.processDo(_cont, _stack, label, this);
    }

}
