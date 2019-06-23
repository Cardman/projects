package code.gui.document;

import javax.swing.*;

import code.formathtml.render.MetaComponent;

public final class DualSeparator extends DualLeaf {
    private JSeparator separator;

    public DualSeparator(DualContainer _container, MetaComponent _component,
            RenderedPage _page) {
        super(_container, _component, _page);
        separator = new JSeparator();
        updateGraphics(separator,_component);
    }

    @Override
    public JComponent getGraphic() {
        return separator;
    }
}
