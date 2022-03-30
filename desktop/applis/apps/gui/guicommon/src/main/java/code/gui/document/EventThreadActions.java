package code.gui.document;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.exec.NatRendStackCall;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.InitPhase;
import code.formathtml.Navigation;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanCustLgNames;
import code.sml.DocumentBuilder;

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
                ((BeanCustLgNames)getPage().getStandards()).processRendFormRequest(getPage().getNavigation(), ctx_, rendStackCall_);
                afterAction(ctx_,rendStackCall_);
                return;
            }
            Navigation navigation = getPage().getNavigation();
            String res_ = ((BeanCustLgNames)getPage().getStandards()).processRendAnchorRequest(DocumentBuilder.getFirstElementByAttribute(navigation.getDocument(), navigation.getSession().getRendKeyWords().getAttrNa(), Long.toString(navigation.getHtmlPage().getUrl())), navigation, ctx_, rendStackCall_);
            navigation.setupText(res_,((BeanCustLgNames)getPage().getStandards()), rendStackCall_.getDocument(), rendStackCall_.getHtmlPage());
            afterAction(ctx_,rendStackCall_);
            return;
        }
        NatRendStackCall rendStackCall_ = new NatRendStackCall();
        if (form) {
            ((BeanNatCommonLgNames)getPage().getStandards()).processRendFormRequest(getPage().getNavigation(), rendStackCall_);
            afterActionWithoutRemove();
            return;
        }
        Navigation navigation = getPage().getNavigation();
        String res_ = ((BeanNatCommonLgNames)getPage().getStandards()).processRendAnchorRequest(DocumentBuilder.getFirstElementByAttribute(navigation.getDocument(), navigation.getSession().getRendKeyWords().getAttrNa(), Long.toString(navigation.getHtmlPage().getUrl())), navigation, rendStackCall_);
        navigation.setupText(res_,(BeanNatCommonLgNames)getPage().getStandards(), rendStackCall_.getDocument(), rendStackCall_.getHtmlPage());
        afterActionWithoutRemove();
    }
}
