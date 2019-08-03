package code.gui.document;

import code.formathtml.render.MetaSlider;
import code.formathtml.render.MetaSpinner;
import code.gui.CustComponent;
import code.gui.Slider;

import javax.swing.*;

public final class DualSlider extends DualInput {

    private final Slider field;

    public DualSlider(DualContainer _container, MetaSlider _component, RenderedPage _page) {
        super(_container, _component, _page);
        field = new Slider();
        field.setValue(Integer.parseInt(_component.getValue()));
        updateGraphics(field,_component);
    }

    @Override
    public CustComponent getGraphic() {
        return field;
    }

    public String getValue() {
        return Integer.toString(field.getValue());
    }

}
