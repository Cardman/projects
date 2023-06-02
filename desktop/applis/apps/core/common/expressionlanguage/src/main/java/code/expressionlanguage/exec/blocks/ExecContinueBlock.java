package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.AbstractStask;
import code.util.CustList;

public final class ExecContinueBlock extends ExecLeaf implements MethodCallingFinally {

    private final String label;
    public ExecContinueBlock(String _label) {
        label = _label;
    }

    @Override
    public void removeBlockFinally(AbstractPageEl _stack) {
        int size_ = _stack.sizeEl();
        _stack.getCurrentEl(0, new CustList<ExecOperationNode>(), this);
        if (size_ < _stack.sizeEl()) {
            return;
        }
        _stack.clearCurrentEls();
        while (true) {
            AbstractStask bl_ = ExecHelperBlocks.hasBlockContinue(_stack,label);
            if (ExecHelperBlocks.setRemovedCallingFinallyToProcessLoop(_stack, bl_, this, null)) {
                return;
            }
        }
    }

}
