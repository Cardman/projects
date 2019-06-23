package code.gui.document;

import javax.swing.*;

import code.formathtml.render.MetaImage;

public abstract class DualImage extends DualLeaf {

    private JLabel label;

    public DualImage(DualContainer _container, MetaImage _component,
            RenderedPage _page) {
        super(_container, _component, _page);
        label = new JLabel();
        updateGraphics(label,_component);
    }

    @Override
    protected void postAdd() {
        paint();
    }

    @Override
    public JComponent getGraphic() {
        return getLabel();
    }

    public JLabel getLabel() {
        return label;
    }

    public void paint() {
    }
}
