package code.formathtml.analyze.blocks;


import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;
import code.util.StringList;

public final class AnaRendEscImg extends AnaRendElement {

    AnaRendEscImg(Element _elt, int _offset) {
        super(_elt, _offset);
    }

    @Override
    public StringList processListAttributes(AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        return attrList(_anaDoc);
    }
}
