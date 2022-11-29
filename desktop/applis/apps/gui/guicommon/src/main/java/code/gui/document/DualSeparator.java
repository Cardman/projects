package code.gui.document;

import code.formathtml.render.MetaComponent;
import code.gui.AbsCustComponent;

public final class DualSeparator extends DualLeaf {
    private final AbsCustComponent separator;

    public DualSeparator(DualContainer _container, MetaComponent _component,
            RenderedPage _page) {
        super(_container, _component, _page);
        separator = _page.getCompoFactory().newAbsPaintableLabel();
        updateGraphics(separator,_component);
    }

    @Override
    public AbsCustComponent getGraphic() {
        return separator;
    }
}
