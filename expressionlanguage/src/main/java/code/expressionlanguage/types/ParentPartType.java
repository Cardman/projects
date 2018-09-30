package code.expressionlanguage.types;

abstract class ParentPartType extends PartType {

    public ParentPartType(ParentPartType _parent, int _index, int _indexInType) {
        super(_parent, _index, _indexInType);
    }
    private PartType firstChild;
    void appendChild(PartType _child) {
        if (firstChild == null) {
            firstChild = _child;
            return;
        }
        PartType p_ = firstChild;
        while (p_.getNextSibling() != null) {
            p_ = p_.getNextSibling();
        }
        p_.setNextSibling(_child);
    }
    public abstract String getBegin();
    public abstract String getSeparator(int _index);
    public abstract String getEnd();
    @Override
    public final PartType getFirstChild() {
        return firstChild;
    }
}
