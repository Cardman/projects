package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.structs.Struct;
import code.formathtml.stacks.RendAbruptCallingFinally;
import code.formathtml.stacks.RendRemovableVars;
import code.formathtml.util.AnalyzingDoc;

public final class RendReturnMehod extends RendLeaf implements RendCallingFinally,RendWithEl {
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
            if (ImportingPage.setRemovedCallingFinallyToProcess(ip_,bl_,this,null)) {
                return;
            }
        }
        ip_.setNullRendReadWrite();
    }

    @Override
    public RendAbruptCallingFinally newAbruptCallingFinally(Struct _struct) {
        return new RendAbruptCallingFinally(this);
    }
    @Override
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {

    }
}
