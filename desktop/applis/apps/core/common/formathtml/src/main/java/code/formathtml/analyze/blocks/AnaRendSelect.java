package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.CommentDelimiters;
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
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class AnaRendSelect extends AnaRendParentBlock implements AnaRendBuildEl {
    private OperationNode rootRead;
    private OperationNode rootValue;
    private final ResultExpression resultExpressionMap = new ResultExpression();
    private OperationNode rootMap;
    private OperationNode rootDefault;
    private ClassMethodIdReturn rootConverter;
    private ClassMethodIdReturn rootConverterField;
    private ClassMethodIdReturn rootConverterFieldValue;
    private final StringMap<ResultExpression> attributesText = new StringMap<ResultExpression>();
    private final StringMap<ResultExpression> attributes = new StringMap<ResultExpression>();
    private String idClass = EMPTY_STRING;
    private String idName = EMPTY_STRING;
    private final Element elt;
    private boolean multiple;
    private String className = EMPTY_STRING;
    private boolean arrayConverter;
    private final ResultInput resultInput;

    public AnaRendSelect(Element _elt, int _offset) {
        super(_offset);
        elt = _elt;
        resultInput = new ResultInput();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        resultInput.build(this, elt,_anaDoc.getRendKeyWords().getAttrVarValue(), _anaDoc, _page);
        rootRead = resultInput.getOpsReadRoot();
        rootValue = resultInput.getOpsValueRoot();
        idClass = resultInput.getIdClass();
        idName = resultInput.getIdName();
        className = resultInput.getClassName();


        for (EntryCust<String,ResultExpression> e: attributesText.entryList()) {
            String attr_ = elt.getAttribute(e.getKey());
            int rowsGrId_ = getAttributeDelimiter(e.getKey());
            _page.setGlobalOffset(rowsGrId_);
            _page.zeroOffset();
            RenderAnalysis.getRootAnalyzedOperations(attr_,0,_anaDoc,_page,e.getValue());
        }

        multiple = elt.hasAttribute(_anaDoc.getRendKeyWords().getAttrMultiple());
        String map_ = elt.getAttribute(_anaDoc.getRendKeyWords().getAttrMap());
        int offMap_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrMap());
        _page.setGlobalOffset(offMap_);
        _page.zeroOffset();
        rootMap = RenderAnalysis.getRootAnalyzedOperations(map_, 0, _anaDoc, _page,resultExpressionMap);
        String converterValue_ = elt.getAttribute(_anaDoc.getRendKeyWords().getAttrConvertValue());
        if (multiple) {
            if (!StringExpUtil.isDollarWord(converterValue_.trim())) {
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
            _page.setGlobalOffset(offConvValue_);
            _page.zeroOffset();
            ClassMethodIdReturn classMethodIdReturn_ = OperationNode.tryGetDeclaredCustMethodSetIndexer(MethodAccessKind.INSTANCE, new StringList(_page.getGlobalClass()), converterValue_.trim(), new StringList(string_), _page, new ScopeFilter(null, true, true, false, _page.getGlobalClass()));
            rootConverter = classMethodIdReturn_;
            String check_ = ResultText.check(_page, offConvValue_, classMethodIdReturn_);
            StringList names_ = resultInput.getOpsValueRoot().getResultClass().getNames();
            if (!resultInput.getOpsValueRoot().getResultClass().isVariable()) {
                IterableAnalysisResult it_ = ContextUtil.getCustomTypeBase(names_,_page);
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
        } else if (rootRead != null){
            Mapping m_ = new Mapping();
            m_.setArg(resultInput.getOpsReadRoot().getResultClass());
            m_.setParam(_anaDoc.getAliasCharSequence());
            if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
                if (!StringExpUtil.isDollarWord(converterValue_.trim())) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFile(_page.getCurrentFile());
                    badEl_.setIndexFile(getOffset());
                    badEl_.buildError(_anaDoc.getRendAnalysisMessages().getEmptyAttr(),
                            _anaDoc.getRendKeyWords().getAttrConvertValue());
                    AnalyzingDoc.addError(badEl_, _page);
                }
                String string_ = _page.getAliasString();
                int offConvValue_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrConvertValue());
                _page.setGlobalOffset(offConvValue_);
                _page.zeroOffset();
                ClassMethodIdReturn classMethodIdReturn_ = OperationNode.tryGetDeclaredCustMethodSetIndexer(MethodAccessKind.INSTANCE, new StringList(_page.getGlobalClass()), converterValue_.trim(), new StringList(string_), _page, new ScopeFilter(null, true, true, false, _page.getGlobalClass()));
                rootConverter = classMethodIdReturn_;
                String check_ = ResultText.check(_page, offConvValue_, classMethodIdReturn_);
                m_.setArg(check_);
                m_.setParam(resultInput.getOpsReadRoot().getResultClass());
                if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFile(_page.getCurrentFile());
                    badEl_.setIndexFile(offConvValue_);
                    badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            check_,
                            StringUtil.join(rootRead.getResultClass().getNames(),AND_ERR));
                    AnalyzingDoc.addError(badEl_, _page);
                }
            } else if (StringExpUtil.isDollarWord(converterValue_.trim())) {
                String string_ = _page.getAliasString();
                int offConvValue_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrConvertValue());
                _page.setGlobalOffset(offConvValue_);
                _page.zeroOffset();
                ClassMethodIdReturn classMethodIdReturn_ = OperationNode.tryGetDeclaredCustMethodSetIndexer(MethodAccessKind.INSTANCE, new StringList(_page.getGlobalClass()), converterValue_.trim(), new StringList(string_), _page, new ScopeFilter(null, true, true, false, _page.getGlobalClass()));
                rootConverter = classMethodIdReturn_;
                String check_ = ResultText.check(_page, offConvValue_, classMethodIdReturn_);
                m_.setArg(check_);
                m_.setParam(resultInput.getOpsReadRoot().getResultClass());
                if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFile(_page.getCurrentFile());
                    badEl_.setIndexFile(offConvValue_);
                    badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            check_,
                            StringUtil.join(rootRead.getResultClass().getNames(),AND_ERR));
                    AnalyzingDoc.addError(badEl_, _page);
                }
            }
        }
        String converterField_ = elt.getAttribute(_anaDoc.getRendKeyWords().getAttrConvertField());
        if (StringExpUtil.isDollarWord(converterField_.trim())) {
            String object_ = _page.getAliasObject();
            int offConvValue_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrConvertField());
            _page.setGlobalOffset(offConvValue_);
            _page.zeroOffset();
            ClassMethodIdReturn classMethodIdReturn_ = OperationNode.tryGetDeclaredCustMethodSetIndexer(MethodAccessKind.INSTANCE, new StringList(_page.getGlobalClass()), converterField_.trim(), new StringList(object_), _page, new ScopeFilter(null, true, true, false, _page.getGlobalClass()));
            rootConverterField = classMethodIdReturn_;
            String check_ = ResultText.check(_page, offConvValue_, classMethodIdReturn_);
            Mapping m_ = new Mapping();
            m_.setArg(check_);
            m_.setParam(_anaDoc.getAliasCharSequence());
            if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFile(_page.getCurrentFile());
                badEl_.setIndexFile(offConvValue_);
                badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        check_,
                        _anaDoc.getAliasCharSequence());
                AnalyzingDoc.addError(badEl_, _page);
            }
        }
        String converterFieldValue_ = elt.getAttribute(_anaDoc.getRendKeyWords().getAttrConvertFieldValue());
        if (StringExpUtil.isDollarWord(converterFieldValue_.trim())) {
            String object_ = _page.getAliasObject();
            int offConvValue_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrConvertFieldValue());
            _page.setGlobalOffset(offConvValue_);
            _page.zeroOffset();
            ClassMethodIdReturn classMethodIdReturn_ = OperationNode.tryGetDeclaredCustMethodSetIndexer(MethodAccessKind.INSTANCE, new StringList(_page.getGlobalClass()), converterFieldValue_.trim(), new StringList(object_), _page, new ScopeFilter(null, true, true, false, _page.getGlobalClass()));
            rootConverterFieldValue = classMethodIdReturn_;
            String check_ = ResultText.check(_page, offConvValue_, classMethodIdReturn_);
            Mapping m_ = new Mapping();
            m_.setArg(check_);
            m_.setParam(_anaDoc.getAliasCharSequence());
            if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFile(_page.getCurrentFile());
                badEl_.setIndexFile(offConvValue_);
                badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        check_,
                        _anaDoc.getAliasCharSequence());
                AnalyzingDoc.addError(badEl_, _page);
            }
        }
        String default_ = elt.getAttribute(_anaDoc.getRendKeyWords().getAttrDefault());
        if (!default_.isEmpty()) {
            String mName_ = elt.getAttribute(_anaDoc.getRendKeyWords().getAttrConvert());
            if (!StringExpUtil.isDollarWord(mName_.trim())) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFile(_page.getCurrentFile());
                badEl_.setIndexFile(getOffset());
                badEl_.buildError(_anaDoc.getRendAnalysisMessages().getEmptyAttr(),
                        _anaDoc.getRendKeyWords().getAttrConvert());
                AnalyzingDoc.addError(badEl_, _page);
            }
            String concat_ = StringUtil.concat(mName_,LEFT_PAR,STR,default_,STR,RIGHT_PAR);
            int offConvValue_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrConvert());
            _page.setGlobalOffset(offConvValue_);
            _page.zeroOffset();
            StringComment strCom_ = new StringComment(concat_,new CustList<CommentDelimiters>());
            _page.getCurrentParts().addAllElts(strCom_.getStringParts());
            rootDefault = RenderAnalysis.getRootAnalyzedOperations(concat_, 0, _anaDoc, _page);
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
                IterableAnalysisResult it_ = ContextUtil.getCustomTypeBase(rootDefault.getResultClass().getNames(),_page);
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
        for (EntryCust<String,ResultExpression> e: attributes.entryList()) {
            String attr_ = elt.getAttribute(e.getKey());
            int rowsGrId_ = getAttributeDelimiter(e.getKey());
            _page.setGlobalOffset(rowsGrId_);
            _page.zeroOffset();
            RenderAnalysis.getRootAnalyzedOperations(attr_,0,_anaDoc,_page,e.getValue());
        }
    }

    public ResultExpression getResultExpressionMap() {
        return resultExpressionMap;
    }

    public StringMap<ResultExpression> getAttributesText() {
        return attributesText;
    }

    public StringMap<ResultExpression> getAttributes() {
        return attributes;
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
        return elt;
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
