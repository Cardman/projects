package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.formathtml.Configuration;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.Element;
import code.util.StringList;

public final class AnaRendStdElement extends AnaRendElement {
    AnaRendStdElement(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(Configuration _cont, AnaRendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        if (StringList.quickEq(_read.getTagName(),StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getKeyWordParam()))) {
            _list.clear();
        }
        _list.removeAllString(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrBean()));
    }
}
