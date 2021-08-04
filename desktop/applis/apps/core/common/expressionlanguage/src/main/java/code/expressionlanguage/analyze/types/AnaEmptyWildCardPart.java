package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.common.StringExpUtil;

final class AnaEmptyWildCardPart extends AnaLeafPartType {

    AnaEmptyWildCardPart(AnaParentPartType _parent, int _index, int _indexInType, String _type, String _previousSeparator, AnalysisMessages _messages) {
        super(_parent, _index, _indexInType, _type, _previousSeparator, _messages);
    }

    @Override
    void analyze(String _globalType, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page, int _loc) {
        anaEmpty(_loc);
    }

    @Override
    void analyzeLine(ReadyTypes _ready, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page, int _loc) {
        anaEmpty(_loc);
    }

    @Override
    void analyzeAccessibleId(AccessedBlock _rooted, AnalyzedPageEl _page, int _loc) {
        anaEmpty(_loc);
    }

    private void anaEmpty(int _page) {
        setLoc(_page);
        if (!(getParent() instanceof AnaTemplatePartType)) {
            return;
        }
        setAnalyzedType(StringExpUtil.SUB_TYPE);
    }

}
