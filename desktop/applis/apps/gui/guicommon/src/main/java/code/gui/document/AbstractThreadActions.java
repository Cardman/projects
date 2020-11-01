package code.gui.document;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ErroneousStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.render.MetaDocument;
import code.gui.CustComponent;
import code.sml.Document;

import javax.swing.*;

public abstract class AbstractThreadActions implements Runnable {

    private final RenderedPage page;

    private Timer timer;

    protected AbstractThreadActions(RenderedPage _page) {
        page = _page;
        timer = null;
    }
    protected AbstractThreadActions(RenderedPage _page, Timer _timer) {
        page = _page;
        timer = _timer;
    }
    protected void afterAction(ContextEl _ctx) {
        if (_ctx == null) {
            finish();
            return;
        }
        page.getContextCreator().removeContext(_ctx);
        afterActionWithoutRemove(_ctx);
    }

    protected void afterActionWithoutRemove(ContextEl _ctx) {
        if (_ctx == null) {
            finish();
            return;
        }
        CallingState exc_ = _ctx.getCallingState();
        if (exc_ instanceof CustomFoundExc) {
            if (page.getArea() != null) {
                Struct exception_ = ((CustomFoundExc) exc_).getStruct();
                if (exception_ instanceof ErroneousStruct) {
                    ArrayStruct fullStack_ = ((ErroneousStruct) exception_).getFullStack();
                    page.getArea().append(((ErroneousStruct) exception_).getStringRep(_ctx, fullStack_));
                } else {
                    _ctx.setCallingState(null);
                    String str_ = page.getStandards().processString(new Argument(exception_),_ctx);
                    page.getArea().append(str_);
                }
            }
            _ctx.setCallingState(null);
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
        CustComponent.invokeLater(new WindowPage(metadoc_, page.getScroll(), page));
    }

    protected void finish() {
        if (timer != null) {
            timer.stop();
        }
        page.finish();
    }

    public RenderedPage getPage() {
        return page;
    }

}
