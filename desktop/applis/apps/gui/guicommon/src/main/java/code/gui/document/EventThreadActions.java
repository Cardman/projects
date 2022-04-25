package code.gui.document;

import code.bean.nat.BeanNatCommonLgNames;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.InitPhase;
import code.formathtml.Navigation;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanCustLgNames;
import code.sml.Element;

public final class EventThreadActions extends AbstractThreadActions {


    private boolean form;
    private Element elt;

    private EventThreadActions(RenderedPage _page){
        super(_page);
    }

    static EventThreadActions inst(RenderedPage _page, boolean _form, Element _elt) {
        EventThreadActions t_ = new EventThreadActions(_page);
        t_.getPage().start();
        t_.form = _form;
        t_.elt = _elt;
        return t_;
    }

    @Override
    public void run() {
        if (getPage().getStandards() instanceof BeanCustLgNames) {
            AbstractContextCreator creator_ = getPage().getContextCreator();
            ContextEl ctx_ = creator_.newContext(getPage().getContext());
            RendStackCall rendStackCall_ = new RendStackCall(InitPhase.NOTHING,ctx_);
            if (form) {
                ((BeanCustLgNames)getPage().getStandards()).processRendFormRequest(getPage().getNavigation(), ctx_, rendStackCall_, elt);
                afterAction(ctx_,rendStackCall_);
                return;
            }
            Navigation navigation_ = getPage().getNavigation();
            ((BeanCustLgNames)getPage().getStandards()).processRendAnchorRequest(elt, navigation_, ctx_, rendStackCall_);
            afterAction(ctx_,rendStackCall_);
            return;
        }
        if (form) {
            ((BeanNatCommonLgNames)getPage().getStandards()).processRendFormRequest(getPage().getNavigation(), elt);
            afterActionWithoutRemove();
            return;
        }
        Navigation navigation_ = getPage().getNavigation();
        ((BeanNatCommonLgNames)getPage().getStandards()).processRendAnchorRequest(elt, navigation_);
        afterActionWithoutRemove();
    }
}
