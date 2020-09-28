package code.formathtml.analyze.blocks;


import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.formathtml.Configuration;
import code.formathtml.util.AnalyzingDoc;
import code.sml.Element;
import code.util.StringList;

public final class AnaRendEscImg extends AnaRendElement {

    AnaRendEscImg(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(Configuration _cont, AnaRendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {

    }
}
