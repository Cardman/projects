package code.gui.document;

import code.formathtml.render.IntSlider;
import code.formathtml.render.MetaSlider;
import code.gui.AbsCustComponent;
import code.gui.AbsSlider;
import code.util.core.NumberUtil;

public final class DualSlider extends DualInput implements IntSlider {

    private final AbsSlider field;

    public DualSlider(DualContainer _container, MetaSlider _component, RenderedPage _page) {
        super(_container, _component, _page);
        field = getPage().getCompoFactory().newAbsSlider();
        field.setValue(NumberUtil.parseInt(_component.getValue()));
        updateGraphics(field,_component);
    }

    @Override
    public AbsCustComponent getGraphic() {
        return field;
    }

    @Override
    public String getValue() {
        return Long.toString(field.getValue());
    }

}
