package code.gui.document;

import java.awt.GridLayout;

import code.formathtml.render.MetaContainer;
import code.formathtml.render.MetaImageMap;
import code.gui.Panel;


public final class DualImageMap extends DualContainer {

    public DualImageMap(DualContainer _container, MetaImageMap _component, RenderedPage _page) {
        super(_container, _component, _page);
    }

    @Override
    protected Panel newPanel(DualContainer _container, MetaContainer _component, RenderedPage _page) {
        int width_ = ((MetaImageMap)_component).getWidth();
        Panel p_ = Panel.newGrid(0,width_);
        return p_;
    }
}
