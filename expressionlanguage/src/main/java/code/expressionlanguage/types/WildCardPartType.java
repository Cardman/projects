package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.RootBlock;
import code.sml.RowCol;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;

final class WildCardPartType extends ParentPartType {

    private String prefix;
    public WildCardPartType(ParentPartType _parent, int _index, int _indexInType, String _prefix) {
        super(_parent, _index, _indexInType);
        prefix = _prefix;
    }

    @Override
    public String getBegin() {
        return prefix;
    }

    @Override
    public String getSeparator(int _index) {
        return EMPTY_STRING;
    }

    @Override
    public String getEnd() {
        return EMPTY_STRING;
    }
    @Override
    public void analyzeDepends(Analyzable _an,
            CustList<NatTreeMap<Integer, String>> _dels,
            RootBlock _rooted, boolean _exact, RowCol _location) {
        if (!(getParent() instanceof TemplatePartType)) {
            stopDepends();
            return;
        }
        String ch_ = getFirstChild().getAnalyzedType();
        ch_ = StringList.concat(getBegin(),ch_);
        setAnalyzedType(ch_);
        StringList ts_ = getFirstChild().getTypeNames();
        getTypeNames().addAllElts(ts_);
    }
    @Override
    public void analyze(Analyzable _an, CustList<NatTreeMap<Integer, String>> _dels, String _globalType, AccessingImportingBlock _rooted,
            boolean _exact, RowCol _location) {
        String ch_ = getFirstChild().getAnalyzedType();
        if (!(getParent() instanceof TemplatePartType)) {
            return;
        }
        ch_ = StringList.concat(getBegin(),ch_);
        setAnalyzedType(ch_);
    }

    @Override
    public void analyze(Analyzable _an, CustList<NatTreeMap<Integer, String>>_dels, String _globalType, AccessingImportingBlock _rooted,
            boolean _exact) {
        if (!(getParent() instanceof TemplatePartType)) {
            return;
        }
        String ch_ = getFirstChild().getAnalyzedType();
        ch_ = StringList.concat(getBegin(),ch_);
        setAnalyzedType(ch_);
    }
}
