package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.linkage.ExportCst;
import code.maths.litteralcom.StrTypes;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.types.KindPartType;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

abstract class AnaPartType {

    protected static final String EMPTY_STRING = "";
    private final AnaParentPartType parent;
    private AnaPartType previousSibling;
    private AnaPartType nextSibling;
    private final int index;
    private final int indexInType;
    private String analyzedType = EMPTY_STRING;
    private final CustList<InaccessibleType> inaccessibleTypes = new CustList<InaccessibleType>();
    private final StringList errs = new StringList();
    private int length;
    private int loc;
    private boolean errorNbParam;
    private final AnalysisMessages messages;
    private AnaGeneType foundType;

    AnaPartType(AnaParentPartType _parent, int _index, int _indexInType, AnalysisMessages _messages) {
        parent = _parent;
        index = _index;
        indexInType = _indexInType;
        messages = _messages;
    }
    static AnaPartType createErrorType(String _value, AnalysisMessages _analysisMessages) {
        return new AnaEmptyPartType(null, 0, 0, _value,"", _analysisMessages);
    }
    static AnaPartType createErrorType() {
        return createErrorType("", null);
    }
    static AnaPartType createPartType(boolean _rootName, AnaParentPartType _parent, int _index, int _indexInType, AnalyzingType _analyze, AnalyzedPageEl _page, String _value) {
        if (_analyze.isError()) {
            return new AnaEmptyPartType(_parent, _index, _indexInType, _value,"", _page.getAnalysisMessages());
        }
        StrTypes operators_ = _analyze.getOperators();
        if (operators_.isEmpty()) {
            String str_ = prevOper(_parent, _index);
            String type_ = _value.trim();
            if (_analyze.getKind() == KindPartType.EMPTY_WILD_CARD) {
                return new AnaEmptyWildCardPart(_parent, _index, _indexInType, type_,str_, _page.getAnalysisMessages());
            }
            boolean okVarType_ = false;
            if (_parent == null && !_rootName || _parent instanceof AnaArraryPartType || _parent instanceof AnaWildCardPartType || _parent instanceof AnaRefPartType) {
                okVarType_ = true;
            } else if (_parent instanceof AnaTemplatePartType && _parent.getFirstChild() != null) {
                okVarType_ = true;
            }
            Integer val_ = _page.getAvailableVariables().getVal(type_);
            if (val_ != null && okVarType_) {
                return new AnaVariablePartType(_parent, _index, _indexInType, type_,str_,val_, _page.getAnalysisMessages());
            }
            return new AnaNamePartType(_parent, _index, _indexInType, type_,str_, _page.getAnalysisMessages());
        }
        return buildPar(_parent, _index, _indexInType, _analyze, _page.getAnalysisMessages());
    }
    static AnaPartType createPartType(AnaParentPartType _parent, int _index, int _indexInType, AnalyzingType _analyze, String _value) {
        if (_analyze.isError()) {
            return new AnaEmptyPartType(_parent, _index, _indexInType, _value,"", null);
        }
        StrTypes operators_ = _analyze.getOperators();
        if (operators_.isEmpty()) {
            String str_ = prevOper(_parent, _index);
            String type_ = _value.trim();
            if (_analyze.getKind() == KindPartType.EMPTY_WILD_CARD) {
                return new AnaEmptyWildCardPart(_parent, _index, _indexInType, type_,str_, null);
            }
            return new AnaNamePartType(_parent, _index, _indexInType, type_,str_, null);
        }
        return buildPar(_parent, _index, _indexInType, _analyze, null);
    }

    private static String prevOper(AnaParentPartType _parent, int _index) {
        String str_ = ".";
        if (_parent instanceof AnaInnerPartType && _index > 0) {
            str_ = _parent.getOperators().getValue(_index - 1);
        }
        return str_;
    }

