package code.gui.document;

import code.formathtml.render.IntRadioButton;
import code.formathtml.render.MetaRadioButton;
import code.gui.CustComponent;
import code.gui.Panel;
import code.gui.RadioButton;

import java.awt.*;

public final class DualRadionButton extends DualInput implements IntRadioButton {

    private final RadioButton radio;
    private String value;
    private Panel panel;

    public DualRadionButton(DualContainer _container, MetaRadioButton _component,
            RenderedPage _page) {
        super(_container, _component, _page);
        value = _component.getValue();
        radio = new RadioButton("", _component.isChecked());
        panel = Panel.newFlow(FlowLayout.CENTER,0,0);
        updateGraphics(radio,_component);
        panel.add(radio);
    }

    @Override
    public CustComponent getGraphic() {
        return panel;
    }

    public RadioButton getRadio() {
        return radio;
    }

    @Override
    public boolean isChecked() {
        return getRadio().isSelected();
    }

    @Override
    public String getValue() {
        return value;
    }

}
