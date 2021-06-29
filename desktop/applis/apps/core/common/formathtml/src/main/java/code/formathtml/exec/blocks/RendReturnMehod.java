package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.stacks.RendRemovableVars;
import code.formathtml.util.BeanLgNames;

public final class RendReturnMehod extends RendLeaf implements RendMethodCallingFinally,RendWithEl {

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        removeBlockFinally(_cont, _stds, _ctx, _rendStack);
    }

    @Override
    public void removeBlockFinally(Configuration _conf, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        while (true) {
            RendRemovableVars bl_ = ip_.tryGetRendLastStack();
            if (bl_ == null) {
                break;
            }
            if (ImportingPage.setRemovedCallingFinallyToProcess(ip_,bl_,this,null)) {
                return;
            }
        }
        ip_.setNullRendReadWrite();
    }

}
