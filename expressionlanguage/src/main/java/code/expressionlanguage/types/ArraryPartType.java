package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.sml.RowCol;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;

public final class ArraryPartType extends ParentPartType {

    public ArraryPartType(ParentPartType _parent, int _index, int _indexInType) {
        super(_parent, _index, _indexInType);
    }

    @Override
    public String getBegin() {
        return Templates.ARR_BEG_STRING;
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
    public void analyze(Analyzable _an, CustList<NatTreeMap<Integer, String>> _dels, String _globalType, AccessingImportingBlock _rooted,
            boolean _exact, RowCol _location) {
        String ch_ = getFirstChild().getAnalyzedType();
        ch_ = StringList.concat(getBegin(),ch_);
        setAnalyzedType(ch_);
    }

    @Override
    public void analyze(Analyzable _an, CustList<NatTreeMap<Integer, String>>_dels, String _globalType, AccessingImportingBlock _rooted,
            boolean _exact) {
        String ch_ = getFirstChild().getAnalyzedType();
        ch_ = StringList.concat(getBegin(),ch_);
        setAnalyzedType(ch_);
    }
}
