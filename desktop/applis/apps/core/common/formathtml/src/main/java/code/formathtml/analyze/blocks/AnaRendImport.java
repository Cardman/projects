package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.formathtml.analyze.ResultText;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;
import code.util.CustList;
import code.util.StringList;

public final class AnaRendImport extends AnaRendParentBlock implements AnaRendBuildEl {
    private final Element elt;

    private CustList<OperationNode> roots;

    private StringList texts = new StringList();

    private final int pageOffset;
    private final ResultText res;
    AnaRendImport(Element _elt, OffsetStringInfo _page, int _offset) {
        super(_offset);
        pageOffset = _page.getOffset();
        elt = _elt;
        res = new ResultText();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.setGlobalOffset(pageOffset);
        _page.zeroOffset();
        String pageName_ = elt.getAttribute(_anaDoc.getRendKeyWords().getAttrPage());
        int rowsGrId_ = getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrPage());
        res.buildIdAna(pageName_, rowsGrId_, _anaDoc, _page);
        roots = res.getOpExpRoot();
        texts = res.getTexts();
    }

    public ResultText getRes() {
        return res;
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
