package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.common.StringExpUtil;
import code.maths.litteralcom.StrTypes;

abstract class AnaPrefPartType extends AnaParentPartType {

    private final String prefix;
    AnaPrefPartType(AnaParentPartType _parent, int _index, int _indexInType, String _prefix, StrTypes _operators) {
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
            return;
        }
        if (!(getParent() instanceof AnaTemplatePartType)) {
            return;
        }
        AnaPartType prev_ = getParent().getFirstChild();
        String base_ = prev_.getAnalyzedType();
        base_ = StringExpUtil.getIdFromAllTypes(base_);
        anaWildCommon(ch_,base_,_page);
    }
    abstract void anaWildCommon(String _ch,String _base, AnalyzedPageEl _page);

    @Override
    int buildErrorInexistBegin() {
        return getFullBegin(0);
    }

    @Override
    int buildErrorInexistEnd() {
        return getOpLen(0);
    }

}
