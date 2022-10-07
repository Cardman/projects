package code.gui.document;

import code.expressionlanguage.ContextEl;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.DefaultInitialization;
import code.sml.Element;

public final class CustRenderAction implements AbstractRenderAction {
    private final AbstractContextCreator creator;
    private final RenderedPage render;
    private final BeanCustLgNames std;

    public CustRenderAction(AbstractContextCreator _c, RenderedPage _re,BeanCustLgNames _stds) {
        this.creator = _c;
        render = _re;
        std = _stds;
    }

    @Override
    public String execute(boolean _form, Element _elt) {
        ContextEl ctx_ = creator.newContext(render.getContext());
        RendStackCall rendStackCall_ = std.execute(_form, _elt, render.getNavigation(), ctx_);
        return afterAction(ctx_,rendStackCall_);
    }

    private String afterAction(ContextEl _ctx, RendStackCall _stackCall) {
        ContextEl ctx_ = render.getContextCreator().removeContext(_ctx);
        return afterActionWithoutRemove(ctx_, _stackCall);
    }

    private String afterActionWithoutRemove(ContextEl _ctx, RendStackCall _stackCall) {
        String error_ = DefaultInitialization.afterActionWithoutRemove(_ctx, _stackCall);
        if (error_ != null) {
            if (render.getArea() != null) {
                render.getArea().append(error_);
            }
            render.finish();
            return error_;
        }
        return null;
    }
}
