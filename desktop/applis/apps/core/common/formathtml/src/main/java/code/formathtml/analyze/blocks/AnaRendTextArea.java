package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.util.ScopeFilter;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.analyze.ResultInput;
import code.formathtml.analyze.ResultText;
import code.sml.Element;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class AnaRendTextArea extends AnaRendParentBlock implements AnaRendBuildEl {
    private OperationNode rootRead;
    private OperationNode rootValue;

    private ClassMethodIdReturn rootConverter;
    private ClassMethodIdReturn rootConverterField;
    private final StringMap<ResultExpression> attributesText = new StringMap<ResultExpression>();
    private final StringMap<ResultExpression> attributes = new StringMap<ResultExpression>();

    private String idClass = EMPTY_STRING;
    private String idName = EMPTY_STRING;
    private String className = EMPTY_STRING;
    private final Element elt;
    private final ResultInput resultInput;

    public AnaRendTextArea(Element _elt, int _offset) {
        super(_offset);
        elt = _elt;
        resultInput = new ResultInput();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        resultInput.build(this, elt, StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrVarValue()), _anaDoc, _page);
        rootRead = resultInput.getOpsReadRoot();
        rootValue = resultInput.getOpsValueRoot();
        idClass = resultInput.getIdClass();
        idName = resultInput.getIdName();
        className = resultInput.getClassName();
        String converterValue_ = elt.getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrConvertValue()));
        if (rootRead != null){
            Mapping m_ = new Mapping();
            m_.setArg(resultInput.getOpsReadRoot().getResultClass());
            m_.setParam(_anaDoc.getAliasCharSequence());
            if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
                if (!StringExpUtil.isDollarWord(converterValue_.trim())) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFile(_page.getCurrentFile());
                    badEl_.setIndexFile(getOffset());
                    badEl_.buildError(_anaDoc.getRendAnalysisMessages().getEmptyAttr(),
                            StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrConvertValue()));
                    AnalyzingDoc.addError(badEl_, _page);
                }
                String string_ = _page.getAliasString();
                int attr_ = getAttributeDelimiter(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrConvertValue()));
                _page.setSumOffset(attr_);
                _page.zeroOffset();
                ClassMethodIdReturn classMethodIdReturn_ = OperationNode.tryGetDeclaredCustMethodSetIndexer(MethodAccessKind.INSTANCE, new StringList(_page.getGlobalClass()), converterValue_.trim(), new StringList(string_), _page, new ScopeFilter(null, true, true, false, _page.getGlobalClass()));
                rootConverter = classMethodIdReturn_;
                String check_ = ResultText.check(_page, attr_, classMethodIdReturn_);
                m_.setArg(check_);
                m_.setParam(resultInput.getOpsReadRoot().getResultClass());
                if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFile(_page.getCurrentFile());
                    badEl_.setIndexFile(attr_);
                    badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            check_,
                            StringUtil.join(rootRead.getResultClass().getNames(),AND_ERR));
                    AnalyzingDoc.addError(badEl_, _page);
                }
            }
        }
        String converterField_ = elt.getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrConvertField()));
        if (StringExpUtil.isDollarWord(converterField_.trim())) {
            String object_ = _page.getAliasObject();
            int attr_ = getAttributeDelimiter(StringUtil.concat(_anaDoc.getPrefix(), _anaDoc.getRendKeyWords().getAttrConvertField()));
            _page.setSumOffset(attr_);
            _page.zeroOffset();
            ClassMethodIdReturn classMethodIdReturn_ = OperationNode.tryGetDeclaredCustMethodSetIndexer(MethodAccessKind.INSTANCE, new StringList(_page.getGlobalClass()), converterField_.trim(), new StringList(object_), _page, new ScopeFilter(null, true, true, false, _page.getGlobalClass()));
            rootConverterField = classMethodIdReturn_;
            String check_ = ResultText.check(_page, attr_, classMethodIdReturn_);
            Mapping m_ = new Mapping();
            m_.setArg(check_);
            m_.setParam(_anaDoc.getAliasCharSequence());
            if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFile(_page.getCurrentFile());
                badEl_.setIndexFile(attr_);
                badEl_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        check_,
                        _anaDoc.getAliasCharSequence());
                AnalyzingDoc.addError(badEl_, _page);
            }
        }
        for (EntryCust<String,ResultExpression> e: attributesText.entryList()) {
            _page.setSumOffset(e.getValue().getSumOffset());
            _page.zeroOffset();
            RenderAnalysis.getRootAnalyzedOperations(0,_anaDoc,_page,e.getValue());
        }
        for (EntryCust<String,ResultExpression> e: attributes.entryList()) {
            _page.setSumOffset(e.getValue().getSumOffset());
            _page.zeroOffset();
            RenderAnalysis.getRootAnalyzedOperations(0,_anaDoc,_page,e.getValue());
        }
    }

    public Element getElt() {
        return elt;
    }

    public String getClassName() {
        return className;
    }

    public String getIdClass() {
        return idClass;
    }

    public String getIdName() {
        return idName;
    }

    public ClassMethodIdReturn getRootConverter() {
        return rootConverter;
    }

    public ClassMethodIdReturn getRootConverterField() {
        return rootConverterField;
    }

    public OperationNode getRootRead() {
        return rootRead;
    }

    public OperationNode getRootValue() {
        return rootValue;
    }

    public StringMap<ResultExpression> getAttributes() {
        return attributes;
    }

    public StringMap<ResultExpression> getAttributesText() {
        return attributesText;
    }

    public ResultInput getResultInput() {
        return resultInput;
    }

}
