package code.gui.document;

import java.awt.Dimension;

import javax.swing.JLabel;

import code.formathtml.render.MetaIndentLabel;

public final class DualIndentLabel extends DualLabel {

    public DualIndentLabel(DualContainer _container, MetaIndentLabel _component,
            RenderedPage _page) {
        super(_container, _component, _page);
        JLabel lab_ = getLabel();
        lab_.setPreferredSize(new Dimension(_component.getStyle().getEmToPixels(), 0));
    }

    @Override
    public void paint() {
    }
}
