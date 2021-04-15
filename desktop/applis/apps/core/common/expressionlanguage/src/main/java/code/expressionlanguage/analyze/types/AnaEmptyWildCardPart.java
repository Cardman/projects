package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.inherits.Templates;

final class AnaEmptyWildCardPart extends AnaLeafPartType {

    AnaEmptyWildCardPart(AnaParentPartType _parent, int _index, int _indexInType, String _type, String _previousSeparator) {
        super(_parent, _index, _indexInType, _type, _previousSeparator);
    }

    @Override
    void analyze(String _globalType, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page) {
        anaEmpty();
    }

    @Override
    void analyzeLine(ReadyTypes _ready, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page) {
        anaEmpty();
    }

    @Override
    void analyzeAccessibleId(AccessedBlock _rooted, AnalyzedPageEl _page) {
        anaEmpty();
    }

    private void anaEmpty() {
        if (!(getParent() instanceof AnaTemplatePartType)) {
            return;
        }
        setAnalyzedType(Templates.SUB_TYPE);
    }

}
