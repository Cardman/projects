package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class AnaRendSpan extends AnaRendElement {
    private StringMap<String> formatted=new StringMap<String>();

    private OperationNode rootFor;

    private final ResultExpression resultExpressionFor = new ResultExpression();
    private int offFor;

    public AnaRendSpan(Element _elt, int _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(AnaRendDocumentBlock _doc, Element _read, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        offFor = resultExpressionFor.getSumOffset();
        _page.setSumOffset(resultExpressionFor.getSumOffset());
        _page.zeroOffset();
        rootFor = getRootAnalyzedOperations(0,_anaDoc,_page,resultExpressionFor);
        for (String l: _anaDoc.getLanguages()) {
            formatted.addEntry(l,EMPTY_STRING);
        }
        String valueMessage_ = _read.getAttribute(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrValueMessage()));
        if (!valueMessage_.isEmpty()) {
            int offMessage_ = getAttributeDelimiter(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrValueMessage()));
            formatted = getPre(valueMessage_,offMessage_, _anaDoc, _page);
        }
    }

    @Override
    public StringList processListAttributes(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        StringList list_ = attrList(_anaDoc);
        list_.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrFor()));
        list_.removeAllString(StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrValueMessage()));
        return list_;
    }

    public int getOffFor() {
        return offFor;
    }

    public OperationNode getRootFor() {
        return rootFor;
    }

    public ResultExpression getResultExpressionFor() {
        return resultExpressionFor;
    }

    public StringMap<String> getFormatted() {
        return formatted;
    }
}
