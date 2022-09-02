package code.bean.nat.analyze.blocks;

import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;
import code.util.StringList;

public final class NatAnaRendEscImg extends NatAnaRendElement {

    NatAnaRendEscImg(Element _elt) {
        super(_elt);
    }

    @Override
    protected StringList processAttributes(NatAnaRendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        return _list;
    }
}
