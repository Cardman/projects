package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.stacks.AbstractStask;

public final class ExecContinueBlock extends ExecLeaf implements MethodCallingFinally {

    private final String label;
    public ExecContinueBlock(String _label) {
        label = _label;
    }

    @Override
    public void removeBlockFinally(AbstractPageEl _stack) {
        while (true) {
            AbstractStask bl_ = ExecHelperBlocks.hasBlockContinue(_stack,label);
            if (ExecHelperBlocks.setRemovedCallingFinallyToProcessLoop(_stack, bl_, this, null)) {
                return;
            }
        }
    }

}
