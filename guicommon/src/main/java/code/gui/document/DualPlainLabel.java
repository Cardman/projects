package code.gui.document;

import code.formathtml.render.MetaPlainLabel;

public final class DualPlainLabel extends DualLabel {

    public DualPlainLabel(DualContainer _container, MetaPlainLabel _component, RenderedPage _page) {
        super(_container, _component, _page);
        getGraphic().setOpaque(true);
    }

}
