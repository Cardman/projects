package code.gui.document;

import code.bean.nat.BeanNatCommonLgNames;
import code.formathtml.Navigation;
import code.sml.Element;

public final class NatRenderAction implements AbstractRenderAction {
    private final RenderedPage render;
    private final BeanNatCommonLgNames std;

    public NatRenderAction(RenderedPage _re, BeanNatCommonLgNames _stds) {
        render = _re;
        std = _stds;
    }
    @Override
    public String execute(boolean _form, Element _elt) {
        if (_form) {
            std.processRendFormRequest(render.getNavigation(), _elt);
        } else {
            Navigation navigation_ = render.getNavigation();
            std.processRendAnchorRequest(_elt, navigation_);
        }
        return null;
    }
}
