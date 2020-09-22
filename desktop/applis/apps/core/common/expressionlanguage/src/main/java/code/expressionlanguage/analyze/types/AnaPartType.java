package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.types.KindPartType;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.StringList;

abstract class AnaPartType {

    protected static final String EMPTY_STRING = "";
    private AnaParentPartType parent;
    private AnaPartType previousSibling;
    private AnaPartType nextSibling;
    private int index;
    private int indexInType;
    private String analyzedType = EMPTY_STRING;
    private PartOffset beginOffset;
    private PartOffset endOffset;
    private final CustList<InaccessibleType> inaccessibleTypes = new CustList<InaccessibleType>();
    private String titleRef = "";
    private StringList errs = new StringList();
    private String href = "";
    private int length;
    private boolean alreadyError;

    AnaPartType(AnaParentPartType _parent, int _index, int _indexInType) {
        parent = _parent;
        index = _index;
        indexInType = _indexInType;
    }
    static AnaPartType createPartType(boolean _rootName, AnaParentPartType _parent, int _index, int _indexInType, AnalyzingType _analyze, IntTreeMap<String> _dels, AnalyzedPageEl _page) {
        if (_analyze.isError()) {
            return new AnaEmptyPartType(_parent, _index, _indexInType, _dels.getValue(_index),"");
        }
        IntTreeMap<String> operators_ = _analyze.getOperators();
        if (operators_.isEmpty()) {
            String str_ = ".";
            if (_parent instanceof AnaInnerPartType && _index > 0) {
                str_ = _parent.getOperators().getValue(_index - 1);
            }
            if (_analyze.getKind() == KindPartType.EMPTY_WILD_CARD) {
                return new AnaEmptyWildCardPart(_parent, _index, _indexInType, _dels.getValue(_index).trim(),str_);
            }
            String type_ = _dels.getValue(_index);
            type_ = StringExpUtil.removeDottedSpaces(type_);
            boolean okVarType_ = false;
            if (_parent == null && !_rootName || _parent instanceof AnaArraryPartType || _parent instanceof AnaWildCardPartType) {
                okVarType_ = true;
            } else if (_parent instanceof AnaTemplatePartType && _parent.getFirstChild() != null) {
                okVarType_ = true;
            }
            Integer val_ = _page.getAvailableVariables().getVal(type_);
            if (val_ != null && okVarType_) {
                return new AnaVariablePartType(_parent, _index, _indexInType, type_,str_,val_);
            }
            return new AnaNamePartType(_parent, _index, _indexInType, type_,str_);
        }
        if (_analyze.getPrio() == ParserType.TMP_PRIO) {
            return new AnaTemplatePartType(_parent, _index, _indexInType,operators_);
        }
        if (_analyze.getPrio() == ParserType.INT_PRIO) {
            return new AnaInnerPartType(_parent, _index, _indexInType,operators_);
        }
        if (_analyze.getPrio() == ParserType.ARR_PRIO) {
            return new AnaArraryPartType(_parent, _index, _indexInType,operators_);
        }
        return new AnaWildCardPartType(_parent, _index, _indexInType, operators_.firstValue(),operators_);
    }
    static AnaPartType createPartType(AnaParentPartType _parent, int _index, int _indexInType, AnalyzingType _analyze, IntTreeMap<String> _dels) {
        if (_analyze.isError()) {
            return new AnaEmptyPartType(_parent, _index, _indexInType, _dels.getValue(_index),"");
        }
        IntTreeMap<String> operators_ = _analyze.getOperators();
        if (operators_.isEmpty()) {
            String str_ = ".";
            if (_parent instanceof AnaInnerPartType && _index > 0) {
                str_ = _parent.getOperators().getValue(_index - 1);
            }
            if (_analyze.getKind() == KindPartType.EMPTY_WILD_CARD) {
                return new AnaEmptyWildCardPart(_parent, _index, _indexInType, _dels.getValue(_index).trim(),str_);
            }
            String type_ = _dels.getValue(_index);
            return new AnaNamePartType(_parent, _index, _indexInType, type_.trim(),str_);
        }
        if (_analyze.getPrio() == ParserType.TMP_PRIO) {
            return new AnaTemplatePartType(_parent, _index, _indexInType,operators_);
        }
        if (_analyze.getPrio() == ParserType.INT_PRIO) {
            return new AnaInnerPartType(_parent, _index, _indexInType,operators_);
        }
        if (_analyze.getPrio() == ParserType.ARR_PRIO) {
            return new AnaArraryPartType(_parent, _index, _indexInType,operators_);
        }
        return new AnaWildCardPartType(_parent, _index, _indexInType, operators_.firstValue(),operators_);
    }

