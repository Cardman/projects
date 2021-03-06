package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.maths.litteralcom.StrTypes;
import code.util.core.StringUtil;

final class AnaArraryPartType extends AnaParentPartType {
    AnaArraryPartType(AnaParentPartType _parent, int _index, int _indexInType, StrTypes _operators) {
        super(_parent, _index, _indexInType,_operators);
    }

    String getBegin() {
        return AnaTemplates.ARR_BEG_STRING;
    }

    @Override
    void analyze(String _globalType, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page) {
        anaArray();
    }

    @Override
    void analyzeLine(ReadyTypes _ready, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page) {
        anaArray();
    }

    @Override
    void analyzeAccessibleId(AccessedBlock _rooted, AnalyzedPageEl _page) {
        anaArray();
    }

    private void anaArray() {
        String ch_ = getFirstChild().getAnalyzedType();
        if (ch_.isEmpty()) {
            setAlreadyError();
            return;
        }
        ch_ = StringUtil.concat(getBegin(),ch_);
        setAnalyzedType(ch_);
    }

    @Override
    void buildErrorInexist(AnalyzedPageEl _page) {
        int begin_ = _page.getLocalInType() + getIndexInType() + getOperators().firstKey();
        int len_ = getOperators().firstValue().length();
        buildOffsetPart(begin_,len_);
    }
}
