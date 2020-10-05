package code.gui.document;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ErroneousStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.render.MetaDocument;
import code.bean.nat.BeanNatLgNames;
import code.gui.CustComponent;
import code.sml.Document;

public final class ThreadRefresh implements Runnable {

    private RenderedPage page;

    private BeanNatLgNames stds;

    ThreadRefresh(RenderedPage _page, BeanNatLgNames _lgNames) {
        page = _page;
        page.start();
        stds = _lgNames;
    }

    @Override
    public void run() {
        stds.rendRefresh(page.getNavigation(), page.getContext());
        afterAction();
    }
    private void afterAction() {
        ContextEl context_ = page.getContext();
        CallingState exc_ = context_.getCallingState();
        if (exc_ instanceof Struct) {
            if (page.getArea() != null) {
                Struct exception_ = (Struct) exc_;
                if (exception_ instanceof ErroneousStruct) {
                    ArrayStruct fullStack_ = ((ErroneousStruct) exception_).getFullStack();
                    page.getArea().append(((ErroneousStruct) exception_).getStringRep(context_, fullStack_));
                } else {
                    context_.setCallingState(null);
                    String str_ = page.getStandards().processString(new Argument(exception_), page.getContext());
                    page.getArea().append(str_);
                }
            }
            context_.setCallingState(null);
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

    private void finish() {
        page.finish();
    }
}
