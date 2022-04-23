package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.maths.litteralcom.StrTypes;
import code.util.core.StringUtil;

final class AnaArraryPartType extends AnaParentPartType {
    AnaArraryPartType(AnaParentPartType _parent, int _index, int _indexInType, StrTypes _operators, AnalysisMessages _messages, StrTypes _values) {
        super(_parent, _index, _indexInType,_operators, _messages,_values);
    }

    String getBegin() {
        return AnaTemplates.ARR_BEG_STRING;
    }

    @Override
    void analyze(String _globalType, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page, int _loc) {
        anaArray(_loc);
    }

    @Override
    void analyzeLine(ReadyTypes _ready, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page, int _loc) {
        anaArray(_loc);
    }

    @Override
    void analyzeAccessibleId(AccessedBlock _rooted, AnalyzedPageEl _page, int _loc) {
        anaArray(_loc);
    }

    private void anaArray(int _page) {
        setLoc(_page);
        String ch_ = getFirstChild().getAnalyzedType();
        if (ch_.isEmpty()) {
            return;
        }
        ch_ = StringUtil.concat(getBegin(),ch_);
        setAnalyzedType(ch_);
    }

    @Override
    int buildErrorInexistBegin() {
        return getFullBegin(0);
    }

    @Override
    int buildErrorInexistEnd() {
        return getOpLen(0);
    }
}
