package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.analyze.files.SegmentStringPart;
import code.expressionlanguage.analyze.files.StringComment;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.util.ScopeFilter;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.util.IterableAnalysisResult;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.analyze.ResultInput;
import code.formathtml.analyze.ResultText;
import code.maths.litteralcom.MathExpUtil;
import code.sml.Element;
import code.sml.NavigationCore;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AnaRendSelect extends AnaRendElement implements AnaRendBuildEl,AnaRendInputInt,AnaRendElementAttr {
    private OperationNode rootRead;
    private OperationNode rootValue;
    private final ResultExpression resultExpressionMap = new ResultExpression();
    private OperationNode rootMap;
    private OperationNode rootDefault;
    private ClassMethodIdReturn rootConverter;
    private ClassMethodIdReturn rootConverterField;
    private ClassMethodIdReturn rootConverterFieldValue;
    private String idClass = NavigationCore.EMPTY_STRING;
    private String idName = NavigationCore.EMPTY_STRING;
    private boolean multiple;
    private String className = NavigationCore.EMPTY_STRING;
    private boolean arrayConverter;
    private final ResultInput resultInput;

    public AnaRendSelect(Element _elt, int _offset) {
        super(_elt,_offset);
        resultInput = new ResultInput();
    }

    @Override
    public void processAttributes(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        resultInput.build(this, getElt(),_anaDoc.getRendKeyWords().getAttrVarValue(), _anaDoc, _page);
        rootRead = resultInput.getOpsReadRoot();
        rootValue = resultInput.getOpsValueRoot();
        idClass = resultInput.getIdClass();
        idName = resultInput.getIdName();
        className = resultInput.getClassName();

        multiple = getElt().hasAttribute(_anaDoc.getRendKeyWords().getAttrMultiple());
        _page.setSumOffset(resultExpressionMap.getSumOffset());
        _page.zeroOffset();
        rootMap = RenderAnalysis.getRootAnalyzedOperations(0, _anaDoc, _page,resultExpressionMap);
        String converterValue_ = getElt().getAttribute(_anaDoc.getRendKeyWords().getAttrConvertValue());
        if (multiple) {
            if (!MathExpUtil.isDollarWord(converterValue_.trim())) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFile(_page.getCurrentFile());
                badEl_.setIndexFile(getOffset());
                badEl_.buildError(_anaDoc.getRendAnalysisMessages().getEmptyAttr(),
                        _anaDoc.getRendKeyWords().getAttrConvertValue());
                AnalyzingDoc.addError(badEl_, _page);
            }
            String string_ = StringExpUtil.getPrettyArrayType(_page.getAliasString());
            arrayConverter = true;
            int offConvValue_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrConvertValue());
            _page.setSumOffset(offConvValue_);
            _page.zeroOffset();
            ClassMethodIdReturn classMethodIdReturn_ = OperationNode.tryGetDeclaredCustMethodSetIndexer(MethodAccessKind.INSTANCE, new StringList(_page.getGlobalClass()), converterValue_.trim(), new StringList(string_), _page, new ScopeFilter(null, true, true, false, _page.getGlobalType().getRootBlock()));
            rootConverter = classMethodIdReturn_;
            checkMultiple(_anaDoc,_page, classMethodIdReturn_);
        } else if (rootRead != null){
            Mapping m_ = new Mapping();
            m_.setArg(resultInput.getOpsReadRoot().getResultClass());
            m_.setParam(_anaDoc.getAliasCharSequence());
            if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
                if (!MathExpUtil.isDollarWord(converterValue_.trim())) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFile(_page.getCurrentFile());
                    badEl_.setIndexFile(getOffset());
                    badEl_.buildError(_anaDoc.getRendAnalysisMessages().getEmptyAttr(),
                            _anaDoc.getRendKeyWords().getAttrConvertValue());
                    AnalyzingDoc.addError(badEl_, _page);
                }
                checkConverter(_anaDoc, _page, converterValue_, m_);
            } else if (MathExpUtil.isDollarWord(converterValue_.trim())) {
                checkConverter(_anaDoc, _page, converterValue_, m_);
            }
        }
        String converterField_ = getElt().getAttribute(_anaDoc.getRendKeyWords().getAttrConvertField());
        if (MathExpUtil.isDollarWord(converterField_.trim())) {
            String object_ = _page.getAliasObject();
            int offConvValue_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrConvertField());
            _page.setSumOffset(offConvValue_);
            _page.zeroOffset();
            ClassMethodIdReturn classMethodIdReturn_ = OperationNode.tryGetDeclaredCustMethodSetIndexer(MethodAccessKind.INSTANCE, new StringList(_page.getGlobalClass()), converterField_.trim(), new StringList(object_), _page, new ScopeFilter(null, true, true, false, _page.getGlobalType().getRootBlock()));
            rootConverterField = classMethodIdReturn_;
            checkCharSeq(_anaDoc,_page,offConvValue_,classMethodIdReturn_);
        }
        String converterFieldValue_ = getElt().getAttribute(_anaDoc.getRendKeyWords().getAttrConvertFieldValue());
        if (MathExpUtil.isDollarWord(converterFieldValue_.trim())) {
            String object_ = _page.getAliasObject();
            int offConvValue_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrConvertFieldValue());
            _page.setSumOffset(offConvValue_);
            _page.zeroOffset();
            ClassMethodIdReturn classMethodIdReturn_ = OperationNode.tryGetDeclaredCustMethodSetIndexer(MethodAccessKind.INSTANCE, new StringList(_page.getGlobalClass()), converterFieldValue_.trim(), new StringList(object_), _page, new ScopeFilter(null, true, true, false, _page.getGlobalType().getRootBlock()));
            rootConverterFieldValue = classMethodIdReturn_;
            checkCharSeq(_anaDoc,_page,offConvValue_,classMethodIdReturn_);
        }
        defValue(_anaDoc, _page);
    }

    private void checkMultiple(AnalyzingDoc _anaDoc, AnalyzedPageEl _page, ClassMethodIdReturn _id) {
        int offConvValue_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrConvertValue());
        String check_ = ResultText.check(_page, offConvValue_, _id);
        StringList names_ = resultInput.getOpsValueRoot().getResultClass().getNames();
        if (!resultInput.getOpsValueRoot().getResultClass().isVariable()) {
            IterableAnalysisResult it_ = ContextUtil.getCustomTypeBase(names_, _page);
            StringList candidates_ = it_.getClassName();
            if (!candidates_.onlyOneElt()) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFile(_page.getCurrentFile());
                badEl_.setIndexFile(offConvValue_);
                badEl_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                        StringUtil.join(candidates_,AND_ERR));
                AnalyzingDoc.addError(badEl_, _page);
            }
            Mapping m_ = new Mapping();
            m_.setArg(check_);
            m_.setParam(resultInput.getOpsReadRoot().getResultClass());
            if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFile(_page.getCurrentFile());
                badEl_.setIndexFile(getOffset());
                badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        check_,
                        StringUtil.join(rootRead.getResultClass().getNames(),AND_ERR));
                AnalyzingDoc.addError(badEl_, _page);
            }
        }
    }

    private void defValue(AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        String default_ = getElt().getAttribute(_anaDoc.getRendKeyWords().getAttrDefault());
        if (!default_.isEmpty()) {
            String mName_ = getElt().getAttribute(_anaDoc.getRendKeyWords().getAttrConvert());
            if (!MathExpUtil.isDollarWord(mName_.trim())) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFile(_page.getCurrentFile());
                badEl_.setIndexFile(getOffset());
                badEl_.buildError(_anaDoc.getRendAnalysisMessages().getEmptyAttr(),
                        _anaDoc.getRendKeyWords().getAttrConvert());
                AnalyzingDoc.addError(badEl_, _page);
            }
            String concat_ = StringUtil.concat(mName_,LEFT_PAR,STR,def(default_),STR,RIGHT_PAR);
            int offConvValue_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrConvert());
            _page.setSumOffset(offConvValue_);
            _page.zeroOffset();
            StringComment strCom_ = new StringComment(concat_,new CustList<CommentDelimiters>());
            CustList<SegmentStringPart> stringParts_ = strCom_.getStringParts();
            _page.getCurrentParts().addAllElts(stringParts_);
            ResultExpression res_ = new ResultExpression();
            res_.setAnalyzedString(concat_);
            res_.partsAbsol(stringParts_);
            res_.getParts().addAllElts(stringParts_);
            rootDefault = RenderAnalysis.getRootAnalyzedOperations(res_, 0, _anaDoc, _page);
            _page.getCurrentParts().clear();
            Mapping m_ = new Mapping();
            m_.setArg(rootDefault.getResultClass());
            if (!multiple) {
                m_.setParam(_anaDoc.getAliasCharSequence());
                if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFile(_page.getCurrentFile());
                    badEl_.setIndexFile(getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrDefault()));
                    badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringUtil.join(rootDefault.getResultClass().getNames(),AND_ERR),
                            _anaDoc.getAliasCharSequence());
                    AnalyzingDoc.addError(badEl_, _page);
                }
            } else {
                IterableAnalysisResult it_ = ContextUtil.getCustomTypeBase(rootDefault.getResultClass().getNames(), _page);
                StringList candidates_ = it_.getClassName();
                if (!candidates_.onlyOneElt()) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFile(_page.getCurrentFile());
                    badEl_.setIndexFile(getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrDefault()));
                    badEl_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                            StringUtil.join(candidates_,AND_ERR));
                    AnalyzingDoc.addError(badEl_, _page);
                }
            }
        }
    }

    private void checkConverter(AnalyzingDoc _anaDoc, AnalyzedPageEl _page, String _conValue, Mapping _m) {
        String string_ = _page.getAliasString();
        int offConvValue_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrConvertValue());
        _page.setSumOffset(offConvValue_);
        _page.zeroOffset();
        ClassMethodIdReturn classMethodIdReturn_ = OperationNode.tryGetDeclaredCustMethodSetIndexer(MethodAccessKind.INSTANCE, new StringList(_page.getGlobalClass()), _conValue.trim(), new StringList(string_), _page, new ScopeFilter(null, true, true, false, _page.getGlobalType().getRootBlock()));
        rootConverter = classMethodIdReturn_;
        String check_ = ResultText.check(_page, offConvValue_, classMethodIdReturn_);
        _m.setArg(check_);
        _m.setParam(resultInput.getOpsReadRoot().getResultClass());
        if (!AnaInherits.isCorrectOrNumbers(_m, _page)) {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFile(_page.getCurrentFile());
            badEl_.setIndexFile(offConvValue_);
            badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    check_,
                    StringUtil.join(rootRead.getResultClass().getNames(),AND_ERR));
            AnalyzingDoc.addError(badEl_, _page);
        }
    }

    private static String def(String _value) {
        int len_ = _value.length();
        StringBuilder str_ = new StringBuilder();
        int i_ = 0;
        while (i_ < len_) {
            char ch_ = _value.charAt(i_);
            if (ch_ == '\\' || ch_ == '"') {
                str_.append('\\').append(ch_);
            } else {
                str_.append(ch_);
            }
            i_++;
        }
        return str_.toString();
    }

    @Override
    public StringList processListAttributes(AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        StringList list_ = attrList(_anaDoc);
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrMap());
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrDefault());
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrMultiple());
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrName());
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrNi());
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrConvert());
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrConvertValue());
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrConvertFieldValue());
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrConvertField());
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrVarValue());
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrValidator());
        return list_;
    }

    public ResultExpression getResultExpressionMap() {
        return resultExpressionMap;
    }

    public OperationNode getRootValue() {
        return rootValue;
    }

    public OperationNode getRootRead() {
        return rootRead;
    }

    public ClassMethodIdReturn getRootConverterField() {
        return rootConverterField;
    }

    public ClassMethodIdReturn getRootConverter() {
        return rootConverter;
    }

    public ClassMethodIdReturn getRootConverterFieldValue() {
        return rootConverterFieldValue;
    }

    public String getIdName() {
        return idName;
    }

    public String getIdClass() {
        return idClass;
    }

    public String getClassName() {
        return className;
    }

    public Element getElt() {
        return getRead();
    }

    public OperationNode getRootDefault() {
        return rootDefault;
    }

    public OperationNode getRootMap() {
        return rootMap;
    }

    public boolean isArrayConverter() {
        return arrayConverter;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public ResultInput getResultInput() {
        return resultInput;
    }

}
