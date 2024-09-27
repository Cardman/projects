package code.gui.document;

import code.formathtml.render.MetaDocument;
import code.sml.Document;

public abstract class AbstractThreadActions implements Runnable {

    private final RenderedPage page;
    private boolean rendered;

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
        rendered = false;
        if (!page.isProcessing()) {
            return;
        }
        Document doc_ = page.getNavCore().getDocument();
        if (doc_ == null) {
            finish();
            return;
        }
        MetaDocument metadoc_ = MetaDocument.newInstance(doc_,page.getKeys(),getPage().getKeyWordDigit(), getPage().getConverter(), getPage().getBase());
        page.getGene().getCompoFactory().invokeNow(new WindowPage(metadoc_, page.getScroll(), page));
        rendered = true;
    }

    public boolean isRendered() {
        return rendered;
    }

    protected void finish() {
        page.finish();
    }

    public RenderedPage getPage() {
        return page;
    }

}
