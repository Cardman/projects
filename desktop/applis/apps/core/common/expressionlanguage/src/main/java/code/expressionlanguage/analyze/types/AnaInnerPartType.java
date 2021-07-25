package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;

final class AnaInnerPartType extends AnaBinaryType {

    AnaInnerPartType(AnaParentPartType _parent, int _index, int _indexInType, StrTypes _operators) {
        super(_parent, _index, _indexInType,_operators);
    }

    @Override
    void analyze(String _globalType, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page, int _loc) {
        analyzeType(_loc);
    }

    @Override
    void analyzeLine(ReadyTypes _ready, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page, int _loc) {
        analyzeType(_loc);
    }

    @Override
    void analyzeAccessibleId(AccessedBlock _rooted, AnalyzedPageEl _page, int _loc) {
        analyzeType(_loc);
    }

    private void analyzeType(int _page) {
        setLoc(_page);
        CustList<AnaPartType> ch_ = new CustList<AnaPartType>();
        AnaPartType f_ = getFirstChild();
        while (f_ != null) {
            ch_.add(f_);
            f_ = f_.getNextSibling();
        }
        String t_ = ch_.last().getAnalyzedType();
        setAnalyzedType(t_);
    }

    @Override
    int buildErrorInexistBegin() {
        return getFullBegin();
    }

    @Override
    int buildErrorInexistEnd() {
        return getOpLen();
    }
}
