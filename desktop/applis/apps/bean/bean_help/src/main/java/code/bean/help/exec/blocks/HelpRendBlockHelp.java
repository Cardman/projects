package code.bean.help.exec.blocks;

import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.exec.blocks.RendParentBlock;
import code.formathtml.stacks.RendAbstractStask;
import code.formathtml.stacks.RendIfStack;
import code.formathtml.stacks.RendReadWrite;

public final class HelpRendBlockHelp {
    static final String EMPTY_STRING = "";

    private HelpRendBlockHelp(){
    }

    public static void processBlockAndRemove(RendStackCall _rendStackCall, RendBlock _rendBlock) {
        ImportingPage ip_ = _rendStackCall.getLastPage();
        ip_.removeRendLastBlock();
        processBlock(_rendStackCall, _rendBlock);
    }

    public static void processBlock(RendStackCall _rendStackCall, RendBlock _rendBlock) {
        ImportingPage ip_ = _rendStackCall.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendBlock nextSibling_ = _rendBlock.getNextSibling();
        if (nextSibling_ != null) {
            rw_.setRead(nextSibling_);
            return;
        }
        RendParentBlock par_ = _rendBlock.getParent();
        RendAbstractStask lastStack_ = ip_.tryGetRendLastStack();
        if (lastStack_ != null) {
            rw_.setRead(par_);
            nextIfStack(ip_, rw_, par_, (RendIfStack) lastStack_);
            return;
        }
        ip_.setNullRendReadWrite();
    }

    private static void nextIfStack(ImportingPage _ip, RendReadWrite _rw, RendParentBlock _par, RendIfStack _lastStack) {
        _par.removeAllVars(_ip);
        _rw.setRead(_lastStack.getLastBlock());
    }

}
