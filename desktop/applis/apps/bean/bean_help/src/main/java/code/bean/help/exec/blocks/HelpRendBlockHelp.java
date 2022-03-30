package code.bean.help.exec.blocks;

import code.bean.nat.exec.NatImportingPage;
import code.bean.nat.exec.NatRendStackCall;
import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.exec.blocks.RendParentBlock;
import code.formathtml.stacks.RendAbstractStask;
import code.formathtml.stacks.RendIfStack;
import code.formathtml.stacks.RendReadWrite;

public final class HelpRendBlockHelp {
    static final String EMPTY_STRING = "";

    private HelpRendBlockHelp(){
    }

    public static void processBlockAndRemove(NatRendStackCall _rendStackCall, RendBlock _rendBlock) {
        NatImportingPage ip_ = _rendStackCall.getLastPage();
        ip_.removeRendLastBlock();
        processBlock(_rendStackCall, _rendBlock);
    }

    public static void processBlock(NatRendStackCall _rendStackCall, RendBlock _rendBlock) {
        NatImportingPage ip_ = _rendStackCall.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendBlock nextSibling_ = _rendBlock.getNextSibling();
        if (nextSibling_ != null) {
            rw_.setRead(nextSibling_);
            return;
        }
        RendParentBlock par_ = _rendBlock.getParent();
        RendAbstractStask lastStack_ = ip_.tryGetNatLastStack();
        if (lastStack_ != null) {
            rw_.setRead(par_);
            nextIfStack(rw_, (RendIfStack) lastStack_);
            return;
        }
        ip_.setNullRendReadWrite();
    }

    private static void nextIfStack(RendReadWrite _rw, RendIfStack _lastStack) {
        _rw.setRead(_lastStack.getLastBlock());
    }

}
