package code.gui.document;

import code.formathtml.render.MetaImage;
import code.gui.*;

public abstract class DualImage extends DualLeaf {

    private final AbsPreparedLabel label;

    public DualImage(DualContainer _container, MetaImage _component,
            RenderedPage _page) {
        super(_container, _component, _page);
        label = FrameUtil.prep(_page.getGene().getImageFactory());
        updateGraphics(label,_component);
    }

    @Override
    public AbsCustComponent getGraphic() {
        return getLabel();
    }

    public AbsPreparedLabel getLabel() {
        return label;
    }

    public abstract void paint();
}
