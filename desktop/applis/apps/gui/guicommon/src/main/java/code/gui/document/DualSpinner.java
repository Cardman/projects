package code.gui.document;

import code.formathtml.render.IntSpinner;
import code.formathtml.render.MetaSpinner;
import code.gui.CustComponent;
import code.gui.Spinner;
import code.util.core.NumberUtil;

public final class DualSpinner extends DualInput implements IntSpinner {

    private final Spinner field;

    public DualSpinner(DualContainer _container, MetaSpinner _component, RenderedPage _page) {
        super(_container, _component, _page);
        field = new Spinner(NumberUtil.parseInt(_component.getValue()),Integer.MIN_VALUE,Integer.MAX_VALUE,1);
        updateGraphics(field,_component);
    }

    @Override
    public CustComponent getGraphic() {
        return field;
    }

    @Override
    public String getValue() {
        return Long.toString(field.getValue());
    }

}
