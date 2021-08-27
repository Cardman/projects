package code.gui.document;

import code.formathtml.render.MetaContainer;
import code.formathtml.render.MetaImageMap;
import code.gui.AbsPanel;
import code.gui.Panel;


public final class DualImageMap extends DualContainer {

    public DualImageMap(DualContainer _container, MetaImageMap _component, RenderedPage _page) {
        super(_container, _component, _page);
    }

    @Override
    protected AbsPanel newPanel(DualContainer _container, MetaContainer _component, RenderedPage _page) {
        int width_ = ((MetaImageMap)_component).getWidth();
        return Panel.newGrid(0,width_);
    }
}
