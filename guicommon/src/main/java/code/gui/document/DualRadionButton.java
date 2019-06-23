package code.gui.document;

import javax.swing.*;

import code.formathtml.render.MetaRadioButton;

public final class DualRadionButton extends DualInput {

    private final JRadioButton radio;
    private String value;

    public DualRadionButton(DualContainer _container, MetaRadioButton _component,
            RenderedPage _page) {
        super(_container, _component, _page);
        value = _component.getValue();
        radio = new JRadioButton("", _component.isChecked());
        updateGraphics(radio,_component);
    }

    @Override
    public JComponent getGraphic() {
        return getRadio();
    }

    public JRadioButton getRadio() {
        return radio;
    }

    public String getValue() {
        if (radio.isSelected()) {
            return value;
        }
        return "";
    }

}
