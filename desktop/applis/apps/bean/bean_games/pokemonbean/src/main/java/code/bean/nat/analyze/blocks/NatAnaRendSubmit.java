package code.bean.nat.analyze.blocks;

import code.sml.Element;
import code.sml.NatAnalyzingDoc;
import code.util.StringList;
import code.util.StringMap;

public final class NatAnaRendSubmit extends NatAnaRendElementSpec {

    private StringMap<String> preformatted;
    public NatAnaRendSubmit(Element _elt) {
        super(_elt);
    }

    void submit(Element _read, StringList _list, NatAnalyzingDoc _anaDoc) {
        _list.removeAllString(_anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrMessage());
        String value_ = _read.getAttribute(_anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrMessage());
        preformatted = AnaRendBlockHelp.getPre(value_, _anaDoc);
        _list.removeAllString(_anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrValue());
        _list.removeAllString(_anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrType());
    }

    public StringMap<String> getPreformatted() {
        return preformatted;
    }

}
