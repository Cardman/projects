package code.gui.document;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.InitPhase;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanCustLgNames;

public final class EventThreadActions extends AbstractThreadActions {


    private boolean form;

    private EventThreadActions(RenderedPage _page){
        super(_page);
    }

    static EventThreadActions inst(RenderedPage _page, boolean _form) {
        EventThreadActions t_ = new EventThreadActions(_page);
        t_.getPage().start();
        t_.form = _form;
        return t_;
    }

    @Override
    public void run() {
        if (getPage().getStandards() instanceof BeanCustLgNames) {
            AbstractContextCreator creator_ = getPage().getContextCreator();
            ContextEl ctx_ = creator_.newContext(getPage().getContext());
            RendStackCall rendStackCall_ = new RendStackCall(InitPhase.NOTHING,ctx_);
            if (form) {
                getPage().getNavigation().processRendFormRequest(getPage().getStandards(), ctx_, rendStackCall_);
                afterAction(ctx_,rendStackCall_);
                return;
            }
            getPage().getNavigation().processRendAnchorRequest(getPage().getStandards(), ctx_, rendStackCall_);
            afterAction(ctx_,rendStackCall_);
            return;
        }
        RendStackCall rendStackCall_ = new RendStackCall(InitPhase.NOTHING,null);
        if (form) {
            getPage().getNavigation().processRendFormRequest(getPage().getStandards(), null, rendStackCall_);
            afterActionWithoutRemove();
            return;
        }
        getPage().getNavigation().processRendAnchorRequest(getPage().getStandards(), null, rendStackCall_);
        afterActionWithoutRemove();
    }
}
