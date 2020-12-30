package code.gui.document;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ErroneousStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.render.MetaDocument;
import code.bean.nat.BeanNatLgNames;
import code.gui.CustComponent;
import code.sml.Document;

public final class ThreadRefresh implements Runnable {

    private final RenderedPage page;

    private final BeanNatLgNames stds;

    ThreadRefresh(RenderedPage _page, BeanNatLgNames _lgNames) {
        page = _page;
        page.start();
        stds = _lgNames;
    }

    @Override
    public void run() {
        StackCall stack_ = StackCall.newInstance(InitPhase.NOTHING,page.getContext());
        stds.rendRefresh(page.getNavigation(), page.getContext(), stack_);
        afterAction(stack_);
    }
    private void afterAction(StackCall _stackCall) {
        ContextEl context_ = page.getContext();
        CallingState exc_ = _stackCall.getCallingState();
        if (exc_ instanceof CustomFoundExc) {
            if (page.getArea() != null) {
                Struct exception_ = ((CustomFoundExc) exc_).getStruct();
                if (exception_ instanceof ErroneousStruct) {
                    ArrayStruct fullStack_ = ((ErroneousStruct) exception_).getFullStack();
                    page.getArea().append(((ErroneousStruct) exception_).getStringRep(context_, fullStack_));
                } else {
                    _stackCall.setCallingState(null);
                    String str_ = page.getStandards().processString(new Argument(exception_), page.getContext(), _stackCall);
                    page.getArea().append(str_);
                }
            }
            _stackCall.setCallingState(null);
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
