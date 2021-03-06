package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringList;

abstract class AnaParentPartType extends AnaPartType {
    private final CustList<PartOffset> beginOps = new CustList<PartOffset>();
    private final CustList<PartOffset> endOps = new CustList<PartOffset>();
    private final CustList<StringList> errsList = new CustList<StringList>();
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

    abstract void buildErrorInexist(AnalyzedPageEl _page);

    CustList<PartOffset> getBeginOps() {
        return beginOps;
    }

    CustList<PartOffset> getEndOps() {
        return endOps;
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

    CustList<StringList> getErrsList() {
        return errsList;
    }
}
