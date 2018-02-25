package code.gui.document;

import javax.swing.JCheckBox;

import code.formathtml.render.MetaCheckedBox;

public final class DualCheckedBox extends DualInput {

    public DualCheckedBox(DualContainer _container, MetaCheckedBox _component,
            RenderedPage _page) {
        super(_container, _component, new JCheckBox("",_component.isChecked()), _page);
    }

    @Override
    public JCheckBox getGraphic() {
        return (JCheckBox) super.getGraphic();
    }

    @Override
    public String getValue() {
        if (getGraphic().isSelected()) {
            return "on";
        }
        return "off";
    }

}
