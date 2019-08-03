package code.gui.document;

import javax.swing.*;

import code.formathtml.render.MetaTextArea;
import code.gui.CustComponent;
import code.gui.TextArea;

public final class DualTextArea extends DualInput {

    private final TextArea area;

    public DualTextArea(DualContainer _container, MetaTextArea _component, RenderedPage _page) {
        super(_container, _component, _page);
        area = new TextArea(_component.getValue(), _component.getRows(), _component.getCols());
        updateGraphics(area,_component);
    }

    @Override
    public CustComponent getGraphic() {
        return area;
    }

    public String getValue() {
        return area.getText();
    }

}
