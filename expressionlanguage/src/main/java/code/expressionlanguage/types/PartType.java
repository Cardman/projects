package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.options.Options;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;


abstract class PartType {

    protected static final String EMPTY_STRING = "";
    private ParentPartType parent;
    private PartType previousSibling;
    private PartType nextSibling;
    private int index;
    private int indexInType;
    private String analyzedType = EMPTY_STRING;
    private StringList typeNames = new StringList();
    public PartType(ParentPartType _parent, int _index, int _indexInType) {
        parent = _parent;
        index = _index;
        indexInType = _indexInType;
    }
    static PartType createPartType(Analyzable _an, ParentPartType _parent, int _index, int _indexInType, AnalyzingType _analyze, NatTreeMap<Integer, String> _dels, boolean _removedFirst, Options _options) {
        if (_analyze.isError()) {
            return new EmptyPartType(_parent, _index, _indexInType, _dels.getValue(_index));
        }
        if (_analyze.getOperators().isEmpty()) {
            if (_analyze.getKind() == KindPartType.TYPE_NAME) {
                String type_ = _dels.getValue(_index);
                if (_options.isVarTypeFirst()) {
                    type_ = ContextEl.removeDottedSpaces(type_);
                    boolean okVarType_ = false;
                    if (_parent == null || _parent instanceof ArraryPartType || _parent instanceof WildCardPartType) {
                        okVarType_ = true;
                    } else if (_parent instanceof TemplatePartType && _parent.getFirstChild() != null) {
                        okVarType_ = true;
                    }
                    if (_an != null && _an.getAvailableVariables().containsStr(type_) && okVarType_) {
                        return new VariablePartType(_parent, _index, _indexInType, type_);
                    }
                }
                return new NamePartType(_parent, _index, _indexInType, type_);
            }
            if (_analyze.getKind() == KindPartType.EMPTY_WILD_CARD) {
                return new EmptyWildCardPart(_parent, _index, _indexInType, _dels.getValue(_index));
            }
            return new VariablePartType(_parent, _index, _indexInType, _dels.getValue(_index));
        }
        if (_analyze.getPrio() == ParserType.TMP_PRIO) {
            return new TemplatePartType(_parent, _index, _indexInType);
        }
        if (_analyze.getPrio() == ParserType.INT_PRIO) {
            return new InnerPartType(_parent, _index, _indexInType, _removedFirst);
        }
        if (_analyze.getPrio() == ParserType.ARR_PRIO) {
            return new ArraryPartType(_parent, _index, _indexInType);
        }
        return new WildCardPartType(_parent, _index, _indexInType, _analyze.getOperators().firstValue());
    }
    static PartType createPartTypeExec(ParentPartType _parent, int _index, int _indexInType, AnalyzingType _analyze, NatTreeMap<Integer, String> _dels, boolean _removedFirst) {
        if (_analyze.isError()) {
            return new EmptyPartType(_parent, _index, _indexInType, _dels.getValue(_index));
        }
        if (_analyze.getOperators().isEmpty()) {
            if (_analyze.getKind() == KindPartType.TYPE_NAME) {
                return new NamePartType(_parent, _index, _indexInType, _dels.getValue(_index));
            }
            if (_analyze.getKind() == KindPartType.EMPTY_WILD_CARD) {
                return new EmptyWildCardPart(_parent, _index, _indexInType, _dels.getValue(_index));
            }
            return new VariablePartType(_parent, _index, _indexInType, _dels.getValue(_index));
        }
        if (_analyze.getPrio() == ParserType.TMP_PRIO) {
            return new TemplatePartType(_parent, _index, _indexInType);
        }
        if (_analyze.getPrio() == ParserType.INT_PRIO) {
            return new InnerPartType(_parent, _index, _indexInType, _removedFirst);
        }
        if (_analyze.getPrio() == ParserType.ARR_PRIO) {
            return new ArraryPartType(_parent, _index, _indexInType);
        }
        return new WildCardPartType(_parent, _index, _indexInType, _analyze.getOperators().firstValue());
    }
    public abstract void analyze(Analyzable _an, CustList<NatTreeMap<Integer, String>> _dels, String _globalType, AccessingImportingBlock _rooted,boolean _exact);
    public abstract void analyzeInherits(Analyzable _an, int _index, CustList<NatTreeMap<Integer, String>> _dels, String _globalType, RootBlock _rooted,boolean _exact, boolean _protected);
    public abstract void analyzeDepends(Analyzable _an, int _index, CustList<NatTreeMap<Integer, String>>_dels, RootBlock _rooted,boolean _exact);
    public abstract void analyzeAccessibleId(Analyzable _an, CustList<NatTreeMap<Integer, String>>_dels, AccessingImportingBlock _rooted);
    public StringList getTypeNames() {
        return typeNames;
    }
    public boolean isStoppedDepends() {
        return typeNames == null;
    }
    public void stopDepends() {
        typeNames = null;
    }

    public int getIndex() {
        return index;
    }
    public int getIndexInType() {
        return indexInType;
    }
    public final ParentPartType getParent() {
        return parent;
    }
    public final PartType getNextSibling() {
        return nextSibling;
    }
    public final void setPreviousSibling(PartType _previousSibling) {
        previousSibling = _previousSibling;
    }
    public final PartType getPreviousSibling() {
        return previousSibling;
    }
    public abstract PartType getFirstChild();
    public void setNextSibling(PartType _child) {
        nextSibling = _child;
    }
    public String getAnalyzedType() {
        return analyzedType;
    }
    public void setAnalyzedType(String _analyzedType) {
        analyzedType = _analyzedType;
    }
}
