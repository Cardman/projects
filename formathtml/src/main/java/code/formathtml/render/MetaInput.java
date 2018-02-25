package code.formathtml.render;

public abstract class MetaInput extends MetaLeaf {

    private final int group;

    public MetaInput(MetaContainer _parent, int _group) {
        super(_parent);
        group = _group;
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