    abstract void analyze(CustList<IntTreeMap<String>> _dels, String _globalType, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page);
    abstract void analyzeLine(ReadyTypes _ready, CustList<IntTreeMap<String>> _dels, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page);

    abstract void analyzeAccessibleId(CustList<IntTreeMap<String>> _dels, AccessedBlock _rooted, AnalyzedPageEl _page);

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

    void processBadFormedOffsets(AnalyzedPageEl _page) {
        if (!_page.isGettingParts()) {
            return;
        }
        getErrs().add(FoundErrorInterpret.buildARError(_page.getAnalysisMessages().getBadParamerizedType(),getAnalyzedType()));
    }

    void processInexistType(String _in, AnalyzedPageEl _page) {
        getErrs().add(FoundErrorInterpret.buildARError(_page.getAnalysisMessages().getUnknownType(),_in));
    }
    void buildOffsetPartDefault(AnalyzedPageEl _page) {
        int begin_ = _page.getLocalInType() + getIndexInType();
        buildOffsetPart(begin_,Math.max(1, length));
    }
    void buildOffsetPart(int _offset,int _len) {
        StringBuilder pref_ = new StringBuilder("<a");
        boolean add_ = false;
        if (!errs.isEmpty()) {
            add_ = true;
            pref_.append(" title=\"");
            pref_.append(LinkageUtil.transform(StringList.join(errs,"\n\n")));
            appendTitleRef(pref_, "\n\n"+titleRef);
            pref_.append("\"");
            appendHref(pref_);
            pref_.append(" class=\"e\">");
        } else {
            appendTitleRef(pref_, " title=\"" + titleRef + "\"");
            appendHref(pref_);
            pref_.append(">");
        }
        if (!titleRef.isEmpty()) {
            add_ = true;
        }
        if (!href.isEmpty()) {
            add_ = true;
        }
        if (!add_) {
            return;
        }
        setBeginOffset(new PartOffset(pref_.toString(),_offset));
        setEndOffset(new PartOffset("</a>",_offset+_len));
    }

    private void appendTitleRef(StringBuilder _pref, String _str) {
        if (!titleRef.isEmpty()) {
            _pref.append(_str);
        }
    }

    private void appendHref(StringBuilder _pref) {
        if (!href.isEmpty()) {
            _pref.append(" href=\"").append(href).append("\"");
        }
    }

    StringList getErrs() {
        return errs;
    }

    void setHref(String _href) {
        href = _href;
    }

    void setTitleRef(String _titleRef) {
        titleRef = _titleRef;
    }

    PartOffset getBeginOffset() {
        return beginOffset;
    }

    void setBeginOffset(PartOffset _beginOffset) {
        beginOffset = _beginOffset;
    }

    PartOffset getEndOffset() {
        return endOffset;
    }

    void setEndOffset(PartOffset _endOffset) {
        endOffset = _endOffset;
    }

    CustList<InaccessibleType> getInaccessibleTypes() {
        return inaccessibleTypes;
    }

    void setLength(int _length) {
        length = _length;
    }

    boolean isAlreadyError() {
        return alreadyError;
    }

    void setAlreadyError() {
        alreadyError = true;
    }
}
