package code.expressionlanguage.analyze.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.types.AnalyzingType;
import code.expressionlanguage.types.KindPartType;
import code.expressionlanguage.types.ParserType;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.StringList;
import code.util.StringMap;

abstract class AnaPartType {

    protected static final String EMPTY_STRING = "";
    private AnaParentPartType parent;
    private AnaPartType previousSibling;
    private AnaPartType nextSibling;
    private int index;
    private int indexInType;
    private String analyzedType = EMPTY_STRING;

    AnaPartType(AnaParentPartType _parent, int _index, int _indexInType) {
        parent = _parent;
        index = _index;
        indexInType = _indexInType;
    }
    static AnaPartType createPartType(ContextEl _an, boolean _rootName, AnaParentPartType _parent, int _index, int _indexInType, AnalyzingType _analyze, IntTreeMap<String> _dels) {
        if (_analyze.isError()) {
            return new AnaEmptyPartType(_parent, _index, _indexInType, _dels.getValue(_index),"");
        }
        IntTreeMap<String> operators_ = _analyze.getOperators();
        if (operators_.isEmpty()) {
            String str_ = ".";
            if (_parent instanceof AnaInnerPartType && _index > 0) {
                str_ = ((AnaInnerPartType) _parent).getOperators().get(_index - 1);
            }
            if (_analyze.getKind() == KindPartType.TYPE_NAME) {
                String type_ = _dels.getValue(_index);
                type_ = StringExpUtil.removeDottedSpaces(type_);
                boolean okVarType_ = false;
                if (_parent == null && !_rootName || _parent instanceof AnaArraryPartType || _parent instanceof AnaWildCardPartType) {
                    okVarType_ = true;
                } else if (_parent instanceof AnaTemplatePartType && _parent.getFirstChild() != null) {
                    okVarType_ = true;
                }
                if (_an.getAnalyzing().getAvailableVariables().contains(type_) && okVarType_) {
                    return new AnaVariablePartType(_parent, _index, _indexInType, type_,str_);
                }
                return new AnaNamePartType(_parent, _index, _indexInType, type_,str_);
            }
            if (_analyze.getKind() == KindPartType.EMPTY_WILD_CARD) {
                return new AnaEmptyWildCardPart(_parent, _index, _indexInType, _dels.getValue(_index),str_);
            }
            return new AnaVariablePartType(_parent, _index, _indexInType, _dels.getValue(_index),str_);
        }
        if (_analyze.getPrio() == ParserType.TMP_PRIO) {
            return new AnaTemplatePartType(_parent, _index, _indexInType);
        }
        if (_analyze.getPrio() == ParserType.INT_PRIO) {
            return new AnaInnerPartType(_parent, _index, _indexInType,operators_.values());
        }
        if (_analyze.getPrio() == ParserType.ARR_PRIO) {
            return new AnaArraryPartType(_parent, _index, _indexInType);
        }
        return new AnaWildCardPartType(_parent, _index, _indexInType, operators_.firstValue());
    }
    static AnaPartType createPartType(AnaParentPartType _parent, int _index, int _indexInType, AnalyzingType _analyze, IntTreeMap<String> _dels) {
        if (_analyze.isError()) {
            return new AnaEmptyPartType(_parent, _index, _indexInType, _dels.getValue(_index),"");
        }
        IntTreeMap<String> operators_ = _analyze.getOperators();
        if (operators_.isEmpty()) {
            String str_ = ".";
            if (_parent instanceof AnaInnerPartType && _index > 0) {
                str_ = ((AnaInnerPartType) _parent).getOperators().get(_index - 1);
            }
            if (_analyze.getKind() == KindPartType.TYPE_NAME) {
                String type_ = _dels.getValue(_index);
                return new AnaNamePartType(_parent, _index, _indexInType, type_.trim(),str_);
            }
            if (_analyze.getKind() == KindPartType.EMPTY_WILD_CARD) {
                return new AnaEmptyWildCardPart(_parent, _index, _indexInType, _dels.getValue(_index),str_);
            }
            return new AnaVariablePartType(_parent, _index, _indexInType, _dels.getValue(_index),str_);
        }
        if (_analyze.getPrio() == ParserType.TMP_PRIO) {
            return new AnaTemplatePartType(_parent, _index, _indexInType);
        }
        if (_analyze.getPrio() == ParserType.INT_PRIO) {
            return new AnaInnerPartType(_parent, _index, _indexInType,operators_.values());
        }
        if (_analyze.getPrio() == ParserType.ARR_PRIO) {
            return new AnaArraryPartType(_parent, _index, _indexInType);
        }
        return new AnaWildCardPartType(_parent, _index, _indexInType, operators_.firstValue());
    }
    static AnaPartType createQuickPartType(AnaParentPartType _parent, int _index, int _indexInType, AnalyzingType _analyze, IntTreeMap<String> _dels) {
        IntTreeMap<String> operators_ = _analyze.getOperators();
        if (operators_.isEmpty()) {
            String str_ = "..";
            if (_parent instanceof AnaInnerPartType && _index > 0) {
                str_ = ((AnaInnerPartType) _parent).getOperators().get(_index - 1);
            }
            if (_analyze.getKind() == KindPartType.TYPE_NAME) {
                String type_ = _dels.getValue(_index);
                return new AnaNamePartType(_parent, _index, _indexInType, type_,str_);
            }
            if (_analyze.getKind() == KindPartType.EMPTY_WILD_CARD) {
                return new AnaEmptyWildCardPart(_parent, _index, _indexInType, _dels.getValue(_index),str_);
            }
            return new AnaVariablePartType(_parent, _index, _indexInType, _dels.getValue(_index),str_);
        }
        if (_analyze.getPrio() == ParserType.TMP_PRIO) {
            return new AnaTemplatePartType(_parent, _index, _indexInType);
        }
        if (_analyze.getPrio() == ParserType.INT_PRIO) {
            return new AnaInnerPartType(_parent, _index, _indexInType,operators_.values());
        }
        if (_analyze.getPrio() == ParserType.ARR_PRIO) {
            return new AnaArraryPartType(_parent, _index, _indexInType);
        }
        return new AnaWildCardPartType(_parent, _index, _indexInType, operators_.firstValue());
    }

    abstract void analyze(ContextEl _an, CustList<IntTreeMap< String>> _dels, String _globalType, AccessingImportingBlock _local,AccessingImportingBlock _rooted);
    abstract void analyzeLine(ContextEl _an, ReadyTypes _ready, CustList<IntTreeMap< String>> _dels, AccessingImportingBlock _local, AccessingImportingBlock _rooted);

    abstract void analyzeAccessibleId(ContextEl _an, CustList<IntTreeMap< String>>_dels, AccessingImportingBlock _rooted);

    abstract void analyzeTemplate(ContextEl _an, CustList<IntTreeMap<String>> _dels, StringMap<StringList> _inherit);

    int getIndex() {
        return index;
    }
    int getIndexInType() {
        return indexInType;
    }
    final AnaParentPartType getParent() {
        return parent;
    }
    final AnaPartType getNextSibling() {
        return nextSibling;
    }
    final void setPreviousSibling(AnaPartType _previousSibling) {
        previousSibling = _previousSibling;
    }
    final AnaPartType getPreviousSibling() {
        return previousSibling;
    }
    abstract AnaPartType getFirstChild();
    void setNextSibling(AnaPartType _child) {
        nextSibling = _child;
    }
    String getAnalyzedType() {
        return analyzedType;
    }
    void setAnalyzedType(String _analyzedType) {
        analyzedType = _analyzedType;
    }
}
