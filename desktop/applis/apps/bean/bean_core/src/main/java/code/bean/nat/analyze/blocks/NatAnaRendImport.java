package code.bean.nat.analyze.blocks;

import code.bean.nat.AbstractNatImpLgNames;
import code.bean.nat.analyze.NatResultText;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;
import code.util.CustList;
import code.util.StringList;

public final class NatAnaRendImport extends NatAnaRendParentBlock implements NatRendBuildEl {
    private final Element elt;

    private CustList<NatOperationNode> roots;

    private StringList texts = new StringList();

    private final AbstractNatImpLgNames natImpLgNames;
    NatAnaRendImport(Element _elt, AbstractNatImpLgNames _natImpLgNames) {
        super();
        elt = _elt;
        natImpLgNames = _natImpLgNames;
    }

    @Override
    public void buildExpressionLanguage(NatAnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        NatResultText res_ = new NatResultText();
        String pageName_ = elt.getAttribute(_anaDoc.getRendKeyWords().getAttrPage());
        res_.buildAna(pageName_, _anaDoc, _page);
        roots = res_.getOpExpRoot();
        texts = res_.getTexts();
    }

    public AbstractNatImpLgNames getNatImpLgNames() {
        return natImpLgNames;
    }

    public StringList getTexts() {
        return texts;
    }

    public CustList<NatOperationNode> getRoots() {
        return roots;
    }
}
