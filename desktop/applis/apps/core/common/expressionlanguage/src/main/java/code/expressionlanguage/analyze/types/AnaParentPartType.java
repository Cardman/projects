package code.expressionlanguage.analyze.types;

import code.maths.litteralcom.StrTypes;
import code.util.CustList;

abstract class AnaParentPartType extends AnaPartType {
    private final CustList<String> errsList = new CustList<String>();
    private final StrTypes strTypes = new StrTypes();
    private AnaPartType firstChild;
    private final StrTypes operators;
    AnaParentPartType(AnaParentPartType _parent, int _index, int _indexInType, StrTypes _operators) {
        super(_parent, _index, _indexInType);
        operators = _operators;
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

    abstract int buildErrorInexistBegin();
    abstract int buildErrorInexistEnd();

    int getFullBegin(int _index){
        return getFull()+getOperators().getKey(_index);
    }

    int getFullBegin(){
        return getFull()+getOperators().lastKey();
    }
    int getOpLen(int _index){
        return getOperators().getValue(_index).length();
    }
    int getOpLen(){
        return getOperators().lastValue().length();
    }

    @Override
    final AnaPartType getFirstChild() {
        return firstChild;
    }

    StrTypes getOperators() {
        return operators;
    }

    StrTypes getStrTypes() {
        return strTypes;
    }

    CustList<String> getErrsList() {
        return errsList;
    }
}
