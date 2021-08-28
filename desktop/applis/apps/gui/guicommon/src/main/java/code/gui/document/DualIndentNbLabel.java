package code.gui.document;



import code.formathtml.render.MetaIndentNbLabel;
import code.gui.AbsPreparedLabel;
import code.gui.images.MetaDimension;

public final class DualIndentNbLabel extends DualLabel {

    public DualIndentNbLabel(DualContainer _container, MetaIndentNbLabel _component,
            RenderedPage _page, int _width) {
        super(_container, _component, _page);
        AbsPreparedLabel lab_ = getLabel();
        lab_.setPreferredSize(new MetaDimension(_width, _component.getStyle().getEmToPixels()));
    }

    @Override
    public void paint() {
        //
    }
}
