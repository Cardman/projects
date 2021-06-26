package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public final class ExecDoBlock extends ExecBracedBlock implements StackableBlock {

    private final String label;
    public ExecDoBlock(String _label) {
        label = _label;
    }

    public void processLastElementLoop(StackCall _stack) {
        ExecHelperBlocks.processLastElementLoopDo(_stack, this);
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.processDo(_cont, _stack, label, this);
    }

}
