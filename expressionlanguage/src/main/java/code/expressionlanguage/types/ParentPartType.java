package code.expressionlanguage.types;

import code.expressionlanguage.ExecutableCode;
import code.util.CustList;
import code.util.NatTreeMap;

abstract class ParentPartType extends PartType {

    ParentPartType(ParentPartType _parent, int _index, int _indexInType) {
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
    abstract String getPrettyBegin();
    abstract String getPrettyEnd();
    abstract String getBegin();

    abstract String getEnd();
    @Override
    final PartType getFirstChild() {
        return firstChild;
    }

    abstract boolean analyzeTree(ExecutableCode an, CustList<NatTreeMap<Integer,String>> dels_);
}
