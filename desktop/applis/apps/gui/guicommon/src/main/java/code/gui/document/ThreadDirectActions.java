package code.gui.document;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.InitPhase;
import code.formathtml.exec.RendStackCall;

public final class ThreadDirectActions extends AbstractThreadActions {

    ThreadDirectActions(RenderedPage _page) {
        super(_page);
        getPage().start();
    }

    @Override
    public void run() {
        RenderedPage page_ = getPage();
        ContextEl ctx_ = page_.getContext();
        if (ctx_ == null) {
            return;
        }
        RendStackCall rendStackCall_ = new RendStackCall(InitPhase.NOTHING,ctx_);
        page_.getNavigation().initializeRendSession(ctx_, page_.getStandards(), rendStackCall_);
        afterAction(ctx_,rendStackCall_.getStackCall());
    }
}
