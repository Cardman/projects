package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.stacks.RendRemovableVars;

import code.formathtml.util.BeanLgNames;

public final class RendBreakBlock extends RendLeaf implements RendMethodCallingFinally {

    private final String label;
    public RendBreakBlock(String _info) {
        label = _info;
    }


    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        removeBlockFinally(_cont, _stds, _ctx, _rendStack);
    }

    @Override
    public void removeBlockFinally(Configuration _conf, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        while (true) {
            RendRemovableVars bl_ = hasBlockBreak(ip_,label);
            if (ImportingPage.setRemovedCallingFinallyToProcessLoop(ip_, bl_, this, null)) {
                return;
            }
        }
    }

}
