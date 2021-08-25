package code.gui.document;

import code.formathtml.render.MetaComponent;
import code.gui.AbsCustComponent;
import code.gui.Separator;

public final class DualSeparator extends DualLeaf {
    private final Separator separator;

    public DualSeparator(DualContainer _container, MetaComponent _component,
            RenderedPage _page) {
        super(_container, _component, _page);
        separator = new Separator(_page.getCompoFactory());
        updateGraphics(separator.getPaintableLabel(),_component);
    }

    @Override
    public AbsCustComponent getGraphic() {
        return separator.getPaintableLabel();
    }
}
