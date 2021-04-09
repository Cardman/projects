package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.formathtml.analyze.ResultText;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;
import code.util.CustList;
import code.util.StringList;

public final class AnaRendImport extends AnaRendParentBlock {
    private final Element elt;

    private CustList<OperationNode> roots;

    private StringList texts = new StringList();

    private final int pageOffset;
    AnaRendImport(Element _elt, OffsetStringInfo _page, int _offset) {
        super(_offset);
        pageOffset = _page.getOffset();
        elt = _elt;
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        ResultText res_ = new ResultText();
        _page.setGlobalOffset(pageOffset);
        _page.setOffset(0);
        String pageName_ = elt.getAttribute(_anaDoc.getRendKeyWords().getAttrPage());
        int rowsGrId_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrPage());
        res_.buildAna(pageName_, rowsGrId_, _anaDoc, _page);
        roots = res_.getOpExpRoot();
        texts = res_.getTexts();
    }

    public int getPageOffset() {
        return pageOffset;
    }

    public Element getElt() {
        return elt;
    }

    public StringList getTexts() {
        return texts;
    }

    public CustList<OperationNode> getRoots() {
        return roots;
    }
}
