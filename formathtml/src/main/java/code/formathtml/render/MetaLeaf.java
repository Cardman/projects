package code.formathtml.render;

public abstract class MetaLeaf extends MetaComponent {

    protected MetaLeaf(MetaContainer _parent) {
        super(_parent);
    }

    protected MetaLeaf(MetaContainer _parent, String _title) {
        super(_parent, _title);
    }

    @Override
    public final MetaComponent getFirstChild() {
        return null;
    }
    @Override
    public MetaComponent getLastChild() {
        return null;
    }
}
