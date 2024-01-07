package code.formathtml.render;


public final class MetaIndentNbLabel extends MetaIndent {

    private MetaOrderedList ordered;
    public MetaIndentNbLabel(MetaContainer _parent) {
        super(_parent);
    }

    public MetaOrderedList getOrdered() {
        return ordered;
    }

    public void setOrdered(MetaOrderedList _o) {
        this.ordered = _o;
    }
}
