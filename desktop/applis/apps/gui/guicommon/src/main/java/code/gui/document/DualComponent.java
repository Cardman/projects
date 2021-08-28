package code.gui.document;



import code.formathtml.render.BorderEnum;
import code.formathtml.render.IntComponent;
import code.formathtml.render.MetaComponent;
import code.formathtml.render.MetaStyle;
import code.gui.AbsCustComponent;
import code.gui.AbsPanel;
import code.gui.GuiConstants;
import code.util.CustList;

public abstract class DualComponent implements IntComponent {

    private final DualContainer container;
    private final CustList<DualComponent> children = new CustList<DualComponent>();
    private final MetaComponent component;
    private DualComponent nextSibling;

    private final RenderedPage page;

    public DualComponent(DualContainer _container, MetaComponent _component, RenderedPage _page) {
        container = _container;
        component = _component;
        page = _page;
    }

    protected static void updateGraphics(AbsCustComponent _component, MetaComponent _metaComponent) {
        MetaStyle style_ = _metaComponent.getStyle();
        if (style_.getBorder() == BorderEnum.SOLID) {
            _component.setLineBorder(GuiConstants.newColor(style_.getBorderColor()), style_.getBorderSize());
        }
        String title_ = _metaComponent.getTitle();
        if (!title_.isEmpty()) {
            _component.setToolTipText(title_);
        }
    }

    @Override
    public IntComponent getFirstChildCompo() {
        if (children.isEmpty()) {
            return null;
        }
        return children.first();
    }

    @Override
    public IntComponent getNextSibling() {
        return nextSibling;
    }

    public void setNextSibling(DualComponent _nextSibling) {
        nextSibling = _nextSibling;
    }

    public CustList<DualComponent> getChildren() {
        return children;
    }

    public DualContainer getContainer() {
        return container;
    }

    public int getComponentCount() {
        AbsCustComponent g_ = getGraphic();
        if (g_ instanceof AbsPanel) {
            return ((AbsPanel)g_).getComponentCount();
        }
        return 0;
    }

    @Override
    public IntComponent getParentCompo() {
        return container;
    }

    public AbsCustComponent getParent() {
        return getGraphic().getParent();
    }
    public AbsCustComponent getComponent(int _index) {
        AbsCustComponent g_ = getGraphic();
        if (g_ instanceof AbsPanel) {
            return ((AbsPanel)g_).getComponent(_index);
        }
        return null;
    }

    public void add(DualComponent _dual) {
        page.getRefs().put(_dual.getComponent(), _dual);
        if (!children.isEmpty()) {
            children.last().setNextSibling(_dual);
        }
        children.add(_dual);
        AbsCustComponent g_ = getGraphic();
        if (g_ instanceof AbsPanel) {
            ((AbsPanel)g_).add(_dual.getGraphic());
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

    public abstract AbsCustComponent getGraphic();

    public RenderedPage getPage() {
        return page;
    }

}
