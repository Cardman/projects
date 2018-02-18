package code.gui.document;

import javax.swing.JSeparator;

import code.formathtml.render.MetaComponent;

public final class DualSeparator extends DualLeaf {

    public DualSeparator(DualContainer _container, MetaComponent _component,
            RenderedPage _page) {
        super(_container, _component, new JSeparator(), _page);
    }

}
