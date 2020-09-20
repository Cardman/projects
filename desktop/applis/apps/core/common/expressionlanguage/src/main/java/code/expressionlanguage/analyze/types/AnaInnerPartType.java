package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.util.CustList;
import code.util.IntTreeMap;

final class AnaInnerPartType extends AnaBinaryType {

    AnaInnerPartType(AnaParentPartType _parent, int _index, int _indexInType, IntTreeMap<String> _operators) {
        super(_parent, _index, _indexInType,_operators);
    }

    @Override
    void analyze(CustList<IntTreeMap<String>> _dels, String _globalType, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page) {
        analyzeType();
    }

    @Override
    void analyzeLine(ReadyTypes _ready, CustList<IntTreeMap<String>> _dels, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page) {
        analyzeType();
    }

    @Override
    void analyzeAccessibleId(CustList<IntTreeMap<String>> _dels, AccessedBlock _rooted, AnalyzedPageEl _page) {
        analyzeType();
    }

    private void analyzeType() {
        CustList<AnaPartType> ch_ = new CustList<AnaPartType>();
        AnaPartType f_ = getFirstChild();
        while (f_ != null) {
            ch_.add(f_);
            f_ = f_.getNextSibling();
        }
        String t_ = ch_.last().getAnalyzedType();
        if (t_.isEmpty()){
            setAlreadyError();
        }
        setAnalyzedType(t_);
    }

    @Override
    void buildErrorInexist(AnalyzedPageEl _page) {
        int begin_ = _page.getLocalInType() + getIndexInType() + getOperators().lastKey();
        int len_ = getOperators().lastValue().length();
        buildOffsetPart(begin_,len_);
    }
}
