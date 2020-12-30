package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.stacks.RendRemovableVars;
import code.formathtml.util.BeanLgNames;

public final class RendContinueBlock extends RendLeaf implements RendWithEl,RendMethodCallingFinally {

    private final String label;

    public RendContinueBlock(int _offsetTrim, String _info) {
        super(_offsetTrim);
        label = _info;
    }


    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        removeBlockFinally(_cont, _stds, _ctx, _stack, _rendStack);
    }

    @Override
    public void removeBlockFinally(Configuration _conf, BeanLgNames _stds, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        while (hasBlockContinue(_conf,_stds,_ctx,ip_,label, _stack, _rendStack)) {
            RendRemovableVars bl_ = ip_.getRendLastStack();
            if (ImportingPage.setRemovedCallingFinallyToProcess(ip_,bl_,this,null)) {
                return;
            }
        }
    }

}
