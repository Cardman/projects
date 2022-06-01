package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.analyze.ResultText;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class AnaRendTitledAnchor extends AnaRendElement implements AnaRendElementAttr {

    private final StringMap<ResultExpression> opExpTitle = new StringMap<ResultExpression>();

    private final ResultText res = new ResultText();
    private StringMap<String> preformatted;
    private StringMap<ResultExpression> results = new StringMap<ResultExpression>();

    AnaRendTitledAnchor(Element _elt, int _offset) {
        super(_elt, _offset);
    }

    @Override
    public void processAttributes(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        ResultText.buildAnchor(this, getRead(), _anaDoc, _page,res);
        results = res.getResults();
        String value_ = getRead().getAttribute(_anaDoc.getRendKeyWords().getAttrValue());
        int offMessage_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrValue());
        preformatted = getPre(value_,offMessage_, _anaDoc, _page);
        if (preformatted.isEmpty()) {
            return;
        }
        for (EntryCust<String,String> e: preformatted.entryList()) {
            e.setValue(DocumentBuilder.transformSpecialChars(e.getValue(), getRead().hasAttribute(_anaDoc.getRendKeyWords().getAttrEscapedAmp())));
        }
        for (EntryCust<String,ResultExpression> e: opExpTitle.entryList()) {
            _page.setSumOffset(e.getValue().getSumOffset());
            _page.zeroOffset();
            RenderAnalysis.getRootAnalyzedOperations(0,_anaDoc,_page,e.getValue());
        }
    }

    public StringList titles(AnalyzingDoc _anaDoc) {
        String value_ = getRead().getAttribute(_anaDoc.getRendKeyWords().getAttrValue());
        StringMap<String> href_ = getPreQuick(value_, _anaDoc);
        if (href_.isEmpty()) {
            return new StringList();
        }
        return AnaRendLink.paramsList(_anaDoc,getRead());
    }

    public ResultText getRes() {
        return res;
    }

    @Override
    public StringList processListAttributes(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        StringList list_ = attrList(_anaDoc);
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrHref());
        list_.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrCommand()));
        list_.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrSgn()));
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrValue());
        String value_ = getRead().getAttribute(_anaDoc.getRendKeyWords().getAttrValue());
        StringMap<String> preQuick_ = getPreQuick(value_, _anaDoc);
        int i_ = IndexConstants.FIRST_INDEX;
        while (getRead().hasAttribute(StringUtil.concat(_anaDoc.getRendKeyWords().getAttrParam(),Long.toString(i_)))) {
            list_.removeAllString(StringUtil.concat(_anaDoc.getRendKeyWords().getAttrParam(),Long.toString(i_)));
            i_++;
        }
        i_ = IndexConstants.FIRST_INDEX;
        while (getRead().hasAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrParam(),Long.toString(i_)))) {
            list_.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrParam(),Long.toString(i_)));
            i_++;
        }
        if (preQuick_.isEmpty()) {
            return list_;
        }
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrTitle());
        return list_;
    }

    public StringMap<String> getPreformatted() {
        return preformatted;
    }

    public StringMap<ResultExpression> getOpExpTitle() {
        return opExpTitle;
    }

    public StringMap<ResultExpression> getResults() {
        return results;
    }
}
