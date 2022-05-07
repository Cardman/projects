package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;
import code.util.StringList;

public final class AnaRendImg extends AnaRendElement {
    private OperationNode rootSrc;

    private final ResultExpression resultExpressionSrc = new ResultExpression();
    private int offSrc;
    public AnaRendImg(Element _elt, int _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(AnaRendDocumentBlock _doc, Element _read, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        offSrc = resultExpressionSrc.getSumOffset();
        _page.setSumOffset(resultExpressionSrc.getSumOffset());
        _page.zeroOffset();
        rootSrc = getRootAnalyzedOperations(0, _anaDoc, _page, resultExpressionSrc);
    }
    @Override
    public StringList processListAttributes(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        StringList list_ = attrList(_anaDoc);
        list_.removeAllString(_anaDoc.getRendKeyWords().getAttrSrc());
        return list_;
    }

    public ResultExpression getResultExpressionSrc() {
        return resultExpressionSrc;
    }

    public OperationNode getRootSrc() {
        return rootSrc;
    }

    public int getOffSrc() {
        return offSrc;
    }

}
