package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.RootBlock;
import code.sml.RowCol;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;

final class InnerPartType extends ParentPartType {

    private boolean removedBefore;

    public InnerPartType(ParentPartType _parent, int _index, int _indexInType, boolean _removedBefore) {
        super(_parent, _index, _indexInType);
        removedBefore = _removedBefore;
    }

    @Override
    public String getBegin() {
        return EMPTY_STRING;
    }

    @Override
    public String getSeparator(int _index) {
        return Templates.INNER_TYPE;
    }

    @Override
    public void analyzeDepends(Analyzable _an,
            CustList<NatTreeMap<Integer, String>> _dels,
            RootBlock _rooted, boolean _exact, RowCol _location) {
        CustList<PartType> ch_ = new CustList<PartType>();
        PartType f_ = getFirstChild();
        while (f_ != null) {
            ch_.add(f_);
            f_ = f_.getNextSibling();
        }
        StringList types_ = getTypeNames();
        int len_ = ch_.size();
        for (int i = 0; i < len_; i++) {
            types_.addAllElts(ch_.get(i).getTypeNames());
        }
        String t_ = ch_.last().getAnalyzedType();
        setAnalyzedType(t_);
    }
    @Override
    public void analyze(Analyzable _an, CustList<NatTreeMap<Integer, String>> _dels, String _globalType, AccessingImportingBlock _rooted,
            boolean _exact, boolean _protected, RowCol _location) {
        CustList<PartType> ch_ = new CustList<PartType>();
        PartType f_ = getFirstChild();
        while (f_ != null) {
            ch_.add(f_);
            f_ = f_.getNextSibling();
        }
        String t_ = ch_.last().getAnalyzedType();
        setAnalyzedType(t_);
    }
    @Override
    public void analyze(Analyzable _an, CustList<NatTreeMap<Integer, String>>_dels, String _globalType, AccessingImportingBlock _rooted,
            boolean _exact) {
        CustList<PartType> ch_ = new CustList<PartType>();
        PartType f_ = getFirstChild();
        while (f_ != null) {
            ch_.add(f_);
            f_ = f_.getNextSibling();
        }
        String t_ = ch_.last().getAnalyzedType();
        setAnalyzedType(t_);
    }
    @Override
    public String getEnd() {
        return EMPTY_STRING;
    }

    public boolean isRemovedBefore() {
        return removedBefore;
    }
}
