package code.formathtml;

import code.expressionlanguage.files.OffsetsBlock;
import code.formathtml.util.RendRemovableVars;
import code.formathtml.util.RendTryBlockStack;

public final class RendReturnMehod extends RendLeaf implements RendCallingFinally,RendBuildableElMethod {
    RendReturnMehod(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void processEl(Configuration _cont) {
        removeBlockFinally(_cont);
    }

    @Override
    public void removeBlockFinally(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        while (ip_.hasBlock()) {
            RendRemovableVars bl_ = ip_.getRendLastStack();
            ip_.setFinallyToProcess(false);
            bl_.removeVarAndLoop(ip_);
            if (ip_.isFinallyToProcess()) {
                ((RendTryBlockStack)bl_).setCalling(this);
                return;
            }
        }
        ip_.setNullRendReadWrite();
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc) {

    }
}
