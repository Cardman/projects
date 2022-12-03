package code.bean.nat.analyze.blocks;

import code.sml.NatAnalyzingDoc;
import code.sml.Element;
import code.util.StringList;

public final class NatAnaRendAnchor extends NatAnaRendElementSpec {
    private NatResultTextForm resultText;

    public NatAnaRendAnchor(Element _elt) {
        super(_elt);
    }

    void anchor(Element _read, StringList _list, NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        resultText = NatAnaRendElementSpec.buildAnchor(_read, _list, _anaDoc, _page);
    }

    public NatResultTextForm getResultText() {
        return resultText;
    }

}
