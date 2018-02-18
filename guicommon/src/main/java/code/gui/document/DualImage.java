package code.gui.document;

import javax.swing.JLabel;

import code.formathtml.render.MetaImage;

public abstract class DualImage extends DualLeaf {

    public DualImage(DualContainer _container, MetaImage _component,
            RenderedPage _page) {
        super(_container, _component, new JLabel(), _page);
    }

    @Override
    protected void postAdd() {
        paint();
    }

    @Override
    public JLabel getGraphic() {
        return (JLabel) super.getGraphic();
    }

    @Override
    public MetaImage getComponent() {
        return (MetaImage) super.getComponent();
    }
    public void paint() {
    }
}
