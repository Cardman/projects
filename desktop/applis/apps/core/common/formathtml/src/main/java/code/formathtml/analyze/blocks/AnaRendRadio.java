package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.util.ScopeFilter;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.RenderAnalysis;
import code.sml.Element;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AnaRendRadio extends AnaRendInput implements AnaRendElementAttr {

    private ClassMethodIdReturn rootConverterFieldValue;
    private final ResultExpression expRad = new ResultExpression();

    private OperationNode rootRadio;
    AnaRendRadio(Element _elt, int _offset) {
        super(_elt, _offset);
    }

    @Override
    public void processAttributes(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        String varValue_ = getRead().getAttribute(_anaDoc.getRendKeyWords().getAttrNr());
        if (!varValue_.trim().isEmpty()) {
            _page.setSumOffset(expRad.getSumOffset());
            _page.zeroOffset();
            rootRadio = RenderAnalysis.getRootAnalyzedOperations(0, _anaDoc, _page,expRad);
        }
        processAnaInput(getRead(), _anaDoc, _page);
        String converterFieldValue_ = getRead().getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrConvertFieldValue()));
        if (StringExpUtil.isDollarWord(converterFieldValue_.trim())) {
            String object_ = _page.getAliasObject();
            int attr_ = getAttributeDelimiter(StringUtil.concat(_anaDoc.getPrefix(), _anaDoc.getRendKeyWords().getAttrConvertFieldValue()));
            _page.setSumOffset(attr_);
            _page.zeroOffset();
            ClassMethodIdReturn classMethodIdReturn_ = OperationNode.tryGetDeclaredCustMethodSetIndexer(MethodAccessKind.INSTANCE, new StringList(_page.getGlobalClass()), converterFieldValue_.trim(), new StringList(object_), _page, new ScopeFilter(null, true, true, false, _page.getGlobalType().getRootBlock()));
            rootConverterFieldValue = classMethodIdReturn_;
            checkCharSeq(_anaDoc, _page, attr_, classMethodIdReturn_);
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
