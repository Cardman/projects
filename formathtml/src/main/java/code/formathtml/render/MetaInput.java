package code.formathtml.render;

public abstract class MetaInput extends MetaLeaf {

    private final int group;

    private final String name;

    public MetaInput(MetaContainer _parent, int _group, String _name) {
        super(_parent);
        group = _group;
        name = _name;
    }

    public String getName() {
        return name;
    }

    public MetaForm getParentForm() {
        MetaContainer par_ = getParent();
        while (par_ != null) {
            if (par_ instanceof MetaForm) {
                break;
            }
            par_ = par_.getParent();
        }
        return (MetaForm) par_;
    }

    public int getGroup() {
        return group;
    }
}
