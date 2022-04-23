package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.ResultText;
import code.sml.Element;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class AnaRendAnchor extends AnaRendElement {

    private final ResultText res = new ResultText();
    private StringMap<ResultExpression> results = new StringMap<ResultExpression>();
    private ClassMethodIdReturn resultAnc;

    AnaRendAnchor(Element _elt, int _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(AnaRendDocumentBlock _doc, Element _read, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        ResultText.buildAnchor(this, _read, _anaDoc, _page,res);
        results = res.getResults();
        resultAnc = res.getResultAnc();
    }

    @Override
    public StringList processListAttributes(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        StringList list_ = attrList(_anaDoc);
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrHref());
        list_.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrCommand()));
        list_.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrSgn()));
        int i_ = IndexConstants.FIRST_INDEX;
        while (getRead().hasAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrParam(),Long.toString(i_)))) {
            list_.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrParam(),Long.toString(i_)));
            i_++;
        }
        return list_;
    }

    public ResultText getRes() {
        return res;
    }

    public StringMap<ResultExpression> getResults() {
        return results;
    }

    public ClassMethodIdReturn getResultAnc() {
        return resultAnc;
    }

}
