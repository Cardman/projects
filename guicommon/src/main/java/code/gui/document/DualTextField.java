package code.gui.document;

import javax.swing.*;

import code.formathtml.render.MetaTextField;
import code.gui.CustComponent;
import code.gui.TextField;

public final class DualTextField extends DualInput {

    private final TextField field;

    public DualTextField(DualContainer _container, MetaTextField _component, RenderedPage _page) {
        super(_container, _component, _page);
        field = new TextField(_component.getValue(), _component.getCols());
        updateGraphics(field,_component);
    }

    @Override
    public CustComponent getGraphic() {
        return field;
    }

    public String getValue() {
        return field.getText();
    }

}
