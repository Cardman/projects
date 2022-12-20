package code.gui.document;

import code.formathtml.render.MetaContainer;
import code.formathtml.render.MetaImageMap;
import code.gui.AbsPanel;


public final class DualImageMap extends DualContainer {

    public DualImageMap(DualContainer _container, MetaImageMap _component, RenderedPage _page) {
        super(_container, _component, _page);
    }

    @Override
    protected AbsPanel newPanel(DualContainer _container, MetaContainer _component, RenderedPage _page) {
        int width_ = ((MetaImageMap)_component).getWidth();
        return _page.getCompoFactory().newGrid(0,width_);
    }
}
