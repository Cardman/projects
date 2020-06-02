package code.gui.document;

import javax.swing.*;

import code.formathtml.render.MetaImage;
import code.gui.CustComponent;
import code.gui.PreparedLabel;
import code.gui.TextLabel;

public abstract class DualImage extends DualLeaf {

    private PreparedLabel label;

    public DualImage(DualContainer _container, MetaImage _component,
            RenderedPage _page) {
        super(_container, _component, _page);
        label = new PreparedLabel();
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

    public void paint() {
    }
}
