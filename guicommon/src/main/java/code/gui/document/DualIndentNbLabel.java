package code.gui.document;

import java.awt.Dimension;

import javax.swing.JLabel;

import code.formathtml.render.MetaIndentNbLabel;

public final class DualIndentNbLabel extends DualLabel {

    public DualIndentNbLabel(DualContainer _container, MetaIndentNbLabel _component,
            RenderedPage _page, int _width) {
        super(_container, _component, _page);
        JLabel lab_ = getLabel();
        lab_.setPreferredSize(new Dimension(_width, _component.getStyle().getEmToPixels()));
    }

    @Override
    public void paint() {
    }
}
