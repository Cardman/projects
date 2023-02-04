package code.formathtml.render;

public final class MetaListItem extends MetaContainer {

    private MetaOrderedList ordered;
    public MetaListItem(MetaContainer _parent) {
        super(_parent, MetaLayout.BOX);
    }

    public MetaOrderedList getOrdered() {
        return ordered;
    }

    public void setOrdered(MetaOrderedList _o) {
        this.ordered = _o;
    }
}
