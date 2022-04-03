package code.bean.help.exec.blocks;

import code.bean.nat.exec.*;
import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.exec.blocks.RendParentBlock;

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
        NatRendReadWrite rw_ = ip_.getRendReadWrite();
        RendBlock nextSibling_ = _rendBlock.getNextSibling();
        if (nextSibling_ != null) {
            rw_.setRead(nextSibling_);
            return;
        }
        RendParentBlock par_ = _rendBlock.getParent();
        NatAbstractStask lastStack_ = ip_.tryGetRendLastStack();
        if (lastStack_ != null) {
            rw_.setRead(par_);
            nextIfStack(rw_, (NatIfStack) lastStack_);
            return;
        }
        ip_.setNullRendReadWrite();
    }

    private static void nextIfStack(NatRendReadWrite _rw, NatIfStack _lastStack) {
        _rw.setRead(_lastStack.getLastBlock());
    }

}
