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
import code.formathtml.analyze.ResultText;
import code.sml.Element;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AnaRendRadio extends AnaRendInput {

    private ClassMethodIdReturn rootConverterFieldValue;
    private final ResultExpression expRad = new ResultExpression();

    private OperationNode rootRadio;
    AnaRendRadio(Element _elt, int _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(AnaRendDocumentBlock _doc, Element _read, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        String varValue_ = getRead().getAttribute(_anaDoc.getRendKeyWords().getAttrNr());
        if (!varValue_.trim().isEmpty()) {
            int offVarValue_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrNr());
            _page.setGlobalOffset(offVarValue_);
            _page.zeroOffset();
            rootRadio = RenderAnalysis.getRootAnalyzedOperations(varValue_, 0, _anaDoc, _page,expRad);
        }
        processAnaInput(_read, _anaDoc, _page);
        String converterFieldValue_ = _read.getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrConvertFieldValue()));
        if (StringExpUtil.isDollarWord(converterFieldValue_.trim())) {
            String object_ = _page.getAliasObject();
            int attr_ = getAttributeDelimiter(StringUtil.concat(_anaDoc.getPrefix(), _anaDoc.getRendKeyWords().getAttrConvertFieldValue()));
            _page.setGlobalOffset(attr_);
            _page.zeroOffset();
            ClassMethodIdReturn classMethodIdReturn_ = OperationNode.tryGetDeclaredCustMethodSetIndexer(MethodAccessKind.INSTANCE, new StringList(_page.getGlobalClass()), converterFieldValue_.trim(), new StringList(object_), _page, new ScopeFilter(null, true, true, false, _page.getGlobalClass()));
            rootConverterFieldValue = classMethodIdReturn_;
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
    }
    @Override
    public StringList processListAttributes(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        StringList list_ = attrList(_anaDoc);
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrChecked());
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrValue());
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrName());
        list_.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrClassName()));
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrNi());
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrNr());
        list_.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrConvertValue()));
        list_.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrConvertFieldValue()));
        list_.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrConvertField()));
        list_.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrVarValue()));
        list_.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrValidator()));
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrType());
        return list_;
    }

    public ResultExpression getExpRad() {
        return expRad;
    }


    public OperationNode getRootRadio() {
        return rootRadio;
    }

    public ClassMethodIdReturn getRootConverterFieldValue() {
        return rootConverterFieldValue;
    }

}
