package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.RootBlock;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;

final class ArraryPartType extends ParentPartType {

    ArraryPartType(ParentPartType _parent, int _index, int _indexInType) {
        super(_parent, _index, _indexInType);
    }

    @Override
    String getPrettyBegin() {
        return EMPTY_STRING;
    }

    @Override
    String getBegin() {
        return Templates.ARR_BEG_STRING;
    }

    @Override
    String getPrettyEnd() {
        return "[]";
    }
    @Override
    String getEnd() {
        return EMPTY_STRING;
    }

    @Override
    boolean analyzeTree(ExecutableCode _an, CustList<NatTreeMap<Integer, String>> dels_) {
        return true;
    }

    @Override
    void analyzeDepends(Analyzable _an,
            int _index, CustList<NatTreeMap<Integer, String>> _dels,
            RootBlock _rooted, boolean _exact) {
        String ch_ = getFirstChild().getAnalyzedType();
        ch_ = StringList.concat(getBegin(),ch_);
        setAnalyzedType(ch_);
        StringList ts_ = getFirstChild().getTypeNames();
        getTypeNames().addAllElts(ts_);
    }

    @Override
    void analyzeInherits(Analyzable _an, int _index,
            CustList<NatTreeMap<Integer, String>> _dels, String _globalType,
            RootBlock _rooted,
            boolean _protected) {
        String ch_ = getFirstChild().getAnalyzedType();
        ch_ = StringList.concat(getBegin(),ch_);
        setAnalyzedType(ch_);
    }
    @Override
    void analyze(Analyzable _an, CustList<NatTreeMap<Integer, String>>_dels, String _globalType, AccessingImportingBlock _rooted) {
        String ch_ = getFirstChild().getAnalyzedType();
        ch_ = StringList.concat(getBegin(),ch_);
        setAnalyzedType(ch_);
    }
    @Override
    void analyzeLine(Analyzable _an, CustList<NatTreeMap<Integer, String>>_dels, String _globalType, AccessingImportingBlock _rooted) {
        String ch_ = getFirstChild().getAnalyzedType();
        ch_ = StringList.concat(getBegin(),ch_);
        setAnalyzedType(ch_);
    }
    @Override
    void analyzeAccessibleId(Analyzable _an,
            CustList<NatTreeMap<Integer, String>> _dels,
            AccessingImportingBlock _rooted) {
        String ch_ = getFirstChild().getAnalyzedType();
        ch_ = StringList.concat(getBegin(),ch_);
        setAnalyzedType(ch_);
    }
}
