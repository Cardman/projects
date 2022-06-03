package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.util.ScopeFilter;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.ResultInput;
import code.sml.Element;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AnaRendTextArea extends AnaRendElement implements AnaRendBuildEl,AnaRendInputInt,AnaRendElementAttr {
    private OperationNode rootRead;
    private OperationNode rootValue;

    private ClassMethodIdReturn rootConverter;
    private ClassMethodIdReturn rootConverterField;

    private String idClass = EMPTY_STRING;
    private String idName = EMPTY_STRING;
    private String className = EMPTY_STRING;
    private final ResultInput resultInput;

    public AnaRendTextArea(Element _elt, int _offset) {
        super(_elt,_offset);
        resultInput = new ResultInput();
    }

    @Override
    public void processAttributes(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        resultInput.build(this, getElt(), StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrVarValue()), _anaDoc, _page);
        rootRead = resultInput.getOpsReadRoot();
        rootValue = resultInput.getOpsValueRoot();
        idClass = resultInput.getIdClass();
        idName = resultInput.getIdName();
        className = resultInput.getClassName();
        String converterValue_ = getElt().getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrConvertValue()));
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
                checkRead(_page, attr_, classMethodIdReturn_, resultInput);
            }
        }
        String converterField_ = getElt().getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrConvertField()));
        if (StringExpUtil.isDollarWord(converterField_.trim())) {
            String object_ = _page.getAliasObject();
            int attr_ = getAttributeDelimiter(StringUtil.concat(_anaDoc.getPrefix(), _anaDoc.getRendKeyWords().getAttrConvertField()));
            _page.setSumOffset(attr_);
            _page.zeroOffset();
            ClassMethodIdReturn classMethodIdReturn_ = OperationNode.tryGetDeclaredCustMethodSetIndexer(MethodAccessKind.INSTANCE, new StringList(_page.getGlobalClass()), converterField_.trim(), new StringList(object_), _page, new ScopeFilter(null, true, true, false, _page.getGlobalClass()));
            rootConverterField = classMethodIdReturn_;
            checkCharSeq(_anaDoc, _page, attr_, classMethodIdReturn_);
        }
    }

    @Override
    public StringList processListAttributes(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        StringList list_ = attrList(_anaDoc);
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrName());
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrNi());
        list_.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrConvertValue()));
        list_.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrConvertField()));
        list_.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrVarValue()));
        list_.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrValidator()));
        return list_;
    }
    public Element getElt() {
        return getRead();
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

    public ResultInput getResultInput() {
        return resultInput;
    }

}
