package code.gui.document;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

import code.formathtml.render.BorderEnum;
import code.formathtml.render.MetaComponent;
import code.formathtml.render.MetaStyle;
import code.gui.CustComponent;
import code.gui.Panel;
import code.util.CustList;

public abstract class DualComponent {

    private final DualContainer container;
    private final CustList<DualComponent> children = new CustList<DualComponent>();
    private final MetaComponent component;

    private RenderedPage page;

    public DualComponent(DualContainer _container, MetaComponent _component, RenderedPage _page) {
        container = _container;
        component = _component;
        page = _page;
    }

    protected void updateGraphics(CustComponent _component,MetaComponent _metaComponent) {
        MetaStyle style_ = _metaComponent.getStyle();
        if (style_.getBorder() == BorderEnum.SOLID) {
            _component.setBorder(BorderFactory.createLineBorder(new Color(style_.getBorderColor()), style_.getBorderSize()));
        }
        String title_ = _metaComponent.getTitle();
        if (!title_.isEmpty()) {
            _component.setToolTipText(title_);
        }
    }
    public CustList<DualComponent> getChildren() {
        return children;
    }

    public DualContainer getContainer() {
        return container;
    }

    public int getComponentCount() {
        CustComponent g_ = getGraphic();
        if (g_ instanceof Panel) {
            return ((Panel)g_).getComponentCount();
        }
        return 0;
    }

    public CustComponent getParent() {
        return getGraphic().getParent();
    }
    public CustComponent getComponent(int _index) {
        CustComponent g_ = getGraphic();
        if (g_ instanceof Panel) {
            return ((Panel)g_).getComponent(_index);
        }
        return null;
    }

    public void add(DualComponent _dual) {
        page.getRefs().put(_dual.getComponent(), _dual);
        children.add(_dual);
        CustComponent g_ = getGraphic();
        if (g_ instanceof Panel) {
            ((Panel)g_).add(_dual.getGraphic());
        }
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

    public abstract CustComponent getGraphic();

    public RenderedPage getPage() {
        return page;
    }

}
