package code.gui.document;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ErroneousStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.render.MetaDocument;
import code.gui.CustComponent;
import code.sml.Document;

import javax.swing.*;

public abstract class AbstractThreadActions implements Runnable {

    private RenderedPage page;

    private Timer timer;
    protected AbstractThreadActions() {
    }
    protected AbstractThreadActions(RenderedPage _page, Timer _timer) {
        page = _page;
        timer = _timer;
    }
    protected void afterAction() {
        Configuration conf_ = page.getNavigation().getSession();
        ContextEl context_ = page.getContext();
        CallingState exc_ = context_.getCallingState();
        if (exc_ instanceof Struct) {
            if (page.getArea() != null) {
                Struct exception_ = (Struct) exc_;
                if (exception_ instanceof ErroneousStruct) {
                    ArrayStruct fullStack_ = ((ErroneousStruct) exception_).getFullStack();
                    page.getArea().append(((ErroneousStruct) exception_).getStringRep(context_, fullStack_));
                } else {
                    context_.setException(null);
                    String str_ = page.getStandards().processString(new Argument(exception_), page.getContext());
                    page.getArea().append(str_);
                }
            }
            context_.setException(null);
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

    public void setPage(RenderedPage page) {
        this.page = page;
    }
}
