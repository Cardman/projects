package code.bean.nat.analyze.blocks;

import code.bean.nat.AbstractNatImpLgNames;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.bean.nat.analyze.NatResultText;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.analyze.blocks.AnaRendParentBlock;
import code.sml.Element;
import code.util.CustList;
import code.util.StringList;

public final class NatAnaRendImport extends AnaRendParentBlock implements NatRendBuildEl {
    private final Element elt;

    private CustList<NatOperationNode> roots;

    private StringList texts = new StringList();

    private final int pageOffset;
    private final AbstractNatImpLgNames natImpLgNames;
    NatAnaRendImport(Element _elt, OffsetStringInfo _page, int _offset, AbstractNatImpLgNames _natImpLgNames) {
        super(_offset);
        pageOffset = _page.getOffset();
        elt = _elt;
        natImpLgNames = _natImpLgNames;
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        NatResultText res_ = new NatResultText();
        String pageName_ = elt.getAttribute(_anaDoc.getRendKeyWords().getAttrPage());
        res_.buildAna(pageName_, _anaDoc, _page);
        roots = res_.getOpExpRoot();
        texts = res_.getTexts();
    }

    public AbstractNatImpLgNames getNatImpLgNames() {
        return natImpLgNames;
    }

    public int getPageOffset() {
        return pageOffset;
    }

    public StringList getTexts() {
        return texts;
    }

    public CustList<NatOperationNode> getRoots() {
        return roots;
    }
}
