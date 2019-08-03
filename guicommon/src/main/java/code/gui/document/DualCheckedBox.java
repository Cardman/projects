package code.gui.document;

import code.formathtml.render.MetaCheckedBox;
import code.gui.CustCheckBox;
import code.gui.CustComponent;

public final class DualCheckedBox extends DualInput {

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

    public String getValue() {
        if (check.isSelected()) {
            return "on";
        }
        return "off";
    }

}
