package code.gui.document;

import code.formathtml.render.IntSpinner;
import code.formathtml.render.MetaSpinner;
import code.gui.AbsCustComponent;
import code.gui.AbsSpinner;
import code.util.core.NumberUtil;

public final class DualSpinner extends DualInput implements IntSpinner {

    private final AbsSpinner field;

    public DualSpinner(DualContainer _container, MetaSpinner _component, RenderedPage _page) {
        super(_container, _component, _page);
        field = _page.getCompoFactory().newSpinner(NumberUtil.parseInt(_component.getValue()),Integer.MIN_VALUE,Integer.MAX_VALUE,1);
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
