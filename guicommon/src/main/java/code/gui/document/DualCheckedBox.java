package code.gui.document;

import javax.swing.*;

import code.formathtml.render.MetaCheckedBox;

public final class DualCheckedBox extends DualInput {

    private final JCheckBox check;

    public DualCheckedBox(DualContainer _container, MetaCheckedBox _component,
                          RenderedPage _page) {
        super(_container, _component, _page);
        check = new JCheckBox("", _component.isChecked());
        updateGraphics(check,_component);
    }

    @Override
    public JComponent getGraphic() {
        return check;
    }

    public String getValue() {
        if (check.isSelected()) {
            return "on";
        }
        return "off";
    }

}
