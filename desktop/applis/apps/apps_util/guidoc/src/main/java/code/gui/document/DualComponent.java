package code.gui.document;



import code.formathtml.render.BorderEnum;
import code.formathtml.render.IntComponent;
import code.formathtml.render.MetaComponent;
import code.formathtml.render.MetaStyle;
import code.gui.AbsCustComponent;
import code.gui.GuiConstants;
import code.util.CustList;

public abstract class DualComponent implements IntComponent {

    private final DualContainer container;
    private final CustList<DualComponent> children = new CustList<DualComponent>();
    private final MetaComponent component;
    private DualComponent nextSibling;

    private final RenderedPage page;

    protected DualComponent(DualContainer _container, MetaComponent _component, RenderedPage _page) {
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

    @Override
    public IntComponent getParentCompo() {
        return getContainer();
    }

    protected static void postAdd(DualComponent _dual) {
        if (_dual instanceof DualLabel) {
            ((DualLabel) _dual).backAndFront();
            paintLabel((DualLabel) _dual);
            return;
        }
        if (_dual instanceof DualButton) {
            ((DualButton) _dual).paint();
            return;
        }
        if (_dual instanceof DualImage) {
            ((DualImage) _dual).paint();
        }
    }

    public static void paintLabel(DualLabel _dual) {
        if (_dual instanceof DualIndentLabel) {
            return;
        }
        _dual.paint();
    }

    public MetaComponent getComponent() {
        return component;
    }

    public abstract AbsCustComponent getGraphic();

    public RenderedPage getPage() {
        return page;
    }

}
