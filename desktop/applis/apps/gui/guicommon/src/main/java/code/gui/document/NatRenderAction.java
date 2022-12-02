package code.gui.document;

import aiki.beans.BeanNatCommonLgNamesForm;
import code.sml.Element;

public final class NatRenderAction implements AbstractRenderAction {
    private final RenderedPage render;
    private final BeanNatCommonLgNamesForm std;

    public NatRenderAction(RenderedPage _re, BeanNatCommonLgNamesForm _stds) {
        render = _re;
        std = _stds;
    }
    @Override
    public String execute(boolean _form, Element _elt) {
        std.execute(_form, render.getNavigation());
        return null;
    }
}
