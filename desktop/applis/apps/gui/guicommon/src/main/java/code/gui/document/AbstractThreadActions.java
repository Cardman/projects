package code.gui.document;

import code.formathtml.render.MetaDocument;
import code.gui.FrameUtil;
import code.sml.Document;

public abstract class AbstractThreadActions implements Runnable {

    private final RenderedPage page;

    protected AbstractThreadActions(RenderedPage _page) {
        page = _page;
    }

//    protected void afterAction(ContextEl _ctx, RendStackCall _stackCall) {
//        if (_ctx == null || _stackCall == null) {
//            finish();
//            return;
//        }
//        ContextEl ctx_ = page.getContextCreator().removeContext(_ctx);
//        afterActionWithoutRemove(ctx_, _stackCall);
//    }

//    protected void afterActionWithoutRemove(ContextEl _ctx, RendStackCall _stackCall) {
//        String error_ = ProcessMethod.error(_ctx, _stackCall.getStackCall());
//        if (error_ != null) {
//            if (page.getArea() != null) {
//                page.getArea().append(error_);
//            }
//            _stackCall.getStackCall().setNullCallingState();
//            finish();
//            return;
//        }
//        afterActionWithoutRemove();
//    }

    protected void afterActionWithoutRemove() {
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
