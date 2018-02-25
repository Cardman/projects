package code.gui.document;

import javax.swing.JRadioButton;

import code.formathtml.render.MetaRadioButton;

public final class DualRadionButton extends DualInput {

    private String value;

    public DualRadionButton(DualContainer _container, MetaRadioButton _component,
            RenderedPage _page) {
        super(_container, _component, new JRadioButton("",_component.isChecked()), _page);
        value = _component.getValue();
    }

    @Override
    public JRadioButton getGraphic() {
        return (JRadioButton) super.getGraphic();
    }

    @Override
    public String getValue() {
        if (getGraphic().isSelected()) {
            return value;
        }
        return "";
    }

}
