package code.formathtml.render;

public abstract class MetaInput extends MetaLeaf implements IntInput {

    private final int group;

    public MetaInput(MetaContainer _parent, int _group) {
        super(_parent);
        group = _group;
    }

    @Override
    public int getGroup() {
        return group;
    }
}
