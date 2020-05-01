package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.util.CustList;
import code.util.*;
import code.util.StringList;


abstract class PartType {

    protected static final String EMPTY_STRING = "";
    private ParentPartType parent;
    private PartType previousSibling;
    private PartType nextSibling;
    private int index;
    private int indexInType;
    private String analyzedType = EMPTY_STRING;

    PartType(ParentPartType _parent, int _index, int _indexInType) {
        parent = _parent;
        index = _index;
        indexInType = _indexInType;
    }
    static PartType createPartType(Analyzable _an, boolean _rootName,ParentPartType _parent, int _index, int _indexInType, AnalyzingType _analyze, IntTreeMap<String> _dels) {
        if (_analyze.isError()) {
            return new EmptyPartType(_parent, _index, _indexInType, _dels.getValue(_index),"");
        }
        IntTreeMap<String> operators_ = _analyze.getOperators();
        if (operators_.isEmpty()) {
            if (_analyze.getKind() == KindPartType.TYPE_NAME) {
                String type_ = _dels.getValue(_index);
                type_ = ContextEl.removeDottedSpaces(type_);
                boolean okVarType_ = false;
                if (_parent == null && !_rootName || _parent instanceof ArraryPartType || _parent instanceof WildCardPartType) {
                    okVarType_ = true;
                } else if (_parent instanceof TemplatePartType && _parent.getFirstChild() != null) {
                    okVarType_ = true;
                }
                if (_an.getAvailableVariables().contains(type_) && okVarType_) {
                    return new VariablePartType(_parent, _index, _indexInType, type_,"");
                }
                return new NamePartType(_parent, _index, _indexInType, type_,"");
            }
            if (_analyze.getKind() == KindPartType.EMPTY_WILD_CARD) {
                return new EmptyWildCardPart(_parent, _index, _indexInType, _dels.getValue(_index),"");
            }
            return new VariablePartType(_parent, _index, _indexInType, _dels.getValue(_index),"");
        }
        if (_analyze.getPrio() == ParserType.TMP_PRIO) {
            return new TemplatePartType(_parent, _index, _indexInType);
        }
        if (_analyze.getPrio() == ParserType.INT_PRIO) {
            return new InnerPartType(_parent, _index, _indexInType,operators_.values());
        }
        if (_analyze.getPrio() == ParserType.ARR_PRIO) {
            return new ArraryPartType(_parent, _index, _indexInType);
        }
        return new WildCardPartType(_parent, _index, _indexInType, operators_.firstValue());
    }
    static PartType createQuickPartType(ParentPartType _parent, int _index, int _indexInType, AnalyzingType _analyze, IntTreeMap<String> _dels) {
        IntTreeMap<String> operators_ = _analyze.getOperators();
        if (operators_.isEmpty()) {
            String str_ = "..";
            if (_parent instanceof InnerPartType && _index > 0) {
                str_ = ((InnerPartType) _parent).getOperators().get(_index - 1);
            }
            if (_analyze.getKind() == KindPartType.TYPE_NAME) {
                String type_ = _dels.getValue(_index);
                return new NamePartType(_parent, _index, _indexInType, type_,str_);
            }
            if (_analyze.getKind() == KindPartType.EMPTY_WILD_CARD) {
                return new EmptyWildCardPart(_parent, _index, _indexInType, _dels.getValue(_index),str_);
            }
            return new VariablePartType(_parent, _index, _indexInType, _dels.getValue(_index),str_);
        }
        if (_analyze.getPrio() == ParserType.TMP_PRIO) {
            return new TemplatePartType(_parent, _index, _indexInType);
        }
        if (_analyze.getPrio() == ParserType.INT_PRIO) {
            return new InnerPartType(_parent, _index, _indexInType,operators_.values());
        }
        if (_analyze.getPrio() == ParserType.ARR_PRIO) {
            return new ArraryPartType(_parent, _index, _indexInType);
        }
        return new WildCardPartType(_parent, _index, _indexInType, operators_.firstValue());
    }
    static PartType createPartTypeExec(ParentPartType _parent, int _index, int _indexInType, AnalyzingType _analyze, IntTreeMap<String> _dels) {
        if (_analyze.isError()) {
            return new EmptyPartType(_parent, _index, _indexInType, _dels.getValue(_index),"");
        }
        IntTreeMap<String> operators_ = _analyze.getOperators();
        if (operators_.isEmpty()) {
            String str_ = "..";
            if (_parent instanceof InnerPartType && _index > 0) {
                str_ = ((InnerPartType) _parent).getOperators().get(_index - 1);
            }
            if (_analyze.getKind() == KindPartType.TYPE_NAME) {
                return new NamePartType(_parent, _index, _indexInType, _dels.getValue(_index),str_);
            }
            if (_analyze.getKind() == KindPartType.EMPTY_WILD_CARD) {
                return new EmptyWildCardPart(_parent, _index, _indexInType, _dels.getValue(_index),str_);
            }
            return new VariablePartType(_parent, _index, _indexInType, _dels.getValue(_index),str_);
        }
        if (_analyze.getPrio() == ParserType.TMP_PRIO) {
            return new TemplatePartType(_parent, _index, _indexInType);
        }
        if (_analyze.getPrio() == ParserType.INT_PRIO) {
            return new InnerPartType(_parent, _index, _indexInType,operators_.values());
        }
        if (_analyze.getPrio() == ParserType.ARR_PRIO) {
            return new ArraryPartType(_parent, _index, _indexInType);
        }
        return new WildCardPartType(_parent, _index, _indexInType, operators_.firstValue());
    }
    abstract void analyze(Analyzable _an, CustList<IntTreeMap< String>> _dels, String _globalType, AccessingImportingBlock _local,AccessingImportingBlock _rooted);
    abstract void analyzeLine(Analyzable _an, ReadyTypes _ready,CustList<IntTreeMap< String>> _dels, String _globalType, AccessingImportingBlock _local,AccessingImportingBlock _rooted);

    abstract void analyzeAccessibleId(Analyzable _an, CustList<IntTreeMap< String>>_dels, AccessingImportingBlock _rooted);
    abstract void analyzeTemplate(Analyzable _an, CustList<IntTreeMap<String>> _dels, StringMap<StringList> _inherit);

    int getIndex() {
        return index;
    }
    int getIndexInType() {
        return indexInType;
    }
    final ParentPartType getParent() {
        return parent;
    }
    final PartType getNextSibling() {
        return nextSibling;
    }
    final void setPreviousSibling(PartType _previousSibling) {
        previousSibling = _previousSibling;
    }
    final PartType getPreviousSibling() {
        return previousSibling;
    }
    abstract PartType getFirstChild();
    void setNextSibling(PartType _child) {
        nextSibling = _child;
    }
    String getAnalyzedType() {
        return analyzedType;
    }
    void setAnalyzedType(String _analyzedType) {
        analyzedType = _analyzedType;
    }
}
