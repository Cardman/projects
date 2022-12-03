package code.gui.document;

import aiki.beans.BeanNatCommonLgNamesForm;
import code.bean.nat.NatNavigation;
import code.sml.Element;

public final class NatRenderAction implements AbstractRenderAction {
    private final BeanNatCommonLgNamesForm std;
    private final NatNavigation nav;

    public NatRenderAction(BeanNatCommonLgNamesForm _stds, NatNavigation _n) {
        std = _stds;
        nav = _n;
    }
    @Override
    public String execute(boolean _form, Element _elt) {
        std.execute(_form, nav);
        return null;
    }
}
