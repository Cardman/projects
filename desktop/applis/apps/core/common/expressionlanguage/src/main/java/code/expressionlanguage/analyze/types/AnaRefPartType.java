package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.maths.litteralcom.StrTypes;
import code.expressionlanguage.common.StringExpUtil;
import code.util.core.StringUtil;

final class AnaRefPartType extends AnaParentPartType {

    private final String prefix;
    AnaRefPartType(AnaParentPartType _parent, int _index, int _indexInType, String _prefix, StrTypes _operators) {
        super(_parent, _index, _indexInType,_operators);
        prefix = _prefix;
    }
    String getBegin() {
        return prefix;
    }

    @Override
    void analyze(String _globalType, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page, int _loc) {
        anaWild(_page,_loc);
    }

    @Override
    void analyzeLine(ReadyTypes _ready, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page, int _loc) {
        anaWild(_page,_loc);
    }

    @Override
    void analyzeAccessibleId(AccessedBlock _rooted, AnalyzedPageEl _page, int _loc) {
        anaWild(_page,_loc);
    }

    private void anaWild(AnalyzedPageEl _page, int _loc) {
        setLoc(_loc);
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
        if (!StringUtil.quickEq(base_.trim(), _page.getAliasFct())) {
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
