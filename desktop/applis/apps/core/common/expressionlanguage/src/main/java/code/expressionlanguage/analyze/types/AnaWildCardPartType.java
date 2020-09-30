package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.StringList;

final class AnaWildCardPartType extends AnaParentPartType {

    private String prefix;
    AnaWildCardPartType(AnaParentPartType _parent, int _index, int _indexInType, String _prefix, IntTreeMap<String> _operators) {
        super(_parent, _index, _indexInType,_operators);
        prefix = _prefix;
    }
    String getBegin() {
        return prefix;
    }

    @Override
    void analyze(CustList<IntTreeMap<String>> _dels, String _globalType, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page) {
        analyzeLine(null,_dels,_local,_rooted, _page);
    }

    @Override
    void analyzeLine(ReadyTypes _ready, CustList<IntTreeMap<String>> _dels, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page) {
        anaWild(_page);
    }

    @Override
    void analyzeAccessibleId(CustList<IntTreeMap<String>> _dels, AccessedBlock _rooted, AnalyzedPageEl _page) {
        anaWild(_page);
    }

    private void anaWild(AnalyzedPageEl _page) {
        String ch_ = getFirstChild().getAnalyzedType();
        if (ch_.isEmpty()) {
            setAlreadyError();
            return;
        }
        if (!(getParent() instanceof AnaTemplatePartType)) {
            return;
        }
        AnaPartType prev_ = getParent().getFirstChild();
        String base_ = prev_.getAnalyzedType();
        base_ = StringExpUtil.getIdFromAllTypes(base_);
        if (StringList.quickEq(base_.trim(), _page.getAliasFct())) {
            return;
        }
        ch_ = StringList.concat(getBegin(),ch_);
        setAnalyzedType(ch_);
    }

    @Override
    void buildErrorInexist(AnalyzedPageEl _page) {
        int begin_ = _page.getLocalInType() + getIndexInType() + getOperators().firstKey();
        int len_ = getOperators().firstValue().length();
        buildOffsetPart(begin_,len_);
    }
}
