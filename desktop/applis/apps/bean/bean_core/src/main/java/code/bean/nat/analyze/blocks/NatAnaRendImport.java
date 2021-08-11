package code.bean.nat.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.bean.nat.analyze.NatResultText;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.analyze.blocks.AnaRendParentBlock;
import code.sml.Element;
import code.util.CustList;
import code.util.StringList;

public final class NatAnaRendImport extends AnaRendParentBlock {
    private final Element elt;

    private CustList<NatOperationNode> roots;

    private StringList texts = new StringList();

    private final int pageOffset;
    NatAnaRendImport(Element _elt, OffsetStringInfo _page, int _offset) {
        super(_offset);
        pageOffset = _page.getOffset();
        elt = _elt;
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        NatResultText res_ = new NatResultText();
        _page.setGlobalOffset(pageOffset);
        _page.zeroOffset();
        String pageName_ = elt.getAttribute(_anaDoc.getRendKeyWords().getAttrPage());
        res_.buildAna(pageName_, _anaDoc, _page);
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

    public CustList<NatOperationNode> getRoots() {
        return roots;
    }
}
