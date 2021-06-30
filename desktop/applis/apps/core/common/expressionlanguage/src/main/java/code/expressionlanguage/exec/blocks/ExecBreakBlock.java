package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.stacks.AbstractStask;

public final class ExecBreakBlock extends ExecLeaf implements MethodCallingFinally {

    private final String label;
    public ExecBreakBlock(String _label) {
        label = _label;
    }

    @Override
    public void removeBlockFinally(ContextEl _conf, StackCall _stack) {
        AbstractPageEl ip_ = _stack.getLastPage();
        //when labelled this loop does not remove if
        //the last statement is a "try" with "finally" clause
        //and the current block is a "try" or a "catch"
        while (true) {
            AbstractStask bl_ = ExecHelperBlocks.hasBlockBreak(ip_,label);
            if (ExecHelperBlocks.setRemovedCallingFinallyToProcessLoop(ip_, bl_, this, null)) {
                return;
            }
        }

    }

}
