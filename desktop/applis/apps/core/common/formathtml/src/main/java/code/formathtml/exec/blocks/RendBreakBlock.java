package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.stacks.*;
import code.formathtml.util.BeanLgNames;
import code.util.StringList;

public final class RendBreakBlock extends RendLeaf implements RendWithEl,RendCallingFinally {

    private String label;
    public RendBreakBlock(int _offsetTrim, String _info) {
        super(_offsetTrim);
        label = _info;
    }


    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx) {
        removeBlockFinally(_cont, _stds, _ctx);
    }

    @Override
    public void removeBlockFinally(Configuration _conf, BeanLgNames _stds, ContextEl _ctx) {
        ImportingPage ip_ = _conf.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendRemovableVars stack_;
        while (true) {
            RendRemovableVars bl_ = ip_.getRendLastStack();
            stack_ = bl_;
            if (label.isEmpty()) {
                if (bl_ instanceof RendLoopBlockStack || bl_ instanceof RendSwitchBlockStack) {
                    break;
                }
            } else {
                RendParentBlock par_ = bl_.getBlock();
                if (par_ instanceof RendBreakableBlock) {
                    RendBreakableBlock br_ = (RendBreakableBlock) par_;
                    if (StringList.quickEq(label, br_.getRealLabel())){
                        break;
                    }
                }
            }
            if (ImportingPage.setRemovedCallingFinallyToProcess(ip_,bl_,this,null)) {
                return;
            }
        }
        RendBlock forLoopLoc_ = stack_.getLastBlock();
        rw_.setRead(forLoopLoc_);
        if (stack_ instanceof RendLoopBlockStack) {
            ((RendLoopBlockStack)stack_).setFinished(true);
        }
    }

    @Override
    public RendAbruptCallingFinally newAbruptCallingFinally(Struct _struct) {
        return new RendAbruptCallingFinally(this);
    }
}