    private static AnaPartType buildPar(AnaParentPartType _parent, int _index, int _indexInType, AnalyzingType _analyze, AnalysisMessages _messages) {
        StrTypes operators_ = _analyze.getOperators();
        if (_analyze.getPrio() == ParserType.TMP_PRIO) {
            return new AnaTemplatePartType(_parent, _index, _indexInType,operators_, _messages);
        }
        if (_analyze.getPrio() == ParserType.INT_PRIO) {
            return new AnaInnerPartType(_parent, _index, _indexInType,operators_, _messages);
        }
        if (_analyze.getPrio() == ParserType.ARR_PRIO) {
            return new AnaArraryPartType(_parent, _index, _indexInType,operators_, _messages);
        }
        if (StringUtil.quickEq(operators_.firstValue(),"~")) {
            return new AnaRefPartType(_parent, _index, _indexInType, operators_.firstValue(),operators_, _messages);
        }
        return new AnaWildCardPartType(_parent, _index, _indexInType, operators_.firstValue(),operators_, _messages);
    }

    abstract void analyze(String _globalType, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page, int _loc);
    abstract void analyzeLine(ReadyTypes _ready, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page, int _loc);

    abstract void analyzeAccessibleId(AccessedBlock _rooted, AnalyzedPageEl _page, int _loc);

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

    void processBadFormedOffsets(StringList _errs) {
        _errs.add(FoundErrorInterpret.buildARError(messages.getBadParamerizedType(),getAnalyzedType()));
    }

    void processInexistType(String _in, StringList _errs) {
        _errs.add(FoundErrorInterpret.buildARError(messages.getUnknownType(),_in));
    }

    void processFound(AnalyzedPageEl _page) {
        String imported_ = getAnalyzedType();
        String idCl_ = StringExpUtil.getIdFromAllTypes(imported_);
        foundType = _page.getAnaGeneType(idCl_);
    }

    void foundType(AnaPartType _part) {
        foundType = _part.foundType;
    }
    AnaGeneType getFoundType() {
        return foundType;
    }

    AnalysisMessages getMessages() {
        return messages;
    }

    String buildOffsetPartDefault(String _href) {
        return buildOffsetPart(_href, "");
    }

    int getFull() {
        return getLoc() + getIndexInType();
    }

    String buildOffsetPart() {
        return buildOffsetPart("", "");
    }
    String buildOffsetPart(String _href, String _titleRef) {
        StringBuilder pref_ = new StringBuilder(ExportCst.BEGIN_ANCHOR);
        boolean add_ = false;
        if (!errs.isEmpty()||!_titleRef.isEmpty()) {
            pref_.append(ExportCst.SEP_ATTR).append(ExportCst.title(errs, _titleRef));
        }
        if (!_href.isEmpty()) {
            pref_.append(ExportCst.SEP_ATTR).append(_href);
        }
        if (!errs.isEmpty()) {
            pref_.append(ExportCst.SEP_ATTR_CLASS_ERR+ExportCst.END);
        } else {
            pref_.append(ExportCst.END);
        }
        if (!errs.isEmpty()) {
            add_ = true;
        }
        if (!_titleRef.isEmpty()) {
            add_ = true;
        }
        if (!_href.isEmpty()) {
            add_ = true;
        }
        if (!add_) {
            return "";
        }
        return pref_.toString();
    }

    StringList getErrs() {
        return errs;
    }
    void errs(AnaPartType _part) {
        errs(_part.errs);
    }
    void errs(StringList _errs) {
        errs.clear();
        errs.addAllElts(_errs);
    }

    CustList<InaccessibleType> getInaccessibleTypes() {
        return inaccessibleTypes;
    }

    int getLength() {
        return Math.max(1, length);
    }

    void setLength(int _length) {
        length = _length;
    }

    boolean isErrorNbParam() {
        return errorNbParam;
    }

    void setErrorNbParam() {
        errorNbParam = true;
    }

    void setLoc(int _loc) {
        loc = _loc;
    }

    int getLoc() {
        return loc;
    }
}
