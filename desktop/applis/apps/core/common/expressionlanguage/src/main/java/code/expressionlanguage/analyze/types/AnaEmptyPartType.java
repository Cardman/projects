package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.util.CustList;
import code.util.IntTreeMap;

final class AnaEmptyPartType extends AnaLeafPartType {
    AnaEmptyPartType(AnaParentPartType _parent, int _index, int _indexInType, String _type, String _previousSeparator) {
        super(_parent, _index, _indexInType, _type, _previousSeparator);
    }

    @Override
    void analyze(CustList<IntTreeMap<String>> _dels, String _globalType, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page) {
    }

    @Override
    void analyzeLine(ReadyTypes _ready, CustList<IntTreeMap<String>> _dels, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page) {
       //
    }

    @Override
    void analyzeAccessibleId(CustList<IntTreeMap<String>> _dels, AccessedBlock _rooted, AnalyzedPageEl _page) {
        //
    }

}
