package code.formathtml.render;

import code.util.CustList;

public abstract class MetaContainer extends MetaComponent {

    private CustList<MetaComponent> children = new CustList<MetaComponent>();

    private final MetaLayout layout;

    private boolean addEmpty;

    public MetaContainer(MetaContainer _parent, MetaLayout _layout) {
        super(_parent);
        layout = _layout;
    }

    public boolean isAddEmpty() {
        return addEmpty;
    }

    public void setAddEmpty(boolean _addEmpty) {
        addEmpty = _addEmpty;
    }

    public final boolean containsOnlyEndLine() {
        if (children.size() != 1) {
            return false;
        }
        return children.first() instanceof MetaEndLine;
    }

    public final boolean onlyBlanks() {
        for (MetaComponent c: children) {
            if (c instanceof MetaIndent) {
                continue;
            }
            return false;
        }
        return true;
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
