package code.expressionlanguage.analyze.types;

abstract class AnaParentPartType extends AnaPartType {
    private AnaPartType firstChild;
    AnaParentPartType(AnaParentPartType _parent, int _index, int _indexInType) {
        super(_parent, _index, _indexInType);
    }
    void appendChild(AnaPartType _child) {
        if (firstChild == null) {
            firstChild = _child;
            return;
        }
        AnaPartType p_ = firstChild;
        while (p_.getNextSibling() != null) {
            p_ = p_.getNextSibling();
        }
        p_.setNextSibling(_child);
    }

    @Override
    final AnaPartType getFirstChild() {
        return firstChild;
    }
}
