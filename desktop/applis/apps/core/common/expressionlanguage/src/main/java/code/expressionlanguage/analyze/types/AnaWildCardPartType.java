package code.expressionlanguage.analyze.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecAccessingImportingBlock;
import code.expressionlanguage.inherits.Templates;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.StringList;
import code.util.StringMap;

final class AnaWildCardPartType extends AnaParentPartType {

    private String prefix;
    AnaWildCardPartType(AnaParentPartType _parent, int _index, int _indexInType, String _prefix) {
        super(_parent, _index, _indexInType);
        prefix = _prefix;
    }
    String getBegin() {
        return prefix;
    }

    @Override
    void analyze(ContextEl _an, CustList<IntTreeMap<String>> _dels, String _globalType, ExecAccessingImportingBlock _local, ExecAccessingImportingBlock _rooted) {
        analyzeLine(_an,null,_dels,_local,_rooted);
    }

    @Override
    void analyzeLine(ContextEl _an, ReadyTypes _ready, CustList<IntTreeMap<String>> _dels, ExecAccessingImportingBlock _local, ExecAccessingImportingBlock _rooted) {
        String ch_ = getFirstChild().getAnalyzedType();
        if (!(getParent() instanceof AnaTemplatePartType)) {
            return;
        }
        AnaPartType prev_ = getParent().getFirstChild();
        String base_ = prev_.getAnalyzedType();
        base_ = StringExpUtil.getIdFromAllTypes(base_);
        if (StringList.quickEq(base_.trim(), _an.getStandards().getAliasFct())) {
            return;
        }
        ch_ = StringList.concat(getBegin(),ch_);
        setAnalyzedType(ch_);
    }

    @Override
    void analyzeAccessibleId(ContextEl _an, CustList<IntTreeMap<String>> _dels, ExecAccessingImportingBlock _rooted) {
        String ch_ = getFirstChild().getAnalyzedType();
        if (!(getParent() instanceof AnaTemplatePartType)) {
            return;
        }
        AnaPartType prev_ = getParent().getFirstChild();
        String base_ = prev_.getAnalyzedType();
        base_ = StringExpUtil.getIdFromAllTypes(base_);
        if (StringList.quickEq(base_.trim(), _an.getStandards().getAliasFct())) {
            return;
        }
        ch_ = StringList.concat(getBegin(),ch_);
        setAnalyzedType(ch_);
    }

    @Override
    void analyzeTemplate(ContextEl _an, CustList<IntTreeMap<String>> _dels, StringMap<StringList> _inherit) {
        String ch_ = getFirstChild().getAnalyzedType();
        ch_ = StringList.concat(getBegin(),ch_);
        setAnalyzedType(ch_);
    }
}
