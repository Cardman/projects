package code.expressionlanguage.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
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
    static PartType createPartType(ContextEl _an, boolean _rootName,ParentPartType _parent, int _index, int _indexInType, AnalyzingType _analyze, IntTreeMap<String> _dels) {
        if (_analyze.isError()) {
            return new EmptyPartType(_parent, _index, _indexInType, _dels.getValue(_index),"");
        }
        IntTreeMap<String> operators_ = _analyze.getOperators();
        if (operators_.isEmpty()) {
            String str_ = ".";
            if (_parent instanceof InnerPartType && _index > 0) {
                str_ = ((InnerPartType) _parent).getOperators().get(_index - 1);
            }
            if (_analyze.getKind() == KindPartType.TYPE_NAME) {
                String type_ = _dels.getValue(_index);
                type_ = StringExpUtil.removeDottedSpaces(type_);
                boolean okVarType_ = false;
                if (_parent == null && !_rootName || _parent instanceof ArraryPartType || _parent instanceof WildCardPartType) {
                    okVarType_ = true;
                } else if (_parent instanceof TemplatePartType && _parent.getFirstChild() != null) {
                    okVarType_ = true;
                }
                if (_an.getAnalyzing().getAvailableVariables().contains(type_) && okVarType_) {
                    return new VariablePartType(_parent, _index, _indexInType, type_,str_);
                }
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
    static PartType createPartType(ParentPartType _parent, int _index, int _indexInType, AnalyzingType _analyze, IntTreeMap<String> _dels) {
        if (_analyze.isError()) {
            return new EmptyPartType(_parent, _index, _indexInType, _dels.getValue(_index),"");
        }
        IntTreeMap<String> operators_ = _analyze.getOperators();
        if (operators_.isEmpty()) {
            String str_ = ".";
            if (_parent instanceof InnerPartType && _index > 0) {
                str_ = ((InnerPartType) _parent).getOperators().get(_index - 1);
            }
            if (_analyze.getKind() == KindPartType.TYPE_NAME) {
                String type_ = _dels.getValue(_index);
                return new NamePartType(_parent, _index, _indexInType, type_.trim(),str_);
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
    abstract void analyze(ContextEl _an, CustList<IntTreeMap< String>> _dels, String _globalType, AccessingImportingBlock _local,AccessingImportingBlock _rooted);
    abstract void analyzeLine(ContextEl _an, ReadyTypes _ready,CustList<IntTreeMap< String>> _dels, String _globalType, AccessingImportingBlock _local,AccessingImportingBlock _rooted);

    abstract void analyzeAccessibleId(ContextEl _an, CustList<IntTreeMap< String>>_dels, AccessingImportingBlock _rooted);
    abstract void setAnalyzedType(CustList<IntTreeMap<String>> _dels, StringMap<StringList> _inherit);
    abstract void analyzeTemplate(ContextEl _an, CustList<IntTreeMap<String>> _dels, StringMap<StringList> _inherit);

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
