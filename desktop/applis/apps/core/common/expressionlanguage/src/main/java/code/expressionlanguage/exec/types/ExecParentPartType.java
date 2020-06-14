package code.expressionlanguage.exec.types;

import code.expressionlanguage.ContextEl;
import code.util.CustList;
import code.util.IntTreeMap;

abstract class ExecParentPartType extends ExecPartType {

    private ExecPartType firstChild;
    ExecParentPartType(ExecParentPartType _parent, int _index) {
        super(_parent, _index);
    }
    void appendChild(ExecPartType _child) {
        if (firstChild == null) {
            firstChild = _child;
            return;
        }
        ExecPartType p_ = firstChild;
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
    final ExecPartType getFirstChild() {
        return firstChild;
    }

    abstract boolean analyzeTree(ContextEl _an, CustList<IntTreeMap<String>> _dels);

}
