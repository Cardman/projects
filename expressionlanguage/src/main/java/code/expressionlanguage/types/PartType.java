package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Options;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.sml.RowCol;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;


public abstract class PartType {

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
    static PartType createPartType(ParentPartType _parent, int _index, int _indexInType, AnalyzingType _analyze, NatTreeMap<Integer, String> _dels, boolean _removedFirst, Options _options) {
        if (_analyze.isError()) {
            return new EmptyPartType(_parent, _index, _indexInType, _dels.getValue(_index));
        }
        if (_analyze.getOperators().isEmpty()) {
            if (_analyze.getKind() == KindPartType.TYPE_NAME) {
                return new NamePartType(_parent, _index, _indexInType, _dels.getValue(_index));
            }
            return new VariablePartType(_parent, _index, _indexInType, _dels.getValue(_index));
        }
        if (_analyze.getPrio() == ParserType.TMP_PRIO) {
            return new TemplatePartType(_parent, _index, _indexInType);
        }
        if (_analyze.getPrio() == ParserType.INT_PRIO) {
            return new InnerPartType(_parent, _index, _indexInType, _removedFirst);
        }
        return new ArraryPartType(_parent, _index, _indexInType);
    }
    static PartType createPartTypeExec(ParentPartType _parent, int _index, int _indexInType, AnalyzingType _analyze, NatTreeMap<Integer, String> _dels, boolean _removedFirst) {
        if (_analyze.isError()) {
            return new EmptyPartType(_parent, _index, _indexInType, _dels.getValue(_index));
        }
        if (_analyze.getOperators().isEmpty()) {
            if (_analyze.getKind() == KindPartType.TYPE_NAME) {
                return new NamePartType(_parent, _index, _indexInType, _dels.getValue(_index));
            }
            return new VariablePartType(_parent, _index, _indexInType, _dels.getValue(_index));
        }
        if (_analyze.getPrio() == ParserType.TMP_PRIO) {
            return new TemplatePartType(_parent, _index, _indexInType);
        }
        if (_analyze.getPrio() == ParserType.INT_PRIO) {
            return new InnerPartType(_parent, _index, _indexInType, _removedFirst);
        }
        return new ArraryPartType(_parent, _index, _indexInType);
    }
    public abstract void analyze(Analyzable _an, CustList<NatTreeMap<Integer, String>> _dels, String _globalType, AccessingImportingBlock _rooted,boolean _exact, RowCol _location);
    public abstract void analyze(Analyzable _an, CustList<NatTreeMap<Integer, String>>_dels, String _globalType, AccessingImportingBlock _rooted,boolean _exact);
    public abstract void analyzeDepends(Analyzable _an, CustList<NatTreeMap<Integer, String>>_dels, String _globalType, AccessingImportingBlock _rooted,boolean _exact, RowCol _location);
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
