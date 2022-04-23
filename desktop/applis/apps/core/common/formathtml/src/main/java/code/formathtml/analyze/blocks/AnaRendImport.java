package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;

public final class AnaRendImport extends AnaRendParentBlock implements AnaRendBuildEl {
    private final Element elt;

    private final int pageOffset;
    private OperationNode rootPage;

    private final ResultExpression resultExpressionPage = new ResultExpression();

    AnaRendImport(Element _elt, OffsetStringInfo _page, int _offset) {
        super(_offset);
        pageOffset = _page.getOffset();
        elt = _elt;
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.setGlobalOffset(pageOffset);
        _page.zeroOffset();
        String pageName_ = elt.getAttribute(_anaDoc.getRendKeyWords().getAttrPage());
        rootPage = AnaRendElement.getRootAnalyzedOperations(pageName_,0,_anaDoc,_page,resultExpressionPage);
    }

    public int getPageOffset() {
        return pageOffset;
    }

    public Element getElt() {
        return elt;
    }

    public OperationNode getRootPage() {
        return rootPage;
    }

    public ResultExpression getResultExpressionPage() {
        return resultExpressionPage;
    }
}
