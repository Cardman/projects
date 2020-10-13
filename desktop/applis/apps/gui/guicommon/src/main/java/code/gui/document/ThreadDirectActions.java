package code.gui.document;

import code.expressionlanguage.ContextEl;

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
        page_.getNavigation().initializeRendSession(ctx_, page_.getStandards());
        afterAction(ctx_);
    }
}
