package code.gui.document;

import javax.swing.JComponent;

import code.formathtml.render.MetaComponent;
import code.util.CustList;

public abstract class DualComponent {

    private final DualContainer container;
    private final CustList<DualComponent> children = new CustList<DualComponent>();
    private final MetaComponent component;

    private final JComponent graphic;

    private RenderedPage page;

    public DualComponent(DualContainer _container, MetaComponent _component, JComponent _graphic, RenderedPage _page) {
        container = _container;
        component = _component;
        graphic = _graphic;
        page = _page;
        setTitle(_component);
    }

    public CustList<DualComponent> getChildren() {
        return children;
    }

    public DualContainer getContainer() {
        return container;
    }

    private void setTitle(MetaComponent _component) {
        String title_ = _component.getTitle();
        if (!title_.isEmpty()) {
            graphic.setToolTipText(title_);
        }
    }
    public int getComponentCount() {
        return graphic.getComponentCount();
    }

    public JComponent getParent() {
        return (JComponent) graphic.getParent();
    }
    public JComponent getComponent(int _index) {
        return (JComponent) graphic.getComponent(_index);
    }

    public void add(DualComponent _dual) {
        page.getRefs().put(_dual.getComponent(), _dual);
        children.add(_dual);
        graphic.add(_dual.graphic);
        if (_dual instanceof DualAnimatedImage) {
            page.getAnims().add((DualAnimatedImage) _dual);
        }
        _dual.postAdd();
    }

    protected void postAdd() {
    }

    public MetaComponent getComponent() {
        return component;
    }

    public JComponent getGraphic() {
        return graphic;
    }

    public RenderedPage getPage() {
        return page;
    }

}
