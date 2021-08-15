package code.bean.nat.analyze.blocks;

import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.sml.Element;
import code.util.StringList;

public final class NatAnaRendEscImg extends NatAnaRendElement {

    NatAnaRendEscImg(Element _elt, int _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(AnaRendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        //
    }
}
