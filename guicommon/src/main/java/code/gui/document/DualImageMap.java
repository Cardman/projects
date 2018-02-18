package code.gui.document;

import java.awt.GridLayout;

import code.formathtml.render.MetaImageMap;


public final class DualImageMap extends DualContainer {

    public DualImageMap(DualContainer _container, MetaImageMap _component, RenderedPage _page) {
        super(_container, _component, _page);
        int width_ = _component.getWidth();
        GridLayout lay_ = new GridLayout(0,width_);
        getGraphic().setLayout(lay_);
    }

}
