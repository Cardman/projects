package code.expressionlanguage.analyze.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.inherits.Templates;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.StringList;

final class AnaArraryPartType extends AnaParentPartType {
    AnaArraryPartType(AnaParentPartType _parent, int _index, int _indexInType, IntTreeMap<String> _operators) {
        super(_parent, _index, _indexInType,_operators);
    }

    String getBegin() {
        return AnaTemplates.ARR_BEG_STRING;
    }

    @Override
    void analyze(ContextEl _an, CustList<IntTreeMap<String>> _dels, String _globalType, AccessedBlock _local, AccessedBlock _rooted) {
        anaArray();
    }

    @Override
    void analyzeLine(ContextEl _an, ReadyTypes _ready, CustList<IntTreeMap<String>> _dels, AccessedBlock _local, AccessedBlock _rooted) {
        anaArray();
    }

    @Override
    void analyzeAccessibleId(ContextEl _an, CustList<IntTreeMap<String>> _dels, AccessedBlock _rooted) {
        anaArray();
    }

    private void anaArray() {
        String ch_ = getFirstChild().getAnalyzedType();
        if (ch_.isEmpty()) {
            setAlreadyError();
            return;
        }
        ch_ = StringList.concat(getBegin(),ch_);
        setAnalyzedType(ch_);
    }

    @Override
    void buildErrorInexist(ContextEl _an) {
        int begin_ = _an.getAnalyzing().getLocalInType() + getIndexInType() + getOperators().firstKey();
        int len_ = getOperators().firstValue().length();
        buildOffsetPart(begin_,len_);
    }
}
