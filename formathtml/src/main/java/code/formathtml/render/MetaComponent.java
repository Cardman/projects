package code.formathtml.render;

import code.util.CustList;

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

    public final MetaComponent getNextSibling() {
        if (parent == null) {
            //root
            return null;
        }
        CustList<MetaComponent> children_ = parent.getChildren();
        int len_ = children_.size();
        int index_ = len_;
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (children_.get(i) == this) {
                index_ = i;
                break;
            }
        }
        if (index_ + 1 < len_) {
            return children_.get(index_ + 1);
        }
        return null;
    }
}
