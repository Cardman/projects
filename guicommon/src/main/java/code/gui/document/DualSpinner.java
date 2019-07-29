package code.gui.document;

import code.formathtml.render.MetaSpinner;
import code.formathtml.render.MetaTextField;

import javax.swing.*;

public final class DualSpinner extends DualInput {

    private final JSpinner field;

    public DualSpinner(DualContainer _container, MetaSpinner _component, RenderedPage _page) {
        super(_container, _component, _page);
        field = new JSpinner();
        field.setValue(Integer.parseInt(_component.getValue()));
        updateGraphics(field,_component);
    }

    @Override
    public JComponent getGraphic() {
        return field;
    }

    public String getValue() {
        return Integer.toString((Integer)field.getValue());
    }

}
