package code.gui.document;

import code.formathtml.render.IntRadioButton;
import code.formathtml.render.MetaRadioButton;
import code.gui.*;

public final class DualRadionButton extends DualInput implements IntRadioButton {

    private final AbsRadioButton radio;
    private String value;
    private AbsPanel panel;

    public DualRadionButton(DualContainer _container, MetaRadioButton _component,
            RenderedPage _page) {
        super(_container, _component, _page);
        value = _component.getValue();
        radio = _page.getCompoFactory().newRadioButton("", _component.isChecked());
        panel = _page.getCompoFactory().newLineBox();
        updateGraphics(radio,_component);
        panel.add(radio);
    }

    @Override
    public AbsCustComponent getGraphic() {
        return panel;
    }

    public AbsRadioButton getRadio() {
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
