package code.gui.document;

import javax.swing.*;

import code.formathtml.render.MetaTextArea;

public final class DualTextArea extends DualInput {

    private final JTextArea area;

    public DualTextArea(DualContainer _container, MetaTextArea _component, RenderedPage _page) {
        super(_container, _component, _page);
        area = new JTextArea(_component.getValue(), _component.getRows(), _component.getCols());
        updateGraphics(area,_component);
    }

    @Override
    public JComponent getGraphic() {
        return area;
    }

    public String getValue() {
        return area.getText();
    }

}
