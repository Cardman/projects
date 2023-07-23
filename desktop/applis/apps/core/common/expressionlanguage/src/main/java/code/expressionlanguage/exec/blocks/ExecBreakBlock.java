package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.stacks.AbstractStask;

public final class ExecBreakBlock extends ExecLeaf implements MethodCallingFinally {

    private final int off;
    private final String label;

    public ExecBreakBlock(int _o,String _label) {
        off = _o;
        label = _label;
    }

    @Override
    public void removeBlockFinally(StackCall _stackCall, AbstractPageEl _stack) {
        //when labelled this loop does not remove if
        //the last statement is a "try" with "finally" clause
        //and the current block is a "try" or a "catch"
        while (true) {
            AbstractStask bl_ = ExecHelperBlocks.hasBlockBreak(_stackCall,_stack,label);
            if (ExecHelperBlocks.setRemovedCallingFinallyToProcessLoop(_stack, bl_, this, null)) {
                return;
            }
        }

    }

    @Override
    public int getOff() {
        return off;
    }
}
