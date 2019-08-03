package code.gui.document;

import javax.swing.*;

import code.formathtml.render.MetaRadioButton;
import code.gui.CustComponent;
import code.gui.RadioButton;

public final class DualRadionButton extends DualInput {

    private final RadioButton radio;
    private String value;

    public DualRadionButton(DualContainer _container, MetaRadioButton _component,
            RenderedPage _page) {
        super(_container, _component, _page);
        value = _component.getValue();
        radio = new RadioButton("", _component.isChecked());
        updateGraphics(radio,_component);
    }

    @Override
    public CustComponent getGraphic() {
        return getRadio();
    }

    public RadioButton getRadio() {
        return radio;
    }

    public String getValue() {
        if (radio.isSelected()) {
            return value;
        }
        return "";
    }

}
