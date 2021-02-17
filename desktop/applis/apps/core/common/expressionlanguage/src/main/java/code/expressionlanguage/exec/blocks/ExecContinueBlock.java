package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelperBlocks;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.stacks.AbstractStask;

public final class ExecContinueBlock extends ExecLeaf implements MethodCallingFinally {

    private final String label;
    public ExecContinueBlock(String _label, int _offsetTrim) {
        super(_offsetTrim);
        label = _label;
    }

    @Override
    public void removeBlockFinally(ContextEl _conf, StackCall _stack) {
        AbstractPageEl ip_ = _stack.getLastPage();
        while (ExecHelperBlocks.hasBlockContinue(_conf,ip_,label, _stack)) {
            AbstractStask bl_ = ip_.getLastStack();
            if (AbstractPageEl.setRemovedCallingFinallyToProcess(ip_,bl_,this,null)) {
                break;
            }
        }
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        removeBlockFinally(_cont, _stack);
    }

}
