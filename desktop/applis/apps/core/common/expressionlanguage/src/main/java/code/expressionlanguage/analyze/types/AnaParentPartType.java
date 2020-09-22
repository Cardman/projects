package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.StringList;

abstract class AnaParentPartType extends AnaPartType {
    private CustList<PartOffset> beginOps = new CustList<PartOffset>();
    private CustList<PartOffset> endOps = new CustList<PartOffset>();
    private CustList<StringList> errsList = new CustList<StringList>();
    private AnaPartType firstChild;
    private IntTreeMap<String> operators;
    AnaParentPartType(AnaParentPartType _parent, int _index, int _indexInType, IntTreeMap<String> _operators) {
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

    IntTreeMap<String> getOperators() {
        return operators;
    }

    CustList<StringList> getErrsList() {
        return errsList;
    }
}
