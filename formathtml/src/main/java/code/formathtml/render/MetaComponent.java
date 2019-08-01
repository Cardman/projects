package code.formathtml.render;


public abstract class MetaComponent {

    protected static final String EMPTY_STRING = "";
    private final String title;

    private final MetaContainer parent;
    private MetaStyle style = new MetaStyle();

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

    public final MetaContainer getParent() {
        return parent;
    }

    public abstract MetaComponent getFirstChild();
    public abstract MetaComponent getLastChild();

}
