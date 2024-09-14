package code.bean.nat.analyze.blocks;

import code.sml.Element;
import code.sml.NatAnalyzingDoc;
import code.util.StringList;
import code.util.StringMap;

public final class NatAnaRendTitledAnchor extends NatAnaRendElementSpec {
    private NatResultTextForm resultText;

    private StringMap<String> preformatted;
    public NatAnaRendTitledAnchor(Element _elt) {
        super(_elt);
    }

    void titled(Element _read, StringList _list, NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        resultText = NatAnaRendElementSpec.buildAnchor(_read, _list, _anaDoc, _page);
        _list.removeAllString(_anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrValue());
        String value_ = _read.getAttribute(_anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrValue());
        preformatted = AnaRendBlockHelp.getPre(value_, _anaDoc);
        _list.removeAllString(_anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrTitle());
    }

    public StringMap<String> getPreformatted() {
        return preformatted;
    }

    public NatResultTextForm getResultText() {
        return resultText;
    }

}
