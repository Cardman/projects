package code.gui.document;

import javax.swing.*;

import code.formathtml.render.MetaTextField;

public final class DualTextField extends DualInput {

    private final JTextField field;

    public DualTextField(DualContainer _container, MetaTextField _component, RenderedPage _page) {
        super(_container, _component, _page);
        field = new JTextField(_component.getValue(), _component.getCols());
        updateGraphics(field,_component);
    }

    @Override
    public JComponent getGraphic() {
        return field;
    }

    public String getValue() {
        return field.getText();
    }

}
