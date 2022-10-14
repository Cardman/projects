package code.bean.nat.analyze.blocks;

import code.bean.nat.analyze.NatAnalyzingDoc;
import code.bean.nat.analyze.NatResultText;
import code.sml.Element;
import code.util.StringList;

public final class NatAnaRendAnchor extends NatAnaRendElement {
    private NatResultText resultText;

    NatAnaRendAnchor(Element _elt) {
        super(_elt);
    }

    void anchor(Element _read, StringList _list, NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        resultText = NatResultText.buildAnchor(_read, _list, _anaDoc, _page);
    }

    public NatResultText getResultText() {
        return resultText;
    }

}
