package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.common.StrTypes;
import code.expressionlanguage.inherits.Templates;
import code.util.CustList;
import code.util.IntTreeMap;

final class AnaEmptyWildCardPart extends AnaLeafPartType {

    AnaEmptyWildCardPart(AnaParentPartType _parent, int _index, int _indexInType, String _type, String _previousSeparator) {
        super(_parent, _index, _indexInType, _type, _previousSeparator);
    }

    @Override
    void analyze(CustList<StrTypes> _dels, String _globalType, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page) {
        analyzeLine(null,_dels,_local,_rooted, null);
    }

    @Override
    void analyzeLine(ReadyTypes _ready, CustList<StrTypes> _dels, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page) {
        if (!(getParent() instanceof AnaTemplatePartType)) {
            return;
        }
        setAnalyzedType(Templates.SUB_TYPE);
    }

    @Override
    void analyzeAccessibleId(CustList<StrTypes> _dels, AccessedBlock _rooted, AnalyzedPageEl _page) {
        if (!(getParent() instanceof AnaTemplatePartType)) {
            return;
        }
        setAnalyzedType(Templates.SUB_TYPE);
    }

}
