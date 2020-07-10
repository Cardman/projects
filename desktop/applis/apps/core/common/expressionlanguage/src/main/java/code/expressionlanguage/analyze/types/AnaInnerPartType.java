package code.expressionlanguage.analyze.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.util.CustList;
import code.util.IntTreeMap;

final class AnaInnerPartType extends AnaBinaryType {

    AnaInnerPartType(AnaParentPartType _parent, int _index, int _indexInType, IntTreeMap<String> _operators) {
        super(_parent, _index, _indexInType,_operators);
    }

    @Override
    void analyze(ContextEl _an, CustList<IntTreeMap<String>> _dels, String _globalType, AccessedBlock _local, AccessedBlock _rooted) {
        analyzeType();
    }

    @Override
    void analyzeLine(ContextEl _an, ReadyTypes _ready, CustList<IntTreeMap<String>> _dels, AccessedBlock _local, AccessedBlock _rooted) {
        analyzeType();
    }

    @Override
    void analyzeAccessibleId(ContextEl _an, CustList<IntTreeMap<String>> _dels, AccessedBlock _rooted) {
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
    void buildErrorInexist(ContextEl _an) {
        int begin_ = _an.getAnalyzing().getLocalInType() + getIndexInType() + getOperators().lastKey();
        int len_ = getOperators().lastValue().length();
        buildOffsetPart(begin_,len_);
    }
}
