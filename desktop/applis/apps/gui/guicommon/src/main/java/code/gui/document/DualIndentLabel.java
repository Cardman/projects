package code.gui.document;

import java.awt.Dimension;

import code.formathtml.render.MetaIndentLabel;
import code.gui.AbsPreparedLabel;
import code.gui.images.MetaDimension;

public final class DualIndentLabel extends DualLabel {

    public DualIndentLabel(DualContainer _container, MetaIndentLabel _component,
            RenderedPage _page) {
        super(_container, _component, _page);
        AbsPreparedLabel lab_ = getLabel();
        lab_.setPreferredSize(new MetaDimension(_component.getStyle().getEmToPixels(), 0));
    }

    @Override
    public void paint() {
        //
    }
}
