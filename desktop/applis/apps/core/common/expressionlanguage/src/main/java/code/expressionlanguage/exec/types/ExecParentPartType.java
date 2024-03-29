package code.expressionlanguage.exec.types;

import code.expressionlanguage.ContextEl;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;

abstract class ExecParentPartType extends ExecPartType {
    private final CustList<ExecPartType> children = new CustList<ExecPartType>();
    private final StrTypes strTypes;

    private ExecPartType firstChild;
    ExecParentPartType(ExecParentPartType _parent, int _index, String _previousOperator, StrTypes _valuesEx) {
        super(_parent, _index, _previousOperator);
        strTypes = _valuesEx;
    }
    void appendChild(ExecPartType _child) {
        children.add(_child);
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

    abstract boolean analyzeTree(ContextEl _an);

    StrTypes getStrTypes() {
        return strTypes;
    }

    CustList<ExecPartType> getChildren() {
        return children;
    }
}
