package code.bean.nat.analyze.blocks;

import code.bean.nat.analyze.NatAnalyzingDoc;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class NatAnaRendSubmit extends NatAnaRendElement {

    private StringMap<String> preformatted;
    NatAnaRendSubmit(Element _elt) {
        super(_elt);
    }

    void submit(Element _read, StringList _list, NatAnalyzingDoc _anaDoc) {
        _list.removeAllString(_anaDoc.getRendKeyWords().getAttrMessage());
        String value_ = _read.getAttribute(_anaDoc.getRendKeyWords().getAttrMessage());
        preformatted = AnaRendBlockHelp.getPre(value_, _anaDoc);
        for (EntryCust<String,String> e: preformatted.entryList()) {
            e.setValue(DocumentBuilder.transformSpecialChars(e.getValue(), _read.hasAttribute(_anaDoc.getRendKeyWords().getAttrEscapedAmp())));
        }
        _list.removeAllString(_anaDoc.getRendKeyWords().getAttrValue());
        _list.removeAllString(_anaDoc.getRendKeyWords().getAttrType());
    }

    public StringMap<String> getPreformatted() {
        return preformatted;
    }

}
