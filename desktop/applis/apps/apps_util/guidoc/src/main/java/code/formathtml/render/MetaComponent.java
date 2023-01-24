package code.formathtml.render;


public abstract class MetaComponent implements IntComponent {

    protected static final String EMPTY_STRING = "";
    private final String title;

    private final MetaContainer parent;
    private MetaStyle style = new MetaStyle();

    private IntComponent nexSibling;
    protected MetaComponent(MetaContainer _parent) {
        parent = _parent;
        title = EMPTY_STRING;
    }

    protected MetaComponent(MetaContainer _parent, String _title) {
        parent = _parent;
        title = _title;
    }

    public MetaStyle getStyle() {
        return style;
    }

    public void setStyle(MetaStyle _style) {
        style = _style;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public IntComponent getParentCompo() {
        return getParent();
    }

    public final MetaContainer getParent() {
        return parent;
    }

    @Override
    public IntComponent getFirstChildCompo() {
        return getFirstChild();
    }

    @Override
    public IntComponent getNextSibling() {
        return nexSibling;
    }

    public void setNexSibling(IntComponent _nexSibling) {
        nexSibling = _nexSibling;
    }

    public abstract MetaComponent getFirstChild();
    public abstract MetaComponent getLastChild();

}
