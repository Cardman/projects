package code.expressionlanguage.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.util.CustList;
import code.util.*;
import code.util.StringList;

final class WildCardPartType extends ParentPartType {

    private String prefix;
    WildCardPartType(ParentPartType _parent, int _index, int _indexInType, String _prefix) {
        super(_parent, _index, _indexInType);
        prefix = _prefix;
    }

    @Override
    String getBegin() {
        return prefix;
    }

    @Override
    String getPrettyBegin() {
        return prefix;
    }

    @Override
    String getPrettyEnd() {
        return EMPTY_STRING;
    }
    @Override
    String getEnd() {
        return EMPTY_STRING;
    }

    @Override
    boolean analyzeTree(ContextEl _an, CustList<IntTreeMap< String>> _dels) {
        if (!(getParent() instanceof TemplatePartType)) {
            return false;
        }
        PartType prev_ = getParent().getFirstChild();
        String base_ = ((NamePartType)prev_).getTypeName();
        if (StringList.quickEq(base_.trim(), _an.getStandards().getAliasFct())) {
            return false;
        }
        return true;
    }

    @Override
    void analyze(ContextEl _an, CustList<IntTreeMap< String>> _dels, String _globalType, AccessingImportingBlock _local,AccessingImportingBlock _rooted) {
        analyzeLine(_an,null,_dels,_globalType,_local,_rooted);
    }

    @Override
    void analyzeTemplate(ContextEl _an, CustList<IntTreeMap<String>> _dels, StringMap<StringList> _inherit) {
        String ch_ = getFirstChild().getAnalyzedType();
        ch_ = StringList.concat(getBegin(),ch_);
        setAnalyzedType(ch_);
    }

    @Override
    void analyzeLine(ContextEl _an, ReadyTypes _ready,CustList<IntTreeMap< String>> _dels, String _globalType, AccessingImportingBlock _local,AccessingImportingBlock _rooted) {
        String ch_ = getFirstChild().getAnalyzedType();
        if (!(getParent() instanceof TemplatePartType)) {
            return;
        }
        PartType prev_ = getParent().getFirstChild();
        String base_ = prev_.getAnalyzedType();
        base_ = Templates.getIdFromAllTypes(base_);
        if (StringList.quickEq(base_.trim(), _an.getStandards().getAliasFct())) {
            return;
        }
        ch_ = StringList.concat(getBegin(),ch_);
        setAnalyzedType(ch_);
    }

    @Override
    void analyzeAccessibleId(ContextEl _an,
            CustList<IntTreeMap< String>> _dels,
                             AccessingImportingBlock _rooted) {
        String ch_ = getFirstChild().getAnalyzedType();
        if (!(getParent() instanceof TemplatePartType)) {
            return;
        }
        PartType prev_ = getParent().getFirstChild();
        String base_ = prev_.getAnalyzedType();
        base_ = Templates.getIdFromAllTypes(base_);
        if (StringList.quickEq(base_.trim(), _an.getStandards().getAliasFct())) {
            return;
        }
        ch_ = StringList.concat(getBegin(),ch_);
        setAnalyzedType(ch_);
    }
}
