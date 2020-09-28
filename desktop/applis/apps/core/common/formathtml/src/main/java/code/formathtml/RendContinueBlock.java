package code.formathtml;

import code.expressionlanguage.structs.Struct;
import code.formathtml.stacks.RendAbruptCallingFinally;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.stacks.RendRemovableVars;
import code.util.StringList;

public final class RendContinueBlock extends RendLeaf implements RendWithEl,RendCallingFinally {

    private String label;

    public RendContinueBlock(int _offsetTrim, String _info) {
        super(_offsetTrim);
        label = _info;
    }


    @Override
    public void processEl(Configuration _cont) {
        removeBlockFinally(_cont);
    }

    @Override
    public void removeBlockFinally(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        RendLoop loop_;
        while (true) {
            RendRemovableVars bl_ = ip_.getRendLastStack();
            if (bl_ instanceof RendLoopBlockStack) {
                RendParentBlock br_ = bl_.getBlock();
                if (label.isEmpty()) {
                    br_.removeLocalVars(ip_);
                    loop_ = (RendLoop) br_;
                    break;
                }
                if (StringList.quickEq(label, ((RendBreakableBlock) br_).getRealLabel())){
                    br_.removeLocalVars(ip_);
                    loop_ = (RendLoop) br_;
                    break;
                }
            }
            if (ImportingPage.setRemovedCallingFinallyToProcess(ip_,bl_,this,null)) {
                return;
            }
        }
        ip_.getRendReadWrite().setRead((RendBlock) loop_);
        loop_.processLastElementLoop(_conf);
    }

    @Override
    public RendAbruptCallingFinally newAbruptCallingFinally(Struct _struct) {
        return new RendAbruptCallingFinally(this);
    }
}
