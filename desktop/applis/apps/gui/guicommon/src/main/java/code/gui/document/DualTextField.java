package code.gui.document;

import code.formathtml.render.IntTextField;
import code.formathtml.render.MetaTextField;
import code.gui.AbsCustComponent;
import code.gui.AbsTextField;

public final class DualTextField extends DualInput implements IntTextField {

    private final AbsTextField field;

    public DualTextField(DualContainer _container, MetaTextField _component, RenderedPage _page) {
        super(_container, _component, _page);
        field = _page.getCompoFactory().newTextField(_component.getValue(), _component.getCols());
        updateGraphics(field,_component);
    }

    @Override
    public AbsCustComponent getGraphic() {
        return field;
    }

    @Override
    public String getValue() {
        return field.getText();
    }

}
