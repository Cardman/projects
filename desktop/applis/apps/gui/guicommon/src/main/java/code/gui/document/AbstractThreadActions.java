package code.gui.document;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ErroneousStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.render.MetaDocument;
import code.gui.FrameUtil;
import code.sml.Document;

public abstract class AbstractThreadActions implements Runnable {

    private final RenderedPage page;

    protected AbstractThreadActions(RenderedPage _page) {
        page = _page;
    }

    protected void afterAction(ContextEl _ctx, RendStackCall _stackCall) {
        if (_ctx == null || _stackCall == null) {
            finish();
            return;
        }
        ContextEl ctx_ = page.getContextCreator().removeContext(_ctx);
        afterActionWithoutRemove(ctx_, _stackCall);
    }

    protected void afterActionWithoutRemove(ContextEl _ctx, RendStackCall _stackCall) {
        if (_ctx == null || _stackCall == null) {
            finish();
            return;
        }
        CallingState exc_ = _stackCall.getStackCall().getCallingState();
        if (exc_ instanceof CustomFoundExc) {
            if (page.getArea() != null) {
                Struct exception_ = ((CustomFoundExc) exc_).getStruct();
                if (exception_ instanceof ErroneousStruct) {
                    ArrayStruct fullStack_ = ((ErroneousStruct) exception_).getFullStack();
                    page.getArea().append(((ErroneousStruct) exception_).getStringRep(_ctx, fullStack_));
                } else {
                    _stackCall.getStackCall().setCallingState(null);
                    String str_ = NumParsers.getString(RendDynOperationNode.processString(new Argument(exception_),_ctx, _stackCall).getStruct()).getInstance();
                    page.getArea().append(str_);
                }
            }
            _stackCall.getStackCall().setCallingState(null);
            finish();
            return;
        }
        if (!page.isProcessing()) {
            return;
        }
        Document doc_ = page.getNavigation().getDocument();
        if (doc_ == null) {
            finish();
            return;
        }
        MetaDocument metadoc_ = MetaDocument.newInstance(doc_,page.getNavigation().getSession().getRendKeyWords());
        FrameUtil.invokeLater(new WindowPage(metadoc_, page.getScroll(), page), page.getGene());
    }

    protected void finish() {
        page.finish();
    }

    public RenderedPage getPage() {
        return page;
    }

}
