package code.gui.document;

import code.bean.nat.BeanNatCommonLgNames;
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
        std.execute(_form, _elt, render.getNavigation());
        return null;
    }
}
