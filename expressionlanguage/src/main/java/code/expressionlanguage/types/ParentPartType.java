package code.expressionlanguage.types;

import code.expressionlanguage.ExecutableCode;
import code.util.CustList;
import code.util.*;

abstract class ParentPartType extends PartType {

    private PartType firstChild;
    ParentPartType(ParentPartType _parent, int _index, int _indexInType) {
        super(_parent, _index, _indexInType);
    }
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

    abstract boolean analyzeTree(ExecutableCode _an, CustList<IntTreeMap<String>> _dels);
}
