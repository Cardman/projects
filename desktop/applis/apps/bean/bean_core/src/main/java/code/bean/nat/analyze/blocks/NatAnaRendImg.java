package code.bean.nat.analyze.blocks;

import code.bean.nat.analyze.NatResultText;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;
import code.util.CustList;
import code.util.StringList;

public final class NatAnaRendImg extends NatAnaRendElement {

    private CustList<NatOperationNode> roots;

    private StringList texts = new StringList();
    NatAnaRendImg(Element _elt) {
        super(_elt);
    }

    @Override
    protected void processAttributes(NatAnaRendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        NatResultText res_ = new NatResultText();
        String pageName_ = _read.getAttribute(_anaDoc.getRendKeyWords().getAttrSrc());
        res_.buildAna(pageName_, _anaDoc, _page);
        roots = res_.getOpExpRoot();
        texts = res_.getTexts();
        _list.removeAllString(_anaDoc.getRendKeyWords().getAttrSrc());
    }

    public CustList<NatOperationNode> getRoots() {
        return roots;
    }

    public StringList getTexts() {
        return texts;
    }
}
