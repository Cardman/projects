package code.gui.document;

import code.bean.nat.BeanNatCommonLgNames;
import code.formathtml.render.MetaDocument;
import code.gui.FrameUtil;
import code.sml.Document;

public final class ThreadRefresh implements Runnable {

    private final RenderedPage page;

    private final BeanNatCommonLgNames stds;

    ThreadRefresh(RenderedPage _page, BeanNatCommonLgNames _lgNames) {
        page = _page;
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
        FrameUtil.invokeLater(new WindowPage(metadoc_, page.getScroll(), page), page.getGene());
    }

    private void finish() {
        page.finish();
    }
}
