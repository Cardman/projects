package code.bean.nat.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.bean.nat.analyze.NatResultText;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.sml.Element;
import code.util.CustList;
import code.util.StringList;

public final class NatAnaRendAnchor extends NatAnaRendElement {
    private NatOperationNode root;

    private StringList texts = new StringList();
    private StringList varNames = new StringList();
    private CustList<NatOperationNode> roots;

    NatAnaRendAnchor(Element _elt, int _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(AnaRendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        NatResultText res_ = NatResultText.buildAnchor(this, _read, _list, _anaDoc, _page);
        varNames = res_.getVarNames();
        root = res_.getOpExpAnchorRoot();
        roots = res_.getOpExpRoot();
        texts = res_.getTexts();
    }

    public StringList getVarNames() {
        return varNames;
    }

    public NatOperationNode getRoot() {
        return root;
    }

    public CustList<NatOperationNode> getRoots() {
        return roots;
    }

    public StringList getTexts() {
        return texts;
    }
}
