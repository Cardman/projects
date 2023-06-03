package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.stacks.AbstractStask;

public final class ExecContinueBlock extends ExecLeaf implements MethodCallingFinally {

    private final int off;
    private final String label;
    public ExecContinueBlock(int _o,String _label) {
        off = _o;
        label = _label;
    }

    @Override
    public void removeBlockFinally(AbstractPageEl _stack) {
        _stack.globalOffset(off);
        if (ExecHelperBlocks.checkBp(_stack,this)) {
            return;
        }
        while (true) {
            AbstractStask bl_ = ExecHelperBlocks.hasBlockContinue(_stack,label);
            if (ExecHelperBlocks.setRemovedCallingFinallyToProcessLoop(_stack, bl_, this, null)) {
                return;
            }
        }
    }

}
