package code.gui.document;

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
        RendStackCall rendStackCall_ = new RendStackCall(InitPhase.NOTHING,null);
        page_.getNavigation().initializeRendSession(null, page_.getStandards(), rendStackCall_);
        afterActionWithoutRemove();
    }
}
