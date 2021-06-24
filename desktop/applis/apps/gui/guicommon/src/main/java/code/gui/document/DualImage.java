package code.gui.document;

import code.formathtml.render.MetaImage;
import code.gui.CustComponent;
import code.gui.PreparedLabel;

public abstract class DualImage extends DualLeaf {

    private final PreparedLabel label;

    public DualImage(DualContainer _container, MetaImage _component,
            RenderedPage _page) {
        super(_container, _component, _page);
        label = PreparedLabel.prep(_page.getGene().getImageFactory());
        updateGraphics(label,_component);
    }

    @Override
    protected void postAdd() {
        paint();
    }

    @Override
    public CustComponent getGraphic() {
        return getLabel();
    }

    public PreparedLabel getLabel() {
        return label;
    }

    public abstract void paint();
}
