package code.gui.document;

import java.awt.Dimension;

import javax.swing.JLabel;

import code.formathtml.render.MetaIndentLabel;

public final class DualIndentLabel extends DualLabel {

    public DualIndentLabel(DualContainer _container, MetaIndentLabel _component,
            RenderedPage _page) {
        super(_container, _component, new JLabel(), _page);
        JLabel lab_ = getGraphic();
        lab_.setPreferredSize(new Dimension(_component.getStyle().getEmToPixels(), 0));
        WindowPage._texts_.put(getGraphic(), "");
    }

    @Override
    public void paint() {
    }
}
