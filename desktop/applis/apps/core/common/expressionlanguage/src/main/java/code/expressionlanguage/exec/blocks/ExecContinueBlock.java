package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.stacks.AbstractStask;

public final class ExecContinueBlock extends ExecLeaf implements MethodCallingFinally {

    private final String label;
    public ExecContinueBlock(String _label) {
        label = _label;
    }

    @Override
    public void removeBlockFinally(ContextEl _conf, StackCall _stack) {
        AbstractPageEl ip_ = _stack.getLastPage();
        while (true) {
            AbstractStask bl_ = ExecHelperBlocks.hasBlockContinue(_conf,ip_,label, _stack);
            if (bl_ == null || ExecHelperBlocks.setRemovedCallingFinallyToProcess(ip_, bl_, this, null)) {
                return;
            }
        }
    }

}
