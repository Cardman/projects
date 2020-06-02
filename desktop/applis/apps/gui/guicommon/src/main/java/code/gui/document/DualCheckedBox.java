package code.gui.document;

import code.formathtml.render.IntCheckBox;
import code.formathtml.render.MetaCheckedBox;
import code.formathtml.util.BeanLgNames;
import code.gui.CustCheckBox;
import code.gui.CustComponent;

public final class DualCheckedBox extends DualInput implements IntCheckBox {

    private final CustCheckBox check;

    public DualCheckedBox(DualContainer _container, MetaCheckedBox _component,
                          RenderedPage _page) {
        super(_container, _component, _page);
        check = new CustCheckBox("", _component.isChecked());
        updateGraphics(check,_component);
    }

    @Override
    public CustComponent getGraphic() {
        return check;
    }

    @Override
    public String getValue() {
        if (check.isSelected()) {
            return BeanLgNames.ON;
        }
        return BeanLgNames.OFF;
    }

}
