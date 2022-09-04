package code.bean.nat.analyze.blocks;

import code.bean.nat.analyze.NatResultText;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class NatAnaRendTitledAnchor extends NatAnaRendElement {
    private CustList<NatOperationNode> roots;
    private NatOperationNode root;
    private StringList texts = new StringList();
    private StringList varNames = new StringList();

    private StringMap<String> preformatted;
    NatAnaRendTitledAnchor(Element _elt) {
        super(_elt);
    }

    void titled(Element _read, StringList _list, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        roots = new CustList<NatOperationNode>();
        NatResultText res_ = NatResultText.buildAnchor(_read, _list, _anaDoc, _page);
        varNames = res_.getVarNames();
        root = res_.getOpExpAnchorRoot();
        roots = res_.getOpExpRoot();
        texts = res_.getTexts();
        _list.removeAllString(_anaDoc.getRendKeyWords().getAttrValue());
        String value_ = _read.getAttribute(_anaDoc.getRendKeyWords().getAttrValue());
        preformatted = AnaRendBlockHelp.getPre(value_, _anaDoc);
        for (EntryCust<String,String> e: preformatted.entryList()) {
            e.setValue(DocumentBuilder.transformSpecialChars(e.getValue(), _read.hasAttribute(_anaDoc.getRendKeyWords().getAttrEscapedAmp())));
        }
        _list.removeAllString(_anaDoc.getRendKeyWords().getAttrTitle());
    }

    public StringMap<String> getPreformatted() {
        return preformatted;
    }

    public StringList getTexts() {
        return texts;
    }

    public CustList<NatOperationNode> getRoots() {
        return roots;
    }

    public StringList getVarNames() {
        return varNames;
    }

    public NatOperationNode getRoot() {
        return root;
    }
}
