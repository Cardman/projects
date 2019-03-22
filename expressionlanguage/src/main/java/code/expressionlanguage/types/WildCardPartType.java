package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.RootBlock;
import code.util.CustList;
import code.util.NatTreeMap;
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
    boolean analyzeTree(ExecutableCode _an, CustList<NatTreeMap<Integer, String>> _dels) {
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
    void analyzeDepends(Analyzable _an,
            int _index, CustList<NatTreeMap<Integer, String>> _dels,
            RootBlock _rooted, boolean _exact) {
        if (!processOkInherits(_an)) {
            return;
        }
        StringList ts_ = getFirstChild().getTypeNames();
        getTypeNames().addAllElts(ts_);
    }
    @Override
    void analyzeInherits(Analyzable _an, int _index,
            CustList<NatTreeMap<Integer, String>> _dels, String _globalType,
            RootBlock _rooted,
            boolean _protected) {
        processOkInherits(_an);
    }
    private boolean processOkInherits(Analyzable _an) {
        if (!(getParent() instanceof TemplatePartType)) {
            _an.getCurrentBadIndexes().add(getIndexInType());
            return false;
        }
        PartType prev_ = getParent().getFirstChild();
        String base_ = prev_.getAnalyzedType();
        base_ = Templates.getIdFromAllTypes(base_);
        if (StringList.quickEq(base_.trim(), _an.getStandards().getAliasFct())) {
            _an.getCurrentBadIndexes().add(getIndexInType());
            return false;
        }
        String ch_ = getFirstChild().getAnalyzedType();
        ch_ = StringList.concat(getBegin(),ch_);
        setAnalyzedType(ch_);
        return true;
    }
    @Override
    void analyze(Analyzable _an, CustList<NatTreeMap<Integer, String>> _dels, String _globalType, AccessingImportingBlock _rooted) {
        analyzeLine(_an,_dels,_globalType,_rooted);
    }
    @Override
    void analyzeLine(Analyzable _an, CustList<NatTreeMap<Integer, String>> _dels, String _globalType, AccessingImportingBlock _rooted) {
        String ch_ = getFirstChild().getAnalyzedType();
        if (!(getParent() instanceof TemplatePartType)) {
            _an.getCurrentBadIndexes().add(getIndexInType());
            return;
        }
        PartType prev_ = getParent().getFirstChild();
        String base_ = prev_.getAnalyzedType();
        base_ = Templates.getIdFromAllTypes(base_);
        if (StringList.quickEq(base_.trim(), _an.getStandards().getAliasFct())) {
            _an.getCurrentBadIndexes().add(getIndexInType());
            return;
        }
        ch_ = StringList.concat(getBegin(),ch_);
        setAnalyzedType(ch_);
    }

    @Override
    void analyzeAccessibleId(Analyzable _an,
            CustList<NatTreeMap<Integer, String>> _dels,
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
