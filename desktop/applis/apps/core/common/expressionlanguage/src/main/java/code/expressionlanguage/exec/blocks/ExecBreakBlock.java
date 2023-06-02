package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.stacks.AbstractStask;

public final class ExecBreakBlock extends ExecLeaf implements MethodCallingFinally {

    private final String label;
    public ExecBreakBlock(String _label) {
        label = _label;
    }

    @Override
    public void removeBlockFinally(AbstractPageEl _stack) {
        //when labelled this loop does not remove if
        //the last statement is a "try" with "finally" clause
        //and the current block is a "try" or a "catch"
        while (true) {
            AbstractStask bl_ = ExecHelperBlocks.hasBlockBreak(_stack,label);
            if (ExecHelperBlocks.setRemovedCallingFinallyToProcessLoop(_stack, bl_, this, null)) {
                return;
            }
        }

    }

}
