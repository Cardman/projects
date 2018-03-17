package code.gui.document;

import javax.swing.JLabel;

import code.formathtml.render.MetaPlainLabel;

public final class DualPlainLabel extends DualLabel {

    public DualPlainLabel(DualContainer _container, MetaPlainLabel _component, RenderedPage _page) {
        super(_container, _component, new JLabel(), _page);
        getGraphic().setOpaque(true);
    }

    @Override
    public MetaPlainLabel getComponent() {
        return (MetaPlainLabel) super.getComponent();
    }

}
