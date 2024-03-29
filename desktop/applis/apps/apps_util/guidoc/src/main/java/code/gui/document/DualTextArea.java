package code.gui.document;

import code.formathtml.render.IntTextArea;
import code.formathtml.render.MetaTextArea;
import code.gui.AbsCustComponent;
import code.gui.AbsTextArea;

public final class DualTextArea extends DualInput implements IntTextArea {

    private final AbsTextArea area;

    public DualTextArea(DualContainer _container, MetaTextArea _component, RenderedPage _page) {
        super(_container, _component, _page);
        area = _page.getCompoFactory().newTextArea(_component.getValue(), _component.getRows(), _component.getCols());
        updateGraphics(area,_component);
    }

    @Override
    public AbsCustComponent getGraphic() {
        return area;
    }

    @Override
    public String getValue() {
        return area.getText();
    }

}
