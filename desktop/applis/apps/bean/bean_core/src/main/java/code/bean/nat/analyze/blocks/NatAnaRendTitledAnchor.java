package code.bean.nat.analyze.blocks;

import code.bean.nat.analyze.NatAnalyzingDoc;
import code.bean.nat.analyze.NatResultText;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class NatAnaRendTitledAnchor extends NatAnaRendElement {
    private NatResultText resultText;

    private StringMap<String> preformatted;
    NatAnaRendTitledAnchor(Element _elt) {
        super(_elt);
    }

    void titled(Element _read, StringList _list, NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        resultText = NatResultText.buildAnchor(_read, _list, _anaDoc, _page);
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

    public NatResultText getResultText() {
        return resultText;
    }

}
