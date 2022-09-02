package code.bean.nat.analyze.blocks;

import code.bean.nat.analyze.NatResultText;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;
import code.util.CustList;
import code.util.StringList;

public final class NatAnaRendAnchor extends NatAnaRendElement {
    private NatOperationNode root;

    private StringList texts = new StringList();
    private StringList varNames = new StringList();
    private CustList<NatOperationNode> roots;

    NatAnaRendAnchor(Element _elt) {
        super(_elt);
    }

    @Override
    protected StringList processAttributes(NatAnaRendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        NatResultText res_ = NatResultText.buildAnchor(_read, _list, _anaDoc, _page);
        varNames = res_.getVarNames();
        root = res_.getOpExpAnchorRoot();
        roots = res_.getOpExpRoot();
        texts = res_.getTexts();
        return _list;
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
