package code.gui.document;

import javax.swing.*;

import code.formathtml.render.MetaComponent;
import code.gui.CustComponent;
import code.gui.Separator;

public final class DualSeparator extends DualLeaf {
    private Separator separator;

    public DualSeparator(DualContainer _container, MetaComponent _component,
            RenderedPage _page) {
        super(_container, _component, _page);
        separator = new Separator();
        updateGraphics(separator,_component);
    }

    @Override
    public CustComponent getGraphic() {
        return separator;
    }
}
