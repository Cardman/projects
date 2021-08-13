package code.gui.document;

import code.expressionlanguage.exec.InitPhase;
import code.formathtml.exec.RendStackCall;
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
//        RendStackCall rendStack_ = new RendStackCall(InitPhase.NOTHING,page.getContext());
//        stds.rendRefresh(page.getNavigation(), page.getContext(), rendStack_);
        afterAction();
    }
    private void afterAction() {
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
