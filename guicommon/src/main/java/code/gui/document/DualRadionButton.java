package code.gui.document;

import code.formathtml.render.IntRadioButton;
import code.formathtml.render.MetaRadioButton;
import code.gui.CustComponent;
import code.gui.RadioButton;

public final class DualRadionButton extends DualInput implements IntRadioButton {

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

    @Override
    public boolean isChecked() {
        return getRadio().isSelected();
    }

    @Override
    public String getValue() {
        return value;
    }

}
