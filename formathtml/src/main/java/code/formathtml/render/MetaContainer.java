package code.formathtml.render;

import code.util.CustList;

public abstract class MetaContainer extends MetaComponent {

    private CustList<MetaComponent> children = new CustList<MetaComponent>();

    private final MetaLayout layout;

    public MetaContainer(MetaContainer _parent, MetaLayout _layout) {
        super(_parent);
        layout = _layout;
    }

    public final void appendChild(MetaComponent _child) {
        children.add(_child);
    }

    public MetaLayout getLayout() {
        return layout;
    }

    public final CustList<MetaComponent> getChildren() {
        return children;
    }

    @Override
    public final MetaComponent getFirstChild() {
        if (children.isEmpty()) {
            return null;
        }
        return children.first();
    }
    @Override
    public MetaComponent getLastChild() {
        if (children.isEmpty()) {
            return null;
        }
        return children.last();
    }
}
